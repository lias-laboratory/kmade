package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.expression.ExpressionConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Acteur;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Evenement;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEvent;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIteration;


/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public final class ExpressSimulation {
    private static Tache currentTask;
      
    private static ArrayList<User> myUserList = new ArrayList<User>();
       
    private static int currentUserList = 0;
    
    public static void setUserList(ArrayList<User> p) {
        myUserList = p;
    }
    
    public static User getCurrentUserList(int p) {
        currentUserList = p;
        return myUserList.get(currentUserList);
    }
    
    public static void clearUserList() {
        myUserList.clear();
    }
    
    public static Tache getCurrentTask() {
        return currentTask;
    }
             
    public static void setCurrentTask(Tache pcurrentTask) {
    	currentTask = pcurrentTask;
    }
           
           
    // Algorithmes pour la partie simulation du modele de taches K-MAD    
    public static ArrayList<TokenSimulation> startSimulation(Tache myTask) {
        currentTask = myTask;
        myTask.getStateSimulation().setPassive();
        ExpressSimulation.applyDecomposition(currentTask);
        ArrayList<TokenSimulation> toto = ExpressSimulation.searchActivableTask(currentTask);
        return toto;
    }

    public static ArrayList<TokenSimulation> continueSimulation() {
        ExpressSimulation.applyDecomposition(currentTask);
        ArrayList<TokenSimulation> toto = ExpressSimulation.searchActivableTask(currentTask);
        return toto;
    }

    public static ArrayList<TokenSimulation> searchActivableTask() {
   		return ExpressSimulation.searchActivableTask(currentTask);
    }
    
    public static ArrayList<TokenSimulation> searchActivableTask(Tache myTache) {
        ArrayList<TokenSimulation> myTacheList = new ArrayList<TokenSimulation>();
        
        // L'interruption est une affaire de parallélisme et d'entrelacement
        if (myTache.isInterruptible() && (!myTache.getStateSimulation().isTerminalState())) {            
            if (!myTache.getStateSimulation().isSuspended()) {
                if (myTache.getStateSimulation().isPassive() || myTache.getStateSimulation().isActive()) {
                    myTacheList.add(new TokenSimulation(myTache, TokenSimulation.INTERROMPRE));
                }
            } else {                                
                if (myTache.getExecutant().equals(Executant.SYS)) {
                    // Un exécutant système est interrompue automatiquement                    
                    // Vérifie avec la tache mere s'il existe une sous-tache de plus haute priorite
                    
                	// /!\ getMotherTask peut renvoy� null -> � modifier 
                	Tache myMother = myTache.getMotherTask();
                    boolean etat = false;
                    for (int i = 0; i < myMother.getFils().size() && !etat; i++) {
                        // Etat Terminal
                        if (!myMother.getFils().get(i).getStateSimulation().isTerminalState()) {
                            etat = myTache.getImportance().isMoreImportant(myMother.getFils().get(i).getImportance());
                        }
                    }
                    if (!etat) {
                        myTacheList.add(new TokenSimulation(myTache, TokenSimulation.REPRENDRE));
                    }
                } else {
                    // Les autres exécutants peuvent reprendre l'exécution d'une tâche.
                    if (myTache.isFacultatif()) {
                        myTacheList.add(new TokenSimulation(myTache, TokenSimulation.ABANDONNER));
                    }
                    myTacheList.add(new TokenSimulation(myTache, TokenSimulation.REPRENDRE));                    
                }
            }
        }
        
        // Leaf Task
        if (myTache.getDecomposition().equals((Decomposition.ELE))) {
            if (myTache.getStateSimulation().isPassive() || myTache.getStateSimulation().isActive()) {
                myTacheList.add(new TokenSimulation(myTache, TokenSimulation.EXECUTER));                
            }
            if (myTache.getStateSimulation().isPassive() && myTache.isFacultatif()) {
            	if (!myTache.isRoot() && !myTache.getMotherTask().getDecomposition().equals(Decomposition.ALT) && !myTache.getExecutant().equals(Executant.SYS)) {
            		myTacheList.add(new TokenSimulation(myTache, TokenSimulation.PASSER));
				}
            }
            return myTacheList;
        }
        
        // No Leaf Task
		if (myTache.getStateSimulation().isPassive()) {
			if (myTache.isFacultatif()) {
				if (!myTache.isRoot() && !myTache.getMotherTask().getDecomposition().equals(Decomposition.ALT) && !myTache.getExecutant().equals(Executant.SYS)) {
					myTacheList.add(new TokenSimulation(myTache, TokenSimulation.PASSER));
				}
			}
		}

		for (Tache current : myTache.getFils()) {
			if (!myTache.getStateSimulation().isSuspended()) {
				ArrayList<TokenSimulation> myList = ExpressSimulation.searchActivableTask(current);
				myTacheList.addAll(myList);
			}
		}
		 
		return myTacheList;
    }

    public static void initSimulation(Tache task) {
        task.getStateSimulation().setNotAccessible();
        task.getIteExpression().setInitIterationVariant();
        for (Tache current : task.getFils()) {
            ExpressSimulation.initSimulation(current);
        }
    }

    public static void initIteration(Tache task) {
        for (Tache current : task.getFils()) {
            ExpressSimulation.initSimulation(current);
        }
    }
    
    public static boolean isExecutableTask(boolean exe, boolean event, boolean pre, int value, Tache myCurrentTask) {        
        // Cette méthode s'occupe de gérer la première catégorie
        System.out.println(KMADEConstant.EXECUTE_TASK_TRAITEMENT_MESSAGE + " : " + myCurrentTask.getName());
        System.out.println(" * " + KMADEConstant.EXECUTION_CONSTRAINT_MESSAGE); 
        
        // Est-ce le bon utilisateur qui exécute.
        System.out.print("  - " + KMADEConstant.USER_EXECUTION_CONSTRAINT_MESSAGE + " : ");
        if (exe) {
        	boolean trouve = false;
        	boolean isSystem = myCurrentTask.getExecutant().equals(Executant.SYS);
        	
			if (myCurrentTask.getActeurs().size() != 0 && !isSystem) {				
				User selectUser = ExpressSimulation.getCurrentUserList(value);
				for (Acteur current : myCurrentTask.getActeurs()) {
					if (current.getUserRef().getName().equals(selectUser.getName())) {
						trouve = true;
						break;
					}
				}

				if (trouve) {
					System.out.println(selectUser.getName() + " " + KMADEConstant.CAN_EXECUTE_TASK_MESSAGE + " " + myCurrentTask.getName());
				} else {
					System.out.println(selectUser.getName() + " " + KMADEConstant.CAN_NOT_EXECUTE_TASK_MESSAGE + " " + myCurrentTask.getName());
			       	System.out.println("");
					return false;
				}
			} else {
				// La tâche n'est pas soumise à la contrainte d'un utilisateur.
				System.out.println(KMADEConstant.AUTHORIZED_EXECUTER_USER_MESSAGE);
			}
        } else {
       		System.out.println(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        // Est-ce le bon événement déclencheur.
        System.out.print("  - " + KMADEConstant.EVENT_TRIGGER_CONSTRAINT_MESSAGE + " : ");
        if (event) {
            Evenement eventTask = myCurrentTask.getDeclencheur();
            if (eventTask == null) {
                System.out.println(KMADEConstant.NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE);
            } else {
                if (ExpressEvent.isExistingEvent(eventTask)) {
                    System.out.println(eventTask.getName() + " " + KMADEConstant.CAN_TRIGGER_TASK_MESSAGE + " " + myCurrentTask.getName());
                } else {
                    System.out.println(eventTask.getName() + " " + KMADEConstant.CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE + " " + myCurrentTask.getName());
                }
            }          
        } else {
       		System.out.println(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        // La précondition est-elle respectée.
        System.out.print("  - " + KMADEConstant.PRECONDITION_CONSTRAINT_MESSAGE + " : ");
        if (pre) {
        		try {
        			if (myCurrentTask.getPreExpression().getNodeExpression() == null) {
        				System.out.print(myCurrentTask.getPreExpression().getName());
        			} else {
        				System.out.print(ExpressSimulation.getLinearExpressionWithUserValues(myCurrentTask.getPreExpression().getNodeExpression().getLinearExpression()));
                 }   

        			myCurrentTask.getPreExpression().getNodeExpression().evaluateNode(null);
				if ((Boolean) myCurrentTask.getPreExpression().getNodeExpression().getNodeValue()) {
					System.out.println(" -> " + KMADEConstant.PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE);
				} else {
					System.out.println(" -> " + KMADEConstant.PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE);
			        System.out.println("");
					return false;
				}
			} catch (SemanticUnknownException e) {
				System.out.println(" -> " + KMADEConstant.MISSING_USER_VALUE_MESSAGE);
		        System.out.println("");
				return false;
			} catch (SemanticErrorException e) {
				System.out.println(" -> " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE);
		        System.out.println("");
				return false;
			} catch (SemanticException e) {
				return false;
			}        
        } else {
        	System.out.println(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        return true;
    }
    
    public static boolean setExecuter(Tache myTask, boolean post, boolean iter, boolean trigevent, boolean event) {
        // Faire le traitement. Première catégorie, seconde catégorie et troisième catégorie.    
        
        if(ExpressIteration.isFinished(myTask)){
        	 myTask.getStateSimulation().setFinished();
        	 System.out.println(" * " + KMADEConstant.ACTION_CONSTRAINT_MESSAGE);
        	 System.out.println("  - " + KMADEConstant.ITERATION_FINISH_NO_ACTION);
        	return true;
        }else{
        	ExpressIteration.evaluateIteration(myTask);
        myTask.getMotherTask().getStateSimulation().setActive();
        
        // Post-traitement ...
        System.out.println(" * " + KMADEConstant.ACTION_CONSTRAINT_MESSAGE);
            // Evénement déclencheur
        if (event) {
            ExpressEvent.extractFiringEvent(myTask.getDeclencheur());
        }
        
            // Génération des Evénements
        System.out.print("  - " + KMADEConstant.GENERATE_EVENTS_CONSTRAINT_MESSAGE + " : ");
        if (trigevent) {
            String temp = ExpressEvent.generateFiringEvent(myTask);
            if (temp.length() == 0) {
                System.out.println(KMADEConstant.NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE);
            } else {
                System.out.println(temp);
            }
        } else {
        	System.out.println(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
            // Traitement des effetsdebord
        if (post) {            
            System.out.print("  - " + KMADEConstant.EFFETSDEBORD_CONSTRAINT_MESSAGE + " : " );
            
			if (myTask.getEffetsDeBordExpression().getNodeExpression() == null) {
				System.out.print(myTask.getEffetsDeBordExpression().getName());
			} else {
				System.out.print(ExpressSimulation.getLinearExpressionWithUserValues(myTask.getEffetsDeBordExpression().getNodeExpression().getLinearExpression()));
			}   

        	try {
        		myTask.getEffetsDeBordExpression().getNodeExpression().evaluateNode(null);
        		System.out.println(" -> " + KMADEConstant.EFFETSDEBORD_EXECUTED_STATE_MESSAGE);
        	} catch (SemanticErrorException e) {
        		System.out.println(" -> " + KMADEConstant.EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE + " , " + e.getMessage());
        		System.out.println("");
        		return false;
            } catch (SemanticUnknownException e) {
            	System.out.println(" -> " + KMADEConstant.EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE + " , " + e.getMessage());
            	System.out.println("");
            	return false;
            } catch (SemanticException e) {
            	System.out.println("");
            	return false;
            }
        	
        } else {
        	System.out.println("  - " + KMADEConstant.EFFETSDEBORD_CONSTRAINT_MESSAGE + " : " + KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        		// Etat de la tâche
        if (ExpressIteration.isFinished(myTask)) {
            myTask.getStateSimulation().setFinished();
        } else {
            myTask.getStateSimulation().setActive();
        }
        }
        System.out.println("");
        return true;
    }

    public static void setNoResumed(Tache myTache) {
    	myTache.getStateSimulation().setNoResumed();
    	ExpressSimulation.noResumedTasks(myTache);
    }
    
    public static void setPasser(Tache myTache) {
        myTache.getMotherTask().getStateSimulation().setActive();
        ExpressSimulation.passedTasks(myTache);
    }
    
    public static void setSuspend(Tache myTache) {
    	myTache.getStateSimulation().setSuspended();
    	ExpressSimulation.suspendSubTasks(myTache);
    }
    
    public static void setResume(Tache myTache) {
		myTache.getStateSimulation().setResume();
		myTache.getStateSimulation().resumeTag = true;
    	ExpressSimulation.resumeSubTasks(myTache);
    }

    private static void resumeSubTasks(Tache myTache) {
        for (Tache current : myTache.getFils()) {
    		if (current.getStateSimulation().isSuspended()) {
    			// Ne rien faire dans cette sous-tache. Elle est encore suspendue.
    		} else {
    			current.getStateSimulation().setSuspendedSubTaskOff();
    			resumeSubTasks(current);
    		}
    	}
    }
    
    private static void suspendSubTasks(Tache myTache) {
    	myTache.getStateSimulation().setSuspendedSubTaskOn();
    	for (Tache current : myTache.getFils()) {
    		suspendSubTasks(current);
    	}
    }
    
    private static void noResumedTasks(Tache myTache) {
    	myTache.getStateSimulation().setNoResumed();
    	for (Tache current : myTache.getFils()) {
    		noResumedTasks(current);
    	}
    }
    
    private static void passedTasks(Tache myTache) {
        myTache.getStateSimulation().setPassed();
        for (Tache current : myTache.getFils()) {
            passedTasks(current);
        }
    }
    
    private static void finishedTasks(Tache myTache) {
    	myTache.getStateSimulation().setFinished();
    	for (Tache current : myTache.getFils()) {
    		finishedTasks(current);
    	}
    }

    private static void noAccessibledTasks(Tache myTache) {
        myTache.getStateSimulation().setNotAccessible();
        for (Tache current : myTache.getFils()) {
            noAccessibledTasks(current);
        }
    }
    
    // Construction des règles pour les opérateurs
    public static void applyDecomposition(Tache refTask) {
        if (refTask.getDecomposition().equals(Decomposition.SEQ)) {
            ExpressSimulation.sequenceOperatorEffects(refTask);
        } else if (refTask.getDecomposition().equals(Decomposition.ALT)) {
            ExpressSimulation.choiceOperatorEffects(refTask);
        } else if (refTask.getDecomposition().equals(Decomposition.PAR)) {
            ExpressSimulation.concurencyOperatorEffects(refTask);
        } else if (refTask.getDecomposition().equals(Decomposition.ET)) {
            ExpressSimulation.indenpendancyOrderOperatorEffects(refTask);
        } else if (refTask.getDecomposition().equals(Decomposition.ELE)) {
        } 
    }

    private static void sequenceOperatorEffects(Tache refTask) {
        // Le début : mettre la première tâche fille a Passive
        if (refTask.getStateSimulation().isPassive() && refTask.getFils().get(0).getStateSimulation().isNotAccessible()) {
            refTask.getFils().get(0).getStateSimulation().setPassive();
        }

        boolean finishedOne = false;
        boolean passeeOne = false;
        boolean activedOne = false;
        boolean noresumedOne = false;
        
        for (Tache current : refTask.getFils()) {
            if (activedOne) {
                refTask.getStateSimulation().setActive();
            }
            
            if ((finishedOne || passeeOne || noresumedOne) && current.getStateSimulation().isNotAccessible()) {
                // La tâche précédente est une tâche terminée et celle-ci est non accessible
                refTask.getStateSimulation().setActive();
                current.getStateSimulation().setPassive();
                ExpressSimulation.applyDecomposition(current);
                return;
            }

            if (current.getStateSimulation().isPassive() || current.getStateSimulation().isActive()) {
                // Le cas d'une tâche passive ou le cas d'une tâche active
                // (tâche mère).
                ExpressSimulation.applyDecomposition(current);
            }

            activedOne = activedOne | current.getStateSimulation().isActive();
            finishedOne = current.getStateSimulation().isFinished();
            passeeOne = current.getStateSimulation().isPassed();
            noresumedOne = current.getStateSimulation().isNoResumed();
        }

        if (finishedOne || passeeOne || noresumedOne) {
            // Normalement c'est la dernière tâche, on modifie la tâche mère
            // pour qu'elle soit terminée
            
            ExpressIteration.evaluateIteration(refTask);
            if (ExpressIteration.isFinished(refTask)) {
                refTask.getStateSimulation().setFinished();
            } else {
            	refTask.getStateSimulation().setPassive();
            	ExpressSimulation.initIteration(refTask);
            	ExpressSimulation.sequenceOperatorEffects(refTask);
            }
        }
    }
    
    // Pas de tâche facultative pour une tâche de type Choice.
    private static void choiceOperatorEffects(Tache refTask) {
        // Le debut : mettre toutes les sous-taches fille a Passive
    	if ((refTask.getStateSimulation().isPassive() || refTask.getStateSimulation().isActive()) && refTask.getFils().get(0).getStateSimulation().isNotAccessible()) {
    		// Pour la verification on a simplement besoin de verifier la premiere tache a NotAccessible car cet etat
    		// n'est jamais repris
    		for (Tache current : refTask.getFils()) {
    			current.getStateSimulation().setPassive();
    		}
    	}
    		
         boolean activedOne = false;
         boolean finishedOne = false;
         boolean suspended = false;
         for (Tache current : refTask.getFils()) {
             if (activedOne) {
                 // La tache precedente est une tache terminee et celle-ci est obligatoiteremnt en passive
            	 	refTask.getStateSimulation().setActive();            	 	
            	 	for (Tache subCurrent : refTask.getFils()) {
            	 		if (!subCurrent.getStateSimulation().isActive()) { 
            	 			ExpressSimulation.finishedTasks(subCurrent);
            	 		}
            	 	}
                 return;
             }

             if (current.getStateSimulation().isPassive() || current.getStateSimulation().isActive()) {
                 ExpressSimulation.applyDecomposition(current);
             }

             activedOne = current.getStateSimulation().isActive(); 
             suspended = suspended | current.getStateSimulation().isSuspended();
             finishedOne = finishedOne | current.getStateSimulation().isFinished();
         }
        
         if (activedOne) {
        	 	refTask.getStateSimulation().setActive();
        	 	for (Tache subCurrent : refTask.getFils()) {
        	 		if (!subCurrent.getStateSimulation().isActive()) { 
        	 			ExpressSimulation.finishedTasks(subCurrent);
        	 		}
        	 	}
         } else if (!activedOne && finishedOne && !suspended) { 
             ExpressIteration.evaluateIteration(refTask);
             if (ExpressIteration.isFinished(refTask)) {
                 refTask.getStateSimulation().setFinished();
                 
                 for (Tache subCurrent : refTask.getFils()) {
                     // Toutes les sous-taches sont dans l'état terminee.
                     ExpressSimulation.finishedTasks(subCurrent);
                 }
                 
             } else {
         		refTask.getStateSimulation().setPassive();
                ExpressSimulation.initIteration(refTask);
                ExpressSimulation.choiceOperatorEffects(refTask);
             }                        	 	
         }         
    }   
    
    /**
     * Remarques :
     *  - L'interruption est proposée quand une tâche de plus haute priorité est trouvée
     *  lors d'une concurrence ou d'une ind�pendance.
     *  Si pas d'interruption pour toutes les t�ches
     */    
    private static void concurencyOperatorEffects(Tache refTask) {
    		Importance temp = Importance.PEU;
		for (Tache current : refTask.getFils()) {
            if (!current.getStateSimulation().isTerminalState()) {
				temp = Importance.getMoreImportant(temp, current.getImportance());
			}

			if (current.getStateSimulation().isNotAccessible()) {
				current.getStateSimulation().setPassive();
			}
		}
      
		// Maintenant qu'on a fait le tour, il faut interrompre les taches de priorite plus faible.	
        boolean activedOne = false;
        for (Tache current : refTask.getFils()) {
        	if (current.isInterruptible() && 
        			current.getImportance().isMoreImportant(temp) && 
        			!current.getStateSimulation().isSuspended() && 
        			!current.getImportance().equals(Importance.INCONNU) &&
        			!current.getStateSimulation().resumeTag) {
        		// Faut stocker une valeur qui permet de dire explicitement que l'on souhaite reprendre.
        		current.getStateSimulation().setSuspended();
            	ExpressSimulation.suspendSubTasks(current);
        	}
        	
            if (activedOne) {
                refTask.getStateSimulation().setActive();
            }
            
            if (current.getStateSimulation().isPassive() || current.getStateSimulation().isActive()) {
                ExpressSimulation.applyDecomposition(current);
            }
            
            activedOne = activedOne | current.getStateSimulation().isActive();
        }

        for (Tache current : refTask.getFils()) {
            if (!current.getStateSimulation().isTerminalState()) {
                return;
            }
        }
        // On a tout fait donc
        ExpressIteration.evaluateIteration(refTask);
        if (ExpressIteration.isFinished(refTask)) {
        	refTask.getStateSimulation().setFinished();
        } else {
        	refTask.getStateSimulation().setPassive();
        	ExpressSimulation.initIteration(refTask);
        	ExpressSimulation.concurencyOperatorEffects(refTask);
        }        
    }
    
    private static void indenpendancyOrderOperatorEffects(Tache refTask) {
        Importance temp = Importance.PEU;
        boolean notAccessibleAll = false;
        for (Tache current : refTask.getFils()) {
            if (!current.getStateSimulation().isTerminalState()) {
                temp = Importance.getMoreImportant(temp, current.getImportance());
            }
            notAccessibleAll = notAccessibleAll | !current.getStateSimulation().isNotAccessible();            
        }      
        
        if (!notAccessibleAll) {
            for (Tache current : refTask.getFils()) {
                current.getStateSimulation().setPassive();
            }
        }
        
        boolean activedOne = false;
        boolean finishedOne = false;
        for (Tache current : refTask.getFils()) {            
            if (activedOne) {
                // Si active => rendre NotAccessibleAll les t�ches Passive.
                for (Tache subCurrent : refTask.getFils()) {
                    if (subCurrent.getStateSimulation().isPassive()) {
                        ExpressSimulation.noAccessibledTasks(subCurrent);
                    }
                }
                return;
            }
            
            if (finishedOne) {
                // Si finished => rendre Passive les t�ches NotAccessible.
                for (Tache subCurrent : refTask.getFils()) {
                    if (subCurrent.getStateSimulation().isNotAccessible()) {
                        subCurrent.getStateSimulation().setPassive();
                        ExpressSimulation.applyDecomposition(subCurrent);
                    }
                }
            }
            
            if (current.getStateSimulation().isPassive() || current.getStateSimulation().isActive()) {
                ExpressSimulation.applyDecomposition(current);
            }            
            
            activedOne = current.getStateSimulation().isActive();
            finishedOne = current.getStateSimulation().isFinished();
        }        

        if (activedOne) {
            // Si la derni�re t�che active => rendre NotAccessibleAll les taches Passive.
            for (Tache subCurrent : refTask.getFils()) {
                if (subCurrent.getStateSimulation().isPassive()) {
                    ExpressSimulation.noAccessibledTasks(subCurrent);
                }
            }
            return;
        }
        
        if (finishedOne) {
            // Si la derni�re t�che finished => rendre Passive les taches NotAccessible.
            for (Tache subCurrent : refTask.getFils()) {
                if (subCurrent.getStateSimulation().isNotAccessible()) {
                    subCurrent.getStateSimulation().setPassive();
                    ExpressSimulation.applyDecomposition(subCurrent);
                }
            }
        }

        // V�rifie si toutes les t�ches sont dans l'état terminé
        for (Tache current : refTask.getFils()) {
            if (!current.getStateSimulation().isTerminalState()) {
                return;
            }
        }
        
        ExpressIteration.evaluateIteration(refTask);
        if (ExpressIteration.isFinished(refTask)) {
            refTask.getStateSimulation().setFinished();
        } else {
            refTask.getStateSimulation().setPassive();
            ExpressSimulation.initIteration(refTask);
            ExpressSimulation.indenpendancyOrderOperatorEffects(refTask);
        }        
    }
    
    public static String getLinearExpressionWithUserValues(ArrayList<?> myList) {
        String result = "";
        for (Object tt : myList) {
            if (tt instanceof UserExpression) {
                if (((UserExpression)tt).getObjectValueState() == ExpressionConstant.VALUE) {
                    result += ((UserExpression)tt).getNodeValue().toString(); 
                } else {
                    result += ((UserExpression)tt).getName();
                }       
            } else if (tt instanceof ConcreteObjectType) {
                    // Car un ConcreteObjectType est obligatoirement un NodeExpression.
                    result += ((NodeExpression)tt).getName();
                    result += "[";
                    if (((ConcreteObjectType)tt).getUserConcreteObject() != null) {
                        result += ((ConcreteObjectType)tt).getUserConcreteObject().toString();
                    } else {
                    result += KMADEConstant.NO_CONCRETE_OBJECT_GROUPE_NAME;
                    }                        
                    result += "]";
            } else {
                result += (String)tt;
            }
        }
        return result;
    }
}
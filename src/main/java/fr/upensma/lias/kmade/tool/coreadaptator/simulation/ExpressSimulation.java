/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
* 
* KMADe is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* KMADe is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
**********************************************************************************/
package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.expression.ExpressionConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Actor;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Event;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEvent;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIteration;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class ExpressSimulation {
    private static Task currentTask;
      
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
    
    public static Task getCurrentTask() {
        return currentTask;
    }
             
    public static void setCurrentTask(Task pcurrentTask) {
    	currentTask = pcurrentTask;
    }
           
           
    // Algorithmes pour la partie simulation du modele de taches K-MAD    
    public static ArrayList<TokenSimulation> startSimulation(Task myTask) {
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
    
    public static ArrayList<TokenSimulation> searchActivableTask(Task myTache) {
        ArrayList<TokenSimulation> myTacheList = new ArrayList<TokenSimulation>();
        
        // L'interruption est une affaire de parallélisme et d'entrelacement
        if (myTache.isInterruptible() && (!myTache.getStateSimulation().isTerminalState())) {            
            if (!myTache.getStateSimulation().isSuspended()) {
                if (myTache.getStateSimulation().isPassive() || myTache.getStateSimulation().isActive()) {
                    myTacheList.add(new TokenSimulation(myTache, TokenSimulation.INTERROMPRE));
                }
            } else {                                
                if (myTache.getExecutor().equals(Executor.SYS)) {
                    // Un exécutant système est interrompue automatiquement                    
                    // Vérifie avec la tache mere s'il existe une sous-tache de plus haute priorite
                    
                	// /!\ getMotherTask peut renvoy� null -> � modifier 
                	Task myMother = myTache.getMother();
                    boolean etat = false;
                    for (int i = 0; i < myMother.getChildren().size() && !etat; i++) {
                        // Etat Terminal
                        if (!myMother.getChildren().get(i).getStateSimulation().isTerminalState()) {
                            etat = myTache.getImportance().isMoreImportant(myMother.getChildren().get(i).getImportance());
                        }
                    }
                    if (!etat) {
                        myTacheList.add(new TokenSimulation(myTache, TokenSimulation.REPRENDRE));
                    }
                } else {
                    // Les autres exécutants peuvent reprendre l'exécution d'une tâche.
                    if (myTache.isOptional()) {
                        myTacheList.add(new TokenSimulation(myTache, TokenSimulation.ABANDONNER));
                    }
                    myTacheList.add(new TokenSimulation(myTache, TokenSimulation.REPRENDRE));                    
                }
            }
        }
        
        // Leaf Task
        if (myTache.getOrdering().equals((Decomposition.ELE))) {
            if (myTache.getStateSimulation().isPassive() || myTache.getStateSimulation().isActive()) {
                myTacheList.add(new TokenSimulation(myTache, TokenSimulation.EXECUTER));                
            }
            if (myTache.getStateSimulation().isPassive() && myTache.isOptional()) {
            	if (!myTache.isRoot() && !myTache.getMother().getOrdering().equals(Decomposition.ALT) && !myTache.getExecutor().equals(Executor.SYS)) {
            		myTacheList.add(new TokenSimulation(myTache, TokenSimulation.PASSER));
				}
            }
            return myTacheList;
        }
        
        // No Leaf Task
		if (myTache.getStateSimulation().isPassive()) {
			if (myTache.isOptional()) {
				if (!myTache.isRoot() && !myTache.getMother().getOrdering().equals(Decomposition.ALT) && !myTache.getExecutor().equals(Executor.SYS)) {
					myTacheList.add(new TokenSimulation(myTache, TokenSimulation.PASSER));
				}
			}
		}

		for (Task current : myTache.getChildren()) {
			if (!myTache.getStateSimulation().isSuspended()) {
				ArrayList<TokenSimulation> myList = ExpressSimulation.searchActivableTask(current);
				myTacheList.addAll(myList);
			}
		}
		 
		return myTacheList;
    }

    public static void initSimulation(Task task) {
        task.getStateSimulation().setNotAccessible();
        task.getIterExpression().setInitIterationVariant();
        for (Task current : task.getChildren()) {
            ExpressSimulation.initSimulation(current);
        }
    }

    public static void initIteration(Task task) {
        for (Task current : task.getChildren()) {
            ExpressSimulation.initSimulation(current);
        }
    }
    
    public static boolean isExecutableTask(boolean exe, boolean event, boolean pre, int value, Task myCurrentTask) {        
        // Cette méthode s'occupe de gérer la première catégorie
    	 KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EXECUTE_TASK_TRAITEMENT_MESSAGE + " : " + myCurrentTask.getName());
    	 KMADEHistoryMessageManager.printlnMessage(" * " + KMADEConstant.EXECUTION_CONSTRAINT_MESSAGE); 
    	
        // Est-ce le bon utilisateur qui exécute.
    	 KMADEHistoryMessageManager.printMessage("  - " + KMADEConstant.USER_EXECUTION_CONSTRAINT_MESSAGE + " : ");
        if (exe) {
        	boolean trouve = false;
        	boolean isSystem = myCurrentTask.getExecutor().equals(Executor.SYS);
        	
			if (myCurrentTask.getActors().size() != 0 && !isSystem) {				
				User selectUser = ExpressSimulation.getCurrentUserList(value);
				for (Actor current : myCurrentTask.getActors()) {
					if (current.getUserRef().getName().equals(selectUser.getName())) {
						trouve = true;
						break;
					}
				}

				if (trouve) {
					KMADEHistoryMessageManager.printlnMessage(selectUser.getName() + " " + KMADEConstant.CAN_EXECUTE_TASK_MESSAGE + " " + myCurrentTask.getName());
				} else {
					KMADEHistoryMessageManager.printlnMessage(selectUser.getName() + " " + KMADEConstant.CAN_NOT_EXECUTE_TASK_MESSAGE + " " + myCurrentTask.getName());
			       	KMADEHistoryMessageManager.printlnMessage("");
					return false;
				}
			} else {
				// La tâche n'est pas soumise à la contrainte d'un utilisateur.
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.AUTHORIZED_EXECUTER_USER_MESSAGE);
			}
        } else {
       		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        // Est-ce le bon événement déclencheur.
        KMADEHistoryMessageManager.printMessage("  - " + KMADEConstant.EVENT_TRIGGER_CONSTRAINT_MESSAGE + " : ");
        if (event) {
            Event eventTask = myCurrentTask.getRaisingEvent();
            if (eventTask == null) {
            	 KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NO_TRIGGER_EVENT_CONSTRAINT_MESSAGE);
            } else {
                if (ExpressEvent.isExistingEvent(eventTask)) {
                	 KMADEHistoryMessageManager.printlnMessage(eventTask.getName() + " " + KMADEConstant.CAN_TRIGGER_TASK_MESSAGE + " " + myCurrentTask.getName());
                } else {
                	 KMADEHistoryMessageManager.printlnMessage(eventTask.getName() + " " + KMADEConstant.CAN_NOT_TRIGGER_CONSTRAINT_MESSAGE + " " + myCurrentTask.getName());
                }
            }          
        } else {
       		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        // La précondition est-elle respectée.
        KMADEHistoryMessageManager.printMessage("  - " + KMADEConstant.PRECONDITION_CONSTRAINT_MESSAGE + " : ");
        if (pre) {
        		try {
        			if (myCurrentTask.getPreExpression().getNodeExpression() == null) {
        				KMADEHistoryMessageManager.printMessage(myCurrentTask.getPreExpression().getFormalText());
        			} else {
        				KMADEHistoryMessageManager.printMessage(ExpressSimulation.getLinearExpressionWithUserValues(myCurrentTask.getPreExpression().getNodeExpression().getLinearExpression()));
                 }   

        			myCurrentTask.getPreExpression().getNodeExpression().evaluateNode(null);
				if ((Boolean) myCurrentTask.getPreExpression().getNodeExpression().getNodeValue()) {
					KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE);
				} else {
					KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE);
					 KMADEHistoryMessageManager.printlnMessage("");
					return false;
				}
			} catch (SemanticUnknownException e) {
				KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.MISSING_USER_VALUE_MESSAGE);
				 KMADEHistoryMessageManager.printlnMessage("");
				return false;
			} catch (SemanticErrorException e) {
				KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE);
				 KMADEHistoryMessageManager.printlnMessage("");
				return false;
			} catch (SemanticException e) {
				return false;
			}        
        } else {
        	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        return true;
    }
    
    public static boolean setExecuter(Task myTask, boolean post, boolean iter, boolean trigevent, boolean event) {
        // Faire le traitement. Première catégorie, seconde catégorie et troisième catégorie.    
        
        if(ExpressIteration.isFinished(myTask)){
        	 myTask.getStateSimulation().setFinished();
        	 KMADEHistoryMessageManager.printlnMessage(" * " + KMADEConstant.ACTION_CONSTRAINT_MESSAGE);
        	 KMADEHistoryMessageManager.printlnMessage("  - " + KMADEConstant.ITERATION_FINISH_NO_ACTION);
        	return true;
        }else{
        	ExpressIteration.evaluateIteration(myTask);

        myTask.getMother().getStateSimulation().setActive();
        
        // Post-traitement ...
        KMADEHistoryMessageManager.printlnMessage(" * " + KMADEConstant.ACTION_CONSTRAINT_MESSAGE);
            // Evénement déclencheur
        if (event) {
            ExpressEvent.extractFiringEvent(myTask.getRaisingEvent());
        }
        
            // Génération des Evénements
        KMADEHistoryMessageManager.printMessage("  - " + KMADEConstant.GENERATE_EVENTS_CONSTRAINT_MESSAGE + " : ");
        if (trigevent) {
            String temp = ExpressEvent.generateFiringEvent(myTask);
            if (temp.length() == 0) {
            	 KMADEHistoryMessageManager.printlnMessage(KMADEConstant.NO_GENERATED_EVENTS_CONSTRAINT_MESSAGE);
            } else {
            	 KMADEHistoryMessageManager.printlnMessage(temp);
            }
        } else {
        	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
            // Traitement des effetsdebord
        if (post) {            
        	KMADEHistoryMessageManager.printMessage("  - " + KMADEConstant.EFFETSDEBORD_CONSTRAINT_MESSAGE + " : " );
            
			if (myTask.getEffetsDeBordExpression().getNodeExpression() == null) {
				KMADEHistoryMessageManager.printMessage(myTask.getEffetsDeBordExpression().getFormalText());
			} else {
				KMADEHistoryMessageManager.printMessage(ExpressSimulation.getLinearExpressionWithUserValues(myTask.getEffetsDeBordExpression().getNodeExpression().getLinearExpression()));
			}   

        	try {
        		myTask.getEffetsDeBordExpression().getNodeExpression().evaluateNode(null);
        		KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.EFFETSDEBORD_EXECUTED_STATE_MESSAGE);
        	} catch (SemanticErrorException e) {
        		KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE + " , " + e.getMessage());
        		KMADEHistoryMessageManager.printlnMessage("");
        		return false;
            } catch (SemanticUnknownException e) {
            	KMADEHistoryMessageManager.printlnMessage(" -> " + KMADEConstant.EFFETSDEBORD_NO_EXECUTED_STATE_MESSAGE + " , " + e.getMessage());
            	KMADEHistoryMessageManager.printlnMessage("");
            	return false;
            } catch (SemanticException e) {
            	KMADEHistoryMessageManager.printlnMessage("");
            	return false;
            }
        	
        } else {
        	KMADEHistoryMessageManager.printlnMessage("  - " + KMADEConstant.EFFETSDEBORD_CONSTRAINT_MESSAGE + " : " + KMADEConstant.DISABLED_CONSTRAINT_MESSAGE);
        }
        
        		// Etat de la tâche
        if (ExpressIteration.isFinished(myTask)) {
            myTask.getStateSimulation().setFinished();
        } else {
            myTask.getStateSimulation().setActive();
        }
        }
 
        KMADEHistoryMessageManager.printlnMessage("");
        return true;
    }

    public static void setNoResumed(Task myTache) {
    	myTache.getStateSimulation().setNoResumed();
    	ExpressSimulation.noResumedTasks(myTache);
    }
    
    public static void setPasser(Task myTache) {
        myTache.getMother().getStateSimulation().setActive();
        ExpressSimulation.passedTasks(myTache);
    }
    
    public static void setSuspend(Task myTache) {
    	myTache.getStateSimulation().setSuspended();
    	ExpressSimulation.suspendSubTasks(myTache);
    }
    
    public static void setResume(Task myTache) {
		myTache.getStateSimulation().setResume();
		myTache.getStateSimulation().resumeTag = true;
    	ExpressSimulation.resumeSubTasks(myTache);
    }

    private static void resumeSubTasks(Task myTache) {
        for (Task current : myTache.getChildren()) {
    		if (current.getStateSimulation().isSuspended()) {
    			// Ne rien faire dans cette sous-tache. Elle est encore suspendue.
    		} else {
    			current.getStateSimulation().setSuspendedSubTaskOff();
    			resumeSubTasks(current);
    		}
    	}
    }
    
    private static void suspendSubTasks(Task myTache) {
    	myTache.getStateSimulation().setSuspendedSubTaskOn();
    	for (Task current : myTache.getChildren()) {
    		suspendSubTasks(current);
    	}
    }
    
    private static void noResumedTasks(Task myTache) {
    	myTache.getStateSimulation().setNoResumed();
    	for (Task current : myTache.getChildren()) {
    		noResumedTasks(current);
    	}
    }
    
    private static void passedTasks(Task myTache) {
        myTache.getStateSimulation().setPassed();
        for (Task current : myTache.getChildren()) {
            passedTasks(current);
        }
    }
    
    private static void finishedTasks(Task myTache) {
    	myTache.getStateSimulation().setFinished();
    	for (Task current : myTache.getChildren()) {
    		finishedTasks(current);
    	}
    }

    private static void noAccessibledTasks(Task myTache) {
        myTache.getStateSimulation().setNotAccessible();
        for (Task current : myTache.getChildren()) {
            noAccessibledTasks(current);
        }
    }
    
    // Construction des règles pour les opérateurs
    public static void applyDecomposition(Task refTask) {
        if (refTask.getOrdering().equals(Decomposition.SEQ)) {
            ExpressSimulation.sequenceOperatorEffects(refTask);
        } else if (refTask.getOrdering().equals(Decomposition.ALT)) {
            ExpressSimulation.choiceOperatorEffects(refTask);
        } else if (refTask.getOrdering().equals(Decomposition.PAR)) {
            ExpressSimulation.concurencyOperatorEffects(refTask);
        } else if (refTask.getOrdering().equals(Decomposition.ET)) {
            ExpressSimulation.indenpendancyOrderOperatorEffects(refTask);
        } else if (refTask.getOrdering().equals(Decomposition.ELE)) {
        } 
    }

    private static void sequenceOperatorEffects(Task refTask) {
        // Le début : mettre la première tâche fille a Passive
        if (refTask.getStateSimulation().isPassive() && refTask.getChildren().get(0).getStateSimulation().isNotAccessible()) {
            refTask.getChildren().get(0).getStateSimulation().setPassive();
        }

        boolean finishedOne = false;
        boolean passeeOne = false;
        boolean activedOne = false;
        boolean noresumedOne = false;
        
        for (Task current : refTask.getChildren()) {
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
    private static void choiceOperatorEffects(Task refTask) {
        // Le debut : mettre toutes les sous-taches fille a Passive
    	if ((refTask.getStateSimulation().isPassive() || refTask.getStateSimulation().isActive()) && refTask.getChildren().get(0).getStateSimulation().isNotAccessible()) {
    		// Pour la verification on a simplement besoin de verifier la premiere tache a NotAccessible car cet etat
    		// n'est jamais repris
    		for (Task current : refTask.getChildren()) {
    			current.getStateSimulation().setPassive();
    		}
    	}
    		
         boolean activedOne = false;
         boolean finishedOne = false;
         boolean suspended = false;
         for (Task current : refTask.getChildren()) {
             if (activedOne) {
                 // La tache precedente est une tache terminee et celle-ci est obligatoiteremnt en passive
            	 	refTask.getStateSimulation().setActive();            	 	
            	 	for (Task subCurrent : refTask.getChildren()) {
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
        	 	for (Task subCurrent : refTask.getChildren()) {
        	 		if (!subCurrent.getStateSimulation().isActive()) { 
        	 			ExpressSimulation.finishedTasks(subCurrent);
        	 		}
        	 	}
         } else if (!activedOne && finishedOne && !suspended) { 
             ExpressIteration.evaluateIteration(refTask);
             if (ExpressIteration.isFinished(refTask)) {
                 refTask.getStateSimulation().setFinished();
                 
                 for (Task subCurrent : refTask.getChildren()) {
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
    private static void concurencyOperatorEffects(Task refTask) {
    		Importance temp = Importance.PEU;
		for (Task current : refTask.getChildren()) {
            if (!current.getStateSimulation().isTerminalState()) {
				temp = Importance.getMoreImportant(temp, current.getImportance());
			}

			if (current.getStateSimulation().isNotAccessible()) {
				current.getStateSimulation().setPassive();
			}
		}
      
		// Maintenant qu'on a fait le tour, il faut interrompre les taches de priorite plus faible.	
        boolean activedOne = false;
        for (Task current : refTask.getChildren()) {
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

        for (Task current : refTask.getChildren()) {
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
    
    private static void indenpendancyOrderOperatorEffects(Task refTask) {
        Importance temp = Importance.PEU;
        boolean notAccessibleAll = false;
        for (Task current : refTask.getChildren()) {
            if (!current.getStateSimulation().isTerminalState()) {
                temp = Importance.getMoreImportant(temp, current.getImportance());
            }
            notAccessibleAll = notAccessibleAll | !current.getStateSimulation().isNotAccessible();            
        }      
        
        if (!notAccessibleAll) {
            for (Task current : refTask.getChildren()) {
                current.getStateSimulation().setPassive();
            }
        }
        
        boolean activedOne = false;
        boolean finishedOne = false;
        for (Task current : refTask.getChildren()) {            
            if (activedOne) {
                // Si active => rendre NotAccessibleAll les t�ches Passive.
                for (Task subCurrent : refTask.getChildren()) {
                    if (subCurrent.getStateSimulation().isPassive()) {
                        ExpressSimulation.noAccessibledTasks(subCurrent);
                    }
                }
                return;
            }
            
            if (finishedOne) {
                // Si finished => rendre Passive les t�ches NotAccessible.
                for (Task subCurrent : refTask.getChildren()) {
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
            for (Task subCurrent : refTask.getChildren()) {
                if (subCurrent.getStateSimulation().isPassive()) {
                    ExpressSimulation.noAccessibledTasks(subCurrent);
                }
            }
            return;
        }
        
        if (finishedOne) {
            // Si la derni�re t�che finished => rendre Passive les taches NotAccessible.
            for (Task subCurrent : refTask.getChildren()) {
                if (subCurrent.getStateSimulation().isNotAccessible()) {
                    subCurrent.getStateSimulation().setPassive();
                    ExpressSimulation.applyDecomposition(subCurrent);
                }
            }
        }

        // V�rifie si toutes les t�ches sont dans l'état terminé
        for (Task current : refTask.getChildren()) {
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
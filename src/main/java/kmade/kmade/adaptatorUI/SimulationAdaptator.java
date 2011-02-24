package kmade.kmade.adaptatorUI;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorFC.ExpressActeur;
import kmade.kmade.adaptatorFC.ExpressEffetsDeBord;
import kmade.kmade.adaptatorFC.ExpressEvent;
import kmade.kmade.adaptatorFC.ExpressHistory;
import kmade.kmade.adaptatorFC.ExpressIteration;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.kmade.adaptatorFC.ExpressUser;
import kmade.kmade.adaptatorFC.simulation.ExpressSimulation;
import kmade.kmade.adaptatorFC.simulation.ExpressSimulationXML;
import kmade.kmade.adaptatorFC.simulation.ReplayScenarioModel;
import kmade.kmade.adaptatorFC.simulation.TokenRecordScenarioSimulation;
import kmade.kmade.adaptatorFC.simulation.TokenReplayScenarioSimulation;
import kmade.kmade.adaptatorFC.simulation.TokenSimulation;
import kmade.kmade.view.KMADEMainFrame;
import kmade.kmade.view.simulation.KMADEInputUserExpressionAndConcreteObjectDialog;
import kmade.kmade.view.simulation.KMADESimulationToolBar;
import kmade.kmade.view.taskmodel.KMADEDefaultGraphCell;
import kmade.kmade.view.taskproperties.constrainteditors.KMADEArrayTypeComboBox;
import kmade.kmade.view.taskproperties.constrainteditors.KMADEGroupTypeComboBox;
import kmade.kmade.view.taskproperties.constrainteditors.KMADESetTypeComboBox;
import kmade.kmade.view.taskproperties.constrainteditors.KMADEUserExpressionField;
import kmade.kmade.view.toolutilities.InDevelopmentGlassPanel;
import kmade.kmade.view.toolutilities.KMADEFileChooser;
import kmade.kmade.view.toolutilities.KMADEToolUtilities;
import kmade.nmda.schema.expression.ConcreteObjectType;
import kmade.nmda.schema.expression.CurrentObject;
import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.SemanticErrorException;
import kmade.nmda.schema.expression.SemanticException;
import kmade.nmda.schema.expression.SemanticUnknownException;
import kmade.nmda.schema.expression.UserExpression;
import kmade.nmda.schema.tache.Acteur;
import kmade.nmda.schema.tache.Evenement;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Expression;
import kmade.nmda.schema.tache.Tache;
import kmade.nmda.schema.tache.User;

import org.jgraph.graph.DefaultGraphModel;

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
public final class SimulationAdaptator {
	private static KMADEInputUserExpressionAndConcreteObjectDialog refInputDialog = new KMADEInputUserExpressionAndConcreteObjectDialog(GraphicEditorAdaptator.getMainFrame().getSimulationDialog());
	
    private static TokenSimulation currentTokenSimulation;
    
    private static TokenSimulation currentSelectedTokenSimulation;
       
    private static Tache currentUserEditTask;
    
    private static boolean userValueOk;
       
    private static boolean recordOrReplay = true; // False : record / True : replay
    
    private static boolean recordScenarioModified = false; // False : non modifié / True : modifié
    
    private static boolean replayScenarioModified = false; // False
    
    // Concerne l'automate qui gère l'animation de la simulation    
    public static final int STOP_STATE = 0;
    
    public static final int PLAY_STATE = 1;
    
    public static final int PAUSE_STATE = 2;
    
    public static final int NEXT_STATE = 3;
    
    public static int SIMULATION_STATE = STOP_STATE;
    
    // Concerne l'automate du rejeu de scénario
    public static final int NO_REPLAY_SCENARIO = 0;
    
    public static final int REPLAY_SCENARIO_NO_STARTED = 1;
    
    public static final int REPLAY_SCENARIO_IN_PROGRESS = 2;
    
    public static final int REPLAY_SCENARIO_FINISHED = 3;

    public static final int REPLAY_SCENARIO_STOPED = 4;

    public static int REPLAY_SCENARIO_STATE = NO_REPLAY_SCENARIO;
    
	public static void openSimulationDialog() {
        // Traitement des modèles
            // Le modèle du JGraph
		DefaultGraphModel myGraphModel = GraphicEditorAdaptator.getTaskModelPanel().getGraphModel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setModelToSimulation(myGraphModel);
            // Pas de tâche sélectionnée.
        SimulationAdaptator.selectNoTask();
            // Initialisation du modèle de tâches.
        SimulationAdaptator.initTaskModels();
            // La partie pour les exécutants
        SimulationAdaptator.generateExecutant();
        // Initialise la partie présentation.
        recordOrReplay = true;
        if (recordOrReplay) {
            SimulationAdaptator.initRecordSimulation();
        } else {
            SimulationAdaptator.initReplaySimulation();
        }
        GraphicEditorAdaptator.disabledMainFrameBeforeSimulation();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().enableSimulationDialog();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setVisible(true);
	}    
    
    public static void closeSimulationDialog() {
        if (recordOrReplay) {
            if (!SimulationAdaptator.isRecordScenario()) {
                return;
            }
            SimulationAdaptator.initRecordSimulation();
        } else {
            if (!SimulationAdaptator.isReplayScenario()) {
                return;
            }
            SimulationAdaptator.initReplaySimulation();
        }
        // Initialise l'outil de simulation avant de quitter.
        GraphicEditorAdaptator.enabledMainFrameAfterSimulation();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().closeSimulationDialog();
    }
    
    public static boolean saveScenario() {
        String scenarioFileName = KMADEFileChooser.saveKMADScenarioFile();
        if (scenarioFileName != null) {
        	boolean isSuccess = false;
            if (recordOrReplay) {
                isSuccess = ExpressSimulationXML.saveScenario(scenarioFileName, GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().getRecordScenarioList(), ExpressSimulation.getCurrentTask());
                if (isSuccess)
                	recordScenarioModified = false;
            } else {
            	isSuccess = ExpressSimulationXML.saveScenario(scenarioFileName, GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().getReplayScenarioList(), ExpressSimulation.getCurrentTask());
            	if (isSuccess)
            		replayScenarioModified = false;
            }            
            if (isSuccess) {
            	System.out.println(KMADEConstant.SUCCEEDED_SAVE_SCENARIO_MESSAGE);
            	return true;
            } else {
            	System.out.println(KMADEConstant.NO_SUCCEEDED_SAVE_SCENARIO_MESSAGE);
            	return false;
            }
        } else {
        	return false;
        }
    }
       
	public static void constraintsEnablingAction() {
		if (currentSelectedTokenSimulation != null) {
			SimulationAdaptator.isExecutableTask(currentSelectedTokenSimulation);
		}
	}
    
    public static boolean recordOrReplayMode(int p) {       
        // Dire si on souhaite sauvegarder avant changement ?
        if (p == 1) {
            recordOrReplay = true;
            // Nettoyer la partie replay
            if (!SimulationAdaptator.isReplayScenario()) {
                return false;
            }
            SimulationAdaptator.initReplaySimulation();
        } else {
            recordOrReplay = false;
            // Nettoyer la partie record.
            if (!SimulationAdaptator.isRecordScenario()) {
                return false;
            }
            SimulationAdaptator.initRecordSimulation();
        }
        // Initialisation du modèle de tâches.
        SimulationAdaptator.initTaskModels();
        SimulationAdaptator.hideConstraintsExecute();
        return true;
    }
        
    public static void setGlobalTaskModel() {
        Dimension dimension = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().getPreferredSize();
        Dimension dimension1 = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().getBounds().getSize();
        dimension.width = Math.max(dimension.width, dimension1.width);
        dimension1.height = Math.max(dimension.height, dimension1.height);
        double d = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().getScale();
        dimension.setSize((double) (dimension.width * 1) / d,(double) (dimension.height * 1) / d);
        Dimension dimension2 = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getJGraphScrollPaneParent().getSize();
        double d1 = dimension2.getWidth() / dimension.getWidth();
        double d2 = dimension2.getHeight() / dimension.getHeight();
        d = Math.min(Math.min(d1, d2),1d);
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().setScale(d);        
    }
    
    private static void generateExecutant() {
        ArrayList<User> userList = ExpressUser.getUsers();
        ExpressSimulation.setUserList(userList);
        Object[] temp = new Object[userList.size()];
        for (int i = 0 ; i < userList.size() ; i++) {        	
        		ImageIcon myCurrent;
        		if (userList.get(i).getImage() == null || userList.get(i).getImage().equals("")) {
        			myCurrent = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().UNKNOWN_USER_IMAGEICON;
        		} else {       			
        			Image tempImage = KMADEToolUtilities.getImageThumbnail(userList.get(i).getImage(),KMADEConstant.ROW_HEIGHT);
         			
        			if (tempImage == null) {
        				myCurrent = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().ERROR_USER_IMAGEICON;
        			} else {
        	   			myCurrent = new ImageIcon(tempImage);   				
        			}         			
        		}
        	
            Object[] couple = {userList.get(i).getName(),userList.get(i).getImage(), myCurrent};
            temp[i] = couple;
        }
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setUserListCombo(temp);
    }
	   
    public static void undoSimulation() {  
    	ExpressHistory.undoStateHistory();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().repaint();

        ArrayList<TokenSimulation> myTasks = ExpressSimulation.searchActivableTask();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().updateListEnabledTask(myTasks);
        if (ExpressSimulation.getCurrentTask().getStateSimulation().isFinished()) {
            System.out.println(KMADEConstant.FINISHED_RECORD_SIMULATION_MESSAGE);
        }
        
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().clearLastActionInScenarioModel();

        if (ExpressHistory.getHistoricUndoLength() <= 0) {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setDisableUndoButton();
        } else {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setEnableUndoButton();
        }         
    }
    
    private static void initTaskModels() {
        ArrayList<Tache> myTaskList = ExpressTask.getRootTasks();
        for (Tache current : myTaskList) {
            ExpressSimulation.initSimulation(current);
        }
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().repaint();
        ExpressEvent.clearFiringEvent();
        ExpressEffetsDeBord.clearCurrentObject();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideEventsPanel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideCurrentObjectPanel();
        ExpressHistory.initStateHistory();
    }
    
	public static void initRecordLaunchSimulation() {
        // Sauvegarder le scénario s'il existe ...
        if (!SimulationAdaptator.isRecordScenario()) {
            return;
        }
        SimulationAdaptator.initRecordSimulation();
        SimulationAdaptator.initTaskModels();
        
		KMADEDefaultGraphCell myCell = (KMADEDefaultGraphCell)(GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().getSelectionCell());
        if (myCell == null) {
            System.out.println(KMADEConstant.NO_SELECTED_TASK_BEFORE_RECORD_SIMULATION_MESSAGE);
        } else {
            ArrayList<TokenSimulation> myTasks = ExpressSimulation.startSimulation(myCell.getTask());
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().repaint();
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().updateListEnabledTask(myTasks);
        } 
	}
	
    public static void goToTask(TokenSimulation tokenSimulation) {
        if (tokenSimulation != null) {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().scrollCellToVisible((KMADEDefaultGraphCell)(tokenSimulation.getTask().getJTask()));
        }
    }

    public static void selectAndGoToTask() {
        TokenSimulation tokenSimulation = null;
        if (recordOrReplay) {
            tokenSimulation = (TokenSimulation)GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().getActionList().getSelectedValue();
        } else {
            tokenSimulation = (TokenSimulation)GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().getScenarioList().getSelectedValue();
        }

        if (tokenSimulation != null) {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setToActionState();
            currentSelectedTokenSimulation = tokenSimulation;
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().scrollCellToVisible((KMADEDefaultGraphCell)(currentSelectedTokenSimulation.getTask().getJTask()));
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().setSelectionCell(currentSelectedTokenSimulation.getTask().getJTask());
            
            // Est-ce un Token pour l'exécution.
            if (currentSelectedTokenSimulation.isExecuterAction()) {
                GraphicEditorAdaptator.getMainFrame().getSimulationDialog().showConstraintsExecute();
                SimulationAdaptator.isExecutableTask(currentSelectedTokenSimulation);
                return;
            }
        } else {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setToNoActionState();
        }
        currentSelectedTokenSimulation = null;
        SimulationAdaptator.hideConstraintsExecute();
    }
        
    public static void hideConstraintsExecute() {
        if (recordOrReplay) {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideRecordConstraintsExecute();           
        } else {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideReplayConstraintsExecute();
        }
    }
       
    
    private static void isExecutableTask(TokenSimulation tokenSimulation) {
    		if (tokenSimulation == null) {
    			return;
    		}
		KMADESimulationToolBar toolbar = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar();
    	
    	// Utilisateur ...
    	String[] executantTab = new String[2];    
    	boolean isSystem = tokenSimulation.getTask().getExecutant().equals(Executant.SYS); 
    	if (isSystem) {
    		executantTab[0] = KMADEConstant.ACTOR_NO_ELEMENT_INTO_TASK;
    	} else {
	    	String acteurList = ExpressActeur.getActorList(tokenSimulation.getTask());
	    	if (acteurList.equals("")) {
	    		executantTab[0] = KMADEConstant.ACTOR_NO_ELEMENT_INTO_TASK;
	    	} else {
	    		executantTab[0] = acteurList;
	    	}
    	}
    	
    	if (toolbar.isExeSelected()) {
			if (isSystem) {
				executantTab[1] = KMADEConstant.AUTHORIZED_EXECUTER_USER_MESSAGE;
			} else {
				boolean trouve = false;
				if (tokenSimulation.getTask().getActeurs().size() != 0) {
					int value = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSelectionUserListCombo();
					User selectUser = ExpressSimulation.getCurrentUserList(value);
					for (Acteur current : tokenSimulation.getTask().getActeurs()) {
						if (current.getUserRef().getName().equals(	selectUser.getName())) {
							trouve = true;
							break;
						}
					}
					if (trouve) {
						executantTab[1] = KMADEConstant.AUTHORIZED_EXECUTER_USER_MESSAGE;
					} else {
						executantTab[1] = KMADEConstant.NO_AUTHORIZED_EXECUTER_USER_MESSAGE;
					}
				} else {
					// La tâche n'est pas soumise à la contrainte d'un
					// utilisateur.
					executantTab[1] = KMADEConstant.AUTHORIZED_EXECUTER_USER_MESSAGE;
				}
			}
		} else {
			executantTab[1] = KMADEConstant.DISABLED_CONSTRAINT_MESSAGE;
		}
    		
    	// Evénement ...
    	String[] eventTab = new String[2];
    	Evenement eventTask = tokenSimulation.getTask().getDeclencheur();
    	eventTab[0] = (eventTask == null ? KMADEConstant.NO_FIRING_EVENT_TINY_MESSAGE : eventTask.getName());
    	if (toolbar.isEventSelected()) {
    		if (eventTask == null) {
    			eventTab[1] = KMADEConstant.FIRABLE_EVENT_STATE_MESSAGE;
    		} else {
	    		if (ExpressEvent.isExistingEvent(eventTask)) {
	    			eventTab[1] = KMADEConstant.FIRABLE_EVENT_STATE_MESSAGE;;
	    		} else {
	    			eventTab[1] = KMADEConstant.NO_FIRABLE_EVENT_STATE_MESSAGE;
	    		}
    		}
    	} else {
    		eventTab[1] = KMADEConstant.DISABLED_CONSTRAINT_MESSAGE;
    	}
		// Précondition ...
    	String[] preconditionTab = new String[2];
		       
		if (toolbar.isPreSelected()) {
            if (tokenSimulation.getTask().getPreExpression().getNodeExpression() == null) {
                preconditionTab[0] = tokenSimulation.getTask().getPreExpression().getName();
            } else {
                preconditionTab[0] = ExpressSimulation.getLinearExpressionWithUserValues(tokenSimulation.getTask().getPreExpression().getNodeExpression().getLinearExpression());
            }   
            
			try {
				tokenSimulation.getTask().getPreExpression().getNodeExpression().evaluateNode(null);
				if ((Boolean) tokenSimulation.getTask().getPreExpression().getNodeExpression().getNodeValue()) {
					preconditionTab[1] = KMADEConstant.PRECONDITION_CONSTRAINTS_RESPECTED_STATE_MESSAGE;
				} else {
					preconditionTab[1] = KMADEConstant.PRECONDITION_CONSTRAINTS_NO_RESPECTED_STATE_MESSAGE;
				}
			} catch (SemanticUnknownException e) {
				preconditionTab[1] = KMADEConstant.PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE;
			} catch (SemanticErrorException e) {
				preconditionTab[1] = KMADEConstant.PRECONDITION_CONSTRAINTS_ERROR_STATE_MESSAGE;
			} catch (SemanticException e) {
			}
			
			if (tokenSimulation.getTask().getPreExpression().isAnyUserExpressionInExpression()) {
				if (!tokenSimulation.getTask().getPreExpression().isLocked()) {
					preconditionTab[1] = KMADEConstant.PRECONDITION_CONSTRAINTS_NEED_VALUES_STATE_MESSAGE;
				}
			}		
		} else {
            preconditionTab[0] = tokenSimulation.getTask().getPreExpression().getName(); 
			preconditionTab[1] = KMADEConstant.DISABLED_CONSTRAINT_MESSAGE;
		}
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setValue(executantTab,preconditionTab,eventTab);
    }
    
    public static void editUserValues() {
   		currentUserEditTask = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSelectedTask();
   		if (currentUserEditTask == null) {
   			return;
   		}
        // Préparation pour les valeurs utilisateurs de la précondition.
        ArrayList<Object> myListPre = currentUserEditTask.getPreExpression().getNodeExpression().getLinearExpression();       
        ArrayList<JComponent> pCompoPre = new ArrayList<JComponent>();
        SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListPre,refInputDialog.getExpressionPreconditionFieldList(), currentUserEditTask.getPreExpression().getObjectValues(), currentUserEditTask.getPreExpression().isLocked(), refInputDialog.getExpressionPreconditionComboList(),pCompoPre);

        // Préparation pour les valeurs utilisateurs de la effetsdebord.
        ArrayList<Object> myListPost = currentUserEditTask.getEffetsDeBordExpression().getNodeExpression().getLinearExpression();
        ArrayList<JComponent> pCompoPost = new ArrayList<JComponent>();
        SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListPost,refInputDialog.getExpressionEffetsDeBordFieldList(),currentUserEditTask.getEffetsDeBordExpression().getObjectValues(), currentUserEditTask.getPreExpression().isLocked(), refInputDialog.getExpressionEffetsDeBordComboList(),pCompoPost);
        
        // Préparation pour les valeurs utilisateurs de l'itération.
        ArrayList<Object> myListIter = currentUserEditTask.getIteExpression().getNodeExpression().getLinearExpression();
        ArrayList<JComponent> pCompoIter = new ArrayList<JComponent>();
        SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListIter,refInputDialog.getExpressionIterationFieldList(),currentUserEditTask.getIteExpression().getObjectValues(), currentUserEditTask.getPreExpression().isLocked(), refInputDialog.getExpressionIterationComboList(),pCompoIter);
        
        refInputDialog.setLockOrUnlockPreconditionUserValuesAndConcreteObjects(currentUserEditTask.getPreExpression().isLocked());
        refInputDialog.setInputUserConcretePreconditionPanel(pCompoPre);
        refInputDialog.setInputUserConcreteEffetsDeBordPanel(pCompoPost);
        refInputDialog.setInputUserConcreteIterationPanel(pCompoIter);
        SimulationAdaptator.disabledMainFrameBeforeEdition();
            
        refInputDialog.showInputUserConcreteDialogToEdit();
    }
    
    public static void updateSimulationGraph() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().updateSimulationGraph();
    }
    
    public static void launchActionFromRecord(TokenSimulation tokenSimulation) {
   		if (!SimulationAdaptator.launchAction(tokenSimulation)) {
   			return;
   		}

        ArrayList<TokenSimulation> myTasks = ExpressSimulation.continueSimulation();

        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().updateListEnabledTask(myTasks);
        // Mise à jour du modèle graphique.
        SimulationAdaptator.updateSimulationGraph();
        
        if (ExpressSimulation.getCurrentTask().getStateSimulation().isFinished()) {
            System.out.println(KMADEConstant.FINISHED_RECORD_SIMULATION_MESSAGE);            
        }       
        
   		// Enregistre l'action exécutée.
   		SimulationAdaptator.addActionInScenarioModel();
    		
   		// Mise a jour des boutons pour l'undo et le redo
   		if (ExpressHistory.isUndoAble()) {
   			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setEnableUndoButton();   			
   		} else {
   			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setDisableUndoButton();
   		}
    }
    
	private static boolean launchAction(TokenSimulation tokenSimulation) {
        // Verification par rapport a la precondition, la effetsdebord et l'iteration.
        currentTokenSimulation = tokenSimulation;
        Tache myTask = currentTokenSimulation.getTask();
        
        ExpressHistory.saveStateHistory(tokenSimulation);
        if (currentTokenSimulation.isExecuterAction()) {
        	if(!ExpressIteration.isFinished(myTask)){
        		if (GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar().isPreSelected()) {
        		boolean userValues = SimulationAdaptator.searchAnyUserExpressionOrConcreteObjectType(myTask);

                if (userValues && !userValueOk) {
                    return false;
                }
        	}
        	}
            KMADESimulationToolBar toolbar = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar();
            int value = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSelectionUserListCombo();
            boolean exe = toolbar.isExeSelected();
            boolean event = toolbar.isEventSelected();
            boolean pre = toolbar.isPreSelected();            
        		if (!ExpressSimulation.isExecutableTask(exe,event,pre, value, myTask)) {
            	return false;
            } else {
            		ExpressSimulation.setExecuter(myTask, toolbar.isPostSelected(), toolbar.isIterSelected(), toolbar.isTrigEventSelected(), event);
            		// Mise à jour de la partie événement.
            		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setEventListModel(ExpressEvent.getAllCurrentEvents());
            		// Mise à jour de la partie objet concret.
            		CurrentObject temp = ExpressEffetsDeBord.getCurrentObject();
            		if (temp.isExistCurrentEvaluateConcreteObject()) {
                		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().setCurrentObjectPanel(temp.toString());            			
            		} else {
            			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideCurrentObjectPanel();
            		}
            }
        } else if (currentTokenSimulation.isPasserAction()) {
            ExpressSimulation.setPasser(myTask);
        } else if ( currentTokenSimulation.isInterrompreAction()) {
        		ExpressSimulation.setSuspend(myTask);
        } else if (currentTokenSimulation.isReprendreAction()) {
        		ExpressSimulation.setResume(myTask);
        } else if (currentTokenSimulation.isAbandonnerAction()) {
        		ExpressSimulation.setNoResumed(myTask);
        }    
        
        return true;
	}
    
	private static void addActionInScenarioModel() {
        // Prend en compte l'utilisateur courant.
        int value = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSelectionUserListCombo();
        TokenRecordScenarioSimulation myTokenScenarioSimulation;
        if (value == -1) {
            myTokenScenarioSimulation = new TokenRecordScenarioSimulation(currentTokenSimulation);
        } else {
            User selectUser = ExpressSimulation.getCurrentUserList(value);
            myTokenScenarioSimulation = new TokenRecordScenarioSimulation(currentTokenSimulation,selectUser);
        }
        recordScenarioModified = true;
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().addActionInScenarioModel(myTokenScenarioSimulation);
	}
    
       
    public static void setPlusZoom() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().zoomPlus(1.5d);
    }
    
    public static void setMinusZoom() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().zoomMinus(1.5d);
    }
    
    public static void setDefaultZoom() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().zoomDefault(1d);
    }
    
    public static void selectNoTask() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideTaskProperties();
        GraphicEditorAdaptator.setSelectedTask(null);
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar().setDisabledNoSelectedTask();
    }
    
    public static void selectSeveralTask() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideTaskProperties();
        GraphicEditorAdaptator.setSelectedTask(null);
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar().setDisabledNoSelectedTask();
    }
    
    public static void selectOneTask(Object defaultGraphCell) {
        if (defaultGraphCell instanceof KMADEDefaultGraphCell) {
            // Vérifie si on peut le sélectionner car peut-être invisible.            
            KMADEDefaultGraphCell myDefaultGraphCellRef = (KMADEDefaultGraphCell) defaultGraphCell;
            GraphicEditorAdaptator.setSelectedTask(myDefaultGraphCellRef);
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().showTaskProperties();
            Tache tache = myDefaultGraphCellRef.getTask();
            KMADEMainFrame.getProjectPanel().getPanelProprieteTache().displayTaskProperties(
                    tache.getNumero(), 
                    tache.getMotherTaskName(),
                    tache.getName(), 
                    /*tache.getBut(),*/
                    tache.getRessources(), 
                    /*tache.getMedia(),*/
                    tache.getLabelName(),
                   /* tache.getFeedBack(),*/
                   /* tache.getDuree(), */
                    tache.getObservation(),
                    tache.getExecutant(), 
                    tache.getModalite().getValue(), 
                    tache.getFrequence().getValue(),
                    tache.getCompFreq(),
                    tache.getImportance().getValue(), 
                    tache.getEventsName(),
                    tache.isFacultatif(),
                    tache.isInterruptible(), 
                    tache.getDeclencheurName(), 
                    tache.getActeurs(),
                    tache.getPreExpression(), 
                    tache.getEffetsDeBordExpression(), 
                    tache.getDecomposition().getValue(), 
                    tache.getIteExpression()
                );
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar().setEnabledOneSelectedTask();
        } else {            
            // C'est un lien.
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().hideTaskProperties();
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSimulationToolBar().setDisabledNoSelectedTask();
        }
    }
    
    public static void showOverviewWindow() {
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getPreviewWindow().setVisible(!GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getPreviewWindow().isVisible());
    }

    public static void enabledMainFrameAfterEdition() {
        InDevelopmentGlassPanel.unPlugWindowInDisabled(GraphicEditorAdaptator.getMainFrame().getSimulationDialog());
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().enableSimulationDialog();
    }
    
    public static void disabledMainFrameBeforeEdition() {
        InDevelopmentGlassPanel.plugWindowInDisabled(GraphicEditorAdaptator.getMainFrame().getSimulationDialog());
    }
    
    public static void switchLockOrUnlockPreconditionUserValuesAndConcreteObjects() {
        Expression myPreExpression = currentUserEditTask.getPreExpression();
        if (myPreExpression.isLocked()) {
            // Débloquer le tout.
            refInputDialog.setEnabledPreconditionFieldAndComboComponents(true);
            myPreExpression.setLocked(false); 
            refInputDialog.setPreconditionUnLockImage();
        } else {
            // Bloquer le tout.
            refInputDialog.setEnabledPreconditionFieldAndComboComponents(false);
            myPreExpression.setLocked(true);
            refInputDialog.setPreconditionLockImage();
        }
    }
    
    public static void switchLockOrUnlockEffetsDeBordUserValuesAndConcreteObjects() {
        Expression myEffetsDeBordExpression = currentUserEditTask.getEffetsDeBordExpression();
        if (myEffetsDeBordExpression.isLocked()) {
            // Débloquer le tout.
            refInputDialog.setEnabledEffetsDeBordFieldAndComboComponents(true);
            myEffetsDeBordExpression.setLocked(false); 
            refInputDialog.setEffetsDeBordUnLockImage();
        } else {
            // Bloquer le tout.
            refInputDialog.setEnabledEffetsDeBordFieldAndComboComponents(false);
            myEffetsDeBordExpression.setLocked(true);
            refInputDialog.setEffetsDeBordLockImage();
        }
    }
    
    public static void cancelInputConcreteDialog() {
        SimulationAdaptator.enabledMainFrameAfterEdition();
        refInputDialog.closeInputUserConcreteDialog(); 
        userValueOk = false;
        
        // Mise à jour de la partie isExecutable ...
        SimulationAdaptator.isExecutableTask(currentSelectedTokenSimulation);
    }
    

	public static void switchLockOrUnlockIterationUserValuesAndConcreteObjects() {
        Expression myIterExpression = currentUserEditTask.getIteExpression();
        if (myIterExpression.isLocked()) {
            // Débloquer le tout.
            refInputDialog.setEnabledIterationFieldAndComboComponents(true);
            myIterExpression.setLocked(false); 
            refInputDialog.setIterationUnLockImage();
        } else {
            // Bloquer le tout.
            refInputDialog.setEnabledIterationFieldAndComboComponents(false);
            myIterExpression.setLocked(true);
            refInputDialog.setIterationLockImage();
        }
	}

    
	public static void closeInputConcreteDialog() {
        // Avant tout chose, il faut vérifier si les valeurs sont correctes.
        // On test donc la précondition, la effetsdebord et l'itération.
        
        boolean erreurUserExpression = false;        
        for (int i = 0; i < refInputDialog.getExpressionPreconditionFieldList().size() && !erreurUserExpression; i++) {
            erreurUserExpression = erreurUserExpression | refInputDialog.getExpressionPreconditionFieldList().get(i).isUnknownOrErrorUserExpressionState();
        }      
        boolean erreurConcreteObject = false;
        for (int i = 0; i < refInputDialog.getExpressionPreconditionComboList().size() && !erreurConcreteObject; i++) {        
            erreurConcreteObject = erreurConcreteObject | refInputDialog.getExpressionPreconditionComboList().get(i).isConcreteObjectEmpty();
        }

        String message = "";
        if (erreurUserExpression) {
            message = KMADEConstant.USER_VALUES_MISSING_OR_WRONG_MESSAGE + "\n";
        }
        if (erreurConcreteObject) {
            message += KMADEConstant.CONCRETE_OBJECT_NO_SELECTED_MESSAGE;
        }
        
        if (message.equals("")) {
       		// Stocke les valeurs utilisateurs.
            SimulationAdaptator.storeUserValues(refInputDialog.getExpressionPreconditionFieldList(), currentUserEditTask.getPreExpression());
            SimulationAdaptator.storeUserValues(refInputDialog.getExpressionEffetsDeBordFieldList(), currentUserEditTask.getEffetsDeBordExpression());
            SimulationAdaptator.storeUserValues(refInputDialog.getExpressionIterationFieldList(), currentUserEditTask.getIteExpression());

            // Stocke les objets concrets choisis par l'utilisateur.
            SimulationAdaptator.storeUserConcreteObjects(refInputDialog.getExpressionPreconditionComboList(), currentUserEditTask.getPreExpression());
            SimulationAdaptator.storeUserConcreteObjects(refInputDialog.getExpressionEffetsDeBordComboList(), currentUserEditTask.getEffetsDeBordExpression());
            SimulationAdaptator.storeUserConcreteObjects(refInputDialog.getExpressionIterationComboList(), currentUserEditTask.getIteExpression());
            
            SimulationAdaptator.enabledMainFrameAfterEdition();
            refInputDialog.closeInputUserConcreteDialog();
            userValueOk = true;
        } else {            
            JOptionPane.showMessageDialog(refInputDialog,message, KMADEConstant.USER_VALUES_PROBLEM_MESSAGE + "...", JOptionPane.ERROR_MESSAGE, new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.INFO_DIALOG_IMAGE)));            
        }
        
        // Mise à jour de la partie isExecutable ...
        SimulationAdaptator.isExecutableTask(currentSelectedTokenSimulation);
	}
    
    private static void storeUserValues(ArrayList<KMADEUserExpressionField> myFieldList, Expression myExpression) {
        ArrayList<String> myList = new ArrayList<String>();
        for (KMADEUserExpressionField current : myFieldList) {
            myList.add(current.getText());
        }
        myExpression.setObjectValues(myList);
    }
    
    private static void storeUserConcreteObjects(ArrayList<KMADEGroupTypeComboBox> arrayList, Expression myExpression) {

    }
	
    private static boolean searchAnyUserExpressionOrConcreteObjectType(Tache myTask) {
        // Préparation pour les valeurs utilisateurs de la précondition.
        ArrayList<Object> myListPre = myTask.getPreExpression().getNodeExpression().getLinearExpression();       
        ArrayList<JComponent> pCompoPre = new ArrayList<JComponent>();
        boolean valeur = SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListPre,refInputDialog.getExpressionPreconditionFieldList(), myTask.getPreExpression().getObjectValues(), myTask.getPreExpression().isLocked(), refInputDialog.getExpressionPreconditionComboList(),pCompoPre);

        // Préparation pour les valeurs utilisateurs de la effetsdebord.
        ArrayList<Object> myListPost = myTask.getEffetsDeBordExpression().getNodeExpression().getLinearExpression();
        ArrayList<JComponent> pCompoPost = new ArrayList<JComponent>();
        valeur |= SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListPost,refInputDialog.getExpressionEffetsDeBordFieldList(),myTask.getEffetsDeBordExpression().getObjectValues(), myTask.getPreExpression().isLocked(), refInputDialog.getExpressionEffetsDeBordComboList(),pCompoPost);
        
        // Préparation pour les valeurs utilisateurs de l'itération.
        ArrayList<Object> myListIter = myTask.getIteExpression().getNodeExpression().getLinearExpression();
        ArrayList<JComponent> pCompoIter = new ArrayList<JComponent>();
        valeur |= SimulationAdaptator.buildAnyUserExpressionOrConcreteObjectType(myListIter,refInputDialog.getExpressionIterationFieldList(),myTask.getIteExpression().getObjectValues(), myTask.getPreExpression().isLocked(), refInputDialog.getExpressionIterationComboList(),pCompoIter);
        
        currentUserEditTask = myTask;
        
        if (valeur) {
            refInputDialog.setLockOrUnlockPreconditionUserValuesAndConcreteObjects(myTask.getPreExpression().isLocked());
            refInputDialog.setInputUserConcretePreconditionPanel(pCompoPre);
            refInputDialog.setInputUserConcreteEffetsDeBordPanel(pCompoPost);
            refInputDialog.setInputUserConcreteIterationPanel(pCompoIter);
            SimulationAdaptator.disabledMainFrameBeforeEdition();
            
            refInputDialog.showInputUserConcreteDialogToSimulate();
            return true;
        } else {
            return false;
        }
    }    
    
    /**
     * @param noOpen : force ou pas l'ouverture selon les contraintes d'ouverture.
     * @param myList : la liste des objets de l'expression
     * @param myfield : la référence à la liste des futurs champs de texte
     * @param value : les valeurs de l'utilisateur
     * @param isLocked : cadenas ou pas
     * @param arrayList : ...
     * @param myListComponent : ...
     * @return true y a des choses à modifier, false y a rien à modifier.
     */
    private static boolean buildAnyUserExpressionOrConcreteObjectType(
            ArrayList<Object> myList, 
            ArrayList<KMADEUserExpressionField> myfield, 
            ArrayList<String> value, 
            boolean isLocked, 
            ArrayList<KMADEGroupTypeComboBox> arrayList, 
            ArrayList<JComponent> myListComponent) {
        // Nettoyage de la liste des champs pour les valeurs utilisateurs et les objets concrets utilisateurs.
   		myfield.clear();
   		arrayList.clear();

        boolean isUserConcreteExpression = false;
        boolean isUserError = false;
        Iterator<String> myIte = value.iterator();
        for (Object tt:myList) {
            if (tt instanceof UserExpression) {   
                KMADEUserExpressionField current = new KMADEUserExpressionField((UserExpression)tt);
                myListComponent.add(current);
                if (myIte.hasNext()) {
                	current.setInitValueIntoUserExpression(myIte.next().toString());
                } else {
                    // Y a plus rien dans l'itérator.
                    current.setToUnknownUserExpression();
                }
                isUserError |= current.isUnknownOrErrorUserExpressionState();
                myfield.add(current);
                isUserConcreteExpression = true;
            } else if (tt instanceof ConcreteObjectType) {
                    // Car un ConcreteObjectType est obligatoirement un NodeExpression.
                    myListComponent.add(new JLabel(((NodeExpression)tt).getName()));
                    myListComponent.add(new JLabel("["));
                    if (((ConcreteObjectType)tt).isGroupSetType()) {
                        isUserConcreteExpression = true;
                        KMADESetTypeComboBox currentCombo = new KMADESetTypeComboBox((ConcreteObjectType)tt,((ConcreteObjectType)tt).getConcreteObjects());                       
                        arrayList.add(currentCombo);
                        myListComponent.add(currentCombo);
                    } else if (((ConcreteObjectType)tt).isGroupArrayType()) {
                        isUserConcreteExpression = true;
                        KMADEArrayTypeComboBox currentCombo = new KMADEArrayTypeComboBox((ConcreteObjectType)tt,((ConcreteObjectType)tt).getConcreteObjects());                       
                        arrayList.add(currentCombo);
                        myListComponent.add(currentCombo);
                    } else {
                        myListComponent.add(new JLabel(((ConcreteObjectType)tt).getConcreteObject().getName()));
                    }
                    myListComponent.add(new JLabel("]"));
            } else {                
                myListComponent.add(new JLabel((String)tt));
            }
        }        
        
        if (isUserConcreteExpression) {
            if (!isLocked) {
                return true;
            } else {
                if (isUserError) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;            
        }
    }
    
    public static void initRecordSimulation() {
        recordScenarioModified = false;
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().clearScenarioListModel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setToNoActionState();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().clearAvailableTasksListModel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().setDisableUndoButton();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToInitState();              
    }
    
    public static boolean isRecordScenario() {
        // Enregistrer le scénario
        if ((!GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getRecordingPanel().isEmptyScenarioModel()) && recordScenarioModified) {
            int result = JOptionPane.showConfirmDialog(GraphicEditorAdaptator.getMainFrame().getSimulationDialog(),KMADEConstant.RECORD_SCENARIO_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // Montrer la boite de dialogue qui permet d'enregistrer le scéanrio
                return SimulationAdaptator.saveScenario();
            } else if (result == JOptionPane.NO_OPTION) {
                // Continuer
                recordScenarioModified = false;
            } else if (result == JOptionPane.CANCEL_OPTION) {
                // Annuler donc arrêter le traitement
                recordScenarioModified = true;
                return false;
            }
        }
        return true;
    }
    
    // **
    // * Partie réservée pour la gestion du rejeu.
    // **
    public static void loadScenario() {
        // Avant de charger un nouveau scénario, regardons s'il n'existe pas un scénario à enregistrer.
        if (!SimulationAdaptator.closeReplayScenario()) {
        	return;
        }
        
        File myScenarioFile = KMADEFileChooser.openKMADScenarioFile();
        if (myScenarioFile != null) {
            ReplayScenarioModel scenarioModel = ExpressSimulationXML.loadScenario(myScenarioFile);
            ArrayList<TokenReplayScenarioSimulation> scenarioList = scenarioModel.getTokenReplayScenarioSimulation();
            if (scenarioModel.getRootTask() == null) {
                // Pas de tâche Root
                REPLAY_SCENARIO_STATE = SimulationAdaptator.NO_REPLAY_SCENARIO;
                System.out.println(KMADEConstant.NO_ROOT_TASK_PROBLEM_MESSAGE + "...");               
            } else {
                REPLAY_SCENARIO_STATE = SimulationAdaptator.REPLAY_SCENARIO_NO_STARTED;
                GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().updateReplayListTask(scenarioList);
                SimulationAdaptator.initReplayScenario(scenarioModel.getRootTask());
                // Initialise la présentation du gestionnaire de Replay : activer les boutons de contrôle ...
                SIMULATION_STATE = SimulationAdaptator.STOP_STATE;
                GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToStopState();
            }            
        }
    }
    
    public static void playTask(TokenReplayScenarioSimulation ptoken) {
    		boolean value = SimulationAdaptator.launchActionFromReplay(ptoken);
    		
    		if (!value) {
    			// L'action ne s'est pas exécutée. On va dans l'état pause.
    			SimulationAdaptator.firePauseActionReplaySimulation();
    		} else if (REPLAY_SCENARIO_STATE == SimulationAdaptator.REPLAY_SCENARIO_IN_PROGRESS) {
    			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().resumeAfterExecuteAction();
    		}
    }
    
    private static void nextTask(TokenReplayScenarioSimulation ptask) {
        REPLAY_SCENARIO_STATE = SimulationAdaptator.REPLAY_SCENARIO_IN_PROGRESS;
        SimulationAdaptator.launchActionFromReplay(ptask);
    }
    
    
    private static boolean launchActionFromReplay(TokenReplayScenarioSimulation tokenSimulation) {       
        if (tokenSimulation.isNotFounded()) {
            // La tâche n'a pas été trouvée.
            System.out.println(KMADEConstant.NO_FOUNDED_TASK_TO_REPLAY_PROBLEM_MESSAGE + " : UNDER DEVELOPMENT");
            return false;
        } else {
        	if (!tokenSimulation.isActionable(GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().getAvailableTaches())) {
        		System.out.println(KMADEConstant.CAN_NOT_EXECUTE_TASK_TO_REPLAY_PROBLEM_MESSAGE + " : UNDER DEVELOPMENT");
        		return false;
        	}
            // Injecter les valeurs utilisateurs dans la tâche (pre/post/iter).
            tokenSimulation.getTask().getPreExpression().setObjectValues(tokenSimulation.getUserValuePre());
            tokenSimulation.getTask().getEffetsDeBordExpression().setObjectValues(tokenSimulation.getUserValuePost());
            tokenSimulation.getTask().getIteExpression().setObjectValues(tokenSimulation.getUserValueIter());
        }
        
        if (!SimulationAdaptator.launchAction(tokenSimulation)) {
            return false;
        }
        
        // Enregistre l'action exécutée.
        SimulationAdaptator.addActionInScenarioReplayModel();

        ArrayList<TokenSimulation> myTasks = ExpressSimulation.continueSimulation();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().updateListEnabledTask(myTasks);
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().incrementCurrentReplaySimulation();
        SimulationAdaptator.updateSimulationGraph();
        
        boolean isFinished = false;
        if (GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().isFinishedReplayScenario()) {
            if (ExpressSimulation.getCurrentTask().getStateSimulation().isFinished()) {
                System.out.println(KMADEConstant.FINISHED_SCENARIO_AND_SIMULATION_PROBLEM_MESSAGE);
                isFinished = true;
            } else {
                System.out.println(KMADEConstant.FINISHED_SCENARIO_PROBLEM_MESSAGE);
                isFinished = true;
            }            
        } else {
            if (ExpressSimulation.getCurrentTask().getStateSimulation().isFinished()) {
                System.out.println(KMADEConstant.FINISHED_SIMULATION_PROBLEM_MESSAGE);
                isFinished = true;
            } else {                     
                GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setSelectCurrentTask();
            }
        }            
        if (isFinished) {
           	REPLAY_SCENARIO_STATE = SimulationAdaptator.REPLAY_SCENARIO_FINISHED;	
			SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.STOP_STATE;
			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().stopWaitController();
			GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToStopState();
        }       
        return true;
    }

    private static void addActionInScenarioReplayModel() {
        // Prend en compte l'utilisateur courant.
        int value = GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getSelectionUserListCombo();
        TokenRecordScenarioSimulation myTokenScenarioSimulation;
        if (value == -1) {
            myTokenScenarioSimulation = new TokenRecordScenarioSimulation(currentTokenSimulation);
        } else {
            User selectUser = ExpressSimulation.getCurrentUserList(value);
            myTokenScenarioSimulation = new TokenRecordScenarioSimulation(currentTokenSimulation,selectUser);
        }
        replayScenarioModified = true;
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().addActionInReplayScenarioModel(myTokenScenarioSimulation);
    }

    private static void initReplayScenario() {
        if (ExpressSimulation.getCurrentTask() == null) {
            KMADEDefaultGraphCell myCell = (KMADEDefaultGraphCell)(GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getGraphSimulation().getSelectionCell());
            SimulationAdaptator.initReplayScenario(myCell.getTask());
        } else {
            SimulationAdaptator.initReplayScenario(ExpressSimulation.getCurrentTask());            
        }
    }
    
    private static void initReplayScenario(Tache rootTask) {
        if (rootTask == null) {
            System.out.println(KMADEConstant.SELECT_TASK_TO_REPLAY_MESSAGE);
        } else {
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().initCurrentReplaySimulation();
            ExpressSimulation.initSimulation(rootTask);
            ArrayList<TokenSimulation> myTasks = ExpressSimulation.startSimulation(rootTask);
            GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().updateListEnabledTask(myTasks);
       } 
    }

	public static void fireStopActionReplaySimulation() {
		switch(SIMULATION_STATE) {
			case SimulationAdaptator.STOP_STATE : {
				// Ne rien faire.
				break;
			}
			case SimulationAdaptator.PLAY_STATE : {
				// Revenir dans l'état STOP_STATE.
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.STOP_STATE;
				GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().stopWaitController();
				break;
			}
			case SimulationAdaptator.PAUSE_STATE : {
				// Revenir dans l'état STOP_STATE.
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.STOP_STATE;
				GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().stopWaitController();
				break;
			}
		}
		REPLAY_SCENARIO_STATE = SimulationAdaptator.REPLAY_SCENARIO_STOPED;
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToStopState();		
	}
	
	public static void firePlayActionReplaySimulation() {
		switch(SIMULATION_STATE) {
			case SimulationAdaptator.STOP_STATE : {
				// Passer dans l'état PLAY
				if (REPLAY_SCENARIO_STATE == REPLAY_SCENARIO_FINISHED || REPLAY_SCENARIO_STATE == REPLAY_SCENARIO_STOPED) {
                    if (!SimulationAdaptator.isReplayScenario()) {
                        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToNoPlaySelectedStateButton();
                        return;
                    }                    
                    GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().clearScenarioListModel();
                }
                
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.PLAY_STATE;
				REPLAY_SCENARIO_STATE = SimulationAdaptator.REPLAY_SCENARIO_IN_PROGRESS;
				SimulationAdaptator.initReplayScenario();
				GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().startWaitController();
				break;
			}
			case SimulationAdaptator.PLAY_STATE : {
				// Ne rien faire.
				break;
			}
			case SimulationAdaptator.PAUSE_STATE : {
				// Revenir dans l'état PLAY
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.PLAY_STATE;
				GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().startWaitController();
				break;
			}
		}
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToPlayState();
	}
	
	public static void firePauseActionReplaySimulation() {
		switch(SIMULATION_STATE) {
			case SimulationAdaptator.STOP_STATE : {
				// Ne doit pas se produire
				return;
			}
			case SimulationAdaptator.PLAY_STATE : {
				// Passer dans l'état PAUSE
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.PAUSE_STATE;
				GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().pauseWaitController();
				break;
			}
			case SimulationAdaptator.PAUSE_STATE : {
				// Ne rien faire.
				break;
			}
		}
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToPauseState();		
	}

	public static void fireNextActionReplaySimulation(TokenReplayScenarioSimulation p) {
		switch(SIMULATION_STATE) {
			case SimulationAdaptator.PAUSE_STATE : {
				SimulationAdaptator.SIMULATION_STATE = SimulationAdaptator.PAUSE_STATE;
				break;
			}
			case SimulationAdaptator.NEXT_STATE : {
				// Ne rien faire.
				break;
			}
		}
		GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToPauseState();
		SimulationAdaptator.nextTask(p);
	}

    public static boolean closeReplayScenario() {
        if (!SimulationAdaptator.isReplayScenario()) {
            return false;
        }
        SimulationAdaptator.initReplaySimulation();
        return true;
    }
    
	private static void initReplaySimulation() {
        replayScenarioModified = false;
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().clearAvailableTasksListModel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().clearScenarioListModel();
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().clearScenarioPerformedListModel();
        SIMULATION_STATE = SimulationAdaptator.STOP_STATE;
        REPLAY_SCENARIO_STATE = SimulationAdaptator.NO_REPLAY_SCENARIO;
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().setToInitState();
	}

    private static boolean isReplayScenario() {
        // Nettoyer la partie replay.
        GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().stopWaitController();
        // Enregistrer le scénario
        if ((!GraphicEditorAdaptator.getMainFrame().getSimulationDialog().getReplayPanel().isEmptyScenarioModel()) && replayScenarioModified) {
            int result = JOptionPane.showConfirmDialog(GraphicEditorAdaptator.getMainFrame().getSimulationDialog(),KMADEConstant.RECORD_SCENARIO_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // Montrer la boite de dialogue qui permet d'enregistrer le scénario
                return SimulationAdaptator.saveScenario();
            } else if (result == JOptionPane.NO_OPTION) {
                // Continuer
                recordScenarioModified = false;
            } else if (result == JOptionPane.CANCEL_OPTION) {
                // Annuler donc arrêter le traitement
                recordScenarioModified = true;
                return false;
            }
        }        
        return true;
    }
}

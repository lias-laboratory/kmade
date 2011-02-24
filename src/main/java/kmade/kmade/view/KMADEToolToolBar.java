package kmade.kmade.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.EntityListAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.KMADeAdaptator;
import kmade.kmade.adaptatorUI.ProjectManagerAdaptator;
import kmade.kmade.adaptatorUI.SearchAdaptator;
import kmade.kmade.adaptatorUI.TaskPropertiesEnhancedEditorAdaptator;
import kmade.kmade.view.taskmodel.KMADETaskModelToolBar;
import kmade.kmade.view.toolutilities.KMADEMoreButton;
import kmade.kmade.view.toolutilities.LanguageFactory;

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
public class KMADEToolToolBar extends JPanel implements LanguageFactory {
	private static final long serialVersionUID = 6450443851794174687L;

	public static final ImageIcon SHOW_GRILLE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.SHOW_GRID_IMAGE));

	public static final ImageIcon HIDE_GRILLE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.HIDE_GRID_IMAGE));
	
	public static final ImageIcon SHOW_RULE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.SHOW_RULE_IMAGE));
	
	public static final ImageIcon HIDE_RULE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.HIDE_RULE_IMAGE));
    
    public static final ImageIcon CHOICE_GRID_SIZE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CHOICE_GRID_SIZE_IMAGE));
    
    public static final ImageIcon UNDO = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.UNDO_IMAGE));
    
    public static final ImageIcon REDO = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.REDO_IMAGE));
    
    public static final ImageIcon CUT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CUT_IMAGE));
    
    public static final ImageIcon COPY = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.COPY_IMAGE));
    
    public static final ImageIcon PASTE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PASTE_IMAGE));
    
    public static final ImageIcon DELETE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.DELETE_IMAGE));
    
    public static final ImageIcon PRINT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PRINT_IMAGE));
    
    public static final ImageIcon PRINT_PREVIEW = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PRINT_PREVIEW_IMAGE));
    
    public static final ImageIcon PRINT_LAYOUT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PRINT_LAYOUT_IMAGE));
    
    public static final ImageIcon NEW_PROJECT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.NEW_PROJECT_IMAGE));
    
    public static final ImageIcon OPEN_PROJECT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.OPEN_PROJECT_IMAGE));
    
    public static final ImageIcon CLOSE_PROJECT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CLOSE_PROJECT_IMAGE));
    
    public static final ImageIcon SAVE_PROJECT = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.SAVE_PROJECT_IMAGE));
    
    public static final ImageIcon OVERVIEW_PREVIEW = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.OVERVIEW_IMAGE));
        
    public static final ImageIcon CLIPBOARD_PREVIEW = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CLIPBOARD_IMAGE));
    
    public static final ImageIcon OPTIONS_PREVIEW = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.OPTIONS_IMAGE));
    
    public static final ImageIcon PROJECT_IMAGE_ICON = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PROJECT_IMAGE));
    
    public static final ImageIcon ENTITY_LIST_IMAGE_ICON = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ENTITIES_IMAGE));
    
    private AbstractAction modeleAction;
    
    private AbstractAction toolAction;
    
    private AbstractAction debugAction;
    
    private AbstractAction historyAction;
    
    private AbstractAction aboutAction;
    
    private AbstractAction simulationAction;
    
    private AbstractAction statisticAction;
    
    private AbstractAction checkAction;
    
    private AbstractAction redoAction;
    
    private AbstractAction undoAction;
    
    private AbstractAction projectAction;
    
    private AbstractAction exitAction;
    
    private AbstractAction loadAction;
    
	private AbstractAction myGridAction;
	
	private AbstractAction myRuleAction;
	
    private AbstractAction myEditTaskAction;
    
    private AbstractAction myCompleteEditTaskAction;
    
    private AbstractAction myCutAction;
    
    private AbstractAction myCopyAction;
    
    private AbstractAction myPasteAction;
    
    private AbstractAction myRemoveAction;
    
    private AbstractAction findReplaceAction;
    
	private JMenuBar myMenuBar;

    private AbstractAction mySaveAction;

    private AbstractAction mySaveAsAction;

    private AbstractAction myCloseAction;

    private AbstractAction myOverviewPrintAction;

    private AbstractAction myPrintAction;

    private AbstractAction myOverviewClipboardAction;

    private AbstractAction mySetGridAction;

    private AbstractAction myOverviewWindowAction;

    private AbstractAction mySetProjectAction;
	
    private AbstractAction myPreferenceTool;

    private AbstractAction searchAction;
    
    private AbstractAction entityListAction;
    
    private JToggleButton myOverviewClipboardToggleButton;
    
    private KMADETaskModelToolBar EditorToolBar;
       
	public KMADEToolToolBar() {
        this.setMinimumSize(new Dimension(100,100));
		myMenuBar = new JMenuBar();
		
		JToolBar toolbar = new JToolBar();
		toolbar.setPreferredSize(new Dimension(460,43));
		toolbar.setFloatable(false);
		EditorToolBar = new KMADETaskModelToolBar();
		EditorToolBar.setVisible(false);

		
		// **
		// * Fichier
		// **
		JMenu myFileMenu = new JMenu(KMADEConstant.FILE_MENU_MESSAGE);
		// Nouveau projet.
		projectAction = new AbstractAction(KMADEConstant.NEW_PROJECT_ACTION_MESSAGE, NEW_PROJECT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if ( ! activeMenu()) {
				KMADeAdaptator.newProject();
				}
			}
		};
        projectAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_PROJECT_ACTION_MESSAGE);
		toolbar.add(projectAction);
		myFileMenu.add(projectAction);
		myFileMenu.addSeparator();		
		
		toolbar.addSeparator();
		
		// Charger un projet.
		loadAction = new AbstractAction(KMADEConstant.OPEN_PROJECT_ACTION_MESSAGE, OPEN_PROJECT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if ( ! activeMenu()) {
				KMADeAdaptator.loadProjectXML();
				}
			}
		};
        loadAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.OPEN_PROJECT_ACTION_MESSAGE);
		toolbar.add(loadAction);
		myFileMenu.add(loadAction);
		
		
		// Sauvegarder un projet.
		mySaveAction = new AbstractAction(KMADEConstant.SAVE_PROJECT_ACTION_MESSAGE, SAVE_PROJECT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if (! activeMenu()) {
                KMADeAdaptator.saveProjectXML();
				}
			}
		};
        mySaveAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SAVE_PROJECT_ACTION_MESSAGE);
        mySaveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		toolbar.add(mySaveAction);
		myFileMenu.add(mySaveAction);
		
        // Sauver sous ...
        mySaveAsAction = new AbstractAction(KMADEConstant.SAVE_PROJECT_AS_ACTION_MESSAGE) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
            	
                KMADeAdaptator.saveProjectXMLAs(); 
            	
            }
        };
        mySaveAsAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SAVE_PROJECT_AS_ACTION_MESSAGE);
        myFileMenu.add(mySaveAsAction);
        
        myFileMenu.addSeparator();
        AbstractAction myImportAction = new AbstractAction("Importer SPF (BETA)") {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
            	
                KMADeAdaptator.openProject();    
            	
            }
        };
        myFileMenu.add(myImportAction);
        myFileMenu.addSeparator();
        
        
		// Fermer projet
		myCloseAction = new AbstractAction(KMADEConstant.CLOSE_PROJECT_ACTION_MESSAGE, CLOSE_PROJECT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if ( ! activeMenu()) {
			    KMADeAdaptator.closeProject();
				}
			}
		};
        myCloseAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CLOSE_PROJECT_ACTION_MESSAGE);
		toolbar.add(myCloseAction);	
		myFileMenu.add(myCloseAction);		
		myFileMenu.addSeparator();
		
		toolbar.addSeparator();

        // Aperçu de l'impression.
        myOverviewPrintAction = new AbstractAction(KMADEConstant.PRINT_PREVIEW_ACTION_MESSAGE, KMADEToolToolBar.PRINT_PREVIEW) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.printCurrentTaskModel();
            	}
            }
        };
        myOverviewPrintAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_PREVIEW_ACTION_MESSAGE);
        toolbar.add(myOverviewPrintAction);
        myFileMenu.add(myOverviewPrintAction);

        
		// Imprimer modèle de tâches.
		myPrintAction = new AbstractAction(KMADEConstant.PRINT_PROJECT_ACTION_MESSAGE, KMADEToolToolBar.PRINT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if ( ! activeMenu()) {
					//TODO myPrintAction
				}
			}
		};
        myPrintAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_PROJECT_ACTION_MESSAGE);
		toolbar.add(myPrintAction);
		myFileMenu.add(myPrintAction);
        myFileMenu.addSeparator();
        myPrintAction.setEnabled(false);
		
		exitAction = new AbstractAction(KMADEConstant.EXIT_ACTION_MESSAGE) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				if ( ! activeMenu()) {
				KMADeAdaptator.closeApplication();
				}
			}
		};
		exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		myFileMenu.add(exitAction);
		myMenuBar.add(myFileMenu);
				
		toolbar.addSeparator();
		
		// **
		// * Edition
		// **
		myFileMenu = new JMenu(KMADEConstant.EDITING_MENU_MESSAGE);
		myMenuBar.add(myFileMenu);

        // Undo
        undoAction = new AbstractAction(KMADEConstant.UNDO_ACTION_MESSAGE, KMADEToolToolBar.UNDO) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
            		//TODO undoAction
            	}
            }
        };
        undoAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.UNDO_ACTION_MESSAGE);
        undoAction.setEnabled(false);
        toolbar.add(undoAction);
        myFileMenu.add(undoAction);
        
        // Redo
        redoAction = new AbstractAction(KMADEConstant.REDO_ACTION_MESSAGE, KMADEToolToolBar.REDO) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
            		//TODO redoAction
            	}
            }
        };
        redoAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.REDO_ACTION_MESSAGE);
        redoAction.setEnabled(false);
        toolbar.add(redoAction);
        myFileMenu.add(redoAction);

        myFileMenu.addSeparator();
        toolbar.addSeparator();
        
        // Cut
        myCutAction = new AbstractAction(KMADEConstant.CUT_ACTION_MESSAGE, KMADEToolToolBar.CUT) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.cutAction();
            	}
            }
        };
        myCutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CUT_ACTION_MESSAGE);
        myCutAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
        myCutAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        toolbar.add(myCutAction);
        myFileMenu.add(myCutAction);
 
        // Copy
        myCopyAction = new AbstractAction(KMADEConstant.COPY_ACTION_MESSAGE, KMADEToolToolBar.COPY) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.copyAction();
            	}
            }
        };
        myCopyAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.COPY_ACTION_MESSAGE);
        myCopyAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
        myCopyAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        toolbar.add(myCopyAction);
        myFileMenu.add(myCopyAction);

        // Paste
        myPasteAction = new AbstractAction(KMADEConstant.PASTE_ACTION_MESSAGE, KMADEToolToolBar.PASTE) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if (! activeMenu()) {
                KMADeAdaptator.pasteAction();
            	}
            }
        };
        myPasteAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PASTE_ACTION_MESSAGE);
        myPasteAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_V);
        myPasteAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        toolbar.add(myPasteAction);
        myFileMenu.add(myPasteAction);        
        myFileMenu.addSeparator();
        
        // Find Replace
        this.findReplaceAction = new AbstractAction(KMADEConstant.FIND_REPLACE_ACTION_MESSAGE) {
			private static final long serialVersionUID = -4748009240488562933L;
			public void actionPerformed(ActionEvent e) {
				
                SearchAdaptator.openFindReplaceDialog();
				
            }
        };
        findReplaceAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.FIND_REPLACE_ACTION_MESSAGE);
        findReplaceAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_F);
        findReplaceAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        myFileMenu.add(findReplaceAction);
        myFileMenu.addSeparator();
        
        // Supprimer
        myRemoveAction = new AbstractAction(KMADEConstant.DELETE_CELL_ACTION_MESSAGE, KMADEToolToolBar.DELETE) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.deleteAction();            
            	}
            }
        };
        myRemoveAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.DELETE_CELL_ACTION_MESSAGE);
        myRemoveAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
        myRemoveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_DELETE);
        
        toolbar.add(myRemoveAction);
        myFileMenu.add(myRemoveAction);    
        myFileMenu.addSeparator();
        toolbar.addSeparator();
        
        // Aperçu du presse-papier.
        myOverviewClipboardAction = new AbstractAction(KMADEConstant.SHOW_CLIPBOARD_MESSAGE, KMADEToolToolBar.CLIPBOARD_PREVIEW) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.showClipBoardOverView();
            	}
            }
        };
        myOverviewClipboardAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_CLIPBOARD_MESSAGE);
        myOverviewClipboardToggleButton = new JToggleButton(myOverviewClipboardAction);
        myOverviewClipboardToggleButton.setText("");
        toolbar.add(myOverviewClipboardToggleButton);
        myFileMenu.add(myOverviewClipboardAction);        
        
		// **
		// * Projet
		// **
		myFileMenu = new JMenu(KMADEConstant.PROJECT_MENU_MESSAGE);        
		myMenuBar.add(myFileMenu);
        mySetProjectAction = new AbstractAction(KMADEConstant.SET_EXISTING_PROJECT_SHORT_MESSAGE, KMADEToolToolBar.PROJECT_IMAGE_ICON) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                ProjectManagerAdaptator.setProject();
            	}
            }
        };
        mySetProjectAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SET_EXISTING_PROJECT_LONG_MESSAGE);
        toolbar.add(mySetProjectAction);
        myFileMenu.add(mySetProjectAction);

        // Liste des entités
        entityListAction = new AbstractAction(KMADEConstant.ENTITY_LIST_ACTION_MESSAGE, KMADEToolToolBar.ENTITY_LIST_IMAGE_ICON) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                EntityListAdaptator.openEntityListDialog();
            	}
            }
        };
        entityListAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ENTITY_LIST_ACTION_MESSAGE);
        toolbar.add(entityListAction);
        
        // Editer tâche simplement.
        myEditTaskAction = new AbstractAction(KMADEConstant.EDIT_TASK_ACTION_MESSAGE) {
            private static final long serialVersionUID = -8902209393561926967L;

            public void actionPerformed(ActionEvent e) {
            	
                GraphicEditorAdaptator.setFewAttributesSelectedTask();
            	
            }
        };
        myEditTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.EDIT_TASK_ACTION_MESSAGE);
        myFileMenu.add(myEditTaskAction);
        
        // Editer tâche complexe.
        myCompleteEditTaskAction = new AbstractAction(KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE) {
            private static final long serialVersionUID = 7525184272508549151L;

            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                TaskPropertiesEnhancedEditorAdaptator.showNMDAEnhancedTaskEditor();
            	}
            }
        };
        myCompleteEditTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE);
        myFileMenu.add(myCompleteEditTaskAction);        
        toolbar.addSeparator();
               
		// **
		// * Outils
		// **
		myFileMenu = new JMenu(KMADEConstant.TOOLS_MENU_MESSAGE);
		myMenuBar.add(myFileMenu);
        
        simulationAction = new AbstractAction(KMADEConstant.SIMULATION_ACTION_MESSAGE) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
       
                KMADeAdaptator.openSimulationDialog();
            	
            }
        };
        simulationAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SIMULATION_ACTION_MESSAGE);
        simulationAction.setEnabled(true);
        myFileMenu.add(simulationAction);
    
        statisticAction = new AbstractAction(KMADEConstant.INTERROGATION_ACTION_MESSAGE) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	KMADeAdaptator.openStatisticDialog();
            }
        };
        statisticAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.INTERROGATION_ACTION_MESSAGE);
        statisticAction.setEnabled(false);
        myFileMenu.add(statisticAction);
        
        checkAction = new AbstractAction(KMADEConstant.CHECK_COHERENCE_ACTION_MESSAGE) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
            	 KMADeAdaptator.openCoherenceDialog();
            }
        };
        checkAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CHECK_COHERENCE_ACTION_MESSAGE);
        checkAction.setEnabled(false);
        myFileMenu.add(checkAction);

        searchAction = new AbstractAction(KMADEConstant.SEARCH_ACTION_MESSAGE) {
            private static final long serialVersionUID = 4428123559892038081L;
            public void actionPerformed(ActionEvent e) {
            	//TODO searchAction
            }
        };
        searchAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SEARCH_ACTION_MESSAGE);
        searchAction.setEnabled(false);
        myFileMenu.add(searchAction);
       
        // **
        // * Visualisation
        // **
        myFileMenu = new JMenu(KMADEConstant.VIEW_MENU_MESSAGE);
        // Options d'affichage : cacher/montrer la grille.
        myGridAction = new AbstractAction(KMADEConstant.HIDE_GRID_ACTION_MESSAGE, KMADEToolToolBar.SHOW_GRILLE) {
            private static final long serialVersionUID = -3565098144261030486L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.showOrHideGrid();
            }
        };
        myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
        myFileMenu.add(myGridAction);

        // Options d'affichage : modifier le grain de la grille.
        mySetGridAction = new AbstractAction(KMADEConstant.CHOICE_GRID_SIZE_MESSAGE, KMADEToolToolBar.CHOICE_GRID_SIZE) {
            private static final long serialVersionUID = -717649588461770417L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.setGridSize();
            }
        };
        mySetGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CHOICE_GRID_SIZE_MESSAGE);
        myFileMenu.add(mySetGridAction);
        
        // Options d'affichage : cacher/montrer la r�gle.
        myRuleAction = new AbstractAction(KMADEConstant.SHOW_RULE_ACTION_MESSAGE, KMADEToolToolBar.SHOW_RULE) {
            private static final long serialVersionUID = -7001694110308248140L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.showOrHideRule();
            }
        };
        myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
        myFileMenu.add(myRuleAction);
        
        // Fenêtre d'aperçu
        myOverviewWindowAction = new AbstractAction(KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE, OVERVIEW_PREVIEW) {
            private static final long serialVersionUID = 5128574049819721220L;
            public void actionPerformed(ActionEvent e) {
                KMADeAdaptator.showOverviewWindow();
            }
        };
        myOverviewWindowAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE);
        myFileMenu.add(myOverviewWindowAction);
        myMenuBar.add(myFileMenu);
        myFileMenu.addSeparator();
        
        myPreferenceTool = new AbstractAction(KMADEConstant.PREFERENCE_ACTION_MESSAGE, OPTIONS_PREVIEW) {
            private static final long serialVersionUID = 5128574049819721220L;
            public void actionPerformed(ActionEvent e) {
            	if ( ! activeMenu()) {
                KMADeAdaptator.showPreferences();
            	}
            }
        };
        myPreferenceTool.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PREFERENCE_ACTION_MESSAGE);
        myFileMenu.add(myPreferenceTool);
        toolbar.add(myPreferenceTool);
        toolbar.addSeparator();
        myPreferenceTool.setEnabled(true);
        myMenuBar.add(myFileMenu);
        
		// **
		// * Aide
		// **
		myFileMenu = new JMenu(KMADEConstant.HELP_MENU_MESSAGE);
		myMenuBar.add(myFileMenu);

		// Documentation Mod�le
		modeleAction = new AbstractAction(KMADEConstant.HELP_MODEL_ACTION_MESSAGE) {
			private static final long serialVersionUID = -7001694110308248140L;
			public void actionPerformed(ActionEvent e) {
				KMADeAdaptator.showHelpModelDialog();
			}
		};
		myFileMenu.add(modeleAction);
        modeleAction.setEnabled(false);

		// Documentation Outils
		toolAction = new AbstractAction(KMADEConstant.HELP_TOOL_ACTION_MESSAGE) {
			private static final long serialVersionUID = -7001694110308248140L;
			public void actionPerformed(ActionEvent e) {
				KMADeAdaptator.showHelpToolInfoDebugDialog();
			}
		};
		myFileMenu.add(toolAction);
        toolAction.setEnabled(false);
		myFileMenu.addSeparator();

		// Debug Info
		debugAction = new AbstractAction(KMADEConstant.DEBUG_INFO_ACTION_MESSAGE) {
			private static final long serialVersionUID = -7001694110308248140L;
			public void actionPerformed(ActionEvent e) {
				KMADeAdaptator.showInfoDebugDialog();
			}
		};
		myFileMenu.add(debugAction);
        
        historyAction = new AbstractAction(KMADEConstant.HISTORY_ACTION_MESSAGE) {
            private static final long serialVersionUID = -7001694110308248140L;
            public void actionPerformed(ActionEvent e) {
                KMADeAdaptator.showHistoryDialog();
            }
        };
        myFileMenu.add(historyAction);

		myFileMenu.addSeparator();
		
		// About
		aboutAction = new AbstractAction(KMADEConstant.ABOUT_TITLE_NAME) {
			private static final long serialVersionUID = -7001694110308248140L;
			public void actionPerformed(ActionEvent e) {
				KMADeAdaptator.showAboutDialog();
			}
		};
		myFileMenu.add(aboutAction);        
        
        JToolBar moreToolbar = new JToolBar();
        toolbar.setRollover(true);
        toolbar.setFloatable(false);
        moreToolbar.setRollover(true);
        moreToolbar.setFloatable(false); 
        moreToolbar.add(new KMADEMoreButton(toolbar)); 
 
        this.setLayout(new BorderLayout());
        this.add(toolbar, BorderLayout.WEST);
        this.add(EditorToolBar, BorderLayout.CENTER);
        this.add(moreToolbar, BorderLayout.EAST);
	}

	public KMADETaskModelToolBar getEditorsToolBar() {
		return EditorToolBar;
	}
	/* un test ...
	public void setVisibleEditorsToolBar(Boolean bool){
		if(bool){
			// on regarde si le composant n'est pas d�j� pr�sent avant de l'ajouter
			EditorToolBar.setVisible(true);
			Component[] compo = this.getComponents();
			boolean haveIt = false;
			for(Component cur:compo ){
				if(cur.equals(EditorToolBar)){
					haveIt = true;
				}
			}
			if(!haveIt){
				this.add(EditorToolBar, BorderLayout.CENTER);
			}
		}else{
			System.err.println("testremove !s");
			EditorToolBar.setVisible(false);
				this.getLayout().removeLayoutComponent(EditorToolBar);
		}
	}
	*/
    public void setEnabledGrid() {
        myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
        myGridAction.putValue(AbstractAction.NAME, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);         
        myGridAction.putValue(AbstractAction.SMALL_ICON,KMADEToolToolBar.SHOW_GRILLE);
    }
    
    public void setDisabledGrid() {
        myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_GRID_ACTION_MESSAGE);
        myGridAction.putValue(AbstractAction.NAME, KMADEConstant.SHOW_GRID_ACTION_MESSAGE);         
        myGridAction.putValue(AbstractAction.SMALL_ICON,KMADEToolToolBar.HIDE_GRILLE);
    }
    
    public void setEnabledRule() {
        myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_RULE_ACTION_MESSAGE);
        myRuleAction.putValue(AbstractAction.NAME, KMADEConstant.HIDE_RULE_ACTION_MESSAGE);
        myRuleAction.putValue(AbstractAction.SMALL_ICON,KMADEToolToolBar.SHOW_RULE);
    }
    
    public void setDisabledRule() {
        myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
        myRuleAction.putValue(AbstractAction.NAME, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);         
        myRuleAction.putValue(AbstractAction.SMALL_ICON,KMADEToolToolBar.HIDE_RULE);
    }
    
	/**
	 * @return Returns the myMenuBar.
	 */
	public JMenuBar getMenuBar() {
		return myMenuBar;
	}    

    public void setSelectedOverviewClipboardToggleButton(boolean t) {
        this.myOverviewClipboardToggleButton.setSelected(t);
    }
    
    public void noFileInEditingState() {
        this.mySaveAction.setEnabled(false);
    }
    
    public void fileInEditingState() {
        this.mySaveAction.setEnabled(true);
    }
    
    public void noProjectViewState() {
        this.myCutAction.setEnabled(false);
        this.myCopyAction.setEnabled(false);
        this.myPasteAction.setEnabled(false);
        this.myRemoveAction.setEnabled(false);
        this.mySaveAction.setEnabled(false);
        this.mySaveAsAction.setEnabled(false);
        this.myCloseAction.setEnabled(false);
        this.myOverviewPrintAction.setEnabled(false);
        this.myOverviewClipboardToggleButton.setSelected(false);
        this.myPrintAction.setEnabled(false);
        this.myOverviewClipboardAction.setEnabled(false);
        this.myGridAction.setEnabled(false);
        this.myRuleAction.setEnabled(false);
        this.mySetGridAction.setEnabled(false);
        this.myOverviewWindowAction.setEnabled(false);
        this.mySetProjectAction.setEnabled(false);
        this.myEditTaskAction.setEnabled(false);
        this.myCompleteEditTaskAction.setEnabled(false);
        this.searchAction.setEnabled(false);
        this.findReplaceAction.setEnabled(false);
        this.simulationAction.setEnabled(false);
        this.checkAction.setEnabled(false);
        this.statisticAction.setEnabled(false);
        this.entityListAction.setEnabled(false);
    }
    
    public void existProjectViewState() {
        this.mySaveAsAction.setEnabled(true);
        this.myCloseAction.setEnabled(true);
        this.myOverviewPrintAction.setEnabled(true);
        this.myOverviewClipboardAction.setEnabled(true);
        this.myGridAction.setEnabled(true);
        this.myRuleAction.setEnabled(true);
        this.mySetGridAction.setEnabled(true);
        this.myOverviewWindowAction.setEnabled(true);
        this.mySetProjectAction.setEnabled(true);
        this.findReplaceAction.setEnabled(true);
        this.simulationAction.setEnabled(true);
        this.checkAction.setEnabled(true);
        this.statisticAction.setEnabled(true);
        this.entityListAction.setEnabled(true);
    }
    
    public void selectedEdgeViewState() {
        this.myRemoveAction.setEnabled(true);
        this.myCopyAction.setEnabled(false);
        this.myCutAction.setEnabled(false);
        this.myEditTaskAction.setEnabled(false);
        this.myCompleteEditTaskAction.setEnabled(false);
    }
    
    public void selectedElementsViewState() {
        this.myCopyAction.setEnabled(true);
        this.myCutAction.setEnabled(true);
        this.myRemoveAction.setEnabled(true);
        this.myEditTaskAction.setEnabled(false);
        this.myCompleteEditTaskAction.setEnabled(false);
    }
    
    public void selectedElementViewState() {
        this.myCopyAction.setEnabled(true);
        this.myCutAction.setEnabled(true);
        this.myRemoveAction.setEnabled(true);
        this.myEditTaskAction.setEnabled(true);
        this.myCompleteEditTaskAction.setEnabled(true);
    }
    
    public void noSelectedElementViewState() {
        this.myCopyAction.setEnabled(false);
        this.myCutAction.setEnabled(false);
        this.myRemoveAction.setEnabled(false);
        this.myEditTaskAction.setEnabled(false);
        this.myCompleteEditTaskAction.setEnabled(false);
    }
    
    public void enablePasteActionViewState() {
        this.myPasteAction.setEnabled(true);
    }

    public void disablePasteActionViewState() {
        this.myPasteAction.setEnabled(false);
    }
   
    public JPopupMenu createPopupMenuForAdd(final Point pt) {
        JPopupMenu menu = new JPopupMenu();
        
        AbstractAction myAction = new AbstractAction(KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE, KMADETaskModelToolBar.UNKNOWN_TASK_IMAGE_ICON) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {                
                GraphicEditorAdaptator.addNewUnknownTask(pt);
            }
        };
        menu.add(myAction);
        
        myAction = new AbstractAction(KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE, KMADETaskModelToolBar.ABSTRACT_TASK_IMAGE_ICON) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.addNewAbstractTask(pt);
            }
        };
        menu.add(myAction);
        
        myAction = new AbstractAction(KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE, KMADETaskModelToolBar.USER_TASK_IMAGE_ICON) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.addNewUserTask(pt);
            }
        };
        menu.add(myAction);
        
        myAction = new AbstractAction(KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE, KMADETaskModelToolBar.FEEDBACK_TASK_IMAGE_ICON) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.addNewFeedbackTask(pt);
            }
        };
        menu.add(myAction);
 
        myAction = new AbstractAction(KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE, KMADETaskModelToolBar.INTERACTION_TASK_IMAGE_ICON) {
            private static final long serialVersionUID = 838662422873173261L;
            public void actionPerformed(ActionEvent e) {
                GraphicEditorAdaptator.addNewInteractionTask(pt);
            }
        };
        menu.add(myAction);
        menu.addSeparator(); 
        menu.add(myPasteAction);
        return menu;
    }
    
    public JPopupMenu createPopupMenuForOneTask(final Point pt) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(myEditTaskAction);
        menu.add(myCompleteEditTaskAction);
        menu.addSeparator();
        menu.add(myCutAction);
        menu.add(myCopyAction);
        menu.add(myPasteAction);
        menu.addSeparator();
        menu.add(myRemoveAction);
        return menu;
    }
    
    public JPopupMenu createPopupMenuForSeveralTasks(final Point pt) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(myCutAction);
        menu.add(myCopyAction);
        menu.add(myPasteAction);
        menu.addSeparator();        
        menu.add(myRemoveAction);
        return menu;    	
    }
    
    public JPopupMenu createPopupMenuForSeveralEdges(final Point pt) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(myRemoveAction);
        return menu;
    }

    public JPopupMenu createPopupMenuForOneEdge(Point pt) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(myRemoveAction);
        return menu;
    }

    public void notifLocalisationModification() {
        modeleAction.putValue(AbstractAction.NAME,KMADEConstant.HELP_MODEL_ACTION_MESSAGE);               
        toolAction.putValue(AbstractAction.NAME,KMADEConstant.DEBUG_INFO_ACTION_MESSAGE);               
        debugAction.putValue(AbstractAction.NAME,KMADEConstant.DEBUG_INFO_ACTION_MESSAGE);
        aboutAction.putValue(AbstractAction.NAME,KMADEConstant.ABOUT_TITLE_NAME);       
        simulationAction.putValue(AbstractAction.NAME,KMADEConstant.SIMULATION_ACTION_MESSAGE);
        simulationAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SIMULATION_ACTION_MESSAGE);              
        statisticAction.putValue(AbstractAction.NAME,KMADEConstant.INTERROGATION_ACTION_MESSAGE);      
        statisticAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.INTERROGATION_ACTION_MESSAGE);
        checkAction.putValue(AbstractAction.NAME,KMADEConstant.CHECK_COHERENCE_ACTION_MESSAGE);
        checkAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CHECK_COHERENCE_ACTION_MESSAGE);        
        redoAction.putValue(AbstractAction.NAME,KMADEConstant.REDO_ACTION_MESSAGE);
        redoAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.REDO_ACTION_MESSAGE);
        undoAction.putValue(AbstractAction.NAME,KMADEConstant.UNDO_ACTION_MESSAGE);
        undoAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.UNDO_ACTION_MESSAGE);        
        projectAction.putValue(AbstractAction.NAME,KMADEConstant.NEW_PROJECT_ACTION_MESSAGE);
        projectAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_PROJECT_ACTION_MESSAGE);
        exitAction.putValue(AbstractAction.NAME,KMADEConstant.EXIT_ACTION_MESSAGE);
        loadAction.putValue(AbstractAction.NAME,KMADEConstant.OPEN_PROJECT_ACTION_MESSAGE);
        loadAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.OPEN_PROJECT_ACTION_MESSAGE);             
        myGridAction.putValue(AbstractAction.NAME, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
        myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
        myRuleAction.putValue(AbstractAction.NAME,KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
        myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
        myEditTaskAction.putValue(AbstractAction.NAME,KMADEConstant.EDIT_TASK_ACTION_MESSAGE);        
        myEditTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.EDIT_TASK_ACTION_MESSAGE);
        myCompleteEditTaskAction.putValue(AbstractAction.NAME,KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE);
        myCompleteEditTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE);        
        myCutAction.putValue(AbstractAction.NAME,KMADEConstant.CUT_ACTION_MESSAGE);
        myCutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CUT_ACTION_MESSAGE);
        myCopyAction.putValue(AbstractAction.NAME,KMADEConstant.COPY_ACTION_MESSAGE);
        myCopyAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.COPY_ACTION_MESSAGE);
        myPasteAction.putValue(AbstractAction.NAME,KMADEConstant.PASTE_ACTION_MESSAGE);
        myPasteAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PASTE_ACTION_MESSAGE);
        myRemoveAction.putValue(AbstractAction.NAME,KMADEConstant.DELETE_CELL_ACTION_MESSAGE);
        myRemoveAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.DELETE_CELL_ACTION_MESSAGE);
        mySaveAction.putValue(AbstractAction.NAME,KMADEConstant.SAVE_PROJECT_ACTION_MESSAGE);
        mySaveAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SAVE_PROJECT_ACTION_MESSAGE);
        mySaveAsAction.putValue(AbstractAction.NAME,KMADEConstant.SAVE_PROJECT_AS_ACTION_MESSAGE);
        mySaveAsAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SAVE_PROJECT_AS_ACTION_MESSAGE);
        myCloseAction.putValue(AbstractAction.NAME,KMADEConstant.CLOSE_PROJECT_ACTION_MESSAGE);
        myCloseAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CLOSE_PROJECT_ACTION_MESSAGE);
        myOverviewPrintAction.putValue(AbstractAction.NAME,KMADEConstant.PRINT_PREVIEW_ACTION_MESSAGE);
        myOverviewPrintAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_PREVIEW_ACTION_MESSAGE);
        myPrintAction.putValue(AbstractAction.NAME,KMADEConstant.PRINT_PROJECT_ACTION_MESSAGE);
        myPrintAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_PROJECT_ACTION_MESSAGE);
        myOverviewClipboardAction.putValue(AbstractAction.NAME,KMADEConstant.SHOW_CLIPBOARD_MESSAGE);
        myOverviewClipboardAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_CLIPBOARD_MESSAGE);
        mySetGridAction.putValue(AbstractAction.NAME,KMADEConstant.CHOICE_GRID_SIZE_MESSAGE);
        mySetGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CHOICE_GRID_SIZE_MESSAGE);
        myOverviewWindowAction.putValue(AbstractAction.NAME, KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE);
        myOverviewWindowAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE);
        mySetProjectAction.putValue(AbstractAction.NAME,KMADEConstant.SET_EXISTING_PROJECT_SHORT_MESSAGE);        
        mySetProjectAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SET_EXISTING_PROJECT_LONG_MESSAGE);      
        myPreferenceTool.putValue(AbstractAction.NAME,KMADEConstant.PREFERENCE_ACTION_MESSAGE);
        myPreferenceTool.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PREFERENCE_ACTION_MESSAGE);
    }

	public AbstractAction getLoadAction() {
		return loadAction;
	}

	public AbstractAction getProjectAction() {
		return projectAction;
	} 
	
	/** teste si un des menus est actif */
	public boolean activeMenu() {
		KMADEProjectPanel projectPanel = KMADEMainFrame.getProjectPanel();
		if( projectPanel.getTaskModelPanel().getMenuTask() != null ) {
			if ( projectPanel.getTaskModelPanel().getMenuTask().isVisible()) {
				return true;
			}
		}
		
		if( projectPanel.getTaskModelPanel().getMenuEditDecomposition() != null ) {
			if ( projectPanel.getTaskModelPanel().getMenuEditDecomposition().isVisible()) {
				return true;
			}
		}
		
		if( projectPanel.getTaskModelPanel().getMenuEditExecutant() != null ) {
			if ( projectPanel.getTaskModelPanel().getMenuEditExecutant().isVisible()) {
				return true;
			}
		}
		
		if( projectPanel.getTaskModelPanel().getMenuEdition() != null ) {
			if ( projectPanel.getTaskModelPanel().getMenuEdition().isVisible()) {
				return true;
			}
		}
				
		return false;
	}

 }

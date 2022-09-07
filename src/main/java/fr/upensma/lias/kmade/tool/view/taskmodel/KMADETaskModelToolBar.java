/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.KMADEProjectPanel;
import fr.upensma.lias.kmade.tool.view.KMADEToolToolBar;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.KMADeAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADETaskModelToolBar extends JPanel {

	private static final long serialVersionUID = 7064622303016526648L;

	public static final ImageIcon ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE));

	public static final ImageIcon INTERACTION_TASK_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));

	public static final ImageIcon FEEDBACK_TASK_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));

	public static final ImageIcon USER_TASK_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_16_IMAGE));

	public static final ImageIcon UNKNOWN_TASK_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE));

	public static final ImageIcon ZOOMIN_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.ZOOM_IN_IMAGE));

	public static final ImageIcon ZOOMOUT_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.ZOOM_OUT_IMAGE));

	public static final ImageIcon ZOOM_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.ZOOM_IMAGE));

	public static final ImageIcon ZOOM_GLOBAL_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.ZOOM_GLOBAL_IMAGE));

	public static final ImageIcon MAGN_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.MAGNETISM_IMAGE));

	public static final ImageIcon JUSTIFY_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.JUSTIFY_IMAGE));

	public static final ImageIcon UNIFORM_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.UNIFORM_IMAGE));

	public static final ImageIcon COLOR_LABEL_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.LABEL_COLOR_IMAGE));

	public static final ImageIcon VISIBLE_LABEL_IMAGE_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.LABEL_VISIBLE_IMAGE));

	/*** Image bouton lancement dialogue simulation ***/
	public static final ImageIcon OPEN_SIMULATION_ICON = new ImageIcon(
			KMADETaskModelToolBar.class.getResource(KMADEConstant.GO_SIMULATION_SCENARIO_IMAGE));

	private JToggleButton unknownTask;

	private JToggleButton abstractTask;

	private JToggleButton userTask;

	private JToggleButton systemTask;

	private JToggleButton interactionTask;

	private JToggleButton magnetOption;

	private AbstractAction unknownTaskAction;

	private AbstractAction abstractTaskAction;

	private AbstractAction userTaskAction;

	private AbstractAction feedbackTaskAction;

	private AbstractAction interactionTaskAction;

	private AbstractAction zoomInAction;

	private AbstractAction zoomOutAction;

	private AbstractAction zoomDefaultAction;

	private AbstractAction magnetismAction;

	private AbstractAction justificationAction;

	private AbstractAction sameExecutant;

	private AbstractAction globalGraph;

	private AbstractAction myGridAction;

	private AbstractAction myRuleAction;

	private AbstractAction mySetGridAction;

	private AbstractAction myOverviewWindowAction;

	/*** Lancement simulation dialogue ***/
	private AbstractAction openSimulationAction;

	private JToggleButton myShowOrNotTaskLabelToggleButton;

	private JToggleButton myShowOrNotTaskColorLabelToggleButton;

	private JToggleButton myOverviewWindowToggleButton;

	private JToolBar toolbar;

	public void enabledButton() {
		unknownTask.setSelected(false);
		abstractTask.setSelected(false);
		userTask.setSelected(false);
		systemTask.setSelected(false);
		interactionTask.setSelected(false);
	}

	public void enabledUnknownButton() {
		abstractTask.setSelected(false);
		userTask.setSelected(false);
		systemTask.setSelected(false);
		interactionTask.setSelected(false);
	}

	public void enabledAbstractButton() {
		unknownTask.setSelected(false);
		userTask.setSelected(false);
		systemTask.setSelected(false);
		interactionTask.setSelected(false);
	}

	public void enabledUserButton() {
		unknownTask.setSelected(false);
		abstractTask.setSelected(false);
		systemTask.setSelected(false);
		interactionTask.setSelected(false);
	}

	public void enabledSystemButton() {
		unknownTask.setSelected(false);
		abstractTask.setSelected(false);
		userTask.setSelected(false);
		interactionTask.setSelected(false);
	}

	public void enabledInteractionButton() {
		unknownTask.setSelected(false);
		abstractTask.setSelected(false);
		userTask.setSelected(false);
		systemTask.setSelected(false);
	}

	public boolean isMagnet() {
		return magnetOption.isSelected();
	}

	public KMADETaskModelToolBar() {
		toolbar = new JToolBar();
		toolbar.setFloatable(false);

		// Ins�rer une t�che inconnue.
		unknownTaskAction = new AbstractAction("", UNKNOWN_TASK_IMAGE_ICON) {
			private static final long serialVersionUID = 838662422873173261L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.addNewUnknownTask();
				}
			}
		};
		unknownTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE);
		unknownTask = new JToggleButton(unknownTaskAction);
		toolbar.add(unknownTask);

		// Ins�rer une t�che abstraite.
		abstractTaskAction = new AbstractAction("", ABSTRACT_TASK_IMAGE_ICON) {
			private static final long serialVersionUID = 838662422873173261L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.addNewAbstractTask();
				}
			}
		};
		abstractTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE);
		abstractTask = new JToggleButton(abstractTaskAction);
		toolbar.add(abstractTask);

		// Ins�rer une t�che utilisateur
		userTaskAction = new AbstractAction("", USER_TASK_IMAGE_ICON) {
			private static final long serialVersionUID = 838662422873173261L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.addNewUserTask();
				}
			}
		};
		userTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE);
		userTask = new JToggleButton(userTaskAction);
		toolbar.add(userTask);

		// Ins�rer une t�che feedback.
		feedbackTaskAction = new AbstractAction("", FEEDBACK_TASK_IMAGE_ICON) {
			private static final long serialVersionUID = 838662422873173261L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.addNewFeedbackTask();
				}
			}
		};
		feedbackTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE);
		systemTask = new JToggleButton(feedbackTaskAction);
		toolbar.add(systemTask);

		// Ins�rer une t�che interaction.
		interactionTaskAction = new AbstractAction("", INTERACTION_TASK_IMAGE_ICON) {
			private static final long serialVersionUID = 838662422873173261L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.addNewInteractionTask();
				}
			}
		};
		interactionTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION,
				KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE);
		interactionTask = new JToggleButton(interactionTaskAction);
		toolbar.add(interactionTask);
		toolbar.addSeparator();

		// Zoom In
		zoomInAction = new AbstractAction("", KMADETaskModelToolBar.ZOOMIN_IMAGE_ICON) {
			private static final long serialVersionUID = 5128574049819721220L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setPlusZoom();
				}
			}
		};
		zoomInAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_IN_ACTION_MESSAGE);
		toolbar.add(zoomInAction);

		// Zoom Out
		zoomOutAction = new AbstractAction("", KMADETaskModelToolBar.ZOOMOUT_IMAGE_ICON) {
			private static final long serialVersionUID = 5128574049819721220L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setMinusZoom();
				}
			}
		};
		zoomOutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_OUT_ACTION_MESSAGE);
		toolbar.add(zoomOutAction);

		// Zoom Default
		zoomDefaultAction = new AbstractAction("", KMADETaskModelToolBar.ZOOM_IMAGE_ICON) {
			private static final long serialVersionUID = 5128574049819721220L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setDefaultZoom();
				}
			}
		};
		zoomDefaultAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_DEFAULT_ACTION_MESSAGE);
		toolbar.add(zoomDefaultAction);

		// Zoom globale
		globalGraph = new AbstractAction("", KMADETaskModelToolBar.ZOOM_GLOBAL_ICON) {
			private static final long serialVersionUID = 4000465475546408455L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setGlobalTaskModel();
				}
			}
		};
		globalGraph.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_GLOBAL_ACTION_MESSAGE);
		toolbar.add(globalGraph);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(toolbar);
		toolbar.addSeparator();

		myGridAction = new AbstractAction(KMADEConstant.HIDE_GRID_ACTION_MESSAGE, KMADEToolToolBar.SHOW_GRILLE) {
			private static final long serialVersionUID = -3565098144261030486L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.showOrHideGrid();
				}
			}
		};
		myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
		toolbar.add(myGridAction);

		// Options d'affichage : modifier le grain de la grille.
		mySetGridAction = new AbstractAction(KMADEConstant.CHOICE_GRID_SIZE_MESSAGE,
				KMADEToolToolBar.CHOICE_GRID_SIZE) {
			private static final long serialVersionUID = -717649588461770417L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setGridSize();
				}
			}
		};
		mySetGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.CHOICE_GRID_SIZE_MESSAGE);
		toolbar.add(mySetGridAction);

		// Options d'affichage : cacher/montrer la r�gle.
		myRuleAction = new AbstractAction(KMADEConstant.SHOW_RULE_ACTION_MESSAGE, KMADEToolToolBar.SHOW_RULE) {
			private static final long serialVersionUID = -7001694110308248140L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.showOrHideRule();
				}
			}
		};
		myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
		toolbar.add(myRuleAction);

		// Fenêtre d'aperçu
		myOverviewWindowAction = new AbstractAction(KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE,
				KMADEToolToolBar.OVERVIEW_PREVIEW) {
			private static final long serialVersionUID = 5128574049819721220L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					KMADeAdaptator.showOverviewWindow();
				}
			}
		};
		myOverviewWindowAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE);
		myOverviewWindowToggleButton = new JToggleButton(myOverviewWindowAction);
		myOverviewWindowToggleButton.setRolloverEnabled(false);
		myOverviewWindowToggleButton.setText("");
		toolbar.add(myOverviewWindowToggleButton);
		toolbar.addSeparator();

		// Magnetisme
		magnetismAction = new AbstractAction("", KMADETaskModelToolBar.MAGN_IMAGE_ICON) {
			private static final long serialVersionUID = -6700309108031818171L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					// TODO magnetismAction
				}
			}
		};
		magnetismAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.MAGNET_OPTION_ACTION_MESSAGE);
		magnetOption = new JToggleButton(magnetismAction);
		toolbar.add(magnetOption);

		// Justification
		justificationAction = new AbstractAction("", KMADETaskModelToolBar.JUSTIFY_IMAGE_ICON) {
			private static final long serialVersionUID = 4000465475546408455L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.setJustifyTaskModel();
				}
			}
		};
		justificationAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.JUSTIFY_OPTION_ACTION_MESSAGE);
		toolbar.add(justificationAction);

		// Appliquer le même exécutant
		sameExecutant = new AbstractAction("", KMADETaskModelToolBar.UNIFORM_IMAGE_ICON) {
			private static final long serialVersionUID = 4000465475546408455L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.applySameExecutant();
				}
			}
		};
		sameExecutant.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SAME_EXECUTANT_MESSAGE);
		toolbar.add(sameExecutant);
		toolbar.addSeparator();

		// Activer l'affichage des labels
		AbstractAction myShowOrNotTaskLabelToggleAction = new AbstractAction("",
				KMADETaskModelToolBar.VISIBLE_LABEL_IMAGE_ICON) {
			private static final long serialVersionUID = -9065303558600036445L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.refreshJGraphView();
				}
			}
		};
		myShowOrNotTaskLabelToggleAction.putValue(AbstractAction.SHORT_DESCRIPTION,
				KMADEConstant.VISIBLE_LABEL_ACTION_MESSAGE);
		myShowOrNotTaskLabelToggleButton = new JToggleButton(myShowOrNotTaskLabelToggleAction);
		toolbar.add(myShowOrNotTaskLabelToggleButton);
		myShowOrNotTaskLabelToggleButton.setSelected(false);

		// Activer la couleur des labels
		AbstractAction myShowOrNotTaskColorLabelToggleAction = new AbstractAction("",
				KMADETaskModelToolBar.COLOR_LABEL_IMAGE_ICON) {
			private static final long serialVersionUID = -4598463136823680701L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					GraphicEditorAdaptator.refreshJGraphView();
				}
			}
		};
		myShowOrNotTaskColorLabelToggleAction.putValue(AbstractAction.SHORT_DESCRIPTION,
				KMADEConstant.COLOR_LABEL_ACTION_MESSAGE);
		myShowOrNotTaskColorLabelToggleButton = new JToggleButton(myShowOrNotTaskColorLabelToggleAction);
		toolbar.add(myShowOrNotTaskColorLabelToggleButton);

		/*** Boite de dialogue lancement de simulation ***/
		toolbar.addSeparator();
		openSimulationAction = new AbstractAction("", KMADETaskModelToolBar.OPEN_SIMULATION_ICON) {
			private static final long serialVersionUID = 4000465475546408455L;

			public void actionPerformed(ActionEvent e) {
				if (!activeMenu()) {
					KMADeAdaptator.openSimulationDialog();
				}
			}
		};
		openSimulationAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.OPEN_SIMULATION_MESSAGE);
		toolbar.add(openSimulationAction);

	}

	public boolean isLabelSelected() {
		return this.myShowOrNotTaskLabelToggleButton.isSelected();
	}

	public void setLabelSelected(boolean p) {
		this.myShowOrNotTaskLabelToggleButton.setSelected(p);
	}

	public boolean isLabelColorSelected() {
		return this.myShowOrNotTaskColorLabelToggleButton.isSelected();
	}

	public void setLabelColorSelected(boolean p) {
		this.myShowOrNotTaskColorLabelToggleButton.setSelected(p);
	}

	public void selectedElementViewState() {
		justificationAction.setEnabled(true);
		sameExecutant.setEnabled(true);
	}

	public void noSelectedElementViewState() {
		justificationAction.setEnabled(false);
		sameExecutant.setEnabled(false);
	}

	public void selectedElementsViewState() {
		this.noSelectedElementViewState();
	}

	public void setSelectedOverviewWindowToggleButton(boolean t) {
		this.myOverviewWindowToggleButton.setSelected(t);
	}

	public void notifLocalisationModification() {
		unknownTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE);
		abstractTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE);
		userTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE);
		feedbackTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE);
		interactionTaskAction.putValue(AbstractAction.SHORT_DESCRIPTION,
				KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE);
		zoomInAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_IN_ACTION_MESSAGE);
		zoomOutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_OUT_ACTION_MESSAGE);
		zoomDefaultAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_DEFAULT_ACTION_MESSAGE);
		magnetismAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.MAGNET_OPTION_ACTION_MESSAGE);
		justificationAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.JUSTIFY_OPTION_ACTION_MESSAGE);
	}

	public void setEnabledGrid() {
		myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
		myGridAction.putValue(AbstractAction.NAME, KMADEConstant.HIDE_GRID_ACTION_MESSAGE);
		myGridAction.putValue(AbstractAction.SMALL_ICON, KMADEToolToolBar.SHOW_GRILLE);
	}

	public void setDisabledGrid() {
		myGridAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_GRID_ACTION_MESSAGE);
		myGridAction.putValue(AbstractAction.NAME, KMADEConstant.SHOW_GRID_ACTION_MESSAGE);
		myGridAction.putValue(AbstractAction.SMALL_ICON, KMADEToolToolBar.HIDE_GRILLE);
	}

	public void setEnabledRule() {
		myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.HIDE_RULE_ACTION_MESSAGE);
		myRuleAction.putValue(AbstractAction.NAME, KMADEConstant.HIDE_RULE_ACTION_MESSAGE);
		myRuleAction.putValue(AbstractAction.SMALL_ICON, KMADEToolToolBar.SHOW_RULE);
	}

	public void setDisabledRule() {
		myRuleAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
		myRuleAction.putValue(AbstractAction.NAME, KMADEConstant.SHOW_RULE_ACTION_MESSAGE);
		myRuleAction.putValue(AbstractAction.SMALL_ICON, KMADEToolToolBar.HIDE_RULE);
	}

	public void setVisible(boolean s) {
		toolbar.setVisible(s);
	}

	/**
	 * Teste si un des menus est actif
	 */
	private boolean activeMenu() {
		KMADEProjectPanel projectPanel = KMADEMainFrame.getProjectPanel();
		if (projectPanel.getTaskModelPanel().getMenuTask() != null) {
			if (projectPanel.getTaskModelPanel().getMenuTask().isVisible()) {
				return true;
			}
		}

		if (projectPanel.getTaskModelPanel().getMenuEditDecomposition() != null) {
			if (projectPanel.getTaskModelPanel().getMenuEditDecomposition().isVisible()) {
				return true;
			}
		}

		if (projectPanel.getTaskModelPanel().getMenuEditExecutant() != null) {
			if (projectPanel.getTaskModelPanel().getMenuEditExecutant().isVisible()) {
				return true;
			}
		}

		if (projectPanel.getTaskModelPanel().getMenuEdition() != null) {
			if (projectPanel.getTaskModelPanel().getMenuEdition().isVisible()) {
				return true;
			}
		}

		return false;
	}
}

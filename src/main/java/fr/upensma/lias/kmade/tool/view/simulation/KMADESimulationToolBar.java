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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.KMADEToolToolBar;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEMoreButton;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.SimulationAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADESimulationToolBar extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = 3835132181112170183L;

    private AbstractAction zoomInAction;

    private AbstractAction zoomOutAction;

    private AbstractAction zoomDefaultAction;

    private AbstractAction myOverviewWindowAction;

    private AbstractAction globalGraphAction;

    private JToggleButton myOverviewWindowToggleButton;

    private JToggleButton preEnabledButton;

    private JToggleButton postEnabledButton;

    private JToggleButton iterEnabledButton;

    private JToggleButton exeEnabledButton;

    private JToggleButton eventEnabledButton;

    private JToggleButton eventTriggerEnabledButton;

    private AbstractAction editUserValues;

    private JToggleButton myShowOrNotTaskLabelAction;

    private JToggleButton myShowOrNotTaskColorLabelAction;

    public boolean isEventSelected() {
	return this.eventEnabledButton.isSelected();
    }

    public boolean isTrigEventSelected() {
	return this.eventTriggerEnabledButton.isSelected();
    }

    public boolean isExeSelected() {
	return this.exeEnabledButton.isSelected();
    }

    public boolean isPreSelected() {
	return this.preEnabledButton.isSelected();
    }

    public boolean isPostSelected() {
	return this.postEnabledButton.isSelected();
    }

    public boolean isIterSelected() {
	return this.iterEnabledButton.isSelected();
    }

    public void setPreSelected(boolean p) {
	this.preEnabledButton.setSelected(p);
    }

    public void setPostSelected(boolean p) {
	this.postEnabledButton.setSelected(p);
    }

    public void setIterSelected(boolean p) {
	this.iterEnabledButton.setSelected(p);
    }

    public void setExeSelected(boolean p) {
	this.exeEnabledButton.setSelected(p);
    }

    public void setEventSelected(boolean p) {
	this.eventEnabledButton.setSelected(p);
    }

    public KMADESimulationToolBar() {
	JToolBar toolbar = new JToolBar();

	// Zoom In
	zoomInAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOMIN_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.setPlusZoom();
	    }
	};
	zoomInAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_IN_ACTION_MESSAGE);
	toolbar.add(zoomInAction);

	// Zoom Out
	zoomOutAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOMOUT_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.setMinusZoom();
	    }
	};
	zoomOutAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_OUT_ACTION_MESSAGE);
	toolbar.add(zoomOutAction);

	// Zoom Default
	zoomDefaultAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOM_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.setDefaultZoom();
	    }
	};
	zoomDefaultAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_DEFAULT_ACTION_MESSAGE);
	toolbar.add(zoomDefaultAction);

	// Zoom global
	globalGraphAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOM_GLOBAL_ICON) {
	    private static final long serialVersionUID = 4000465475546408455L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.setGlobalTaskModel();
	    }
	};
	globalGraphAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_GLOBAL_ACTION_MESSAGE);
	toolbar.add(globalGraphAction);
	toolbar.addSeparator();

	// Aperçu rapide du modèle de tâches
	myOverviewWindowAction = new AbstractAction(
		KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE,
		KMADEToolToolBar.OVERVIEW_PREVIEW) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.showOverviewWindow();
	    }
	};
	myOverviewWindowAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.PREVIEW_WINDOW_ACTION_MESSAGE);
	myOverviewWindowToggleButton = new JToggleButton(myOverviewWindowAction);
	myOverviewWindowToggleButton.setRolloverEnabled(false);
	myOverviewWindowToggleButton.setText("");
	toolbar.add(myOverviewWindowToggleButton);
	toolbar.addSeparator();

	toolbar.setFloatable(false);

	// Activer ou pas la prise en compte de la précondition
	this.preEnabledButton = new JToggleButton("Pre", true);
	this.preEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});
	// Activer ou pas la prise en compte de la effetsdebord
	this.postEnabledButton = new JToggleButton("Post", true);
	this.postEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});
	// Activer ou pas la prise en compte de l'itération
	this.iterEnabledButton = new JToggleButton("Iter", true);
	this.iterEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});
	// Activer ou pas la prise en compte de l'exécutant
	this.exeEnabledButton = new JToggleButton("Exe", true);
	this.exeEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});
	// Activer ou pas la prise en compte de l'événement
	this.eventEnabledButton = new JToggleButton("Event", true);
	this.eventEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});
	// Activer ou pas la prise en compte le déclenchement d'événement
	this.eventTriggerEnabledButton = new JToggleButton("TrigEvent", true);
	this.eventTriggerEnabledButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.constraintsEnablingAction();
	    }
	});

	toolbar.add(preEnabledButton);
	toolbar.add(postEnabledButton);
	toolbar.add(iterEnabledButton);
	toolbar.add(exeEnabledButton);
	toolbar.add(eventEnabledButton);
	toolbar.add(eventTriggerEnabledButton);
	toolbar.addSeparator();

	editUserValues = new AbstractAction("Edit", null) {
	    private static final long serialVersionUID = 4000465475546408455L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.editUserValues();
	    }
	};
	editUserValues.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.EDIT_USER_VALUES_SIMULATION_MESSAGE);
	toolbar.add(editUserValues);

	toolbar.addSeparator();

	// Activer l'affichage des labels
	AbstractAction myShowOrNotTaskLabelToggleAction = new AbstractAction(
		"", KMADETaskModelToolBar.VISIBLE_LABEL_IMAGE_ICON) {
	    private static final long serialVersionUID = -9065303558600036445L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.updateSimulationGraph();
	    }
	};
	myShowOrNotTaskLabelToggleAction.putValue(
		AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.VISIBLE_LABEL_ACTION_MESSAGE);
	myShowOrNotTaskLabelAction = new JToggleButton(
		myShowOrNotTaskLabelToggleAction);
	toolbar.add(myShowOrNotTaskLabelAction);
	myShowOrNotTaskLabelAction.setSelected(false);

	// Activer la couleur des labels
	AbstractAction myShowOrNotTaskColorLabelToggleAction = new AbstractAction(
		"", KMADETaskModelToolBar.COLOR_LABEL_IMAGE_ICON) {
	    private static final long serialVersionUID = -4598463136823680701L;

	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.updateSimulationGraph();
	    }
	};
	myShowOrNotTaskColorLabelToggleAction.putValue(
		AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.COLOR_LABEL_ACTION_MESSAGE);
	myShowOrNotTaskColorLabelAction = new JToggleButton(
		myShowOrNotTaskColorLabelToggleAction);
	toolbar.add(myShowOrNotTaskColorLabelAction);

	JToolBar moreToolbar = new JToolBar();
	toolbar.setRollover(true);
	toolbar.setFloatable(false);
	moreToolbar.setRollover(true);
	moreToolbar.setFloatable(false);
	moreToolbar.add(new KMADEMoreButton(toolbar));

	this.setLayout(new BorderLayout());
	this.add(toolbar, BorderLayout.CENTER);
	this.add(moreToolbar, BorderLayout.EAST);
    }

    public boolean isLabelSelected() {
	return this.myShowOrNotTaskLabelAction.isSelected();
    }

    public void setLabelSelected(boolean p) {
	this.myShowOrNotTaskLabelAction.setSelected(p);
    }

    public boolean isLabelColorSelected() {
	return this.myShowOrNotTaskColorLabelAction.isSelected();
    }

    public void setLabelColorSelected(boolean p) {
	this.myShowOrNotTaskColorLabelAction.setSelected(p);
    }

    public void setSelectedOverviewWindowToggleButton(boolean t) {
	this.myOverviewWindowToggleButton.setSelected(t);
    }

    public void setDisabledNoSelectedTask() {
	editUserValues.setEnabled(false);
    }

    public void setEnabledOneSelectedTask() {
	editUserValues.setEnabled(true);
    }

    public void notifLocalisationModification() {

    }
}

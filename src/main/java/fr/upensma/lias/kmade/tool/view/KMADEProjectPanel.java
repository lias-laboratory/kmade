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
package fr.upensma.lias.kmade.tool.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskDescriptionPanel;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADETaskPropertiesPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.view.toolutilities.VerticalTextIcon;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.concreteobject.KMADEReadWriteConcreteObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.condition.KMADEReadWriteConditionObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.event.KMADEReadWriteEventObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.label.KMADELabelPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.user.KMADEReadWriteIndividuObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.user.KMADEReadWriteOrganisationObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.usersystem.KMADEReadWriteMachineObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.usersystem.KMADEReadWriteParcMachinesPanel;
import fr.upensma.lias.kmade.tool.viewadaptator.ConcreteObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEProjectPanel extends JPanel implements LanguageFactory {

    static final long serialVersionUID = 4285L;

    private final KMADETaskDescriptionPanel taskDescriptionPanel = new KMADETaskDescriptionPanel();

    private final KMADEReadWriteAbstractTypeObjectPanel tablesEDM = new KMADEReadWriteAbstractTypeObjectPanel();

    private final KMADEReadWriteIndividuObjectPanel tableIndividu = new KMADEReadWriteIndividuObjectPanel();

    private final KMADEReadWriteConditionObjectPanel tableCondition = new KMADEReadWriteConditionObjectPanel();

    private final KMADEReadWriteMachineObjectPanel tableMachine = new KMADEReadWriteMachineObjectPanel();

    private final KMADEReadWriteParcMachinesPanel tableParcMachines = new KMADEReadWriteParcMachinesPanel();

    private final KMADEReadWriteEventObjectPanel tableEvent = new KMADEReadWriteEventObjectPanel();

    private final KMADEReadWriteConcreteObjectPanel concreteObjectPanel = new KMADEReadWriteConcreteObjectPanel();

    private final KMADEReadWriteOrganisationObjectPanel tableOrganisation = new KMADEReadWriteOrganisationObjectPanel();

    private final KMADELabelPanel refLabelPanel = new KMADELabelPanel();

    private final JTabbedPane tabpaneProjet;

    public KMADELabelPanel getLabelPanel() {
	return refLabelPanel;
    }

    public KMADEReadWriteConcreteObjectPanel getPanelCreationObjConc() {
	return this.concreteObjectPanel;
    }

    public KMADETaskDescriptionPanel getTaskDescriptionPanel() {
	return this.taskDescriptionPanel;
    }

    public KMADETaskModelPanel getTaskModelPanel() {
	return this.taskDescriptionPanel.getTaskModelPanel();
    }

    public KMADETaskPropertiesPanel getPanelProprieteTache() {
	return taskDescriptionPanel.getProprieteTache();
    }

    public KMADEReadWriteAbstractTypeObjectPanel getTablesPanel() {
	return this.tablesEDM;
    }

    public KMADEReadWriteIndividuObjectPanel tableIndividu() {
	return this.tableIndividu;
    }

    public KMADEReadWriteConditionObjectPanel getConditionPanel() {
	return this.tableCondition;
    }

    public KMADEReadWriteOrganisationObjectPanel getOrganisationPanel() {
	return tableOrganisation;
    }

    public KMADEReadWriteEventObjectPanel tableEvent() {
	return this.tableEvent;
    }

    public KMADEReadWriteMachineObjectPanel getMachinePanel() {
	return tableMachine;
    }

    public KMADEReadWriteParcMachinesPanel getParcMachinesPanel() {
	return tableParcMachines;
    }

    public boolean isTaskEditorVisible() {
	return this.tabpaneProjet.getSelectedIndex() == 0;
    }

    public KMADEProjectPanel() {
	super();
	boolean v2 = false;
	v2=true;
	if (v2) {
	    tabpaneProjet = new JTabbedPane();
	    /* 0 espace de tache */tabpaneProjet.addTab(
		    KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME,
		    taskDescriptionPanel);
	    /* 1 condition */
	    tabpaneProjet.addTab(
		    KMADEConstant.CONDITION_TABBEDPANE_TITLE_MESSAGE,
		    tableCondition);

	    /* 2 objet abstrait */tabpaneProjet.addTab(
		    KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME,
		    tablesEDM);
	    /* 3 objet concret */tabpaneProjet.addTab(
		    KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME,
		    concreteObjectPanel);
	    /* 4 individus */tabpaneProjet
		    .addTab(KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME,
			    tableIndividu);
	    /* 5 organisation */tabpaneProjet.addTab(
		    KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME,
		    tableOrganisation);
	    /* 6 machines */tabpaneProjet.addTab(
		    KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, tableMachine);
	    /* 7 parcs machine */tabpaneProjet.addTab(
		    KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME,
		    tableParcMachines);
	    /* 8 event */tabpaneProjet.addTab(
		    KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, tableEvent);
	    /* 9 label */tabpaneProjet.addTab(
		    KMADEConstant.LABEL_TITLE_MESSAGE, refLabelPanel);

	    tabpaneProjet.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		    if (tabpaneProjet.getSelectedIndex() == 0) {
			GraphicEditorAdaptator.getMainFrame()
				.getApplicationToolBar().getEditorsToolBar()
				.setVisible(true);
			if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
			} else {
			    TaskPropertiesAdaptator.updateTaskPropertiesPanel();
			}
		    } else {
			GraphicEditorAdaptator.getMainFrame()
				.getApplicationToolBar().getEditorsToolBar()
				.setVisible(false);
			if (tabpaneProjet.getSelectedIndex() == 3) {
			    ConcreteObjectPanelAdaptator
				    .updateConcreteObjectView();
			}
			// permet la mise � jour des onglets utilisateurs et
			// equipements pour que les mises � jour effectuer dans
			// l'un
			// le soit dans l'autre
			if (tabpaneProjet.getSelectedIndex() == 4) {
			    tableIndividu.refreshActiveIndividu();
			}
			if (tabpaneProjet.getSelectedIndex() == 5) {
			    tableOrganisation.refreshActiveOrganisation();
			}
			if (tabpaneProjet.getSelectedIndex() == 6) {
			    tableMachine.refreshActiveMachine();
			}
			if (tabpaneProjet.getSelectedIndex() == 7) {
			    tableParcMachines.refreshActiveParcMachines();
			}
		    }
		}
	    });
	} else {
	    tabpaneProjet = new JTabbedPane();
	    /* 0 espace de tache */tabpaneProjet.addTab(
		    KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME,
		    taskDescriptionPanel);
	    /* 1 objet abstrait */tabpaneProjet.addTab(
		    KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME,
		    tablesEDM);
	    /* 2 objet concret */tabpaneProjet.addTab(
		    KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME,
		    concreteObjectPanel);
	    /* 3 individus */tabpaneProjet
		    .addTab(KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME,
			    tableIndividu);
	    /* 4 organisation */tabpaneProjet.addTab(
		    KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME,
		    tableOrganisation);
	    /* 5 machines */tabpaneProjet.addTab(
		    KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, tableMachine);
	    /* 6 parcs machine */tabpaneProjet.addTab(
		    KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME,
		    tableParcMachines);
	    /* 7 event */tabpaneProjet.addTab(
		    KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, tableEvent);
	    /* 8 label */tabpaneProjet.addTab(
		    KMADEConstant.LABEL_TITLE_MESSAGE, refLabelPanel);

	    tabpaneProjet.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		    if (tabpaneProjet.getSelectedIndex() == 0) {
			GraphicEditorAdaptator.getMainFrame()
				.getApplicationToolBar().getEditorsToolBar()
				.setVisible(true);
			if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
			} else {
			    TaskPropertiesAdaptator.updateTaskPropertiesPanel();
			}
		    } else {
			GraphicEditorAdaptator.getMainFrame()
				.getApplicationToolBar().getEditorsToolBar()
				.setVisible(false);
			if (tabpaneProjet.getSelectedIndex() == 2) {
			    ConcreteObjectPanelAdaptator
				    .updateConcreteObjectView();
			}
			// permet la mise � jour des onglets utilisateurs et
			// equipements pour que les mises � jour effectuer dans
			// l'un
			// le soit dans l'autre
			if (tabpaneProjet.getSelectedIndex() == 3) {
			    tableIndividu.refreshActiveIndividu();
			}
			if (tabpaneProjet.getSelectedIndex() == 4) {
			    tableOrganisation.refreshActiveOrganisation();
			}
			if (tabpaneProjet.getSelectedIndex() == 5) {
			    tableMachine.refreshActiveMachine();
			}
			if (tabpaneProjet.getSelectedIndex() == 6) {
			    tableParcMachines.refreshActiveParcMachines();
			}
		    }
		}
	    });

	}
	this.setMinimumSize(new Dimension(300, 300));
	this.setLayout(new BorderLayout());
	this.add(tabpaneProjet, BorderLayout.CENTER);
    }

    public void initMainFrame() {
	tabpaneProjet.setSelectedIndex(0);
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.getEditorsToolBar().setVisible(true);
    }

    public void notifLocalisationModification() {
	// NMDAProjectPanel
	tabpaneProjet.setIconAt(0, new VerticalTextIcon(
		KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, false,
		this));
	tabpaneProjet.setIconAt(2, new VerticalTextIcon(
		KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME, false,
		this));
	tabpaneProjet.setIconAt(3, new VerticalTextIcon(
		KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME,
		false, this));
	tabpaneProjet.setIconAt(4, new VerticalTextIcon(
		KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME, false, this));
	tabpaneProjet.setIconAt(5, new VerticalTextIcon(
		KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME, false, this));
	tabpaneProjet.setIconAt(6, new VerticalTextIcon(
		KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, false, this));
	tabpaneProjet.setIconAt(7, new VerticalTextIcon(
		KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME, false, this));
	tabpaneProjet.setIconAt(8, new VerticalTextIcon(
		KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, false, this));
	tabpaneProjet.setIconAt(9, new VerticalTextIcon(
		KMADEConstant.LABEL_TITLE_MESSAGE, false, this));
	taskDescriptionPanel.notifLocalisationModification();
	tablesEDM.notifLocalisationModification();
	tableIndividu.notifLocalisationModification();
	tableCondition.notifLocalisationModification();
	tableOrganisation.notifLocalisationModification();
	tableMachine.notifLocalisationModification();
	tableParcMachines.notifLocalisationModification();
	tableEvent.notifLocalisationModification();
	concreteObjectPanel.notifLocalisationModification();
    }

    public JTabbedPane getTabpaneProjet() {
	return tabpaneProjet;
    }
}

package kmade.kmade.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskmodel.KMADETaskDescriptionPanel;
import kmade.kmade.UI.taskmodel.KMADETaskModelPanel;
import kmade.kmade.UI.taskproperties.KMADETaskPropertiesPanel;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.UI.toolutilities.VerticalTextIcon;
import kmade.kmade.UI.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.kmade.UI.worldobject.concreteobject.KMADEReadWriteConcreteObjectPanel;
import kmade.kmade.UI.worldobject.event.KMADEReadWriteEventObjectPanel;
import kmade.kmade.UI.worldobject.label.KMADELabelPanel;
import kmade.kmade.UI.worldobject.user.KMADEReadWriteIndividuObjectPanel;
import kmade.kmade.UI.worldobject.user.KMADEReadWriteOrganisationObjectPanel;
import kmade.kmade.UI.worldobject.usersystem.KMADEReadWriteMachineObjectPanel;
import kmade.kmade.UI.worldobject.usersystem.KMADEReadWriteParcMachinesPanel;
import kmade.kmade.adaptatorUI.ConcreteObjectPanelAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.TaskPropertiesAdaptator;

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
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEProjectPanel extends JPanel implements LanguageFactory {
    static final long serialVersionUID = 4285L;

    private final KMADETaskDescriptionPanel taskDescriptionPanel = new KMADETaskDescriptionPanel();

    private final KMADEReadWriteAbstractTypeObjectPanel tablesEDM = new KMADEReadWriteAbstractTypeObjectPanel();

    private final KMADEReadWriteIndividuObjectPanel tableIndividu = new KMADEReadWriteIndividuObjectPanel();

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
	
    public KMADEReadWriteOrganisationObjectPanel getOrganisationPanel() {
		return tableOrganisation;
	}
    
    public KMADEReadWriteEventObjectPanel tableEvent() {
        return this.tableEvent;
    }
    
    public KMADEReadWriteMachineObjectPanel getMachinePanel(){
    	return tableMachine;
    }
    
    public KMADEReadWriteParcMachinesPanel getParcMachinesPanel(){
    	return tableParcMachines;
    }

    public boolean isTaskEditorVisible() {
        return this.tabpaneProjet.getSelectedIndex() == 0;
    }
    
    public KMADEProjectPanel() {
    	super();
    	boolean top = true; // permet de mettre les onglets a gauche ou en haut (le haut n'est pas fini d'implémenté
        //  /!\ les onglets à gauche ne fonctionne pas correctement !
        
        if(top){ 
        	tabpaneProjet = new JTabbedPane();
        	/* 0 espace de tache*/tabpaneProjet.addTab(KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, taskDescriptionPanel);
        	/* 1 objet abstrait */tabpaneProjet.addTab(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME,  tablesEDM);
        	/* 2 objet concret */tabpaneProjet.addTab(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME, concreteObjectPanel);
        	/* 3 individus */tabpaneProjet.addTab(KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME,tableIndividu);
        	/* 4 organisation */tabpaneProjet.addTab(KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME,  tableOrganisation);
        	/* 5 machines */tabpaneProjet.addTab(KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, tableMachine);
        	/* 6 parcs machine*/tabpaneProjet.addTab(KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME, tableParcMachines);
        	/* 7 event */tabpaneProjet.addTab(KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, tableEvent);
        	/* 8 label */tabpaneProjet.addTab(KMADEConstant.LABEL_TITLE_MESSAGE, refLabelPanel);
            
        	
        }
        else
        {
        tabpaneProjet = new JTabbedPane(JTabbedPane.LEFT);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, false, this), taskDescriptionPanel);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME, false,this), tablesEDM);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME, false,this), concreteObjectPanel);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME, false,this), tableIndividu);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME, false,this), tableOrganisation);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, false,this), tableMachine);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME, false,this), tableParcMachines);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, false,this), tableEvent);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.LABEL_TITLE_MESSAGE, false, this), refLabelPanel);
        
        
        tabpaneProjet.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        }
       
        tabpaneProjet.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (tabpaneProjet.getSelectedIndex() == 0) {
                	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar().setVisible(true);
                    if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
                    } else {
                        TaskPropertiesAdaptator.updateTaskPropertiesPanel();
                    }
                } else {
                	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar().setVisible(false);
                	if (tabpaneProjet.getSelectedIndex() == 2) {
                    ConcreteObjectPanelAdaptator.updateConcreteObjectView();
                	}
                	// permet la mise à jour des onglets utilisateurs et equipements pour que les mises à jour effectuer dans l'un le soit dans l'autre
                	if(tabpaneProjet.getSelectedIndex()==3){
                		tableIndividu.refreshActiveIndividu();
                	}
                	if(tabpaneProjet.getSelectedIndex()==4){
                		tableOrganisation.refreshActiveOrganisation();
                	}
                	if(tabpaneProjet.getSelectedIndex()==5){
                		tableMachine.refreshActiveMachine();
                	}
                	if( tabpaneProjet.getSelectedIndex()==6){
                		tableParcMachines.refreshActiveParcMachines();
                	}
                }
            }
        });
        
       
        this.setMinimumSize(new Dimension(300, 300));
        this.setLayout(new BorderLayout());
        this.add(tabpaneProjet, BorderLayout.CENTER);
    }

    public void initMainFrame() {
   		tabpaneProjet.setSelectedIndex(0);
   		GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar().setVisible(true);
    }
    
    public void notifLocalisationModification() {
        // NMDAProjectPanel
        tabpaneProjet.setIconAt(0, new VerticalTextIcon(KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, false,this)); 
        tabpaneProjet.setIconAt(1, new VerticalTextIcon(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(2, new VerticalTextIcon(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(3, new VerticalTextIcon(KMADEConstant.INDIVIDU_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(4, new VerticalTextIcon(KMADEConstant.ORGANIZATION_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(5, new VerticalTextIcon(KMADEConstant.MACHINE_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(6, new VerticalTextIcon(KMADEConstant.PARCMACHINES_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(7, new VerticalTextIcon(KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(8, new VerticalTextIcon(KMADEConstant.LABEL_TITLE_MESSAGE, false,this));
        taskDescriptionPanel.notifLocalisationModification();
        tablesEDM.notifLocalisationModification();
        tableIndividu.notifLocalisationModification();
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

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
import kmade.kmade.UI.worldobject.user.KMADEReadWriteUserObjectPanel;
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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class KMADEProjectPanel extends JPanel implements LanguageFactory {
    static final long serialVersionUID = 4285L;

    private final KMADETaskDescriptionPanel taskDescriptionPanel = new KMADETaskDescriptionPanel();

    private final KMADEReadWriteAbstractTypeObjectPanel tablesEDM = new KMADEReadWriteAbstractTypeObjectPanel();

    private final KMADEReadWriteUserObjectPanel tableUtil = new KMADEReadWriteUserObjectPanel();

    private final KMADEReadWriteEventObjectPanel tableEvent = new KMADEReadWriteEventObjectPanel();

    private final KMADEReadWriteConcreteObjectPanel concreteObjectPanel = new KMADEReadWriteConcreteObjectPanel();

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

    public KMADEReadWriteUserObjectPanel tableUtil() {
        return this.tableUtil;
    }

    public KMADEReadWriteEventObjectPanel tableEvent() {
        return this.tableEvent;
    }

    public boolean isTaskEditorVisible() {
        return this.tabpaneProjet.getSelectedIndex() == 0;
    }
    
    public KMADEProjectPanel() {
        super();
        tabpaneProjet = new JTabbedPane(JTabbedPane.LEFT);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, false, this), taskDescriptionPanel);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME, false,this), tablesEDM);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME, false,this), concreteObjectPanel);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.USER_TABBEDPANE_TITLE_NAME, false,this), tableUtil);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, false,this), tableEvent);
        tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.LABEL_TITLE_MESSAGE, false, this), refLabelPanel);
        tabpaneProjet.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tabpaneProjet.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (tabpaneProjet.getSelectedIndex() == 0) {
                    if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
                    } else {
                        TaskPropertiesAdaptator.updateTaskPropertiesPanel();
                    }
                } else if (tabpaneProjet.getSelectedIndex() == 2) {
                    ConcreteObjectPanelAdaptator.updateConcreteObjectView();
                }
            }
        });

        this.setMinimumSize(new Dimension(300, 300));
        this.setLayout(new BorderLayout());
        this.add(tabpaneProjet, BorderLayout.CENTER);
    }

    public void initMainFrame() {
   		tabpaneProjet.setSelectedIndex(0);
    }
    
    public void notifLocalisationModification() {
        // NMDAProjectPanel
        tabpaneProjet.setIconAt(0, new VerticalTextIcon(KMADEConstant.TASK_DESCRIPTION_TABBEDPANE_TITLE_NAME, false,this)); 
        tabpaneProjet.setIconAt(1, new VerticalTextIcon(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(2, new VerticalTextIcon(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(3, new VerticalTextIcon(KMADEConstant.USER_TABBEDPANE_TITLE_NAME, false,this));
        tabpaneProjet.setIconAt(4, new VerticalTextIcon(KMADEConstant.EVENT_TABBEDPANE_TITLE_NAME, false,this));
        
        taskDescriptionPanel.notifLocalisationModification();
        tablesEDM.notifLocalisationModification();
        tableUtil.notifLocalisationModification();
        tableEvent.notifLocalisationModification();
        concreteObjectPanel.notifLocalisationModification();
    }
}

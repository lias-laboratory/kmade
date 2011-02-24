package kmade.kmade.UI.taskproperties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadEventObjectTable;
import kmade.kmade.UI.toolutilities.DefaultPropertiesTableModel;
import kmade.kmade.UI.toolutilities.JPropertiesEditorDialog;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.adaptatorUI.EventAdaptator;
import kmade.kmade.adaptatorUI.PrePostIterExpressionAdaptator;

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
public class KMADEEditorEventDecl extends JPropertiesEditorDialog {
    private static final long serialVersionUID = -6348137983085333000L;

    private ArrayList<String> listOfEvent = new ArrayList<String>();
    
    private JComboBox myComboBox;

    public KMADEEditorEventDecl() {
        super();
        this.setModal(false);
        this.setTitle(KMADEConstant.EVENT_TASK_LINKED_TITLE_NAME);
       
        /* Liste déroulante pour la sélection de l'événement */
       
        String[] temp = new String[listOfEvent.size()];
        myComboBox = new JComboBox(listOfEvent.toArray(temp));
        
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.PAGE_AXIS));
        comboPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Evénement déclencheur"));
        comboPanel.add(myComboBox);
        
        /* Liste des événements disponibles*/
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Evénements disponibles"));
        KMADEReadEventObjectTable obj = new KMADEReadEventObjectTable(EventAdaptator.getEventReadPanel(),false);
        listPanel.add(obj);
        
        /* Panel principal */
        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(comboPanel,BorderLayout.PAGE_START);
        panelCenter.add(listPanel,BorderLayout.CENTER);

        this.getContentPane().add(BorderLayout.CENTER, panelCenter);
        this.setPreferredSize(new Dimension(400, 300));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        
        /* Listener sur la liste déroulante, pour prendre en compte la sélection*/
        this.myComboBox.addActionListener(new java.awt.event.ActionListener() {	
    	  	public void actionPerformed(java.awt.event.ActionEvent evt) {
    	  		KMADEMainFrame.getProjectPanel().getPanelProprieteTache().setDeclenchement((String)myComboBox.getSelectedItem());
    	  	}
        });
        
    }

    protected void stopEditorDialog() {
        EventAdaptator.enabledFrame();
        super.stopEditorDialog();
    }

    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int row) {
    	EventAdaptator.refreshReadEventTable();
    	
    	/* Récupération des événements disponibles et ajout de la chaîne vide (pas d'événement) */
        listOfEvent = EventAdaptator.getEventsName();
        listOfEvent.add(0, "");
        String[] temp = new String[listOfEvent.size()];
        
        /* Insertion de la liste récupérée dans la liste déroulante */
        myComboBox.setModel(new javax.swing.DefaultComboBoxModel(listOfEvent.toArray(temp)));

        PrePostIterExpressionAdaptator.disabledFrame();
        super.showPropertiesEditor(refModel, row);
    }

    public void notifLocalisationModification() {
        super.notifLocalisationModification();
        
        // NMDAEditorEventDialog
        this.setTitle(KMADEConstant.EVENT_TASK_LINKED_TITLE_NAME);
    }
}

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
package fr.upensma.lias.kmade.tool.view.taskproperties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadEventObjectTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultPropertiesTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesEditorDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.EventAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.PrePostIterExpressionAdaptator;

/**
 * @author Mickael BARON
 */
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
	comboPanel.setBorder(javax.swing.BorderFactory
		.createTitledBorder(KMADEConstant.EVENT_TRIGGER));
	comboPanel.add(myComboBox);

	/* Liste des événements disponibles */
	JPanel listPanel = new JPanel(new BorderLayout());
	listPanel.setBorder(javax.swing.BorderFactory
		.createTitledBorder(KMADEConstant.EVENT_ENABLE));
	KMADEReadEventObjectTable obj = new KMADEReadEventObjectTable(
		EventAdaptator.getEventReadPanel(), false);
	listPanel.add(obj);

	/* Panel principal */
	JPanel panelCenter = new JPanel(new BorderLayout());
	panelCenter.add(comboPanel, BorderLayout.PAGE_START);
	panelCenter.add(listPanel, BorderLayout.CENTER);

	this.getContentPane().add(BorderLayout.CENTER, panelCenter);
	this.setPreferredSize(new Dimension(400, 300));
	this.pack();
	KMADEToolUtilities.setCenteredInScreen(this);

	/* Listener sur la liste déroulante, pour prendre en compte la sélection */
	this.myComboBox.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		KMADEMainFrame
			.getProjectPanel()
			.getPanelProprieteTache()
			.setDeclenchement((String) myComboBox.getSelectedItem());
	    }
	});

    }

    protected void stopEditorDialog() {
	EventAdaptator.enabledFrame();
	super.stopEditorDialog();
    }

    public void showPropertiesEditor(DefaultPropertiesTableModel refModel,
	    int row) {
	EventAdaptator.refreshReadEventTable();

	/*
	 * Récupération des événements disponibles et ajout de la chaîne vide
	 * (pas d'événement)
	 */
	listOfEvent = EventAdaptator.getEventsName();
	listOfEvent.add(0, "");
	String[] temp = new String[listOfEvent.size()];

	/* Insertion de la liste récupérée dans la liste déroulante */
	myComboBox.setModel(new javax.swing.DefaultComboBoxModel(listOfEvent
		.toArray(temp)));
	String evt = "null";
	if (row == KMADETaskPropertiesPanel.DECLENCHEMENT_TITLE_ELEMENT) {
	    if (GraphicEditorAdaptator.getSelectedExpressTask()
		    .getRaisingEvent() != null)
		evt = GraphicEditorAdaptator.getSelectedExpressTask()
			.getRaisingEvent().getName();
	}
	if (!evt.equals("null")) {
	    myComboBox.setSelectedItem(evt);
	}
	PrePostIterExpressionAdaptator.disabledFrame();
	super.showPropertiesEditor(refModel, row);
    }

    public void notifLocalisationModification() {
	super.notifLocalisationModification();

	// NMDAEditorEventDialog
	this.setTitle(KMADEConstant.EVENT_TASK_LINKED_TITLE_NAME);
    }
}

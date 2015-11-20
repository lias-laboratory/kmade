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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class JPropertiesEditorDialog extends JDialog implements LanguageFactory {

    private static final long serialVersionUID = -8318323148935479783L;

    protected JPropertiesTable refPropertiesTable;

    protected DefaultPropertiesTableModel model;

    protected int row;

    protected JButton myOkButton;

    protected JPanel panelSouth;

    public JPropertiesEditorDialog() {
    	this.setModalityType(ModalityType.APPLICATION_MODAL);
	//this.setModal(true);
	myOkButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
	panelSouth = new JPanel();
	panelSouth.add(myOkButton);

	myOkButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JPropertiesEditorDialog.this.stopEditorDialog();
	    }
	});

	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		JPropertiesEditorDialog.this.stopEditorDialog();
	    }
	});

	this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
    }

    public void showPropertiesEditor(DefaultPropertiesTableModel refModel,
	    int prow) {
	model = refModel;
	row = prow;
	this.setVisible(true);
    }

    protected void stopEditorDialog() {
	JPropertiesEditorDialog.this.setVisible(false);
    }

    protected void cancelEditorDialog() {
    }

    public void notifLocalisationModification() {
	this.myOkButton.setText(KMADEConstant.GO_BACK_MESSAGE);
    }
}

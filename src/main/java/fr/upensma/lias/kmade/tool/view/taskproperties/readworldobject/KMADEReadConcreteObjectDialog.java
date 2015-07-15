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
package fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;

/**
 * @author Mickael BARON
 */
public class KMADEReadConcreteObjectDialog extends JDialog {

    private static final long serialVersionUID = -4495133696562074076L;

    private final KMADEReadConcreteObjectTable objAbstrait = new KMADEReadConcreteObjectTable();

    private JButton myOkButton;

    public KMADEReadConcreteObjectDialog() {
	this.getContentPane().add(BorderLayout.CENTER, objAbstrait);
	myOkButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
	JPanel panelSouth = new JPanel();
	panelSouth.add(myOkButton);
	this.setAlwaysOnTop(true);

	myOkButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		KMADEReadConcreteObjectDialog.this.setVisible(false);
	    }
	});
	this.getContentPane().add(BorderLayout.SOUTH, panelSouth);

	InDevelopmentGlassPanel.plugDialogUnderDevelopmentGlassPanel(this);
	this.setVisible(false);
	this.pack();
	KMADEToolUtilities.setCenteredInScreen(this);
    }

    public KMADEReadConcreteObjectTable getAbstractObjectTable() {
	return objAbstrait;
    }
}

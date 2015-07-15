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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.EntityListAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEEntityDialog extends JDialog implements LanguageFactory {

    private static final long serialVersionUID = -2258571876517155414L;

    private JTextArea myTextArea;

    private JButton verifier;

    private JButton fermer;

    public KMADEEntityDialog(Frame owner) {
	super(owner, KMADEConstant.ENTITY_LIST_DIALOG_TITLE);
	myTextArea = new JTextArea();
	JScrollPane myScrollPane = new JScrollPane(myTextArea);

	JPanel myPanelControlButtons = new JPanel();
	verifier = new JButton(KMADEConstant.FORCE_ENTITY_LIST_ACTION_MESSAGE);
	verifier.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		EntityListAdaptator.startEntityList();
	    }
	});
	fermer = new JButton(KMADEConstant.GO_BACK_MESSAGE);
	fermer.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		EntityListAdaptator.closeEntityListDialog();
	    }
	});

	myPanelControlButtons.add(verifier);
	myPanelControlButtons.add(fermer);

	this.getContentPane().add(BorderLayout.CENTER, myScrollPane);
	this.getContentPane().add(BorderLayout.SOUTH, myPanelControlButtons);

	this.pack();
	Dimension dim = new Dimension(700, 300);
	if (Toolkit.getDefaultToolkit().getScreenSize().height < dim.height) {
	    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setLocation(0, 0);
	} else {
	    this.setSize(new Dimension(700, 300));
	    KMADEToolUtilities.setCenteredInScreen(this);
	}
	this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
	this.setVisible(false);
	this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		EntityListAdaptator.closeEntityListDialog();
	    }
	});
    }

    public void writeInTextArea(String value) {
	this.myTextArea.append(value);
	this.myTextArea.append("\n");
    }

    public void clearTextArea() {
	this.myTextArea.setText("");
    }

    public void notifLocalisationModification() {
	this.setTitle(KMADEConstant.ENTITY_LIST_DIALOG_TITLE);
	verifier.setText(KMADEConstant.FORCE_ENTITY_LIST_ACTION_MESSAGE);
	fermer.setText(KMADEConstant.GO_BACK_MESSAGE);
    }
}

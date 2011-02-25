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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEInfoHTMLDialog extends JFrame {
    private static final long serialVersionUID = 5454495876612597939L;
    // Instance de la classe JEditorPane.
    private JEditorPane editor_pane;

    private JButton ok_button;

    /**
     * Constructeur de la classe InfoDebugAbout.
     * 
     * @param param_instance_dialog
     *            Instance du contr�leur de dialogue de la partie Builder.
     **/
    public KMADEInfoHTMLDialog() {
	Container content_pane = getContentPane();
	content_pane.setLayout(new BorderLayout());

	editor_pane = new JEditorPane();
	editor_pane.setEditable(false);
	editor_pane.setContentType("text/html");
	JScrollPane area_scroll_pane = new JScrollPane(editor_pane);
	area_scroll_pane
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	area_scroll_pane
		.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	area_scroll_pane.setPreferredSize(new Dimension(400, 200));

	JPanel panel_button = new JPanel();
	panel_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
	ok_button = new JButton(KMADEConstant.VALID_MESSAGE);
	ok_button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		GraphicEditorAdaptator.getMainFrame().requestFocus();
		KMADEInfoHTMLDialog.this.setVisible(false);
	    }
	});
	panel_button.add(ok_button);

	content_pane.add(area_scroll_pane, BorderLayout.CENTER);
	content_pane.add(panel_button, BorderLayout.SOUTH);
	this.setPreferredSize(new Dimension(800, 600));
	this.pack();
	KMADEToolUtilities.setCenteredInScreen(this);
	this.setVisible(false);
    }

    /**
     * Méthode qui affiche cette boite de dialogue et modifie son contenu.
     * 
     * @param title
     *            Titre de la boite de dialogue.
     * @param content
     *            Adresse de la page web contenant les informations. C'est une
     *            adresse qui pointe sur un fichier.
     **/
    public void showInfoAbout(String title, String content) {
	this.setTitle(title);
	try {
	    editor_pane.setPage(this.getClass().getResource(content));
	    this.pack();
	    this.setVisible(true);
	} catch (IOException e) {

	}
    }

    public void notifLocalisationModification() {
	ok_button.setText(KMADEConstant.VALID_MESSAGE);
    }
}
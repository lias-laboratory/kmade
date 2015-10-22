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
package fr.upensma.lias.kmade.tool.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADTXT;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Thomas LACHAUME
 */
public class KMADESimpleTreePanel extends JFrame {

    private static final long serialVersionUID = 6331603702903812405L;
    
    private JTextArea textArea = new JTextArea();
    
    private JPanel myContentPane;
    
    private static final ImageIcon SAVE_HISTORY = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.SAVE_HISTORY_IMAGE));

    public KMADESimpleTreePanel() {
	super("KMC TITLE");
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		closeSimpleTreePanel();
	    }
	});
	Dimension screen_dimension = Toolkit.getDefaultToolkit()
		.getScreenSize();
	// TODO dont understant why it is /4 and /2
	this.setLocation(
		(int) ((screen_dimension.getWidth() - this.getWidth()) / 4),
		(int) ((screen_dimension.getHeight() - this.getHeight()) / 4));
	// JPanel for constraint
	this.myContentPane = new JPanel();
	this.setContentPane(myContentPane);

	Dimension dim = new Dimension(1000, 700);
	if(Toolkit.getDefaultToolkit().getScreenSize().height< dim.height){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
	}else{
		this.setSize(new Dimension(1000, 700));
		KMADEToolUtilities.setCenteredInScreen(this);
		}
	this.validate();
	this.repaint();
	myContentPane.setLayout(new BoxLayout(myContentPane,
		BoxLayout.LINE_AXIS));

	textArea.append("KMC pas d'arbre");
	JButton selectButton = new JButton("KMC Séléctionner");
	selectButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		getTextArea().selectAll();
		getTextArea().requestFocus();

	    }
	});
	JButton copyButton = new JButton("KMC Copier");
	copyButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		getTextArea().selectAll();
		getTextArea().requestFocus();
		getTextArea().copy();

	    }
	});

	JButton saveButton = new JButton("KMC Enregistrer");
	saveButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String myCurrentFile = KMADEFileChooser
			.saveKMADModelSimpleFile();
		if (myCurrentFile != null) {
		    ExpressKMADTXT.saveKMADSimpleModel(myCurrentFile);
		}
	    }
	});

	JPanel buttonPanel = new JPanel();
	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
	buttonPanel.add(selectButton);
	buttonPanel.add(copyButton);
	buttonPanel.add(saveButton);
	myContentPane.add(buttonPanel);
	JScrollPane scrollpane = new JScrollPane(textArea);
	myContentPane.add(scrollpane);
    }

    public void closeSimpleTreePanel() {
	this.setVisible(false);
    }

    public JTextArea getTextArea() {
	return textArea;
    }
}

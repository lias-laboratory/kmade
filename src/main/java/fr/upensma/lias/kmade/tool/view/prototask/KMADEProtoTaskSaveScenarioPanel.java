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
package fr.upensma.lias.kmade.tool.view.prototask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADTXT;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;

/**
 * @author Thomas LACHAUME
 */
public class KMADEProtoTaskSaveScenarioPanel extends JInternalFrame {

	private static final long serialVersionUID = 2331971899758022471L;

	private JTextArea textArea = new JTextArea();

	private JPanel myContentPane = new JPanel();

	public KMADEProtoTaskSaveScenarioPanel() {
		super();
		this.setClosable(true);
		this.setMaximizable(true);
		this.add(myContentPane);
		myContentPane.validate();
		myContentPane.repaint();

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
				String myCurrentFile = KMADEFileChooser.saveKMADModelSimpleFile();
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
		JScrollPane scrollpane = new JScrollPane(textArea);
	}

	public void closeSimpleTreePanel() {
		this.setVisible(false);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}

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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEHistoryMessagePanel extends JPanel implements MessageIO {

    private static final long serialVersionUID = -6863003670072426978L;

    private static final ImageIcon CLEAR_HISTORY = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.CLEAR_HISTORY_IMAGE));

    private static final ImageIcon SAVE_HISTORY = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.SAVE_HISTORY_IMAGE));

    private JTextArea refTextArea;

    private JButton myClearButton;

    private JButton mySaveButton;

    public KMADEHistoryMessagePanel(String borderMessage) {
	this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	this.setLayout(new BorderLayout(5, 5));
	refTextArea = new JTextArea();
	JScrollPane myScrollPane = new JScrollPane(refTextArea);
	if (!borderMessage.equals("")) {
	    this.setBorder(BorderFactory.createTitledBorder(borderMessage));
	}
	new JTextAreaOutputStream(refTextArea);
	JPanel panelTool = new JPanel();
	panelTool.setLayout(new BoxLayout(panelTool, BoxLayout.Y_AXIS));
	myClearButton = new JButton(CLEAR_HISTORY);
	mySaveButton = new JButton(SAVE_HISTORY);
	mySaveButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		KMADEHistoryMessagePanel.this.saveHistoryMessage();
	    }
	});
	mySaveButton.setToolTipText(KMADEConstant.SAVE_HISTORIC_ACTION_MESSAGE);
	panelTool.add(myClearButton);
	panelTool.add(Box.createRigidArea(new Dimension(5, 5)));
	panelTool.add(mySaveButton);
	this.add(BorderLayout.WEST, panelTool);
	this.add(BorderLayout.CENTER, myScrollPane);

	myClearButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		refTextArea.setText("");
	    }
	});
	myClearButton
		.setToolTipText(KMADEConstant.CLEAR_HISTORIC_ACTION_MESSAGE);
    }

    public void setBorderName(String message) {
	this.setBorder(BorderFactory.createTitledBorder(message));
    }

    @Override
    public void setOutputMessage() {

	KMADEHistoryMessageManager.setOutputMessage(this);
    }

    @Override
    public void setErrputMessage() {
	KMADEHistoryMessageManager.setErrputMessage(this);
    }

    private void saveHistoryMessage() {
	File nomFichier = KMADEFileChooser.saveTxtFile(GraphicEditorAdaptator
		.getMainFrame().getFindReplaceDialog());
	if (nomFichier == null) {
	    return;
	}

	if (nomFichier != null) {
	    try {
		FileWriter fw = new FileWriter(nomFichier);
		fw.write(refTextArea.getText());
		fw.close();
		KMADEHistoryMessageManager
			.printlnMessage(KMADEConstant.WRITE_TXT_FILE_OK
				+ nomFichier.getName());
	    } catch (IOException e) {
		KMADEHistoryMessageManager
			.printlnMessage(KMADEConstant.WRITE_TXT_FILE_ERROR);
	    }
	}
    }

    @Override
    public void printlnMessage(String message) {
	message += "\n";
	refTextArea.append(message);

    }

    @Override
    public void printlnError(String message) {
	message += "\n";
	refTextArea.append(message);

    }

    @Override
    public void printlnError(Throwable e) {
	refTextArea.append(e.getMessage());

    }

    @Override
    public void printMessage(String message) {
	refTextArea.append(message);
    }
}

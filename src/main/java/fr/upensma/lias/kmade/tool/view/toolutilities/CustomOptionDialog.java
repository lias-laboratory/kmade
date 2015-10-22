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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class CustomOptionDialog extends JDialog {
    private static final long serialVersionUID = -7337055439452113257L;

    private static int state;

    private static CustomOptionDialog myDialog;

    public static final int YES_OPTION = 0;

    public static final int NO_OPTION = 1;

    public static final int CANCEL_OPTION = 2;

    public static final int ALL_OPTION = 3;

    public CustomOptionDialog(JFrame owner, String titre) {
	super(owner);

    }

    public CustomOptionDialog(JDialog owner, String titre) {
	super(owner);
    }

    public static int showAllConfirmDeleteDialog(Window owner, String titre,
	    String message) {
	if (owner instanceof JDialog) {
	    myDialog = new CustomOptionDialog((JDialog) owner, titre);
	} else if (owner instanceof JFrame) {
	    myDialog = new CustomOptionDialog((JFrame) owner, titre);
	}

	myDialog.setTitle(titre);
	myDialog.setModal(true);
	myDialog.setResizable(false);
	myDialog.setVisible(false);

	myDialog.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		state = CANCEL_OPTION;
	    }
	});

	myDialog.getRootPane().setWindowDecorationStyle(
		JRootPane.QUESTION_DIALOG);
	JButton myYesButton = new JButton(KMADEConstant.YES_MESSAGE);
	JButton myAllButton = new JButton(KMADEConstant.ALL_MESSAGE);
	JButton myNoButton = new JButton(KMADEConstant.NO_MESSAGE);
	JButton myCancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);
	myYesButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		state = YES_OPTION;
		CustomOptionDialog.myDialog.setVisible(false);
	    }
	});
	myAllButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		state = ALL_OPTION;
		CustomOptionDialog.myDialog.setVisible(false);
	    }
	});
	myNoButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		state = NO_OPTION;
		CustomOptionDialog.myDialog.setVisible(false);
	    }
	});
	myCancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		state = CANCEL_OPTION;
		CustomOptionDialog.myDialog.setVisible(false);
	    }
	});
	JPanel myPanelBut = new JPanel(new GridLayout(1, 4, 5, 5));
	myPanelBut.add(myYesButton);
	myPanelBut.add(myAllButton);
	myPanelBut.add(myNoButton);
	myPanelBut.add(myCancelButton);
	JPanel myPanelSouth = new JPanel();
	myPanelSouth.add(myPanelBut);

	JPanel panelCenter = new JPanel(new BorderLayout(15, 10));
	panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	JLabel myLabelImage = new JLabel(new ImageIcon(
		CustomOptionDialog.class
			.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
	myLabelImage.setVerticalAlignment(JLabel.TOP);
	JTextArea myLabelText = new JTextArea(message);
	Font myFont = myLabelText.getFont();
	myFont = new Font(myFont.getFontName(), Font.BOLD, myFont.getSize());
	myLabelText.setFont(myFont);
	myLabelText.setEditable(false);
	myLabelText.setDisabledTextColor(myLabelText.getCaretColor());
	myLabelText.setEnabled(false);
	myLabelText.setOpaque(false);
	panelCenter.add(BorderLayout.WEST, myLabelImage);
	panelCenter.add(BorderLayout.CENTER, myLabelText);

	myDialog.getContentPane().setLayout(new BorderLayout());
	myDialog.getContentPane().add(BorderLayout.CENTER, panelCenter);
	myDialog.getContentPane().add(BorderLayout.SOUTH, myPanelSouth);
	myDialog.pack();
	KMADEToolUtilities.setCenteredInScreen(myDialog);
	myDialog.setVisible(true);
	return state;
    }
}

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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEGroupTypeComboBox;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEUserExpressionField;
import fr.upensma.lias.kmade.tool.view.toolutilities.JTextAreaMessageIO;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.SimulationAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEInputUserExpressionAndConcreteObjectDialog extends JDialog {

    private static final long serialVersionUID = -4517731161541499165L;

    private JButton buttonRetour;

    private JButton buttonAnnuler;

    private static final ImageIcon LOCK_USER = new ImageIcon(
	    GraphicEditorAdaptator.class.getResource(KMADEConstant.LOCK_IMAGE));

    private static final ImageIcon UNLOCK_USER = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.UNLOCK_IMAGE));

    private static final ImageIcon CLEAR_USER_VALUES = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.CLEAR_HISTORY_IMAGE));

    private ArrayList<KMADEUserExpressionField> myExpressionPreconditionFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADEGroupTypeComboBox> myExpressionPreconditionComboList = new ArrayList<KMADEGroupTypeComboBox>();

    private ArrayList<KMADEUserExpressionField> myExpressionEffetsDeBordFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADEGroupTypeComboBox> myExpressionEffetsDeBordComboList = new ArrayList<KMADEGroupTypeComboBox>();

    private ArrayList<KMADEUserExpressionField> myExpressionIterationFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADEGroupTypeComboBox> myExpressionIterationComboList = new ArrayList<KMADEGroupTypeComboBox>();

    private JPanel panelPrecondition;

    private JPanel panelEffetsDeBord;

    private JPanel panelIteration;

    private JTextAreaMessageIO myTextArea;

    private JPanelUserTool panelPreconditionTool;

    private JPanelUserTool panelEffetsDeBordTool;

    private JPanelUserTool panelIterationTool;

    public ArrayList<KMADEUserExpressionField> getExpressionPreconditionFieldList() {
	return myExpressionPreconditionFieldList;
    }

    public ArrayList<KMADEGroupTypeComboBox> getExpressionPreconditionComboList() {
	return myExpressionPreconditionComboList;
    }

    public ArrayList<KMADEUserExpressionField> getExpressionEffetsDeBordFieldList() {
	return myExpressionEffetsDeBordFieldList;
    }

    public ArrayList<KMADEGroupTypeComboBox> getExpressionEffetsDeBordComboList() {
	return myExpressionEffetsDeBordComboList;
    }

    public ArrayList<KMADEUserExpressionField> getExpressionIterationFieldList() {
	return myExpressionIterationFieldList;
    }

    public ArrayList<KMADEGroupTypeComboBox> getExpressionIterationComboList() {
	return myExpressionIterationComboList;
    }

    public KMADEInputUserExpressionAndConcreteObjectDialog(Frame owner) {
	super(owner, KMADEConstant.USER_VALUES_TITLE, true);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	panelPrecondition = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JScrollPane scrollPanelPrecondition = new JScrollPane(panelPrecondition);
	scrollPanelPrecondition.setPreferredSize(new Dimension(100, 90));
	scrollPanelPrecondition.setBorder(null);
	JPanel panelPreconditionEdit = new JPanel(new BorderLayout(0, 0));
	panelPreconditionEdit
		.setBorder(BorderFactory
			.createTitledBorder(KMADEConstant.PRECONDITION_USER_EDITION_MESSAGE));
	panelPreconditionTool = new JPanelUserTool();
	panelPreconditionEdit.add(BorderLayout.WEST, panelPreconditionTool);
	panelPreconditionEdit.add(BorderLayout.CENTER, scrollPanelPrecondition);
	panelPreconditionTool.getLockUnlockButton().addActionListener(
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			SimulationAdaptator
				.switchLockOrUnlockPreconditionUserValuesAndConcreteObjects();
		    }
		});

	panelEffetsDeBord = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JScrollPane scrollPanelEffetsDeBord = new JScrollPane(panelEffetsDeBord);
	scrollPanelEffetsDeBord.setPreferredSize(new Dimension(100, 90));
	scrollPanelEffetsDeBord.setBorder(null);
	JPanel panelEffetsDeBordEdit = new JPanel(new BorderLayout(0, 0));
	panelEffetsDeBordEdit
		.setBorder(BorderFactory
			.createTitledBorder(KMADEConstant.EFFETSDEBORD_USER_EDITION_MESSAGE));
	panelEffetsDeBordTool = new JPanelUserTool();
	panelEffetsDeBordEdit.add(BorderLayout.WEST, panelEffetsDeBordTool);
	panelEffetsDeBordEdit.add(BorderLayout.CENTER, scrollPanelEffetsDeBord);
	panelEffetsDeBordTool.getLockUnlockButton().addActionListener(
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			SimulationAdaptator
				.switchLockOrUnlockEffetsDeBordUserValuesAndConcreteObjects();
		    }
		});

	panelIteration = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JScrollPane scrollPanelIteration = new JScrollPane(panelIteration);
	scrollPanelIteration.setPreferredSize(new Dimension(100, 90));
	scrollPanelIteration.setBorder(null);
	JPanel panelIterationEdit = new JPanel(new BorderLayout(0, 0));
	panelIterationEdit
		.setBorder(BorderFactory
			.createTitledBorder(KMADEConstant.ITERATION_USER_EDITION_MESSAGE));
	panelIterationTool = new JPanelUserTool();
	panelIterationEdit.add(BorderLayout.WEST, panelIterationTool);
	panelIterationEdit.add(BorderLayout.CENTER, scrollPanelIteration);
	panelIterationTool.getLockUnlockButton().addActionListener(
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			SimulationAdaptator
				.switchLockOrUnlockIterationUserValuesAndConcreteObjects();
		    }
		});

	JPanel panelHaut = new JPanel();
	panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
	panelHaut.add(panelPreconditionEdit);
	panelHaut.add(Box.createRigidArea(new Dimension(0, 10)));
	panelHaut.add(panelEffetsDeBordEdit);
	panelHaut.add(Box.createRigidArea(new Dimension(0, 10)));
	panelHaut.add(panelIterationEdit);

	myTextArea = new JTextAreaMessageIO();
	JScrollPane myScroll = new JScrollPane(myTextArea);
	myScroll.setBorder(BorderFactory
		.createTitledBorder(KMADEConstant.MESSAGES_MESSAGE));

	JSplitPane mySplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
		panelHaut, myScroll);
	mySplitPane.setOneTouchExpandable(true);
	mySplitPane.setDividerLocation(300);

	this.getContentPane().add(BorderLayout.CENTER, mySplitPane);
	buttonRetour = new JButton(KMADEConstant.TRIGGER_VALID_TASK_MESSAGE);
	buttonRetour.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.closeInputConcreteDialog();
	    }
	});

	buttonAnnuler = new JButton(
		KMADEConstant.NO_TRIGGER_CANCEL_TASK_MESSAGE);
	buttonAnnuler.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.cancelInputConcreteDialog();
	    }
	});

	JPanel panelSouth = new JPanel();
	panelSouth.add(buttonRetour);
	panelSouth.add(buttonAnnuler);
	this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
	this.pack();
	Dimension dim = new Dimension(500, 500);
	if (Toolkit.getDefaultToolkit().getScreenSize().height < dim.height) {
	    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	    this.setLocation(0, 0);
	} else {
	    this.setSize(new Dimension(500, 500));
	    KMADEToolUtilities.setCenteredInScreen(this);
	}

	this.setVisible(false);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		SimulationAdaptator.cancelInputConcreteDialog();
	    }
	});
    }

    public void setPreconditionLockImage() {
	panelPreconditionTool.getLockUnlockButton().setIcon(LOCK_USER);
    }

    public void setPreconditionUnLockImage() {
	panelPreconditionTool.getLockUnlockButton().setIcon(UNLOCK_USER);
    }

    public void setInputUserConcretePreconditionPanel(ArrayList<JComponent> p) {
	panelPrecondition.removeAll();
	for (JComponent current : p) {
	    panelPrecondition.add(current);
	}
    }

    public void setInputUserConcreteEffetsDeBordPanel(ArrayList<JComponent> p) {
	panelEffetsDeBord.removeAll();
	for (JComponent current : p) {
	    panelEffetsDeBord.add(current);
	}
    }

    public void setInputUserConcreteIterationPanel(ArrayList<JComponent> p) {
	panelIteration.removeAll();
	for (JComponent current : p) {
	    panelIteration.add(current);
	}
    }

    public void showInputUserConcreteDialogToEdit() {
	myTextArea.setOutputMessage();
	this.getContentPane().validate();
	this.getContentPane().repaint();
	buttonRetour.setText(KMADEConstant.VALID_MESSAGE);
	buttonAnnuler.setText(KMADEConstant.CANCEL_MESSAGE);
	this.setVisible(true);
    }

    public void showInputUserConcreteDialogToSimulate() {
	myTextArea.setOutputMessage();
	this.getContentPane().validate();
	this.getContentPane().repaint();
	buttonRetour.setText(KMADEConstant.TRIGGER_VALID_TASK_MESSAGE);
	buttonAnnuler.setText(KMADEConstant.NO_TRIGGER_CANCEL_TASK_MESSAGE);
	this.setVisible(true);
    }

    public void closeInputUserConcreteDialog() {
	this.setVisible(false);
    }

    public void setLockOrUnlockPreconditionUserValuesAndConcreteObjects(
	    boolean lock) {
	if (this.getExpressionPreconditionFieldList().size() == 0
		&& this.getExpressionPreconditionComboList().size() == 0) {
	    this.panelPreconditionTool.getLockUnlockButton().setEnabled(false);
	    this.panelPreconditionTool.getClearButton().setEnabled(false);
	} else {
	    this.panelPreconditionTool.getLockUnlockButton().setEnabled(true);
	    this.panelPreconditionTool.getClearButton().setEnabled(true);
	}
	if (lock) {
	    this.setEnabledPreconditionFieldAndComboComponents(false);
	    this.setPreconditionLockImage();
	} else {
	    this.setEnabledPreconditionFieldAndComboComponents(true);
	    this.setPreconditionUnLockImage();
	}
    }

    public void setEnabledPreconditionFieldAndComboComponents(boolean p) {
	for (KMADEUserExpressionField currentField : this
		.getExpressionPreconditionFieldList()) {
	    currentField.setEnabled(p);
	}

	for (KMADEGroupTypeComboBox currentCombo : this
		.getExpressionPreconditionComboList()) {
	    currentCombo.setEnabled(p);
	}
    }

    public void setEnabledEffetsDeBordFieldAndComboComponents(boolean b) {
	for (KMADEUserExpressionField currentField : this
		.getExpressionEffetsDeBordFieldList()) {
	    currentField.setEnabled(b);
	}

	for (KMADEGroupTypeComboBox currentCombo : this
		.getExpressionEffetsDeBordComboList()) {
	    currentCombo.setEnabled(b);
	}
    }

    public void setEffetsDeBordUnLockImage() {
	panelEffetsDeBordTool.getLockUnlockButton().setIcon(UNLOCK_USER);
    }

    public void setEffetsDeBordLockImage() {
	panelEffetsDeBordTool.getLockUnlockButton().setIcon(LOCK_USER);
    }

    public void setEnabledIterationFieldAndComboComponents(boolean b) {
	for (KMADEUserExpressionField currentField : this
		.getExpressionIterationFieldList()) {
	    currentField.setEnabled(b);
	}

	for (KMADEGroupTypeComboBox currentCombo : this
		.getExpressionIterationComboList()) {
	    currentCombo.setEnabled(b);
	}
    }

    public void setIterationUnLockImage() {
	panelIterationTool.getLockUnlockButton().setIcon(UNLOCK_USER);
    }

    public void setIterationLockImage() {
	panelIterationTool.getLockUnlockButton().setIcon(LOCK_USER);
    }

    static class JPanelUserTool extends JPanel {

	private static final long serialVersionUID = -7695673613336253447L;

	private JButton myLockUnlockButton;

	private JButton myClearButton;

	public JPanelUserTool() {
	    this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, this
		    .getBackground().darker()));
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    myLockUnlockButton = new JButton(LOCK_USER);
	    myClearButton = new JButton(CLEAR_USER_VALUES);
	    myClearButton.setEnabled(false);
	    this.add(myLockUnlockButton);
	    this.add(Box.createRigidArea(new Dimension(5, 5)));
	    this.add(myClearButton);
	}

	/**
	 * @return Returns the myClearButton.
	 */
	public JButton getClearButton() {
	    return myClearButton;
	}

	/**
	 * @return Returns the myLockUnlockButton.
	 */
	public JButton getLockUnlockButton() {
	    return myLockUnlockButton;
	}
    }
}

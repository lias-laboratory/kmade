package kmade.kmade.UI.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
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
import javax.swing.JTextArea;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.constrainteditors.KMADESetTypeComboBox;
import kmade.kmade.UI.taskproperties.constrainteditors.KMADEUserExpressionField;
import kmade.kmade.UI.toolutilities.JTextAreaOutputStream;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.SimulationAdaptator;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class KMADEInputUserExpressionAndConcreteObjectDialog extends JDialog {

    private static final long serialVersionUID = -4517731161541499165L;

    private JButton buttonRetour;
    
    private JButton buttonAnnuler;
    
    private static final ImageIcon LOCK_USER = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.LOCK_IMAGE));
    
    private static final ImageIcon UNLOCK_USER = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.UNLOCK_IMAGE));    

    private static final ImageIcon CLEAR_USER_VALUES = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CLEAR_HISTORY_IMAGE));
    
    private ArrayList<KMADEUserExpressionField> myExpressionPreconditionFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADESetTypeComboBox> myExpressionPreconditionComboList = new ArrayList<KMADESetTypeComboBox>();

    private ArrayList<KMADEUserExpressionField> myExpressionPostconditionFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADESetTypeComboBox> myExpressionPostconditionComboList = new ArrayList<KMADESetTypeComboBox>();

    private ArrayList<KMADEUserExpressionField> myExpressionIterationFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADESetTypeComboBox> myExpressionIterationComboList = new ArrayList<KMADESetTypeComboBox>();
    
    private JPanel panelPrecondition;
    
    private JPanel panelPostcondition;
    
    private JPanel panelIteration;
    
    private JTextArea myTextArea;
    
    private JPanelUserTool panelPreconditionTool;
    
    private JPanelUserTool panelPostconditionTool;
    
    private JPanelUserTool panelIterationTool;
    
    public ArrayList<KMADEUserExpressionField> getExpressionPreconditionFieldList() {
        return myExpressionPreconditionFieldList;
    }
    
    public ArrayList<KMADESetTypeComboBox> getExpressionPreconditionComboList() {
        return myExpressionPreconditionComboList;
    }
    
    public ArrayList<KMADEUserExpressionField> getExpressionPostconditionFieldList() {
        return myExpressionPostconditionFieldList;
    }
    
    public ArrayList<KMADESetTypeComboBox> getExpressionPostconditionComboList() {
        return myExpressionPostconditionComboList;
    }
    
    public ArrayList<KMADEUserExpressionField> getExpressionIterationFieldList() {
        return myExpressionIterationFieldList;
    }
    
    public ArrayList<KMADESetTypeComboBox> getExpressionIterationComboList() {
        return myExpressionIterationComboList;
    }
    
    public KMADEInputUserExpressionAndConcreteObjectDialog(Frame owner) {
        super(owner,KMADEConstant.USER_VALUES_TITLE,true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panelPrecondition = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPanelPrecondition = new JScrollPane(panelPrecondition);
        scrollPanelPrecondition.setPreferredSize(new Dimension(100,90));
        scrollPanelPrecondition.setBorder(null);
        JPanel panelPreconditionEdit = new JPanel(new BorderLayout(0,0));
        panelPreconditionEdit.setBorder(BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_USER_EDITION_MESSAGE));
        panelPreconditionTool = new JPanelUserTool();
        panelPreconditionEdit.add(BorderLayout.WEST,panelPreconditionTool);
        panelPreconditionEdit.add(BorderLayout.CENTER,scrollPanelPrecondition);
        panelPreconditionTool.getLockUnlockButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationAdaptator.switchLockOrUnlockPreconditionUserValuesAndConcreteObjects();
            }
        });        
        
        panelPostcondition = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPanelPostcondition = new JScrollPane(panelPostcondition);
        scrollPanelPostcondition.setPreferredSize(new Dimension(100,90));
        scrollPanelPostcondition.setBorder(null);
        JPanel panelPostconditionEdit = new JPanel(new BorderLayout(0,0));
        panelPostconditionEdit.setBorder(BorderFactory.createTitledBorder(KMADEConstant.POSTCONDITION_USER_EDITION_MESSAGE));
        panelPostconditionTool = new JPanelUserTool();
        panelPostconditionEdit.add(BorderLayout.WEST,panelPostconditionTool);
        panelPostconditionEdit.add(BorderLayout.CENTER,scrollPanelPostcondition);
        panelPostconditionTool.getLockUnlockButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationAdaptator.switchLockOrUnlockPostconditionUserValuesAndConcreteObjects();
            }
        });        

        
        panelIteration = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPanelIteration = new JScrollPane(panelIteration);
        scrollPanelIteration.setPreferredSize(new Dimension(100,90));
        scrollPanelIteration.setBorder(null);
        JPanel panelIterationEdit = new JPanel(new BorderLayout(0,0));
        panelIterationEdit.setBorder(BorderFactory.createTitledBorder(KMADEConstant.ITERATION_USER_EDITION_MESSAGE));
        panelIterationTool = new JPanelUserTool();
        panelIterationEdit.add(BorderLayout.WEST,panelIterationTool);
        panelIterationEdit.add(BorderLayout.CENTER,scrollPanelIteration);
        panelIterationTool.getLockUnlockButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationAdaptator.switchLockOrUnlockIterationUserValuesAndConcreteObjects();
            }
        });        
        
        
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut,BoxLayout.Y_AXIS));
        panelHaut.add(panelPreconditionEdit);
        panelHaut.add(Box.createRigidArea(new Dimension(0,10)));
        panelHaut.add(panelPostconditionEdit);
        panelHaut.add(Box.createRigidArea(new Dimension(0,10)));
        panelHaut.add(panelIterationEdit);
        
        myTextArea = new JTextArea();
        JScrollPane myScroll = new JScrollPane(myTextArea);
        myScroll.setBorder(BorderFactory.createTitledBorder(KMADEConstant.MESSAGES_MESSAGE));
        
        JSplitPane mySplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelHaut,myScroll);
        mySplitPane.setOneTouchExpandable(true);
        mySplitPane.setDividerLocation(300);
        
        this.getContentPane().add(BorderLayout.CENTER,mySplitPane);
        buttonRetour = new JButton(KMADEConstant.TRIGGER_VALID_TASK_MESSAGE);
        buttonRetour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SimulationAdaptator.closeInputConcreteDialog();
        	}
        });
        
        buttonAnnuler = new JButton(KMADEConstant.NO_TRIGGER_CANCEL_TASK_MESSAGE);
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
        this.setSize(new Dimension(500, 500));
        KMADEToolUtilities.setCenteredInScreen(this);
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

    public void setInputUserConcretePostconditionPanel(ArrayList<JComponent> p) {
        panelPostcondition.removeAll();
        for (JComponent current : p) {
            panelPostcondition.add(current);
        }
    }
    
    public void setInputUserConcreteIterationPanel(ArrayList<JComponent> p) {
        panelIteration.removeAll();
        for (JComponent current : p) {
            panelIteration.add(current);
        }
    }
    
    public void showInputUserConcreteDialogToEdit() {
        JTextAreaOutputStream out_stream = new JTextAreaOutputStream(myTextArea);
        System.setOut(new PrintStream(out_stream));
        this.getContentPane().validate();
        this.getContentPane().repaint();
        buttonRetour.setText(KMADEConstant.VALID_MESSAGE);
        buttonAnnuler.setText(KMADEConstant.CANCEL_MESSAGE);
        this.setVisible(true);     
    }
    
    public void showInputUserConcreteDialogToSimulate() {
        JTextAreaOutputStream out_stream = new JTextAreaOutputStream(myTextArea);
        System.setOut(new PrintStream(out_stream));
        this.getContentPane().validate();
        this.getContentPane().repaint();
        buttonRetour.setText(KMADEConstant.TRIGGER_VALID_TASK_MESSAGE);
        buttonAnnuler.setText(KMADEConstant.NO_TRIGGER_CANCEL_TASK_MESSAGE);
        this.setVisible(true);       
    }
    
    public void closeInputUserConcreteDialog() {
    		this.setVisible(false);
    }
    
    public void setLockOrUnlockPreconditionUserValuesAndConcreteObjects(boolean lock) {
    		if (this.getExpressionPreconditionFieldList().size() == 0 && this.getExpressionPreconditionComboList().size() == 0) {
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
        for (KMADEUserExpressionField currentField : this.getExpressionPreconditionFieldList()) {
            currentField.setEnabled(p);
        }
        
        for (KMADESetTypeComboBox currentCombo : this.getExpressionPreconditionComboList()) {
            currentCombo.setEnabled(p);
        }
    }
    
	public void setEnabledPostconditionFieldAndComboComponents(boolean b) {
        for (KMADEUserExpressionField currentField : this.getExpressionPostconditionFieldList()) {
            currentField.setEnabled(b);
        }
        
        for (KMADESetTypeComboBox currentCombo : this.getExpressionPostconditionComboList()) {
            currentCombo.setEnabled(b);
        }		
	}

	public void setPostconditionUnLockImage() {
		panelPostconditionTool.getLockUnlockButton().setIcon(UNLOCK_USER);
	}
	
    public void setPostconditionLockImage() {
    		panelPostconditionTool.getLockUnlockButton().setIcon(LOCK_USER);
    }
    
	public void setEnabledIterationFieldAndComboComponents(boolean b) {
        for (KMADEUserExpressionField currentField : this.getExpressionIterationFieldList()) {
            currentField.setEnabled(b);
        }
        
        for (KMADESetTypeComboBox currentCombo : this.getExpressionIterationComboList()) {
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
            this.setBorder(BorderFactory.createMatteBorder(0,0,0,1,this.getBackground().darker()));
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

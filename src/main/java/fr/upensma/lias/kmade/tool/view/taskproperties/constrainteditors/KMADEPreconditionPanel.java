package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessagePanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.PreconditionAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.ReadConcreteObjectAdaptator;


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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEPreconditionPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 2659692780990739523L;

    public JTextArea textArea;

    public JTextArea descriptionTextuelArea = new JTextArea();
    
    public JPanel panelEvaluationUser;
    
	private JScrollPane scroll;

	private JButton checkButton;
    
    private JButton validButton;

    private JButton clearButton;
    
    private JButton dbListButton;
    
    private JButton initButton;
    
    private JLabel labelResultat = new JLabel();
    
    private JPanel panelHaut;
    
    private JPanel panelDescription;
    
    private KMADEPreconditionExpressionPanel preconditionExpressionPanel;
    
    private KMADEHistoryMessagePanel myHMP;
    
    private ArrayList<KMADEUserExpressionField> myExpressionFieldList = new ArrayList<KMADEUserExpressionField>();

    private ArrayList<KMADEGroupTypeComboBox> myExpressionComboList = new ArrayList<KMADEGroupTypeComboBox>();
    
    public void setDescriptionArea(String p) {
    		this.descriptionTextuelArea.setText(p);
    }
    
    public String getDescriptionArea() {
    		return this.descriptionTextuelArea.getText();
    }
    
	public KMADEPreconditionPanel() {
		JPanel panelExpression = new JPanel(new BorderLayout());
		JPanel panelEdition = new JPanel(new BorderLayout(0,10));
        JPanel panelEvaluation = new JPanel(new BorderLayout(0,10));
        
		textArea = new JTextArea();
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                PreconditionAdaptator.modifiedExpression();
            }            
        });
        textArea.setLineWrap(true);
		scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(100,60));
        panelEdition.add(BorderLayout.CENTER, scroll);
        
        clearButton = new JButton(KMADEConstant.CLEAR_EXPRESSION_MESSAGE);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreconditionAdaptator.clearPrecondition();
                textArea.setText("true");
            }
        });
        checkButton = new JButton(KMADEConstant.CHECK_VALIDATE_MESSAGE);
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreconditionAdaptator.checkPrecondition(textArea.getText());
            }
        });
        dbListButton = new JButton(KMADEConstant.CONCRETE_OBJECTS_LIST_MESSAGE);
        dbListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            		ReadConcreteObjectAdaptator.showReadConcreteObject();
            }
        });

        JPanel panelEditionControl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEditionControl.add(checkButton);
        panelEditionControl.add(clearButton);
        panelEditionControl.add(dbListButton);
        
        panelEdition.add(BorderLayout.SOUTH, panelEditionControl);
        
        validButton = new JButton(KMADEConstant.EVALUATE_FORCE1_MESSAGE);
        validButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreconditionAdaptator.evaluatePrecondition(textArea.getText());
            }
        });
        initButton = new JButton(KMADEConstant.RESET_ACTION_MESSAGE);
        initButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (KMADEUserExpressionField current:myExpressionFieldList) {
                    current.getUserExpression().setStateToUnknown();
                    current.setText("");
                    current.setPreferredSize(new Dimension(20,current.getPreferredSize().height));
                }
                for (KMADEGroupTypeComboBox currentCombo:myExpressionComboList) {
               		currentCombo.getConcreteObjectType().setUserConcreteObject(null);
               		currentCombo.setSelectedIndex(0);
                }
                PreconditionAdaptator.initPrecondition();
            }
        });
        
        panelEvaluationUser = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane myPanelEvaluationScroll = new JScrollPane(panelEvaluationUser);
        myPanelEvaluationScroll.setPreferredSize(new Dimension(100,60));
        panelEvaluation.add(BorderLayout.CENTER,myPanelEvaluationScroll);
        
        JPanel panelEvaluationControl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEvaluationControl.add(validButton);
        panelEvaluationControl.add(initButton);
        panelEvaluation.add(BorderLayout.SOUTH,panelEvaluationControl);
        
        panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_EDITION_MESSAGE));
        panelHaut.setLayout(new BoxLayout(panelHaut,BoxLayout.Y_AXIS));
        panelHaut.add(panelEdition);
        panelHaut.add(panelEvaluation);
        panelExpression.add(BorderLayout.CENTER,panelHaut);
		
        preconditionExpressionPanel = new KMADEPreconditionExpressionPanel();
        panelExpression.add(BorderLayout.SOUTH,preconditionExpressionPanel);             
        
        panelDescription = new JPanel(new BorderLayout());
        panelDescription.setBorder(BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_TEXTUEL_EDITION_MESSAGE));
        JScrollPane myDescriptionScrollPane = new JScrollPane(descriptionTextuelArea);
        myDescriptionScrollPane.setPreferredSize(new Dimension(200,50));
        panelDescription.add(myDescriptionScrollPane);
        panelExpression.add(BorderLayout.NORTH,panelDescription);
        
        myHMP = new KMADEHistoryMessagePanel(KMADEConstant.MESSAGES_MESSAGE);
        
        JSplitPane mySplitPane = new JSplitPane();
        mySplitPane.setOneTouchExpandable(true);
        mySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mySplitPane.setTopComponent(panelExpression);
        mySplitPane.setBottomComponent(myHMP);
        mySplitPane.setDividerLocation(510);
        mySplitPane.setResizeWeight(1);

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, mySplitPane);
	}
    
    public void appendToken(String t) {
        this.textArea.replaceRange(t,this.textArea.getSelectionStart(),this.textArea.getSelectionEnd());
        PreconditionAdaptator.modifiedExpression();
    }

    public void setOutputMessage() {
        myHMP.setOutputMessage();      
    }
    
    public void disableEvaluateControl(String message) {
        validButton.setEnabled(false);
        initButton.setEnabled(false);
        panelEvaluationUser.removeAll();
        myExpressionFieldList.clear();
        panelEvaluationUser.add(new JLabel(message));
        panelEvaluationUser.validate();
        panelEvaluationUser.repaint();
    }
    
    public void giveResult(String value) {
        labelResultat.setText(value);
    }
    
    public void enabledEvaluateControl(ArrayList<?> myList) {
        validButton.setEnabled(true);
        panelEvaluationUser.removeAll();       
        panelEvaluationUser.add(labelResultat);
        
        boolean isUserExpression = false;
        for (Object tt:myList) {
            if (tt instanceof UserExpression) {   
                KMADEUserExpressionField current = new KMADEUserExpressionField((UserExpression)tt);
                panelEvaluationUser.add(current); 
                myExpressionFieldList.add(current);
                isUserExpression = true;
            } else if (tt instanceof ConcreteObjectType) {
            		// Car un ConcreteObjectType est obligatoirement un NodeExpression.
            		panelEvaluationUser.add(new JLabel(((NodeExpression)tt).getName()));
        			panelEvaluationUser.add(new JLabel("["));
        			isUserExpression = true;
            		if (((ConcreteObjectType)tt).isGroupSetType()) {
            			KMADEGroupTypeComboBox currentCombo = new KMADESetTypeComboBox((ConcreteObjectType)tt,((ConcreteObjectType)tt).getConcreteObjects());         				
            			myExpressionComboList.add(currentCombo);
            			panelEvaluationUser.add(currentCombo);
            		} else if(((ConcreteObjectType)tt).isGroupArrayType()){
            			KMADEGroupTypeComboBox currentCombo = new KMADEArrayTypeComboBox((ConcreteObjectType)tt,((ConcreteObjectType)tt).getConcreteObjects());         				
            			myExpressionComboList.add(currentCombo);
            			panelEvaluationUser.add(currentCombo);
            		}else {
                        if (((ConcreteObjectType)tt).getConcreteObject() == null) {
                            panelEvaluationUser.add(new JLabel(KMADEConstant.NO_CONCRETE_OBJECT_GROUPE_NAME));
                        } else {
                            panelEvaluationUser.add(new JLabel(((ConcreteObjectType)tt).getConcreteObject().getName()));
                        }
            		}
        			panelEvaluationUser.add(new JLabel("]"));
            } else {            	
                panelEvaluationUser.add(new JLabel((String)tt));
            }
        }
        
        if (isUserExpression) {
            initButton.setEnabled(true);
        } else {
            initButton.setEnabled(false);
        }
        
        panelEvaluationUser.validate();
        panelEvaluationUser.repaint();
    }

    public void notifLocalisationModification() {
        preconditionExpressionPanel.notifLocalisationModification();
        
        clearButton.setText(KMADEConstant.CLEAR_EXPRESSION_MESSAGE);
        checkButton.setText(KMADEConstant.CHECK_VALIDATE_MESSAGE);
        dbListButton.setText(KMADEConstant.CONCRETE_OBJECTS_LIST_MESSAGE);
        validButton.setText(KMADEConstant.EVALUATE_FORCE1_MESSAGE);
        initButton.setText(KMADEConstant.RESET_ACTION_MESSAGE);
        panelHaut.setBorder(BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_EDITION_MESSAGE));
        panelDescription.setBorder(BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_TEXTUEL_EDITION_MESSAGE));
        myHMP.setBorderName(KMADEConstant.MESSAGES_MESSAGE);
    }
}

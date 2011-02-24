package kmade.kmade.view.taskproperties.constrainteditors;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.EffetsDeBordAdaptator;
import kmade.kmade.adaptatorUI.ReadConcreteObjectAdaptator;
import kmade.kmade.view.toolutilities.KMADEHistoryMessagePanel;
import kmade.kmade.view.toolutilities.LanguageFactory;
import kmade.nmda.schema.expression.ConcreteObjectType;
import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.UserExpression;

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
public class KMADEEffetsDeBordPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 7717828432846384404L;

    public JTextArea textArea;

    private JTextArea descriptionTextuelArea = new JTextArea();
    
    private JPanel panelEvaluationUser;

    private JButton initButton;
    
    private JButton validButton;
    
    private JButton checkButton;
    
    private JButton clearButton;
    
    private JButton dbListButton;
    
    private JButton removeAllHistoryButton;
    
    private JScrollPane scroll;

    private JList myList;
    
    private DefaultListModel myModel;
    
    private JTextField myConcreteObjectField;
    
    private KMADEHistoryMessagePanel myHMP;
    
    private JPanel panelHaut;
    
    private JScrollPane myHistoryScrollPane;
    
    private JButton load;
    
    private JButton remove;
    
    private JPanel panelConcreteObjectVariable;
    
    private JPanel panelDescription;
    
    private KMADEEffetsDeBordExpressionPanel refEffetsDeBordExpressionPanel;
    
    private ArrayList<KMADEUserExpressionField> myExpressionFieldList = new ArrayList<KMADEUserExpressionField>();
    
    private ArrayList<KMADEGroupTypeComboBox> myExpressionComboList = new ArrayList<KMADEGroupTypeComboBox>();
    
    public void setDescriptionArea(String p) {
		this.descriptionTextuelArea.setText(p);
    }

    public String getDescriptionArea() {
		return this.descriptionTextuelArea.getText();
    }
    
    public KMADEEffetsDeBordPanel() {
        JPanel panelExpression = new JPanel(new BorderLayout());
        JPanel panelEdition = new JPanel(new BorderLayout(0, 10));
        JPanel panelEvaluation = new JPanel(new BorderLayout(0, 10));

        textArea = new JTextArea();
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EffetsDeBordAdaptator.modifiedExpression();
            }
        });
        textArea.setLineWrap(true);
        scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(100, 60));
        panelEdition.add(BorderLayout.CENTER, scroll);

        clearButton = new JButton(KMADEConstant.CLEAR_EXPRESSION_MESSAGE);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EffetsDeBordAdaptator.clearEffetsDeBord();
                textArea.setText("void");
            }
        });
        checkButton = new JButton(KMADEConstant.CHECK_VALIDATE_MESSAGE);
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EffetsDeBordAdaptator.checkEffetsDeBord(textArea.getText());
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
                EffetsDeBordAdaptator.evaluateEffetsDeBord(textArea.getText());
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
                EffetsDeBordAdaptator.initEffetsDeBord();
            }
        });

        panelEvaluationUser = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane myPanelEvaluationScroll = new JScrollPane(panelEvaluationUser);
        myPanelEvaluationScroll.setPreferredSize(new Dimension(100, 60));
        panelEvaluation.add(BorderLayout.CENTER, myPanelEvaluationScroll);

        JPanel panelEvaluationControl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEvaluationControl.add(validButton);
        panelEvaluationControl.add(initButton);
        panelEvaluation.add(BorderLayout.SOUTH, panelEvaluationControl);

        panelHaut = new JPanel();
        panelHaut.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_EDITION_MESSAGE));
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.add(panelEdition);
        panelHaut.add(panelEvaluation);
        panelExpression.add(BorderLayout.CENTER, panelHaut);

        refEffetsDeBordExpressionPanel = new KMADEEffetsDeBordExpressionPanel();
        panelExpression.add(BorderLayout.SOUTH, refEffetsDeBordExpressionPanel);

        panelDescription = new JPanel(new BorderLayout());
        panelDescription.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE));
        JScrollPane myDescriptionScrollPane = new JScrollPane(descriptionTextuelArea);
        myDescriptionScrollPane.setPreferredSize(new Dimension(200,50));
        panelDescription.add(myDescriptionScrollPane);
        panelExpression.add(BorderLayout.NORTH,panelDescription);
        
        myHMP = new KMADEHistoryMessagePanel(KMADEConstant.MESSAGES_MESSAGE);
        
        JPanel panelModel = new JPanel(new BorderLayout());
        panelModel.setPreferredSize(new Dimension(150,150));
        JPanel panelHistorique = new JPanel(new BorderLayout());
        myModel = new DefaultListModel();
        myList = new JList(myModel);
        myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (myList.getSelectedIndex() != -1) {
					EffetsDeBordAdaptator.historySelected();
				} else {
					EffetsDeBordAdaptator.noHistorySelected();				
				}
			}        	
        });
        myHistoryScrollPane = new JScrollPane(myList);        
        myHistoryScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_HISTORY_TITLE_MESSAGE));
        
        JPanel panelHistoricTopControl = new JPanel(new GridLayout(1,2,5,5));
        load = new JButton(KMADEConstant.EFFETSDEBORD_LOAD_STATE_MESSAGE);
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EffetsDeBordAdaptator.loadConcreteHistory(myList.getSelectedIndex());
            }
        });
        load.setEnabled(true);
        remove = new JButton(KMADEConstant.REMOVE_MESSAGE);
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EffetsDeBordAdaptator.removeConcreteHistory(myList.getSelectedIndex());
            }
        });
        remove.setEnabled(true);
        
        removeAllHistoryButton = new JButton(KMADEConstant.REMOVE_MESSAGE);
        removeAllHistoryButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		EffetsDeBordAdaptator.clearAllHistory();
        	}
        });
        removeAllHistoryButton.setEnabled(true);
        
        panelHistoricTopControl.add(load);
        panelHistoricTopControl.add(remove);
        JPanel panelHistoricControl = new JPanel(new BorderLayout(5,5));
        panelHistoricControl.add(BorderLayout.NORTH,panelHistoricTopControl);
        panelHistoricControl.add(BorderLayout.CENTER,removeAllHistoryButton);
        panelHistoricControl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelHistorique.add(BorderLayout.CENTER, myHistoryScrollPane);
        panelHistorique.add(BorderLayout.SOUTH, panelHistoricControl);
        panelModel.add(BorderLayout.CENTER, panelHistorique);
        panelConcreteObjectVariable = new JPanel(new BorderLayout());
        panelConcreteObjectVariable.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE));
        myConcreteObjectField = new JTextField();
        myConcreteObjectField.setEditable(false);
        myConcreteObjectField.setBorder(null);
        panelConcreteObjectVariable.add(myConcreteObjectField);
        panelModel.add(BorderLayout.NORTH, panelConcreteObjectVariable);
        
        JPanel panelExpressionAndModel = new JPanel(new BorderLayout());
        panelExpressionAndModel.add(panelModel, BorderLayout.EAST);

        JSplitPane mySplitPane = new JSplitPane();
        mySplitPane.setOneTouchExpandable(true);
        mySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mySplitPane.setTopComponent(panelExpression);
        mySplitPane.setBottomComponent(myHMP);
        mySplitPane.setDividerLocation(490);
        mySplitPane.setResizeWeight(1);

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, mySplitPane);
        this.add(BorderLayout.WEST, panelModel);
    }
    
    public void setConcreteObjectName(String p) {
        myConcreteObjectField.setText(p);
    }
    
    public void clearConcreteObjectName() {
        myConcreteObjectField.setText("");
    }
    
    public void addNewHistory(String value) {
        myModel.add(0,value);
    }
    
    public void removeHistory(int p) {      
        myModel.remove(p);
        if (myModel.getSize() != 0) {
        	int selec = (p <= 0 ? 0 : p -1); 
        	myList.getSelectionModel().setSelectionInterval(selec, selec);
        }
    }    
    
    public void removeAllHistory() {
        myModel.removeAllElements();
    }
    
    public void appendToken(String t) {
        this.textArea.replaceRange(t,this.textArea.getSelectionStart(),this.textArea.getSelectionEnd());
    }

    public void setOutputMessage() {
        myHMP.setOutputMessage();       
    }
    

    public void disableLoadRemoveHistoryControl() {
    	this.load.setEnabled(false);
    	this.remove.setEnabled(false);
    }
    
    public void disableHistoryControl() {
    	this.load.setEnabled(false);
    	this.remove.setEnabled(false);
    	this.removeAllHistoryButton.setEnabled(false);
    }
    
    public void enableRemoveAllHistoryControl() {
    	this.removeAllHistoryButton.setEnabled(true);
    }
    
    public void enableLoadRemoveHistoryControl() {
    	this.load.setEnabled(true);
    	this.remove.setEnabled(true);
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
        
    public void enabledEvaluateControl(ArrayList<?> myList) {
        validButton.setEnabled(true);
        panelEvaluationUser.removeAll();       
               
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
        refEffetsDeBordExpressionPanel.notifLocalisationModification();
        
        clearButton.setText(KMADEConstant.CLEAR_EXPRESSION_MESSAGE);
        checkButton.setText(KMADEConstant.CHECK_VALIDATE_MESSAGE);
        dbListButton.setText(KMADEConstant.CONCRETE_OBJECTS_LIST_MESSAGE);
        validButton.setText(KMADEConstant.EVALUATE_FORCE1_MESSAGE);
        initButton.setText(KMADEConstant.RESET_ACTION_MESSAGE);
        panelHaut.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_EDITION_MESSAGE));
        panelDescription.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_TEXTUEL_EDITION_MESSAGE));
        myHMP.setBorderName(KMADEConstant.MESSAGES_MESSAGE);
        myHistoryScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_HISTORY_TITLE_MESSAGE));
        load.setText(KMADEConstant.EFFETSDEBORD_LOAD_STATE_MESSAGE);
        remove.setText(KMADEConstant.REMOVE_MESSAGE);
        panelConcreteObjectVariable.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE));
    }
}

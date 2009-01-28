package kmade.kmade.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.JTextAreaOutputStream;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorFC.ExpressIteration;
import kmade.kmade.adaptatorFC.ExpressPostcondition;
import kmade.kmade.adaptatorFC.ExpressPrecondition;
import kmade.kmade.adaptatorFC.parserKMAD.ExpressKMADXML;
import kmade.kmade.adaptatorUI.AbstractObjectAdaptator;
import kmade.kmade.adaptatorUI.ConcreteObjectPanelAdaptator;
import kmade.kmade.adaptatorUI.EnumAdaptator;
import kmade.kmade.adaptatorUI.EventAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.IntervalAdaptator;
import kmade.kmade.adaptatorUI.KMADeAdaptator;
import kmade.kmade.adaptatorUI.LabelAdaptator;
import kmade.kmade.adaptatorUI.UserAdaptator;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;

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
public class KMADEProgressBarPanel extends JDialog implements LanguageFactory {
    private static final long serialVersionUID = -1757807085591845144L;

    private JProgressBar myProgressBar;

    private JTextArea myTextArea;

    private JTextAreaOutputStream myOutputStream;

    private JButton cancelButton;

    private JButton retourButton;

    private JPanel panelSouth;
    
    private JScrollPane myScrollPane;

    private Timer myTimer;

    private TitledBorder myTitledBorder;
        
    public KMADEProgressBarPanel() {
        super(GraphicEditorAdaptator.getMainFrame(),true);
        myProgressBar = new JProgressBar(0, 200);
        myProgressBar.setValue(0);
        myProgressBar.setStringPainted(true);
        myTitledBorder = BorderFactory.createTitledBorder(KMADEConstant.STATE_LOAD_TITLE_NAME);
        myProgressBar.setBorder(myTitledBorder);

        myTextArea = new JTextArea();
        myScrollPane = new JScrollPane(myTextArea);
        myScrollPane.setPreferredSize(new Dimension(600, 300));
        myTextArea.setEditable(false);
        myOutputStream = new JTextAreaOutputStream(myTextArea);
        
        panelSouth = new JPanel();
        cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);
        retourButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myTimer != null) {
                    if (myTimer.isRunning()) {
                        return;
                    }
                }
                
                GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                GraphicEditorAdaptator.enabledMainFrameAfterLoadAndSaveProcess();
                KMADEProgressBarPanel.this.setVisible(false);
            }
        });

        panelSouth.add(cancelButton);
        panelSouth.add(retourButton);

        JPanel panelCentral = new JPanel();
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panelCentral.setLayout(new BorderLayout(10,10));
        panelCentral.add(BorderLayout.NORTH, myProgressBar);
        panelCentral.add(BorderLayout.CENTER, myScrollPane);
        panelCentral.add(BorderLayout.SOUTH, panelSouth);
        this.getContentPane().add(BorderLayout.CENTER, panelCentral);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (myTimer != null) {
                    if (myTimer.isRunning()) {
                        return;
                    }
                }
                GraphicEditorAdaptator.enabledMainFrameAfterLoadAndSaveProcess();
                GraphicEditorAdaptator.requestFocus();
                KMADEProgressBarPanel.this.setVisible(false);
            }
       
            public void windowActivated(WindowEvent e) {
                if (myTimer != null) {
                    if (!myTimer.isRunning()) {
                        myTimer.start();
                    }
                }
            }
        });
    }

    public void initWriteKMADModelFromXMLFile() {
        this.setTitle(KMADEConstant.SAVE_MONITOR_TITLE_NAME);
   		System.setOut(new PrintStream(myOutputStream));
   		myTextArea.setText("");
    		
        panelSouth.removeAll();
        cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExpressKMADXML.setCanceled(true);
            }
        });
        
        panelSouth.add(cancelButton);
        panelSouth.add(retourButton);
            
        retourButton.setEnabled(false);
        cancelButton.setEnabled(true);
            
        myProgressBar.setMaximum(ExpressKMADXML.getEntitySize());
        
		ExpressKMADXML.setBegining(true);
		ExpressKMADXML.setCanceled(false);
		ExpressKMADXML.setDone(false);
		ExpressKMADXML.setError(false);
		
        myScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.SAVE_CONSOLE_TITLE_NAME));
        
        GraphicEditorAdaptator.disabledMainFrameBeforeLoadAndSaveProcess();
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);   	
    }
    
    public void writeKMADModelFromXMLFile() {
        this.setTitle(KMADEConstant.SAVE_MONITOR_TITLE_NAME);
   		System.setOut(new PrintStream(myOutputStream));
   		myTextArea.setText("");
    		
        panelSouth.removeAll();
        cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExpressKMADXML.setCanceled(true);
            }
        });
        
        panelSouth.add(cancelButton);
        panelSouth.add(retourButton);
            
            retourButton.setEnabled(false);
            cancelButton.setEnabled(true);
            
            myProgressBar.setMaximum(ExpressKMADXML.getEntitySize());
            
    		myTimer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {                
				if (ExpressKMADXML.isBegining()) {
                    myProgressBar.setString(KMADEConstant.EXPRESS_OBJECTS_TITLE_MESSAGE);
                    myProgressBar.setValue(0);
					ExpressKMADXML.setBegining(false);
					ExpressKMADXML.saveKMADModelProcess();
				}

                myProgressBar.setValue(ExpressKMADXML.getCurrentEntity());
                myTextArea.setCaretPosition(myTextArea.getDocument().getLength());

				if (ExpressKMADXML.isDone()) {
					cancelButton.setEnabled(false);
					retourButton.setEnabled(true);
                    myProgressBar.setString(KMADEConstant.SAVE_PROJECT_FINISHED_MESSAGE);
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.enableCurrentFileName(ExpressKMADXML.getCurrentFileName());
                    
                    InterfaceExpressJava.setBddSetOff();
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.WRITE_EXPRESS_OK_FILE + ExpressKMADXML.getCurrentFileName());					
				}

				if (ExpressKMADXML.isError()) {
					cancelButton.setEnabled(false);
					retourButton.setEnabled(true);
                    System.out.println(KMADEConstant.IMPLICIT_STOP_SAVE_FILE_MESSAGE);
                    myTimer.stop();             
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.WRITE_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
				}

				if (ExpressKMADXML.isCanceled()) {
					cancelButton.setEnabled(false);
					retourButton.setEnabled(true);
                    System.out.println(KMADEConstant.EXPLICIT_STOP_SAVE_FILE_MESSAGE);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.WRITE_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
				}
			}
		});
		ExpressKMADXML.setBegining(true);
		ExpressKMADXML.setCanceled(false);
		ExpressKMADXML.setDone(false);
		ExpressKMADXML.setError(false);
		
        myScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.SAVE_CONSOLE_TITLE_NAME));
        
        GraphicEditorAdaptator.disabledMainFrameBeforeLoadAndSaveProcess();
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setVisible(true);
    }
    
    public void readKMADModelFromXMLFile() {
        this.setTitle(KMADEConstant.LOAD_MONITOR_TITLE_NAME);
        System.setOut(new PrintStream(myOutputStream));
        myTextArea.setText("");

        panelSouth.removeAll();
        cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExpressKMADXML.setCanceled(true);
                GraphicEditorAdaptator.setCanceled(true);
                ExpressPrecondition.setCanceled(true);
                ExpressPostcondition.setCanceled(true);
            }
        });

        panelSouth.add(cancelButton);
        panelSouth.add(retourButton);

        retourButton.setEnabled(false);
        cancelButton.setEnabled(true);

        myProgressBar.setMaximum(6);
        myTimer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (ExpressKMADXML.isBegining()) {
                    myProgressBar.setString(KMADEConstant.EXPRESS_OBJECTS_TITLE_MESSAGE);
                    ExpressKMADXML.setBegining(false);
                    ExpressKMADXML.loadKMADModelProcess();
                }

                if (!ExpressKMADXML.isDone()) {
                    myProgressBar.setValue(1);
                } else if (!GraphicEditorAdaptator.isDone()) {
                    myProgressBar.setValue(2);
                } else if (!ExpressPrecondition.isDone()) {
                    myProgressBar.setValue(3);
                } else if (!ExpressPostcondition.isDone()) {
                    myProgressBar.setValue(4);
                } else if (!ExpressIteration.isDone()) {
                    myProgressBar.setValue(5);
                }

                myTextArea.setCaretPosition(myTextArea.getDocument().getLength());

                if (ExpressKMADXML.isDone()
                        && GraphicEditorAdaptator.isBegining()
                        && ExpressPrecondition.isBegining()
                        && ExpressPostcondition.isBegining()
                        && ExpressIteration.isBegining()) {
                    myProgressBar.setString(KMADEConstant.GRAPHICAL_OBJECTS_MESSAGE);
                    GraphicEditorAdaptator.setBegining(false);
                    GraphicEditorAdaptator.makeAllGraphElements();
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isBegining()
                        && ExpressPostcondition.isBegining()
                        && ExpressIteration.isBegining()) {
                    myProgressBar.setString(KMADEConstant.PRECONDITION_PROGRESSBAR_MESSAGE);
                    ExpressPrecondition.setBegining(false);
                    ExpressPrecondition.makeAndCheckPreconditionOpenSPFFile();
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isDone()
                        && ExpressPostcondition.isBegining()
                        && ExpressIteration.isBegining()) {
                    myProgressBar.setString(KMADEConstant.POSTCONDITION_PROGRESSBAR_MESSAGE);
                    ExpressPostcondition.setBegining(false);
                    ExpressPostcondition.makeAndCheckPostconditionOpenSPFFile();
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isDone()
                        && ExpressPostcondition.isDone()
                        && ExpressIteration.isBegining()) {
                    myProgressBar.setString(KMADEConstant.ITERATION_PROGRESSBAR_MESSAGE);
                    ExpressIteration.setBegining(false);
                    ExpressIteration.makeAndCheckIterationOpenSPFFile();
                }

                if (ExpressIteration.isDone()) {
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    myTimer.stop();
                    myTimer = null;
                    
                    myProgressBar.setValue(6);
                    myProgressBar.setString(KMADEConstant.LOAD_PROJECT_FINISHED_MESSAGE);

                    GraphicEditorAdaptator.clearSelection();
                    // Afficher les nouvelles valeurs dans les Tables (Graphique)
                    AbstractObjectAdaptator.updateAbstractObjectView();
                    IntervalAdaptator.updateIntervalView();
                    EnumAdaptator.updateEnumView();
                    UserAdaptator.updateUserView();
                    EventAdaptator.updateEventView();
                    LabelAdaptator.updateLabelView();
                    // A voir si c'est utile?
                    ConcreteObjectPanelAdaptator.updateConcreteObjectView();
                    GraphicEditorAdaptator.setGlobalTaskModel();
                    GraphicEditorAdaptator.getMainFrame().showProjectPanel();
                    InterfaceExpressJava.setBddSetOff();
                    
                    KMADeAdaptator.enableCurrentFileName(ExpressKMADXML.getCurrentFileName());
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_OK_FILE + ExpressKMADXML.getCurrentFileName());                  
                }

                if (ExpressKMADXML.isError()) {
                    System.out.println(KMADEConstant.IMPLICIT_STOP_LOAD_SPF_FILE);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }

                if (ExpressKMADXML.isCanceled() && GraphicEditorAdaptator.isBegining()) {
                    System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }

                if (ExpressKMADXML.isDone()
                        && GraphicEditorAdaptator.isCanceled()
                        && ExpressPrecondition.isBegining()) {
                    System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isCanceled()
                        && ExpressPostcondition.isBegining()) {
                    System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isDone()
                        && ExpressPostcondition.isCanceled()
                        && ExpressIteration.isBegining()) {
                    System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    GraphicEditorAdaptator.getTaskModelPanel()
                            .emptyRootFromModel();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }

                if (ExpressKMADXML.isDone() && GraphicEditorAdaptator.isDone()
                        && ExpressPrecondition.isDone()
                        && ExpressPostcondition.isDone()
                        && ExpressIteration.isCanceled()) {
                    System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                    cancelButton.setEnabled(false);
                    retourButton.setEnabled(true);
                    InterfaceExpressJava.clearCurrentProject();
                    KMADeAdaptator.cleanAllAdaptateur();
                    GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                    myTimer.stop();
                    myTimer = null;
                    
                    KMADeAdaptator.disableCurrentFileName();
                    
                    GraphicEditorAdaptator.getMainFrame().activeMessageStream();
                    System.out.println(KMADEConstant.OPEN_EXPRESS_NO_OK_FILE + ExpressKMADXML.getCurrentFileName());
                }
            }
        });

        ExpressKMADXML.setBegining(true);
        ExpressKMADXML.setCanceled(false);
        ExpressKMADXML.setDone(false);
        ExpressKMADXML.setError(false);

        GraphicEditorAdaptator.setBegining(true);
        GraphicEditorAdaptator.setDone(false);
        GraphicEditorAdaptator.setCanceled(false);

        ExpressPrecondition.setBegining(true);
        ExpressPrecondition.setDone(false);
        ExpressPrecondition.setCanceled(false);

        ExpressPostcondition.setBegining(true);
        ExpressPostcondition.setDone(false);
        ExpressPostcondition.setCanceled(false);

        ExpressIteration.setBegining(true);
        ExpressIteration.setDone(false);
        ExpressIteration.setCanceled(false);

        myScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.LOAD_CONSOLE_TITLE_NAME));

        GraphicEditorAdaptator.disabledMainFrameBeforeLoadAndSaveProcess();
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setVisible(true);
    }

    
    public void goReadSPF(boolean ioError, String message) {
        this.setTitle(KMADEConstant.LOAD_MONITOR_TITLE_NAME);
        myTextArea.setText("");
        if (ioError) {
            cancelButton.setEnabled(false);
            retourButton.setEnabled(true);
        } else {
            panelSouth.removeAll();
            cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    InterfaceExpressJava.setCanceled(true);
                    GraphicEditorAdaptator.setCanceled(true);
                    ExpressPrecondition.setCanceled(true);
                    ExpressPostcondition.setCanceled(true);
                }
            });
        
            panelSouth.add(cancelButton);
            panelSouth.add(retourButton);
            
            retourButton.setEnabled(false);
            cancelButton.setEnabled(true);

            myProgressBar.setMaximum(6);
            myTimer = new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (InterfaceExpressJava.isBegining()) {
                        myProgressBar.setString(KMADEConstant.EXPRESS_OBJECTS_TITLE_MESSAGE);
                        InterfaceExpressJava.setBegining(false);
                        InterfaceExpressJava.readOIDFromSPFFile();
                    }
                    
                    if (!InterfaceExpressJava.isDone()) {
                        myProgressBar.setValue(1);
                    } else if (!GraphicEditorAdaptator.isDone()) {
                        myProgressBar.setValue(2);                        
                    } else if (!ExpressPrecondition.isDone()) {
                        myProgressBar.setValue(3);
                    } else if (!ExpressPostcondition.isDone()) {
                        myProgressBar.setValue(4);
                    } else if (!ExpressIteration.isDone()) {
                        myProgressBar.setValue(5);
                    }

                    myTextArea.setCaretPosition(myTextArea.getDocument().getLength());
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isBegining() && ExpressPrecondition.isBegining() && ExpressPostcondition.isBegining() && ExpressIteration.isBegining()) {
                        myProgressBar.setString(KMADEConstant.GRAPHICAL_OBJECTS_MESSAGE);
                        GraphicEditorAdaptator.setBegining(false);
                        GraphicEditorAdaptator.makeAllGraphElements();
                    }
    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isBegining() && ExpressPostcondition.isBegining() && ExpressIteration.isBegining()) {
                        myProgressBar.setString(KMADEConstant.PRECONDITION_PROGRESSBAR_MESSAGE);
                        ExpressPrecondition.setBegining(false);
                        ExpressPrecondition.makeAndCheckPreconditionOpenSPFFile();
                    }
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isDone() && ExpressPostcondition.isBegining() && ExpressIteration.isBegining()) {
                        myProgressBar.setString(KMADEConstant.POSTCONDITION_PROGRESSBAR_MESSAGE);
                        ExpressPostcondition.setBegining(false);
                        ExpressPostcondition.makeAndCheckPostconditionOpenSPFFile();
                    }
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isDone() && ExpressPostcondition.isDone() && ExpressIteration.isBegining()) {
                        myProgressBar.setString(KMADEConstant.ITERATION_PROGRESSBAR_MESSAGE);
                        ExpressIteration.setBegining(false);
                        ExpressIteration.makeAndCheckIterationOpenSPFFile();
                    }
                    
                    if (ExpressIteration.isDone()) {
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        myTimer.stop();
                        myTimer = null;
                        
                        myProgressBar.setValue(6);
                        myProgressBar.setString(KMADEConstant.LOAD_PROJECT_FINISHED_MESSAGE);
                        
                        GraphicEditorAdaptator.clearSelection();
                        // Afficher les nouvelles valeurs dans les Tables (Graphique)
                        AbstractObjectAdaptator.updateAbstractObjectView();     
                        IntervalAdaptator.updateIntervalView();
                        EnumAdaptator.updateEnumView();
                        UserAdaptator.updateUserView();
                        EventAdaptator.updateEventView();
                        // A voir si c'est utile?
                        ConcreteObjectPanelAdaptator.updateConcreteObjectView();
                        GraphicEditorAdaptator.setGlobalTaskModel();
                        GraphicEditorAdaptator.getMainFrame().showProjectPanel();   
                        InterfaceExpressJava.setBddSetOff();
                    }
    
                    if (InterfaceExpressJava.isError()) {
                        System.err.println(KMADEConstant.IMPLICIT_STOP_LOAD_SPF_FILE);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);   
                        InterfaceExpressJava.clearCurrentProject();
                        KMADeAdaptator.cleanAllAdaptateur();
                        myTimer.stop();
                        myTimer = null;
                    }
                    
                    if (InterfaceExpressJava.isCanceled() && GraphicEditorAdaptator.isBegining()) {
                        System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_OBJECT);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        InterfaceExpressJava.clearCurrentProject();
                        KMADeAdaptator.cleanAllAdaptateur();
                        myTimer.stop();
                        myTimer = null;                        
                    }
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isCanceled() && ExpressPrecondition.isBegining()) {
                        System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        InterfaceExpressJava.clearCurrentProject();
                        KMADeAdaptator.cleanAllAdaptateur();
                        GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                        myTimer.stop();
                        myTimer = null;
                    }
    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isCanceled() && ExpressPostcondition.isBegining()) {
                        System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        InterfaceExpressJava.clearCurrentProject();
                        KMADeAdaptator.cleanAllAdaptateur();
                        GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                        myTimer.stop();
                        myTimer = null;
                    }
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isDone() && ExpressPostcondition.isCanceled() && ExpressIteration.isBegining()) {
                        System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        InterfaceExpressJava.clearCurrentProject();
                        KMADeAdaptator.cleanAllAdaptateur();
                        GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                        myTimer.stop();
                        myTimer = null;
                    }
                    
                    if (InterfaceExpressJava.isDone() && GraphicEditorAdaptator.isDone() && ExpressPrecondition.isDone() && ExpressPostcondition.isDone() && ExpressIteration.isCanceled()) {
                        System.out.println(KMADEConstant.EXPLICIT_STOP_LOAD_SPF_FILE_DURING_GRAPHICAL_OBJECT);
                        cancelButton.setEnabled(false);
                        retourButton.setEnabled(true);
                        InterfaceExpressJava.clearCurrentProject();                        
                        KMADeAdaptator.cleanAllAdaptateur();
                        GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();
                        myTimer.stop();
                        myTimer = null;
                    }
                }
            });

            InterfaceExpressJava.setBegining(true);
            InterfaceExpressJava.setCanceled(false);
            InterfaceExpressJava.setDone(false);
            InterfaceExpressJava.setError(false);
            
            GraphicEditorAdaptator.setBegining(true);
            GraphicEditorAdaptator.setDone(false);
            GraphicEditorAdaptator.setCanceled(false);
            
            ExpressPrecondition.setBegining(true);
            ExpressPrecondition.setDone(false);
            ExpressPrecondition.setCanceled(false);
            
            ExpressPostcondition.setBegining(true);
            ExpressPostcondition.setDone(false);
            ExpressPostcondition.setCanceled(false);
            
            ExpressIteration.setBegining(true);
            ExpressIteration.setDone(false);
            ExpressIteration.setCanceled(false);
                        
            myScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.LOAD_CONSOLE_TITLE_NAME));            
        }
        
        System.setOut(new PrintStream(myOutputStream));
        System.out.println(message);
        
        GraphicEditorAdaptator.disabledMainFrameBeforeLoadAndSaveProcess();
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setVisible(true);
    }

    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.LOAD_MONITOR_TITLE_NAME);
        myTitledBorder.setTitle(KMADEConstant.STATE_LOAD_TITLE_NAME);
        cancelButton.setText(KMADEConstant.CANCEL_MESSAGE);
        retourButton.setText(KMADEConstant.GO_BACK_MESSAGE);
    }
}

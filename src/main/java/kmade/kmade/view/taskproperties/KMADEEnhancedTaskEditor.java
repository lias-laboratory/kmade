package kmade.kmade.view.taskproperties;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.ActorAdaptator;
import kmade.kmade.adaptatorUI.ActorSystemAdaptator;
import kmade.kmade.adaptatorUI.EventAdaptator;
import kmade.kmade.adaptatorUI.PrePostIterExpressionAdaptator;
import kmade.kmade.adaptatorUI.TaskPropertiesEnhancedEditorAdaptator;
import kmade.kmade.view.toolutilities.KMADEToolUtilities;
import kmade.kmade.view.toolutilities.LanguageFactory;
import kmade.nmda.schema.tache.Decomposition;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Frequence;
import kmade.nmda.schema.tache.Importance;
import kmade.nmda.schema.tache.Modalite;
import kmade.nmda.schema.tache.Tache;

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
public class KMADEEnhancedTaskEditor extends JFrame implements ActionListener, KeyListener, MouseListener, LanguageFactory {

    private static final long serialVersionUID = -2083164606554606839L;

    public static float[] BLUR = { 0.10f, 0.10f, 0.10f, 0.10f, 0.30f, 0.10f,
            0.10f, 0.10f, 0.10f };

    public static ConvolveOp blurOp = new ConvolveOp(new Kernel(3, 3, BLUR));

    private Timer myTimer;

    private long start;

    private boolean isInitialized;
  
    private boolean stop;
    
    private int stateAnimation = 0; // 0 : rien; 1 : autre tache; 2 : tache courante
    
    private int DELAY_ANIMATION = 1500;

    private int WAIT_DELAY_BEFORE_ANIMATION = 500;
    
    private String taskName;
    
    private MyGlassPane myGlassPane;

    private ButtonGroup ImportanceGroup;

    private JLabel ImportanceLabel;

    private JLabel InterruptibiliteLabel;

    private JLabel modaliteLabel;

    private JLabel NecessiteLabel;

    private JRadioButton absExecutant;

    private JPanel acteurPanel;

    private JTable acteurTable;
    
    private JPanel acteurSystemePanel;
    
    private JTable acteurSystemeTable;

    private JRadioButton averageFrequence;

    private JPanel caracteristiquePanel;

    private JRadioButton choiceOperateur;

    private JRadioButton cogModalite;

    private JRadioButton concOperateur;

    private JComboBox declenchementField;

    private JLabel declenchementLabel;

    private JTextField dureeField;

    private JLabel dureeLabel;

    private JRadioButton enaOperateur;

    private ButtonGroup executantGroup;

    private JLabel executantLabel;

    private JTextArea executionArea;

    private JTextField feedbackField;

    private JLabel feedbackLabel;

    private JLabel frequenceLabel;

    private ButtonGroup frequencyGroup;

    private KMADEEnhancedTaskLabel gaucheLabel;

    private KMADEEnhancedTaskLabel hautLabel;

    private KMADEEnhancedTaskLabel basLabel;

    private KMADEEnhancedTaskLabel droiteLabel;
    
    private JRadioButton highFrequence;

    private JRadioButton highImportance;

    private JRadioButton intExecutant;

    private ButtonGroup interruptibilityGroup;

    private JRadioButton interruptibleInterruptibilite;

    private JTextArea iterationArea;

    private JScrollPane jScrollPane1;

    private JScrollPane jScrollPane2;

    private JScrollPane jScrollPane3;

    private JScrollPane jScrollPane4;

    private JScrollPane jScrollPane5;

    private JScrollPane jScrollPane6;

    private JScrollPane jScrollPane7;

    private JScrollPane jScrollPane8;
    
    private JScrollPane jScrollPane16;

    private JTextArea fireEvents;

    private JRadioButton leafOperateur;

    private JRadioButton lowFrequence;

    private JRadioButton lowImportance;

    private JRadioButton middleImportance;

    private ButtonGroup modalityGroup;

    private JTextField nameField;

    private JLabel nameLabel;

    private ButtonGroup necessityGroup;

    private JRadioButton nointerruptibleInterruptibilite;

    private JTextField numField;

    private JLabel numLabel;

    private JTextArea objetsListPanel;

    private JRadioButton obligatoireNecessite;

    private JTextArea observationArea;

    private JRadioButton oiOperateur;

    private JLabel operateurLabel;

    private ButtonGroup operatorGroup;

    private JRadioButton optionnelleNecessite;

    private JPanel ordonnancementPanel;

    private JPanel panelCenter;

    private JPanel panelEast;

    private JPanel panelNorth;

    private JPanel panelSouth;

    private JPanel panelWest;

    private JPanel effetsdebordPanel;

    private JPanel preconditionPanel;

    private JTextField purposeField;

    private JLabel purposeLabel;

    private JRadioButton smModalite;

    private JRadioButton sysExecutant;

    private JRadioButton unkExecutant;

    private JRadioButton unkFrequence;

    private JRadioButton unkImportance;

    private JRadioButton unkModalite;

    private JRadioButton unkOperateur;

    private JTextArea usedObjects;

    private JRadioButton userExecutant;    

    private JPanel jPanel1;
    
    private JTextField valeurFrequenceField;
    
    private JLabel valeurFrequenceLabel;
    
    private MyActorTableModel refTableModel;
    
    private MyActorSystemeTableModel refTableModelSys;
       
    private JButton myButton;
    
    public KMADEEnhancedTaskEditor() {
        this.setTitle(KMADEConstant.COMPLETE_EDITOR_TITLE_NAME);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.initComponents();
        myGlassPane = new MyGlassPane();
        this.setGlassPane(myGlassPane);
        KMADEToolUtilities.setCenteredInScreen(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.closeNMDAEnhancedTaskEditor();
            }
        });
        nameField.addKeyListener(this);
        numField.setEditable(false);
        dureeField.addKeyListener(this);
        /*
        purposeField.setEditable(false);
        purposeField.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setPurpose();
           }
        });
        purposeField.addMouseListener(this);
        purposeField.setBackground(nameField.getBackground());
		*/
        /*
        feedbackField.setEditable(false);
        feedbackField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setFeedBack();
            }
        });
        feedbackField.addMouseListener(this);
        feedbackField.setBackground(nameField.getBackground());
        */
        /*
        observationArea.setEditable(false);
        observationArea.addMouseListener(this);
		*/
        unkModalite.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setUnknownModalite();
           }
        });
        smModalite.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setSensoriMotriceModalite();
           }
        });
        cogModalite.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setCognitiveModalite();
           }
        });
        
        unkExecutant.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setUnknownExecutant();
           }
        });
        userExecutant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               TaskPropertiesEnhancedEditorAdaptator.setUserExecutant();
            }
         });
        sysExecutant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setSystemExecutant();                
            }
         });
        intExecutant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setInteractifExecutant();                
            }
         });
        absExecutant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setAbstractExecutant();                
            }
         });
        
        unkImportance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setUnknownImportance();           
            }
         });
        
        lowImportance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setLowImportance();                            
            }
         });
        
        middleImportance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setMiddleImportance();                            
            }
         });
        
        highImportance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setHighImportance();                            
            }
         });
        
        unkFrequence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setUnknownFrequence();                            
            }
         });
        
        highFrequence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setHeighFrequence();                            
            }
         });
        
        averageFrequence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setAverageFrequence();                            
            }
         });
        
        lowFrequence.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setLowFrequence();                            
            }
        });
        
        valeurFrequenceField.addKeyListener(this);
        
        this.declenchementField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setDeclenchement((String)declenchementField.getSelectedItem());   
            }
        });
        
        this.unkOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setUnknownOperator();   
            }
        });
        
        this.enaOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setEnablingOperator();                                       
            }
        });
        
        this.concOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setConcurrentOperator();                                       
            }
        });
        
        this.choiceOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setChoiceOperator();                                       
            }
        });
        
        this.leafOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setLeafOperator();                                       
            }
        });
        
        this.oiOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setOrderIndependencyOperator();                                       
            }
        });
        
        this.optionnelleNecessite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setOptionnelleNecessite();                                       
            }
        });
        
        this.obligatoireNecessite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setObligatoireNecessite();                                       
            }
        });
        
        this.interruptibleInterruptibilite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setInterruptibilite();                                       
            }
        });
        
        this.nointerruptibleInterruptibilite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.setNoInterruptibilite();                                       
            }
        });
        iterationArea.setEditable(false);
        iterationArea.addMouseListener(this);
     
        executionArea.setEditable(false);
        executionArea.addMouseListener(this);
        
        fireEvents.setEditable(false);
        fireEvents.addMouseListener(this);
        
        usedObjects.setEditable(false);
        usedObjects.addMouseListener(this);
        
        acteurTable.addMouseListener(this);
        acteurTable.getTableHeader().addMouseListener(this);
        
        refTableModel = new MyActorTableModel();
        acteurTable.setModel(refTableModel);
        
        acteurSystemeTable.addMouseListener(this);
        acteurSystemeTable.getTableHeader().addMouseListener(this);
        
        refTableModelSys = new MyActorSystemeTableModel();
        acteurSystemeTable.setModel(refTableModelSys);
        
        
        objetsListPanel.setEditable(false);
        this.setPreferredSize(new Dimension(900,900));
        KMADEToolUtilities.setCenteredInScreen(this);
    }

    public void launchAnimation(String taskName) {
        isInitialized = false;
        stop = false;
        if (myTimer != null && myTimer.isRunning()) {
            if (stateAnimation == 2) {
                myGlassPane.setVisible(false);
                myTimer.stop();
            } else {
                return;
            }
        }
        this.taskName = taskName;
        stateAnimation = 1;
        myTimer = new Timer(100 / 60, this);
        myTimer.start();
    }
    
    public void stopAnimation() {
        stop = true;
    }

    public void setVisible(boolean value) {
        super.setVisible(value);

        if (value) {
            isInitialized = false;
            stateAnimation = 2;
            if (myTimer != null && myTimer.isRunning()) {
                myTimer.stop();
            }
        
            myTimer = new Timer(100 / 60, this);        
            myTimer.start();
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (stateAnimation == 2) {
            if (!this.isInitialized) {
                start = System.currentTimeMillis();
                isInitialized = true;            
            }
            long elapsed = System.currentTimeMillis() - start;
            
            if (elapsed < DELAY_ANIMATION) {                
                myGlassPane.setVisible(true);
                myGlassPane.repaint();
            } else {
                myGlassPane.setVisible(false);
                ((Timer) e.getSource()).stop();
                stateAnimation = 0;
            }            
        }
        
        if (stateAnimation == 1) {        
            if (!this.isInitialized) {
                start = System.currentTimeMillis();
                isInitialized = true;            
            }
            long elapsed = System.currentTimeMillis() - start;
    
            if (stop) {
                myGlassPane.setVisible(false);
                ((Timer) e.getSource()).stop();
                stateAnimation = 0;
            }
            
            if ((!stop) && (elapsed > WAIT_DELAY_BEFORE_ANIMATION)) {
                myGlassPane.setVisible(true);
                myGlassPane.repaint();
            }  
        }
    }
      
    public void setLeftButton(boolean isEnabled, String name, String numero) {
        gaucheLabel.setEnabled(isEnabled);
        gaucheLabel.setTaskName(name);
        gaucheLabel.setTaskNum(numero);
    }
    
    public void setRightButton(boolean isEnabled, String name, String numero) {
        droiteLabel.setEnabled(isEnabled);
        droiteLabel.setTaskName(name);
        droiteLabel.setTaskNum(numero);        
    }
    
    public void setUpButton(boolean isEnabled, String name, String numero) {
        hautLabel.setEnabled(isEnabled);
        hautLabel.setTaskName(name);
        hautLabel.setTaskNum(numero);
    }
    
    public void setDownButton(boolean isEnabled, String name, String numero) {
        basLabel.setEnabled(isEnabled);
        basLabel.setTaskName(name);
        basLabel.setTaskNum(numero);
    }
    
    public void displayTaskProperties(String numero, String tacheMere,
            String name, String but, String res, String feed, String duree,
            String obs, Executant exec, Modalite mod, Frequence freq, String compFreq,Importance imp,
            String events, boolean facultatif,
            boolean interruptible, String[] allevents, String dec, ArrayList<String[]> actRef,ArrayList<String[]> actRefSys,
            String prec, String post,Decomposition decomposition, String it) {
        this.numField.setText(numero);
        // tacheMere
        this.nameField.setText(name);
        this.purposeField.setText(but);
        // res
        this.feedbackField.setText(feed);
        this.dureeField.setText(duree);
        this.observationArea.setText(obs);
        if (exec == Executant.INCONNU) {
            this.unkExecutant.setSelected(true);
        } else if (exec == Executant.USER) {
            this.userExecutant.setSelected(true);
        } else if (exec == Executant.SYS) {
            this.sysExecutant.setSelected(true);
        } else if (exec == Executant.INT) {
            this.intExecutant.setSelected(true);
        } else if (exec == Executant.ABS) {
            this.absExecutant.setSelected(true);
        }

        if (mod == Modalite.INCONNU) {
            this.unkModalite.setSelected(true);
        } else if (mod == Modalite.COGN) {
            this.cogModalite.setSelected(true);
        } else if (mod == Modalite.SENS) {
            this.smModalite.setSelected(true);
        }
        
        if (freq == Frequence.INCONNU) {
            this.unkFrequence.setSelected(true);
        } else if (freq == Frequence.ELEVE) {
            this.highFrequence.setSelected(true);
        } else if (freq == Frequence.MOYENNE) {
            this.averageFrequence.setSelected(true);
        } else if (freq == Frequence.FAIBLE) {
            this.lowFrequence.setSelected(true);
        }
        this.valeurFrequenceField.setText(compFreq);
        
        if (imp == Importance.INCONNU) {
            this.unkImportance.setSelected(true);            
        } else if (imp == Importance.PEU) {
            this.lowImportance.setSelected(true);
        } else if (imp == Importance.ASSEZ) {
            this.middleImportance.setSelected(true);
        } else if (imp == Importance.TRES) {
            this.highImportance.setSelected(true);
        }
              
        if (facultatif) {
            this.optionnelleNecessite.setSelected(true);
        } else {
            this.obligatoireNecessite.setSelected(true);
        }
        
        if (interruptible) {
            this.interruptibleInterruptibilite.setSelected(true);
        } else {
            this.nointerruptibleInterruptibilite.setSelected(true);
        }
        
        this.declenchementField.setModel(new DefaultComboBoxModel(allevents));
        this.declenchementField.setSelectedItem(dec);
        
        if (decomposition == Decomposition.INCONNU) {
            this.unkOperateur.setSelected(true);
        } else if (decomposition == Decomposition.SEQ) {
            this.enaOperateur.setSelected(true);
        } else if (decomposition == Decomposition.PAR) {
            this.concOperateur.setSelected(true);
        } else if (decomposition == Decomposition.ALT) {
            this.choiceOperateur.setSelected(true);
        } else if (decomposition == Decomposition.ELE) {
            this.leafOperateur.setSelected(true);
        } else if (decomposition == Decomposition.ET) {
            this.oiOperateur.setSelected(true);
        }
        
        this.fireEvents.setText(events);     
        this.refTableModel.setData(actRef);
        this.refTableModelSys.setData(actRefSys);
        if(Tache.canHaveActor(exec)){
        	this.acteurPanel.setVisible(true);
        }else{
        	this.acteurPanel.setVisible(false);
        }
        if(Tache.canHaveActorSystem(exec)){
        	 this.acteurSystemePanel.setVisible(true);
        }else{
        	this.acteurSystemePanel.setVisible(false);
        }

        // Précondition, itération et liste des objets
        this.executionArea.setText(prec);
        this.usedObjects.setText(post);
        this.iterationArea.setText(it);
    }

    class MyGlassPane extends JComponent {
        private static final long serialVersionUID = -7389728241960316601L;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Font font = g.getFont();

            Dimension size = getSize();
            int h = size.height;
            int w = size.width;
            int arc = 0;

            if (size.width > 300) {
                font = font.deriveFont(Font.PLAIN, 48);
                arc = 20;
            } else if (size.width > 150) {
                font = font.deriveFont(Font.PLAIN, 24);
                arc = 10;
            } else {
                font = font.deriveFont(Font.PLAIN, 12);
                arc = 3;
            }

            String text = "";
            if (stateAnimation == 2) {
                text = KMADEEnhancedTaskEditor.this.nameField.getText();                
            } else if (stateAnimation == 1) {
                text = KMADEEnhancedTaskEditor.this.taskName;
            }

            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics();
            Rectangle2D stringBounds = metrics.getStringBounds(text, g);

            // Figure out how big we want our rounded rectangle to be
            int preferredWidth = (int) stringBounds.getWidth()
                    + metrics.getHeight();
            int preferredHeight = (int) stringBounds.getHeight()
                    + metrics.getHeight();

            w = Math.min(preferredWidth, w);
            h = Math.min(preferredHeight, h);

            int x = (size.width - w) / 2;
            int y = (size.height - h) / 2;

            // Create the path that runs through the rounded rectangle
            float h2 = h / 2;
            float h4 = h / 4;

            GeneralPath path = new GeneralPath();
            path.moveTo(0, h);
            path.curveTo(0, h - h4, h4, h2, h2, h2);
            path.lineTo(w - h2, h2);
            path.curveTo(w - h4, h2, w, h4, w, 0);
            path.lineTo(w, h);

            // Create a buffered image to paint the rounded rectangle
            BufferedImage vBuffer = new BufferedImage(preferredWidth,
                    preferredHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D bg2d = vBuffer.createGraphics();
            bg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Paint the background
            RoundRectangle2D fillArea = new RoundRectangle2D.Double(0.0D, 0.0D,
                    w, h, arc, arc);
            bg2d.setClip(fillArea);

            Area area = new Area(fillArea);
            area.subtract(new Area(path));

            Color vStartColor = new Color(10, 0, 40);
            Color vEndColor = new Color(175, 165, 225);

            Paint p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h,
                    vEndColor);
            bg2d.setPaint(p);

            bg2d.fill(area);

            vStartColor = new Color(5, 0, 50);
            vEndColor = new Color(105, 100, 155);

            p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h, vEndColor);
            bg2d.setPaint(p);

            bg2d.fill(path);

            // Blur the background
            vBuffer = blurOp.filter(vBuffer, null);

            // Figure out where to place the background and the text
            int insetX = (size.width - w) / 2;
            int insetY = (size.height - h) / 2;

            g2d.translate(insetX, insetY);

            Composite composite = g2d.getComposite();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
            Shape clip = g2d.getClip();
            g2d.setClip(fillArea);
            g2d.drawImage(vBuffer, 0, 0, null);
            g2d.setClip(clip);
            g2d.setComposite(composite);

            // Paint a border around the background since it was clipped and the
            // edges
            // weren't anti-aliased
            Color vWrapColor = new Color(175, 165, 225);
            g2d.setColor(vWrapColor);
            g2d.drawRoundRect(0, 0, w, h, arc, arc);

            // Figure out where to draw the text
            x = (w - (int) stringBounds.getWidth()) / 2;
            y = (h / 2) + ((metrics.getAscent() - metrics.getDescent()) / 2);

            // Draw a shadwo
            g2d.setColor(new Color(0, 0, 0, 70));
            g2d.drawString(text, x + 2, y + 2);

            // Draw the text
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, x, y);

            g2d.translate(-insetX, -insetY);
        }
    }
    
    private void initComponents() {
        executantGroup = new javax.swing.ButtonGroup();
        modalityGroup = new javax.swing.ButtonGroup();
        frequencyGroup = new javax.swing.ButtonGroup();
        ImportanceGroup = new javax.swing.ButtonGroup();
        operatorGroup = new javax.swing.ButtonGroup();
        necessityGroup = new javax.swing.ButtonGroup();
        interruptibilityGroup = new javax.swing.ButtonGroup();
        panelWest = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        panelCenter = new javax.swing.JPanel();
        preconditionPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        executionArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        iterationArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        objetsListPanel = new javax.swing.JTextArea();
        caracteristiquePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        dureeLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        dureeField = new javax.swing.JTextField();
        purposeLabel = new javax.swing.JLabel();
        purposeField = new javax.swing.JTextField();
        feedbackLabel = new javax.swing.JLabel();
        feedbackField = new javax.swing.JTextField();
        numLabel = new javax.swing.JLabel();
        numField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        modaliteLabel = new javax.swing.JLabel();
        unkModalite = new javax.swing.JRadioButton();
        smModalite = new javax.swing.JRadioButton();
        cogModalite = new javax.swing.JRadioButton();
        executantLabel = new javax.swing.JLabel();
        unkExecutant = new javax.swing.JRadioButton();
        userExecutant = new javax.swing.JRadioButton();
        sysExecutant = new javax.swing.JRadioButton();
        intExecutant = new javax.swing.JRadioButton();
        absExecutant = new javax.swing.JRadioButton();
        ImportanceLabel = new javax.swing.JLabel();
        unkImportance = new javax.swing.JRadioButton();
        lowImportance = new javax.swing.JRadioButton();
        middleImportance = new javax.swing.JRadioButton();
        highImportance = new javax.swing.JRadioButton();
        frequenceLabel = new javax.swing.JLabel();
        unkFrequence = new javax.swing.JRadioButton();
        highFrequence = new javax.swing.JRadioButton();
        averageFrequence = new javax.swing.JRadioButton();
        lowFrequence = new javax.swing.JRadioButton();
        valeurFrequenceLabel = new javax.swing.JLabel();
        valeurFrequenceField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        observationArea = new javax.swing.JTextArea();
        ordonnancementPanel = new javax.swing.JPanel();
        NecessiteLabel = new javax.swing.JLabel();
        InterruptibiliteLabel = new javax.swing.JLabel();
        declenchementLabel = new javax.swing.JLabel();
        optionnelleNecessite = new javax.swing.JRadioButton();
        obligatoireNecessite = new javax.swing.JRadioButton();
        interruptibleInterruptibilite = new javax.swing.JRadioButton();
        nointerruptibleInterruptibilite = new javax.swing.JRadioButton();
        declenchementField = new JComboBox();
        operateurLabel = new javax.swing.JLabel();
        unkOperateur = new javax.swing.JRadioButton();
        enaOperateur = new javax.swing.JRadioButton();
        concOperateur = new javax.swing.JRadioButton();
        leafOperateur = new javax.swing.JRadioButton();
        choiceOperateur = new javax.swing.JRadioButton();
        oiOperateur = new javax.swing.JRadioButton();
        effetsdebordPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usedObjects = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        fireEvents = new javax.swing.JTextArea();
        acteurPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        acteurTable = new javax.swing.JTable();
        panelNorth = new javax.swing.JPanel();
        panelEast = new javax.swing.JPanel();
        panelSouth = new javax.swing.JPanel();
        acteurSystemePanel = new javax.swing.JPanel();
        acteurSystemeTable = new javax.swing.JTable();
        JPanel panelDuCentre = new JPanel(new BorderLayout(1,1));
        
        gaucheLabel = new KMADEEnhancedTaskLabel(KMADEEnhancedTaskLabel.LEFT);
        hautLabel = new KMADEEnhancedTaskLabel(KMADEEnhancedTaskLabel.TOP);
        droiteLabel = new KMADEEnhancedTaskLabel(KMADEEnhancedTaskLabel.RIGHT);
        basLabel = new KMADEEnhancedTaskLabel(KMADEEnhancedTaskLabel.BOTTOM);
        
        org.jdesktop.layout.GroupLayout panelWestLayout = new org.jdesktop.layout.GroupLayout(panelWest);
        panelWest.setLayout(panelWestLayout);
        panelWestLayout.setHorizontalGroup(
            panelWestLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(gaucheLabel)
        );
        panelWestLayout.setVerticalGroup(
            panelWestLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(gaucheLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        panelDuCentre.add(panelWest, java.awt.BorderLayout.WEST);
        jScrollPane8.setBorder(null);
        
        panelCenter.setMaximumSize(new java.awt.Dimension(32767, 24000));
        preconditionPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.EXECUTION_CONDITION_TITLE_NAME));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_LABEL_VALUE));
        jScrollPane7.setOpaque(false);
        executionArea.setColumns(20);
        executionArea.setRows(5);
        executionArea.setBorder(null);
        executionArea.setOpaque(true);
        jScrollPane7.setViewportView(executionArea);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.ITERATION_LABEL_VALUE));
        jScrollPane3.setOpaque(false);
        iterationArea.setColumns(20);
        iterationArea.setRows(5);
        iterationArea.setBorder(null);
        iterationArea.setOpaque(true);
        jScrollPane3.setViewportView(iterationArea);

        org.jdesktop.layout.GroupLayout preconditionPanelLayout = new org.jdesktop.layout.GroupLayout(preconditionPanel);
        preconditionPanel.setLayout(preconditionPanelLayout);
        preconditionPanelLayout.setHorizontalGroup(
            preconditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
            .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );
        preconditionPanelLayout.setVerticalGroup(
            preconditionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, preconditionPanelLayout.createSequentialGroup()
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
        );

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.OBJECT_LIST_TITLE_NAME));
        jScrollPane4.setOpaque(false);
        objetsListPanel.setColumns(20);
        objetsListPanel.setRows(5);
        objetsListPanel.setWrapStyleWord(true);
        objetsListPanel.setBorder(null);
        objetsListPanel.setOpaque(true);
        jScrollPane4.setViewportView(objetsListPanel);

        caracteristiquePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.GENERAL_INFORMATION_PANEL_TITLE_NAME));
        nameLabel.setText(KMADEConstant.TASK_NAME_TITLE);
        
        dureeLabel.setText(KMADEConstant.TASK_DURATION_TITLE);

        purposeLabel.setText(KMADEConstant.TASK_PURPOSE_TITLE);

        feedbackLabel.setText(KMADEConstant.TASK_FEEDBACK_TITLE);

        numLabel.setText(KMADEConstant.TASK_NUMBER_NAME_TITLE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        modaliteLabel.setText(KMADEConstant.MODALITY_NAME_TITLE + " :");

        modalityGroup.add(unkModalite);
        unkModalite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        unkModalite.setText(Modalite.getEnumereIntoLocaleModalite("UNK"));
        unkModalite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        unkModalite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        modalityGroup.add(smModalite);
        smModalite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        smModalite.setText(Modalite.getEnumereIntoLocaleModalite("SENS"));
        smModalite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        smModalite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        modalityGroup.add(cogModalite);
        cogModalite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        cogModalite.setText(Modalite.getEnumereIntoLocaleModalite("COGN"));
        cogModalite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cogModalite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        executantLabel.setText(KMADEConstant.EXECUTING_NAME_TITLE + " :");

        executantGroup.add(unkExecutant);
        unkExecutant.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        unkExecutant.setText(Executant.getEnumereIntoLocaleExecutant("UNK"));
        unkExecutant.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        unkExecutant.setMargin(new java.awt.Insets(0, 0, 0, 0));

        executantGroup.add(userExecutant);
        userExecutant.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        userExecutant.setText(Executant.getEnumereIntoLocaleExecutant("USER"));
        userExecutant.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        userExecutant.setMargin(new java.awt.Insets(0, 0, 0, 0));

        executantGroup.add(sysExecutant);
        sysExecutant.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        sysExecutant.setText(Executant.getEnumereIntoLocaleExecutant("SYS"));
        sysExecutant.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sysExecutant.setMargin(new java.awt.Insets(0, 0, 0, 0));

        executantGroup.add(intExecutant);
        intExecutant.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        intExecutant.setText(Executant.getEnumereIntoLocaleExecutant("INT"));
        intExecutant.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        intExecutant.setMargin(new java.awt.Insets(0, 0, 0, 0));

        executantGroup.add(absExecutant);
        absExecutant.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        absExecutant.setText(Executant.getEnumereIntoLocaleExecutant("ABS"));
        absExecutant.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        absExecutant.setMargin(new java.awt.Insets(0, 0, 0, 0));

        ImportanceLabel.setText(KMADEConstant.IMPORTANT_NAME_TITLE + " :");

        ImportanceGroup.add(unkImportance);
        unkImportance.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        unkImportance.setText(Importance.getEnumereIntoLocaleImportance("UNK"));
        unkImportance.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        unkImportance.setMargin(new java.awt.Insets(0, 0, 0, 0));

        ImportanceGroup.add(lowImportance);
        lowImportance.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        lowImportance.setText(Importance.getEnumereIntoLocaleImportance("LOW"));
        lowImportance.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lowImportance.setMargin(new java.awt.Insets(0, 0, 0, 0));

        ImportanceGroup.add(middleImportance);
        middleImportance.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        middleImportance.setText(Importance.getEnumereIntoLocaleImportance("AVERAGE"));
        middleImportance.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        middleImportance.setMargin(new java.awt.Insets(0, 0, 0, 0));

        ImportanceGroup.add(highImportance);
        highImportance.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        highImportance.setText(Importance.getEnumereIntoLocaleImportance("HIGH"));
        highImportance.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        highImportance.setMargin(new java.awt.Insets(0, 0, 0, 0));

        frequenceLabel.setText(KMADEConstant.FREQUENCY_NAME_TITLE + " :");

        frequencyGroup.add(unkFrequence);
        unkFrequence.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        unkFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("UNK"));
        unkFrequence.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        unkFrequence.setMargin(new java.awt.Insets(0, 0, 0, 0));

        frequencyGroup.add(highFrequence);
        highFrequence.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        highFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("HIGH"));
        highFrequence.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        highFrequence.setMargin(new java.awt.Insets(0, 0, 0, 0));

        frequencyGroup.add(averageFrequence);
        averageFrequence.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        averageFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("AVERAGE"));
        averageFrequence.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        averageFrequence.setMargin(new java.awt.Insets(0, 0, 0, 0));

        frequencyGroup.add(lowFrequence);
        lowFrequence.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        lowFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("LOW"));
        lowFrequence.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lowFrequence.setMargin(new java.awt.Insets(0, 0, 0, 0));

        valeurFrequenceLabel.setText(KMADEConstant.FREQUENCY_VALUE_NAME_TITLE + " :");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(modaliteLabel)
                    .add(executantLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(smModalite)
                    .add(cogModalite)
                    .add(userExecutant)
                    .add(sysExecutant)
                    .add(intExecutant)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(unkModalite)
                            .add(unkExecutant)
                            .add(absExecutant))
                        .add(16, 16, 16)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(valeurFrequenceLabel)
                    .add(frequenceLabel)
                    .add(ImportanceLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(highImportance)
                    .add(middleImportance)
                    .add(lowImportance)
                    .add(unkImportance, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(unkFrequence, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(highFrequence)
                    .add(averageFrequence)
                    .add(lowFrequence)
                    .add(valeurFrequenceField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(modaliteLabel)
                            .add(unkModalite))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(smModalite)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cogModalite)
                        .add(45, 45, 45)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(unkExecutant, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(executantLabel))
                        .add(6, 6, 6)
                        .add(userExecutant)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sysExecutant)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(intExecutant)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(absExecutant))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(ImportanceLabel)
                            .add(unkImportance))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lowImportance)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(middleImportance)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(highImportance)
                        .add(26, 26, 26)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(frequenceLabel)
                            .add(unkFrequence))
                        .add(6, 6, 6)
                        .add(highFrequence)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(averageFrequence)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lowFrequence)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(valeurFrequenceLabel)
                            .add(valeurFrequenceField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_OBSERVATION_TITLE));
        jScrollPane5.setOpaque(false);
        observationArea.setColumns(20);
        observationArea.setRows(5);
        observationArea.setBorder(null);
        observationArea.setOpaque(true);
        jScrollPane5.setViewportView(observationArea);

        org.jdesktop.layout.GroupLayout caracteristiquePanelLayout = new org.jdesktop.layout.GroupLayout(caracteristiquePanel);
        caracteristiquePanel.setLayout(caracteristiquePanelLayout);
        caracteristiquePanelLayout.setHorizontalGroup(
            caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, caracteristiquePanelLayout.createSequentialGroup()
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, caracteristiquePanelLayout.createSequentialGroup()
                        .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(feedbackLabel)                                
                            .add(purposeLabel)
                            .add(dureeLabel)
                            .add(numLabel)
                            .add(nameLabel))
                        .add(10, 10, 10)
                        .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(feedbackField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .add(purposeField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .add(dureeField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .add(numField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, nameField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        caracteristiquePanelLayout.setVerticalGroup(
            caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, caracteristiquePanelLayout.createSequentialGroup()
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nameLabel)
                    .add(nameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(numLabel)
                    .add(numField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dureeLabel)
                    .add(dureeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(purposeLabel)
                    .add(purposeField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(caracteristiquePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(feedbackLabel)
                    .add(feedbackField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        ordonnancementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.SCHEDULE_PANEL_TITLE_NAME));
        NecessiteLabel.setText(KMADEConstant.NECESSITE_LABEL_NAME + " :");

        InterruptibiliteLabel.setText(KMADEConstant.INTERRUPTIBLE_LABEL_NAME + " :");

        declenchementLabel.setText(KMADEConstant.DECLENCHEMENT_LABEL_NAME + " :");

        necessityGroup.add(optionnelleNecessite);
        optionnelleNecessite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        optionnelleNecessite.setText(KMADEConstant.OPTIONAL_NECESSITE_VALUE);
        optionnelleNecessite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        optionnelleNecessite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        necessityGroup.add(obligatoireNecessite);
        obligatoireNecessite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        obligatoireNecessite.setText(KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE);
        obligatoireNecessite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        obligatoireNecessite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        interruptibilityGroup.add(interruptibleInterruptibilite);
        interruptibleInterruptibilite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        interruptibleInterruptibilite.setText(KMADEConstant.INTERRUPTIBLE_VALUE);
        interruptibleInterruptibilite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        interruptibleInterruptibilite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        interruptibilityGroup.add(nointerruptibleInterruptibilite);
        nointerruptibleInterruptibilite.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        nointerruptibleInterruptibilite.setText(KMADEConstant.NO_INTERRUPTIBLE_VALUE);
        nointerruptibleInterruptibilite.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        nointerruptibleInterruptibilite.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operateurLabel.setText(KMADEConstant.SCHEDULE_LABEL_NAME + " :");

        operatorGroup.add(unkOperateur);
        unkOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        unkOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("UNK"));
        unkOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        unkOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operatorGroup.add(enaOperateur);
        enaOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        enaOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("SEQ"));
        enaOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        enaOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operatorGroup.add(concOperateur);
        concOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        concOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("PAR"));
        concOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        concOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operatorGroup.add(leafOperateur);
        leafOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        leafOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("LEAF"));
        leafOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leafOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operatorGroup.add(choiceOperateur);
        choiceOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        choiceOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("ALT"));
        choiceOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        choiceOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        operatorGroup.add(oiOperateur);
        oiOperateur.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10));
        oiOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("ENT"));
        oiOperateur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        oiOperateur.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout ordonnancementPanelLayout = new org.jdesktop.layout.GroupLayout(ordonnancementPanel);
        ordonnancementPanel.setLayout(ordonnancementPanelLayout);
        ordonnancementPanelLayout.setHorizontalGroup(
            ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                        .add(declenchementLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(declenchementField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                        .add(operateurLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                                        .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(unkOperateur)
                                            .add(choiceOperateur)
                                            .add(leafOperateur))
                                        .add(62, 62, 62)
                                        .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(NecessiteLabel)
                                            .add(InterruptibiliteLabel)))
                                    .add(enaOperateur)
                                    .add(oiOperateur))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(nointerruptibleInterruptibilite)
                                    .add(interruptibleInterruptibilite)
                                    .add(optionnelleNecessite)
                                    .add(obligatoireNecessite)))
                            .add(concOperateur))))
                .addContainerGap())
        );
        ordonnancementPanelLayout.setVerticalGroup(
            ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(declenchementLabel)
                    .add(declenchementField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(operateurLabel)
                    .add(unkOperateur)
                    .add(NecessiteLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(optionnelleNecessite))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(enaOperateur)
                    .add(obligatoireNecessite))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(concOperateur)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(ordonnancementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(InterruptibiliteLabel)
                            .add(interruptibleInterruptibilite))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nointerruptibleInterruptibilite))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanelLayout.createSequentialGroup()
                        .add(choiceOperateur)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(leafOperateur)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(oiOperateur)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        effetsdebordPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.EFFETSDEBORD_TITLE_NAME));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_LABEL_VALUE));
        jScrollPane1.setOpaque(false);
        usedObjects.setColumns(20);
        usedObjects.setRows(5);
        usedObjects.setBorder(null);
        usedObjects.setOpaque(true);
        jScrollPane1.setViewportView(usedObjects);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.EVENT_NAME_TITLE));
        jScrollPane2.setOpaque(false);
        fireEvents.setColumns(20);
        fireEvents.setRows(5);
        fireEvents.setBorder(null);
        fireEvents.setOpaque(true);
        jScrollPane2.setViewportView(fireEvents);

        org.jdesktop.layout.GroupLayout effetsdebordPanelLayout = new org.jdesktop.layout.GroupLayout(effetsdebordPanel);
        effetsdebordPanel.setLayout(effetsdebordPanelLayout);
        effetsdebordPanelLayout.setHorizontalGroup(
            effetsdebordPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
        );
        effetsdebordPanelLayout.setVerticalGroup(
            effetsdebordPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, effetsdebordPanelLayout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
        );

        acteurPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.UTILISATEUR_LABEL_NAME));
        acteurSystemePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.MATERIEL_LABEL_NAME));

        jScrollPane6.setBorder(null);        
        jScrollPane6.setViewportView(acteurTable);
        jScrollPane16.setBorder(null);
        jScrollPane16.setViewportView(acteurSystemeTable);

        org.jdesktop.layout.GroupLayout acteurPanelLayout = new org.jdesktop.layout.GroupLayout(acteurPanel);
        acteurPanel.setLayout(acteurPanelLayout);
        acteurPanelLayout.setHorizontalGroup(
            acteurPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        acteurPanelLayout.setVerticalGroup(
            acteurPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        
        org.jdesktop.layout.GroupLayout acteurSystemePanelLayout = new org.jdesktop.layout.GroupLayout(acteurSystemePanel);
        acteurSystemePanel.setLayout(acteurSystemePanelLayout);
        acteurSystemePanelLayout.setHorizontalGroup(
            acteurSystemePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        acteurSystemePanelLayout.setVerticalGroup(
            acteurSystemePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panelCenterLayout = new org.jdesktop.layout.GroupLayout(panelCenter);
        panelCenter.setLayout(panelCenterLayout);
        panelCenterLayout.setHorizontalGroup(
            panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, panelCenterLayout.createSequentialGroup()
                .add(2, 2, 2)
                .add(panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, panelCenterLayout.createSequentialGroup()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 231, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(preconditionPanel))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, caracteristiquePanel)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, panelCenterLayout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(acteurPanel)
                            .add(acteurSystemePanel)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, panelCenterLayout.createSequentialGroup()
                                .add(ordonnancementPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(effetsdebordPanel)))))
                .add(2, 2, 2))
        );
        panelCenterLayout.setVerticalGroup(
            panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.LEADING, panelCenterLayout.createSequentialGroup()
                .add(panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(preconditionPanel)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane4))
                .add(2, 2, 2)
                .add(caracteristiquePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(panelCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(effetsdebordPanel)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, ordonnancementPanel))
                .add(2, 2, 2)
                .add(acteurPanel)
                .add(2, 2, 2)
                .add(acteurSystemePanel)
                .add(2, 2, 2))
        );
        jScrollPane8.setViewportView(panelCenter);

        panelDuCentre.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        org.jdesktop.layout.GroupLayout panelNorthLayout = new org.jdesktop.layout.GroupLayout(panelNorth);
        panelNorth.setLayout(panelNorthLayout);
        panelNorthLayout.setHorizontalGroup(
            panelNorthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(hautLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        panelNorthLayout.setVerticalGroup(
            panelNorthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(hautLabel)
        );
        panelDuCentre.add(panelNorth, java.awt.BorderLayout.NORTH);

        org.jdesktop.layout.GroupLayout panelEastLayout = new org.jdesktop.layout.GroupLayout(panelEast);
        panelEast.setLayout(panelEastLayout);
        panelEastLayout.setHorizontalGroup(
            panelEastLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(droiteLabel)
        );
        panelEastLayout.setVerticalGroup(
            panelEastLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(droiteLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
        );
        panelDuCentre.add(panelEast, java.awt.BorderLayout.EAST);

        org.jdesktop.layout.GroupLayout panelSouthLayout = new org.jdesktop.layout.GroupLayout(panelSouth);
        panelSouth.setLayout(panelSouthLayout);
        panelSouthLayout.setHorizontalGroup(
            panelSouthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(basLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        panelSouthLayout.setVerticalGroup(
            panelSouthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(basLabel)
        );
        panelDuCentre.add(panelSouth, java.awt.BorderLayout.SOUTH);
        this.getContentPane().add(BorderLayout.CENTER, panelDuCentre);
        
        JPanel panelSouth = new JPanel();
        myButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
        panelSouth.add(myButton);
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskPropertiesEnhancedEditorAdaptator.closeNMDAEnhancedTaskEditor();
            }
        });
        this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        pack();
    }
  
    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getSource() == nameField) {
            TaskPropertiesEnhancedEditorAdaptator.setNameInTaskProperties(nameField.getText());
        }
       /* if (keyEvent.getSource() == dureeField) {
            TaskPropertiesEnhancedEditorAdaptator.setDureeInTaskProperties(dureeField.getText());
        }
       */
        if (keyEvent.getSource() == valeurFrequenceField) {
            TaskPropertiesEnhancedEditorAdaptator.setFrequencyValueInTaskProperties(valeurFrequenceField.getText());
        }
    }

    public void keyTyped(KeyEvent keyEvent) {
    }
    
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) {
            
        } else {
            return;
        }
        /*
        if (e.getSource() == purposeField) {
            TaskPropertiesEnhancedEditorAdaptator.setPurpose();
        }        
        if (e.getSource() == feedbackField) {
            TaskPropertiesEnhancedEditorAdaptator.setFeedBack();            
        }
        if (e.getSource() == observationArea) {
            TaskPropertiesEnhancedEditorAdaptator.setObservation();
        }
        */
        if (e.getSource() == fireEvents) {
            EventAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setFiredEvents();
        }
        if (e.getSource() == usedObjects) {
            PrePostIterExpressionAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setEffetsDeBord();
        }
        if ((e.getSource() == acteurTable) || (e.getSource() == acteurTable.getTableHeader())) {
            ActorAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setActeur();
        }
        if ((e.getSource() == acteurSystemeTable) || (e.getSource() == acteurSystemeTable.getTableHeader())) {
            ActorSystemAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setActeurSysteme();
        }
        if (e.getSource() == iterationArea) {
            PrePostIterExpressionAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setIteration();
        }
        if (e.getSource() == executionArea) {
            PrePostIterExpressionAdaptator.editedFromEnhancedFrame();
            TaskPropertiesEnhancedEditorAdaptator.setPrecondition();
        }
    }

    public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }    
    
    public void setPurposeField(String t) {
        purposeField.setText(t);
    }

    public void setFeedbackField(String feedback) {
        feedbackField.setText(feedback);        
    }
    
    public void setObservationArea(String observation) {
        observationArea.setText(observation);
    }
    
    public void setEnabledModalityGroup() {
        unkModalite.setEnabled(true);
        smModalite.setEnabled(true);
        cogModalite.setEnabled(true);       
    }
    
    public void setDisabledModalityGroup() {
        unkModalite.setEnabled(false);
        smModalite.setEnabled(false);
        cogModalite.setEnabled(false);
    }
    
    public void setEnabledNecessityGroup() {
        this.obligatoireNecessite.setEnabled(true);
        this.optionnelleNecessite.setEnabled(true);
    }
    
    public void setDisabledNecessityGroup() {
        this.obligatoireNecessite.setEnabled(false);
        this.optionnelleNecessite.setEnabled(false);
    }
    
    public void setEnabledFrequenceValue() {
        valeurFrequenceField.setEditable(true);
    }
    
    public void setDisabledFrequenceValue() {
        valeurFrequenceField.setEditable(false);
    }

    public void setFiredEventsField(String firedEvents) {
        this.fireEvents.setText(firedEvents);        
    }
    
    public void setActorList(ArrayList<String[]> plist) {
        refTableModel.setData(plist);
        refTableModel.fireTableDataChanged();
    }
    
    public void setActorSystemList(ArrayList<String[]> plist) {
        refTableModelSys.setData(plist);
        refTableModelSys.fireTableDataChanged();
    }
    
    static class MyActorTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 6645822274038186940L;

        private ArrayList<String[]> myList = new ArrayList<String[]>();
        
        public void setData(ArrayList<String[]> plist) {
            this.myList = plist;
        }
        
        public int getRowCount() {
            return myList.size();
        }

        public int getColumnCount() {            
            return 3;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return myList.get(rowIndex)[columnIndex];
        }

        public String getColumnName(int column) {
            switch(column) {
                case 0 : {
                    return KMADEConstant.ACTOR_NAME_TABLE;
                }
                case 1 : {
                    return KMADEConstant.ACTOR_EXPERIENCE_TABLE;
                }
                case 2 : {
                    return KMADEConstant.ACTOR_COMPETENCE_TABLE;
                }
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
        
    }
    
    static class MyActorSystemeTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 7120094526807221960L;
		private ArrayList<String[]> myList = new ArrayList<String[]>();
        
        public void setData(ArrayList<String[]> plist) {
            this.myList = plist;
        }
        
        public int getRowCount() {
            return myList.size();
        }

        public int getColumnCount() {            
            return 3;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return myList.get(rowIndex)[columnIndex];
        }

        public String getColumnName(int column) {
            switch(column) {
                case 0 : {
                    return KMADEConstant.ACTORSYSTEM_NAME_TABLE;
                }
                case 1 : {
                    return KMADEConstant.ACTORSYSTEM_EXPERIENCE_TABLE;
                }
                case 2 : {
                    return KMADEConstant.ACTORSYSTEM_COMPETENCE_TABLE;
                }
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
        
    }
    
    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.COMPLETE_EDITOR_TITLE_NAME);
        preconditionPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.EXECUTION_CONDITION_TITLE_NAME));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.PRECONDITION_LABEL_VALUE));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.ITERATION_LABEL_VALUE));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.OBJECT_LIST_TITLE_NAME));
        caracteristiquePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.GENERAL_INFORMATION_PANEL_TITLE_NAME));
        nameLabel.setText(KMADEConstant.TASK_NAME_TITLE);       
        dureeLabel.setText(KMADEConstant.TASK_DURATION_TITLE);
        purposeLabel.setText(KMADEConstant.TASK_PURPOSE_TITLE);
        feedbackLabel.setText(KMADEConstant.TASK_FEEDBACK_TITLE);
        numLabel.setText(KMADEConstant.TASK_NUMBER_NAME_TITLE);
        modaliteLabel.setText(KMADEConstant.MODALITY_NAME_TITLE + " :");
        unkModalite.setText(Modalite.getEnumereIntoLocaleModalite("UNK"));
        smModalite.setText(Modalite.getEnumereIntoLocaleModalite("SENS"));
        cogModalite.setText(Modalite.getEnumereIntoLocaleModalite("COGN"));
        executantLabel.setText(KMADEConstant.EXECUTING_NAME_TITLE + " :");
        unkExecutant.setText(Executant.getEnumereIntoLocaleExecutant("UNK"));
        userExecutant.setText(Executant.getEnumereIntoLocaleExecutant("USER"));
        sysExecutant.setText(Executant.getEnumereIntoLocaleExecutant("SYS"));
        intExecutant.setText(Executant.getEnumereIntoLocaleExecutant("NUM"));
        absExecutant.setText(Executant.getEnumereIntoLocaleExecutant("ABS"));
        unkImportance.setText(Importance.getEnumereIntoLocaleImportance("UNK"));
        lowImportance.setText(Importance.getEnumereIntoLocaleImportance("LOW"));
        middleImportance.setText(Importance.getEnumereIntoLocaleImportance("AVERAGE"));
        highImportance.setText(Importance.getEnumereIntoLocaleImportance("HIGH"));
        unkFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("UNK"));
        highFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("HIGH"));
        averageFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("AVERAGE"));
        lowFrequence.setText(Frequence.getEnumereIntoLocaleFrequence("LOW"));
        valeurFrequenceLabel.setText(KMADEConstant.FREQUENCY_VALUE_NAME_TITLE + " :");
        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_OBSERVATION_TITLE));
        ordonnancementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.SCHEDULE_PANEL_TITLE_NAME));
        NecessiteLabel.setText(KMADEConstant.NECESSITE_LABEL_NAME + " :");
        InterruptibiliteLabel.setText(KMADEConstant.INTERRUPTIBLE_LABEL_NAME + " :");
        declenchementLabel.setText(KMADEConstant.DECLENCHEMENT_LABEL_NAME + " :");
        optionnelleNecessite.setText(KMADEConstant.OPTIONAL_NECESSITE_VALUE);
        obligatoireNecessite.setText(KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE);
        interruptibleInterruptibilite.setText(KMADEConstant.INTERRUPTIBLE_VALUE);
        nointerruptibleInterruptibilite.setText(KMADEConstant.NO_INTERRUPTIBLE_VALUE);
        operateurLabel.setText(KMADEConstant.SCHEDULE_LABEL_NAME + " :");
        unkOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("UNK"));
        enaOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("SEQ"));
        concOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("PAR"));
        leafOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("LEAF"));
        choiceOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("ALT"));
        oiOperateur.setText(Decomposition.getEnumereIntoLocaleDecomposition("ENT"));
        effetsdebordPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.EFFETSDEBORD_TITLE_NAME));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.EFFETSDEBORD_LABEL_VALUE));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.EVENT_NAME_TITLE));
        acteurPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.UTILISATEUR_LABEL_NAME));
        acteurSystemePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("activeCaptionBorder"), 2, true), KMADEConstant.MATERIEL_LABEL_NAME));
        myButton.setText(KMADEConstant.GO_BACK_MESSAGE);
    }

    public void setPrecondition(String precondition) {
        this.executionArea.setText(precondition);
    }
    
    public void setEffetsDeBord(String effetsdebord) {
        this.usedObjects.setText(effetsdebord);
    }
    
    public void setIteration(String iteration) {
        this.iterationArea.setText(iteration);
    }
}

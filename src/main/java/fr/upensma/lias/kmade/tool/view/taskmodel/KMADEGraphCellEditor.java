package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;


import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCellEditor;

import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesEditorAdaptator;

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
public class KMADEGraphCellEditor extends DefaultGraphCellEditor implements ActionListener {
    private static final long serialVersionUID = -3486215513037506669L;

    private JPanel myEditorPanel = new JPanel();

    private JComboBox myCategorieEditor = new JComboBox();

    private JCheckBox myFaculEditor = new JCheckBox(KMADEConstant.OPTIONAL_NECESSITE_VALUE);

    private JCheckBox myInterEditor = new JCheckBox(KMADEConstant.INTERRUPTIBLE_VALUE);

    private JTextField myNameEditor = new JTextField(10);

    private JComboBox myDecompoEditor = new JComboBox();

    private JButton valider = new JButton(KMADEConstant.VALID_MESSAGE);
   

    @SuppressWarnings("unused")
	private String[] executantName;

    private ImageIcon[] executantImage;

    public KMADEGraphCellEditor() {
        super();
       JPanel panelExterieur = new JPanel();
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth,BoxLayout.Y_AXIS));

        //executantName = Executant.getNameLocaleExecutant();
        //executantImage = new ImageIcon[executantName.length];
       // String[] executantTempImage = Executant.getImageLocaleExecutant();
       // for (int i = 0; i < executantName.length; i++) {
       //     executantImage[i] = new ImageIcon(KMADEGraphCellEditor.class.getResource(executantTempImage[i]));
       // }

       // JPanel panelExecutant = new JPanel(new BorderLayout());
       // myCategorieEditor = new JComboBox(executantName);
       // myCategorieEditor.addActionListener(this);
       // myCategorieEditor.setRenderer(new ComboBoxRenderer());
       // panelExecutant.add(BorderLayout.WEST, new JLabel(KMADEConstant.EDITOR_EXECUTANT_NAME + " "));
       // panelExecutant.add(BorderLayout.CENTER, myCategorieEditor);
      //  panelSouth.add(panelExecutant);

       // JPanel panelFacInt = new JPanel(new GridLayout(1, 2));
       // myFaculEditor.addActionListener(this);
       // panelFacInt.add(myFaculEditor);
       // myInterEditor.addActionListener(this);
       // panelFacInt.add(myInterEditor);
       // panelSouth.add(panelFacInt);
       // panelSouth.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panelNameEditor = new JPanel(new BorderLayout());
        panelNameEditor.add(BorderLayout.WEST, new JLabel(KMADEConstant.EDITOR_NAME_NAME + " "));
        panelNameEditor.add(BorderLayout.CENTER, myNameEditor);
        myNameEditor.selectAll();
        myNameEditor.addActionListener(this);
        myNameEditor.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { myNameEditor.selectAll();}

            public void focusLost(FocusEvent e) {
                TaskPropertiesEditorAdaptator.setNameInTaskProperties(myNameEditor.getText());
            }
            
        });
      
        panelSouth.add(panelNameEditor);
        panelSouth.add(Box.createRigidArea(new Dimension(0,10)));

        //JPanel panelNameDecompo = new JPanel(new BorderLayout());
       // panelNameDecompo.add(BorderLayout.WEST, new JLabel(KMADEConstant.EDITOR_OPERATOR_NAME + " "));
 
       // myDecompoEditor = new JComboBox(Decomposition.getNameLocaleDecomposition());
       // myDecompoEditor.addActionListener(this);
       // panelNameDecompo.add(BorderLayout.CENTER, myDecompoEditor);

       //panelSouth.add(panelNameDecompo);
       // panelSouth.add(Box.createRigidArea(new Dimension(0,10)));
        
        panelSouth.add(valider);
        valider.addActionListener(this);

        panelExterieur.setBorder(BorderFactory.createLineBorder(myEditorPanel.getBackground(), 10));
        panelExterieur.setLayout(new BorderLayout());
        panelExterieur.add(BorderLayout.CENTER, panelSouth);
        panelExterieur.setOpaque(false);
        myEditorPanel.setLayout(new BorderLayout());
        myEditorPanel.add(BorderLayout.CENTER, panelExterieur);
        myEditorPanel.setOpaque(false);
        myEditorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //myEditorPanel.setBorder(BorderFactory.createTitledBorder(KMADEConstant.EDITOR_MESSAGE_TITLE));
        //myEditorPanel.setBorder(BorderFactory.createTitledBorder("Nom de la t�che"));
        
    }

    public Component getGraphCellEditorComponent(JGraph graph, Object cell, boolean isSelected) {
        super.getGraphCellEditorComponent(graph, cell, isSelected);
        if (cell instanceof KMADEDefaultGraphCell) {
            // Initialisation des valeurs.
            KMADEDefaultGraphCell myCell = (KMADEDefaultGraphCell) cell;
            if (myCell.getExecutant().equals(Executant.SYS)) {
            	myFaculEditor.setEnabled(false);
            	myFaculEditor.setSelected(false);
            } else {
            	myFaculEditor.setEnabled(true);
                myFaculEditor.setSelected(myCell.isFacultatif());            	
            }
            myInterEditor.setSelected(myCell.isInterruptible());
            myNameEditor.setText(myCell.getName());
            
            myNameEditor.selectAll();
            myCategorieEditor.setSelectedItem(Executant.getEnumereIntoLocaleExecutant(myCell.getExecutant().getValue()));
            myDecompoEditor.setSelectedItem(myCell.getDecomposition());
        }
        return myEditorPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myNameEditor || e.getSource() == valider) {
            this.stopCellEditing();
        } else if (e.getSource() == myCategorieEditor) {
            TaskPropertiesEditorAdaptator.setExecutantInTaskProperties((String)myCategorieEditor.getSelectedItem());
        } else if (e.getSource() == myFaculEditor) {
            TaskPropertiesEditorAdaptator.setOptionalInTaskProperties(myFaculEditor.isSelected());
        } else if (e.getSource() == myInterEditor) {
            TaskPropertiesEditorAdaptator.setInterruptibleInTaskProperties(myInterEditor.isSelected());
        } else if (e.getSource() == myDecompoEditor) {
            TaskPropertiesEditorAdaptator.setDecompositionInTaskProperties((String)myDecompoEditor.getSelectedItem());
        }
    }

    public void setEnableFaculEditor(boolean b) {
    	myFaculEditor.setEnabled(b);
    }
    
    public boolean isCellEditable(EventObject arg0) {
        if (arg0 != null) {
            if (((JGraph)arg0.getSource()).getSelectionCount() == 0) {
                return false;
            }
        }
        return true;
    }

    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
        private static final long serialVersionUID = -5771046098633593675L;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            String maValue = (String) value;
            int myInt = Executant.getLocaleExecutantAt(maValue);
            this.setHorizontalAlignment(JLabel.LEFT);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            ImageIcon icon = executantImage[myInt];
            setIcon(icon);
            setText(maValue);
            return this;
        }
    }
}

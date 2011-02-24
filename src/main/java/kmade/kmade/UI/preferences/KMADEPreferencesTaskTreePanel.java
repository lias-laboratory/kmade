package kmade.kmade.UI.preferences;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.PreferencesAdaptator;

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
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEPreferencesTaskTreePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1094298792155216566L;

    private JCheckBox orthogonalityTreeCheckBox;

    private JComboBox heightTreeComboBox;

    private JComboBox orientationtTreeComboBox;

    private JComboBox distanceNodaleComboBox;

    private JComboBox colorSelectTreeComboBox;

    private JLabel heightTreeLabel;

    private JLabel orientationTreeLabel;

    private JLabel distanceNodaleLabel;

    private JLabel colorSelectTreeLabel;

    private JPanel agencementPanel;

    private JPanel optionsPanel;
    
    public KMADEPreferencesTaskTreePanel() {
        initComponents();
        
        this.heightTreeComboBox.addActionListener(new java.awt.event.ActionListener() {
        		public void actionPerformed(java.awt.event.ActionEvent evt) {
        			PreferencesAdaptator.enableApplyButton();
			}
		});
        	heightTreeComboBox.setModel(new DefaultComboBoxModel(new String[] {/*"90","100","110","120",*/"130","140","160","180","200","220"})) ;
        
		this.orientationtTreeComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
        	orientationtTreeComboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4","5","6","7" }));
		this.orientationtTreeComboBox.setEnabled(false);

		this.distanceNodaleComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		//distanceNodaleComboBox.setModel(new DefaultComboBoxModel(new String[] { "10","20","30","40","50","60","70"}));

		
		////////////////modifié /////////////////
		distanceNodaleComboBox.setModel(new DefaultComboBoxModel(new String[] { "60","70", "80", "90", "100", "110", "120","150","200","250","500"}));
		
		
		this.colorSelectTreeComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		colorSelectTreeComboBox.setModel(new DefaultComboBoxModel( new String[] { KMADEConstant.COLOR_BLACK, KMADEConstant.COLOR_RED, KMADEConstant.COLOR_BLUE, KMADEConstant.COLOR_GREEN, KMADEConstant.COLOR_YELLOW})); 

		this.orthogonalityTreeCheckBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});     
    }

    private void initComponents() {
        agencementPanel = new JPanel();
        distanceNodaleLabel = new JLabel();
        colorSelectTreeLabel = new JLabel();
        orientationTreeLabel = new JLabel();
        heightTreeLabel = new JLabel();
        orientationtTreeComboBox = new JComboBox();
        heightTreeComboBox = new JComboBox();
        colorSelectTreeComboBox = new JComboBox();
        distanceNodaleComboBox = new JComboBox();
        optionsPanel = new JPanel();
        orthogonalityTreeCheckBox = new JCheckBox();

        agencementPanel.setBorder(BorderFactory.createTitledBorder(KMADEConstant.TASK_TREE_PANEL_LAYOUT_PANEL_NAME));
        distanceNodaleLabel.setText(KMADEConstant.TASK_TREE_PANEL_LAYOUT_DISTANCE_LABEL + " : ");

        colorSelectTreeLabel.setText(KMADEConstant.TASK_TREE_PANEL_LAYOUT_SELECTION_COLOR_LABEL + " : ");

        orientationTreeLabel.setText(KMADEConstant.TASK_TREE_PANEL_LAYOUT_ORIENTATION_LABEL + " : ");

        heightTreeLabel.setText(KMADEConstant.TASK_TREE_PANEL_LAYOUT_LEVEL_DISTANCE_LABEL + " : ");

        org.jdesktop.layout.GroupLayout agencementPanelLayout = new org.jdesktop.layout.GroupLayout(agencementPanel);
        agencementPanel.setLayout(agencementPanelLayout);
        agencementPanelLayout.setHorizontalGroup(
            agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(agencementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(agencementPanelLayout.createSequentialGroup()
                        .add(orientationTreeLabel)
                        .add(31, 31, 31))
                    .add(colorSelectTreeLabel)
                    .add(distanceNodaleLabel)
                    .add(heightTreeLabel))
                .add(0, 0, 0)
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(orientationtTreeComboBox, 0, 110, Short.MAX_VALUE)
                    .add(heightTreeComboBox, 0, 110, Short.MAX_VALUE)
                    .add(distanceNodaleComboBox, 0, 110, Short.MAX_VALUE)
                    .add(colorSelectTreeComboBox, 0, 110, Short.MAX_VALUE))
                .add(82, 82, 82))
        );
        agencementPanelLayout.setVerticalGroup(
            agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, agencementPanelLayout.createSequentialGroup()
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(orientationTreeLabel)
                    .add(orientationtTreeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(heightTreeLabel)
                    .add(heightTreeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(distanceNodaleLabel)
                    .add(distanceNodaleComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(agencementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(colorSelectTreeLabel)
                    .add(colorSelectTreeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(11, 11, 11))
        );

        optionsPanel.setBorder(BorderFactory.createTitledBorder(KMADEConstant.TASK_TREE_PANEL_OPTIONS_PANEL_NAME));
        orthogonalityTreeCheckBox.setText(KMADEConstant.TASK_TREE_PANEL_OPTIONS_ORTHOGONAL_EDGE_LABEL);
        orthogonalityTreeCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        orthogonalityTreeCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout optionsPanelLayout = new org.jdesktop.layout.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(orthogonalityTreeCheckBox)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionsPanelLayout.createSequentialGroup()
                .add(orthogonalityTreeCheckBox)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(agencementPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, optionsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(agencementPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(optionsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }                     
   
    public void setHauteurArbre(String value) {
		heightTreeComboBox.setSelectedItem(value);
	}

	public void setOrientationArbre(String value) {
		orientationtTreeComboBox.setSelectedItem(value);
	}

	public void setDistanceNodale(String value) {
		distanceNodaleComboBox.setSelectedItem(value);
	}

	public void setCouleurSelectionTache(String value) {
		colorSelectTreeComboBox.setSelectedItem(value);
	}

	public void setOrthogonaliteArbre(String value) {
		orthogonalityTreeCheckBox.setSelected(Boolean.parseBoolean(value));
	}
	
    public JComboBox getHauteurArbre() {
		return heightTreeComboBox;
	}

	public JComboBox getOrientationArbre() {
		return orientationtTreeComboBox;
	}

	public JComboBox getDistanceNodale() {
		return distanceNodaleComboBox;
	}

	public JComboBox getCouleurSelectionTache() {
		return colorSelectTreeComboBox;
	}

	public JCheckBox getOrthogonaliteArbre() {
		return orthogonalityTreeCheckBox;
	}
}

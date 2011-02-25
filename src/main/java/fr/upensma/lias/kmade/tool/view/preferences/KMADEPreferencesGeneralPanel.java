/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.view.preferences;

import java.awt.Dimension;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.PreferencesAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPreferencesGeneralPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = -6364689664262811528L;

    private javax.swing.JButton forceGarbageJButton;

    private javax.swing.JCheckBox splashScreenCheckBox;

    private javax.swing.JCheckBox automaticSaveCheckBox;

    private javax.swing.JCheckBox envoiBugCheckBox;

    private javax.swing.JComboBox languageComboBox;

    private javax.swing.JComboBox timeSaveComboBox;

    private javax.swing.JComboBox zoomComboBox;

    private javax.swing.JLabel languageLabel;

    private javax.swing.JLabel forceGarbageLabel;

    private javax.swing.JLabel zoomLabel;

    private javax.swing.JPanel configurationPanel;

    private javax.swing.JPanel languagePanel;

    private javax.swing.JPanel raccourcisPanel;

    private javax.swing.JTable raccourcisJTable;

    public KMADEPreferencesGeneralPanel() {
	initComponents();

	this.splashScreenCheckBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});

	this.automaticSaveCheckBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});
	this.automaticSaveCheckBox.setEnabled(false);

	this.timeSaveComboBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});
	this.timeSaveComboBox.setModel(new javax.swing.DefaultComboBoxModel(
		new String[] { "1", "5", "15", "30", "45", "60" }));
	this.timeSaveComboBox.setEnabled(false);

	this.zoomComboBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});
	this.zoomComboBox.setModel(new javax.swing.DefaultComboBoxModel(
		new String[] { "0.10", "0.25", "0.50", "0.75", "1" }));
	this.zoomComboBox.setEditable(true);

	this.forceGarbageJButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.forceGarbage();
		    }
		});

	this.envoiBugCheckBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});
	this.envoiBugCheckBox.setEnabled(false);

	this.languageComboBox
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			PreferencesAdaptator.enableApplyButton();
		    }
		});
	this.languageComboBox.setModel(new javax.swing.DefaultComboBoxModel(
		new String[] { KMADEConstant.FRENCH_LANGUAGE_INFO,
			KMADEConstant.ENGLISH_LANGUAGE_INFO }));

	// this.raccourcisJTable
    }

    private void initComponents() {
	configurationPanel = new javax.swing.JPanel();
	envoiBugCheckBox = new javax.swing.JCheckBox();
	splashScreenCheckBox = new javax.swing.JCheckBox();
	automaticSaveCheckBox = new javax.swing.JCheckBox();
	timeSaveComboBox = new javax.swing.JComboBox();
	zoomComboBox = new javax.swing.JComboBox();
	forceGarbageLabel = new javax.swing.JLabel();
	forceGarbageJButton = new javax.swing.JButton();
	forceGarbageJButton.setMinimumSize(new Dimension(100,
		forceGarbageJButton.getMinimumSize().height));
	languagePanel = new javax.swing.JPanel();
	languageLabel = new javax.swing.JLabel();
	zoomLabel = new javax.swing.JLabel();
	languageComboBox = new javax.swing.JComboBox();
	raccourcisPanel = new javax.swing.JPanel();

	configurationPanel
		.setBorder(javax.swing.BorderFactory
			.createTitledBorder(KMADEConstant.GENERAL_PANEL_CONFIGURATION_PANEL_NAME));
	envoiBugCheckBox.setText(KMADEConstant.GENERAL_PANEL_BUG_MESSAGE_LABEL);
	envoiBugCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(
		0, 0, 0, 0));
	envoiBugCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

	splashScreenCheckBox
		.setText(KMADEConstant.GENERAL_PANEL_SPLASH_SREEN_LABEL);
	splashScreenCheckBox.setBorder(javax.swing.BorderFactory
		.createEmptyBorder(0, 0, 0, 0));
	splashScreenCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

	automaticSaveCheckBox
		.setText(KMADEConstant.GENERAL_PANEL_AUTOMATIC_SAVE_LABEL);
	automaticSaveCheckBox.setBorder(javax.swing.BorderFactory
		.createEmptyBorder(0, 0, 0, 0));
	automaticSaveCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

	forceGarbageLabel
		.setText(KMADEConstant.GENERAL_PANEL_GARBAGE_COLLECTOR_LABEL);

	forceGarbageJButton.setText(KMADEConstant.YES_MESSAGE);

	zoomLabel.setText(KMADEConstant.GENERAL_PANEL_ZOOM_LABEL);

	org.jdesktop.layout.GroupLayout configurationPanelLayout = new org.jdesktop.layout.GroupLayout(
		configurationPanel);
	configurationPanel.setLayout(configurationPanelLayout);
	configurationPanelLayout
		.setHorizontalGroup(configurationPanelLayout
			.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING)
			.add(configurationPanelLayout
				.createSequentialGroup()
				.add(configurationPanelLayout
					.createParallelGroup(
						org.jdesktop.layout.GroupLayout.LEADING)
					.add(configurationPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.add(configurationPanelLayout
							.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
							.add(automaticSaveCheckBox)
							.add(forceGarbageLabel)
							.add(zoomLabel))
						.add(9, 9, 9)
						.add(configurationPanelLayout
							.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING,
								false)
							.add(timeSaveComboBox,
								0,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.add(forceGarbageJButton,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.add(zoomComboBox,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)))
					.add(configurationPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.add(splashScreenCheckBox))
					.add(configurationPanelLayout
						.createSequentialGroup()
						.addContainerGap()
						.add(envoiBugCheckBox)))
				.addContainerGap(55, Short.MAX_VALUE)));
	configurationPanelLayout
		.setVerticalGroup(configurationPanelLayout
			.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING)
			.add(configurationPanelLayout
				.createSequentialGroup()
				.add(configurationPanelLayout
					.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE)
					.add(timeSaveComboBox,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
					.add(automaticSaveCheckBox))
				.addPreferredGap(
					org.jdesktop.layout.LayoutStyle.RELATED)
				.add(configurationPanelLayout
					.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE)
					.add(forceGarbageJButton)
					.add(forceGarbageLabel))
				.addPreferredGap(
					org.jdesktop.layout.LayoutStyle.RELATED)
				.add(configurationPanelLayout
					.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE)
					.add(zoomComboBox).add(zoomLabel))
				.addPreferredGap(
					org.jdesktop.layout.LayoutStyle.RELATED)
				.add(envoiBugCheckBox)
				.addPreferredGap(
					org.jdesktop.layout.LayoutStyle.RELATED,
					12, Short.MAX_VALUE)
				.add(splashScreenCheckBox).add(9, 9, 9)));

	languagePanel
		.setBorder(javax.swing.BorderFactory
			.createTitledBorder(KMADEConstant.GENERAL_PANEL_LANGUAGE_PANEL_NAME));
	languageLabel.setText(KMADEConstant.GENERAL_PANEL_LANGUAGE_LABEL);

	org.jdesktop.layout.GroupLayout languagePanelLayout = new org.jdesktop.layout.GroupLayout(
		languagePanel);
	languagePanel.setLayout(languagePanelLayout);
	languagePanelLayout.setHorizontalGroup(languagePanelLayout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(languagePanelLayout
			.createSequentialGroup()
			.addContainerGap()
			.add(languageLabel)
			.addPreferredGap(
				org.jdesktop.layout.LayoutStyle.RELATED)
			.add(languageComboBox,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				98,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
			.addContainerGap(87, Short.MAX_VALUE)));
	languagePanelLayout.setVerticalGroup(languagePanelLayout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(languagePanelLayout
			.createParallelGroup(
				org.jdesktop.layout.GroupLayout.BASELINE)
			.add(languageComboBox,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
			.add(languageLabel)));

	raccourcisPanel
		.setBorder(javax.swing.BorderFactory
			.createTitledBorder(KMADEConstant.GENERAL_PANEL_KEYS_PANEL_NAME));
	org.jdesktop.layout.GroupLayout raccourcisPanelLayout = new org.jdesktop.layout.GroupLayout(
		raccourcisPanel);
	raccourcisPanel.setLayout(raccourcisPanelLayout);
	raccourcisPanelLayout.setHorizontalGroup(raccourcisPanelLayout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(0, 335, Short.MAX_VALUE));
	raccourcisPanelLayout.setVerticalGroup(raccourcisPanelLayout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(0, 33, Short.MAX_VALUE));

	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
		this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(languagePanel,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE)
		.add(configurationPanel,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE)
		.add(raccourcisPanel,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(
		org.jdesktop.layout.GroupLayout.LEADING).add(
		layout.createSequentialGroup()
			.add(configurationPanel,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(
				org.jdesktop.layout.LayoutStyle.RELATED)
			.add(languagePanel,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
			.add(3, 3, 3)
			.add(raccourcisPanel,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE)));
    }

    /* ***************************************** */
    /* Modificateurs des valeurs des composants */
    /* ***************************************** */
    public void setLanguage(String value) {
	languageComboBox.setSelectedItem(value);
    }

    public void setTimeSave(String value) {
	timeSaveComboBox.setSelectedItem(value);
    }

    public void setSplashScreen(String value) {
	if (value.equals("true")) {
	    splashScreenCheckBox.setSelected(true);
	}
    }

    public void setAutomaticSave(String value) {
	if (value.equals("true")) {
	    automaticSaveCheckBox.setSelected(true);
	    timeSaveComboBox.setEnabled(false);
	}
    }

    public void setWheelZoom(String value) {
	zoomComboBox.setSelectedItem(value);
    }

    public void setEnvoiBug(String value) {
	if (value.equals("true")) {
	    envoiBugCheckBox.setSelected(true);
	}
    }

    public void setRaccourcis(String name, String value) {
	int i = 0;
	while ((!(raccourcisJTable.getValueAt(i, 1).equals(name)))
		&& (i <= raccourcisJTable.getRowCount())) {
	    i++;
	}
	if (i < raccourcisJTable.getRowCount()) {
	    raccourcisJTable.setValueAt(value, i, 0);

	}
    }

    public javax.swing.JComboBox getLanguage() {
	return languageComboBox;
    }

    public javax.swing.JComboBox getTimeSave() {
	return timeSaveComboBox;
    }

    public javax.swing.JCheckBox getSplashScreen() {
	return splashScreenCheckBox;
    }

    public javax.swing.JCheckBox getAutomaticSave() {
	return automaticSaveCheckBox;
    }

    public javax.swing.JCheckBox getEnvoiBug() {
	return envoiBugCheckBox;
    }

    public javax.swing.JTable getRaccourcis() {
	return raccourcisJTable;
    }

    public javax.swing.JComboBox getWheelZoom() {
	return zoomComboBox;
    }
}

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
package fr.upensma.lias.kmade.tool.view.preferences;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.viewadaptator.PreferencesAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPreferencesTaskPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -1435128807087103018L;

	private javax.swing.JButton saveModelJButton;

	private javax.swing.JButton loadModelJButton;

	private javax.swing.JCheckBox eventTaskCheckBox;

	private javax.swing.JCheckBox actorTaskCheckBox;

	private javax.swing.JComboBox taskFontComboBox;

	private javax.swing.JComboBox operatorFontComboBox;

	private javax.swing.JComboBox taskFontSizeComboBox;

	private javax.swing.JComboBox operatorFontStyleComboBox;

	private javax.swing.JComboBox operatorFontSizeComboBox;

	private javax.swing.JComboBox taskFontColorComboBox;

	private javax.swing.JComboBox taskFontStyleComboBox;

	private javax.swing.JComboBox taskBackgroundColorComboBox;

	private javax.swing.JComboBox operatorFontColorComboBox;

	private javax.swing.JComboBox modelComboBox;

	private javax.swing.JLabel taskFontLabel;

	private javax.swing.JLabel operatorFontLabel;

	private javax.swing.JLabel taskFontSizeLabel;

	private javax.swing.JLabel operatorFontStyleLabel;

	private javax.swing.JLabel operatorFontSizeLabel;

	private javax.swing.JLabel operatorFontColorLabel;

	private javax.swing.JLabel taskFontColorLabel;

	private javax.swing.JLabel taskFontStyleLabel;

	private javax.swing.JLabel taskBackgroundColorLabel;

	private javax.swing.JLabel modelLabel;

	private javax.swing.JPanel taskNomPanel;

	private javax.swing.JPanel operatorPanel;

	private javax.swing.JPanel modelPanel;

	private javax.swing.JPanel previewPanel;

	private javax.swing.JPanel optionsPanel;

	private javax.swing.JPanel panelEdition;

	public static final ImageIcon ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(
			KMADEPreferencesTaskPanel.class.getResource(KMADEConstant.ABSTRACT_TASK_IMAGE));

	public KMADEPreferencesTaskPanel() {
		initComponents();
		this.taskFontComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		taskFontComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { KMADEConstant.ARIAL_FONT, KMADEConstant.TIMES_FONT, KMADEConstant.TAHOMA_FONT }));

		this.taskFontSizeComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		taskFontSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "8", "10", "12", "13", "14", "15", "16", "17", "18", "19" }));

		this.taskFontColorComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		taskFontColorComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { KMADEConstant.COLOR_BLACK, KMADEConstant.COLOR_WHITE, KMADEConstant.COLOR_BLUE,
						KMADEConstant.COLOR_GREEN, KMADEConstant.COLOR_YELLOW }));

		this.taskFontStyleComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		taskFontStyleComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { KMADEConstant.STYLE_PLAIN,
				KMADEConstant.STYLE_BOLD, KMADEConstant.STYLE_ITALIC, KMADEConstant.STYLE_BOLD_ITALIC }));

		this.taskBackgroundColorComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		taskBackgroundColorComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { KMADEConstant.COLOR_NO_COLOR, /*
																 * KMADEConstant.COLOR_RED,
																 */
						KMADEConstant.COLOR_BLUE, KMADEConstant.COLOR_GREEN, KMADEConstant.COLOR_YELLOW }));

		this.operatorFontComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();

			}
		});
		operatorFontComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { KMADEConstant.ARIAL_FONT, KMADEConstant.TIMES_FONT, KMADEConstant.TAHOMA_FONT }));

		this.operatorFontStyleComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		operatorFontStyleComboBox
				.setModel(new javax.swing.DefaultComboBoxModel(new String[] { KMADEConstant.STYLE_PLAIN,
						KMADEConstant.STYLE_BOLD, KMADEConstant.STYLE_ITALIC, KMADEConstant.STYLE_BOLD_ITALIC }));

		operatorFontSizeComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		operatorFontSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "8", "10", "12", "13", "14", "15", "16", "17", "18", "19" }));

		this.operatorFontColorComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		operatorFontColorComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { KMADEConstant.COLOR_BLACK, /* KMADEConstant.COLOR_RED, */
						KMADEConstant.COLOR_BLUE, KMADEConstant.COLOR_GREEN, KMADEConstant.COLOR_YELLOW }));

		this.modelComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		modelComboBox.setModel(
				new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		this.modelComboBox.setEnabled(false);
		this.saveModelJButton.setEnabled(false);
		this.loadModelJButton.setEnabled(false);

		this.eventTaskCheckBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		this.eventTaskCheckBox.setEnabled(false);

		this.actorTaskCheckBox.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.enableApplyButton();
			}
		});
		this.actorTaskCheckBox.setEnabled(false);
		this.previewPanel.setLayout(new GridBagLayout());
		previewPanel.add(new OverviewPane(), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
	}

	static class OverviewPane extends JPanel {

		private static final long serialVersionUID = -475807695773242287L;

		public Dimension getPreferredSize() {
			JPanel myPanel = new JPanel();

			FontMetrics fname = myPanel.getFontMetrics(KMADEConstant.TASK_NAME_FONT);
			String name = "Nom de la t�che";

			FontMetrics fnum = myPanel.getFontMetrics(KMADEConstant.TASK_NUM_FONT);
			String num = "N:1.1.2.2";
			String iteration = KMADEConstant.VERTEX_STATE_LETTER + KMADEConstant.PREDICAT_ITERATION_VIEW_MESSAGE;
			int maxNumOrIter = Math.max(fnum.stringWidth(num), fnum.stringWidth(iteration));

			int width = 0;
			if (fname.stringWidth(
					name) < (16 + 32 + 4 + 4 * Math.max(num.length(), iteration.length()) + maxNumOrIter + 10)) {
				width = 16 + 32 + 4 + 4 * Math.max(num.length(), iteration.length()) + maxNumOrIter + 10;
			} else {
				width = fname.stringWidth(name) + 10;
			}
			int hauteur = 10 + 32 + fname.getHeight() + 10 + 10 + 15;
			return new Dimension(width, hauteur);
		}

		protected void paintStateCaract(Graphics2D f, int widthString, String numString, String iterString, double num,
				double iter) {
			int V_NUM_ITE_GAP = (int) (32 - num - iter) / 3;
			f.drawString(numString, widthString, 10 + V_NUM_ITE_GAP + (int) num);
			f.drawString(iterString, widthString, 10 + V_NUM_ITE_GAP + (int) num + V_NUM_ITE_GAP + (int) iter);
		}

		public void paint(Graphics gr) {
			Graphics2D f = (Graphics2D) gr;

			f.setColor(Color.BLACK);
			int width = (int) this.getBounds().getWidth() / 2;

			// L'image
			ImageIcon myImage = KMADEPreferencesTaskPanel.ABSTRACT_TASK_IMAGE_ICON;
			myImage.paintIcon(this, gr, width - 32 / 2, 10);

			int currentHeighImage = 32 + 10;

			// Le numéro
			String numero = "N:1.1.2.2";
			f.setFont(KMADEConstant.TASK_NUM_FONT);
			FontMetrics fm = f.getFontMetrics();
			Rectangle2D rnum = fm.getStringBounds(numero, f);

			// L'itération
			String iteration = "I:pr�dicat";

			Rectangle2D riter = fm.getStringBounds(iteration, f);

			this.paintStateCaract(f, width + 32 / 2 + 2, numero, iteration, rnum.getHeight(), riter.getHeight());

			// Le nom
			String name = "Nom de la t�che";
			f.setFont(KMADEConstant.TASK_NAME_FONT);
			f.setColor(KMADEConstant.TASK_NAME_COLOR);
			fm = f.getFontMetrics();
			Rectangle2D rn = fm.getStringBounds(name, f);
			f.drawString(name, (int) (width - (rn.getWidth() / 2)), currentHeighImage + (int) (rn.getHeight()));
			currentHeighImage += (int) (rn.getHeight()) + 10;

			// L'opérateur
			String operateur = Decomposition.getEnumereIntoLocaleDecomposition("SEQ");

			f.setFont(KMADEConstant.TASK_OPERATOR_FONT);
			f.setColor(KMADEConstant.TASK_OPERATOR_COLOR);
			fm = f.getFontMetrics();
			Rectangle2D ro = fm.getStringBounds(operateur, f);
			f.drawString(operateur, (int) (width - (ro.getWidth() / 2)), currentHeighImage + (int) (ro.getHeight()));

			// Le rectange autour de l'opérateur
			f.drawRect((int) (width - (ro.getWidth() / 2)) - 2, currentHeighImage - 2, (int) ro.getWidth() + 2 * 2,
					(int) ro.getHeight() + 3 * 2);
		}
	}

	private void initComponents() {
		panelEdition = new javax.swing.JPanel();
		taskNomPanel = new javax.swing.JPanel();
		taskFontLabel = new javax.swing.JLabel();
		taskFontComboBox = new javax.swing.JComboBox();
		taskFontStyleLabel = new javax.swing.JLabel();
		taskFontStyleComboBox = new javax.swing.JComboBox();
		taskFontSizeLabel = new javax.swing.JLabel();
		taskFontColorLabel = new javax.swing.JLabel();
		taskFontSizeComboBox = new javax.swing.JComboBox();
		taskFontColorComboBox = new javax.swing.JComboBox();
		taskBackgroundColorLabel = new javax.swing.JLabel();
		taskBackgroundColorComboBox = new javax.swing.JComboBox();
		operatorPanel = new javax.swing.JPanel();
		operatorFontLabel = new javax.swing.JLabel();
		operatorFontComboBox = new javax.swing.JComboBox();
		operatorFontStyleLabel = new javax.swing.JLabel();
		operatorFontStyleComboBox = new javax.swing.JComboBox();
		operatorFontSizeLabel = new javax.swing.JLabel();
		operatorFontColorLabel = new javax.swing.JLabel();
		operatorFontSizeComboBox = new javax.swing.JComboBox();
		operatorFontColorComboBox = new javax.swing.JComboBox();
		modelPanel = new javax.swing.JPanel();
		modelLabel = new javax.swing.JLabel();
		modelComboBox = new javax.swing.JComboBox();
		saveModelJButton = new javax.swing.JButton();
		loadModelJButton = new javax.swing.JButton();
		previewPanel = new javax.swing.JPanel();
		optionsPanel = new javax.swing.JPanel();
		actorTaskCheckBox = new javax.swing.JCheckBox();
		eventTaskCheckBox = new javax.swing.JCheckBox();

		taskNomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_PANEL_NAME_PANEL_NAME));
		taskFontLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_NAME_LABEL);

		taskFontStyleLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_STYLE_LABEL);

		taskFontSizeLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_SIZE_LABEL);

		taskFontColorLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_COLOR_LABEL);

		taskBackgroundColorLabel.setText(KMADEConstant.TASK_PANEL_NAME_BACKGROUND_COLOR_LABEL);

		org.jdesktop.layout.GroupLayout taskNomPanelLayout = new org.jdesktop.layout.GroupLayout(taskNomPanel);
		taskNomPanel.setLayout(taskNomPanelLayout);
		taskNomPanelLayout.setHorizontalGroup(taskNomPanelLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(taskNomPanelLayout.createSequentialGroup().addContainerGap().add(taskNomPanelLayout
						.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(taskNomPanelLayout.createSequentialGroup()
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(taskFontLabel).add(taskFontStyleLabel))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(taskFontStyleComboBox, 0, 119, Short.MAX_VALUE)
										.add(taskFontComboBox, 0, 119, Short.MAX_VALUE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(taskFontColorLabel).add(taskFontSizeLabel))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(taskFontColorComboBox, 0, 123, Short.MAX_VALUE)
										.add(taskFontSizeComboBox, 0, 123, Short.MAX_VALUE))
								.addContainerGap())
						.add(taskNomPanelLayout.createSequentialGroup().add(taskBackgroundColorLabel)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskBackgroundColorComboBox, 0, 192, Short.MAX_VALUE).addContainerGap()))));
		taskNomPanelLayout.setVerticalGroup(taskNomPanelLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(taskNomPanelLayout.createSequentialGroup().add(taskNomPanelLayout
						.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(taskNomPanelLayout.createSequentialGroup()
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
										.add(taskFontLabel).add(taskFontComboBox,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
										.add(taskFontStyleLabel).add(taskFontStyleComboBox,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
						.add(taskNomPanelLayout.createSequentialGroup()
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
										.add(taskFontSizeLabel).add(taskFontSizeComboBox,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
										.add(taskFontColorLabel).add(taskFontColorComboBox,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
						.add(16, 16, 16)
						.add(taskNomPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
								.add(taskBackgroundColorLabel).add(taskBackgroundColorComboBox,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		operatorPanel.setBorder(
				javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_PANEL_DECOMPOSITION_PANEL_NAME));
		operatorFontLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_NAME_LABEL);

		operatorFontStyleLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_STYLE_LABEL);

		operatorFontSizeLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_SIZE_LABEL);

		operatorFontColorLabel.setText(KMADEConstant.TASK_PANEL_NAME_FONT_COLOR_LABEL);

		org.jdesktop.layout.GroupLayout operatorPanelLayout = new org.jdesktop.layout.GroupLayout(operatorPanel);
		operatorPanel.setLayout(operatorPanelLayout);
		operatorPanelLayout
				.setHorizontalGroup(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(operatorPanelLayout.createSequentialGroup().addContainerGap()
								.add(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(operatorFontLabel).add(operatorFontStyleLabel))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(operatorFontStyleComboBox, 0, 112, Short.MAX_VALUE)
										.add(operatorFontComboBox, 0, 112, Short.MAX_VALUE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(operatorFontColorLabel).add(operatorFontSizeLabel))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(operatorFontColorComboBox, 0, 128, Short.MAX_VALUE)
										.add(operatorFontSizeComboBox, 0, 128, Short.MAX_VALUE))
								.addContainerGap()));
		operatorPanelLayout
				.setVerticalGroup(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(operatorPanelLayout.createSequentialGroup().add(operatorPanelLayout
								.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(operatorFontLabel)
								.add(operatorFontComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(operatorFontSizeLabel).add(operatorFontSizeComboBox,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
										.add(operatorFontStyleLabel)
										.add(operatorFontStyleComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(operatorFontColorLabel).add(operatorFontColorComboBox,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		modelPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_PANEL_MODEL_PANEL_NAME));
		modelLabel.setText(KMADEConstant.TASK_PANEL_MODEL_CHOICE_LABEL);

		saveModelJButton.setText(KMADEConstant.TASK_PANEL_MODEL_SAVE_BUTTON_LABEL);

		loadModelJButton.setText(KMADEConstant.TASK_PANEL_MODEL_LOAD_BUTTON_LABEL);

		org.jdesktop.layout.GroupLayout modelPanelLayout = new org.jdesktop.layout.GroupLayout(modelPanel);
		modelPanel.setLayout(modelPanelLayout);
		modelPanelLayout
				.setHorizontalGroup(modelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(modelPanelLayout.createSequentialGroup().addContainerGap().add(modelLabel)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(modelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(modelPanelLayout.createSequentialGroup().add(saveModelJButton).add(2, 2, 2)
												.add(loadModelJButton))
										.add(modelComboBox, 0, 305, Short.MAX_VALUE))
								.addContainerGap()));
		modelPanelLayout.setVerticalGroup(modelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(modelPanelLayout.createSequentialGroup()
						.add(modelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
								.add(modelLabel).add(modelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
						.add(modelPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
								.add(loadModelJButton).add(saveModelJButton))
						.addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		previewPanel
				.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_PANEL_PREVIEW_PANEL_NAME));
		org.jdesktop.layout.GroupLayout previewPanelLayout = new org.jdesktop.layout.GroupLayout(previewPanel);
		previewPanel.setLayout(previewPanelLayout);
		previewPanelLayout.setHorizontalGroup(previewPanelLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(0, 101, Short.MAX_VALUE));
		previewPanelLayout.setVerticalGroup(previewPanelLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(0, 327, Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout panelEditionLayout = new org.jdesktop.layout.GroupLayout(panelEdition);
		panelEdition.setLayout(panelEditionLayout);
		panelEditionLayout
				.setHorizontalGroup(panelEditionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(panelEditionLayout.createSequentialGroup()
								.add(panelEditionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
										.add(modelPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(panelEditionLayout.createSequentialGroup().add(2, 2, 2).add(operatorPanel,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.add(org.jdesktop.layout.GroupLayout.LEADING, taskNomPanel,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(previewPanel,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelEditionLayout
				.setVerticalGroup(panelEditionLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(previewPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(panelEditionLayout.createSequentialGroup()
								.add(taskNomPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(modelPanel,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));

		optionsPanel
				.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.TASK_PANEL_OPTIONS_PANEL_NAME));
		actorTaskCheckBox.setText(KMADEConstant.TASK_PANEL_OPTIONS_ACTOR_TASK_LABEL);
		actorTaskCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		actorTaskCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

		eventTaskCheckBox.setText(KMADEConstant.TASK_PANEL_OPTIONS_TRIGGER_EVENT_LABEL);
		eventTaskCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
		eventTaskCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

		org.jdesktop.layout.GroupLayout optionsPanelLayout = new org.jdesktop.layout.GroupLayout(optionsPanel);
		optionsPanel.setLayout(optionsPanelLayout);
		optionsPanelLayout
				.setHorizontalGroup(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(optionsPanelLayout.createSequentialGroup().addContainerGap()
								.add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
										.add(actorTaskCheckBox).add(eventTaskCheckBox))
								.addContainerGap(165, Short.MAX_VALUE)));
		optionsPanelLayout.setVerticalGroup(
				optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(optionsPanelLayout
						.createSequentialGroup().add(actorTaskCheckBox).add(11, 11, 11).add(eventTaskCheckBox)));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(panelEdition, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.add(optionsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout.createSequentialGroup().add(panelEdition, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(optionsPanel,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));
	}

	/* ***************************************** */
	/* modificateurs des valeurs des composants */
	/* ***************************************** */
	public void setFontTask(String value) {
		if (value == KMADEConstant.FRENCH_LANGUAGE_INFO) {
			taskFontComboBox.setSelectedItem(KMADEConstant.FRENCH_LANGUAGE_INFO);
		} else if (value == KMADEConstant.ENGLISH_LANGUAGE_INFO) {
			taskFontComboBox.setSelectedItem(KMADEConstant.ENGLISH_LANGUAGE_INFO);
		}

	}

	public void setFontSizeTask(String value) {
		taskFontSizeComboBox.setSelectedItem(value);
	}

	public void setColorTask(String value) {
		taskFontColorComboBox.setSelectedItem(value);
	}

	public void setFontStyleTask(String value) {
		taskFontStyleComboBox.setSelectedItem(value);
	}

	public void setBackgroundColorTask(String value) {
		taskBackgroundColorComboBox.setSelectedItem(value);
	}

	public void setColorOperatorTask(String value) {
		operatorFontColorComboBox.setSelectedItem(value);
	}

	public void setModelTask(String value) {
		modelComboBox.setSelectedItem(value);
	}

	public void setFontOperatorTask(String value) {
		operatorFontComboBox.setSelectedItem(value);
	}

	public void setFontStyleOperatorTask(String value) {
		operatorFontStyleComboBox.setSelectedItem(value);
	}

	public void setFontSizeOperatorTask(String value) {
		operatorFontSizeComboBox.setSelectedItem(value);
	}

	public void setEventTask(String value) {
		if (value.equals("true")) {
			eventTaskCheckBox.setSelected(true);
		} else
			eventTaskCheckBox.setSelected(false);
	}

	public void setActorTask(String value) {
		if (value.equals("true")) {
			actorTaskCheckBox.setSelected(true);
		} else
			actorTaskCheckBox.setSelected(false);
	}

	public javax.swing.JComboBox getFontTask() {
		return taskFontComboBox;
	}

	public javax.swing.JComboBox getFontSizeTask() {
		return taskFontSizeComboBox;
	}

	public javax.swing.JComboBox getColorTask() {
		return taskFontColorComboBox;
	}

	public javax.swing.JComboBox getFontStyleTask() {
		return taskFontStyleComboBox;
	}

	public javax.swing.JComboBox getBackgroundColorTask() {
		return taskBackgroundColorComboBox;
	}

	public javax.swing.JComboBox getColorOperatorTask() {
		return operatorFontColorComboBox;
	}

	public javax.swing.JComboBox getModelTask() {
		return modelComboBox;
	}

	public javax.swing.JComboBox getFontOperatorTask() {
		return operatorFontComboBox;
	}

	public javax.swing.JComboBox getFontStyleOperatorTask() {
		return operatorFontStyleComboBox;
	}

	public javax.swing.JComboBox getFontSizeOperatorTask() {
		return operatorFontSizeComboBox;
	}

	public javax.swing.JCheckBox getEventTask() {
		return eventTaskCheckBox;
	}

	public javax.swing.JCheckBox getBackgroundTask() {
		return eventTaskCheckBox;
	}

	public javax.swing.JCheckBox getActorTask() {
		return eventTaskCheckBox;
	}
}

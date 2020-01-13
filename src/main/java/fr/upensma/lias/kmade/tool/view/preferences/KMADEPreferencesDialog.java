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

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.l2fprod.common.swing.JButtonBar;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.PreferencesAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPreferencesDialog extends JDialog {
	private static final long serialVersionUID = -4761044457326457755L;

	private JPanel panelGeneral;

	private JPanel panelTask;

	private JPanel panelObject;

	private CardLayout myCard;

	private JPanel panelCenter;

	private JButton apply;

	private JButton cancel;

	private JButton ok;

	private AbstractAction generalAction;

	private AbstractAction taskAction;

	private AbstractAction objectAction;

	private static final KMADEPreferencesTaskTreePanel treePanel = new KMADEPreferencesTaskTreePanel();

	private static final KMADEPreferencesGeneralPanel generalPanel = new KMADEPreferencesGeneralPanel();

	private static final KMADEPreferencesTaskPanel taskPanel = new KMADEPreferencesTaskPanel();

	public KMADEPreferencesDialog(Frame owner) {
		super(owner, KMADEConstant.PREFERENCES_TITLE_NAME, true);
		JButtonBar toolbar = new JButtonBar(JButtonBar.VERTICAL);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PreferencesAdaptator.closePreferencesDialog();
			}
		});

		ButtonGroup myButtonGroup = new ButtonGroup();
		generalAction = new AbstractAction(KMADEConstant.GENERAL_TABBEDPANE_TITLE_NAME) {
			private static final long serialVersionUID = 6205222957824228463L;

			public void actionPerformed(ActionEvent e) {
				myCard.show(panelCenter, "general");
			}
		};
		JToggleButton button = new JToggleButton(generalAction);
		button.setSelected(true);
		myButtonGroup.add(button);
		toolbar.add(button);
		taskAction = new AbstractAction(KMADEConstant.TASK_MODEL_EDITOR_TABBED_NAME) {
			private static final long serialVersionUID = 6893898398604537997L;

			public void actionPerformed(ActionEvent e) {
				myCard.show(panelCenter, "task");

			}
		};
		button = new JToggleButton(taskAction);
		myButtonGroup.add(button);
		toolbar.add(button);

		objectAction = new AbstractAction(KMADEConstant.TREE_EDITOR_TABBED_NAME) {
			private static final long serialVersionUID = -296596008620192136L;

			public void actionPerformed(ActionEvent e) {
				myCard.show(panelCenter, "object");
			}
		};
		button = new JToggleButton(objectAction);
		myButtonGroup.add(button);
		toolbar.add(button);

		panelGeneral = makePanel(KMADEConstant.GENERAL_TABBED_NAME);
		panelTask = makePanel(KMADEConstant.TASK_MODEL_EDITOR_TABBED_LONG_NAME);
		panelObject = makePanel(KMADEConstant.TREE_EDITOR_TABBED_LONG_NAME);
		panelTask.add("Center", taskPanel);
		panelGeneral.add("Center", generalPanel);
		panelObject.add("Center", treePanel);

		JPanel panelSouth = new JPanel();
		ok = new JButton(KMADEConstant.VALID_MESSAGE);
		cancel = new JButton(KMADEConstant.CANCEL_MESSAGE);
		apply = new JButton(KMADEConstant.APPLY_MESSAGE);
		panelSouth.add(ok);
		panelSouth.add(cancel);
		panelSouth.add(apply);

		this.ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.savePreferencesOkAction();
			}
		});

		this.cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.cancelPreferences();
			}
		});

		this.apply.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PreferencesAdaptator.savePreferencesApplyAction();
			}
		});

		myCard = new CardLayout();

		panelCenter = new JPanel(myCard);
		panelCenter.add(panelGeneral, "general");
		panelCenter.add(panelTask, "task");
		panelCenter.add(panelObject, "object");

		this.getContentPane().add(BorderLayout.WEST, toolbar);
		this.getContentPane().add(BorderLayout.CENTER, panelCenter);
		this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
		Dimension dim = new Dimension(600, 500);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		Insets insets = tk.getScreenInsets(getGraphicsConfiguration());
		int width = (int) (d.getWidth() - insets.left - insets.right);
		int height = (int) (d.getHeight() - insets.top - insets.bottom);
		Dimension screenDim = new Dimension(width, height);

		if (screenDim.height < dim.height) {
			this.setSize(screenDim);
			this.setLocation(insets.left, insets.top);
		} else {
			this.setSize(new Dimension(600, 500));
			KMADEToolUtilities.setCenteredInScreen(this);
		}
	}

	private JPanel makePanel(String title) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel top = new JLabel(title);
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.setFont(top.getFont().deriveFont(Font.BOLD));
		top.setOpaque(true);
		top.setBackground(panel.getBackground().brighter());
		panel.add("North", top);
		panel.setPreferredSize(new Dimension(450, 420));
		panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		return panel;
	}

	public void setDisabledApplyButton() {
		apply.setEnabled(false);
	}

	public void setEnabledApplyButton() {
		apply.setEnabled(true);
	}

	public JButton getApplyJButton() {
		return apply;
	}

	public JButton getOkJButton() {
		return ok;
	}

	public JButton getCancelJButton() {
		return cancel;
	}

	public KMADEPreferencesTaskTreePanel getTree() {
		return treePanel;
	}

	public KMADEPreferencesGeneralPanel getGeneral() {
		return generalPanel;
	}

	public KMADEPreferencesTaskPanel getTask() {
		return taskPanel;
	}

	public KMADEPreferencesDialog getPreferencesDialog() {
		return this;
	}

	public void notifLocalisationModification() {
		this.setTitle(KMADEConstant.PREFERENCES_TITLE_NAME);
		generalAction.putValue(AbstractAction.NAME, KMADEConstant.GENERAL_TABBEDPANE_TITLE_NAME);
		taskAction.putValue(AbstractAction.NAME, KMADEConstant.TASK_MODEL_EDITOR_TABBED_NAME);
		objectAction.putValue(AbstractAction.NAME, KMADEConstant.TREE_EDITOR_TABBED_NAME);
	}
}

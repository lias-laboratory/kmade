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
package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.EffetsDeBordConstants;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.EffetsDeBordAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEEffetsDeBordExpressionPanel extends JPanel implements LanguageFactory {
	private static final long serialVersionUID = 2960070943938168776L;

	private JButton add;

	private JButton assignment;

	private JButton create;

	private JPanel functionjPanel1;

	private JPanel jPanel2;

	private JPanel literalPanel1;

	private JButton minusAssignment;

	private JPanel operatorPanel1;

	private JButton plusAssignment;

	private JButton remove;

	private JButton replace;

	private JButton sequence;

	private JButton set1;

	private JButton set2;

	private JButton ubool1;

	private JButton uint1;

	private JButton ustr1;

	private JTextField vcst1;

	private JComboBox vcstchoice1;

	private JButton getValue2;

	private JButton getValue1;

	private JButton plusComputing;

	private JButton minusComputing;

	public KMADEEffetsDeBordExpressionPanel() {
		this.initComponents();

		// Diff???rents abonnements.
		this.add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.ADD]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.ADD].length() - 1) + "("
						+ "  " + ")");
			}
		});
		this.assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.ASSIGNMENT]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.ASSIGNMENT].length() - 1));
			}
		});
		this.create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.CREATE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.CREATE].length() - 1) + "("
						+ "  ,  " + ")");
			}
		});
		this.minusAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(
						EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.MINUS_ASSIGNMENT].substring(1,
								EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.MINUS_ASSIGNMENT].length() - 1));
			}
		});
		this.plusAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(
						EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.PLUS_ASSIGNMENT].substring(1,
								EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.PLUS_ASSIGNMENT].length() - 1));
			}
		});
		this.remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.REMOVE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.REMOVE].length() - 1) + "("
						+ "  " + ")");
			}
		});
		this.replace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.REPLACE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.REPLACE].length() - 1)
						+ "(" + "  ,  " + ")");
			}
		});
		this.set1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SET]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SET].length() - 1) + "("
						+ "  " + ")");
			}
		});
		this.set2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SET]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SET].length() - 1) + "("
						+ "  ,  " + ")");
			}
		});
		this.ubool1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(
						EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_BOOLEAN].substring(1,
								EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_BOOLEAN].length() - 1));
			}
		});
		this.uint1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator
						.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_NUMBER].substring(1,
								EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_NUMBER].length() - 1));
			}
		});
		this.ustr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator
						.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_STRING].substring(1,
								EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.UNKNOWN_STRING].length() - 1));
			}
		});
		this.vcst1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentValue = ((String) vcstchoice1.getSelectedItem());
				EffetsDeBordAdaptator.addNewLiteral(TypeStructure.getLocaleTypeStructureIntoEnumere(currentValue),
						vcst1.getText());
			}
		});

		this.sequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SEQUENCE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.SEQUENCE].length() - 1));
			}
		});

		vcstchoice1.setModel(new DefaultComboBoxModel(TypeStructure.getNameLocaleTypeStructureWithoutInterval()));

		this.getValue2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.GETVALUE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.GETVALUE].length() - 1)
						+ "(" + "  ,  " + ")");
			}
		});

		this.getValue1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EffetsDeBordAdaptator.setNewToken(EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.GETVALUE]
						.substring(1, EffetsDeBordConstants.tokenImage[EffetsDeBordConstants.GETVALUE].length() - 1)
						+ "(" + "  " + ")");
			}
		});
	}

	private void initComponents() {
		operatorPanel1 = new JPanel();
		assignment = new JButton();
		minusAssignment = new JButton();
		plusAssignment = new JButton();
		sequence = new JButton();
		functionjPanel1 = new JPanel();
		remove = new JButton();
		add = new JButton();
		set2 = new JButton();
		create = new JButton();
		set1 = new JButton();
		replace = new JButton();
		literalPanel1 = new JPanel();
		ubool1 = new JButton();
		getValue2 = new JButton();
		getValue1 = new JButton();
		ustr1 = new JButton();
		uint1 = new JButton();
		jPanel2 = new JPanel();
		vcst1 = new JTextField();
		vcstchoice1 = new JComboBox();
		plusComputing = new JButton();
		minusComputing = new JButton();

		operatorPanel1.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

		operatorPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.OPERATORS_TITLE_MESSAGE));
		assignment.setText(":=");
		operatorPanel1.add(assignment);

		minusAssignment.setText("-=");
		operatorPanel1.add(minusAssignment);

		plusAssignment.setText("+=");
		operatorPanel1.add(plusAssignment);

		sequence.setText(";");
		operatorPanel1.add(sequence);

		plusComputing.setText("+");
		operatorPanel1.add(plusComputing);

		minusComputing.setText("-");
		operatorPanel1.add(minusComputing);

		functionjPanel1.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

		functionjPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.FUNCTIONS_TITLE_MESSAGE));
		add.setText("add(...)");
		functionjPanel1.add(add);

		create.setText("create(...,...)");
		functionjPanel1.add(create);

		getValue2.setText("getValue(...,...)");
		functionjPanel1.add(getValue2);

		getValue1.setText("getValue(...)");
		functionjPanel1.add(getValue1);

		remove.setText("remove(...)");
		functionjPanel1.add(remove);

		replace.setText("replace(...,...)");
		functionjPanel1.add(replace);

		set2.setText("set(...,...)");
		functionjPanel1.add(set2);

		set1.setText("set(...)");
		functionjPanel1.add(set1);

		literalPanel1.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

		literalPanel1
				.setBorder(javax.swing.BorderFactory.createTitledBorder(null, KMADEConstant.LITTERAUX_TITLE_MESSAGE));
		vcst1.setMinimumSize(new java.awt.Dimension(30, 19));

		vcstchoice1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Integer", "Boolean", "String" }));

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(jPanel2Layout.createSequentialGroup().add(vcstchoice1, 0, 90, Short.MAX_VALUE)
						.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
						.add(vcst1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(vcst1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
				.add(vcstchoice1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE));
		literalPanel1.add(jPanel2);

		ubool1.setText("?BOOL");
		literalPanel1.add(ubool1);

		uint1.setText("?NUM");
		literalPanel1.add(uint1);

		ustr1.setText("?STR");
		literalPanel1.add(ustr1);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(layout.createSequentialGroup().addContainerGap()
								.add(literalPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(operatorPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
								.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
								.add(functionjPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout.createSequentialGroup().addContainerGap()
						.add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
								.add(org.jdesktop.layout.GroupLayout.TRAILING, functionjPanel1,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.add(operatorPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.add(literalPanel1, 0, 0, Short.MAX_VALUE))
						.addContainerGap()));
	}

	public void notifLocalisationModification() {

	}
}

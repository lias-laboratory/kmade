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
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.PreconditionConstants;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.PreconditionAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPreconditionExpressionPanel extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = -2655878307740956908L;

    private JButton and;

    private JButton diff;

    private JButton equal;

    private JPanel functionPanel;

    private JButton in;

    private JButton inf;

    private JButton infEqual;

    private JButton isEmpty;

    private JButton isExistAt;

    private JButton isExist;

    private JPanel jPanel1;

    private JButton length1;

    private JButton length2;

    private JPanel literalPanel;

    private JButton not;

    private JPanel operatorPanel;

    private JButton or;

    private JButton pf;

    private JButton po;

    private JButton sup;

    private JButton supEqual;

    private JButton ubool;

    private JButton uint;

    private JButton ustr;

    private JTextField vcst;

    private JComboBox vcstchoice;

    private JPanel jPanel2;

    private JButton getValue2;

    private JButton getValue1;

    private JPanel panelGetValue;

    private JPanel panelExist;

    public KMADEPreconditionExpressionPanel() {
	this.initComponents();
	this.and.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.AND]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.AND]
						.length() - 1));
	    }
	});
	this.diff.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOT_EQUAL]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.NOT_EQUAL]
						.length() - 1));
	    }
	});
	this.equal.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EQUAL]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.EQUAL]
						.length() - 1));
	    }
	});
	this.in.setEnabled(false);
	this.inf.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.INF]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.INF]
						.length() - 1));
	    }
	});
	this.infEqual.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.INF_EQUAL]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.INF_EQUAL]
						.length() - 1));
	    }
	});
	this.isEmpty.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EMPTY]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.EMPTY]
					.length() - 1)
			+ "(" + "  " + ")");
	    }
	});
	this.isExist.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EXIST]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.EXIST]
					.length() - 1)
			+ "(" + "  ,  " + ")");
	    }
	});
	this.isExistAt.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EXISTAT]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.EXISTAT]
					.length() - 1)
			+ "(" + "  ,  " + ")");
	    }
	});
	this.length1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
					.length() - 1)
			+ "(" + "  " + ")");
	    }
	});
	this.length2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
					.length() - 1)
			+ "(" + "  ,  " + ")");
	    }
	});
	this.not.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOT]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.NOT]
					.length() - 1)
			+ "(" + "  " + ")");
	    }
	});
	this.or.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.OR]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.OR]
						.length() - 1));
	    }
	});
	this.pf.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.PARF]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.PARF]
						.length() - 1));
	    }
	});
	this.po.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.PARO]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.PARO]
						.length() - 1));
	    }
	});
	this.sup.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.SUP]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.SUP]
						.length() - 1));
	    }
	});
	this.supEqual.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.SUP_EQUAL]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.SUP_EQUAL]
						.length() - 1));
	    }
	});
	this.ubool.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_BOOLEAN]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_BOOLEAN]
						.length() - 1));
	    }
	});
	this.uint.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_NUMBER]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_NUMBER]
						.length() - 1));
	    }
	});
	this.ustr.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator
			.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_STRING]
				.substring(
					1,
					PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_STRING]
						.length() - 1));
	    }
	});
	this.vcst.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String currentValue = ((String) vcstchoice.getSelectedItem());
		PreconditionAdaptator.addNewLiteral(TypeStructure
			.getLocaleTypeStructureIntoEnumere(currentValue), vcst
			.getText());
	    }
	});

	vcstchoice.setModel(new DefaultComboBoxModel(TypeStructure
		.getNameLocaleTypeStructureWithoutInterval()));

	this.getValue2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
					.length() - 1)
			+ "(" + "  ,  " + ")");
	    }
	});

	this.getValue1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		PreconditionAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
			.substring(
				1,
				PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
					.length() - 1)
			+ "(" + "  " + ")");
	    }
	});
    }

    private void initComponents() {
	literalPanel = new JPanel();
	jPanel1 = new JPanel();
	vcst = new JTextField();
	vcstchoice = new JComboBox();
	ubool = new JButton();
	ustr = new JButton();
	uint = new JButton();
	functionPanel = new JPanel();
	isExistAt = new JButton();
	isExist = new JButton();
	jPanel2 = new JPanel();
	length2 = new JButton();
	length1 = new JButton();
	isEmpty = new JButton();
	getValue2 = new JButton();
	getValue1 = new JButton();
	operatorPanel = new JPanel();
	inf = new JButton();
	diff = new JButton();
	sup = new JButton();
	infEqual = new JButton();
	equal = new JButton();
	supEqual = new JButton();
	po = new JButton();
	in = new JButton();
	pf = new JButton();
	and = new JButton();
	or = new JButton();
	not = new JButton();
	panelGetValue = new JPanel();
	panelExist = new JPanel();

	literalPanel.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

	literalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
		null, KMADEConstant.LITTERAUX_TITLE_MESSAGE));
	vcst.setMinimumSize(new java.awt.Dimension(30, 19));

	vcstchoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
		"Integer", "Boolean", "String" }));

	org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
		jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
		org.jdesktop.layout.GroupLayout.LEADING).add(
		jPanel1Layout
			.createSequentialGroup()
			.add(vcstchoice, 0, 85, Short.MAX_VALUE)
			.addPreferredGap(
				org.jdesktop.layout.LayoutStyle.RELATED)
			.add(vcst,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				44, Short.MAX_VALUE)));
	jPanel1Layout.setVerticalGroup(jPanel1Layout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(vcst, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 32,
			Short.MAX_VALUE)
		.add(vcstchoice, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
			32, Short.MAX_VALUE));
	literalPanel.add(jPanel1);

	ubool.setText("?BOOL");
	literalPanel.add(ubool);

	uint.setText("?NUM");
	literalPanel.add(uint);

	ustr.setText("?STR");
	literalPanel.add(ustr);

	functionPanel.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

	functionPanel.setBorder(javax.swing.BorderFactory
		.createTitledBorder(KMADEConstant.FUNCTIONS_TITLE_MESSAGE));
	jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

	length2.setText("card(...,...)");
	jPanel2.add(length2);

	length1.setText("card(...)");
	jPanel2.add(length1);

	functionPanel.add(jPanel2);

	panelGetValue.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

	getValue2.setText("getValue(...,...)");
	panelGetValue.add(getValue2);

	getValue1.setText("getValue(...)");
	panelGetValue.add(getValue1);

	functionPanel.add(panelGetValue);

	panelExist.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

	isExistAt.setText("isExistAt(...,...)");
	panelExist.add(isExistAt);

	isExist.setText("isExist(...,...)");
	panelExist.add(isExist);

	functionPanel.add(panelExist);

	isEmpty.setText("isEmpty(...)");
	functionPanel.add(isEmpty);

	operatorPanel.setLayout(new java.awt.GridLayout(4, 3, 10, 10));

	operatorPanel.setBorder(javax.swing.BorderFactory
		.createTitledBorder(KMADEConstant.OPERATORS_TITLE_MESSAGE));
	inf.setText("<");
	operatorPanel.add(inf);

	diff.setText("!=");
	operatorPanel.add(diff);

	sup.setText(">");
	operatorPanel.add(sup);

	infEqual.setText("<=");
	operatorPanel.add(infEqual);

	equal.setText("==");
	operatorPanel.add(equal);

	supEqual.setText(">=");
	operatorPanel.add(supEqual);

	po.setText("(");
	operatorPanel.add(po);

	in.setText("IN");
	operatorPanel.add(in);

	pf.setText(")");
	operatorPanel.add(pf);

	and.setText("AND");
	operatorPanel.add(and);

	or.setText("OR");
	operatorPanel.add(or);

	not.setText("not(...)");
	operatorPanel.add(not);

	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
		this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(
		org.jdesktop.layout.GroupLayout.LEADING).add(
		layout.createSequentialGroup()
			.addContainerGap()
			.add(literalPanel,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				151, Short.MAX_VALUE)
			.addPreferredGap(
				org.jdesktop.layout.LayoutStyle.RELATED)
			.add(operatorPanel,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				224, Short.MAX_VALUE)
			.addPreferredGap(
				org.jdesktop.layout.LayoutStyle.RELATED)
			.add(functionPanel,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE).addContainerGap()));
	layout.setVerticalGroup(layout
		.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
		.add(layout
			.createSequentialGroup()
			.addContainerGap()
			.add(layout
				.createParallelGroup(
					org.jdesktop.layout.GroupLayout.LEADING)
				.add(org.jdesktop.layout.GroupLayout.TRAILING,
					literalPanel,
					org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
					191, Short.MAX_VALUE)
				.add(functionPanel,
					org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
					191, Short.MAX_VALUE)
				.add(operatorPanel,
					org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
					191, Short.MAX_VALUE))
			.addContainerGap()));
    }

    public void notifLocalisationModification() {

    }
}

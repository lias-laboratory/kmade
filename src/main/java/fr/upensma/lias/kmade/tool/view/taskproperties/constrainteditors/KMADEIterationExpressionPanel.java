package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.IterationConstants;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.PreconditionConstants;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.IterationAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.PreconditionAdaptator;


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
public class KMADEIterationExpressionPanel extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = -4541014693978562341L;

    private JButton and;

    private JButton diff;

    private JButton equal;

    private JPanel functionPanel;

    private JButton in;

    private JButton inf;

    private JButton infEqual;

    private JButton isEmpty;

    private JButton isExist;

    private JButton isExistAt;
    
    private JButton while1;
    
    private JPanel cardPanel;

    private JLabel jLabel1;

    private JPanel jPanel1;

    private JPanel iteratorPanel;

    private JTextField jTextField1;

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

    private JButton getValue2;
    
    private JButton getValue1;
    
    private JButton notWhile2;
    
    private JPanel variantPanel;
    
    private JPanel panelGetValue;
    
    private JPanel panelExist;
    
    public KMADEIterationExpressionPanel() {
        this.initComponents();
        
        this.and.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.AND].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.AND].length() - 1));
            }
        });
        this.diff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOT_EQUAL].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.NOT_EQUAL].length() - 1));
            }
        });
        this.equal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EQUAL].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.EQUAL].length() - 1));
            }
        });
        this.in.setEnabled(false);
        this.inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.INF].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.INF].length() - 1));
            }
        });
        this.infEqual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.INF_EQUAL].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.INF_EQUAL].length() - 1));
            }
        });
        this.isEmpty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EMPTY].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.EMPTY].length() - 1) + "(" + "  " + ")");
            }
        });
        this.isExist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EXIST].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.EXIST].length() - 1) + "(" + "  ,  " + ")");
            }
        });
        this.isExistAt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.EXISTAT].substring(1,
                        PreconditionConstants.tokenImage[PreconditionConstants.EXISTAT].length() - 1) + "(" + "  ,  " + ")");
            }
        });        
        this.length1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
                                                .length() - 1)
                                + "(" + "  " + ")");
            }
        });
        this.length2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.NOMBRE]
                                                .length() - 1)
                                + "(" + "  ,  " + ")");
            }
        });
        this.not.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.NOT]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.NOT]
                                                .length() - 1)
                                + "(" + "  " + ")");
            }
        });
        this.or.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.OR]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.OR]
                                                .length() - 1));
            }
        });
        this.pf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.PARF]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.PARF]
                                                .length() - 1));
            }
        });
        this.po.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.PARO]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.PARO]
                                                .length() - 1));
            }
        });
        this.sup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.SUP]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.SUP]
                                                .length() - 1));
            }
        });
        this.supEqual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.SUP_EQUAL]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.SUP_EQUAL]
                                                .length() - 1));
            }
        });
        this.ubool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_BOOLEAN]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_BOOLEAN]
                                                .length() - 1));
            }
        });
        this.uint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_NUMBER]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.UNKNOWN_NUMBER]
                                                .length() - 1));
            }
        });
        this.ustr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
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
                IterationAdaptator.addNewLiteral(TypeStructure
                        .getLocaleTypeStructureIntoEnumere(currentValue), vcst
                        .getText());
            }
        });

        vcstchoice.setModel(new DefaultComboBoxModel(TypeStructure.getNameLocaleTypeStructureWithoutInterval()));
        
        this.jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator.addLoopVariant(jTextField1.getText());
            }
        });
        
        this.while1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(IterationConstants.tokenImage[IterationConstants.WHILE]
                                .substring(
                                        1,
                                        IterationConstants.tokenImage[IterationConstants.WHILE]
                                                .length() - 1)
                                + "(" + "  " + ")");
            }
        });
        
        this.notWhile2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(IterationConstants.tokenImage[IterationConstants.NOTWHILE]
                                .substring(
                                        1,
                                        IterationConstants.tokenImage[IterationConstants.NOTWHILE]
                                                .length() - 1)
                                + "(" + "  " + ")");
            }
        });
        
        this.getValue2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IterationAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
                                .substring(
                                        1,
                                        PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
                                                .length() - 1)
                                + "(" + "  ,  " + ")");
            }
        });
        
        this.getValue1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreconditionAdaptator
                        .setNewToken(PreconditionConstants.tokenImage[PreconditionConstants.GETVALUE]
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
        isExist = new JButton();
        length2 = new JButton();
        length1 = new JButton();
        isEmpty = new JButton();
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
        isExistAt = new JButton();
        iteratorPanel = new JPanel();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        while1 = new JButton();        
        cardPanel = new JPanel();
        getValue2 = new JButton();
        getValue1 = new JButton();
        variantPanel = new JPanel();
        panelGetValue = new JPanel();
        notWhile2 = new JButton();
        panelExist = new JPanel();
        
        literalPanel.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

        literalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, KMADEConstant.LITTERAUX_TITLE_MESSAGE));
        vcst.setMinimumSize(new java.awt.Dimension(30, 19));

        vcstchoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Integer", "Boolean", "String" }));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(vcstchoice, 0, 69, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(vcst, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(vcst, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .add(vcstchoice, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
        );
        literalPanel.add(jPanel1);

        ubool.setText("?BOOL");
        literalPanel.add(ubool);

        uint.setText("?NUM");
        literalPanel.add(uint);

        ustr.setText("?STR");
        literalPanel.add(ustr);

        functionPanel.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

        functionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.FUNCTIONS_TITLE_MESSAGE));
        cardPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        length2.setText("card(...,...)");
        cardPanel.add(length2);

        length1.setText("card(...)");
        cardPanel.add(length1);

        functionPanel.add(cardPanel);

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

        operatorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.OPERATORS_TITLE_MESSAGE));
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

        iteratorPanel.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        iteratorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(KMADEConstant.ITERATORS_TITLE_MESSAGE));
        variantPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Variant Boucle");
        variantPanel.add(jLabel1);

        variantPanel.add(jTextField1);

        iteratorPanel.add(variantPanel);

        while1.setText("while(...)");
        iteratorPanel.add(while1);

        notWhile2.setText("notWhile(...)");
        iteratorPanel.add(notWhile2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(literalPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(operatorPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(functionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                    .add(iteratorPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(iteratorPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(functionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(operatorPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(literalPanel, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }       
    
    public void notifLocalisationModification() {
    	
    }
}

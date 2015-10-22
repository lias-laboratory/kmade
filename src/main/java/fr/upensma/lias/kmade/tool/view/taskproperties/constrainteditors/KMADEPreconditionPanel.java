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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessagePanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.ConcreteObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.PreconditionAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.ReadConcreteObjectAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPreconditionPanel extends JPanel implements LanguageFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6251751576712160692L;


	private final KMADEProtoTaskPreconditionPanel proto = new KMADEProtoTaskPreconditionPanel();

	private final KMADEBasePreconditionPanel base = new KMADEBasePreconditionPanel();

	public KMADEProtoTaskPreconditionPanel getProtoTaskPreconditionPanel() {
		return proto;
	}
	public KMADEBasePreconditionPanel getBasePreconditionPanel() {
		return base;
	}
	private final JTabbedPane tabpanePre;


	public Object textArea;

	public KMADEPreconditionPanel(){
		tabpanePre = new JTabbedPane();
		tabpanePre.addTab(KMADEConstant.PRECONDITION_TAB_FORMAL,base);
		tabpanePre.addTab(KMADEConstant.PRECONDITION_TAB_PROTOTASK,proto);

		tabpanePre.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabpanePre.getSelectedIndex() == 0) {
					ConcreteObjectPanelAdaptator.updateConcreteObjectView();
				} else if (tabpanePre.getSelectedIndex() == 1) {
						
						proto.updateDataModel();
					}
				}
			});
		this.add(tabpanePre, BorderLayout.CENTER);
	//	this.setPreferredSize(new Dimension(800,700));
		}
		@Override
		public void notifLocalisationModification() {
			// TODO Auto-generated method stub
		}
		public void setOutputMessage() {
			base.setOutputMessage();

		}
		public void setDescriptionArea(String description) {
			base.setDescriptionArea(description);

		}
		public JTextComponent getTextArea() {
			return base.textArea;
		}



	}

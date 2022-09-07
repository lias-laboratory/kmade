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

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import fr.upensma.lias.kmade.kmad.schema.expression.ExpressionConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.UserBoolean;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.UserNumber;
import fr.upensma.lias.kmade.kmad.schema.expression.UserString;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public class KMADEUserExpressionField extends JTextField {

	private static final long serialVersionUID = -7138413572029624691L;

	private UserExpression myUserExpression;

	public KMADEUserExpressionField(UserExpression ref) {
		super();
		myUserExpression = ref;
		KMADEUserExpressionField.this
				.setPreferredSize(new Dimension(20, KMADEUserExpressionField.this.getPreferredSize().height));
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				if (KMADEUserExpressionField.this.getText().length() < 2) {
					KMADEUserExpressionField.this.setPreferredSize(
							new Dimension(20, KMADEUserExpressionField.this.getPreferredSize().height));
				} else {
					KMADEUserExpressionField.this.setPreferredSize(null);
				}

				KMADEUserExpressionField.this.getParent().validate();
				KMADEUserExpressionField.this.getParent().repaint();

				KMADEUserExpressionField.this.setValueIntoUserExpression(KMADEUserExpressionField.this.getText());
			}
		});

		if (ref.getObjectValueState() == ExpressionConstant.VALUE) {
			this.setText(ref.getNodeValue().toString());
		}
		this.setAutoscrolls(true);
	}

	public UserExpression getUserExpression() {
		return myUserExpression;
	}

	public void setInitValueIntoUserExpression(String myText) {
		this.setText(myText);
		this.setValueIntoUserExpression(myText);
		if (this.getText().length() < 2) {
			KMADEUserExpressionField.this
					.setPreferredSize(new Dimension(20, KMADEUserExpressionField.this.getPreferredSize().height));
		} else {
			KMADEUserExpressionField.this.setPreferredSize(null);
		}
	}

	private void setValueIntoUserExpression(String myText) {
		if (myUserExpression instanceof UserBoolean) {
			myUserExpression.setNodeValue(Boolean.valueOf(myText));
		} else if (myUserExpression instanceof UserString) {
			myUserExpression.setNodeValue(myText);
		} else if (myUserExpression instanceof UserNumber) {
			try {
				myUserExpression.setNodeValue(new NumberValue(myText));
			} catch (NumberFormatException nfe) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.STRING_TO_INTEGER + " : " + myText);
				myUserExpression.setStateToError();
			}
		}
	}

	public void setToUnknownUserExpression() {
		myUserExpression.setStateToUnknown();
	}

	public boolean isUnknownOrErrorUserExpressionState() {
		if (myUserExpression != null) {
			return (myUserExpression.isErrorState() || myUserExpression.isUnknownState());
		} else {
			return true;
		}
	}
}

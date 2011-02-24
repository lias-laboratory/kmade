package kmade.kmade.UI.taskproperties.constrainteditors;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import kmade.kmade.KMADEConstant;
import kmade.nmda.schema.expression.ExpressionConstant;
import kmade.nmda.schema.expression.UserBoolean;
import kmade.nmda.schema.expression.UserExpression;
import kmade.nmda.schema.expression.UserNumber;
import kmade.nmda.schema.expression.UserString;
import kmade.nmda.schema.metaobjet.NumberValue;

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
public class KMADEUserExpressionField extends JTextField {
    private static final long serialVersionUID = -7138413572029624691L;

    private UserExpression myUserExpression;
      
    public KMADEUserExpressionField(UserExpression ref) {
        super();
        myUserExpression = ref;
        KMADEUserExpressionField.this.setPreferredSize(new Dimension(20,KMADEUserExpressionField.this.getPreferredSize().height));
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {           
            	
                if (KMADEUserExpressionField.this.getText().length() < 2) {
                    KMADEUserExpressionField.this.setPreferredSize(new Dimension(20,KMADEUserExpressionField.this.getPreferredSize().height));
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
    			KMADEUserExpressionField.this.setPreferredSize(new Dimension(20,KMADEUserExpressionField.this.getPreferredSize().height));
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
                System.out.println(KMADEConstant.STRING_TO_INTEGER + " : " + myText);
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

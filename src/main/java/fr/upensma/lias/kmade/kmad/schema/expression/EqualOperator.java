package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

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
public class EqualOperator extends EqualityOperator {

    private static final long serialVersionUID = 6340750747985767574L;

    public EqualOperator(NodeExpression left) {
        super(left);
        this.name = ExpressConstant.EQUAL_OPERATOR_EXPRESSION;
    }    
	
    public void evaluateNode(ObjetConcret ref) throws SemanticException { 
		super.evaluateNode(ref);
		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}
		
		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}
		// Certains tests ne doivent pas se présenter, le checkNode n'autorisant que le même type à gauche et à droite
		if (getLeftNode().isNumber() && getRightNode().isString()) {
			this.setNodeValue(new Boolean( ((NumberValue)(getLeftNode().getNodeValue())).equalOperator( (new NumberValue((String)getRightNode().getNodeValue())))));
			return;
		}
		
		if (getLeftNode().isString() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((new NumberValue((String)getLeftNode().getNodeValue()))).equalOperator(((NumberValue)getRightNode().getNodeValue()))));
			return;
		}
		
		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue(new Boolean((((String)getLeftNode().getNodeValue()).toLowerCase().equals( ((String)getRightNode().getNodeValue()).toLowerCase()))));
			return;
		}
		
		if (getLeftNode().isNumber() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((NumberValue)getLeftNode().getNodeValue()).equalOperator((NumberValue)getRightNode().getNodeValue())));
			return;
		}
        
        if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
            this.setNodeValue(new Boolean(((Boolean)getLeftNode().getNodeValue()).booleanValue() == ((Boolean)getRightNode().getNodeValue()).booleanValue()));
            return;
        }
               
        if (this.getLeftNode().isBoolean() && this.getRightNode().isNumber()) {
            this.setNodeValue(new Boolean(((Boolean)getLeftNode().getNodeValue()).booleanValue() == (((NumberValue) getRightNode().getNodeValue()).equalOperator(new NumberValue("0")) ? true : false)));
            return;
        }

        if (this.getLeftNode().isNumber() && this.getRightNode().isBoolean()) {
            this.setNodeValue(new Boolean(((Boolean)getRightNode().getNodeValue()).booleanValue() == (((NumberValue) getLeftNode().getNodeValue()).equalOperator(new NumberValue("0")) ? true : false)));
            return;            
        }
    }
    
    public String toString() {
        return super.toString();
    }
	
}

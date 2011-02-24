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
public class PlusComputing extends ComputingOperator {
    private static final long serialVersionUID = -394810851362269302L;

    public PlusComputing(NodeExpression left, NodeExpression right) {
        super(left,right);
        this.name = "+";
    }
    
    public void checkNode() throws SemanticException {
        super.checkNode();


        if (this.getLeftNode().isString() && this.getRightNode().isString()) {
            this.setNodeType("");
            return;
        }
        
        if (this.getLeftNode().isNumber() && this.getRightNode().isNumber()) {
            this.setNodeType(new NumberValue());
            return;
        }

        this.setStateToError();
        throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        
		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}
		
		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}
			// les cas autres que String + String ou Nombre+ Nombre ne doivent pas arriver normalement
        	if (getLeftNode().isNumber() && getRightNode().isString()) {
        		this.setNodeValue(new String(((NumberValue)getLeftNode().getNodeValue()).toString() + ((String)getRightNode().getNodeValue())));
        	}

        	if (getLeftNode().isString() && getRightNode().isNumber()) {
        		this.setNodeValue(new String(((String)getLeftNode().getNodeValue()) + ((NumberValue)getRightNode().getNodeValue()).toString()));
        	}
    		
        	
        	if (getLeftNode().isNumber() && getRightNode().isNumber()) {
        		this.setNodeValue( ((NumberValue)getLeftNode().getNodeValue()).plusComputing(((NumberValue)getRightNode().getNodeValue())));
        	}
    }
}

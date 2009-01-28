package kmade.nmda.schema.expression;

import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
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
		
		if (getLeftNode().isInteger() && getRightNode().isString()) {
			this.setNodeValue(new Boolean(((Integer)getLeftNode().getNodeValue()).intValue() == (new Integer((String)getRightNode().getNodeValue())).intValue()));
			return;
		}
		
		if (getLeftNode().isString() && getRightNode().isInteger()) {
			this.setNodeValue(new Boolean(((new Integer((String)getLeftNode().getNodeValue())).intValue()) == (((Integer)getRightNode().getNodeValue()).intValue())));
			return;
		}
		
		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue(new Boolean((((String)getLeftNode().getNodeValue()).equals((String)getRightNode().getNodeValue()))));
			return;
		}
		
		if (getLeftNode().isInteger() && getRightNode().isInteger()) {
			this.setNodeValue(new Boolean(((Integer)getLeftNode().getNodeValue()).intValue() == ((Integer)getRightNode().getNodeValue()).intValue()));
			return;
		}
        
        if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
            this.setNodeValue(new Boolean(((Boolean)getLeftNode().getNodeValue()).booleanValue() == ((Boolean)getRightNode().getNodeValue()).booleanValue()));
            return;
        }
               
        if (this.getLeftNode().isBoolean() && this.getRightNode().isInteger()) {
            this.setNodeValue(new Boolean(((Boolean)getLeftNode().getNodeValue()).booleanValue() == ((Integer)getRightNode().getNodeValue() == 0 ? false : true)));
            return;
        }

        if (this.getLeftNode().isInteger() && this.getRightNode().isBoolean()) {
            this.setNodeValue(new Boolean(((Integer)getRightNode().getNodeValue() == 0 ? false : true) == ((Boolean)getLeftNode().getNodeValue()).booleanValue()));            
            return;            
        }
    }
    
    public String toString() {
        return super.toString();
    }
	
}

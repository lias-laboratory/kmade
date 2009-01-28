package kmade.nmda.schema.expression;

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
public class PlusComputing extends ComputingOperator {
    private static final long serialVersionUID = -394810851362269302L;

    public PlusComputing(NodeExpression left, NodeExpression right) {
        super(left,right);
        this.name = "+";
    }
    
    public void checkNode() throws SemanticException {
        super.checkNode();

        if (this.getLeftNode().isInteger() && this.getRightNode().isString()) {
            this.setNodeType("");
        }
        
        if (this.getLeftNode().isString() && this.getRightNode().isInteger()) {
            this.setNodeType("");
            return;
        }
        
        if (this.getLeftNode().isString() && this.getRightNode().isString()) {
            this.setNodeType("");
            return;
        }
        
        if (this.getLeftNode().isInteger() && this.getRightNode().isInteger()) {
            this.setNodeType(0);
            return;
        }

        this.setStateToError();
        throw new SemanticException();
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        
		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}
		
		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}

        	if (getLeftNode().isString() && getRightNode().isString()) {
        		this.setNodeValue(new String(((String)getLeftNode().getNodeValue()) + ((String)getRightNode().getNodeValue())));
        	}

        	if (getLeftNode().isString() && getRightNode().isInteger()) {
        		this.setNodeValue(new String(((String)getLeftNode().getNodeValue()) + ((Integer)getRightNode().getNodeValue()).toString()));
        	}
    		
        	if (getLeftNode().isInteger() && getRightNode().isString()) {
        		int temp = new Integer((String)getRightNode().getNodeValue());
        		this.setNodeValue(new String(((Integer)getLeftNode().getNodeValue()).toString() + temp).toString());
        	}
        	
        	if (getLeftNode().isInteger() && getRightNode().isInteger()) {
        		this.setNodeValue(Integer.valueOf(((Integer)getLeftNode().getNodeValue()) + ((Integer)getRightNode().getNodeValue())));
        	}
    }
}

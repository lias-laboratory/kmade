package kmade.nmda.schema.expression;

import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.AttributConcret;
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
public class Assignment extends AssignmentOperator {
 
    private static final long serialVersionUID = 4204749580312652874L;

    public Assignment(AttributExpressExpression left) {
        super(false,left);
        this.name = ExpressConstant.ASSIGNMENT_EXPRESSION;
    }   
    
    
    public void checkNode() throws SemanticException {
        super.checkNode();        
        
        if (getLeftNode().isString() && getRightNode().isBoolean()) {
            this.setNodeValue("");
            this.setStateToUnknown();
            return;
        }
        
        if (getLeftNode().isString() && getRightNode().isString()) {
            this.setNodeValue("");
            this.setStateToUnknown();
            return;
        }       
        
        if (getLeftNode().isString() && getRightNode().isInteger()) {
            this.setNodeValue("");
            this.setStateToUnknown();
            return;
        }

        if (getLeftNode().isBoolean() && getRightNode().isString()) {
            try {
                new Boolean((String)getRightNode().getNodeValue()).booleanValue();
                this.setNodeValue(false);
                this.setStateToUnknown();
                return;
            }
            catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }

        if (getLeftNode().isBoolean() && getRightNode().isBoolean()) {                          
            this.setNodeValue(false);
            this.setStateToUnknown();
            return;
        }       
        
        if (getLeftNode().isBoolean() && getRightNode().isInteger()) {                          
            this.setNodeValue(false); 
            this.setStateToUnknown();
            return;
        }       

        if (getLeftNode().isInteger() && getRightNode().isString()) {
            try {
                new Integer((String)getRightNode().getNodeValue());
                this.setNodeValue(0);
                this.setStateToUnknown();
                return;
            }
            catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }       

        if (getLeftNode().isInteger() && getRightNode().isBoolean()) {                          
            this.setNodeValue(new Integer(0)); 
            this.setStateToUnknown();
            return;
        }       
  

        if (getLeftNode().isInteger() && getRightNode().isInteger()) {                          
            this.setNodeValue(new Integer(0)); 
            this.setStateToUnknown();
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
        
        AttributConcret refConcret = ref.getAttribut(((AttributExpressExpression)this.leftNode).getAbstractAttribut());
        boolean error = refConcret.setValeur(this.rightNode.getNodeValue().toString());
        if (error) {      
            this.setStateToError();
            throw new SemanticErrorException();
        }
    }
}

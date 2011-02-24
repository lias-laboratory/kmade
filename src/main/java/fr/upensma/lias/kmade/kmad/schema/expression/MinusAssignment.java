package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
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
public class MinusAssignment extends AssignmentOperator {

    private static final long serialVersionUID = -5920755994926850384L;

    public MinusAssignment(AttributExpressExpression left) {
        super(false,left);
        this.name = ExpressConstant.MINUS_ASSIGNMENT_EXPRESSION;
    }
    
    public void checkNode() throws SemanticException {
        super.checkNode();        
        
        if (getLeftNode().isNumber() && getRightNode().isNumber()) {                          
            this.setNodeValue(new NumberValue()); 
            this.setStateToUnknown();
            return;
        }       
        
        this.setStateToError();
        throw new SemanticException(ExpressConstant.TYPE_NOT_A_NUMBER);
    }
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        
		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}
		
		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}
			
			// dans certains cas un java null pointer exception peut �tre lever ce qui correspond � une semanticunknownException
    		try{
    			AttributConcret refConcret = ref.getAttribut(((AttributExpressExpression)this.leftNode).getAbstractAttribut());
    		boolean error = false;
        	
        	if (getLeftNode().isNumber() && getRightNode().isNumber()) {
         		error = refConcret.setValeur(((NumberValue) refConcret.getValue()).minusComputing((NumberValue)getRightNode().getNodeValue()).toString());
        	}
        	
        	if (error) {
        		this.setStateToError();
    			throw new SemanticErrorException();
        	}
    	}catch (Exception e) {
    		throw new SemanticUnknownException();
		}
    }
}

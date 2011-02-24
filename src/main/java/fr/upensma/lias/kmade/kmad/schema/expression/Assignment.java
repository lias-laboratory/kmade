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
public class Assignment extends AssignmentOperator {

	private static final long serialVersionUID = 4204749580312652874L;

	public Assignment(AttributExpressExpression left) {
		super(false,left);
		this.name = ExpressConstant.ASSIGNMENT_EXPRESSION;
	}   


	public void checkNode() throws SemanticException {
		super.checkNode();        




		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue("");
			this.setStateToUnknown();
			return;
		} 

		if (getLeftNode().isBoolean() && getRightNode().isBoolean()) {                          
			this.setNodeValue(false);
			this.setStateToUnknown();
			return;
		}
		if (getLeftNode().isNumber() && getRightNode().isNumber()) {                          
			this.setNodeValue(new NumberValue()); 
			this.setStateToUnknown();
			return;
		}       

		// L'ensemble des if comment�s correspond � des cast que l'on ne souhaite plus
		/*
        if (getLeftNode().isString() && getRightNode().isBoolean()) {
            this.setNodeValue("");
            this.setStateToUnknown();
            return;
        }
        if (getLeftNode().isString() && getRightNode().isNumber()) {
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
        if (getLeftNode().isBoolean() && getRightNode().isNumber()) {                          
            this.setNodeValue(false); 
            this.setStateToUnknown();
            return;
        //Si on a un nombre et une chaîne de caractères, on essaye de construire un NumberValue avec la chaîne
        //L'utilisateur ne pourra pas rentrer de nombre avec ?STR
        if (getLeftNode().isNumber() && getRightNode().isString()) {
        	try {
        		new NumberValue((String)getRightNode().getNodeValue());
        		this.setNodeValue(0);
        		this.setStateToUnknown();
        		return;
        	}
        	catch (NumberFormatException e) {
        			this.setStateToError();
        			throw new SemanticException();
        		}
        	}
        if (getLeftNode().isNumber() && getRightNode().isBoolean()) {                          
            this.setNodeValue(new NumberValue()); 
            this.setStateToUnknown();
            return;
        }       
		 */


		this.setStateToError();
		throw new SemanticException(ExpressConstant.TYPAGE_ERROR);
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
			boolean error = refConcret.setValeur(this.rightNode.getNodeValue().toString());
			if (error) {      
				this.setStateToError();
				throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
			}
		}catch( Exception e){
			throw new SemanticUnknownException();
		}
	}
}

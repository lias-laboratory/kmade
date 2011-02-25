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
package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class Assignment extends AssignmentOperator {

    private static final long serialVersionUID = 4204749580312652874L;

    public Assignment(AttributExpressExpression left) {
	super(false, left);
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
	// dans certains cas un java null pointer exception peut �tre lever ce
	// qui correspond � une semanticunknownException
	try {
	    AttributConcret refConcret = ref
		    .getAttribut(((AttributExpressExpression) this.leftNode)
			    .getAbstractAttribut());
	    boolean error = refConcret.setValeur(this.rightNode.getNodeValue()
		    .toString());
	    if (error) {
		this.setStateToError();
		throw new SemanticException(
			ExpressConstant.COMPARISON_OPERATOR_ERROR + " : "
				+ this.name);
	    }
	} catch (Exception e) {
	    throw new SemanticUnknownException();
	}
    }
}

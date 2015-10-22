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
package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StrValue;

/**
 * @author Mickael BARON
 */
public class PlusAssignment extends AssignmentOperator {

    private static final long serialVersionUID = 7980770992751137196L;

    public PlusAssignment(AttributExpressExpression left) {
	super(false, left);
	this.name = ExpressConstant.PLUS_ASSIGNMENT_EXPRESSION;
    }

    public void checkNode() throws SemanticException {
	super.checkNode();

	if (getLeftNode().isString() && getRightNode().isString()) {
	    this.setNodeValue("");
	    this.setStateToUnknown();
	    return;
	}

	if (getLeftNode().isNumber() && getRightNode().isNumber()) {
	    this.setNodeValue(new NumberValue());
	    this.setStateToUnknown();
	    return;
	}

	this.setStateToError();
	throw new SemanticException(ExpressConstant.TYPE_PLUS_ASSIGNMENT);
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
	    boolean error = false;

	    if (getLeftNode().isString() && getRightNode().isBoolean()) {
		error = refConcret
			.setValeur(new String(
				((StrValue) refConcret.getValue())
					+ ((Boolean) getRightNode()
						.getNodeValue()).toString()));
	    }

	    if (getLeftNode().isString() && getRightNode().isString()) {
		error = refConcret
			.setValeur(new String(
				((StrValue) refConcret.getValue())
					+ ((String) getRightNode()
						.getNodeValue())));
	    }

	    if (getLeftNode().isString() && getRightNode().isNumber()) {
		error = refConcret.setValeur(new String((((StrValue) refConcret
			.getValue()) + ((NumberValue) getRightNode()
			.getNodeValue()).toString())));
	    }

	    if (getLeftNode().isNumber() && getRightNode().isString()) {
		error = refConcret.setValeur(((NumberValue) refConcret
			.getValue()).plusComputing(
			new NumberValue(
				((String) getRightNode().getNodeValue())))
			.toString());
	    }

	    if (getLeftNode().isNumber() && getRightNode().isNumber()) {
		error = refConcret.setValeur(((NumberValue) refConcret
			.getValue()).plusComputing(
			((NumberValue) getRightNode().getNodeValue()))
			.toString());
	    }

	    if (error) {
		this.setStateToError();
		throw new SemanticErrorException();
	    }
	} catch (Exception e) {
	    throw new SemanticUnknownException();
	}
    }
}

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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class DiffOperator extends EqualityOperator {

    private static final long serialVersionUID = -266701582462346678L;

    public DiffOperator(NodeExpression left) {
	super(left);
	this.name = ExpressConstant.DIFF_OPERATOR_EXPRESSION;
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	super.evaluateNode(ref);

	if (this.isErrorState()) {
	    throw new SemanticErrorException();
	}

	if (this.isUnknownState()) {
	    throw new SemanticUnknownException();
	}
	// Certains tests ne doivent pas se présenter, le checkNode n'autorisant
	// que le même type à gauche et à droite
	if (getLeftNode().isNumber() && getRightNode().isString()) {

	    NumberValue n = (NumberValue) getLeftNode().getNodeValue();
	    NumberValue n2 = new NumberValue((String) getRightNode()
		    .getNodeValue());
	    boolean res = n.diffOperator(n2);

	    this.setNodeValue(new Boolean(res));
	    return;
	}

	if (getLeftNode().isString() && getRightNode().isNumber()) {

	    NumberValue n = (NumberValue) getRightNode().getNodeValue();
	    boolean res = false;

	    NumberValue n2 = new NumberValue((String) getLeftNode()
		    .getNodeValue());
	    res = n.diffOperator(n2);

	    this.setNodeValue(new Boolean(res));
	    return;
	}

	if (getLeftNode().isString() && getRightNode().isString()) {
	    this.setNodeValue(!(new Boolean((((String) getLeftNode()
		    .getNodeValue()).toLowerCase()
		    .equals(((String) getRightNode().getNodeValue())
			    .toLowerCase())))));
	    return;
	}

	if (getLeftNode().isNumber() && getRightNode().isNumber()) {
	    this.setNodeValue(new Boolean(((NumberValue) getLeftNode()
		    .getNodeValue()).diffOperator(((NumberValue) getRightNode()
		    .getNodeValue()))));
	    return;
	}

	if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
	    this.setNodeValue(new Boolean(
		    ((Boolean) getLeftNode().getNodeValue()).booleanValue() != ((Boolean) getRightNode()
			    .getNodeValue()).booleanValue()));
	    return;
	}

	if (this.getLeftNode().isBoolean() && this.getRightNode().isNumber()) {
	    this.setNodeValue(new Boolean(
		    ((Boolean) getLeftNode().getNodeValue()).booleanValue() != (((NumberValue) getRightNode()
			    .getNodeValue())
			    .equalOperator(new NumberValue("0")) ? true : false)));
	    return;
	}

	if (this.getLeftNode().isNumber() && this.getRightNode().isBoolean()) {
	    this.setNodeValue(new Boolean(
		    ((Boolean) getRightNode().getNodeValue()).booleanValue() != (((NumberValue) getLeftNode()
			    .getNodeValue())
			    .equalOperator(new NumberValue("0")) ? true : false)));
	    return;
	}
    }

    public String toString() {
	return super.toString();
    }
}

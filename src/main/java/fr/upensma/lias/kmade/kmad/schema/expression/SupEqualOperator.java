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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class SupEqualOperator extends RelationalOperator {

    private static final long serialVersionUID = 5547865175021787122L;

    public SupEqualOperator(NodeExpression left) {
	super(left);
	this.name = ">=";
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	super.evaluateNode(ref);
	if (this.isErrorState()) {
	    throw new SemanticErrorException();
	}

	if (this.isUnknownState()) {
	    throw new SemanticUnknownException();
	}

	if (getLeftNode().isNumber() && getRightNode().isString()) {
	    this.setNodeValue(new Boolean(((NumberValue) getLeftNode()
		    .getNodeValue()).supEqualOperator((new NumberValue(
		    (String) getRightNode().getNodeValue())))));
	    return;
	}

	if (getLeftNode().isString() && getRightNode().isNumber()) {
	    this.setNodeValue(new Boolean(((new NumberValue(
		    (String) getLeftNode().getNodeValue()))
		    .supEqualOperator(((NumberValue) getRightNode()
			    .getNodeValue())))));
	    return;
	}

	// uniquement ordre lexicographique
	if (getLeftNode().isString() && getRightNode().isString()) {
	    this.setNodeValue(new Boolean(
		    ((String) getLeftNode().getNodeValue()).toLowerCase()
			    .compareTo(
				    ((String) getRightNode().getNodeValue())
					    .toLowerCase()) >= 0));
	    return;
	}

	if (getLeftNode().isNumber() && getRightNode().isNumber()) {
	    this.setNodeValue(new Boolean(((NumberValue) getLeftNode()
		    .getNodeValue())
		    .supEqualOperator((NumberValue) getRightNode()
			    .getNodeValue())));
	    return;
	}
    }

    public String toString() {
	return super.toString();
    }
}

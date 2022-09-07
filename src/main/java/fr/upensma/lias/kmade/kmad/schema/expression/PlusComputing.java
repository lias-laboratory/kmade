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
public class PlusComputing extends ComputingOperator {

	private static final long serialVersionUID = -394810851362269302L;

	public PlusComputing(NodeExpression left, NodeExpression right) {
		super(left, right);
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
		// les cas autres que String + String ou Nombre+ Nombre ne doivent pas
		// arriver normalement
		if (getLeftNode().isNumber() && getRightNode().isString()) {
			this.setNodeValue(new String(((NumberValue) getLeftNode().getNodeValue()).toString()
					+ ((String) getRightNode().getNodeValue())));
		}

		if (getLeftNode().isString() && getRightNode().isNumber()) {
			this.setNodeValue(new String(((String) getLeftNode().getNodeValue())
					+ ((NumberValue) getRightNode().getNodeValue()).toString()));
		}

		if (getLeftNode().isNumber() && getRightNode().isNumber()) {
			this.setNodeValue(((NumberValue) getLeftNode().getNodeValue())
					.plusComputing(((NumberValue) getRightNode().getNodeValue())));
		}
	}
}

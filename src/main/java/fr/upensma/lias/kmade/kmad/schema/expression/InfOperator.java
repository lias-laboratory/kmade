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
public class InfOperator extends RelationalOperator {

	private static final long serialVersionUID = -1311669179618639600L;

	public InfOperator(NodeExpression left) {
		super(left);
		this.name = ExpressConstant.INF_OPERATOR_EXPRESSION;
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
			this.setNodeValue(new Boolean(((NumberValue) getLeftNode().getNodeValue())
					.infOperator((new NumberValue((String) getRightNode().getNodeValue())))));
			return;
		}

		if (getLeftNode().isString() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((new NumberValue((String) getLeftNode().getNodeValue()))
					.infOperator(((NumberValue) getRightNode().getNodeValue())))));
			return;
		}

		// uniquement ordre lexicographique
		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue(new Boolean(((String) getLeftNode().getNodeValue()).toLowerCase()
					.compareTo(((String) getRightNode().getNodeValue()).toLowerCase()) < 0));
			return;
		}

		if (getLeftNode().isNumber() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((NumberValue) getLeftNode().getNodeValue())
					.infOperator((NumberValue) getRightNode().getNodeValue())));
			return;
		}
	}

	public String toString() {
		return super.toString();
	}
}

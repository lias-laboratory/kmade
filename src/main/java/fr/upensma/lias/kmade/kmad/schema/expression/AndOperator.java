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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class AndOperator extends ConditionalOperator {

	private static final long serialVersionUID = 3972146291618352854L;

	public AndOperator(NodeExpression left) {
		super(left);
		this.name = ExpressConstant.AND_OPERATOR_EXPRESSION;
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {
		super.evaluateNode(ref);

		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}

		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}

		if (getLeftNode().isBoolean() && getRightNode().isBoolean()) {
			this.setNodeValue(new Boolean(((Boolean) getLeftNode().getNodeValue()).booleanValue()
					&& ((Boolean) getRightNode().getNodeValue()).booleanValue()));
			return;
		}
		// ne dois jamais arriver si checkNode() effectuée
		if (getLeftNode().isBoolean() && getRightNode().isString()) {
			this.setNodeValue(new Boolean(((Boolean) getLeftNode().getNodeValue()).booleanValue()
					&& (new Boolean((String) getRightNode().getNodeValue())).booleanValue()));
			return;
		}
		// ne dois jamais arriver si checkNode() effectuée
		if (getLeftNode().isString() && getRightNode().isBoolean()) {
			this.setNodeValue(new Boolean(((new Boolean((String) getLeftNode().getNodeValue())).booleanValue())
					&& ((Boolean) getRightNode().getNodeValue()).booleanValue()));
			return;
		}
		// ne dois jamais arriver si checkNode() effectuée
		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue(new Boolean(((new Boolean((String) getLeftNode().getNodeValue())).booleanValue())
					&& (new Boolean((String) getRightNode().getNodeValue())).booleanValue()));
			return;
		}
	}

	public String toString() {
		return super.toString();
	}
}

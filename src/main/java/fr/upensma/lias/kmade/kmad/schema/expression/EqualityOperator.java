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

/**
 * @author Mickael BARON
 */
public class EqualityOperator extends ComparisonOperator {

	private static final long serialVersionUID = 3907268708910997841L;

	public EqualityOperator(NodeExpression left) {
		super(false, left);
	}

	public void checkNode() throws SemanticException {
		super.checkNode();

		if (this.getLeftNode().isString() && this.getRightNode().isString()) {
			this.setNodeType(true);
			return;
		}

		if (this.getLeftNode().isNumber() && this.getRightNode().isNumber()) {
			this.setNodeType(true);
			return;
		}

		if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
			this.setNodeType(true);
			return;
		}
		this.setStateToError();
		throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
	}
}

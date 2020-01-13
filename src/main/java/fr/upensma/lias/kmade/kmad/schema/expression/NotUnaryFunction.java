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
public class NotUnaryFunction extends UnaryFunction {

	private static final long serialVersionUID = 6844275161051663640L;

	public NotUnaryFunction(NodeExpression pnode) {
		super(false, pnode);
		this.name = ExpressConstant.NOT_UNARY_EXPRESSION;
	}

	public void checkNode() throws SemanticException {
		super.checkNode();

		if (this.getAloneExpression().isBoolean()) {
			this.setStateToUnknown();
			return;
		}
		// Y a une erreur de type.
		this.setStateToError();
		throw new SemanticException(ExpressConstant.TYPE_NEED_BOOLEAN);
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {
		super.evaluateNode(ref);
		if (this.isValueState()) {
			this.setNodeValue(new Boolean(!(Boolean) this.getAloneExpression().getNodeValue()));
		}
	}
}

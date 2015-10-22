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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class IsExistFunction extends BinaryFunction {

    private static final long serialVersionUID = -4093221522071521465L;

    public IsExistFunction(GroupExpressExpression left, NodeExpression right) {
	super(false, left, right);
	this.name = ExpressConstant.IS_EXIST_FUNCTION_EXPRESSION;
    }

    public void checkNode() throws SemanticException {
	super.checkNode();

	if (this.rightNode.isBoolean()) {
	    this.setStateToUnknown();
	    return;
	}
	this.setStateToError();
	throw new SemanticException(ExpressConstant.BOOLEAN_IS_NEEDED_ERROR
		+ " : " + this.getName());
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	ArrayList<ObjetConcret> refObjetConcretList = (((GroupExpressExpression) this.leftNode)
		.getGroup().getEnsemble().getLstObjConcrets());

	for (ObjetConcret current : refObjetConcretList) {
	    super.evaluateNode(current);

	    if (this.isValueState()) {
		this.setNodeValue(this.rightNode.getNodeValue());
		break;
	    }
	}
    }
}

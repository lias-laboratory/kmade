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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class LengthFunction extends BinaryFunction {

    private static final long serialVersionUID = -891728359658195164L;

    public LengthFunction(GroupExpressExpression left, NodeExpression right) {
	super(new Integer(0), left, right);
	this.name = ExpressConstant.LENGTH_FUNCTION_EXPRESSION;
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
	ArrayList<ObjetConcret> list = ((GroupExpressExpression) this
		.getLeftNode()).getGroup().getEnsemble().getLstObjConcrets();

	int compteur = 0;
	for (ObjetConcret current : list) {
	    super.evaluateNode(current);

	    if (this.isValueState()) {
		if (this.rightNode.isBoolean()) {
		    if ((Boolean) this.rightNode.getNodeValue()) {
			compteur++;
		    }
		}
	    } else {
		throw new SemanticErrorException();
	    }
	}
	if (this.isValueState()) {
	    this.setNodeValue(compteur);
	}
    }
}

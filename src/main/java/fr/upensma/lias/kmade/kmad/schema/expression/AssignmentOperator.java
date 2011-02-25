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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class AssignmentOperator extends Operator {    

    private static final long serialVersionUID = 1775472343465419728L;

    public AssignmentOperator(Object value, AttributExpressExpression left) {
	super(value, left);
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	this.rightNode.evaluateNode(ref);

	if (getRightNode().isUnknownState()) {
	    this.setStateToUnknown();
	    throw new SemanticUnknownException();
	}

	if (getRightNode().isErrorState()) {
	    this.setStateToError();
	    throw new SemanticErrorException();
	}
	this.setStateToValue();
    }
}

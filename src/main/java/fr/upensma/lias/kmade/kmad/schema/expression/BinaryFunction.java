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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class BinaryFunction extends BinaryExpression {

    private static final long serialVersionUID = -3402788153213192714L;

    public BinaryFunction(Object value, GroupExpressExpression left,
	    NodeExpression right) {
	super(value, left, right);
    }

    public ArrayList<Object> getLinearExpression() {
	ArrayList<Object> myLinearList = new ArrayList<Object>();
	myLinearList.add(this.getName());
	myLinearList.add("(");
	for (Object current : leftNode.getLinearExpression()) {
	    myLinearList.add(current);
	}
	myLinearList.add(",");
	for (Object current : rightNode.getLinearExpression()) {
	    myLinearList.add(current);
	}
	myLinearList.add(")");
	return myLinearList;
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	this.rightNode.evaluateNode(ref);
	// Plus simple qu'opérateur car seul le membre de droite est consid�r�.
	this.setObjectValueState(this.getRightNode().getObjectValueState());
    }

    public void checkNode() throws SemanticException {
	this.rightNode.checkNode();
    }
}

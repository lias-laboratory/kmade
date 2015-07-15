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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class ParameterExpression extends UnaryExpression {

    private static final long serialVersionUID = 4305149294752713361L;

    protected NodeExpression myAloneExpression;

    public ParameterExpression(Object value, NodeExpression pAloneExpression) {
	super(value);
	this.myAloneExpression = pAloneExpression;
    }

    public void checkNode() throws SemanticException {
	this.myAloneExpression.checkNode();
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	myAloneExpression.evaluateNode(ref);
	this.setObjectValueState(myAloneExpression.getObjectValueState());
    }

    public NodeExpression getAloneExpression() {
	return myAloneExpression;
    }

    public ArrayList<Object> getLinearExpression() {
	ArrayList<Object> myList = new ArrayList<Object>();
	myList.add(this.getName());
	myList.add("(");
	for (Object current : myAloneExpression.getLinearExpression()) {
	    myList.add(current);
	}
	myList.add(")");
	return myList;
    }
}

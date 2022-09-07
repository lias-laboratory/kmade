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

import java.io.Serializable;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public abstract class NodeExpression implements Serializable {

	private static final long serialVersionUID = 655075478603783412L;

	private ObjectValue refValue;

	protected String name;

	public abstract void checkNode() throws SemanticException;

	public abstract void evaluateNode(ObjetConcret ref) throws SemanticException;

	public NodeExpression() {
		this(new ObjectValue(new Object()));
	}

	public NodeExpression(Object p_node_value) {
		refValue = new ObjectValue(p_node_value);
		this.setStateToUnknown();
	}

	/**
	 * @return Returns the refValue.
	 */
	public Object getNodeValue() {
		return refValue.getObjectValue();
	}

	public void setNodeValue(Object p_node) {
		refValue.setObjectValue(p_node);
		refValue.setStateToValue();
	}

	public void setNodeType(Object pType) {
		refValue.setObjectValue(pType);
		refValue.setStateToUnknown();
	}

	public Object getNodeType() {
		return refValue.getObjectValue();
	}

	public boolean isString() {
		return (refValue.isStringType());
	}

	public boolean isNumber() {
		return (refValue.isNumberType());
	}

	public boolean isBoolean() {
		return (refValue.isBooleanType());
	}

	public void setStateToValue() {
		refValue.setStateToValue();
	}

	public void setStateToUnknown() {
		refValue.setStateToUnknown();
	}

	public void setStateToError() {
		refValue.setStateToError();
	}

	public boolean isUnknownState() {
		return refValue.isUnknownState();
	}

	public boolean isErrorState() {
		return refValue.isErrorState();
	}

	public boolean isValueState() {
		return refValue.isValueState();
	}

	public String toString() {
		return name + " " + refValue.toString();
	}

	public ArrayList<Object> getLinearExpression() {
		ArrayList<Object> myList = new ArrayList<Object>();
		myList.add(this.getName());
		return myList;
	}

	public String getName() {
		return name;
	}

	public void setObjectValueState(int p) {
		refValue.setObjectValueState(p);
	}

	public int getObjectValueState() {
		return refValue.getObjectValueState();
	}
}

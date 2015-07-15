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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;

/**
 * @author Mickael BARON
 */
public class ObjectValue implements Serializable {

    private static final long serialVersionUID = 2135584525822886070L;

    private Object objectValue;

    private int stateObjectValue;

    public ObjectValue() {
	objectValue = null;
	this.stateObjectValue = ExpressionConstant.UNKNOWN;
    }

    public ObjectValue(Object myValue) {
	this.objectValue = myValue;
	this.stateObjectValue = ExpressionConstant.UNKNOWN;
    }

    /**
     * @return Returns the objectValue.
     */
    public Object getObjectValue() {
	return objectValue;
    }

    public void setObjectValueState(int p) {
	this.stateObjectValue = p;
    }

    public int getObjectValueState() {
	return this.stateObjectValue;
    }

    /**
     * @param objectValue
     *            The objectValue to set.
     */
    public void setObjectValue(Object objectValue) {
	this.objectValue = objectValue;
    }

    public boolean isStringType() {
	return (objectValue instanceof String);
    }

    // Retourne si le type est NumberValue, si le type est Integer il le
    // transforme en NumberValue
    public boolean isNumberType() {
	if (objectValue instanceof Integer) {
	    objectValue = new NumberValue((Integer) objectValue);
	}
	if (objectValue instanceof Double) {
	    objectValue = new NumberValue((Double) objectValue);
	}
	return ((objectValue instanceof NumberValue));
    }

    public boolean isBooleanType() {
	return (objectValue instanceof Boolean);
    }

    public boolean isUnknownState() {
	return stateObjectValue == ExpressionConstant.UNKNOWN;
    }

    public boolean isErrorState() {
	return stateObjectValue == ExpressionConstant.ERROR;
    }

    public boolean isValueState() {
	return stateObjectValue == ExpressionConstant.VALUE;
    }

    public void setStateToValue() {
	stateObjectValue = ExpressionConstant.VALUE;
    }

    public void setStateToUnknown() {
	stateObjectValue = ExpressionConstant.UNKNOWN;
    }

    public void setStateToError() {
	stateObjectValue = ExpressionConstant.ERROR;
    }

    public String toString() {
	return objectValue.toString();
    }
}

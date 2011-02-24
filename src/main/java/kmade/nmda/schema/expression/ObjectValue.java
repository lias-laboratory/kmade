package kmade.nmda.schema.expression;

import java.io.Serializable;

import kmade.nmda.schema.metaobjet.NumberValue;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
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
     * @param objectValue The objectValue to set.
     */
    public void setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }
    
    public boolean isStringType() {
         return (objectValue instanceof String);
    }
    
    //Retourne si le type est NumberValue, si le type est Integer il le transforme en NumberValue
    public boolean isNumberType() {
    	if(objectValue instanceof Integer){
    	objectValue = new NumberValue((Integer)objectValue);
    	}
      	if(objectValue instanceof Double){
        	objectValue = new NumberValue((Double)objectValue);
        	}
    	return ( (objectValue instanceof NumberValue) );
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

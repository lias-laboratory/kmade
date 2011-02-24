package kmade.nmda.schema.expression;

import java.io.Serializable;
import java.util.ArrayList;

import kmade.nmda.schema.metaobjet.ObjetConcret;

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

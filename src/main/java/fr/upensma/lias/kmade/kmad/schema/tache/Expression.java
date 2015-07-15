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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.io.Serializable;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.UserExpression;

/**
 * @author Mickael BARON
 */
public abstract class Expression implements Serializable {

    private static final long serialVersionUID = 4742830993511833531L;

    protected transient NodeExpression refNode = null;

    protected ArrayList<String> myObjectValues = new ArrayList<String>();

    protected String value = "";

    protected boolean locked = false;

    protected String description = "";

    protected ProtoTaskConditionExpression protoTaskConditionExpression = new ProtoTaskConditionExpression();

    public void setDescription(String p) {
	this.description = p;
    }

    public String getDescription() {
	return description;
    }

    public void setObjectValues(ArrayList<String> p) {
	this.myObjectValues = p;
    }

    // Todo : les valeurs utilisateurs concernant les groupes.
    public boolean isAnyUserExpressionInExpression() {
	ArrayList<Object> myList = this.getNodeExpression()
		.getLinearExpression();
	for (Object current : myList) {
	    if (current instanceof UserExpression) {
		return true;
	    }
	}
	return false;
    }

    public ArrayList<String> getObjectValues() {
	return this.myObjectValues;
    }

    public boolean isLocked() {
	return locked;
    }

    public void setLocked(boolean p) {
	this.locked = p;
    }

    public String toSPF() {
	return value;
    }

    public String getFormalText() {
	return value;
    }

    public void setFormalText(String pname) {
	value = pname;
    }

    /**
     * @return Returns the refNode.
     */
    public NodeExpression getNodeExpression() {
	return refNode;
    }

    /**
     * @param refNode
     *            The refNode to set.
     */
    public void setNodeExpression(NodeExpression refNode) {
	this.refNode = refNode;
	myObjectValues = new ArrayList<String>();
	locked = false;
    }

    public boolean isErrorInExpression() {
	return (refNode == null);
    }

    public ProtoTaskConditionExpression getProtoTaskConditionExpression() {
	return protoTaskConditionExpression;
    }

    public void setProtoTaskConditionExpression(
	    ProtoTaskConditionExpression protoTaskConditionExpression) {
	this.protoTaskConditionExpression = protoTaskConditionExpression;
    }
}

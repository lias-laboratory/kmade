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

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;

/**
 * @author Mickael BARON
 */
public class SetFunction extends BinaryFunction implements ConcreteObjectType {

    private static final long serialVersionUID = -3249866164189644951L;

    private ObjetConcret refUserObjetConcret;

    public SetFunction(GroupExpressExpression left, AssignmentOperator right) {
	super(new Object(), left, right);
	this.name = "set";
    }

    // V�rifie si le groupe a un type set
    public boolean isGroupSetType() {
	return (((GroupExpressExpression) this.leftNode).getGroup()
		.getEnsemble() instanceof EnsembleAg);
    }

    public boolean isGroupArrayType() {
	return (((GroupExpressExpression) this.leftNode).getGroup()
		.getEnsemble() instanceof TableauAg);
    }

    // R�cup�re la liste compl�te des objets concrets
    public ArrayList<ObjetConcret> getConcreteObjects() {
	return (((GroupExpressExpression) this.leftNode).getGroup()
		.getEnsemble().getLstObjConcrets());
    }

    // Retourne null si il n'y en a pas ou s'il s'agit d'un ensemble avec plus
    // d'un objet concret
    public ObjetConcret getConcreteObject() {
	return ((GroupExpressExpression) this.leftNode).getGroup()
		.getEnsemble().get();
    }

    public void setUserConcreteObject(ObjetConcret p) {
	this.refUserObjetConcret = p;
    }

    public ObjetConcret getUserConcreteObject() {
	return this.refUserObjetConcret;
    }

    public boolean isEmptyUserConcreteObject() {
	return this.refUserObjetConcret == null;
    }

    public ArrayList<Object> getLinearExpression() {
	ArrayList<Object> myLinearList = new ArrayList<Object>();
	myLinearList.add(this);
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

    public void checkNode() throws SemanticException {
	super.checkNode();
	InterfaceExpressJava
		.getCurrentObject()
		.setCurrentCheckGroup(
			((AttributExpressExpression) ((AssignmentOperator) this.rightNode)
				.getLeftNode()).getGroupExpressExpression()
				.getGroup());
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {

	if (!this.isGroupSetType() && !this.isGroupArrayType()) {
	    refUserObjetConcret = this.getConcreteObject();
	}

	super.evaluateNode(refUserObjetConcret);
	if (this.isValueState()) {
	    InterfaceExpressJava.appendHistoryMessage(this.name);
	    InterfaceExpressJava.getCurrentObject()
		    .setCurrentEvaluateConcreteObject(refUserObjetConcret);
	}
    }
}

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

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class CreateFunction extends BinaryFunction {

    private static final long serialVersionUID = 6628594676613676862L;

    public CreateFunction(GroupExpressExpression left, NodeExpression right) {
	super(false, left, right);
	this.name = ExpressConstant.CREATE_FUNCTION_EXPRESSION;
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
	super.evaluateNode(ref);

	ObjetConcret myRef = null;
	Oid oidObjConc = InterfaceExpressJava.createEntity(
		ExpressConstant.METAOBJECT_PACKAGE,
		ExpressConstant.CONCRETE_OBJECT_CLASS);
	ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava
		.prendre(oidObjConc);
	concreteObject.setName(this.rightNode.getNodeValue().toString());
	// Le même objet abstrait
	concreteObject.setUtiliseParClass(((GroupExpressExpression) leftNode)
		.getGroup().getContientObj());
	// Le groupe spécifié par cette fonction
	concreteObject.setAppartientGroupe(((GroupExpressExpression) leftNode)
		.getGroup());

	ArrayList<AttributAbstrait> listattributabs = concreteObject
		.getUtiliseParClass().getInverseAttributsAbs();
	for (int i = 0; i < listattributabs.size(); i++) {
	    Oid oidAttribut = InterfaceExpressJava.createEntity(
		    ExpressConstant.METAOBJECT_PACKAGE,
		    ExpressConstant.CONCRETE_ATTRIBUTE_CLASS);
	    AttributConcret concreteAttribut = (AttributConcret) InterfaceExpressJava
		    .prendre(oidAttribut);
	    AttributAbstrait abstractAttribut = listattributabs.get(i);
	    concreteAttribut.setObjConcDe(concreteObject);
	    concreteAttribut.setAttributDe(abstractAttribut);
	    concreteAttribut.setName(abstractAttribut.getName());
	    concreteAttribut.setInitValeur();
	}
	myRef = concreteObject;
	this.setStateToValue();

	if (this.isValueState()) {
	    InterfaceExpressJava.appendHistoryMessage(this.name);
	    InterfaceExpressJava.getCurrentObject()
		    .setCurrentEvaluateConcreteObject(myRef);
	}
    }
}

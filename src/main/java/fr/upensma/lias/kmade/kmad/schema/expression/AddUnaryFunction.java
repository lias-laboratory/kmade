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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class AddUnaryFunction extends UnaryFunction {

    private static final long serialVersionUID = 7194395202228059922L;

    public AddUnaryFunction(GroupExpressExpression pnode) {
	super(false, pnode);
	this.name = ExpressConstant.ADD_UNARY_FUNCTION_EXPRESSION;
    }

    public void checkNode() throws SemanticException {
	try {
	    ObjetAbstrait groupeStored = InterfaceExpressJava
		    .getCurrentObject()

		    .getCurrentCheckGroup().getContientObj();
	    ObjetAbstrait groupeAttributed = ((GroupExpressExpression) myAloneExpression)
		    .getGroup().getContientObj();

	    if (!groupeStored.getName().equals(groupeAttributed.getName())) {
		throw new SemanticException(
			ExpressConstant.NO_SAME_ABSTRACT_OBJECT + " : \""
				+ groupeStored.getName() + "\" et " + "\""
				+ groupeAttributed.getName() + "\"");
	    }
	} catch (Exception e) {
	    System.err.println("KMC");
	    e.printStackTrace();
	}
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {

	ObjetConcret myRef = ref;
	if (InterfaceExpressJava.getCurrentObject()
		.isExistCurrentEvaluateConcreteObject()) {
	    myRef = InterfaceExpressJava.getCurrentObject()
		    .getCurrentEvaluateConcreteObject();
	} else {
	    myRef = null;
	}

	if (myRef == null) {
	    this.setStateToError();
	    throw new SemanticErrorException();
	}

	ObjetAbstrait groupeStored = InterfaceExpressJava.getCurrentObject()
		.getCurrentCheckGroup().getContientObj();
	ObjetAbstrait groupeAttributed = ((GroupExpressExpression) myAloneExpression)
		.getGroup().getContientObj();

	if (groupeStored.getName().equals(groupeAttributed.getName())) {
	    // Création de l'objet concret
	    Oid oidObjConc = InterfaceExpressJava.createEntity(
		    ExpressConstant.METAOBJECT_PACKAGE,
		    ExpressConstant.CONCRETE_OBJECT_CLASS);
	    // Récupère l'objet Abstrait
	    ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava
		    .prendre(groupeStored.getOid());
	    // Récupère l'instance de l'objet concret
	    ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava
		    .prendre(oidObjConc);
	    concreteObject.setName(InterfaceExpressJava.getCurrentObject()
		    .getCurrentEvaluateConcreteObject().getName());
	    // Récupère le groupe
	    Groupe g = (Groupe) InterfaceExpressJava
		    .prendre(((GroupExpressExpression) myAloneExpression)
			    .getGroup().getOid());
	    // Le même objet abstrait
	    concreteObject.setUtiliseParClass(abstractObject);
	    // Le groupe spécifié par cette fonction
	    concreteObject.setAppartientGroupe(g);
	    // La partie liée aux objets concrets
	    ArrayList<AttributAbstrait> listattributabs = abstractObject
		    .getInverseAttributsAbs();
	    boolean etat = false;
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
		etat = etat
			| concreteAttribut.setValeur(InterfaceExpressJava
				.getCurrentObject()
				.getCurrentEvaluateConcreteObject()
				.getInverseListAttribut().get(i).getValue()
				.toString());
	    }

	    if (etat) {
		this.setStateToError();
		throw new SemanticErrorException();
	    } else {
		myRef = concreteObject;
		this.setStateToValue();
	    }
	} else {
	    this.setStateToError();
	    throw new SemanticErrorException();
	}

	if (this.isValueState()) {
	    InterfaceExpressJava.appendHistoryMessage(this.name);
	    InterfaceExpressJava.getCurrentObject()
		    .setCurrentEvaluateConcreteObject(myRef);
	}
    }
}

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

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ValeurType;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;

/**
 * @author Mickael BARON
 */
public class AttributExpressExpression extends ExpressExpression {

    private static final long serialVersionUID = -4901763108260815664L;

    /**
     * Un attribut doit connaitre son groupe.  
     */    
    private GroupExpressExpression refGroup;

    private AttributAbstrait refAttrAbstrait;

    private final boolean groupKnown;

    public AttributExpressExpression(String ie, GroupExpressExpression ge)
	    throws ExpressException {
	super(ie);
	this.refGroup = ge;
	this.setGroup(ge, ie);
	this.groupKnown = true;
    }

    public AttributExpressExpression(String ie) {
	super(ie);
	this.refGroup = null;
	groupKnown = false;
    }

    private void setGroup(GroupExpressExpression refGroup, String ie)
	    throws ExpressException {
	refAttrAbstrait = ExpressAbstractAttribut
		.getRefAbstractAttributFromName(refGroup.getGroup(), ie);
	if (refAttrAbstrait == null) {
	    throw new ExpressException(ExpressConstant.ATTRIBUT_ABSTRAIT
		    + " \"" + this.identExpress + "\" "
		    + ExpressConstant.FROM_GROUP_MESSAGE + " \""
		    + refGroup.identExpress + "\" "
		    + ExpressConstant.NOT_FOUNDED_ERROR + ".");
	}

	// Maintenant il faut déterminer le type de l'attribut.
	TypeStructure typeStruct = refAttrAbstrait.getTypeStructure();
	if (typeStruct.equals(TypeStructure.ENUM_STRUCT)) {
	    // Un énuméré n'est pas une chaine de caractères?
	    this.setNodeType("");
	    return;
	} else if (typeStruct.equals(TypeStructure.INTERVAL_STRUCT)) {
	    // Un intervalle n'est pas un entier?
	    this.setNodeType(0);
	    return;
	} else if (typeStruct.equals(TypeStructure.BOOLEAN_STRUCT)) {
	    this.setNodeType(false);
	    return;
	} else if (typeStruct.equals(TypeStructure.STRING_STRUCT)) {
	    this.setNodeType("");
	    return;
	} else if (typeStruct.equals(TypeStructure.NUMBER_STRUCT)) {
	    this.setNodeType(0);
	    return;
	}
	this.setNodeType(new Object());
	throw new ExpressException(
		ExpressConstant.ATTRIBUT_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE);
    }

    public void setGroup(GroupExpressExpression refGroup)
	    throws ExpressException {
	this.refGroup = refGroup;
	this.setGroup(refGroup, identExpress);
    }

    public GroupExpressExpression getGroupExpressExpression() {
	return this.refGroup;
    }

    public AttributAbstrait getAbstractAttribut() {
	return refAttrAbstrait;
    }

    public void checkNode() throws SemanticException {
	// Vérifie s'il existe dans InterfaceExpressJava un objet concret.
	if (!this.groupKnown) {
	    if (InterfaceExpressJava.getCurrentObject()
		    .isExistCurrentCheckGroup()) {
		Groupe myGroupe = InterfaceExpressJava.getCurrentObject()
			.getCurrentCheckGroup();
		try {
		    GroupExpressExpression myExpression = new GroupExpressExpression(
			    myGroupe.getName().toLowerCase());
		    this.setGroup(myExpression);
		} catch (ExpressException e) {
		    throw new SemanticException(e.getMessage());
		}
	    } else {
		throw new SemanticException(
			ExpressConstant.GROUP_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE);
	    }
	}
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {

	if (ref == null) {
	    this.setStateToUnknown();
	    throw new SemanticUnknownException();
	} else {
	    ValeurType myValue = (ValeurType) (ref.getAttribut(refAttrAbstrait)
		    .getValue());
	    if (myValue == null) {
		this.setStateToError();
		throw new SemanticErrorException();
	    } else {
		this.setNodeValue(myValue.getValeur());
	    }
	}
    }
}

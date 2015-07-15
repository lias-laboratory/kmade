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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class ExpressConcreteAttribut {
    public static ArrayList<AttributConcret> getConcreteAttr() {
	ArrayList<AttributConcret> lst = new ArrayList<AttributConcret>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.METAOBJECT_PACKAGE,
		ExpressConstant.CONCRETE_ATTRIBUTE_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    AttributConcret obj = (AttributConcret) objs[i];
	    lst.add(obj);
	}
	return lst;
    }

    /**
     * Cette méthode permet de créer des attributs concrets
     * 
     * @param ObjConc
     * @param objAbs
     */
    public static void createConcreteAttribut(ObjetConcret ObjConc,
	    ObjetAbstrait objAbs) {
	ArrayList<AttributAbstrait> listattributabs = objAbs
		.getInverseAttributsAbs();
	for (int i = 0; i < listattributabs.size(); i++) {
	    Oid oidAttribut = InterfaceExpressJava.createEntity(
		    ExpressConstant.METAOBJECT_PACKAGE,
		    ExpressConstant.CONCRETE_ATTRIBUTE_CLASS);
	    AttributConcret concreteAttribut = (AttributConcret) InterfaceExpressJava
		    .prendre(oidAttribut);
	    AttributAbstrait abstractAttribut = listattributabs.get(i);
	    concreteAttribut.setObjConcDe(ObjConc);
	    concreteAttribut.setAttributDe(abstractAttribut);
	    concreteAttribut.setName(abstractAttribut.getName());
	    concreteAttribut.setInitValeur();
	}
    }

    /**
     * Cette méthode permet d'ajouter des attributs à un objet Concret déjà créé
     * 
     * @param attributabs
     * @param ObjConc
     */
    public static void addConcreteAttribut(AttributAbstrait attributabs,
	    ObjetConcret ObjConc) {
	Oid oidAttribut = InterfaceExpressJava.createEntity(
		ExpressConstant.METAOBJECT_PACKAGE,
		ExpressConstant.CONCRETE_ATTRIBUTE_CLASS);
	AttributConcret attribut = (AttributConcret) InterfaceExpressJava
		.prendre(oidAttribut);
	attribut.setObjConcDe(ObjConc);
	attribut.setAttributDe(attributabs);
	attribut.setInitValeur();
    }

    /**
     * Extrait à partir d'un objet concret la liste des attributs concrets et de
     * ses valeurs.
     * 
     * @param ObjConcref
     * @return pour chaque item {le nom de l'attribut abstrait , le type, la
     *         valeur(ou null), oid de l'attribut}
     */
    public static ArrayList<Object[]> getConcreteAttribut(Oid ObjConcref) {
	ObjetConcret ObjConc = (ObjetConcret) InterfaceExpressJava
		.prendre(ObjConcref);
	ArrayList<AttributConcret> listattribut = ObjConc
		.getInverseListAttribut();
	ArrayList<Object[]> myConcreteAttribut = new ArrayList<Object[]>();
	for (int i = 0; i < listattribut.size(); i++) {
	    Object[] myTab = new Object[4];
	    AttributConcret concreteAttribut = listattribut.get(i);
	    myTab[0] = concreteAttribut.getDeriveName();
	    myTab[1] = concreteAttribut.getAttributAbsDe().getTypeStructure()
		    .getValue();
	    if (concreteAttribut.isAttributeNull()) {
		myTab[2] = null;
	    } else {
		myTab[2] = concreteAttribut.getValue();
	    }
	    myTab[3] = concreteAttribut.getOid().get();
	    myConcreteAttribut.add(myTab);
	}
	return myConcreteAttribut;
    }

    /**
     * Change la valeur de l'attribut concret
     * 
     * @param oid
     *            l'oid de l'attribut
     * @param valeur
     *            la valeur de l'attribut
     * @return
     */
    public static boolean setConcreteAttributValue(String oid, String valeur) {
	AttributConcret m = (AttributConcret) InterfaceExpressJava
		.prendre(new Oid(oid));
	return m.setValeur(valeur);
    }
}

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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class ExpressConcreteObject {    
    public static ArrayList<ObjetConcret> getConcreteObj() {
	ArrayList<ObjetConcret> lst = new ArrayList<ObjetConcret>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE, 
			ExpressConstant.CONCRETE_OBJECT_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    ObjetConcret obj = (ObjetConcret) objs[i];
	    lst.add(obj);
	}
	return lst;
    }

    public static ArrayList<String> getConcreteObjName() {
	ArrayList<String> lst = new ArrayList<String>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE, 
			ExpressConstant.CONCRETE_OBJECT_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    ObjetConcret obj = (ObjetConcret) objs[i];
	    lst.add(obj.getName());
	}
	return lst;
    }

    /**
     * Cette méthode permet de créer un objet concret et par conséquent ses
     * attributs concrets.
     * 
     * @param oidObjAbs
     * @param oidG
     * @return
     */
    public static String createConcreteObject(Oid oidObjAbs, Oid oidG) {
	Oid oidObjConc = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE, 
			ExpressConstant.CONCRETE_OBJECT_CLASS);

	ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava
		.prendre(oidObjAbs);
	ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava
		.prendre(oidObjConc);
	Groupe g = (Groupe) InterfaceExpressJava.prendre(oidG);
	// L'ordre à une importance.
	concreteObject.setUtiliseParClass(abstractObject);
	concreteObject.setAppartientGroupe(g);
	// Créer les attributs a partir des attributs abstraits de
	// abstractObject.
	ExpressConcreteAttribut.createConcreteAttribut(concreteObject,
		abstractObject);
	return (oidObjConc.get());
    }

    public static void removeConcreteObject(String oid) {
	ObjetConcret g = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(
		oid));
	g.delete();
    }

    public static void displayRemoveConcreteObject(String oid) {
	ObjetConcret g = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(
		oid));
	g.affDelete();
    }

    public static String setConcreteObjectName(String oid, String name) {
	ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setName(name);
	return m.getName();
    }

    public static void setConcreteObjectDescription(String oid,
	    String description) {
	ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setDescription(description);
    }

    public static void setConcreteObjectGroup(String oid, String g) {
	ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setAppartientGroupe(ExpressGroup.stringToGroup(g));
    }

    public static ObjetConcret getObjetConcret(String name) {
	ArrayList<ObjetConcret> lstConcreteObj = getConcreteObj();
	for (int i = 0; i < lstConcreteObj.size(); i++) {
	    ObjetConcret obj = (ObjetConcret) lstConcreteObj.get(i);
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	return null;
    }

    public static String setConcreteObjectName(ObjetConcret ref, String name) {
	ref.setName(name);
	return ref.getName();
    }

    public static void setConcreteObjectDescription(ObjetConcret ref,
	    String description) {
	ref.setDescription(description);
    }

    /**
     * Cette méthode permet de créer un objet concret et par conséquent ses
     * attributs concrets.
     * 
     * @return
     */
    public static ObjetConcret createConcreteObject(Oid oidObjAbs, Groupe g,
	    String concreteObjectName) {
	Oid oidObjConc = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE, 
			ExpressConstant.CONCRETE_OBJECT_CLASS);

	ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava
		.prendre(oidObjAbs);
	ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava
		.prendre(oidObjConc);

	// L'ordre à une importance.
	concreteObject.setUtiliseParClass(abstractObject);
	concreteObject.setAppartientGroupe(g);
	// Créer les attributs a partir des attributs abstraits de
	// abstractObject.
	ExpressConcreteAttribut.createConcreteAttribut(concreteObject,
		abstractObject);
	concreteObject.setName(concreteObjectName);
	return concreteObject;
    }
}

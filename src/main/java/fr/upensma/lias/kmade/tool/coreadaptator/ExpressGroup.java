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
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Agregat;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AgregatStructure;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ListeAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.PileAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.UniqAg;

/**
 * @author Mickael BARON
 */
public class ExpressGroup {
    public static String createGroup(Oid oidObjAbs) {
	Oid oidG = InterfaceExpressJava.createEntity("metaobjet", "Groupe");
	ObjetAbstrait ObjAbs = (ObjetAbstrait) InterfaceExpressJava
		.prendre(oidObjAbs);
	Groupe g = (Groupe) InterfaceExpressJava.prendre(oidG);

	Oid oidLst = InterfaceExpressJava.createEntity("metaobjet", "ListeAg");
	ListeAg lst = (ListeAg) InterfaceExpressJava.prendre(oidLst);
	g.setEnsemble(lst);
	g.setContientObj(ObjAbs);
	return (oidG.get());
    }

    public static void removeGroup(String oid) {
	Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
	g.removeGroup();
    }

    public static void affRemoveGroup(String oid) {
	Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
	g.affDelete();
    }

    public static Groupe stringToGroup(String name) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet",
		"Groupe");
	for (int i = 0; i < objs.length; i++) {
	    Groupe obj = (Groupe) objs[i];
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	return null;
    }

    public static String stringToOid(String name) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet",
		"Groupe");
	for (int i = 0; i < objs.length; i++) {
	    Groupe obj = (Groupe) objs[i];
	    if (obj.getName().equals(name)) {
		return obj.oid.get();
	    }
	}
	return null;
    }

    public static String setGroupName(String oid, String name) {
	Groupe m = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
	m.setName(name);
	return m.getName();

    }

    public static void setGroupDescription(String oid, String desc) {
	Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
	if (g == null) {
	    return;
	}
	g.setDescription(desc);
    }

    public static boolean affRemoveAgregat(String oid, String ensemble) {
	Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));
	if (ensemble.equals(AgregatStructure.SINGLETON_AGREGAT.getValue())) {
	    if (!(g.getEnsemble() instanceof UniqAg)) {
		return ExpressGroup.removeConcreteObjectsToSingletonAgregat(g
			.getEnsemble());
	    }
	}
	return false;
    }

    public static boolean removeConcreteObjectsToSingletonAgregat(
	    Agregat myAgregat) {
	boolean concreteObject = false;
	InterfaceExpressJava.getGestionWarning().addMessage(
		myAgregat.oid,
		14,
		ExpressConstant.FROM_GROUP_MESSAGE + " \""
			+ myAgregat.getInverseGroupe().getName() + "\"");

	for (int i = 1; i < myAgregat.getLstObjConcrets().size(); i++) {
	    ObjetConcret obj = myAgregat.getLstObjConcrets().get(i);
	    obj.affDelete();
	    concreteObject = true;
	}

	return concreteObject;
    }

    public static void removeOldAgregatAndCreateNewAgregat(String oid,
	    String ensemble) {
	Groupe g = (Groupe) InterfaceExpressJava.prendre(new Oid(oid));

	// Création du nouvel agrégat
	Agregat myAgregat = ExpressGroup.createNewGroupType(ensemble);
	// On déplace les objets concrets de l'ancien agregat dans le nouveau
	myAgregat.moveConcreteObject(g.getEnsemble().getLstObjConcrets());
	// Suppression de l'ancien agregat
	g.getEnsemble().delete();
	// Affectation du nouveau agregat au groupe.
	g.setEnsemble(myAgregat);
    }

    private static Agregat createNewGroupType(String ensemble) {
	Agregat myAgregat = null;
	if (ensemble.equals(AgregatStructure.LIST_AGREGAT.getValue())) {
	    Oid oidLst = InterfaceExpressJava.createEntity("metaobjet",
		    "ListeAg");
	    myAgregat = (ListeAg) InterfaceExpressJava.prendre(oidLst);
	} else if (ensemble.equals(AgregatStructure.STACK_AGREGAT.getValue())) {
	    Oid oidLst = InterfaceExpressJava.createEntity("metaobjet",
		    "PileAg");
	    myAgregat = (PileAg) InterfaceExpressJava.prendre(oidLst);
	} else if (ensemble.equals(AgregatStructure.SINGLETON_AGREGAT
		.getValue())) {
	    Oid oidLst = InterfaceExpressJava.createEntity("metaobjet",
		    "UniqAg");
	    myAgregat = (UniqAg) InterfaceExpressJava.prendre(oidLst);
	} else if (ensemble.equals(AgregatStructure.SET_AGREGAT.getValue())) {
	    Oid oidLst = InterfaceExpressJava.createEntity("metaobjet",
		    "EnsembleAg");
	    myAgregat = (EnsembleAg) InterfaceExpressJava.prendre(oidLst);
	} else if (ensemble.equals(AgregatStructure.ARRAY_AGREGAT.getValue())) {
	    Oid oidLst = InterfaceExpressJava.createEntity("metaobjet",
		    "TableauAg");
	    myAgregat = (TableauAg) InterfaceExpressJava.prendre(oidLst);
	}
	return myAgregat;
    }

    public static ArrayList<Groupe> getGroups(ObjetAbstrait ref) {
	return ref.getInverseGroupe();
    }

    public static Object[][] getArrayGroups(String oidObj) {
	ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(
		oidObj));
	ArrayList<Groupe> groupes = m.getInverseGroupe();
	Object[][] tabObj = new Object[groupes.size()][4];
	for (int i = 0; i < groupes.size(); i++) {
	    Groupe g = groupes.get(i);
	    tabObj[i][0] = g.getName();
	    tabObj[i][1] = g.getDescription();
	    tabObj[i][2] = g.getEnsemble().getAgregatStructure().getValue();
	    tabObj[i][3] = g.getOid().get();
	}
	return tabObj;
    }

    public static Groupe getRefAbstractGroupFromName(String name) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet",
		"Groupe");
	for (int i = 0; i < objs.length; i++) {
	    if (((Entity) objs[i]).getName().toLowerCase().equals(name)) {
		return (Groupe) objs[i];
	    }
	}
	return null;
    }
}

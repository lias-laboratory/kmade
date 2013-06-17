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
import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;

/**
 * @author Mickael BARON
 */
public class ExpressIndividu{
    public static String createIndividu() {
	Oid oidUser = InterfaceExpressJava.createEntity("tache", ExpressConstant.PERSON_ENTITY);
	return (oidUser.get());
    }

    public static void deleteIndividu(String oid) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.delete();
    }

    public static void affRemoveIndividu(String oid) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.affDelete();
    }

    public static String[] getIndividusName() {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", ExpressConstant.PERSON_ENTITY);
	String[] lst = new String[objs.length];
	for (int i = 0; i < objs.length; i++) {
	    Person obj = (Person) objs[i];
	    lst[i] = obj.getName();
	}
	return lst;
    }

    public static ArrayList<Person> getIndividus() {
	ArrayList<Person> lst = new ArrayList<Person>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", ExpressConstant.PERSON_ENTITY);
	for (int i = 0; i < objs.length; i++) {
	    Person obj = (Person) objs[i];
	    lst.add(obj);
	}
	return lst;
    }

    public static Person getIndividuWithName(String name) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", ExpressConstant.PERSON_ENTITY);
	for (int i = 0; i < objs.length; i++) {
	    Person obj = (Person) objs[i];
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	return null;
    }

    public static String setIndividuName(String oid, String name) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.setName(name);
	return m.getName();
    }

    public static void setIndividuStatut(String oid, String st) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.setStatus(st);
    }

    public static void setIndividuRole(String oid, String r) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.setRole(r);
    }

    public static void setIndividuImage(String oid, String r) {
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	m.setImage(r);
    }

    public static void addIndividuInOrganisation(String oidIndividu,
	    String oidOrganisation) {
	Person i = (Person) InterfaceExpressJava.prendre(new Oid(
		oidIndividu));
	Organization o = (Organization) InterfaceExpressJava.prendre(new Oid(
		oidOrganisation));
	// on les ajoutes mutuellement
	i.addOrganization(o);
	o.addMember(i);
    }

    public static void removeIndividuInOrganisation(String oidIndividu,
	    String oidOrganisation) {
	Person i = (Person) InterfaceExpressJava.prendre(new Oid(
		oidIndividu));
	Organization o = (Organization) InterfaceExpressJava.prendre(new Oid(
		oidOrganisation));
	// on les ajoutes mutuellement
	i.removeOrganisation(o);
	o.removeIndividu(i);
    }

    /**
     * Cette m�thode retourne les organisations auquel appartient l'individu
     * ayant pour oid l'oid
     * 
     * @param oid
     * @return les organisations {oid, name, statut, role, image path}
     */
    public static Object[][] getOrganisationIntoTab(String oid) {
	if (oid == "") {
	    // s'il n'y a pas d'oid on renvoi un tableau sans ligne
	    Object[][] resnul = new Object[0][0];
	    return resnul;
	}
	// R�cup�ration de l'individu
	Person m;
	try {
	    m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	    // Récupération de ses organisations.
	    ArrayList<Organization> myOrga = m.getOrganisations();
	    Object[][] res = new Object[myOrga.size()][Organization
		    .toArrayLenght()];
	    for (int i = 0; i < myOrga.size(); i++) {
		res[i] = myOrga.get(i).toArray();
	    }
	    return res;
	} catch (Exception e) {
	    return new Object[0][0];
	}

    }

    /**
     * Cette m�thode retourne les organisation auquel l'individu n'appartient
     * pas
     * 
     * @param oid
     * @return les organisations {oid, name, statut, role, image path}
     */
    public static Object[][] getOtherOrganisationIntoTab(String oid) {
	if (oid == "") {
	    // s'il n'y a pas d'oid on renvoi un tableau sans ligne
	    Object[][] resnul = new Object[0][0];
	    return resnul;
	}
	// la liste � laquelle on va ajouter les organisations
	ArrayList<Object[]> restmp = new ArrayList<Object[]>();

	// R�cup�ration de l'individu
	Person m = (Person) InterfaceExpressJava.prendre(new Oid(oid));
	// R�cup�ration de toutes les organisations
	Object[] tabObj = (Object[]) InterfaceExpressJava
		.prendreAllOidOfEntity("tache", "Organisation");
	// cast des objets r�cup�r�s en organisation
	Organization[] tabOrg = new Organization[tabObj.length];
	for (int i = 0; i < tabOrg.length; i++) {
	    tabOrg[i] = (Organization) tabObj[i];
	}

	// pour toutes les organisations
	for (int i = 0; i < tabOrg.length; i++) {
	    // on regarde si l'organisation � pour membre l'individu, si non on
	    // l'ajoute � notre r�sultat
	    if (!tabOrg[i].getMembers().contains(m)) {
		restmp.add(tabOrg[i].toArray());
	    }
	}

	// construction du resultat � partir de l'arrayList
	Object[][] res = new Object[restmp.size()][Organization.toArrayLenght()];
	for (int i = 0; i < restmp.size(); i++) {
	    res[i] = restmp.get(i);
	}
	return res;
    }
}

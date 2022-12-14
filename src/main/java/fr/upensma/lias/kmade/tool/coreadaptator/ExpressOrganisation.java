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
import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;

/**
 * @author Mickael BARON
 */
public class ExpressOrganisation {
	public static String createOrganisation() {
		Oid oidUser = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		return (oidUser.get());
	}

	public static void deleteOrganisation(String oid) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}

	public static void affRemoveOrganisation(String oid) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.affDelete();
	}

	public static String[] getOrganisationsName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Organization obj = (Organization) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<Organization> getOrganisations() {
		ArrayList<Organization> lst = new ArrayList<Organization>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Organization obj = (Organization) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static Organization getOrganisationWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Organization obj = (Organization) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

	public static String setOrganisationName(String oid, String name) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setOrganisationStatut(String oid, String st) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.setStatus(st);
	}

	public static void setOrganisationRole(String oid, String r) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.setRole(r);
	}

	public static void setOrganisationImage(String oid, String r) {
		Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
		m.setImage(r);
	}

	public static void addOrganisationMember(String oidOrganisation, String oidIndividu) {
		Person i = (Person) InterfaceExpressJava.prendre(new Oid(oidIndividu));
		Organization o = (Organization) InterfaceExpressJava.prendre(new Oid(oidOrganisation));
		// on les ajoutes mutuellement
		i.addOrganization(o);
		o.addMember(i);
	}

	public static Object[][] getIndividuAddIntoTab(String oid) {
		// R??cup??ration de l'individu
		Organization o;
		try {
			o = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
			// r??cup??ration de ses individus
			ArrayList<Person> myIndi = o.getMembers();
			Object[][] res = new Object[myIndi.size()][Person.toArrayLenght()];
			for (int i = 0; i < myIndi.size(); i++) {
				res[i] = myIndi.get(i).toArray();
			}
			return res;
		} catch (Exception e) {
			return new Object[0][0];
		}
	}

	public static Object[][] getIndividuNotAddIntoTab(String oid) {
		try {
			// la liste ?? laquelle on va ajouter les individus
			ArrayList<Object[]> restmp = new ArrayList<Object[]>();

			// R??cup??ration de l'organisation
			Organization m = (Organization) InterfaceExpressJava.prendre(new Oid(oid));
			// R??cup??ration de toutes les individus
			Object[] tabObj = (Object[]) InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
					ExpressConstant.PERSON_CLASS);
			// cast des objet r??cup??r?? en individus
			Person[] tabInd = new Person[tabObj.length];
			for (int i = 0; i < tabInd.length; i++) {
				tabInd[i] = (Person) tabObj[i];
			}

			// pour toute les individus
			for (int i = 0; i < tabInd.length; i++) {
				// on regarde si l'individu appartient ?? l'organisation, si non
				// on l'ajoute ?? notre r??sultat
				if (!tabInd[i].getOrganisations().contains(m)) {
					restmp.add(tabInd[i].toArray());
				}
			}

			// construction du resultat ?? partir de l'arrayList
			Object[][] res = new Object[restmp.size()][Organization.toArrayLenght()];
			for (int i = 0; i < restmp.size(); i++) {
				res[i] = restmp.get(i);
			}
			return res;
		} catch (Exception e) {
			// s'il n'y a pas d'oid ou un oid ??rron?? on renvoi un tableau sans
			// ligne
			Object[][] resnul = new Object[0][0];
			return resnul;
		}
	}
}

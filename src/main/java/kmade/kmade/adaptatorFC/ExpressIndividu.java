package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Individu;
import kmade.nmda.schema.tache.Organisation;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class ExpressIndividu{
	public static String createIndividu() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "Individu");		
		return (oidUser.get());
	}

	public static void deleteIndividu(String oid) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}

	public static void affRemoveIndividu(String oid) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.affDelete();
	}

	public static String[] getIndividusName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Individu obj = (Individu) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<Individu> getIndividus() {
		ArrayList<Individu> lst = new ArrayList<Individu>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		for (int i = 0; i < objs.length; i++) {
			Individu obj = (Individu) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static Individu getIndividuWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		for (int i = 0; i < objs.length; i++) {
			Individu obj = (Individu) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}


	public static String setIndividuName(String oid, String name) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setIndividuStatut(String oid, String st) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.setStatut(st);
	}

	public static void setIndividuRole(String oid, String r) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.setRole(r);
	}

	public static void setIndividuImage(String oid, String r) {
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		m.setImage(r);
	}

	public static void addIndividuInOrganisation(String oidIndividu,String oidOrganisation){
		Individu i = (Individu) InterfaceExpressJava.prendre(new Oid(oidIndividu));
		Organisation o = (Organisation) InterfaceExpressJava.prendre(new Oid(oidOrganisation));
		// on les ajoutes mutuellement
		i.addToOrganization(o);
		o.addMember(i);
	}

	public static void removeIndividuInOrganisation(String oidIndividu,String oidOrganisation){
		Individu i = (Individu) InterfaceExpressJava.prendre(new Oid(oidIndividu));
		Organisation o = (Organisation) InterfaceExpressJava.prendre(new Oid(oidOrganisation));
		// on les ajoutes mutuellement
		i.removeToOrganization(o);
		o.removeToOrganization(i);
	}
	
	/**
	 * Cette méthode retourne les organisations auquel appartient l'individu ayant pour oid l'oid
	 * @param oid
	 * @return
	 * 		les organisations {oid, name, statut, role, image path}
	 */
	public static Object[][] getOrganisationIntoTab(String oid) {
		if(oid==""){
			//s'il n'y a pas d'oid on renvoi un tableau sans ligne
			Object[][] resnul = new Object[0][0]; 
			return resnul;
		}
		// Récupération de l'individu
		Individu m;
		try {
			m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
			// récupération de ses organisations
			ArrayList<Organisation> 	myOrga = m.getMemberOf();
			Object[][] res = new Object[myOrga.size()][Organisation.toArrayLenght()];
			for(int i = 0; i < myOrga.size();i++){
				res[i] = myOrga.get(i).toArray();
			}
			return res;
		} catch (Exception e) {
				return new Object[0][0];
		}

	}

	/**
	 * Cette méthode retourne les organisation auquel l'individu n'appartient pas
	 * @param oid
	 * @return
	 * 		les organisations {oid, name, statut, role, image path}
	 */
	public static Object[][] getOtherOrganisationIntoTab(String oid) {
		if(oid==""){
			//s'il n'y a pas d'oid on renvoi un tableau sans ligne
			Object[][] resnul = new Object[0][0]; 
			return resnul;
		}
		//la liste à laquelle on va ajouter les organisations
		ArrayList<Object[]> restmp = new ArrayList<Object[]>();

		// Récupération de l'individu
		Individu m = (Individu) InterfaceExpressJava.prendre(new Oid(oid));
		// Récupération de toutes les organisations
		 Object[] tabObj = (Object[])InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		 // cast des objets récupérés en organisation
		Organisation[] tabOrg = new Organisation[tabObj.length];
		for(int i =0;i<tabOrg.length;i++){
			tabOrg[i] = (Organisation)tabObj[i];
		}
		
		 // pour toutes les organisations
		for(int i =0;i<tabOrg.length;i++){
			// on regarde si l'organisation à pour membre l'individu, si non on l'ajoute à notre résultat
			if (!tabOrg[i].getMember().contains(m)){
				restmp.add(tabOrg[i].toArray());
			}
		}
		
		// construction du resultat à partir de l'arrayList
		Object[][] res = new Object[restmp.size()][Organisation.toArrayLenght()];
		for(int i =0;i<restmp.size();i++){
			res[i]=restmp.get(i);
		}
		return res;
	}

}

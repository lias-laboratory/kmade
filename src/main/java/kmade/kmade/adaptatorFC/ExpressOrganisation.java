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
public class ExpressOrganisation{
	public static String createOrganisation() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "Organisation");		
		return (oidUser.get());
	}

	public static void deleteOrganisation(String oid) {
		Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}
    
    public static void affRemoveOrganisation(String oid) {
    	Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }
    
	public static String[] getOrganisationsName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
        String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Organisation obj = (Organisation) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<Organisation> getOrganisations() {
		ArrayList<Organisation> lst = new ArrayList<Organisation>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		for (int i = 0; i < objs.length; i++) {
			Organisation obj = (Organisation) objs[i];
			lst.add(obj);
		}
		return lst;
	}
    
	public static Organisation getOrganisationWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		for (int i = 0; i < objs.length; i++) {
			Organisation obj = (Organisation) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

   
	public static String setOrganisationName(String oid, String name) {
		Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setOrganisationStatut(String oid, String st) {
		Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
		m.setStatut(st);
	}

	public static void setOrganisationRole(String oid, String r) {
		Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
		m.setRole(r);
	}
    
    public static void setOrganisationImage(String oid, String r) {
    	Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
        m.setImage(r);
    }
    
    public static void addOrganisationMember(String oidOrganisation, String oidIndividu){
    	Individu i = (Individu) InterfaceExpressJava.prendre(new Oid(oidIndividu));
    	Organisation o = (Organisation) InterfaceExpressJava.prendre(new Oid(oidOrganisation));
    	// on les ajoutes mutuellement
    	i.addToOrganization(o);
    	o.addMember(i);
    }

	public static Object[][] getIndividuAddIntoTab(String oid) {
		// Récupération de l'individu
		Organisation o;
		try {
			o = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
			// récupération de ses individus
			ArrayList<Individu> 	myIndi = o.getMember();
			Object[][] res = new Object[myIndi.size()][Individu.toArrayLenght()];
			for(int i = 0; i < myIndi.size();i++){
				res[i] = myIndi.get(i).toArray();
			}
			return res;
		} catch (Exception e) {
				return new Object[0][0];
		}
	}

	public static Object[][] getIndividuNotAddIntoTab(String oid) {
		try{
		//la liste à laquelle on va ajouter les individus
		ArrayList<Object[]> restmp = new ArrayList<Object[]>();

		// Récupération de l'organisation
		Organisation m = (Organisation) InterfaceExpressJava.prendre(new Oid(oid));
		// Récupération de toutes les individus
		 Object[] tabObj = (Object[])InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		 // cast des objet récupéré en individus
		 Individu[] tabInd = new Individu[tabObj.length];
		for(int i =0;i<tabInd.length;i++){
			tabInd[i] = (Individu)tabObj[i];
		}
		
		 // pour toute les individus
		for(int i =0;i<tabInd.length;i++){
			// on regarde si l'individu appartient à l'organisation, si non on l'ajoute à notre résultat
			if (!tabInd[i].getMemberOf().contains(m)){
				restmp.add(tabInd[i].toArray());
			}
		}
		
		// construction du resultat à partir de l'arrayList
		Object[][] res = new Object[restmp.size()][Organisation.toArrayLenght()];
		for(int i =0;i<restmp.size();i++){
			res[i]=restmp.get(i);
		}
		return res;
		}catch (Exception e) {
			//s'il n'y a pas d'oid ou un oid érroné on renvoi un tableau sans ligne
			Object[][] resnul = new Object[0][0]; 
			return resnul;
		}
	}

}

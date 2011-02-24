package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Machine;
import kmade.nmda.schema.tache.ParcMachines;

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
public class ExpressParcMachines{
	public static String createParcMachines() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "ParcMachines");		
		return (oidUser.get());
	}

	public static void deleteParcMachines(String oid) {
		
		
		ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}
    
    public static void affRemoveParcMachines(String oid) {
    	ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }
    
	public static String[] getParcMachinesName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "ParcMachines");
        String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			ParcMachines obj = (ParcMachines) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<ParcMachines> getParcsMachines() {
		ArrayList<ParcMachines> lst = new ArrayList<ParcMachines>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "ParcMachines");
		for (int i = 0; i < objs.length; i++) {
			ParcMachines obj = (ParcMachines) objs[i];
			lst.add(obj);
		}
		return lst;
	}
    
	public static ParcMachines getParcMachinesWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "ParcMachines");
		for (int i = 0; i < objs.length; i++) {
			ParcMachines obj = (ParcMachines) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

   
	public static String setParcMachinesName(String oid, String name) {
		ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setParcMachinesDescription(String oid, String desc) {
		ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(desc);
	}
	
	public static void setParcMachinesImage(String oid, String path) {
		ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
		m.setImage(path);
	}

    public static void addParcMachinesMember(String oidParcMachines, String oidMachine){
    	Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
    	ParcMachines o = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oidParcMachines));
    	// on les ajoutes mutuellement
    	i.addToParc(o);
    	o.addMember(i);
    }
    
	public static void removeMachineOfParc(String oidParc,
			String oidMachine) {
		Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
		ParcMachines o = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oidParc));
		// on les retire mutuellement
		i.removeToParc(o);
		o.removeToParc(i);
	}


	public static Object[][] getMachineIntoTab(String oid) {
		
		ParcMachines o;
		try {
			// récupération du parc
			o = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
			// récupération de ses Machines
			ArrayList<Machine> 	myMachine = o.getMember();
			Object[][] res = new Object[myMachine.size()][Machine.toArrayLenght()];
			for(int i = 0; i < myMachine.size();i++){
				res[i] = myMachine.get(i).toArray();
			}
			return res;
		} catch (Exception e) {
				return new Object[0][0];
		}
	}

	public static Object[][] getMachineNotAddIntoTab(String oid) {
		try{
		//la liste à laquelle on va ajouter les Machines
		ArrayList<Object[]> restmp = new ArrayList<Object[]>();

		// Récupération de l'ParcMachines
		ParcMachines m = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oid));
		// Récupération de toutes les Machines
		 Object[] tabObj = (Object[])InterfaceExpressJava.prendreAllOidOfEntity("tache", "Machine");
		 // cast des objet récupéré en Machines
		 Machine[] tabMach = new Machine[tabObj.length];
		for(int i =0;i<tabMach.length;i++){
			tabMach[i] = (Machine)tabObj[i];
		}
		
		 // pour toute les Machines
		for(int i =0;i<tabMach.length;i++){
			// on regarde si l'Machine appartient au ParcMachines, si non on l'ajoute à notre résultat
			if (!tabMach[i].getMemberOf().contains(m)){
				restmp.add(tabMach[i].toArray());
			}
		}
		
		// construction du resultat à partir de l'arrayList
		Object[][] res = new Object[restmp.size()][ParcMachines.toArrayLenght()];
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

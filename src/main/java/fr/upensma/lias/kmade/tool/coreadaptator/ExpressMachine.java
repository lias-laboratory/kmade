package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.kmad.schema.tache.ParcMachines;


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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class ExpressMachine{
	public static String createMachine() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "Machine");		
		return (oidUser.get());
	}

	public static void deleteMachine(String oid) {
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}
    
    public static void affRemoveMachine(String oid) {
        Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }
    
	public static String[] getMachinesName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Machine");
        String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			Machine obj = (Machine) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<Machine> getMachines() {
		ArrayList<Machine> lst = new ArrayList<Machine>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Machine");
		for (int i = 0; i < objs.length; i++) {
			Machine obj = (Machine) objs[i];
			lst.add(obj);
		}
		return lst;
	}
    
	public static Machine getMachineWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Machine");
		for (int i = 0; i < objs.length; i++) {
			Machine obj = (Machine) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}
   
	public static String setMachineName(String oid, String name) {
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setMachineDescription(String oid, String desc) {
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(desc);
	}
	
	public static void setMachineIsComputer(String oid, String iscomp) {
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		try{
			Boolean b = new Boolean(iscomp);
			m.setIsComputer(b);
		}catch(Exception e){
			//on ne fait pas de mise � jour
		}
	}
	
	public static void setMachineImage(String oid, String path) {
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		m.setImage(path);
	}
   
    public static void addMachineInParcMachines(String oidMachine,String oidParcMachines){
    	Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
    	ParcMachines o = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oidParcMachines));
    	// on les ajoutes mutuellement
    	i.addToParc(o);
    	o.addMember(i);
    }
    
	public static void removeMachineInParcMachines(String oidMachine, String oidParcMachines) {
		Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
		ParcMachines o = (ParcMachines) InterfaceExpressJava.prendre(new Oid(oidParcMachines));
		// on les ajoutes mutuellement
		i.removeToParc(o);
		o.removeToParc(i);
		
	}
    
    /**
	 * Cette m�thode retourne les pacs auquel appartient la machine ayant pour oid l'oid
	 * @param oid
	 * @return
	 * 		les parcs {oid, name, description, machines du parcs }
	 */
	public static Object[][] getParcsIntoTab(String oid) {
		// R�cup�ration de l'individu
		Machine m;
		try {
			m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
			// r�cup�ration de ses organisations
			ArrayList<ParcMachines> 	myParc = m.getMemberOf();
			Object[][] res = new Object[myParc.size()][ParcMachines.toArrayLenght()];
			for(int i = 0; i < myParc.size();i++){
				res[i] = myParc.get(i).toArray();
			}
			return res;
		} catch (Exception e) {
				return new Object[0][0];
		}

	}

	/**
	 * Cette m�thode retourne les parcs auquel la machine(oid) n'appartient pas
	 * @param oid
	 * @return
	 * 		les parcs {oid, name, description, isComputer,machines du parcs}
	 */
	public static Object[][] getOtherParcsIntoTab(String oid) {
		try {
		//la liste � laquelle on va ajouter les parcs
		ArrayList<Object[]> restmp = new ArrayList<Object[]>();

		// R�cup�ration de la machine
		Machine m = (Machine) InterfaceExpressJava.prendre(new Oid(oid));
		// R�cup�ration de tous les parcs
		 Object[] tabObj = (Object[])InterfaceExpressJava.prendreAllOidOfEntity("tache", "ParcMachines");
		 // cast des objets r�cup�r�s en organisation
		ParcMachines[] tabParc = new ParcMachines[tabObj.length];
		for(int i =0;i<tabParc.length;i++){
			tabParc[i] = (ParcMachines)tabObj[i];
		}
		
		 // pour tous les parcs
		for(int i =0;i<tabParc.length;i++){
			// on regarde si le parc � pour membre la macine, si non on l'ajoute � notre r�sultat
			if (!tabParc[i].getMember().contains(m)){
				restmp.add(tabParc[i].toArray());
			}
		}
		
		// construction du resultat � partir de l'arrayList
		Object[][] res = new Object[restmp.size()][ParcMachines.toArrayLenght()];
		for(int i =0;i<restmp.size();i++){
			res[i]=restmp.get(i);
		}
		return res;
		}catch (Exception e){
			//s'il n'y a pas d'oid ou pas d'oid, on renvoi un tableau sans ligne
			Object[][] resnul = new Object[0][0]; 
			return resnul;
		}
	}






}

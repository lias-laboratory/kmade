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
import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.kmad.schema.tache.ParkMachines;

/**
 * @author Mickael BARON
 */
public class ExpressParcMachines {
    public static String createParcMachines() {
	Oid oidUser = InterfaceExpressJava.createEntity(
		ExpressConstant.CORE_PACKAGE,
		ExpressConstant.MACHINE_PARK_CLASS);
	return (oidUser.get());
    }

    public static void deleteParcMachines(String oid) {

	ParkMachines m = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.delete();
    }

    public static void affRemoveParcMachines(String oid) {
	ParkMachines m = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.affDelete();
    }

    public static String[] getParcMachinesName() {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.CORE_PACKAGE,
		ExpressConstant.MACHINE_PARK_CLASS);
	String[] lst = new String[objs.length];
	for (int i = 0; i < objs.length; i++) {
	    ParkMachines obj = (ParkMachines) objs[i];
	    lst[i] = obj.getName();
	}
	return lst;
    }

    public static ArrayList<ParkMachines> getParcsMachines() {
	ArrayList<ParkMachines> lst = new ArrayList<ParkMachines>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.CORE_PACKAGE,
		ExpressConstant.MACHINE_PARK_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    ParkMachines obj = (ParkMachines) objs[i];
	    lst.add(obj);
	}
	return lst;
    }

    public static ParkMachines getParcMachinesWithName(String name) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.CORE_PACKAGE,
		ExpressConstant.MACHINE_PARK_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    ParkMachines obj = (ParkMachines) objs[i];
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	return null;
    }

    public static String setParcMachinesName(String oid, String name) {
	ParkMachines m = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setName(name);
	return m.getName();
    }

    public static void setParcMachinesDescription(String oid, String desc) {
	ParkMachines m = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setDescription(desc);
    }

    public static void setParcMachinesImage(String oid, String path) {
	ParkMachines m = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setImage(path);
    }

    public static void addParcMachinesMember(String oidParcMachines,
	    String oidMachine) {
	Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
	ParkMachines o = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oidParcMachines));
	// on les ajoutes mutuellement
	i.addToPark(o);
	o.addMember(i);
    }

    public static void removeMachineOfParc(String oidParc, String oidMachine) {
	Machine i = (Machine) InterfaceExpressJava.prendre(new Oid(oidMachine));
	ParkMachines o = (ParkMachines) InterfaceExpressJava.prendre(new Oid(
		oidParc));
	// on les retire mutuellement
	i.removeToPark(o);
	o.removeFromPark(i);
    }

    public static Object[][] getMachineIntoTab(String oid) {

	ParkMachines o;
	try {
	    // r�cup�ration du parc
	    o = (ParkMachines) InterfaceExpressJava.prendre(new Oid(oid));
	    // r�cup�ration de ses Machines
	    ArrayList<Machine> myMachine = o.getMembers();
	    Object[][] res = new Object[myMachine.size()][Machine
		    .toArrayLenght()];
	    for (int i = 0; i < myMachine.size(); i++) {
		res[i] = myMachine.get(i).toArray();
	    }
	    return res;
	} catch (Exception e) {
	    return new Object[0][0];
	}
    }

    public static Object[][] getMachineNotAddIntoTab(String oid) {
	try {
	    // la liste � laquelle on va ajouter les Machines
	    ArrayList<Object[]> restmp = new ArrayList<Object[]>();

	    // R�cup�ration de l'ParcMachines
	    ParkMachines m = (ParkMachines) InterfaceExpressJava
		    .prendre(new Oid(oid));
	    // R�cup�ration de toutes les Machines
	    Object[] tabObj = (Object[]) InterfaceExpressJava
		    .prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			    ExpressConstant.MACHINE_CLASS);
	    // cast des objet r�cup�r� en Machines
	    Machine[] tabMach = new Machine[tabObj.length];
	    for (int i = 0; i < tabMach.length; i++) {
		tabMach[i] = (Machine) tabObj[i];
	    }

	    // pour toute les Machines
	    for (int i = 0; i < tabMach.length; i++) {
		// on regarde si l'Machine appartient au ParcMachines, si non on
		// l'ajoute � notre r�sultat
		if (!tabMach[i].getMemberOf().contains(m)) {
		    restmp.add(tabMach[i].toArray());
		}
	    }

	    // construction du resultat � partir de l'arrayList
	    Object[][] res = new Object[restmp.size()][ParkMachines
		    .toArrayLenght()];
	    for (int i = 0; i < restmp.size(); i++) {
		res[i] = restmp.get(i);
	    }
	    return res;
	} catch (Exception e) {
	    // s'il n'y a pas d'oid ou un oid �rron� on renvoi un tableau sans
	    // ligne
	    Object[][] resnul = new Object[0][0];
	    return resnul;
	}
    }
}

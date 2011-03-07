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

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.kmad.schema.tache.Materiel;
import fr.upensma.lias.kmade.kmad.schema.tache.ParcMachines;

/**
 * @author Mickael BARON
 */
public class ExpressUserSystem {

    public static String[] getUserSystemName() {
	Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Machine");
	Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ParcMachines");
	int length = objs1.length + objs2.length;
	String[] lst = new String[length];
	for (int i = 0; i < objs1.length; i++) {
	    ParcMachines obj = (ParcMachines) objs1[i];
	    lst[i] = obj.getName();
	}
	for (int i = objs1.length; i < length; i++) {
	    Machine obj = (Machine) objs2[i - objs1.length];
	    lst[i] = obj.getName();
	}
	return lst;
    }

    public static ArrayList<Materiel> getUserSystem() {
	ArrayList<Materiel> lst = new ArrayList<Materiel>();
	Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Machine");
	Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ParcMachines");
	int length = objs1.length + objs2.length;
	for (int i = 0; i < objs1.length; i++) {
	    ParcMachines obj = (ParcMachines) objs1[i];
	    lst.add(obj);
	}
	for (int i = objs1.length; i < length; i++) {
	    Machine obj = (Machine) objs2[i - objs1.length];
	    lst.add(obj);
	}
	return lst;
    }

    public static Materiel getUserSystemWithName(String name) {
	Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Machine");
	Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ParcMachines");
	int length = objs1.length + objs2.length;
	for (int i = 0; i < objs1.length; i++) {
	    ParcMachines obj = (ParcMachines) objs1[i];
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	for (int i = objs1.length; i < length; i++) {
	    Machine obj = (Machine) objs2[i - objs1.length];
	    if (obj.getName().equals(name)) {
		return obj;
	    }
	}
	return null;
    }

    public static String setUserSystemName(String oid, String name) {
	Materiel m = (Materiel) InterfaceExpressJava.prendre(new Oid(oid));
	m.setName(name);
	return m.getName();
    }

    public static void setUserSystemDescription(String oid, String st) {
	Materiel m = (Materiel) InterfaceExpressJava.prendre(new Oid(oid));
	m.setDescription(st);
    }

    public static void setUserSystemImage(String oid, String r) {
	Materiel m = (Materiel) InterfaceExpressJava.prendre(new Oid(oid));
	m.setImage(r);
    }
}
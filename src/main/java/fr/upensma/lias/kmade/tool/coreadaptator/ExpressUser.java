package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Acteur;
import fr.upensma.lias.kmade.kmad.schema.tache.Individu;
import fr.upensma.lias.kmade.kmad.schema.tache.Organisation;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.kmad.schema.tache.User;


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
public class ExpressUser {
	// pour créer un utilisateur il faut créer soit un individu, soit une organisation
	public static String createUser() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "User");		
		return (oidUser.get());
	}
	
	
	// on supprime un individu ou une organisation
	public static void deleteUser(String oid) {
		User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}
	
    
	// on supprime un individu ou une organisation
    public static void affRemoveUser(String oid) {
        User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }
    
    
	public static String[] getUsersName() {
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		int length = objs1.length + objs2.length;
		String[] lst = new String[length];
		for (int i = 0; i < objs1.length; i++) {
			Organisation obj = (Organisation) objs1[i];
			lst[i] = obj.getName();
		}
		for (int i = objs1.length ; i < length; i++) {
			Individu obj = (Individu) objs2[i-objs1.length];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<User> getUsers() {
		ArrayList<User> lst = new ArrayList<User>();
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organisation obj = (Organisation) objs1[i];
			lst.add(obj);
		}
		for (int i = objs1.length ; i < length; i++) {
			Individu obj = (Individu) objs2[i-objs1.length];
			lst.add(obj);
		}
		return lst;
	}
    
	public static User getUserWithName(String name) {
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organisation obj = (Organisation) objs1[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		for (int i = objs1.length ; i < length; i++) {
			Individu obj = (Individu) objs2[i-objs1.length];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des users qui ne sont pas acteurs de la t�che
	 * @param oidtask
	 * @param oidTask2 
	 * @return
	 */
	public static String[] getUsersNoActorName(String oidTask) {
		ArrayList<String> lst = new ArrayList<String>();
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Individu");
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Organisation");
		Object task = InterfaceExpressJava.prendre(new Oid(oidTask));
		Tache myTask = (Tache)task;
		ArrayList<Acteur> myActor = myTask.getActeurs();
		ArrayList<User> myUser = new ArrayList<User>();
		for(int i=0;i<myActor.size();i++){
				myUser.add(myActor.get(i).getUserRef());
		}
		
		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organisation obj = (Organisation) objs1[i];
			if( !myUser.contains(obj) ){
				lst.add(obj.getName());
			}
		}
		for (int i = objs1.length ; i < length; i++) {
			Individu obj = (Individu) objs2[i-objs1.length];
			if( !myUser.contains(obj) ){
			lst.add(obj.getName());
			}
		}
		String[] r = new String[lst.size()];
		for(int i =0;i<r.length;i++){
			r[i]=lst.get(i);
		}
		return r;
	}
	
	
	public static String setUserName(String oid, String name) {
		User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setUserStatut(String oid, String st) {
		User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
		m.setStatut(st);
	}

	public static void setUserRole(String oid, String r) {
		User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
		m.setRole(r);
	}
    
    public static void setUserImage(String oid, String r) {
        User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
        m.setImage(r);
    }




}

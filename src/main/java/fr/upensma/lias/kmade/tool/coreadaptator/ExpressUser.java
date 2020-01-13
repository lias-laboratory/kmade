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
import fr.upensma.lias.kmade.kmad.schema.tache.Actor;
import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.User;

/**
 * @author Mickael BARON
 */
public class ExpressUser {
	// pour créer un utilisateur il faut créer soit un individu, soit une
	// organisation
	public static String createUser() {
		Oid oidUser = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE, ExpressConstant.USER_CLASS);
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
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.PERSON_CLASS);
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		int length = objs1.length + objs2.length;
		String[] lst = new String[length];
		for (int i = 0; i < objs1.length; i++) {
			Organization obj = (Organization) objs1[i];
			lst[i] = obj.getName();
		}
		for (int i = objs1.length; i < length; i++) {
			Person obj = (Person) objs2[i - objs1.length];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<User> getUsers() {
		ArrayList<User> lst = new ArrayList<User>();
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.PERSON_CLASS);
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organization obj = (Organization) objs1[i];
			lst.add(obj);
		}
		for (int i = objs1.length; i < length; i++) {
			Person obj = (Person) objs2[i - objs1.length];
			lst.add(obj);
		}
		return lst;
	}

	public static User getUserWithName(String name) {
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.PERSON_CLASS);
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organization obj = (Organization) objs1[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		for (int i = objs1.length; i < length; i++) {
			Person obj = (Person) objs2[i - objs1.length];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des users qui ne sont pas acteurs de la t�che
	 * 
	 * @param oidtask
	 * @param oidTask2
	 * @return
	 */
	public static String[] getUsersNoActorName(String oidTask) {
		ArrayList<String> lst = new ArrayList<String>();
		Object[] objs2 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.PERSON_CLASS);
		Object[] objs1 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ORGANIZATION_CLASS);
		Object task = InterfaceExpressJava.prendre(new Oid(oidTask));
		Task myTask = (Task) task;
		ArrayList<Actor> myActor = myTask.getActors();
		ArrayList<User> myUser = new ArrayList<User>();
		for (int i = 0; i < myActor.size(); i++) {
			myUser.add(myActor.get(i).getUserRef());
		}

		int length = objs1.length + objs2.length;
		for (int i = 0; i < objs1.length; i++) {
			Organization obj = (Organization) objs1[i];
			if (!myUser.contains(obj)) {
				lst.add(obj.getName());
			}
		}
		for (int i = objs1.length; i < length; i++) {
			Person obj = (Person) objs2[i - objs1.length];
			if (!myUser.contains(obj)) {
				lst.add(obj.getName());
			}
		}
		String[] r = new String[lst.size()];
		for (int i = 0; i < r.length; i++) {
			r[i] = lst.get(i);
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
		m.setStatus(st);
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

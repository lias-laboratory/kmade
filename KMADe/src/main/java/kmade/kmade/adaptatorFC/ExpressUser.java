package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.User;

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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class ExpressUser {
	public static String createUser() {
		Oid oidUser = InterfaceExpressJava.createEntity("tache", "User");		
		return (oidUser.get());
	}

	public static void deleteUser(String oid) {
		User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}
    
    public static void affRemoveUser(String oid) {
        User m = (User) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }
    
	public static String[] getUsersName() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "User");
        String[] lst = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			User obj = (User) objs[i];
			lst[i] = obj.getName();
		}
		return lst;
	}

	public static ArrayList<User> getUsers() {
		ArrayList<User> lst = new ArrayList<User>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "User");
		for (int i = 0; i < objs.length; i++) {
			User obj = (User) objs[i];
			lst.add(obj);
		}
		return lst;
	}
    
	public static User getUserWithName(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "User");
		for (int i = 0; i < objs.length; i++) {
			User obj = (User) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
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

package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Enumeration;


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
public class ExpressEnum {    
	public static String createEnum() {
		Oid oidEnumeration = InterfaceExpressJava.createEntity("metaobjet", "Enumeration");
		return (oidEnumeration.get());
	}

	public static void removeEnum(String oid) {
		Enumeration m = (Enumeration) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}

	public static void affRemoveEnum(String oid) {
		Enumeration m = (Enumeration) InterfaceExpressJava.prendre(new Oid(oid));
		m.affDelete();
	}
    
    public static ArrayList<Enumeration> getEnums() {
        ArrayList<Enumeration> lst = new ArrayList<Enumeration>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Enumeration");
        for (int i = 0; i < objs.length; i++) {
            Enumeration obj = (Enumeration) objs[i];
            lst.add(obj);
        }
        return lst;
    }

	public static String setEnumName(String oid, String name) {
		Enumeration m = (Enumeration) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setEnumDescription(String oid, String description) {
		Enumeration m = (Enumeration) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(description);
	}

	public static String[][] getArrayEnum() {
		Object[] enumerations = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet","Enumeration");
		if (enumerations.length == 0)
			return null;
		String[][] s = new String[enumerations.length][2];
		for (int i = 0; i < enumerations.length; i++) {
			Enumeration enumeration = (Enumeration) enumerations[i];
			s[i][0] = enumeration.getName();
			s[i][1] = enumeration.getOid().get();
		}
		return s;
	}

	public static Enumeration getEnum(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Enumeration");
		for (int i = 0; i < objs.length; i++) {
			Enumeration obj = (Enumeration) objs[i];
			if (obj.getName().equalsIgnoreCase(name)) {
				return obj;
			}
		}
		return null;
	}
}

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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Enumeration;

/**
 * @author Mickael BARON
 */
public class ExpressEnum {
	public static String createEnum() {
		Oid oidEnumeration = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.ENUMERATION_CLASS);
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
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.ENUMERATION_CLASS);
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
		Object[] enumerations = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.ENUMERATION_CLASS);
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
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.ENUMERATION_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Enumeration obj = (Enumeration) objs[i];
			if (obj.getName().equalsIgnoreCase(name)) {
				return obj;
			}
		}
		return null;
	}
}

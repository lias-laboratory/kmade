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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Intervalle;

/**
 * @author Mickael BARON
 */
public class ExpressInterval {
	public static String createInterval() {
		Oid oidIntervalle = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.RANGE_CLASS);
		return (oidIntervalle.get());
	}

	public static void removeInterval(String oid) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}

	public static void affRemoveInterval(String oid) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.affDelete();
	}

	public static String setIntervalName(String oid, String name) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setIntervalDescription(String oid, String description) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(description);
	}

	public static void setIntervalMin(String oid, Integer min) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.setMin(min);
	}

	public static void setIntervalMax(String oid, Integer max) {
		Intervalle m = (Intervalle) InterfaceExpressJava.prendre(new Oid(oid));
		m.setMax(max);
	}

	public static String[][] getArrayIntervals() {
		Object[] intervalles = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.RANGE_CLASS);
		if (intervalles.length == 0)
			return null;
		String[][] s = new String[intervalles.length][2];
		for (int i = 0; i < intervalles.length; i++) {
			Intervalle intervalle = (Intervalle) intervalles[i];
			s[i][0] = intervalle.getName();
			s[i][1] = intervalle.getOid().get();
		}
		return s;
	}

	public static ArrayList<Intervalle> getIntervals() {
		ArrayList<Intervalle> lst = new ArrayList<Intervalle>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.RANGE_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Intervalle obj = (Intervalle) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static Intervalle getInterval(String name) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.RANGE_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Intervalle obj = (Intervalle) objs[i];
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}
}

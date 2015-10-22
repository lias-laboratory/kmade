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

import java.awt.Color;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Label;

/**
 * @author Mickael BARON
 */
public final class ExpressLabel {
    public static String createLabel() {
	Oid oidEvent = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.LABEL_CLASS);
	return (oidEvent.get());
    }

    public static void setLabelDescription(String oid, String description) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	m.setDescription(description);
    }

    public static void setLabelColor(String oid, Color myColor) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	m.setColor(myColor);
    }

    public static void setLabelVisible(String oid, boolean isVisible) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	m.setVisible(isVisible);
    }

    /**
     * @param newLabelObject
     * @param value
     * @return
     */
    public static String setLabelName(String newLabelObject, String value) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(newLabelObject));
	m.setName(value);
	return m.getName();
    }

    public static ArrayList<Label> getLabels() {
	ArrayList<Label> lst = new ArrayList<Label>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.LABEL_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    Label obj = (Label) objs[i];
	    lst.add(obj);
	}
	return lst;
    }

    public static ArrayList<String> getLabelsNameList() {
	ArrayList<String> lst = new ArrayList<String>();
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.LABEL_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    Label obj = (Label) objs[i];
	    lst.add(obj.getName());
	}
	return lst;
    }

    public static String[] getLabelsNameArray() {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.LABEL_CLASS);
	String[] array = new String[objs.length];
	for (int i = 0; i < objs.length; i++) {
	    Label obj = (Label) objs[i];
	    array[i] = obj.getName();
	}
	return array;
    }

    public static Label stringToLabel(String s) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.LABEL_CLASS);
	for (int i = 0; i < objs.length; i++) {
	    Label obj = (Label) objs[i];
	    if (obj.getName().equals(s)) {
		return obj;
	    }
	}
	return null;
    }

    public static void affRemoveLabel(String oid) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	m.affDelete();
    }

    public static void removeLabel(String oidAct) {
	Label a = (Label) InterfaceExpressJava.prendre(new Oid(oidAct));
	a.delete();
    }

    /**
     * @param oidRow
     * @param boolean1
     */
    public static void setLabelColorVisible(String oidRow, boolean boolean1) {
	Label m = (Label) InterfaceExpressJava.prendre(new Oid(oidRow));
	m.setColorVisible(boolean1);
    }
}

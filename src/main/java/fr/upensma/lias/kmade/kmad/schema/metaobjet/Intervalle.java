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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Vincent LUCQUIAUD and Mickael BARON
 **/
public final class Intervalle extends TypeAbs implements Entity {

    private static final long serialVersionUID = 5401805142580604125L;

    private Integer min = null;

    private Integer max = null;

    public static final Integer MIN_DEFAULT = 0;

    public static final Integer MAX_DEFAULT = 1;

    public Intervalle() {
	this.name = "";
	this.description = "";
	this.min = 0;
	this.max = 1;
    }

    public Intervalle(String name, String description, Integer min,
	    Integer max, Oid oid) {
	this.name = name;
	this.description = description;
	if (0 < min.compareTo(max)) {
	    Integer tmp = min;
	    this.min = max;
	    this.max = tmp;
	}
	this.min = min;
	this.max = max;
	this.oid = oid;
    }

    public Intervalle(String name, Integer min, Integer max) {
	this.name = name;
	this.max = max;
	this.min = min;
    }

    public void delete() {
	for (int i = 0; i < this.inverseAttributAbs.size(); i++) {
	    AttributAbstrait attr = this.inverseAttributAbs.get(i);
	    attr.setTypeRef(null);
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 12);

	for (int i = 0; i < this.inverseAttributAbs.size(); i++) {
	    AttributAbstrait attr = this.inverseAttributAbs.get(i);
	    InterfaceExpressJava.getGestionWarning().addMessage(
		    oid,
		    12,
		    ExpressConstant.REMOVE_OF_THE_ABSTRACT_ATTRIBUT_MESSAGE
			    + " \"" + attr.getName() + "\"");
	}
    }

    public static boolean checkWhere(Intervalle intervalle) {
	return ((0 < intervalle.min.compareTo(intervalle.max)) ? true : false);
    }

    public String toSPF() {
	return (this.oid.get() + "=" + "Intervalle" + "(" + "'" + this.name
		+ "'" + "," + "'" + this.description + "'" + "," + this.min
		+ "," + this.max + ");");
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public void setMin(Integer i) {
	min = i;
    }

    public void setMax(Integer i) {
	max = i;
    }

    public Integer getMin() {
	return min;
    }

    public Integer getMax() {
	return max;
    }

    public int getTaille() {
	return max - min + 1;
    }

    public ArrayList<String> getValues() {
	ArrayList<String> values = new ArrayList<String>();
	for (int i = min; i <= max; i++) {
	    values.add(String.valueOf(i));
	}
	return values;
    }

    public void setName(String n) {
	boolean ok = false;
	int cpt = 0;
	n = n.replace(" ", "_");
	while (!ok) {
	    if (cpt != 0) {
		if (cpt == 1) {
		    n = n + "_" + String.valueOf(cpt);
		} else {
		    n = n.substring(0, n.length() - 1) + String.valueOf(cpt);
		}
	    }
	    ok = isUniqueName(n);
	    cpt++;
	}
	name = n;
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "Intervalle");
	for (int i = 0; i < objAbs.length; i++) {
	    Intervalle obj = (Intervalle) objAbs[i];
	    if (s.equalsIgnoreCase(obj.name)) {
		return false;
	    }
	}
	return true;
    }

    public static boolean isUnique() {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "Intervalle");
	for (int i = 0; i < objAbs.length; i++) {
	    Intervalle obj = (Intervalle) objAbs[i];
	    if (!isUniqueName(obj.name))
		return false;
	}
	return true;
    }

    public boolean minInfMax() {
	return (min < max);
    }

    public org.w3c.dom.Element toXML(Document doc) {
	return null;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {

    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	return this.toXML(doc);
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	// TODO Auto-generated method stub
	return this.oidIsAnyMissing(p);
    }
}

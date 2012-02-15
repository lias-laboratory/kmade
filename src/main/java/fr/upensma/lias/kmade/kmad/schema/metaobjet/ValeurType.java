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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @authors Vincent LUCQUIAUD and Mickael BARON
 **/
public abstract class ValeurType implements Entity, Cloneable {
    private static final long serialVersionUID = 5673942188790701306L;

    public Oid oid;

    public ValeurType() {
    }

    public abstract TypeStructure getType();

    public abstract Object getValeur();

    public abstract void setValeur(String s);

    public abstract String toString();

    public void delete() {
	InterfaceExpressJava.remove(oid);
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    public String getName() {
	return "";
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("typevalue");
	racine.setAttribute("idkmad", oid.get());

	Element valueElement = doc.createElement("typevalue-value");
	valueElement.setTextContent(this.toString());
	racine.appendChild(valueElement);

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public Object clone() {
	try {
	    return super.clone();
	} catch (CloneNotSupportedException e) {
	    return null;
	}
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
    	Element racine = doc.createElement("typevalue");
    	racine.setAttribute("idkmad", oid.get());

    	Element valueElement = doc.createElement("typevalue-value");
    	valueElement.setTextContent(this.toString());
    	racine.appendChild(valueElement);

    	return racine;
    }


    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {
	return false;
    }
}

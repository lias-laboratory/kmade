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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import org.w3c.dom.Document;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public class EnumValue extends ValeurType implements Entity {

	private static final long serialVersionUID = -5037423451302969688L;

	public Enumeration enumere = null;

	public Element element = null;

	public EnumValue() {
		enumere = null;
		element = null;
	}

	public EnumValue(Enumeration enumere, Element elem) {
		this.enumere = enumere;
		this.element = elem;
	}

	public EnumValue(Enumeration enumere, Element elem, Oid oid) {
		this.enumere = enumere;
		this.element = elem;
		this.oid = oid;
	}

	public void setEnumeration(Enumeration e) {
		KMADEHistoryMessageManager.printlnMessage(e.toString());
		this.enumere = e;
	}

	public void setElement(Element e) {
		KMADEHistoryMessageManager.printlnMessage(e.toString());
		this.element = e;
	}

	public String toSPF() {
		if (this.element == null) {
			if (enumere == null) {
				return (oid.get() + "=" + "EnumValue" + "(" + "Not defined" + ",$);");
			} else {
				return (oid.get() + "=" + "EnumValue" + "(" + this.enumere.getOid().get() + ",$);");
			}
		}
		if (enumere == null) {
			return (oid.get() + "=" + "EnumValue" + "(" + "Not defined" + "," + this.element.getOid().get() + ");");
		} else {
			return (oid.get() + "=" + "EnumValue" + "(" + this.enumere.getOid().get() + ","
					+ this.element.getOid().get() + ");");
		}
	}

	public String toString() {
		if (this.element != null) {
			return this.element.getName();
		} else {
			return "?";
		}
	}

	public TypeStructure getType() {
		return TypeStructure.ENUM_STRUCT;
	}

	public Object getValeur() {
		if (this.element != null) {
			return this.element.getName();
		} else {
			return null;
		}
	}

	public void setValeur(String s) {
		for (int i = 0; i < enumere.getInverseElementDe().size(); i++) {
			Element elem = enumere.getInverseElementDe().get(i);
			if (elem.getName().equals(s)) {
				element = elem;
			}
		}
	}

	public void modify(String s) {
		this.element = new Element(s, this.enumere);
	}

	public int getTaille() {
		return this.element.getTaille();
	}

	public String getName() {
		String retValue;

		retValue = super.getName();
		return retValue;
	}

	public org.w3c.dom.Element toXML(Document doc) {
		return null;
	}

	public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
		return false;
	}

	public void createObjectFromXMLElement(org.w3c.dom.Element p) {

	}

	public Object clone() {
		ValeurType clone = new StrValue();
		clone.oid = this.oid;
		return clone;
	}

	@Override
	public org.w3c.dom.Element toXML2(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createObjectFromXMLElement2(org.w3c.dom.Element p) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {
		return false;
	}
}

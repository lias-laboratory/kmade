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

import java.util.ArrayList;

import org.w3c.dom.Document;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class ListeAg extends Agregat implements Entity {

	private static final long serialVersionUID = -1653901133419278678L;

	public ListeAg() {
		this.lstObjConcrets = new ArrayList<ObjetConcret>();
		this.agregatStruct = AgregatStructure.LIST_AGREGAT;
	}

	public ListeAg(Oid oid) {
		this.lstObjConcrets = new ArrayList<ObjetConcret>();
		this.agregatStruct = AgregatStructure.LIST_AGREGAT;
		this.oid = oid;
	}

	public ListeAg(ArrayList<ObjetConcret> lst, Oid oid) {
		this.lstObjConcrets = lst;
		this.oid = oid;
		this.agregatStruct = AgregatStructure.LIST_AGREGAT;
	}

	public ObjetConcret get() {
		try {
			return this.lstObjConcrets.get(0);
		} catch (java.lang.IndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean put(ObjetConcret i) {
		this.lstObjConcrets.add(i);
		return true;
	}

	public String toSPF() {
		String SPF = this.oid.get() + "=ListeAg()";
		return SPF;
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public Oid getOid() {
		return oid;
	}

	public String getName() {
		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.LIST_AGREGAT.getValue());
	}

	public org.w3c.dom.Element toXML(Document doc) {
		org.w3c.dom.Element racine = super.toXML(doc);
		racine.setAttribute("classkmad", "metaobjet.ListeAg");
		return racine;
	}

	public org.w3c.dom.Element toXML2(Document doc) {
		org.w3c.dom.Element racine = super.toXML2(doc);
		racine.setAttribute("classkmad", "metaobjet.ListeAg");
		return racine;
	}
}

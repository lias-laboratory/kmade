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

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Delphine AUTARD
 * @author Mickael BARON
 */
public class EnsembleAg extends Agregat {

	private static final long serialVersionUID = 920371404772169860L;

	public EnsembleAg() {
		this.lstObjConcrets = new ArrayList<ObjetConcret>();
		this.agregatStruct = AgregatStructure.SET_AGREGAT;
	}

	public EnsembleAg(Oid oid) {
		this.lstObjConcrets = new ArrayList<ObjetConcret>();
		this.agregatStruct = AgregatStructure.SET_AGREGAT;
		this.oid = oid;
	}

	public EnsembleAg(ArrayList<ObjetConcret> lst, Oid oid) {
		this.lstObjConcrets = lst;
		this.oid = oid;
		this.agregatStruct = AgregatStructure.SET_AGREGAT;
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public boolean put(ObjetConcret i) {
		this.lstObjConcrets.add(i);
		return true;
	}

	public ObjetConcret get() {
		if (lstObjConcrets.size() == 1) {
			return lstObjConcrets.get(0);
		}
		return null;
	}

	public String toSPF() {
		String SPF = this.oid.get() + "=EnsembleAg();";
		return SPF;
	}

	public org.w3c.dom.Element toXML(Document doc) {
		org.w3c.dom.Element racine = super.toXML(doc);
		racine.setAttribute("classkmad", "metaobjet.EnsembleAg");
		return racine;
	}

	public org.w3c.dom.Element toXML2(Document doc) {
		return this.toXML(doc);
	}

	public String getName() {
		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.SET_AGREGAT.getValue());
	}

}

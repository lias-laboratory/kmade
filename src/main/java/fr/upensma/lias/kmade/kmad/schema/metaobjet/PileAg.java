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
public class PileAg extends Agregat {

    private static final long serialVersionUID = 8638149917321920353L;

    public PileAg() {
	this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public PileAg(Oid oid) {
	this.oid = oid;
	this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public PileAg(ArrayList<ObjetConcret> lst, Oid oid) {
	this.lstObjConcrets = lst;
	for (int i = 0; i < lst.size(); i++) {
	    ObjetConcret oc = lst.get(i);
	    oc.setAppartientGroupe(this.inverseGroupe);
	}
	this.oid = oid;
	this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public boolean put(ObjetConcret co) {
	this.lstObjConcrets.add(0, co);
	return true;
    }

    public ObjetConcret get() {
	return this.lstObjConcrets.get(0);
    }

    public String toSPF() {
	String SPF = this.oid.get() + "=PileAg();";
	return SPF;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	org.w3c.dom.Element racine = super.toXML(doc);
	racine.setAttribute("classkmad", "metaobjet.PileAg");
	return racine;
    }
    
    public org.w3c.dom.Element toXML2(Document doc) {
	org.w3c.dom.Element racine = super.toXML2(doc);
	racine.setAttribute("classkmad", "metaobjet.PileAg");
	return racine;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public String getName() {
	return AgregatStructure
		.getEnumereIntoLocaleAgregatStructure(AgregatStructure.STACK_AGREGAT
			.getValue());
    }
}

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

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @authors Delphine AUTARD and Mickael BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class UniqAg extends Agregat {

    private static final long serialVersionUID = 4998023325756830369L;

    public UniqAg() {
	this.lstObjConcrets = new ArrayList<ObjetConcret>(1);
	this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }

    public UniqAg(Oid oid) {
	this.lstObjConcrets = new ArrayList<ObjetConcret>(1);
	this.oid = oid;
	this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }

    public UniqAg(ArrayList<ObjetConcret> lst, Oid oid) {
	this.lstObjConcrets = lst;
	this.oid = oid;
	this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }

    public boolean put(ObjetConcret i) {
	if (this.lstObjConcrets.size() == 0) {
	    this.lstObjConcrets.add(i);
	    return true;
	} else {
	    return false;
	}
    }

    public ObjetConcret get() {
	return this.lstObjConcrets.get(0);
    }

    public String toSPF() {
	String SPF = this.oid.get() + "=UniqAg();";
	return SPF;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	org.w3c.dom.Element racine = super.toXML(doc);
	racine.setAttribute("classkmad", "metaobjet.UniqAg");
	return racine;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public String getName() {
	return AgregatStructure
		.getEnumereIntoLocaleAgregatStructure(AgregatStructure.SINGLETON_AGREGAT
			.getValue());
    }

    public void moveConcreteObject(ArrayList<ObjetConcret> p) {
	lstObjConcrets = new ArrayList<ObjetConcret>();
	if (p.size() >= 1)
	    lstObjConcrets.add(p.get(0));

	int taille = p.size();
	for (int i = 1; i < taille; i++) {
	    ObjetConcret obj = p.get(i);
	    obj.delete();
	}
    }
}

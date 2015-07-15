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
import org.w3c.dom.Element;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 * @author Thomas LACHAUME
 */
public abstract class Agregat implements Entity {

    private static final long serialVersionUID = 3620717672270513861L;

    public Oid oid = null;

    protected AgregatStructure agregatStruct;

    protected Groupe inverseGroupe = null;

    protected ArrayList<ObjetConcret> lstObjConcrets = new ArrayList<ObjetConcret>();

    public abstract ObjetConcret get();

    public abstract boolean put(ObjetConcret i);

    public boolean isEmpty() {
	return lstObjConcrets.isEmpty();
    }

    public int size() {
	return lstObjConcrets.size();
    }

    public AgregatStructure getAgregatStructure() {
	return this.agregatStruct;
    }

    public void removeFromConcreteObject(ObjetConcret o) {
	lstObjConcrets.remove(o);
    }

    public void removeAllConcreteObject() {
	lstObjConcrets = new ArrayList<ObjetConcret>();
    }

    public ArrayList<ObjetConcret> getLstObjConcrets() {
	return this.lstObjConcrets;
    }

    public void setInverseGroupe(Groupe g) {
	inverseGroupe = g;
    }

    public Groupe getInverseGroupe() {
	return inverseGroupe;
    }

    public boolean isUnique(ObjetConcret o) {
	return lstObjConcrets.indexOf(o) == -1;
    }

    public Oid getOid() {
	return oid;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("agregat");
	racine.setAttribute("classkmad", "metaobjet.ListeAg");
	racine.setAttribute("idkmad", oid.get());

	if (lstObjConcrets.size() != 0) {
	    Element idAgregatList = doc
		    .createElement("id-agregat-concreteobjects-list");
	    for (int i = 0; i < lstObjConcrets.size(); i++) {
		Element idConcreteObjects = doc
			.createElement("id-agregat-concreteobject");
		idConcreteObjects.setTextContent(lstObjConcrets.get(i).getOid()
			.get());
		idAgregatList.appendChild(idConcreteObjects);
	    }
	    racine.appendChild(idAgregatList);
	}
	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));
    }

    public void delete() {
	InterfaceExpressJava.remove(oid);
    }

    public void moveConcreteObject(ArrayList<ObjetConcret> p) {
	lstObjConcrets = new ArrayList<ObjetConcret>();
	lstObjConcrets.addAll(p);
    }

    @Override
    public Element toXML2(Document doc) {
	Element racine = doc.createElement("agregat");
	racine.setAttribute("idkmad", oid.get());
	// Concrete objects are now considered as attributes
	if (!lstObjConcrets.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < lstObjConcrets.size(); i++) {
		list += lstObjConcrets.get(i).getOid().get() + " ";
	    }
	    racine.setAttribute("id-agregat-concreteobjects-list", list);
	}
	return racine;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) {
	createObjectFromXMLElement(p);
    }

    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {
	return false;
    }
}

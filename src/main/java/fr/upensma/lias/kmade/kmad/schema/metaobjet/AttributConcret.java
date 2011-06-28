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
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class AttributConcret implements Entity, Cloneable {

    private static final long serialVersionUID = 2989569559332885070L;

    public Oid oid = null;

    private ObjetConcret objConcDe = null;

    private AttributAbstrait attributAbsDe = null;

    private ValeurType valeur = null;

    private String name;

    public AttributConcret() {
	this.name = "";
    }

    public AttributConcret(ObjetConcret i, AttributAbstrait attr,
	    ValeurType valeur) {
	this.valeur = valeur;
	this.setObjConcDe(i);
	this.setAttributDe(attr);
	this.name = attr.getName();
    }

    public AttributConcret(ObjetConcret i, AttributAbstrait attr,
	    ValeurType valeur, Oid oid) {
	this.valeur = valeur;
	this.setObjConcDe(i);
	this.setAttributDe(attr);
	this.oid = oid;
	this.name = attr.getName();
    }

    public void setObjConcDe(ObjetConcret objConcDe) {
	this.objConcDe = objConcDe;
	objConcDe.addInverseListAttribut(this);
    }

    public void setInitValeur() {
	TypeStructure typeStructure = this.attributAbsDe.getTypeStructure();

	if (valeur != null) {
	    valeur.delete();
	}

	if (typeStructure.equals(TypeStructure.STRING_STRUCT)) {
	    Oid oid = InterfaceExpressJava
		    .createEntity("metaobjet", "StrValue");
	    StrValue val = (StrValue) InterfaceExpressJava.prendre(oid);
	    val.setValeur("");
	    this.valeur = val;

	} else if (typeStructure.equals(TypeStructure.NUMBER_STRUCT)) {
	    Oid oid = InterfaceExpressJava.createEntity("metaobjet",
		    "NumberValue");
	    NumberValue val = (NumberValue) InterfaceExpressJava.prendre(oid);
	    val.setValeur("0");
	    this.valeur = val;
	} else if (typeStructure.equals(TypeStructure.BOOLEAN_STRUCT)) {
	    Oid oid = InterfaceExpressJava.createEntity("metaobjet",
		    "BoolValue");
	    BoolValue val = (BoolValue) InterfaceExpressJava.prendre(oid);
	    val.setValeur("true");
	    this.valeur = val;
	}
    }

    public boolean setValeur(String v) {
	TypeStructure typeStructure = this.attributAbsDe.getTypeStructure();

	if (typeStructure.equals(TypeStructure.STRING_STRUCT)) {
	    if (this.valeur == null) {
		Oid oid = InterfaceExpressJava.createEntity("metaobjet",
			"StrValue");
		StrValue val = (StrValue) InterfaceExpressJava.prendre(oid);
		val.setValeur(v);
		this.valeur = val;
	    } else {
		((StrValue) valeur).setValeur(v);
	    }

	} else if (typeStructure.equals(TypeStructure.NUMBER_STRUCT)) {
	    // Retourne true si la chaîne ne peut pas être un int ou un double
	    try {
		new NumberValue(v);
	    } catch (NumberFormatException e) {
		return true;
	    }
	    if (this.valeur == null) {
		Oid oid = InterfaceExpressJava.createEntity("metaobjet",
			"NumberValue");
		NumberValue val = (NumberValue) InterfaceExpressJava
			.prendre(oid);
		val.setValeur(v);
		this.valeur = val;
	    } else {
		((NumberValue) valeur).setValeur(v);
	    }
	} else if (typeStructure.equals(TypeStructure.ENUM_STRUCT)) {
	    Enumeration enu = (Enumeration) this.attributAbsDe.getTypeRef();
	    Element el = enu.getElement(v);
	    if (this.valeur == null) {
		Oid oid = InterfaceExpressJava.createEntity("metaobjet",
			"EnumValue");
		EnumValue val = (EnumValue) InterfaceExpressJava.prendre(oid);
		val.setEnumeration(enu);
		val.setElement(el);
		this.valeur = val;
	    } else {
		((EnumValue) valeur).setEnumeration(enu);
		((EnumValue) valeur).setElement(el);
	    }
	} else if (typeStructure.equals(TypeStructure.INTERVAL_STRUCT)) {
	    Intervalle inter = (Intervalle) this.attributAbsDe.getTypeRef();
	    if (this.valeur == null) {
		Oid oid = InterfaceExpressJava.createEntity("metaobjet",
			"IntervalleValue");
		IntervalleValue val = (IntervalleValue) InterfaceExpressJava
			.prendre(oid);
		val.setIntervalle(inter);
		val.setValeur(v);
		this.valeur = val;
	    } else {
		((IntervalleValue) valeur).setIntervalle(inter);
		((IntervalleValue) valeur).setValeur(v);
	    }
	} else if (typeStructure.equals(TypeStructure.BOOLEAN_STRUCT)) {
	    if (this.valeur == null) {
		Oid oid = InterfaceExpressJava.createEntity("metaobjet",
			"BoolValue");
		BoolValue val = (BoolValue) InterfaceExpressJava.prendre(oid);
		val.setValeur(v);
		this.valeur = val;
	    } else {
		((BoolValue) valeur).setValeur(v);
	    }
	}
	return false;
    }

    public void delete() {
	objConcDe.removeInverseListAttribut(this);
	attributAbsDe.removeUtiliseParAttr(this);
	if (valeur != null) {
	    valeur.delete();
	}
	InterfaceExpressJava.remove(this.oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 6);
    }

    public void setAttributDe(AttributAbstrait a) {
	this.attributAbsDe = a;
	a.setUtiliseParAttr(this);
    }

    public AttributAbstrait getAttributAbsDe() {
	return this.attributAbsDe;
    }

    public String getDeriveName() {
	return attributAbsDe.getName();
    }

    public org.w3c.dom.Element toXML(Document doc) {
	org.w3c.dom.Element racine = doc.createElement("concreteattribut");
	racine.setAttribute("classkmad", "metaobjet.AttributConcret");
	racine.setAttribute("idkmad", oid.get());

	org.w3c.dom.Element element = doc
		.createElement("id-concreteattribut-concreteobject");
	element.setTextContent(this.objConcDe.getOid().get());
	racine.appendChild(element);

	element = doc.createElement("id-concreteattribut-abstractattribut");
	element.setTextContent(this.attributAbsDe.getOid().get());
	racine.appendChild(element);

	element = doc.createElement("id-concreteattribut-value");
	element.setTextContent(this.valeur.getOid().get());
	racine.appendChild(element);

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p
		.getElementsByTagName("id-concreteattribut-concreteobject");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	userValue = p
		.getElementsByTagName("id-concreteattribut-abstractattribut");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	userValue = p.getElementsByTagName("id-concreteattribut-value");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p
		.getElementsByTagName("id-concreteattribut-concreteobject");
	this.setObjConcDe((ObjetConcret) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent())));

	nodeList = p
		.getElementsByTagName("id-concreteattribut-abstractattribut");
	this.setAttributDe((AttributAbstrait) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent())));
	this.name = this.attributAbsDe.getName();

	nodeList = p.getElementsByTagName("id-concreteattribut-value");
	if (nodeList.item(0) != null) {
	    this.valeur = (ValeurType) InterfaceExpressJava.bdd
		    .prendre(new Oid(nodeList.item(0).getTextContent()));
	}
    }

    public String toSPF() {
	String SPF = oid.get() + "=" + "AttributConcret" + "(";
	if (objConcDe != null)
	    SPF = SPF + objConcDe.getOid().get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ",";
	if (attributAbsDe != null)
	    SPF = SPF + attributAbsDe.oid.get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ",";
	if (valeur != null)
	    SPF = SPF + valeur.oid.get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ");";
	return SPF;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return this.oid;
    }

    public void setName(String n) {
	name = n;
    }

    public String getName() {
	return name;
    }

    public ObjetConcret getObjetConcDe() {
	return this.objConcDe;
    }

    public boolean isAttributeNull() {
	return (this.valeur == null);
    }

    public ValeurType getValue() {
	return this.valeur;
    }

    public String toString() {
	return this.name;
    }

    public Object clone() {
	AttributConcret clone = new AttributConcret();
	clone.oid = this.oid;
	clone.attributAbsDe = this.attributAbsDe;
	clone.valeur = (ValeurType) (this.valeur).clone();
	clone.name = this.name;
	return clone;
    }

	@Override
	public org.w3c.dom.Element toXML2(Document doc) throws Exception {
		// TODO Auto-generated method stub
		org.w3c.dom.Element racine = doc.createElement("concreteattribut");
		racine.setAttribute("classkmad", "metaobjet.AttributConcret");
		racine.setAttribute("idkmad", oid.get());

		racine.setAttribute("id-concreteattribut-concreteobject",this.objConcDe.getOid().get());

		racine.setAttribute("id-concreteattribut-abstractattribut",this.attributAbsDe.getOid().get());

		racine.setAttribute("id-concreteattribut-value",this.valeur.getOid().get());
		
		racine.appendChild(this.valeur.toXML2(doc));

		return racine;
	}

	@Override
	public void createObjectFromXMLElement2(org.w3c.dom.Element p)
			throws Exception {
		// TODO Auto-generated method stub
		this.oid = new Oid(p.getAttribute("idkmad"));

		this.setObjConcDe((ObjetConcret) InterfaceExpressJava.bdd
			.prendre(new Oid(p.getAttribute("id-concreteattribut-concreteobject"))));

		this.setAttributDe((AttributAbstrait) InterfaceExpressJava.bdd
			.prendre(new Oid(p.getAttribute("id-concreteattribut-abstractattribut"))));
		this.name = this.attributAbsDe.getName();
		
	    this.valeur = (ValeurType) InterfaceExpressJava.bdd
		    .prendre(new Oid(p.getAttribute("id-concreteattribut-value")));
		
	}

	@Override
	public boolean oidIsAnyMissing2(org.w3c.dom.Element p) throws Exception {
		// TODO Auto-generated method stub
		String userValue = p
		.getAttribute("id-concreteattribut-concreteobject");
		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null) {
			return true;
		}

		userValue = p
			.getAttribute("id-concreteattribut-abstractattribut");
		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null) {
			return true;
		}

		userValue = p.getAttribute("id-concreteattribut-value");
		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null) {
			return true;
		}
		return false;
	}
}
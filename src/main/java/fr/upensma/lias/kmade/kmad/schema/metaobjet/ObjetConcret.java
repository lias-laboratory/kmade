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
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;

/**
 * @author Mickael BARON
 */
public class ObjetConcret implements Entity, Cloneable {

    private static final long serialVersionUID = 869199920623604044L;

    public Oid oid = null;

    private String name = "";

    private String description = "";

    private ObjetAbstrait utiliseParClass = null;

    private ArrayList<AttributConcret> inverseAttribut = new ArrayList<AttributConcret>();
    
    private Point point = null;

    private Groupe appartientGroupe = null;

    public ObjetConcret() {
	this.name = "";
	this.description = "";
    }

    public ObjetConcret(String name, String description, ObjetAbstrait objAbs,
	    Oid oid) {
	this.name = name;
	this.description = description;
	this.setUtiliseParClass(objAbs);
	this.oid = oid;
	this.inverseAttribut = new ArrayList<AttributConcret>();
    }

    public ObjetConcret(String name, String description, Groupe g,
	    ObjetAbstrait objAbs, Oid oid) {
	this.name = name;
	this.description = description;
	this.setUtiliseParClass(objAbs);
	this.oid = oid;
	this.inverseAttribut = new ArrayList<AttributConcret>();
	this.setAppartientGroupe(g);
    }

    public ObjetConcret(String name, ObjetAbstrait objAbs, Groupe g) {
	this.name = name;
	this.setUtiliseParClass(objAbs);
	this.inverseAttribut = new ArrayList<AttributConcret>();
	this.setAppartientGroupe(g);
	this.description = "";
    }

    public void delete() {
	this.appartientGroupe.removeFromGroup(this);
	this.utiliseParClass.removeInverseObjConc(this);
	int taille = this.inverseAttribut.size();
	for (int i = 0; i < taille; i++) {
	    AttributConcret attr = this.inverseAttribut.get(0);
	    attr.delete();
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 5);
	for (int i = 0; i < this.inverseAttribut.size(); i++) {
	    AttributConcret attr = this.inverseAttribut.get(i);
	    attr.affDelete();
	}
    }

    public ObjetAbstrait getUtiliseParClass() {
	return this.utiliseParClass;
    }

    public void setUtiliseParClass(ObjetAbstrait objAbs) {
	this.utiliseParClass = objAbs;
	objAbs.addInverseObjConc(this);
    }

    public void setAppartientGroupe(Groupe g) {
	this.appartientGroupe = g;
	if(g != null)
	    g.addToGroup(this);
    }

    public Groupe getAppartientGroupe() {
	return this.appartientGroupe;
    }

    public void addInverseListAttribut(AttributConcret attribut) {
	this.inverseAttribut.add(attribut);
    }

    public void removeInverseListAttribut(AttributConcret attribut) {
	this.inverseAttribut.remove(attribut);
    }

    public ArrayList<AttributConcret> getInverseListAttribut() {
	return this.inverseAttribut;
    }

    public AttributConcret getAttribut(AttributAbstrait attrAbs) {
	for (int i = 0; i < this.inverseAttribut.size(); i++) {
	    AttributConcret attr = this.inverseAttribut.get(i);
	    if (attr.getAttributAbsDe() == attrAbs) {
		return attr;
	    }
	}
	return null;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("concreteobject");
	racine.setAttribute("classkmad", "metaobjet.ObjetConcret");
	racine.setAttribute("idkmad", oid.get());

	Element element = doc.createElement("concreteobject-name");
	element.setTextContent(this.getName());
	racine.appendChild(element);

	if (!this.description.equals("")) {
	    element = doc.createElement("concreteobject-description");
	    element.setTextContent(this.description);
	    racine.appendChild(element);
	}

	element = doc.createElement("id-concreteobject-abstractobject");
	element.setTextContent(this.utiliseParClass.getOid().get());
	racine.appendChild(element);

	element = doc.createElement("id-concreteobject-group");
	element.setTextContent(this.appartientGroupe.getOid().get());
	racine.appendChild(element);

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p
		.getElementsByTagName("id-concreteobject-abstractobject");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	userValue = p.getElementsByTagName("id-concreteobject-group");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("concreteobject-name");
	this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("concreteobject-description");
	if (nodeList.item(0) != null) {
	    this.description = nodeList.item(0).getTextContent();
	}

	nodeList = p.getElementsByTagName("id-concreteobject-abstractobject");
	this.setUtiliseParClass((ObjetAbstrait) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent())));

	nodeList = p.getElementsByTagName("id-concreteobject-group");
	this.setAppartientGroupe((Groupe) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent())));
    }

    public String toSPF() {
	String SPF = oid.get() + "=" + "ObjetConcret" + "(" + "'" + name + "'"
		+ "," + "'" + description + "'" + ",";
	if (appartientGroupe == null) {
	    SPF = SPF + "Pas De Groupe" + ",";
	} else {
	    SPF = SPF + appartientGroupe.oid.get() + ",";
	}
	if (utiliseParClass == null) {
	    SPF = SPF + ");";
	} else {
	    SPF = SPF + utiliseParClass.getOid().get() + ");";
	}
	return SPF;
    }

    public String toString() {
	return this.name;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String d) {
	description = d;
    }

    public void setName(String n) {
	name = n;
    }

    public void setPoint(Point point) {
	this.point = point;
    }

    public Point getPoint() {
	return point;
    }

    public int getTaille() {
	return this.name.length();
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "ObjetConcret");
	for (int i = 0; i < objAbs.length; i++) {
	    ObjetConcret obj = (ObjetConcret) objAbs[i];
	    if (s.equalsIgnoreCase(obj.name)) {
		// renommage nom objet abstrait...
		// InterfaceExpressJava.IEJ.gestionErreur.empileErreur(obj.oid,
		// 5 );
		// InterfaceExpressJava.IEJ.gestionErreur.afficherEtat();
		return false;
	    }
	}
	return true;
    }

    public Object clone() {
	ObjetConcret clone = new ObjetConcret();
	clone.oid = this.oid;
	clone.name = this.name;
	clone.description = this.description;
	clone.utiliseParClass = this.utiliseParClass;
	clone.appartientGroupe = this.appartientGroupe;
	clone.inverseAttribut = new ArrayList<AttributConcret>();

	for (int i = 0; i < inverseAttribut.size(); i++) {
	    AttributConcret temp = (AttributConcret) (inverseAttribut.get(i))
		    .clone();
	    temp.setObjConcDe(clone);
	}
	return clone;
    }

    public static String propositionNom(String n) {
	boolean ok = false;
	int cpt = 0;
	// n = n.replace(" ", "_");
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
	return n;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	Element racine = doc.createElement("concreteobject");
	racine.setAttribute("classkmad", "metaobjet.ObjetConcret");
	racine.setAttribute("idkmad", oid.get());

	racine.setAttribute("id-concreteobject-abstractobject",
		this.utiliseParClass.getOid().get());

	racine.setAttribute("id-concreteobject-group", this.appartientGroupe
		.getOid().get());

	Element element = doc.createElement("concreteobject-name");
	element.setTextContent(this.getName());
	racine.appendChild(element);

	if (!this.description.equals("")) {
	    element = doc.createElement("concreteobject-description");
	    element.setTextContent(this.description);
	    racine.appendChild(element);
	}
	
	//Add object's attributes as child
	if (!this.inverseAttribut.isEmpty()) {
	    for (int i = 0; i < inverseAttribut.size(); i++) {
		racine.appendChild(inverseAttribut.get(i).toXML2(doc));
	    }
	}
	
	if(this.point != null){
	    racine.setAttribute("id-task-point", this.point.getOid().get());
	    racine.appendChild(this.point.toXML2(doc));
	}

	return racine;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	this.oid = new Oid(p.getAttribute("idkmad"));

	this.setUtiliseParClass((ObjetAbstrait) InterfaceExpressJava.bdd
		.prendre(new Oid(p
			.getAttribute("id-concreteobject-abstractobject"))));

	this.setAppartientGroupe((Groupe) InterfaceExpressJava.bdd
		.prendre(new Oid(p.getAttribute("id-concreteobject-group"))));

	NodeList nodeList = p.getElementsByTagName("concreteobject-name");
	this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("concreteobject-description");
	if (nodeList.item(0) != null) {
	    this.description = nodeList.item(0).getTextContent();
	}
	
	// Point
	if (p.hasAttribute("id-task-point"))
	    this.point = (Point) InterfaceExpressJava.bdd.prendre(new Oid(p
		    .getAttribute("id-task-point")));
	else 
	    this.point = null;
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	String userValue = p.getAttribute("id-concreteobject-abstractobject");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null) {
	    return true;
	}

	userValue = p.getAttribute("id-concreteobject-group");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null) {
	    return true;
	}

	return false;
    }
}

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
 * @author Delphine AUTARD and Mickael BARON
 **/
public class ObjetAbstrait implements Entity {

    private static final long serialVersionUID = 7595794787552617084L;

    public Oid oid = null;

    private String name = null;

    private String description = null;
    
    private Point point;

    private ArrayList<ObjetConcret> inverseObjConcDe = null;

    private ArrayList<AttributAbstrait> inverseAttributsAbs = null;

    private ArrayList<Groupe> inverseGroupe = null;

    public ObjetAbstrait() {
	this.name = "";
	this.description = "";
	this.inverseAttributsAbs = new ArrayList<AttributAbstrait>();
	this.inverseObjConcDe = new ArrayList<ObjetConcret>();
	this.inverseGroupe = new ArrayList<Groupe>();
    }

    public ObjetAbstrait(String name, String description, Oid oid) {
	this.name = name;
	this.description = description;
	this.inverseAttributsAbs = new ArrayList<AttributAbstrait>();
	this.inverseObjConcDe = new ArrayList<ObjetConcret>();
	this.inverseGroupe = new ArrayList<Groupe>();
	this.oid = oid;
    }

    public void delete() {
	int tailleG = this.inverseGroupe.size();
	for (int i = 0; i < tailleG; i++) {
	    Groupe g = this.inverseGroupe.get(0);
	    g.removeGroup();
	    InterfaceExpressJava.remove(g.getOid());
	}
	int taille = this.inverseAttributsAbs.size();
	for (int i = 0; i < taille; i++) {
	    AttributAbstrait a = this.inverseAttributsAbs.get(0);
	    a.delete();
	    InterfaceExpressJava.remove(a.getOid());
	}
	InterfaceExpressJava.remove(this.oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 0);
	for (int i = 0; i < this.inverseGroupe.size(); i++) {
	    Groupe g = this.inverseGroupe.get(i);
	    g.affDelete();
	}
	for (int i = 0; i < this.inverseAttributsAbs.size(); i++) {
	    AttributAbstrait a = this.inverseAttributsAbs.get(i);
	    a.affDelete();
	}
    }

    public void addInverseAttributsAbs(AttributAbstrait attributabs) {
	inverseAttributsAbs.add(attributabs);
    }

    public void removeInverseAttributsAbs(AttributAbstrait attributabs) {
	inverseAttributsAbs.remove(attributabs);
    }

    public void addInverseGroupe(Groupe g) {
	this.inverseGroupe.add(g);
    }

    public void removeInverseGroupe(Groupe g) {
	this.inverseGroupe.remove(g);
    }

    public ArrayList<Groupe> getInverseGroupe() {
	return this.inverseGroupe;
    }

    public ArrayList<AttributAbstrait> getInverseAttributsAbs() {
	return this.inverseAttributsAbs;
    }

    public void addInverseObjConc(ObjetConcret ObjConc) {
	inverseObjConcDe.add(ObjConc);
    }

    public void removeInverseObjConc(ObjetConcret ObjConc) {
	inverseObjConcDe.remove(ObjConc);
    }

    public ArrayList<ObjetConcret> getInverseObjConc() {
	return this.inverseObjConcDe;
    }

    public void removeAllConcreteObject() {
	inverseObjConcDe = new ArrayList<ObjetConcret>();
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("abstractobject");
	racine.setAttribute("classkmad", "metaobjet.ObjetAbstrait");
	racine.setAttribute("idkmad", oid.get());

	Element element = doc.createElement("abstractobject-name");
	element.setTextContent(this.getName());
	racine.appendChild(element);

	if (!this.description.equals("")) {
	    element = doc.createElement("abstractobject-description");
	    element.setTextContent(this.description);
	    racine.appendChild(element);
	}

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("abstractobject-name");
	this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("abstractobject-description");
	if (nodeList.item(0) != null) {
	    this.description = nodeList.item(0).getTextContent();
	}
    }

    public String toSPF() {
	return (this.oid.get() + "=" + "ObjetAbstrait" + "(" + "'" + name + "'"
		+ "," + "'" + description + "'" + ")");
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return this.oid;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String n) {
	name = n;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String d) {
	description = d;
    }

    public int getTaille() {
	return this.name.length();
    }
    
    public void setPoint(Point p) {
	this.point = p;
    }

    public Point getPoint() {
	return point;
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "ObjetAbstrait");
	for (int i = 0; i < objAbs.length; i++) {
	    ObjetAbstrait obj = (ObjetAbstrait) objAbs[i];
	    if (s.equalsIgnoreCase(obj.name)) {
		return false;
	    }
	}
	return true;
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

    public boolean unGroupePresent() {
	return (inverseGroupe.size() != 0);
    }

    public boolean noSpace() {
	return (name.indexOf(" ") == -1);
    }

    public String toString() {
	return this.name;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = doc.createElement("abstractobject");
	racine.setAttribute("classkmad", "metaobjet.ObjetAbstrait");
	racine.setAttribute("idkmad", oid.get());

	Element element = doc.createElement("abstractobject-name");
	element.setTextContent(this.getName());
	racine.appendChild(element);

	if (!this.description.equals("")) {
	    element = doc.createElement("abstractobject-description");
	    element.setTextContent(this.description);
	    racine.appendChild(element);
	}

	//Add the object's attributes as childs
	if (!this.inverseGroupe.isEmpty()) {
	    for (int i = 0; i < this.inverseGroupe.size(); i++) {
		racine.appendChild(this.inverseGroupe.get(i).toXML2(doc));
	    }
	}

	if (!this.inverseAttributsAbs.isEmpty()) {
	    for (int i = 0; i < this.inverseAttributsAbs.size(); i++) {
		racine.appendChild(this.inverseAttributsAbs.get(i).toXML2(doc));
	    }
	}

	if (!this.inverseObjConcDe.isEmpty()) {
	    for (int i = 0; i < this.inverseObjConcDe.size(); i++) {
		racine.appendChild(this.inverseObjConcDe.get(i).toXML2(doc));
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
	// TODO Auto-generated method stub
	createObjectFromXMLElement(p);
	// Point
	if (p.hasAttribute("id-task-point")){
	    this.point = (Point) InterfaceExpressJava.bdd.prendre(new Oid(p
		    .getAttribute("id-task-point")));
	}
	else 
	    this.point = null;
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	if(p.hasAttribute("id-task-point")){
	    String nodeList = p.getAttribute("id-task-point");
	    if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
		return true;
	    }
	}
	return false;
    }
}

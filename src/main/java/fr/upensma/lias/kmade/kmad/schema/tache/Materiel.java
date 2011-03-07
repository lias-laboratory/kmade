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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Delphine AUTARD and Mickael BARON
 **/
public class Materiel implements Entity {

    private static final long serialVersionUID = 4380917017782960768L;

    public Oid oid = null;

    private String name = "";

    private String description = "";

    private String imagePath = "";

    private ArrayList<ActeurSysteme> inverseActeur = new ArrayList<ActeurSysteme>();

    public Materiel() {
	this("", "", null);
    }

    public Materiel(String name, Oid oid) {
	this(name, "", oid);
    }

    public Materiel(String name, String description, Oid oid) {
	this.name = name;
	this.description = description;
	this.oid = oid;
    }

    public void addInverseActeurSysteme(ActeurSysteme a) {
	inverseActeur.add(a);
    }

    public void removeInverseActeurSysteme(ActeurSysteme a) {
	inverseActeur.remove(a);
    }

    public void delete() {
	for (int i = 0; i < inverseActeur.size(); i++) {
	    ActeurSysteme a = inverseActeur.get(i);
	    a.delete();
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 16);
	for (int i = 0; i < inverseActeur.size(); i++) {
	    ActeurSysteme a = inverseActeur.get(i);
	    a.affDelete();
	}
    }

    public String getName() {
	return name;
    }

    public void setName(String n) {
	name = n;
    }

    public String getDescription() {
	return description;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public String toString() {
	return name;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("materiel");
	racine.setAttribute("classkmad", "tache.Materiel");
	racine.setAttribute("idkmad", oid.get());

	Element kmadUserName = doc.createElement("materiel-name");
	kmadUserName.setTextContent(this.getName());
	racine.appendChild(kmadUserName);

	if (!this.getDescription().equals("")) {
	    Element kmadUserDescription = doc
		    .createElement("materiel-description");
	    kmadUserDescription.setTextContent(this.getDescription());
	    racine.appendChild(kmadUserDescription);
	}

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadUserName = p.getElementsByTagName("materiel-name");
	if (kmadUserName.item(0) != null)
	    this.name = kmadUserName.item(0).getTextContent();

	NodeList kmadUserDescription = p
		.getElementsByTagName("materiel-description");
	if (kmadUserDescription.item(0) != null)
	    this.description = kmadUserDescription.item(0).getTextContent();
    }

    public String toSPF() {
	return oid.get() + "=Materiel('" + name + "','" + description + "');";
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Materiel");
	Object[] objAbs1 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Machine");
	Object[] objAbs2 = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ParcMachines");
	for (int i = 0; i < objAbs.length; i++) {
	    Materiel obj = (Materiel) objAbs[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	for (int i = 0; i < objAbs1.length; i++) {
	    Machine obj = (Machine) objAbs1[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	for (int i = 0; i < objAbs2.length; i++) {
	    ParcMachines obj = (ParcMachines) objAbs2[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
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

    public Oid getOid() {
	return oid;
    }

    public void setDescription(String desc) {
	this.description = desc;

    }

    public String getImage() {
	return imagePath;
    }

    public void setImage(String path) {
	imagePath = path;
    }
}
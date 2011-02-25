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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Groupe implements Entity {

    private static final long serialVersionUID = 5955358324604564174L;

    private String name = "";

    // Liste de tous les objets concrets contenus dans ce groupe
    private Agregat ensemble = new ListeAg();

    // Type d'objet abstrait 1 seul possible
    private ObjetAbstrait contientObj = null;

    private String description = "";

    public Oid oid;

    public Groupe() {
	this.name = "";
	this.description = "";
	this.ensemble = new ListeAg();
    }

    public Groupe(String nom, String d, Agregat ag, ObjetAbstrait typeObj,
	    Oid oid) {
	name = nom;
	description = d;
	this.setContientObj(typeObj);
	setEnsemble(ag);
	this.oid = oid;
    }

    public Groupe(String nom, ObjetAbstrait typeObj, Agregat ag) {
	name = nom;
	description = "";
	this.setContientObj(typeObj);
	setEnsemble(ag);
    }

    public Groupe(String nom, ObjetAbstrait typeObj, Agregat ag, Oid oid) {
	name = nom;
	description = "";
	this.setContientObj(typeObj);
	setEnsemble(ag);
	this.oid = oid;
    }

    public void removeGroup() {
	int taille = this.getEnsemble().getLstObjConcrets().size();
	for (int i = 0; i < taille; i++) {
	    ObjetConcret obj = ensemble.getLstObjConcrets().get(0);
	    obj.delete();
	    InterfaceExpressJava.remove(obj.getOid());
	}

	this.ensemble.delete();
	this.contientObj.removeInverseGroupe(this);
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(
		oid,
		2,
		ExpressConstant.REMOVE_OF_THE_ABSTRACT_OBJECT_MESSAGE + " \""
			+ contientObj.getName() + "\"");
	for (int i = 0; i < this.getEnsemble().getLstObjConcrets().size(); i++) {
	    ObjetConcret obj = ensemble.getLstObjConcrets().get(i);
	    obj.affDelete();
	}
    }

    /**
     * Ajoute un objet concret � ensemble
     * 
     * @param i
     *            un objet concret
     */
    public boolean addToGroup(ObjetConcret i) {
	if (i.getUtiliseParClass().getName().equals(this.contientObj.getName())) {
	    if (this.ensemble.isUnique(i)) {
		return this.ensemble.put(i);
	    }
	}
	return false;
    }

    public void removeFromGroup(ObjetConcret i) {
	this.ensemble.removeFromConcreteObject(i);
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

    public Agregat getEnsemble() {
	return this.ensemble;
    }

    public void setEnsemble(Agregat ag) {
	ensemble = ag;
	ag.setInverseGroupe(this);
    }

    public void setContientObj(ObjetAbstrait o) {
	this.contientObj = o;
	o.addInverseGroupe(this);
    }

    public ObjetAbstrait getContientObj() {
	return this.contientObj;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("group");
	racine.setAttribute("classkmad", "metaobjet.Groupe");
	racine.setAttribute("idkmad", oid.get());

	Element element = doc.createElement("group-name");
	element.setTextContent(this.getName());
	racine.appendChild(element);

	if (!this.description.equals("")) {
	    element = doc.createElement("group-description");
	    element.setTextContent(this.getDescription());
	    racine.appendChild(element);
	}

	element = doc.createElement("id-group-agregat");
	element.setTextContent(this.ensemble.getOid().get());
	racine.appendChild(element);

	element = doc.createElement("id-group-abstractobject");
	element.setTextContent(this.contientObj.getOid().get());
	racine.appendChild(element);

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p.getElementsByTagName("id-group-agregat");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	userValue = p.getElementsByTagName("id-group-abstractobject");
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}

	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("group-name");
	this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("group-description");
	if (nodeList.item(0) != null) {
	    this.description = nodeList.item(0).getTextContent();
	}

	nodeList = p.getElementsByTagName("id-group-agregat");
	this.setEnsemble((Agregat) InterfaceExpressJava.bdd.prendre(new Oid(
		nodeList.item(0).getTextContent())));

	nodeList = p.getElementsByTagName("id-group-abstractobject");
	this.setContientObj((ObjetAbstrait) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent())));
    }

    public String toSPF() {
	String SPF = oid.get() + "=" + "Groupe" + "(" + "'" + name + "'" + ","
		+ "'" + description + "'" + ",";
	if (ensemble.oid != null)
	    SPF = SPF + ensemble.getOid().get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ",";
	if (contientObj != null)
	    SPF = SPF + contientObj.getOid().get() + ")";
	else
	    SPF = SPF + "$);";
	return SPF;
    }

    public String toString() {
	return this.name;
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "Groupe");
	for (int i = 0; i < objAbs.length; i++) {
	    Groupe obj = (Groupe) objAbs[i];
	    if (s.equalsIgnoreCase(obj.name)) {
		return false;
	    }
	}
	return true;
    }

    public boolean noSpace() {
	return (name.indexOf(" ") == -1);
    }
}

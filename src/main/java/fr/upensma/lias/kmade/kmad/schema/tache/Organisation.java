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

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Organisation extends User {

    private static final long serialVersionUID = -4187152840289735872L;

    private ArrayList<Individu> inverseMember = new ArrayList<Individu>();

    public Organisation() {
	super();
    }

    public Organisation(String name, Oid oid) {
	super(name, "", "", "", oid);
    }

    public Organisation(String name, String st, String r, Oid oid) {
	super(name, st, r, "", oid);
    }

    public Organisation(String name, String st, String r, String pi, Oid oid) {
	super(name, st, r, pi, oid);
    }

    public void delete() {
	// supprimer l'individu des organisations
	for (int i = 0; i < inverseMember.size(); i++) {
	    inverseMember.get(i).removeToOrganization(this);
	}
	super.delete();
    }

    /**
     * Enregistre l'individu ind au sein de l'organisation L'individu ne sait
     * pas qu'il appartient à l'organisation
     * 
     * @param org
     */
    public void addMember(Individu ind) {
	if (!inverseMember.contains(ind)) {
	    inverseMember.add(ind);
	}
    }

    public void removeToOrganization(Individu ind) {
	inverseMember.remove(ind);
    }

    public ArrayList<Individu> getMember() {
	return inverseMember;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("Organisation");
	racine.setAttribute("classkmad", "tache.Organisation");
	racine.setAttribute("idkmad", oid.get());

	Element kmadOrganisationName = doc.createElement("organisation-name");
	kmadOrganisationName.setTextContent(this.getName());
	racine.appendChild(kmadOrganisationName);

	if (!this.getStatut().equals("")) {
	    Element kmadOrganisationStatut = doc
		    .createElement("organisation-statut");
	    kmadOrganisationStatut.setTextContent(this.getStatut());
	    racine.appendChild(kmadOrganisationStatut);
	}

	if (!this.getRole().equals("")) {
	    Element kmadOrganisationRole = doc
		    .createElement("organisation-role");
	    kmadOrganisationRole.setTextContent(this.getRole());
	    racine.appendChild(kmadOrganisationRole);
	}

	if (!this.getImage().equals("")) {
	    Element kmadOrganisationImagePath = doc
		    .createElement("organisation-imagepath");
	    kmadOrganisationImagePath.setTextContent(this.getImage());
	    racine.appendChild(kmadOrganisationImagePath);
	}

	/*
	 * seul les individus stocks les organisations dans le fichier XML
	 * if(this.inverseMember.size()!=0){ for(int i =0;
	 * i<inverseMember.size();i++){ Element idOrganisation =
	 * doc.createElement("id-Organisation");
	 * idOrganisation.setTextContent(inverseMember.get(i).getOid().get());
	 * racine.appendChild(idOrganisation); } }
	 */
	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadOrganisationName = p
		.getElementsByTagName("organisation-name");
	if (kmadOrganisationName.item(0) != null)
	    super.setName(kmadOrganisationName.item(0).getTextContent());

	NodeList kmadOrganisationStatut = p
		.getElementsByTagName("organisation-statut");
	if (kmadOrganisationStatut.item(0) != null)
	    super.setStatut(kmadOrganisationStatut.item(0).getTextContent());

	NodeList kmadOrganisationRole = p
		.getElementsByTagName("organisation-role");
	if (kmadOrganisationRole.item(0) != null)
	    super.setRole(kmadOrganisationRole.item(0).getTextContent());

	NodeList kmadOrganisationImagePath = p
		.getElementsByTagName("organisation-imagepath");
	if (kmadOrganisationImagePath.item(0) != null)
	    super.setImage(kmadOrganisationImagePath.item(0).getTextContent());
    }

    public String toSPF() {

	String s = new String("");
	/*
	 * affichage des oid des membres désactivés s += "','("; for(int i =
	 * 0;i<inverseMember.size();i++){ s = s +
	 * inverseMember.get(i).getOid().toString(); if(i!=
	 * inverseMember.size()-1) s += ","; } s += ")";
	 */
	return oid.get() + "=Organisation('" + super.getName() + "','"
		+ super.getStatut() + "','" + super.getRole() + "','"
		+ super.getImage() + s + "');";
    }

    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < inverseMember.size(); i++) {
	    s += inverseMember.get(i).getName();
	    if (i != inverseMember.size() - 1) {
		s += ", ";
	    }
	}

	Object[] res = { super.oid.get(), super.getName(), super.getStatut(),
		super.getRole(), super.getImage(), s };
	return res;
    }

    public static int toArrayLenght() {
	return 5;
    }

}

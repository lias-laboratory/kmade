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
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Individu extends User {

    private static final long serialVersionUID = 144972651724436715L;

    private ArrayList<Organisation> memberOf = new ArrayList<Organisation>();

    public Individu() {
	super();
    }

    public Individu(String name, Oid oid) {
	super(name, "", "", "", oid);
    }

    public Individu(String name, String st, String r, Oid oid) {
	super(name, st, r, "", oid);
    }

    public Individu(String name, String st, String r, String pi, Oid oid) {
	super(name, st, r, pi, oid);
    }

    public void delete() {
	// supprimer l'individu des organisations
	for (int i = 0; i < memberOf.size(); i++) {
	    memberOf.get(i).removeToOrganization(this);
	}
	super.delete();
    }

    /**
     * Enregistre que l'individu fait partie de l'organisation org
     * L'organisation org ne sait pas que l'individu en fait partie
     * 
     * @param org
     */
    public void addToOrganization(Organisation org) {
	if (!memberOf.contains(org)) {
	    memberOf.add(org);
	}
    }

    public ArrayList<Organisation> getMemberOf() {
	return memberOf;
    }

    public void removeToOrganization(Organisation org) {
	memberOf.remove(org);
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("Individu");
	racine.setAttribute("classkmad", "tache.Individu");
	racine.setAttribute("idkmad", oid.get());

	Element kmadIndividuName = doc.createElement("individu-name");
	kmadIndividuName.setTextContent(this.getName());
	racine.appendChild(kmadIndividuName);

	if (!this.getStatut().equals("")) {
	    Element kmadIndividuStatut = doc.createElement("individu-statut");
	    kmadIndividuStatut.setTextContent(this.getStatut());
	    racine.appendChild(kmadIndividuStatut);
	}

	if (!this.getRole().equals("")) {
	    Element kmadIndividuRole = doc.createElement("individu-role");
	    kmadIndividuRole.setTextContent(this.getRole());
	    racine.appendChild(kmadIndividuRole);
	}

	if (!this.getImage().equals("")) {
	    Element kmadIndividuImagePath = doc
		    .createElement("individu-imagepath");
	    kmadIndividuImagePath.setTextContent(this.getImage());
	    racine.appendChild(kmadIndividuImagePath);
	}
	if (this.memberOf.size() != 0) {
	    for (int i = 0; i < memberOf.size(); i++) {
		Element idOrganisation = doc.createElement("id-organisation");
		idOrganisation.setTextContent(memberOf.get(i).getOid().get());
		racine.appendChild(idOrganisation);
	    }
	}
	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p.getElementsByTagName("id-organisation");
	for (int i = 0; i < userValue.getLength(); i++) {
	    if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(i)
		    .getTextContent())) == null) {
		return true;
	    }
	}

	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadIndividuName = p.getElementsByTagName("individu-name");

	if (kmadIndividuName.item(0) != null)
	    super.setName(kmadIndividuName.item(0).getTextContent());

	NodeList kmadIndividuStatut = p.getElementsByTagName("individu-statut");

	if (kmadIndividuStatut.item(0) != null)
	    super.setStatut(kmadIndividuStatut.item(0).getTextContent());

	NodeList kmadIndividuRole = p.getElementsByTagName("individu-role");

	if (kmadIndividuRole.item(0) != null)
	    super.setRole(kmadIndividuRole.item(0).getTextContent());

	NodeList kmadIndividuImagePath = p
		.getElementsByTagName("individu-imagepath");

	if (kmadIndividuImagePath.item(0) != null)
	    super.setImage(kmadIndividuImagePath.item(0).getTextContent());

	NodeList kmadIndividuOrganisation = p
		.getElementsByTagName("id-organisation");

	if (kmadIndividuOrganisation.item(0) != null) {
	    for (int i = 0; i < kmadIndividuOrganisation.getLength(); i++) {
		this.addToOrganization((Organisation) InterfaceExpressJava.bdd
			.prendre(new Oid(kmadIndividuOrganisation.item(i)
				.getTextContent())));
	    }
	}
    }

    public String toSPF() {
	String s = new String("(");
	for (int i = 0; i < memberOf.size(); i++) {
	    s = s + "#" + memberOf.get(i).getOid().toString();
	    if (i != memberOf.size() - 1)
		s += ",";
	}
	s += ")";
	return oid.get() + "=Individu('" + super.getName() + "','"
		+ super.getStatut() + "','" + super.getRole() + "','"
		+ super.getImage() + "','" + s + "');";
    }

    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < memberOf.size(); i++) {
	    s += memberOf.get(i).getName();
	    if (i != memberOf.size() - 1) {
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

    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = doc.createElement("Individu");
	racine.setAttribute("classkmad", "tache.Individu");
	racine.setAttribute("idkmad", oid.get());

	if (this.memberOf.size() != 0) {
	    String list = new String("");
	    for (int i = 0; i < memberOf.size(); i++) {
		list += memberOf.get(i).getOid().get() + " ";
	    }
	    racine.setAttribute("id-organisation", list);
	}

	Element kmadIndividuName = doc.createElement("individu-name");
	kmadIndividuName.setTextContent(this.getName());
	racine.appendChild(kmadIndividuName);

	if (!this.getStatut().equals("")) {
	    Element kmadIndividuStatut = doc.createElement("individu-statut");
	    kmadIndividuStatut.setTextContent(this.getStatut());
	    racine.appendChild(kmadIndividuStatut);
	}

	if (!this.getRole().equals("")) {
	    Element kmadIndividuRole = doc.createElement("individu-role");
	    kmadIndividuRole.setTextContent(this.getRole());
	    racine.appendChild(kmadIndividuRole);
	}

	if (!this.getImage().equals("")) {
	    Element kmadIndividuImagePath = doc
		    .createElement("individu-imagepath");
	    kmadIndividuImagePath.setTextContent(this.getImage());
	    racine.appendChild(kmadIndividuImagePath);
	}
	if (this.memberOf.size() != 0) {
	    for (int i = 0; i < memberOf.size(); i++) {
		Element idOrganisation = doc.createElement("id-organisation");
		idOrganisation.setTextContent(memberOf.get(i).getOid().get());
		racine.appendChild(idOrganisation);
	    }
	}
	return racine;

    }

    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub
	this.oid = new Oid(p.getAttribute("idkmad"));
	memberOf.clear();
	if(p.hasAttribute("id-organisation")){
	    String[] kmadIndividuOrganisation = p.getAttribute("id-organisation").split(" ");
	    for (int i = 0; i < kmadIndividuOrganisation.length; i++) {
		this.addToOrganization((Organisation) InterfaceExpressJava.bdd
			.prendre(new Oid(kmadIndividuOrganisation[i])));
	    }
	}

	NodeList kmadIndividuName = p.getElementsByTagName("individu-name");
	if(kmadIndividuName.item(0).getParentNode() != p){
		kmadIndividuName = null;}
	if (kmadIndividuName.item(0) != null)
	    super.setName(kmadIndividuName.item(0).getTextContent());

	NodeList kmadIndividuStatut = p.getElementsByTagName("individu-statut");
	if(kmadIndividuStatut.item(0).getParentNode() != p){
		kmadIndividuStatut = null;}
	if (kmadIndividuStatut.item(0) != null)
	    super.setStatut(kmadIndividuStatut.item(0).getTextContent());

	NodeList kmadIndividuRole = p.getElementsByTagName("individu-role");
	if(kmadIndividuRole.item(0).getParentNode() != p){
		kmadIndividuRole = null;}
	if (kmadIndividuRole.item(0) != null)
	    super.setRole(kmadIndividuRole.item(0).getTextContent());

	NodeList kmadIndividuImagePath = p.getElementsByTagName("individu-imagepath");
	if(kmadIndividuImagePath.item(0).getParentNode() != p){
		kmadIndividuImagePath = null;}
	if (kmadIndividuImagePath.item(0) != null)
	    super.setImage(kmadIndividuImagePath.item(0).getTextContent());

    }
    
    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {

	if (p.hasAttribute("id-organisation")) {
	    String[] userValue = p.getAttribute("id-organisation").split(" ");
	    for (int i = 0; i < userValue.length; i++) {
		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue[i])) == null) {
		    return true;
		}
	    }
	}
	return false;
    }
}

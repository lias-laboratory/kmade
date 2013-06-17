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
 * The Organisation class stands for "groups", named "Organizations", which can organize individuals. 
 * The semantics associated to grouping is left to task model designers. It can relates to authorization levels,
 * abilities, or everything else. organizations cannot contain other organizations.
 * The class inherits from User to allow using Individuals or Organizations as Actors
 * The only responsibility of this class is to maintain the list or individuals who are members.
 * 
 * @author Mickael BARON
 * @author [Comment] Patrick GIRARD
 */
public class Organization extends User {

    private static final long serialVersionUID = -4187152840289735872L;

    /**
     * List of individuals who are member of the organization
     */
    private ArrayList<Person> reverseMember = new ArrayList<Person>();

    /**
     * Empty constructor
     */
    public Organization() {
	super();
    }

    /**
     * Constructor with only a name and an oid
     * @param name name of the individual
     * @param oid unique Express identifier
     */
    public Organization(String name, Oid oid) {
	super(name, "", "", "", oid);
    }

    /**
     * Constructor with an empty image path
     * @param name name of the individual
     * @param st status
     * @param r role
     * @param oid unique Express identifier
     */
    public Organization(String name, String st, String r, Oid oid) {
	super(name, st, r, "", oid);
    }

    /**
     * Complete constructor
     * @param name name of the individual
     * @param st status
     * @param r role
     * @param pi image path
     * @param oid unique Express identifier
     */
    public Organization(String name, String st, String r, String pi, Oid oid) {
	super(name, st, r, pi, oid);
    }

    @Override
    /**
     * removes the organization from all individuals who are members
     * CommentPG: Seems redundant with the equivalent method in Individu
     * @see fr.upensma.lias.kmade.kmad.schema.tache.User#delete()
     */
    public void delete() {
	// supprimer l'individu des organisations
	for (int i = 0; i < reverseMember.size(); i++) {
	    reverseMember.get(i).removeOrganisation(this);
	}
	super.delete();
    }

    /**
     * Registers an individual into the organization
     * A verification is made for avoiding duplication
     * Warning: the reverse registering is not done at this step
     * 
     * @param ind Individu -> the individual to add to the organization
     */
    public void addMember(Person ind) {
		if (!reverseMember.contains(ind)) {
		    reverseMember.add(ind);
		}
    }

    /**
     * Removes the individual from the organization
     * Warning: no verification is done to verify if it where already a member
     * @param ind : Individu -> The inidividual to remove
     */
    public void removeIndividu(Person ind) {
    	reverseMember.remove(ind);
    }

    /**
     * Gives the list of individual who are member of the organization
     * Warning: it is a reference on the list itself. Be careful not to modify it
     * 
     * @return an ArrayList which contains all individuals who are members
     */
    public ArrayList<Person> getMembers() {
    	return reverseMember;
    }

    /**
     * Returns an array containing all characteristics of an organization
     * 
     * @return an array of Object, which contains all attributes of superclass, plus a list of name of individual
     * who are member of the prganization 
     */
    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < reverseMember.size(); i++) {
	    s += reverseMember.get(i).getName();
	    if (i != reverseMember.size() - 1) {
		s += ", ";
	    }
	}

	Object[] res = { super.oid.get(), super.getName(), super.getStatus(),
		super.getRole(), super.getImage(), s };
	return res;
    }

    /**
     * This function is supposed to give the size of the array returned by the "toArray" method
     * It returns always 5 !
     * Warning: Very dangerous implementation
     * @return 5
     */
    public static int toArrayLenght() {
	return 5;
    }


/*    public org.w3c.dom.Element toXML(Document doc) {
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

	
	 * seul les individus stocks les organisations dans le fichier XML
	 * if(this.inverseMember.size()!=0){ for(int i =0;
	 * i<inverseMember.size();i++){ Element idOrganisation =
	 * doc.createElement("id-Organisation");
	 * idOrganisation.setTextContent(inverseMember.get(i).getOid().get());
	 * racine.appendChild(idOrganisation); } }
	 
	return racine;
    }
*/
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));
	reverseMember.clear();
	NodeList kmadOrganisationName = p
		.getElementsByTagName("organisation-name");
	if (kmadOrganisationName.item(0) != null)
	    super.setName(kmadOrganisationName.item(0).getTextContent());

	NodeList kmadOrganisationStatut = p
		.getElementsByTagName("organisation-statut");
	if (kmadOrganisationStatut.item(0) != null)
	    super.setStatus(kmadOrganisationStatut.item(0).getTextContent());

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
	 * affichage des oid des membres desactives s += "','("; for(int i =
	 * 0;i<inverseMember.size();i++){ s = s +
	 * inverseMember.get(i).getOid().toString(); if(i!=
	 * inverseMember.size()-1) s += ","; } s += ")";
	 */
	return oid.get() + "=Organisation('" + super.getName() + "','"
		+ super.getStatus() + "','" + super.getRole() + "','"
		+ super.getImage() + s + "');";
    }

    public Element toXML2(Document doc) throws Exception {
    	Element racine = doc.createElement("Organisation");
    	racine.setAttribute("classkmad", "tache.Organisation");
    	racine.setAttribute("idkmad", oid.get());

    	Element kmadOrganisationName = doc.createElement("organisation-name");
    	kmadOrganisationName.setTextContent(this.getName());
    	racine.appendChild(kmadOrganisationName);

    	if (!this.getStatus().equals("")) {
    	    Element kmadOrganisationStatut = doc
    		    .createElement("organisation-statut");
    	    kmadOrganisationStatut.setTextContent(this.getStatus());
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

    public void createObjectFromXMLElement2(Element p) throws Exception {
    	this.oid = new Oid(p.getAttribute("idkmad"));
    	reverseMember.clear();
    	NodeList kmadOrganisationName = p
    		.getElementsByTagName("organisation-name");
    	if(kmadOrganisationName.item(0).getParentNode() != p){
    		kmadOrganisationName = null;}
    	if (kmadOrganisationName.item(0) != null)
    	    super.setName(kmadOrganisationName.item(0).getTextContent());

    	NodeList kmadOrganisationStatut = p
    		.getElementsByTagName("organisation-statut");
    	if(kmadOrganisationStatut.item(0).getParentNode() != p){
    		kmadOrganisationStatut = null;}
    	if (kmadOrganisationStatut.item(0) != null)
    	    super.setStatus(kmadOrganisationStatut.item(0).getTextContent());

    	NodeList kmadOrganisationRole = p
    		.getElementsByTagName("organisation-role");
    	if(kmadOrganisationRole.item(0).getParentNode() != p){
    		kmadOrganisationRole = null;}
    	if (kmadOrganisationRole.item(0) != null)
    	    super.setRole(kmadOrganisationRole.item(0).getTextContent());

    	NodeList kmadOrganisationImagePath = p
    		.getElementsByTagName("organisation-imagepath");
    	if(kmadOrganisationImagePath.item(0).getParentNode() != p){
    		kmadOrganisationImagePath = null;}
    	if (kmadOrganisationImagePath.item(0) != null)
    	    super.setImage(kmadOrganisationImagePath.item(0).getTextContent());
    }

}

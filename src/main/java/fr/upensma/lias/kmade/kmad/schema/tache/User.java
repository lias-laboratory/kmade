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

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * Abstract class which allows to manipulate the same way individuals or groups of individuals, 
 * which can be defined as sub-classes 
 * The class provides for a name, a role, a status, all three are free texts, the semantics of which should be
 * defined by the task model designer for each project. 
 * The last attribute is the imagePath, which is supposed to represent the physical path for an image which can
 * be used in the interface of K-MADe to personalize it
 * 
 * @author Delphine AUTARD and Mickael BARON
 * @author [Comment] Patrick GIRARD
 **/
public abstract class User implements Entity {

    private static final long serialVersionUID = 4380917017782960768L;

    public Oid oid = null;

    /**
     * name : String -> name of the user
     */
    private String name = "";

    /**
     * statut : String -> a semantically free "status" to be used for the User
     */
    private String status = "";

    /**
     * role : String -> a semantically free "role" to be used for the User
     */
    private String role = "";

    /**
     * imagePath : String -> Path for an image to use in the interface of K-MADe
     */
    private String imagePath = "";

    /**
     * List of actors the user is involved in. 
     * CommentPG: Not sure it is very necessary and useful
     */
    private ArrayList<Actor> reverseActors = new ArrayList<Actor>();

    /**
     * Empty constructor
     */
    public User() {
	this("", "", "", "", null);
    }

    /**
     * Constructor with name an oid
     * 
     * @param name name of the individual
     * @param oid unique Express identifier
     */
    public User(String name, Oid oid) {
	this(name, "", "", "", oid);
    }

    /**
     * Constructor without image path
     * 
     * @param name name of the individual
     * @param st status
     * @param r role
     * @param oid unique Express identifier
     */
    public User(String name, String st, String r, Oid oid) {
	this(name, st, r, "", oid);
    }

    /**
     * Complete constructor
     * 
     * @param name name of the individual
     * @param st status
     * @param r role
     * @param pi image path
     * @param oid unique Express identifier
     */
    public User(String name, String st, String r, String pi, Oid oid) {
	this.name = name;
	this.status = st;
	this.role = r;
	this.imagePath = pi;
	this.oid = oid;
    }

    /**
     * Builds the link between an actor and its user. No verification is made on the 
     * correctness of the link
     * CommentPG: I am not sure of the interest of this inverse link. It seems not used at all, 
     * and there is no way to use the list of actors related to a user...
     * 
     * @param a the actor to add
     */
    public void addReverseActor(Actor a) {
	reverseActors.add(a);
    }

    /**
     * Removes an actor from the reverse list of actors
     * 
     * @param a the actor to be removed
     */
    public void removeInverseActeur(Actor a) {
	reverseActors.remove(a);
    }

    public void delete() {
	for (int i = 0; i < reverseActors.size(); i++) {
	    Actor a = reverseActors.get(i);
	    a.delete();
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 3);
	for (int i = 0; i < reverseActors.size(); i++) {
	    Actor a = reverseActors.get(i);
	    a.affDelete();
	}
    }

    public String getName() {
	return name;
    }

    public void setName(String n) {
	name = n;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String n) {
	status = n;
    }

    public void setImage(String n) {
	this.imagePath = n;
    }

    public String getImage() {
	return this.imagePath;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String n) {
	role = n;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    @Override
    /*
     * Returns the name of the User
     * @see java.lang.Object#toString()
     */
    public String toString() {
	return name;
    }

    /**
     * Utilitary static function able to verify in the whole express model that a name is unique among
     * all existing users (User, Individu, Organisation classes). 
     * Warning: The method ignore case for characters
     * 
     * @param s the name to verify
     * @return false if the name already exists, else true
     */
    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.USER_CLASS);
	Object[] objAbs1 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.PERSON_CLASS);
	Object[] objAbs2 = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.ORGANIZATION_CLASS);
	for (int i = 0; i < objAbs.length; i++) {
	    User obj = (User) objAbs[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	for (int i = 0; i < objAbs1.length; i++) {
	    Person obj = (Person) objAbs1[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	for (int i = 0; i < objAbs2.length; i++) {
	    Organization obj = (Organization) objAbs2[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Static method to calculate a new name composed by a supposed not unique name and a 
     * numerical value. For example, if the name Patrick already exists, it will search for Patrick_1, 
     * and Patrick_2 if needed, and so on.
     * 
     * @param n the name to modify to become unique
     * @return a unique name based on the first one
     */
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
        
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadUserName = p.getElementsByTagName("user-name");
	if (kmadUserName.item(0) != null)
	    this.name = kmadUserName.item(0).getTextContent();

	NodeList kmadUserStatut = p.getElementsByTagName("user-statut");
	if (kmadUserStatut.item(0) != null)
	    this.status = kmadUserStatut.item(0).getTextContent();

	NodeList kmadUserRole = p.getElementsByTagName("user-role");
	if (kmadUserRole.item(0) != null)
	    this.role = kmadUserRole.item(0).getTextContent();

	NodeList kmadUserImagePath = p.getElementsByTagName("user-imagepath");
	if (kmadUserImagePath.item(0) != null)
	    this.imagePath = kmadUserImagePath.item(0).getTextContent();

    }

    public String toSPF() {
	return oid.get() + "=User('" + name + "','" + status + "','" + role
		+ "','" + this.imagePath + "');";
    }

    public Oid getOid() {
	return oid;
    }

    @Override
	public Element toXML2(Document doc) throws Exception {
    	Element racine = doc.createElement("user");
    	racine.setAttribute("classkmad", "tache.User");
    	racine.setAttribute("idkmad", oid.get());

    	Element kmadUserName = doc.createElement("user-name");
    	kmadUserName.setTextContent(this.getName());
    	racine.appendChild(kmadUserName);

    	if (!this.getStatus().equals("")) {
    	    Element kmadUserStatut = doc.createElement("user-statut");
    	    kmadUserStatut.setTextContent(this.getStatus());
    	    racine.appendChild(kmadUserStatut);
    	}

    	if (!this.getRole().equals("")) {
    	    Element kmadUserRole = doc.createElement("user-role");
    	    kmadUserRole.setTextContent(this.getRole());
    	    racine.appendChild(kmadUserRole);
    	}

    	if (!this.getImage().equals("")) {
    	    Element kmadUserImagePath = doc.createElement("user-imagepath");
    	    kmadUserImagePath.setTextContent(this.getImage());
    	    racine.appendChild(kmadUserImagePath);
    	}

    	return racine;
	}

	@Override
	public void createObjectFromXMLElement2(Element p) throws Exception {
		this.oid = new Oid(p.getAttribute("idkmad"));

		NodeList kmadUserName = p.getElementsByTagName("user-name");
		 if(kmadUserName.item(0).getParentNode() != p){
			 kmadUserName = null;}
		if (kmadUserName.item(0) != null)
		    this.name = kmadUserName.item(0).getTextContent();

		NodeList kmadUserStatut = p.getElementsByTagName("user-statut");
		 if(kmadUserStatut.item(0).getParentNode() != p){
			 kmadUserStatut = null;}
		if (kmadUserStatut.item(0) != null)
		    this.status = kmadUserStatut.item(0).getTextContent();

		NodeList kmadUserRole = p.getElementsByTagName("user-role");
		 if(kmadUserRole.item(0).getParentNode() != p){
			 kmadUserRole = null;}
		if (kmadUserRole.item(0) != null)
		    this.role = kmadUserRole.item(0).getTextContent();

		NodeList kmadUserImagePath = p.getElementsByTagName("user-imagepath");
		 if(kmadUserImagePath.item(0).getParentNode() != p){
			 kmadUserImagePath = null;}
		if (kmadUserImagePath.item(0) != null)
		    this.imagePath = kmadUserImagePath.item(0).getTextContent();

	}

	@Override
	public boolean oidIsAnyMissing2(Element p) throws Exception {
		return false;
	}
}

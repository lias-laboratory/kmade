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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.KMADXMLParserException;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * An actor is an association between one task and one user (one person or one organization). 
 * Relatively to this association, an experience (enumerated type) and a competence (free string) 
 * can be defined
 * 
 * @author Mickael BARON 
 * @author [Comment] Patrick GIRARD
 */
public class Actor implements Entity {

    private static final long serialVersionUID = -5271006845716055412L;

    public Oid oid = null;

    /**
     * userRef : User -> This user may be a person or an organization (i.e. a group of persons) 
     */
    private User userRef;

    /**
     * experience : Experience -> Enumerated attribute, which is supposed to reflect the required level of 
     * experience of the user in accomplishing his/her task.  
     */
    private Experience experience = Experience.INCONNU;

    /**
     * competence : String -> free text for detailing the required competence for the task
     */
    private String competence = "";

    /**
     * Reverse link to the task where the actor is defined
     */
    private Task reverseTask;

    public Actor() {
	userRef = null;
	experience = Experience.INCONNU;
	competence = "";
    }

    /**
     * Constructor with all parameters
     * The parameters are supposed to be ok !
     * The inverse task is not set
     * 
     * @param exp String value from enumerated type Experience
     * @param comp free text for the competence
     * @param u User the associated user
     * @param o Oid unique Express identifier
     */
    public Actor(String exp, String comp, User u, Oid o) {
    	userRef = u;
    	experience = Experience.getValue(exp);
    	competence = comp;
    	this.oid = o;
    }

    /**
     * Suppress the actor, deleting the inverse links in the user and the task
     */
    public void delete() {
    	userRef.removeInverseActeur(this);
    	reverseTask.removeActor(this);
    	InterfaceExpressJava.remove(oid);
    }

    public String getName() {
    	return userRef.getName();
    }

    public void affDelete() {
    	InterfaceExpressJava.getGestionWarning().addMessage(
		oid,
		9,
		ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
			+ reverseTask.getName() + "\"");
    }

    public void setOid(Oid oid) {
    	this.oid = oid;
    }

    /**
     * Sets the inverse link to the task in which the actor is defined
     * Warning: no verification is made to ensure the task is the right one
     * 
     * @param a the task where the actor is defined
     */
    public void setReverseTask(Task a) {
    	this.reverseTask = a;
    }

    public Task getInverseTache() {
    	return reverseTask;
    }

    public void setExperience(String s) {
    	experience = Experience.getValue(s);
    }

    public Experience getExperience() {
    	return experience;
    }

    public void setCompetence(String s) {
    	competence = s;
    }

    public String getCompetence() {
    	return competence;
    }

    public void setUserRef(User u) {
    	userRef = u;
    }

    public User getUserRef() {
    	return userRef;
    }

    public Oid getOid() {
    	return oid;
    }

    public String toString() {
    	return userRef.toString();
    }

/*    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("actor");
	racine.setAttribute("classkmad", "tache.Acteur");
	racine.setAttribute("idkmad", oid.get());

	racine.appendChild(experience.toXML(doc));

	if (!this.competence.equals("")) {
	    Element kmadActorCompetence = doc.createElement("actor-competence");
	    kmadActorCompetence.setTextContent(this.competence);
	    racine.appendChild(kmadActorCompetence);
	}

	Element idUser = doc.createElement("id-user");
	idUser.setTextContent(this.userRef.getOid().get());
	racine.appendChild(idUser);
	return racine;
    }
*/
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) throws Exception,
	    KMADXMLParserException {
	NodeList nodeList = p.getElementsByTagName("id-user");
	if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
		.getTextContent())) == null) {
	    return true;
	}
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	this.experience = Experience.getXMLExperienceValue(p);

	NodeList nodeList = p.getElementsByTagName("actor-competence");
	if (nodeList.item(0) != null) {
	    this.competence = nodeList.item(0).getTextContent();
	}

	nodeList = p.getElementsByTagName("id-user");

	this.userRef = (User) InterfaceExpressJava.bdd.prendre(new Oid(nodeList
		.item(0).getTextContent()));
    }

    public String toSPF() {
	String SPF = oid.get() + "=" + "Acteur" + "(" + experience.toSPF()
		+ "," + "'" + competence + "'" + ",";
	if (userRef != null)
	    SPF = SPF + userRef.getOid().get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ");";
	return SPF;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = doc.createElement("actor");
	racine.setAttribute("classkmad", "tache.Acteur");
	racine.setAttribute("idkmad", oid.get());
	racine.setAttribute("id-user", this.userRef.getOid().get());

	racine.appendChild(experience.toXML2(doc));
	if (!this.competence.equals("")) {
	    Element kmadActorCompetence = doc.createElement("actor-competence");
	    kmadActorCompetence.setTextContent(this.competence);
	    racine.appendChild(kmadActorCompetence);
	}

	return racine;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub
	this.oid = new Oid(p.getAttribute("idkmad"));

	this.userRef = (User) InterfaceExpressJava.bdd.prendre(new Oid(p
		.getAttribute("id-user")));
	this.experience = Experience.getXMLExperienceValue(p);

	NodeList nodeList = p.getElementsByTagName("actor-competence");
	if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		nodeList = null;}
	if (nodeList.item(0) != null) {
	    this.competence = nodeList.item(0).getTextContent();
	}

    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	// TODO Auto-generated method stub
	String nodeList = p.getAttribute("id-user");
	if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
	    return true;
	}

	return false;
    }
}

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
 * @author Mickael BARON
 */
public class ActorSystem implements Entity {

    private static final long serialVersionUID = -5271006845716055412L;

    public Oid oid = null;

    private Material materialRef;

    private Experience experience = Experience.INCONNU;

    private String competence = "";

    private Task reverseTask;

    public ActorSystem() {
	materialRef = null;
	experience = Experience.INCONNU;
	competence = "";
    }

    public ActorSystem(String exp, String comp, Material u, Oid o) {
	materialRef = u;
	experience = Experience.getValue(exp);
	competence = comp;
	this.oid = o;
    }

    public void delete() {
	materialRef.removeReverseActorSystem(this);
	reverseTask.removeActorSystem(this);
	InterfaceExpressJava.remove(oid);
    }

    public String getName() {
	return materialRef.getName();
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(
		oid,
		15,
		ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
			+ reverseTask.getName() + "\"");
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public void setReverseTask(Task a) {
	this.reverseTask = a;
    }

    public String toString() {
	return materialRef.toString();
    }

/*    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("actorSystem");
	racine.setAttribute("classkmad", "tache.ActeurSysteme");
	racine.setAttribute("idkmad", oid.get());

	racine.appendChild(experience.toXML(doc));

	if (!this.competence.equals("")) {
	    Element kmadActorCompetence = doc
		    .createElement("actorSystem-competence");
	    kmadActorCompetence.setTextContent(this.competence);
	    racine.appendChild(kmadActorCompetence);
	}

	Element idUser = doc.createElement("id-userSystem");
	idUser.setTextContent(this.userRef.getOid().get());
	racine.appendChild(idUser);
	return racine;
    }
*/
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) throws Exception,
	    KMADXMLParserException {
	NodeList nodeList = p.getElementsByTagName("id-userSystem");
	if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
		.getTextContent())) == null) {
	    return true;
	}
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	this.experience = Experience.getXMLExperienceValue(p);

	NodeList nodeList = p.getElementsByTagName("actorSystem-competence");
	if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		nodeList = null;}
	if (nodeList.item(0) != null) {
	    this.competence = nodeList.item(0).getTextContent();
	}

	nodeList = p.getElementsByTagName("id-userSystem");
	if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		nodeList = null;}
	this.materialRef = (Material) InterfaceExpressJava.bdd.prendre(new Oid(
		nodeList.item(0).getTextContent()));
    }

    public String toSPF() {
	String SPF = oid.get() + "=" + "Acteur System" + "("
		+ experience.toSPF() + "," + "'" + competence + "'" + ",";
	if (materialRef != null)
	    SPF = SPF + materialRef.getOid().get();
	else
	    SPF = SPF + "$";
	SPF = SPF + ");";
	return SPF;
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

    public void setMaterialRef(Material u) {
	materialRef = u;
    }

    public Material getMaterialRef() {
	return materialRef;
    }

    public Oid getOid() {
	return oid;
    }

    public Task getReverseTask() {
	return reverseTask;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = doc.createElement("actorSystem");
	racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "." + ExpressConstant.ACTOR_SYSTEM_CLASS);
	racine.setAttribute("idkmad", oid.get());
	racine.setAttribute("id-user", this.materialRef.getOid().get());

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
	this.oid = new Oid(p.getAttribute("idkmad"));
	this.materialRef = (Material) InterfaceExpressJava.bdd.prendre(new Oid(p
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
	String nodeList = p.getAttribute("id-user");
	if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
	    return true;
	}
	return false;
    }
}

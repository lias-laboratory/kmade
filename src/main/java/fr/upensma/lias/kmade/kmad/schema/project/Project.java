/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
package fr.upensma.lias.kmade.kmad.schema.project;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Project implements Entity {

    private static final long serialVersionUID = -473392542373483656L;

    public Oid oid = null;

    private GeneralInformation myGenInfo;

    private ArrayList<InterviewIndex> myIntIndex = new ArrayList<InterviewIndex>();

    public Project() {
	myGenInfo = null;
    }

    public void removeGeneralInformationAndInterviewIndexAttributes() {
	this.myGenInfo = null;
	myIntIndex = new ArrayList<InterviewIndex>();
    }

    public Project(GeneralInformation pgenInfo,
	    ArrayList<InterviewIndex> pmyIntIndex, Oid o) {
	this.myGenInfo = pgenInfo;
	this.myGenInfo.setInverseProject(this);

	this.myIntIndex = pmyIntIndex;
	for (int i = 0; i < pmyIntIndex.size(); i++) {
	    this.myIntIndex.get(i).setInverseProject(this);
	}

	this.oid = o;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	Element racine = doc.createElement("project");
	racine.setAttribute("classkmad", "project.Project");
	racine.setAttribute("idkmad", oid.get());

	if (this.myGenInfo != null && !this.myGenInfo.isEmpty()) {
	    racine.setAttribute("id-project-information", myGenInfo.getOid()
		    .get());
	    racine.appendChild(this.myGenInfo.toXML2(doc));
	}
	if (!myIntIndex.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < myIntIndex.size(); i++) {
		list += myIntIndex.get(i).getOid().get() + " ";
		racine.appendChild(this.myIntIndex.get(i).toXML2(doc));
	    }
	    racine.setAttribute("id-project-interviews-list", list);
	}
	return racine;
    }

    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {
	String userValue;
	if (p.hasAttribute("id-project-information")) {
	    userValue = p.getAttribute("id-project-information");
	    if (InterfaceExpressJava.bdd.prendre(new Oid(userValue)) == null)
		return true;
	}
	if (p.hasAttribute("id-project-interviews-list")) {
	    String[] values = p.getAttribute("id-project-interviews-list")
		    .split(" ");
	    for (int i = 0; i < values.length; i++) {
		if (InterfaceExpressJava.bdd.prendre(new Oid((values[i]))) == null) {
		    return true;
		}
	    }
	}

	return false;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub
	this.oid = new Oid(p.getAttribute("idkmad"));
	// General Information
	if (p.hasAttribute("id-project-information")) {
	    this.myGenInfo = (GeneralInformation) InterfaceExpressJava.bdd
		    .prendre(new Oid(p.getAttribute("id-project-information")));
	}
	// else{
	// this.myGenInfo = new GeneralInformation();
	// KMADEHistoryMessageManager.printMessage("ah ba si");
	// }
	// Interviews
	if (p.hasAttribute("id-project-interview-list")) {
	    String[] interviews = p.getAttribute("id-project-interviews-list")
		    .split(" ");
	    for (int i = 0; i < interviews.length; i++) {
		this.myIntIndex.add((InterviewIndex) InterfaceExpressJava.bdd
			.prendre(new Oid(interviews[i])));
	    }
	}
    }

    public void addInterviewIndex(InterviewIndex pInterviewIndex) {
	myIntIndex.add(pInterviewIndex);
    }

    public Element toXML(Document doc) {
	Element racine = doc.createElement("project");
	racine.setAttribute("classkmad", "project.Project");
	racine.setAttribute("idkmad", oid.get());

	Element idprojectGeneralInfo = doc
		.createElement("id-project-information");
	idprojectGeneralInfo.setTextContent(myGenInfo.getOid().get());
	racine.appendChild(idprojectGeneralInfo);

	if (myIntIndex.size() != 0) {
	    Element idProjectInterviewsList = doc
		    .createElement("id-project-interviews-list");
	    for (int i = 0; i < myIntIndex.size(); i++) {
		Element idProjectInterviews = doc
			.createElement("id-project-interview");
		idProjectInterviews.setTextContent(myIntIndex.get(i).getOid()
			.get());
		idProjectInterviewsList.appendChild(idProjectInterviews);
	    }
	    racine.appendChild(idProjectInterviewsList);
	}
	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p.getElementsByTagName("id-project-information");
	if (userValue != null && userValue.item(0) != null
		&& userValue.item(0).getParentNode() != p) {
	    userValue = null;
	}
	if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0)
		.getTextContent())) == null) {
	    return true;
	}
	userValue = p.getElementsByTagName("id-project-interviews-list");
	if (userValue != null && userValue.item(0) != null
		&& userValue.item(0).getParentNode() != p) {
	    userValue = null;
	}
	if (userValue.getLength() != 0) {
	    NodeList nodeListInterview = userValue.item(0).getChildNodes();
	    for (int i = 0; i < nodeListInterview.getLength(); i++) {
		if (nodeListInterview.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    if (InterfaceExpressJava.bdd.prendre(new Oid(
			    ((Element) nodeListInterview.item(i))
				    .getTextContent())) == null) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));
	myIntIndex.clear();
	NodeList nodeList = p.getElementsByTagName("id-project-information");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	myGenInfo = (GeneralInformation) InterfaceExpressJava.bdd
		.prendre(new Oid(nodeList.item(0).getTextContent()));

	nodeList = p.getElementsByTagName("id-project-interviews-list");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.getLength() != 0) {
	    NodeList nodeListInterview = nodeList.item(0).getChildNodes();
	    for (int i = 0; i < nodeListInterview.getLength(); i++) {
		if (nodeListInterview.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    myIntIndex.add((InterviewIndex) InterfaceExpressJava.bdd
			    .prendre(new Oid(nodeListInterview.item(i)
				    .getTextContent())));
		}
	    }
	}
    }

    public String toSPF() {
	String spfString = oid.get() + "=" + "Project" + "(";

	if (myGenInfo != null)
	    spfString = spfString + myGenInfo.getOid().get();

	spfString = spfString + ",(";

	for (int i = 0; i < myIntIndex.size(); i++) {
	    spfString = spfString + myIntIndex.get(i).getOid().get();
	    if (i < (myIntIndex.size() - 1))
		spfString = spfString + ",";
	}

	spfString = spfString + "));";
	return spfString;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    public String getName() {
	return "";
    }

    public GeneralInformation getGeneralInformation() {
	return myGenInfo;
    }

    public void setGeneralInformation(GeneralInformation myGenInfo) {
	this.myGenInfo = myGenInfo;
    }

    public ArrayList<InterviewIndex> getInterviewIndex() {
	return myIntIndex;
    }

    public void setInterviewIndex(ArrayList<InterviewIndex> myIntIndex) {
	this.myIntIndex = myIntIndex;
    }
}

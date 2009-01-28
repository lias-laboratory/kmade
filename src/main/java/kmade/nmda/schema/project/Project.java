package kmade.nmda.schema.project;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
    
    public Project(GeneralInformation pgenInfo, ArrayList<InterviewIndex> pmyIntIndex, Oid o) {
        this.myGenInfo = pgenInfo;
        this.myGenInfo.setInverseProject(this);
        
        this.myIntIndex = pmyIntIndex;
        for (int i = 0; i < pmyIntIndex.size(); i++) {
            this.myIntIndex.get(i).setInverseProject(this);
        }
        
        this.oid = o;
    }
    
    public void addInterviewIndex(InterviewIndex pInterviewIndex) {
        myIntIndex.add(pInterviewIndex);
    }
    
    public Element toXML(Document doc) {
        Element racine = doc.createElement("project");
        racine.setAttribute("classkmad", "project.Project");
        racine.setAttribute("idkmad", oid.get());
        
        Element idprojectGeneralInfo = doc.createElement("id-project-information");
        idprojectGeneralInfo.setTextContent(myGenInfo.getOid().get());
        racine.appendChild(idprojectGeneralInfo);
        
        if (myIntIndex.size() != 0) {
            Element idProjectInterviewsList = doc.createElement("id-project-interviews-list"); 
            for (int i = 0 ; i < myIntIndex.size(); i++) {
                Element idProjectInterviews = doc.createElement("id-project-interview");
                idProjectInterviews.setTextContent(myIntIndex.get(i).getOid().get());
                idProjectInterviewsList.appendChild(idProjectInterviews);
            }        
            racine.appendChild(idProjectInterviewsList);
        }
        return racine;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        NodeList userValue = p.getElementsByTagName("id-project-information");
        if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
            return true;
        }
        userValue = p.getElementsByTagName("id-project-interviews-list");
        if (userValue.getLength() != 0) {
            NodeList nodeListInterview = userValue.item(0).getChildNodes();
            for (int i = 0 ; i < nodeListInterview.getLength() ; i++) {
                if (nodeListInterview.item(i).getNodeType() == Element.ELEMENT_NODE) {
                    if (InterfaceExpressJava.bdd.prendre(new Oid(((Element)nodeListInterview.item(i)).getTextContent())) == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
        
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));
        
        NodeList nodeList = p.getElementsByTagName("id-project-information");
        myGenInfo = (GeneralInformation)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent()));
        
        nodeList = p.getElementsByTagName("id-project-interviews-list");
        if (nodeList.getLength() != 0) {
            NodeList nodeListInterview = nodeList.item(0).getChildNodes();
            for (int i = 0 ; i < nodeListInterview.getLength() ; i++) {
                if (nodeListInterview.item(i).getNodeType() == Element.ELEMENT_NODE) {
                	myIntIndex.add((InterviewIndex)InterfaceExpressJava.bdd.prendre(new Oid(nodeListInterview.item(i).getTextContent())));
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

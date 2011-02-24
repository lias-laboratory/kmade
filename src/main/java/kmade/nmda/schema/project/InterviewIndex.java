package kmade.nmda.schema.project;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class InterviewIndex implements Entity {

    private static final long serialVersionUID = -3876650976240756349L;

    public Oid oid = null;
    
    private String interviewedName;
    
    private String placeInformation;
    
    private String statut;
      
    private String seniority;
    
    private String date;
    
    private String type;
    
    private String searchedInformations;
    
    private String interviewerName;
   
    private Project inverseProject;
    
    public InterviewIndex() {
        this.interviewedName = "";
        this.placeInformation = "";
        this.statut = "";
        this.seniority = "";
        this.date = "";
        this.type = "";
        this.searchedInformations = "";
        this.interviewerName = "";
    }
    
    public InterviewIndex(String pinterviewedName, String pplaceInformation, String pStatut, String pSeniority,
            String pDate, String pInterviewType, String psearchedInformations, String pinterviewerName, Oid o) {
        this.interviewedName = pinterviewedName;
        this.placeInformation = pplaceInformation;
        this.statut = pStatut;
        this.seniority = pSeniority;
        this.date = pDate;
        this.type = pInterviewType;
        this.searchedInformations = psearchedInformations;
        this.interviewerName = pinterviewerName;
        this.oid = o;
    }
    
    public Element toXML(Document doc) {
        Element racine = doc.createElement("projectinterview");
        racine.setAttribute("idkmad", oid.get());
        racine.setAttribute("classkmad", "project.InterviewIndex");
        
        Element interviewActor = doc.createElement("projectinterview-actor");
        interviewActor.setTextContent(interviewedName);
        
        if (!placeInformation.equals("")) {
            Element interviewPlace = doc.createElement("projectinterview-place");
            interviewPlace.setTextContent(placeInformation);
            racine.appendChild(interviewPlace);
        }
        
        if (!statut.equals("")) {
            Element interviewStatut = doc.createElement("projectinterview-statut");
            interviewStatut.setTextContent(statut);
            racine.appendChild(interviewStatut);
        }
        
        if (!seniority.equals("")) {
            Element interviewSeniority = doc.createElement("projectinterview-seniority");
            interviewSeniority.setTextContent(seniority);
            racine.appendChild(interviewSeniority);
        }
        
        if (!date.equals("")) {
            Element interviewDate = doc.createElement("projectinterview-date");
            interviewDate.setTextContent(date);
            racine.appendChild(interviewDate);
        }
        
        if (!type.equals("")) {
            Element interviewType = doc.createElement("projectinterview-type");
            interviewType.setTextContent(type);
            racine.appendChild(interviewType);
        }
        
        if (!searchedInformations.equals("")) {
            Element interviewInfo = doc.createElement("projectinterview-info");
            interviewInfo.setTextContent(searchedInformations);
            racine.appendChild(interviewInfo);
        }
        
        if (!interviewerName.equals("")) {
            Element interviewDirector = doc.createElement("projectinterview-director");
            interviewDirector.setTextContent(interviewerName);
            racine.appendChild(interviewDirector);
        }
 
        racine.appendChild(interviewActor);
        return racine;
    }    
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));
        
        NodeList userPreValue = p.getElementsByTagName("projectinterview-actor");
        interviewedName = userPreValue.item(0).getTextContent();

        userPreValue = p.getElementsByTagName("projectinterview-place");
        if (userPreValue.item(0) != null) {
            placeInformation = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-statut");
        if (userPreValue.item(0) != null) {
            statut = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-seniority");
        if (userPreValue.item(0) != null) {
            seniority = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-date");
        if (userPreValue.item(0) != null) {
            date = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-type");
        if (userPreValue.item(0) != null) {
            type = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-info");
        if (userPreValue.item(0) != null) {
            searchedInformations = userPreValue.item(0).getTextContent();
        }
        
        userPreValue = p.getElementsByTagName("projectinterview-director");
        if (userPreValue.item(0) != null) {
            interviewerName = userPreValue.item(0).getTextContent();
        }
    }
    
    public String toSPF() {
        String spfString = oid.get() + "=" + "InterviewIndex" + "(" + "'" + this.interviewedName + "'" + ","
        + "'" + this.placeInformation + "'" + "," + "'" + this.statut + "'" + "," 
        + "'" + this.seniority + "'" + "," + "'" + this.date + "'" + "," + "'"+ this.type + "'" + ","
        + "'" + this.searchedInformations + "'" + "," + "'" + this.interviewerName + "'" + ")";
        return spfString;
    }
    
    public void setOid(Oid oid) {
       this.oid = oid;
    }

    public Oid getOid() {
        return oid;
    }

    public String getName() {
        return "temp";
    }

    /**
     * @return Returns the date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return Returns the interviewedName.
     */
    public String getInterviewedName() {
        return interviewedName;
    }

    /**
     * @param interviewedName The interviewedName to set.
     */
    public void setInterviewedName(String interviewedName) {
        this.interviewedName = interviewedName;
    }

    /**
     * @return Returns the interviewerName.
     */
    public String getInterviewerName() {
        return interviewerName;
    }

    /**
     * @param interviewerName The interviewerName to set.
     */
    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    /**
     * @return Returns the interviewType.
     */
    public String getType() {
        return type;
    }

    /**
     * @param interviewType The interviewType to set.
     */
    public void setType(String interviewType) {
        this.type = interviewType;
    }

    /**
     * @return Returns the placeInformation.
     */
    public String getPlaceInformation() {
        return placeInformation;
    }

    /**
     * @param placeInformation The placeInformation to set.
     */
    public void setPlaceInformation(String placeInformation) {
        this.placeInformation = placeInformation;
    }

    /**
     * @return Returns the searchedInformations.
     */
    public String getSearchedInformations() {
        return searchedInformations;
    }

    /**
     * @param searchedInformations The searchedInformations to set.
     */
    public void setSearchedInformations(String searchedInformations) {
        this.searchedInformations = searchedInformations;
    }

    /**
     * @return Returns the seniority.
     */
    public String getSeniority() {
        return seniority;
    }

    /**
     * @param seniority The seniority to set.
     */
    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    /**
     * @return Returns the statut.
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut The statut to set.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return Returns the inverseProject.
     */
    public Project getInverseProject() {
        return inverseProject;
    }

    /**
     * @param inverseProject The inverseProject to set.
     */
    public void setInverseProject(Project inverseProject) {
        this.inverseProject = inverseProject;
    }
}

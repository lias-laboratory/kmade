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
package fr.upensma.lias.kmade.kmad.schema.project;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class GeneralInformation implements Entity {

    private static final long serialVersionUID = -6892335301321373118L;

    public Oid oid = null;

    private String entreprise;

    private String site;

    private String typePost;

    private String date;

    private String resources;

    private String justification;

    private Project inverseProject;

    public GeneralInformation() {
	this.entreprise = "";
	this.site = "";
	this.typePost = "";
	this.date = "";
	this.resources = "";
	this.justification = "";
    }

    public GeneralInformation(String pentreprise, String psite,
	    String ptypePost, String pdate, String presources,
	    String justification, Oid o) {
	this.entreprise = pentreprise;
	this.site = psite;
	this.typePost = ptypePost;
	this.date = pdate;
	this.resources = presources;
	this.justification = justification;
	this.oid = o;
    }

    public String toSPF() {
	String spfString = oid.get() + "=" + "GeneralInformation" + "(" + "'"
		+ entreprise + "'" + "," + "'" + site + "'" + "," + "'"
		+ typePost + "'" + "," + "'" + date + "'" + "," + "'"
		+ resources + "'" + "," + "'" + justification + "'" + ");";
	return spfString;
    }

    public Element toXML(Document doc) {
	Element racine = doc.createElement("projectinformation");
	racine.setAttribute("idkmad", oid.get());
	racine.setAttribute("classkmad", "project.GeneralInformation");

	if (!this.entreprise.equals("")) {
	    Element infoCompagny = doc
		    .createElement("projectinformation-compagny");
	    infoCompagny.setTextContent(entreprise);
	    racine.appendChild(infoCompagny);
	}

	if (!this.site.equals("")) {
	    Element infoPlace = doc.createElement("projectinformation-place");
	    infoPlace.setTextContent(site);
	    racine.appendChild(infoPlace);
	}

	if (!this.typePost.equals("")) {
	    Element infoType = doc.createElement("projectinformation-type");
	    infoType.setTextContent(typePost);
	    racine.appendChild(infoType);
	}

	if (!this.date.equals("")) {
	    Element infoDate = doc.createElement("projectinformation-date");
	    infoDate.setTextContent(date);
	    racine.appendChild(infoDate);
	}

	if (!this.resources.equals("")) {
	    Element infoResources = doc
		    .createElement("projectinformation-resources");
	    infoResources.setTextContent(resources);
	    racine.appendChild(infoResources);
	}

	if (!this.justification.equals("")) {
	    Element infoMotivation = doc
		    .createElement("projectinformation-motivation");
	    infoMotivation.setTextContent(justification);
	    racine.appendChild(infoMotivation);
	}

	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList userPreValue = p
		.getElementsByTagName("projectinformation-compagny");
	if (userPreValue.item(0) != null) {
	    entreprise = userPreValue.item(0).getTextContent();
	}
	userPreValue = p.getElementsByTagName("projectinformation-place");
	if (userPreValue.item(0) != null) {
	    site = userPreValue.item(0).getTextContent();
	}
	userPreValue = p.getElementsByTagName("projectinformation-type");
	if (userPreValue.item(0) != null) {
	    typePost = userPreValue.item(0).getTextContent();
	}
	userPreValue = p.getElementsByTagName("projectinformation-date");
	if (userPreValue.item(0) != null) {
	    date = userPreValue.item(0).getTextContent();
	}
	userPreValue = p.getElementsByTagName("projectinformation-resources");
	if (userPreValue.item(0) != null) {
	    resources = userPreValue.item(0).getTextContent();
	}
	userPreValue = p.getElementsByTagName("projectinformation-motivation");
	if (userPreValue.item(0) != null) {
	    justification = userPreValue.item(0).getTextContent();
	}
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return this.oid;
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
     * @param date
     *            The date to set.
     */
    public void setDate(String date) {
	this.date = date;
    }

    /**
     * @return Returns the entreprise.
     */
    public String getEntreprise() {
	return entreprise;
    }

    /**
     * @param entreprise
     *            The entreprise to set.
     */
    public void setEntreprise(String entreprise) {
	this.entreprise = entreprise;
    }

    /**
     * @return Returns the resources.
     */
    public String getResources() {
	return resources;
    }

    /**
     * @param resources
     *            The resources to set.
     */
    public void setResources(String resources) {
	this.resources = resources;
    }

    /**
     * @return Returns the site.
     */
    public String getSite() {
	return site;
    }

    /**
     * @param site
     *            The site to set.
     */
    public void setSite(String site) {
	this.site = site;
    }

    /**
     * @return Returns the typePost.
     */
    public String getTypePost() {
	return typePost;
    }

    /**
     * @param typePost
     *            The typePost to set.
     */
    public void setTypePost(String typePost) {
	this.typePost = typePost;
    }

    /**
     * @return Returns the inverseProject.
     */
    public Project getInverseProject() {
	return inverseProject;
    }

    /**
     * @param inverseProject
     *            The inverseProject to set.
     */
    public void setInverseProject(Project inverseProject) {
	this.inverseProject = inverseProject;
    }

    /**
     * @return Returns the justification.
     */
    public String getJustification() {
	return justification;
    }

    /**
     * @param justification
     *            The justification to set.
     */
    public void setJustification(String justification) {
	this.justification = justification;
    }
}

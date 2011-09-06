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

/**
 * @author Delphine AUTARD and Mickaël BARON
 **/
public enum Experience implements Enumere {
    INCONNU("UNK"), EXPERT("HIGH"), MOYEN("MIDDLE"), NOVICE("LOW");

    private final String enonce;

    private Experience(String s) {
	enonce = s;
    }

    public static Experience getValue(String s) {
	for (Experience i : Experience.values()) {
	    if (s.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return null;
    }

    public static String[] getNameLocaleExperience() {
	String[] myExperienceArray = new String[4];
	myExperienceArray[0] = ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
	myExperienceArray[1] = ExpressConstant.HIGH_EXPERIENCE_NAME;
	myExperienceArray[2] = ExpressConstant.AVERAGE_EXPERIENCE_NAME;
	myExperienceArray[3] = ExpressConstant.LOW_EXPERIENCE_NAME;
	return myExperienceArray;
    }

    public static String getEnumereIntoLocaleExperience(String exp) {
	if (exp.equals("UNK"))
	    return ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
	else if (exp.equals("HIGH"))
	    return ExpressConstant.HIGH_EXPERIENCE_NAME;
	else if (exp.equals("MIDDLE"))
	    return ExpressConstant.AVERAGE_EXPERIENCE_NAME;
	else if (exp.equals("LOW"))
	    return ExpressConstant.LOW_EXPERIENCE_NAME;
	else
	    return ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
    }

    public static String getLocaleExperienceIntoEnumere(String experience) {
	if (experience.equals(ExpressConstant.UNKNOWN_EXPERIENCE_NAME)) {
	    return "UNK";
	} else if (experience.equals(ExpressConstant.HIGH_EXPERIENCE_NAME)) {
	    return "HIGH";
	} else if (experience.equals(ExpressConstant.AVERAGE_EXPERIENCE_NAME)) {
	    return "MIDDLE";
	} else if (experience.equals(ExpressConstant.LOW_EXPERIENCE_NAME)) {
	    return "LOW";
	}
	return "UNK";
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element kmadActorExperience = doc.createElement("actor-experience");
	kmadActorExperience.setTextContent(this.enonce);
	return kmadActorExperience;
    }

    public static Experience getXMLExperienceValue(org.w3c.dom.Element p) {
	NodeList nodeList = p.getElementsByTagName("actor-experience");
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}
	String value = (String) nodeList.item(0).getTextContent();

	for (Experience i : Experience.values()) {
	    if (value.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public String toSPF() {
	return "." + enonce + ".";
    }

    public String getValue() {
	return enonce;
    }

    public org.w3c.dom.Element toXML2(Document doc) {
	// TODO Auto-generated method stub
	return this.toXML(doc);
    }
}

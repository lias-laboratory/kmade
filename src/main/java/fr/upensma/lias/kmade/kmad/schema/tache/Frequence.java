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
 * @author Delphine AUTARD and Mickael BARON
 **/
public enum Frequence implements Enumerated {
    INCONNU("UNK"), ELEVE("HIGH"), MOYENNE("AVERAGE"), FAIBLE("LOW");

    private final String value;

    private Frequence(String s) {
	value = s;
    }

    public String getValue() {
	return value;
    }

    public String toString() {
	return Frequence.getEnumereIntoLocaleFrequence(value);
    }

    public static Frequence getValue(String s) {
	for (Frequence i : Frequence.values()) {
	    if (s.equalsIgnoreCase(i.value)) {
		return i;
	    }
	}
	return null;
    }

    public static Frequence getXMLFrequenceValue(org.w3c.dom.Element p) {
	NodeList nodeList = p.getElementsByTagName("task-frequency");
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}
	String value = (String) nodeList.item(0).getTextContent();

	for (Frequence i : Frequence.values()) {
	    if (value.equalsIgnoreCase(i.value)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public static String[] getNameLocaleFrequence() {
	String[] myFrequenceArray = new String[4];
	myFrequenceArray[0] = ExpressConstant.UNKNOWN_FREQUENCY_NAME;
	myFrequenceArray[1] = ExpressConstant.HIGH_FREQUENCY_NAME;
	myFrequenceArray[2] = ExpressConstant.AVERAGE_FREQUENCY_NAME;
	myFrequenceArray[3] = ExpressConstant.LOW_FREQUENCY_NAME;
	return myFrequenceArray;
    }

    public static String getEnumereIntoLocaleFrequence(String freq) {
	if (freq.equals("UNK"))
	    return ExpressConstant.UNKNOWN_FREQUENCY_NAME;
	else if (freq.equals("HIGH"))
	    return ExpressConstant.HIGH_FREQUENCY_NAME;
	else if (freq.equals("AVERAGE"))
	    return ExpressConstant.AVERAGE_FREQUENCY_NAME;
	else if (freq.equals("LOW"))
	    return ExpressConstant.LOW_FREQUENCY_NAME;
	else
	    return ExpressConstant.UNKNOWN_FREQUENCY_NAME;
    }

    public static String getLocaleFrequenceIntoEnumerate(String frequence) {
	if (frequence.equals(ExpressConstant.UNKNOWN_FREQUENCY_NAME)) {
	    return "UNK";
	} else if (frequence.equals(ExpressConstant.HIGH_FREQUENCY_NAME)) {
	    return "HIGH";
	} else if (frequence.equals(ExpressConstant.AVERAGE_FREQUENCY_NAME)) {
	    return "AVERAGE";
	} else if (frequence.equals(ExpressConstant.LOW_FREQUENCY_NAME)) {
	    return "LOW";
	}
	return "UNK";
    }

    @Override
    public org.w3c.dom.Element toXML2(Document doc) {
	Element kmadTaskFrequency = doc.createElement("task-frequency");
	kmadTaskFrequency.setTextContent(this.value);
	return kmadTaskFrequency;
    }

    public String toSPF() {
	return "." + value + ".";
    }


	public static Frequence getXMLFrequenceValue2(Element p) {
		NodeList nodeList = p.getElementsByTagName("task-frequency");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			return INCONNU;}
		if (nodeList.item(0) == null) {
		    return INCONNU;
		}
		String value = (String) nodeList.item(0).getTextContent();

		for (Frequence i : Frequence.values()) {
		    if (value.equalsIgnoreCase(i.value)) {
			return i;
		    }
		}
		return INCONNU;
	}
}

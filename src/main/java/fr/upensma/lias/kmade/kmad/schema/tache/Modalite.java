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
 * @authors Delphine AUTARD and Mickael BARON
 **/
public enum Modalite implements Enumere {
    INCONNU("UNK"), SENS("SENS"), COGN("COGN");

    private final String enonce;

    private Modalite(String s) {
	enonce = s;
    }

    public String toString() {
	return Modalite.getEnumereIntoLocaleModalite(enonce);
    }

    public static Modalite getXMLModalityValue(org.w3c.dom.Element p) {
	NodeList nodeList = p.getElementsByTagName("task-modality");
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}

	String value = (String) nodeList.item(0).getTextContent();
	for (Modalite i : Modalite.values()) {
	    if (value.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public static Modalite getValue(String s) {
	for (Modalite i : Modalite.values()) {
	    if (s.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return null;
    }

    public static String[] getNameLocaleModalite() {
	String[] myImportanceArray = new String[3];
	myImportanceArray[0] = ExpressConstant.UNKNOWN_MODALITY_NAME;
	myImportanceArray[1] = ExpressConstant.SENSORI_MODALITY_NAME;
	myImportanceArray[2] = ExpressConstant.COGNITIF_MODALITY_NAME;
	return myImportanceArray;
    }

    public static String getLocaleModaliteIntoEnumere(String locale) {
	if (locale.equals(ExpressConstant.UNKNOWN_MODALITY_NAME)) {
	    return "UNK";
	} else if (locale.equals(ExpressConstant.SENSORI_MODALITY_NAME)) {
	    return "SENS";
	} else if (locale.equals(ExpressConstant.COGNITIF_MODALITY_NAME)) {
	    return "COGN";
	}
	return "UNK";
    }

    public static String getEnumereIntoLocaleModalite(String imp) {
	if (imp.equals("UNK"))
	    return ExpressConstant.UNKNOWN_MODALITY_NAME;
	else if (imp.equals("SENS"))
	    return ExpressConstant.SENSORI_MODALITY_NAME;
	else if (imp.equals("COGN"))
	    return ExpressConstant.COGNITIF_MODALITY_NAME;
	else
	    return ExpressConstant.UNKNOWN_MODALITY_NAME;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element kmadTaskModality = doc.createElement("task-modality");
	kmadTaskModality.setTextContent(this.enonce);
	return kmadTaskModality;
    }

    public String toSPF() {
	return "." + enonce + ".";
    }

    public String getValue() {
	return enonce;
    }

    @Override
    public Element toXML2(Document doc) {
	// TODO Auto-generated method stub
	return this.toXML(doc);
    }

	public static Modalite getXMLModalityValue2(Element p) {
		NodeList nodeList = p.getElementsByTagName("task-modality");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			return INCONNU;}
		if (nodeList.item(0) == null) {
		    return INCONNU;
		}

		String value = (String) nodeList.item(0).getTextContent();
		for (Modalite i : Modalite.values()) {
		    if (value.equalsIgnoreCase(i.enonce)) {
			return i;
		    }
		}
		return INCONNU;
	}
}
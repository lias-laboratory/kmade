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
public enum Importance implements Enumerated {
    INCONNU("UNK"), PEU("LOW"), ASSEZ("AVERAGE"), TRES("HIGH");

    private final String enonce;

    private Importance(String s) {
	enonce = s;
    }

    public String toString() {
	return Importance.getEnumereIntoLocaleImportance(enonce);
    }

    public static Importance getXMLExecutantValue(org.w3c.dom.Element p) {
	NodeList nodeList = p.getElementsByTagName("task-importance");
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}
	String value = (String) nodeList.item(0).getTextContent();

	for (Importance i : Importance.values()) {
	    if (value.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public static Importance getValue(String s) {
	for (Importance i : Importance.values()) {
	    if (s.equalsIgnoreCase(i.enonce)) {
		return i;
	    }
	}
	return null;
    }

    public boolean isMoreImportant(Importance comp) {
	return this.ordinal() < comp.ordinal();
    }

    public static Importance getMoreImportant(Importance left, Importance right) {
	if (left.ordinal() < right.ordinal()) {
	    return right;
	} else {
	    return left;
	}
    }

    public static String[] getNameLocaleImportance() {
	String[] myImportanceArray = new String[4];
	myImportanceArray[0] = ExpressConstant.UNKNOWN_IMPORTANCE_NAME;
	myImportanceArray[1] = ExpressConstant.HIGH_IMPORTANCE_NAME;
	myImportanceArray[2] = ExpressConstant.AVERAGE_IMPORTANCE_NAME;
	myImportanceArray[3] = ExpressConstant.LOW_IMPORTANCE_NAME;
	return myImportanceArray;
    }

    public static String getLocaleImportanceIntoEnumerate(String locale) {
	if (locale.equals(ExpressConstant.UNKNOWN_IMPORTANCE_NAME)) {
	    return "UNK";
	} else if (locale.equals(ExpressConstant.HIGH_IMPORTANCE_NAME)) {
	    return "HIGH";
	} else if (locale.equals(ExpressConstant.AVERAGE_IMPORTANCE_NAME)) {
	    return "AVERAGE";
	} else if (locale.equals(ExpressConstant.LOW_IMPORTANCE_NAME)) {
	    return "LOW";
	}
	return "UNK";
    }

    public static String getEnumereIntoLocaleImportance(String imp) {
	if (imp.equals("UNK"))
	    return ExpressConstant.UNKNOWN_IMPORTANCE_NAME;
	else if (imp.equals("HIGH"))
	    return ExpressConstant.HIGH_IMPORTANCE_NAME;
	else if (imp.equals("AVERAGE"))
	    return ExpressConstant.AVERAGE_IMPORTANCE_NAME;
	else if (imp.equals("LOW"))
	    return ExpressConstant.LOW_IMPORTANCE_NAME;
	else
	    return ExpressConstant.UNKNOWN_IMPORTANCE_NAME;
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element kmadTaskImportance = doc.createElement("task-importance");
	kmadTaskImportance.setTextContent(this.enonce);
	return kmadTaskImportance;
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

	public static Importance getXMLExecutantValue2(Element p) {
		NodeList nodeList = p.getElementsByTagName("task-importance");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			return INCONNU;}
		if (nodeList.item(0) == null) {
		    return INCONNU;
		}
		String value = (String) nodeList.item(0).getTextContent();

		for (Importance i : Importance.values()) {
		    if (value.equalsIgnoreCase(i.enonce)) {
			return i;
		    }
		}
		return INCONNU;
	}
}

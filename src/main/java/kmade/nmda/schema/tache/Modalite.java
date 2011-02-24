package kmade.nmda.schema.tache;

import kmade.nmda.ExpressConstant;

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
 * @authors Delphine Autard and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public enum Modalite implements Enumere {
    INCONNU("UNK"), 
    SENS("SENS"), 
    COGN("COGN");

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
     
        String value = (String)nodeList.item(0).getTextContent();
        for(Modalite i: Modalite.values()){
            if(value.equalsIgnoreCase(i.enonce)){
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
}
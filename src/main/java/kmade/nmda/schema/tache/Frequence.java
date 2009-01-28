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
 * @author Delphine Autard and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public enum Frequence implements Enumere {
    INCONNU("UNK"), 
    ELEVE("HIGH"), 
    MOYENNE("AVERAGE"), 
    FAIBLE("LOW");

    private final String enonce;

    private Frequence(String s) {
        enonce = s;
    }

    public String getValue() {
        return enonce;
    }
    
    public String toString() {
    	return Frequence.getEnumereIntoLocaleFrequence(enonce);
    }
    
    public static Frequence getValue(String s) {
        for (Frequence i : Frequence.values()) {
            if (s.equalsIgnoreCase(i.enonce)) {
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
        String value = (String)nodeList.item(0).getTextContent();
     
        for(Frequence i: Frequence.values()){
            if(value.equalsIgnoreCase(i.enonce)){
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

    public static String getLocaleFrequenceIntoEnumere(String frequence) {
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
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element kmadTaskFrequency = doc.createElement("task-frequency");
        kmadTaskFrequency.setTextContent(this.enonce);
        return kmadTaskFrequency;
    }
    
    public String toSPF() {
        return "." + enonce + ".";
    }
}

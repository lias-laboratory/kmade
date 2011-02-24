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
 * @author Delphine Autard and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public enum Decomposition implements Enumere {
    SEQ("SEQ"), 
    PAR("PAR"), 
    ELE("LEAF"), 
    INCONNU("UNK"), 
    ALT("ALT"), 
    ET("ENT");
    
    private final String enonce;

    private Decomposition(String s) {
        enonce = s;       
    }

    public String toString() {
    	return Decomposition.getEnumereIntoLocaleDecomposition(enonce);
    }
    
    public String getValue() {
        return enonce;
    }

    public static Decomposition getXMLModalityValue(org.w3c.dom.Element p) {
        NodeList nodeList = p.getElementsByTagName("task-decomposition");
        if (nodeList.item(0) == null) {
        	return INCONNU;
        }
        String value = (String)nodeList.item(0).getTextContent();
     
        for(Decomposition i: Decomposition.values()){
            if(value.equalsIgnoreCase(i.enonce)){
                return i;
            }
        }    
        return INCONNU;
    }
    
    public static Decomposition getValue(String s) {
        for (Decomposition i : Decomposition.values()) {
            if (s.equalsIgnoreCase(i.enonce)) {
                return i;
            }
        }
        return null;
    }
 
    public org.w3c.dom.Element toXML(Document doc) {
        Element kmadTaskDecomposition = doc.createElement("task-decomposition");
        kmadTaskDecomposition.setTextContent(this.enonce);
        return kmadTaskDecomposition;
    }
    
    public String toSPF() {
        return "." + enonce + ".";
    }
    
    public static String[] getNameLocaleDecomposition() {
        String[] myDecompositionArray = new String[6];
        myDecompositionArray[0] = ExpressConstant.SEQUENTIEL_LONG_NAME;                                     
        myDecompositionArray[1] = ExpressConstant.PARALLELE_LONG_NAME;
        myDecompositionArray[2] = ExpressConstant.LEAF_LONG_NAME;
        myDecompositionArray[3] = ExpressConstant.UNKNOWN_LONG_NAME;
        myDecompositionArray[4] = ExpressConstant.ALTERNATIF_LONG_NAME;
        myDecompositionArray[5] = ExpressConstant.NO_ORDER_LONG_NAME;
        return myDecompositionArray;
    }
    
    public static Decomposition getLocaleDecompositionIntoDecomposition(String myStringDecomp) {
        if (myStringDecomp.equals(ExpressConstant.SEQUENTIEL_LONG_NAME)) 
            return Decomposition.SEQ;
        else if (myStringDecomp.equals(ExpressConstant.PARALLELE_LONG_NAME))
            return Decomposition.PAR;
        else if (myStringDecomp.equals(ExpressConstant.LEAF_LONG_NAME))
            return Decomposition.ELE;
        else if (myStringDecomp.equals(ExpressConstant.UNKNOWN_LONG_NAME))
            return Decomposition.INCONNU;
        else if (myStringDecomp.equals(ExpressConstant.ALTERNATIF_LONG_NAME))
            return Decomposition.ALT;
        else
            return Decomposition.ET;
    }
    
    // A partir d'une chaine Locale transformation en chaine décomposition.
    public static String getLocaleDecompositionIntoEnumere(String myStringDecomp) {
        if (myStringDecomp.equals(ExpressConstant.SEQUENTIEL_LONG_NAME)) 
            return "SEQ";
        else if (myStringDecomp.equals(ExpressConstant.PARALLELE_LONG_NAME))
            return "PAR";
        else if (myStringDecomp.equals(ExpressConstant.LEAF_LONG_NAME))
            return "LEAF";      
        else if (myStringDecomp.equals(ExpressConstant.UNKNOWN_LONG_NAME))
            return "UNK";
        else if (myStringDecomp.equals(ExpressConstant.ALTERNATIF_LONG_NAME))
            return "ALT";
        else if (myStringDecomp.equals(ExpressConstant.NO_ORDER_LONG_NAME))
            return "ENT";
        else
            return "UNK";
    }
    
    // A partir d'une chaine décomposition retourne la chaine locale.
    public static String getEnumereIntoLocaleDecomposition(String myStringDecomp) {
        if (myStringDecomp.equals("SEQ")) 
            return ExpressConstant.SEQUENTIEL_LONG_NAME;
        else if (myStringDecomp.equals("PAR"))
            return ExpressConstant.PARALLELE_LONG_NAME;
        else if (myStringDecomp.equals("LEAF"))
            return ExpressConstant.LEAF_LONG_NAME;      
        else if (myStringDecomp.equals("UNK"))
            return ExpressConstant.UNKNOWN_LONG_NAME;
        else if (myStringDecomp.equals("ALT"))
            return ExpressConstant.ALTERNATIF_LONG_NAME;
        else if (myStringDecomp.equals("ENT"))
            return ExpressConstant.NO_ORDER_LONG_NAME;
        else
            return null;
    } 
}
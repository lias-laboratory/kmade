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
public enum Executant implements Enumere {
    INCONNU("UNK", ExpressConstant.UNKNOWN_TASK_16_IMAGE),
    USER("USER", ExpressConstant.USER_TASK_16_IMAGE),
    SYS("SYS", ExpressConstant.FEEDBACK_TASK_16_IMAGE),
    INT("INT", ExpressConstant.INTERACTIF_TASK_16_IMAGE),
    ABS("ABS", ExpressConstant.ABSTRACT_TASK_16_IMAGE);
    
    private final String enonce;
    
    private final String image;
    
    private Executant(String s, String pimage) {
        enonce = s;
        image = pimage;
    }

    public String getImage() {
        return image;
    }
    
    public String getValue() {
        return enonce;
    }
    
    public String toString() {
    	return Executant.getEnumereIntoLocaleExecutant(enonce);
    }
    
    public static Executant getXMLExecutantValue(org.w3c.dom.Element p) {
        NodeList nodeList = p.getElementsByTagName("task-executant");
        if (nodeList.item(0) == null) {
        	return INCONNU;
        }
        String value = (String)nodeList.item(0).getTextContent();
     
        for(Executant i: Executant.values()){
            if(value.equalsIgnoreCase(i.enonce)){
                return i;
            }
        }    
        return INCONNU;        
    }  
    
    public static Executant getValue(String s) {
        for (Executant i : Executant.values()) {
            if (s.equalsIgnoreCase(i.enonce)) {
                return i;
            }
        }
        return null;
    }

    public org.w3c.dom.Element toXML(Document doc) {
        Element kmadTaskExecutant = doc.createElement("task-executant");
        kmadTaskExecutant.setTextContent(this.enonce);
        return kmadTaskExecutant;
    }
    
    public String toSPF() {
        return "." + enonce + ".";
    }
   
    // Retourne un tableau de String.
    public static String[] getNameLocaleExecutant() {
        String[] myExecutantArray = new String[5];
        myExecutantArray[0] = ExpressConstant.UNKNOWN_EXECUTANT_NAME;
        myExecutantArray[1] = ExpressConstant.USER_EXECUTANT_NAME;
        myExecutantArray[2] = ExpressConstant.SYSTEM_EXECUTANT_NAME;
        myExecutantArray[3] = ExpressConstant.INTERACTION_EXECUTANT_NAME;
        myExecutantArray[4] = ExpressConstant.ABSTRACT_EXECUTANT_NAME;
        return myExecutantArray;
    }
    
    public static String[] getImageLocaleExecutant() {
        String[] myExecutantArray = new String[5];
        myExecutantArray[0] = Executant.INCONNU.getImage();
        myExecutantArray[1] = Executant.USER.getImage();
        myExecutantArray[2] = Executant.SYS.getImage();
        myExecutantArray[3] = Executant.INT.getImage();
        myExecutantArray[4] = Executant.ABS.getImage();
        return myExecutantArray;
    }
    
    public static Executant getLocaleExecutantIntoExecutant(String myStringExecutant) {
        if (myStringExecutant.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME)) 
            return Executant.INCONNU;
        else if (myStringExecutant.equals(ExpressConstant.USER_EXECUTANT_NAME))
            return Executant.USER;
        else if (myStringExecutant.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
            return Executant.SYS;
        else if (myStringExecutant.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
            return Executant.INT;
        else if (myStringExecutant.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
            return Executant.ABS;
        else
            return Executant.INCONNU;
    }
    
    public static String getLocaleExecutantIntoEnumere(String myStringExecutant) {
        if (myStringExecutant.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME)) 
            return "UNK";
        else if (myStringExecutant.equals(ExpressConstant.USER_EXECUTANT_NAME))
            return "USER";
        else if (myStringExecutant.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
            return "SYS";      
        else if (myStringExecutant.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
            return "INT";
        else if (myStringExecutant.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
            return "ABS";
        else
            return "UNK";
    }
    
    public static int getLocaleExecutantAt(String myStringExecutant) {
        if (myStringExecutant.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME)) 
            return 0;
        else if (myStringExecutant.equals(ExpressConstant.USER_EXECUTANT_NAME))
            return 1;
        else if (myStringExecutant.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
            return 2;      
        else if (myStringExecutant.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
            return 3;
        else if (myStringExecutant.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
            return 4;
        else
            return 0;
    }
    
    public static String getEnumereIntoLocaleExecutant(String myStringExecutant) {
        if (myStringExecutant.equals("UNK")) 
            return ExpressConstant.UNKNOWN_EXECUTANT_NAME;
        else if (myStringExecutant.equals("USER"))
            return ExpressConstant.USER_EXECUTANT_NAME;
        else if (myStringExecutant.equals("SYS"))
            return ExpressConstant.SYSTEM_EXECUTANT_NAME;      
        else if (myStringExecutant.equals("INT"))
            return ExpressConstant.INTERACTION_EXECUTANT_NAME;
        else if (myStringExecutant.equals("ABS"))
            return ExpressConstant.ABSTRACT_EXECUTANT_NAME;        
        else
            return null;
    }
    
    public static int getEnumereIntoLocaleAt(String myStringExecutant) {
        if (myStringExecutant.equals("UNK")) 
            return 0;
        else if (myStringExecutant.equals("USER"))
            return 1;
        else if (myStringExecutant.equals("SYS"))
            return 2;      
        else if (myStringExecutant.equals("INT"))
            return 3;
        else if (myStringExecutant.equals("ABS"))
            return 4;        
        else
            return 0;
    } 
}

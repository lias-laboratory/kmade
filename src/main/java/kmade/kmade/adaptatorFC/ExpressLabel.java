package kmade.kmade.adaptatorFC;

import java.awt.Color;
import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Label;

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
public final class ExpressLabel {
	public static String createLabel() {
		Oid oidEvent = InterfaceExpressJava.createEntity("tache", "Label");
	    return (oidEvent.get());
	}
	
	public static void setLabelDescription(String oid, String description) {
		Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	    m.setDescription(description);
	}
	
	public static void setLabelColor(String oid, Color myColor) {
		Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	    m.setColor(myColor);
	}
	
	public static void setLabelVisible(String oid, boolean isVisible) {
		Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
	    m.setVisible(isVisible);
	}

    /**
     * @param newLabelObject
     * @param value
     * @return
     */
    public static String setLabelName(String newLabelObject, String value) {
        Label m = (Label) InterfaceExpressJava.prendre(new Oid(newLabelObject));
        m.setName(value);
        return m.getName();
    }

    public static ArrayList<Label> getLabels() {
        ArrayList<Label> lst = new ArrayList<Label>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Label");
        for (int i = 0; i < objs.length; i++) {
            Label obj = (Label) objs[i];
            lst.add(obj);
        }
        return lst;
    }
    
    public static ArrayList<String> getLabelsNameList() {
        ArrayList<String> lst = new ArrayList<String>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Label");
        for (int i = 0; i < objs.length; i++) {
            Label obj = (Label) objs[i];
            lst.add(obj.getName());
        }
        return lst;
    }
    
    public static String[] getLabelsNameArray() {
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Label");
        String[] array = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            Label obj = (Label) objs[i];
            array[i] = obj.getName();
        }
        return array;
    }
    
    public static Label stringToLabel(String s) {
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Label");
        for (int i = 0; i < objs.length; i++) {
            Label obj = (Label)objs[i];
            if (obj.getName().equals(s)) {
                return obj;
            }
        }
        return null;
    }

    public static void affRemoveLabel(String oid) {
        Label m = (Label) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();        
    }

    public static void removeLabel(String oidAct) {
        Label a = (Label) InterfaceExpressJava.prendre(new Oid(oidAct));
        a.delete();       
    }

    /**
     * @param oidRow
     * @param boolean1
     */
    public static void setLabelColorVisible(String oidRow, boolean boolean1) {
        Label m = (Label) InterfaceExpressJava.prendre(new Oid(oidRow));
        m.setColorVisible(boolean1);
    } 
}

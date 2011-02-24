package fr.upensma.lias.kmade.kmad.schema.metaobjet;


import org.w3c.dom.Document;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

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
public class EnumValue extends ValeurType implements Entity {

    private static final long serialVersionUID = -5037423451302969688L;

    public Enumeration enumere = null;

    public Element element = null;

    public EnumValue() {
        enumere = null;
        element = null;
    }

    public EnumValue(Enumeration enumere, Element elem) {
        this.enumere = enumere;
        this.element = elem;
    }

    public EnumValue(Enumeration enumere, Element elem, Oid oid) {
        this.enumere = enumere;
        this.element = elem;
        this.oid = oid;
    }

    public void setEnumeration(Enumeration e) {
        System.out.println(e);
        this.enumere = e;
    }

    public void setElement(Element e) {
        System.out.println(e);
        this.element = e;
    }

    public String toSPF() {
        if (this.element == null) {
            if (enumere == null) {
                return (oid.get() + "=" + "EnumValue" + "(" + "Not defined" + ",$);");   
            } else {
                return (oid.get() + "=" + "EnumValue" + "(" + this.enumere.getOid().get() + ",$);");
            }            
        }
        if (enumere == null) {
            return (oid.get() + "=" + "EnumValue" + "("
                    + "Not defined" + ","
                    + this.element.getOid().get() + ");");
        } else {
            return (oid.get() + "=" + "EnumValue" + "("
                    + this.enumere.getOid().get() + ","
                    + this.element.getOid().get() + ");");
        }        
    }

    public String toString() {
        if (this.element != null) {
            return this.element.getName();
        } else {
            return "?";
        }
    }

    public TypeStructure getType() {
        return TypeStructure.ENUM_STRUCT;
    }

    public Object getValeur() {
        if (this.element != null) {
            return this.element.getName();
        } else {
            return null;
        }
    }

    public void setValeur(String s) {
        for (int i = 0; i < enumere.getInverseElementDe().size(); i++) {
            Element elem = enumere.getInverseElementDe().get(i);
            if (elem.getName().equals(s)) {
                element = elem;
            }
        }
    }

    public void modify(String s) {
        this.element = new Element(s, this.enumere);
    }

    public int getTaille() {
        return this.element.getTaille();
    }

    public String getName() {
        String retValue;

        retValue = super.getName();
        return retValue;
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        return null;
    }
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        
    }

    public Object clone() {
    	ValeurType clone = new StrValue();
    	clone.oid = this.oid;
    	return clone;
    }
}

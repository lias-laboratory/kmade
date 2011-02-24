package fr.upensma.lias.kmade.kmad.schema.metaobjet;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
 * @author Vincent Lucquiaud and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class StrValue extends ValeurType {

    private static final long serialVersionUID = 1689930001606248935L;

    private String valeur = "";

    public StrValue() {
        this.setValeur("");
        valeur = "";
    }

    public StrValue(String data) {
        this.setValeur(data);
    }

    public StrValue(String data, Oid oid) {
        this.oid = oid;
        this.setValeur(data);
    }

    public String toString() {
        if (valeur == null) {
            return "";
        }
        return valeur;
    }

    public void setValeur(String s) {
        valeur = s;
    }

    public Object getValeur() {
        return valeur;
    }

    public String toSPF() {
        if (valeur == null) {
            return (this.oid.get() + "=StrValue($);");
        }
        return (this.oid.get() + "=StrValue('" + this.valeur + "');");
    }

    public TypeStructure getType() {
        return TypeStructure.STRING_STRUCT;
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.StrValue");
         
        return racine;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));
        
        NodeList nodeList = p.getElementsByTagName("typevalue-value");
        this.setValeur(nodeList.item(0).getTextContent());
    }   
    
    public Object clone() {
    	ValeurType clone = new StrValue();
    	clone.oid = this.oid;
    	clone.setValeur(this.valeur);
    	return clone;
    }
}

package kmade.nmda.schema.metaobjet;

import kmade.nmda.schema.Oid;

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
public class BoolValue extends ValeurType {

    private static final long serialVersionUID = -6987507370130373247L;

    private Boolean valeur;
    
    public BoolValue() {
        valeur = null;        
    }

    public BoolValue(String data) {
        this.setValeur(data);
    }

    public BoolValue(String data, Oid oid) {
        this.setValeur(data);
        this.oid = oid;
    }    

    public Object getValeur() {        
        return this.valeur;
    }

    public String toSPF() {
        if (this.valeur == null) {
            return (this.oid.get() + "=BoolValue($);");
        } else {
            return (this.oid.get() + "=BoolValue('" + this.valeur + "');");
        }
    }

    public String toString() {
        if (this.valeur == null) {
            return "";
        }
        return this.valeur.toString();
    }

    public TypeStructure getType() {
        return TypeStructure.BOOLEAN_STRUCT;
    }

    public void setValeur(String s) {
        if (s == null) {
            valeur = false;
        } else if (s.equalsIgnoreCase("true")) {
            valeur = true;
        } else {
            valeur = false;
        }
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.BoolValue");
         
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
    	clone.setValeur(this.valeur.toString());
    	return clone;
    }
}

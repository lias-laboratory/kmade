package kmade.nmda.schema.metaobjet;

import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;

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
 * @authors Vincent Lucquiaud and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class IntervalleValue extends ValeurType {

    private static final long serialVersionUID = 5338273650635386769L;

    public Intervalle intervalle = null;

    public Integer valeur;

    public IntervalleValue() {
        valeur = 0;
    }

    public IntervalleValue(Intervalle inter, String value) {
        this.intervalle = inter;
        if (value == null) {
            this.valeur = null;
        } else {
            this.valeur = new Integer(value);
        }
    }

    public IntervalleValue(Integer value, Intervalle inter, Oid oid) {
        this.intervalle = inter;
        this.valeur = value;
        this.oid = oid;
    }

    public void setIntervalle(Intervalle i) {
        this.intervalle = i;
    }

    public TypeStructure getType() {
        return TypeStructure.INTERVAL_STRUCT;
    }

    public Object getValeur() {
        if (this.valeur == null) {
            return null;
        }
        return this.valeur;
    }

    public String toSPF() {
        if (this.valeur == null) {
            if (intervalle == null) 
                return (this.oid.get() + "=IntervalleValue($," + "Not defined" + ");");
            else
                return (this.oid.get() + "=IntervalleValue($," + this.intervalle.oid.get() + ");");
        } else {
            if (intervalle == null) 
                return (this.oid.get() + "=IntervalleValue(" + this.valeur + "," + "Not defined" + ");");
            else 
                return (this.oid.get() + "=IntervalleValue(" + this.valeur + "," + this.intervalle.oid.get() + ");");
        }
    }

    public String toString() {
        if (this.valeur == null) {
            return "";
        }
        return this.valeur.toString();
    }

    public void modify(String s) {
        this.valeur = new Integer(s);
    }

    public int getTaille() {
        return this.valeur.toString().length();
    }

    public void setValeur(String s) {
        this.valeur = new Integer(s);
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

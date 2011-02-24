package kmade.nmda.schema.metaobjet;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
 * @authors Vincent Lucquiaud and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public abstract class ValeurType implements Entity, Cloneable {
	private static final long serialVersionUID = 5673942188790701306L;
	
	public Oid oid;

    public ValeurType() { }

    public abstract TypeStructure getType();

    public abstract Object getValeur();

    public abstract void setValeur(String s);

    public abstract String toString();
   
    public void delete() {
        InterfaceExpressJava.remove(oid);
    }
    
    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public Oid getOid() {
        return oid;
    }   

    public String getName() {
        return "";
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("typevalue");
        racine.setAttribute("idkmad", oid.get());
         
        Element valueElement = doc.createElement("typevalue-value");
        valueElement.setTextContent(this.toString());
        racine.appendChild(valueElement);
        
        return racine;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public Object clone() {
    	try {
    		return super.clone();
    	} catch (CloneNotSupportedException e) {
    		return null;
    	}
    }
}

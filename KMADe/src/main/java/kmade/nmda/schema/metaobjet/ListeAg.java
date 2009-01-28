package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

import kmade.nmda.schema.Entity;
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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class ListeAg extends Agregat implements Entity {
 
    private static final long serialVersionUID = -1653901133419278678L;

    public ListeAg() {
        this.lstObjConcrets = new ArrayList<ObjetConcret>();
        this.agregatStruct = AgregatStructure.LIST_AGREGAT;
    }

    public ListeAg(Oid oid) {
        this.lstObjConcrets = new ArrayList<ObjetConcret>();
        this.agregatStruct = AgregatStructure.LIST_AGREGAT;
        this.oid = oid;
    }

    public ListeAg(ArrayList<ObjetConcret> lst, Oid oid) {
        this.lstObjConcrets = lst;
        this.oid = oid;
        this.agregatStruct = AgregatStructure.LIST_AGREGAT;
    }

    public ObjetConcret get() {
        try {
            return this.lstObjConcrets.get(0);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean put(ObjetConcret i) {
        this.lstObjConcrets.add(i);
        return true;
    }

    public String toSPF() {
        String SPF = this.oid.get() + "=ListeAg()";
        return SPF;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public Oid getOid() {
        return oid;
    }
    
    public String getName() {
    		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.LIST_AGREGAT.getValue());
    	}    
    
    public org.w3c.dom.Element toXML(Document doc) {
        org.w3c.dom.Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.ListeAg");
        return racine;
    }
}

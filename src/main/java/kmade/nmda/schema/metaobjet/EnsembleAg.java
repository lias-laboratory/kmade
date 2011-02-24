package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

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
 * @author Delphine Autard and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class EnsembleAg extends Agregat { 

    private static final long serialVersionUID = 920371404772169860L;

    public EnsembleAg() {
        this.lstObjConcrets = new ArrayList<ObjetConcret>();
        this.agregatStruct = AgregatStructure.SET_AGREGAT;
    }

    public EnsembleAg(Oid oid) {
        this.lstObjConcrets = new ArrayList<ObjetConcret>();
        this.agregatStruct = AgregatStructure.SET_AGREGAT;
        this.oid = oid;
    }

    public EnsembleAg(ArrayList<ObjetConcret> lst, Oid oid) {
        this.lstObjConcrets = lst;
        this.oid = oid;
        this.agregatStruct = AgregatStructure.SET_AGREGAT;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public boolean put(ObjetConcret i) {
        this.lstObjConcrets.add(i);
        return true;
    }

    public ObjetConcret get() {
        if (lstObjConcrets.size() == 1) {
            return lstObjConcrets.get(0);
        }
        return null;
    }

    public String toSPF() {
        String SPF = this.oid.get() + "=EnsembleAg();";
        return SPF;
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        org.w3c.dom.Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.EnsembleAg");
        return racine;
    }
    
    public String getName() {
		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.SET_AGREGAT.getValue());
	}    
}

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
public class PileAg extends Agregat {
 
    private static final long serialVersionUID = 8638149917321920353L;

    public PileAg() {
        this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public PileAg(Oid oid) {
        this.oid = oid;
        this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public PileAg(ArrayList<ObjetConcret> lst, Oid oid) {
        this.lstObjConcrets = lst;
        for (int i = 0; i < lst.size(); i++) {
            ObjetConcret oc = lst.get(i);
            oc.setAppartientGroupe(this.inverseGroupe);
        }
        this.oid = oid;
        this.agregatStruct = AgregatStructure.STACK_AGREGAT;
    }

    public boolean put(ObjetConcret co) {
        this.lstObjConcrets.add(0, co);
        return true;
    }

    public ObjetConcret get() {
        return this.lstObjConcrets.get(0);
    }

    public String toSPF() {
        String SPF = this.oid.get() + "=PileAg();";
        return SPF;
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        org.w3c.dom.Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.PileAg");
        return racine;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }
    
    public String getName() {
		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.STACK_AGREGAT.getValue());
	}    
}

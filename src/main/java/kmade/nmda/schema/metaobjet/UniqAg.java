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
 * @authors Delphine Autard and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class UniqAg extends Agregat {

    private static final long serialVersionUID = 4998023325756830369L;

    public UniqAg() {
        this.lstObjConcrets=new ArrayList<ObjetConcret>(1);
        this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }
    
     public UniqAg(Oid oid) {
        this.lstObjConcrets=new ArrayList<ObjetConcret>(1);
        this.oid=oid;
        this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }
    
    public UniqAg(ArrayList<ObjetConcret> lst,Oid oid) {
        this.lstObjConcrets=lst;
        this.oid=oid;
        this.agregatStruct = AgregatStructure.SINGLETON_AGREGAT;
    }
  
    
    public boolean put(ObjetConcret i) {
        if (this.lstObjConcrets.size()==0){
            this.lstObjConcrets.add(i);
            return true;
        } else {
            return false;
        }
    }
    
    public ObjetConcret get() {
        return this.lstObjConcrets.get(0);
    }

    public String toSPF(){
        String SPF= this.oid.get() + "=UniqAg();";
        return SPF;        
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        org.w3c.dom.Element racine = super.toXML(doc);
        racine.setAttribute("classkmad", "metaobjet.UniqAg");
        return racine;
    }
    
    public void setOid(Oid oid){
        this.oid=oid;
    }
    
    public String getName() {
		return AgregatStructure.getEnumereIntoLocaleAgregatStructure(AgregatStructure.SINGLETON_AGREGAT.getValue());
	} 
    
    public void moveConcreteObject(ArrayList<ObjetConcret> p) {
        lstObjConcrets = new ArrayList<ObjetConcret>();
        if (p.size() >= 1) 
            lstObjConcrets.add(p.get(0));
        
        int taille = p.size();
        for (int i = 1; i < taille; i++) {
            ObjetConcret obj = p.get(i);
            obj.delete();
        }
    }
}

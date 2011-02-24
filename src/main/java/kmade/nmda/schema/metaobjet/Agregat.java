package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public abstract class Agregat implements Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3620717672270513861L;

	public Oid oid = null;

    protected AgregatStructure agregatStruct;
    
    protected Groupe inverseGroupe = null;

    protected ArrayList<ObjetConcret> lstObjConcrets = new ArrayList<ObjetConcret>();

    public abstract ObjetConcret get();

    public abstract boolean put(ObjetConcret i);
    
    public boolean isEmpty() {
        return lstObjConcrets.isEmpty();
    }

    public int size() {
        return lstObjConcrets.size();
    }

    public AgregatStructure getAgregatStructure() {
        return this.agregatStruct;
    }

    public void removeFromConcreteObject(ObjetConcret o) {
        lstObjConcrets.remove(o);
    }
    
    public void removeAllConcreteObject() {
    	lstObjConcrets = new ArrayList<ObjetConcret>();
    }

    public ArrayList<ObjetConcret> getLstObjConcrets() {
        return this.lstObjConcrets;
    }

    public void setInverseGroupe(Groupe g) {
        inverseGroupe = g;
    }

    public Groupe getInverseGroupe() {
        return inverseGroupe;
    }

    public boolean isUnique(ObjetConcret o) {
        return lstObjConcrets.indexOf(o) == -1;
    }

    public Oid getOid() {
        return oid;
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("agregat");
        racine.setAttribute("idkmad", oid.get());
        
        if (lstObjConcrets.size() != 0) {
            Element idAgregatList = doc.createElement("id-agregat-concreteobjects-list"); 
            for (int i = 0 ; i < lstObjConcrets.size(); i++) {
                Element idConcreteObjects = doc.createElement("id-agregat-concreteobject");
                idConcreteObjects.setTextContent(lstObjConcrets.get(i).getOid().get());
                idAgregatList.appendChild(idConcreteObjects);
            }        
            racine.appendChild(idAgregatList);
        }
        return racine;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));
    }
    
    public void delete() {       
        InterfaceExpressJava.remove(oid);
    }
    
    public void moveConcreteObject(ArrayList<ObjetConcret> p) {
        lstObjConcrets = new ArrayList<ObjetConcret>();
        lstObjConcrets.addAll(p);
    }
}

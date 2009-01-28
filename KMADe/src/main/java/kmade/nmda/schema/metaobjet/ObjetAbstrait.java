package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
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
public class ObjetAbstrait implements Entity {

    private static final long serialVersionUID = 7595794787552617084L;

    public Oid oid = null;

    private String name = null;

    private String description = null;

    private ArrayList<ObjetConcret> inverseObjConcDe = null;

    private ArrayList<AttributAbstrait> inverseAttributsAbs = null;

    private ArrayList<Groupe> inverseGroupe = null;
    
    public ObjetAbstrait() {
        this.name = "";
        this.description = "";
        this.inverseAttributsAbs = new ArrayList<AttributAbstrait>();
        this.inverseObjConcDe = new ArrayList<ObjetConcret>();
        this.inverseGroupe = new ArrayList<Groupe>();
    }

    public ObjetAbstrait(String name, String description, Oid oid) {
        this.name = name;
        this.description = description;
        this.inverseAttributsAbs = new ArrayList<AttributAbstrait>();
        this.inverseObjConcDe = new ArrayList<ObjetConcret>();
        this.inverseGroupe = new ArrayList<Groupe>();
        this.oid = oid;
    }

    public void delete() {
        int tailleG = this.inverseGroupe.size();
        for (int i = 0; i < tailleG; i++) {
            Groupe g = this.inverseGroupe.get(0);
            g.removeGroup();
            InterfaceExpressJava.remove(g.getOid());
        }
        int taille = this.inverseAttributsAbs.size();
        for (int i = 0; i < taille; i++) {
            AttributAbstrait a = this.inverseAttributsAbs.get(0);
            a.delete();
            InterfaceExpressJava.remove(a.getOid());
        }
        InterfaceExpressJava.remove(this.oid);
    }

    public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 0);
        for (int i = 0; i < this.inverseGroupe.size(); i++) {
            Groupe g = this.inverseGroupe.get(i);
            g.affDelete();
        }
        for (int i = 0; i < this.inverseAttributsAbs.size(); i++) {
            AttributAbstrait a = this.inverseAttributsAbs.get(i);
            a.affDelete();
        }
    }

    public void addInverseAttributsAbs(AttributAbstrait attributabs) {
        inverseAttributsAbs.add(attributabs);
    }

    public void removeInverseAttributsAbs(AttributAbstrait attributabs) {
        inverseAttributsAbs.remove(attributabs);
    }

    public void addInverseGroupe(Groupe g) {
        this.inverseGroupe.add(g);
    }

    public void removeInverseGroupe(Groupe g) {
        this.inverseGroupe.remove(g);
    }

    public ArrayList<Groupe> getInverseGroupe() {
        return this.inverseGroupe;
    }

    public ArrayList<AttributAbstrait> getInverseAttributsAbs() {
        return this.inverseAttributsAbs;
    }

    public void addInverseObjConc(ObjetConcret ObjConc) {
        inverseObjConcDe.add(ObjConc);
    }

    public void removeInverseObjConc(ObjetConcret ObjConc) {
        inverseObjConcDe.remove(ObjConc);
    }

    public ArrayList<ObjetConcret> getInverseObjConc() {
        return this.inverseObjConcDe;
    }
    
    public void removeAllConcreteObject() {
    	inverseObjConcDe = new ArrayList<ObjetConcret>();
    }

    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("abstractobject");
        racine.setAttribute("classkmad", "metaobjet.ObjetAbstrait");
        racine.setAttribute("idkmad", oid.get());
        
        Element element = doc.createElement("abstractobject-name");
        element.setTextContent(this.getName());
        racine.appendChild(element);
        
        if (!this.description.equals("")) {
            element = doc.createElement("abstractobject-description");
            element.setTextContent(this.description);
            racine.appendChild(element);
        }

        return racine;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList nodeList = p.getElementsByTagName("abstractobject-name");
        this.name = nodeList.item(0).getTextContent();
        
        nodeList = p.getElementsByTagName("abstractobject-description");
        if (nodeList.item(0) != null) {
            this.description = nodeList.item(0).getTextContent();
        }
    }
    
    public String toSPF() {
        return (this.oid.get() + "=" + "ObjetAbstrait" + "(" + "'" + name + "'"
                + "," + "'" + description + "'" + ")");        
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public Oid getOid() {
        return this.oid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        boolean ok = false;
        int cpt = 0;
        n = n.replace(" ", "_");
        while (!ok) {
            if (cpt != 0) {
                if (cpt == 1) {
                    n = n + "_" + String.valueOf(cpt);
                } else {
                    n = n.substring(0, n.length() - 1) + String.valueOf(cpt);
                }
            }
            ok = isUniqueName(n);
            cpt++;
        }
        name = n;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String d) {
        description = d;
    }

    public int getTaille() {
        return this.name.length();
    }

    public static boolean isUniqueName(String s) {
        Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetAbstrait");
        for (int i = 0; i < objAbs.length; i++) {
            ObjetAbstrait obj = (ObjetAbstrait) objAbs[i];
            if (s.equalsIgnoreCase(obj.name)) {
                return false;
            }
        }
        return true;
    }

    public boolean unGroupePresent() {
        return (inverseGroupe.size() != 0);
    }

    public boolean noSpace() {
        return (name.indexOf(" ") == -1);
    }
    
    public String toString() {
    	return this.name;
    }    
}

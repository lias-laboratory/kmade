package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;
import java.util.Iterator;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
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
 * @author Vincent Lucquiaud and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public final class Enumeration extends TypeAbs {

    private static final long serialVersionUID = -5667088185950172365L;

    private ArrayList<Element> inverseElementDe = null;
    
    public Enumeration() {
        this.name = "";
        this.description = "";
        this.inverseElementDe = new ArrayList<Element>();
    }

    public Enumeration(String name, String description, Oid oid) {
        this.name = name;
        this.description = description;
        this.inverseElementDe = new ArrayList<Element>();
        this.oid = oid;
    }

    public Enumeration(String name) {
        this.name = name;
        this.description = "";
        this.inverseElementDe = new ArrayList<Element>();
    }

    public void addInverseElementDe(Element element) {
        inverseElementDe.add(element);
    }

    public void removeInverseElementDe(Element element) {
        inverseElementDe.remove(element);
    }

    public ArrayList<Element> getInverseElementDe() {
        return this.inverseElementDe;
    }

    public Element getElement(String n) {
        for (int i = 0; i < inverseElementDe.size(); i++) {
            Element e = inverseElementDe.get(i);
            if (e.getName().equals(n)) {
                return e;
            }
        }
        return null;
    }

    public void delete() {
        for (int i = 0; i < this.inverseElementDe.size(); i++) {
            Element g = this.inverseElementDe.get(i);            
            g.delete();
        }
        this.inverseElementDe.clear();

        for (int j = 0; j < this.inverseAttributAbs.size(); j++) {
            AttributAbstrait attr = this.inverseAttributAbs.get(j);
            attr.setTypeRef(null);
        }
        this.inverseAttributAbs.clear();
        InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 10);
        Iterator i = inverseElementDe.iterator();
        while (i.hasNext()) {
            Element g = (Element) i.next();
            g.affDelete();
        }
        Iterator j = this.inverseAttributAbs.iterator();
        while (j.hasNext()) {
            AttributAbstrait attr = (AttributAbstrait) j.next();
            InterfaceExpressJava.getGestionWarning().addMessage(oid,10, ExpressConstant.REMOVE_OF_THE_ABSTRACT_ATTRIBUT_MESSAGE + " \"" + attr.getName() + "\"");
        }
    }

    public String toSPF() {
        return (oid.get() + "=" + "Enumeration" + "(" + "'" + name + "'" + ","
                + "'" + description + "'" + ");");
    }

    public static String[] getInverseElementDe(Enumeration en) {
        String[] s = new String[en.inverseElementDe.size()];
        Iterator i = en.inverseElementDe.iterator();
        int j = 0;
        while (i.hasNext())
            s[j++] = ((Element) i.next()).getName();
        return s;
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

    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < inverseElementDe.size(); i++) {
            values.add(inverseElementDe.get(i).getName());
        }
        return values;
    }

    public static boolean isUniqueName(String s) {
        Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Enumeration");
        for (int i = 0; i < objAbs.length; i++) {
            Enumeration obj = (Enumeration) objAbs[i];
            if (s.equalsIgnoreCase(obj.name)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique() {
        Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Enumeration");
        for (int i = 0; i < objAbs.length; i++) {
            Enumeration obj = (Enumeration) objAbs[i];
            if (!isUniqueName(obj.name))
                return false;
        }
        return true;
    }

    public boolean noSpace() {
        return ((name.indexOf(" ")) != 1);
    }
    
    public org.w3c.dom.Element toXML(Document doc) {
        return null;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        
    }
}

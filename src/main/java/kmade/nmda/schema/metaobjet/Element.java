package kmade.nmda.schema.metaobjet;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
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
 * @authors Vincent Lucquiaud and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class Element implements Entity {

    private static final long serialVersionUID = -2086941688924181010L;

    public Oid oid = null;

    private String name = null;

    private Enumeration utiliseParEnum = null;
    
    public Element() {
        this.name = "";
    }

    public Element(String name, Enumeration enumere) {
        this.name = name;
        setUtiliseParEnum(enumere);
    }

    public Element(String name, Enumeration enumeration, Oid oid) {
        this.name = name;
        setUtiliseParEnum(enumeration);
        this.oid = oid;
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
            ok = isUniqueName(n, oid.get());
            cpt++;
        }
        name = n;
    }

    public String getName() {
        return name;
    }

    public Oid getOid() {
        return oid;
    }

    public void delete() {
        InterfaceExpressJava.remove(oid);
        utiliseParEnum = null;
    }

    public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 11);
    }

    public void setUtiliseParEnum(Enumeration enumeration) {
        this.utiliseParEnum = enumeration;
        enumeration.addInverseElementDe(this);
    }

    public Enumeration getUtiliseParEnum() {
        return utiliseParEnum;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public String toSPF() {
        String SPF = oid.get() + "=" + "Element" + "(" + "'" + name + "'" + ",";
        if (utiliseParEnum == null) {
            SPF = SPF + ");";
        } else {
            SPF = SPF + utiliseParEnum.oid.get() + ");";
        }
        return SPF;
    }

    public String toString() {
        return this.name;
    }

    public int getTaille() {
        return this.name.length();
    }

    public static boolean isUniqueName(String s, String oid) {
        Element attr = (Element) InterfaceExpressJava.prendre(new Oid(oid));
        Enumeration enu = attr.utiliseParEnum;
        for (int i = 0; i < enu.getInverseElementDe().size(); i++) {
            Element obj = enu.getInverseElementDe().get(i);
            if (s.equalsIgnoreCase(obj.name) && (!obj.oid.get().equals(oid))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUnique() {
        Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Element");
        for (int i = 0; i < objAbs.length; i++) {
            AttributAbstrait obj = (AttributAbstrait) objAbs[i];
            if (!isUniqueName(obj.getName(), obj.oid.get()))
                return false;
        }
        return true;
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

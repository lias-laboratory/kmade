package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
public class ExpressConcreteObject {    
    public static ArrayList<ObjetConcret> getConcreteObj() {
        ArrayList<ObjetConcret> lst = new ArrayList<ObjetConcret>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetConcret");
        for (int i = 0; i < objs.length; i++) {
            ObjetConcret obj = (ObjetConcret) objs[i];
            lst.add(obj);
        }
        return lst;
    }

    public static ArrayList<String> getConcreteObjName() {
        ArrayList<String> lst = new ArrayList<String>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetConcret");
        for (int i = 0; i < objs.length; i++) {
            ObjetConcret obj = (ObjetConcret) objs[i];
            lst.add(obj.getName());
        }
        return lst;
    }

    /**
     * Cette méthode permet de créer un objet concret et par conséquent ses attributs concrets.
     * @param oidObjAbs
     * @param oidG
     * @return
     */
    public static String createConcreteObject(Oid oidObjAbs, Oid oidG) {
        Oid oidObjConc = InterfaceExpressJava.createEntity("metaobjet", "ObjetConcret");
        
        ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava.prendre(oidObjAbs);
        ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava.prendre(oidObjConc);
        Groupe g = (Groupe)InterfaceExpressJava.prendre(oidG);
        // L'ordre à une importance.
        concreteObject.setUtiliseParClass(abstractObject);
        concreteObject.setAppartientGroupe(g);
        // Créer les attributs a partir des attributs abstraits de abstractObject.
        ExpressConcreteAttribut.createConcreteAttribut(concreteObject, abstractObject);
        return (oidObjConc.get());
    }

    public static void removeConcreteObject(String oid) {
        ObjetConcret g = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(oid));
        g.delete();
    }

    public static void displayRemoveConcreteObject(String oid) {
        ObjetConcret g = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(oid));
        g.affDelete();
    }

    public static String setConcreteObjectName(String oid, String name) {
        ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(oid));
        m.setName(name);
        return m.getName();
    }
       
    public static void setConcreteObjectDescription(String oid, String description) {
        ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(oid));
        m.setDescription(description);
    }

    public static void setConcreteObjectGroup(String oid, String g) {
        ObjetConcret m = (ObjetConcret) InterfaceExpressJava.prendre(new Oid(oid));
        m.setAppartientGroupe(ExpressGroup.stringToGroup(g));
    }

    public static ObjetConcret getObjetConcret(String name) {
        ArrayList<ObjetConcret> lstConcreteObj = getConcreteObj();
        for (int i = 0; i < lstConcreteObj.size(); i++) {
            ObjetConcret obj = (ObjetConcret) lstConcreteObj.get(i);
            if (obj.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }
    
    public static String setConcreteObjectName(ObjetConcret ref, String name) {        
        ref.setName(name);
        return ref.getName();
    }

    public static void setConcreteObjectDescription(ObjetConcret ref, String description) {
        ref.setDescription(description);
    }
    
    /**
     * Cette méthode permet de créer un objet concret et par conséquent ses attributs concrets.
     * @return
     */
    public static ObjetConcret createConcreteObject(Oid oidObjAbs, Groupe g, String concreteObjectName) {
        Oid oidObjConc = InterfaceExpressJava.createEntity("metaobjet", "ObjetConcret");
        
        ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava.prendre(oidObjAbs);
        ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava.prendre(oidObjConc);
        
        // L'ordre à une importance.
        concreteObject.setUtiliseParClass(abstractObject);
        concreteObject.setAppartientGroupe(g);
        // Créer les attributs a partir des attributs abstraits de abstractObject.
        ExpressConcreteAttribut.createConcreteAttribut(concreteObject, abstractObject);
        concreteObject.setName(concreteObjectName);
        return concreteObject;
    }
}

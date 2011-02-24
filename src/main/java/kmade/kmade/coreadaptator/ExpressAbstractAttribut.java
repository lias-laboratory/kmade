package kmade.kmade.coreadaptator;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.metaobjet.TypeAbs;
import kmade.nmda.schema.metaobjet.TypeStructure;

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
public class ExpressAbstractAttribut {    
    public static ArrayList<AttributAbstrait> getAbstractAttributs(ObjetAbstrait oa) {
        return oa.getInverseAttributsAbs();
    }

    public static String getOidWithAbstractAttributName(String n, String oidObjAbs) {
        ObjetAbstrait objAbs = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oidObjAbs));
        ArrayList<AttributAbstrait> attrs = objAbs.getInverseAttributsAbs();
        for (int i = 0; i < attrs.size(); i++) {
            AttributAbstrait a = attrs.get(i);
            if (a.getName().equalsIgnoreCase(n)) {
                return a.oid.get();
            }
        }
        return null;
    }

    public static ArrayList<String> getTypeRefValues(String oidAttrAbs) {
        AttributAbstrait attrib = (AttributAbstrait)InterfaceExpressJava.prendre(new Oid(oidAttrAbs));
        TypeAbs typeref = attrib.getTypeRef();
        if (typeref == null) {
            if (attrib.getTypeStructure().equals(TypeStructure.BOOLEAN_STRUCT)) {
                ArrayList<String> boolLst = new ArrayList<String>();
                boolLst.add("true");
                boolLst.add("false");
                return boolLst;
            }
            return new ArrayList<String>();
        } else
            return typeref.getValues();
    }

    /**
     * Permet de créer les attributs d'un objet Abstrait
     * @param oidobjAbs
     * @return
     */
    public static String createAbstractAttribut(Oid oidobjAbs) {
        Oid oidAttributAbs = InterfaceExpressJava.createEntity("metaobjet", "AttributAbstrait");
        ObjetAbstrait objAbs = (ObjetAbstrait) InterfaceExpressJava.prendre(oidobjAbs);
        AttributAbstrait attrib = (AttributAbstrait) InterfaceExpressJava.prendre(oidAttributAbs);
        attrib.setUtiliseeparClass(objAbs);
        
        ArrayList<ObjetConcret> ObjConcSet = objAbs.getInverseObjConc();
        for (int i = 0; i < ObjConcSet.size(); i++) {
            ObjetConcret ObjConc = ObjConcSet.get(i);
            ExpressConcreteAttribut.addConcreteAttribut(attrib, ObjConc);
        }
        return (oidAttributAbs.get());
    }

    public static void removeAbstractAttribut(String oid) {
        AttributAbstrait m = (AttributAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
        m.delete();
    }

    public static void affRemoveAbstractAttribut(String oid) {
        AttributAbstrait m = (AttributAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
        m.affDelete();
    }

    public static String setAbstractAttributName(String oid, String name) {
        AttributAbstrait m = (AttributAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
        m.setName(name);
        return m.getName();
    }

    public static void setAbstractAttributDescription(String oid, String description) {
        AttributAbstrait m = (AttributAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
        if (description == null) {
            return;
        }
        if (m == null) {          
            return;
        }
        m.setDescription(description);
    }

    public static void setAbstractAttributType(String oid, String type) {
        AttributAbstrait m = (AttributAbstrait)InterfaceExpressJava.prendre(new Oid(oid));
        if (m == null) {
            return;
        }
        m.setTypeStructure(TypeStructure.getValue(type));
        m.delTypeRef();
    }

    public static void setAbstractAttributNameType(String oid, String oidNameType) {
        AttributAbstrait m = (AttributAbstrait)InterfaceExpressJava.prendre(new Oid(oid));
        if (m == null) {
            return;
        }

        if (oidNameType.equals(Oid.OID_NULL)) {
            // Suppression d'un Enuméré ou d'un intervalle.
            m.setTypeRef(null);
        } else {
            TypeAbs a = (TypeAbs) InterfaceExpressJava.prendre(new Oid(oidNameType));

            if (a == null) {
                return;
            }
            m.setTypeRef(a);
        }
    }
    
    public static AttributAbstrait getRefAbstractAttributFromName(Groupe rGroup, String name) {
        ObjetAbstrait myRefOA = rGroup.getContientObj();
        for(AttributAbstrait valeur : myRefOA.getInverseAttributsAbs()){
            if (valeur.getName().toLowerCase().equals(name)) {
                return valeur;
            }
        }
        return null;
    }
}

package kmade.kmade.coreadaptator;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.AttributConcret;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class ExpressConcreteAttribut {
    public static ArrayList<AttributConcret> getConcreteAttr() {
        ArrayList<AttributConcret> lst = new ArrayList<AttributConcret>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "AttributConcret");
        for (int i = 0; i < objs.length; i++) {
            AttributConcret obj = (AttributConcret) objs[i];
            lst.add(obj);
        }
        return lst;
    }

    /**
     * Cette méthode permet de créer des attributs concrets
     * @param ObjConc
     * @param objAbs
     */
    public static void createConcreteAttribut(ObjetConcret ObjConc, ObjetAbstrait objAbs) {
        ArrayList<AttributAbstrait> listattributabs = objAbs.getInverseAttributsAbs();
        for (int i = 0; i < listattributabs.size(); i++) {
            Oid oidAttribut = InterfaceExpressJava.createEntity("metaobjet", "AttributConcret");
            AttributConcret concreteAttribut = (AttributConcret)InterfaceExpressJava.prendre(oidAttribut);
            AttributAbstrait abstractAttribut = listattributabs.get(i);
            concreteAttribut.setObjConcDe(ObjConc);
            concreteAttribut.setAttributDe(abstractAttribut);
            concreteAttribut.setName(abstractAttribut.getName());
            concreteAttribut.setInitValeur();
        }
    }

    /**
     * Cette méthode permet d'ajouter des attributs à un objet Concret déjà créé
     * @param attributabs
     * @param ObjConc
     */
    public static void addConcreteAttribut(AttributAbstrait attributabs, ObjetConcret ObjConc) {
        Oid oidAttribut = InterfaceExpressJava.createEntity("metaobjet","AttributConcret");
        AttributConcret attribut = (AttributConcret) InterfaceExpressJava.prendre(oidAttribut);
        attribut.setObjConcDe(ObjConc);
        attribut.setAttributDe(attributabs);
        attribut.setInitValeur();
    }

    /**
     * Extrait à partir d'un objet concret la liste des attributs concrets et de ses valeurs.
     * @param ObjConcref
     * @return
     * 	pour chaque item {le nom de l'attribut abstrait , le type, la valeur(ou null), oid de l'attribut} 
     */
    public static ArrayList<Object[]> getConcreteAttribut(Oid ObjConcref) {
        ObjetConcret ObjConc = (ObjetConcret) InterfaceExpressJava.prendre(ObjConcref);
        ArrayList<AttributConcret> listattribut = ObjConc.getInverseListAttribut();
        ArrayList<Object[]> myConcreteAttribut = new ArrayList<Object[]>();
        for (int i = 0; i < listattribut.size(); i++) {
            Object[] myTab = new Object[4];
            AttributConcret concreteAttribut = listattribut.get(i);
            myTab[0] = concreteAttribut.getDeriveName();
            myTab[1] = concreteAttribut.getAttributAbsDe().getTypeStructure().getValue();
            if (concreteAttribut.isAttributeNull()) {
                myTab[2] = null;
            } else {
                myTab[2] = concreteAttribut.getValue();
            }
            myTab[3] = concreteAttribut.getOid().get();
            myConcreteAttribut.add(myTab);
        }
        return myConcreteAttribut;
    }

    /**
     * Change la valeur de l'attribut concret
     * @param oid
     * 			l'oid de l'attribut
     * @param valeur
     * 				la valeur de l'attribut
     * @return
     */
    public static boolean setConcreteAttributValue(String oid, String valeur) {
        AttributConcret m = (AttributConcret)InterfaceExpressJava.prendre(new Oid(oid));
        return m.setValeur(valeur);
    }
}

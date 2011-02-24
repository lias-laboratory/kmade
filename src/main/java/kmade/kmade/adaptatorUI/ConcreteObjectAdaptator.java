package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.coreadaptator.ExpressConcreteObject;
import kmade.kmade.coreadaptator.ExpressGroup;
import kmade.kmade.view.worldobject.concreteobject.KMADEReadWriteConcreteObjectTreeTable;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.metaobjet.UniqAg;

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
public final class ConcreteObjectAdaptator {

    private static final KMADEReadWriteConcreteObjectTreeTable concreteObjectTreeTable = GraphicEditorAdaptator.getPanelCreationObjConc().getTreeTableObjConc();
    
    private static String oidGroupe = Oid.OID_NULL;
       
    /**
     * Cette méthode vérifie si on peut créer un objet concret à partir de cette objet abstrait
     * @return
     */
    public static boolean concreteObjectCanBeCreated() {
        ArrayList<Groupe> listGroup = ExpressGroup.getGroups(ConcreteObjectPanelAdaptator.getCurrentAbstractObject());
        for (Groupe current:listGroup) {
            if (!(current.getEnsemble() instanceof UniqAg)) {
                return true;
            } else {
                // Vérifie s'il est pris. Il en faut au moins un!!!
                if (current.getEnsemble().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void setActiveConcreteObject(String oid, String concrete) {
        // Mise à jour du Border.
        concreteObjectTreeTable.setConcreteObjectNameBorder(concrete);
        if (oid.equals(Oid.OID_NULL)) {
            // Supprime tous les objets de la partie AttributConcret et cache la table attribut concret.
            GraphicEditorAdaptator.getPanelCreationObjConc().hideConcreteAttributes();
        } else {
            ConcreteAttributAdaptator.updateConcreteObjectView(oid,concrete);
            GraphicEditorAdaptator.getPanelCreationObjConc().showConcreteAttributes();
        }    
    }

    public static String getActiveGroupOid() {
        return oidGroupe;
    }  
        
    public static void setActiveGroupFromName(String pGroupName) {
        if (pGroupName == null) {
            oidGroupe = Oid.OID_NULL;
            return;
        }

        String oidRefGroup = ExpressGroup.stringToOid(pGroupName);
        
        if (oidRefGroup == null) { 
            oidGroupe = Oid.OID_NULL;
            return;
        }        
        oidGroupe = oidRefGroup;    
    }

   
    public static String addConcreteObject(String pGroupName) {
        ConcreteObjectAdaptator.setActiveGroupFromName(pGroupName);
        
        if (ConcreteObjectPanelAdaptator.getActiveAbstractObject().equals(Oid.OID_NULL)) {
            return null;
        } 

        if (ConcreteObjectAdaptator.getActiveGroupOid().equals(Oid.OID_NULL)) {
            return null;
        }
        
        String newOidConcreteObject = ExpressConcreteObject.createConcreteObject(new Oid(ConcreteObjectPanelAdaptator.getActiveAbstractObject()),
                new Oid(ConcreteObjectAdaptator.getActiveGroupOid()));
		ReadConcreteObjectAdaptator.updateReadConcreteObject();
        return newOidConcreteObject;
    }
    
    public static void removeObjConc(String oid) {
        ExpressConcreteObject.removeConcreteObject(oid);
		ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }
    
    public static void affRemoveObjConc(String oid) {
         ExpressConcreteObject.displayRemoveConcreteObject(oid);
    }
    
     public static String setConcreteObjectName(String oid, String name) {
   	 	String temp = ExpressConcreteObject.setConcreteObjectName(oid, name);
 		ReadConcreteObjectAdaptator.updateReadConcreteObject();
        return temp; 
    }
    
    public static void setConcreteObjectGroup(String oid, String g) {
        ExpressConcreteObject.setConcreteObjectGroup(oid, g);
 		ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }
    
    public static void setConcreteObjectDescription(String oid, String description) {
        ExpressConcreteObject.setConcreteObjectDescription(oid, description);
 		ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }    
    
    public static void updateConcreteObjectView(ObjetAbstrait refAbstractObject) {
        concreteObjectTreeTable.updateDataModel(refAbstractObject);
        setActiveConcreteObject(Oid.OID_NULL,"");
    }
    
    public static String setConcreteObjectName(ObjetConcret refOC, String name) {
        return ExpressConcreteObject.setConcreteObjectName(refOC, name);
 
    }
    
    public static void setConcreteObjectDescription(ObjetConcret refOC, String description) {
        ExpressConcreteObject.setConcreteObjectDescription(refOC, description);
    }
    
    public static ObjetConcret addNewConcreteObject(Groupe myGroup, String concreteObjectName) {
        if (ConcreteObjectPanelAdaptator.getActiveAbstractObject().equals(Oid.OID_NULL)) {
            // Objet Abstrait non sélectionné.
            return null;
        } 
               
        ObjetConcret refConcreteObject = ExpressConcreteObject.createConcreteObject(new Oid(ConcreteObjectPanelAdaptator.getActiveAbstractObject()), myGroup, concreteObjectName);
        return refConcreteObject;
    }
    
    public static void setNoSelectedConcreteObject() {
        ConcreteObjectAdaptator.setSelectedConcreteObject(null);
    }
    
    public static void setSelectedConcreteObject(ObjetConcret refObjetConcret) {
        String borderName = "";
        if (refObjetConcret == null) {
            GraphicEditorAdaptator.getPanelCreationObjConc().hideConcreteAttributes();            
        } else {
            borderName = refObjetConcret.getName();
            ConcreteAttributAdaptator.updateConcreteObjectView(refObjetConcret.getOid().get(),borderName);
            GraphicEditorAdaptator.getPanelCreationObjConc().showConcreteAttributes();
        }        
    }
}

package kmade.kmade.adaptatorUI;

import kmade.kmade.adaptatorFC.ExpressAbstractObject;
import kmade.kmade.adaptatorFC.ExpressGroup;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;

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
public final class ConcreteObjectPanelAdaptator {
    private static String oidAbstractObject = Oid.OID_NULL;
    
    private static ObjetAbstrait refAbstractObject;
    
    public static void removeAllConcreteObject() {
        GraphicEditorAdaptator.getPanelCreationObjConc().removeAllConcreteObject();
    }

    public static String getActiveAbstractObject() {
        return oidAbstractObject;
    }    
    
    public static ObjetAbstrait getCurrentAbstractObject() {
        return refAbstractObject;
    }
    
    public static void setActiveAbstractObject(String oid) {
        oidAbstractObject = oid;           
        
        if (oid.equals(Oid.OID_NULL)) {
            // Rend invisible la table des objets et attributs concrets.
            GraphicEditorAdaptator.getPanelCreationObjConc().hideConcreteObjectsAttributes();            
            GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs().setAbstractObjectNameBorder("");
        } else {
            // AdaptateurConcreteObject.
            refAbstractObject = ExpressAbstractObject.getObjetAbstrait(oidAbstractObject);
            ConcreteObjectAdaptator.updateConcreteObjectView(refAbstractObject);
            GraphicEditorAdaptator.getPanelCreationObjConc().showConcreteObjects();
            GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs().setAbstractObjectNameBorder(refAbstractObject.getName());
        }        
    }

    public static String getOidGroupe(String name) {
        return ExpressGroup.stringToOid(name);
    }

    public static String[] getAttrsList(String oidObjAbs) {
        return ExpressAbstractObject.getAttrsNames(oidObjAbs);
    }

    public static void updateConcreteObjectView() {
        // On prend tous les objets abstrait et on les place dans la table des objets abstraits.
        Object[][] refAbstractObject = ExpressAbstractObject.getAbsObjIntoTab();
        GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs().setData(refAbstractObject);
        ConcreteObjectPanelAdaptator.setActiveAbstractObject(Oid.OID_NULL);
    }
}

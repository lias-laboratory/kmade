package fr.upensma.lias.kmade.tool.viewadaptator;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressInterval;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

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
public final class AbstractAttributeAdaptator {
    
    public static void removeAllAtributes(){        
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable().removeAllAttributes();
    }
    
    public static void updateEnumList() {
        String[][] listEnumeration = ExpressEnum.getArrayEnum();
        if (listEnumeration == null) {
            listEnumeration = new String[0][2];
        }
        updateAttributes();
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable().buildEnumerationList(listEnumeration);
    }
    
    public static void updateIntervalList() {
        String[][] listIntervalle = ExpressInterval.getArrayIntervals();
        if (listIntervalle == null) {
            listIntervalle = new String[0][2];
        }
        updateAttributes();
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable().buildIntervalList(listIntervalle);
    }
    
    public static String addAbstractAttributes(String oidObjAbs) {
        if (!oidObjAbs.equals(Oid.OID_NULL)) {
            Oid oid = new Oid(oidObjAbs);
            return (ExpressAbstractAttribut.createAbstractAttribut(oid));
        } else {
            return "-1";
        }
    }
    
    public static String setAbstractAttributeName(String oid, String name) {
        return ExpressAbstractAttribut.setAbstractAttributName(oid, name);
    }
    
    public static void setAbstractAttributDescription(String oid, String description) {
        ExpressAbstractAttribut.setAbstractAttributDescription(oid, description);
    }
    
    public static void setAbstractAttributType(String oid, String type) {
        ExpressAbstractAttribut.setAbstractAttributType(oid, type);
    }
    
    public static void setAbstractAttributNameType(String oid, String oidnameType) {
        ExpressAbstractAttribut.setAbstractAttributNameType(oid, oidnameType);
    }
    
    public static void removeAbstractAttribute(String oid){
        ExpressAbstractAttribut.removeAbstractAttribut(oid);    
    }
    
    public static void affRemoveAttrAbs(String oid){
        ExpressAbstractAttribut.affRemoveAbstractAttribut(oid);    
    }
    
    public static void updateAttributes() {
        Object[][] tabAttr = ExpressAbstractObject.getAttributes(AbstractObjectPanelAdaptator.getActiveAbstractObject());
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable().updateDataModel(tabAttr);
    }
}

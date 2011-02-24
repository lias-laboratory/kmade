package kmade.kmade.adaptatorUI;

import kmade.kmade.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.nmda.schema.Oid;

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
public final class TypePanelAdaptator {  
    private static String oidEnumeration = Oid.OID_NULL;
    
    public static void setActiveEnumeration(String name , String oid) {
        oidEnumeration = oid;
        
        String afficher = "";  

        if (oid.equals(Oid.OID_NULL)) {
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().setVisible(false);
            afficher = "";
        } else {
            afficher = " : " + name;
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().setVisible(true);
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().removeAllEnumElement();
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().setEnabled(true);
            Object[][] tabElem = EnumElementAdaptator.getElementIntoTab(oid);
            if (tabElem.length != 0) {
                KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().updateDataModel(tabElem);
            }
        }    
        
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().setBorderTypePanel(afficher);
    }
    
    public static String getEnumerationActive() {
        return oidEnumeration;
    }  
}

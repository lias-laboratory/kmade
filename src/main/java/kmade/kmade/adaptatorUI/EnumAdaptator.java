package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.UI.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.kmade.adaptatorFC.ExpressEnum;
import kmade.nmda.schema.metaobjet.Enumeration;

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
public final class EnumAdaptator {
	public static void removeEnum(String oid) {
		ExpressEnum.removeEnum(oid);
	}

	public static void removeAllEnum() {
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumPanel().removeAllEnum();
	}

    public static void affRemoveEnumeration(String oid) {
        ExpressEnum.affRemoveEnum(oid);
    }
    
    /**
     * Cette méthode permet de communiquer avec Express pour créer un objet abstrait
     * et retourne l'OID du nouvel objet.
     */
    public static String addEnum() {
    		String temp = ExpressEnum.createEnum();
    		ReadConcreteObjectAdaptator.updateReadConcreteObject();
    		return temp;
    }

	public static String setEnumName(String oid, String name) {
		String n = ExpressEnum.setEnumName(oid, name);
		ReadConcreteObjectAdaptator.updateReadConcreteObject();
		return n;
	}

	public static void setEnumDescription(String oid, String Description) {
		ExpressEnum.setEnumDescription(oid, Description);
	}
    
    public static void updateEnumView() {
        ArrayList<Enumeration> myList = ExpressEnum.getEnums();
        for (int i = 0; i < myList.size(); i++) {
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumPanel().addEnum(myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getOid().get());
        }        
    }
}

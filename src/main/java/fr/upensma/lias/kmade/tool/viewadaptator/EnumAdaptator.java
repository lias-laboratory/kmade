/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
* 
* KMADe is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* KMADe is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
**********************************************************************************/
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.Enumeration;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnum;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class EnumAdaptator {
    
    public static void removeEnum(String oid) {
	ExpressEnum.removeEnum(oid);
    }

    public static void removeAllEnum() {
	KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor()
		.getEnumPanel().removeAllEnum();
    }

    public static void affRemoveEnumeration(String oid) {
	ExpressEnum.affRemoveEnum(oid);
    }

    /**
     * Cette méthode permet de communiquer avec Express pour créer un objet
     * abstrait et retourne l'OID du nouvel objet.
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
	    KMADEReadWriteAbstractTypeObjectPanel
		    .getEnumIntervalEditor()
		    .getEnumPanel()
		    .addEnum(myList.get(i).getName(),
			    myList.get(i).getDescription(),
			    myList.get(i).getOid().get());
	}
    }
}

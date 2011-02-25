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

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class AbstractObjectPanelAdaptator {    

    private static String oidClaseActive = Oid.OID_NULL;

    public static void setActiveAbstractObject(String name, String oid) {
	oidClaseActive = oid;
	KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor()
		.setActiveAbstractObject(name, oidClaseActive);
    }

    public static String getActiveAbstractObject() {
	return oidClaseActive;
    }

    public static Object[][] getGroupesIntoTab(String oidObj) {
	return ExpressGroup.getArrayGroups(oidObj);
    }

    public static Object[][] getAttributesIntoTab(String oidA) {
	return ExpressAbstractObject.getAttributes(oidA);
    }

}

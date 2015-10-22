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
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnumElement;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class EnumElementAdaptator {

    public static void removeAllEnumElement() {
	KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor()
		.getEnumElementPanel().removeAllEnumElement();
    }

    public static void removeElement(String oid) {
	ExpressEnumElement.deleteElement(oid);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }

    public static void affRemoveElement(String oid) {
	ExpressEnumElement.affDeleteElement(oid);
    }

    public static String addElement(String oidEnum) {
	if (!oidEnum.equals(Oid.OID_NULL)) {
	    Oid oid = new Oid(oidEnum);
	    String element = ExpressEnumElement.creerElement(oid);
	    ReadConcreteObjectAdaptator.updateReadConcreteObject();
	    return element;
	} else {
	    return "-1";
	}
    }

    public static Object[][] getElementIntoTab(String oidEnum) {
	return ExpressEnumElement.getElementIntoTab(oidEnum);
    }

    public static String setElementName(String oid, String name) {
	String element = ExpressEnumElement.setElementName(oid, name);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
	return element;
    }
}

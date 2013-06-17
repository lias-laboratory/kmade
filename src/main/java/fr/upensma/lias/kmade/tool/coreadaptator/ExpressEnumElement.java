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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Element;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Enumeration;

/**
 * @author Mickael BARON
 */
public class ExpressEnumElement {    
    public static String creerElement(Oid oidEnumeration) {
	Oid oidElement = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE, 
			ExpressConstant.ELEMENT_CLASS);
	Enumeration enumeration = (Enumeration) InterfaceExpressJava
		.prendre(oidEnumeration);
	Element element = (Element) InterfaceExpressJava.prendre(oidElement);
	element.setUtiliseParEnum(enumeration);
	return (oidElement.get());
    }

    public static void deleteElement(String oid) {
	Element m = (Element) InterfaceExpressJava.prendre(new Oid(oid));
	m.delete();
    }

    public static void affDeleteElement(String oid) {
	Element m = (Element) InterfaceExpressJava.prendre(new Oid(oid));
	m.affDelete();
    }

    public static Object[][] getElementIntoTab(String oid) {
	Enumeration g = (Enumeration) InterfaceExpressJava
		.prendre(new Oid(oid));
	ArrayList<Element> elem = g.getInverseElementDe();
	Object[][] tabObj = new Object[elem.size()][2];
	for (int i = 0; i < elem.size(); i++) {
	    Element a = elem.get(i);
	    tabObj[i][0] = a.getName();
	    tabObj[i][1] = a.getOid().get();
	}
	return tabObj;
    }

    public static String setElementName(String oid, String name) {
	Element m = (Element) InterfaceExpressJava.prendre(new Oid(oid));
	m.setName(name);
	return m.getName();
    }
}
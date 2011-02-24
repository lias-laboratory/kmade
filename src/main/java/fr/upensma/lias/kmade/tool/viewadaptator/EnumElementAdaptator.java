package fr.upensma.lias.kmade.tool.viewadaptator;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnumElement;
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
public final class EnumElementAdaptator {
	public static void removeAllEnumElement() {
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel().removeAllEnumElement();
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

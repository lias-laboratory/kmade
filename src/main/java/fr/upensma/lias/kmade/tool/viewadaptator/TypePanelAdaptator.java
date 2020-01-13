/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class TypePanelAdaptator {

	private static String oidEnumeration = Oid.OID_NULL;

	public static void setActiveEnumeration(String name, String oid) {
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
				KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getEnumElementPanel()
						.updateDataModel(tabElem);
			}
		}

		KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().setBorderTypePanel(afficher);
	}

	public static String getEnumerationActive() {
		return oidEnumeration;
	}
}

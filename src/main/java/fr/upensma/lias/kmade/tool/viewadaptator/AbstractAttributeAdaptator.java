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
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressInterval;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class AbstractAttributeAdaptator {

	public static void removeAllAtributes() {
		KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable()
				.removeAllAttributes();
	}

	public static void updateEnumList() {
		String[][] listEnumeration = ExpressEnum.getArrayEnum();
		if (listEnumeration == null) {
			listEnumeration = new String[0][2];
		}
		updateAttributes();
		KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable()
				.buildEnumerationList(listEnumeration);
	}

	public static void updateIntervalList() {
		String[][] listIntervalle = ExpressInterval.getArrayIntervals();
		if (listIntervalle == null) {
			listIntervalle = new String[0][2];
		}
		updateAttributes();
		KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable()
				.buildIntervalList(listIntervalle);
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

	public static void removeAbstractAttribute(String oid) {
		ExpressAbstractAttribut.removeAbstractAttribut(oid);
	}

	public static void affRemoveAttrAbs(String oid) {
		ExpressAbstractAttribut.affRemoveAbstractAttribut(oid);
	}

	public static void updateAttributes() {
		Object[][] tabAttr = ExpressAbstractObject
				.getAttributes(AbstractObjectPanelAdaptator.getActiveAbstractObject());
		KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectAttributTable()
				.updateDataModel(tabAttr);
	}
}

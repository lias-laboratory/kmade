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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;

/**
 * @author Mickael BARON
 */
public final class ConcreteObjectPanelAdaptator {
    private static String oidAbstractObject = Oid.OID_NULL;

    private static ObjetAbstrait refAbstractObject;

    public static void removeAllConcreteObject() {
	GraphicEditorAdaptator.getPanelCreationObjConc()
		.removeAllConcreteObject();
    }

    public static String getActiveAbstractObject() {
	return oidAbstractObject;
    }

    public static ObjetAbstrait getCurrentAbstractObject() {
	return refAbstractObject;
    }

    public static void setActiveAbstractObject(String oid) {
	oidAbstractObject = oid;

	if (oid.equals(Oid.OID_NULL)) {
	    // Rend invisible la table des objets et attributs concrets.
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .hideConcreteObjectsAttributes();
	    GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs()
		    .setAbstractObjectNameBorder("");
	} else {
	    // AdaptateurConcreteObject.
	    refAbstractObject = ExpressAbstractObject
		    .getObjetAbstrait(oidAbstractObject);
	    ConcreteObjectAdaptator.updateConcreteObjectView(refAbstractObject);
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .showConcreteObjects();
	    GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs()
		    .setAbstractObjectNameBorder(refAbstractObject.getName());
	}
    }

    public static String getOidGroupe(String name) {
	return ExpressGroup.stringToOid(name);
    }

    public static String[] getAttrsList(String oidObjAbs) {
	return ExpressAbstractObject.getAttrsNames(oidObjAbs);
    }

    public static void updateConcreteObjectView() {
	// On prend tous les objets abstrait et on les place dans la table des
	// objets abstraits.
	Object[][] refAbstractObject = ExpressAbstractObject.getAbsObjIntoTab();
	GraphicEditorAdaptator.getPanelCreationObjConc().getTableObjAbs()
		.setData(refAbstractObject);
	ConcreteObjectPanelAdaptator.setActiveAbstractObject(Oid.OID_NULL);
    }
}

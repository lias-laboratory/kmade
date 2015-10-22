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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class AbstractObjectAdaptator {
    /**
     * Supprime tous les objets abstrait
     */
    public static void removeAllAbstractObject() {
	KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor()
		.getAbstractObjectTable().removeAllAbstractObject();
    }

    /**
     * Cette méthode permet de communiquer avec Express pour créer un objet
     * abstrait et retourne l'OID du nouvel objet.
     */
    public static String addAbstractObject() {
	return ExpressAbstractObject.createAbstractObject();
    }

    /**
     * Modifie le nom de l'objet abstrait
     */
    public static String setAbstractObjectName(String oid, String name) {
	String value = ExpressAbstractObject.setAbstractObjectName(oid, name);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
	return value;
    }

    /**
     * Modifie la description de l'objet abstrait
     */
    public static void setAbstractObjectDescription(String oid,
	    String Description) {
	ExpressAbstractObject.setAbstractObjectDescription(oid, Description);
    }

    /**
     * Supprime un objet abstrait
     */
    public static void removeAbstractObject(String oid) {
	ExpressAbstractObject.removeObj(oid);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }

    public static void affRemoveObjAbs(String oid) {
	ExpressAbstractObject.affRemoveObj(oid);
    }

    public static void updateAbstractObjectView() {
	ArrayList<ObjetAbstrait> myList = ExpressAbstractObject
		.getAbstractObjects();

	for (int i = 0; i < myList.size(); i++) {
	    KMADEReadWriteAbstractTypeObjectPanel
		    .getAbstractObjectEditor()
		    .getAbstractObjectTable()
		    .addAbstractObject(myList.get(i).getName(),
			    myList.get(i).getDescription(),
			    myList.get(i).getOid().get());
	}
    }
}

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

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.UniqAg;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressConcreteObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;
import fr.upensma.lias.kmade.tool.view.worldobject.concreteobject.KMADEReadWriteConcreteObjectTreeTable;

public final class ConcreteObjectAdaptator {

    private static final KMADEReadWriteConcreteObjectTreeTable concreteObjectTreeTable = GraphicEditorAdaptator
	    .getPanelCreationObjConc().getTreeTableObjConc();

    private static String oidGroupe = Oid.OID_NULL;

    /**
     * Cette méthode vérifie si on peut créer un objet concret à partir de cette
     * objet abstrait
     * 
     * @return
     */
    public static boolean concreteObjectCanBeCreated() {
	ArrayList<Groupe> listGroup = ExpressGroup
		.getGroups(ConcreteObjectPanelAdaptator
			.getCurrentAbstractObject());
	for (Groupe current : listGroup) {
	    if (!(current.getEnsemble() instanceof UniqAg)) {
		return true;
	    } else {
		// Vérifie s'il est pris. Il en faut au moins un!!!
		if (current.getEnsemble().isEmpty()) {
		    return true;
		}
	    }
	}
	return false;
    }

    public static void setActiveConcreteObject(String oid, String concrete) {
	// Mise à jour du Border.
	concreteObjectTreeTable.setConcreteObjectNameBorder(concrete);
	if (oid.equals(Oid.OID_NULL)) {
	    // Supprime tous les objets de la partie AttributConcret et cache la
	    // table attribut concret.
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .hideConcreteAttributes();
	} else {
	    ConcreteAttributAdaptator.updateConcreteObjectView(oid, concrete);
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .showConcreteAttributes();
	}
    }

    public static String getActiveGroupOid() {
	return oidGroupe;
    }

    public static void setActiveGroupFromName(String pGroupName) {
	if (pGroupName == null) {
	    oidGroupe = Oid.OID_NULL;
	    return;
	}

	String oidRefGroup = ExpressGroup.stringToOid(pGroupName);

	if (oidRefGroup == null) {
	    oidGroupe = Oid.OID_NULL;
	    return;
	}
	oidGroupe = oidRefGroup;
    }

    public static String addConcreteObject(String pGroupName) {
	ConcreteObjectAdaptator.setActiveGroupFromName(pGroupName);

	if (ConcreteObjectPanelAdaptator.getActiveAbstractObject().equals(
		Oid.OID_NULL)) {
	    return null;
	}

	if (ConcreteObjectAdaptator.getActiveGroupOid().equals(Oid.OID_NULL)) {
	    return null;
	}

	String newOidConcreteObject = ExpressConcreteObject
		.createConcreteObject(
			new Oid(ConcreteObjectPanelAdaptator
				.getActiveAbstractObject()), new Oid(
				ConcreteObjectAdaptator.getActiveGroupOid()));
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
	return newOidConcreteObject;
    }

    public static void removeObjConc(String oid) {
	ExpressConcreteObject.removeConcreteObject(oid);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }

    public static void affRemoveObjConc(String oid) {
	ExpressConcreteObject.displayRemoveConcreteObject(oid);
    }

    public static String setConcreteObjectName(String oid, String name) {
	String temp = ExpressConcreteObject.setConcreteObjectName(oid, name);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
	return temp;
    }

    public static void setConcreteObjectGroup(String oid, String g) {
	ExpressConcreteObject.setConcreteObjectGroup(oid, g);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }

    public static void setConcreteObjectDescription(String oid,
	    String description) {
	ExpressConcreteObject.setConcreteObjectDescription(oid, description);
	ReadConcreteObjectAdaptator.updateReadConcreteObject();
    }

    public static void updateConcreteObjectView(ObjetAbstrait refAbstractObject) {
	concreteObjectTreeTable.updateDataModel(refAbstractObject);
	setActiveConcreteObject(Oid.OID_NULL, "");
    }

    public static String setConcreteObjectName(ObjetConcret refOC, String name) {
	return ExpressConcreteObject.setConcreteObjectName(refOC, name);

    }

    public static void setConcreteObjectDescription(ObjetConcret refOC,
	    String description) {
	ExpressConcreteObject.setConcreteObjectDescription(refOC, description);
    }

    public static ObjetConcret addNewConcreteObject(Groupe myGroup,
	    String concreteObjectName) {
	if (ConcreteObjectPanelAdaptator.getActiveAbstractObject().equals(
		Oid.OID_NULL)) {
	    // Objet Abstrait non sélectionné.
	    return null;
	}

	ObjetConcret refConcreteObject = ExpressConcreteObject
		.createConcreteObject(
			new Oid(ConcreteObjectPanelAdaptator
				.getActiveAbstractObject()), myGroup,
			concreteObjectName);
	return refConcreteObject;
    }

    public static void setNoSelectedConcreteObject() {
	ConcreteObjectAdaptator.setSelectedConcreteObject(null);
    }

    public static void setSelectedConcreteObject(ObjetConcret refObjetConcret) {
	String borderName = "";
	if (refObjetConcret == null) {
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .hideConcreteAttributes();
	} else {
	    borderName = refObjetConcret.getName();
	    ConcreteAttributAdaptator.updateConcreteObjectView(refObjetConcret
		    .getOid().get(), borderName);
	    GraphicEditorAdaptator.getPanelCreationObjConc()
		    .showConcreteAttributes();
	}
    }
}

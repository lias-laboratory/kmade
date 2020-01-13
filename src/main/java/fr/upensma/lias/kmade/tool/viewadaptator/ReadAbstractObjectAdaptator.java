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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.AgregatStructure;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractObject;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorPrePostIterDialog;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadAbstractObjectPanel;

/**
 * @author Mickael BARON
 */
public final class ReadAbstractObjectAdaptator {

	private static ArrayList<ObjetAbstrait> refAbstractObject;

	private static ArrayList<Groupe> refGroupes;

	private static ArrayList<AttributAbstrait> refAttributs;

	private static ObjetAbstrait currentAbstractObject;

	public static KMADEReadAbstractObjectPanel getReadAbstractObjectPanel() {
		return KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel();
	}

	public static void addNewGroup(int selected) {
		// V�rifie s'il existe bien un index parmis la liste
		if (selected < refGroupes.size() && selected >= 0) {
			Groupe myGroupe = refGroupes.get(selected);
			PrePostIterExpressionAdaptator.setNewToken("$" + myGroupe.getName());
		}
	}

	public static void addNewAttribut(int selected) {
		if (selected < refAttributs.size() && selected >= 0) {
			AttributAbstrait myaa = refAttributs.get(selected);
			PrePostIterExpressionAdaptator.setNewToken("$" + myaa.getName());
		}
	}

	public static void initReadAbstractObjectTable() {
		// On prend tous les objets abstrait et on les place dans la table des
		// objets abstraits.
		ReadAbstractObjectAdaptator.refAbstractObject = ExpressAbstractObject.getAbstractObjects();
		Object[][] tabObj = new Object[refAbstractObject.size()][2];
		for (int i = 0; i < refAbstractObject.size(); i++) {
			tabObj[i][0] = refAbstractObject.get(i).getName();
			tabObj[i][1] = refAbstractObject.get(i).getDescription();
		}
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable().setData(tabObj);
		ReadAbstractObjectAdaptator.noAbstractSelection();
	}

	public static void noAbstractSelection() {
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().hideGroupAndAttributPanel();
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable()
				.setAbstractObjectNameBorder("");
		currentAbstractObject = null;
	}

	public static void setAbstractObjectSelection(int minSelectionIndex) {
		ObjetAbstrait refAO = refAbstractObject.get(minSelectionIndex);
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable()
				.setAbstractObjectNameBorder(refAO.getName());
		currentAbstractObject = refAO;

		// Mise � jour du groupe et de l'attribut.
		refGroupes = ExpressGroup.getGroups(currentAbstractObject);
		String[][] tabGroup = new String[refGroupes.size()][3];
		for (int i = 0; i < refGroupes.size(); i++) {
			tabGroup[i][0] = refGroupes.get(i).getName();
			tabGroup[i][1] = refGroupes.get(i).getDescription();
			tabGroup[i][2] = AgregatStructure.getEnumereIntoLocaleAgregatStructure(
					refGroupes.get(i).getEnsemble().getAgregatStructure().getValue());
		}
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getGroupTable().setData(tabGroup);

		refAttributs = ExpressAbstractAttribut.getAbstractAttributs(currentAbstractObject);
		Object[][] tabAttribut = new Object[refAttributs.size()][4];
		for (int i = 0; i < refAttributs.size(); i++) {
			tabAttribut[i][0] = refAttributs.get(i).getName();
			tabAttribut[i][1] = refAttributs.get(i).getDescription();
			tabAttribut[i][2] = TypeStructure
					.getEnumereIntoLocaleTypeStructure(refAttributs.get(i).getTypeStructure().getValue());
			tabAttribut[i][3] = refAttributs.get(i).getTypeRef();
		}
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractAttributTable().setData(tabAttribut);

		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().showGroupAndAttributPanel();
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getGroupTable().setGroupNameBorder(refAO.getName());
		KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractAttributTable()
				.setAttributNameBorder(refAO.getName());
	}
}

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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressCondition;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;

/**
 * @author Mickael BARON
 */
public final class ConditionAdaptator {

	public static int origine = 0;

	public static void disabledFrame() {
		GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
		TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
	}

	public static void editedFromEnhancedFrame() {
		origine = 1;
	}

	public static void enabledFrame() {
		GraphicEditorAdaptator.enabledMainFrameAfterEdition();
		TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
		if (origine == 0) {
			GraphicEditorAdaptator.requestFocus();
		} else {
			TaskPropertiesEnhancedEditorAdaptator.requestFocus();
		}
		origine = 0;
	}

	public static boolean affRemoveCondition(String oid) {
		return ExpressCondition.affRemoveCondition(oid);
	}

	public static void removeCondition(String oidAct) {
		ExpressCondition.removeCondition(oidAct);
	}

	public static void updateConditionView() {
		ArrayList<ProtoTaskCondition> myList = ExpressCondition.getConditions();
		for (ProtoTaskCondition current : myList) {
			KMADEMainFrame.getProjectPanel().getConditionPanel().addCondition(current.getDescription(),
					current.getDefaultValueText(), current.getOid().get());
		}
	}

	public static String[] getConditionsDescriptionArray() {
		return ExpressCondition.getConditionsDescriptionArray();
	}

	public static ArrayList<String> getConditionsDescriptionList() {
		return ExpressCondition.getConditionsDescriptionList();
	}

	public static String addCondition() {
		return ExpressCondition.createCondition();
	}

	public static void setConditionDefaultValue(String oid, String defaultValue) {
		ExpressCondition.setConditionDefaultValue(oid, defaultValue);
	}

	public static void removeAllConditions() {
		KMADEMainFrame.getProjectPanel().getConditionPanel().removeAllConditions();
	}

	/**
	 * @param newConditionObject
	 * @param value
	 * @return
	 */
	public static String setConditionDescription(String oid, String value) {
		return (ExpressCondition.setConditionDescription(oid, value));
	}

	public static Object[][] getProtoTaskConditionsIntoTab() {
		ArrayList<ProtoTaskCondition> myList = ExpressCondition.getConditions();
		Object[][] res = new Object[myList.size()][3];
		for (int i = 0; i < myList.size(); i++) {
			res[i][0] = myList.get(i).getDescription();
			res[i][1] = myList.get(i).getDefaultValueText();
			res[i][2] = myList.get(i).getOid().get();
		}
		return res;
	}

	public static void setPreCondition(String oidConditions) {
		ExpressCondition.setPreCondition(GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getOid().get(),
				oidConditions);

	}

	public static void setIterCondition(String oidConditions) {
		ExpressCondition.setIterCondition(GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getOid().get(),
				oidConditions);

	}

}

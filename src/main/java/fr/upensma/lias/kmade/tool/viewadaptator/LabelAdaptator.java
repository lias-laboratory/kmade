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

import java.awt.Color;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Label;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressLabel;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;

/**
 * @author Mickael BARON
 */
public final class LabelAdaptator {

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

	public static void affRemoveLabel(String oid) {
		ExpressLabel.affRemoveLabel(oid);
	}

	public static void removeLabel(String oidAct) {
		ExpressLabel.removeLabel(oidAct);
	}

	public static void updateLabelView() {
		ArrayList<Label> myList = ExpressLabel.getLabels();
		for (Label current : myList) {
			KMADEMainFrame.getProjectPanel().getLabelPanel().addLabel(current.getName(), current.getDescription(),
					current.getColor(), current.isColorVisible(), current.isVisible(), current.getOid().get());
		}
	}

	public static String[] getLabelsNameArray() {
		return ExpressLabel.getLabelsNameArray();
	}

	public static ArrayList<String> getLabelsNameList() {
		return ExpressLabel.getLabelsNameList();
	}

	public static String addLabel() {
		return ExpressLabel.createLabel();
	}

	public static void setLabelDescription(String oid, String description) {
		ExpressLabel.setLabelDescription(oid, description);
	}

	public static void setLabelColor(String oid, Color color) {
		ExpressLabel.setLabelColor(oid, color);
	}

	public static void setLabelVisible(String oid, boolean p) {
		ExpressLabel.setLabelVisible(oid, p);
	}

	public static void removeAllLabels() {
		KMADEMainFrame.getProjectPanel().getLabelPanel().removeAllLabels();
	}

	/**
	 * @param newLabelObject
	 * @param value
	 * @return
	 */
	public static String setLabelName(String newLabelObject, String value) {
		return (ExpressLabel.setLabelName(newLabelObject, value));
	}

	/**
	 * @param oidRow
	 * @param boolean1
	 */
	public static void setLabelColorVisible(String oidRow, boolean boolean1) {
		ExpressLabel.setLabelColorVisible(oidRow, boolean1);
	}
}

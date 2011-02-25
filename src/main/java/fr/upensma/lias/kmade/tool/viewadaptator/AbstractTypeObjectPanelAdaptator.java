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

import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

/**
 * @author Mickael BARON
 */
public final class AbstractTypeObjectPanelAdaptator {

    private static int stateEnumereOrInterval = 0; // 0 : aucun; 1 : enuemere; 2
						   // : intervalle.

    public static void showEnumPanel() {
	KMADEReadWriteAbstractTypeObjectPanel.showEnumOrIntervalPanel();
	KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor()
		.showEnumOrIntervalPanel(true);
	stateEnumereOrInterval = 1;
    }

    public static void showIntervalPanel() {
	KMADEReadWriteAbstractTypeObjectPanel.showEnumOrIntervalPanel();
	KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor()
		.showEnumOrIntervalPanel(false);
	stateEnumereOrInterval = 2;
    }

    public static void setActiveEnumereOrInterval(int p) {
	if (p == 0) {
	    if (stateEnumereOrInterval == 1) {
		AbstractAttributeAdaptator.updateEnumList();
	    } else if (stateEnumereOrInterval == 2) {
		AbstractAttributeAdaptator.updateIntervalList();
	    }
	    KMADEReadWriteAbstractTypeObjectPanel.showAbstractObjectPanel();
	}
	AbstractTypeObjectPanelAdaptator.stateEnumereOrInterval = p;
    }

    public static int getActiveEnumereOrInterval() {
	return AbstractTypeObjectPanelAdaptator.stateEnumereOrInterval;
    }
}


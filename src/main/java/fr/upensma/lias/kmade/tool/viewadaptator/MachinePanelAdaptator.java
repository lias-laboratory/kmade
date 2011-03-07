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
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressMachine;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;

/**
 * @author Mickael BARON
 */
public final class MachinePanelAdaptator {    

    private static String oidClaseActive = Oid.OID_NULL;

    public static void setActiveMachine(String name, String oid) {
	oidClaseActive = oid;
	KMADEMainFrame.getProjectPanel().getMachinePanel()
		.setActiveMachineObject(name, oid);
    }

    public static String getActiveMachine() {
	return oidClaseActive;
    }

    public static Object[][] getParcsIntoTab(String oid) {

	return ExpressMachine.getParcsIntoTab(oid);
    }

    public static Object[][] getOtherParcsIntoTab(String oid) {
	return ExpressMachine.getOtherParcsIntoTab(oid);
    }
}
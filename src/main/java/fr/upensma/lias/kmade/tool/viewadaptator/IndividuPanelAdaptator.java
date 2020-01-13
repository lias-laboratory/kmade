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
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividu;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;

/**
 * @author Mickael BARON
 */
public final class IndividuPanelAdaptator {

	private static String oidClaseActive = Oid.OID_NULL;

	public static void setActiveIndividu(String name, String oid) {
		oidClaseActive = oid;
		KMADEMainFrame.getProjectPanel().tableIndividu().setActiveIndividuObject(name, oid);
	}

	public static String getActiveIndividu() {
		return oidClaseActive;
	}

	public static Object[][] getOrganizationIntoTab(String oid) {

		return ExpressIndividu.getOrganisationIntoTab(oid);
	}

	public static Object[][] getOtherOrganizationIntoTab(String oid) {
		return ExpressIndividu.getOtherOrganisationIntoTab(oid);
	}
}

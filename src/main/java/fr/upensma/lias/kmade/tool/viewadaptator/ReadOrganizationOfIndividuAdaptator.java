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

import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividu;
import fr.upensma.lias.kmade.tool.view.worldobject.user.KMADEReadOrganisationOfIndividu;

/**
 * @author Mickael BARON
 */
public class ReadOrganizationOfIndividuAdaptator {

    private static final KMADEReadOrganisationOfIndividu myReadOrganisationOfIndividu = new KMADEReadOrganisationOfIndividu();

    public static void showReadOrganisationOfIndividu() {
	ReadOrganizationOfIndividuAdaptator.updateReadOrganizationOfIndividu();
	if (!myReadOrganisationOfIndividu.isVisible()) {
	    myReadOrganisationOfIndividu.setVisible(true);
	}
    }

    public static void closeReadOrganizationOfIndividu() {
	myReadOrganisationOfIndividu.setVisible(false);
    }

    public static void updateReadOrganizationOfIndividu() {
	myReadOrganisationOfIndividu.updateDataModel(ExpressIndividu
		.getOrganisationIntoTab(""));
    }

}

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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividu;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadIndividuObjectTable;

/**
 * @author Mickael BARON
 */
public final class IndividuAdaptator {

    private static final KMADEReadIndividuObjectTable individuPanel = new KMADEReadIndividuObjectTable();

    public static String addIndividu() {
	return ExpressIndividu.createIndividu();
    }

    public static String[] getIndividusName() {
	return ExpressIndividu.getIndividusName();
    }

    public static void removeIndividu(String oid) {
	ExpressIndividu.deleteIndividu(oid);
    }

    public static void affRemoveIndividu(String oid) {
	ExpressIndividu.affRemoveIndividu(oid);
    }

    public static void removeAllIndividu() {
	KMADEMainFrame.getProjectPanel().tableIndividu()
		.getIndividuObjectTable().removeAllIndividu();
    }

    public static String setIndividuName(String oid, String name) {
	return ExpressIndividu.setIndividuName(oid, name);

    }

    public static void setIndividuStatut(String oid, String name) {
	ExpressIndividu.setIndividuStatut(oid, name);

    }

    public static void setIndividuRole(String oid, String name) {
	ExpressIndividu.setIndividuRole(oid, name);
    }

    public static void setIndividuImage(String oid, String name) {
	ExpressIndividu.setIndividuImage(oid, name);
    }

    public static void refreshReadIndividuTable() {
	Object[][] temp = new Object[KMADEMainFrame.getProjectPanel()
		.tableIndividu().getIndividuObjectTable().getModel()
		.getRowCount() - 1][6];
	for (int i = 0; i < KMADEMainFrame.getProjectPanel().tableIndividu()
		.getIndividuObjectTable().getModel().getRowCount() - 1; i++) {
	    temp[i][0] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 0);
	    temp[i][1] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 1);
	    temp[i][2] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 2);
	    temp[i][3] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 3);
	    temp[i][4] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 4);
	    temp[i][5] = KMADEMainFrame.getProjectPanel().tableIndividu()
		    .getIndividuObjectTable().getModel().getValueAt(i, 5);
	}
	individuPanel.setData(temp);
    }

    public static void updateIndividuView() {
	ArrayList<Person> myList = ExpressIndividu.getIndividus();
	for (int i = 0; i < myList.size(); i++) {
	    KMADEMainFrame
		    .getProjectPanel()
		    .tableIndividu()
		    .getIndividuObjectTable()
		    .addIndividu(myList.get(i).getName(),
			    myList.get(i).getStatut(), myList.get(i).getRole(),
			    myList.get(i).getImage(),
			    myList.get(i).getOrganisations(),
			    myList.get(i).getOid().get());
	}
    }

    public static KMADEReadIndividuObjectTable getIndividuPanel() {
	return individuPanel;
    }
}

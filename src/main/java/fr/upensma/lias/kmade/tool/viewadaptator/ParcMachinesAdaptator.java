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

import fr.upensma.lias.kmade.kmad.schema.tache.ParkMachines;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressParcMachines;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadParcMachinesObjectTable;

/**
 * @author Mickael BARON
 */
public final class ParcMachinesAdaptator {

	private static final KMADEReadParcMachinesObjectTable parcMachinesPanel = new KMADEReadParcMachinesObjectTable();

	public static String addParcMachines() {
		return ExpressParcMachines.createParcMachines();
	}

	public static String[] getParcMachinessName() {
		return ExpressParcMachines.getParcMachinesName();
	}

	public static void removeParcMachines(String oid) {
		ExpressParcMachines.deleteParcMachines(oid);
	}

	public static void affRemoveParcMachines(String oid) {
		ExpressParcMachines.affRemoveParcMachines(oid);
	}

	public static void removeAllParcMachines() {
		KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().removeAllParcs();
	}

	public static String setParcMachinesName(String oid, String name) {
		return ExpressParcMachines.setParcMachinesName(oid, name);

	}

	public static void setParcMachinesDescription(String oid, String desc) {
		ExpressParcMachines.setParcMachinesDescription(oid, desc);

	}

	public static void setParcMachinesImage(String oid, String name) {
		ExpressParcMachines.setParcMachinesImage(oid, name);
	}

	public static void refreshReadParcMachinesTable() {
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().getParcMachinesPanel()
				.getParcMachinesObjectTable().getModel().getRowCount() - 1][6];
		for (int i = 0; i < KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable()
				.getModel().getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 5);
		}
		parcMachinesPanel.setData(temp);
	}

	public static void updateParcMachinesView() {
		ArrayList<ParkMachines> myList = ExpressParcMachines.getParcsMachines();
		for (int i = 0; i < myList.size(); i++) {
			KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().addParcMachines(
					myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getImage(),
					myList.get(i).getMembers(), myList.get(i).getOid().get());
		}
	}

	public static KMADEReadParcMachinesObjectTable getParcMachinesPanel() {
		return parcMachinesPanel;
	}
}

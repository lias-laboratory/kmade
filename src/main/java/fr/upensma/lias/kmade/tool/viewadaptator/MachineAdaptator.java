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

import fr.upensma.lias.kmade.kmad.schema.tache.Machine;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressMachine;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadMachineObjectTable;

/**
 * @author Mickael BARON
 */
public final class MachineAdaptator {
	private static final KMADEReadMachineObjectTable machinePanel = new KMADEReadMachineObjectTable();

	public static String addMachine() {
		return ExpressMachine.createMachine();
	}

	public static String[] getMachinesName() {
		return ExpressMachine.getMachinesName();
	}

	public static void removeMachine(String oid) {
		ExpressMachine.deleteMachine(oid);
	}

	public static void affRemoveMachine(String oid) {
		ExpressMachine.affRemoveMachine(oid);
	}

	public static void removeAllMachine() {
		KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().removeAllMachine();
	}

	public static String setMachineName(String oid, String name) {
		return ExpressMachine.setMachineName(oid, name);

	}

	public static void setMachineDescription(String oid, String desc) {
		ExpressMachine.setMachineDescription(oid, desc);

	}

	public static void setMachineIsComputer(String oid, String iscomp) {
		ExpressMachine.setMachineIsComputer(oid, iscomp);

	}

	public static void setMachineImage(String oid, String path) {
		ExpressMachine.setMachineImage(oid, path);
	}

	public static void refreshReadIndividuTable() {
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable()
				.getModel().getRowCount() - 1][6];
		for (int i = 0; i < KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
				.getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i, 5);
		}
		machinePanel.setData(temp);
	}

	public static void updateMachineView() {
		ArrayList<Machine> myList = ExpressMachine.getMachines();
		for (int i = 0; i < myList.size(); i++) {
			KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().addMachine(
					myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getIsComputer().toString(),
					myList.get(i).getImage(), myList.get(i).getMemberOf(), myList.get(i).getOid().get());
		}
	}

	public static KMADEReadMachineObjectTable getIndividuPanel() {
		return machinePanel;
	}
}

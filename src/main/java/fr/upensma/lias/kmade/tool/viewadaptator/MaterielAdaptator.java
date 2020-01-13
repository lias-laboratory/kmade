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

import fr.upensma.lias.kmade.tool.coreadaptator.ExpressMateriel;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadMaterielObjectTable;

/**
 * @author Mickael BARON
 */
public final class MaterielAdaptator {
	private static final KMADEReadMaterielObjectTable MaterielPanel = new KMADEReadMaterielObjectTable();

	public static String[] getMaterielsName() {
		return ExpressMateriel.getMaterielsName();
	}

	public static String setMaterielName(String oid, String name) {
		return ExpressMateriel.setMaterielName(oid, name);

	}

	public static void setMaterielDescription(String oid, String name) {
		ExpressMateriel.setMaterielDescription(oid, name);

	}

	public static void setMaterielImage(String oid, String name) {
		ExpressMateriel.setMaterielImage(oid, name);
	}

	public static void refreshReadMaterielTable() {
		int temporg = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
				.getRowCount() - 1;
		int tempind = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
				.getRowCount() - 1;

		Object[][] temp = new Object[temporg + tempind][6];
		for (int i = 0; i < temporg; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 1);
			temp[i][2] = "";// possibilit� de mettre une fonction qui dit si le
			// parc de machine poss�de que des machines
			// informatis�s, ou non, ou les deux!
			temp[i][3] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 2);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 3);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel()
					.getValueAt(i, 4);
		}
		for (int i = temporg; i < temporg + tempind; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel()
					.getValueAt(i - temporg, 5);
		}
		MaterielPanel.setData(temp);
	}

	public static KMADEReadMaterielObjectTable getMaterielPanel() {
		return MaterielPanel;
	}
}

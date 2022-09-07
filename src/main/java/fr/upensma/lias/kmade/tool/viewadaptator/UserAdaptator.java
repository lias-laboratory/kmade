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

import fr.upensma.lias.kmade.tool.coreadaptator.ExpressUser;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadUserObjectTable;

/**
 * @author Mickael BARON
 */
public final class UserAdaptator {
	private static final KMADEReadUserObjectTable userPanel = new KMADEReadUserObjectTable();

	public static String addUser() {
		return ExpressUser.createUser();
	}

	public static String[] getUsersName() {
		return ExpressUser.getUsersName();
	}

	/**
	 * Retourne la liste des noms des acteurs potentiels de la t�che qui ne sont pas
	 * d�j� acteur de la t�che
	 * 
	 * @param oidtask
	 * @param oidTask
	 * @return
	 */
	public static String[] getUsersNoActorName(String oidTask) {
		return ExpressUser.getUsersNoActorName(oidTask);
	}

	public static void removeUser(String oid) {
		ExpressUser.deleteUser(oid);
	}

	public static void affRemoveUser(String oid) {
		ExpressUser.affRemoveUser(oid);
	}

	// public static void removeAllUser() {
	// KMADEMainFrame.getProjectPanel().tableUtil().removeAllUser();
	// }

	public static String setUserName(String oid, String name) {
		return ExpressUser.setUserName(oid, name);

	}

	public static void setUserStatut(String oid, String name) {
		ExpressUser.setUserStatut(oid, name);

	}

	public static void setUserRole(String oid, String name) {
		ExpressUser.setUserRole(oid, name);
	}

	public static void setUserImage(String oid, String name) {
		ExpressUser.setUserImage(oid, name);
	}

	public static void refreshReadUserTable() {
		int temporg = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
				.getRowCount() - 1;
		int tempind = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel().getRowCount()
				- 1;

		Object[][] temp = new Object[temporg + tempind][6];
		for (int i = 0; i < temporg; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel()
					.getValueAt(i, 5);
		}
		for (int i = temporg; i < temporg + tempind; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().tableIndividu().getIndividuObjectTable().getModel()
					.getValueAt(i - temporg, 5);
		}
		userPanel.setData(temp);
	}

	public static KMADEReadUserObjectTable getUserPanel() {
		return userPanel;
	}
}

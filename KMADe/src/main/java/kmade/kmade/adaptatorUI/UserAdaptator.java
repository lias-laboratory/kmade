package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadUserObjectTable;
import kmade.kmade.adaptatorFC.ExpressUser;
import kmade.nmda.schema.tache.User;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public final class UserAdaptator {
	private static final KMADEReadUserObjectTable userPanel = new KMADEReadUserObjectTable();
    
	public static String addUser() {
		return ExpressUser.createUser();
	}

    public static String[] getUsersName() {
        return ExpressUser.getUsersName();
    }
    
	public static void removeUser(String oid) {
        ExpressUser.deleteUser(oid);
	}

	public static void affRemoveUser(String oid) {
        ExpressUser.affRemoveUser(oid);
	}

	public static void removeAllUser() {
		KMADEMainFrame.getProjectPanel().tableUtil().removeAllUser();
	}

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
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().tableUtil().getModel().getRowCount() -1][5];
		for (int i = 0 ; i < KMADEMainFrame.getProjectPanel().tableUtil().getModel().getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().tableUtil().getModel().getValueAt(i,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().tableUtil().getModel().getValueAt(i,1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().tableUtil().getModel().getValueAt(i,2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().tableUtil().getModel().getValueAt(i,3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().tableUtil().getModel().getValueAt(i,4);
		}
        userPanel.setData(temp);
    }
    
    public static void updateUserView() {
        ArrayList<User> myList = ExpressUser.getUsers();
        for (int i = 0; i < myList.size(); i++) {
            KMADEMainFrame.getProjectPanel().tableUtil().addUser(myList.get(i).getName(), myList.get(i).getStatut(), myList.get(i).getRole(), myList.get(i).getImage(), myList.get(i).getOid().get());
        }        
    }

    public static KMADEReadUserObjectTable getUserPanel() {
        return userPanel;
    }
}

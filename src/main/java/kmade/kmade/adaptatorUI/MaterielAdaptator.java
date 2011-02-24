package kmade.kmade.adaptatorUI;

import kmade.kmade.adaptatorFC.ExpressMateriel;
import kmade.kmade.view.KMADEMainFrame;
import kmade.kmade.view.taskproperties.readworldobject.KMADEReadMaterielObjectTable;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/

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
    	int temporg = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getRowCount() -1;
    	int tempind = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getRowCount() -1;
    	
    	
    	
		Object[][] temp = new Object[temporg+tempind][6];
		for (int i = 0 ; i < temporg; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,1);
			temp[i][2] = "";//possibilit� de mettre une fonction qui dit si le parc de machine poss�de que des machines informatis�s, ou non, ou les deux!
			temp[i][3] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,2);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,3);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,4);
		}
		for(int i = temporg; i < temporg+tempind; i++){
			temp[i][0] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i-temporg,5);
		}
		MaterielPanel.setData(temp);
    }
    

    public static KMADEReadMaterielObjectTable getMaterielPanel() {
        return MaterielPanel;
    }



}

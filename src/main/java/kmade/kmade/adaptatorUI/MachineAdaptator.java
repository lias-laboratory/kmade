package kmade.kmade.adaptatorUI;


import java.util.ArrayList;

import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadMachineObjectTable;
import kmade.kmade.adaptatorFC.ExpressMachine;
import kmade.nmda.schema.tache.Machine;

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
		ExpressMachine.setMachineIsComputer(oid,iscomp);
		
	}
	  	
	public static void setMachineImage(String oid, String path){
		ExpressMachine.setMachineImage(oid,path);
	}
	
    public static void refreshReadIndividuTable() {
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getRowCount() -1][6];
		for (int i = 0 ; i < KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().getModel().getValueAt(i,5);
		}
        machinePanel.setData(temp);
    }
    
    public static void updateMachineView() {
        ArrayList<Machine> myList = ExpressMachine.getMachines();
        for (int i = 0; i < myList.size(); i++) {
            KMADEMainFrame.getProjectPanel().getMachinePanel().getMachineObjectTable().addMachine(myList.get(i).getName(), myList.get(i).getDescription(),myList.get(i).getIsComputer().toString(), myList.get(i).getImage(),myList.get(i).getMemberOf(), myList.get(i).getOid().get());
        }        
    }

    public static KMADEReadMachineObjectTable getIndividuPanel() {
        return machinePanel;
    }


}

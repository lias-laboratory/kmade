package kmade.kmade.adaptatorUI;


import java.util.ArrayList;

import kmade.kmade.adaptatorFC.ExpressParcMachines;
import kmade.kmade.view.KMADEMainFrame;
import kmade.kmade.view.taskproperties.readworldobject.KMADEReadParcMachinesObjectTable;
import kmade.nmda.schema.tache.ParcMachines;

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
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getRowCount() -1][6];
		for (int i = 0 ; i < KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().getModel().getValueAt(i,5);
		}
		parcMachinesPanel.setData(temp);
    }
    
    public static void updateParcMachinesView() {
        ArrayList<ParcMachines> myList = ExpressParcMachines.getParcsMachines();
        for (int i = 0; i < myList.size(); i++) {
            KMADEMainFrame.getProjectPanel().getParcMachinesPanel().getParcMachinesObjectTable().addParcMachines(myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getImage(),myList.get(i).getMember(), myList.get(i).getOid().get());
        }        
    }

    public static KMADEReadParcMachinesObjectTable getParcMachinesPanel() {
        return parcMachinesPanel;
    }
}

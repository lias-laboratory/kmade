package kmade.kmade.adaptatorUI;


import java.util.ArrayList;

import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadOrganisationObjectTable;
import kmade.kmade.adaptatorFC.ExpressOrganisation;
import kmade.nmda.schema.tache.Organisation;

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
 
public final class OrganisationAdaptator {

	private static final KMADEReadOrganisationObjectTable organisationPanel = new KMADEReadOrganisationObjectTable();
    
	public static String addOrganisation() {
		return ExpressOrganisation.createOrganisation();
	}

    public static String[] getOrganisationsName() {
        return ExpressOrganisation.getOrganisationsName();
    }
    
	public static void removeOrganisation(String oid) {
        ExpressOrganisation.deleteOrganisation(oid);
	}

	public static void affRemoveOrganisation(String oid) {
        ExpressOrganisation.affRemoveOrganisation(oid);
	}

	public static void removeAllOrganisation() {
		KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().removeAllOrganization();
	}

	public static String setOrganisationName(String oid, String name) {
		return ExpressOrganisation.setOrganisationName(oid, name);

	}

	public static void setOrganisationStatut(String oid, String name) {
		ExpressOrganisation.setOrganisationStatut(oid, name);

	}

	public static void setOrganisationRole(String oid, String name) {
		ExpressOrganisation.setOrganisationRole(oid, name);
	}

    public static void setOrganisationImage(String oid, String name) {
        ExpressOrganisation.setOrganisationImage(oid, name);
    }
    
    public static void refreshReadOrganisationTable() {
		Object[][] temp = new Object[KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getRowCount() -1][6];
		for (int i = 0 ; i < KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getRowCount() - 1; i++) {
			temp[i][0] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,0);
			temp[i][1] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,1);
			temp[i][2] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,2);
			temp[i][3] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,3);
			temp[i][4] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,4);
			temp[i][5] = KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().getModel().getValueAt(i,5);
		}
        organisationPanel.setData(temp);
    }
    
    public static void updateOrganisationView() {
        ArrayList<Organisation> myList = ExpressOrganisation.getOrganisations();
        for (int i = 0; i < myList.size(); i++) {
            KMADEMainFrame.getProjectPanel().getOrganisationPanel().getOrganizationObjectTable().addOrganization(myList.get(i).getName(), myList.get(i).getStatut(), myList.get(i).getRole(), myList.get(i).getImage(),myList.get(i).getMember(), myList.get(i).getOid().get());
        }        
    }

    public static KMADEReadOrganisationObjectTable getorganisationPanel() {
        return organisationPanel;
    }
}

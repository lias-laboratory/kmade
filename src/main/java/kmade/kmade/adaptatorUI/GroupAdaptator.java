package kmade.kmade.adaptatorUI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import kmade.kmade.KMADEConstant;
import kmade.kmade.coreadaptator.ExpressGroup;
import kmade.kmade.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;

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
public final class GroupAdaptator {
    public static void removeAllGroup() {
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getGroupTable().removeAllGroup();
    }

    public static String addGroup(String oidObjAbs) {
        if (!oidObjAbs.equals(Oid.OID_NULL)) {
            Oid oid = new Oid(oidObjAbs);
            String toto = ExpressGroup.createGroup(oid);
            ReadConcreteObjectAdaptator.updateReadConcreteObject();
            return toto;
        } else {
            return "-1";
        }
    }

    public static String setGroupName(String oid, String name) {
        return ExpressGroup.setGroupName(oid, name);
    }

    public static void setGroupDescription(String oid, String description) {
        ExpressGroup.setGroupDescription(oid, description);
    }

    public static boolean setGroupSet(String oid, String ensemble) {
   		boolean isConcreteObject = ExpressGroup.affRemoveAgregat(oid, ensemble);
   	
        if (isConcreteObject) {           
            String[] lstWarning = InterfaceExpressJava.getGestionWarning().takeMessages();
            String s = "";
            for (int j = 0; j < lstWarning.length; j++) {
                if (j == 0)
                    s = s + lstWarning[j];
                else
                    s = s + "\n" + lstWarning[j];
            }
    		
            int value = JOptionPane.showConfirmDialog(GraphicEditorAdaptator.getMainFrame(),s,"Suppression Objets Concrets",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE)));
            if (value == JOptionPane.YES_OPTION) {
            } else {
                return false;
            }    		
        } else {
            // On vide les messages.
            InterfaceExpressJava.getGestionWarning().takeMessages();
        }
        ExpressGroup.removeOldAgregatAndCreateNewAgregat(oid, ensemble);
        return true;
    }

    public static void removeGroup(String oid) {
        ExpressGroup.removeGroup(oid);
    }

    public static void affRemoveGroupe(String oid) {
        ExpressGroup.affRemoveGroup(oid);
    }
}

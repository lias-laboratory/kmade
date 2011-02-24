package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressAbstractAttribut;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressConcreteAttribut;
import fr.upensma.lias.kmade.tool.view.worldobject.concreteobject.KMADEReadWriteConcreteAttributTable;


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
public final class ConcreteAttributAdaptator {
	private static final KMADEReadWriteConcreteAttributTable concreteAttributPanel = GraphicEditorAdaptator.getPanelCreationObjConc().getTableAttr();

	public static boolean setConcreteAttributValue(String oidAttributConcret, String name) {
		boolean isError = ExpressConcreteAttribut.setConcreteAttributValue(oidAttributConcret, name);
		if (!isError) {
			ReadConcreteObjectAdaptator.updateReadConcreteObject();
		}
		return isError;
	}
    
    protected static String getOidAttrAbs(String attributName) {
        return ExpressAbstractAttribut.getOidWithAbstractAttributName(attributName, ConcreteObjectPanelAdaptator.getActiveAbstractObject());
    } 

    public static ArrayList<String> getTypeRefValues(String attributName) {
        return ExpressAbstractAttribut.getTypeRefValues(ConcreteAttributAdaptator.getOidAttrAbs(attributName));
    }
    
    public static void updateConcreteObjectView(String oidConcreteObject, String concrete) {
        // Mise � jour de la liste des attributs concrets d'un objet concret donn�.
        ArrayList<Object[]> myValue = ExpressConcreteAttribut.getConcreteAttribut(new Oid(oidConcreteObject));
        
        Object[][] myList = new Object[myValue.size()][4];
        for (int i = 0 ; i < myValue.size() ; i++) {
            // Le nom
            myList[i][0] = myValue.get(i)[0];
            // Le type
            myList[i][1] = myValue.get(i)[1];
            // La valeur
            myList[i][2] = myValue.get(i)[2];
            // Oid de l'attribut concret.
            myList[i][3] = myValue.get(i)[3];            
        }
        concreteAttributPanel.setData(myList);
        concreteAttributPanel.setConcreteAttributNameBorder(concrete);
    }
}

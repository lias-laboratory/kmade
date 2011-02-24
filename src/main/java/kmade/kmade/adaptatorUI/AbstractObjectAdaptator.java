package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.coreadaptator.ExpressAbstractObject;
import kmade.kmade.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;

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
public final class AbstractObjectAdaptator {
	/**
	 * Supprime tous les objets abstrait
	 */
	public static void removeAllAbstractObject() {
        KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectTable().removeAllAbstractObject();
	}
  
    /**
     * Cette méthode permet de communiquer avec Express pour créer un objet abstrait
     * et retourne l'OID du nouvel objet.
     */
    public static String addAbstractObject() {
    	return ExpressAbstractObject.createAbstractObject();
    }
    
    /**
     * Modifie le nom de l'objet abstrait
     */    
	public static String setAbstractObjectName(String oid, String name) {
		String value = ExpressAbstractObject.setAbstractObjectName(oid, name);
		ReadConcreteObjectAdaptator.updateReadConcreteObject();
		return value;
	}

    /**
     * Modifie la description de l'objet abstrait
     */    
	public static void setAbstractObjectDescription(String oid, String Description) {
		ExpressAbstractObject.setAbstractObjectDescription(oid, Description);
	}

    /**
     * Supprime un objet abstrait
     */    
	public static void removeAbstractObject(String oid) {
		ExpressAbstractObject.removeObj(oid);
		ReadConcreteObjectAdaptator.updateReadConcreteObject();		
	}

	public static void affRemoveObjAbs(String oid) {
		ExpressAbstractObject.affRemoveObj(oid);
	}
    
    public static void updateAbstractObjectView() {
        ArrayList<ObjetAbstrait> myList = ExpressAbstractObject.getAbstractObjects();
        
        for (int i = 0; i < myList.size(); i++) {
            KMADEReadWriteAbstractTypeObjectPanel.getAbstractObjectEditor().getAbstractObjectTable().addAbstractObject(myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getOid().get());
        }        
    }
}

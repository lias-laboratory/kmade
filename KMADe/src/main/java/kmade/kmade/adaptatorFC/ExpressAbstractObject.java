package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
public class ExpressAbstractObject {
	public static String createAbstractObject() {
		Oid oidObjAbs = InterfaceExpressJava.createEntity("metaobjet", "ObjetAbstrait");
		return (oidObjAbs.get());
	}

	public static void removeObj(String oid) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
		m.delete();
	}

	public static void affRemoveObj(String oid) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
		m.affDelete();
	}

	public static String setAbstractObjectName(String oid, String name) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setAbstractObjectDescription(String oid, String description) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(description);
	}

    public static ArrayList<ObjetAbstrait> getAbstractObjects() {
		ArrayList<ObjetAbstrait> lst = new ArrayList<ObjetAbstrait>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetAbstrait");
		for (int i = 0; i < objs.length; i++) {
			ObjetAbstrait obj = (ObjetAbstrait) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static Object[][] getAbsObjIntoTab() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetAbstrait");
		Object[][] tabObj = new Object[objs.length][3];
		for (int i = 0; i < objs.length; i++) {
			ObjetAbstrait obj = (ObjetAbstrait) objs[i];
			tabObj[i][0] = obj.getName();
			tabObj[i][1] = obj.getDescription();
			tabObj[i][2] = obj.getOid().get();
		}
		return tabObj;
	}

	
	/**
	 * Cette méthode permet de retourner l'ensemble des valeurs utilisées pour la Table qui crée les attributs.
	 * @param oidObj
	 * @return nom, description, type, 
	 */
	public static Object[][] getAttributes(String oidObj) {
		ObjetAbstrait m = (ObjetAbstrait)InterfaceExpressJava.prendre(new Oid(oidObj));
		ArrayList<AttributAbstrait> attrs = m.getInverseAttributsAbs();
		Object[][] tabObj = new Object[attrs.size()][6];
		for (int i = 0; i < attrs.size(); i++) {
			AttributAbstrait a = attrs.get(i);
			tabObj[i][0] = a.getName();
			tabObj[i][1] = a.getDescription();
			tabObj[i][2] = a.getTypeStructure().getValue();
			if (a.getTypeRef() != null) {
				tabObj[i][3] = a.getTypeRef().getName();
				tabObj[i][4] = a.getTypeRef().getOid().get();
			} else {
				tabObj[i][3] = "";
				tabObj[i][4] = Oid.OID_NULL;
			}
			tabObj[i][5] = a.getOid().get();
		}
		return tabObj;
	}

	public static String[] getAttrsNames(String oidObj) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oidObj));
		ArrayList<AttributAbstrait> attrs = m.getInverseAttributsAbs();
		String[] tabObj = new String[attrs.size()];
		for (int i = 0; i < attrs.size(); i++) {
			AttributAbstrait a = attrs.get(i);
			tabObj[i] = a.getName();
		}
		return tabObj;
	}

    public static ObjetAbstrait getObjetAbstrait(String oidObj) {
        return (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oidObj));
    }
    
	/**
     * Cette méthode récupère l'ensemble des objets concret d'un objet abstrait donné.
	 * @param oidObj
	 * @return
	 */
	public static Object[][] getConcreteObjectsFromAbstractObject(String oidObj) {
		ObjetAbstrait m = (ObjetAbstrait) InterfaceExpressJava.prendre(new Oid(oidObj));
		ArrayList<ObjetConcret> objs = m.getInverseObjConc();
		Object[][] tabObj = new Object[objs.size()][4];
		for (int i = 0; i < objs.size(); i++) {
			ObjetConcret a = objs.get(i);
			tabObj[i][0] = a.getName();
			tabObj[i][1] = a.getDescription();
			tabObj[i][2] = a.getAppartientGroupe().getName();
			tabObj[i][3] = a.getOid().get();
		}
		return tabObj;
	}
}

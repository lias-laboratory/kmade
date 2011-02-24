package kmade.kmade.adaptatorFC;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.ActeurSysteme;
import kmade.nmda.schema.tache.Materiel;
import kmade.nmda.schema.tache.Tache;

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
public class ExpressActeurSysteme {
    public static String getActorSystemList(Tache myRefTask) {
    		String toDisplay = "";
         for (int i = 0; i < myRefTask.getActeurs().size(); i++) {
			toDisplay += ((ActeurSysteme) myRefTask.getActeurSysteme().get(i)).getName();
			if (i < myRefTask.getActeurSysteme().size() - 1) {
				toDisplay += ";";
			}
		}
		return toDisplay;
    }
    
	public static Oid createActorSystem(Oid oidUserSystem) {
		Oid oidActorSystem = InterfaceExpressJava.createEntity("tache", "ActeurSysteme");
		ActeurSysteme a = (ActeurSysteme)InterfaceExpressJava.prendre(oidActorSystem);
		Materiel u = (Materiel)InterfaceExpressJava.prendre(oidUserSystem);
		a.setMaterielRef(u);
        u.addInverseActeurSysteme(a);
		return (oidActorSystem);
	}
  
    public static String[] getActorSystemFromOid(Oid actorSystem) {
        String[] myTable = new String[4];
        ActeurSysteme a = (ActeurSysteme)InterfaceExpressJava.prendre(actorSystem);
        myTable[0] = a.getName();
        myTable[1] = a.getExperience().getValue();
        myTable[2] = a.getCompetence();
        myTable[3] = actorSystem.get();
        return myTable;
    }
    

	public static ArrayList<ActeurSysteme> extractActorSystemFromTask(Tache current) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "ActeurSysteme");

		ArrayList<ActeurSysteme> empilActeur = new ArrayList<ActeurSysteme>();
		for (int i = 0; i < objs.length; i++) {
			ActeurSysteme acteursysteme = (ActeurSysteme) objs[i];
			// Le Oid de la tache qui contient l'acteur i.
			String oidActeurTache = acteursysteme.getInverseTache().getOid().get();

			if (oidActeurTache.equals(current.getOid().get())) {
				empilActeur.add(acteursysteme);
			}
		}
		return empilActeur;
	}

	public static void setActorSystemCompetence(String oid, String comp) {
		ActeurSysteme m = (ActeurSysteme) InterfaceExpressJava.prendre(new Oid(oid));
		m.setCompetence(comp);
	}

	public static void setActorSystemExperience(String oid, String exp) {
		ActeurSysteme m = (ActeurSysteme) InterfaceExpressJava.prendre(new Oid(oid));
		m.setExperience(exp);
	}

}

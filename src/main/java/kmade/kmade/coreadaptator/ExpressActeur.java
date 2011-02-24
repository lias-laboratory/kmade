package kmade.kmade.coreadaptator;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Acteur;
import kmade.nmda.schema.tache.Tache;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class ExpressActeur {
    public static String getActorList(Tache myRefTask) {
    		String toDisplay = "";
         for (int i = 0; i < myRefTask.getActeurs().size(); i++) {
			toDisplay += ((Acteur) myRefTask.getActeurs().get(i)).getName();
			if (i < myRefTask.getActeurs().size() - 1) {
				toDisplay += ";";
			}
		}
		return toDisplay;
    }
    
	public static Oid createActor(Oid oidUser) {
		Oid oidActor = InterfaceExpressJava.createEntity("tache", "Acteur");
		Acteur a = (Acteur)InterfaceExpressJava.prendre(oidActor);
		User u = (User)InterfaceExpressJava.prendre(oidUser);
		a.setUserRef(u);
        u.addInverseActeur(a);
		return (oidActor);
	}
  
    public static String[] getActorFromOid(Oid actor) {
        String[] myTable = new String[4];
        Acteur a = (Acteur)InterfaceExpressJava.prendre(actor);
        myTable[0] = a.getName();
        myTable[1] = a.getExperience().getValue();
        myTable[2] = a.getCompetence();
        myTable[3] = actor.get();
        return myTable;
    }
    
	/**
	 * Cette méthode permet de retourner toutes les instances Acteurs d'une tâche donnée.
	 * @param oidTacheSel
	 * @return
	 */
	public static ArrayList<Acteur> extractActorFromTask(Tache current) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Acteur");

		ArrayList<Acteur> empilActeur = new ArrayList<Acteur>();
		for (int i = 0; i < objs.length; i++) {
			Acteur acteur = (Acteur) objs[i];
			// Le Oid de la tache qui contient l'acteur i.
			String oidActeurTache = acteur.getInverseTache().getOid().get();

			if (oidActeurTache.equals(current.getOid().get())) {
				empilActeur.add(acteur);
			}
		}
		return empilActeur;
	}

	public static void setActorExperience(String oid, String exp) {
		Acteur m = (Acteur) InterfaceExpressJava.prendre(new Oid(oid));
		m.setExperience(exp);
	}

	public static void setActorCompetence(String oid, String name) {
		Acteur m = (Acteur) InterfaceExpressJava.prendre(new Oid(oid));
		m.setCompetence(name);
	}
}

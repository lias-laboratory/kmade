/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Actor;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.User;

/**
 * @author Mickael BARON
 */
public class ExpressActeur {
	public static String getActorList(Task myRefTask) {
		String toDisplay = "";
		for (int i = 0; i < myRefTask.getActors().size(); i++) {
			toDisplay += ((Actor) myRefTask.getActors().get(i)).getName();
			if (i < myRefTask.getActors().size() - 1) {
				toDisplay += ";";
			}
		}
		return toDisplay;
	}

	public static Oid createActor(Oid oidUser) {
		Oid oidActor = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ACTOR_CLASS);
		Actor a = (Actor) InterfaceExpressJava.prendre(oidActor);
		User u = (User) InterfaceExpressJava.prendre(oidUser);
		a.setUserRef(u);
		u.addReverseActor(a);
		return (oidActor);
	}

	public static String[] getActorFromOid(Oid actor) {
		String[] myTable = new String[4];
		Actor a = (Actor) InterfaceExpressJava.prendre(actor);
		myTable[0] = a.getName();
		myTable[1] = a.getExperience().getValue();
		myTable[2] = a.getCompetence();
		myTable[3] = actor.get();
		return myTable;
	}

	/**
	 * Cette méthode permet de retourner toutes les instances Acteurs d'une
	 * tâche donnée.
	 * 
	 * @param oidTacheSel
	 * @return
	 */
	public static ArrayList<Actor> extractActorFromTask(Task current) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.ACTOR_CLASS);

		ArrayList<Actor> empilActeur = new ArrayList<Actor>();
		for (int i = 0; i < objs.length; i++) {
			Actor acteur = (Actor) objs[i];
			// Le Oid de la tache qui contient l'acteur i.
			Task a = acteur.getInverseTache();
			if(a !=null){
				String oidActeurTache   = a.getOid().get();
				if (oidActeurTache.equals(current.getOid().get())) {
					empilActeur.add(acteur);
				}
			}



		}
		return empilActeur;
	}

	public static void setActorExperience(String oid, String exp) {
		Actor m = (Actor) InterfaceExpressJava.prendre(new Oid(oid));
		m.setExperience(exp);
	}

	public static void setActorCompetence(String oid, String name) {
		Actor m = (Actor) InterfaceExpressJava.prendre(new Oid(oid));
		m.setCompetence(name);
	}
}

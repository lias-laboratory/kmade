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

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Acteur;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.kmad.schema.tache.User;

/**
 * @author Mickael BARON
 */
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
	Acteur a = (Acteur) InterfaceExpressJava.prendre(oidActor);
	User u = (User) InterfaceExpressJava.prendre(oidUser);
	a.setUserRef(u);
	u.addInverseActeur(a);
	return (oidActor);
    }

    public static String[] getActorFromOid(Oid actor) {
	String[] myTable = new String[4];
	Acteur a = (Acteur) InterfaceExpressJava.prendre(actor);
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
    public static ArrayList<Acteur> extractActorFromTask(Tache current) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"Acteur");

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

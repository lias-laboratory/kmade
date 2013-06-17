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
import fr.upensma.lias.kmade.kmad.schema.tache.ActorSystem;
import fr.upensma.lias.kmade.kmad.schema.tache.Material;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;

/**
 * @author Mickael BARON
 */
public class ExpressActeurSysteme {
    public static String getActorSystemList(Task myRefTask) {
	String toDisplay = "";
	for (int i = 0; i < myRefTask.getActors().size(); i++) {
	    toDisplay += ((ActorSystem) myRefTask.getActorSystem().get(i))
		    .getName();
	    if (i < myRefTask.getActorSystem().size() - 1) {
		toDisplay += ";";
	    }
	}
	return toDisplay;
    }

    public static Oid createActorSystem(Oid oidUserSystem) {
	Oid oidActorSystem = InterfaceExpressJava.createEntity("tache",
		"ActeurSysteme");
	ActorSystem a = (ActorSystem) InterfaceExpressJava
		.prendre(oidActorSystem);
	Material u = (Material) InterfaceExpressJava.prendre(oidUserSystem);
	a.setMaterialRef(u);
	u.addReverseActorSystem(a);
	return (oidActorSystem);
    }

    public static String[] getActorSystemFromOid(Oid actorSystem) {
	String[] myTable = new String[4];
	ActorSystem a = (ActorSystem) InterfaceExpressJava
		.prendre(actorSystem);
	myTable[0] = a.getName();
	myTable[1] = a.getExperience().getValue();
	myTable[2] = a.getCompetence();
	myTable[3] = actorSystem.get();
	return myTable;
    }

    public static ArrayList<ActorSystem> extractActorSystemFromTask(
	    Task current) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ActeurSysteme");

	ArrayList<ActorSystem> empilActeur = new ArrayList<ActorSystem>();
	for (int i = 0; i < objs.length; i++) {
	    ActorSystem acteursysteme = (ActorSystem) objs[i];
	    // Le Oid de la tache qui contient l'acteur i.
	    String oidActeurTache = acteursysteme.getReverseTask().getOid()
		    .get();

	    if (oidActeurTache.equals(current.getOid().get())) {
		empilActeur.add(acteursysteme);
	    }
	}
	return empilActeur;
    }

    public static void setActorSystemCompetence(String oid, String comp) {
	ActorSystem m = (ActorSystem) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setCompetence(comp);
    }

    public static void setActorSystemExperience(String oid, String exp) {
	ActorSystem m = (ActorSystem) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setExperience(exp);
    }
}

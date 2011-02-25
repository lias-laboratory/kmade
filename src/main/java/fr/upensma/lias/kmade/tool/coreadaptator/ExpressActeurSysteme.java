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
import fr.upensma.lias.kmade.kmad.schema.tache.ActeurSysteme;
import fr.upensma.lias.kmade.kmad.schema.tache.Materiel;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;

/**
 * @author Mickael BARON
 */
public class ExpressActeurSysteme {
    public static String getActorSystemList(Tache myRefTask) {
	String toDisplay = "";
	for (int i = 0; i < myRefTask.getActeurs().size(); i++) {
	    toDisplay += ((ActeurSysteme) myRefTask.getActeurSysteme().get(i))
		    .getName();
	    if (i < myRefTask.getActeurSysteme().size() - 1) {
		toDisplay += ";";
	    }
	}
	return toDisplay;
    }

    public static Oid createActorSystem(Oid oidUserSystem) {
	Oid oidActorSystem = InterfaceExpressJava.createEntity("tache",
		"ActeurSysteme");
	ActeurSysteme a = (ActeurSysteme) InterfaceExpressJava
		.prendre(oidActorSystem);
	Materiel u = (Materiel) InterfaceExpressJava.prendre(oidUserSystem);
	a.setMaterielRef(u);
	u.addInverseActeurSysteme(a);
	return (oidActorSystem);
    }

    public static String[] getActorSystemFromOid(Oid actorSystem) {
	String[] myTable = new String[4];
	ActeurSysteme a = (ActeurSysteme) InterfaceExpressJava
		.prendre(actorSystem);
	myTable[0] = a.getName();
	myTable[1] = a.getExperience().getValue();
	myTable[2] = a.getCompetence();
	myTable[3] = actorSystem.get();
	return myTable;
    }

    public static ArrayList<ActeurSysteme> extractActorSystemFromTask(
	    Tache current) {
	Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache",
		"ActeurSysteme");

	ArrayList<ActeurSysteme> empilActeur = new ArrayList<ActeurSysteme>();
	for (int i = 0; i < objs.length; i++) {
	    ActeurSysteme acteursysteme = (ActeurSysteme) objs[i];
	    // Le Oid de la tache qui contient l'acteur i.
	    String oidActeurTache = acteursysteme.getInverseTache().getOid()
		    .get();

	    if (oidActeurTache.equals(current.getOid().get())) {
		empilActeur.add(acteursysteme);
	    }
	}
	return empilActeur;
    }

    public static void setActorSystemCompetence(String oid, String comp) {
	ActeurSysteme m = (ActeurSysteme) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setCompetence(comp);
    }

    public static void setActorSystemExperience(String oid, String exp) {
	ActeurSysteme m = (ActeurSysteme) InterfaceExpressJava.prendre(new Oid(
		oid));
	m.setExperience(exp);
    }
}

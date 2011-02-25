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
import java.util.TreeMap;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.KMADConcreteHistory;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.tache.StateSimulation;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.StackState;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.StackStateModel;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenSimulation;

/**
 * @author Mickael BARON
 */
public final class ExpressHistory {

    private static ArrayList<KMADConcreteHistory> myHistoryDB = new ArrayList<KMADConcreteHistory>();

    private static ArrayList<StackStateModel> myHistoryStateSimulation = new ArrayList<StackStateModel>();

    private static int cursorUndo;

    // **
    // ** Concrete Object History part
    // **
    public static void removeExpressHistoryAt(int indice) {
	myHistoryDB.remove(indice);
    }

    public static void removeAllExpressHistory() {
	myHistoryDB.clear();
    }

    public static int getHistorySize() {
	return myHistoryDB.size();
    }

    public static boolean saveConcreteHistory() {
	Object[] coTab = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "ObjetConcret");

	KMADConcreteHistory refHistory = new KMADConcreteHistory(coTab);
	myHistoryDB.add(0, refHistory);
	return true;
    }

    public static boolean loadConcreteHistoryAt(int p) {
	// Remove from ExpressDataBase
	ExpressHistory.removeConcreteObject();
	// Remove from Abstract object
	ExpressHistory.removeLinkBetweenConcreteWithAbstract();
	// Link conrete objects with abstract objects
	ExpressHistory.linkConcreteWithAbstract(myHistoryDB.get(p));
	return true;
    }

    private static void removeConcreteObject() {
	Object[] tabCon = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "ObjetConcret");

	for (int i = 0; i < tabCon.length; i++) {
	    ObjetConcret occurrent = (ObjetConcret) tabCon[i];
	    for (AttributConcret accurrent : occurrent.getInverseListAttribut()) {
		InterfaceExpressJava.remove(accurrent.getOid());
		InterfaceExpressJava.remove(accurrent.getValue().getOid());
	    }
	    InterfaceExpressJava.remove(occurrent.getOid());
	}
    }

    private static void removeLinkBetweenConcreteWithAbstract() {
	Object[] aoTab = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "ObjetAbstrait");
	for (int i = 0; i < aoTab.length; i++) {
	    ((ObjetAbstrait) aoTab[i]).removeAllConcreteObject();
	}

	Object[] gTab = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet",
		"Groupe");
	for (int i = 0; i < gTab.length; i++) {
	    ((Groupe) gTab[i]).getEnsemble().removeAllConcreteObject();
	}

	Object[] aaTab = InterfaceExpressJava.prendreAllOidOfEntity(
		"metaobjet", "AttributAbstrait");
	for (int i = 0; i < aaTab.length; i++) {
	    ((AttributAbstrait) aaTab[i]).removeAllConcreteAttribut();
	}
    }

    private static void linkConcreteWithAbstract(KMADConcreteHistory refHistory) {
	ObjetConcret[] tabOC = refHistory.getNewHistoryClone();

	for (int i = 0; i < tabOC.length; i++) {
	    ObjetConcret current = tabOC[i];
	    current.getUtiliseParClass().addInverseObjConc(current);
	    current.getAppartientGroupe().getEnsemble().put(current);
	    // Put to ExpressDataBase
	    InterfaceExpressJava.bdd.mettre(current.getOid(), current);
	    for (AttributConcret accurrent : current.getInverseListAttribut()) {
		accurrent.getAttributAbsDe().setUtiliseParAttr(accurrent);
		// Put to ExpressDataBase
		InterfaceExpressJava.bdd.mettre(accurrent.getOid(), accurrent);
		InterfaceExpressJava.bdd.mettre(accurrent.getValue().getOid(),
			accurrent.getValue());
	    }
	}
    }

    // **
    // ** State Simulation History part
    // **
    public static void saveStateHistory(TokenSimulation p) {
	// All tasks from current task model
	ArrayList<Tache> cellModel = ExpressTask.getTasksFromExpress();
	// This list is used to store StateSimulation objects
	TreeMap<Oid, StackState> myStateSimulationCollection = new TreeMap<Oid, StackState>();

	for (Tache current : cellModel) {
	    try {
		StackState myNew = new StackState((StateSimulation) current
			.getStateSimulation().clone(), current
			.getIteExpression().getIterationVariant());
		myStateSimulationCollection.put(current.getOid(), myNew);
	    } catch (CloneNotSupportedException e) {
		e.printStackTrace();
	    }
	}

	if (cellModel.size() != myStateSimulationCollection.size()) {
	    System.out.println("Problème IO");
	}

	for (int i = 0; i < cursorUndo; i++) {
	    myHistoryStateSimulation.remove(0);
	    ExpressHistory.removeExpressHistoryAt(0);
	}

	if (!ExpressHistory.saveConcreteHistory()) {
	    return;
	}

	myHistoryStateSimulation.add(0, new StackStateModel(
		myStateSimulationCollection, p));
	cursorUndo = 0;
    }

    private static void loadStateHistoryAt(int indice) {
	ArrayList<Tache> cellModel = ExpressTask.getTasksFromExpress();
	TreeMap<Oid, StackState> temp = myHistoryStateSimulation.get(indice)
		.getStateSimulationList();

	for (Tache current : cellModel) {
	    current.setStateSimulation(temp.get(current.getOid())
		    .getStateSimulation());
	    // Au cas où il s'agit d'une action Execute et que l'itération est
	    // un variant
	    if (current.getIteExpression().isVariableExpressionNode()) {
		current.getIteExpression().setIterationVariant(
			temp.get(current.getOid()).getVariant());
	    }
	}
	ExpressHistory.loadConcreteHistoryAt(indice);
    }

    public static void undoStateHistory() {
	if (myHistoryStateSimulation.size() == 0)
	    return;

	if (cursorUndo < myHistoryStateSimulation.size()) {
	    ExpressHistory.loadStateHistoryAt(cursorUndo);
	    cursorUndo++;
	}
    }

    public static boolean isUndoAble() {
	return cursorUndo < myHistoryStateSimulation.size();
    }

    public static void initStateHistory() {
	myHistoryStateSimulation = new ArrayList<StackStateModel>();
	cursorUndo = 0;
    }

    public static int getHistoricSize() {
	return myHistoryStateSimulation.size();
    }

    public static int getHistoricCursor() {
	return cursorUndo;
    }

    public static int getHistoricUndoLength() {
	return myHistoryStateSimulation.size() - cursorUndo >= 0 ? myHistoryStateSimulation
		.size() - cursorUndo
		: 0;
    }
}

package kmade.kmade.coreadaptator;

import java.util.ArrayList;
import java.util.TreeMap;

import kmade.kmade.coreadaptator.simulation.StackState;
import kmade.kmade.coreadaptator.simulation.StackStateModel;
import kmade.kmade.coreadaptator.simulation.TokenSimulation;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.KMADConcreteHistory;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.AttributConcret;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.tache.StateSimulation;
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
        Object[] coTab = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetConcret");
        
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
    	Object[] tabCon = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetConcret");
    	
		for (int i = 0 ; i < tabCon.length ; i++) {
			ObjetConcret occurrent = (ObjetConcret)tabCon[i];
			for (AttributConcret accurrent : occurrent.getInverseListAttribut()) {
				InterfaceExpressJava.remove(accurrent.getOid());
				InterfaceExpressJava.remove(accurrent.getValue().getOid());
			}	
			InterfaceExpressJava.remove(occurrent.getOid());
		}
	}
    
    private static void removeLinkBetweenConcreteWithAbstract() {
    	Object[] aoTab = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "ObjetAbstrait");
    	for (int i = 0 ; i < aoTab.length ; i++) {
    		((ObjetAbstrait)aoTab[i]).removeAllConcreteObject();
    	}
    	
    	Object[] gTab = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "Groupe");
    	for (int i = 0 ; i < gTab.length ; i++) {
    		((Groupe)gTab[i]).getEnsemble().removeAllConcreteObject();
    	}    	
    	
    	Object[] aaTab = InterfaceExpressJava.prendreAllOidOfEntity("metaobjet", "AttributAbstrait");
    	for (int i = 0 ; i < aaTab.length ; i++) {
    		((AttributAbstrait)aaTab[i]).removeAllConcreteAttribut();
    	}    	    	
    }
    
    private static void linkConcreteWithAbstract(KMADConcreteHistory refHistory) {   
    	ObjetConcret[] tabOC = refHistory.getNewHistoryClone();
  	
    	for (int i = 0 ; i < tabOC.length ; i++) {
        	ObjetConcret current = tabOC[i];
    		current.getUtiliseParClass().addInverseObjConc(current);
    		current.getAppartientGroupe().getEnsemble().put(current);
    		// Put to ExpressDataBase
    		InterfaceExpressJava.bdd.mettre(current.getOid(), current);
    		for (AttributConcret accurrent : current.getInverseListAttribut()) {
    			accurrent.getAttributAbsDe().setUtiliseParAttr(accurrent);
        		// Put to ExpressDataBase
        		InterfaceExpressJava.bdd.mettre(accurrent.getOid(), accurrent);
        		InterfaceExpressJava.bdd.mettre(accurrent.getValue().getOid(), accurrent.getValue());
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
            	StackState myNew = new StackState((StateSimulation)current.getStateSimulation().clone(), current.getIteExpression().getIterationVariant());
                myStateSimulationCollection.put(current.getOid(), myNew);
            } catch (CloneNotSupportedException e) {
            	e.printStackTrace();
            }
        }

        if (cellModel.size() != myStateSimulationCollection.size()) {
            System.out.println("Problème IO");
        }
        
        for (int i = 0 ; i < cursorUndo; i++) {
            myHistoryStateSimulation.remove(0);
            ExpressHistory.removeExpressHistoryAt(0);
        }
        
    	if (!ExpressHistory.saveConcreteHistory()) {
    		return;
    	}
        
        myHistoryStateSimulation.add(0, new StackStateModel(myStateSimulationCollection,p));
        cursorUndo = 0;
    }
    
    private static void loadStateHistoryAt(int indice) {
    	ArrayList<Tache> cellModel = ExpressTask.getTasksFromExpress();        
    	TreeMap<Oid, StackState> temp = myHistoryStateSimulation.get(indice).getStateSimulationList();        
  
    	for (Tache current : cellModel) {
    		current.setStateSimulation(temp.get(current.getOid()).getStateSimulation());
    		// Au cas où il s'agit d'une action Execute et que l'itération est un variant
    		if (current.getIteExpression().isVariableExpressionNode()) {
    			current.getIteExpression().setIterationVariant(temp.get(current.getOid()).getVariant());
    		}
    	}        
    	ExpressHistory.loadConcreteHistoryAt(indice);
    }
    
    public static void undoStateHistory() {    	
    	if (myHistoryStateSimulation.size() == 0) 
    		return;

    	if (cursorUndo  < myHistoryStateSimulation.size()) { 
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
    	return myHistoryStateSimulation.size() - cursorUndo >= 0 ? myHistoryStateSimulation.size() - cursorUndo : 0; 
    }
}

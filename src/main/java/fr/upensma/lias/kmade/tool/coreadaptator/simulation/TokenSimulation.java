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
package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class TokenSimulation {

    protected Tache myTask;

    protected int action;

    public static final int EXECUTER = 0;

    public static final int PASSER = 1;

    public static final int INTERROMPRE = 2;

    public static final int REPRENDRE = 3;

    public static final int ABANDONNER = 4;

    public TokenSimulation(Tache ptask, int paction) {
	this.myTask = ptask;
	this.action = paction;
    }

    public Tache getTask() {
	return myTask;
    }

    public int getAction() {
	return action;
    }

    public boolean isExecuterAction() {
	return action == TokenSimulation.EXECUTER;
    }

    public boolean isPasserAction() {
	return action == TokenSimulation.PASSER;
    }

    public boolean isInterrompreAction() {
	return action == TokenSimulation.INTERROMPRE;
    }

    public boolean isReprendreAction() {
	return action == TokenSimulation.REPRENDRE;
    }

    public boolean isAbandonnerAction() {
	return action == TokenSimulation.ABANDONNER;
    }

    public String toString() {
	String actionName = "";
	switch (action) {
	case EXECUTER: {
	    actionName = KMADEConstant.EXECUTE_ACTION_SIMULATION_MESSAGE;
	    break;
	}
	case PASSER: {
	    actionName = KMADEConstant.PASS_ACTION_SIMULATION_MESSAGE;
	    break;
	}
	case INTERROMPRE: {
	    actionName = KMADEConstant.SUSPEND_ACTION_SIMULATION_MESSAGE;
	    break;
	}
	case REPRENDRE: {
	    actionName = KMADEConstant.RESUME_ACTION_SIMULATION_MESSAGE;
	    break;
	}
	case ABANDONNER: {
	    actionName = KMADEConstant.NO_RESUME_ACTION_SIMULATION_MESSAGE;
	    break;
	}
	}
	return (myTask != null ? myTask.getName() : "???") + " : Action("
		+ actionName + ")";
    }
}
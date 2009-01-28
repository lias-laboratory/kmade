package kmade.kmade.adaptatorFC.simulation;

import kmade.kmade.KMADEConstant;
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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
        switch(action) {
            case EXECUTER : {
                actionName = KMADEConstant.EXECUTE_ACTION_SIMULATION_MESSAGE; break;
            }
            case PASSER : {
                actionName = KMADEConstant.PASS_ACTION_SIMULATION_MESSAGE; break;
            }
            case INTERROMPRE : {
                actionName = KMADEConstant.SUSPEND_ACTION_SIMULATION_MESSAGE; break;
            }
            case REPRENDRE : {
                actionName = KMADEConstant.RESUME_ACTION_SIMULATION_MESSAGE; break;
            }
            case ABANDONNER : {
                actionName = KMADEConstant.NO_RESUME_ACTION_SIMULATION_MESSAGE; break;
            }
        }
        return (myTask != null ? myTask.getName() : "???") + " : Action(" + actionName + ")";
    }
}
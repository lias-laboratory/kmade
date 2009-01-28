package kmade.kmade.adaptatorFC.simulation;

import java.util.ArrayList;

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
public class TokenReplayScenarioSimulation extends TokenRecordScenarioSimulation {
    
	public static final int DONE_STATE = 0;
	
	public static final int NOT_DONE_STATE = 1;
	
	public static final int ERROR_STATE = 2;
	
    protected int state = 1;
	
    protected ArrayList<Tache> myPossibleTasks = new ArrayList<Tache>();
    
    protected boolean notFounded;
    
    public TokenReplayScenarioSimulation(Tache p, int paction) {
   		// La tâche trouvée est une tâche du modèle. Rien ne dit que c'est la bonne.
        super(p,paction);
        notFounded = (p == null);
    }
    
    public TokenReplayScenarioSimulation(Tache p) {
        this(p, -1);
        myPossibleTasks = new ArrayList<Tache>();
    }
    
    public TokenReplayScenarioSimulation() {
        this(null, -1);
        myPossibleTasks = new ArrayList<Tache>();
    }
    
    public TokenReplayScenarioSimulation(ArrayList<Tache> possibleTasks) {
        this(null, -1);
        myPossibleTasks = possibleTasks;
    }
    
    public boolean isNotFounded() {
        return notFounded;
    }
    
    public void setAction(int p) {
        this.action = p;
    }    
    
    public boolean isDoneState() {
        return this.state == TokenReplayScenarioSimulation.DONE_STATE;
    }
    
    public boolean isNotDoneState() {
        return this.state == TokenReplayScenarioSimulation.NOT_DONE_STATE;
    }
    
    public boolean isErrorState() {
        return this.state == TokenReplayScenarioSimulation.ERROR_STATE;
    }
    
    public void setToDoneState() {
        this.state = DONE_STATE;
    }
    
    public void setToNotDoneState() {
        this.state = NOT_DONE_STATE;
    }
    
    public void setToErrorState() {
        this.state = ERROR_STATE;
    }
    
    /**
     * Cette méthode permet de vérifier parmi la liste donnée s'il existe une tâche
     * identique (Lia liste correspond à la liste des tâches actionables).
     * @param p
     * @return
     */
    public boolean isActionable(ArrayList<Tache> p) {
    	for (Tache current : p) {
    		if (current.getOid().get().equals(myTask.getOid().get())) {
    			return true;
    		}
    	}
    	return false;
    }
}

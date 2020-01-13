/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;

/**
 * @author Mickael BARON
 */
public class TokenReplayScenarioSimulation extends TokenRecordScenarioSimulation {

	public static final int DONE_STATE = 0;

	public static final int NOT_DONE_STATE = 1;

	public static final int ERROR_STATE = 2;

	protected int state = 1;

	protected ArrayList<Task> myPossibleTasks = new ArrayList<Task>();

	protected boolean notFounded;

	public TokenReplayScenarioSimulation(Task p, int paction) {
		// La tâche trouvée est une tâche du modèle. Rien ne dit que c'est la
		// bonne.
		super(p, paction);
		notFounded = (p == null);
	}

	public TokenReplayScenarioSimulation(Task p) {
		this(p, -1);
		myPossibleTasks = new ArrayList<Task>();
	}

	public TokenReplayScenarioSimulation() {
		this(null, -1);
		myPossibleTasks = new ArrayList<Task>();
	}

	public TokenReplayScenarioSimulation(ArrayList<Task> possibleTasks) {
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
	 * 
	 * @param p
	 * @return
	 */
	public boolean isActionable(ArrayList<Task> p) {
		for (Task current : p) {
			if (current.getOid().get().equals(myTask.getOid().get())) {
				return true;
			}
		}
		return false;
	}
}

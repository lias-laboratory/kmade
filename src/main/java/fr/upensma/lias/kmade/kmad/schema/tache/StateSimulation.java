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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.io.Serializable;

/**
 * @author Mickael BARON
 */
public class StateSimulation implements Serializable, Cloneable {

    private static final long serialVersionUID = -6961942809061136142L;

    public static final int NOT_ACCESSIBLE = 0;

    public static final int PASSIVE = 1;

    public static final int ACTIVE = 2;

    public static final int EXECUTE = 3;

    public static final int FINISHED = 4;

    public static final int PASSED = 5;

    public static final int SUSPENDED = 6;

    public static final int NO_RESUMED = 7;

    private int state = StateSimulation.NOT_ACCESSIBLE;

    private boolean isSuspendedSubTask = false;

    private int saveStateBeforeSuspendState = StateSimulation.NOT_ACCESSIBLE;

    public boolean resumeTag = false;

    public StateSimulation(int pState, boolean pIsSuspended, int pSaveState) {
	this.state = pState;
	this.isSuspendedSubTask = pIsSuspended;
	this.saveStateBeforeSuspendState = pSaveState;
    }

    public StateSimulation() {
	state = StateSimulation.NOT_ACCESSIBLE;
	isSuspendedSubTask = false;
	saveStateBeforeSuspendState = StateSimulation.NOT_ACCESSIBLE;
    }

    public int getStateBeforeSuspendState() {
	return saveStateBeforeSuspendState;
    }

    public boolean isSuspendedSubTask() {
	return this.isSuspendedSubTask;
    }

    public void setSuspendedSubTaskOff() {
	this.isSuspendedSubTask = false;
    }

    public void setSuspendedSubTaskOn() {
	this.isSuspendedSubTask = true;
    }

    public boolean isNotAccessible() {
	return state == StateSimulation.NOT_ACCESSIBLE;
    }

    public boolean isPassive() {
	return state == StateSimulation.PASSIVE;
    }

    public boolean isActive() {
	return state == StateSimulation.ACTIVE;
    }

    public boolean isExecute() {
	return state == StateSimulation.EXECUTE;
    }

    public boolean isFinished() {
	return state == StateSimulation.FINISHED;
    }

    public boolean isPassed() {
	return state == StateSimulation.PASSED;
    }

    public boolean isSuspended() {
	return state == StateSimulation.SUSPENDED;
    }

    public boolean isNoResumed() {
	return state == StateSimulation.NO_RESUMED;
    }

    public boolean isTerminalState() {
	return (this.isPassed() || this.isNoResumed() || this.isFinished());
    }

    public boolean isIntermediateState() {
	return (this.isPassive() || this.isActive() || this.isExecute() || this
		.isSuspended());
    }

    public void setNotAccessible() {
	this.state = StateSimulation.NOT_ACCESSIBLE;
	this.isSuspendedSubTask = false;
	this.resumeTag = false;
    }

    public void setPassive() {
	this.state = StateSimulation.PASSIVE;
    }

    public void setActive() {
	this.state = StateSimulation.ACTIVE;
    }

    public void setExecute() {
	this.state = StateSimulation.EXECUTE;
    }

    public void setFinished() {
	this.state = StateSimulation.FINISHED;
    }

    public void setPassed() {
	this.state = StateSimulation.PASSED;
    }

    public void setSuspended() {
	this.saveStateBeforeSuspendState = this.state;
	this.state = StateSimulation.SUSPENDED;
    }

    public void setResume() {
	this.state = this.saveStateBeforeSuspendState;
	this.setSuspendedSubTaskOff();
    }

    public void setNoResumed() {
	this.state = StateSimulation.NO_RESUMED;
	this.setSuspendedSubTaskOff();
    }

    public int getState() {
	return this.state;
    }

    public String toString() {
	return state + " " + isSuspendedSubTask + " "
		+ saveStateBeforeSuspendState + " " + resumeTag;
    }

    public Object clone() throws CloneNotSupportedException {
	return new StateSimulation(state, isSuspendedSubTask,
		saveStateBeforeSuspendState);
    }
}

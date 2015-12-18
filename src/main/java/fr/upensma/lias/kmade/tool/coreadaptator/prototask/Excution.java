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
package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;

/**
 * @author Thomas LACHAUME
 */
public class Excution {

	public static void doTask(Task t) throws ProtoTaskException {
		if (t.getStateExecution() == StateExecution.ACTIVABLE
				|| t.getStateExecution() == StateExecution.ACTIVE) {
			t.setStateExecution(StateExecution.ACTIVE);
			takeCareOfMyChildren(t);
			if (t.getMother() != null)
				oneOfMyChildrenBecommeActive(t.getMother());
		} else {
			throw new ProtoTaskException();
		}
	}

	public void cancelTask(Task t) throws ProtoTaskException {
	}

	public void suspendTask(Task t) throws ProtoTaskException {
	}

	/**
	 * @param t
	 */
	private static void oneOfMyChildrenBecommeActive(Task t)
			throws ProtoTaskException {

		// je regarde mon ordonnancement et je met à jours mes filles si besoin
		// et je me met en attente si besoin
		Decomposition decompo = t.getOrdering();
		ArrayList<Task> enfants;
		switch (decompo) {
		case ALT:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case ACTIVE:
				case ATTENTETASK:
					break;
				default:
					fille.setStateExecution(StateExecution.PASSEE);
				}
			}
			break;
		case ELE:
			throw new ProtoTaskException();
		case ET:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					fille.setStateExecution(StateExecution.ATTENTETASK);
					break;
				default:
					break;
				}
			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			boolean canStartAChild = false;
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					canStartAChild = true;
					break;
				default:
					break;
				}
			}
			if (!canStartAChild) {
				t.setStateExecution(StateExecution.ATTENTETASK);
			}
			break;
		case SEQ:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			int i= 0;
			for (Task child : enfants){
				if(child.getStateExecution()==StateExecution.ACTIVE || child.getStateExecution()==StateExecution.ATTENTEFIN || child.getStateExecution()==StateExecution.ATTENTEFINKO){
					i=enfants.indexOf(child);
				}
			}
			for(int j=0;j<i;j++){
				if(!(enfants.get(j).getStateExecution()==StateExecution.FINISHED || enfants.get(j).getStateExecution()==StateExecution.PASSEE))
					enfants.get(j).setStateExecution(StateExecution.PASSEE);

			}
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					fille.setStateExecution(StateExecution.ATTENTETASK);
					break;
				default:
					break;
				}
			}
			break;
		default:
			break;
		}
	}

	private static void takeCareOfMyChildren(Task t) throws ProtoTaskException {
		Decomposition decompo = t.getOrdering();
		ArrayList<Task> children;
		switch (decompo) {
		case ALT:
			children = t.getChildren();
			for (Task child : children) {
				if (child.getStateExecution() == null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch (child.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
				case ATTENTETASK:
				case INACTIVE:
					iCanBeActivable(child);
					break;
				case FINISHED:
					waitEndTask(t);
					break;
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					break;
					// throw new ProtoTaskException();
				}

			}
			break;
		case ELE:
			waitEndTask(t);
			break;
		case ET:
			children = t.getChildren();
			boolean oneActivable = false;
			for (Task child : children) {
				if (child.getStateExecution() == null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch (child.getStateExecution()) {
				case ATTENTETASK:
				case ACTIVABLE:
				case INACTIVABLE:
				case INACTIVE:
					iCanBeActivable(child);
					oneActivable = true;

					break;
				case FINISHED:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					
				}

			}
			if (!oneActivable) {
				waitEndTask(t);
			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			children = t.getChildren();
			for (Task child : children) {
				if (child.getStateExecution() == null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch (child.getStateExecution()) {

				case ACTIVABLE:
				case INACTIVABLE:
				case ATTENTETASK:
				case INACTIVE:
					iCanBeActivable(child);
					break;
				case FINISHED:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					// throw new ProtoTaskException();
					break;
				}

			}
			break;
		case SEQ:
			children = t.getChildren();
			// permet de dire si la première tache non optionnel a été trouvé
			boolean first = false;
			for (Task child : children) {
				if (child.getStateExecution() == null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch (child.getStateExecution()) {
				case ATTENTETASK:
				case ACTIVABLE:
				case INACTIVABLE:
				case INACTIVE:
					if (!first) {
						iCanBeActivable(child);
						if (!child.getFacultatif()) {
							first = true;
						}
					}
					break;
				case FINISHED:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:

				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					break;
					// throw new ProtoTaskException();
				}

			}
			break;

		default:
			break;
		}
	}

	private static void iCanBeActivable(Task t) {
		// if the task has a condition

		if (t.getPreExpression().getProtoTaskConditionExpression() != null
				&& !t.getPreExpression().getProtoTaskConditionExpression()
				.getValue().getDescription().equals("")) {
			if (t.getPreExpression().getProtoTaskConditionExpression()
					.getValue().getCurrentValue() == StateCondition.TRUE) {
				t.setStateExecution(StateExecution.ACTIVABLE);
			} else {
				t.setStateExecution(StateExecution.INACTIVABLE);
			}
		} else {
			t.setStateExecution(StateExecution.ACTIVABLE);
		}
	}

	//
	private static void waitEndTask(Task t) {
		// faire la gestion de l'iteration

		if (t.getIterExpression().getProtoTaskConditionExpression() != null
				&& !t.getIterExpression().getProtoTaskConditionExpression()
				.getValue().getDescription().equals("")) {
			if (t.getIterExpression().getProtoTaskConditionExpression()
					.getValue().getCurrentValue() == StateCondition.TRUE) {
				t.setStateExecution(StateExecution.ATTENTEFINKO);
			} else {
				t.setStateExecution(StateExecution.ATTENTEFIN);
			}
		} else {
			t.setStateExecution(StateExecution.ATTENTEFIN);
		}

	}

	public static void changeCondition(ProtoTaskCondition exp,
			StateCondition state) {
		exp.setCurrentValue(state);
		testrafraichissementconditionmodele();
	}

	private static void testrafraichissementconditionmodele() {
		ArrayList<Task> active = getTaskInState(StateExecution.ACTIVE);
		ArrayList<Task> attentefin = getTaskInState(StateExecution.ATTENTEFIN);
		ArrayList<Task> attentefinKO = getTaskInState(StateExecution.ATTENTEFINKO);
		active.addAll(attentefin);
		active.addAll(attentefinKO);
		for (Task t : active) {
			try {
				takeCareOfMyChildren(t);
			} catch (ProtoTaskException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static ArrayList<Task> getTaskInState(StateExecution state) {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		ArrayList<Task> res = new ArrayList<Task>();
		for (Task tache : root) {
			res.addAll(getTaskInStateRec(state, new ArrayList<Task>(), tache));
		}

		return res;

	}

	private static ArrayList<Task> getTaskInStateRec(StateExecution state,
			ArrayList<Task> prev, Task t) {
		if (t.getStateExecution() == state) {
			prev.add(t);
		}
		for (Task fils : t.getChildren()) {
			prev = getTaskInStateRec(state, prev, fils);

		}
		return prev;
	}

	public static void interruptTask() throws ProtoTaskException {
		// TODO Auto-generated method stub

	}

	public static void repeatTask(Task t) throws ProtoTaskException {
		ArrayList<Task> children = t.getChildren();
		for (Task child : children) {
			child.setStateExecution(StateExecution.INACTIVE);
		}
		t.setStateExecution(StateExecution.ACTIVE);
		doTask(t);
	}

	public static void endTask(Task t) throws ProtoTaskException {
		t.setStateExecution(StateExecution.FINISHED);
		Task mother = t.getMother();
		if (mother != null)
			oneOfMyChildrenBecomeFinish(mother);
		else {
			// TODO FIN SIMUL
			System.err.println("fin simu");
		}
	}

	private static void oneOfMyChildrenBecomeFinish(Task t)
			throws ProtoTaskException {
		Decomposition decompo = t.getOrdering();
		ArrayList<Task> enfants;
		switch (decompo) {
		case ALT:
			waitEndTask(t);
			break;
		case ELE:
			throw new ProtoTaskException();
		case ET:
			// t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			int counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
				case ATTENTETASK:
					
					if (!fille.getFacultatif()) {
						counter++;
					}
					break;
				default:
					break;
				}
			}
			if (counter == 0) {
				waitEndTask(t);
				takeCareOfMyChildren(t);
			} else {
				t.setStateExecution(StateExecution.ACTIVE);
				doTask(t);
			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			boolean canStartAChild = false;
			enfants = t.getChildren();
			counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					canStartAChild = true;
					if (fille.getFacultatif())
						counter++;
					break;
				case FINISHED:
					counter++;
				default:
					break;
				}
			}
			if (counter == enfants.size()) {
				waitEndTask(t);
			} else if (!canStartAChild) {
				t.setStateExecution(StateExecution.ATTENTETASK);
			} else {
				doTask(t);
			}

			break;
		case SEQ:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
				case INACTIVE:
				case ATTENTETASK:
					if (!fille.getFacultatif()) {
						counter++;
					}
					break;
				default:
					break;
				}
			}
			if (counter == 0) {
				waitEndTask(t);
				takeCareOfMyChildren(t);
			} else {
				t.setStateExecution(StateExecution.ACTIVE);
				doTask(t);
			}
			break;
		default:
			break;
		}
	}

	public static void resetScenario() {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		setAllTaskState(StateExecution.INACTIVE);
		for (Task tache : root) {
			tache.setStateExecution(StateExecution.ACTIVABLE);
			try {
				Excution.doTask(tache);
			} catch (ProtoTaskException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void setAllTaskState(StateExecution state) {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		for (Task tache : root) {
			tache.setStateExecution(state);
			for (Task t : tache.getChildren()) {
				setStateRecu(t, state);
			}
		}
	}

	private static void setStateRecu(Task t, StateExecution state) {
		t.setStateExecution(state);
		for (Task f : t.getChildren()) {
			setStateRecu(f, state);
		}

	}
}

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
package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import fr.upensma.lias.kmade.kmad.schema.expression.ChoiceEnum;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Thomas LACHAUME
 */
public class PROTOExecution {

	private static Task currentTask;

	private static Task startTask;

	public static void startExecution() {
		PROTOHistoric.clearHisto();
		ArrayList<Task> selected = GraphicEditorAdaptator.getSelectedTasks();
		if (selected.size() == 1) {
			startTask = selected.get(0);
			setAllChildStateExecution(selected.get(0), StateExecution.INACTIVE);
			setCurrentTask(selected.get(0), true, null);
		} else {
			ArrayList<Task> root = ExpressTask.getRootTasks();
			if (root.size() == 1) {
				startTask = root.get(0);
				// normal execution with one mother task
				setAllChildStateExecution(root.get(0), StateExecution.INACTIVE);
				setCurrentTask(root.get(0), true, null);
			} else {
				// two or more mother tasks...
			}
		}
	}

	// modification of task state
	public static void setCurrentTask(Task t, boolean historic, HashMap<String, ChoiceEnum> map) {
		int reponse = 42;
		/*
		 * if(t.getPreExpression()!= null && t.getPreExpression().getDescription()!=null
		 * && !t.getPreExpression().getDescription().equals("")){ reponse =
		 * JOptionPane.showConfirmDialog(null,
		 * KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE +" : "+
		 * t.getPreExpression().getDescription(),KMADEConstant.
		 * PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE,
		 * JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
		 * 
		 * }
		 */
		switch (reponse) {
		case JOptionPane.YES_OPTION:
			PROTOHistoric.addPrecondition(t, true);
		case 42:
			currentTask = t;
			if (historic) {
				PROTOHistoric.addTask(t);
			}
			t.setStateExecution(StateExecution.ACTIVE);
			execution(map);
			break;
		case JOptionPane.NO_OPTION:
			PROTOHistoric.addPrecondition(t, false);
			break;
		default:

		}

	}

	private static void execution(HashMap<String, ChoiceEnum> map) {
		// update displayed task
		// update information
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setCurrentTask(currentTask);
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, false);

		switch (currentTask.getStateExecution()) {
		case INACTIVE:
			// never happen

		case PASSIVE:
			// never happen
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR);
			return;
		case ACTIVE:
			// depend of ordonnancement
			switch (currentTask.getOrdering()) {
			case ELE:
				// Elementary task have to change in WAIT_END state
				elementarycase(map);

				break;
			case SEQ:
				sequentialCase(map);
				break;
			case ALT:
				alternatifCase(map);
				break;
			case ET:
				noOrderCase(map);
				break;
			case PAR:
				// TODO now it's no order but need to change it
				noOrderCase(map);
				break;
			}
			break;
		case WAITEND:
			switch (currentTask.getOrdering()) {
			case SEQ:
				sequentialCase(map);
				break;
			case ALT:
				alternatifCase(map);
				break;
			case ET:
				noOrderCase(map);
			default:
			}
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, true);
			iteration(map);
			// TODO une t??che en attente de fin peut ??tre r??p??t?? si il y a lieu

			break;
		case FINISHED:
			// if the task is in finish state we need to go up in the tree
			if (currentTask.getOrdering() != Decomposition.ELE) {
				PROTOHistoric.addFinished(currentTask);
			}
			if (currentTask.getMother() != null && !currentTask.equals(startTask)) {
				currentTask = currentTask.getMother();
				execution(map);
			} else {
				// TODO display simulation end
				System.out.println("ProtoTask fini");
			}
			break;
		}

	}

	private static void iteration(HashMap<String, ChoiceEnum> map) {
		if ((currentTask.getIterExpression().getDescription() != null)
				&& !currentTask.getIterExpression().getDescription().equals("")
				&& (map != null && map.get(currentTask.getIterExpression().getDescription()) == ChoiceEnum.vrai)) {
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask, true,
					ChoiceEnum.vrai);
		} else {
			if ((currentTask.getIterExpression().getDescription() == null)
					|| currentTask.getIterExpression().getDescription().equals("")) {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask, false,
						ChoiceEnum.indeterminee);
			} else {
				ChoiceEnum choiceE;
				if (map == null || map.get(currentTask.getIterExpression().getDescription()) == null) {
					choiceE = ChoiceEnum.indeterminee;
				} else {
					choiceE = map.get(currentTask.getIterExpression().getDescription());
				}
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask, true,
						choiceE);
			}
		}

	}

	public static void setAllChildStateExecution(Task t, StateExecution s) {
		if (t.getChildren() != null) {
			ArrayList<Task> child = t.getChildren();
			for (Task tache : child) {
				tache.setStateExecution(s);
				setAllChildStateExecution(tache, s);
			}
		}
	}

	public static void finishedTask(Task t, HashMap<String, ChoiceEnum> map) {
		if (currentTask.equals(t)) {
			PROTOHistoric.remonter();
			currentTask.setStateExecution(StateExecution.FINISHED);
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, false);
			execution(map);
		} else {
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_END_ERROR + " : " + t.getName());
		}
	}

	private static void elementarycase(HashMap<String, ChoiceEnum> map) {
		currentTask.setStateExecution(StateExecution.WAITEND);
		execution(map);

	}

	private static void alternatifCase(HashMap<String, ChoiceEnum> map) {
		// si le choix a ??t?? fait la t??che ne peut que se terminer
		boolean choice = false;
		for (Task t : currentTask.getChildren()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				choice = true;
			}
		}
		int number = 1;
		boolean onefinished = false;
		for (Task t : currentTask.getChildren()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, false, true, number++,
						false, ChoiceEnum.vrai);
				onefinished = true;
			} else {
				if ((t.getPreExpression().getDescription() == null) || t.getPreExpression().getDescription().equals("")
						|| // (!(t.getPreExpression().getDescription()!=
						// null)
						// &&
						// !t.getPreExpression().getDescription().equals("")
						// &&
						(map != null && map.get(t.getPreExpression().getDescription()) == ChoiceEnum.vrai)) {
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,
							t.getStateExecution() != StateExecution.FINISHED, !choice, number++, true && !onefinished,
							ChoiceEnum.vrai);
				} else {
					ChoiceEnum choiceE;
					if (map == null || map.get(t.getPreExpression().getDescription()) == null) {
						choiceE = ChoiceEnum.indeterminee;
					} else {
						choiceE = map.get(t.getPreExpression().getDescription());
					}
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, !choice, false,
							number++, true && !onefinished, choiceE);
				}
			}
		}
		if (choice) {
			if (currentTask.getStateExecution() != StateExecution.WAITEND) {
				currentTask.setStateExecution(StateExecution.WAITEND);
				execution(map);
			}
		}
	}

	private static void sequentialCase(HashMap<String, ChoiceEnum> map) {
		boolean futurActiveFound = false;
		Task lastfinished = null;
		for (Task t : currentTask.getChildren()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				lastfinished = t;
			}
		}
		int number = 1;
		for (Task t : currentTask.getChildren()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				// il faut afficher cette sous t??che comme termin??e
				// faire affichage sous t??che non ex??cutable
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, false, true, number++,
						false, ChoiceEnum.vrai);
			} else {

				// si la t??che n'est pas termin??e il faut regarder si la
				// derni??re active lan??eable a ??t?? trouv??e
				if (futurActiveFound
						|| currentTask.getChildren().indexOf(lastfinished) > currentTask.getChildren().indexOf(t)) {
					// afficher la t??che comme non ??x??cutable
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, true, false,
							number++, false, ChoiceEnum.vrai);

				} else {
					// la t??che est ??x??cutable il faut l'affich?? pour pouvoir la
					// lancer
					if ((t.getPreExpression().getDescription() == null)
							|| t.getPreExpression().getDescription().equals("") || // (!(t.getPreExpression().getDescription()!=
																					// null) &&
																					// !t.getPreExpression().getDescription().equals("")
																					// &&
							(map != null && map.get(t.getPreExpression().getDescription()) == ChoiceEnum.vrai)) {
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, true, true,
								number++, true, ChoiceEnum.vrai);
					} else {
						ChoiceEnum choice;
						if (map == null || map.get(t.getPreExpression().getDescription()) == null) {
							choice = ChoiceEnum.indeterminee;
						} else {
							choice = map.get(t.getPreExpression().getDescription());
						}
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, true, false,
								number++, true, choice);

					}
					// faire appel fonction avec un parametre qui prend
					// l'optionnalit??
					if (!t.getFacultatif()) {
						// on ne met que futurActiveFound que si la t??che est
						// non optionnel
						futurActiveFound = true;
					}
				}
			}

		}
		if (!futurActiveFound && currentTask.getStateExecution() != StateExecution.WAITEND) {
			// la t??che peut se terminer s'il n'y a pas de futur active trouv??
			// affich?? terminaison possible
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution(map);
		}
	}

	private static void noOrderCase(HashMap<String, ChoiceEnum> map) {
		int nbFinished = 0;
		int number = 1;
		for (Task t : currentTask.getChildren()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				nbFinished++;
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, false, true, number++,
						false, ChoiceEnum.vrai);
			} else {
				if ((t.getPreExpression().getDescription() == null) || t.getPreExpression().getDescription().equals("")
						|| // (!(t.getPreExpression().getDescription()!=
						// null)
						// &&
						// !t.getPreExpression().getDescription().equals("")
						// &&
						(map != null && map.get(t.getPreExpression().getDescription()) == ChoiceEnum.vrai)) {
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, true, true,
							number++, true, ChoiceEnum.vrai);
				} else {
					ChoiceEnum choice;
					if (map == null || map.get(t.getPreExpression().getDescription()) == null) {
						choice = ChoiceEnum.indeterminee;
					} else {
						choice = map.get(t.getPreExpression().getDescription());
					}
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t, true, false,
							number++, true, choice);
				}
				if (t.getFacultatif()) {
					nbFinished++;
				}
			}
		}
		if (nbFinished == currentTask.getChildren().size()
				&& currentTask.getStateExecution() != StateExecution.WAITEND) {
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution(map);
		}
	}

	public static void repeatCurrentTask(HashMap<String, ChoiceEnum> map) {
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		PROTOHistoric.remonter();
		setCurrentTask(currentTask, true, map);
	}

	public static void cancelTask(HashMap<String, ChoiceEnum> map) {
		PROTOHistoric.deleteCurrentTask();
		currentTask.setStateExecution(StateExecution.INACTIVE);
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		setCurrentTask(currentTask.getMother(), false, map);
	}

	public static void updateCondition(HashMap<String, ChoiceEnum> map) {
		execution(map);
	}

}

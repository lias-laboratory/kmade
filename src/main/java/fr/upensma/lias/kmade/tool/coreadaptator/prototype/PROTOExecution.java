package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class PROTOExecution {

	private static Tache currentTask;

	public static void startExecution() {
		PROTOHistoric.clearHisto();
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		if (root.size() == 1) {
			// normal execution with one mother task
			setAllChildStateExecution(root.get(0), StateExecution.INACTIVE);
			setCurrentTask(root.get(0),true);
		} else {
			// two or more mother tasks...
		}
	}

	//modification of task state
	public static void setCurrentTask(Tache t, boolean historic) {
		int reponse = 42;
		if(t.getPreExpression().getDescription()!=null && !t.getPreExpression().getDescription().equals("")){
			reponse = JOptionPane.showConfirmDialog(null, KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE +" : "+ t.getPreExpression().getDescription(),KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);

		}
		switch(reponse){
		case JOptionPane.YES_OPTION :
			PROTOHistoric.addPrecondition(t,true);
		case 42 : 	
			currentTask = t;
			if(historic){
				PROTOHistoric.addTask(t);
			}
			t.setStateExecution(StateExecution.ACTIVE);
			execution();
			break;
		case JOptionPane.NO_OPTION :
			PROTOHistoric.addPrecondition(t,false);
			break;
		default:

		}

	}

	private static void execution() {
		// update displayed task
		// update information
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setCurrentTask(currentTask);
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, false);

		switch (currentTask.getStateExecution()) {
		case INACTIVE:
			//never happen

		case PASSIVE:
			//never happen
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR);
			return;
		case ACTIVE:
			// depend of ordonnancement
			switch (currentTask.getOrdonnancement()) {
			case ELE:
				// Elementary task have to change in WAIT_END state
				currentTask.setStateExecution(StateExecution.WAITEND);
				execution();
				break;
			case SEQ:
				sequentialCase();
				break;
			case ALT:
				alternatifCase();
				break;
			case ET:
				noOrderCase();
				break;
			case PAR:
				// TODO now it's no order but need to change it
				noOrderCase();
				break;
			}
			break;
		case WAITEND:
			switch (currentTask.getOrdonnancement()) {
			case SEQ:
				sequentialCase();
				break;

			case ALT:
				alternatifCase();
				break;
			case ET:
				noOrderCase();
			default:
			}
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, true);
			// TODO une tâche en attente de fin peut être répété si il y a lieu
			if( !(currentTask.getIteExpression().getDescription() != null && currentTask.getIteExpression().getDescription().equals("")) ){
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(true);
			}

			break;
		case FINISHED:
			// if the task is in finish state we need to go up in the tree
			if(currentTask.getOrdonnancement() != Decomposition.ELE){
				PROTOHistoric.addFinished(currentTask);
			}
			if (currentTask.getMotherTask() != null) {
				currentTask = currentTask.getMotherTask();
				execution();
			} else {
				// TODO display simulation end
			}
			break;
		}

	}

	public static void setAllChildStateExecution(Tache t, StateExecution s) {
		if (t.getFils() != null) {
			ArrayList<Tache> child = t.getFils();
			for (Tache tache : child) {
				tache.setStateExecution(s);
				setAllChildStateExecution(tache, s);
			}
		}
	}

	public static void finishedTask(Tache t) {
		if (currentTask.equals(t)) {
			PROTOHistoric.remonter();
			currentTask.setStateExecution(StateExecution.FINISHED);
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
			.setEnabledEnd(currentTask, false);
			execution();
		} else {
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_END_ERROR + " : "
					+ t.getName());
		}
	}

	private static void alternatifCase() {
		// si le choix a été fait la tâche ne peut que se terminer
		boolean choice = false;
		for (Tache t : currentTask.getFils()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				choice = true;
			}
		}
		int number =1;
		for (Tache t : currentTask.getFils()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
				.setExecutableTask(t,false,number++);
			} else {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
				.setExecutableTask(t, !choice,number++);
			}
		}
		if (choice) {
			if (currentTask.getStateExecution() != StateExecution.WAITEND) {
				currentTask.setStateExecution(StateExecution.WAITEND);
				execution();
			}
		}
	}

	private static void sequentialCase() {
		boolean futurActiveFound = false;
		Tache lastfinished = null;
		for(Tache t : currentTask.getFils()){
			if(t.getStateExecution() == StateExecution.FINISHED){
				lastfinished = t;
			}
		}
		int number = 1;
		for (Tache t : currentTask.getFils()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				// il faut afficher cette sous tâche comme terminée
				// faire affichage sous tâche non exécutable
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
				.setExecutableTask(t,false, number++);
			} else {

				// si la tâche n'est pas terminée il faut regarder si la
				// dernière active lançeable a été trouvée
				if (futurActiveFound || currentTask.getFils().indexOf(lastfinished)>currentTask.getFils().indexOf(t)) {
					// afficher la tâche comme non éxécutable
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
					.setExecutableTask(t,false,number++);

				} else {
					// la tâche est éxécutable il faut l'affiché pour pouvoir la
					// lancer
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
					.setExecutableTask(t, true,number++);
					// faire appel fonction avec un parametre qui prend
					// l'optionnalité
					if (!t.getFacultatif()) {
						// on ne met que futurActiveFound que si la tâche est
						// non optionnel
						futurActiveFound = true;
					}
				}
			}

		}
		if (!futurActiveFound
				&& currentTask.getStateExecution() != StateExecution.WAITEND) {
			// la tâche peut se terminer s'il n'y a pas de futur active trouvé
			// affiché terminaison possible
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution();
		}
	}

	private static void noOrderCase() {
		int nbFinished = 0;
		int number = 1;
		for (Tache t : currentTask.getFils()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				nbFinished++;
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
				.setExecutableTask(t,false,number++);
			} else {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
				.setExecutableTask(t, true,number++);
				if (t.getFacultatif()) {
					nbFinished++;
				}
			}
		}
		if (nbFinished == currentTask.getFils().size()
				&& currentTask.getStateExecution() != StateExecution.WAITEND) {
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution();
		}
	}

	public static void repeatCurrentTask() {
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		PROTOHistoric.remonter();

		setCurrentTask(currentTask,true);
	}

	public static void cancelTask() {

		PROTOHistoric.deleteCurrentTask();
		currentTask.setStateExecution(StateExecution.INACTIVE);
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		setCurrentTask(currentTask.getMotherTask(),false);
	}
}

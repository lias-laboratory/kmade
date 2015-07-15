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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.UndoableEditEvent;

import org.jgraph.graph.GraphUndoManager;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ExpressDB;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Frequence;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.Modality;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Adel GHAMNIA
 */
public class KMADEUndoRedoManager {

    /* AG */
    private GraphUndoManager undoManager;

    private KMADEGraph graph;

    /*
     * 2 piles contenant les objets traités, ex : un lien supprimé, une tâche
     * supprimée, renommée ....
     */
    private ArrayDeque<Object> objToUndo = new ArrayDeque<Object>();
    private ArrayDeque<Object> objToRedo = new ArrayDeque<Object>();

    /*
     * 2 piles indiquant l'opération (des strings) à faire : "Edge" pour lien,
     * "Name" pour renommer une tâche,....
     */
    public ArrayDeque<Object> whatUndo = new ArrayDeque<Object>();
    public ArrayDeque<Object> whatRedo = new ArrayDeque<Object>();

    public KMADEUndoRedoManager(KMADEGraph g) {
	setGraph(g);

	undoManager = new GraphUndoManager() {

	    /*
	     * une méthode qui écoute et indique chaque modification au niveau
	     * du JGraph
	     */
	    public void undoableEditHappened(UndoableEditEvent e) {

		super.undoableEditHappened(e);
		whatUndo.push("Mouvement");
		ExpressDB n;
		n = (ExpressDB) InterfaceExpressJava.bdd.clone();
		InterfaceExpressJava.toUndo.push(n);

		updateHistoryButtons();

	    }

	};

    }

    public GraphUndoManager getUndoManager() {

	return undoManager;
    }

    public void setUndoManager(GraphUndoManager undoManager) {
	this.undoManager = undoManager;
    }

    public KMADEGraph getGraph() {
	return graph;
    }

    public void setGraph(KMADEGraph graph) {
	this.graph = graph;
    }

    public void undo() {
	try {

	    Object undo = whatUndo.pop();

	    if (undo.equals("Name")) {

		Vector<Object> vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String name = (String) vec.remove(1); // L'ancien nom

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getName()); // le nom actuel

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		ExpressTask.setNameTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), name);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("But")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String but = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getGoal());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setButTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), but);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Label")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String label = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getLabelName());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setLabelTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), label);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Duration")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String duration = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getDuration());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setDurationTask(
			GraphicEditorAdaptator.getSelectedExpressTask(),
			duration);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Observation")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String obs = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getDescription());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setObservationTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), obs);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Executant")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		Executor ex = (Executor) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getExecutor());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setExecutingUserTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), ex);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Frequence")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		Frequence fr = (Frequence) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFrequency());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setFrequencyTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), fr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("FrequenceValue")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		String fr = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFrequencyValue());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setFrequencyValueTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), fr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Modalite")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		Modality mod = (Modality) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getModality());

		objToRedo.push(vec);
		whatRedo.push(undo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setModalityTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), mod);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Facultatif")) {

		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		boolean opt = (Boolean) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFacultatif());

		objToRedo.push(vec);
		whatRedo.push(undo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setOptionalTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), opt);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();
	    } else if (undo.equals("Importance")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		Importance imp = (Importance) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getImportance());

		objToRedo.push(vec);
		whatRedo.push(undo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setSignificantTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), imp);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Interruptible")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		boolean intr = (Boolean) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getInterruptible());

		objToRedo.push(vec);
		whatRedo.push(undo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setInterruptibleTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), intr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Decomposition")) {
		Vector vec = (Vector) objToUndo.pop();
		String oid = (String) vec.get(0);
		Decomposition dec = (Decomposition) vec.remove(1);

		Task task = ExpressTask.getTaskFromOid(oid);
		// vec.add(task.getDecomposition());
		vec.add(task.getOrdering());

		objToRedo.push(vec);
		whatRedo.push(undo);

		ExpressTask.setOperatorTask2(task, dec);

		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (undo.equals("Paste")) {

		Vector vec = (Vector) objToUndo.pop();
		objToRedo.push(vec);
		InterfaceExpressJava.toRedo
			.push((ExpressDB) InterfaceExpressJava.toUndo.peek()
				.clone());

		Task[] taskList = (Task[]) vec.get(1);
		ArrayList<String[]> edgeOIDList = new ArrayList<String[]>();
		for (int i = 0; i < taskList.length; i++) {
		    undoManager.undo();
		    InterfaceExpressJava.toRedo
			    .push(InterfaceExpressJava.toUndo.pop());
		    whatRedo.push(whatUndo.pop());

		    String[] couple = new String[2];
		    String oidFils = null;
		    String oidMere = null;

		    String oid = ((Task) taskList[i]).getOid().get();
		    // R�f�rence de la t�che m�re.
		    Task mere = ((Task) taskList[i]).getMother();
		    if (mere != null) {
			// OID de la t�che (
			oidFils = oid;
			// R�f�rence de la t�che m�re.
			oidMere = ((Task) taskList[i]).getMother().getOid()
				.get();

			couple[0] = oidMere;
			couple[1] = oidFils;
			edgeOIDList.add(couple);
		    }

		}

		for (int i = 0; i < edgeOIDList.size(); i++) {

		    undoManager.undo();

		    InterfaceExpressJava.toRedo
			    .push(InterfaceExpressJava.toUndo.pop());
		    whatRedo.push(whatUndo.pop());

		}

		InterfaceExpressJava.bdd = (ExpressDB) InterfaceExpressJava.toUndo
			.peek().clone();

		for (int i = 0; i < edgeOIDList.size(); i++) {

		    Task mere = null, fils = null;

		    for (int j = 0; j < taskList.length; j++) {
			if (edgeOIDList.get(i)[0].equals(taskList[j].getOid()
				.get()))
			    mere = (Task) taskList[j];
			if (edgeOIDList.get(i)[1].equals(taskList[j].getOid()
				.get()))
			    fils = (Task) taskList[j];

		    }
		    ExpressTask.removeEdge(mere, fils);

		}
		whatRedo.push(undo);

	    } else if (undo.equals("EdgeAndTaskRemove")) {

		Vector vec = (Vector) objToUndo.pop();
		objToRedo.push(vec);
		InterfaceExpressJava.toRedo
			.push((ExpressDB) InterfaceExpressJava.toUndo.peek()
				.clone());

		ArrayList<String[]> edgeOIDList = (ArrayList<String[]>) vec
			.get(1);

		ArrayList<Task> taskList = (ArrayList<Task>) vec.get(0);

		for (int i = 0; i < taskList.size(); i++) {
		    undoManager.undo();
		    InterfaceExpressJava.toRedo
			    .push(InterfaceExpressJava.toUndo.pop());
		    whatRedo.push(whatUndo.pop());

		}

		for (int i = 0; i < edgeOIDList.size(); i++) {
		    undoManager.undo();

		    InterfaceExpressJava.toRedo
			    .push(InterfaceExpressJava.toUndo.pop());
		    whatRedo.push(whatUndo.pop());

		    undoManager.undo();
		    whatRedo.push(whatUndo.pop());
		    InterfaceExpressJava.toRedo
			    .push(InterfaceExpressJava.toUndo.pop());

		}

		InterfaceExpressJava.bdd = (ExpressDB) InterfaceExpressJava.toUndo
			.peek().clone();

		for (int i = 0; i < edgeOIDList.size(); i++) {

		    KMADEDefaultGraphCell mere = GraphicEditorAdaptator
			    .getTaskModelPanel().getTask(edgeOIDList.get(i)[0]);
		    KMADEDefaultGraphCell fils = GraphicEditorAdaptator
			    .getTaskModelPanel().getTask(edgeOIDList.get(i)[1]);

		    Task mother = mere.getTask();
		    Task son = fils.getTask();

		    if (mother.getChildren().contains(son)) {

			mother.getChildren().remove(son);
		    }

		    ExpressTask.addNewEdge(mother, son);

		}
		whatRedo.push(undo);

	    }

	    else if (undo.equals("EdgeRemove")) {

		undoManager.undo();
		undoManager.undo();

		InterfaceExpressJava.toRedo.push(InterfaceExpressJava.toUndo
			.pop());
		InterfaceExpressJava.toRedo.push(InterfaceExpressJava.toUndo
			.pop());

		whatRedo.push(whatUndo.pop());
		whatRedo.push(whatUndo.pop());
		whatRedo.push(undo);

		Vector edgeToRemove = (Vector) objToUndo.pop();
		String oidMother = (String) edgeToRemove.get(0);
		String oidSon = (String) edgeToRemove.get(1);
		objToRedo.push(edgeToRemove);

		KMADEDefaultGraphCell mere = GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oidMother);
		KMADEDefaultGraphCell fils = GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oidSon);

		ExpressTask.addNewEdge(mere.getTask(), fils.getTask());

	    } else if (undo.equals("Edge")) {
		KMADEDefaultEdge edge = (KMADEDefaultEdge) objToUndo.pop();

		objToRedo.push(edge);
		whatRedo.push(undo);

		KMADEDefaultGraphCell motherCell = ((KMADEDefaultEdge) edge)
			.getMotherCell();
		// Suppression dans Express du lien.
		ExpressTask.removeEdge(motherCell.getTask(),
			((KMADEDefaultEdge) edge).getSonCell().getTask());
		whatUndo.pop();
		undoManager.undo();
		InterfaceExpressJava.toUndo.pop();
	    } else {

		whatRedo.push("Mouvement");
		ExpressDB bddToRedo;
		bddToRedo = InterfaceExpressJava.toUndo.pop();
		InterfaceExpressJava.toRedo.push(bddToRedo);

		ExpressDB bddToUndo = new ExpressDB();
		bddToUndo = InterfaceExpressJava.toUndo.peek();

		undoManager.undo();
		InterfaceExpressJava.bdd = (ExpressDB) bddToUndo.clone();
	    }
	} catch (Exception ex) {
	    undoManager.undo();
	    KMADEHistoryMessageManager.printlnError("no error message yet");

	} finally {
	    updateHistoryButtons();
	}
    }

    public void redo() {

	try {

	    Object Redo = whatRedo.pop();

	    if (Redo.equals("Name")) {

		Vector<Object> vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String name = (String) vec.remove(1);// l'ancien nom

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getName());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setNameTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), name);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("But")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String but = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getGoal());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setButTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), but);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Label")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String label = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getLabelName());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setLabelTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), label);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Duration")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String duration = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getDuration());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setDurationTask(
			GraphicEditorAdaptator.getSelectedExpressTask(),
			duration);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Observation")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String obs = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getDescription());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setObservationTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), obs);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Executant")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		Executor ex = (Executor) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getExecutor());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setExecutingUserTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), ex);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Frequence")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		Frequence fr = (Frequence) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFrequency());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setFrequencyTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), fr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("FrequenceValue")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		String fr = (String) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFrequencyValue());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setFrequencyValueTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), fr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Modalite")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		Modality mod = (Modality) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getModality());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		// Tache task = ExpressTask.getTaskFromOid(oid);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setModalityTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), mod);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Facultatif")) {

		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		boolean opt = (Boolean) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getFacultatif());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setOptionalTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), opt);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();
	    } else if (Redo.equals("Importance")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		Importance imp = (Importance) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getImportance());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setSignificantTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), imp);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Interruptible")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		boolean intr = (Boolean) vec.remove(1);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));

		vec.add(GraphicEditorAdaptator.getSelectedExpressTask()
			.getInterruptible());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		GraphicEditorAdaptator.setSelectedTask(GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oid));
		ExpressTask.setInterruptibleTask(
			GraphicEditorAdaptator.getSelectedExpressTask(), intr);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Decomposition")) {
		Vector vec = (Vector) objToRedo.pop();
		String oid = (String) vec.get(0);
		Decomposition dec = (Decomposition) vec.remove(1);

		/*
		 * GraphicEditorAdaptator.setSelectedTask(
		 * GraphicEditorAdaptator.getTaskModelPanel().getTask(oid) );
		 */
		Task task = ExpressTask.getTaskFromOid(oid);

		vec.add(task.getOrdering());

		objToUndo.push(vec);
		whatUndo.push(Redo);

		ExpressTask.setOperatorTask2(task, dec);
		GraphicEditorAdaptator.updateSelectedTaskGraphModel();

	    } else if (Redo.equals("Paste")) {

		Vector vec = (Vector) objToRedo.pop();
		objToUndo.push(vec);
		ExpressDB bddToUndo = (ExpressDB) InterfaceExpressJava.bdd
			.clone();

		InterfaceExpressJava.toUndo.push((ExpressDB) bddToUndo.clone());

		Task[] taskList = (Task[]) vec.get(1);
		ArrayList<String[]> edgeOIDList = new ArrayList<String[]>();

		for (int i = 0; i < taskList.length; i++) {

		    InterfaceExpressJava.toUndo
			    .push(InterfaceExpressJava.toRedo.pop());
		    whatUndo.push(whatRedo.pop());

		}

		InterfaceExpressJava.toUndo.pop();
		for (int i = 0; i < edgeOIDList.size(); i++) {

		    InterfaceExpressJava.toUndo
			    .push(InterfaceExpressJava.toRedo.pop());
		    whatUndo.push(whatRedo.pop());

		}
		InterfaceExpressJava.bdd = (ExpressDB) InterfaceExpressJava.toRedo
			.pop().clone();

		for (int i = 0; i < edgeOIDList.size(); i++) {

		    Task mere = null, fils = null;

		    for (int j = 0; j < taskList.length; j++) {
			if (edgeOIDList.get(i)[0].equals(taskList[j].getOid()
				.get()))
			    mere = (Task) taskList[j];
			if (edgeOIDList.get(i)[1].equals(taskList[j].getOid()
				.get()))
			    fils = (Task) taskList[j];

		    }
		    ExpressTask.removeEdge(mere, fils);

		}
		for (int i = 0; i < taskList.length; i++)
		    undoManager.redo();

		for (int i = 0; i < edgeOIDList.size(); i++) {
		    undoManager.redo();
		    undoManager.redo();
		}

		whatUndo.push(Redo);
	    }

	    else if (Redo.equals("EdgeAndTaskRemove")) {

		Vector vec = (Vector) objToRedo.pop();
		objToUndo.push(vec);
		ExpressDB bddToUndo = (ExpressDB) InterfaceExpressJava.bdd
			.clone();

		InterfaceExpressJava.toUndo.push((ExpressDB) bddToUndo.clone());

		ArrayList<String[]> edgeOIDList = (ArrayList<String[]>) vec
			.get(1);

		ArrayList<Task> taskList = (ArrayList<Task>) vec.get(0);

		for (int i = 0; i < taskList.size(); i++) {

		    InterfaceExpressJava.toUndo
			    .push(InterfaceExpressJava.toRedo.pop());
		    whatUndo.push(whatRedo.pop());

		}

		InterfaceExpressJava.toUndo.pop();
		for (int i = 0; i < edgeOIDList.size(); i++) {

		    InterfaceExpressJava.toUndo
			    .push(InterfaceExpressJava.toRedo.pop());
		    whatUndo.push(whatRedo.pop());

		    whatUndo.push(whatRedo.pop());
		    InterfaceExpressJava.toUndo
			    .push(InterfaceExpressJava.toRedo.pop());

		}
		InterfaceExpressJava.bdd = (ExpressDB) InterfaceExpressJava.toRedo
			.pop().clone();

		for (int i = 0; i < edgeOIDList.size(); i++) {

		    KMADEDefaultGraphCell mere = GraphicEditorAdaptator
			    .getTaskModelPanel().getTask(edgeOIDList.get(i)[0]);
		    KMADEDefaultGraphCell fils = GraphicEditorAdaptator
			    .getTaskModelPanel().getTask(edgeOIDList.get(i)[1]);

		    Task mother = mere.getTask();
		    Task son = fils.getTask();

		    if (mother.getChildren().contains(son)) {

			mother.getChildren().remove(son);
		    }

		    ExpressTask.removeEdge(mother, son);

		}
		for (int i = 0; i < taskList.size(); i++)
		    undoManager.redo();

		for (int i = 0; i < edgeOIDList.size(); i++) {
		    undoManager.redo();
		    undoManager.redo();
		}

		whatUndo.push(Redo);
	    }

	    else if (Redo.equals("EdgeRemove")) {

		undoManager.redo();
		undoManager.redo();
		InterfaceExpressJava.toUndo.push(InterfaceExpressJava.toRedo
			.pop());
		InterfaceExpressJava.toUndo.push(InterfaceExpressJava.toRedo
			.pop());

		whatUndo.push(whatRedo.pop());
		whatUndo.push(whatRedo.pop());
		whatUndo.push(Redo);

		Vector edgeToRemove = (Vector) objToRedo.pop();
		String oidMother = (String) edgeToRemove.get(0);
		String oidSon = (String) edgeToRemove.get(1);
		objToUndo.push(edgeToRemove);

		KMADEDefaultGraphCell mere = GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oidMother);
		KMADEDefaultGraphCell fils = GraphicEditorAdaptator
			.getTaskModelPanel().getTask(oidSon);

		ExpressTask.removeEdge(mere.getTask(), fils.getTask());
	    } else if (Redo.equals("Edge")) {
		whatUndo.push("Mouvement");

		ExpressDB n;
		n = (ExpressDB) InterfaceExpressJava.bdd.clone();
		InterfaceExpressJava.toUndo.push(n);

		undoManager.redo();
		KMADEDefaultEdge edge = (KMADEDefaultEdge) objToRedo.pop();

		// objToUndo.push(edge);
		// whatUndo.push(Redo);
		KMADEDefaultGraphCell motherCell = ((KMADEDefaultEdge) edge)
			.getMotherCell();

		// Suppression dans Express du lien.

		ExpressTask.addNewEdge(motherCell.getTask(),
			((KMADEDefaultEdge) edge).getSonCell().getTask());

		objToUndo.push(edge);
		whatUndo.push(Redo);

	    }

	    else {
		whatUndo.push("Mouvement");
		ExpressDB bddToRedo;
		bddToRedo = InterfaceExpressJava.toRedo.pop();

		InterfaceExpressJava.toUndo.push((ExpressDB) bddToRedo.clone());

		InterfaceExpressJava.bdd = (ExpressDB) bddToRedo.clone();
		undoManager.redo();

	    }
	} catch (Exception ex) {
	    KMADEHistoryMessageManager.printlnError("no error message yet");
	} finally {
	    updateHistoryButtons();

	}
    }

    public boolean canRedo() {

	return undoManager.canRedo(graph.getGraphLayoutCache())
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals(
			"Decomposition"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Name"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Executant"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals(
			"Interruptible"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Frequence"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Facultatif"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Durations"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("But"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals(
			"FrequenceValue"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Label"))
		|| (!whatRedo.isEmpty() && whatRedo.peek().equals("Modalite"));
    }

    // Update Undo/Redo Button State based on Undo Manager
    protected void updateHistoryButtons() {
	// The View Argument Defines the Context

	/* AG */
	GraphicEditorAdaptator
		.getMainFrame()
		.getApplicationToolBar()
		.setUndoActionViewState(
			undoManager.canUndo(graph.getGraphLayoutCache()));
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.setRedoActionViewState(canRedo());
	// KMADEMainFrame.getAPPLICATION_TOOL_BAR().setRedoActionViewState(what);
    }

    public ArrayDeque<Object> getObjToRedo() {

	return objToRedo;
    }

    public void setObjToRedo(ArrayDeque<Object> objToRedo) {
	this.objToRedo = objToRedo;
    }

    public ArrayDeque<Object> getObjToUndo() {
	return objToUndo;
    }

    public void setObjToUndo(ArrayDeque<Object> objToUndo) {
	this.objToUndo = objToUndo;
    }

    /* Pour initialiser toutes les piles, utile après le chargement */

    public void clearAllUndoRedoDeque() {

	whatRedo.clear();
	whatUndo.clear();

	objToRedo.clear();
	objToUndo.clear();

	InterfaceExpressJava.toRedo.clear();

	while (InterfaceExpressJava.toUndo.size() > 1) {
	    InterfaceExpressJava.toUndo.pop();
	}
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.setUndoActionViewState(false);
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.setRedoActionViewState(false);

    }

}

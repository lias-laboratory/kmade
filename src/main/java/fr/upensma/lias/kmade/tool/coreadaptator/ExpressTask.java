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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Actor;
import fr.upensma.lias.kmade.kmad.schema.tache.ActorSystem;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.SideEffectExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Event;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Frequence;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.IterExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Media;
import fr.upensma.lias.kmade.kmad.schema.tache.Modality;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.kmad.schema.tache.PreExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEObservable;

/**
 * @author Mickael BARON
 */
public final class ExpressTask {

    private static int counterClipboard = 0;
    
    private static KMADEObservable expressTaskObservable = new KMADEObservable();

    public static void addObserver(Observer o) {
	expressTaskObservable.addObserver(o);
    }

    public static void notifyObservers() {
	expressTaskObservable.notifyKMADEObserver();
    }

    public static void notifyObservers(Object argv) {
	expressTaskObservable.notifyKMADEObserver(argv);
    }

    public static ArrayList<Task> getRootTasks() {
	Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.TASK_CLASS);
	ArrayList<Task> rootTasks = new ArrayList<Task>();
	for (int i = 0; i < taches.length; i++) {
	    if (((Task) taches[i]).isRoot()) {
		rootTasks.add((Task) taches[i]);
	    }
	}
	return rootTasks;
    }

    public static Task getTaskFromOid(String oid) {
	if (oid.equals("")) {
	    return null;
	}
	Object toto = InterfaceExpressJava.prendre(new Oid(oid));

	// On teste s'il existe une entité avec un Oid équivalent.
	if (toto == null) {
	    return null;
	}
	// On teste pour savoir s'il s'agit bien d'un objet de type tâche.
	try {
	    return (Task) toto;
	} catch (Exception e) {
	    return null;
	}
    }

    public static ArrayList<Task> getTasksFromName(String name) {
	ArrayList<Task> myTaskName = new ArrayList<Task>();
	Object[] toto = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.TASK_CLASS);

	for (int i = 0; i < toto.length; i++) {
	    if (((Task) toto[i]).equals(name)) {
		myTaskName.add((Task) toto[i]);
	    }
	}

	return myTaskName;
    }

    public static void clearPreExpression(Task pTache) {
	pTache.setPreExpression(new PreExpression());
	notifyObservers();
    }

    public static void clearEffetsDeBordExpression(Task pTache) {
	pTache.setSideEffetExpression(new SideEffectExpression());
	notifyObservers();
    }

    public static void clearIterExpression(Task pTache) {
	pTache.setIterExpression(new IterExpression());
	notifyObservers();
    }

    /**
     * Méthode qui permet de retourner toutes les tâches du modèle Express.
     */
    public static Task[] getAllTaskFromExpress() {
	Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.TASK_CLASS);
	Task[] myNewTask = new Task[taches.length];
	for (int i = 0; i < taches.length; i++) {
	    myNewTask[i] = (Task) taches[i];
	}
	return myNewTask;
    }

    public static ArrayList<Task> getTasksFromExpress() {
	Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
		ExpressConstant.TASK_CLASS);
	ArrayList<Task> toto = new ArrayList<Task>();
	for (int i = 0; i < taches.length; i++) {
	    toto.add((Task) taches[i]);
	}
	return toto;
    }

    /**
     * Cette methode permet de coller des elements du ClipBoard dans la base
     * Express.
     */
    public static Task[] pasteElementsFromClipBoard(java.awt.Point pt) {
	counterClipboard++;
	Object[] myTaskList = InterfaceExpressJava
		.getAllReferencesOfEntityFromClipBoard(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.TASK_CLASS);

	Task[] myNewTaskList = new Task[myTaskList.length];
	// PG: correction de l'anomalie "NewTache dans la classe Tache
	HashMap<Task, Task> myNewTasks = new HashMap<Task, Task>();
	
	// KMADEHistoryMessageManager.printMessage("nb tache arbre debut" + myTaskList.length);

	for (int i = 0; i < myTaskList.length; i++) {
	    // KMADEHistoryMessageManager.printMessage("Collage a partir du PP2 " + myTaskList[i]);
	    // On cree un point et une tache.
	    Point refPoint = (Point) InterfaceExpressJava
		    .createEntityReferenceBack(ExpressConstant.CORE_PACKAGE,ExpressConstant.POINT_CLASS);
	    Task refTache = (Task) InterfaceExpressJava
		    .createEntityReferenceBack(ExpressConstant.CORE_PACKAGE,ExpressConstant.TASK_CLASS);

	    if (pt != null) {
		refPoint.setX(pt.x);
		refPoint.setY(pt.y);
	    } else {
		// On pioche.
		Integer x = ((Task) myTaskList[i]).getPoint().getX();
		Integer y = ((Task) myTaskList[i]).getPoint().getY();

		// On modifie le contenu.
		refPoint.setX(new Integer(x.intValue()
			+ ExpressTask.counterClipboard * 50));
		refPoint.setY(new Integer(y.intValue()
			+ ExpressTask.counterClipboard * 10));
	    }

	    refTache.setPoint(refPoint);

	    // To save a reference between my old task and my new task
	    myNewTasks.put((Task) myTaskList[i], refTache) ; 

	    /**********************************************************
	     * On effectue la relation Clipboard et modele Express. *
	     **********************************************************/
	    refTache.setName(((Task) myTaskList[i]).getName()); // nom de la
								 // tache
	    refTache.setOrdering(((Task) myTaskList[i])
		    .getOrdering()); // decomposition
	    refTache.setExecutor(((Task) myTaskList[i]).getExecutor()); // executant

	    // interruption
	    if (((Task) myTaskList[i]).getInterruptible() == true) {
		refTache.setInterruptible(((Task) myTaskList[i])
			.getInterruptible());
	    }

	    // Facultative
	    if (((Task) myTaskList[i]).getFacultatif() == true) {
		refTache.setOptional(((Task) myTaskList[i]).getFacultatif());
	    }

	    refTache.setPreExpression(((Task) myTaskList[i])
		    .getPreExpression()); // precondition
	    refTache.setSideEffetExpression(((Task) myTaskList[i])
		    .getEffetsDeBordExpression()); // effetsdebord
	    refTache.setIterExpression(((Task) myTaskList[i])
		    .getIterExpression()); // iteration

	    refTache.setActors(((Task) myTaskList[i]).getActors()); // acteurs
	    refTache.setEvents(((Task) myTaskList[i]).getEvents()); // evenements

	    myNewTaskList[i] = refTache;
	}

	// Links (mother and children) must be changed from clipboard tasks to new tasks
	for (int i = 0; i < myTaskList.length; i++) {
		ArrayList<Task> mySubTasks = ((Task) myTaskList[i]).getChildren();
		if (mySubTasks.size() != 0) {
			ArrayList<Task> myNewSubTasks = new ArrayList<Task>();
			for (int j = 0; j < mySubTasks.size(); j++) {
				// get my new task through the old one 
				Task myNewTask=myNewTasks.get(mySubTasks.get(j));
				myNewSubTasks.add(myNewTask);
				myNewTask.setMother(myNewTaskList[i]);
			}
			myNewTaskList[i].setChildren(myNewSubTasks);
		}
	}

	return myNewTaskList;
    }

    /**
     * Cette methode permet de copier des taches dans le presse-papier.
     * 
     * @param taskOIDList
     * @param edgeOIDList
     */
    public static Task[] copyElementsIntoClipBoard(
	    ArrayList<Task> myTacheList, ArrayList<String[]> edgeOIDList) {
	// On nettoie le presse-papier.
	counterClipboard = 0;
	InterfaceExpressJava.clearClipBoard();

	// Pour l'instant on fait une copie sans les autres attributs.
	// Y a au moins le point puisqu'il doit etre cree par Express
	Task[] myNewTaskList = new Task[myTacheList.size()];
		
	for (int i = 0; i < myTacheList.size(); i++) {
	    // On pioche.
	    Integer x = (myTacheList.get(i)).getPoint().getX();
	    Integer y = (myTacheList.get(i)).getPoint().getY();

	    // On cr��
	    Point refPoint = (Point) InterfaceExpressJava
		    .createEntityReferenceBackIntoClipBoard(ExpressConstant.CORE_PACKAGE,
					ExpressConstant.POINT_CLASS);
	    Task refTache = (Task) InterfaceExpressJava
		    .createEntityReferenceBackIntoClipBoard(ExpressConstant.CORE_PACKAGE,
		    		ExpressConstant.TASK_CLASS);

	    // On integre
	    refPoint.setX(new Integer(x.intValue()));
	    refPoint.setY(new Integer(y.intValue()));
	    refTache.setPoint(refPoint);

	    /*******************************************
	     * Les caracteristiques a copier.
	     *******************************************/
	    refTache.setName((myTacheList.get(i)).getName()); // nom
	    refTache.setExecutor((myTacheList.get(i)).getExecutor());// executant
	    refTache.setOrdering((myTacheList.get(i)).getOrdering());// decomposition
	    refTache.setInterruptible((myTacheList.get(i)).getInterruptible());// interruption
	    refTache.setOptional((myTacheList.get(i)).getFacultatif());// necessite

	    refTache.setPreExpression(myTacheList.get(i).getPreExpression());// precondition
	    refTache.setSideEffetExpression(myTacheList.get(i)
		    .getEffetsDeBordExpression());// effetsdebord
	    refTache.setIterExpression(myTacheList.get(i).getIterExpression());// iteration

	    refTache.setActors(myTacheList.get(i).getActors()); // acteur
	    refTache.setEvents(myTacheList.get(i).getEvents()); // evenements

	    /*****************
	     * objet
	     *****************/

	    myNewTaskList[i] = refTache;

	}

	// Traitement des liens.
	int i = 0;
	boolean[] motherTasksUsed = new boolean[myNewTaskList.length];
	
	while (edgeOIDList.size() != 0) {
	    String oldMotherTask = (edgeOIDList.get(i))[0];

	    boolean supprimee = false;
	    for (int j = 0; j < myNewTaskList.length; j++) {
			if (myTacheList.get(j).getOid()
			.equals(new Oid(oldMotherTask))
//			&& !myNewTaskList[j].isMotherUsed()) {
			&& !motherTasksUsed[j]) {

		    int l = 0;
		    while (l != edgeOIDList.size()) {
			// On parcours les OID des fils.
			if (oldMotherTask.equals(edgeOIDList.get(l)[0])) {
			    String oldSonTask = (edgeOIDList.get(l))[1];
			    boolean trouveeFils = false;
			    for (int k = 0; k < myNewTaskList.length
				    && !trouveeFils; k++) {
				if (k != j) { // Une tache mere ne peut etre son
					      // fils.
					if (myTacheList.get(k).getOid()
					    .equals(new Oid(oldSonTask))) {
					// Ici on a vérifié qu'il y a une
					// nouvelle tache qui doit etre
					// connectee.
					myNewTaskList[j].getChildren().add(
						myNewTaskList[k]);
					// On n'oublie pas la reference de la
					// sous-taches a la tache mere
					myNewTaskList[k]
						.setMother(myNewTaskList[j]);
					trouveeFils = true;
					edgeOIDList.remove(l);
					if (l != 0)
					    l = l - 1;
					supprimee = true;
				    }
				}
			    }
			    if (!trouveeFils) {
				edgeOIDList.remove(l);
				if (l != 0)
				    l = l - 1;
				supprimee = true;
			    }
			} else {
			    l++;
			}
		    }
		    // On a effectue la recherche dans toutes les sous-taches et
		    // ainsi la mere est comblee...
		    motherTasksUsed[j]=true;
		}
	    }

	    if (!supprimee) {
		edgeOIDList.remove(i);
	    } else {
		i = 0;
	    }
	}

	return myNewTaskList;
    }

    public static Task[] setTaskPoint(int x, int y, Task myTache) {
	Task[] myTemp = myTache.getTasksToModify(x, y);
	notifyObservers();
	return myTemp;
    }

    /**
     * Cette methode cree une tache au sens Express.
     * 
     * @param x  : position abcisse
     * @param y  : position ordonnee
     * @return Oid de la tache
     */
    public static Task addNewTask(int x, int y, Executor e) {
	Oid oidPoint = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.POINT_CLASS);
	Oid oidTache = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.TASK_CLASS);
	Oid oidMedia = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE, 
    		ExpressConstant.MEDIA_CLASS);

	Point p = (Point) InterfaceExpressJava.prendre(oidPoint);
	Task t = (Task) InterfaceExpressJava.prendre(oidTache);
	Media m = (Media) InterfaceExpressJava.prendre(oidMedia);
	t.setExecutor(e);
	t.setPoint(p);
	t.setMedia(m);
	p.setX(new Integer(x));
	p.setY(new Integer(y));

	notifyObservers();
	return t;
    }

    /**
     * Cette m�thode cr�e une liaison express entre deux t�ches.
     * 
     * @param oidMere
     *            : oid de la t�che m�re
     * @param oidFils
     *            : oid de la t�che fille
     * @return
     */
    public static void addNewEdge(Task taskMere, Task taskFils) {
	taskMere.addSubTask(taskFils);
	notifyObservers();
    }

    /**
     * Cette m�thode permet de supprimer une t�che et ces liens associ�s.
     * 
     * @param oidTache
     * @return
     */
    public static ArrayList<Task> removeTask(Task currentTask) {
	ArrayList<Task> myTemp = currentTask.removeTask();
	notifyObservers();
	return myTemp;
    }

    /**
     * Cette m�thode permet de supprimer un lien.
     * 
     * @param oidTacheMere
     * @param oidTacheFils
     * @return
     */
    public static ArrayList<Task> removeEdge(Task tacheMere, Task tacheFils) {
	ArrayList<Task> myTemp = tacheMere.disconnectSubTask(tacheFils);
	notifyObservers();
	return myTemp;
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut name.
     * 
     * @param oid
     *            : oid de la t�che
     * @param name
     *            : nouveau nom de la t�che
     */
    public static void setNameTask(Task currentTask, String name) {
	currentTask.setName(name);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut but.
     * 
     * @param oid
     * @param but
     */
    public static void setButTask(Task currentTask, String but) {
	currentTask.setGoal(but);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut feedback.
     * 
     * @param oid
     * @param res
     */
    public static void setFeedbackTask(Task currentTask, String res) {
	currentTask.setFeedback(res);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut dur�e.
     * 
     * @param oid
     * @param res
     */
    public static void setDurationTask(Task currentTask, String res) {
	currentTask.setDuration(res);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut observation.
     * 
     * @param oid
     * @param name
     */
    public static void setObservationTask(Task currentTask, String name) {
	currentTask.setDescription(name);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut ex�cutant.
     * 
     * @param oid
     * @param name
     */
    public static void setExecutingUserTask(Task currentTask, Executor name) {
	if (name == null)
	    return;
	currentTask.setExecutor(name);
	notifyObservers();
    }

    public static void setFrequencyTask(Task currentTask, Frequence valeur) {
	if (valeur == null)
	    return;
	currentTask.setFrequency(valeur);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut fr�quence valeur.
     * 
     * @param oid
     * @param valeur
     */
    public static void setFrequencyValueTask(Task currentTask, String valeur) {
	currentTask.setFrequencyValue(valeur);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut modalit�.
     * 
     * @param oid
     * @param sens
     * @param cogn
     */
    public static void setModalityTask(Task currentTask, Modality sensOrCogn) {
	currentTask.setModality(sensOrCogn);
	notifyObservers();
    }

    public static void setSignificantTask(Task currentTask, Importance valeur) {
	if (valeur == null)
	    return;
	currentTask.setImportance(valeur);
	notifyObservers();
    }

    /**
     * Modifie avec l'oid de la t�che l'attribut �v�nement.
     * 
     * @param oid
     * @param valeur
     * @return
     */
    public static boolean addNewEventTask(Task currentTask, String valeur) {
	Event e = ExpressEvent.stringToEvent(valeur);

	if (currentTask.addEvent(e)) {
	    notifyObservers();
	    return true;
	}
	return false;
    }

    public static boolean setEventTask(Task currentTask, String oldvaleur,
	    String valeur) {
	Event oldEvent = ExpressEvent.stringToEvent(oldvaleur);
	Event newEvent = ExpressEvent.stringToEvent(valeur);

	if (oldEvent == null || newEvent == null) {
	    return false;
	}

	if (currentTask.addEvent(newEvent)) {
	    currentTask.removeEvent(oldEvent);
	    notifyObservers();
	    return true;
	}
	return false;
    }

    public static void setOptionalTask(Task currentTask, Boolean valeur) {
	currentTask.setOptional(valeur);
	notifyObservers();
    }

    public static void setInterruptibleTask(Task currentTask, Boolean valeur) {
	currentTask.setInterruptible(valeur);
	notifyObservers();
    }

    public static void setDeclenchementTask(Task currentTask, String valeur) {
	if (valeur == null)
	    return;
	currentTask.setRaisingEvent(ExpressEvent.stringToEvent(valeur));
	notifyObservers();
    }

    public static void setOperatorTask(Task currentTask, Decomposition valeur) {
	currentTask.setOrdering(valeur);
	notifyObservers();
    }

    public static void removeEventTask(Task currentTask, String valeur) {
	Event e = ExpressEvent.stringToEvent(valeur);
	currentTask.removeEvent(e);
	notifyObservers();
    }

    public static boolean addActor(Task currentTask, String oidact) {
	Actor act = (Actor) InterfaceExpressJava.prendre(new Oid(oidact));
	boolean value = currentTask.addActor(act);
	notifyObservers();
	return value;
    }

    public static boolean addActorSystem(Task currentTask, String oidactSys) {
	ActorSystem act = (ActorSystem) InterfaceExpressJava
		.prendre(new Oid(oidactSys));
	boolean value = currentTask.addActorSystem(act);
	notifyObservers();
	return value;
    }

    public static void removeActor(String oidAct) {
	Actor a = (Actor) InterfaceExpressJava.prendre(new Oid(oidAct));
	a.delete();
	notifyObservers();
    }

    public static void removeActorSystem(String oidAct) {
	ActorSystem a = (ActorSystem) InterfaceExpressJava.prendre(new Oid(
		oidAct));
	a.delete();
	notifyObservers();
    }

    public static void removeAllActors(Task ref) {
	// Have to make a copy of Actor List because we'r going to iterate on it
	// and for each element an actor is deleted ...
	Actor[] toto = new Actor[ref.getActors().size()];
	toto = ref.getActors().toArray(toto);
	for (int i = 0; i < toto.length; i++) {
	    toto[i].delete();
	}
	notifyObservers();
    }

    public static void affRemoveActeur(String oidAct) {
	Actor a = (Actor) InterfaceExpressJava.prendre(new Oid(oidAct));
	a.affDelete();
    }

    public static void affRemoveActeurSystem(String oidAct) {
	ActorSystem a = (ActorSystem) InterfaceExpressJava.prendre(new Oid(
		oidAct));
	a.affDelete();

    }

    public static void affRemoveEvent(Task currentTask, String maValue) {
	Event e = ExpressEvent.stringToEvent(maValue);
	InterfaceExpressJava.getGestionWarning().addMessage(
		e.getOid(),
		4,
		ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
			+ currentTask.getName() + "\"");
    }

    public static void setLabelTask(Task selectedExpressTask, String pLabel) {
	if (pLabel == null)
	    return;
	selectedExpressTask.setLabel(ExpressLabel.stringToLabel(pLabel));
	notifyObservers();
    }
}

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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * 
 * @author Vincent LUCQUIAUD and Mickael BARON
 * @author [Renaming] Patrick GIRARD
 * @author [Comment] Patrick GIRARD
 **/
public class Task implements Entity {

	private static final long serialVersionUID = -7319483011074082713L;

	public Oid oid = null;

	// Task type    
	/** executant : Executant -> Enumerated values for the kind of 
		                      task (user, system, interactive, etc.) */
	private Executor executor = Executor.INCONNU;

	/** modality : Modalite -> Enumerated values for the modalities 
	 *                    of user tasks */
	private Modality modality= Modality.INCONNU;

	// Static attributes    
	/** name : String -> Name of the task - no need to be unique, 
	 *                    but cannot be null */
	private String name = ExpressConstant.NEW_NAME_TASK;

	/** goal : String -> The goal of the task. Can be null */
	private String goal = "";

	/** feed : String -> Task feedback, not really used */
	private String feedback = "";

	/** duration : String -> Task duration - can be of either form. 
	 *                     If only a number, can be interpreted */
	private String duration = "";

	/** description : String -> informal description of the task. 
	 *                      Can be null, but not recommended */
	private String description = "";

	/** frequency : Frequence -> Enumerated value for frequency */
	private Frequence frequency = Frequence.INCONNU;

	/** frequencyValue : String -> Value for the frequency. 
	 *                      Free text, no interpretation */
	private String frequencyValue = "";

	/** importance : Importance -> Enumerated value for importance */
	private Importance importance = Importance.INCONNU;

	/** media : Audio-Video file, which illustrates the task */
	private Media media = null;

	// Dynamic attributes
	/** optional = Boolean -> is the task optional? - 
	 * 						default value false  */
	private Boolean optional = false;

	/** interruptible : Boolean -> is the task interruptible - 
	 * 						default value false */
	private Boolean interruptible = false;

	/** ordering : Decomposition -> Enumerated value for task 
	 * 				ordering, including leaf */
	private Decomposition ordering;

	// Structural attributes

	/** number : String -> computed value for the numbering of tasks: 
	 * 						root, 1, 1.1, 1.1.1, etc.  
	 * Warning: no setter, so setting numbers is made at several places ! */
	private String number = null;

	/** label : Label -> Free characterization of tasks, 
	 * 						associated to a color  */
	private Label label;

	/** mother : Task -> Super-task of the current task. Null if 
	 * the task is root, or if it is not attached to the tree */
	private Task mother = null;

	/** children : ArrayList<Tache> -> ordered list of sub-tasks */
	private ArrayList<Task> children = new ArrayList<Task>();

	// Actors

	/** Actors : ArrayList<Acteur> -> Human actors involved 
	 *  						in the task*/
	/**
	 * 
	 */
	private ArrayList<Actor> actors = new ArrayList<Actor>();

	/** actorSystem : ArrayList<actorSystem> -> 
	 * 					System actors involved in the task   */
	private ArrayList<ActorSystem> actorSystem = 
			new ArrayList<ActorSystem>();

	// Expression management

	/** preExpression : PreExpression -> Precondition of the task  */
	private PreExpression preExpression;

	/** iterExpression : IterExpression -> condition for the 
	 * 			iteration of the task */
	private IterExpression iterExpression;

	/** effetsDeBordExpression : EffetsDeBordExpression -> 
	 * 						Side effects for the task (action)   */
	private SideEffectExpression sideEffectExpression;

	// Event management    
	/** lstEvent : ArrayList<Evenement> -> list of event  
	 * 					the task may fire. Often empty   */
	private ArrayList<Event> events = 
			new ArrayList<Event>();

	/**  declencheur : Evenement -> the possible firing event. 
	 * 						May be null  */
	private Event raisingEvent = null;

	// Graphical attributes
	/** Graphical position of the task on the graphical layout */
	private Point point = null;

	/** noPoint : Boolean -> States if the point owns a valid graphical 
	 * 					position or no. Default false */
	private boolean noPoint = false;

	// Graphical optimization
	/** refJTask : GraphicCell reference of the graphical view of the task
	 * used to optimize visualization
	 */
	private Object refJTask = null;

	// Attributes for prototyping and simulating

	//simulation
	private StateSimulation stateSimulation = new StateSimulation();

	//PROTOTASK
	private StateExecution stateExecution;

	//PROTOTASK
	//private ChoiceEnum IterationValue = ChoiceEnum.indeterminee;











	/**
	 * Creation d'une tache avec des valeurs par defaut.
	 */
	public Task() {
		this.name = ExpressConstant.NEW_NAME_TASK;
		this.goal = "";
		this.feedback = "";
		this.duration = "";
		this.description = "";
		this.executor = Executor.INCONNU;
		this.importance = Importance.INCONNU;
		this.modality = Modality.COGN;
		this.frequency = Frequence.INCONNU;
		this.frequencyValue = "";
		this.events = new ArrayList<Event>();
		this.optional = false;
		this.interruptible = false;
		this.ordering = Decomposition.ELE;
		this.children = new ArrayList<Task>();
		this.mother = null;
		this.number = ExpressConstant.ROOT_TASK_NAME;
		this.point = null;
		this.media = null;
		this.raisingEvent = null;
		this.label = null;
		this.preExpression = new PreExpression();
		this.sideEffectExpression = new SideEffectExpression();
		this.iterExpression = new IterExpression();
		/*	this.stateSimulation = new StateSimulation();
		this.stateSimulation = new StateSimulation();*/

	}


	/*
	 * public Tache( String name, String but, String res, String f, String
	 * duree, String obs, String exe, String freq, String comp, String imp,
	 * String mod, Evenement dec, ArrayList<Evenement> events, Boolean
	 * facultatif, Boolean interruptible, String decomposition,
	 * ArrayList<Acteur> act, ArrayList<Tache> fils, Point point, String prec,
	 * String post, String it, Oid oid) { this.name = name; this.but = but;
	 * this.ressources = res; this.feed = f; this.duree = duree;
	 * this.observation = obs; this.executant = Executant.getValue(exe);
	 * this.imp = Importance.getValue(imp); this.modal = Modalite.getValue(mod);
	 * this.frequence = Frequence.getValue(freq); this.compFreq = comp;
	 * this.setEvents(events); this.setDeclencheur(dec); if (facultatif == null)
	 * this.facultatif = false; else this.facultatif = facultatif; if
	 * (interruptible == null) this.interruptible = false; else
	 * this.interruptible = interruptible; this.setactors(act);
	 * this.decomposition = Decomposition.getValue(decomposition);
	 * this.preExpression = new PreExpression(prec); this.effetsDeBordExpression
	 * = new EffetsDeBordExpression(post); this.iteExpression = new
	 * IterExpression(it); this.oid = oid; this.fils = new ArrayList<Tache>();
	 * this.numero = ExpressConstant.ROOT_TASK_NAME;
	 * 
	 * for (int i = 0; i < fils.size(); i++) { Tache tache = fils.get(i);
	 * addSubTask(tache); }
	 * 
	 * this.point = point;
	 * 
	 * // Le media pour rester compatible avec les anciennes versions qui
	 * exploitent SPF. idMedia = null;
	 * 
	 * this.stateSimulation = new StateSimulation(); }
	 */

	public Media getMedia() {
		return this.media;
	}

	public void setMedia(Media m) {
		this.media = m;
	}

	public void setActors(ArrayList<Actor> e) {
		actors = e;
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).setReverseTask(this);
		}
	}

	public void setActorSystem(ArrayList<ActorSystem> e) {
		actorSystem = e;
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).setReverseTask(this);
		}
	}

	/**
	 * Adds an actor to the list of actors. Must be unique in the list
	 * @param a the actor
	 * @return false if the actor already exists (same name) in the list
	 */
	public boolean addActor(Actor a) {
		for (int i = 0; i < actors.size(); i++) {
			Actor act = actors.get(i);
			if (act.getUserRef().getName().equals(a.getUserRef().getName())) {
				return false;
			}
		}
		actors.add(a);
		a.setReverseTask(this);
		return true;

	}

	/**
	 * Adds a System actor to the list of system actors. Must be unique in the list
	 * @param a the system actor
	 * @return false if the actor already exists (same oid) in the list
	 */
	public boolean addActorSystem(ActorSystem a) {
		for (int i = 0; i < actorSystem.size(); i++) {
			ActorSystem act = actorSystem.get(i);
			if (act.getMaterialRef().getOid()
					.equals(a.getMaterialRef().getOid())) {
				return false;
			}
		}
		actorSystem.add(a);
		a.setReverseTask(this);
		return true;

	}

	public void removeActor(Actor a) {
		actors.remove(a);
	}

	public void removeActorSystem(ActorSystem a) {
		actorSystem.remove(a);
	}

	public void setEvents(ArrayList<Event> e) {
		events = e;
		for (int i = 0; i < events.size(); i++) {
			events.get(i).addReverseTask(this);
		}
	}

	/**
	 * Adds an event to the list of fired events
	 * @param a the event to add
	 * @return false if the event already exists (ref)
	 */
	public boolean addEvent(Event a) {
		if (events.indexOf(a) == -1) {
			events.add(a);
			a.addReverseTask(this);
			return true;
		}
		return false;
	}

	/**
	 * @param a the event
	 * @return true if the event is in the list of events the task may generate
	 */
	public boolean isEventHere(Event a) {
		return (events.indexOf(a) != -1);
	}

	/**
	 * Remove the event from the list AND the raisingEvent
	 * @param a
	 */
	public void removeEvent(Event a) {
		if (raisingEvent == a) {
			raisingEvent = null;
		}
		events.remove(a);
	}

	public void setExecutor(Executor s) {
		executor = s;
	}

	public Executor getExecutor() {
		return this.executor;
	}

	public void setFrequency(Frequence s) {
		frequency = s;
	}

	public void setFrequencyValue(String s) {
		frequencyValue = s;
	}

	public void setImportance(Importance s) {
		importance = s;
	}

	/**
	 * @return the name of the label, or null if no label
	 */
	public String getLabelName() {
		return ((this.label == null) ? "" : this.label.getName());
	}

	public Label getLabel() {
		return this.label;
	}

	public void setLabel(Label p) {
		if (this.label != null) {
			this.label.removeInversteTask(this);
		}

		this.label = p;
		if (this.label != null) {
			this.label.addReverseTask(this);
		}
	}

	public void removeLabel() {
		if (this.label != null) {
			this.label.removeInversteTask(this);
			this.label = null;
		}
	}

	public void setModality(Modality modalite) {
		modality = modalite;
	}

	public Decomposition getOrdering() {
		return this.ordering;
	}

	public void setOrdering(Decomposition d) {
		ordering = d;
	}

	public ArrayList<Task> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Task> pSubTasks) {
		this.children = pSubTasks;
	}

	public ArrayList<Actor> getActors() {
		return actors;
	}

	public ArrayList<ActorSystem> getActorSystem() {
		return actorSystem;
	}

	/**
	 * @return list of names of task' actors
	 */
	public ArrayList<String> getActorsName() {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i = 0; i < actors.size(); i++) {
			lst.add(actors.get(i).getUserRef().getName());
		}
		return lst;
	}

	/*    public ArrayList<String> getActorSystemOid() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < actorSystem.size(); i++) {
	    lst.add(actorSystem.get(i).oid.get());
	}
	return lst;
    }

    public ArrayList<User> getUsers() {
	ArrayList<User> lst = new ArrayList<User>();
	for (int i = 0; i < actors.size(); i++) {
	    lst.add(actors.get(i).getUserRef());
	}
	return lst;
    }
	 */
	/*    public ArrayList<Materiel> getMateriels() {
	ArrayList<Materiel> lst = new ArrayList<Materiel>();
	for (int i = 0; i < actorSystem.size(); i++) {
	    lst.add(actorSystem.get(i).getMaterielRef());
	}
	return lst;
    }
	 */
	/*    public ArrayList<String> getActorsExp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < actors.size(); i++) {
	    lst.add(actors.get(i).getExperience().getValue());
	}
	return lst;
    }

    public ArrayList<String> getActorSystemxp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < actorSystem.size(); i++) {
	    lst.add(actorSystem.get(i).getExperience().getValue());
	}
	return lst;
    }*/

	/*    public ArrayList<String> getActorsComp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < actors.size(); i++) {
	    lst.add(actors.get(i).getCompetence());
	}
	return lst;
    }
	 */
	/*    public ArrayList<String> getActorSystemComp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < actorSystem.size(); i++) {
	    lst.add(actorSystem.get(i).getCompetence());
	}
	return lst;
    }*/

	public ArrayList<Event> getEvents() {
		return this.events;
	}

	/**
	 * @return the list of event names
	 */
	public ArrayList<String> getEventsName() {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i = 0; i < events.size(); i++) {
			lst.add(events.get(i).getName());
		}
		return lst;
	}

	public void setRaisingEvent(Event e) {
		if (raisingEvent != null) {
			raisingEvent.removeReverseTask(this);
		}

		raisingEvent = e;
		if (e != null)
			raisingEvent.addReverseTask(this);
	}

	public Event getRaisingEvent() {
		return this.raisingEvent;
	}

	/**
	 * @return the name of the raising event if it exists, else null string
	 */
	public String getRaisingEventName() {
		return this.raisingEvent != null ? this.getRaisingEvent().getName() : "";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoal() {
		return this.goal;
	}

	public void setGoal(String v) {
		this.goal = v;
	}

	public Task getMother() {
		return this.mother;
	}

	public void setMother(Task pMere) {
		this.mother = pMere;
	}

	/**
	 * @return the name of the mother, or NO_MOTHER_TASK_NAME_MESSAGE if null
	 */
	public String getMotherTaskName() {
		if (this.mother == null) {
			return KMADEConstant.NO_MOTHER_TASK_NAME_MESSAGE;
		} else {
			return this.getMother().getName();
		}
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String v) {
		this.duration = v;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String p) {
		this.description = p;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String v) {
		this.feedback = v;
	}

	public Frequence getFrequency() {
		return this.frequency;
	}

	public Modality getModality() {
		return this.modality;
	}

	public Importance getImportance() {
		return this.importance;
	}

	public String getNumber() {
		return number;
	}

	/**
	 * The index is the numbered position of the child, starting from 0
	 * -1 if root task
	 * @return the index of the child among children
	 */
	public int indexOf() {
		return (mother == null) ? -1 : this.mother.children.indexOf(this);
	}

	public Boolean isOptional() {
		return this.optional;
	}

	public Boolean isInterruptible() {
		return this.interruptible;
	}

	public boolean hasNoPoint(){
		return this.noPoint;
	}

	/**
	 * Cuts off the subtask from her mother
	 * 
	 * @param tachefils
	 * @return
	 */
	public ArrayList<Task> disconnectSubTask(Task tachefils) {
		// 1. supprimer la reference Ã  la tÃ¢che mere
		// 2. supprimer la reference aux taches filles
		// 3. modifier le numero de chaque tache fille
		// 4. verifier le numero des tï¿½ches soeurs

		ArrayList<Task> numeroTacheModifie = new ArrayList<Task>();

		// A. traitement des nouveaux sous-arbres gï¿½rï¿½nï¿½s par la
		// suppression
		// 2. supprimer la reference aux taches filles
		tachefils.mother = null;
		// 3. modifier le numero de chaque tache fille
		tachefils.number = ExpressConstant.ROOT_TASK_NAME;
		tachefils.setDeriveTaskNumero(0);
		numeroTacheModifie.add(tachefils);

		// B. traitement des soeurs de la tache supprimï¿½e
		// 1. supprimer la reference ï¿½ la tï¿½che mere
		this.children.remove(tachefils);
		// if (this.fils.size() == 0) this.fils = null;
		// 4. verifier le numero des tï¿½ches soeurs
		// setDeriveNumero(0, this.mere );
		// numeroTacheModifie.add( this.mere );

		return numeroTacheModifie;
	}

	/**
	 * Cette mÃ©thode permet de supprimer une tÃ¢che et ses liens si
	 * nÃ©cessaire.
	 * 
	 * @return
	 */
	public ArrayList<Task> removeTask() {
		// 1. Supprimer la reference Ã  la tÃ¢che mere
		// 2. Supprimer la reference aux taches filles
		// 3. Modifier le numero de chaque tache fille
		// 4. VÃ©rifier le numero des tÃ¢ches soeurs
		// 5. Supprimer Point
		// 6. Supprimer relation avec les Ã©vÃ©nements
		// 7. Supprimer relation avec les utilisateurs
		// 8. Supprimer relation avec le libellÃ©
		// 9. Supprimer la tache

		ArrayList<Task> numeroTacheModifie = new ArrayList<Task>();

		// A Traitement des nouveaux sous-arbres gÃ©rÃ©nÃ©s par la suppression
		int taille = this.children.size();
		for (int i = 0; i < taille; i++) {
			Task t = (Task) this.children.get(i);
			// 2. supprimer la reference aux taches filles
			t.mother = null;
			// 3. modifier le numero de chaque tache fille
			t.number = ExpressConstant.ROOT_TASK_NAME;
			t.setDeriveTaskNumero(0);
			numeroTacheModifie.add(t);
		}

		// B. traitement des soeurs de la tache supprimÃ©e
		if (this.mother != null) {
			// 1. supprimer la reference Ã  la tÃ¢che mere
			this.mother.children.remove(this);
			// 4. verifier le numero des tï¿½ches soeurs
			this.mother.setDeriveTaskNumero(0);
			numeroTacheModifie.add(this.mother);
		}

		// 5. supprimer Point
		InterfaceExpressJava.remove(this.point.oid);

		// 6. Supprimer relation avec les evenements.
		if (this.raisingEvent != null) {
			this.raisingEvent.removeReverseTask(this);
		}

		for (Event current : events) {
			current.removeReverseTask(this);
		}

		// 7. Supprimer relation avec les actors (les supprimer)
		Actor[] arrayCopy = new Actor[this.actors.size()];
		arrayCopy = this.actors.toArray(arrayCopy);
		for (int i = 0; i < arrayCopy.length; i++) {
			arrayCopy[i].delete();
		}

		// 8. supprimer relation avec label
		if (this.label != null) {
			this.label.removeInversteTask(this);
		}

		// 9. supprimer la tache
		InterfaceExpressJava.remove(this.oid);

		return numeroTacheModifie;
	}

	/*
	 * Compute the rank of child among children
	 */
	private static int computeRank(Task leFils, Task laMere) {
		int pointX = (leFils.point.getX()).intValue();
		int px = 0;
		int taille = laMere.children.size();
		for (int i = 0; i < taille; i++) {
			px = (((Task) laMere.children.get(i)).point.getX()).intValue();
			if (pointX < (px))
				return i;
		}
		return taille;
	}

	/**
	 * Adds a subtask to the task. Rank is outomatically computed
	 * @param tachefils
	 */
	public void addSubTask(Task tachefils) {
		tachefils.setMother(this);
		int rank = computeRank(tachefils, this);
		children.add(rank, tachefils);
		this.setDeriveTaskNumero(rank);
	}

	/**
	 * @param rank
	 */
	private void setDeriveTaskNumero(int rank) {
		String s = this.number;
		if (s.startsWith(ExpressConstant.ROOT_TASK_NAME))
			s = "";
		if (this.mother == null)
			s = "";
		for (int i = rank; i < this.children.size(); i++) {
			Task tmp = (Task) this.children.get(i);
			if (s.length() == 0)
				tmp.number = "" + (i + 1);
			else
				tmp.number = s + "." + (i + 1);
			// attention a la memoire...
			tmp.setDeriveTaskNumero(0);
		}
	}

	/*    /**
	 * Sets the point value from elementary coordinates
	 * @param x X coordinate of the task
	 * @param y Y coordinate of the task
	 *//*
    public void setPointFromCoordinates(int x, int y) {
	this.point.x = new Integer(x);
	this.point.y = new Integer(y);
    }
	  */
	/**
	 * returns the list of tasks which must be modified 
	 * because of new coordinates
	 * @param x
	 * @param y
	 * @return the list of tasks
	 */
	public Task[] getTasksToModify(int x, int y) {
		this.point.setX(new Integer(x));
		this.point.setY(new Integer(y));
		if (mother == null)
			return null;
		int placeOld = mother.children.indexOf(this);
		// Mickael BARON : Tache dec = (Tache) mere.fils.remove(placeOld);
		mother.children.remove(placeOld);
		int placeNew = computeRank(this, mother);
		mother.children.add(placeNew, this);
		int debut = 0;
		if (placeNew == placeOld)
			return null;
		if (placeNew < placeOld)
			debut = placeNew;
		if (placeOld < placeNew)
			debut = placeOld;
		mother.setDeriveTaskNumero(debut);

		int taille = mother.children.size();
		Task[] taches = new Task[taille];
		for (int i = 0; i < taille; i++) {
			taches[i] = ((Task) mother.children.get(i));
		}
		return taches;
	}

	public String toString() {
		return this.number + " - " + name;
	}


	public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
		NodeList nodeList = p.getElementsByTagName("id-task-eventtrigger");
		if (nodeList.item(0) != null) {
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
					.getTextContent())) == null) {
				return true;
			}
		}

		nodeList = p.getElementsByTagName("id-task-media");
		if (nodeList.item(0) != null) {
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
					.getTextContent())) == null) {
				return true;
			}
		}

		nodeList = p.getElementsByTagName("id-task-events-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListEvents = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListEvents.getLength(); i++) {
				if (nodeListEvents.item(i).getNodeType() == Element.ELEMENT_NODE) {
					if (InterfaceExpressJava.bdd
							.prendre(new Oid(((Element) nodeListEvents.item(i))
									.getTextContent())) == null) {
						return true;
					}
				}
			}
		}

		nodeList = p.getElementsByTagName("id-task-actors-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListActors = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListActors.getLength(); i++) {
				if (nodeListActors.item(i).getNodeType() == Element.ELEMENT_NODE) {
					if (InterfaceExpressJava.bdd
							.prendre(new Oid(((Element) nodeListActors.item(i))
									.getTextContent())) == null) {
						return true;
					}
				}
			}
		}

		nodeList = p.getElementsByTagName("id-task-actorSystem-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListActors = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListActors.getLength(); i++) {
				if (nodeListActors.item(i).getNodeType() == Element.ELEMENT_NODE) {
					if (InterfaceExpressJava.bdd
							.prendre(new Oid(((Element) nodeListActors.item(i))
									.getTextContent())) == null) {
						return true;
					}
				}
			}
		}

		nodeList = p.getElementsByTagName("id-task-subtasks-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListSubTasks = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListSubTasks.getLength(); i++) {
				if (nodeListSubTasks.item(i).getNodeType() == Element.ELEMENT_NODE) {
					if (InterfaceExpressJava.bdd.prendre(new Oid(
							((Element) nodeListSubTasks.item(i))
							.getTextContent())) == null) {
						return true;
					}
				}
			}
		}

		nodeList = p.getElementsByTagName("id-task-point");
		if (nodeList.item(0) != null) {
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
					.getTextContent())) == null) {
				return true;
			}
		}

		nodeList = p.getElementsByTagName("id-task-label");
		if (nodeList.item(0) != null) {
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0)
					.getTextContent())) == null) {
				return true;
			}
		}

		return false;
	}

	public void createObjectFromXMLElement(org.w3c.dom.Element p) {
		this.oid = new Oid(p.getAttribute("idkmad"));

		// Name
		NodeList nodeList = p.getElementsByTagName("task-name");
		this.name = nodeList.item(0).getTextContent();

		// Purpose
		nodeList = p.getElementsByTagName("task-purpose");
		if (nodeList.item(0) != null) {
			this.goal = nodeList.item(0).getTextContent();
		}

		// Duration
		nodeList = p.getElementsByTagName("task-duration");
		if (nodeList.item(0) != null) {
			this.duration = nodeList.item(0).getTextContent();
		}

		// Media
		nodeList = p.getElementsByTagName("id-task-media");
		if (nodeList.item(0) != null) {
			this.media = (Media) InterfaceExpressJava.bdd.prendre(new Oid(
					nodeList.item(0).getTextContent()));
		}

		// Feedback
		nodeList = p.getElementsByTagName("task-feedback");
		if (nodeList.item(0) != null) {
			this.feedback = nodeList.item(0).getTextContent();
		}

		// Observation
		nodeList = p.getElementsByTagName("task-observation");
		if (nodeList.item(0) != null) {
			this.description = nodeList.item(0).getTextContent();
		}

		// Executant
		this.executor = Executor.getXMLExecutorValue(p);

		// Frequence
		this.frequency = Frequence.getXMLFrequenceValue(p);

		// Valeur de la Frequence
		nodeList = p.getElementsByTagName("task-compfrequency");
		if (nodeList.item(0) != null) {
			this.frequencyValue = nodeList.item(0).getTextContent();
		}

		// Importance
		this.importance = Importance.getXMLExecutantValue(p);

		// Modality
		this.modality = Modality.getXMLModalityValue(p);

		// Triggering Event
		nodeList = p.getElementsByTagName("id-task-eventtrigger");
		if (nodeList.item(0) != null) {
			Event event = (Event) InterfaceExpressJava.bdd
					.prendre(new Oid(nodeList.item(0).getTextContent()));
			this.raisingEvent = event;
			this.raisingEvent.addReverseTask(this);
		}

		// Generated Events
		nodeList = p.getElementsByTagName("id-task-events-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListEvent = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListEvent.getLength(); i++) {
				if (nodeListEvent.item(i).getNodeType() == Element.ELEMENT_NODE) {
					Event event = (Event) InterfaceExpressJava.bdd
							.prendre(new Oid(nodeListEvent.item(i)
									.getTextContent()));
					this.events.add(event);
					event.addReverseTask(this);
				}
			}
		}

		// Optional
		nodeList = p.getElementsByTagName("task-optional");
		this.optional = new Boolean(nodeList.item(0).getTextContent());

		// Interruptible
		nodeList = p.getElementsByTagName("task-interruptible");
		this.interruptible = new Boolean(nodeList.item(0).getTextContent());

		// Decomposition
		this.ordering = Decomposition.getXMLModalityValue(p);

		// Actors
		nodeList = p.getElementsByTagName("id-task-actors-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListActors = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListActors.getLength(); i++) {
				if (nodeListActors.item(i).getNodeType() == Element.ELEMENT_NODE) {
					Actor acteur = (Actor) InterfaceExpressJava.bdd
							.prendre(new Oid(nodeListActors.item(i)
									.getTextContent()));
					this.actors.add(acteur);
					acteur.setReverseTask(this);
				}
			}
		}

		// Actors System
		nodeList = p.getElementsByTagName("id-task-actorSystem-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListActors = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListActors.getLength(); i++) {
				if (nodeListActors.item(i).getNodeType() == Element.ELEMENT_NODE) {
					ActorSystem actorSystem = (ActorSystem) InterfaceExpressJava.bdd
							.prendre(new Oid(nodeListActors.item(i)
									.getTextContent()));
					this.actorSystem.add(actorSystem);
					actorSystem.setReverseTask(this);
				}
			}
		}
		// Subtasks
		nodeList = p.getElementsByTagName("id-task-subtasks-list");
		if (nodeList.getLength() != 0) {
			NodeList nodeListSubTasks = nodeList.item(0).getChildNodes();
			for (int i = 0; i < nodeListSubTasks.getLength(); i++) {
				if (nodeListSubTasks.item(i).getNodeType() == Element.ELEMENT_NODE) {
					Task tache = (Task) InterfaceExpressJava.bdd
							.prendre(new Oid(nodeListSubTasks.item(i)
									.getTextContent()));
					this.addSubTask(tache);
				}
			}
		}

		// Point
		nodeList = p.getElementsByTagName("id-task-point");
		this.point = (Point) InterfaceExpressJava.bdd.prendre(new Oid(nodeList
				.item(0).getTextContent()));

		// Label
		nodeList = p.getElementsByTagName("id-task-label");
		if (nodeList.item(0) != null)
			this.label = (Label) InterfaceExpressJava.bdd.prendre(new Oid(
					nodeList.item(0).getTextContent()));

		// Precondition
		nodeList = p.getElementsByTagName("task-precondition");
		if (nodeList.item(0) != null) {
			this.preExpression = new PreExpression(nodeList.item(0)
					.getTextContent());
		}

		// Precondition Description
		nodeList = p.getElementsByTagName("task-descriptionprecondition");
		if (nodeList.item(0) != null) {
			this.preExpression
			.setDescription(nodeList.item(0).getTextContent());
		}

		// EffetsDeBord
		nodeList = p.getElementsByTagName("task-effetsdebord");
		if (nodeList.item(0) != null) {
			this.sideEffectExpression = new SideEffectExpression(nodeList
					.item(0).getTextContent());
		}
		// attention il ne faut pas utiliser le tag postcondition pour les
		// postcondition de la V2 !
		nodeList = p.getElementsByTagName("task-postcondition");
		if (nodeList.item(0) != null) {
			this.sideEffectExpression = new SideEffectExpression(nodeList
					.item(0).getTextContent());
		}

		// attention il ne faut pas utiliser le tag postcondition pour les
		// postcondition de la V2 !
		nodeList = p.getElementsByTagName("task-descriptionpostcondition");
		if (nodeList.item(0) != null) {
			this.sideEffectExpression.setDescription(nodeList.item(0)
					.getTextContent());
		}
		// EffetsDeBord Description
		nodeList = p.getElementsByTagName("task-descriptioneffetsdebord");
		if (nodeList.item(0) != null) {
			this.sideEffectExpression.setDescription(nodeList.item(0)
					.getTextContent());
		}

		// Iteration
		nodeList = p.getElementsByTagName("task-iteration");
		this.iterExpression = new IterExpression(nodeList.item(0)
				.getTextContent());

		// Iteration Description
		nodeList = p.getElementsByTagName("task-descriptioniteration");
		if (nodeList.item(0) != null) {
			this.iterExpression
			.setDescription(nodeList.item(0).getTextContent());
		}
	}

	/**
	 * 
	 * @return
	 */
	public String toSPF() {
		String SPF = oid.get() + "=" + "Tache" + "(" + "'"
				+ name.replaceAll("'", "\\\\'") + "'" + "," + "'" + goal + "'"
				+ "," + "'" /*+ resources + "'"*/ + "," + "'" + feedback + "'" + ","
				+ "'" + duration + "'" + "," + "'" + description + "'" + ","
				+ executor.toSPF() + "," + frequency.toSPF() + ",'" + frequencyValue
				+ "'," + importance.toSPF() + "," + modality.toSPF() + ",";
		if (raisingEvent != null)
			SPF = SPF + raisingEvent.oid.get() + ",";
		else
			SPF = SPF + "$,";
		// events
		SPF = SPF + "(";
		for (int i = 0; i < events.size(); i++) {
			SPF = SPF + events.get(i).oid.get();
			if (i != events.size())
				SPF = SPF + ",";
		}
		SPF = SPF + "),";
		// priorite + "," +
		if (optional == null)
			SPF = SPF + "$,";
		else
			SPF = SPF + "." + optional + "." + ",";
		if (interruptible == null)
			SPF = SPF + "$,";
		else
			SPF = SPF + "." + interruptible + "." + ",";
		SPF = SPF + "," + ordering.toSPF();
		// actors
		SPF = SPF + ",(";
		for (int i = 0; i < actors.size(); i++) {
			SPF = SPF + actors.get(i).oid.get();
			if (i != actors.size())
				SPF = SPF + ",";
		}
		SPF = SPF + "),";
		// actors System
		SPF = SPF + "(";
		for (int i = 0; i < actorSystem.size(); i++) {
			SPF = SPF + actorSystem.get(i).oid.get();
			if (i != actorSystem.size())
				SPF = SPF + ",";
		}
		SPF = SPF + "),";
		// fils
		SPF = SPF + "(";
		for (int i = 0; i < children.size(); i++) {
			SPF = SPF + children.get(i).oid.get();
			if (i != children.size())
				SPF = SPF + ",";
		}
		SPF = SPF + "),";
		// Media
		if (media == null) {
			// Ne rien faire
		} else {
			SPF = SPF + media.oid.get() + ",";
		}
		// Point
		if (point == null) {
			// SPF = SPF;
		} else {
			SPF = SPF + point.oid.get() + ",";
		}
		SPF = SPF + ",'" + preExpression.getFormalText().replaceAll("'", "\\\\'")
				+ "'," + "'"
				+ sideEffectExpression.getFormalText().replaceAll("'", "\\\\'")
				+ "'," + "'" + iterExpression.getFormalText().replaceAll("'", "\\\\'")
				+ "')";
		return SPF;
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public Oid getOid() {
		return oid;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * @return the next child task in the list, null if none
	 */
	public Task getNextChildTask() {
		if (this.mother == null) {
			return null;
		}
		ArrayList<Task> children = this.mother.getChildren();
		if (children == null) {
			return null;
		}
		int myIndex = children.indexOf(this);
		if (myIndex <= 0) {
			return null;
		}
		return (children.get(myIndex - 1));
	}

	/**
	 * @return the previous child task in the list, null if none
	 */
	public Task getPreviousChildTask() {
		if (this.mother == null) {
			return null;
		}
		ArrayList<Task> children = this.mother.getChildren();
		if (children == null) {
			return null;
		}
		int myIndex = children.indexOf(this);
		if (myIndex >= children.size() - 1) {
			return null;
		}
		return (children.get(myIndex + 1));
	}

	/**
	 * @return the first child of the task, null if none
	 */
	public Task getFirstChildTask() {
		ArrayList<Task> myChildren = this.getChildren();
		if (myChildren == null || myChildren.size() == 0) {
			return null;
		}
		return (myChildren.get(0));
	}

	/**
	 * @return Returns the facultatif.
	 */
	public Boolean getFacultatif() {
		return optional;
	}

	/**
	 * @param facultatif
	 *            The facultatif to set.
	 */
	public void setOptional(Boolean facultatif) {
		this.optional = facultatif;
	}

	/**
	 * @return Returns the interruptible.
	 */
	public Boolean getInterruptible() {
		return interruptible;
	}

	/**
	 * @param interruptible
	 *            The interruptible to set.
	 */
	public void setInterruptible(Boolean interruptible) {
		this.interruptible = interruptible;
	}

	/**
	 * @return Returns the value for the frequency.
	 */
	public String getFrequencyValue() {
		return frequencyValue;
	}

	/**
	 * @return Returns the refJTask.
	 */
	public Object getRefJTask() {
		return refJTask;
	}

	/**
	 * @param refJTask
	 *            The refJTask to set.
	 */
	public void setRefJTask(Object refJTask) {
		this.refJTask = refJTask;
	}

	/**
	 * @return Returns the preExpression.
	 */
	public PreExpression getPreExpression() {
		return preExpression;
	}

	/**
	 * @param preExpression
	 *            The preExpression to set.
	 */
	public void setPreExpression(PreExpression preExpression) {
		this.preExpression = preExpression;
	}

	public SideEffectExpression getEffetsDeBordExpression() {
		return sideEffectExpression;
	}

	public void setSideEffetExpression(
			SideEffectExpression effetsDeBordExpression) {
		this.sideEffectExpression = effetsDeBordExpression;
	}

	public IterExpression getIterExpression() {
		return iterExpression;
	}

	public void setIterExpression(IterExpression iterExpression) {
		this.iterExpression = iterExpression;
	}

	public StateSimulation getStateSimulation() {
		return this.stateSimulation;
	}

	public void setStateSimulation(StateSimulation pSimulation) {
		this.stateSimulation = pSimulation;
	}

	/**
	 * @return true if the task have no child
	 */
	public boolean isLeaf() {
		return this.getChildren() != null ? this.getChildren().size() == 0 : true;
	}

	/**
	 * @return true if the task is root
	 */
	public boolean isRoot() {
		return (this.mother == null);
	}

	public static boolean canHaveActor(Executor ex) {
		if (ex == Executor.SYS) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canHaveActor() {
		return canHaveActor(executor);
	}

	public static boolean canHaveActorSystem(Executor ex) {
		if (ex == Executor.USER) {
			return false;
		} else {
			return true;
		}
	}


	public void setStateExecution(StateExecution stateExecution) {
		this.stateExecution = stateExecution;
	}

	public StateExecution getStateExecution() {
		return stateExecution;
	}


	/**
	 * Same as "toXML" method adapted to the new dtd
	 * 
	 * @author Joachim TROUVERIE
	 */
	@Override
	public Element toXML2(Document doc) throws Exception {
		// Arguments
		Element racine = doc.createElement("task");

		// Elements
		// Name
		Element kmadElement = doc.createElement("task-name");
		kmadElement.setTextContent(this.name);
		racine.appendChild(kmadElement);
		// Numero
		kmadElement = doc.createElement("task-numero");
		kmadElement.setTextContent(this.number);
		racine.appendChild(kmadElement);
		// But
		if (!this.goal.equals("")) {
			kmadElement = doc.createElement("task-purpose");
			kmadElement.setTextContent(this.goal);
			racine.appendChild(kmadElement);
		}
		// Duration
		if (!this.duration.equals("")) {
			kmadElement = doc.createElement("task-duration");
			kmadElement.setTextContent(this.duration);
			racine.appendChild(kmadElement);
		}
		// Feedback
		if (!this.feedback.equals("")) {
			kmadElement = doc.createElement("task-feedback");
			kmadElement.setTextContent(this.feedback);
			racine.appendChild(kmadElement);
		}
		// Observation
		//if (!this.observation.equals("")) {
		kmadElement = doc.createElement("task-observation");
		kmadElement.setTextContent(this.description);
		racine.appendChild(kmadElement);
		//}
		// Executant
		racine.appendChild(this.executor.toXML2(doc));
		// Frequency
		if (!this.frequency.equals(Frequence.INCONNU)) {
			racine.appendChild(this.frequency.toXML2(doc));
		}
		// Frequency Value
		if (!this.frequencyValue.equals("")) {
			kmadElement = doc.createElement("task-compfrequency");
			kmadElement.setTextContent(this.frequencyValue);
			racine.appendChild(kmadElement);
		}
		// Importance
		if (!this.importance.equals(Importance.INCONNU)) {
			racine.appendChild(this.importance.toXML2(doc));
		}
		// Modality
		if (!this.modality.equals(Modality.INCONNU)) {
			racine.appendChild(this.modality.toXML2(doc));
		}
		// Optional
		kmadElement = doc.createElement("task-optional");
		kmadElement.setTextContent(this.optional.toString());
		racine.appendChild(kmadElement);
		// Interruptible
		kmadElement = doc.createElement("task-interruptible");
		kmadElement.setTextContent(this.interruptible.toString());
		racine.appendChild(kmadElement);
		// Decomposition
		racine.appendChild(this.ordering.toXML2(doc));
		// Precondition
		kmadElement = doc.createElement("task-precondition");
		kmadElement.setTextContent(this.preExpression.getFormalText());
		racine.appendChild(kmadElement);
		// Precondition Description
		if (!this.getPreExpression().getDescription().equals("")) {
			kmadElement = doc.createElement("task-descriptionprecondition");
			kmadElement.setTextContent(this.preExpression.getDescription());
			racine.appendChild(kmadElement);
		}
		if (this.getPreExpression().getProtoTaskConditionExpression().getValue().getOid()!=null){
			kmadElement = doc.createElement("task-prototaskPrecondition");
			kmadElement.setTextContent(this.getPreExpression().getProtoTaskConditionExpression().getValue().getOid().get());
			racine.appendChild(kmadElement);
		}

		// attention il ne faut pas utilise le tag postcondition pour les
		// postcondition de la V2 !
		// ils sont reserver aux effets de bord de la v1!
		// EffetsDeBord
		// ATTENTION optionnal in the v3
		if (!this.sideEffectExpression.getFormalText().equals("Void")) {
			kmadElement = doc.createElement("task-effetsdebord");
			kmadElement.setTextContent(this.sideEffectExpression.getFormalText());
			racine.appendChild(kmadElement);
		}
		// EffetsDeBord Description
		if (!this.getEffetsDeBordExpression().getDescription().equals("")) {
			kmadElement = doc.createElement("task-descriptioneffetsdebord");
			kmadElement.setTextContent(this.sideEffectExpression
					.getDescription());
			racine.appendChild(kmadElement);
		}
		// Iteration
		kmadElement = doc.createElement("task-iteration");
		kmadElement.setTextContent(this.iterExpression.getFormalText());
		racine.appendChild(kmadElement);
		// Iteration Description
		if (!this.getIterExpression().getDescription().equals("")) {
			kmadElement = doc.createElement("task-descriptioniteration");
			kmadElement.setTextContent(this.iterExpression.getDescription());
			racine.appendChild(kmadElement);
		}
		if (this.getIterExpression().getProtoTaskConditionExpression().getValue().getOid()!=null){
			kmadElement = doc.createElement("task-prototaskIteration");
			kmadElement.setTextContent(this.getIterExpression().getProtoTaskConditionExpression().getValue().getOid().get());
			racine.appendChild(kmadElement);
		}
		//Attributes
		racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "." + ExpressConstant.TASK_CLASS);
		racine.setAttribute("idkmad", oid.get());
		// Media
		if (this.media != null && this.media.isExisting()){
			racine.setAttribute("id-task-media", this.media.getOid().get());
			racine.appendChild(this.media.toXML2(doc));
		}
		// Event trigger
		if (this.raisingEvent != null){
			racine.setAttribute("id-task-eventtrigger", this.raisingEvent
					.getOid().get());
		}
		// Point
		racine.setAttribute("id-task-point", this.point.getOid().get());
		racine.appendChild(this.point.toXML2(doc));
		// Label
		if (this.label != null){
			racine.setAttribute("id-task-label", this.label.getOid().get());
		}
		// Events
		if (!this.events.isEmpty()) {
			String list = new String("");
			for (int i = 0; i < this.events.size(); i++) {
				list += events.get(i).getOid().get() + " ";
			}
			racine.setAttribute("id-task-events-list", list);
		}
		// Actors
		if (!this.actors.isEmpty()) {
			String list = new String("");
			for (int i = 0; i < this.actors.size(); i++) {
				list += actors.get(i).getOid().get() + " ";
				racine.appendChild(this.actors.get(i).toXML2(doc));
			}
			racine.setAttribute("id-task-actors-list", list);
		}
		// Actors System
		if (!this.actorSystem.isEmpty()) {
			String list = new String("");
			for (int i = 0; i < this.actorSystem.size(); i++) {
				list += actorSystem.get(i).getOid().get() + " ";
				racine.appendChild(this.actorSystem.get(i).toXML2(doc));
			}
			racine.setAttribute("id-task-actorSystem", list);
		}
		// Subtasks
		if (!this.children.isEmpty()) {
			String list = new String("");
			for (int i = 0; i < this.children.size(); i++) {
				list += children.get(i).getOid().get() + " ";
				racine.appendChild(this.children.get(i).toXML2(doc));
			}
			racine.setAttribute("id-task-subtasks-list", list);
		}

		return racine;

	}


	/**
	 * Same as "createObjectFromXMLElement" method adapted to the new dtd
	 * @author Joachim TROUVERIE
	 */
	@Override
	public void createObjectFromXMLElement2(Element p) throws Exception {
		this.events.clear();
		this.actors.clear();
		this.actorSystem.clear();
		this.children.clear();
		this.oid = new Oid(p.getAttribute("idkmad"));
		// Media
		if (p.hasAttribute("id-task-media)"))
			this.media = (Media) InterfaceExpressJava.bdd.prendre(new Oid(p
					.getAttribute("id-task-media")));
		// Triggering Event
		if (p.hasAttribute("id-task-eventtrigger"))
			this.raisingEvent = (Event) InterfaceExpressJava.bdd.prendre(new Oid(p.getAttribute("id-task-eventtrigger")));
		// Generated Events
		if (p.hasAttribute("id-task-events-list")) {
			String[] events = p.getAttribute("id-task-events-list").split(" ");
			for (int i = 0; i < events.length; i++) {
				Event event = (Event) InterfaceExpressJava.bdd.prendre(new Oid(events[i]));
				this.events.add(event);
				event.addReverseTask(this);
			}
		}
		// Actors
		if (p.hasAttribute("id-task-actors-list")) {
			String[] actors = p.getAttribute("id-task-actors-list").split(" ");
			for (int i = 0; i < actors.length; i++) {
				this.addActor((Actor) InterfaceExpressJava.bdd.prendre(new Oid(actors[i])));
				this.actors.get(i).setReverseTask(this);
			}
		}
		// Actors system
		if (p.hasAttribute("id-task-actorSystem-list")) {
			String[] actorSys = p.getAttribute("id-task-actorSystem-list")
					.split(" ");
			for (int i = 0; i < actorSys.length; i++) {
				this.addActorSystem((ActorSystem) InterfaceExpressJava.bdd
						.prendre(new Oid(actorSys[i])));
				this.actorSystem.get(i).setReverseTask(this);
			}
		}
		// Point (coordonnée)
		if (p.hasAttribute("id-task-point"))
			this.point = (Point) InterfaceExpressJava.bdd.prendre(new Oid(p
					.getAttribute("id-task-point")));
		else {
			this.noPoint = true;
		}
		// Subtasks
		if (p.hasAttribute("id-task-subtasks-list")) {
			String[] son = p.getAttribute("id-task-subtasks-list").split(" ");
			ArrayList<String> set = new ArrayList<String>();
			for (int i = 0; i < son.length; i++){
				if(!set.contains(son[i])){
					set.add(son[i]);
				}
			}

			for (int i = 0; i < set.size(); i++) {
				if(!noPoint)
					this.addSubTask((Task) InterfaceExpressJava.bdd
							.prendre(new Oid(set.get(i))));
				else{
					this.children.add((Task) InterfaceExpressJava.bdd
							.prendre(new Oid(set.get(i))));
					this.setMother((Task) InterfaceExpressJava.bdd
							.prendre(new Oid(set.get(i))));
				}
			}
		}


		// Label
		if (p.hasAttribute("id-task-label"))
			this.label = (Label) InterfaceExpressJava.bdd.prendre(new Oid(p
					.getAttribute("id-task-label")));

		// Elements
		// Name
		NodeList nodeList = p.getElementsByTagName("task-name");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		this.name = nodeList.item(0).getTextContent();
		// Numero

		nodeList = p.getElementsByTagName("task-numero");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		this.number = nodeList.item(0).getTextContent();

		// Purpose
		nodeList = p.getElementsByTagName("task-purpose");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.goal = nodeList.item(0).getTextContent();
		}

		// Duration
		nodeList = p.getElementsByTagName("task-duration");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.duration = nodeList.item(0).getTextContent();
		}

		// Feedback
		nodeList = p.getElementsByTagName("task-feedback");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.feedback = nodeList.item(0).getTextContent();
		}
		// Observation
		nodeList = p.getElementsByTagName("task-observation");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if(nodeList == null){
			this.description = "";
		} else if (nodeList.item(0) != null) {
			this.description = nodeList.item(0).getTextContent();
		}

		// Executant
		this.executor = Executor.getXMLExecutorValue2(p);
		// Frequence
		this.frequency = Frequence.getXMLFrequenceValue2(p);
		// Valeur de la Frequence
		nodeList = p.getElementsByTagName("task-compfrequency");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.frequencyValue = nodeList.item(0).getTextContent();
		}
		// Importance
		this.importance = Importance.getXMLExecutantValue2(p);
		// Modality
		this.modality = Modality.getXMLModalityValue2(p);
		// Optional
		nodeList = p.getElementsByTagName("task-optional");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		this.optional = new Boolean(nodeList.item(0).getTextContent());
		// Interruptible
		nodeList = p.getElementsByTagName("task-interruptible");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		this.interruptible = new Boolean(nodeList.item(0).getTextContent());
		// Decomposition
		this.ordering = Decomposition.getXMLDecompositionValue2(p);
		// Precondition
		nodeList = p.getElementsByTagName("task-precondition");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.preExpression = new PreExpression(nodeList.item(0)
					.getTextContent());
		}


		// Precondition Description
		nodeList = p.getElementsByTagName("task-descriptionprecondition");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList.item(0) != null) {
			this.preExpression
			.setDescription(nodeList.item(0).getTextContent());
		}

		//precondition ProtoTask
		nodeList = p.getElementsByTagName("task-prototaskPrecondition");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList.item(0) != null) {

			this.getPreExpression().getProtoTaskConditionExpression().setValue((ProtoTaskCondition) InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())));
		}
		// EffetsDeBord

		nodeList = p.getElementsByTagName("task-effetsdebord");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.sideEffectExpression = new SideEffectExpression(nodeList
					.item(0).getTextContent());
		}
		// attention il ne faut pas utilisï¿½ le tag postcondition pour les
		// postcondition de la V2 !
		nodeList = p.getElementsByTagName("task-postcondition");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList.item(0) != null) {
			this.sideEffectExpression = new SideEffectExpression(nodeList
					.item(0).getTextContent());
		}
		// attention il ne faut pas utilisï¿½ le tag postcondition pour les
		// postcondition de la V2 !
		nodeList = p.getElementsByTagName("task-descriptionpostcondition");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.sideEffectExpression.setDescription(nodeList.item(0)
					.getTextContent());
		}

		// EffetsDeBord Description
		nodeList = p.getElementsByTagName("task-descriptioneffetsdebord");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null&& nodeList.item(0) != null) {
			this.sideEffectExpression.setDescription(nodeList.item(0)
					.getTextContent());
		}
		// Iteration
		nodeList = p.getElementsByTagName("task-iteration");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		this.iterExpression = new IterExpression(nodeList.item(0)
				.getTextContent());
		// Iteration Description
		nodeList = p.getElementsByTagName("task-descriptioniteration");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList!= null && nodeList.item(0) != null) {
			this.iterExpression
			.setDescription(nodeList.item(0).getTextContent());
		}	
		nodeList = p.getElementsByTagName("task-prototaskIteration");
		if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
			nodeList = null;}
		if (nodeList.item(0) != null) {

			this.getIterExpression().getProtoTaskConditionExpression().setValue((ProtoTaskCondition) InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())));
		}

	}

	/**
	 * Same as "oidIsAnyMissing" method adapted to the new dtd
	 * 
	 * @author Joachim TROUVERIE
	 */
	@Override
	public boolean oidIsAnyMissing2(Element p) throws Exception {
		// Event Trigger
		if (p.hasAttribute("id-task-eventtrigger")) {
			String nodeList = p.getAttribute("id-task-eventtrigger");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return true;
			}
		}
		// Media
		if (p.hasAttribute("id-task-media")) {
			String nodeList = p.getAttribute("id-task-media");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return true;
			}
		}
		// Events list
		if (p.hasAttribute("id-task-events-list")) {
			String[] list = p.getAttribute("id-task-events-list").split(" ");
			for (int i = 0; i < list.length; i++) {
				if (InterfaceExpressJava.bdd.prendre(new Oid(list[i])) == null) {
					return true;
				}
			}
		}
		// Actors list
		if (p.hasAttribute("id-task-actors-list")) {
			String[] list = p.getAttribute("id-task-actors-list").split(" ");
			for (int i = 0; i < list.length; i++) {
				if (InterfaceExpressJava.bdd.prendre(new Oid(list[i])) == null) {
					return true;
				}
			}
		}
		// Actors System
		if (p.hasAttribute("id-task-actorSystem")) {
			String[] list = p.getAttribute("id-task-actorSystem").split(" ");
			for (int i = 0; i < list.length; i++) {
				if (InterfaceExpressJava.bdd.prendre(new Oid(list[i])) == null) {
					return true;
				}
			}
		}
		// Subtasks list
		if (p.hasAttribute("id-task-subtasks-list")) {
			String[] list = p.getAttribute("id-task-subtasks-list").split(" ");
			for (int i = 0; i < list.length; i++) {
				if (InterfaceExpressJava.bdd.prendre(new Oid(list[i])) == null) {
					return true;
				}
			}
		}
		// Point
		if (p.hasAttribute("id-task-point")) {
			String nodeList = p.getAttribute("id-task-point");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return false;
			}
		}
		// Label
		if (p.hasAttribute("id-task-label")) {
			String nodeList = p.getAttribute("id-task-label");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return true;
			}

		}
		//prototask condition
		if(p.hasAttribute("task-prototaskPrecondition")){
			String nodeList = p.getAttribute("task-prototaskPrecondition");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return true;
			}
		}
		if(p.hasAttribute("task-prototaskIteration")){
			String nodeList = p.getAttribute("task-prototaskIteration");
			if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList)) == null) {
				return true;
			}
		}
		return false;

	}

	//prototask
	/*public ChoiceEnum getPreconditionValue() {
		return getPreExpression().getPreconditionValue();
	}
	//prototask
	public void setPreconditionValue(ChoiceEnum preconditionValue) {
		getPreExpression().setPreconditionValue(preconditionValue);
	}
	 */
	/*public ChoiceEnum getIterationValue() {
	return IterationValue;
}

public void setIterationValue(ChoiceEnum iterationValue) {
	IterationValue = iterationValue;
}*/


}

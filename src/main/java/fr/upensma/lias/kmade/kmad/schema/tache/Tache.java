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
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;

/**
 * 
 * @author Vincent LUCQUIAUD and Mickael BARON
 * @author [Comment] Patrick GIRARD
 **/
public class Tache implements Entity {

    private static final long serialVersionUID = -7319483011074082713L;

    public Oid oid = null;

    // Task type
    
		/**
		 * executant : Executant -> Enumerated values for the kind of task (user, system, interactive, etc.)
		 */
		private Executant executant = Executant.INCONNU;

		/**
		 * modality : Modalite -> Enumerated values for the modalities of user tasks
		 */
		private Modalite modality= Modalite.INCONNU;
    
    // Static task attributes
    
    	/**
    	 * name : String -> Name of the task - no need to be unique, but cannot be null
    	 */
    	private String name = ExpressConstant.NEW_NAME_TASK;

    	/**
    	 * goal : String -> The goal of the task. Can be null
    	 */
    	private String goal = "";

    	/**
    	 * resources : String -> Resources ???
    	 */
    	private String resources = "";

    	/**
    	 * feed : String -> Task feedback, not really used
    	 */
    	private String feed = "";

	    /**
	     * duration : String -> Task duration - can be of either form. If only a number, can be interpreted
	     */
	    private String duration = "";

	    /**
	     * description : String -> informal description of the task. Can be null, but not recommended
	     */
	    private String description = "";

	    /**
	     * frequency : Frequence -> Enumerated value for frequency
	     */
	    private Frequence frequency = Frequence.INCONNU;
	    
	    /**
	     * frequencyValue : String -> Value for the frequency. Free text, no interpretation
	     */
	    private String frequencyValue = "";

	    /**
	     * importance : Importance -> Enumerated value for importance
	     */
	    private Importance imp = Importance.INCONNU;

	    private String media = "";

	    private Media idMedia = null;

    // Dynamic attributes
	    
	    /**
	     * optional = Boolean -> is the task optional - default value false
	     */
	    private Boolean optional = false;

	    /**
	     * interruptible : Boolean -> is the task interruptible - default value false
	     */
	    private Boolean interruptible = false;

	    /**
	     * ordering : Decomposition -> Enumerated value for task ordering, including leaf
	     */
	    private Decomposition ordering;
	    
    // Structural attributes
	    
	    /**
	     * number : String -> computed value for the numbering of tasks: root, 1, 1.1, 1.1.1, etc.
	     */
	    private String number = null;

	    /**
	     * label : Label -> Free characterization of tasks, associated to a color
	     */
	    private Label label;

	    /**
	     * mere : Task -> Super-task of the current task. Null if the task is root, or if it is 
	     *                        not attached to the tree
	     */
	    private Tache mere = null;
	    
	    /**
	     * fils : ArrayList<Tache> -> ordered list of sub-tasks
	     */
	    private ArrayList<Tache> fils = new ArrayList<Tache>();

    // Actors
	    
	    /**
	     * acteurs : ArrayList<Acteur> -> Human actors involved in the task
	     */
	    private ArrayList<Acteur> acteurs = new ArrayList<Acteur>();

	    /**
	     * acteurSysteme : ArrayList<ActeurSysteme> -> System actors involved in the task
	     */
	    private ArrayList<ActeurSysteme> acteurSysteme = new ArrayList<ActeurSysteme>();

    // Expressions management

	    /**
	     * preExpression : PreExpression -> Precondition of the task
	     */
	    private PreExpression preExpression;

	    /**
	     * iterExpression : IterExpression -> condition for the iteration of the task
	     */
	    private IterExpression iteExpression;

	    /**
	     * effetsDeBordExpression : EffetsDeBordExpression -> Side effects for the task (action)
	     */
	    private EffetsDeBordExpression effetsDeBordExpression;

    // Event management
	    
	    /**
	     * lstEvent : ArrayList<Evenement> -> list of event the task may fire. Often empty
	     */
	    private ArrayList<Evenement> lstEvent = new ArrayList<Evenement>();
	    
	    /**
	     * declencheur : Evenement -> the possible firing event. May be null
	     */
	    private Evenement declencheur = null;
	    
    // Graphical attributes

	    /**
	     * Graphical position of the task on the graphical layout
	     */
	    private Point point = null;

	    /**
	     * noPoint : Boolean -> States if the oint owns a valid graphical position or no. Default false
	     */
	    private boolean noPoint = false;

	    // Graphical optimization
	    private Object refJTask = null;

    // Interactive attributes, for copy-paste
    
	    private Tache taskCloned = null;

	    private Tache newTask = null;

	    private boolean motherUsed = false;

    // Attributes cfor prototyping and simulating

	    private StateSimulation stateSimulation;
    
	    //PROTOTASK
	    private StateExecution stateExecution;
    
	    //PROTOTASK
	    private ChoiceEnum IterationValue = ChoiceEnum.indeterminee;

    /**
     * Creation d'une tache avec des valeurs par defaut.
     */
    public Tache() {
	this.name = ExpressConstant.NEW_NAME_TASK;
	this.goal = "";
	this.resources = "";
	this.feed = "";
	this.duration = "";
	this.description = "";
	this.executant = Executant.INCONNU;
	this.imp = Importance.INCONNU;
	this.modality = Modalite.COGN;
	this.frequency = Frequence.INCONNU;
	this.frequencyValue = "";
	this.lstEvent = new ArrayList<Evenement>();
	this.optional = false;
	this.interruptible = false;
	this.ordering = Decomposition.ELE;
	this.fils = new ArrayList<Tache>();
	this.mere = null;
	this.number = ExpressConstant.ROOT_TASK_NAME;
	this.point = null;
	this.idMedia = null;
	this.declencheur = null;
	this.label = null;
	this.preExpression = new PreExpression();
	this.effetsDeBordExpression = new EffetsDeBordExpression();
	this.iteExpression = new IterExpression();
	this.stateSimulation = new StateSimulation();
	this.stateSimulation = new StateSimulation();

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
     * this.interruptible = interruptible; this.setActeurs(act);
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
	return this.idMedia;
    }

    public void setMedia(Media m) {
	this.idMedia = m;
    }

    public void setActeurs(ArrayList<Acteur> e) {
	acteurs = e;
	for (int i = 0; i < acteurs.size(); i++) {
	    acteurs.get(i).setInverseTache(this);
	}
    }

    public void setActeurSystem(ArrayList<ActeurSysteme> e) {
	acteurSysteme = e;
	for (int i = 0; i < acteurs.size(); i++) {
	    acteurs.get(i).setInverseTache(this);
	}
    }

    public boolean addActeur(Acteur a) {
	for (int i = 0; i < acteurs.size(); i++) {
	    Acteur act = acteurs.get(i);
	    if (act.getUserRef().getName().equals(a.getUserRef().getName())) {
		return false;
	    }
	}
	acteurs.add(a);
	a.setInverseTache(this);
	return true;

    }

    public boolean addActeurSystem(ActeurSysteme a) {
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    ActeurSysteme act = acteurSysteme.get(i);
	    if (act.getMaterielRef().getOid()
		    .equals(a.getMaterielRef().getOid())) {
		return false;
	    }
	}
	acteurSysteme.add(a);
	a.setInverseTache(this);
	return true;

    }

    public void removeActeur(Acteur a) {
	acteurs.remove(a);
	a = null;
    }

    public void removeActeurSysteme(ActeurSysteme a) {
	acteurSysteme.remove(a);
	a = null;
    }

    public void setEvents(ArrayList<Evenement> e) {
	lstEvent = e;
	for (int i = 0; i < lstEvent.size(); i++) {
	    lstEvent.get(i).addInverseTache(this);
	}
    }

    public boolean addEvent(Evenement a) {
	if (lstEvent.indexOf(a) == -1) {
	    lstEvent.add(a);
	    a.addInverseTache(this);
	    return true;
	}
	return false;
    }

    public boolean isEventHere(Evenement a) {
	return (lstEvent.indexOf(a) != -1);
    }

    public void removeEvent(Evenement a) {
	if (declencheur == a) {
	    declencheur = null;
	}
	lstEvent.remove(a);
    }

    public void setExecutant(Executant s) {
	executant = s;
    }

    public Executant getExecutant() {
	return this.executant;
    }

    public void setFreq(Frequence s) {
	frequency = s;
    }

    public void setCompFreq(String s) {
	frequencyValue = s;
    }

    public void setImp(Importance s) {
	imp = s;
    }

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

    public void setModal(Modalite modalite) {
	modality = modalite;
    }

    public Decomposition getDecomposition() {
	return this.ordering;
    }

    public void setDecomposition(Decomposition d) {
	ordering = d;
    }

    public ArrayList<Tache> getFils() {
	return fils;
    }

    public void setFils(ArrayList<Tache> pSubTasks) {
	this.fils = pSubTasks;
    }

    public ArrayList<Acteur> getActeurs() {
	return acteurs;
    }

    public ArrayList<ActeurSysteme> getActeurSysteme() {
	return acteurSysteme;
    }

    public ArrayList<String> getActeursName() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurs.size(); i++) {
	    lst.add(acteurs.get(i).getUserRef().getName());
	}
	return lst;
    }

    public ArrayList<String> getActeurSystemeOid() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    lst.add(acteurSysteme.get(i).oid.get());
	}
	return lst;
    }

    public ArrayList<User> getUsers() {
	ArrayList<User> lst = new ArrayList<User>();
	for (int i = 0; i < acteurs.size(); i++) {
	    lst.add(acteurs.get(i).getUserRef());
	}
	return lst;
    }

    public ArrayList<Materiel> getMateriels() {
	ArrayList<Materiel> lst = new ArrayList<Materiel>();
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    lst.add(acteurSysteme.get(i).getMaterielRef());
	}
	return lst;
    }

    public ArrayList<String> getActeursExp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurs.size(); i++) {
	    lst.add(acteurs.get(i).getExperience().getValue());
	}
	return lst;
    }

    public ArrayList<String> getActeurSystemeExp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    lst.add(acteurSysteme.get(i).getExperience().getValue());
	}
	return lst;
    }

    public ArrayList<String> getActeursComp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurs.size(); i++) {
	    lst.add(acteurs.get(i).getCompetence());
	}
	return lst;
    }

    public ArrayList<String> getActeurSystemeComp() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    lst.add(acteurSysteme.get(i).getCompetence());
	}
	return lst;
    }

    public ArrayList<Evenement> getEvents() {
	return this.lstEvent;
    }

    public ArrayList<String> getEventsName() {
	ArrayList<String> lst = new ArrayList<String>();
	for (int i = 0; i < lstEvent.size(); i++) {
	    lst.add(lstEvent.get(i).getName());
	}
	return lst;
    }

    public void setDeclencheur(Evenement e) {
	if (declencheur != null) {
	    declencheur.removeInverseTache(this);
	}

	declencheur = e;
	if (e != null)
	    declencheur.addInverseTache(this);
    }

    public Evenement getDeclencheur() {
	return this.declencheur;
    }

    public String getDeclencheurName() {
	return this.declencheur != null ? this.getDeclencheur().getName() : "";
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBut() {
	return this.goal;
    }

    public void setBut(String v) {
	this.goal = v;
    }

    public Tache getMotherTask() {
	return this.mere;
    }

    public void setMotherTask(Tache pMere) {
	this.mere = pMere;
    }

    public String getMotherTaskName() {
	if (this.mere == null) {
	    return KMADEConstant.NO_MOTHER_TASK_NAME_MESSAGE;
	} else {
	    return this.getMotherTask().getName();
	}
    }

    public String getRessources() {
	return this.resources;
    }

    public void setRessources(String p) {
	this.resources = p;
    }

    public String getDuree() {
	return this.duration;
    }

    public void setDuree(String v) {
	this.duration = v;
    }

    public String getObservation() {
	return this.description;
    }

    public void setObservation(String p) {
	this.description = p;
    }

    public String getFeedBack() {
	return this.feed;
    }

    public void setFeedBack(String v) {
	this.feed = v;
    }

    public Frequence getFrequence() {
	return this.frequency;
    }

    public Modalite getModalite() {
	return this.modality;
    }

    public Decomposition getOrdonnancement() {
	return this.ordering;
    }

    public Importance getImportance() {
	return this.imp;
    }

    public String getNumero() {
	return number;
    }

    public int getPlace() {
	return (mere == null) ? -1 : this.mere.fils.indexOf(this);
    }

    public Boolean isFacultatif() {
	return this.optional;
    }

    public Boolean isInterruptible() {
	return this.interruptible;
    }
    
    public boolean hasNoPoint(){
	return this.noPoint;
    }

    /**
     * Cette mï¿½thode permet de supprimer une sous-tï¿½che.
     * 
     * @param tachefils
     * @return
     */
    public ArrayList<Tache> removeSubTask(Tache tachefils) {
	// 1. supprimer la reference Ã  la tÃ¢che mere
	// 2. supprimer la reference aux taches filles
	// 3. modifier le numero de chaque tache fille
	// 4. verifier le numero des tï¿½ches soeurs

	ArrayList<Tache> numeroTacheModifie = new ArrayList<Tache>();

	// A. traitement des nouveaux sous-arbres gï¿½rï¿½nï¿½s par la
	// suppression
	// 2. supprimer la reference aux taches filles
	tachefils.mere = null;
	// 3. modifier le numero de chaque tache fille
	tachefils.number = ExpressConstant.ROOT_TASK_NAME;
	tachefils.setDeriveTaskNumero(0);
	numeroTacheModifie.add(tachefils);

	// B. traitement des soeurs de la tache supprimï¿½e
	// 1. supprimer la reference ï¿½ la tï¿½che mere
	this.fils.remove(tachefils);
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
    public ArrayList<Tache> removeTask() {
	// 1. Supprimer la reference Ã  la tÃ¢che mere
	// 2. Supprimer la reference aux taches filles
	// 3. Modifier le numero de chaque tache fille
	// 4. VÃ©rifier le numero des tÃ¢ches soeurs
	// 5. Supprimer Point
	// 6. Supprimer relation avec les Ã©vÃ©nements
	// 7. Supprimer relation avec les utilisateurs
	// 8. Supprimer relation avec le libellÃ©
	// 9. Supprimer la tache

	ArrayList<Tache> numeroTacheModifie = new ArrayList<Tache>();

	// A Traitement des nouveaux sous-arbres gÃ©rÃ©nÃ©s par la suppression
	int taille = this.fils.size();
	for (int i = 0; i < taille; i++) {
	    Tache t = (Tache) this.fils.get(i);
	    // 2. supprimer la reference aux taches filles
	    t.mere = null;
	    // 3. modifier le numero de chaque tache fille
	    t.number = ExpressConstant.ROOT_TASK_NAME;
	    t.setDeriveTaskNumero(0);
	    numeroTacheModifie.add(t);
	}

	// B. traitement des soeurs de la tache supprimÃ©e
	if (this.mere != null) {
	    // 1. supprimer la reference Ã  la tÃ¢che mere
	    this.mere.fils.remove(this);
	    // 4. verifier le numero des tï¿½ches soeurs
	    this.mere.setDeriveTaskNumero(0);
	    numeroTacheModifie.add(this.mere);
	}

	// 5. supprimer Point
	InterfaceExpressJava.remove(this.point.oid);

	// 6. Supprimer relation avec les Ã©vÃ©nements.
	if (this.declencheur != null) {
	    this.declencheur.removeInverseTache(this);
	}

	for (Evenement current : lstEvent) {
	    current.removeInverseTache(this);
	}

	// 7. Supprimer relation avec les acteurs (les supprimer)
	Acteur[] arrayCopy = new Acteur[this.acteurs.size()];
	arrayCopy = this.acteurs.toArray(arrayCopy);
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
     * Donne le numéro de la tache selon son positionnement dans les fils
     */
    private static int place(Tache leFils, Tache laMere) {
	int pointX = (leFils.point.x).intValue();
	int px = 0;
	int taille = laMere.fils.size();
	for (int i = 0; i < taille; i++) {
	    px = (((Tache) laMere.fils.get(i)).point.x).intValue();
	    if (pointX < (px))
		return i;
	}
	return taille;
    }

    /**
     * 
     * @param tachefils
     * @return
     */
    public void addSubTask(Tache tachefils) {
	mereInverse(tachefils);
	int place = place(tachefils, this);
	fils.add(place, tachefils);
	this.setDeriveTaskNumero(place);
    }

    private void setDeriveTaskNumero(int place) {
	String s = this.number;
	if (s.startsWith(ExpressConstant.ROOT_TASK_NAME))
	    s = "";
	if (this.mere == null)
	    s = "";
	for (int i = place; i < this.fils.size(); i++) {
	    Tache tmp = (Tache) this.fils.get(i);
	    if (s.length() == 0)
		tmp.number = "" + (i + 1);
	    else
		tmp.number = s + "." + (i + 1);
	    // attention a la memoire...
	    tmp.setDeriveTaskNumero(0);
	}
    }

    public void fixerPoint(int x, int y) {
	this.point.x = new Integer(x);
	this.point.y = new Integer(y);
    }

    /**
     * revoie les oids ï¿½ mettre ï¿½ jours si il y a eu des modification dans
     * la numerotation d'une tÃ¢che mÃ¨re valable pour le "collage" et le
     * "dÃ©collage"
     * 
     * @param x
     * @param y
     * @return
     */
    public Tache[] modifierPoint(int x, int y) {
	this.point.x = new Integer(x);
	this.point.y = new Integer(y);
	if (mere == null)
	    return null;
	int placeOld = mere.fils.indexOf(this);
	// Mickael BARON : Tache dec = (Tache) mere.fils.remove(placeOld);
	mere.fils.remove(placeOld);
	int placeNew = place(this, mere);
	mere.fils.add(placeNew, this);
	int debut = 0;
	if (placeNew == placeOld)
	    return null;
	if (placeNew < placeOld)
	    debut = placeNew;
	if (placeOld < placeNew)
	    debut = placeOld;
	mere.setDeriveTaskNumero(debut);

	int taille = mere.fils.size();
	Tache[] taches = new Tache[taille];
	for (int i = 0; i < taille; i++) {
	    taches[i] = ((Tache) mere.fils.get(i));
	}
	return taches;
    }

    public String toString() {
	return this.number + " - " + name;
    }

    // attention il ne faut pas utilisï¿½ le tag postcondition pour les
    // postcondition de la V2 !
    // ils sont rï¿½server aux effets de bord de la v1!
    public org.w3c.dom.Element toXML(Document doc) {

	Element racine = doc.createElement("task");
	racine.setAttribute("classkmad", "tache.Tache");
	racine.setAttribute("idkmad", oid.get());

	// Name
	Element kmadElement = doc.createElement("task-name");
	kmadElement.setTextContent(this.name);
	racine.appendChild(kmadElement);

	// But
	if (!this.goal.equals("")) {
	    kmadElement = doc.createElement("task-purpose");
	    kmadElement.setTextContent(this.goal);
	    racine.appendChild(kmadElement);
	}

	// Media
	if (this.idMedia != null) {
	    kmadElement = doc.createElement("id-task-media");
	    kmadElement.setTextContent(this.idMedia.getOid().get());
	    racine.appendChild(kmadElement);
	}

	// Duration
	if (!this.duration.equals("")) {
	    kmadElement = doc.createElement("task-duration");
	    kmadElement.setTextContent(this.duration);
	    racine.appendChild(kmadElement);
	}

	// Media
	if (!this.media.equals("")) {
	    kmadElement = doc.createElement("task-media");
	    kmadElement.setTextContent(this.media);
	    racine.appendChild(kmadElement);
	}

	// Resources
	if (!this.resources.equals("")) {
	    kmadElement = doc.createElement("task-resources");
	    kmadElement.setTextContent(this.resources);
	    racine.appendChild(kmadElement);
	}

	// Feedback
	if (!this.feed.equals("")) {
	    kmadElement = doc.createElement("task-feedback");
	    kmadElement.setTextContent(this.feed);
	    racine.appendChild(kmadElement);
	}

	// Observation
	if (!this.description.equals("")) {
	    kmadElement = doc.createElement("task-observation");
	    kmadElement.setTextContent(this.description);
	    racine.appendChild(kmadElement);
	}

	// Executant
	racine.appendChild(this.executant.toXML(doc));

	// Frequency
	if (!this.frequency.equals(Frequence.INCONNU)) {
	    racine.appendChild(this.frequency.toXML(doc));
	}

	// Frequency Value
	if (!this.frequencyValue.equals("")) {
	    kmadElement = doc.createElement("task-compfrequency");
	    kmadElement.setTextContent(this.frequencyValue);
	    racine.appendChild(kmadElement);
	}

	// Importance
	if (!this.imp.equals(Importance.INCONNU)) {
	    racine.appendChild(this.imp.toXML(doc));
	}

	// Modality
	if (!this.modality.equals(Modalite.INCONNU)) {
	    racine.appendChild(this.modality.toXML(doc));
	}

	// EventTrigger
	if (this.declencheur != null) {
	    kmadElement = doc.createElement("id-task-eventtrigger");
	    kmadElement.setTextContent(this.declencheur.getOid().get());
	    racine.appendChild(kmadElement);
	}

	// Events
	if (this.lstEvent.size() != 0) {
	    Element eventList = doc.createElement("id-task-events-list");
	    for (int i = 0; i < this.lstEvent.size(); i++) {
		Element idEvent = doc.createElement("id-task-event");
		idEvent.setTextContent(lstEvent.get(i).getOid().get());
		eventList.appendChild(idEvent);
	    }
	    racine.appendChild(eventList);
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
	racine.appendChild(this.ordering.toXML(doc));

	// Actors
	if (this.acteurs.size() != 0) {
	    Element actorsList = doc.createElement("id-task-actors-list");
	    for (int i = 0; i < this.acteurs.size(); i++) {
		Element idActor = doc.createElement("id-task-actor");
		idActor.setTextContent(this.acteurs.get(i).getOid().get());
		actorsList.appendChild(idActor);
	    }
	    racine.appendChild(actorsList);
	}
	// Actors system
	if (this.acteurSysteme.size() != 0) {
	    Element actorSystemList = doc
		    .createElement("id-task-actorSystem-list");
	    for (int i = 0; i < this.acteurSysteme.size(); i++) {
		Element idActor = doc.createElement("id-task-actorSystem");
		idActor.setTextContent(this.acteurSysteme.get(i).getOid().get());
		actorSystemList.appendChild(idActor);
	    }
	    racine.appendChild(actorSystemList);
	}

	// Sub-tasks
	if (this.fils.size() != 0) {
	    Element subTasksList = doc.createElement("id-task-subtasks-list");
	    for (int i = 0; i < this.fils.size(); i++) {
		Element idActor = doc.createElement("id-task-subtask");
		idActor.setTextContent(this.fils.get(i).getOid().get());
		subTasksList.appendChild(idActor);
	    }
	    racine.appendChild(subTasksList);
	}

	// Point
	kmadElement = doc.createElement("id-task-point");
	kmadElement.setTextContent(this.point.getOid().get());
	racine.appendChild(kmadElement);

	// Label
	if (this.label != null) {
	    kmadElement = doc.createElement("id-task-label");
	    kmadElement.setTextContent(this.label.getOid().get());
	    racine.appendChild(kmadElement);
	}

	// Precondition
	kmadElement = doc.createElement("task-precondition");
	kmadElement.setTextContent(this.preExpression.getName());
	racine.appendChild(kmadElement);

	// Precondition Description
	if (!this.getPreExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptionprecondition");
	    kmadElement.setTextContent(this.preExpression.getDescription());
	    racine.appendChild(kmadElement);
	}

	// attention il ne faut pas utilisï¿½ le tag postcondition pour les
	// postcondition de la V2 !
	// ils sont rï¿½server aux effets de bord de la v1!
	// EffetsDeBord
	kmadElement = doc.createElement("task-effetsdebord");
	kmadElement.setTextContent(this.effetsDeBordExpression.getName());
	racine.appendChild(kmadElement);

	// EffetsDeBord Description
	if (!this.getEffetsDeBordExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptioneffetsdebord");
	    kmadElement.setTextContent(this.effetsDeBordExpression
		    .getDescription());
	    racine.appendChild(kmadElement);
	}

	// Iteration
	kmadElement = doc.createElement("task-iteration");
	kmadElement.setTextContent(this.iteExpression.getName());
	racine.appendChild(kmadElement);

	// Iteration Description
	if (!this.getIteExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptioniteration");
	    kmadElement.setTextContent(this.iteExpression.getDescription());
	    racine.appendChild(kmadElement);
	}

	return racine;
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
	    this.idMedia = (Media) InterfaceExpressJava.bdd.prendre(new Oid(
		    nodeList.item(0).getTextContent()));
	}

	// Resources
	nodeList = p.getElementsByTagName("task-resources");
	if (nodeList.item(0) != null) {
	    this.resources = nodeList.item(0).getTextContent();
	}

	// Feedback
	nodeList = p.getElementsByTagName("task-feedback");
	if (nodeList.item(0) != null) {
	    this.feed = nodeList.item(0).getTextContent();
	}

	// Observation
	nodeList = p.getElementsByTagName("task-observation");
	if (nodeList.item(0) != null) {
	    this.description = nodeList.item(0).getTextContent();
	}

	// Executant
	this.executant = Executant.getXMLExecutantValue(p);

	// Frequence
	this.frequency = Frequence.getXMLFrequenceValue(p);

	// Valeur de la Frequence
	nodeList = p.getElementsByTagName("task-compfrequency");
	if (nodeList.item(0) != null) {
	    this.frequencyValue = nodeList.item(0).getTextContent();
	}

	// Importance
	this.imp = Importance.getXMLExecutantValue(p);

	// Modality
	this.modality = Modalite.getXMLModalityValue(p);

	// Triggering Event
	nodeList = p.getElementsByTagName("id-task-eventtrigger");
	if (nodeList.item(0) != null) {
	    Evenement event = (Evenement) InterfaceExpressJava.bdd
		    .prendre(new Oid(nodeList.item(0).getTextContent()));
	    this.declencheur = event;
	    this.declencheur.addInverseTache(this);
	}

	// Generated Events
	nodeList = p.getElementsByTagName("id-task-events-list");
	if (nodeList.getLength() != 0) {
	    NodeList nodeListEvent = nodeList.item(0).getChildNodes();
	    for (int i = 0; i < nodeListEvent.getLength(); i++) {
		if (nodeListEvent.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    Evenement event = (Evenement) InterfaceExpressJava.bdd
			    .prendre(new Oid(nodeListEvent.item(i)
				    .getTextContent()));
		    this.lstEvent.add(event);
		    event.addInverseTache(this);
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
		    Acteur acteur = (Acteur) InterfaceExpressJava.bdd
			    .prendre(new Oid(nodeListActors.item(i)
				    .getTextContent()));
		    this.acteurs.add(acteur);
		    acteur.setInverseTache(this);
		}
	    }
	}

	// Actors System
	nodeList = p.getElementsByTagName("id-task-actorSystem-list");
	if (nodeList.getLength() != 0) {
	    NodeList nodeListActors = nodeList.item(0).getChildNodes();
	    for (int i = 0; i < nodeListActors.getLength(); i++) {
		if (nodeListActors.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    ActeurSysteme acteurSysteme = (ActeurSysteme) InterfaceExpressJava.bdd
			    .prendre(new Oid(nodeListActors.item(i)
				    .getTextContent()));
		    this.acteurSysteme.add(acteurSysteme);
		    acteurSysteme.setInverseTache(this);
		}
	    }
	}
	// Subtasks
	nodeList = p.getElementsByTagName("id-task-subtasks-list");
	if (nodeList.getLength() != 0) {
	    NodeList nodeListSubTasks = nodeList.item(0).getChildNodes();
	    for (int i = 0; i < nodeListSubTasks.getLength(); i++) {
		if (nodeListSubTasks.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    Tache tache = (Tache) InterfaceExpressJava.bdd
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
	    this.effetsDeBordExpression = new EffetsDeBordExpression(nodeList
		    .item(0).getTextContent());
	}
	// attention il ne faut pas utiliser le tag postcondition pour les
	// postcondition de la V2 !
	nodeList = p.getElementsByTagName("task-postcondition");
	if (nodeList.item(0) != null) {
	    this.effetsDeBordExpression = new EffetsDeBordExpression(nodeList
		    .item(0).getTextContent());
	}

	// attention il ne faut pas utiliser le tag postcondition pour les
	// postcondition de la V2 !
	nodeList = p.getElementsByTagName("task-descriptionpostcondition");
	if (nodeList.item(0) != null) {
	    this.effetsDeBordExpression.setDescription(nodeList.item(0)
		    .getTextContent());
	}
	// EffetsDeBord Description
	nodeList = p.getElementsByTagName("task-descriptioneffetsdebord");
	if (nodeList.item(0) != null) {
	    this.effetsDeBordExpression.setDescription(nodeList.item(0)
		    .getTextContent());
	}

	// Iteration
	nodeList = p.getElementsByTagName("task-iteration");
	this.iteExpression = new IterExpression(nodeList.item(0)
		.getTextContent());

	// Iteration Description
	nodeList = p.getElementsByTagName("task-descriptioniteration");
	if (nodeList.item(0) != null) {
	    this.iteExpression
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
		+ "," + "'" + resources + "'" + "," + "'" + feed + "'" + ","
		+ "'" + duration + "'" + "," + "'" + description + "'" + ","
		+ executant.toSPF() + "," + frequency.toSPF() + ",'" + frequencyValue
		+ "'," + imp.toSPF() + "," + modality.toSPF() + ",";
	if (declencheur != null)
	    SPF = SPF + declencheur.oid.get() + ",";
	else
	    SPF = SPF + "$,";
	// events
	SPF = SPF + "(";
	for (int i = 0; i < lstEvent.size(); i++) {
	    SPF = SPF + lstEvent.get(i).oid.get();
	    if (i != lstEvent.size())
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
	// acteurs
	SPF = SPF + ",(";
	for (int i = 0; i < acteurs.size(); i++) {
	    SPF = SPF + acteurs.get(i).oid.get();
	    if (i != acteurs.size())
		SPF = SPF + ",";
	}
	SPF = SPF + "),";
	// acteurs System
	SPF = SPF + "(";
	for (int i = 0; i < acteurSysteme.size(); i++) {
	    SPF = SPF + acteurSysteme.get(i).oid.get();
	    if (i != acteurSysteme.size())
		SPF = SPF + ",";
	}
	SPF = SPF + "),";
	// fils
	SPF = SPF + "(";
	for (int i = 0; i < fils.size(); i++) {
	    SPF = SPF + fils.get(i).oid.get();
	    if (i != fils.size())
		SPF = SPF + ",";
	}
	SPF = SPF + "),";
	// Media
	if (idMedia == null) {
	    // Ne rien faire
	} else {
	    SPF = SPF + idMedia.oid.get() + ",";
	}
	// Point
	if (point == null) {
	    // SPF = SPF;
	} else {
	    SPF = SPF + point.oid.get() + ",";
	}
	SPF = SPF + ",'" + preExpression.getName().replaceAll("'", "\\\\'")
		+ "'," + "'"
		+ effetsDeBordExpression.getName().replaceAll("'", "\\\\'")
		+ "'," + "'" + iteExpression.getName().replaceAll("'", "\\\\'")
		+ "')";
	return SPF;
    }

    /**
     * 
     * @param tache
     */
    protected void mereInverse(Tache tache) {
	tache.mere = this;
    }

    /**
     * 
     * @return
     */
    public Tache mere() {
	return this.mere;
    }

    /**
     * 
     * @param oid
     */
    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    /**
     * Cette mï¿½thode retourne le point de la tï¿½che.
     * 
     * @return Returns the point.
     */
    public Point getPoint() {
	return point;
    }

    /**
     * Cette mï¿½thode modifie le point de la tï¿½che.
     * 
     * @param point
     *            The point to set.
     */
    public void setPoint(Point point) {
	this.point = point;
    }

    public Tache getOldSisterTask() {
	if (this.mere == null) {
	    return null;
	}
	ArrayList<Tache> mySon = this.mere.getFils();
	if (mySon == null) {
	    return null;
	}
	int index = mySon.indexOf(this);
	if (index <= 0) {
	    return null;
	}
	return (mySon.get(index - 1));
    }

    public Tache getYoungSisterTask() {
	if (this.mere == null) {
	    return null;
	}
	ArrayList<Tache> mySon = this.mere.getFils();
	if (mySon == null) {
	    return null;
	}
	int index = mySon.indexOf(this);
	if (index >= mySon.size() - 1) {
	    return null;
	}
	return (mySon.get(index + 1));
    }

    public Tache getFirstSonTask() {
	ArrayList<Tache> mySon = this.getFils();
	if (mySon == null || mySon.size() == 0) {
	    return null;
	}
	return (mySon.get(0));
    }

    /**
     * @return Returns the taskCloned.
     */
    public Tache getTaskCloned() {
	return taskCloned;
    }

    /**
     * @param taskCloned
     *            The taskCloned to set.
     */
    public void setTaskCloned(Tache taskCloned) {
	this.taskCloned = taskCloned;
    }

    /**
     * @return Returns the motherUsed.
     */
    public boolean isMotherUsed() {
	return motherUsed;
    }

    /**
     * @param b
     */
    public void setMotherUsed(boolean b) {
	this.motherUsed = b;
    }

    /**
     * @return Returns the newTask.
     */
    public Tache getNewTask() {
	return newTask;
    }

    /**
     * @param newTask
     *            The newTask to set.
     */
    public void setNewTask(Tache newTask) {
	this.newTask = newTask;
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
    public void setFacultatif(Boolean facultatif) {
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
     * @return Returns the compFreq.
     */
    public String getCompFreq() {
	return frequencyValue;
    }

    /**
     * @return Returns the refJTask.
     */
    public Object getJTask() {
	return refJTask;
    }

    /**
     * @param refJTask
     *            The refJTask to set.
     */
    public void setJTask(Object refJTask) {
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

    public EffetsDeBordExpression getEffetsDeBordExpression() {
	return effetsDeBordExpression;
    }

    public void setEffetsDeBordExpression(
	    EffetsDeBordExpression effetsDeBordExpression) {
	this.effetsDeBordExpression = effetsDeBordExpression;
    }

    public IterExpression getIteExpression() {
	return iteExpression;
    }

    public void setIterExpression(IterExpression iterExpression) {
	this.iteExpression = iterExpression;
    }

    public StateSimulation getStateSimulation() {
	return this.stateSimulation;
    }

    public void setStateSimulation(StateSimulation pSimulation) {
	this.stateSimulation = pSimulation;
    }

    public boolean isLeaf() {
	return this.getFils() != null ? this.getFils().size() == 0 : true;
    }

    public boolean isRoot() {
	return (this.mere == null);
    }

    public static boolean canHaveActor(Executant ex) {
	if (ex == Executant.SYS) {
	    return false;
	} else {
	    return true;
	}
    }

    public boolean canHaveActor() {
	return canHaveActor(executant);
    }

    public static boolean canHaveActorSystem(Executant ex) {
	if (ex == Executant.USER) {
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
	// Resources
	if (!this.resources.equals("")) {
	    kmadElement = doc.createElement("task-resources");
	    kmadElement.setTextContent(this.resources);
	    racine.appendChild(kmadElement);
	}
	// Feedback
	if (!this.feed.equals("")) {
	    kmadElement = doc.createElement("task-feedback");
	    kmadElement.setTextContent(this.feed);
	    racine.appendChild(kmadElement);
	}
	// Observation
	//if (!this.observation.equals("")) {
	    kmadElement = doc.createElement("task-observation");
	    kmadElement.setTextContent(this.description);
	    racine.appendChild(kmadElement);
	//}
	// Executant
	racine.appendChild(this.executant.toXML2(doc));
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
	if (!this.imp.equals(Importance.INCONNU)) {
	    racine.appendChild(this.imp.toXML2(doc));
	}
	// Modality
	if (!this.modality.equals(Modalite.INCONNU)) {
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
	kmadElement.setTextContent(this.preExpression.getName());
	racine.appendChild(kmadElement);
	// Precondition Description
	if (!this.getPreExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptionprecondition");
	    kmadElement.setTextContent(this.preExpression.getDescription());
	    racine.appendChild(kmadElement);
	}
	// attention il ne faut pas utilise le tag postcondition pour les
	// postcondition de la V2 !
	// ils sont reserver aux effets de bord de la v1!
	// EffetsDeBord
	// ATTENTION optionnal in the v3
	if (!this.effetsDeBordExpression.getName().equals("Void")) {
	    kmadElement = doc.createElement("task-effetsdebord");
	    kmadElement.setTextContent(this.effetsDeBordExpression.getName());
	    racine.appendChild(kmadElement);
	}
	// EffetsDeBord Description
	if (!this.getEffetsDeBordExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptioneffetsdebord");
	    kmadElement.setTextContent(this.effetsDeBordExpression
		    .getDescription());
	    racine.appendChild(kmadElement);
	}
	// Iteration
	kmadElement = doc.createElement("task-iteration");
	kmadElement.setTextContent(this.iteExpression.getName());
	racine.appendChild(kmadElement);
	// Iteration Description
	if (!this.getIteExpression().getDescription().equals("")) {
	    kmadElement = doc.createElement("task-descriptioniteration");
	    kmadElement.setTextContent(this.iteExpression.getDescription());
	    racine.appendChild(kmadElement);
	}
	
	//Attributes
	racine.setAttribute("classkmad", "tache.Tache");
	racine.setAttribute("idkmad", oid.get());
	// Media
	if (this.idMedia != null && this.idMedia.isExisting()){
	    racine.setAttribute("id-task-media", this.idMedia.getOid().get());
	    racine.appendChild(this.idMedia.toXML2(doc));
	}
	// Event trigger
	if (this.declencheur != null){
	    racine.setAttribute("id-task-eventtrigger", this.declencheur
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
	if (!this.lstEvent.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < this.lstEvent.size(); i++) {
		list += lstEvent.get(i).getOid().get() + " ";
	    }
	    racine.setAttribute("id-task-events-list", list);
	}
	// Actors
	if (!this.acteurs.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < this.acteurs.size(); i++) {
		list += acteurs.get(i).getOid().get() + " ";
		racine.appendChild(this.acteurs.get(i).toXML2(doc));
	    }
	    racine.setAttribute("id-task-actors-list", list);
	}
	// Actors System
	if (!this.acteurSysteme.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < this.acteurSysteme.size(); i++) {
		list += acteurSysteme.get(i).getOid().get() + " ";
		racine.appendChild(this.acteurSysteme.get(i).toXML2(doc));
	    }
	    racine.setAttribute("id-task-actorSystem", list);
	}
	// Subtasks
	if (!this.fils.isEmpty()) {
	    String list = new String("");
	    for (int i = 0; i < this.fils.size(); i++) {
		list += fils.get(i).getOid().get() + " ";
		racine.appendChild(this.fils.get(i).toXML2(doc));
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
      	this.lstEvent.clear();
        this.acteurs.clear();
        this.acteurSysteme.clear();
        this.fils.clear();
	this.oid = new Oid(p.getAttribute("idkmad"));
	// Media
	if (p.hasAttribute("id-task-media)"))
	    this.idMedia = (Media) InterfaceExpressJava.bdd.prendre(new Oid(p
		    .getAttribute("id-task-media")));
	// Triggering Event
	if (p.hasAttribute("id-task-eventtrigger"))
	    this.declencheur = (Evenement) InterfaceExpressJava.bdd.prendre(new Oid(p.getAttribute("id-task-eventtrigger")));
	// Generated Events
	if (p.hasAttribute("id-task-events-list")) {
	    String[] events = p.getAttribute("id-task-events-list").split(" ");
	    for (int i = 0; i < events.length; i++) {
		Evenement event = (Evenement) InterfaceExpressJava.bdd.prendre(new Oid(events[i]));
		this.lstEvent.add(event);
		event.addInverseTache(this);
	    }
	}
	// Actors
	if (p.hasAttribute("id-task-actors-list")) {
	    String[] actors = p.getAttribute("id-task-actors-list").split(" ");
	    for (int i = 0; i < actors.length; i++) {
		this.addActeur((Acteur) InterfaceExpressJava.bdd.prendre(new Oid(actors[i])));
		this.acteurs.get(i).setInverseTache(this);
	    }
	}
	// Actors system
	if (p.hasAttribute("id-task-actorSystem-list")) {
	    String[] actorSys = p.getAttribute("id-task-actorSystem-list")
		    .split(" ");
	    for (int i = 0; i < actorSys.length; i++) {
		this.addActeurSystem((ActeurSysteme) InterfaceExpressJava.bdd
			.prendre(new Oid(actorSys[i])));
		this.acteurSysteme.get(i).setInverseTache(this);
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
	    for (int i = 0; i < son.length; i++) {
		if(!noPoint)
		    this.addSubTask((Tache) InterfaceExpressJava.bdd
			    .prendre(new Oid(son[i])));
		else{
		    this.fils.add((Tache) InterfaceExpressJava.bdd
			    .prendre(new Oid(son[i])));
		    this.mereInverse((Tache) InterfaceExpressJava.bdd
			    .prendre(new Oid(son[i])));
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

	// Resources
	nodeList = p.getElementsByTagName("task-resources");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null && nodeList.item(0) != null) {
	    this.resources = nodeList.item(0).getTextContent();
	}
	// Feedback
	nodeList = p.getElementsByTagName("task-feedback");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null && nodeList.item(0) != null) {
	    this.feed = nodeList.item(0).getTextContent();
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
	this.executant = Executant.getXMLExecutantValue2(p);
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
	this.imp = Importance.getXMLExecutantValue2(p);
	// Modality
	this.modality = Modalite.getXMLModalityValue2(p);
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

	// EffetsDeBord

	nodeList = p.getElementsByTagName("task-effetsdebord");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null && nodeList.item(0) != null) {
	    this.effetsDeBordExpression = new EffetsDeBordExpression(nodeList
		    .item(0).getTextContent());
	}
	// attention il ne faut pas utilisï¿½ le tag postcondition pour les
	// postcondition de la V2 !
	nodeList = p.getElementsByTagName("task-postcondition");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList.item(0) != null) {
	    this.effetsDeBordExpression = new EffetsDeBordExpression(nodeList
		    .item(0).getTextContent());
	}
	// attention il ne faut pas utilisï¿½ le tag postcondition pour les
	// postcondition de la V2 !
	nodeList = p.getElementsByTagName("task-descriptionpostcondition");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null && nodeList.item(0) != null) {
	    this.effetsDeBordExpression.setDescription(nodeList.item(0)
		    .getTextContent());
	}

	// EffetsDeBord Description
	nodeList = p.getElementsByTagName("task-descriptioneffetsdebord");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null&& nodeList.item(0) != null) {
	    this.effetsDeBordExpression.setDescription(nodeList.item(0)
		    .getTextContent());
	}
	// Iteration
	nodeList = p.getElementsByTagName("task-iteration");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	this.iteExpression = new IterExpression(nodeList.item(0)
		.getTextContent());
	// Iteration Description
	nodeList = p.getElementsByTagName("task-descriptioniteration");
	 if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		 nodeList = null;}
	if (nodeList!= null && nodeList.item(0) != null) {
	    this.iteExpression
		    .setDescription(nodeList.item(0).getTextContent());
	}

    }

    /**
     * Same as "oidIsAnyMissing" method adapted to the new dtd
     * 
     * @author Joachim TROUVERIE
     */
    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	// TODO Auto-generated method stub
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
	public ChoiceEnum getIterationValue() {
		return IterationValue;
	}

	public void setIterationValue(ChoiceEnum iterationValue) {
		IterationValue = iterationValue;
	}



}

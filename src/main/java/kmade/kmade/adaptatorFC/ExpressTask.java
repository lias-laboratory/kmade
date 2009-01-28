package kmade.kmade.adaptatorFC;

import java.util.ArrayList;
import java.util.Observer;

import kmade.kmade.UI.toolutilities.KMADEObservable;
import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.Acteur;
import kmade.nmda.schema.tache.Decomposition;
import kmade.nmda.schema.tache.Evenement;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Frequence;
import kmade.nmda.schema.tache.Importance;
import kmade.nmda.schema.tache.IterExpression;
import kmade.nmda.schema.tache.Media;
import kmade.nmda.schema.tache.Modalite;
import kmade.nmda.schema.tache.Point;
import kmade.nmda.schema.tache.PostExpression;
import kmade.nmda.schema.tache.PreExpression;
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

    public static ArrayList<Tache> getRootTasks() {
        Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Tache");
        ArrayList<Tache> rootTasks = new ArrayList<Tache>();
        for (int i = 0 ; i < taches.length ; i++) {
            if (((Tache)taches[i]).isRoot()) {
                rootTasks.add((Tache)taches[i]);
            }
        }
        return rootTasks;
    }
    
    public static Tache getTaskFromOid(String oid) {
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
            return (Tache)toto;
        } catch(Exception e) {
            return null;
        }
    }
    
    public static ArrayList<Tache> getTasksFromName(String name) {
        ArrayList<Tache> myTaskName = new ArrayList<Tache>();
        Object[] toto = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Tache");
        
        for (int i = 0 ; i < toto.length ; i++) {
            if (((Tache)toto[i]).equals(name)) {
                myTaskName.add((Tache)toto[i]);
            }
        }
        
        return myTaskName;        
    }
    
    public static void clearPreExpression(Tache pTache) {
        pTache.setPreExpression(new PreExpression());
        notifyObservers();
    }
    
    public static void clearPostExpression(Tache pTache) {
        pTache.setPostExpression(new PostExpression());
        notifyObservers();
    }
    
    public static void clearIterExpression(Tache pTache) {
        pTache.setIterExpression(new IterExpression());
        notifyObservers();
    }
    
    /**
     * Méthode qui permet de retourner toutes les tâches du modèle Express.
     */
    public static Tache[] getAllTaskFromExpress() {
        Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Tache");
        Tache[] myNewTask = new Tache[taches.length];
        for (int i = 0; i < taches.length; i++) {
            myNewTask[i] = (Tache)taches[i];
        }
        return myNewTask;
    }
    
    public static ArrayList<Tache> getTasksFromExpress() {
        Object[] taches = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Tache");
        ArrayList<Tache> toto = new ArrayList<Tache>();
        for (int i = 0; i < taches.length;i++) {
        		toto.add((Tache)taches[i]);
        }
        return toto;
    }
      
    /**
     * Cette méthode permet de coller des éléments du ClipBoard dans la base Express.
     */
    public static Tache[] pasteElementsFromClipBoard(java.awt.Point pt) {
        counterClipboard++;        
        Object[] myTaskList = InterfaceExpressJava.getAllReferencesOfEntityFromClipBoard("tache","Tache");
        
        Tache [] myNewTaskList = new Tache[myTaskList.length];
        for (int i = 0; i < myTaskList.length; i++) {
            // On crée un point et une tâche.
            Point refPoint = (Point)InterfaceExpressJava.createEntityReferenceBack("tache","Point");
            Tache refTache = (Tache)InterfaceExpressJava.createEntityReferenceBack("tache", "Tache");
            
            if (pt != null) {
                refPoint.setX(pt.x);
                refPoint.setY(pt.y);
            } else {
                // On pioche.
                Integer x = ((Tache)myTaskList[i]).getPoint().getX();
                Integer y = ((Tache)myTaskList[i]).getPoint().getY();
                
                // On modifie le contenu.
                refPoint.setX(new Integer(x.intValue() + ExpressTask.counterClipboard * 10));
                refPoint.setY(new Integer(y.intValue() + ExpressTask.counterClipboard * 10));
            }
            
            refTache.setPoint(refPoint);
            
            // On effectue la relation Clipboard et mod�le Express.
            refTache.setTaskCloned((Tache)myTaskList[i]);
            ((Tache)myTaskList[i]).setNewTask(refTache);
            myNewTaskList[i] = refTache;
        }
        
        // Création des liaisons.
        for (int i = 0; i< myTaskList.length;i ++) {
            ArrayList<Tache> mySubTasks = ((Tache)myTaskList[i]).getFils();
            if (mySubTasks.size() != 0) {
                ArrayList<Tache> myNewSubTasks = new ArrayList<Tache>();
                for (int j = 0; j < mySubTasks.size(); j++) {
                    myNewSubTasks.add(mySubTasks.get(j).getNewTask());
                    mySubTasks.get(j).getNewTask().setMotherTask(myNewTaskList[i]);                    
                }
                myNewTaskList[i].setFils(myNewSubTasks);
            }
        }
        
        return myNewTaskList;
    }
    
    /**
     * Cette m�thode permet de copier des t�ches dans le presse-papier.
     * @param taskOIDList
     * @param edgeOIDList
     */
    public static Tache[] copyElementsIntoClipBoard(ArrayList<Tache> myTacheList, ArrayList<String[]> edgeOIDList) {
        // On nettoie le presse-papier.
        counterClipboard = 0;        
        InterfaceExpressJava.clearClipBoard();
              
        // Pour l'instant on fait une copie sans les autres attributs.
        // Y a au moins le point puisqu'il doit �tre cr�e par Express
        Tache [] myNewTaskList = new Tache[myTacheList.size()];
        for (int i = 0; i < myTacheList.size(); i++) {
            // On pioche.
            Integer x = (myTacheList.get(i)).getPoint().getX();
            Integer y = (myTacheList.get(i)).getPoint().getY();
            
            // On cr��
            Point refPoint = (Point)InterfaceExpressJava.createEntityReferenceBackIntoClipBoard("tache", "Point");
            Tache refTache = (Tache)InterfaceExpressJava.createEntityReferenceBackIntoClipBoard("tache", "Tache");
            
            // On int�gre       
            refPoint.setX(new Integer(x.intValue()));
            refPoint.setY(new Integer(y.intValue()));
            refTache.setPoint(refPoint);
            refTache.setTaskCloned(myTacheList.get(i));
            // Les caract�ristiques � copier.
            refTache.setName((myTacheList.get(i)).getName());
            refTache.setExecutant((myTacheList.get(i)).getExecutant());
            myNewTaskList[i] = refTache;
        }
         
        // Traitement des liens.
        int i = 0;
        while (edgeOIDList.size() != 0) {  
            String oldMotherTask = (edgeOIDList.get(i))[0]; 

            boolean supprimee = false;
            for (int j = 0; j < myNewTaskList.length ; j++) {
                if (myNewTaskList[j].getTaskCloned().getOid().equals(new Oid(oldMotherTask)) && !myNewTaskList[j].isMotherUsed()) {
 
                    int l = 0;
                    while (l != edgeOIDList.size()) {                    
                        // On parcours les OID des fils.
                        if (oldMotherTask.equals(edgeOIDList.get(l)[0])) {
                            String oldSonTask = (edgeOIDList.get(l))[1];
                            boolean trouveeFils = false;
                            for (int k = 0; k < myNewTaskList.length && !trouveeFils; k++) {
                                if (k != j) { // Une t�che m�re ne peut �tre son fils.
                                    if (myNewTaskList[k].getTaskCloned().getOid().equals(new Oid(oldSonTask))) {
                                        // Ici on a vérifié qu'il y a une nouvelle t�che qui doit �tre connect�e.
                                        myNewTaskList[j].getFils().add(myNewTaskList[k]);
                                        // On n'oublie pas la r�f�rence de la sous-t�ches � la t�che m�re
                                        myNewTaskList[k].setMotherTask(myNewTaskList[j]);
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
                    // On a effectu� la recherche dans toutes les sous-t�ches et ainsi la m�re est combl�e...
                    myNewTaskList[j].setMotherUsed(true);
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

	public static Tache[] setTaskPoint(int x, int y, Tache myTache) {
        Tache[] myTemp = myTache.modifierPoint(x, y);
        notifyObservers();
		return myTemp;
	}

    /**
	 * Cette m�thode cr�e une t�che au sens Express.
	 * @param x : position abcisse
	 * @param y : position ordonn�e
	 * @return Oid de la t�che
	 */
	public static Tache addNewTask(int x, int y, Executant e) {
		Oid oidPoint = InterfaceExpressJava.createEntity("tache", "Point");
		Oid oidTache = InterfaceExpressJava.createEntity("tache", "Tache");
		Oid oidMedia = InterfaceExpressJava.createEntity("tache", "Media");
		
		Point p = (Point) InterfaceExpressJava.prendre(oidPoint);
		Tache t = (Tache) InterfaceExpressJava.prendre(oidTache);
		Media m = (Media) InterfaceExpressJava.prendre(oidMedia);
        t.setExecutant(e);
		t.setPoint(p);
		t.setMedia(m);
		p.x = new Integer(x);
		p.y = new Integer(y);
        
        notifyObservers();
		return t;
	}
 
    /**
     * Cette m�thode cr�e une liaison express entre deux t�ches.
     * @param oidMere : oid de la t�che m�re
     * @param oidFils : oid de la t�che fille
     * @return
     */
    public static void addNewEdge(Tache taskMere, Tache taskFils) {
        taskMere.addSubTask(taskFils);
        notifyObservers();
    }

    /**
     * Cette m�thode permet de supprimer une t�che et ces liens associ�s.
     * @param oidTache
     * @return
     */
    public static ArrayList<Tache> removeTask(Tache currentTask) {
        ArrayList<Tache> myTemp = currentTask.removeTask();
        notifyObservers();
        return myTemp;
    }
    
	/**
     * Cette m�thode permet de supprimer un lien.
	 * @param oidTacheMere
	 * @param oidTacheFils
	 * @return
	 */
	public static ArrayList<Tache> removeEdge(Tache tacheMere, Tache tacheFils) {
        ArrayList<Tache> myTemp = tacheMere.removeSubTask(tacheFils); 
        notifyObservers();
		return myTemp;
	}

	/**
	 * Modifie avec l'oid de la t�che l'attribut name.
	 * @param oid : oid de la t�che
	 * @param name : nouveau nom de la t�che
	 */
	public static void setNameTask(Tache currentTask, String name) {
        currentTask.setName(name);
        notifyObservers();
	}

	/**
	 * Modifie avec l'oid de la t�che l'attribut but.
	 * @param oid
	 * @param but
	 */
	public static void setButTask(Tache currentTask, String but) {
        currentTask.setBut(but);
        notifyObservers();
	}
	
	/**
	 * Modifie avec l'oid de la t�che l'attribut feedback.
	 * @param oid
	 * @param res
	 */
	public static void setFeedbackTask(Tache currentTask, String res) {
        currentTask.setFeedBack(res);
        notifyObservers();
	}
	
	/**
	 * Modifie avec l'oid de la t�che l'attribut dur�e.
	 * @param oid
	 * @param res
	 */
	public static void setDurationTask(Tache currentTask, String res) {
        currentTask.setDuree(res);
        notifyObservers();
	}
	
	/**
	 *  Modifie avec l'oid de la t�che l'attribut observation.
	 * @param oid
	 * @param name
	 */
	public static void setObservationTask(Tache currentTask, String name) {
        currentTask.setObservation(name);
        notifyObservers();
	}
	
	/**
	 *  Modifie avec l'oid de la t�che l'attribut ex�cutant.
	 * @param oid
	 * @param name
	 */
    public static void setExecutingUserTask(Tache currentTask, Executant name) {
        if (name == null)
            return;
        currentTask.setExecutant(name);
        notifyObservers();
    }
    
    public static void setFrequencyTask(Tache currentTask, Frequence valeur) {
        if (valeur == null)
            return;
        currentTask.setFreq(valeur);
        notifyObservers();
    }
	
	/**
	 *  Modifie avec l'oid de la t�che l'attribut fr�quence valeur.
	 * @param oid
	 * @param valeur
	 */
	public static void setFrequencyValueTask(Tache currentTask, String valeur) {
        currentTask.setCompFreq(valeur);
        notifyObservers();
	}
    
    /**
     *  Modifie avec l'oid de la t�che l'attribut modalit�.
     * @param oid
     * @param sens
     * @param cogn
     */
    public static void setModalityTask(Tache currentTask, Modalite sensOrCogn) {
        currentTask.setModal(sensOrCogn);
        notifyObservers();
    }
	
    public static void setSignificantTask(Tache currentTask, Importance valeur) {
        if (valeur == null)
            return;
        currentTask.setImp(valeur);
        notifyObservers();
    }
    
	/**
	 *  Modifie avec l'oid de la t�che l'attribut �v�nement.
	 * @param oid
	 * @param valeur
	 * @return
	 */
	public static boolean addNewEventTask(Tache currentTask, String valeur) {		
		Evenement e = ExpressEvent.stringToEvent(valeur);
		
		if (currentTask.addEvent(e)) {
            notifyObservers();
			return true;
		}
		return false;
	}
    
    public static boolean setEventTask(Tache currentTask, String oldvaleur, String valeur) {
        Evenement oldEvent = ExpressEvent.stringToEvent(oldvaleur);
        Evenement newEvent = ExpressEvent.stringToEvent(valeur);
        
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

    public static void setOptionalTask(Tache currentTask, Boolean valeur) {
        currentTask.setFacultatif(valeur);
        notifyObservers();
    }

    public static void setInterruptibleTask(Tache currentTask, Boolean valeur) {
        currentTask.setInterruptible(valeur); 
        notifyObservers();
    }

	public static void setDeclenchementTask(Tache currentTask, String valeur) {
        if (valeur == null)
            return;
        currentTask.setDeclencheur(ExpressEvent.stringToEvent(valeur));
        notifyObservers();
	}
	
    public static void setOperatorTask(Tache currentTask, Decomposition valeur) {
        currentTask.setDecomposition(valeur);
        notifyObservers();
    }

	public static void removeEventTask(Tache currentTask, String valeur) {
		Evenement e = ExpressEvent.stringToEvent(valeur);
        currentTask.removeEvent(e);
        notifyObservers();
	}
	
	public static boolean addActor(Tache currentTask, String oidact) {
		Acteur act = (Acteur) InterfaceExpressJava.prendre(new Oid(oidact));
        boolean value = currentTask.addActeur(act);
        notifyObservers();
		return value;
	}

	public static void removeActor(String oidAct) {
		Acteur a = (Acteur) InterfaceExpressJava.prendre(new Oid(oidAct));
        a.delete();
        notifyObservers();
	}

    public static void removeAllActors(Tache ref) {
        // Have to make a copy of Actor List because we'r going to iterate on it 
        // and for each element an actor is deleted ...  
        Acteur[] toto = new Acteur[ref.getActeurs().size()];
        toto = ref.getActeurs().toArray(toto);
        for (int i = 0 ; i < toto.length ; i++) {
            toto[i].delete();
        }
        notifyObservers();
    }

    public static void affRemoveActeur(String oidAct) {
        Acteur a = (Acteur) InterfaceExpressJava.prendre(new Oid(oidAct));
        a.affDelete();        
    }

    public static void affRemoveEvent(Tache currentTask, String maValue) {
        Evenement e = ExpressEvent.stringToEvent(maValue);
        InterfaceExpressJava.getGestionWarning().addMessage(e.getOid(), 4, ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \"" +  currentTask.getName() + "\"");
    }

	public static void setLabelTask(Tache selectedExpressTask, String pLabel) {
        if (pLabel == null)
            return;
        selectedExpressTask.setLabel(ExpressLabel.stringToLabel(pLabel));
        notifyObservers();
	}
}

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
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;
import java.util.Vector;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Frequence;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.Modality;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADETaskPropertiesPanel;

/**
 * @author Mickael BARON
 */
public final class TaskPropertiesAdaptator {
    protected static final KMADETaskPropertiesPanel fenetreTacheProp = GraphicEditorAdaptator
	    .getPanelProp();

    public static void showTaskProperties() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.showPropertyTaskCard();
    }

    public static void hideTaskProperties() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.showEmptyCard();
    }

    public static void updateNameRealTime(String nameJTache) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setTaskNameRealTime(nameJTache);
    }
    
    public static void updatePurposeRealTime(String p){
    	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setPurposeRealTime(p);
    }
    public static void updateObservationRealTime(String p){
    	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setObservationRealTime(p);
    }
    

    public static void updateExecutantTypeRealTime(Executor myExecutant) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutantTypeRealTime(myExecutant);
    }

    public static void updateInterruptibleCharacRealTime(boolean b) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setInterruptibleCharacRealTime(b);
    }

    public static void updateOptionalCharacRealTime(boolean b) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOptionalCharacRealTime(b);
    }

    public static void updateDecompositionRealTime(
	    String localeDecompositionIntoEnumere) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setDecompositionRealTime(localeDecompositionIntoEnumere);
    }

    
      public static void updateDurationRealTime(String duree) {
      KMADEMainFrame.getProjectPanel
      ().getPanelProprieteTache().setDureeRealTime(duree); }
     
    public static void updateFrequencyRealTime(String text) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequencyRealTime(text);
    }

    
      public static void setPurpose() {
      KMADEMainFrame.getProjectPanel().getPanelProprieteTache().setPurpose(); }
      
      public static String getPurpose() { return
      KMADEMainFrame.getProjectPanel().getPanelProprieteTache().getPurpose(); }
     
    /*
     * public static void setFeedback() {
     * KMADEMainFrame.getProjectPanel().getPanelProprieteTache().setFeedback();
     * }
     * 
     * public static String getFeedback() { return
     * KMADEMainFrame.getProjectPanel().getPanelProprieteTache().getFeedback();
     * }
     */

    public static void setObservation() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setObservation();
    }

    public static String getObservation() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getObservation();
    }

    public static void setUnknownExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executor.INCONNU);
    }

    public static void setUserExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executor.USER);
    }

    public static void setSystemExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executor.SYS);
    }

    public static void setInteractifExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executor.INT);
    }

    public static void setAbstractExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executor.ABS);
    }

    public static void setUnknownModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modality.INCONNU.getValue());
    }

    public static void setSensoriMotriceModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modality.SENS.getValue());
    }

    public static void setCognitiveModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modality.COGN.getValue());
    }

    public static void setUnknownImportance() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setImportance(Importance.INCONNU.getValue());
    }

    public static void setLowImportance() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setImportance(Importance.PEU.getValue());
    }

    public static void setMiddleImportance() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setImportance(Importance.ASSEZ.getValue());
    }

    public static void setHighImportance() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setImportance(Importance.TRES.getValue());
    }

    public static void setUnknownFrequence() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequence(Frequence.INCONNU.getValue());
    }

    public static void setHeighFrequence() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequence(Frequence.ELEVE.getValue());
    }

    public static void setAverageFrequence() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequence(Frequence.MOYENNE.getValue());
    }

    public static void setLowFrequence() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequence(Frequence.FAIBLE.getValue());
    }

    public static void setUnknownOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.INCONNU.getValue());
    }

    public static void setEnablingOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.SEQ.getValue());
    }

    public static void setConcurrentOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.PAR.getValue());
    }

    public static void setChoiceOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.ALT.getValue());
    }

    public static void setLeafOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.ELE.getValue());
    }

    public static void setOrderIndependencyOperator() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setOperator(Decomposition.ET.getValue());
    }

    public static boolean isNecessityEnabled() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.isNecessityEnabled();
    }

    public static void setFiredEvents() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFiredEvents();
    }

    public static String getFiredEvents() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getFiredEvents();
    }

    public static void setEffetsDeBord() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setEffetsDeBord();
    }

    public static String getEffetsDeBord() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getEffetsDeBord();
    }

    public static ArrayList<?> getActorList() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getActorList();
    }

    public static void setActorList() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setActorList();
    }

    public static ArrayList<?> getActorSystemList() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getActorSystemList();
    }

    public static void setActorSystemList() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setActorSystemList();
    }

    public static void setIteration() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setIteration();
    }

    public static String getIteration() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getIteration();
    }

    public static void setPrecondition() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setPrecondition();
    }

    public static String getPrecondition() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getPrecondition();
    }

    public static void setDeclenchementEvent(String p) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setDeclenchement(p);
    }

    public static String[] getAllEvents() {
	return KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.getAllEvents();
    }

    /**
     * Modifie les attributs de la t�che de l'Oid oid
     * 
     * @param oid
     *            : oid de la t�che s�lectionn�e
     */
    public static void setSelectedTaskAttributes() {
	Task tache = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();

	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.displayTaskProperties(tache.getNumber(),
			tache.getMotherTaskName(), tache.getName(),
			 tache.getGoal(), 
			/* tache.getMedia(), */
			tache.getLabelName(),
			/* tache.getFeedBack(), */
			 tache.getDuration(), 
			tache.getDescription(), tache.getExecutor(),
			tache.getModality().getValue(),
			tache.getFrequency().getValue(), tache.getFrequencyValue(),
			tache.getImportance().getValue(),
			tache.getEventsName(), tache.getFacultatif(),
			tache.isInterruptible(), tache.getRaisingEventName(),
			tache.getActors(), tache.getPreExpression(),
			tache.getEffetsDeBordExpression(),
			tache.getOrdering().getValue(),
			tache.getIterExpression());
	TaskPropertiesAdaptator.updateTaskPropertiesPanel();
    }

    /**
     * Cette méthode est utilisée pour mettre a jour certaines valeurs suite �
     * une modification des objets du monde. Utilisateur/Evenement
     */
    public static void updateTaskPropertiesPanel() {
	// Pour la liste de déclenchement.
	ArrayList<String> allEvent = EventAdaptator.getEventsName();

	if (allEvent.size() != 0)
	    allEvent.add(0, "");

	String[] newTabEvent = new String[allEvent.size()];
	newTabEvent = allEvent.toArray(newTabEvent);

	// Pour la liste des libellés.
	ArrayList<String> allLibelle = LabelAdaptator.getLabelsNameList();

	if (allLibelle.size() != 0)
	    allLibelle.add(0, "");

	String[] newLabelArray = new String[allLibelle.size()];
	newLabelArray = allLibelle.toArray(newLabelArray);

	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.updateTaskPropertiesPanel(
			newTabEvent,
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getEventsName(),
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getActors(),
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getActorSystem(), newLabelArray);
    }

    public static void setNameFromExpressTask(String name) {
    	/*AG*/
    	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   String nom = GraphicEditorAdaptator.getSelectedExpressTask().getName();
    	   Vector<Object> task_name = new Vector<Object>();
    	   task_name.add(oid);
    	   task_name.add(nom);
    		GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_name);
    		GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Name");
    	   /////////////////////////////////////////////////////
    	   ExpressTask.setNameTask(
    	   GraphicEditorAdaptator.getSelectedExpressTask(), name);
    	   GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    
      public static void setButFromExpressTask(String but) {
    	  /*AG*/
      	
   	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
   	   String But = GraphicEditorAdaptator.getSelectedExpressTask().getGoal();
   	   Vector<Object> task_but = new Vector<Object>();
   	   task_but.add(oid);
   	   task_but.add(But);
   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_but);
	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("But");
   	   /////////////////////////////////////////////////////
   	   
   	   
   	   
    	  
    ExpressTask.setButTask(GraphicEditorAdaptator.getSelectedExpressTask(),
      but); }
    
      
      
    public static void setLabelExpressTask(String pcolor) {
  	  /*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   String label = GraphicEditorAdaptator.getSelectedExpressTask().getLabelName();
    	   Vector<Object> task_label = new Vector<Object>();
    	   task_label.add(oid);
    	   task_label.add(label);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_label);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Label");
    	   /////////////////////////////////////////////////////
    	
    	
    	
    	
    	
    	
	ExpressTask.setLabelTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), pcolor);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    /*
     * public static void setFeedbackFromExpressTask(String res) {
     * ExpressTask.setFeedbackTask
     * (GraphicEditorAdaptator.getSelectedExpressTask(), res); }
     */

    public static void setDurationFromExpressTask(String res) {
  	  /*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   String dur = GraphicEditorAdaptator.getSelectedExpressTask().getDuration();
    	   Vector<Object> task_duration = new Vector<Object>();
    	   task_duration.add(oid);
    	   task_duration.add(dur);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_duration);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Duration");
    	   /////////////////////////////////////////////////////
    	
    	
    	
    ExpressTask.setDurationTask(
	GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setObservationFromExpressTask(String res) {
  	  /*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   String obs = GraphicEditorAdaptator.getSelectedExpressTask().getDescription();
    	   Vector<Object> task_observation = new Vector<Object>();
    	   task_observation.add(oid);
    	   task_observation.add(obs);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_observation);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Observation");
    	   /////////////////////////////////////////////////////
    		
    	
    	
    	
	ExpressTask.setObservationTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setExecutingUserFromExpressTask(Executor res) {
  	  /*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   Executor exe = GraphicEditorAdaptator.getSelectedExpressTask().getExecutor();
    	   Vector<Object> task_executant = new Vector<Object>();
    	   task_executant.add(oid);
    	   task_executant.add(exe);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_executant);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Executant");
    	   /////////////////////////////////////////////////////
    	
	ExpressTask.setExecutingUserTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    
    public static void setFrequencyFromExpressTask(Frequence res) {
  	  /*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   Frequence fr = GraphicEditorAdaptator.getSelectedExpressTask().getFrequency();
    	   Vector<Object> task_frequence = new Vector<Object>();
    	   task_frequence.add(oid);
    	   task_frequence.add(fr);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_frequence);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Frequence");
    	   /////////////////////////////////////////////////////
    	
    	
    	
    	ExpressTask.setFrequencyTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    
    
    public static void setFrequencyValueFromExpressTask(String res) {
    	 /*AG*/
      	
 	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
 	   String fr = GraphicEditorAdaptator.getSelectedExpressTask().getFrequencyValue();
 	   Vector<Object> task_frequence = new Vector<Object>();
 	   task_frequence.add(oid);
 	   task_frequence.add(fr);
 	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_frequence);
 	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("FrequenceValue");
 	   /////////////////////////////////////////////////////
 		
 	   ExpressTask.setFrequencyValueTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setModalityFromExpressTask(Modality sensOrCogn) {
   	 /*AG*/
      	
  	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
  	   Modality mod = GraphicEditorAdaptator.getSelectedExpressTask().getModality();
  	   Vector<Object> task_modality = new Vector<Object>();
  	   task_modality.add(oid);
  	   task_modality.add(mod);
  	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_modality);
  	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Modalite");
  	   /////////////////////////////////////////////////////
  		
    	
    	
    	
	ExpressTask.setModalityTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), sensOrCogn);
    }

    
    
    public static void setSignificantFromExpressTask(Importance res) {
    	/*AG*/
      	
   	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
   	   Importance imp = GraphicEditorAdaptator.getSelectedExpressTask().getImportance();
   	   Vector<Object> task_importance = new Vector<Object>();
   	   task_importance.add(oid);
   	   task_importance.add(imp);
   	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_importance);
   	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Importance");
   	   /////////////////////////////////////////////////////
    	
    ExpressTask.setSignificantTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    /**
     * @param oid
     * @param res
     */
    public static void setDeclenchementFromExpressTask(String res) {
	ExpressTask.setDeclenchementTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setOptionalFromExpressTask(boolean res) {
    	
    	/*AG*/
      	
   	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
   	   boolean opt = GraphicEditorAdaptator.getSelectedExpressTask().getFacultatif();
   	   Vector<Object> task_modality = new Vector<Object>();
   	   task_modality.add(oid);
   	   task_modality.add(opt);
   	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_modality);
   	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Facultatif");
   	   /////////////////////////////////////////////////////
   		
    	
    	
	ExpressTask.setOptionalTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    public static void setInterruptibleFromExpressTask(boolean res) {
    	/*AG*/
      	
    	   String oid =  GraphicEditorAdaptator.getOidSelectedTask();
    	   boolean opt = GraphicEditorAdaptator.getSelectedExpressTask().getInterruptible();
    	   Vector<Object> task_interruptible = new Vector<Object>();
    	   task_interruptible.add(oid);
    	   task_interruptible.add(opt);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().getObjToUndo().push(task_interruptible);
    	   	GraphicEditorAdaptator.getTaskModelPanel().getUndoRedoManager().whatUndo.push("Interruptible");
    	   /////////////////////////////////////////////////////   	
    	
    	
    	
	ExpressTask.setInterruptibleTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    /**
     * @param oid
     * @param res
     */
    public static void setOperatorFromExpressTask(Decomposition res,
	    String value) {
	ExpressTask.setOperatorTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    // Partie liée aux précondition, effetsdebord et itération
    public static void setPreconditionInModel() {
	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.setPreconditionInModel(
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getPreExpression());
    }

    public static void setEffetsDeBordInModel() {
	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.setEffetsDeBordInModel(
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getEffetsDeBordExpression());
    }

    public static void setIterationInModel() {
	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.setIterationInModel(
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getIterExpression());
    }
}

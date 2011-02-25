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

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.kmad.schema.tache.Frequence;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.Modalite;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
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

    public static void updateExecutantTypeRealTime(Executant myExecutant) {
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

    /*
     * public static void updateDurationRealTime(String duree) {
     * KMADEMainFrame.getProjectPanel
     * ().getPanelProprieteTache().setDureeRealTime(duree); }
     */
    public static void updateFrequencyRealTime(String text) {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setFrequencyRealTime(text);
    }

    /*
     * public static void setPurpose() {
     * KMADEMainFrame.getProjectPanel().getPanelProprieteTache().setPurpose(); }
     * 
     * public static String getPurpose() { return
     * KMADEMainFrame.getProjectPanel().getPanelProprieteTache().getPurpose(); }
     */
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
		.setExecutant(Executant.INCONNU);
    }

    public static void setUserExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executant.USER);
    }

    public static void setSystemExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executant.SYS);
    }

    public static void setInteractifExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executant.INT);
    }

    public static void setAbstractExecutant() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setExecutant(Executant.ABS);
    }

    public static void setUnknownModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modalite.INCONNU.getValue());
    }

    public static void setSensoriMotriceModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modalite.SENS.getValue());
    }

    public static void setCognitiveModalite() {
	KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		.setModalite(Modalite.COGN.getValue());
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
	Tache tache = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();

	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.displayTaskProperties(tache.getNumero(),
			tache.getMotherTaskName(), tache.getName(),
			/* tache.getBut(), */
			tache.getRessources(),
			/* tache.getMedia(), */
			tache.getLabelName(),
			/* tache.getFeedBack(), */
			/* tache.getDuree(), */
			tache.getObservation(), tache.getExecutant(),
			tache.getModalite().getValue(),
			tache.getFrequence().getValue(), tache.getCompFreq(),
			tache.getImportance().getValue(),
			tache.getEventsName(), tache.getFacultatif(),
			tache.isInterruptible(), tache.getDeclencheurName(),
			tache.getActeurs(), tache.getPreExpression(),
			tache.getEffetsDeBordExpression(),
			tache.getDecomposition().getValue(),
			tache.getIteExpression());
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
				.getTask().getActeurs(),
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getActeurSysteme(), newLabelArray);
    }

    public static void setNameFromExpressTask(String name) {
	ExpressTask.setNameTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), name);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    /*
     * public static void setButFromExpressTask(String but) {
     * ExpressTask.setButTask(GraphicEditorAdaptator.getSelectedExpressTask(),
     * but); }
     */
    public static void setLabelExpressTask(String pcolor) {
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
	ExpressTask.setDurationTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setObservationFromExpressTask(String res) {
	ExpressTask.setObservationTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setExecutingUserFromExpressTask(Executant res) {
	ExpressTask.setExecutingUserTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    public static void setFrequencyFromExpressTask(Frequence res) {
	ExpressTask.setFrequencyTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setFrequencyValueFromExpressTask(String res) {
	ExpressTask.setFrequencyValueTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static void setModalityFromExpressTask(Modalite sensOrCogn) {
	ExpressTask.setModalityTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), sensOrCogn);
    }

    public static void setSignificantFromExpressTask(Importance res) {
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
	ExpressTask.setOptionalTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
	GraphicEditorAdaptator.updateSelectedTaskGraphModel();
    }

    public static void setInterruptibleFromExpressTask(boolean res) {
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
				.getTask().getIteExpression());
    }
}

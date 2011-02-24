package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.KMADEConstant;
import kmade.kmade.view.KMADEMainFrame;
import kmade.kmade.view.taskproperties.KMADEEnhancedTaskEditor;
import kmade.kmade.view.taskproperties.KMADEEnhancedTaskLabel;
import kmade.kmade.view.toolutilities.InDevelopmentGlassPanel;
import kmade.nmda.schema.tache.Acteur;
import kmade.nmda.schema.tache.ActeurSysteme;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Experience;
import kmade.nmda.schema.tache.Frequence;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public final class TaskPropertiesEnhancedEditorAdaptator {
    private static KMADEEnhancedTaskEditor myRefTaskEditor = new KMADEEnhancedTaskEditor();
    
    private static Tache motherTask;

    private static Tache oldSisterTask;

    private static Tache youngSisterTask;

    private static Tache firstSonTask;
    
    private static Tache currentEditedTask;
    
    public static KMADEEnhancedTaskEditor getEnhancedTaskEditorFrame() {
    	return myRefTaskEditor;
    }
    
    public static void showNMDAEnhancedTaskEditor() {
        if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
            KMADEMainFrame.getProjectPanel().getPanelProprieteTache().showEmptyCard();
            return;
        }
        
        currentEditedTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        setSelectedTaskAttributes(currentEditedTask);
        myRefTaskEditor.setVisible(true);
    }
    
    public static void requestFocus() {
        myRefTaskEditor.requestFocus();
    }
    
    public static boolean isVisible() {
        return myRefTaskEditor.isVisible();
    }
    
    public static void enabledMainFrameAfterEdition() {
        myRefTaskEditor.setEnabled(true);
        InDevelopmentGlassPanel.unPlugWindowInDisabled(myRefTaskEditor);
    }
    
    public static void disabledMainFrameBeforeEdition() {
        myRefTaskEditor.setEnabled(false);
        InDevelopmentGlassPanel.plugWindowInDisabled(myRefTaskEditor);
    }
    
    public static void closeNMDAEnhancedTaskEditor() {
        myRefTaskEditor.setVisible(false);
    }
    
    public static void setSelectedTaskAttributes(Tache current) {
        currentEditedTask = current;
        myRefTaskEditor.displayTaskProperties(
                currentEditedTask.getNumero(), 
                currentEditedTask.getMotherTaskName(),
                currentEditedTask.getName(), 
                currentEditedTask.getBut(),
                currentEditedTask.getRessources(), 
                currentEditedTask.getFeedBack(),
                currentEditedTask.getDuree(), 
                currentEditedTask.getObservation(),
                currentEditedTask.getExecutant(), 
                currentEditedTask.getModalite(), 
                currentEditedTask.getFrequence(),
                currentEditedTask.getCompFreq(),
                currentEditedTask.getImportance(), 
                TaskPropertiesAdaptator.getFiredEvents(),
                currentEditedTask.getFacultatif(),
                currentEditedTask.isInterruptible(),
                TaskPropertiesAdaptator.getAllEvents(),
                currentEditedTask.getDeclencheurName(),
                TaskPropertiesEnhancedEditorAdaptator.getActorTable(),
                TaskPropertiesEnhancedEditorAdaptator.getActorSystemTable(),
                currentEditedTask.getPreExpression().getName(), 
                currentEditedTask.getEffetsDeBordExpression().getName(), 
                currentEditedTask.getDecomposition(), 
                currentEditedTask.getIteExpression().getName()
            );

            motherTask = currentEditedTask.getMotherTask();
            oldSisterTask = currentEditedTask.getOldSisterTask();
            youngSisterTask = currentEditedTask.getYoungSisterTask();
            firstSonTask = currentEditedTask.getFirstSonTask(); 
            
            if (motherTask == null) {
                myRefTaskEditor.setUpButton(false, KMADEConstant.NO_MOTHER_TASK_MESSAGE,KMADEConstant.NO_NUMERO_TASK);
            } else {
                myRefTaskEditor.setUpButton(true,motherTask.getName(),motherTask.getNumero());
            }
            
            if (oldSisterTask == null) {
                myRefTaskEditor.setLeftButton(false,KMADEConstant.NO_LEFT_TASK_MESSAGE,KMADEConstant.NO_NUMERO_TASK);
            } else {
                myRefTaskEditor.setLeftButton(true,oldSisterTask.getName(),oldSisterTask.getNumero());
            }
            
            if (youngSisterTask == null) {
                myRefTaskEditor.setRightButton(false,KMADEConstant.NO_RIGHT_TASK_MESSAGE,KMADEConstant.NO_NUMERO_TASK);
            } else {
                myRefTaskEditor.setRightButton(true,youngSisterTask.getName(),youngSisterTask.getNumero());
            }
            
            if (firstSonTask == null) {
                myRefTaskEditor.setDownButton(false,KMADEConstant.NO_SUB_TASK_MESSAGE,KMADEConstant.NO_NUMERO_TASK);
            } else {
                myRefTaskEditor.setDownButton(true,firstSonTask.getName(),firstSonTask.getNumero());
            }
            
            // Mise à jour des activations des JRadioButton's
            if ((currentEditedTask.getExecutant() == Executant.USER) || (currentEditedTask.getExecutant() == Executant.ABS)) {
                myRefTaskEditor.setEnabledModalityGroup();
            } else {
                myRefTaskEditor.setDisabledModalityGroup();
            }
            
            if ((currentEditedTask.getExecutant() == Executant.SYS)) {
                myRefTaskEditor.setDisabledNecessityGroup();
            } else {
                myRefTaskEditor.setEnabledNecessityGroup();
            }
            
            if (currentEditedTask.getFrequence() == Frequence.INCONNU) {
                myRefTaskEditor.setDisabledFrequenceValue();
            } else {
                myRefTaskEditor.setEnabledFrequenceValue();
            }
            myRefTaskEditor.stopAnimation();        
    }
    
    public static void setSelectedTaskAttributes() {
        GraphicEditorAdaptator.setSelectionTask(currentEditedTask);
    }

    private static ArrayList<String[]> getActorTable() {
        ArrayList<?> myActorList = TaskPropertiesAdaptator.getActorList();
        ArrayList<String[]> myStringActeurList = new ArrayList<String[]>();
        
        for (int i = 0; i < myActorList.size(); i++) {
            Acteur myActeur = (Acteur)(myActorList.get(i));
            String[] myTab = new String[3];
            myTab[0] = myActeur.getName();
            myTab[1] = Experience.getEnumereIntoLocaleExperience(myActeur.getExperience().getValue());
            myTab[2] = myActeur.getCompetence();
            myStringActeurList.add(myTab);
        }
        return myStringActeurList;
    }
    
    private static ArrayList<String[]> getActorSystemTable() {
        ArrayList<?> myActorSystemList = TaskPropertiesAdaptator.getActorSystemList();
        ArrayList<String[]> myStringActeurSystemList = new ArrayList<String[]>();
        
        for (int i = 0; i < myActorSystemList.size(); i++) {
            ActeurSysteme myActeurSysteme = (ActeurSysteme)(myActorSystemList.get(i));
            String[] myTab = new String[3];
            myTab[0] = myActeurSysteme.getName();
            myTab[1] = Experience.getEnumereIntoLocaleExperience(myActeurSysteme.getExperience().getValue());
            myTab[2] = myActeurSysteme.getCompetence();
            myStringActeurSystemList.add(myTab);
        }
        return myStringActeurSystemList;
    }
    
    public static void switchToOtherTask(int position) {
        switch(position) {
            case KMADEEnhancedTaskLabel.LEFT : {
                currentEditedTask = oldSisterTask;             
                break;
            }
            case KMADEEnhancedTaskLabel.RIGHT : {
                currentEditedTask = youngSisterTask;
                break;
            }
            case KMADEEnhancedTaskLabel.TOP : {
                currentEditedTask = motherTask;
                break;
            }
            case KMADEEnhancedTaskLabel.BOTTOM : {
                currentEditedTask = firstSonTask;
                break;
            }
        }
    }
    
    public static void startAnimation() {
        myRefTaskEditor.launchAnimation(currentEditedTask.getName());
    }
    
    public static void stopAnimation() {
        myRefTaskEditor.stopAnimation();
    }
    
    public static void setNameInTaskProperties(String value) {
        TaskPropertiesAdaptator.updateNameRealTime(value);
    }
    /*
    public static void setDureeInTaskProperties(String text) {
        TaskPropertiesAdaptator.updateDurationRealTime(text);        
    }
    */
    /*
    public static void setPurpose() {
        TaskPropertiesAdaptator.setPurpose();
        myRefTaskEditor.setPurposeField(TaskPropertiesAdaptator.getPurpose());
    }
	*/
    /*
    public static void setFeedBack() {
        TaskPropertiesAdaptator.setFeedback();
        myRefTaskEditor.setFeedbackField(TaskPropertiesAdaptator.getFeedback());
    }
	*/
    /*
    public static void setObservation() {
        TaskPropertiesAdaptator.setObservation();
        myRefTaskEditor.setObservationArea(TaskPropertiesAdaptator.getObservation());        
    }
	*/
    public static void setUnknownExecutant() {
        TaskPropertiesAdaptator.setUnknownExecutant();
        myRefTaskEditor.setEnabledModalityGroup();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setUserExecutant() {
        TaskPropertiesAdaptator.setUserExecutant();
        myRefTaskEditor.setEnabledModalityGroup();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setSystemExecutant() {
        TaskPropertiesAdaptator.setSystemExecutant();
        myRefTaskEditor.setDisabledModalityGroup();
        myRefTaskEditor.setDisabledNecessityGroup();
    }

    public static void setInteractifExecutant() {
        TaskPropertiesAdaptator.setInteractifExecutant();
        myRefTaskEditor.setDisabledModalityGroup();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setAbstractExecutant() {
        TaskPropertiesAdaptator.setAbstractExecutant();
        myRefTaskEditor.setDisabledModalityGroup();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setUnknownModalite() {
        TaskPropertiesAdaptator.setUnknownModalite();
    }

    public static void setSensoriMotriceModalite() {
        TaskPropertiesAdaptator.setSensoriMotriceModalite();
    }

    public static void setCognitiveModalite() {
        TaskPropertiesAdaptator.setCognitiveModalite();
    }

    public static void setUnknownImportance() {
        TaskPropertiesAdaptator.setUnknownImportance();
    }

    public static void setLowImportance() {
        TaskPropertiesAdaptator.setLowImportance();
    }

    public static void setMiddleImportance() {
        TaskPropertiesAdaptator.setMiddleImportance();
    }

    public static void setHighImportance() {
        TaskPropertiesAdaptator.setHighImportance();
    }

    public static void setUnknownFrequence() {
        TaskPropertiesAdaptator.setUnknownFrequence();
        myRefTaskEditor.setDisabledFrequenceValue();
    }

    public static void setHeighFrequence() {
        TaskPropertiesAdaptator.setHeighFrequence();
        myRefTaskEditor.setEnabledFrequenceValue();
    }

    public static void setAverageFrequence() {
        TaskPropertiesAdaptator.setAverageFrequence();
        myRefTaskEditor.setEnabledFrequenceValue();
    }

    public static void setLowFrequence() {
        TaskPropertiesAdaptator.setLowFrequence();
        myRefTaskEditor.setEnabledFrequenceValue();
    }

    public static void setFrequencyValueInTaskProperties(String text) {
        TaskPropertiesAdaptator.updateFrequencyRealTime(text); 
    }
	
    public static void setUnknownOperator() {
        TaskPropertiesAdaptator.setUnknownOperator();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setEnablingOperator() {
        TaskPropertiesAdaptator.setEnablingOperator();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setConcurrentOperator() {
        TaskPropertiesAdaptator.setConcurrentOperator();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setChoiceOperator() {
        TaskPropertiesAdaptator.setChoiceOperator();
        myRefTaskEditor.setDisabledNecessityGroup();
    }

    public static void setLeafOperator() {
        TaskPropertiesAdaptator.setLeafOperator();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setOrderIndependencyOperator() {
        TaskPropertiesAdaptator.setOrderIndependencyOperator();
        if (TaskPropertiesAdaptator.isNecessityEnabled()) {
            myRefTaskEditor.setEnabledNecessityGroup();
        } else {
            myRefTaskEditor.setDisabledNecessityGroup();
        }
    }

    public static void setOptionnelleNecessite() {
        TaskPropertiesAdaptator.updateOptionalCharacRealTime(true);
    }

    public static void setObligatoireNecessite() {
        TaskPropertiesAdaptator.updateOptionalCharacRealTime(false);
    }

    public static void setInterruptibilite() {
        TaskPropertiesAdaptator.updateInterruptibleCharacRealTime(true);       
    }

    public static void setNoInterruptibilite() {
        TaskPropertiesAdaptator.updateInterruptibleCharacRealTime(false);
    }

    public static void setFiredEvents() {
        TaskPropertiesAdaptator.setFiredEvents();
        myRefTaskEditor.setFiredEventsField(TaskPropertiesAdaptator.getFiredEvents());
    }

    public static void setEffetsDeBord() {
        TaskPropertiesAdaptator.setEffetsDeBord();
        myRefTaskEditor.setEffetsDeBord(TaskPropertiesAdaptator.getEffetsDeBord());
    }

    public static void setActeur() {
        TaskPropertiesAdaptator.setActorList();
        myRefTaskEditor.setActorList(TaskPropertiesEnhancedEditorAdaptator.getActorTable());
    }

    public static void setActeurSysteme() {
        TaskPropertiesAdaptator.setActorSystemList();
        myRefTaskEditor.setActorSystemList(TaskPropertiesEnhancedEditorAdaptator.getActorSystemTable());
    }
    
    public static void setIteration() {
        TaskPropertiesAdaptator.setIteration();
        myRefTaskEditor.setIteration(TaskPropertiesAdaptator.getIteration());
    }

    public static void setPrecondition() {
        TaskPropertiesAdaptator.setPrecondition();
        myRefTaskEditor.setPrecondition(TaskPropertiesAdaptator.getPrecondition());
    }

    public static void setDeclenchement(String selectedItem) {
        TaskPropertiesAdaptator.setDeclenchementEvent(selectedItem);
        
    }    
    
    public static void notifLocalisationModification() {
        myRefTaskEditor.notifLocalisationModification();
    }
}

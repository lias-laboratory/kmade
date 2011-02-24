package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;


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
 * @author Micka�l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public final class CoherenceAdaptator {
    
    private static ArrayList<Object[]> myMessageList = new ArrayList<Object[]>();
    
    public static final int NO_PROBLEM = 0;
    
    public static final int WARNING_PROBLEM = 1;
    
    public static final int ERROR_PROBLEM = 2;
    
    public static final int HIERARCHICAL_TASK_MODEL = 0;
    
    public static final int POST_CONDITION_DIALOG = 1;
    
    public static final int ITERATION_DIALOG = 2;
    
    public static final int PRE_CONDITION_DIALOG = 3;
    
    // Les positions des informations
    public static final int PROBLEM_TYPE = 0;
    
    public static final int MESSAGE = 1;
    
    public static final int TASK = 2;
    
    public static final int MESSAGE_TYPE = 3;
    
    public static final int LOCATION = 4;
       
    public static final int HIERARCHICAL_TYPE = 0;
    
    public static final int EXPRESSION_TYPE = 1;
    
    private static SwingWorker worker;
        
	public static void selectTaskFromError(Object valueAt) {
		if (valueAt != null) {
			if (valueAt instanceof Tache) {
		        GraphicEditorAdaptator.getTaskModelPanel().getJGraph().scrollCellToVisible((KMADEDefaultGraphCell)((Tache)valueAt).getJTask());
		        GraphicEditorAdaptator.setSelectionTask((Tache)valueAt);
			}
		}		
	}
    
    public static void goToSelectMessage(Object[] pmessage) {
        GraphicEditorAdaptator.getTaskModelPanel().getJGraph().scrollCellToVisible((KMADEDefaultGraphCell)((Tache)pmessage[TASK]).getJTask());
        GraphicEditorAdaptator.setSelectionTask((Tache)pmessage[TASK]);
        switch ((Integer)pmessage[LOCATION]) {
            case HIERARCHICAL_TASK_MODEL : {
                break;
            }
            case POST_CONDITION_DIALOG : {
                TaskPropertiesAdaptator.setEffetsDeBord();
                break;
            }
            case ITERATION_DIALOG : {
                TaskPropertiesAdaptator.setIteration();
                break;
            }
            case PRE_CONDITION_DIALOG : {
                TaskPropertiesAdaptator.setPrecondition();
                break;
            }
        }        
    }
    
    public static int getErrorMessageCount() {
        int count = 0;
        for (Object[] current : myMessageList) {
            if (((Integer)current[0]) == ERROR_PROBLEM) {
                count++;
            }
        }
        return count;
    }

    public static int getWarningMessageCount() {
        int count = 0;
        for (Object[] current : myMessageList) {
            if (((Integer)current[0]) == WARNING_PROBLEM) {
                count++;
            }
        }
        return count;
    }
    
    public static void openCoherenceDialog() {
    		CoherenceAdaptator.checkTaskModel();
    		GraphicEditorAdaptator.getMainFrame().getCoherenceDialog().setVisible(true);
    }
    
    public static void closeCoherenceDialog() {
        GraphicEditorAdaptator.getMainFrame().getCoherenceDialog().setVisible(false);
    }
    
    private static void addErrorMessage(String message, int type, int location, Tache tache) {
   		Object[] temp = {CoherenceAdaptator.ERROR_PROBLEM, message, tache, type, location};
   		myMessageList.add(temp);
    }
    
    private static void addWarningMessage(String message, int type, int location, Tache tache) {
   		Object[] temp = {CoherenceAdaptator.WARNING_PROBLEM, message, tache, type, location};
   		myMessageList.add(temp);
    }
    
    public static void stopCheckTaskModel() {
        if (worker != null) {
            worker.interrupt();
        }
    }
    
    public static void checkTaskModel() {

        if (worker != null) {
            worker.interrupt();
            worker = null;
        }
        worker = new SwingWorker() {
            public Object construct() {
                myMessageList.clear();
                checkTaskModels();
                return null;
            }
        };
        worker.start();
    }    
    
    /**
     * Cette m�thode devra �tre "thread�e" pour plus d'efficacit�
     */
    private static synchronized void checkTaskModels() {
        ArrayList<Tache> myTaskList = ExpressTask.getRootTasks();
        for (Tache current : myTaskList) {
            // R�gles : hi�rarchie des t�ches
            if (current.isRoot() && current.isLeaf()) {
            	CoherenceAdaptator.addErrorMessage(KMADEConstant.NO_ALONE_MESSAGE_PROBLEM, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL,current);
            } else {
            	CoherenceAdaptator.checkHiearchicalTaskModel(current);
            }
        
            // R�gles les objets du monde.
            checkObjectsTaskModel(current);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphicEditorAdaptator.getMainFrame().getCoherenceDialog().setProblemMessages(myMessageList);
            }
        });        
    }
    
    private static void checkObjectsTaskModel(Tache myTache) {
        if (myTache.getPreExpression().getNodeExpression() == null) {
        		CoherenceAdaptator.addErrorMessage(KMADEConstant.PRECONDITION_EXPRESSION_MESSAGE_PROBLEM, EXPRESSION_TYPE, PRE_CONDITION_DIALOG, myTache);
        }
        
        if (myTache.getEffetsDeBordExpression().getNodeExpression() == null) {
        		CoherenceAdaptator.addErrorMessage(KMADEConstant.EFFETSDEBORD_EXPRESSION_MESSAGE_PROBLEM, EXPRESSION_TYPE, POST_CONDITION_DIALOG, myTache);
        }

        if (myTache.getIteExpression().getNodeExpression() == null) {
        		CoherenceAdaptator.addErrorMessage(KMADEConstant.ITERATION_EXPRESSION_MESSAGE_PROBLEM, EXPRESSION_TYPE, ITERATION_DIALOG, myTache);
        }
        
        for (Tache subTasks : myTache.getFils()) {
        		CoherenceAdaptator.checkObjectsTaskModel(subTasks);
        }
    }
    
    private static void checkHiearchicalTaskModel(Tache myTache) {
        // Pas de t�che unique.
        if (!myTache.isLeaf() && myTache.getFils().size() == 1) {
        	CoherenceAdaptator.addErrorMessage(KMADEConstant.NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL, myTache);
        }
        
        if (myTache.isLeaf() && (myTache.getDecomposition() != Decomposition.ELE)) {
        	CoherenceAdaptator.addErrorMessage(KMADEConstant.ELEMENTARY_DECOMPOSITION_FOR_LEAF_TASK, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL, myTache);
        }
        
        // Op�rateur de d�composition non pr�cis�.
        if (!myTache.isLeaf() && (myTache.getDecomposition() == Decomposition.ELE || myTache.getDecomposition() == Decomposition.INCONNU)) {
        	CoherenceAdaptator.addErrorMessage(KMADEConstant.NO_DECOMPOSITION_SPECIFIED_MESSAGE_PROBLEM, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL, myTache);
        }
        
        // Les sous-t�ches doivent �tre de m�me cat�gorie que la t�che m�re.
        if (!myTache.isLeaf()) {
            if (myTache.getExecutant() == Executant.INCONNU) {
            		CoherenceAdaptator.addWarningMessage(KMADEConstant.NO_EXECUTANT_SPECIFIED_MESSAGE_WARNING, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL, myTache);
            } else {
                if (!(myTache.getExecutant() == Executant.ABS)) {
                    for (Tache subTasks : myTache.getFils()) {
                        if (subTasks.getExecutant() != myTache.getExecutant()) {
                        		CoherenceAdaptator.addWarningMessage(KMADEConstant.SUBTASKS_EXECUTANT_DIFFERENT_MESSAGE_WARNING, HIERARCHICAL_TYPE, HIERARCHICAL_TASK_MODEL,myTache);
                            break;
                        }
                    }
                }
            }
        }
        
        for (Tache subTasks : myTache.getFils()) {
        		CoherenceAdaptator.checkHiearchicalTaskModel(subTasks);
        }
    }
}
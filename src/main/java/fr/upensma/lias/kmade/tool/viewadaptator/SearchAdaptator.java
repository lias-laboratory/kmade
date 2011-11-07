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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class SearchAdaptator {

    private static boolean side = true; // true = forward ; false = backward

    private static ArrayList<Tache> searchTache = new ArrayList<Tache>();;

    private static int current = 0;

    public static void openFindReplaceDialog() {
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.clearElement();
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.disableReplaceButtons();
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.setVisible(true);
    }

    public static void closeFindReplaceDialog() {
	SearchAdaptator.disableFindFlag();
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.setVisible(false);
    }

    public static void find(String nomTache, String executant,
	    String operateur, int inter, int facul, boolean regExpr,
	    boolean caseSensitive, boolean wholeWord, boolean scope) {
	SearchAdaptator.disableFindFlag();
	// Récupère les tâches
	ArrayList<Tache> allTask = null;
	if (scope) {
	    allTask = ExpressTask.getTasksFromExpress();
	} else {
	    allTask = GraphicEditorAdaptator.getSelectedTasks();
	}

	if (allTask.size() == 0) {
	    GraphicEditorAdaptator
		    .getMainFrame()
		    .getFindReplaceDialog()
		    .addOneElement(
			    KMADEConstant.NO_TASK_AVAILABLE_FOR_SEARCHING_TOOL_MESSAGE);
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .disableReplaceButtons();
	    return;
	}

	if (!SearchAdaptator.findFromCriterias(allTask, nomTache, executant,
		operateur, inter, facul, regExpr, caseSensitive, wholeWord)) {
	    GraphicEditorAdaptator
		    .getMainFrame()
		    .getFindReplaceDialog()
		    .addOneElement(
			    KMADEConstant.TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS);
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .disableReplaceButtons();
	} else {
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .enableReplaceButtons();
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .addElement(SearchAdaptator.getSearchTasks());
	    // Sélectionne le premier élément
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .selectOccurenceAt(SearchAdaptator.getCurrent());
	    GraphicEditorAdaptator.updateGraphModel(SearchAdaptator
		    .getSearchTasksArray());
	}
    }

    public static void replace(String taskNameFind, boolean wholeName,
	    String taskName, String executant, String operateur, int inter,
	    int facul) {
	Tache temp = SearchAdaptator.replaceFromCriterias(taskNameFind,
		wholeName, taskName, executant, operateur, inter, facul);
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.addElement(SearchAdaptator.getSearchTasks());
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.selectOccurenceAt(SearchAdaptator.getCurrent());
	if (temp != null) {
	    GraphicEditorAdaptator.updateGraphModel(temp.getJTask());
	}
    }

    public static void replaceAndNext(String taskNameFind, boolean wholeName,
	    String taskName, String executant, String operateur, int inter,
	    int facul) {
	SearchAdaptator.replace(taskNameFind, wholeName, taskName, executant,
		operateur, inter, facul);

	if (side) {
	    if (SearchAdaptator.hasNextReplace()) {
		SearchAdaptator.nextCurrent();
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.selectOccurenceAt(SearchAdaptator.getCurrent());
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.enableReplaceButtons();
	    } else {
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.disableReplaceButtons();
	    }
	} else {
	    if (SearchAdaptator.hasPreviousReplace()) {
		SearchAdaptator.previousCurrent();
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.selectOccurenceAt(SearchAdaptator.getCurrent());
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.enableReplaceButtons();
	    } else {
		GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
			.disableReplaceButtons();
	    }
	}
    }

    private static void disableFindFlag() {
	ArrayList<Tache> listCell = SearchAdaptator.getSearchTasks();
	for (int i = 0; i < SearchAdaptator.getSearchTasks().size(); i++) {
	    ((KMADEDefaultGraphCell) (listCell.get(i).getJTask()))
		    .setFlagSearch(false);
	}
	GraphicEditorAdaptator.updateGraphModel(SearchAdaptator
		.getSearchTasksArray());
	SearchAdaptator.getSearchTasks().clear();
    }

    private static KMADEDefaultGraphCell[] getSearchTasksArray() {
	ArrayList<Tache> taskList = SearchAdaptator.getSearchTasks();
	KMADEDefaultGraphCell[] array = new KMADEDefaultGraphCell[taskList
		.size()];
	for (int i = 0; i < taskList.size(); i++) {
	    array[i] = (KMADEDefaultGraphCell) (taskList.get(i).getJTask());
	}
	return array;
    }

    public static void resultatSearch(String nomTache, String executant,
	    String operateur, String inter, String facul, boolean regExpr,
	    boolean caseSensitive, boolean wholeWord, boolean scope) {
	File nomFichier = KMADEFileChooser.saveTxtFile(GraphicEditorAdaptator
		.getMainFrame().getFindReplaceDialog());
	if (nomFichier == null) {
	    return;
	}

	if (nomFichier != null) {
	    try {
		FileWriter fw = new FileWriter(nomFichier);
		fw.write("* " + KMADEConstant.FIND_MESSAGE + " *\n");
		if (!nomTache.equalsIgnoreCase("")) {
		    fw.write(KMADEConstant.SEARCH_NAME_NAME + " : " + nomTache
			    + "\n");
		}
		if (!executant.equals("*")) {
		    fw.write(KMADEConstant.EDITOR_EXECUTANT_NAME + " : "
			    + executant + "\n");
		}
		if (!operateur.equals("*")) {
		    fw.write(KMADEConstant.EDITOR_OPERATOR_NAME + " : "
			    + operateur + "\n");
		}
		if (!inter.equals("*")) {
		    fw.write(KMADEConstant.SEARCH_INT_NAME + " : " + inter
			    + "\n");
		}
		if (!facul.equals("*")) {
		    fw.write(KMADEConstant.SEARCH_FAC_NAME + " : " + facul
			    + "\n");
		}

		if (regExpr) {
		    fw.write(KMADEConstant.REGULAR_EXPRESSIONS_OPTIONS_FIND_MESSAGE
			    + "\n");
		}

		if (caseSensitive) {
		    fw.write(KMADEConstant.CASE_SENSITIVE_OPTIONS_FIND_MESSAGE
			    + "\n");
		}

		if (wholeWord) {
		    fw.write(KMADEConstant.WHOLE_TASK_NAME_OPTIONS_FIND_MESSAGE
			    + "\n");
		}

		if (scope) {
		    fw.write(KMADEConstant.ALL_FIND_MESSAGE + "\n");
		} else {
		    fw.write(KMADEConstant.SELECTION_FIND_MESSAGE + "\n");
		}

		fw.write("\n");

		fw.write("* " + KMADEConstant.SEARCH_RESULT_FIND_MESSAGE
			+ " *\n");
		if (SearchAdaptator.getSearchTasks().size() == 0) {
		    fw.write(KMADEConstant.TASK_NOT_FOUNDED_ACCORDING_FIND_CRITERIAS
			    + "\n");
		} else {
		    fw.write(KMADEConstant.COUNT_TASKS_FOUNDED_MESSAGE + " : "
			    + SearchAdaptator.getSearchTasks().size() + "\n");
		}
		for (int i = 0; i < SearchAdaptator.getSearchTasks().size(); i++) {
		    fw.write(SearchAdaptator.getSearchTasks().get(i)
			    .getNumero()
			    + " - "
			    + SearchAdaptator.getSearchTasks().get(i).getName()
			    + "\n");
		}
		fw.close();
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_TXT_FILE_OK);
	    } catch (IOException e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.WRITE_TXT_FILE_ERROR);
	    }
	}
    }

    public static void selectTasksFromOccurence(Tache selectedTask, int p) {
	SearchAdaptator.setCurrent(p);
	GraphicEditorAdaptator.setSelectionTask(selectedTask);
	GraphicEditorAdaptator
		.getTaskModelPanel()
		.getJGraph()
		.scrollCellToVisible(
			(KMADEDefaultGraphCell) ((Tache) selectedTask)
				.getJTask());
    }

    public static void setToforward() {
	side = true;
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.setNextButtonName();
	if (SearchAdaptator.hasNextReplace()) {
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .enableReplaceButtons();
	} else {
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .disableReplaceButtons();
	}
    }

    public static void setToBackward() {
	side = false;
	GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		.setPreviousButtonName();
	if (SearchAdaptator.hasPreviousReplace()) {
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .enableReplaceButtons();
	} else {
	    GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog()
		    .disableReplaceButtons();
	}
    }

    private static int getCurrent() {
	return current;
    }

    private static void nextCurrent() {
	if (current + 1 <= searchTache.size()) {
	    current++;
	}
    }

    private static void previousCurrent() {
	if (current > 0) {
	    current--;
	}
    }

    private static void initCurrent() {
	current = 0;
    }

    private static void setCurrent(int p) {
	current = p;
    }

    private static boolean hasNextReplace() {
	return (current + 1 < searchTache.size());
    }

    private static boolean hasPreviousReplace() {
	return (current > 0);
    }

    private static Tache replaceFromCriterias(String taskNameFind,
	    boolean wholeName, String replaceTaskName, String executant,
	    String operateur, int inter, int facul) {
	if (searchTache.size() != 0) {
	    Tache myTask = searchTache.get(current);
	    if (wholeName) {
		myTask.setName(replaceTaskName);
	    } else {
		myTask.setName(myTask
			.getName()
			.toLowerCase()
			.replaceAll(taskNameFind.toLowerCase(), replaceTaskName));
	    }
	    if (!executant.equals("*")) {
		myTask.setExecutant(Executant
			.getLocaleExecutantIntoExecutant(executant));
	    }
	    if (!operateur.equals("*")) {
		myTask.setDecomposition(Decomposition
			.getLocaleDecompositionIntoDecomposition(operateur));
	    }
	    if (inter != 0) {
		myTask.setInterruptible(inter == 1 ? true : false);
	    }
	    if (facul != 0) {
		myTask.setFacultatif(facul == 1 ? true : false);
	    }
	    return myTask;
	} else {
	    return null;
	}
    }

    private static boolean findFromCriterias(ArrayList<Tache> allTache,
	    String findTaskName, String executant, String operateur, int inter,
	    int facul, boolean regExpression, boolean caseSensitive,
	    boolean wholeWord) {
	searchTache.removeAll(searchTache);
	boolean statut = false;
	boolean a = false, b = false, c, d, e;

	if (!caseSensitive) {
	    findTaskName = findTaskName.toLowerCase();
	}

	for (int i = 0; i < allTache.size(); i++) {
	    // Egalité des statuts de l'optionalité
	    if (facul == 1)
		a = true;
	    else if (facul == 2)
		a = false;
	    a = (allTache.get(i).getFacultatif() == a || facul == 0);
	    // Interuptibilité
	    if (inter == 1)
		b = true;
	    else if (facul == 2)
		b = false;
	    b = (allTache.get(i).getInterruptible() == b || inter == 0);

	    // Prise en compte de la sensibilité à la case
	    String taskNameTemp = allTache.get(i).getName();

	    if (!caseSensitive) {
		taskNameTemp = taskNameTemp.toLowerCase();
	    }

	    // Traitement du nom
	    boolean v;
	    if (regExpression) {
		Pattern p = Pattern.compile(findTaskName);
		Matcher m = p.matcher(taskNameTemp);
		v = m.matches();
	    } else {
		if (wholeWord) {
		    v = taskNameTemp.equals(findTaskName);
		} else {
		    Pattern p = Pattern.compile(".*" + findTaskName + ".*");
		    Matcher m = p.matcher(taskNameTemp);
		    v = m.matches();
		}
	    }

	    c = (v || findTaskName.equalsIgnoreCase(""));
	    // Exécutant
	    d = (allTache.get(i).getExecutant().getValue() == Executant
		    .getLocaleExecutantIntoEnumere(executant) || executant == "*");
	    // Opérateur
	    e = (allTache.get(i).getOrdonnancement().getValue() == Decomposition
		    .getLocaleDecompositionIntoEnumere(operateur) || operateur == "*");

	    if (a && b && c && d && e) {
		statut = true;
		((KMADEDefaultGraphCell) allTache.get(i).getJTask())
			.setFlagSearch(true);
		SearchAdaptator.getSearchTasks().add(allTache.get(i));
	    }
	}
	if (statut) {
	    SearchAdaptator.initCurrent();
	}
	return statut;
    }

    private static ArrayList<Tache> getSearchTasks() {
	return searchTache;
    }
}
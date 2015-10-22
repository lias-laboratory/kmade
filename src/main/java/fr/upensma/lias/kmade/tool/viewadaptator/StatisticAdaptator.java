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

import javax.swing.SwingUtilities;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public final class StatisticAdaptator {
    private static SwingWorker worker;

    private static ArrayList<Object[]> myMessageList = new ArrayList<Object[]>();

    public static void openStatisticDialog() {
	StatisticAdaptator.statisticTaskModel();
	GraphicEditorAdaptator.getMainFrame().getStatisticDialog()
		.setVisible(true);
    }

    public static void stopStatisticTaskModel() {
	if (worker != null) {
	    worker.interrupt();
	}
    }

    public static void statisticTaskModel() {
	if (worker != null) {
	    worker.interrupt();
	    worker = null;
	}
	worker = new SwingWorker() {
	    public Object construct() {
		myMessageList.clear();
		checkStatisticModels();
		return null;
	    }
	};
	worker.start();
    }

    /**
     * Cette méthode devra être "threadée" pour plus d'efficacité
     */
    private static synchronized void checkStatisticModels() {
	ArrayList<Task> myTaskList = ExpressTask.getTasksFromExpress();

	myMessageList.add(getTaskCount(myTaskList));
	ArrayList<Object[]> temp = getTaskExecutant(myTaskList);
	for (Object[] current : temp) {
	    myMessageList.add(current);
	}
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		GraphicEditorAdaptator.getMainFrame().getStatisticDialog()
			.setStatisticInfos(myMessageList);
	    }
	});
    }

    private static Object[] getTaskCount(ArrayList<Task> myTaskList) {
	Object[] temp = { KMADEConstant.COUNT_TASK_INTO_TASK_MODELS_MESSAGE,
		myTaskList.size() };
	return temp;
    }

    private static ArrayList<Object[]> getTaskExecutant(
	    ArrayList<Task> myTaskList) {
	int unknownExe = 0;
	int abstractExe = 0;
	int userExe = 0;
	int systemExe = 0;
	int interactExe = 0;
	int unknownDec = 0;
	int enablingDec = 0;
	int choiceDec = 0;
	int concurrentDec = 0;
	int elementaryDec = 0;
	int noOrderDec = 0;
	for (Task current : myTaskList) {
	    if (current.getExecutor().equals(Executor.USER)) {
		userExe++;
	    } else if (current.getExecutor().equals(Executor.INCONNU)) {
		unknownExe++;
	    } else if (current.getExecutor().equals(Executor.INT)) {
		interactExe++;
	    } else if (current.getExecutor().equals(Executor.SYS)) {
		systemExe++;
	    } else if (current.getExecutor().equals(Executor.ABS)) {
		abstractExe++;
	    }

	    if (current.getOrdering().equals(Decomposition.ALT)) {
		choiceDec++;
	    } else if (current.getOrdering().equals(Decomposition.ELE)) {
		elementaryDec++;
	    } else if (current.getOrdering().equals(Decomposition.ET)) {
		noOrderDec++;
	    } else if (current.getOrdering().equals(Decomposition.INCONNU)) {
		unknownDec++;
	    } else if (current.getOrdering().equals(Decomposition.PAR)) {
		concurrentDec++;
	    } else if (current.getOrdering().equals(Decomposition.SEQ)) {
		enablingDec++;
	    }
	}
	ArrayList<Object[]> temp = new ArrayList<Object[]>();
	Object[] unknownExeTab = { KMADEConstant.COUNT_UNKNOWN_TASK_MESSAGE,
		unknownExe };
	Object[] abstractExeTab = { KMADEConstant.COUNT_ABSTRACT_TASK_MESSAGE,
		abstractExe };
	Object[] userExeTab = { KMADEConstant.COUNT_USER_TASK_MESSAGE, userExe };
	Object[] systemExeTab = { KMADEConstant.COUNT_SYSTEM_TASK_MESSAGE,
		systemExe };
	Object[] interactExeTab = { KMADEConstant.COUNT_INTERACT_TASK_MESSAGE,
		interactExe };
	temp.add(unknownExeTab);
	temp.add(abstractExeTab);
	temp.add(userExeTab);
	temp.add(systemExeTab);
	temp.add(interactExeTab);

	Object[] unknownDecTab = {
		KMADEConstant.COUNT_UNKNOWN_DEC_TASK_MESSAGE, unknownDec };
	Object[] enablingDecTab = {
		KMADEConstant.COUNT_ENABLING_DEC_TASK_MESSAGE, enablingDec };
	Object[] choiceDecTab = { KMADEConstant.COUNT_CHOICE_DEC_TASK_MESSAGE,
		choiceDec };
	Object[] concurrentDecTab = {
		KMADEConstant.COUNT_CONCURRENT_DEC_TASK_MESSAGE, concurrentDec };
	Object[] elementaryDecTab = {
		KMADEConstant.COUNT_ELEMENTARY_DEC_TASK_MESSAGE, elementaryDec };
	Object[] noOrderDecTab = {
		KMADEConstant.COUNT_NO_ORDER_DEC_TASK_MESSAGE, noOrderDec };
	temp.add(unknownDecTab);
	temp.add(enablingDecTab);
	temp.add(choiceDecTab);
	temp.add(concurrentDecTab);
	temp.add(elementaryDecTab);
	temp.add(noOrderDecTab);

	return temp;
    }

    public static void closeStatisticDialog() {
	GraphicEditorAdaptator.getMainFrame().getStatisticDialog()
		.setVisible(false);
    }
}

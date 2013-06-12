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

import java.util.Observer;

import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.Iteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyIteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEObservable;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public final class ExpressIteration {

    private static boolean done = false;

    private static boolean canceled = false;

    private static boolean begining = false;

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

    public static void setIteration(Task pTache, NodeExpression node) {
	pTache.getIterExpression().setNodeExpression(node);
	notifyObservers();
    }

    public static NodeExpression getIteration(Task pTache) {
	return pTache.getIterExpression().getNodeExpression();
    }

    public static boolean isFinished(Task myTache) {
	return myTache.getIterExpression().isFinished();
	/*
	  if (myTache.getIteExpression().isVariableExpressionNode()) { return
	  (myTache.getIteExpression().getIterationVariant() <= 0); } else {
	  return (Boolean)
	  myTache.getIteExpression().getNodeExpression().getNodeValue(); }
	 */
    }

    public static void evaluateIteration(Task myTache) {
	// TODO : prendre en compte le prédicat pour l'expression itération.
	// si c'est un compteur il faut d�cr�menter
	if (myTache.getIterExpression().isNumberVarient()) {
	    myTache.getIterExpression().decreaseCounter();
	} else {
	    // on ne fait rien?
	}
    }

    public static void makeAndCheckIterationOpenSPFFile() {
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		Task[] tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CHECK_ALL_ITERATIONS);
		for (int i = 0; i < tacheToBeCreated.length
			&& !ExpressIteration.isCanceled(); i++) {
		    String iteration = tacheToBeCreated[i].getIterExpression()
			    .getName();
		    // Transformation de la cha�ne de caract�res en flux de
		    // caract�res.
		    java.io.StringReader sr = new java.io.StringReader(
			    iteration);
		    java.io.Reader r = new java.io.BufferedReader(sr);
		    Iteration parser = new MyIteration(r);
		    try {
			NodeExpression ref = parser.expression();
			if (ref == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PARSER_PROBLEM_MESSAGE);
			    ExpressIteration.setIteration(tacheToBeCreated[i],
				    null);
			} else {
			    ref.checkNode();
			    ExpressIteration.setIteration(tacheToBeCreated[i],
				    ref);
			}
		    } catch (SemanticException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressIteration
				.setIteration(tacheToBeCreated[i], null);
		    } catch (ParseException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SYNTAXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressIteration
				.setIteration(tacheToBeCreated[i], null);
		    } catch (TokenMgrError e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressIteration
				.setIteration(tacheToBeCreated[i], null);
		    } catch (Error e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressIteration
				.setIteration(tacheToBeCreated[i], null);
		    }
		}

		if (!ExpressIteration.isCanceled()) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_CHECKED_AND_BUILT_MESSAGE);
		    ExpressIteration.done = true;
		}
		return null;
	    }
	};
	worker.start();
    }

    /**
     * @return Returns the begining.
     */
    public static boolean isBegining() {
	return begining;
    }

    /**
     * @param begining
     *            The begining to set.
     */
    public static void setBegining(boolean begining) {
	ExpressIteration.begining = begining;
    }

    /**
     * @return Returns the canceled.
     */
    public static boolean isCanceled() {
	return canceled;
    }

    /**
     * @param canceled
     *            The canceled to set.
     */
    public static void setCanceled(boolean canceled) {
	ExpressIteration.canceled = canceled;
    }

    /**
     * @return Returns the done.
     */
    public static boolean isDone() {
	return done;
    }

    /**
     * @param done
     *            The done to set.
     */
    public static void setDone(boolean done) {
	ExpressIteration.done = done;
    }

    public static void setIterationDescription(Task task,
	    String descriptionArea) {
	task.getIterExpression().setDescription(descriptionArea);
    }
}

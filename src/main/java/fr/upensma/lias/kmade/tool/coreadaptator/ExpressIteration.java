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
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.Iteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyIteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
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

    public static void setIteration(Tache pTache, NodeExpression node) {
	pTache.getIteExpression().setNodeExpression(node);
	notifyObservers();
    }

    public static NodeExpression getIteration(Tache pTache) {
	return pTache.getIteExpression().getNodeExpression();
    }

    public static boolean isFinished(Tache myTache) {
	return myTache.getIteExpression().isFinished();
	/*
	 * if (myTache.getIteExpression().isVariableExpressionNode()) { return
	 * (myTache.getIteExpression().getIterationVariant() <= 0); } else {
	 * return (Boolean)
	 * myTache.getIteExpression().getNodeExpression().getNodeValue(); }
	 */
    }

    public static void evaluateIteration(Tache myTache) {
	// TODO : prendre en compte le prédicat pour l'expression itération.
	// si c'est un compteur il faut d�cr�menter
	if (myTache.getIteExpression().isNumberVarient()) {
	    myTache.getIteExpression().decreaseCounter();
	} else {
	    // on ne fait rien?
	}
    }

    public static void makeAndCheckIterationOpenSPFFile() {
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		Tache[] tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
		System.out.println();
		System.out.println(KMADEConstant.CHECK_ALL_ITERATIONS);
		for (int i = 0; i < tacheToBeCreated.length
			&& !ExpressIteration.isCanceled(); i++) {
		    String iteration = tacheToBeCreated[i].getIteExpression()
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
			    System.out
				    .println(KMADEConstant.PARSER_PROBLEM_MESSAGE);
			    ExpressIteration.setIteration(tacheToBeCreated[i],
				    null);
			} else {
			    ref.checkNode();
			    ExpressIteration.setIteration(tacheToBeCreated[i],
				    ref);
			}
		    } catch (SemanticException e) {
			System.out
				.println(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
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
			System.out
				.println(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
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
			System.out
				.println(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
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
			System.out
				.println(KMADEConstant.ITERATION_OF_TASK_NO_OK_MESSAGE
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
		    System.out
			    .println(KMADEConstant.ITERATION_CHECKED_AND_BUILT_MESSAGE);
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

    public static void setIterationDescription(Tache task,
	    String descriptionArea) {
	task.getIteExpression().setDescription(descriptionArea);
    }
}
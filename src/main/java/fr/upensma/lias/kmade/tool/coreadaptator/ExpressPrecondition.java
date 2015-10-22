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
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyPrecondition;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.Precondition;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEObservable;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public final class ExpressPrecondition {

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

    public static void setPreconditionDescription(Task pTache, String p) {
	pTache.getPreExpression().setDescription(p);
    }

    public static String getPreconditionDescription(Task pTache) {
	return pTache.getPreExpression().getDescription();
    }

    public static void setPrecondition(Task pTache, NodeExpression node) {
	pTache.getPreExpression().setNodeExpression(node);
	notifyObservers();
    }

    public static NodeExpression getPrecondition(Task pTache) {
	return pTache.getPreExpression().getNodeExpression();
    }

    public static void makeAndCheckPreconditionOpenSPFFile() {
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		Task[] tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CHECK_ALL_PRECONDITIONS);
		for (int i = 0; i < tacheToBeCreated.length
			&& !ExpressPrecondition.isCanceled(); i++) {
		    String precondtion = tacheToBeCreated[i].getPreExpression()
			    .getFormalText();
		    // Transformation de la cha�ne de caract�res en flux de
		    // caract�res.
		    java.io.StringReader sr = new java.io.StringReader(
			    precondtion);
		    java.io.Reader r = new java.io.BufferedReader(sr);
		    Precondition parser = new MyPrecondition(r);
		    try {
			NodeExpression ref = parser.expression();
			if (ref == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PARSER_PROBLEM_MESSAGE);
			    ExpressPrecondition.setPrecondition(
				    tacheToBeCreated[i], null);
			} else {
			    ref.checkNode();
			    ExpressPrecondition.setPrecondition(
				    tacheToBeCreated[i], ref);
			}
		    } catch (SemanticException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressPrecondition.setPrecondition(
				tacheToBeCreated[i], null);
		    } catch (ParseException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SYNTAXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressPrecondition.setPrecondition(
				tacheToBeCreated[i], null);
		    } catch (TokenMgrError e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressPrecondition.setPrecondition(
				tacheToBeCreated[i], null);
		    } catch (Error e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressPrecondition.setPrecondition(
				tacheToBeCreated[i], null);
		    }
		}

		if (!ExpressPrecondition.isCanceled()) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_CHECKED_AND_BUILT_MESSAGE);
		    ExpressPrecondition.done = true;
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
	ExpressPrecondition.begining = begining;
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
	ExpressPrecondition.canceled = canceled;
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
	ExpressPrecondition.done = done;
    }
}

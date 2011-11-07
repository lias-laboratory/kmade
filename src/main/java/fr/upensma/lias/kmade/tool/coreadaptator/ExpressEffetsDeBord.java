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

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.expression.CurrentObject;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.EffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyEffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEObservable;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public class ExpressEffetsDeBord {

    private static boolean done = false;

    private static boolean canceled = false;

    private static boolean begining = false;

    private static KMADEObservable expressTaskObservable = new KMADEObservable();

    public static CurrentObject getCurrentObject() {
	return InterfaceExpressJava.getCurrentObject();
    }

    public static void clearCurrentObject() {
	InterfaceExpressJava.getCurrentObject()
		.clearCurrentEvaluateConcreteObject();
    }

    public static void addObserver(Observer o) {
	expressTaskObservable.addObserver(o);
    }

    public static void notifyObservers() {
	expressTaskObservable.notifyKMADEObserver();
    }

    public static void notifyObservers(Object argv) {
	expressTaskObservable.notifyKMADEObserver(argv);
    }

    public static void setEffetsDeBord(Tache pTache, NodeExpression node) {
	pTache.getEffetsDeBordExpression().setNodeExpression(node);
	notifyObservers();
    }

    public static NodeExpression getEffetsDeBord(Tache pTache) {
	return pTache.getEffetsDeBordExpression().getNodeExpression();
    }

    public static void makeAndCheckEffetsDeBordOpenSPFFile() {
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		Tache[] tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CHECK_ALL_EFFETSDEBORDS);
		for (int i = 0; i < tacheToBeCreated.length
			&& !ExpressEffetsDeBord.isCanceled(); i++) {
		    String effetsdebord = tacheToBeCreated[i]
			    .getEffetsDeBordExpression().getName();
		    // Transformation de la cha�ne de caract�res en flux de
		    // caract�res.
		    java.io.StringReader sr = new java.io.StringReader(
			    effetsdebord);
		    java.io.Reader r = new java.io.BufferedReader(sr);
		    EffetsDeBord parser = new MyEffetsDeBord(r);
		    try {
			NodeExpression ref = parser.expression();
			if (ref == null) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PARSER_PROBLEM_MESSAGE);
			    ExpressEffetsDeBord.setEffetsDeBord(
				    tacheToBeCreated[i], null);
			} else {
			    ref.checkNode();
			    ExpressEffetsDeBord.setEffetsDeBord(
				    tacheToBeCreated[i], ref);
			}
		    } catch (SemanticException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressEffetsDeBord.setEffetsDeBord(
				tacheToBeCreated[i], null);
		    } catch (ParseException e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.SYNTAXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressEffetsDeBord.setEffetsDeBord(
				tacheToBeCreated[i], null);
		    } catch (TokenMgrError e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressEffetsDeBord.setEffetsDeBord(
				tacheToBeCreated[i], null);
		    } catch (Error e) {
		    	KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EFFETSDEBORD_OF_TASK_NO_OK_MESSAGE
					+ " : "
					+ tacheToBeCreated[i].getName()
					+ ". "
					+ KMADEConstant.CAUSE_MESSAGE
					+ " : "
					+ KMADEConstant.LEXICAL_ERROR_MESSAGE
					+ " : " + e.getMessage());
			ExpressEffetsDeBord.setEffetsDeBord(
				tacheToBeCreated[i], null);
		    }
		}

		if (!ExpressEffetsDeBord.isCanceled()) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.EFFETSDEBORD_CHECKED_AND_BUILT_MESSAGE);
		    ExpressEffetsDeBord.done = true;
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
	ExpressEffetsDeBord.begining = begining;
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
	ExpressEffetsDeBord.canceled = canceled;
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
	ExpressEffetsDeBord.done = done;
    }

    public static void setEffetsDeBordDescription(Tache task,
	    String descriptionArea) {
	task.getEffetsDeBordExpression().setDescription(descriptionArea);
    }
}

/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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

import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIteration;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.Iteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyIteration;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorPrePostIterDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class IterationAdaptator {

	public static void setNewToken(String v) {
		KMADEEditorPrePostIterDialog.getIterationPanel().appendToken(v);
	}

	public static void setNewVariantToken(String v) {
		KMADEEditorPrePostIterDialog.getIterationPanel().replaceToken(v);
	}

	public static void modifiedExpression() {
		KMADEEditorPrePostIterDialog.getIterationPanel()
				.disableEvaluateControl(KMADEConstant.NOT_YET_CHECKED_ITERATION_MESSAGE);
	}

	public static void clearIteration() {
		ExpressTask.clearIterExpression(GraphicEditorAdaptator.getSelectedGraphicTask().getTask());
		IterationAdaptator.initIteration();
	}

	public static void initIteration() {
		// Active ou pas l'�valuation
		Task myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
		if (myCurrentTask.getIterExpression().getNodeExpression() != null) {
			// Construire l'expression.
			ArrayList<Object> myList = myCurrentTask.getIterExpression().getNodeExpression().getLinearExpression();
			KMADEEditorPrePostIterDialog.getIterationPanel().enabledEvaluateControl(myList);
			KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
		} else {
			KMADEEditorPrePostIterDialog.getIterationPanel()
					.disableEvaluateControl(KMADEConstant.ITERATION_ERROR_MESSAGE);
		}
	}

	public static void checkIteration(String s) {
		KMADEHistoryMessageManager.printMessage(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
		IterationAdaptator.checkIterationBuilder(s);
		IterationAdaptator.initIteration();
	}

	private static void checkIterationBuilder(String s) {
		Task myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
		myCurrentTask.getIterExpression().setFormalText(s);

		// Transformation de la cha�ne de caract�res en flux de caract�res.
		java.io.StringReader sr = new java.io.StringReader(s);
		java.io.Reader r = new java.io.BufferedReader(sr);

		Iteration parser = new MyIteration(r);
		try {
			NodeExpression ref = parser.expression();
			if (ref == null) {
				KMADEHistoryMessageManager
						.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.PARSER_PROBLEM_MESSAGE);
				ExpressIteration.setIteration(myCurrentTask, null);
			} else {
				ref.checkNode();
				ExpressIteration.setIteration(myCurrentTask, ref);
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_OK_MESSAGE);
			}
		} catch (SemanticException e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
			ExpressIteration.setIteration(myCurrentTask, null);
			return;
		} catch (ParseException e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
					+ KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " : " + e.getMessage());
			ExpressIteration.setIteration(myCurrentTask, null);
			return;
		} catch (TokenMgrError e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(
					KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
			ExpressIteration.setIteration(myCurrentTask, null);
			return;
		} catch (Error e) {
			// Erreur normalement qui doit venir de AttributExpressExpression ou
			// GroupExpressExpression
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
			ExpressIteration.setIteration(myCurrentTask, null);
			return;
		}
	}

	public static void addLoopVariant(String l) {
		try {
			new NumberValue(l);
		} catch (NumberFormatException e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.STRING_TO_INTEGER);
			return;
		}
		IterationAdaptator.setNewVariantToken("[" + l + "]");
	}

	public static void addNewLiteral(String l, String t) {
		if (l.equals(TypeStructure.BOOLEAN_STRUCT.getValue())) {
			t = Boolean.valueOf(Boolean.parseBoolean(t)).toString();
		} else if (l.equals(TypeStructure.NUMBER_STRUCT.getValue())) {
			try {
				new NumberValue(t);
			} catch (NumberFormatException e) {
				KMADEHistoryMessageManager.printlnMessage(KMADEConstant.STRING_TO_INTEGER);
				return;
			}
		} else if (l.equals(TypeStructure.STRING_STRUCT.getValue())) {
			t = "'" + t + "'";
		}
		IterationAdaptator.setNewToken(t);
	}

	public static void evaluateIteration(String text) {
		KMADEHistoryMessageManager.printMessage(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
		Task myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
		try {
			myCurrentTask.getIterExpression().getNodeExpression().evaluateNode(null);
			KMADEEditorPrePostIterDialog.getIterationPanel()
					.giveResult((myCurrentTask.getIterExpression().isFinished()
							? KMADEConstant.CONTINUE_LOOP_ITERATION_MESSAGE
							: KMADEConstant.STOP_LOOP_ITERATION_MESSAGE) + " =");
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_EVAL_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.VALUE_MESSAGE + " = "
					+ myCurrentTask.getIterExpression().getNodeExpression().getNodeValue() + " , "
					+ (myCurrentTask.getIterExpression().isLoopProgress()
							? KMADEConstant.CONTINUE_LOOP_ITERATION_MESSAGE
							: KMADEConstant.STOP_LOOP_ITERATION_MESSAGE));
		} catch (SemanticErrorException e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_EVAL_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
					+ KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());
			KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
		} catch (SemanticUnknownException e) {
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.ITERATION_EVAL_NO_OK_MESSAGE);
			KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
					+ KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , " + e.getMessage());
			KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
		} catch (SemanticException e) {
		}
	}

	public static void setIteration() {
		ExpressIteration.setIterationDescription(GraphicEditorAdaptator.getSelectedGraphicTask().getTask(),
				KMADEEditorPrePostIterDialog.getIterationPanel().getDescriptionArea());
		TaskPropertiesAdaptator.setIterationInModel();
	}
}
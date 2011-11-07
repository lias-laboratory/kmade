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

import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressPrecondition;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyPrecondition;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.Precondition;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorPrePostIterDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public final class PreconditionAdaptator {    

    public static void initPrecondition() {
	// Active ou pas l'évaluation
	Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask()
		.getTask();
	if (myCurrentTask.getPreExpression().getNodeExpression() != null) {
	    // Construire l'expression
	    ArrayList<Object> myList = myCurrentTask.getPreExpression()
		    .getNodeExpression().getLinearExpression();
	    KMADEEditorPrePostIterDialog.getPreconditionPanel()
		    .enabledEvaluateControl(myList);
	    KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(
		    KMADEConstant.VALUE_MESSAGE + " ? =");
	} else {
	    KMADEEditorPrePostIterDialog.getPreconditionPanel()
		    .disableEvaluateControl(
			    KMADEConstant.PRECONDITION_ERROR_MESSAGE);
	}
    }

    public static void modifiedExpression() {
	KMADEEditorPrePostIterDialog.getPreconditionPanel()
		.disableEvaluateControl(
			KMADEConstant.NOT_YET_CHECKED_PRECONDITION_MESSAGE);
    }

    public static void checkPrecondition(String s) {
    	KMADEHistoryMessageManager.printMessage(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
	PreconditionAdaptator.checkPreconditionBuilder(s);
	PreconditionAdaptator.initPrecondition();
    }

    public static void evaluatePrecondition(String text) {
    	KMADEHistoryMessageManager.printMessage(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
	Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask()
		.getTask();
	try {
	    myCurrentTask.getPreExpression().getNodeExpression()
		    .evaluateNode(null);
	    KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(
		    myCurrentTask.getPreExpression().getNodeExpression()
			    .getNodeValue().toString()
			    + " =");
	    KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_EVAL_OK_MESSAGE);
	    KMADEHistoryMessageManager.printlnMessage(KMADEConstant.VALUE_MESSAGE
		    + " = "
		    + myCurrentTask.getPreExpression().getNodeExpression()
			    .getNodeValue());
	} catch (SemanticErrorException e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_EVAL_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , "
		    + e.getMessage());
	    KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(
		    KMADEConstant.VALUE_MESSAGE + " ? =");
	} catch (SemanticUnknownException e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_EVAL_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , "
		    + e.getMessage());
	    KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(
		    KMADEConstant.VALUE_MESSAGE + " ? =");
	} catch (SemanticException e) {
	}
    }

    private static void checkPreconditionBuilder(String s) {
	Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask()
		.getTask();
	myCurrentTask.getPreExpression().setName(s);

	// Transformation de la chaîne de caractères en flux de caractères
	java.io.StringReader sr = new java.io.StringReader(s);
	java.io.Reader r = new java.io.BufferedReader(sr);

	Precondition parser = new MyPrecondition(r);
	try {
	    NodeExpression ref = parser.expression();
	    if (ref == null) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
			+ KMADEConstant.PARSER_PROBLEM_MESSAGE);
		ExpressPrecondition.setPrecondition(myCurrentTask, null);
	    } else {
		ref.checkNode();
		ExpressPrecondition.setPrecondition(myCurrentTask, ref);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_OK_MESSAGE);
	    }
	} catch (SemanticException e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , "
		    + e.getMessage());
	    ExpressPrecondition.setPrecondition(myCurrentTask, null);
	    return;
	} catch (ParseException e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " , "
		    + e.getMessage());
	    ExpressPrecondition.setPrecondition(myCurrentTask, null);
	    return;
	} catch (TokenMgrError e) {
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.LEXICAL_ERROR_MESSAGE + " , "
		    + e.getMessage());
	    ExpressPrecondition.setPrecondition(myCurrentTask, null);
	    return;
	} catch (Error e) {
	    // Erreur normalement qui doit venir de AttributExpressExpression ou
	    // GroupExpressExpression
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
		KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CAUSE_MESSAGE + " : "
		    + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , "
		    + e.getMessage());
	    ExpressPrecondition.setPrecondition(myCurrentTask, null);
	    return;
	}
    }

    public static void clearPrecondition() {
	ExpressTask.clearPreExpression(GraphicEditorAdaptator
		.getSelectedGraphicTask().getTask());
	PreconditionAdaptator.initPrecondition();
    }

    public static void setNewToken(String v) {
	KMADEEditorPrePostIterDialog.getPreconditionPanel().appendToken(v);
    }

    public static void setPrecondition() {
	ExpressPrecondition.setPreconditionDescription(GraphicEditorAdaptator
		.getSelectedGraphicTask().getTask(),
		KMADEEditorPrePostIterDialog.getPreconditionPanel()
			.getDescriptionArea());
	TaskPropertiesAdaptator.setPreconditionInModel();
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
	PreconditionAdaptator.setNewToken(t);
    }
}
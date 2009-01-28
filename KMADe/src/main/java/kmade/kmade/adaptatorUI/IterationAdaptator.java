package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.KMADEEditorPrePostIterDialog;
import kmade.kmade.adaptatorFC.ExpressIteration;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.kmade.adaptatorFC.parserExpression.Iteration;
import kmade.kmade.adaptatorFC.parserExpression.ParseException;
import kmade.kmade.adaptatorFC.parserExpression.TokenMgrError;
import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.SemanticErrorException;
import kmade.nmda.schema.expression.SemanticException;
import kmade.nmda.schema.expression.SemanticUnknownException;
import kmade.nmda.schema.metaobjet.TypeStructure;
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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public final class IterationAdaptator {
   
    public static void setNewToken(String v) {
        KMADEEditorPrePostIterDialog.getIterationPanel().appendToken(v);
    }    
    
    public static void setNewVariantToken(String v) {
        KMADEEditorPrePostIterDialog.getIterationPanel().replaceToken(v);
    }
   
    public static void modifiedExpression() {
        KMADEEditorPrePostIterDialog.getIterationPanel().disableEvaluateControl(KMADEConstant.NOT_YET_CHECKED_ITERATION_MESSAGE);
    }
    
    public static void clearIteration() {
        ExpressTask.clearIterExpression(GraphicEditorAdaptator.getSelectedGraphicTask().getTask());
        IterationAdaptator.initIteration();
    }
    
    public static void initIteration() {
        // Active ou pas l'�valuation
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        if (myCurrentTask.getIteExpression().getNodeExpression() != null) {
            // Construire l'expression.
            ArrayList<Object> myList = myCurrentTask.getIteExpression().getNodeExpression().getLinearExpression();
            KMADEEditorPrePostIterDialog.getIterationPanel().enabledEvaluateControl(myList);
            KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
        } else {            
            KMADEEditorPrePostIterDialog.getIterationPanel().disableEvaluateControl(KMADEConstant.ITERATION_ERROR_MESSAGE);
        }
    }
    
    public static void checkIteration(String s) {
        System.out.print(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
        IterationAdaptator.checkIterationBuilder(s);   
        IterationAdaptator.initIteration();
    }
    
    private static void checkIterationBuilder(String s) {        
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        myCurrentTask.getIteExpression().setName(s);
        
        // Transformation de la cha�ne de caract�res en flux de caract�res.
        java.io.StringReader sr = new java.io.StringReader(s);
        java.io.Reader r = new java.io.BufferedReader(sr);
        
        Iteration parser = new Iteration(r);
        try{
            NodeExpression ref = parser.expression();
            if (ref == null) {
                System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.PARSER_PROBLEM_MESSAGE);
                ExpressIteration.setIteration(myCurrentTask,null);
            } else {
                ref.checkNode();
                ExpressIteration.setIteration(myCurrentTask,ref);
                System.out.println(KMADEConstant.ITERATION_OK_MESSAGE);
            }
        } catch (SemanticException e) {
            System.out.println(KMADEConstant.ITERATION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressIteration.setIteration(myCurrentTask,null);
            return;
        } catch (ParseException e) {   
            System.out.println(KMADEConstant.ITERATION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressIteration.setIteration(myCurrentTask,null);
            return;
        } catch (TokenMgrError e) {
            System.out.println(KMADEConstant.ITERATION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressIteration.setIteration(myCurrentTask,null);
            return;
        } catch (Error e) {
            // Erreur normalement qui doit venir de AttributExpressExpression ou GroupExpressExpression
            System.out.println(KMADEConstant.ITERATION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressIteration.setIteration(myCurrentTask,null);
            return;
        }
    }
    
    public static void addLoopVariant(String l) {
        try {
            Integer.parseInt(l);
        } catch(NumberFormatException e) {
            System.out.println(KMADEConstant.STRING_TO_INTEGER);
            return;                
        }
        IterationAdaptator.setNewVariantToken("[" + l + "]");
    }
    
    public static void addNewLiteral(String l, String t) {
        if (l.equals(TypeStructure.BOOLEAN_STRUCT.getValue())) {
            t = Boolean.valueOf(Boolean.parseBoolean(t)).toString();
        } else if (l.equals(TypeStructure.INTEGER_STRUCT.getValue())) {
            try {
                Integer.parseInt(t);
            } catch(NumberFormatException e) {
                System.out.println(KMADEConstant.STRING_TO_INTEGER);
                return;                
            }
        } else if (l.equals(TypeStructure.STRING_STRUCT.getValue())) {
            t = "'" + t + "'";  
        }
        IterationAdaptator.setNewToken(t);
    }

    public static void evaluateIteration(String text) {
        System.out.print(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        try {
            myCurrentTask.getIteExpression().getNodeExpression().evaluateNode(null);
            KMADEEditorPrePostIterDialog.getIterationPanel().giveResult((myCurrentTask.getIteExpression().isLoopProgress() ? KMADEConstant.CONTINUE_LOOP_ITERATION_MESSAGE : KMADEConstant.STOP_LOOP_ITERATION_MESSAGE) + " =");
            System.out.println(KMADEConstant.ITERATION_EVAL_OK_MESSAGE);
            System.out.println(KMADEConstant.VALUE_MESSAGE + " = " + myCurrentTask.getIteExpression().getNodeExpression().getNodeValue() + " , " + (myCurrentTask.getIteExpression().isLoopProgress() ? KMADEConstant.CONTINUE_LOOP_ITERATION_MESSAGE : KMADEConstant.STOP_LOOP_ITERATION_MESSAGE));
        } catch(SemanticErrorException e) {
            System.out.println(KMADEConstant.ITERATION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());
            KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
        } catch(SemanticUnknownException e) {
            System.out.println(KMADEConstant.ITERATION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , " + e.getMessage());
            KMADEEditorPrePostIterDialog.getIterationPanel().giveResult(KMADEConstant.ITERATION_MESSAGE + " ? =");
        } catch(SemanticException e) {        	
        }
    }

	public static void setIteration() {
		ExpressIteration.setIterationDescription(GraphicEditorAdaptator.getSelectedGraphicTask().getTask(), KMADEEditorPrePostIterDialog.getIterationPanel().getDescriptionArea());
		TaskPropertiesAdaptator.setIterationInModel();
	}
}
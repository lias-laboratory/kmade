package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.KMADEEditorPrePostIterDialog;
import kmade.kmade.adaptatorFC.ExpressPrecondition;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.kmade.adaptatorFC.parserExpression.MyPrecondition;
import kmade.kmade.adaptatorFC.parserExpression.ParseException;
import kmade.kmade.adaptatorFC.parserExpression.Precondition;
import kmade.kmade.adaptatorFC.parserExpression.TokenMgrError;
import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.SemanticErrorException;
import kmade.nmda.schema.expression.SemanticException;
import kmade.nmda.schema.expression.SemanticUnknownException;
import kmade.nmda.schema.metaobjet.NumberValue;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public final class PreconditionAdaptator {    
          
    public static void initPrecondition() {
        // Active ou pas l'évaluation
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        if (myCurrentTask.getPreExpression().getNodeExpression() != null) {
            // Construire l'expression
            ArrayList<Object> myList = myCurrentTask.getPreExpression().getNodeExpression().getLinearExpression();
            KMADEEditorPrePostIterDialog.getPreconditionPanel().enabledEvaluateControl(myList);
            KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(KMADEConstant.VALUE_MESSAGE + " ? =");
        } else {            
            KMADEEditorPrePostIterDialog.getPreconditionPanel().disableEvaluateControl(KMADEConstant.PRECONDITION_ERROR_MESSAGE);
        }
    }

    public static void modifiedExpression() {
        KMADEEditorPrePostIterDialog.getPreconditionPanel().disableEvaluateControl(KMADEConstant.NOT_YET_CHECKED_PRECONDITION_MESSAGE);
    }
    
    public static void checkPrecondition(String s) {
        System.out.print(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
        PreconditionAdaptator.checkPreconditionBuilder(s);   
        PreconditionAdaptator.initPrecondition();
    }
    
    public static void evaluatePrecondition(String text) {
        System.out.print(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        try {
      		myCurrentTask.getPreExpression().getNodeExpression().evaluateNode(null);
       		KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(myCurrentTask.getPreExpression().getNodeExpression().getNodeValue().toString() + " =");
            System.out.println(KMADEConstant.PRECONDITION_EVAL_OK_MESSAGE);
       		System.out.println(KMADEConstant.VALUE_MESSAGE + " = " + myCurrentTask.getPreExpression().getNodeExpression().getNodeValue());
        } catch (SemanticErrorException e) {
            System.out.println(KMADEConstant.PRECONDITION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());
			KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(KMADEConstant.VALUE_MESSAGE + " ? =");        	        			
        } catch (SemanticUnknownException e) {
            System.out.println(KMADEConstant.PRECONDITION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , " + e.getMessage());
			KMADEEditorPrePostIterDialog.getPreconditionPanel().giveResult(KMADEConstant.VALUE_MESSAGE + " ? =");        	        	
        } catch (SemanticException e) {
        }
    }
    
    private static void checkPreconditionBuilder(String s) {        
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        myCurrentTask.getPreExpression().setName(s);
        
        // Transformation de la chaîne de caractères en flux de caractères
        java.io.StringReader sr = new java.io.StringReader(s);
        java.io.Reader r = new java.io.BufferedReader(sr);
        
        Precondition parser = new MyPrecondition(r);
        try{
            NodeExpression ref = parser.expression();
            if (ref == null) {
                System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.PARSER_PROBLEM_MESSAGE);
                ExpressPrecondition.setPrecondition(myCurrentTask,null);
            } else {
                ref.checkNode();
                ExpressPrecondition.setPrecondition(myCurrentTask,ref);
                System.out.println(KMADEConstant.PRECONDITION_OK_MESSAGE);
            }
        } catch (SemanticException e) {
            System.out.println(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());
            ExpressPrecondition.setPrecondition(myCurrentTask,null);
            return;
        } catch (ParseException e) {   
            System.out.println(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " , " + e.getMessage());
            ExpressPrecondition.setPrecondition(myCurrentTask,null);
            return;
        } catch (TokenMgrError e) {
            System.out.println(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " , " + e.getMessage());
            ExpressPrecondition.setPrecondition(myCurrentTask,null);
            return;
        } catch (Error e) {
            // Erreur normalement qui doit venir de AttributExpressExpression ou GroupExpressExpression
            System.out.println(KMADEConstant.PRECONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());
            ExpressPrecondition.setPrecondition(myCurrentTask,null);
            return;
        }
    }
    
    public static void clearPrecondition() {
        ExpressTask.clearPreExpression(GraphicEditorAdaptator.getSelectedGraphicTask().getTask());
        PreconditionAdaptator.initPrecondition();
    }
    
    public static void setNewToken(String v) {
        KMADEEditorPrePostIterDialog.getPreconditionPanel().appendToken(v);
    }    
    
    public static void setPrecondition() {
    	ExpressPrecondition.setPreconditionDescription(GraphicEditorAdaptator.getSelectedGraphicTask().getTask(), KMADEEditorPrePostIterDialog.getPreconditionPanel().getDescriptionArea());
    	TaskPropertiesAdaptator.setPreconditionInModel();
    }
    
    public static void addNewLiteral(String l, String t) {
        if (l.equals(TypeStructure.BOOLEAN_STRUCT.getValue())) {
            t = Boolean.valueOf(Boolean.parseBoolean(t)).toString();
        } else if (l.equals(TypeStructure.NUMBER_STRUCT.getValue())) {
            try {
               new NumberValue(t);                
            } catch(NumberFormatException e) {
                System.out.println(KMADEConstant.STRING_TO_INTEGER);
                return;                
            }
        } else if (l.equals(TypeStructure.STRING_STRUCT.getValue())) {
            t = "'" + t + "'";  
        }
        PreconditionAdaptator.setNewToken(t);
    }
}
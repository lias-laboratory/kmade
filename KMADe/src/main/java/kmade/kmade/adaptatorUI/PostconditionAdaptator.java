package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.KMADEEditorPrePostIterDialog;
import kmade.kmade.adaptatorFC.ExpressHistory;
import kmade.kmade.adaptatorFC.ExpressPostcondition;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.kmade.adaptatorFC.parserExpression.ParseException;
import kmade.kmade.adaptatorFC.parserExpression.Postcondition;
import kmade.kmade.adaptatorFC.parserExpression.TokenMgrError;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
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
public final class PostconditionAdaptator {
       
    public static void setNewToken(String v) {
        KMADEEditorPrePostIterDialog.getPostonditionPanel().appendToken(v);
    }  
    
    public static void noHistorySelected() {
    	KMADEEditorPrePostIterDialog.getPostonditionPanel().disableLoadRemoveHistoryControl();
    }
    
    public static void historySelected() {
    	KMADEEditorPrePostIterDialog.getPostonditionPanel().enableLoadRemoveHistoryControl();
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
        PostconditionAdaptator.setNewToken(t);
    }
    
    public static void loadConcreteHistory(int indice) {
        if (indice == -1) {
            System.out.println(KMADEConstant.NO_SELECTED_HISTORIC_MESSAGE);
            return;
        } 
        if (ExpressHistory.loadConcreteHistoryAt(indice)) {
            // Pas erreur. Mise à jour des vues  Ne pas oublier d'avertir la partie "Objet Concret" d'une modification.
            // Est-ce utile?
            ConcreteObjectPanelAdaptator.updateConcreteObjectView();
            System.out.println(KMADEConstant.HISTORIC_LOADED_MESSAGE);
        } else {
            // Erreur.
            System.out.println(KMADEConstant.HISTORIC_NO_LOADED_MESSAGE);
        }
    }
    

    public static void clearAllHistory() {
    	ExpressHistory.removeAllExpressHistory();
        KMADEEditorPrePostIterDialog.getPostonditionPanel().removeAllHistory();
    }    
    
    public static boolean isAnyHistory() {
        return (ExpressHistory.getHistorySize() != 0);
    }
    
    public static void removeConcreteHistory(int indice) {
        if (indice == -1) {
            System.out.println(KMADEConstant.NO_SELECTED_HISTORIC_MESSAGE);
            return;
        }       
        ExpressHistory.removeExpressHistoryAt(indice);
        KMADEEditorPrePostIterDialog.getPostonditionPanel().removeHistory(indice);
    }

    public static void modifiedExpression() {
        KMADEEditorPrePostIterDialog.getPostonditionPanel().disableEvaluateControl(KMADEConstant.NOT_YET_CHECKED_POSTCONDITION_MESSAGE);
    }

    public static void clearPostcondition() {
        ExpressTask.clearPostExpression(GraphicEditorAdaptator.getSelectedGraphicTask().getTask());
        PostconditionAdaptator.initPostcondition();   
    }   
    
    public static void checkPostcondition(String text) {
        System.out.print(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
        PostconditionAdaptator.checkPostconditionBuilder(text);   
        PostconditionAdaptator.initPostcondition();        
    }
    
    public static void evaluatePostcondition(String text) {
        System.out.print(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        
        boolean historySuccess = ExpressHistory.saveConcreteHistory();
        
        try {
       		myCurrentTask.getPostExpression().getNodeExpression().evaluateNode(null);
       		if (historySuccess) {
                KMADEEditorPrePostIterDialog.getPostonditionPanel().addNewHistory(InterfaceExpressJava.getHistoryMessage());
                KMADEEditorPrePostIterDialog.getPostonditionPanel().enableRemoveAllHistoryControl();
       		} else {
       			if (ExpressHistory.getHistorySize() == 0) {
                    KMADEEditorPrePostIterDialog.getPostonditionPanel().disableHistoryControl();
                    KMADEEditorPrePostIterDialog.getPostonditionPanel().removeAllHistory();
       			} 
       		}
            KMADEEditorPrePostIterDialog.getPostonditionPanel().setConcreteObjectName(ExpressPostcondition.getCurrentObject().toString());
            InterfaceExpressJava.clearHistoryMessage();
            System.out.println(KMADEConstant.POSTCONDITION_EVAL_OK_MESSAGE);
        } catch (SemanticErrorException e) {
            System.out.println(KMADEConstant.POSTCONDITION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());        	
        } catch (SemanticUnknownException e) {
            System.out.println(KMADEConstant.POSTCONDITION_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , " + e.getMessage());        	
        } catch (SemanticException e) {
        	
        }
    }
    
    private static void checkPostconditionBuilder(String s) {        
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        myCurrentTask.getPostExpression().setName(s);
        
        // Transformation de la chaîne de caractères en flux de caractères.
        java.io.StringReader sr = new java.io.StringReader(s);
        java.io.Reader r = new java.io.BufferedReader(sr);
        
        Postcondition parser = new Postcondition(r);
        try{
            NodeExpression ref = parser.expression();
            if (ref == null) {
                System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.PARSER_PROBLEM_MESSAGE);
                ExpressPostcondition.setPostcondition(myCurrentTask,null);
            } else {
                ref.checkNode();
                ExpressPostcondition.setPostcondition(myCurrentTask,ref);
                System.out.println(KMADEConstant.POSTCONDITION_OK_MESSAGE);
            }
        } catch (SemanticException e) {
            System.out.println(KMADEConstant.POSTCONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressPostcondition.setPostcondition(myCurrentTask,null);
            return;
        } catch (ParseException e) {   
            System.out.println(KMADEConstant.POSTCONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressPostcondition.setPostcondition(myCurrentTask,null);
            return;
        } catch (TokenMgrError e) {
            System.out.println(KMADEConstant.POSTCONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressPostcondition.setPostcondition(myCurrentTask,null);
            return;
        } catch (Error e) {
            // Erreur normalement qui doit venir de AttributExpressExpression ou GroupExpressExpression
            System.out.println(KMADEConstant.POSTCONDITION_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressPostcondition.setPostcondition(myCurrentTask,null);
            return;
        }
    }

    public static void initHistory() {
    	KMADEEditorPrePostIterDialog.getPostonditionPanel().disableHistoryControl();
    }
    
    public static void initPostcondition() {
        // Active ou pas l'évaluation
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        if (myCurrentTask.getPostExpression().getNodeExpression() != null) {
            // Construire l'expression.
            ArrayList<Object> myList = myCurrentTask.getPostExpression().getNodeExpression().getLinearExpression();
            KMADEEditorPrePostIterDialog.getPostonditionPanel().enabledEvaluateControl(myList);
        } else {            
            KMADEEditorPrePostIterDialog.getPostonditionPanel().disableEvaluateControl(KMADEConstant.POSTCONDITION_ERROR_MESSAGE);
        }
    }

	public static void setPostcondition() {
		ExpressPostcondition.setPostconditionDescription(GraphicEditorAdaptator.getSelectedGraphicTask().getTask(), KMADEEditorPrePostIterDialog.getPostonditionPanel().getDescriptionArea());
		TaskPropertiesAdaptator.setPostConditionInModel();
	}
}

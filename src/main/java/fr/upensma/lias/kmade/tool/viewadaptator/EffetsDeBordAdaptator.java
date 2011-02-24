package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressHistory;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.EffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.MyEffetsDeBord;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.ParseException;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.TokenMgrError;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADEEditorPrePostIterDialog;


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
public final class EffetsDeBordAdaptator {
       
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
        } else if (l.equals(TypeStructure.NUMBER_STRUCT.getValue())) {
            try {
                new NumberValue(t);                
            } catch(NumberFormatException e) {
            	e.printStackTrace();
                System.out.println(KMADEConstant.STRING_TO_NUMBER);
                return;                
            }
        } else if (l.equals(TypeStructure.STRING_STRUCT.getValue())) {
            t = "'" + t + "'";  
        }
        EffetsDeBordAdaptator.setNewToken(t);
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
        KMADEEditorPrePostIterDialog.getPostonditionPanel().disableEvaluateControl(KMADEConstant.NOT_YET_CHECKED_EFFETSDEBORD_MESSAGE);
    }

    public static void clearEffetsDeBord() {
        ExpressTask.clearEffetsDeBordExpression(GraphicEditorAdaptator.getSelectedGraphicTask().getTask());
        EffetsDeBordAdaptator.initEffetsDeBord();   
    }   
    
    public static void checkEffetsDeBord(String text) {
        System.out.print(KMADEConstant.CHECK_ACTION_MESSAGE + " : ");
        EffetsDeBordAdaptator.checkEffetsDeBordBuilder(text);   
        EffetsDeBordAdaptator.initEffetsDeBord();        
    }
    
    public static void evaluateEffetsDeBord(String text) {
        System.out.print(KMADEConstant.EVALUATE_ACTION_MESSAGE + " : ");
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        
        boolean historySuccess = ExpressHistory.saveConcreteHistory();
        
        try {
       		myCurrentTask.getEffetsDeBordExpression().getNodeExpression().evaluateNode(null);
       		if (historySuccess) {
                KMADEEditorPrePostIterDialog.getPostonditionPanel().addNewHistory(InterfaceExpressJava.getHistoryMessage());
                KMADEEditorPrePostIterDialog.getPostonditionPanel().enableRemoveAllHistoryControl();
       		} else {
       			if (ExpressHistory.getHistorySize() == 0) {
                    KMADEEditorPrePostIterDialog.getPostonditionPanel().disableHistoryControl();
                    KMADEEditorPrePostIterDialog.getPostonditionPanel().removeAllHistory();
       			} 
       		}
            KMADEEditorPrePostIterDialog.getPostonditionPanel().setConcreteObjectName(ExpressEffetsDeBord.getCurrentObject().toString());
            InterfaceExpressJava.clearHistoryMessage();
            System.out.println(KMADEConstant.EFFETSDEBORD_EVAL_OK_MESSAGE);
        } catch (SemanticErrorException e) {
            System.out.println(KMADEConstant.EFFETSDEBORD_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " , " + e.getMessage());        	
        } catch (SemanticUnknownException e) {
            System.out.println(KMADEConstant.EFFETSDEBORD_EVAL_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.MISSING_USER_VALUE_MESSAGE + " , " + e.getMessage());        	
        } catch (SemanticException e) {
        	
        }
    }
    
    private static void checkEffetsDeBordBuilder(String s) {        
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        myCurrentTask.getEffetsDeBordExpression().setName(s);
        
        // Transformation de la chaîne de caractères en flux de caractères.
        java.io.StringReader sr = new java.io.StringReader(s);
        java.io.Reader r = new java.io.BufferedReader(sr);
        
        EffetsDeBord parser = new MyEffetsDeBord(r);
        try{
            NodeExpression ref = parser.expression();
            if (ref == null) {
                System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.PARSER_PROBLEM_MESSAGE);
                ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,null);
            } else {
                ref.checkNode();
                ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,ref);
                System.out.println(KMADEConstant.EFFETSDEBORD_OK_MESSAGE);
            }
        } catch (SemanticException e) {
            System.out.println(KMADEConstant.EFFETSDEBORD_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,null);
            return;
        } catch (ParseException e) {   
            System.out.println(KMADEConstant.EFFETSDEBORD_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,null);
            return;
        } catch (TokenMgrError e) {
            System.out.println(KMADEConstant.EFFETSDEBORD_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,null);
            return;
        } catch (Error e) {
            // Erreur normalement qui doit venir de AttributExpressExpression ou GroupExpressExpression
            System.out.println(KMADEConstant.EFFETSDEBORD_NO_OK_MESSAGE);
            System.out.println(KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
            ExpressEffetsDeBord.setEffetsDeBord(myCurrentTask,null);
            return;
        }
    }

    public static void initHistory() {
    	KMADEEditorPrePostIterDialog.getPostonditionPanel().disableHistoryControl();
    }
    
    public static void initEffetsDeBord() {
        // Active ou pas l'évaluation
        Tache myCurrentTask = GraphicEditorAdaptator.getSelectedGraphicTask().getTask();
        if (myCurrentTask.getEffetsDeBordExpression().getNodeExpression() != null) {
            // Construire l'expression.
            ArrayList<Object> myList = myCurrentTask.getEffetsDeBordExpression().getNodeExpression().getLinearExpression();
            KMADEEditorPrePostIterDialog.getPostonditionPanel().enabledEvaluateControl(myList);
        } else {            
            KMADEEditorPrePostIterDialog.getPostonditionPanel().disableEvaluateControl(KMADEConstant.EFFETSDEBORD_ERROR_MESSAGE);
        }
    }

	public static void setEffetsDeBord() {
		ExpressEffetsDeBord.setEffetsDeBordDescription(GraphicEditorAdaptator.getSelectedGraphicTask().getTask(), KMADEEditorPrePostIterDialog.getPostonditionPanel().getDescriptionArea());
		TaskPropertiesAdaptator.setEffetsDeBordInModel();
	}
}

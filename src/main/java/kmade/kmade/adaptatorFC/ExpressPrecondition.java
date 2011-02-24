package kmade.kmade.adaptatorFC;

import java.util.Observer;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorFC.parserExpression.MyPrecondition;
import kmade.kmade.adaptatorFC.parserExpression.ParseException;
import kmade.kmade.adaptatorFC.parserExpression.Precondition;
import kmade.kmade.adaptatorFC.parserExpression.TokenMgrError;
import kmade.kmade.view.toolutilities.KMADEObservable;
import kmade.kmade.view.toolutilities.SwingWorker;
import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.SemanticException;
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
    
	public static void setPreconditionDescription(Tache pTache, String p) {
		pTache.getPreExpression().setDescription(p);
	}
	
	public static String getPreconditionDescription(Tache pTache) {
		return pTache.getPreExpression().getDescription();
	}
	
    public static void setPrecondition(Tache pTache, NodeExpression node) {
        pTache.getPreExpression().setNodeExpression(node);
        notifyObservers();
    }
    
    public static NodeExpression getPrecondition(Tache pTache) {
        return pTache.getPreExpression().getNodeExpression();
    }
    
    public static void makeAndCheckPreconditionOpenSPFFile() {
        SwingWorker worker = new SwingWorker() {
            public Object construct() {    
            	Tache[] tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
                System.out.println();
                System.out.println(KMADEConstant.CHECK_ALL_PRECONDITIONS);
                for (int i = 0; i < tacheToBeCreated.length && !ExpressPrecondition.isCanceled(); i++) {
                    String precondtion = tacheToBeCreated[i].getPreExpression().getName();            
                    // Transformation de la cha�ne de caract�res en flux de caract�res.
                    java.io.StringReader sr = new java.io.StringReader(precondtion);
                    java.io.Reader r = new java.io.BufferedReader(sr);
                    Precondition parser = new MyPrecondition(r);
                    try{
                        NodeExpression ref = parser.expression();
                        if (ref == null) {
                            System.out.println(KMADEConstant.PARSER_PROBLEM_MESSAGE);
                            ExpressPrecondition.setPrecondition(tacheToBeCreated[i], null);
                        } else {
                            ref.checkNode();
                            ExpressPrecondition.setPrecondition(tacheToBeCreated[i], ref);
                        }
                    } catch (SemanticException e) {
                        System.out.println(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE + " : " + tacheToBeCreated[i].getName() + ". " + KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SEMANTICAL_ERROR_MESSAGE + " : " + e.getMessage());
                        ExpressPrecondition.setPrecondition(tacheToBeCreated[i], null);
                    } catch (ParseException e) {
                        System.out.println(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE + " : " + tacheToBeCreated[i].getName() + ". " + KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.SYNTAXICAL_ERROR_MESSAGE + " : " + e.getMessage());
                        ExpressPrecondition.setPrecondition(tacheToBeCreated[i], null);
                    } catch (TokenMgrError e) {
                        System.out.println(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE + " : " + tacheToBeCreated[i].getName() + ". " + KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
                        ExpressPrecondition.setPrecondition(tacheToBeCreated[i], null);
                    } catch (Error e) {
                        System.out.println(KMADEConstant.PRECONDITION_OF_TASK_NO_OK_MESSAGE + " : " + tacheToBeCreated[i].getName() + ". " + KMADEConstant.CAUSE_MESSAGE + " : " + KMADEConstant.LEXICAL_ERROR_MESSAGE + " : " + e.getMessage());
                        ExpressPrecondition.setPrecondition(tacheToBeCreated[i], null);
                    }                
                }
                
                if (!ExpressPrecondition.isCanceled()) {
                    System.out.println(KMADEConstant.PRECONDITION_CHECKED_AND_BUILT_MESSAGE);
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
     * @param begining The begining to set.
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
     * @param canceled The canceled to set.
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
     * @param done The done to set.
     */
    public static void setDone(boolean done) {
        ExpressPrecondition.done = done;
    }    
}

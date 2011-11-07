package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas Lachaume
 *
 */
public class PROTOHistoric {



    public static void addTask(Tache t){
	
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().getHistoricPanel().addNode(t);
    }

    public static void addPrecondition(Tache t, boolean respected){

    }

    public static void addFinished(Tache t) {
    }

    public static void descendre() {
	
    }

    public static void remonter() {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().getHistoricPanel().remonter();	
    }

    public static void clearHisto() {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().getHistoricPanel().clearHisto();
	
    }
    
    public static void deleteCurrentTask(){
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().getHistoricPanel().deleteCurrentTask();
    }

}

package fr.upensma.lias.kmade.tool.coreadaptator.parserkmad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * KMADe trunk project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class ExpressKMADTXT {
	
	private static String myText = "";

	public static void saveKMADSimpleModel(String myCurrentFile) {
		refreshString();
		try {
			FileWriter fw = new FileWriter(new File(myCurrentFile));
			fw.write(myText);
			fw.close();
		} catch (IOException e) {
			System.err.println("kmc !!!");
			e.printStackTrace();
		}
		
	
	}
	
	
	public static void displayKMADSimpleModel(){
		refreshString();
		GraphicEditorAdaptator.getMainFrame().getSimpleTreePanel().getTextArea().setText(myText);
		GraphicEditorAdaptator.getMainFrame().getSimpleTreePanel().validate();
		GraphicEditorAdaptator.getMainFrame().getSimpleTreePanel().repaint();

	}
	
	private static void refreshString(){
		myText = "";
		ArrayList<Tache> rootTasks = ExpressTask.getRootTasks();
		for (Tache tache : rootTasks) {
			saveKMADSimpleModelRecu(tache,0);
		}		
	}
	
	private static void saveKMADSimpleModelRecu(Tache t, int lvl){
		for(int i = 0; i<lvl; i++){
			myText += "    ";
		}
		myText += t.getNumero() +" - "+t.getName() + "\n";
		lvl += 1;
		for (Tache fils : t.getFils()) {
			saveKMADSimpleModelRecu(fils,lvl);
		}
		
	}

	

}

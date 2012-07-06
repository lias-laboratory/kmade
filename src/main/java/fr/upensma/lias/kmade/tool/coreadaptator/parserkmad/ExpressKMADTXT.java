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
package fr.upensma.lias.kmade.tool.coreadaptator.parserkmad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Thomas LACHAUME
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

package kmade.kmade.adaptatorUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Properties;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.print.KMADEPrintingUserCardsPanel;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.nmda.schema.tache.Tache;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;

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
public final class PrintAdaptator {
	
	private static String template;
	
	private static int indiceSheet;
	
	public static void openPrintDialog(DefaultGraphModel myModel, GraphLayoutCache lc) {
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().setModelToPrint(myModel, lc);

		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().showTaskModel();
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().setVisible(true);
	}
	
	public static void setSheet(int p)  {
		indiceSheet = p;		
		
		switch(indiceSheet) {
			case 0 : {
				
				break;
			}
			
			case 1 : {
				PrintAdaptator.openUserCards();
				break;
			}
			
			case 2 : {
				break;
			}
		}		
	}
	
	public static void printAction() {
		switch(indiceSheet) {
			case 0 : {
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().setScaleView(1.0);
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().makeTraitement();		
				break;
			}
			
			case 1 : {
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getUserCards().makeTraitement();
				break;
			}
			
			case 2 : {
				break;
			}
		}
	}
	
	public static void setToPortrait() {
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().setToPortrait();
		switch(indiceSheet) {
			case 0 : {
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().refreshGraph();
				break;
			}
			
			case 1 : {
				break;
			}
			
			case 2 : {
				break;
			}
		}
	}
	
	public static void setToLandscape() {
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().setToLandscape();
		switch(indiceSheet) {
			case 0 : {
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().refreshGraph();
				break;
			}
			
			case 1 : {
				break;
			}
			
			case 2 : {
				break;
			}
		}
	}
	
	public static void pageLayoutAction() {
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().setPageLayout();
		switch(indiceSheet) {
			case 0 : {
				GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getTaskModel().refreshGraph();
				break;
			}
			
			case 1 : {
				break;
			}
			
			case 2 : {
				break;
			}
		}
	}
	
	public static void openUserCards() {
		PrintAdaptator.buildTreeUserCard();
		template = loadTemplateUserCards();
	}
	
	public static void noSelectionUserCards() {
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getUserCards().setText("");
	}
	
	private static String getValues(ArrayList p) {
		String temp = "";
		for (int i = 0 ; i < p.size() ; i++) {
			temp += " " + p.get(i).toString();
			if (i < p.size() - 1) {
				temp += ",";
			}
		}
		return temp;
	}
	
	public static void selectionUserCards(Tache r) {
		VelocityContext context = new VelocityContext();
		context.put("NUM", r.getNumero());
		context.put("NAME", r.getName());
		context.put("SUBTASKS", PrintAdaptator.getValues(r.getFils()));
		context.put("DURATION", r.getDuree());
		context.put("PURPOSE", r.getBut());
		context.put("MEDIA", r.getMedia().getFileName());
		context.put("LABEL", r.getLabel() == null ? "" : r.getLabel().toString());
		context.put("DISPLAY", r.getFeedBack());
		context.put("OBSERVATION", r.getObservation());
		context.put("EXECUTANT", r.getExecutant().toString());
		context.put("MODALITY", r.getModalite().toString());
		context.put("FREQUENCY", r.getFrequence().toString());
		context.put("FREQUENCYVALUE", r.getCompFreq());
		context.put("IMPORTANCE", r.getImportance());
		context.put("OPTIONAL", r.getFacultatif());
		context.put("INTERRUPTIBLE", r.getInterruptible());
		context.put("EVENTTRIGGER", r.getEvents());
		context.put("DECOMPOSITION", r.getDecomposition().toString());
		context.put("ACTORS", PrintAdaptator.getValues(r.getActeurs()));
		context.put("PRECONDITION", r.getPreExpression().getName());
		context.put("ITERATION", r.getIteExpression().getName());
		context.put("GENERATEDEVENTS", PrintAdaptator.getValues(r.getEvents()));
		context.put("POSTCONDITION", r.getPostExpression().getName());

		try {
			String toto = processStringUserCards(context, template);
			GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getUserCards().setText(toto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getSelectionUserCardsContent(Tache r) {
		VelocityContext context = new VelocityContext();
		context.put("NUM", r.getNumero());
		context.put("NAME", r.getName());
		context.put("SUBTASKS", PrintAdaptator.getValues(r.getFils()));
		context.put("DURATION", r.getDuree());
		context.put("PURPOSE", r.getBut());
		context.put("MEDIA", r.getMedia().getFileName());
		context.put("LABEL", r.getLabel() == null ? "" : r.getLabel().toString());
		context.put("DISPLAY", r.getFeedBack());
		context.put("OBSERVATION", r.getObservation());
		context.put("EXECUTANT", r.getExecutant().toString());
		context.put("MODALITY", r.getModalite().toString());
		context.put("FREQUENCY", r.getFrequence().toString());
		context.put("FREQUENCYVALUE", r.getCompFreq());
		context.put("IMPORTANCE", r.getImportance());
		context.put("OPTIONAL", r.getFacultatif());
		context.put("INTERRUPTIBLE", r.getInterruptible());
		context.put("EVENTTRIGGER", r.getEvents());
		context.put("DECOMPOSITION", r.getDecomposition().toString());
		context.put("ACTORS", PrintAdaptator.getValues(r.getActeurs()));
		context.put("PRECONDITION", r.getPreExpression().getName());
		context.put("ITERATION", r.getIteExpression().getName());
		context.put("GENERATEDEVENTS", PrintAdaptator.getValues(r.getEvents()));
		context.put("POSTCONDITION", r.getPostExpression().getName());

		try {
			String toto = processStringUserCards(context, template);
			return toto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
    private static String processStringUserCards(VelocityContext context, String htmlText) throws Exception {
        StringWriter writer = new StringWriter();
        Properties properties = new Properties();
        properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        Velocity.init(properties);
        Velocity.evaluate(context,
                writer,
                "LOG", // used for logging
                htmlText);
        return writer.getBuffer().toString();
    }
	
	private static String loadTemplateUserCards() {
        StringBuffer htmlBuffer = new StringBuffer();

        try {
            InputStream inputStream = KMADEPrintingUserCardsPanel.class.getResourceAsStream(KMADEConstant.BASIC_CARD_VELOCITY_HTML);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while (true){
                String line = reader.readLine();
                if (line != null){
                    htmlBuffer.append(line);
                } else {
                    break;
                }
            }
        } catch (IOException iox){
            iox.printStackTrace();
        }
        return htmlBuffer.toString();
	}
	
	private static void buildTreeUserCard() {
		ArrayList<Tache> rootTasks = ExpressTask.getRootTasks();
		GraphicEditorAdaptator.getMainFrame().getPrintingFrame().getUserCards().setTasks(rootTasks);
	}
}

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
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

	private static String getValues(ArrayList<?> p) {
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
		context.put("GENERAL_TITLE", KMADEConstant.GENERAL_INFORMATION_PANEL_TITLE_NAME);
		context.put("NUM_TITLE", KMADEConstant.TASK_NUMBER_NAME_TITLE);
		context.put("NAME_TITLE",KMADEConstant.TASK_NAME_TITLE);
		context.put("SUBTASK_TITLE",KMADEConstant.SUB_TASK_PARENT_NAME_TITLE);
		context.put("DURATION_TITLE",KMADEConstant.TASK_DURATION_TITLE);
		context.put("PURPOSE_TITLE",KMADEConstant.TASK_PURPOSE_TITLE);
		context.put("MEDIA_TITLE",KMADEConstant.TASK_MULTIMEDIA_PURPOSE_TITLE);
		context.put("LABEL_TITLE",KMADEConstant.TASK_LABEL_TITLE);
		context.put("DISPLAY_TITLE",KMADEConstant.TASK_FEEDBACK_TITLE);
		context.put("OBSERVATION_TITLE",KMADEConstant.TASK_OBSERVATION_TITLE);
		context.put("EXECUTANT_TITLE",KMADEConstant.EXECUTING_NAME_TITLE);
		context.put("MODALITY_TITLE",KMADEConstant.MODALITY_NAME_TITLE);
		context.put("FREQUENCY_TITLE",KMADEConstant.FREQUENCY_NAME_TITLE);
		context.put("FREQUENCYVALUE_TITLE",KMADEConstant.FREQUENCY_VALUE_NAME_TITLE);
		context.put("IMPORTANCE_TITLE",KMADEConstant.IMPORTANT_NAME_TITLE);
		context.put("ORDO_TITLE",KMADEConstant.SCHEDULE_PANEL_TITLE_NAME);

		context.put("NECESSITE_TITLE",KMADEConstant.NECESSITE_LABEL_NAME);
		context.put("INTERRUPTIBLE_TITLE",KMADEConstant.INTERRUPTIBLE_LABEL_NAME);
		context.put("DECLENCHEMENT_TITLE",KMADEConstant.DECLENCHEMENT_LABEL_NAME);
		context.put("SCHEDULE_TITLE",KMADEConstant.SCHEDULE_LABEL_NAME);
		context.put("UTILISATEUR_TITLE",KMADEConstant.UTILISATEUR_LABEL_NAME);
		context.put("PRECONDITION_TITLE",KMADEConstant.PRECONDITION_LABEL_VALUE);
		context.put("ITERATION_TITLE",KMADEConstant.ITERATION_LABEL_VALUE);
		context.put("POSTCONDITION__TITLE",KMADEConstant.POSTCONDITION_PANEL_TITLE_NAME);
		context.put("EVENT_TITLE",KMADEConstant.EVENT_NAME_TITLE);
		context.put("EFFETSDEBORD_TITLE",KMADEConstant.EFFETSDEBORD_LABEL_VALUE);
		context.put("SYSTEM_USER_TITLE",KMADEConstant.MATERIEL_LABEL_NAME);


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
		if(r.getFacultatif()){
			context.put("OPTIONAL",KMADEConstant.OPTIONAL_NECESSITE_VALUE);
		}else{
			context.put("OPTIONAL",KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE);
		}
		if(r.getInterruptible()){
			context.put("INTERRUPTIBLE", KMADEConstant.INTERRUPTIBLE_VALUE);
		}else{
			context.put("INTERRUPTIBLE", KMADEConstant.NO_INTERRUPTIBLE_VALUE);
		}
		context.put("EVENTTRIGGER", r.getEvents());
		context.put("DECOMPOSITION", r.getDecomposition().toString());
		context.put("ACTORS", PrintAdaptator.getValues(r.getActeurs()));
		context.put("ACTORSYSTEM",PrintAdaptator.getValues(r.getActeurSysteme()));
		context.put("PRECONDITION", r.getPreExpression().getName());
		context.put("ITERATION", r.getIteExpression().getName());
		context.put("GENERATEDEVENTS", PrintAdaptator.getValues(r.getEvents()));
		context.put("EFFETSDEBORD", r.getEffetsDeBordExpression().getName());

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
		context.put("EFFETSDEBORD", r.getEffetsDeBordExpression().getName());

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

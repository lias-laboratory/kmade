package fr.upensma.lias.kmade.tool.viewadaptator;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.preferences.KMADEPreferencesTaskTreePanel;


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
 * @author Micka�l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public final class PreferencesAdaptator {
	
	private static Properties prop;


	/**
	 * M�thode qui initialise les composants de l'outil pr�f�rences
	 */
	public static void initComponent() {
		prop = new Properties();
		PreferencesAdaptator.load();
		PreferencesAdaptator.applyTaskParameters();
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().setDisabledApplyButton();
	}

	/**
	 * M�thode qui enregistre des param�tres dans le fichier config.ini
	 */
	private static void save() {
		try {
			FileOutputStream out = new FileOutputStream("config.ini");
			prop.store(out, "KMADe Parameters");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�thode qui charge des param�tres � partir du fichier config.ini dans le
	 * logiciel
	 */
	private static void load() {
		// une partie de cette fonction est en dur dans KMADeMain
		try {
			FileInputStream in = new FileInputStream("config.ini");
			prop.load(in);
			in.close();
		} catch (IOException e) {
		}

		if (prop.isEmpty()) {
			PreferencesAdaptator.setDefaultProperties();
		}
		PreferencesAdaptator.setParametersToPreferenceUI();
	}

	/**
	 * M�thode qui cr�e un property et le config.ini avec des valeurs par d�faut
	 */
	private static void setDefaultProperties() {
		// Tasks Tree Parameters
		prop.setProperty(KMADEConstant.LEVEL_DISTANCE_NAME, KMADEConstant.LEVEL_DISTANCE+"");
		prop.setProperty(KMADEConstant.TASK_MODEL_ORIENTATION_NAME, "3");
		prop.setProperty(KMADEConstant.TASK_DISTANCE_NAME, KMADEConstant.TASK_DISTANCE+"");
		prop.setProperty(KMADEConstant.TASK_SELECTION_COLOR_NAME, "Green");
		prop.setProperty(KMADEConstant.ORTHOGONAL_EDGE_NAME, "true");
		prop.setProperty(KMADEConstant.WHEEL_ZOOM_NAME, "1");
		prop.setProperty(KMADEConstant.TASK_FONT_NAME, "Arial");
		prop.setProperty(KMADEConstant.TASK_FONT_SIZE_NAME, "15");
		prop.setProperty(KMADEConstant.TASK_FONT_COLOR_NAME, "Black");
		prop.setProperty(KMADEConstant.TASK_FONT_STYLE_NAME, "Bold");
		prop.setProperty(KMADEConstant.TASK_BACKGROUND_COLOR_NAME, "NoColor");
		prop.setProperty(KMADEConstant.TASK_SKIN_STYLE_NAME, "Item 4");
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_NAME, "Arial");
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_STYLE_NAME, "Bold");
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_SIZE_NAME, "10");
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_COLOR_NAME, "Black");
		prop.setProperty(KMADEConstant.TRIGGER_EVENT_NAME, "false");
		prop.setProperty(KMADEConstant.ACTOR_TASK_NAME, "true");
		prop.setProperty(KMADEConstant.AUTOMATIC_SAVE_NAME, "true");
		prop.setProperty(KMADEConstant.TIME_SAVE_PERIOD_NAME, "1");
		prop.setProperty(KMADEConstant.SPLASH_SCREEN_NAME, "true");
		prop.setProperty(KMADEConstant.BUG_MESSAGE_NAME, "false");
		prop.setProperty(KMADEConstant.LANGUAGE_NAME, "French");
		save();
	}

	/**
	 * M�thode qui r�cup�re les param�tres (nom et valeur) du composant modifi�
	 */
	private static void getParametersFromPreferenceUI() {
		// Tasks Tree Parameters 
		KMADEPreferencesTaskTreePanel treePanel = GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTree();
		prop.setProperty(KMADEConstant.LEVEL_DISTANCE_NAME,(String) (treePanel.getHauteurArbre().getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_MODEL_ORIENTATION_NAME,	(String) (treePanel.getOrientationArbre().getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_DISTANCE_NAME,(String) (treePanel.getDistanceNodale().getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_SELECTION_COLOR_NAME, translateColorUIToConfig(treePanel.getCouleurSelectionTache().getSelectedItem()));		
		prop.setProperty(KMADEConstant.ORTHOGONAL_EDGE_NAME, Boolean.toString(treePanel.getOrthogonaliteArbre().isSelected()));

		/* Param�tres du panel Tache */
		prop.setProperty(KMADEConstant.TASK_FONT_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getFontTask()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_FONT_SIZE_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getFontSizeTask()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_FONT_COLOR_NAME,
				translateColorUIToConfig(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getColorTask()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_FONT_STYLE_NAME,
				translateFontStyleUIToConfig(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getFontStyleTask()
						.getSelectedItem()));
		

		prop.setProperty(KMADEConstant.TASK_BACKGROUND_COLOR_NAME,
				translateColorUIToConfig(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask()
						.getBackgroundColorTask().getSelectedItem()));

		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getFontOperatorTask()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_SIZE_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask()
						.getFontSizeOperatorTask().getSelectedItem()));
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_STYLE_NAME,
				translateFontStyleUIToConfig(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask()
						.getFontStyleOperatorTask().getSelectedItem()));
		prop.setProperty(KMADEConstant.DECOMPOSITION_FONT_COLOR_NAME,
				translateColorUIToConfig(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask()
						.getColorOperatorTask().getSelectedItem()));
		prop.setProperty(KMADEConstant.TASK_SKIN_STYLE_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getModelTask()
						.getSelectedItem()));
		

		prop.setProperty(KMADEConstant.TRIGGER_EVENT_NAME,
				Boolean.toString(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getEventTask()
						.isSelected()));
		prop.setProperty(KMADEConstant.ACTOR_TASK_NAME,
				Boolean.toString(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getTask().getActorTask()
						.isSelected()));

		/* Param�tres du panel g�n�ral */
		prop.setProperty(KMADEConstant.AUTOMATIC_SAVE_NAME,
				Boolean.toString(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getAutomaticSave()
						.isSelected()));
		prop.setProperty(KMADEConstant.TIME_SAVE_PERIOD_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getTimeSave()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.SPLASH_SCREEN_NAME,
				Boolean.toString(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getSplashScreen()
						.isSelected()));
		prop.setProperty(KMADEConstant.BUG_MESSAGE_NAME,
				Boolean.toString(GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getEnvoiBug()
						.isSelected()));
		prop.setProperty(KMADEConstant.LANGUAGE_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getLanguage()
						.getSelectedItem()));
		prop.setProperty(KMADEConstant.WHEEL_ZOOM_NAME,
				(String) (GraphicEditorAdaptator.getMainFrame()
						.getPreferencesDialog().getGeneral().getWheelZoom()
						.getSelectedItem()));

	}

	/**
	 * M�thode qui initialise les composants selon les param�tres du fichier config.ini 
	 */
	private static void setParametersToPreferenceUI() {
		/* Param�tres de la partie Arbre */
		KMADEPreferencesTaskTreePanel treePanel = GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTree();
		treePanel.setHauteurArbre(prop.getProperty(KMADEConstant.LEVEL_DISTANCE_NAME));
		treePanel.setOrientationArbre(prop.getProperty(KMADEConstant.TASK_MODEL_ORIENTATION_NAME));
		treePanel.setDistanceNodale(prop.getProperty(KMADEConstant.TASK_DISTANCE_NAME));
		treePanel.setCouleurSelectionTache(translateColorConfigToUI(prop.getProperty(KMADEConstant.TASK_SELECTION_COLOR_NAME)));
		treePanel.setOrthogonaliteArbre(prop.getProperty(KMADEConstant.ORTHOGONAL_EDGE_NAME));

		/* Param�tres de la partie Tache */
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTask()
				.setFontTask(prop.getProperty(KMADEConstant.TASK_FONT_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTask()
				.setFontSizeTask(
						prop.getProperty(KMADEConstant.TASK_FONT_SIZE_NAME));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setColorTask(
						translateColorConfigToUI(prop
								.getProperty(KMADEConstant.TASK_FONT_COLOR_NAME)));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setFontStyleTask(
						translateFontStyleConfigToUI(prop
								.getProperty(KMADEConstant.TASK_FONT_STYLE_NAME)));
		
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setBackgroundColorTask(
						translateColorConfigToUI(prop
								.getProperty(KMADEConstant.TASK_BACKGROUND_COLOR_NAME)));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setColorOperatorTask(
						translateColorConfigToUI(prop
								.getProperty(KMADEConstant.DECOMPOSITION_FONT_COLOR_NAME)));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTask()
				.setModelTask(
						prop.getProperty(KMADEConstant.TASK_SKIN_STYLE_NAME));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setFontOperatorTask(
						prop.getProperty(KMADEConstant.DECOMPOSITION_FONT_NAME));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setFontStyleOperatorTask(
						translateFontStyleConfigToUI(prop
								.getProperty(KMADEConstant.DECOMPOSITION_FONT_STYLE_NAME)));
		GraphicEditorAdaptator
				.getMainFrame()
				.getPreferencesDialog()
				.getTask()
				.setFontSizeOperatorTask(
						prop
								.getProperty(KMADEConstant.DECOMPOSITION_FONT_SIZE_NAME));
		
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTask()
				.setEventTask(
						prop.getProperty(KMADEConstant.TRIGGER_EVENT_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().getTask()
				.setActorTask(prop.getProperty(KMADEConstant.ACTOR_TASK_NAME));

		/* Param�tres de la partie G�n�ral */
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
				.getGeneral().setAutomaticSave(
						prop.getProperty(KMADEConstant.AUTOMATIC_SAVE_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
				.getGeneral().setTimeSave(
						prop.getProperty(KMADEConstant.TIME_SAVE_PERIOD_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
				.getGeneral().setSplashScreen(
						prop.getProperty(KMADEConstant.SPLASH_SCREEN_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
				.getGeneral().setEnvoiBug(
						prop.getProperty(KMADEConstant.BUG_MESSAGE_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
				.getGeneral().setLanguage(
						prop.getProperty(KMADEConstant.LANGUAGE_NAME));
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog()
		.getGeneral().setWheelZoom(
				prop.getProperty(KMADEConstant.WHEEL_ZOOM_NAME));
	}

	/**
	 * Action du bouton ok
	 */
	public static void savePreferencesOkAction() {
		savePreferences();
		applyTaskParameters();
		closePreferencesDialog();		
	}

	/**
	 * Action du bouton Apply
	 */
	public static void savePreferencesApplyAction() {
		savePreferences();
		applyTaskParameters();
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().setDisabledApplyButton();
	}

	/**
	 * Sauvegarde les pr�f�rences
	 */
	public static void savePreferences() {
		getParametersFromPreferenceUI();
		save();
	}

	/**
	 * Action du bouton annuler de l'outil pr�f�rences
	 */
	public static void cancelPreferences() {
		load();
		closePreferencesDialog();
	}

	/**
	 * Methode qui active le bouton apply
	 */
	public static void enableApplyButton() {
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().setEnabledApplyButton();
	}

	/**
	 * Ferme la fen�tre de dialogue de l'outil pr�f�rences
	 */
	public static void closePreferencesDialog() {
		GraphicEditorAdaptator.getMainFrame().getPreferencesDialog().setVisible(false);
	}

	/* //////////////////////////////////////////////////// */
	/* Proc�dures concernants les param�tres g�n�raux /// */
	/* //////////////////////////////////////////////////// */

	/**
	 * M�thode qui force le garbage collector
	 * 
	 */
	public static void forceGarbage() {
		System.gc();
	}

	
	/**
	 * M�thode renvoyant le choix de l'utilisateur pour l'affichage du
	 * splashscreen
	 * 
	 * @return boolean
	 */
	public static boolean splashScreen() {
		if (prop.getProperty(KMADEConstant.SPLASH_SCREEN_NAME).equals("true")) {
			return true;
		}
		return false;
	}

	//
	// Task tree parameters
	//
	
	/**
	 * Modifie la couleur de s�lection d'une t�che
	 */
	private static void setSelectedTaskColor() {
		Color selectedColor = getColorFromString(prop.getProperty(KMADEConstant.TASK_SELECTION_COLOR_NAME));
		KMADEConstant.TASK_SELECTION_COLOR = selectedColor;
	}
	
	/**
	 * Set the horizontal task distance
	 */
	private static void setNodeDistance() {
		try {
			KMADEConstant.TASK_DISTANCE = Integer.parseInt(prop.getProperty(KMADEConstant.TASK_DISTANCE_NAME));
		} catch (NumberFormatException e) {			
		}
	}
	
	/**
	 * Set the vertical task distance
	 */
	private static void setLevelDistance() {
		try {
			KMADEConstant.LEVEL_DISTANCE = Integer.parseInt(prop.getProperty(KMADEConstant.LEVEL_DISTANCE_NAME));
		} catch (NumberFormatException e) {			
		}
	}

	private static void setOrthogonalEdgesOption() {
		KMADEConstant.ORTHOGONAL_EDGES = Boolean.parseBoolean(prop.getProperty(KMADEConstant.ORTHOGONAL_EDGE_NAME));
	}
	
	private static void setWheelZoom() {
		try {
			KMADEConstant.WHEEL_ZOOM = Double.parseDouble(prop.getProperty(KMADEConstant.WHEEL_ZOOM_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * M�thode qui applique les changements de param�tres de police, taille...
	 */
	public static void applyTaskParameters () {
		System.out.println();
		PreferencesAdaptator.setSelectedTaskColor();
		PreferencesAdaptator.setNodeDistance();
		PreferencesAdaptator.setLevelDistance();
		PreferencesAdaptator.setOrthogonalEdgesOption();
		PreferencesAdaptator.setWheelZoom();
		setTaskNameFont();
		setTaskOperatorFont();
		
		// Update Task Model
		GraphicEditorAdaptator.refreshViews();
	}
	
	/**
	 * M�thode qui modifie la police et la couleur du nom de la tache
	 */
	private static void setTaskNameFont() {
		String font = prop.getProperty(KMADEConstant.TASK_FONT_NAME);
		int size = java.lang.Integer.parseInt((prop
				.getProperty(KMADEConstant.TASK_FONT_SIZE_NAME)));
		int style = getStyleFromString(prop
				.getProperty(KMADEConstant.TASK_FONT_STYLE_NAME));
		Color fontColor = getColorFromString(prop
				.getProperty(KMADEConstant.TASK_FONT_COLOR_NAME));
		KMADEConstant.setTaskNameFont(font, style, size, fontColor);
	}

	/**
	 * M�thode qui modifie la police et la couleur de l'operateur de la tache
	 */
	private static void setTaskOperatorFont() {
		String font = prop.getProperty(KMADEConstant.DECOMPOSITION_FONT_NAME);
		int size = java.lang.Integer.parseInt((prop
				.getProperty(KMADEConstant.DECOMPOSITION_FONT_SIZE_NAME)));
		int style = getStyleFromString(prop
				.getProperty(KMADEConstant.DECOMPOSITION_FONT_STYLE_NAME));
		Color fontColor = getColorFromString(prop
				.getProperty(KMADEConstant.DECOMPOSITION_FONT_COLOR_NAME));
		KMADEConstant.setTaskOperatorFont(font, style, size, fontColor);
	}
	

	/**
	 * M�thode qui convertit le string du style en type integer
	 */
	public static int getStyleFromString(String fontStyle) {
		if (fontStyle.equals(KMADEConstant.STYLE_PLAIN))
			return 0;
		if (fontStyle.equals(KMADEConstant.STYLE_BOLD))
			return 1;
		if (fontStyle.equals(KMADEConstant.STYLE_ITALIC))
			return 2;
		else
			return 3;
	}

	/**
	 * M�thode qui convertit le string du style en type integer
	 */
	public static String translateFontStyleUIToConfig(Object isFontStyle) {
		String fontStyle = (String) (isFontStyle);
		if (fontStyle.equals(KMADEConstant.STYLE_PLAIN))
			return "Plain";
		if (fontStyle.equals(KMADEConstant.STYLE_BOLD))
			return "Bold";
		if (fontStyle.equals(KMADEConstant.STYLE_ITALIC))
			return "Italic";
		else
			return "Bold+Italic";
	}

	/**
	 * Methode qui permet la traduction de la valeur du fichier pour le style de
	 * la police
	 * 
	 * @return String
	 */
	public static String translateFontStyleConfigToUI(String fontStyle) {
		if (fontStyle.equals("Bold"))
			return KMADEConstant.STYLE_BOLD;
		if (fontStyle.equals("Italic"))
			return KMADEConstant.STYLE_ITALIC;
		if (fontStyle.equals("Plain"))
			return KMADEConstant.STYLE_PLAIN;
		else
			return KMADEConstant.STYLE_BOLD_ITALIC;
	}

	/**
	 * M�thode qui convertie le string de la couleur en type Color
	 * @return Color
	 */
	private static Color getColorFromString(String fontColor) {
		if (fontColor.equals("Blue"))
			return Color.blue;
		if (fontColor.equals("Green"))
			return Color.green;
		if (fontColor.equals("Red"))
			return Color.red;
		if (fontColor.equals("NoColor"))
			return Color.white;
		if (fontColor.equals("Yellow"))
			return Color.yellow;
		if (fontColor.equals("Black"))
			return Color.black;
		else
			return null;
	}

	/**
	 * Translate color value from config file to UI
	 * @return String
	 */
	public static String translateColorConfigToUI(String color) {
		if (color.equals("Blue"))
			return KMADEConstant.COLOR_BLUE;
		if (color.equals("Green"))
			return KMADEConstant.COLOR_GREEN;
		if (color.equals("Red"))
			return KMADEConstant.COLOR_RED;
		if (color.equals("NoColor"))
			return KMADEConstant.COLOR_NO_COLOR;
		if (color.equals("Yellow"))
			return KMADEConstant.COLOR_YELLOW;
		if (color.equals("Black"))
			return KMADEConstant.COLOR_BLACK;
		else
			return KMADEConstant.COLOR_GREEN;
	}

	/**
	 * Translate color value from UI to config file
	 * @return String
	 */
	private static String translateColorUIToConfig(Object isColor) {
		String color = (String) (isColor);
		if (color.equals(KMADEConstant.COLOR_BLUE))
			return "Blue";
		if (color.equals(KMADEConstant.COLOR_GREEN))
			return "Green";
		if (color.equals(KMADEConstant.COLOR_RED))
			return "Red";
		if (color.equals(KMADEConstant.COLOR_NO_COLOR))
			return "NoColor";
		if (color.equals(KMADEConstant.COLOR_YELLOW))
			return "Yellow";
		if (color.equals(KMADEConstant.COLOR_BLACK))
			return "Black";
		else
			return "Green";
	}


}


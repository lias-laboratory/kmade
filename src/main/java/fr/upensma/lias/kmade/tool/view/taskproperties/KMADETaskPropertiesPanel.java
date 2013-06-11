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
package fr.upensma.lias.kmade.tool.view.taskproperties;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.upensma.lias.kmade.kmad.schema.tache.Actor;
import fr.upensma.lias.kmade.kmad.schema.tache.ActorSystem;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.SideEffectExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Frequence;
import fr.upensma.lias.kmade.kmad.schema.tache.Importance;
import fr.upensma.lias.kmade.kmad.schema.tache.IterExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Modality;
import fr.upensma.lias.kmade.kmad.schema.tache.PreExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultPropertiesTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.view.toolutilities.PropertiesObject;
import fr.upensma.lias.kmade.tool.viewadaptator.EventAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesAdaptator;

/**
 * @author Mickael BARON
 */
public final class KMADETaskPropertiesPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 55465L;

    private final CardLayout card = new CardLayout();

    private final JPanel cache;

    private final JPanel switchEmptyProp = new JPanel();

    private static TaskPropertiesModel myTaskPropertiesModel;

    private final static KMADEEditorTextDialog myEditorText = new KMADEEditorTextDialog();

    private final static KMADEEditorEventDialog myEditorEvent = new KMADEEditorEventDialog();

    private final static KMADEEditorEventDecl myEditorDecl = new KMADEEditorEventDecl();

    private final static KMADEEditorActorDialog myEditorActor = new KMADEEditorActorDialog();

    private final static KMADEEditorActorSystemDialog myEditorActorSystem = new KMADEEditorActorSystemDialog();

    private KMADEEditorPrePostIterDialog myEditorPrePostIter = new KMADEEditorPrePostIterDialog();

    private KMADEEditorMultimediaDialog myEditorMultimedia = new KMADEEditorMultimediaDialog();

    private JEditorPane myTextArea;

    private JPropertiesTable myTaskPropertiesView;

    private JLabel label_text;

    public TaskPropertiesModel getPropertiesModel() {
	return myTaskPropertiesModel;
    }

    private TitledBorder myTitledBorder;

    // private KMADEEditorTextDialog myEditorDecl;

    private static boolean isInit = true;
    /******************************************************
     * Modifications du menu "Caract�ristiques" * 30/01/08 Antoine DAVID *
     * ****************************************************/
    public static final int GENERAL_TITLE_ELEMENT = 0;
    public static final int TASK_NUMBER_NAME_ELEMENT = 1;
    public static final int PARENT_NAME_ELEMENT = 2;
    public static final int TASK_NAME_TITLE_ELEMENT = 3;
    public static final int TASK_OBSERVATION_TITLE_ELEMENT = 4;
     public static final int TASK_DURATION_TITLE_ELEMENT = 5; 
     public static final int TASK_PURPOSE_TITLE_ELEMENT = 6; 
    /* public static final int TASK_VIDEO_PURPOSE_TITLE_ELEMENT = 6; */
    public static final int TASK_LABEL_TITLE_ELEMENT = 7;
    public static final int TASK_OBJECTS_TITLE_ELEMENT = 8;
    /* public static final int TASK_FEEDBACK_TITLE_ELEMENT = 9; */

    public static final int EXECUTING_NAME_TITLE_ELEMENT = 9;
    public static final int MODALITY_NAME_TITLE_ELEMENT = 10;
    public static final int FREQUENCY_NAME_TITLE_ELEMENT = 11;
    public static final int FREQUENCY_VALUE_NAME_TITLE_ELEMENT = 12;
    public static final int IMPORTANT_NAME_TITLE_ELEMENT = 13;

    public static final int SCHEDULING_TITLE_ELEMENT = 14;
    public static final int NECESSITE_TITLE_ELEMENT = 15;
    public static final int INTERRUPTIBLE_TITLE_ELEMENT = 16;
    public static final int DECLENCHEMENT_TITLE_ELEMENT = 17;
    public static final int SCHEDULE_TITLE_ELEMENT = 18;
    public static final int USER_TITLE_ELEMENT = 19;
    public static final int SYSTEM_USER_TITLE_ELEMENT = 20;
    public static final int PRECONDITION_TITLE_ELEMENT = 21;
    public static final int ITERATION_TITLE_ELEMENT = 22;

    public static final int POSTCONDITION_TITLE_ELEMENT = 23;
    public static final int EVENT_TITLE_ELEMENT = 24;
    public static final int EFFETSDEBORD_TITLE_ELEMENT = 25;

    public KMADETaskPropertiesPanel() {

	PropertiesObject[] taskProperties = new PropertiesObject[26];
	taskProperties[GENERAL_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.GENERAL_INFORMATION_PANEL_TITLE_NAME,
		KMADEConstant.GENERAL_INFORMATION_PANEL_LEGEND_TITLE_NAME, "",
		JPropertiesTable.NONE_TEXT, false);
	taskProperties[TASK_NUMBER_NAME_ELEMENT] = new PropertiesObject(
		KMADEConstant.TASK_NUMBER_NAME_TITLE,
		KMADEConstant.TASK_NUMBER_NAME_LEGEND_TITLE, "",
		JPropertiesTable.SHORT_TEXT, false);
	taskProperties[PARENT_NAME_ELEMENT] = new PropertiesObject(
		KMADEConstant.SUB_TASK_PARENT_NAME_TITLE,
		KMADEConstant.SUB_TASK_PARENT_NAME_LEGEND_TITLE, "",
		JPropertiesTable.SHORT_TEXT, false);
	taskProperties[TASK_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.TASK_NAME_TITLE,
		KMADEConstant.TASK_NAME_LEGEND_TITLE, "",
		JPropertiesTable.SHORT_TEXT, true);
	
	 taskProperties[TASK_DURATION_TITLE_ELEMENT] = new
	  PropertiesObject(KMADEConstant
	  .TASK_DURATION_TITLE,KMADEConstant.TASK_DURATION_LEGEND_TITLE, "",
	 JPropertiesTable.SHORT_TEXT, true);
	 
	
	  taskProperties[TASK_PURPOSE_TITLE_ELEMENT] = new
	  PropertiesObject(KMADEConstant.TASK_PURPOSE_TITLE,
	  KMADEConstant.TASK_PURPOSE_LEGEND_TITLE,"",
	 JPropertiesTable.LONG_TEXT, true, null, myEditorText);
	
	/*
	 * taskProperties[TASK_VIDEO_PURPOSE_TITLE_ELEMENT] = new
	 * PropertiesObject(KMADEConstant.TASK_MULTIMEDIA_PURPOSE_TITLE,
	 * KMADEConstant.TASK_MULTIMEDIA_PURPOSE_LEGEND_TITLE,"",
	 * JPropertiesTable.LONG_TEXT, true, null, myEditorMultimedia);
	 */
	String[] labelEmpty = { "" };
	taskProperties[TASK_LABEL_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.TASK_LABEL_TITLE,
		KMADEConstant.TASK_LABEL_LEGEND_TITLE, "",
		JPropertiesTable.COMBO_TEXT, true, labelEmpty);
	taskProperties[TASK_OBJECTS_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.TASK_OBJECTS_TITLE,
		KMADEConstant.TASK_OBJECTS_LEGEND_TITLE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorText);
	/*
	 * taskProperties[TASK_FEEDBACK_TITLE_ELEMENT] = new
	 * PropertiesObject(KMADEConstant.TASK_FEEDBACK_TITLE,
	 * KMADEConstant.TASK_FEEDBACK_LEGEND_TITLE,"",
	 * JPropertiesTable.LONG_TEXT,true, null, myEditorText);
	 */
	taskProperties[TASK_OBSERVATION_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.TASK_OBSERVATION_TITLE,
		KMADEConstant.TASK_OBSERVATION_LEGEND_TITLE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorText);
	taskProperties[EXECUTING_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.EXECUTING_NAME_TITLE,
		KMADEConstant.EXECUTING_NAME_LEGEND_TITLE, "",
		JPropertiesTable.COMBO_TEXT, true,
		Executor.getNameLocaleExecutant());
	taskProperties[MODALITY_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.MODALITY_NAME_TITLE,
		KMADEConstant.MODALITY_NAME_LEGEND_TITLE, "",
		JPropertiesTable.COMBO_TEXT, true,
		Modality.getNameLocalModality());
	taskProperties[FREQUENCY_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.FREQUENCY_NAME_TITLE,
		KMADEConstant.FREQUENCY_NAME_LEGEND_TITLE, "",
		JPropertiesTable.COMBO_TEXT, true,
		Frequence.getNameLocaleFrequence());
	taskProperties[FREQUENCY_VALUE_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.FREQUENCY_VALUE_NAME_TITLE,
		KMADEConstant.FREQUENCY_VALUE_NAME_LEGEND_TITLE, "",
		JPropertiesTable.SHORT_TEXT, false);
	taskProperties[IMPORTANT_NAME_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.IMPORTANT_NAME_TITLE,
		KMADEConstant.IMPORTANT_NAME_LEGEND_TITLE, "",
		JPropertiesTable.COMBO_TEXT, true,
		Importance.getNameLocaleImportance());

	taskProperties[SCHEDULING_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.SCHEDULE_PANEL_TITLE_NAME,
		KMADEConstant.SCHEDULE_PANEL_TITLE_NAME, "",
		JPropertiesTable.NONE_TEXT, false);
	String[] comboFac = { KMADEConstant.OPTIONAL_NECESSITE_VALUE,
		KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE };
	taskProperties[NECESSITE_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.NECESSITE_LABEL_NAME,
		KMADEConstant.NECESSITE_LABEL_LEGEND_NAME, "",
		JPropertiesTable.COMBO_TEXT, true, comboFac);
	String[] comboInt = { KMADEConstant.INTERRUPTIBLE_VALUE,
		KMADEConstant.NO_INTERRUPTIBLE_VALUE };
	taskProperties[INTERRUPTIBLE_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.INTERRUPTIBLE_LABEL_NAME,
		KMADEConstant.INTERRUPTIBLE_LABEL_LEGEND_NAME, "",
		JPropertiesTable.COMBO_TEXT, true, comboInt);
	String[] comboEmpty = { "" };
	taskProperties[DECLENCHEMENT_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.DECLENCHEMENT_LABEL_NAME,
		KMADEConstant.DECLENCHEMENT_LABEL_LEGEND_NAME, "",
		JPropertiesTable.COMBO_TEXT, true, comboEmpty);
	taskProperties[SCHEDULE_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.SCHEDULE_LABEL_NAME,
		KMADEConstant.SCHEDULE_LABEL_LEGEND_NAME, "",
		JPropertiesTable.COMBO_TEXT, true,
		Decomposition.getNameLocaleDecomposition());
	taskProperties[USER_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.UTILISATEUR_LABEL_NAME,
		KMADEConstant.UTILISATEUR_LABEL_LEGEND_NAME, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorActor);
	taskProperties[PRECONDITION_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.PRECONDITION_LABEL_VALUE,
		KMADEConstant.PRECONDITION_LABEL_LEGEND_VALUE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorPrePostIter);
	taskProperties[ITERATION_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.ITERATION_LABEL_VALUE,
		KMADEConstant.ITERATION_LABEL_LEGEND_VALUE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorPrePostIter);

	taskProperties[POSTCONDITION_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.POSTCONDITION_PANEL_TITLE_NAME,
		KMADEConstant.POSTCONDITION_PANEL_TITLE_NAME, "",
		JPropertiesTable.NONE_TEXT, false);
	taskProperties[EVENT_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.EVENT_NAME_TITLE,
		KMADEConstant.EVENT_NAME_LEGEND_TITLE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorEvent);
	taskProperties[EFFETSDEBORD_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.EFFETSDEBORD_LABEL_VALUE,
		KMADEConstant.EFFETSDEBORD_LABEL_LEGEND_VALUE, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorPrePostIter);

	taskProperties[SYSTEM_USER_TITLE_ELEMENT] = new PropertiesObject(
		KMADEConstant.MATERIEL_LABEL_NAME,
		KMADEConstant.MATERIEL_LABEL_LEGEND, "",
		JPropertiesTable.LONG_TEXT, true, null, myEditorActorSystem);

	myTaskPropertiesModel = new TaskPropertiesModel(taskProperties);
	myTaskPropertiesView = new JPropertiesTable(myTaskPropertiesModel, true);

	JSplitPane mySplitPane = new JSplitPane();
	mySplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

	mySplitPane.setTopComponent(myTaskPropertiesView);

	myTextArea = new JEditorPane();
	myTextArea.setContentType("text/html");
	myTextArea.setEditable(false);

	mySplitPane.setBottomComponent(new JScrollPane(myTextArea));
	mySplitPane.setDividerLocation(450);
	mySplitPane.setResizeWeight(0.8);

	cache = new JPanel(new GridLayout(3, 1));
	label_text = new JLabel(KMADEConstant.NO_SELECTED_TASK_MESSAGE);
	label_text.setHorizontalAlignment(JLabel.CENTER);
	cache.add(new JPanel());
	cache.add(label_text);
	cache.add(new JPanel());

	switchEmptyProp.setLayout(card);
	switchEmptyProp.add(mySplitPane, "Prop");
	switchEmptyProp.add(cache, "Empty");

	this.setLayout(new BorderLayout());
	this.add(switchEmptyProp);
	this.setVisible(true);
	myTitledBorder = new TitledBorder(null,
		KMADEConstant.PROPERTIES_MESSAGE, TitledBorder.CENTER,
		TitledBorder.TOP);
	this.setBorder(myTitledBorder);

	myTaskPropertiesView.addSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {
		displayOrNotUserHelp();
	    }
	});

	this.myTaskPropertiesView.addFocusListener(new FocusListener() {
	    public void focusGained(FocusEvent e) {
		KMADEVertexView.editor.stopCellEditing();
	    }

	    public void focusLost(FocusEvent e) {

	    }

	});
    }

    private void displayOrNotUserHelp() {
	// ajout d'un try/catch pour le cas de l'enregistrement d'une valeur
	// null
	try {
	    ListSelectionModel lsm = myTaskPropertiesView.getSelectionModel();
	    if (!lsm.isSelectionEmpty()) {
		int selected = lsm.getMinSelectionIndex();
		setInfoIntoTextArea(
			myTaskPropertiesModel.getValueAt(selected, 0)
				.toString(),
			myTaskPropertiesModel.getValueAt(selected, 6)
				.toString(),
			myTaskPropertiesModel.getValueAt(selected, 1)
				.toString(),
			((Integer) myTaskPropertiesModel
				.getValueAt(selected, 2)) == JPropertiesTable.NONE_TEXT ? false
				: true);
	    } else {
		setNoInfoIntoTextArea();
	    }
	} catch (Exception e) {
	    setNoInfoIntoTextArea();
	}
    }

    private void setInfoIntoTextArea(String titre, String legend,
	    String contenu, boolean isValueVisible) {
	if (isValueVisible) {
	    if (contenu.equals("")) {
		contenu = "<em>" + KMADEConstant.NO_VALUE_LEGEND_MESSAGE
			+ "</em>";
	    }
	    myTextArea.setText("<Strong>" + titre + "</strong>" + "<br><em>"
		    + legend + "</em><br><br>" + "<strong>Valeur : </strong>"
		    + contenu);
	} else {
	    myTextArea.setText("<Strong>" + titre + "</strong>" + "<br><em>"
		    + legend + "</em>");
	}
    }

    public void setNoInfoIntoTextArea() {
	myTextArea
		.setText("<Strong>"
			+ KMADEConstant.NO_CARACTERISTIC_SELECTED_MESSAGE
			+ "</strong>");
    }

    public void updateTaskPropertiesPanel(String[] nameValue,
	    ArrayList<String> eventListFromTask, ArrayList<Actor> refActeurs,
	    ArrayList<ActorSystem> refActeurSysteme, String[] labelNames) {
	// Les Utilisateurs.
	myTaskPropertiesModel.setValueAt(refActeurs, USER_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_VALUE_INDEX);
	myTaskPropertiesModel.setValueAt(refActeurSysteme,
		SYSTEM_USER_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_VALUE_INDEX);
	// Les événements.
	myTaskPropertiesModel.setValueAt(nameValue,
		DECLENCHEMENT_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_COMBOVALUE_INDEX);
	myTaskPropertiesModel.setValueAt(eventListFromTask,
		EVENT_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_VALUE_INDEX);
	myTaskPropertiesModel.setValueAt(labelNames, TASK_LABEL_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_COMBOVALUE_INDEX);
    }

    class TaskPropertiesModel extends DefaultPropertiesTableModel {

	private static final long serialVersionUID = 696529317718457347L;

	public TaskPropertiesModel(PropertiesObject[] data) {
	    super(data);
	}

	public boolean isCellEditable(int row, int column) {
	    if (row == KMADETaskPropertiesPanel.TASK_OBJECTS_TITLE_ELEMENT) {
		return false;
	    } else {
		return super.isCellEditable(row, column);
	    }
	}

	public Object getValueAt(int row, int column) {
	    /*
	     * if (column == 1 && row == TASK_VIDEO_PURPOSE_TITLE_ELEMENT) {
	     * Media myMedia = (Media)this.getValue(row); if (myMedia == null) {
	     * return ""; } if (myMedia.isExisting()) { File myFile = new
	     * File(myMedia.getPath() + File.separator + myMedia.getFileName());
	     * if (!myFile.exists()) { myFile = new File(myMedia.getFileName());
	     * 
	     * if (!myFile.exists()) { return
	     * KMADEConstant.TASK_MULTIMEDIA_ERROR_FILE_MESSAGE + "(" +
	     * myMedia.getFileName() + ")"; } } return myMedia.getFileName(); }
	     * else { return ""; } } else
	     */if (column == 1 && row == TASK_LABEL_TITLE_ELEMENT) {
		return super.getValueAt(row, column);
	    } else if (column == 1 && row == USER_TITLE_ELEMENT) {
		ArrayList<?> myList = (ArrayList<?>) this.getValue(row);

		if (myList.size() == 0) {
		    return KMADEConstant.ACTOR_NO_ELEMENT_INTO_TASK;
		}
		String toDisplay = "";
		for (int i = 0; i < myList.size(); i++) {
		    toDisplay += ((Actor) myList.get(i)).getName();
		    if (i < myList.size() - 1) {
			toDisplay += ";";
		    }
		}
		return toDisplay;
	    } else if (column == 1 && row == SYSTEM_USER_TITLE_ELEMENT) {
		ArrayList<?> myList = (ArrayList<?>) this.getValue(row);

		if (myList.size() == 0) {
		    return KMADEConstant.ACTORSYSTEM_NO_ELEMENT_INTO_TASK;
		}
		String toDisplay = "";
		for (int i = 0; i < myList.size(); i++) {
		    toDisplay += ((ActorSystem) myList.get(i)).getName();
		    if (i < myList.size() - 1) {
			toDisplay += ";";
		    }
		}
		return toDisplay;
	    } else if (column == 1 && row == PRECONDITION_TITLE_ELEMENT) {
		PreExpression myPreExpression = (PreExpression) this
			.getValue(row);
		if (myPreExpression.isErrorInExpression()) {
		    return KMADEConstant.ERROR_CARACTERISTIC_MESSAGE + " : "
			    + myPreExpression.getName();
		} else {
		    return myPreExpression.getName();
		}
	    } else if (column == 1 && row == ITERATION_TITLE_ELEMENT) {
		IterExpression myIterExpression = (IterExpression) this
			.getValue(row);
		if (myIterExpression.isErrorInExpression()) {
		    return KMADEConstant.ERROR_CARACTERISTIC_MESSAGE + " : "
			    + myIterExpression.getName();
		} else {
		    return myIterExpression.getName();
		}
	    } else if (column == 1 && row == EVENT_TITLE_ELEMENT) {
		ArrayList<?> myList = (ArrayList<?>) this.getValue(row);

		if (myList.size() == 0) {
		    return KMADEConstant.EVENT_NO_ELEMENT_INTO_TASK;
		}
		String toDisplay = "";
		for (int i = 0; i < myList.size(); i++) {
		    toDisplay += myList.get(i);
		    if (i < myList.size() - 1) {
			toDisplay += ";";
		    }
		}
		return toDisplay;
	    } else if (column == 1 && row == EFFETSDEBORD_TITLE_ELEMENT) {
		SideEffectExpression myEffetsDeBordExpression = (SideEffectExpression) this
			.getValue(row);
		if (myEffetsDeBordExpression.isErrorInExpression()) {
		    return KMADEConstant.ERROR_CARACTERISTIC_MESSAGE + " : "
			    + myEffetsDeBordExpression.getName();
		} else {
		    return myEffetsDeBordExpression.getName();
		}
	    } else {
		return super.getValueAt(row, column);
	    }
	}

	public boolean isNecessityEnabled() {
	    return this.informationTable[NECESSITE_TITLE_ELEMENT].isEditable();
	}

	/**
	 * @return l'ensemble des objets Acteurs de la t�che
	 */
	public ArrayList<?> getActorList() {
	    return (ArrayList<?>) this.getValue(USER_TITLE_ELEMENT);
	}

	/**
	 * @return l'ensemble des objets Acteurs syst�mes de la t�che
	 */
	public ArrayList<?> getActorSystemList() {
	    return (ArrayList<?>) this.getValue(SYSTEM_USER_TITLE_ELEMENT);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if (columnIndex == 0)
		return;

	    if (columnIndex == 1) {
		switch (rowIndex) {
		case GENERAL_TITLE_ELEMENT: {
		    return;
		}
		case TASK_NUMBER_NAME_ELEMENT: {
		    break;
		}
		case PARENT_NAME_ELEMENT: {
		    break;
		}
		case TASK_NAME_TITLE_ELEMENT: {
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setNameFromExpressTask((String) aValue);
		    }
		    break;
		}
		   
		      case TASK_DURATION_TITLE_ELEMENT : { if (!isInit) {
		      TaskPropertiesAdaptator
		      .setDurationFromExpressTask((String)aValue); } break; }
		     
		    
		      case TASK_PURPOSE_TITLE_ELEMENT : { if (!isInit) {
		      TaskPropertiesAdaptator
		      .setButFromExpressTask((String)aValue); } break; }
		     
		    /*
		     * case TASK_VIDEO_PURPOSE_TITLE_ELEMENT : { break; }
		     */

		case TASK_LABEL_TITLE_ELEMENT: {
		    // Déclenchement.
		    super.setValueAt(aValue, rowIndex, columnIndex);
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setLabelExpressTask((String) aValue);
		    }
		    return;
		}

		case TASK_OBJECTS_TITLE_ELEMENT: {
		    return;
		}
		    /*
		     * case TASK_FEEDBACK_TITLE_ELEMENT : { if (!isInit) {
		     * TaskPropertiesAdaptator
		     * .setFeedbackFromExpressTask((String)aValue); } break; }
		     */
		case TASK_OBSERVATION_TITLE_ELEMENT: {
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setObservationFromExpressTask((String) aValue);
		    }
		    break;
		}
		case EXECUTING_NAME_TITLE_ELEMENT: {
		    Executor mySelectedExecutant = Executor
			    .getValue(Executor
				    .getLocaleExecutantIntoEnumere((String) aValue));
		    // Modification de l'attribut acteur.
		    if (Task.canHaveActor(mySelectedExecutant)) {
			informationTable[USER_TITLE_ELEMENT].setEditable(true);
		    } else {
			informationTable[USER_TITLE_ELEMENT].setEditable(false);
		    }
		    if (Task.canHaveActorSystem(mySelectedExecutant)) {
			informationTable[SYSTEM_USER_TITLE_ELEMENT]
				.setEditable(true);
		    } else {
			informationTable[SYSTEM_USER_TITLE_ELEMENT]
				.setEditable(false);
		    }

		    this.fireTableRowsUpdated(USER_TITLE_ELEMENT,
			    USER_TITLE_ELEMENT);
		    super.setValueAt(aValue, rowIndex, columnIndex);

		    if (mySelectedExecutant.equals(Executor.SYS)
			    || mySelectedExecutant.equals(Executor.ABS)
			    || mySelectedExecutant.equals(Executor.INT)) {
			informationTable[MODALITY_NAME_TITLE_ELEMENT]
				.setEditable(false);
		    } else {
			// Modification de l'attribut Modalité.
			informationTable[MODALITY_NAME_TITLE_ELEMENT]
				.setEditable(true);
		    }
		    this.fireTableRowsUpdated(EXECUTING_NAME_TITLE_ELEMENT,
			    EXECUTING_NAME_TITLE_ELEMENT);

		    // Modification de l'attribut Facultative.
		    String decompValue = ((String) this.getValueAt(
			    SCHEDULING_TITLE_ELEMENT, 1));
		    Decomposition dec = Decomposition.getValue(Decomposition
			    .getLocaleDecompositionIntoEnumere(decompValue));
		    if (mySelectedExecutant.equals(Executor.SYS)
			    || dec.equals(Decomposition.ALT)) {
			this.setValueAt(
				false,
				NECESSITE_TITLE_ELEMENT,
				DefaultPropertiesTableModel.COLUMN_ISEDITABLE_INDEX);
		    } else {
			this.setValueAt(
				true,
				NECESSITE_TITLE_ELEMENT,
				DefaultPropertiesTableModel.COLUMN_ISEDITABLE_INDEX);
		    }

		    // Modification de l'attribut Feedback.
		    /*
		     * if (mySelectedExecutant.equals(Executant.SYS)) {
		     * myTaskPropertiesModel
		     * .setValueAt(true,TASK_FEEDBACK_TITLE_ELEMENT
		     * ,DefaultPropertiesTableModel.COLUMN_ISEDITABLE_INDEX); }
		     * else { myTaskPropertiesModel.setValueAt(false,
		     * TASK_FEEDBACK_TITLE_ELEMENT
		     * ,DefaultPropertiesTableModel.COLUMN_ISEDITABLE_INDEX); }
		     */
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setExecutingUserFromExpressTask(mySelectedExecutant);
		    }
		    return;
		}
		case MODALITY_NAME_TITLE_ELEMENT: {
		    if (!isInit) {
			Modality mySelectedModalite = Modality
				.getValue(Modality
					.getLocaleModalityIntoEnumerate((String) aValue));
			TaskPropertiesAdaptator
				.setModalityFromExpressTask(mySelectedModalite);
		    }
		    break;
		}
		case FREQUENCY_NAME_TITLE_ELEMENT: {
		    Frequence mySelectedExecutant = Frequence
			    .getValue(Frequence
				    .getLocaleFrequenceIntoEnumere((String) aValue));
		    if (mySelectedExecutant.equals(Frequence.INCONNU)) {
			informationTable[FREQUENCY_VALUE_NAME_TITLE_ELEMENT]
				.setEditable(false);
		    } else {
			informationTable[FREQUENCY_VALUE_NAME_TITLE_ELEMENT]
				.setEditable(true);
		    }
		    this.fireTableRowsUpdated(FREQUENCY_NAME_TITLE_ELEMENT,
			    FREQUENCY_NAME_TITLE_ELEMENT);
		    if (!isInit) {
			Frequence mySelectedFrequence = Frequence
				.getValue(Frequence
					.getLocaleFrequenceIntoEnumere((String) aValue));
			TaskPropertiesAdaptator
				.setFrequencyFromExpressTask(mySelectedFrequence);
		    }
		    break;
		}
		case FREQUENCY_VALUE_NAME_TITLE_ELEMENT: {
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setFrequencyValueFromExpressTask((String) aValue);
		    }
		    break;
		}
		case IMPORTANT_NAME_TITLE_ELEMENT: {
		    if (!isInit) {
			Importance mySelectedImportance = Importance
				.getValue(Importance
					.getLocaleImportanceIntoEnumere((String) aValue));
			TaskPropertiesAdaptator
				.setSignificantFromExpressTask(mySelectedImportance);
		    }
		    break;
		}
		case SCHEDULING_TITLE_ELEMENT: {
		    return;
		}
		case NECESSITE_TITLE_ELEMENT: {
		    // Optionnel.
		    super.setValueAt(aValue, rowIndex, columnIndex);
		    if (!isInit) {
			if (((String) aValue)
				.equals(KMADEConstant.OPTIONAL_NECESSITE_VALUE)) {
			    TaskPropertiesAdaptator
				    .setOptionalFromExpressTask(true);
			} else {
			    TaskPropertiesAdaptator
				    .setOptionalFromExpressTask(false);
			}
		    }
		    return;
		}
		case INTERRUPTIBLE_TITLE_ELEMENT: {
		    // Interruptible.
		    super.setValueAt(aValue, rowIndex, columnIndex);
		    if (!isInit) {
			if (((String) aValue)
				.equals(KMADEConstant.INTERRUPTIBLE_VALUE)) {
			    TaskPropertiesAdaptator
				    .setInterruptibleFromExpressTask(true);
			} else {
			    TaskPropertiesAdaptator
				    .setInterruptibleFromExpressTask(false);
			}
		    }
		    return;
		}
		case DECLENCHEMENT_TITLE_ELEMENT: {
		    // Déclenchement.
		    super.setValueAt(aValue, rowIndex, columnIndex);
		    if (!isInit) {
			TaskPropertiesAdaptator
				.setDeclenchementFromExpressTask((String) aValue);
		    }
		    return;
		}
		case SCHEDULE_TITLE_ELEMENT: {
		    // Ordonnancement.
		    super.setValueAt(aValue, rowIndex, columnIndex);
		    Decomposition dec = Decomposition
			    .getValue(Decomposition
				    .getLocaleDecompositionIntoEnumere((String) aValue));
		    String executant = ((String) myTaskPropertiesModel
			    .getValueAt(EXECUTING_NAME_TITLE_ELEMENT, 1));
		    Executor myExecutant = Executor.getValue(Executor
			    .getLocaleExecutantIntoEnumere(executant));

		    if (myExecutant.equals(Executor.SYS)
			    || dec.equals(Decomposition.ALT)) {
			informationTable[NECESSITE_TITLE_ELEMENT]
				.setEditable(false);
		    } else {
			informationTable[NECESSITE_TITLE_ELEMENT]
				.setEditable(true);
		    }
		    if (!isInit) {
			TaskPropertiesAdaptator.setOperatorFromExpressTask(dec,
				(String) aValue);
		    }
		    return;
		}
		case USER_TITLE_ELEMENT: {
		    break;
		}
		case PRECONDITION_TITLE_ELEMENT: {
		    break;
		}
		case ITERATION_TITLE_ELEMENT: {
		    break;
		}
		case POSTCONDITION_TITLE_ELEMENT: {
		    return;
		}
		case EVENT_TITLE_ELEMENT: {
		    break;
		}
		case EFFETSDEBORD_TITLE_ELEMENT: {
		    break;
		}
		case SYSTEM_USER_TITLE_ELEMENT: {
		    break;
		}
		}
	    }
	    super.setValueAt(aValue, rowIndex, columnIndex);
	}
    }

    public void addNewEvent(String name) {
	myEditorEvent.addNewEvent(name);
    }

    /*
     * public void addEventDecl(String name){ myEditorDecl.addNewEvent(name); }
     */

    public void addNewActor(String name, String experience, String competence,
	    String oidActor) {
	myEditorActor.addNewActor(name, experience, competence, oidActor);
    }

    public void addNewActorSystem(String name, String experience,
	    String competence, String oidActor) {
	myEditorActorSystem.addNewActorSystem(name, experience, competence,
		oidActor);
    }

    public void displayTaskProperties(String numero, String tacheMere,
	    String name,String but, /* Media media, */
	    String label, /* String feed, */ String duree, 
	    String obs, Executor exec, String mod, String freq,
	    String compFreq, String imp, ArrayList<String> events,
	    Boolean facultatif, Boolean interruptible, String dec,
	    ArrayList<Actor> actRef, PreExpression prec,
	    SideEffectExpression post, String decomposition, IterExpression it) {
	isInit = true;
	myTaskPropertiesModel.setInitValueAt(numero, TASK_NUMBER_NAME_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(tacheMere, PARENT_NAME_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(name, TASK_NAME_TITLE_ELEMENT);
	
	 myTaskPropertiesModel.setInitValueAt(duree,TASK_DURATION_TITLE_ELEMENT
	 );
	 
	myTaskPropertiesModel.setInitValueAt(but,TASK_PURPOSE_TITLE_ELEMENT); 
	myTaskPropertiesModel.setInitValueAt("", TASK_OBJECTS_TITLE_ELEMENT);
	/*
	 * myTaskPropertiesModel.setInitValueAt(media,
	 * TASK_VIDEO_PURPOSE_TITLE_ELEMENT);
	 */
	myTaskPropertiesModel.setInitValueAt(label, TASK_LABEL_TITLE_ELEMENT);
	/*
	 * myTaskPropertiesModel.setInitValueAt(feed,TASK_FEEDBACK_TITLE_ELEMENT)
	 * ;
	 */
	myTaskPropertiesModel.setInitValueAt(obs,
		TASK_OBSERVATION_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(
		Executor.getEnumereIntoLocaleExecutant(exec.getValue()),
		EXECUTING_NAME_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(
		Modality.getEnumerateIntoLocalModality(mod),
		MODALITY_NAME_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(
		Frequence.getEnumereIntoLocaleFrequence(freq),
		FREQUENCY_NAME_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(compFreq,
		FREQUENCY_VALUE_NAME_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(
		Importance.getEnumereIntoLocaleImportance(imp),
		IMPORTANT_NAME_TITLE_ELEMENT);
	if (facultatif)
	    myTaskPropertiesModel.setInitValueAt(
		    KMADEConstant.OPTIONAL_NECESSITE_VALUE,
		    NECESSITE_TITLE_ELEMENT);
	else
	    myTaskPropertiesModel.setInitValueAt(
		    KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE,
		    NECESSITE_TITLE_ELEMENT);

	if (interruptible) {
	    myTaskPropertiesModel.setInitValueAt(
		    KMADEConstant.INTERRUPTIBLE_VALUE,
		    INTERRUPTIBLE_TITLE_ELEMENT);
	} else {
	    myTaskPropertiesModel.setInitValueAt(
		    KMADEConstant.NO_INTERRUPTIBLE_VALUE,
		    INTERRUPTIBLE_TITLE_ELEMENT);
	}
	myTaskPropertiesModel.setInitValueAt(dec, DECLENCHEMENT_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(
		Decomposition.getEnumereIntoLocaleDecomposition(decomposition),
		SCHEDULE_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(actRef, USER_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(prec, PRECONDITION_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(it, ITERATION_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(events, EVENT_TITLE_ELEMENT);
	myTaskPropertiesModel.setInitValueAt(post, EFFETSDEBORD_TITLE_ELEMENT);
	myTaskPropertiesModel.fireTableRowsUpdated(0, 25);
	isInit = false;

	displayOrNotUserHelp();
    }

    public void showEmptyCard() {
	card.show(switchEmptyProp, "Empty");

    }

    public void showPropertyTaskCard() {
	card.show(switchEmptyProp, "Prop");
    }

    public void setTaskNameRealTime(String n) {
	myTaskPropertiesModel.setValueAt(n, TASK_NAME_TITLE_ELEMENT, 1);
    }

    public void setExecutantTypeRealTime(Executor myExecutant) {
	myTaskPropertiesModel
		.setValueAt(Executor.getEnumereIntoLocaleExecutant(myExecutant
			.getValue()), EXECUTING_NAME_TITLE_ELEMENT, 1);
    }

    public void setPurposeRealTime(String p){
    	myTaskPropertiesModel.setValueAt(p, TASK_PURPOSE_TITLE_ELEMENT,1);
    }
    
    public void setObservationRealTime(String p){
    	myTaskPropertiesModel.setValueAt(p, TASK_OBSERVATION_TITLE_ELEMENT,1);
    }
    
    public void setInterruptibleCharacRealTime(boolean b) {
	if (b) {
	    myTaskPropertiesModel.setValueAt(KMADEConstant.INTERRUPTIBLE_VALUE,
		    INTERRUPTIBLE_TITLE_ELEMENT, 1);
	} else {
	    myTaskPropertiesModel.setValueAt(
		    KMADEConstant.NO_INTERRUPTIBLE_VALUE,
		    INTERRUPTIBLE_TITLE_ELEMENT, 1);
	}
    }

    public void setOptionalCharacRealTime(boolean facultatif) {
	if (facultatif)
	    myTaskPropertiesModel.setValueAt(
		    KMADEConstant.OPTIONAL_NECESSITE_VALUE,
		    NECESSITE_TITLE_ELEMENT, 1);
	else
	    myTaskPropertiesModel.setValueAt(
		    KMADEConstant.NO_OPTIONAL_NECESSITE_VALUE,
		    NECESSITE_TITLE_ELEMENT, 1);
    }

    public void setDecompositionRealTime(String enumere) {
	myTaskPropertiesModel.setValueAt(enumere, SCHEDULE_TITLE_ELEMENT, 1);
    }

    
     public void setDureeRealTime(String duree) {
      myTaskPropertiesModel.setValueAt(duree,TASK_DURATION_TITLE_ELEMENT,1); }
     

    public void setFrequencyRealTime(String freq) {
	myTaskPropertiesModel.setValueAt(freq, FREQUENCY_VALUE_NAME_TITLE_ELEMENT, 1);
    }

    
      public void setPurpose() {
     myEditorText.showPropertiesEditor(myTaskPropertiesModel
      ,TASK_PURPOSE_TITLE_ELEMENT); }
      
      public String getPurpose() { return
      (String)(myTaskPropertiesModel.getValueAt(TASK_PURPOSE_TITLE_ELEMENT,1));
      }
     
    /*
     * public void setFeedback() {
     * myEditorText.showPropertiesEditor(myTaskPropertiesModel
     * ,TASK_FEEDBACK_TITLE_ELEMENT); }
     * 
     * public String getFeedback() { return
     * (String)(myTaskPropertiesModel.getValueAt
     * (TASK_FEEDBACK_TITLE_ELEMENT,1)); }
     */

    public void setObservation() {
	myEditorText.showPropertiesEditor(myTaskPropertiesModel,
		TASK_OBSERVATION_TITLE_ELEMENT);
    }

    public String getObservation() {
	return (String) (myTaskPropertiesModel.getValueAt(
		TASK_OBSERVATION_TITLE_ELEMENT, 1));
    }

    public void setExecutant(Executor exec) {
	myTaskPropertiesModel.setValueAt(
		Executor.getEnumereIntoLocaleExecutant(exec.getValue()),
		EXECUTING_NAME_TITLE_ELEMENT, 1);
    }

    public void setModalite(String mod) {
	myTaskPropertiesModel.setValueAt(
		Modality.getEnumerateIntoLocalModality(mod),
		MODALITY_NAME_TITLE_ELEMENT, 1);
    }

    public void setImportance(String enonce) {
	myTaskPropertiesModel.setValueAt(
		Importance.getEnumereIntoLocaleImportance(enonce),
		IMPORTANT_NAME_TITLE_ELEMENT, 1);
    }

    public void setFrequence(String enonce) {
	myTaskPropertiesModel.setValueAt(
		Frequence.getEnumereIntoLocaleFrequence(enonce),
		FREQUENCY_NAME_TITLE_ELEMENT, 1);
	myTaskPropertiesModel.fireTableDataChanged();
    }

    public void setOperator(String decomp) {
	myTaskPropertiesModel.setValueAt(
		Decomposition.getEnumereIntoLocaleDecomposition(decomp),
		SCHEDULE_TITLE_ELEMENT, 1);
    }

    public boolean isNecessityEnabled() {
	return myTaskPropertiesModel.isNecessityEnabled();
    }

    public void setEventDecl() {
	myEditorDecl.showPropertiesEditor(myTaskPropertiesModel,
		DECLENCHEMENT_TITLE_ELEMENT);
    }

    public void setFiredEvents() {
	myEditorEvent.showPropertiesEditor(myTaskPropertiesModel,
		EVENT_TITLE_ELEMENT);
    }

    public String getFiredEvents() {
	return (String) (myTaskPropertiesModel.getValueAt(EVENT_TITLE_ELEMENT,
		1));
    }

    /*
     * public String getEventDecl(){ return
     * (String)(myTaskPropertiesModel.getValueAt(EVENT_TITLE_ELEMENT, 1)); }
     */

    public void setEffetsDeBord() {
	myEditorPrePostIter.showPropertiesEditor(myTaskPropertiesModel,
		EFFETSDEBORD_TITLE_ELEMENT);
    }

    public String getEffetsDeBord() {
	return (String) myTaskPropertiesModel.getValueAt(
		EFFETSDEBORD_TITLE_ELEMENT, 1);
    }

    /**
     * @return l'ensemble des acteurs de la t�che
     */
    public ArrayList<?> getActorList() {
	return myTaskPropertiesModel.getActorList();
    }

    public void setActorList() {
	myEditorActor.showPropertiesEditor(myTaskPropertiesModel,
		USER_TITLE_ELEMENT);
    }

    /**
     * @return l'ensemble des acteurs syst�mes de la t�che
     */
    public ArrayList<?> getActorSystemList() {
	return myTaskPropertiesModel.getActorSystemList();
    }

    public void setActorSystemList() {
	myEditorActorSystem.showPropertiesEditor(myTaskPropertiesModel,
		SYSTEM_USER_TITLE_ELEMENT);
    }

    public void setIteration() {
	myEditorPrePostIter.showPropertiesEditor(myTaskPropertiesModel,
		ITERATION_TITLE_ELEMENT);
    }

    public String getIteration() {
	return (String) myTaskPropertiesModel.getValueAt(
		ITERATION_TITLE_ELEMENT, 1);
    }

    public void setPrecondition() {
	myEditorPrePostIter.showPropertiesEditor(myTaskPropertiesModel,
		PRECONDITION_TITLE_ELEMENT);
    }

    public String getPrecondition() {
	return (String) myTaskPropertiesModel.getValueAt(
		PRECONDITION_TITLE_ELEMENT, 1);
    }

    public void setDeclenchement(String name) {
	myTaskPropertiesModel.setValueAt(name, DECLENCHEMENT_TITLE_ELEMENT, 1);
    }

    public String[] getAllEvents() {
	return (String[]) myTaskPropertiesModel.getValueAt(
		DECLENCHEMENT_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_COMBOVALUE_INDEX);
    }

    public void setFiredEventsInModel(ArrayList<?> plist) {
	myTaskPropertiesModel.setValueAt(
		EventAdaptator.getEventsNameInSelectedTask(),
		EVENT_TITLE_ELEMENT,
		DefaultPropertiesTableModel.COLUMN_VALUE_INDEX);
    }

    public void notifLocalisationModification() {
	myEditorText.notifLocalisationModification();
	myEditorEvent.notifLocalisationModification();
	myEditorDecl.notifLocalisationModification();
	myEditorActor.notifLocalisationModification();
	myEditorActorSystem.notifLocalisationModification();
	myEditorPrePostIter.notifLocalisationModification();
	myEditorMultimedia.notifLocalisationModification();
    }

    public void setPreconditionInModel(PreExpression name) {
	myTaskPropertiesModel.setValueAt(name, PRECONDITION_TITLE_ELEMENT, 1);
    }

    public void setEffetsDeBordInModel(
	    SideEffectExpression effetsDeBordExpression) {
	myTaskPropertiesModel.setValueAt(effetsDeBordExpression,
		EFFETSDEBORD_TITLE_ELEMENT, 1);
    }

    public void setIterationInModel(IterExpression iterExpression) {
	myTaskPropertiesModel.setValueAt(iterExpression,
		ITERATION_TITLE_ELEMENT, 1);
    }

    public KMADEEditorPrePostIterDialog getEditorPrePostIterDialog() {
	return myEditorPrePostIter;
    }

    public KMADEEditorMultimediaDialog getEditorMultimedia() {
	return myEditorMultimedia;
    }
}

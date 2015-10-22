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
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.tache.Event;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEvent;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadEventObjectTable;

/**
 * @author Mickael BARON
 */
public final class EventAdaptator {

    private static final KMADEReadEventObjectTable eventPanel = new KMADEReadEventObjectTable();

    public static int origine = 0;

    public static void disabledFrame() {
	GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
	TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
    }

    public static void editedFromEnhancedFrame() {
	origine = 1;
    }

    public static void enabledFrame() {
	GraphicEditorAdaptator.enabledMainFrameAfterEdition();
	TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
	if (origine == 0) {
	    GraphicEditorAdaptator.requestFocus();
	} else {
	    TaskPropertiesEnhancedEditorAdaptator.requestFocus();
	}
	origine = 0;
    }

    public static void removeAllEvent() {
	GraphicEditorAdaptator.getEventObjectPanel().removeAllEvent();
    }

    public static KMADEReadEventObjectTable getEventReadPanel() {
	return eventPanel;
    }

    public static String addEvent() {
	return ExpressEvent.createEvent();
    }

    public static String setEventName(String oid, String name) {
	return ExpressEvent.setEventName(oid, name);
    }

    public static void setEventDescription(String oid, String description) {
	ExpressEvent.setEventDescription(oid, description);
    }

    public static void removeEvent(String oid) {
	ExpressEvent.removeEvent(oid);
    }

    public static void affRemoveEvent(String oid) {
	ExpressEvent.affRemoveEvent(oid);
    }

    public static ArrayList<String> getEventsName() {
	ArrayList<String> myEventsName = ExpressEvent.getEventsName();
	return myEventsName;
    }

    public static void refreshReadEventTable() {
	ArrayList<Event> myEvent = ExpressEvent.getEvents();
	Object[][] myTab = new Object[myEvent.size()][2];
	for (int i = 0; i < myEvent.size(); i++) {
	    myTab[i][0] = myEvent.get(i).getName();
	    myTab[i][1] = myEvent.get(i).getDescription();
	}
	eventPanel.setData(myTab);
    }

    public static void updateEventView() {
	ArrayList<Event> myList = ExpressEvent.getEvents();
	for (int i = 0; i < myList.size(); i++) {
	    GraphicEditorAdaptator.getEventObjectPanel().addEvent(
		    myList.get(i).getName(), myList.get(i).getDescription(),
		    myList.get(i).getOid().get());
	}
    }

    // Association Tâches et Caractéristique
    public static boolean setEventInSelectedTask(String oldEvent,
	    String newEvent) {
	return ExpressTask.setEventTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), oldEvent,
		newEvent);
    }

    public static void addNewEventInSelectedTask(String name) {
	if (ExpressTask.addNewEventTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), name)) {
	    KMADEMainFrame.getProjectPanel().getPanelProprieteTache()
		    .addNewEvent(name);
	}
    }

    public static void removeEventIntoSelectedTask(String res) {
	ExpressTask.removeEventTask(
		GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }

    public static ArrayList<String> getEventsNameInSelectedTask() {
	return GraphicEditorAdaptator.getSelectedGraphicTask().getTask()
		.getEventsName();
    }

    public static void affRemoveEventInSelectedTask(String maValue) {
	ExpressTask.affRemoveEvent(
		GraphicEditorAdaptator.getSelectedExpressTask(), maValue);
    }

    public static void setFiredEventsInSelectedTask() {
	KMADEMainFrame
		.getProjectPanel()
		.getPanelProprieteTache()
		.setFiredEventsInModel(
			GraphicEditorAdaptator.getSelectedGraphicTask()
				.getTask().getEventsName());
    }
}

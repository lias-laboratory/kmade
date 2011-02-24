package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.adaptatorFC.ExpressEvent;
import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.kmade.view.KMADEMainFrame;
import kmade.kmade.view.taskproperties.readworldobject.KMADEReadEventObjectTable;
import kmade.nmda.schema.tache.Evenement;

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
        ExpressEvent.setEventDescription(oid,description);
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
        ArrayList<Evenement> myEvent = ExpressEvent.getEvents();
        Object[][] myTab = new Object[myEvent.size()][2];
        for (int i = 0; i < myEvent.size(); i ++) {
            myTab[i][0] = myEvent.get(i).getName();
            myTab[i][1] = myEvent.get(i).getDescription();
        }
        eventPanel.setData(myTab);
    }
    
    public static void updateEventView() {
        ArrayList<Evenement> myList = ExpressEvent.getEvents();
        for (int i = 0; i < myList.size(); i++) {
            GraphicEditorAdaptator.getEventObjectPanel().addEvent(myList.get(i).getName(), myList.get(i).getDescription(), myList.get(i).getOid().get());
        }        
    }
    
    // Association Tâches et Caractéristique
    public static boolean setEventInSelectedTask(String oldEvent, String newEvent) {
        return ExpressTask.setEventTask(GraphicEditorAdaptator.getSelectedExpressTask(),oldEvent,newEvent);
    }
    
    public static void addNewEventInSelectedTask(String name) {
        if (ExpressTask.addNewEventTask(GraphicEditorAdaptator.getSelectedExpressTask(),name)) {
            KMADEMainFrame.getProjectPanel().getPanelProprieteTache().addNewEvent(name);
        }        
    }
    
    public static void removeEventIntoSelectedTask(String res) {
        ExpressTask.removeEventTask(GraphicEditorAdaptator.getSelectedExpressTask(), res);
    }
    
    public static ArrayList<String> getEventsNameInSelectedTask() {
        return GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getEventsName();
    }
    
    public static void affRemoveEventInSelectedTask(String maValue) {    
        ExpressTask.affRemoveEvent(GraphicEditorAdaptator.getSelectedExpressTask(), maValue);
    }

    public static void setFiredEventsInSelectedTask() {
        KMADEMainFrame.getProjectPanel().getPanelProprieteTache().setFiredEventsInModel(GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getEventsName());
    }
}

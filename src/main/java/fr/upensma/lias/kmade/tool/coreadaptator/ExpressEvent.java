/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Event;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.CurrentEvents.CurrentEvent;

/**
 * @author Mickael BARON
 */
public class ExpressEvent {
	public static String createEvent() {
		Oid oidEvent = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE, ExpressConstant.EVENT_CLASS);
		return (oidEvent.get());
	}

	public static void removeEvent(String oid) {
		Event e = (Event) InterfaceExpressJava.prendre(new Oid(oid));
		e.delete();
	}

	/*
	 * not used - what is the purpose ??? public static ArrayList<String>
	 * getForeseeableEvent(String oid) { Event e = (Event)
	 * InterfaceExpressJava.prendre(new Oid(oid)); return e.affProvoquer(); }
	 */
	public static void affRemoveEvent(String oid) {
		Event e = (Event) InterfaceExpressJava.prendre(new Oid(oid));
		e.affDelete();
	}

	public static String setEventName(String oid, String name) {
		Event m = (Event) InterfaceExpressJava.prendre(new Oid(oid));
		m.setName(name);
		return m.getName();
	}

	public static void setEventDescription(String oid, String description) {
		Event m = (Event) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(description);
	}

	public static ArrayList<Event> getEvents() {
		ArrayList<Event> lst = new ArrayList<Event>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.EVENT_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Event obj = (Event) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static ArrayList<String> getEventsName() {
		ArrayList<String> lst = new ArrayList<String>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.EVENT_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Event obj = (Event) objs[i];
			lst.add(obj.getName());
		}
		return lst;
	}

	public static Event stringToEvent(String s) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
				ExpressConstant.EVENT_CLASS);
		for (int i = 0; i < objs.length; i++) {
			Event obj = (Event) objs[i];
			if (obj.getName().equals(s)) {
				return obj;
			}
		}
		return null;
	}

	public static String generateFiringEvent(Task myTache) {
		String temp = "";
		for (int i = 0; i < myTache.getEvents().size(); i++) {
			InterfaceExpressJava.getCurrentEvents().addEvent(myTache.getEvents().get(i), myTache);
			temp += "\"" + myTache.getEvents().get(i).getName() + "\"";

			if (i != (myTache.getEvents().size() - 1)) {
				temp += ", ";
			}
		}
		return temp;
	}

	public static void extractFiringEvent(Event p) {
		InterfaceExpressJava.getCurrentEvents().extractEvent(p);
	}

	public static CurrentEvent[] getAllCurrentEvents() {
		return InterfaceExpressJava.getCurrentEvents().getAllCurrentEvents();
	}

	public static boolean isExistingEvent(Event p) {
		return InterfaceExpressJava.getCurrentEvents().isExistingEvent(p);
	}

	public static void clearFiringEvent() {
		InterfaceExpressJava.getCurrentEvents().clearAllEvents();
	}
}

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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Mickael BARON
 */
public class CurrentEvents {

    private HashMap<String, CurrentEvent> myList;

    public CurrentEvents() {
	myList = new HashMap<String, CurrentEvent>();
    }

    public CurrentEvent[] getAllCurrentEvents() {
	CurrentEvent[] toto = new CurrentEvent[myList.values().size()];
	Collection<CurrentEvent> teoto = myList.values();
	toto = teoto.toArray(toto);
	ArrayList<CurrentEvent> temp = new ArrayList<CurrentEvent>(
		myList.values());
	for (int i = 0; i < myList.values().size(); i++) {
	    toto[i] = temp.get(i);
	}
	return toto;
    }

    public void addEvent(Event p, Task t) {
	if (p == null) {
	    return;
	}
	myList.put(p.getOid().get(), new CurrentEvent(t, p));
    }

    public boolean extractEvent(Event p) {
	if (p == null) {
	    return false;
	}
	myList.remove(p.getOid().get());
	return true;
    }

    public boolean isExistingEvent(Event p) {
	if (p == null) {
	    return false;
	}
	return (myList.get(p.getOid().get()) != null);
    }

    public void clearAllEvents() {
	myList.clear();
    }

    public class CurrentEvent {
	private Task firingTask;
	private Event firableEvent;

	public CurrentEvent(Task t, Event e) {
	    this.firableEvent = e;
	    this.firingTask = t;
	}

	public Event getFirableEvent() {
	    return firableEvent;
	}

	public Task getFiringTask() {
	    return firingTask;
	}
    }
}

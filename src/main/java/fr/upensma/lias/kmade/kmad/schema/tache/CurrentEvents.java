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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class used by the simulator to manage events
 * 
 * @author Mickael BARON
 */
public class CurrentEvents {

    private HashMap<String, CurrentEvent> events;

    public CurrentEvents() {
	events = new HashMap<String, CurrentEvent>();
    }

    public CurrentEvent[] getAllCurrentEvents() {
	CurrentEvent[] toto = new CurrentEvent[events.values().size()];
	Collection<CurrentEvent> teoto = events.values();
	toto = teoto.toArray(toto);
	ArrayList<CurrentEvent> temp = new ArrayList<CurrentEvent>(
		events.values());
	for (int i = 0; i < events.values().size(); i++) {
	    toto[i] = temp.get(i);
	}
	return toto;
    }

    public void addEvent(Event p, Task t) {
	if (p == null) {
	    return;
	}
	events.put(p.getOid().get(), new CurrentEvent(t, p));
    }

    public boolean extractEvent(Event p) {
	if (p == null) {
	    return false;
	}
	events.remove(p.getOid().get());
	return true;
    }

    public boolean isExistingEvent(Event p) {
	if (p == null) {
	    return false;
	}
	return (events.get(p.getOid().get()) != null);
    }

    public void clearAllEvents() {
	events.clear();
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

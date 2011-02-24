package kmade.nmda.schema.tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
public class CurrentEvents {
    // Evite les doublons ...
    private HashMap<String,CurrentEvent> myList;
    
    public CurrentEvents() {
        myList = new HashMap<String,CurrentEvent>();
    }

    public CurrentEvent[] getAllCurrentEvents() {
    		CurrentEvent[] toto = new CurrentEvent[myList.values().size()];
            Collection<CurrentEvent> teoto = myList.values();
            toto = teoto.toArray(toto);
    		ArrayList<CurrentEvent> temp = new ArrayList<CurrentEvent>(myList.values());
    		for (int i = 0 ; i < myList.values().size(); i++) {
    			toto[i] = temp.get(i);
    		}
        return toto;
    }
    
    public void addEvent(Evenement p, Tache t) {
        if (p == null) {
            return;
        }
        myList.put(p.getOid().get(),new CurrentEvent(t,p));
    }
    
    public boolean extractEvent(Evenement p) {
        if (p == null) {
            return false;
        }
        myList.remove(p.getOid().get());
        return true;
    }
    
    public boolean isExistingEvent(Evenement p) {
        if (p == null) {
            return false;
        }
        return (myList.get(p.getOid().get()) != null);
    }
    
    public void clearAllEvents() {
        myList.clear();
    }
    
    public class CurrentEvent {
    	   private Tache firingTask;
    	   private Evenement firableEvent;
    	   
    	   public CurrentEvent(Tache t, Evenement e) {
    		   this.firableEvent = e;
    		   this.firingTask = t;
    	   }
    	   
    	   public Evenement getFirableEvent() {
    		   return firableEvent;
    	   }
    	   
    	   public Tache getFiringTask() {
    		   return firingTask;
    	   }
    }
}

package kmade.kmade.coreadaptator;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.tache.CurrentEvents.CurrentEvent;
import kmade.nmda.schema.tache.Evenement;
import kmade.nmda.schema.tache.Tache;

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
public class ExpressEvent {
    public static String createEvent() {
        Oid oidEvent = InterfaceExpressJava.createEntity("tache", "Evenement");
        return (oidEvent.get());
    }

    public static void removeEvent(String oid) {
        Evenement e = (Evenement) InterfaceExpressJava.prendre(new Oid(oid));
        e.delete();
    }

    public static ArrayList<String> getForeseeableEvent(String oid) {
        Evenement e = (Evenement) InterfaceExpressJava.prendre(new Oid(oid));
        return e.affProvoquer();
    }
    
    public static void affRemoveEvent(String oid) {
        Evenement e = (Evenement) InterfaceExpressJava.prendre(new Oid(oid));
        e.affDelete();
    }
    
    public static String setEventName(String oid, String name) {
        Evenement m = (Evenement) InterfaceExpressJava.prendre(new Oid(oid));
        m.setName(name);
        return m.getName();
    }

    public static void setEventDescription(String oid, String description) {
        Evenement m = (Evenement) InterfaceExpressJava.prendre(new Oid(oid));
        m.setDescription(description);
    }

    public static ArrayList<Evenement> getEvents() {
        ArrayList<Evenement> lst = new ArrayList<Evenement>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Evenement");
        for (int i = 0; i < objs.length; i++) {
            Evenement obj = (Evenement) objs[i];
            lst.add(obj);
        }
        return lst;
    }

    public static ArrayList<String> getEventsName() {
        ArrayList<String> lst = new ArrayList<String>();
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Evenement");
        for (int i = 0; i < objs.length; i++) {
            Evenement obj = (Evenement) objs[i];
            lst.add(obj.getName());
        }
        return lst;
    }

    public static Evenement stringToEvent(String s) {
        Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Evenement");
        for (int i = 0; i < objs.length; i++) {
            Evenement obj = (Evenement) objs[i];
            if (obj.getName().equals(s)) {
                return obj;
            }
        }
        return null;
    } 
    
    public static String generateFiringEvent(Tache myTache) {
        String temp = "";
        for (int i = 0 ; i < myTache.getEvents().size() ; i++) {
            InterfaceExpressJava.getCurrentEvents().addEvent(myTache.getEvents().get(i),myTache);
            temp += "\"" + myTache.getEvents().get(i).getName() + "\"";

            if (i != (myTache.getEvents().size() -1)) {
                temp += ", ";
            }
        }
        return temp;
    }    
    
    public static void extractFiringEvent(Evenement p) {
        InterfaceExpressJava.getCurrentEvents().extractEvent(p);
    }
    
    public static CurrentEvent[] getAllCurrentEvents() {
        return InterfaceExpressJava.getCurrentEvents().getAllCurrentEvents();
    }
    
    public static boolean isExistingEvent(Evenement p) {
    	return InterfaceExpressJava.getCurrentEvents().isExistingEvent(p);
    }
    
    public static void clearFiringEvent() {
    	InterfaceExpressJava.getCurrentEvents().clearAllEvents();
    }
}

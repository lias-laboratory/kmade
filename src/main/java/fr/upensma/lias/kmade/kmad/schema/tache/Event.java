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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Vincent LUCQUIAUD and Mickael BARON
 **/
public class Event implements Entity {

    private static final long serialVersionUID = -7501424700161825538L;

    public Oid oid = null;

    private String name;

    private String description;

    private ArrayList<Task> reverseTask = new ArrayList<Task>();

    public Event() {
	name = "";
	description = "";
    }

    public Event(String name) {
	this.name = name;
	this.description = "";

    }

    public Event(String name, String description) {
	this.name = name;
	this.description = description;
    }

    public Event(String name, String description, Oid oid) {
	this.oid = oid;
	this.name = name;
	this.description = description;
    }

    public void delete() {
	for (int i = 0; i < reverseTask.size(); i++) {
	    Task t = reverseTask.get(i);
	    t.removeEvent(this);
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 4);
	for (int i = 0; i < reverseTask.size(); i++) {
	    Task t = reverseTask.get(i);
	    InterfaceExpressJava.getGestionWarning().addMessage(
		    oid,
		    4,
		    ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
			    + t.getName() + "\"");
	}
    }

    public void addReverseTask(Task a) {
	reverseTask.add(a);
    }

    public void removeReverseTask(Task a) {
	reverseTask.remove(a);
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public org.w3c.dom.Element toXML2(Document doc) {
	Element racine = doc.createElement("event");
	racine.setAttribute("classkmad", "tache.Evenement");
	racine.setAttribute("idkmad", oid.get());

	Element kmadEventName = doc.createElement("event-name");
	kmadEventName.setTextContent(this.getName());
	racine.appendChild(kmadEventName);

	if (!this.getDescription().equals("")) {
	    Element kmadEventDescription = doc
		    .createElement("event-description");
	    kmadEventDescription.setTextContent(this.getDescription());
	    racine.appendChild(kmadEventDescription);
	}

	return racine;
    }

    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadEventName = p.getElementsByTagName("event-name");

	this.name = kmadEventName.item(0).getTextContent();
	NodeList kmadEventDescription = p.getElementsByTagName("event-description");
	
	if (kmadEventDescription.item(0) != null) {
	    this.description = kmadEventDescription.item(0).getTextContent();
	}
    }

    public String toSPF() {
	return oid.get() + "=Evenement('" + name + "',." + description + ".);";
    }

    public String toString() {
	return this.name;
    }

    /**
     * @param s the name of the event
     * @return true if the name is unique among task names and event names
     */
    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.CORE_PACKAGE,
			ExpressConstant.EVENT_CLASS);
	for (int i = 0; i < objAbs.length; i++) {
	    Event obj = (Event) objAbs[i];
	    if (s.equalsIgnoreCase(obj.name)) {
		return false;
	    }
	}
	return true;
    }

    public Oid getOid() {
	return oid;
    }

    public String getDescription() {
	return this.description;
    }

    public void setDescription(String p) {
	this.description = p;
    }

    public String getName() {
	return name;
    }

    public void setName(String n) {
	name = n;
    }

/*  not used - what is the purpose ?
 	public ArrayList<String> affProvoquer() {
	ArrayList<String> stAff = new ArrayList<String>();
	for (int i = 0; i < inverseTache.size(); i++) {
	    Task t = inverseTache.get(i);
	    stAff.add(ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
		    + t.getName() + "\"");
	}
	return stAff;
    }
*/
    public static String proposeName(String n) {
	boolean ok = false;
	int cpt = 0;
	// n = n.replace(" ", "_");
	while (!ok) {
	    if (cpt != 0) {
		if (cpt == 1) {
		    n = n + "_" + String.valueOf(cpt);
		} else {
		    n = n.substring(0, n.length() - 1) + String.valueOf(cpt);
		}
	    }
	    ok = isUniqueName(n);
	    cpt++;
	}
	return n;
    }

/*    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	return toXML(doc);
    }
*/
    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
    	this.oid = new Oid(p.getAttribute("idkmad"));

    	NodeList kmadEventName = p.getElementsByTagName("event-name");
    	if(kmadEventName.item(0).getParentNode() != p){
    		kmadEventName = null;}
    	this.name = kmadEventName.item(0).getTextContent();
    	NodeList kmadEventDescription = p.getElementsByTagName("event-description");
    	if(kmadEventDescription.item(0).getParentNode() != p){
    		kmadEventDescription = null;}
    	if (kmadEventDescription.item(0) != null) {
    	    this.description = kmadEventDescription.item(0).getTextContent();
    	}
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	return false;
    }
}

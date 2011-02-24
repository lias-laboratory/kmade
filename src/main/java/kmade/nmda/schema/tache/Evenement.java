package kmade.nmda.schema.tache;

import java.util.ArrayList;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
 * @author Vincent Lucquiaud Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class Evenement implements Entity {

	private static final long serialVersionUID = -7501424700161825538L;

	public Oid oid = null;

	private String name;

	private String description;

	private ArrayList<Tache> inverseTache = new ArrayList<Tache>();

	public Evenement() {
		name = "";
		description = "";
	}

	public Evenement(String name) {
		this.name = name;
		this.description = "";

	}

	public Evenement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Evenement(String name, String description, Oid oid) {
		this.oid = oid;
		this.name = name;
		this.description = description;
	}

	public void delete() {
		for (int i = 0; i < inverseTache.size(); i++) {
			Tache t = inverseTache.get(i);
			t.removeEvent(this);
		}
		InterfaceExpressJava.remove(oid);
	}

	public void affDelete() {
		InterfaceExpressJava.getGestionWarning().addMessage(oid, 4);
		for (int i = 0; i < inverseTache.size(); i++) {
			Tache t = inverseTache.get(i);
			InterfaceExpressJava.getGestionWarning().addMessage(oid, 4, ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \"" + t.getName() + "\"");
		}
	}

	public void addInverseTache(Tache a) {
		inverseTache.add(a);
	}

	public void removeInverseTache(Tache a) {
		inverseTache.remove(a);
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public org.w3c.dom.Element toXML(Document doc) {   
		Element racine = doc.createElement("event");
		racine.setAttribute("classkmad", "tache.Evenement");
		racine.setAttribute("idkmad", oid.get());

		Element kmadEventName = doc.createElement("event-name");
		kmadEventName.setTextContent(this.getName());
		racine.appendChild(kmadEventName);

		if (!this.getDescription().equals("")) {
			Element kmadEventDescription = doc.createElement("event-description");
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

	public static boolean isUniqueName(String s) {
		Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "Evenement");
		for (int i = 0; i < objAbs.length; i++) {
			Evenement obj = (Evenement) objAbs[i];
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

	public ArrayList<String> affProvoquer() {
		ArrayList<String> stAff = new ArrayList<String>();
		for (int i = 0; i < inverseTache.size(); i++) {
			Tache t = inverseTache.get(i);
			stAff.add(ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \"" + t.getName() + "\"");
		}
		return stAff;
	}
	
	public static String propositionNom(String n){
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
}

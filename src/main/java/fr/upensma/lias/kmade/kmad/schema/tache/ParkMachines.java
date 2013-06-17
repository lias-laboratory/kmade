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

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class ParkMachines extends Material {

    private static final long serialVersionUID = -4187152840289735872L;

    private ArrayList<Machine> members = new ArrayList<Machine>();

    public ParkMachines() {
	super();
    }

    public ParkMachines(String name, Oid oid) {
	super(name, oid);
    }

    public ParkMachines(String name, String description, Oid oid) {
	super(name, description, oid);
    }

    public void delete() {
	// supprimer le parc des machines
	for (int i = 0; i < members.size(); i++) {
	    members.get(i).removeToPark(this);
	}
	super.delete();
    }

    /**
     * Enregistre la machine machine au sein du parc La machine ne sait pas
     * qu'elle appartient au parc
     * 
     * @param machine
     */
    public void addMember(Machine machine) {
	if (!members.contains(machine)) {
	    members.add(machine);
	}
    }

    public void removeFromPark(Machine machine) {
	members.remove(machine);
    }

    public ArrayList<Machine> getMembers() {
	return members;
    }

/*    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("ParcMachines");
	racine.setAttribute("classkmad", "tache.ParcMachines");
	racine.setAttribute("idkmad", oid.get());

	Element kmadParcName = doc.createElement("parcMachines-name");
	kmadParcName.setTextContent(this.getName());
	racine.appendChild(kmadParcName);

	if (!this.getDescription().equals("")) {
	    Element kmadParcDescription = doc
		    .createElement("parcMachines-description");
	    kmadParcDescription.setTextContent(this.getDescription());
	    racine.appendChild(kmadParcDescription);
	}

	if (!super.getImage().equals("")) {
	    Element kmadParcImagePath = doc
		    .createElement("parcMachines-imagepath");
	    kmadParcImagePath.setTextContent(super.getImage());
	    racine.appendChild(kmadParcImagePath);
	}
	// seul les machines stocks les parcs dans le XML

	return racine;
    }
*/
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));
	members.clear();

	NodeList kmadParcName = p.getElementsByTagName("parcMachines-name");
	if (kmadParcName.item(0) != null)
	    super.setName(kmadParcName.item(0).getTextContent());

	NodeList kmadParcDescription = p
		.getElementsByTagName("parcMachines-description");
	if (kmadParcDescription.item(0) != null)
	    super.setDescription(kmadParcDescription.item(0).getTextContent());

	NodeList kmadParcImagePath = p
		.getElementsByTagName("parcMachines-imagepath");
	if (kmadParcImagePath.item(0) != null)
	    super.setImage(kmadParcImagePath.item(0).getTextContent());
    }

    public String toSPF() {
	return oid.get() + "=ParcMachines('" + super.getName() + "','"
		+ super.getDescription() + "','" + super.getImage() + "');";
    }

    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < members.size(); i++) {
	    s += members.get(i).getName();
	    if (i != members.size() - 1) {
		s += ", ";
	    }
	}

	Object[] res = { super.oid.get(), super.getName(),
		super.getDescription(), super.getImage(), s };
	return res;
    }

    public static int toArrayLenght() {
	return 5;
    }

    public Element toXML2(Document doc) throws Exception {
    	Element racine = doc.createElement("ParcMachines");
    	racine.setAttribute("classkmad", "tache.ParcMachines");
    	racine.setAttribute("idkmad", oid.get());

    	Element kmadParcName = doc.createElement("parcMachines-name");
    	kmadParcName.setTextContent(this.getName());
    	racine.appendChild(kmadParcName);

    	if (!this.getDescription().equals("")) {
    	    Element kmadParcDescription = doc
    		    .createElement("parcMachines-description");
    	    kmadParcDescription.setTextContent(this.getDescription());
    	    racine.appendChild(kmadParcDescription);
    	}

    	if (!super.getImage().equals("")) {
    	    Element kmadParcImagePath = doc
    		    .createElement("parcMachines-imagepath");
    	    kmadParcImagePath.setTextContent(super.getImage());
    	    racine.appendChild(kmadParcImagePath);
    	}
    	// seul les machines stocks les parcs dans le XML

    	return racine;
    }

    public void createObjectFromXMLElement2(Element p) throws Exception {
    	this.oid = new Oid(p.getAttribute("idkmad"));
    	members.clear();

    	NodeList kmadParcName = p.getElementsByTagName("parcMachines-name");
    	if(kmadParcName.item(0).getParentNode() != p){
    		kmadParcName = null;}
    	if (kmadParcName.item(0) != null)
    	    super.setName(kmadParcName.item(0).getTextContent());

    	NodeList kmadParcDescription = p
    		.getElementsByTagName("parcMachines-description");
    	if(kmadParcDescription.item(0).getParentNode() != p){
    		kmadParcDescription = null;}
    	if (kmadParcDescription.item(0) != null)
    	    super.setDescription(kmadParcDescription.item(0).getTextContent());

    	NodeList kmadParcImagePath = p
    		.getElementsByTagName("parcMachines-imagepath");
    	if(kmadParcImagePath.item(0).getParentNode() != p){
    		kmadParcImagePath = null;}
    	if (kmadParcImagePath.item(0) != null)
    	    super.setImage(kmadParcImagePath.item(0).getTextContent());
    }

    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {
    	return false;
    }
}
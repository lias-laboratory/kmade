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

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class Machine extends Materiel{

    private static final long serialVersionUID = 144972651724436715L;

    private ArrayList<ParcMachines> memberOf = new ArrayList<ParcMachines>();

    private Boolean isComputer = true;

    public Machine() {
	super();
    }

    public Machine(String name, Oid oid) {
	super(name, oid);
    }

    public Machine(String name, String description, Oid oid) {
	super(name, description, oid);
    }

    public Machine(String name, String description, Oid oid, Boolean isComputer) {
	super(name, description, oid);
	this.isComputer = isComputer;
    }

    public void delete() {
	for (int i = 0; i < memberOf.size(); i++) {
	    memberOf.get(i).removeToParc(this);
	}
	super.delete();
    }

    /**
     * Enregistre que la machine fait partie du parcMachine parc Le parc ne sait
     * pas que la machine en fait partie
     * 
     * @param parc
     */
    public void addToParc(ParcMachines parc) {
	if (!memberOf.contains(parc)) {
	    memberOf.add(parc);
	}
    }

    public ArrayList<ParcMachines> getMemberOf() {
	return memberOf;
    }

    public void removeToParc(ParcMachines parc) {
	memberOf.remove(parc);
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = doc.createElement("Machine");
	racine.setAttribute("classkmad", "tache.Machine");
	racine.setAttribute("idkmad", oid.get());

	Element kmadMachineName = doc.createElement("machine-name");
	kmadMachineName.setTextContent(this.getName());
	racine.appendChild(kmadMachineName);

	if (!this.getDescription().equals("")) {
	    Element kmadMachineDescription = doc
		    .createElement("machine-description");
	    kmadMachineDescription.setTextContent(this.getDescription());
	    racine.appendChild(kmadMachineDescription);
	}

	Element kmadMachineDescription = doc
		.createElement("machine-isComputer");
	kmadMachineDescription.setTextContent(isComputer.toString());
	racine.appendChild(kmadMachineDescription);

	if (!super.getImage().equals("")) {
	    Element kmadMachineImagePath = doc
		    .createElement("machine-imagepath");
	    kmadMachineImagePath.setTextContent(super.getImage());
	    racine.appendChild(kmadMachineImagePath);
	}

	if (this.memberOf.size() != 0) {
	    for (int i = 0; i < memberOf.size(); i++) {
		Element idMachine = doc.createElement("id-parcMachine");
		idMachine.setTextContent(memberOf.get(i).getOid().get());
		racine.appendChild(idMachine);
	    }
	}
	return racine;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p.getElementsByTagName("id-parcMachine");
	for (int i = 0; i < userValue.getLength(); i++) {
	    if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(i)
		    .getTextContent())) == null) {
		return true;
	    }
	}

	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadMachineName = p.getElementsByTagName("machine-name");
	if (kmadMachineName.item(0) != null)
	    super.setName(kmadMachineName.item(0).getTextContent());

	NodeList kmadMachineDescription = p
		.getElementsByTagName("machine-description");
	if (kmadMachineDescription.item(0) != null)
	    super.setDescription(kmadMachineDescription.item(0)
		    .getTextContent());

	NodeList kmadMachineIsComputer = p
		.getElementsByTagName("machine-isComputer");
	if (kmadMachineIsComputer.item(0) != null)
	    setIsComputer(kmadMachineIsComputer.item(0).getTextContent());

	NodeList kmadMachineImagePath = p
		.getElementsByTagName("machine-imagepath");
	if (kmadMachineImagePath.item(0) != null) {
	    super.setImage(kmadMachineImagePath.item(0).getTextContent());
	}

	NodeList kmadMachineParc = p.getElementsByTagName("id-parcMachine");
	if (kmadMachineParc.item(0) != null) {
	    for (int i = 0; i < kmadMachineParc.getLength(); i++) {
		this.addToParc((ParcMachines) InterfaceExpressJava.bdd
			.prendre(new Oid(kmadMachineParc.item(i)
				.getTextContent())));
	    }
	}
    }

    private void setIsComputer(String textContent) {
	try {
	    Boolean t = new Boolean(textContent);
	    isComputer = t;
	} catch (Exception e) {

	}

    }

    public void setIsComputer(Boolean isComputer) {
	this.isComputer = isComputer;
    }

    public Boolean getIsComputer() {
	return isComputer;
    }

    public boolean isComputer() {
	return isComputer.booleanValue();
    }

    public String toSPF() {
	String s = new String("(");
	for (int i = 0; i < memberOf.size(); i++) {
	    s = s + "#" + memberOf.get(i).getOid().toString();
	    if (i != memberOf.size() - 1)
		s += ",";
	}
	s += ")";
	return oid.get() + "=Machine('" + super.getName() + "','"
		+ super.getDescription() + "','" + isComputer() + "','"
		+ super.getImage() + "','" + s + "');";
    }

    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < memberOf.size(); i++) {
	    s += memberOf.get(i).getName();
	    if (i != memberOf.size() - 1) {
		s += ", ";
	    }
	}

	Object[] res = { super.oid.get(), super.getName(),
		super.getDescription(), isComputer(), super.getImage(), s };
	return res;
    }

    public static int toArrayLenght() {
	return 6;
    }
}
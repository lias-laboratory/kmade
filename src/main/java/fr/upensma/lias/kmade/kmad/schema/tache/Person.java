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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * The Individual class stands for human beings involved in the task analysis
 * process. The class inherits from User to allow using Individuals or
 * Organizations as Actors The only responsibility of this class is to maintain
 * the list or organization an individual belongs to.
 * 
 * @author Mickael BARON
 * @author Patrick GIRARD
 */
public class Person extends User {

    private static final long serialVersionUID = 144972651724436715L;

    /**
     * List of organizations the individual is member of
     */
    private ArrayList<Organization> organizations = new ArrayList<Organization>();

    /**
     * Empty constructor
     */
    public Person() {
	super();
    }

    /**
     * Constructor with only a name and an oid
     * 
     * @param name
     *            name of the individual
     * @param oid
     *            unique Express identifier
     */
    public Person(String name, Oid oid) {
	super(name, "", "", "", oid);
    }

    /**
     * Constructor with an empty image path
     * 
     * @param name
     *            name of the individual
     * @param st
     *            status
     * @param r
     *            role
     * @param oid
     *            unique Express identifier
     */
    public Person(String name, String st, String r, Oid oid) {
	super(name, st, r, "", oid);
    }

    /**
     * Complete constructor
     * 
     * @param name
     *            name of the individual
     * @param st
     *            status
     * @param r
     *            role
     * @param pi
     *            image path
     * @param oid
     *            unique Express identifier
     */
    public Person(String name, String st, String r, String pi, Oid oid) {
	super(name, st, r, pi, oid);
    }

    @Override
    /**
     * removes the individual from all organizations he/she is member of
     * @see fr.upensma.lias.kmade.kmad.schema.tache.User#delete()
     */
    public void delete() {
	for (int i = 0; i < organizations.size(); i++) {
	    organizations.get(i).removeIndividu(this);
	}
	super.delete();
    }

    /**
     * Registers the organization from which the individual is supposed to be
     * member A verification is made for avoiding duplication Warning: the
     * reverse registering is not done at this step
     * 
     * @param org
     *            the organization to register
     */
    public void addOrganization(Organization org) {
	if (!organizations.contains(org)) {
	    organizations.add(org);
	}
    }

    /**
     * Gives the list of organizations the individual is member of Warning: it
     * is a reference on the list itself. Be careful not to modify it
     * 
     * @return an ArrayList which contains all organizations the individual is
     *         member of
     */
    public ArrayList<Organization> getOrganisations() {
	return organizations;
    }

    /**
     * Removes the organization from the organization it was member Warning: no
     * verification is made on the existence of the organization
     * 
     * @param org
     *            the organization to be removed
     */
    public void removeOrganisation(Organization org) {
	organizations.remove(org);
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	NodeList userValue = p.getElementsByTagName("id-organisation");
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

	NodeList kmadIndividuName = p.getElementsByTagName("individu-name");

	if (kmadIndividuName.item(0) != null)
	    super.setName(kmadIndividuName.item(0).getTextContent());

	NodeList kmadIndividuStatut = p.getElementsByTagName("individu-statut");

	if (kmadIndividuStatut.item(0) != null)
	    super.setStatus(kmadIndividuStatut.item(0).getTextContent());

	NodeList kmadIndividuRole = p.getElementsByTagName("individu-role");

	if (kmadIndividuRole.item(0) != null)
	    super.setRole(kmadIndividuRole.item(0).getTextContent());

	NodeList kmadIndividuImagePath = p
		.getElementsByTagName("individu-imagepath");

	if (kmadIndividuImagePath.item(0) != null)
	    super.setImage(kmadIndividuImagePath.item(0).getTextContent());

	NodeList kmadIndividuOrganisation = p
		.getElementsByTagName("id-organisation");

	if (kmadIndividuOrganisation.item(0) != null) {
	    for (int i = 0; i < kmadIndividuOrganisation.getLength(); i++) {
		this.addOrganization((Organization) InterfaceExpressJava.bdd
			.prendre(new Oid(kmadIndividuOrganisation.item(i)
				.getTextContent())));
	    }
	}
    }

    public String toSPF() {
	String s = new String("(");
	for (int i = 0; i < organizations.size(); i++) {
	    s = s + "#" + organizations.get(i).getOid().toString();
	    if (i != organizations.size() - 1)
		s += ",";
	}
	s += ")";
	return oid.get() + "=Individu('" + super.getName() + "','"
		+ super.getStatus() + "','" + super.getRole() + "','"
		+ super.getImage() + "','" + s + "');";
    }

    /**
     * Returns an array containing all characteristics of an individual
     * 
     * @return an array of Object, which contains all attributes of superclass,
     *         plus a list of group name the individual is member of
     */
    public Object[] toArray() {
	String s = "";
	for (int i = 0; i < organizations.size(); i++) {
	    s += organizations.get(i).getName();
	    if (i != organizations.size() - 1) {
		s += ", ";
	    }
	}

	Object[] res = { super.oid.get(), super.getName(), super.getStatus(),
		super.getRole(), super.getImage(), s };
	return res;
    }

    /**
     * This function is supposed to give the size of the array returned by the
     * "toArray" method It returns always 5 ! Warning: Very dangerous
     * implementation
     * 
     * @return 5
     */
    public static int toArrayLenght() {
	return 5;
    }

    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = doc.createElement("Individu");
	racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "."
		+ ExpressConstant.PERSON_CLASS);
	racine.setAttribute("idkmad", oid.get());

	if (this.organizations.size() != 0) {
	    String list = new String("");
	    for (int i = 0; i < organizations.size(); i++) {
		list += organizations.get(i).getOid().get() + " ";
	    }
	    racine.setAttribute("id-organisation", list);
	}

	Element kmadIndividuName = doc.createElement("individu-name");
	kmadIndividuName.setTextContent(this.getName());
	racine.appendChild(kmadIndividuName);

	if (!this.getStatus().equals("")) {
	    Element kmadIndividuStatut = doc.createElement("individu-statut");
	    kmadIndividuStatut.setTextContent(this.getStatus());
	    racine.appendChild(kmadIndividuStatut);
	}

	if (!this.getRole().equals("")) {
	    Element kmadIndividuRole = doc.createElement("individu-role");
	    kmadIndividuRole.setTextContent(this.getRole());
	    racine.appendChild(kmadIndividuRole);
	}

	if (!this.getImage().equals("")) {
	    Element kmadIndividuImagePath = doc
		    .createElement("individu-imagepath");
	    kmadIndividuImagePath.setTextContent(this.getImage());
	    racine.appendChild(kmadIndividuImagePath);
	}
	if (this.organizations.size() != 0) {
	    for (int i = 0; i < organizations.size(); i++) {
		Element idOrganisation = doc.createElement("id-organisation");
		idOrganisation.setTextContent(organizations.get(i).getOid()
			.get());
		racine.appendChild(idOrganisation);
	    }
	}
	return racine;

    }

    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub
	this.oid = new Oid(p.getAttribute("idkmad"));
	organizations.clear();
	if (p.hasAttribute("id-organisation")) {
	    String[] kmadIndividuOrganisation = p.getAttribute(
		    "id-organisation").split(" ");
	    for (int i = 0; i < kmadIndividuOrganisation.length; i++) {
		this.addOrganization((Organization) InterfaceExpressJava.bdd
			.prendre(new Oid(kmadIndividuOrganisation[i])));
	    }
	}

	NodeList kmadIndividuName = p.getElementsByTagName("individu-name");
	if (kmadIndividuName.item(0).getParentNode() != p) {
	    kmadIndividuName = null;
	}
	if (kmadIndividuName.item(0) != null)
	    super.setName(kmadIndividuName.item(0).getTextContent());

	NodeList kmadIndividuStatut = p.getElementsByTagName("individu-statut");
	if (kmadIndividuStatut.item(0).getParentNode() != p) {
	    kmadIndividuStatut = null;
	}
	if (kmadIndividuStatut.item(0) != null)
	    super.setStatus(kmadIndividuStatut.item(0).getTextContent());

	NodeList kmadIndividuRole = p.getElementsByTagName("individu-role");
	if (kmadIndividuRole.item(0).getParentNode() != p) {
	    kmadIndividuRole = null;
	}
	if (kmadIndividuRole.item(0) != null)
	    super.setRole(kmadIndividuRole.item(0).getTextContent());

	NodeList kmadIndividuImagePath = p
		.getElementsByTagName("individu-imagepath");
	if (kmadIndividuImagePath.item(0).getParentNode() != p) {
	    kmadIndividuImagePath = null;
	}
	if (kmadIndividuImagePath.item(0) != null)
	    super.setImage(kmadIndividuImagePath.item(0).getTextContent());

    }

    public boolean oidIsAnyMissing2(org.w3c.dom.Element p) {

	if (p.hasAttribute("id-organisation")) {
	    String[] userValue = p.getAttribute("id-organisation").split(" ");
	    for (int i = 0; i < userValue.length; i++) {
		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue[i])) == null) {
		    return true;
		}
	    }
	}
	return false;
    }
}

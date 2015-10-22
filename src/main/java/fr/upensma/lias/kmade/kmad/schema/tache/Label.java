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

import java.awt.Color;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public class Label implements Entity {

    private static final long serialVersionUID = -872813991969377025L;

    public Oid oid = null;

    private String name = "";

    private String description;

    private Color color = Color.WHITE;

    private boolean isVisible;

    private boolean isColorVisible;

    private ArrayList<Task> invTacheList = new ArrayList<Task>();

    public Label() {
	this.name = "";
	this.description = "";
	this.isVisible = true;
	this.color = Color.WHITE;
	this.isColorVisible = true;
    }

    public void addReverseTask(Task p) {
	invTacheList.add(p);
    }

    public void removeInversteTask(Task p) {
	invTacheList.remove(p);
    }

    public void delete() {
	for (int i = 0; i < invTacheList.size(); i++) {
	    Task t = invTacheList.get(i);
	    t.removeLabel();
	}
	InterfaceExpressJava.remove(oid);
    }

    public void affDelete() {
	InterfaceExpressJava.getGestionWarning().addMessage(oid, 13);
	for (int i = 0; i < invTacheList.size(); i++) {
	    Task t = invTacheList.get(i);
	    InterfaceExpressJava.getGestionWarning().addMessage(
		    oid,
		    13,
		    ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \""
			    + t.getName() + "\"");
	}
    }

    public void setDescription(String p) {
	this.description = p;
    }

    public String getDescription() {
	return description;
    }

    public void setColor(Color p) {
	this.color = p;
    }

    public Color getColor() {
	return color;
    }

    public boolean isVisible() {
	return this.isVisible;
    }

    public void setVisible(boolean p) {
	this.isVisible = p;
    }

    public boolean isColorVisible() {
	return this.isColorVisible;
    }

    public void setColorVisible(boolean p) {
	this.isColorVisible = p;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    public String getName() {
	return name;
    }

    public void setName(String n) {
	name = n;
    }

    public static boolean isUniqueName(String s) {
	Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity(
		ExpressConstant.CORE_PACKAGE, ExpressConstant.LABEL_CLASS);
	for (int i = 0; i < objAbs.length; i++) {
	    Label obj = (Label) objAbs[i];
	    if (s.equalsIgnoreCase(obj.getName())) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	Element racine = doc.createElement("label");
	racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "."
		+ ExpressConstant.LABEL_CLASS);
	racine.setAttribute("idkmad", oid.get());

	Element currentElement = doc.createElement("label-name");
	currentElement.setTextContent(this.getName());
	racine.appendChild(currentElement);

	if (!this.getDescription().equals("")) {
	    currentElement = doc.createElement("label-description");
	    currentElement.setTextContent(this.getDescription());
	    racine.appendChild(currentElement);
	}

	if (!this.color.equals(Color.WHITE)) {
	    currentElement = doc.createElement("label-color");

	    Element currentColorDetail = doc.createElement("label-color-red");
	    currentColorDetail.setTextContent(Integer.toString(this.getColor()
		    .getRed()));
	    currentElement.appendChild(currentColorDetail);

	    currentColorDetail = doc.createElement("label-color-green");
	    currentColorDetail.setTextContent(Integer.toString(this.getColor()
		    .getGreen()));
	    currentElement.appendChild(currentColorDetail);

	    currentColorDetail = doc.createElement("label-color-blue");
	    currentColorDetail.setTextContent(Integer.toString(this.getColor()
		    .getBlue()));
	    currentElement.appendChild(currentColorDetail);

	    racine.appendChild(currentElement);
	}

	currentElement = doc.createElement("label-visible");
	currentElement.setTextContent(Boolean.toString(this.isVisible));
	racine.appendChild(currentElement);

	currentElement = doc.createElement("label-colorvisible");
	currentElement.setTextContent(Boolean.toString(this.isColorVisible));
	racine.appendChild(currentElement);

	return racine;
    }

    public boolean oidIsAnyMissing(Element p) throws Exception {
	return false;
    }

    public void createObjectFromXMLElement(Element p) throws Exception {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("label-name");
	if (nodeList.item(0) != null)
	    this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("label-description");
	if (nodeList.item(0) != null)
	    this.description = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("label-color");
	int red = 255;
	int green = 255;
	int blue = 255;
	if (nodeList.getLength() != 0) {
	    NodeList nodeListEvent = nodeList.item(0).getChildNodes();

	    for (int i = 0; i < nodeListEvent.getLength(); i++) {
		if (nodeListEvent.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    Node node = nodeListEvent.item(i);
		    if (node.getNodeName().equals("label-color-red")) {
			KMADEHistoryMessageManager.printlnMessage(node
				.getTextContent());
			try {
			    red = Integer.parseInt(node.getTextContent());
			    KMADEHistoryMessageManager.printlnMessage(node
				    .getTextContent());
			} catch (Exception e) {
			    red = 255;
			}
		    } else if (node.getNodeName().equals("label-color-green")) {
			try {
			    green = Integer.parseInt(node.getTextContent());
			} catch (Exception e) {
			    green = 255;
			}
		    } else if (node.getNodeName().equals("label-color-blue")) {
			try {
			    blue = Integer.parseInt(node.getTextContent());
			} catch (Exception e) {
			    blue = 255;
			}
		    }
		}
	    }
	}
	this.color = new Color(red, green, blue);

	nodeList = p.getElementsByTagName("label-visible");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.isVisible = Boolean.parseBoolean(nodeList.item(0)
		    .getTextContent());

	nodeList = p.getElementsByTagName("label-colorvisible");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.isColorVisible = Boolean.parseBoolean(nodeList.item(0)
		    .getTextContent());
    }

    public String toSPF() {
	return oid.get() + "=Label('" + name + "','" + description + "','"
		+ this.getColor().getRed() + "','" + this.getColor().getGreen()
		+ "','" + this.getColor().getBlue() + "',." + this.isVisible
		+ ".);";
    }

    public static String propositionNom(String n) {
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

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("label-name");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.name = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("label-description");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.description = nodeList.item(0).getTextContent();

	nodeList = p.getElementsByTagName("label-color");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	int red = 255;
	int green = 255;
	int blue = 255;
	if (nodeList.getLength() != 0) {
	    NodeList nodeListEvent = nodeList.item(0).getChildNodes();

	    for (int i = 0; i < nodeListEvent.getLength(); i++) {
		if (nodeListEvent.item(i).getNodeType() == Element.ELEMENT_NODE) {
		    Node node = nodeListEvent.item(i);
		    if (node.getNodeName().equals("label-color-red")) {
			KMADEHistoryMessageManager.printlnMessage(node
				.getTextContent());
			try {
			    red = Integer.parseInt(node.getTextContent());
			    KMADEHistoryMessageManager.printlnMessage(node
				    .getTextContent());
			} catch (Exception e) {
			    red = 255;
			}
		    } else if (node.getNodeName().equals("label-color-green")) {
			try {
			    green = Integer.parseInt(node.getTextContent());
			} catch (Exception e) {
			    green = 255;
			}
		    } else if (node.getNodeName().equals("label-color-blue")) {
			try {
			    blue = Integer.parseInt(node.getTextContent());
			} catch (Exception e) {
			    blue = 255;
			}
		    }
		}
	    }
	}
	this.color = new Color(red, green, blue);

	nodeList = p.getElementsByTagName("label-visible");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.isVisible = Boolean.parseBoolean(nodeList.item(0)
		    .getTextContent());

	nodeList = p.getElementsByTagName("label-colorvisible");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    nodeList = null;
	}
	if (nodeList.item(0) != null)
	    this.isColorVisible = Boolean.parseBoolean(nodeList.item(0)
		    .getTextContent());
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	return false;
    }
}

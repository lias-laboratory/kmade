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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Vincent LUCQUIAUD 
 * @author Mickael BARON
 */
public class Point implements Entity {

    private static final long serialVersionUID = -6577946008177627261L;

    public Oid oid;

    private Integer x = null;

    private Integer y = null;

    public Point() {
    }

    public Point(Integer x, Integer y, Oid oid) {
	this.x = x;
	this.y = y;
	this.oid = oid;
    }

    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
	return false;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadPointX = p.getElementsByTagName("point-x");
	this.x = new Integer(kmadPointX.item(0).getTextContent());
	NodeList kmadPointY = p.getElementsByTagName("point-y");
	this.y = new Integer(kmadPointY.item(0).getTextContent());
    }

    public String toSPF() {
	return (oid.get() + "=" + "Point" + "(" + x + "," + y + ");");
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return oid;
    }

    public String getName() {
	return "";
    }

    /**
     * @return Returns the x.
     */
    public Integer getX() {
	return x;
    }

    /**
     * @param x
     *            The x to set.
     */
    public void setX(Integer x) {
	this.x = x;
    }

    /**
     * @return Returns the y.
     */
    public Integer getY() {
	return y;
    }

    /**
     * @param y
     *            The y to set.
     */
    public void setY(Integer y) {
	this.y = y;
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	Element racine = doc.createElement("point");
	racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "."
		+ ExpressConstant.POINT_CLASS);
	racine.setAttribute("idkmad", oid.get());

	Element kmadPointx = doc.createElement("point-x");
	kmadPointx.setTextContent(this.getX().toString());
	racine.appendChild(kmadPointx);

	Element kmadPointy = doc.createElement("point-y");
	kmadPointy.setTextContent(this.getY().toString());
	racine.appendChild(kmadPointy);

	return racine;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList kmadPointX = p.getElementsByTagName("point-x");
	if (kmadPointX.item(0).getParentNode() != p) {
	    kmadPointX = null;
	}
	this.x = new Integer(kmadPointX.item(0).getTextContent());
	NodeList kmadPointY = p.getElementsByTagName("point-y");
	if (kmadPointY.item(0).getParentNode() != p) {
	    kmadPointY = null;
	}
	this.y = new Integer(kmadPointY.item(0).getTextContent());
    }

    @Override
    public boolean oidIsAnyMissing2(Element p) throws Exception {
	return false;
    }
}
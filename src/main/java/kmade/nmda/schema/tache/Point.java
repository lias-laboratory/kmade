package kmade.nmda.schema.tache;

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
 * @authors Vincent Lucquiaud and Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class Point implements Entity {

    private static final long serialVersionUID = -6577946008177627261L;

    public Oid oid;

    public Integer x = null;

    public Integer y = null;

    public Point() {
    }

    public Point(Integer x, Integer y, Oid oid) {
        this.x = x;
        this.y = y;
        this.oid = oid;
    }

    public org.w3c.dom.Element toXML(Document doc) {        
        Element racine = doc.createElement("point");
        racine.setAttribute("classkmad", "tache.Point");
        racine.setAttribute("idkmad", oid.get());
        
        Element kmadPointx = doc.createElement("point-x");
        kmadPointx.setTextContent(this.getX().toString());
        racine.appendChild(kmadPointx);
        
        Element kmadPointy = doc.createElement("point-y");
        kmadPointy.setTextContent(this.getY().toString());
        racine.appendChild(kmadPointy);
        
        return racine;
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
     * @param x The x to set.
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
     * @param y The y to set.
     */
    public void setY(Integer y) {
        this.y = y;
    }
}
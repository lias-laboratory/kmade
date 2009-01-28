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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class Media implements Entity {

	private static final long serialVersionUID = -947088115441464967L;

	public Oid oid = null;
	
	private boolean isExisting = false;
	
	private String fileName = "";
	
	private String path = "";
	
	private int startMark = -1;
	
	private int finishMark = -1;
	
	public Media() {
		this.fileName = "";
		this.path = "";
		this.startMark = -1;
		this.finishMark = -1;
		this.isExisting = false;
	}
	
	public String getName() {
		return fileName;
	}

	public Oid getOid() {
		return oid;
	}

	public void setOid(Oid oid) {
		this.oid = oid;		
	}

	public String toSPF() {
        return oid.get() + "=Media('" + fileName + "','" + path + "','" + startMark + "','" + this.finishMark + "',." + this.isExisting + ".);"; 
	}

	public void createObjectFromXMLElement(Element p) throws Exception {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList nodeList = p.getElementsByTagName("media-existing");
        if (nodeList.item(0) != null)
            this.isExisting = Boolean.parseBoolean(nodeList.item(0).getTextContent()); 
        
        nodeList = p.getElementsByTagName("media-filename");
        if (nodeList.item(0) != null)
            this.fileName = nodeList.item(0).getTextContent();
        
        nodeList = p.getElementsByTagName("media-path");
        if (nodeList.item(0) != null)
            this.path = nodeList.item(0).getTextContent();
        
        nodeList = p.getElementsByTagName("media-startmark");
        if (nodeList.item(0) != null) {
        	try {
        		this.startMark = Integer.parseInt(nodeList.item(0).getTextContent());
        	} catch(NumberFormatException e) {
        		this.startMark = -1;
        	}
        }
        
        nodeList = p.getElementsByTagName("media-finishmark");
        if (nodeList.item(0) != null) {
        	try {
        		this.finishMark = Integer.parseInt(nodeList.item(0).getTextContent());
        	} catch(NumberFormatException e) {
        		this.startMark = -1;
        	}
        }              
	}

	public boolean oidIsAnyMissing(Element p) throws Exception {
		return false;
	}

	public Element toXML(Document doc) throws Exception {
        Element racine = doc.createElement("media");
        racine.setAttribute("classkmad", "tache.Media");
        racine.setAttribute("idkmad", oid.get());

    	Element currentElement = doc.createElement("media-existing");
    	currentElement.setTextContent(Boolean.toString(this.isExisting));
    	racine.appendChild(currentElement);
        
        if (this.isExisting) {
        	currentElement = doc.createElement("media-filename");
        	currentElement.setTextContent(this.fileName);
        	racine.appendChild(currentElement);
        
        	if (!this.path.equals("")) {
        		currentElement = doc.createElement("media-path");
        		currentElement.setTextContent(this.path);
        		racine.appendChild(currentElement);
        	}

        	if (this.startMark != -1) {
        		currentElement = doc.createElement("media-startmark");
        		currentElement.setTextContent(Integer.toString(this.startMark));
        		racine.appendChild(currentElement);        		
        	}

        	if (this.finishMark != -1) {
        		currentElement = doc.createElement("media-finishmark");
        		currentElement.setTextContent(Integer.toString(this.finishMark));
        		racine.appendChild(currentElement);        		        		
        	}
        }
                
        return racine;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFinishMark() {
		return finishMark;
	}

	public void setFinishMark(int finishMark) {
		this.finishMark = finishMark;
	}

	public boolean isExisting() {
		return isExisting;
	}

	public void setExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStartMark() {
		return startMark;
	}

	public void setStartMark(int startMark) {
		this.startMark = startMark;
	}
}

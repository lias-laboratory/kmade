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
 * @author Mickael BARON
 */
public class Media implements Entity {

	private static final long serialVersionUID = -947088115441464967L;

	public Oid oid = null;

	private boolean existing = false;

	private String fileName = "";

	private String path = "";

	private int startMark = -1;

	private int finishMark = -1;

	public Media() {
		this.fileName = "";
		this.path = "";
		this.startMark = -1;
		this.finishMark = -1;
		this.existing = false;
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
		return oid.get() + "=Media('" + fileName + "','" + path + "','" + startMark + "','" + this.finishMark + "',."
				+ this.existing + ".);";
	}

	public void createObjectFromXMLElement(Element p) throws Exception {
		this.oid = new Oid(p.getAttribute("idkmad"));

		NodeList nodeList = p.getElementsByTagName("media-existing");
		if (nodeList.item(0) != null)
			this.existing = Boolean.parseBoolean(nodeList.item(0).getTextContent());

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
			} catch (NumberFormatException e) {
				this.startMark = -1;
			}
		}

		nodeList = p.getElementsByTagName("media-finishmark");
		if (nodeList.item(0) != null) {
			try {
				this.finishMark = Integer.parseInt(nodeList.item(0).getTextContent());
			} catch (NumberFormatException e) {
				this.startMark = -1;
			}
		}
	}

	public boolean oidIsAnyMissing(Element p) throws Exception {
		return false;
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
		return existing;
	}

	public void setExisting(boolean isExisting) {
		this.existing = isExisting;
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

	@Override
	public Element toXML2(Document doc) throws Exception {
		// TODO Auto-generated method stub
		if (this.existing) {
			Element racine = doc.createElement("media");
			racine.setAttribute("classkmad", ExpressConstant.CORE_PACKAGE + "." + ExpressConstant.MEDIA_CLASS);
			racine.setAttribute("idkmad", oid.get());

			Element currentElement = doc.createElement("media-existing");
			currentElement.setTextContent(Boolean.toString(this.existing));
			racine.appendChild(currentElement);

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
			return racine;
		}

		else
			return null;

	}

	@Override
	public void createObjectFromXMLElement2(Element p) throws Exception {
		this.oid = new Oid(p.getAttribute("idkmad"));

		NodeList nodeList = p.getElementsByTagName("media-existing");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) != null)
			this.existing = Boolean.parseBoolean(nodeList.item(0).getTextContent());

		nodeList = p.getElementsByTagName("media-filename");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) != null)
			this.fileName = nodeList.item(0).getTextContent();

		nodeList = p.getElementsByTagName("media-path");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) != null)
			this.path = nodeList.item(0).getTextContent();

		nodeList = p.getElementsByTagName("media-startmark");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) != null) {
			try {
				this.startMark = Integer.parseInt(nodeList.item(0).getTextContent());
			} catch (NumberFormatException e) {
				this.startMark = -1;
			}
		}

		nodeList = p.getElementsByTagName("media-finishmark");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) != null) {
			try {
				this.finishMark = Integer.parseInt(nodeList.item(0).getTextContent());
			} catch (NumberFormatException e) {
				this.startMark = -1;
			}
		}
	}

	@Override
	public boolean oidIsAnyMissing2(Element p) throws Exception {
		return false;
	}
}

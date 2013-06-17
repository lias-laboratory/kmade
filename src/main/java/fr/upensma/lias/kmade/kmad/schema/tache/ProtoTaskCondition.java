package fr.upensma.lias.kmade.kmad.schema.tache;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

public class ProtoTaskCondition implements Entity{

	private static final long serialVersionUID = 988021647708284024L;

	public Oid oid = null;
	private String text;
	private StateCondition value;
	
	ProtoTaskCondition(){
		text ="";
		value = StateCondition.INDETERMINATE;
	}
	
	ProtoTaskCondition(String texte){
		this.text=texte;
		value = StateCondition.INDETERMINATE;
	}
	
	ProtoTaskCondition(String texte, StateCondition value){
		this.text=texte;
		this.value = value;
	}
	
	/*public Element toXML(Document doc) throws Exception {
		Element racine = doc.createElement("prototaskcondition");
		racine.setAttribute("classkmad", "tache.ProtoTaskCondition");
		racine.setAttribute("idkmad", oid.get());

		Element kmadPTTcondition = doc.createElement("PTTcondition-name");
		kmadPTTcondition.setTextContent(this.getName());
		racine.appendChild(kmadPTTcondition);

		if (!this.getDescription().equals("")) {
		    Element kmadEventDescription = doc
			    .createElement("event-description");
		    kmadEventDescription.setTextContent(this.getDescription());
		    racine.appendChild(kmadEventDescription);
	}
*/
	@Override
	public Element toXML2(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean oidIsAnyMissing(Element p) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean oidIsAnyMissing2(Element p) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createObjectFromXMLElement(Element p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createObjectFromXMLElement2(Element p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toSPF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOid(Oid oid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oid getOid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StateCondition getValue() {
		return value;
	}

	public void setValue(StateCondition value) {
		this.value = value;
	}

	
}

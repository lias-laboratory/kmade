package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
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
import fr.upensma.lias.kmade.kmad.schema.expression.EqualOperator;

public class ProtoTaskCondition implements Entity{

	private static final long serialVersionUID = 988021647708284024L;

	public Oid oid =null;
	private String text;
	private StateCondition defaultValue;
	private StateCondition currentValue;

	public ProtoTaskCondition(){
		text ="";
		defaultValue = StateCondition.INDETERMINATE;
		currentValue = StateCondition.INDETERMINATE;
	}

	public ProtoTaskCondition(String texte){
		this.text=texte;
		defaultValue = StateCondition.INDETERMINATE;
		currentValue = StateCondition.INDETERMINATE;
	}

	public ProtoTaskCondition(String texte, String value){
		this.text=texte;
		setDefaultValue(value);
	}

	public ProtoTaskCondition(String texte, StateCondition value){
		this.text=texte;
		this.defaultValue = value;
		this.currentValue = this.defaultValue;
	}

	public boolean equals(ProtoTaskCondition p){
		if(p.oid!=null && this.oid!=null)
			return p.oid.get().equals(this.oid.get());
		else if(p.oid==null && this.oid==null)
			return true;
		else
			return false;
	}
	
	public Element toXML(Document doc) throws Exception {
		Element racine = doc.createElement("prototaskcondition");
		racine.setAttribute("classkmad", "tache.ProtoTaskCondition");
		racine.setAttribute("idkmad", oid.get());

		Element kmadPTTcondition = doc.createElement("PTTcondition-description");
		kmadPTTcondition.setTextContent(this.getDescription());
		racine.appendChild(kmadPTTcondition);
		Element kmadPTTconditionvalue = doc.createElement("PTTcondition-value");
		kmadPTTconditionvalue.setTextContent(this.getDefaultValue().toString());
		racine.appendChild(kmadPTTconditionvalue);
		return racine;

	}

	@Override
	public Element toXML2(Document doc) throws Exception {
		Element racine = doc.createElement("prototaskcondition");
		racine.setAttribute("classkmad", "metaobjet.ProtoTaskCondition");
		racine.setAttribute("idkmad", oid.get());

		Element kmadPTTcondition = doc.createElement("PTTcondition-description");
		kmadPTTcondition.setTextContent(this.getDescription());
		racine.appendChild(kmadPTTcondition);
		Element kmadPTTconditionvalue = doc.createElement("PTTcondition-value");
		kmadPTTconditionvalue.setTextContent(this.getDefaultValue().toString());
		racine.appendChild(kmadPTTconditionvalue);
		return racine;
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

		this.oid = new Oid(p.getAttribute("idkmad"));

		NodeList nodeList = p.getElementsByTagName("PTTcondition-description");
		if (nodeList.item(0) != null)
			this.text = nodeList.item(0).getTextContent();

		nodeList = p.getElementsByTagName("PTTcondition-value");
		if (nodeList.item(0) != null)
			setDefaultValue(nodeList.item(0).getTextContent());

	}

	@Override
	public void createObjectFromXMLElement2(Element p) throws Exception {
		this.oid = new Oid(p.getAttribute("idkmad"));

		NodeList nodeList = p.getElementsByTagName("PTTcondition-description");
		if (nodeList.item(0) != null)
			this.text = nodeList.item(0).getTextContent();

		nodeList = p.getElementsByTagName("PTTcondition-value");
		if (nodeList.item(0) != null)
			setDefaultValue(nodeList.item(0).getTextContent());
	}

	@Override
	public String toSPF() {
		return oid + " : " + text +", "+ defaultValue; 
	}

	@Override
	public void setOid(Oid oid) {
		this.oid = oid;		
	}

	@Override
	public Oid getOid() {
		return oid;
	}


	public String getDescription() {
		return text;
	}

	public void setDescription(String text) {
		this.text = text;
	}

	public StateCondition getDefaultValue() {
		return defaultValue;
	}

	public String getDefaultValueText() {
		if(defaultValue == StateCondition.TRUE){
			return ExpressConstant.TRUE;
		}else if(defaultValue == StateCondition.FALSE){
			return ExpressConstant.FALSE;
		}	if(defaultValue == StateCondition.INDETERMINATE){
			return ExpressConstant.INDETERMINATE;
		}else{
			return null;
		}

	}
	public void setDefaultValue(StateCondition value) {
		this.defaultValue = value;
	}

	public void affDelete() {
		InterfaceExpressJava.getGestionWarning().addMessage(oid, 17);

	}

	/* verify if the condition is not attach before delete */
	public void delete() {
		InterfaceExpressJava.remove(oid);

	}

	public void setDefaultValue(String defaultValue) {
		if(defaultValue.equals(ExpressConstant.TRUE)){
			this.defaultValue = StateCondition.TRUE;
		}else if(defaultValue.equals(ExpressConstant.FALSE)){
			this.defaultValue = StateCondition.FALSE;
		}	if(defaultValue.equals(ExpressConstant.INDETERMINATE)){
			this.defaultValue = StateCondition.INDETERMINATE;
		}else if (defaultValue.equals("TRUE")){
			this.defaultValue = StateCondition.TRUE;
		}else if(defaultValue.equals("FALSE")){
			this.defaultValue = StateCondition.FALSE;
		}else if(defaultValue.equals("INDETERMINATE")){
			this.defaultValue = StateCondition.INDETERMINATE;
		}else{}
		this.setCurrentValue(getDefaultValue());
	}

	@Override
	public String getName() {
		return getDescription();
	}

	public Object[][] getProtoTaskConditionIntoTab() {
		Object[][] res = new Object[1][3];
		if(oid!=null){
		res[0][0] = this.getDescription();
		res[0][1] = this.getDefaultValueText();
		res[0][2] = this.getOid().get();
		return res;
		}
		return null;
		
	}

	public StateCondition getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(StateCondition currentValue) {
		this.currentValue = currentValue;
	}


	
}

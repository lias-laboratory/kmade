package fr.upensma.lias.kmade.kmad.schema.tache;

import java.io.Serializable;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;

public class ProtoTaskConditionExpression implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 347909019435362985L;

	private ProtoTaskCondition value = new ProtoTaskCondition();

	public ProtoTaskConditionExpression(){
		
	}

	public ProtoTaskCondition getValue() {
		return value;
	}

	public void setValue(ProtoTaskCondition value) {
		this.value = value;
	}

	public void removeCondition() {
		this.value = new ProtoTaskCondition();
	}




}

package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.util.HashMap;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;

public class ItemPrototask {
	private HashMap<Oid, StateExecution> taskStates = new HashMap<Oid, StateExecution>();

	private HashMap<ProtoTaskCondition, StateCondition> condi = new HashMap<ProtoTaskCondition, StateCondition>();
	
	public ItemPrototask(HashMap<Oid, StateExecution> tasks , HashMap<ProtoTaskCondition, StateCondition> conditions){
		HashMap<Oid, StateExecution> clone = (HashMap<Oid, StateExecution>) tasks.clone();
		this.taskStates = clone;
		HashMap<ProtoTaskCondition, StateCondition> clone2 = (HashMap<ProtoTaskCondition, StateCondition>) conditions.clone();
		this.condi = clone2;
	}
	

	public HashMap<ProtoTaskCondition, StateCondition> getConditions() {
		return condi;
	}


	public HashMap<Oid, StateExecution> getTaskStates() {
		return taskStates;
	}

}

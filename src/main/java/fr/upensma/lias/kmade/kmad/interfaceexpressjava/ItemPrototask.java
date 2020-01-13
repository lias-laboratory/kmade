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
package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.util.HashMap;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;

/**
 * @author Thomas LACHAUME
 */
public class ItemPrototask {
	private HashMap<Oid, StateExecution> taskStates = new HashMap<Oid, StateExecution>();

	private HashMap<ProtoTaskCondition, StateCondition> condi = new HashMap<ProtoTaskCondition, StateCondition>();

	@SuppressWarnings("unchecked")
	public ItemPrototask(HashMap<Oid, StateExecution> tasks, HashMap<ProtoTaskCondition, StateCondition> conditions) {
		HashMap<Oid, StateExecution> clone = (HashMap<Oid, StateExecution>) tasks.clone();
		this.taskStates = clone;
		HashMap<ProtoTaskCondition, StateCondition> clone2 = (HashMap<ProtoTaskCondition, StateCondition>) conditions
				.clone();
		this.condi = clone2;
	}

	public HashMap<ProtoTaskCondition, StateCondition> getConditions() {
		return condi;
	}

	public HashMap<Oid, StateExecution> getTaskStates() {
		return taskStates;
	}

}

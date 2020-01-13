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
package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import java.util.ArrayList;
import java.util.HashMap;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ItemPrototask;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.UndoRedoPrototask;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressCondition;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;

/**
 * @author Thomas LACHAUME
 */
public class UndoProtoTask {

	private static void getTaskState(HashMap<Oid, StateExecution> item) {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		for (Task tache : root) {
			item.put(tache.getOid(), tache.getStateExecution());
			getTaskStateRec(item, tache);
		}
	}

	private static void getTaskStateRec(HashMap<Oid, StateExecution> it, Task t) {
		for (Task child : t.getChildren()) {
			it.put(child.getOid(), child.getStateExecution());
			getTaskStateRec(it, child);
		}
	}

	public static void push() {
		HashMap<Oid, StateExecution> item = new HashMap<Oid, StateExecution>();
		getTaskState(item);
		HashMap<ProtoTaskCondition, StateCondition> condi = new HashMap<ProtoTaskCondition, StateCondition>();
		ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
		for (ProtoTaskCondition protoTaskCondition : conditions) {
			condi.put(protoTaskCondition, protoTaskCondition.getCurrentValue());
		}

		ItemPrototask iP = new ItemPrototask(item, condi);
		UndoRedoPrototask.push(iP);
	}

	public static ItemPrototask pull() {
		return UndoRedoPrototask.pull();

	}

}

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

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;

/**
 * @author Thomas LACHAUME
 */
public class UndoRedoPrototask {

	private static ArrayList<ItemPrototask> stack = new ArrayList<ItemPrototask>();

	UndoRedoPrototask() {

	}

	public static void push(ItemPrototask item) {
		stack.add(item);
	}

	public static ItemPrototask pull() {
		if (size() > 0) {
			ItemPrototask item = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			updateModel();
			return item;
		}
		return null;

	}

	private static void updateModel() {
		for (ItemPrototask itemPrototask : stack) {
			// update condition
			Map<ProtoTaskCondition, StateCondition> condi = itemPrototask.getConditions();
			Set<ProtoTaskCondition> keys = condi.keySet();
			for (Object k : keys) {
				ProtoTaskCondition myCondi = (ProtoTaskCondition) k;
				myCondi.setCurrentValue(condi.get(k));
			}
			// update states
			Map<Oid, StateExecution> HMState = itemPrototask.getTaskStates();
			Set<Oid> cle = HMState.keySet();
			for (Object object : cle) {
				Oid o = (Oid) object;
				Task t = ExpressTask.getTaskFromOid(o.get());
				t.setStateExecution(HMState.get(object));
			}
		}
	}

	public static boolean empty() {
		return stack.size() == 0;
	}

	public static int size() {
		return stack.size();
	}

	public static void init() {
		stack.clear();
	}

}

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
package fr.upensma.lias.kmade.tool.coreadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;

/**
 * @author Mickael BARON
 */
public final class ExpressCondition {
	public static String createCondition() {
		Oid oidEvent = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.PROTOTASKCONDITON_CLASS);
		return (oidEvent.get());
	}




	/**
	 * @param newLabelObject
	 * @param value
	 * @return
	 */
	public static String setConditionDescription(String oid, String value) {
		ProtoTaskCondition m = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDescription(value);
		return m.getDescription();
	}

	public static ArrayList<ProtoTaskCondition> getConditions() {
		ArrayList<ProtoTaskCondition> lst = new ArrayList<ProtoTaskCondition>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.PROTOTASKCONDITON_CLASS);
		for (int i = 0; i < objs.length; i++) {
			ProtoTaskCondition obj = (ProtoTaskCondition) objs[i];
			lst.add(obj);
		}
		return lst;
	}

	public static ArrayList<String> getConditionsDescriptionList() {
		ArrayList<String> lst = new ArrayList<String>();
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.PROTOTASKCONDITON_CLASS);
		for (int i = 0; i < objs.length; i++) {
			ProtoTaskCondition obj = (ProtoTaskCondition) objs[i];
			lst.add(obj.getDescription());
		}
		return lst;
	}

	public static String[] getConditionsDescriptionArray() {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.PROTOTASKCONDITON_CLASS);
		String[] array = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			ProtoTaskCondition obj = (ProtoTaskCondition) objs[i];
			array[i] = obj.getDescription();
		}
		return array;
	}

	public static ProtoTaskCondition stringToCondition(String s) {
		Object[] objs = InterfaceExpressJava.prendreAllOidOfEntity(ExpressConstant.METAOBJECT_PACKAGE,
				ExpressConstant.PROTOTASKCONDITON_CLASS);
		for (int i = 0; i < objs.length; i++) {
			ProtoTaskCondition obj = (ProtoTaskCondition) objs[i];
			if (obj.getDescription().equals(s)) {
				return obj;
			}
		}
		return null;
	}

	public static boolean affRemoveCondition(String oid) {
		ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditionsIntoTasks();
		boolean in = false;
		for (ProtoTaskCondition protoTaskCondition : conditions) {
			if(protoTaskCondition.getOid().get().equals(oid)){
				in = true;
			}
		}
		if(!in){
			ProtoTaskCondition m = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oid));
			m.affDelete();
			return true;
		}else{
			InterfaceExpressJava.getGestionWarning().addMessage(new Oid(oid), 18, "\n KMC la condition est présente dans une tâche elle ne peut être supprimée");
		return false;
		}
	}

	public static ArrayList<ProtoTaskCondition> getConditionsIntoTasks() {
		ArrayList<Task> tasks = ExpressTask.getTasksFromExpress();
		ArrayList<ProtoTaskCondition> res = new ArrayList<ProtoTaskCondition>();
		ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
		for (ProtoTaskCondition protoTaskCondition : conditions) {
			for (Task t : tasks) {
				if(t.getPreExpression().getProtoTaskConditionExpression().getValue().equals(protoTaskCondition) || (t.getIterExpression().getProtoTaskConditionExpression().getValue().equals(protoTaskCondition))){
					res.add(protoTaskCondition);
					break;
				}
			}
		}


		return res;
	}




	public static void removeCondition(String oidAct) {

		ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditionsIntoTasks();
		boolean in = false;
		for (ProtoTaskCondition protoTaskCondition : conditions) {
			if(protoTaskCondition.getOid().get().equals(oidAct)){
				in = true;
			}
		}
		if(!in){
			ProtoTaskCondition a = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oidAct));
			a.delete();
		}else{
		}

	}




	public static String setConditionDefaultValue(String oid, String defaultValue) {
		ProtoTaskCondition m = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oid));
		m.setDefaultValue(defaultValue);
		return m.getDefaultValue().toString();

	}




	public static void setPreCondition(String oidTask, String oidCondition) {
		Task t = (Task) InterfaceExpressJava.prendre(new Oid(oidTask));
		ProtoTaskCondition c = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oidCondition));
		t.getPreExpression().getProtoTaskConditionExpression().setValue(c);

	}


	public static void setIterCondition(String oidTask, String oidCondition) {
		Task t = (Task) InterfaceExpressJava.prendre(new Oid(oidTask));
		ProtoTaskCondition c = (ProtoTaskCondition) InterfaceExpressJava.prendre(new Oid(oidCondition));
		t.getIterExpression().getProtoTaskConditionExpression().setValue(c);

	}


}

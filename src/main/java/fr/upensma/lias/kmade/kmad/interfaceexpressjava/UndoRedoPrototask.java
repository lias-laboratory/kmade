package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;

public class UndoRedoPrototask {

		private static ArrayList<ItemPrototask> stack = new ArrayList<ItemPrototask>();
		
		UndoRedoPrototask(){
			
		}
		
		public static void push(ItemPrototask item){
			stack.add(item);
		}
		
		public static ItemPrototask pull(){
			if(size()>0){
			ItemPrototask item = stack.get(stack.size()-1);
			System.out.println("size =" + stack.size());
			stack.remove(stack.size()-1);
			System.out.println("after size" + stack.size());
			updateModel();
			return item;
			}
			return null;
			
		}
		

		private static void updateModel(){
			System.out.println(size());
			for (ItemPrototask itemPrototask : stack) {
				//update condition
				HashMap<ProtoTaskCondition, StateCondition> condi = itemPrototask.getConditions();
				Set keys =  condi.keySet();
				for (Object k : keys) {
					ProtoTaskCondition myCondi = (ProtoTaskCondition)k;
					myCondi.setCurrentValue(condi.get(k));
				}
				//update states
			HashMap<Oid, StateExecution> HMState = itemPrototask.getTaskStates();
				Set cle = HMState.keySet();
				for (Object object : cle) {
					Oid o = (Oid)object;
					Task t = ExpressTask.getTaskFromOid(o.get());
					t.setStateExecution(HMState.get(object));
				}
			}
		}
		public static boolean empty(){
			return stack.size()==0;
		}
		
		public static int size(){
			return stack.size();
		}
		
		public static void init(){
			stack.clear();
		}
		
}

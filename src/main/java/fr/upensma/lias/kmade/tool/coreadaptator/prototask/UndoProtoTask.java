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

public class UndoProtoTask {

    private static void getTaskState(HashMap<Oid, StateExecution> item){
    	ArrayList<Task> root = ExpressTask.getRootTasks();
    	for (Task tache : root) {
    		item.put(tache.getOid(), tache.getStateExecution());
    		getTaskStateRec(item,tache);
    	}
    }
    
        private static void getTaskStateRec(HashMap<Oid, StateExecution> it, Task t) {
              	for (Task child : t.getChildren()) {
              		it.put(child.getOid(), child.getStateExecution());
            		getTaskStateRec(it,child);
              	}
        }

	public static void push(){
	HashMap<Oid, StateExecution> item = new HashMap<Oid, StateExecution>();
	getTaskState(item);
	HashMap<ProtoTaskCondition, StateCondition> condi = new HashMap<ProtoTaskCondition, StateCondition>();
	   ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();	
	   for (ProtoTaskCondition protoTaskCondition : conditions) {
		   		condi.put(protoTaskCondition, protoTaskCondition.getCurrentValue());
	}
	   
	   ItemPrototask iP= new ItemPrototask(item,condi);
	   UndoRedoPrototask.push(iP);
	}
	
	public static  ItemPrototask pull(){
		return UndoRedoPrototask.pull();
		
	}
	   
}

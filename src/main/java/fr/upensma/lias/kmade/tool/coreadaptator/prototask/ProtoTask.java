package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;

public interface ProtoTask {

	
		
		
		/**
		 * do the Task t, the task have to be available
		 * @param t
		 */
		public void doTask(Task t) throws ProtoTaskException;
		
		
		/**
		 * cancel the task t
		 * @param t
		 */
		public void cancelTask(Task t)throws ProtoTaskException;
		
		
		public void suspendTask(Task t)throws ProtoTaskException;
		
		public void changeCondition() throws ProtoTaskException;
		
		public void interruptTask() throws ProtoTaskException;
		
		public void endTask(Task t) throws ProtoTaskException;
		
		
		
		
}

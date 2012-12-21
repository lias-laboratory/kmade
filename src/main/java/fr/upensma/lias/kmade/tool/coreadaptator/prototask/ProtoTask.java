package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;

public interface ProtoTask {

	
		
		
		/**
		 * do the Task t, the task have to be available
		 * @param t
		 */
		public void doTask(Tache t) throws ProtoTaskException;
		
		
		/**
		 * cancel the task t
		 * @param t
		 */
		public void cancelTask(Tache t)throws ProtoTaskException;
		
		
		public void suspendTask(Tache t)throws ProtoTaskException;
		
		public void changeCondition() throws ProtoTaskException;
		
		public void interruptTask() throws ProtoTaskException;
		
		public void endTask(Tache t) throws ProtoTaskException;
		
		
		
		
}

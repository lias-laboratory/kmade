package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import java.util.ArrayList;

import fr.lri.swingstates.debug.Watcher;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.PreExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class Excution{

	public static void doTask(Task t) throws ProtoTaskException {
		if(t.getStateExecution()==StateExecution.ACTIVABLE || t.getStateExecution()==StateExecution.ACTIVE){  
			t.setStateExecution(StateExecution.ACTIVE);
			takeCareOfMyChildren(t);
			if(t.getMother()!=null)
				oneOfMyChildrenBecommeActive(t.getMother());

			System.out.println("do TASK eNd + " + t.getStateExecution());

		}else{
			throw new ProtoTaskException();
		}
	}





	public void cancelTask(Task t) throws ProtoTaskException {
		// TODO Auto-generated method stub

	}

	public void suspendTask(Task t) throws ProtoTaskException {
		// TODO Auto-generated method stub

	}


	/**
	 * @param t
	 */
	private static void oneOfMyChildrenBecommeActive(Task t) throws ProtoTaskException {

		//je regarde mon ordonnancement et je met à jours mes filles si besoin et je me met en attente si besoin
		Decomposition decompo = t.getOrdonnancement();
		ArrayList<Task> enfants ;
		switch (decompo) {
		case ALT:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ATTENTEFIN:
				case 	ATTENTEFINKO :
				case ACTIVE :
				case ATTENTETASK :
					break;
				default:
					fille.setStateExecution(StateExecution.PASSEE);
				}
			}
			break;
		case ELE:
			throw new ProtoTaskException();
		case ET:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					fille.setStateExecution(StateExecution.ATTENTETASK);
					break;
				default:
					break;
				}
			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			boolean canStartAChild = false;
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					canStartAChild = true;
					break;
				default: 
					break;
				}
			}
			if(!canStartAChild){
				t.setStateExecution(StateExecution.ATTENTETASK);
			}
			break;
		case SEQ:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					fille.setStateExecution(StateExecution.ATTENTETASK);
					break;
				default:
					break;
				}
			}
			break;
		default:
			break;
		}
	}


	private static void takeCareOfMyChildren(Task t) throws ProtoTaskException {
		Decomposition decompo = t.getOrdonnancement();
		ArrayList<Task> children;
		switch (decompo) {
		case ALT:
			children = t.getChildren();
			for (Task child : children) {
				if(child.getStateExecution()==null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch(child.getStateExecution()){
				case INACTIVE:
					iCanBeActivable(child);
					break;
				case FINISHED:
					waitEndTask(t);
					break;
				case ACTIVABLE:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case ATTENTETASK:
				case INACTIVABLE:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					break;
					//throw new ProtoTaskException();
				}

			}
			break;
		case ELE:
			waitEndTask(t);
			break;
		case ET:
			children = t.getChildren();
			boolean oneActivable = true;
			for (Task child : children) {
				if(child.getStateExecution()==null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch(child.getStateExecution()){
				case ATTENTETASK:
				case INACTIVE:
					iCanBeActivable(child);
					oneActivable = false;
					break;
				case FINISHED:
				case ACTIVABLE:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case INACTIVABLE:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					if(oneActivable){
						waitEndTask(t);
					}
				}

			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			children = t.getChildren();
			for (Task child : children) {
				if(child.getStateExecution()==null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch(child.getStateExecution()){

				case INACTIVE:
					iCanBeActivable(child);
					break;
				case FINISHED:
				case ATTENTETASK:
				case ACTIVABLE:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case INACTIVABLE:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					//	System.out.println(child.getName() + child.getStateExecution());
					//throw new ProtoTaskException();
					break;
				}

			}
			break;
		case SEQ:					
			children = t.getChildren();
			//permet de dire si la première tache non optionnel a été trouvé
			boolean first = false;
			for (Task child : children) {
				if(child.getStateExecution()==null)
					child.setStateExecution(StateExecution.INACTIVE);
				switch(child.getStateExecution()){

				case INACTIVE:
					if(!first){
						iCanBeActivable(child);
						if(!child.getFacultatif()){
							first = true;
						}
					}
					break;
				case FINISHED:
				case ATTENTETASK:
				case ACTIVABLE:
				case ACTIVE:
				case ATTENTEFIN:
				case ATTENTEFINKO:
				case INACTIVABLE:
				case PASSEE:
				case PASSIVE:
				case WAITEND:
				default:
					System.out.println(child.getName() + child.getStateExecution());
					break;
					//throw new ProtoTaskException();
				}

			}
			break;

		default:
			break;
		}
		children = t.getChildren();
		for (Task child : children) {
			if(child.getStateExecution()!=null)
				System.out.println("fils : " + child.getStateExecution());
		}
	}





	private static void iCanBeActivable(Task t) {
		//TODO vérifier la precondition
		//if the task has a condition
		if(t.getPreExpression().getDescription()!=null && !t.getPreExpression().getDescription().equals("")){
			t.getPreExpression();
			if(PreExpression.getState()==StateCondition.TRUE){
				t.setStateExecution(StateExecution.ACTIVABLE);
			}else{
				t.setStateExecution(StateExecution.INACTIVABLE);
			}


		}else{
			t.setStateExecution(StateExecution.ACTIVABLE);
		}
	}




	//
	private static void waitEndTask(Task t) {
		//faire la gestion de 
		System.out.println("waitend");

		t.setStateExecution(StateExecution.ATTENTEFIN);

	}





	public static void changeCondition(StateCondition state) throws ProtoTaskException {
		// TODO Auto-generated method stub

	}




	public static void  interruptTask() throws ProtoTaskException {
		// TODO Auto-generated method stub

	}





	public static void endTask(Task t) throws ProtoTaskException {
		t.setStateExecution(StateExecution.FINISHED);
		Task mother = t.getMother();
		if(mother!=null)
			oneOfMyChildrenBecomeFinish(mother);
		else{
			//TODO FIN SIMUL
			System.err.println("fin simu");
		}
	}

	private static void oneOfMyChildrenBecomeFinish(Task t) throws ProtoTaskException{
		Decomposition decompo = t.getOrdonnancement();
		ArrayList<Task> enfants ;
		switch (decompo) {
		case ALT:
			waitEndTask(t);
			break;
		case ELE:
			throw new ProtoTaskException();
		case ET:
			//t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			int counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
				case ATTENTETASK:
					if(!fille.getFacultatif()){
						counter++;
					}
					break;
				default:
					break;
				}
			}
			if(counter == 0){
				waitEndTask(t);
			}else{
				t.setStateExecution(StateExecution.ACTIVE);
				doTask(t);
			}
			break;
		case INCONNU:
			throw new ProtoTaskException();
		case PAR:
			boolean canStartAChild = false;
			enfants = t.getChildren();
			counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
					canStartAChild = true;
					if(fille.getFacultatif())
						counter++;
					break;
				case FINISHED:
					counter++;
				default: 
					break;
				}
			}
			if(counter==enfants.size()){
				waitEndTask(t);
			}else if(!canStartAChild){
				t.setStateExecution(StateExecution.ATTENTETASK);
			}else{
				doTask(t);
			}

			break;
		case SEQ:
			t.setStateExecution(StateExecution.ATTENTETASK);
			enfants = t.getChildren();
			counter = 0;
			for (Task fille : enfants) {
				switch (fille.getStateExecution()) {
				case ACTIVABLE:
				case INACTIVABLE:
				case INACTIVE:
				case ATTENTETASK:	
					if(!fille.getFacultatif()){
						counter++;
					}
					break;
				default:
					break;
				}
			}
			if(counter == 0){
				waitEndTask(t);
				takeCareOfMyChildren(t);
			}else{
				t.setStateExecution(StateExecution.ACTIVE);
				doTask(t);
			}
			break;
		default:
			break;
		}
	}





	public static void resetScenario() {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		setAllTaskState(StateExecution.INACTIVE);
		for (Task tache : root) {
			tache.setStateExecution(StateExecution.ACTIVABLE);
			try {
				Excution.doTask(tache);
			} catch (ProtoTaskException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private static void setAllTaskState(StateExecution state){
		ArrayList<Task> root = ExpressTask.getRootTasks();
		for (Task tache : root) {
			tache.setStateExecution(state);
			for(Task t : tache.getChildren()){
				setStateRecu(t,state);
			}
		}
	}





	private static void setStateRecu(Task t, StateExecution state) {
		t.setStateExecution(state);
		for(Task f : t.getChildren()){
			setStateRecu(f, state);
		}
		
	}



}

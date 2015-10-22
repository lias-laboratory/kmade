package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import fr.upensma.lias.kmade.kmad.schema.expression.ChoiceEnum;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;

public class TaskProto {

	private Task myTask;

	private StateExecution stateExecution;

	private ChoiceEnum IterationValue = ChoiceEnum.indeterminee;

	public Task getTask() {
		return myTask;
	}

	public void setTask(Task myTask) {
		this.myTask = myTask;
	}

	public StateExecution getStateExecution() {
		return stateExecution;
	}

	public void setStateExecution(StateExecution stateExecution) {
		this.stateExecution = stateExecution;
	}

	public ChoiceEnum getIterationValue() {
		return IterationValue;
	}

	public void setIterationValue(ChoiceEnum iterationValue) {
		IterationValue = iterationValue;
	}

	
}

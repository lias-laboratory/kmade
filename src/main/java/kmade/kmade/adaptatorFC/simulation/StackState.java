package kmade.kmade.adaptatorFC.simulation;

import kmade.nmda.schema.tache.StateSimulation;

public class StackState {
	private StateSimulation refStateSimulation;
	
	private int variant;
		
	public StackState(StateSimulation pss, int variant) {
		this.refStateSimulation = pss;
		this.variant = variant;
	}
	
	public StateSimulation getStateSimulation() {
		return this.refStateSimulation;
	}
	
	public int getVariant() {
		return variant;
	}
}

package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import fr.upensma.lias.kmade.kmad.schema.tache.StateSimulation;

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

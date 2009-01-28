package kmade.nmda.schema;

import java.util.ArrayList;

import kmade.nmda.schema.metaobjet.AttributConcret;
import kmade.nmda.schema.metaobjet.ObjetConcret;

public class KMADConcreteHistory {
	private ObjetConcret[] concreteObject = new ObjetConcret[0];
	
	public KMADConcreteHistory(Object[] co) {
		// Effectuer des clones.
		concreteObject = new ObjetConcret[co.length];
		for (int i = 0 ; i < co.length ; i++) {
			concreteObject[i] = (ObjetConcret)(((ObjetConcret)co[i]).clone());
    	}
	}
	
	public ObjetConcret[] getNewHistoryClone() {
		ObjetConcret[] newConcreteObject = new ObjetConcret[concreteObject.length];
		for (int i = 0 ; i < concreteObject.length ; i++) {
			newConcreteObject[i] = (ObjetConcret)(((ObjetConcret)concreteObject[i]).clone());
    	}
		return newConcreteObject;
	}
	
	public ArrayList<Oid> getConcreteOid() {
		ArrayList<Oid> temp = new ArrayList<Oid>();
		for (ObjetConcret occurrent : concreteObject) {
			for (AttributConcret accurrent : occurrent.getInverseListAttribut()) {
				temp.add(accurrent.getOid());
				temp.add(accurrent.getValue().getOid());
			}			
			temp.add(occurrent.getOid());
		}
		return temp;
	}
	
	public ObjetConcret[] getConcreteObject() {
		return concreteObject;
	}

	public void setConcreteObject(ObjetConcret[] concreteObject) {
		this.concreteObject = concreteObject;
	}
}

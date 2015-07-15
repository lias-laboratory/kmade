/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
package fr.upensma.lias.kmade.kmad.schema;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class KMADConcreteHistory {
    private ObjetConcret[] concreteObject = new ObjetConcret[0];

    public KMADConcreteHistory(Object[] co) {
	concreteObject = new ObjetConcret[co.length];
	for (int i = 0; i < co.length; i++) {
	    concreteObject[i] = (ObjetConcret) (((ObjetConcret) co[i]).clone());
	}
    }

    public ObjetConcret[] getNewHistoryClone() {
	ObjetConcret[] newConcreteObject = new ObjetConcret[concreteObject.length];
	for (int i = 0; i < concreteObject.length; i++) {
	    newConcreteObject[i] = (ObjetConcret) (((ObjetConcret) concreteObject[i])
		    .clone());
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

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
package fr.upensma.lias.kmade.tool.coreadaptator.simulation;

import java.util.TreeMap;

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class StackStateModel {
    private TreeMap<Oid, StackState> myStateSimulationCollection = new TreeMap<Oid, StackState>();

    private TokenSimulation refTokenSimulation;

    public StackStateModel(TreeMap<Oid, StackState> pStateSimulationCollection,
	    TokenSimulation pts) {
	myStateSimulationCollection = pStateSimulationCollection;
	this.refTokenSimulation = pts;
    }

    public void setStateSimulationList(
	    TreeMap<Oid, StackState> pStateSimulationCollection) {
	myStateSimulationCollection = pStateSimulationCollection;
    }

    public TreeMap<Oid, StackState> getStateSimulationList() {
	return myStateSimulationCollection;
    }

    public TokenSimulation getTokenSimulation() {
	return refTokenSimulation;
    }
}

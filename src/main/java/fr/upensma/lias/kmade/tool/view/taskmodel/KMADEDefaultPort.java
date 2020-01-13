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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.util.Iterator;

import org.jgraph.graph.DefaultPort;

/**
 * @author Mickael BARON
 */
public class KMADEDefaultPort extends DefaultPort {

	private static final long serialVersionUID = 188384865875540612L;

	private boolean isExpanded = true;

	private boolean isSimulationExpanded = true;

	// Mother port du bas et Son port du haut.
	private String identification;

	public KMADEDefaultPort(String p) {
		identification = p;
	}

	public boolean isMotherPort() {
		return identification.equals("mother");
	}

	public boolean isSonPort() {
		return identification.equals("son");
	}

	public KMADEDefaultGraphCell getTaskOwner() {
		return (KMADEDefaultGraphCell) this.getParent();
	}

	/**
	 * @return Returns the isExpanded.
	 */
	public boolean isExpanded() {
		return isExpanded;
	}

	public boolean isSimulationExpanded() {
		return isSimulationExpanded;
	}

	/**
	 * @param isExpanded The isExpanded to set.
	 */
	public void setSimulationExpanded(boolean pIsSimulationExpanded) {
		this.isSimulationExpanded = pIsSimulationExpanded;

		if (isMotherPort()) {
			Iterator<?> i = this.edges();
			while (i.hasNext()) {
				Object myObject = i.next();
				if (myObject instanceof KMADEDefaultEdge) {
					((KMADEDefaultEdge) myObject).setSimulationExpanded(pIsSimulationExpanded);
				}
			}
		} else {
			((KMADEDefaultGraphCell) this.getParent()).setSimulationExpanded(pIsSimulationExpanded);
		}
	}

	/**
	 * @param isExpanded The isExpanded to set.
	 */
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;

		if (isMotherPort()) {
			Iterator<?> i = this.edges();
			while (i.hasNext()) {
				Object myObject = i.next();
				if (myObject instanceof KMADEDefaultEdge) {
					((KMADEDefaultEdge) myObject).setExpanded(isExpanded);
				}
			}
		} else {
			((KMADEDefaultGraphCell) this.getParent()).setExpanded(isExpanded);
		}
	}

	/**
	 * @param dx
	 * @param dy
	 */
	public void setPoint(int dx, int dy) {
		if (isMotherPort()) {
			Iterator<?> i = this.edges();
			while (i.hasNext()) {
				Object myObject = i.next();
				if (myObject instanceof KMADEDefaultEdge) {
					((KMADEDefaultEdge) myObject).setPoint(dx, dy);
				}
			}
		} else {
			((KMADEDefaultGraphCell) this.getParent()).setDeltaPoint(dx, dy);
		}
	}
}
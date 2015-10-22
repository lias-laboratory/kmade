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

import java.util.Hashtable;
import java.util.Map;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

/**
 * @author Mickael BARON
 */
public class KMADEDefaultEdge extends DefaultEdge {

    private static final long serialVersionUID = 2073946250698010718L;

    private boolean isExpanded = true;

    private boolean isSimulationExpanded = true;

    public KMADEDefaultEdge() {
	super();
	Map<Object, Object> map = new Hashtable<Object, Object>();
	GraphConstants.setLineEnd(map, GraphConstants.ARROW_NONE);
	GraphConstants.setLabelAlongEdge(map, false);
	GraphConstants.setRouting(map, new KMADEEdgeView.NMDAEdgeRouting());
	GraphConstants.setBendable(map, false);
	GraphConstants.setMoveable(map, false);
	this.getAttributes().applyMap(map);
    }

    public KMADEDefaultGraphCell getMotherCell() {
	return (this.source == null ? null : ((KMADEDefaultPort) this.source)
		.getTaskOwner());
    }

    public KMADEDefaultGraphCell getSonCell() {
	return (this.target == null ? null : ((KMADEDefaultPort) this.target)
		.getTaskOwner());
    }

    public boolean isExpanded() {
	return isExpanded;
    }

    public boolean isSimulationExpanded() {
	return this.isSimulationExpanded;
    }

    public void setExpanded(boolean isExpanded) {
	this.isExpanded = isExpanded;
	((KMADEDefaultPort) this.getTarget()).setExpanded(isExpanded);
    }

    public void setSimulationExpanded(boolean pIsSimulationExpanded) {
	this.isSimulationExpanded = pIsSimulationExpanded;
	((KMADEDefaultPort) this.getTarget())
		.setSimulationExpanded(pIsSimulationExpanded);
    }

    public void setPoint(int dx, int dy) {
	((KMADEDefaultPort) this.getTarget()).setPoint(dx, dy);
    }
}

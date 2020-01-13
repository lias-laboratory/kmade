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

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.PortRenderer;
import org.jgraph.graph.PortView;

/**
 * @author Mickael BARON
 */
public class KMADEPortView extends PortView {
	private static final long serialVersionUID = 1274573416666928209L;

	public KMADEPortView(Object cell) {
		super(cell);
	}

	public Point2D getLocation(EdgeView edge, Point2D nearest) {
		// Vertex c'est la cell en fait.
		CellView vertex = getParentView();
		Point2D pos = null;
		if (vertex != null) {
			Rectangle2D r = vertex.getBounds();

			double highOrLow = 0;
			if (cell instanceof KMADEDefaultPort) {
				if (((KMADEDefaultPort) cell).isMotherPort())
					highOrLow = r.getMaxY();
				else
					highOrLow = r.getMinY();
			}

			if (r != null)
				return new Point2D.Double(r.getCenterX(), highOrLow);
		}
		return pos;
	}

	public CellViewRenderer getRenderer() {
		return new MyPortRenderer();
	}

	class MyPortRenderer extends PortRenderer {
		private static final long serialVersionUID = -928791334600161081L;

		public void paint(Graphics g) {
			if (!((KMADEDefaultPort) KMADEPortView.this.getCell()).isExpanded()) {
				return;
			} else {
				super.paint(g);
			}
		}
	}
}

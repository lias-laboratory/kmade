package kmade.kmade.UI.taskmodel;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.PortRenderer;
import org.jgraph.graph.PortView;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
				if (((KMADEDefaultPort)cell).isMotherPort())
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

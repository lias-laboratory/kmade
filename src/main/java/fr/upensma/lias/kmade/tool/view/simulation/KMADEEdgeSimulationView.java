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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JViewport;

import org.jgraph.JGraph;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.EdgeRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.VertexView;

import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultEdge;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEEdgeView;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEPortView;

/**
 * @author Mickael BARON
 */
public class KMADEEdgeSimulationView extends KMADEEdgeView {

	private static final long serialVersionUID = -201474302992985695L;

	public KMADEEdgeSimulationView(Object cell, JGraph myGraph) {
		super(cell, myGraph);
	}

	public CellViewRenderer getRenderer() {
		return new MyEdgeSimulationRenderer(this);
	}

	class MyEdgeSimulationRenderer extends EdgeRenderer {

		private static final long serialVersionUID = 1824218275417406698L;

		public MyEdgeSimulationRenderer(EdgeView d) {
			this.view = d;
		}

		protected void paintEdge(Graphics g) {
			g.setColor(Color.RED);
			KMADEPortView mySource = (KMADEPortView) view.getSource();
			KMADEPortView myTarget = (KMADEPortView) view.getTarget();
			VertexView parentView = (VertexView) mySource.getParentView();
			KMADEDefaultGraphCell myParentCell = (KMADEDefaultGraphCell) parentView.getCell();

			JGraph graph = null;
			if (this.graph != null) {
				graph = (JGraph) this.graph.get();
			}

			if (graph.getParent() != KMADEMainFrame.getProjectPanel().getTaskModelPanel()) {
				return;
			}

			JViewport myViewPort = (JViewport) graph.getParent().getParent();
			double ratio = graph.getScale();
			int vpX = (int) (myViewPort.getViewRect().getX() / ratio);
			int vpY = (int) (myViewPort.getViewRect().getY() / ratio);
			int vpW = (int) (myViewPort.getViewRect().getWidth() / ratio);
			int vpH = (int) (myViewPort.getViewRect().getHeight() / ratio);

			boolean testSource = mySource.intersects(graph, new Rectangle(vpX, vpY, vpW, vpH)); //
			boolean testTarget = myTarget.intersects(graph, new Rectangle(vpX, vpY, vpW, vpH));

			if (!testSource && testTarget) {
				double sourceX = mySource.getLocation().getX();
				double sourceY = mySource.getLocation().getY();

				double targetX = myTarget.getLocation().getX();
				double targetY = myTarget.getLocation().getY();

				double deltaX = Math.abs(sourceX - targetX);
				double deltaY = Math.abs(sourceY - targetY);
				double heighPort = 10;

				FontRenderContext frc = ((Graphics2D) g).getFontRenderContext();
				Rectangle2D bounds = ((Graphics2D) g).getFont().getStringBounds(myParentCell.getDecomposition(), frc);
				double widthString = bounds.getWidth();
				double heighString = bounds.getHeight();

				if (deltaX < widthString || deltaY < heighString) {
					return;
				}
				// R???cup???re la hauteur de la t???che.
				VertexView myVertexView = (VertexView) myTarget.getParentView();
				double heighVertex = myVertexView.getBounds().getHeight();
				if (sourceX < targetX && sourceY < targetY) {
					// Cas 1
					g.drawString(myParentCell.getDecomposition(), (int) (targetX - widthString),
							(int) (targetY - heighPort));
				} else if (sourceX > targetX && sourceY < targetY) {
					// Cas 2
					g.drawString(myParentCell.getDecomposition(), (int) (targetX + 2), (int) (targetY - heighPort));
				} else if (sourceX > targetX && (sourceY + CONST_HEIGH) > (targetY + heighString + heighVertex)) {
					// Cas 3
					g.drawString(myParentCell.getDecomposition(), (int) (targetX + 2),
							(int) (targetY + heighVertex + heighPort));
				} else if (sourceX < targetX && (sourceY + CONST_HEIGH) > (targetY + heighString + heighVertex)) {
					// Cas 4
					g.drawString(myParentCell.getDecomposition(), (int) (targetX - widthString),
							(int) (targetY + heighVertex + heighPort));
				}
			}
		}

		public void paint(Graphics g) {
			if (KMADEEdgeSimulationView.this.getCell() instanceof KMADEDefaultEdge) {
				if (!((KMADEDefaultEdge) KMADEEdgeSimulationView.this.getCell()).isSimulationExpanded()) {
					return;
				}
			}
			super.paint(g);
			this.paintEdge(g);
		}
	}
}

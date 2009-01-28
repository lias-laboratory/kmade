package kmade.kmade.UI.taskmodel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JViewport;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;

import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.DefaultGraphCellEditor;
import org.jgraph.graph.Edge;
import org.jgraph.graph.EdgeRenderer;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.PortView;
import org.jgraph.graph.VertexView;

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
public class KMADEEdgeView extends EdgeView {
	private static final long serialVersionUID = -9159952601479964935L;
	
    protected static final int CONST_HEIGH = 10;
    
    protected JGraph myGraph;
    
    public static final DefaultGraphCellEditor editor = new DefaultGraphCellEditor() {
		
        private static final long serialVersionUID = -5807565407502868928L;

        public boolean isCellEditable(EventObject event) {
			return false;	
		}
	};
	
	public KMADEEdgeView(Object cell, JGraph myGraph) {
		super(cell);
        this.myGraph = myGraph;
	}

    public CellViewRenderer getRenderer() {
        return  new MyLocaleEdgeRenderer(this);
    }
    
    
	public GraphCellEditor getEditor() {
		return editor;
	}
    
    public CellHandle getHandle(GraphContext context) {
        return null;
    }
    
    class MyLocaleEdgeRenderer extends EdgeRenderer {

        private static final long serialVersionUID = 1824218275417406698L;

        protected void paintEdge(Graphics g) {
            g.setColor(Color.RED);            
            KMADEPortView mySource = (KMADEPortView)view.getSource();
            KMADEPortView myTarget = (KMADEPortView)view.getTarget();
            VertexView parentView = (VertexView)mySource.getParentView();
            KMADEDefaultGraphCell myParentCell = (KMADEDefaultGraphCell)parentView.getCell();
            
            JGraph graph = null;
            if (this.graph != null) {
                graph = (JGraph)this.graph.get();
            }
            
            if (graph.getParent() != GraphicEditorAdaptator.getTaskModelPanel()) {
                return;
            }
            
            JViewport myViewPort = (JViewport)graph.getParent().getParent();
            double ratio = graph.getScale();
            int vpX = (int)(myViewPort.getViewRect().getX() / ratio); 
            int vpY = (int)(myViewPort.getViewRect().getY() / ratio);
            int vpW = (int)(myViewPort.getViewRect().getWidth() / ratio);
            int vpH = (int)(myViewPort.getViewRect().getHeight() / ratio);

            boolean testSource = mySource.intersects(graph,new Rectangle(vpX,vpY,vpW,vpH)); //
            boolean testTarget = myTarget.intersects(graph,new Rectangle(vpX,vpY,vpW,vpH));

            if (!testSource && testTarget) {                
                double sourceX = mySource.getLocation().getX();
                double sourceY = mySource.getLocation().getY();
    
                double targetX = myTarget.getLocation().getX();
                double targetY = myTarget.getLocation().getY();
    
                double deltaX = Math.abs(sourceX - targetX);
                double deltaY = Math.abs(sourceY - targetY);
                double heighPort = 10;
                
                FontRenderContext frc = ((Graphics2D)g).getFontRenderContext();
                Rectangle2D bounds = ((Graphics2D)g).getFont().getStringBounds(myParentCell.getDecomposition(), frc);
                double widthString = bounds.getWidth();
                double heighString = bounds.getHeight();
                
                if (deltaX < widthString || deltaY < heighString) {
                    return;
                }           
                // R�cup�re la hauteur de la t�che.
                VertexView myVertexView = (VertexView)myTarget.getParentView();
                double heighVertex = myVertexView.getBounds().getHeight();
                if (sourceX < targetX && sourceY < targetY) {
                    // Cas 1
                    g.drawString(myParentCell.getDecomposition(), (int)(targetX - widthString), (int)(targetY - heighPort));
                } else if (sourceX > targetX && sourceY < targetY) {
                    // Cas 2
                    g.drawString(myParentCell.getDecomposition(), (int)(targetX + 2), (int)(targetY - heighPort));
                } else if (sourceX > targetX && (sourceY + CONST_HEIGH) > (targetY + heighString + heighVertex)) {             
                    // Cas 3                
                    g.drawString(myParentCell.getDecomposition(), (int)(targetX + 2), (int)(targetY + heighVertex + heighPort));
                } else if (sourceX < targetX && (sourceY + CONST_HEIGH) > (targetY + heighString + heighVertex)) {  
                    // Cas 4
                    g.drawString(myParentCell.getDecomposition(), (int)(targetX - widthString), (int)(targetY + heighVertex + heighPort));
                }
            }        	
        }
        
        public void paint(Graphics g) {          
            if (KMADEEdgeView.this.getCell() instanceof KMADEDefaultEdge) {
                if (!((KMADEDefaultEdge)KMADEEdgeView.this.getCell()).isExpanded()) {
                    return;
                }
            }
            super.paint(g);
            if (KMADEEdgeView.this.myGraph != GraphicEditorAdaptator.getTaskModelPanel().getJGraph()) {
                // Le Presse-Papier par exemple.
                return;
            }
            this.paintEdge(g);
         }

        public MyLocaleEdgeRenderer(EdgeView d) {
            this.view = d;
        }
    }
    
    public static class NMDAEdgeRouting implements Edge.Routing {
     
    	private static final long serialVersionUID = 8644212784140976492L;

    	private List orthogonal(EdgeView edge) {
	        List<Object> newPoints = new ArrayList<Object>();
			int n = edge.getPointCount();
			Point2D from = edge.getPoint(0);
			newPoints.add(from);

			// Départ
			if (edge.getSource() instanceof PortView) {
				newPoints.set(0, edge.getSource());
				from = ((PortView) edge.getSource()).getLocation();
			} else if (edge.getSource() != null) {
				Rectangle2D b = edge.getSource().getBounds();
				from = edge.getAttributes().createPoint(b.getCenterX(),
						b.getCenterY());
			}

			// Arrivée
			Point2D to = edge.getPoint(n - 1);
			CellView target = edge.getTarget();
			if (target instanceof PortView)
				to = ((PortView) target).getLocation();
			else if (target != null) {
				Rectangle2D b = target.getBounds();
				to = edge.getAttributes().createPoint(b.getCenterX(),
						b.getCenterY());
			}

			if (from != null && to != null) {
				Point2D[] routed;
				routed = new Point2D[2];

				double frombisX = from.getX();
				double frombisY = from.getY() + CONST_HEIGH;
				double tobisX = to.getX();
				double tobisY = frombisY;

				routed[0] = edge.getAttributes()
						.createPoint(frombisX, frombisY);
				routed[1] = edge.getAttributes().createPoint(tobisX, tobisY);

				for (int i = 0; i < routed.length; i++)
					newPoints.add(routed[i]);

				// Add target point
				if (target != null)
					newPoints.add(target);
				else
					newPoints.add(to);

				return newPoints;
			}
            return null;
       	}

		public List route(EdgeView edge) {
			if (KMADEConstant.ORTHOGONAL_EDGES) {
				return orthogonal(edge);
			} else {
				return linear(edge);
			}
		}

		protected List linear(EdgeView edge) {
			return null;
		}
        
        public int getPreferredLineStyle(EdgeView edge) {
            return NO_PREFERENCE;
        }
    }
}

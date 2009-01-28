package kmade.kmade.UI.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultGraphSelectionModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;
import org.jgraph.plaf.basic.BasicGraphUI;

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
public class KMADETaskModelPanel extends JPanel {
       
	private static final long serialVersionUID = 1296221002750129957L;

	private JGraph graph;

	private DefaultGraphModel myGraphModel;

    protected Rectangle2D myInitialRectangle;

    protected boolean isMoved = false;
    
	/**
	 * Retourne la r�f�rence de la vue du graph.
	 * 
	 * @return
	 */
	public JGraph getJGraph() {
		return graph;
	}
    
    public void refreshJGraphView() {
        graph.repaint();
    }
    
    public void refreshViews() {
        graph.getGraphLayoutCache().refresh(graph.getGraphLayoutCache().getCellViews(),false);
    }
    
    public void updateGraphModel(Object[] cells) {
        myGraphModel.cellsChanged(cells);
    }
    
    public void updateGraphModel(Object cell) {
        Object[] myObject = {cell};
        myGraphModel.cellsChanged(myObject);
    }

    public JViewport getJGraphScrollPaneParent() {
        return (JViewport)(this.getParent());
    }
    
	/**
	 * Recherche une t�che dans l'arbre de t�che portant l'oid OID.
	 * 
	 * @param oid
	 * @return
	 */
	public KMADEDefaultGraphCell getTask(String oid) {
		Object[] allCell = DefaultGraphModel.getAll(myGraphModel);

		for (int i = 0; i <= allCell.length; i++) {
			if (allCell[i] instanceof KMADEDefaultGraphCell) {
				KMADEDefaultGraphCell myCurrentCell = (KMADEDefaultGraphCell) allCell[i];
				if (oid.equalsIgnoreCase(myCurrentCell.getOid()))
					return myCurrentCell;
			} else {
				// Ce n'est pas une cellule.
			}
		}
		return null;
	}

	/**
	 * Supprime tous les �l�ments graphique du mod�le de t�ches.
	 */
	public void emptyRootFromModel() {
		Object[] toto = DefaultGraphModel.getAll(myGraphModel);
		for (int i = 0; i < toto.length; i++) {
			myGraphModel.remove(toto);
		}
	}

	/**
	 * Retourne la cellule s�lectionn�e.
	 * 
	 * @return
	 */
	public KMADEDefaultGraphCell getCellSelected() {
		if (this.graph.getSelectionCell() instanceof KMADEDefaultGraphCell) {
			return (KMADEDefaultGraphCell) this.graph.getSelectionCell();
		} else {
			return null;
		}
	}

	/**
	 * Effectue un Zoom plus sur le graph.
	 * 
	 * @param p
	 */
	public void zoomPlus(double p) {
		graph.setScale(graph.getScale() * p);
	}

	/**
	 * Effectue un Zoom moins sur le graph.
	 * 
	 * @param p
	 */
	public void zoomMinus(double p) {
		graph.setScale(graph.getScale() / p);
	}

	/**
	 * Effectue un Zoom d�fault sur le graph.
	 * 
	 * @param p
	 */
	public void zoomDefault(double p) {
		graph.setScale(p);
	}

	/**
	 * @return Returns the myGraphModel.
	 */
	public DefaultGraphModel getGraphModel() {
		return myGraphModel;
	}

	public KMADETaskModelPanel() {
		this.setLayout(new BorderLayout());
		myGraphModel = new DefaultGraphModel();
		graph = new JGraph(myGraphModel);
        graph.setUI(new MyBasicGraphUI());
		graph.setPortsVisible(true);
		graph.setGridEnabled(true);
		graph.setGridVisible(true);
		graph.setGridSize(6);
		graph.setSelectionEnabled(true);
		graph.setTolerance(2);
		graph.setSizeable(false);
		graph.setInvokesStopCellEditing(true);
		graph.setCloneable(false);
		graph.setScale(1);

		graph.setSelectionModel(new NMDAGraphSelectionModel(graph));
		graph.setMarqueeHandler(new NMDAMarqueeHandler());
		graph.setJumpToDefaultPort(true);
		graph.getGraphLayoutCache().setFactory(new DefaultCellViewFactory() {

            private static final long serialVersionUID = -4630110869099714169L;

            public CellView createView(GraphModel arg0, Object arg1) {
				if (arg1 instanceof KMADEDefaultGraphCell) {
					return new KMADEVertexView(arg1,graph);
				} else if (arg1 instanceof DefaultPort) {
					return new KMADEPortView(arg1);
				} else if (arg1 instanceof DefaultEdge)
					return new KMADEEdgeView(arg1,graph);
				{
					return super.createView(arg0, arg1);
				}
			}
		});
		this.add(BorderLayout.CENTER, graph);
	}
    
    // Modèle de sélection.
	static class NMDAGraphSelectionModel extends DefaultGraphSelectionModel implements GraphSelectionListener {
		private static final long serialVersionUID = 2208622699738909622L;

		public NMDAGraphSelectionModel(JGraph arg0) {
			super(arg0);
			this.addGraphSelectionListener(this);
		}

		public void valueChanged(GraphSelectionEvent arg0) {
			boolean enabled = !graph.isSelectionEmpty();
			if (!enabled) {
				GraphicEditorAdaptator.selectNoTask();
			} else {
				// Compte vraiment le nombre d'�l�ments.
				if (this.getSelectionCount() == 0) {
					GraphicEditorAdaptator.selectNoTask();
				} else if (this.getSelectionCount() == 1) {
                    Object toto = NMDAGraphSelectionModel.this.getSelectionCell();
                    if (toto instanceof KMADEDefaultEdge) {
                        if (!((KMADEDefaultEdge)toto).isExpanded()) {
                            NMDAGraphSelectionModel.this.clearSelection();
                            return;
                        }
                    } else if (toto instanceof KMADEDefaultGraphCell) {
                        if (!((KMADEDefaultGraphCell)toto).isExpanded()) {
                            NMDAGraphSelectionModel.this.clearSelection();
                            return;
                        } 
                    }
                    
					GraphicEditorAdaptator.selectOneTask(toto);
				} else if (this.getSelectionCount() > 1) {
                    // Avant d'envoyer � l'AdaptateurEditeurGraphic faire un tri sur les �l�ments cach�s.
                    Object[] toto = NMDAGraphSelectionModel.this.getSelectionCells();
                    ArrayList<Object> myList = new ArrayList<Object>();
                    for (int i = 0 ; i < toto.length ; i++) {
                        if (toto[i] instanceof KMADEDefaultEdge) {
                            if (((KMADEDefaultEdge)toto[i]).isExpanded()) {
                                myList.add(toto[i]);
                            }
                        }
                        if (toto[i] instanceof KMADEDefaultGraphCell) {
                            if (((KMADEDefaultGraphCell)toto[i]).isExpanded()) {
                                myList.add(toto[i]);
                            } 
                        }
                    }
                    if (toto.length == myList.size()) {
                        GraphicEditorAdaptator.selectSeveralTask();
                        // Ne rien faire.
                    } else {
                        NMDAGraphSelectionModel.this.setSelectionCells(myList.toArray());
                    }
				}
			}
		}
	}

    // Le gestionnaire de pointage hors tache.
	public class NMDAMarqueeHandler extends BasicMarqueeHandler {
		protected Point2D start, current;

		protected PortView port, firstPort;
        
		/*
		 * Cette m�thode permet d'activer ou de d�sactiver le d�placement.
		 * Typiquement la s�lection d'un port retourne vraie.
		 */
		public boolean isForceMarqueeEvent(MouseEvent e) {
			if (e.isShiftDown())
				return false;
			port = getSourcePortAt(e.getPoint());                      

            // Pour le popupmenu
            if (SwingUtilities.isRightMouseButton(e))
                return true;
            
			if (port != null && graph.isPortsVisible()) {
                if (((KMADEDefaultPort)port.getCell()).isExpanded()) {
                    return true;
                } else {
                    return false;
                }
            }
            return super.isForceMarqueeEvent(e);
		}

		public void mousePressed(final MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {   
                if (GraphicEditorAdaptator.addNewTask(new Point(e.getX(),e.getY()))) {
                    return;
                }
            }
            
            if (SwingUtilities.isRightMouseButton(e)) {                
                JPopupMenu menu = GraphicEditorAdaptator.showPopupMenu(e.getPoint());
                if (menu != null) {
                    menu.show(graph,e.getX(), e.getY());
                }
            } else if (port != null && graph.isPortsVisible()) {
                start = graph.toScreen(port.getLocation());
                firstPort = port;
            } else {
                super.mousePressed(e);
            }    
		}

		public void mouseDragged(MouseEvent e) {              
			if (start != null) {
				Graphics g = graph.getGraphics();
				PortView newPort = getTargetPortAt(e.getPoint());

				if (newPort == null || newPort != port) {
					paintConnector(Color.black, graph.getBackground(), g);
					port = newPort;
					if (port != null)
						current = graph.toScreen(port.getLocation());
					else
						current = graph.snap(e.getPoint());
					paintConnector(graph.getBackground(), Color.black, g);
				}
			}
			super.mouseDragged(e);
		}

        public void mouseReleased(MouseEvent e) {            
            if (e != null && port != null && firstPort != null && firstPort != port) {
                // Normalement en lachant on devrait cr�er la connexion.
                // A v�rifier si on connecte au bon endroit m�re fils ou fils m�re d'une t�che diff�rente
                // et que la connexion n'est pas d�j� faite
                boolean successed = GraphicEditorAdaptator.addNewEdge((KMADEDefaultPort) (firstPort.getCell()), (KMADEDefaultPort) (port.getCell()));
                if (!successed)
                	graph.repaint();
                e.consume();
            } else {
                graph.repaint();
            }
            
            firstPort = port = null;
            start = current = null;
            super.mouseReleased(e);
        }

        public void mouseMoved(MouseEvent e) {
            PortView myPort = getSourcePortAt(e.getPoint());
            
            if (e != null && myPort != null && graph.isPortsVisible()) {
                if (!((KMADEDefaultPort)myPort.getCell()).isExpanded()) {
                    return;
                }
                graph.setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.consume();
            } else {
                super.mouseMoved(e);
            }
        }        
               
		public PortView getSourcePortAt(Point2D point) {
			graph.setJumpToDefaultPort(false);
			PortView result;
			try {
				result = graph.getPortViewAt(point.getX(), point.getY());
			} finally {
				graph.setJumpToDefaultPort(true);
			}
			return result;
		}

		protected PortView getTargetPortAt(Point2D point) {
			return graph.getPortViewAt(point.getX(), point.getY());
		}

		protected void paintConnector(Color fg, Color bg, Graphics g) {
			g.setColor(fg);
			g.setXORMode(bg);
			paintPort(graph.getGraphics());
			if (firstPort != null && start != null && current != null)
				g.drawLine((int) start.getX(), (int) start.getY(),
						(int) current.getX(), (int) current.getY());
		}

		protected void paintPort(Graphics g) {
			if (port != null) {
				boolean o = (GraphConstants.getOffset(port.getAllAttributes()) != null);
				Rectangle2D r = (o) ? port.getBounds() : port.getParentView()
						.getBounds();
				r = graph.toScreen((Rectangle2D) r.clone());
				r.setFrame(r.getX() - 3, r.getY() - 3, r.getWidth() + 6, r
						.getHeight() + 6);
				graph.getUI().paintCell(g, port, r, true);
			}
		}
	}
    
    // Le UI du JGraph : c'est lui qui dispatche au NMDAMarqueeHandler.
    public class MyBasicGraphUI extends BasicGraphUI {
        
        private static final long serialVersionUID = 1338556852924289L;
        
        protected Object getFocusedCell() {
            if (focus != null)
                return focus.getCell();
            return null;
        }
        
        public CellHandle createHandle(GraphContext context) {
            if (context != null && !context.isEmpty() && graph.isEnabled()) {
                try {
                    return new MyRootHandle(context);
                } catch (NullPointerException e) {
                    // ignore for now...
                }
            }
            return null;
        }
        
        protected MouseListener createMouseListener() {
            return new MyMouseHandler();
        }
        
        protected boolean startEditing(Object cell, MouseEvent event) {
            completeEditing();
            if (graph.isCellEditable(cell)) {
                CellView tmp = graphLayoutCache.getMapping(cell, false);
                cellEditor = tmp.getEditor();
                editingComponent = cellEditor.getGraphCellEditorComponent(graph, cell, graph.isCellSelected(cell));

                if (cellEditor.isCellEditable(event)) {
                    Rectangle2D cellBounds = graph.getCellBounds(cell);

                    editingCell = cell;

                    Dimension2D editorSize = editingComponent.getPreferredSize();

                    graph.add(editingComponent);
                    Point2D p = getEditorLocation(cell, editorSize, graph.toScreen(new Point2D.Double(cellBounds.getX(),
                                    cellBounds.getY())));
                    int deltax = 0;
                    int deltay = 0;
                    if (graph.getParent().getParent() instanceof JViewport) {
                        JViewport myPort = (JViewport)(graph.getParent().getParent());
                        deltay = ((int) p.getY() + (int) editorSize.getHeight()) - myPort.getHeight();
                        if (deltay < 0) {
                            deltay = 0; 
                        } else {
                            deltay = (int) editorSize.getHeight() / 2;
                        }
                        
                        deltax = ((int) p.getX() + (int) editorSize.getWidth()) - myPort.getWidth();
                        if (deltax < 0) {
                            deltax = 0;
                        } else {
                            deltax = (int) editorSize.getWidth() / 2;
                        }
                    }
                    editingComponent.setBounds((int) p.getX()-deltax, (int) p.getY()-deltay,
                            (int) editorSize.getWidth(), (int) editorSize
                                    .getHeight());
                    editingCell = cell;
                    editingComponent.validate();
                    
                    // Add Editor Listener
                    if (cellEditorListener == null)
                        cellEditorListener = createCellEditorListener();
                    if (cellEditor != null && cellEditorListener != null)
                        cellEditor.addCellEditorListener(cellEditorListener);
                    Rectangle2D visRect = graph.getVisibleRect();
                    graph.paintImmediately((int) p.getX(), (int) p.getY(),
                            (int) (visRect.getWidth() + visRect.getX() - cellBounds
                                    .getX()), (int) editorSize.getHeight());
                    if (cellEditor.shouldSelectCell(event)
                            && graph.isSelectionEnabled()) {
                        stopEditingInCompleteEditing = false;
                        try {
                            graph.setSelectionCell(cell);
                        } catch (Exception e) {
                            System.err.println("Editing exception: " + e);
                        }
                        stopEditingInCompleteEditing = true;
                    }

                    if (event instanceof MouseEvent) {
                        /*
                         * Find the component that will get forwarded all the mouse
                         * events until mouseReleased.
                         */
                        Point componentPoint = SwingUtilities.convertPoint(graph,
                                new Point(event.getX(), event.getY()),
                                editingComponent);

                        /*
                         * Create an instance of BasicTreeMouseListener to handle
                         * passing the mouse/motion events to the necessary
                         * component.
                         */
                        // We really want similiar behavior to getMouseEventTarget,
                        // but it is package private.
                        Component activeComponent = SwingUtilities
                                .getDeepestComponentAt(editingComponent,
                                        componentPoint.x, componentPoint.y);
                        if (activeComponent != null) {
                            new MouseInputHandler(graph, activeComponent, event);
                        }
                    }
                    return true;
                } else
                    editingComponent = null;
            }
            return false;
        }
               
        // Le plus haut niveau. Il dispatch et il g�re la s�lection
        class MyMouseHandler extends MouseHandler {            

            private static final long serialVersionUID = 13998973368103617L;
            
            /**
             * Note: Arguments are not expected to be scaled (they are scaled in here).
             */
            public CellView getNextSelectableViewAt(CellView current, double x, double y) {
                CellView[] selectables = getJGraph().getGraphLayoutCache().getMapping(
                        getJGraph().getSelectionModel().getSelectables(), false);
                return getNextViewAt(selectables, current, x, y);
            }

            /**
             * Returns the next view at the specified location wrt. <code>c</code> in
             * the specified array of views. The views must be in order, as returned,
             * for example, by GraphLayoutCache.order(Object[]).
             */
            public CellView getNextViewAt(CellView[] cells, CellView c, double x,
                    double y) {
                return getNextViewAt(cells, c, x, y, false);
            }

            /**
             * Returns the next view at the specified location wrt. <code>c</code> in
             * the specified array of views. The views must be in order, as returned,
             * for example, by GraphLayoutCache.order(Object[]).
             */
            public CellView getNextViewAt(CellView[] cells, CellView c, double x,
                    double y, boolean leafsOnly) {
                if (cells != null) {
                    Rectangle2D r = getJGraph().fromScreen(new Rectangle2D.Double(x - getJGraph().getTolerance(), y
                            - getJGraph().getTolerance(), 2 * getJGraph().getTolerance(), 2 * getJGraph().getTolerance()));
                    // Iterate through cells and switch to active
                    // if current is traversed. Cache first cell.
                    CellView first = null;
                    boolean active = (c == null);

                    for (int i = 0; i < cells.length; i++) {
                        if (cells[i] != null && (!leafsOnly || cells[i].isLeaf()) && cells[i].intersects(getJGraph(), r) && isExpanded(cells[i])) {
                            if (active && !getJGraph().getSelectionModel().isChildrenSelected(cells[i].getCell())) {
                                return cells[i];
                            } else if (first == null) {
                                first = cells[i];
                            }
                            active = active | (cells[i] == c);
                        }
                    }
                    if (first != null)
                        return first;
                }
                return null;
            }

            public boolean isExpanded(CellView myCell) {
                if (myCell.getCell() instanceof KMADEDefaultGraphCell) {
                    if (!((KMADEDefaultGraphCell)myCell.getCell()).isExpanded()) {
                        return false;
                    } else {
                        return true;
                    }                    
                }
                return true;
            }
            
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                } else {
                    super.mouseDragged(e);
                }
            }
            
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    return;
                }
                try {
                    if (e != null && !e.isConsumed() && graph != null && graph.isEnabled()) {
                        if (handler == marquee && marquee != null)
                            // Pour d�sactiver.
                            marquee.mouseReleased(e);
                        else if (handler == handle && handle != null) {
                            handle.mouseReleased(e);
                        }
                            
                        if (isDescendant(cell, focus) && e.getModifiers() != 0) {
                            // Do not switch to parent if Special Selection
                            cell = focus;
                        }
                        if (!e.isConsumed() && cell != null) {
                            Object tmp = cell.getCell();
                            boolean wasSelected = graph.isCellSelected(tmp);
                            selectCellForEvent(tmp, e);
                            focus = cell;
                            postProcessSelection(e, tmp, wasSelected);
                        }
                    }
                } finally {
                    handler = null;
                    cell = null;
                }
            }

            
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    marquee.mousePressed(e);
                    return;
                }
                
                handler = null;
                if (!e.isConsumed() && graph.isEnabled()) {
                    graph.requestFocus();
                    int s = graph.getTolerance();
                    Rectangle2D r = graph.fromScreen(new Rectangle2D.Double(e
                            .getX()
                            - s, e.getY() - s, 2 * s, 2 * s));
                    lastFocus = focus;
                    focus = (focus != null && focus.intersects(graph, r)) ? focus : null;
                    cell = getNextSelectableViewAt(focus, e.getX(), e.getY());
                           
                    if (focus == null)
                        focus = cell;
                    completeEditing();
                    if (!isForceMarqueeEvent(e)) {
                        if (e.getClickCount() == graph.getEditClickCount()
                                && focus != null && focus.isLeaf()
                                && focus.getParentView() == null
                                && graph.isCellEditable(focus.getCell())
                                && handleEditTrigger(cell.getCell(), e)) {
                            e.consume();
                            cell = null;
                        } else if (!isToggleSelectionEvent(e)) {
                            if (handle != null) {
                                handle.mousePressed(e);
                                handler = handle;
                            }
                            // Immediate Selection
                            if (!e.isConsumed() && cell != null
                                    && !graph.isCellSelected(cell.getCell())) {
                                selectCellForEvent(cell.getCell(), e);
                                focus = cell;
                                if (handle != null) {
                                    handle.mousePressed(e);
                                    handler = handle;
                                }
                                e.consume();
                                cell = null;
                            }
                        }
                    }
                    // Marquee Selection
                    if (!e.isConsumed() && marquee != null
                            && (!isToggleSelectionEvent(e) || focus == null)) {
                        marquee.mousePressed(e);
                        handler = marquee;
                    }
                }
            }
        }
        
        // Sur la cellule
        class MyRootHandle extends RootHandle {

            private static final long serialVersionUID = -7701170816197134233L;
            
            public MyRootHandle(GraphContext ctx) {
                super(ctx);
            }
            
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu menu = GraphicEditorAdaptator.showPopupMenu(e.getPoint());
                    if (menu != null) {
                        menu.show(graph,e.getX(), e.getY());
                    }
                } else {
                    super.mousePressed(e);
                }
            }
            
            public void mouseDragged(MouseEvent event) {
                if (!SwingUtilities.isRightMouseButton(event)) {                    
                    super.mouseDragged(event);
                }
            }
            
            public void mouseReleased(MouseEvent event) {   
                if (!SwingUtilities.isRightMouseButton(event)) {                  
                    super.mouseReleased(event);
                    if (this.isMoving) {
                        GraphicEditorAdaptator.fireMoveTasks();
                    }
                }
            }
        }
    }
    
    public void notifLocalisationModification() {
        // NMDATaskModelPanel
    }
}

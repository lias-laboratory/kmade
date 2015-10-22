/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;


import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultGraphSelectionModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.ParentMap;
import org.jgraph.graph.PortView;
import org.jgraph.plaf.basic.BasicGraphUI;

import fr.lri.swingstates.canvas.Canvas;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.PieMenuCreateTask;
import fr.upensma.lias.kmade.tool.view.PieMenuEditDecomposition;
import fr.upensma.lias.kmade.tool.view.PieMenuEditExecutant;
import fr.upensma.lias.kmade.tool.view.PieMenuEdition;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADETaskModelPanel extends JPanel  {

	private static final long serialVersionUID = 1296221002750129957L;

	private PieMenuCreateTask menuTask;

	private PieMenuEditExecutant menuEditExecutant;

	private PieMenuEditDecomposition menuEditDecomposition;

	private PieMenuEdition menuEdition;

	private Canvas canvas;

	private KMADEGraph graph;

	private DefaultGraphModel myGraphModel;

	protected Rectangle2D myInitialRectangle;

	private double zoom;
	/*AG*/
	//===============================================
    private KMADEUndoRedoManager undoManager;
//===================================================
    

	/**
	 * Retourne la référence de la vue du graph.
	 * 
	 * @return
	 */
	public KMADEGraph getJGraph() {
		return graph;
	}

	public void refreshJGraphView() {
		graph.repaint();
	}

	public void refreshViews() {
		graph.getGraphLayoutCache().refresh(
				graph.getGraphLayoutCache().getCellViews(), false);
	}

	public void updateGraphModel(Object[] cells) {
		myGraphModel.cellsChanged(cells);
	}

	public void updateGraphModel(Object cell) {
		Object[] myObject = { cell };
		myGraphModel.cellsChanged(myObject);
	}

	public JViewport getJGraphScrollPaneParent() {
		return (JViewport) (this.getParent());
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
		zoom = p;
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

		graph = new KMADEGraph(myGraphModel);
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

		zoom = 1;

		graph.setScale(zoom);

		graph.setSelectionModel(new NMDAGraphSelectionModel(graph));
		graph.setMarqueeHandler(new NMDAMarqueeHandler());
		graph.setJumpToDefaultPort(true);
		graph.getGraphLayoutCache().setFactory(new DefaultCellViewFactory() {

			private static final long serialVersionUID = -4630110869099714169L;

			public CellView createView(GraphModel arg0, Object arg1) {
				if (arg1 instanceof KMADEDefaultGraphCell) {
					return new KMADEVertexView(arg1, graph);
				} else if (arg1 instanceof DefaultPort) {
					return new KMADEPortView(arg1);
				} else if (arg1 instanceof DefaultEdge)
					return new KMADEEdgeView(arg1, graph);
				{
					return super.createView(arg0, arg1);
				}
			}
		});

		// ////////////////////////////////////////

		this.add(BorderLayout.CENTER, graph);
		
		
		/*AG*/
		//GraphSelectionListener,
		//KeyListener
		undoManager = new KMADEUndoRedoManager (graph);
		
		// Add Listeners to Graph
		// Register UndoManager with the Model
		graph.getModel().addUndoableEditListener(undoManager.getUndoManager());
		// Update ToolBar based on Selection Changes
		
		graph.getSelectionModel().addGraphSelectionListener(new GraphSelectionListener() {
			
			@Override
			public void valueChanged(GraphSelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Listen for Delete Keystroke when the Graph has Focus
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		/*AG*/
	
	}

	// Modèle de sélection.
	static class NMDAGraphSelectionModel extends DefaultGraphSelectionModel
	implements GraphSelectionListener {
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
					Object toto = NMDAGraphSelectionModel.this
							.getSelectionCell();
					if (toto instanceof KMADEDefaultEdge) {
						if (!((KMADEDefaultEdge) toto).isExpanded()) {
							NMDAGraphSelectionModel.this.clearSelection();
							return;
						}
					} else if (toto instanceof KMADEDefaultGraphCell) {
						if (!((KMADEDefaultGraphCell) toto).isExpanded()) {
							NMDAGraphSelectionModel.this.clearSelection();
							return;
						}
					}

					GraphicEditorAdaptator.selectOneTask(toto);
				} else if (this.getSelectionCount() > 1) {
					// Avant d'envoyer � l'AdaptateurEditeurGraphic faire un
					// tri sur les �l�ments cach�s.
					Object[] toto = NMDAGraphSelectionModel.this
							.getSelectionCells();
					ArrayList<Object> myList = new ArrayList<Object>();
					for (int i = 0; i < toto.length; i++) {
						if (toto[i] instanceof KMADEDefaultEdge) {
							if (((KMADEDefaultEdge) toto[i]).isExpanded()) {
								myList.add(toto[i]);
							}
						}
						if (toto[i] instanceof KMADEDefaultGraphCell) {
							if (((KMADEDefaultGraphCell) toto[i]).isExpanded()) {
								myList.add(toto[i]);
							}
						}
					}
					if (toto.length == myList.size()) {
						GraphicEditorAdaptator.selectSeveralTask();
						// Ne rien faire.
					} else {
						NMDAGraphSelectionModel.this.setSelectionCells(myList
								.toArray());
					}
				}
			}
		}
	}

	// Le gestionnaire de pointage hors tache.
	public class NMDAMarqueeHandler extends BasicMarqueeHandler {
		protected Point2D start, current;

		protected PortView port, firstPort;

		protected int m_XDifference, m_YDifference;
		protected boolean m_dragging;
		protected Container c;

		protected boolean release;
		protected boolean interupt;
		protected boolean cursorChange;
		private monThread thread;
		private boolean curseurMove;
		private boolean prems = true;

		private Toolkit tk = Toolkit.getDefaultToolkit();

		private Image img = tk.getImage(KMADEVertexView.class
				.getResource(KMADEConstant.CURSEUR_IMAGE_GRAB));

		public Cursor CURSEUR_GRAB = tk.createCustomCursor(img, new Point(16,
				16), "curseur_grab");

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
				if (((KMADEDefaultPort) port.getCell()).isExpanded()) {
					return true;
				} else {
					return false;
				}
			}
			return super.isForceMarqueeEvent(e);
		}

		public void overlay(JGraph graph, Graphics g, boolean clear) {
			// unused KMADEGraph kGraph = (KMADEGraph) graph;
			if (marqueeBounds != null) {
				if (!graph.isXorEnabled()) {
					g.setColor(graph.getMarqueeColor());
				}
				if (!curseurMove) {
					// on n'est pas en mode "grab"
					g.drawRect((int) marqueeBounds.getX(),
							(int) marqueeBounds.getY(),
							(int) marqueeBounds.getWidth(),
							(int) marqueeBounds.getHeight());
				}
			}
		}

		public void mousePressed(final MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e)) {
				Object obj = graph.getSelectionCellAt(e.getPoint());
				release = false;
				if (GraphicEditorAdaptator.addNewTask(new Point(e.getX(), e
						.getY()))) {
					return;
				} else { // /////////// rajout� ///////////////////

					if (port != null && graph.isPortsVisible()) {
						start = graph.toScreen(port.getLocation());
						firstPort = port;
						return;
					}

					if (thread == null) {
						if (obj == null) {
							thread = new monThread(this, e);
							try {
								thread.start();

							} catch (Exception ex) {

							}
						}
					} else {

						if (!interupt) {

							if (graph.getSelectionCell() == null) {

								graph.setCursor(new Cursor(Cursor.MOVE_CURSOR));

								m_XDifference = e.getX();
								m_YDifference = e.getY();
							} else if (graph.getSelectionCellAt(e.getPoint()) == null) {
								graph.setSelectionCell(null);
								graph.setCursor(new Cursor(Cursor.MOVE_CURSOR));

								m_XDifference = e.getX();
								m_YDifference = e.getY();
							}
							thread = null;
							super.setStartPoint(e.getPoint());
							super.setMarqueeBounds(new Rectangle2D.Double(
									startPoint.getX(), startPoint.getY(), 0, 0));

							return;
						}

					}
				}// //////////////////////////////
			}

			if (SwingUtilities.isRightMouseButton(e)) {

			} else if (port != null && graph.isPortsVisible()) {
				start = graph.toScreen(port.getLocation());
				firstPort = port;
			} else {
				super.mousePressed(e);
			}

		}

		public void mouseDragged(MouseEvent e) {
			curseurMove = false;
			try {
				thread.interrupt();
				interupt = true;
				thread = null;

			} catch (Exception ex) {

			}
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
			} else { // ////////////rajout�///////////////////////
				if (graph.getCursor().getType() == Cursor.MOVE_CURSOR) {
					c = getJGraphScrollPaneParent();
					curseurMove = true;
					if (graph.getSelectionCell() == null
							|| graph.getSelectionCellAt(e.getPoint()) == null) {
						if (c instanceof JViewport) {
							JViewport jv = (JViewport) c;
							Point p = jv.getViewPosition();
							int newX = p.x - (e.getX() - m_XDifference);
							int newY = p.y - (e.getY() - m_YDifference);

							int maxX = graph.getWidth() - jv.getWidth();
							int maxY = graph.getHeight() - jv.getHeight();
							if (newX < 0)
								newX = 0;
							if (newX > maxX)
								newX = maxX;
							if (newY < 0)
								newY = 0;
							if (newY > maxY)
								newY = maxY;

							jv.setViewPosition(new Point(newX, newY));
						}
					}
				}
			}// /////////////////////////////////////////
			super.mouseDragged(e);
		}

		public void mouseReleased(MouseEvent e) {

			if (e != null && port != null && firstPort != null
					&& firstPort != port) {
				// Normalement en lachant on devrait cr�er la connexion.
				// A v�rifier si on connecte au bon endroit m�re fils ou
				// fils m�re d'une t�che diff�rente
				// et que la connexion n'est pas d�j� faite
				boolean successed = GraphicEditorAdaptator.addNewEdge(
						(KMADEDefaultPort) (firstPort.getCell()),
						(KMADEDefaultPort) (port.getCell()));
				if (!successed)
					graph.repaint();
				e.consume();
			} else {
				graph.repaint();
			}

			firstPort = port = null;
			start = current = null;
			super.mouseReleased(e);
			try {
				thread.interrupt();
				interupt = true;
				thread = null;
			} catch (Exception ex) {

			}
			release = true;
			graph.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		public void mouseMoved(MouseEvent e) {

			PortView myPort = getSourcePortAt(e.getPoint());

			if (prems) {
				KMADEMainFrame f = GraphicEditorAdaptator.getMainFrame();
				int w = f.getWidth();
				int h = f.getHeight();
				canvas = new Canvas(w, h);
				canvas.setBounds((int) getBounds().getX(), 50, w, h - 50);
				canvas.setBackground(new Color(1.0f, 1.0f, 1.0f, 0));
				canvas.setOpaque(false);
				f.setGlassPane(canvas);
				menuTask = new PieMenuCreateTask(canvas);
				menuEditExecutant = new PieMenuEditExecutant(canvas);
				menuEditDecomposition = new PieMenuEditDecomposition(canvas);
				menuEdition = new PieMenuEdition(canvas);
				canvas.setOpaque(false);
				prems = false;
			}

			if (e != null && myPort != null && graph.isPortsVisible()) {
				if (!((KMADEDefaultPort) myPort.getCell()).isExpanded()) {
					return;
				}

				// canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
				r.setFrame(r.getX() - 3, r.getY() - 3, r.getWidth() + 6,
						r.getHeight() + 6);
				graph.getUI().paintCell(g, port, r, true);
			}
		}

		class monThread extends Thread {
			private NMDAMarqueeHandler mouseIA;
			private MouseEvent event;

			public monThread(NMDAMarqueeHandler mia, MouseEvent e) {
				mouseIA = mia;
				event = e;
			}

			public void run() {
				synchronized (this) {

					try {

						wait(KMADEConstant.TEMPORISATION_GRAB);

						interupt = false;
						if (!release) {
							mouseIA.mousePressed(event);
						}
					} catch (Exception ex) {
						interrupt();
						interupt = true;

					}
				}
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
				editingComponent = cellEditor.getGraphCellEditorComponent(
						graph, cell, graph.isCellSelected(cell));

				if (cellEditor.isCellEditable(event)) {
					Rectangle2D cellBounds = graph.getCellBounds(cell);

					editingCell = cell;

					Dimension2D editorSize = editingComponent
							.getPreferredSize();

					graph.add(editingComponent);
					Point2D p = getEditorLocation(cell, editorSize,
							graph.toScreen(new Point2D.Double(cellBounds
									.getCenterX(), cellBounds.getCenterY())));
					int deltax = 0;
					int deltay = 0;
					if (graph.getParent().getParent() instanceof JViewport) {
						JViewport myPort = (JViewport) (graph.getParent()
								.getParent());
						deltay = ((int) p.getY() + (int) editorSize.getHeight())
								- myPort.getHeight();
						if (deltay < 0) {
							deltay = 0;
						} else {
							deltay = (int) editorSize.getHeight() / 2;
						}

						deltax = ((int) p.getX() + (int) editorSize.getWidth())
								- myPort.getWidth();
						if (deltax < 0) {
							deltax = 0;
						} else {
							deltax = (int) editorSize.getWidth() / 2;
						}
					}
					editingComponent.setBounds((int) p.getX(), (int) p.getY(),
							(int) editorSize.getWidth(),
							(int) editorSize.getHeight());
					editingCell = cell;
					editingComponent.validate();

					// Add Editor Listener
					if (cellEditorListener == null)
						cellEditorListener = createCellEditorListener();
					if (cellEditor != null && cellEditorListener != null)
						cellEditor.addCellEditorListener(cellEditorListener);
					Rectangle2D visRect = graph.getVisibleRect();
					graph.paintImmediately(
							(int) p.getX(),
							(int) p.getY(),
							(int) (visRect.getWidth() + visRect.getX() - cellBounds
									.getX()), (int) editorSize.getHeight());
					if (cellEditor.shouldSelectCell(event)
							&& graph.isSelectionEnabled()) {
						stopEditingInCompleteEditing = false;
						try {
							graph.setSelectionCell(cell);
						} catch (Exception e) {
							KMADEHistoryMessageManager.printlnError("Editing exception: " + e);
						}
						stopEditingInCompleteEditing = true;
					}

					if (event instanceof MouseEvent) {
						/*
						 * Find the component that will get forwarded all the
						 * mouse events until mouseReleased.
						 */
						Point componentPoint = SwingUtilities.convertPoint(
								graph, new Point(event.getX(), event.getY()),
								editingComponent);

						/*
						 * Create an instance of BasicTreeMouseListener to
						 * handle passing the mouse/motion events to the
						 * necessary component.
						 */
						// We really want similiar behavior to
						// getMouseEventTarget,
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
			 * Note: Arguments are not expected to be scaled (they are scaled in
			 * here).
			 */
			public CellView getNextSelectableViewAt(CellView current, double x,
					double y) {
				CellView[] selectables = getJGraph().getGraphLayoutCache()
						.getMapping(
								getJGraph().getSelectionModel()
								.getSelectables(), false);
				return getNextViewAt(selectables, current, x, y);
			}

			/**
			 * Returns the next view at the specified location wrt.
			 * <code>c</code> in the specified array of views. The views must be
			 * in order, as returned, for example, by
			 * GraphLayoutCache.order(Object[]).
			 */
			public CellView getNextViewAt(CellView[] cells, CellView c,
					double x, double y) {
				return getNextViewAt(cells, c, x, y, false);
			}

			/**
			 * Returns the next view at the specified location wrt.
			 * <code>c</code> in the specified array of views. The views must be
			 * in order, as returned, for example, by
			 * GraphLayoutCache.order(Object[]).
			 */
			public CellView getNextViewAt(CellView[] cells, CellView c,
					double x, double y, boolean leafsOnly) {
				if (cells != null) {
					Rectangle2D r = getJGraph().fromScreen(
							new Rectangle2D.Double(x
									- getJGraph().getTolerance(), y
									- getJGraph().getTolerance(),
									2 * getJGraph().getTolerance(),
									2 * getJGraph().getTolerance()));
					// Iterate through cells and switch to active
					// if current is traversed. Cache first cell.
					CellView first = null;
					boolean active = (c == null);

					for (int i = 0; i < cells.length; i++) {
						if (cells[i] != null
								&& (!leafsOnly || cells[i].isLeaf())
								&& cells[i].intersects(getJGraph(), r)
								&& isExpanded(cells[i])) {
							if (active
									&& !getJGraph().getSelectionModel()
									.isChildrenSelected(
											cells[i].getCell())) {
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
					if (!((KMADEDefaultGraphCell) myCell.getCell())
							.isExpanded()) {
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


				graph.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
							.getX() - s, e.getY() - s, 2 * s, 2 * s));
					lastFocus = focus;
					focus = (focus != null && focus.intersects(graph, r)) ? focus
							: null;
					cell = getNextSelectableViewAt(focus, e.getX(), e.getY());

					if (focus == null)
						focus = cell;
					completeEditing();
					if (!isForceMarqueeEvent(e)) {
						if (e.getClickCount() == graph.getEditClickCount()
								&& focus != null && focus.isLeaf()
								&& focus.getParentView() == null
								&& graph.isCellEditable(focus.getCell())
								/* && handleEditTrigger(cell.getCell(), e) */) {

							((MyRootHandle) handle).mouseClicked(e);
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

			public void mouseClicked(MouseEvent e) {

				CellView[] views = graph.getGraphLayoutCache().getCellViews();
				for (int i = 0; i < views.length; i++) {
					if (views[i] instanceof KMADEVertexView) {
						if (views[i].getCell() == graph.getSelectionCellAt(e
								.getPoint())) {
							((KMADEVertexView.MyCellHandle) ((KMADEVertexView) views[i])
									.getHandle(context)).mouseClicked(e);
						}
					}
				}

			}

			public void mousePressed(MouseEvent event) {
				if (SwingUtilities.isRightMouseButton(event)) {
					JPopupMenu menu = GraphicEditorAdaptator
							.showPopupMenu(event.getPoint());
					if (menu != null) {
						menu.show(graph, event.getX(), event.getY());
					}
				} else {
					if (!event.isConsumed() && graph.isMoveable()) {
						if (handles != null) { // Find Handle
							for (int i = handles.length - 1; i >= 0; i--) {
								if (handles[i] != null) {
									handles[i].mousePressed(event);
									// if (event.isConsumed()) {
									activeHandle = handles[i];
									// return;
									// }
								}
							}
						}
						if (views != null) { // Start Move if over cell
							Point2D screenPoint = event.getPoint();
							Point2D pt = graph.fromScreen((Point2D) screenPoint
									.clone());
							CellView view = findViewForPoint(pt);
							if (view != null) {
								if (snapSelectedView) {

									Rectangle2D bounds = view.getBounds();
									start = graph.toScreen(new Point2D.Double(
											bounds.getX(), bounds.getY()));
									snapStart = graph.snap((Point2D) start
											.clone());
									_mouseToViewDelta_x = screenPoint.getX()
											- start.getX();
									_mouseToViewDelta_y = screenPoint.getY()
											- start.getY();
								} else { // this is the original RootHandle's
									// mode.

									snapStart = graph
											.snap((Point2D) screenPoint.clone());
									_mouseToViewDelta_x = snapStart.getX()
											- screenPoint.getX();
									_mouseToViewDelta_y = snapStart.getY()
											- screenPoint.getY();
									start = (Point2D) snapStart.clone();
								}
								last = (Point2D) start.clone();
								snapLast = (Point2D) snapStart.clone();
								isContextVisible = contextViews != null
										&& contextViews.length < MAXCELLS
										&& (!event.isControlDown() || !graph
												.isCloneable());
								event.consume();
							}
						}
						// Analyze for common parent
						if (graph.isMoveIntoGroups()
								|| graph.isMoveOutOfGroups()) {
							Object[] cells = context.getCells();
							Object ignoreGroup = graph.getModel().getParent(
									cells[0]);
							for (int i = 1; i < cells.length; i++) {
								Object tmp = graph.getModel().getParent(
										cells[i]);
								if (ignoreGroup != tmp) {
									ignoreGroup = null;
									break;
								}
							}
							if (ignoreGroup != null)
								ignoreTargetGroup = graph.getGraphLayoutCache()
								.getMapping(ignoreGroup, false);
						}
					}
				}
			}

			public void mouseDragged(MouseEvent event) {
				if (!SwingUtilities.isRightMouseButton(event)) {

					boolean constrained = isConstrainedMoveEvent(event);
					boolean spread = false;
					Rectangle2D dirty = null;
					if (firstDrag && graph.isDoubleBuffered()
							&& cachedBounds == null) {
						initOffscreen();
						firstDrag = false;
					}

					if (event != null && !event.isConsumed()) {
						if (activeHandle != null) // Paint Active Handle
							activeHandle.mouseDragged(event);
						// Invoke Mouse Dragged
						/* else */if (start != null) { // Move Cells
							Graphics g = (offgraphics != null) ? offgraphics
									: graph.getGraphics();
							Point ep = event.getPoint();
							Point2D point = new Point2D.Double(ep.getX()
									- _mouseToViewDelta_x, ep.getY()
									- _mouseToViewDelta_y);
							Point2D snapCurrent = graph.snap(point);
							current = snapCurrent;
							int thresh = graph.getMinimumMove();
							double dx = current.getX() - start.getX();
							double dy = current.getY() - start.getY();
							if (isMoving || Math.abs(dx) > thresh
									|| Math.abs(dy) > thresh) {
								boolean overlayed = false;
								isMoving = true;
								if (disconnect == null
										&& graph.isDisconnectOnMove())
									disconnect = context
									.disconnect(graphLayoutCache
											.getAllDescendants(views));
								// Constrained movement
								double totDx = current.getX() - start.getX();
								double totDy = current.getY() - start.getY();
								dx = current.getX() - last.getX();
								dy = current.getY() - last.getY();
								Point2D constrainedPosition = constrainDrag(
										event, totDx, totDy, dx, dy);
								if (constrainedPosition != null) {
									dx = constrainedPosition.getX();
									dy = constrainedPosition.getY();
								}
								double scale = graph.getScale();
								dx = dx / scale;
								dy = dy / scale;
								// Start Drag and Drop
								if (graph.isDragEnabled() && !isDragging)
									startDragging(event);
								if (dx != 0 || dy != 0) {
									if (offgraphics != null
											|| !graph.isXorEnabled()) {
										dirty = graph.toScreen(AbstractCellView
												.getBounds(views));
										Rectangle2D t = graph
												.toScreen(AbstractCellView
														.getBounds(contextViews));
										if (t != null)
											dirty.add(t);
									}
									if (graph.isXorEnabled()) {
										g.setColor(graph.getForeground());

										// use 'darker' to force XOR to
										// distinguish
										// between
										// existing background elements during
										// drag
										// http://sourceforge.net/tracker/index.php?func=detail&aid=677743&group_id=43118&atid=435210
										g.setXORMode(graph.getBackground()
												.darker());
									}
									if (!snapLast.equals(snapStart)
											&& (offgraphics != null || !blockPaint)) {
										if (graph.isXorEnabled()) {
											overlay(g);
										}
										overlayed = true;
									}
									isContextVisible = (!event.isControlDown() || !graph.isCloneable()) && contextViews != null && (contextViews.length < MAXCELLS);
									blockPaint = false;
									if (constrained && cachedBounds == null) {
										// Reset Initial Positions
										CellView[] all = graphLayoutCache.getAllDescendants(views);
										for (int i = 0; i < all.length; i++) {
											CellView orig = graphLayoutCache.getMapping(all[i].getCell(),false);
											AttributeMap attr = orig.getAllAttributes();
											all[i].changeAttributes(graph.getGraphLayoutCache(),(AttributeMap) attr.clone());
											all[i].refresh(graph.getGraphLayoutCache(),context, false);
										}
									}
									if (cachedBounds != null) {
										if (dirty != null) {
											dirty.add(cachedBounds);
										}
										cachedBounds.setFrame(
												cachedBounds.getX() + dx
												* scale,
												cachedBounds.getY() + dy
												* scale,
												cachedBounds.getWidth(),
												cachedBounds.getHeight());
										if (dirty != null) {
											dirty.add(cachedBounds);
										}
									} else {
										// Translate
										GraphLayoutCache.translateViews(views,
												dx, dy);
										if (views != null)
											graphLayoutCache.update(views);
										if (contextViews != null)
											graphLayoutCache
											.update(contextViews);
									}
									// Change preferred size of graph
									if (graph.isAutoResizeGraph()&& (event.getX() > graph.getWidth()- SCROLLBORDER
											|| event.getY() > graph.getHeight()- SCROLLBORDER)) {

										int SPREADSTEP = 25;
										Rectangle view = null;
										if (graph.getParent() instanceof JViewport)
											view = ((JViewport) graph
													.getParent()).getViewRect();
										if (view != null) {
											if (view.contains(event.getPoint())) {
												if (view.x + view.width
														- event.getPoint().x < SCROLLBORDER) {
													preferredSize.width = Math
															.max(preferredSize.width,
																	(int) view
																	.getWidth())
																	+ SPREADSTEP;
													spread = true;
												}
												if (view.y + view.height
														- event.getPoint().y < SCROLLBORDER) {
													preferredSize.height = Math
															.max(preferredSize.height,
																	(int) view
																	.getHeight())
																	+ SPREADSTEP;
													spread = true;
												}
												if (spread) {
													graph.revalidate();
													autoscroll(graph,
															event.getPoint());
													if (graph
															.isDoubleBuffered())
														initOffscreen();
												}
											}
										}
									}

									// Move into groups
									Rectangle2D ignoredRegion = (ignoreTargetGroup != null) ? (Rectangle2D) ignoreTargetGroup.getBounds().clone() : null;
											if (targetGroup != null) {
												Rectangle2D tmp = graph
														.toScreen((Rectangle2D) targetGroup
																.getBounds().clone());
												if (dirty != null)
													dirty.add(tmp);
												else
													dirty = tmp;
											}
											targetGroup = null;
											if (graph.isMoveIntoGroups()
													&& (ignoredRegion == null || !ignoredRegion.intersects(AbstractCellView.getBounds(views)))) {
												targetGroup = (event.isControlDown()) ? null
														: findUnselectedInnermostGroup(
																snapCurrent.getX()
																/ scale,
																snapCurrent.getY()
																/ scale);
												if (targetGroup == ignoreTargetGroup)
													targetGroup = null;
											}
											if (!snapCurrent.equals(snapStart)
													&& (offgraphics != null || !blockPaint)
													&& !spread) {
												if (graph.isXorEnabled()) {
													overlay(g);
												}
												overlayed = true;
											}
											if (constrained)
												last = (Point2D) start.clone();
											last.setLocation(last.getX() + dx * scale,
													last.getY() + dy * scale);
											// It is better to translate
											// <code>last<code> by a
											// scaled dx/dy
											// instead of making it to be the
											// <code>current<code> (as in prev version),
											// so that the view would be catching up
											// with a
											// mouse pointer
											snapLast = snapCurrent;
											if (overlayed && (offgraphics != null || !graph
													.isXorEnabled())) {
												if (dirty == null) {
													dirty = new Rectangle2D.Double();
												}
												dirty.add(graph
														.toScreen(AbstractCellView
																.getBounds(views)));
												Rectangle2D t = graph
														.toScreen(AbstractCellView
																.getBounds(contextViews));
												if (t != null)
													dirty.add(t);
												// TODO: Should use real ports if
												// portsVisible
												// and check if ports are scaled
												int border = PortView.SIZE + 4;
												if (graph.isPortsScaled())
													border = (int) (graph.getScale() * border);
												int border2 = border / 2;
												dirty.setFrame(dirty.getX() - border2,
														dirty.getY() - border2,
														dirty.getWidth() + border,
														dirty.getHeight() + border);
												double sx1 = Math.max(0, dirty.getX());
												double sy1 = Math.max(0, dirty.getY());
												double sx2 = sx1 + dirty.getWidth();
												double sy2 = sy1 + dirty.getHeight();
												if (isDragging && !DNDPREVIEW) // BUG IN
													// 1.4.0
													// (FREEZE)
													return;
												if (offgraphics != null) {
													graph.drawImage((int) sx1,
															(int) sy1, (int) sx2,
															(int) sy2, (int) sx1,
															(int) sy1, (int) sx2,
															(int) sy2);
												} else {
													graph.repaint((int) dirty.getX(),
															(int) dirty.getY(),
															(int) dirty.getWidth() + 1,
															(int) dirty.getHeight() + 1);
												}
											}
								}
							} // end if (isMoving or ...)
						} // end if (start != null)
					} else if (event == null)
						graph.repaint();

				}
			}

			@SuppressWarnings("unchecked")
			public void mouseReleased(MouseEvent event) {

				graph.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				if (!SwingUtilities.isRightMouseButton(event)) {

					try {
						if (event != null && !event.isConsumed()) {

							if (activeHandle != null) {
								activeHandle.mouseReleased(event);
								activeHandle = null;
							} /* else */
							if (isMoving && !event.getPoint().equals(start)) {
								
								if (cachedBounds != null) {

									Point ep = event.getPoint();
									Point2D point = new Point2D.Double(
											ep.getX() - _mouseToViewDelta_x,
											ep.getY() - _mouseToViewDelta_y);
									Point2D snapCurrent = graph.snap(point);

									double dx = snapCurrent.getX()
											- start.getX();
									double dy = snapCurrent.getY()
											- start.getY();

									if (!graph.isMoveBelowZero()&& initialLocation.getX() + dx < 0)
										dx = -1 * initialLocation.getX();
									if (!graph.isMoveBelowZero()&& initialLocation.getY() + dy < 0)
										dy = -1 * initialLocation.getY();

									Point2D tmp = graph.fromScreen(new Point2D.Double(dx,dy));
									GraphLayoutCache.translateViews(views,tmp.getX(), tmp.getY());
								}
								CellView[] all = graphLayoutCache.getAllDescendants(views);
								Map attributes = GraphConstants.createAttributes(all, null);
								if (event.isControlDown()&& graph.isCloneable()) { // Clone
									// Cells
									Object[] cells = graph.getDescendants(graph.order(context.getCells()));
									// Include properties from hidden cells
									Map hiddenMapping = graphLayoutCache.getHiddenMapping();
									for (int i = 0; i < cells.length; i++) {
										Object witness = attributes.get(cells[i]);
										if (witness == null) {
											CellView view = (CellView) hiddenMapping.get(cells[i]);
											if (view != null
													&& !graphModel.isPort(view
															.getCell())) {
												// TODO: Clone required? Same in
												// GraphConstants.
												AttributeMap attrs = (AttributeMap) view
														.getAllAttributes()
														.clone();
												// Maybe translate?
												// attrs.translate(dx, dy);
												attributes.put(cells[i],
														attrs.clone());
											}
										}
									}
									ConnectionSet cs = ConnectionSet.create(
											graphModel, cells, false);
									ParentMap pm = ParentMap.create(graphModel,
											cells, false, true);
									cells = graphLayoutCache.insertClones(
											cells, graph.cloneCells(cells),
											attributes, cs, pm, 0, 0);

								} else if (graph.isMoveable()) { // Move Cells
									ParentMap pm = null;

									// Moves into group
									if (targetGroup != null) {

										pm = new ParentMap(context.getCells(),
												targetGroup.getCell());
									} else if (graph.isMoveOutOfGroups()&&
											(ignoreTargetGroup != null && !ignoreTargetGroup.getBounds().intersects(AbstractCellView.getBounds(views)))) {

										pm = new ParentMap(context.getCells(),	null);
									}
									graph.getGraphLayoutCache().edit(attributes,null , pm, null);
								}
								event.consume();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						ignoreTargetGroup = null;
						targetGroup = null;
						isDragging = false;
						disconnect = null;
						firstDrag = true;
						current = null;
						start = null;
					}

					if (this.isMoving) {
						GraphicEditorAdaptator.fireMoveTasks();
					}
				}
			}
		}
	}

	public void notifLocalisationModification() {
	}

	public PieMenuCreateTask getMenuTask() {
		return menuTask;
	}

	public PieMenuEditExecutant getMenuEditExecutant() {
		return menuEditExecutant;
	}

	public PieMenuEditDecomposition getMenuEditDecomposition() {
		return menuEditDecomposition;
	}

	public PieMenuEdition getMenuEdition() {
		return menuEdition;
	}
	
	public KMADEUndoRedoManager getUndoRedoManager(){
		return undoManager;
	}

	
}

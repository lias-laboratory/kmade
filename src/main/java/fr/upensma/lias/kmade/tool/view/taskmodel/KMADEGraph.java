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

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import javax.swing.JViewport;
import javax.swing.ToolTipManager;
import javax.swing.event.MouseInputAdapter;


import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView.MyVertexRenderer;

/**
 * @author Mickael BARON
 */
public class KMADEGraph extends org.jgraph.JGraph {

    private static final long serialVersionUID = -9005217133425447683L;

    protected Point2D start, current;

    protected PortView port, firstPort;

    protected boolean release;
    protected boolean interupt;
    private boolean curseurMove;
    private monThread thread;

    public KMADEGraph() {
	super();

	MouseInputAdapter mia = new MouseInputAdapter() {
	    int m_XDifference, m_YDifference;
	    // boolean m_dragging;
	    Container c;

	    public void mouseDragged(MouseEvent e) {
		curseurMove = false;

		c = KMADEGraph.this.getParent();

		// si on bouge on arr�te le thread
		try {
		    thread.interrupt();
		    interupt = true;
		    thread = null;

		} catch (Exception ex) {

		}

		// on est en mode "grab"
		if (getCursor().getType() == Cursor.MOVE_CURSOR) {
		    curseurMove = true;
		    if (getSelectionCell() == null
			    || getSelectionCellAt(e.getPoint()) == null) {
			if (c instanceof JViewport) {
			    JViewport jv = (JViewport) c;
			    Point p = jv.getViewPosition();
			    int newX = p.x - (e.getX() - m_XDifference);
			    int newY = p.y - (e.getY() - m_YDifference);

			    int maxX = getWidth() - jv.getWidth();
			    int maxY = getHeight() - jv.getHeight();
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

	    }

	    public void mousePressed(MouseEvent e) {

		Object obj = getSelectionCellAt(e.getPoint());

		release = false;

		if (port != null && isPortsVisible()) {
		    start = toScreen(port.getLocation());
		    firstPort = port;

		    return;
		}

		if (thread == null) {
		    if (obj == null) {
			// on lance le thread pour la temporisation
			thread = new monThread(this, e);
			try {
			    thread.start();

			} catch (Exception ex) {

			}
		    }
		} else {

		    if (!interupt) {

			if (getSelectionCell() == null) {

			    setCursor(new Cursor(Cursor.MOVE_CURSOR));

			    m_XDifference = e.getX();
			    m_YDifference = e.getY();
			} else if (getSelectionCellAt(e.getPoint()) == null) {
			    setSelectionCell(null);

			    setCursor(new Cursor(Cursor.MOVE_CURSOR));

			    m_XDifference = e.getX();
			    m_YDifference = e.getY();
			}
			thread = null;

			return;
		    } else {

		    }

		}

	    }

	    public void mouseReleased(MouseEvent e) {

		try {
		    thread.interrupt();
		    interupt = true;
		    thread = null;
		} catch (Exception ex) {

		}
		release = true;

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    }
	};
	addMouseMotionListener(mia);
	addMouseListener(mia);
	addMouseWheelListener(new MouseWheelListener() {
	    public void mouseWheelMoved(MouseWheelEvent e) {

		double zoom = getScale();

		/*
		 * ici on veut zoomer lorsque l'on bouge la molette vers le haut
		 * et d�zoomer lorsqu'on la bouge vers le bas
		 */
		double wheelRotation = -((double) e.getWheelRotation()) / 4;

		// KMADEHistoryMessageManager.printError("wheelRotation = " + wheelRotation);

		if (zoom > 1) {
		    zoom += wheelRotation;
		} else if (zoom == 1) {
		    /*
		     * if (wheelRotation == 1) { zoom+=0.5; } else { zoom-=0.33;
		     * }
		     */
		    zoom = (wheelRotation == 0.25 ? zoom + wheelRotation : 0.75);
		} else if (zoom < 1) {
		    if (zoom >= 0.5) {
			zoom += wheelRotation;
		    } else if (wheelRotation == 0.25) {
			zoom *= 2;
		    } else {
			zoom /= 2;
		    }

		}

		// on zoom en centrant sur la position du curseur
		setScale(zoom, getMousePosition());

		CellView[] views = getGraphLayoutCache().getCellViews();

		for (int i = 0; i < views.length; i++) {
		    if (views[i] instanceof KMADEVertexView) {
			((MyVertexRenderer) ((KMADEVertexView) views[i])
				.getRenderer()).paintTask(getGraphics());
		    }
		}

	    }
	});
    }

    public KMADEGraph(GraphModel model) {
	super(model);
	ToolTipManager.sharedInstance().registerComponent(this);
	MouseInputAdapter mia = new MouseInputAdapter() {
	    int m_XDifference, m_YDifference;
	    // boolean m_dragging;
	    Container c;

	    public void mouseDragged(MouseEvent e) {

		c = KMADEGraph.this.getParent();

		try {
		    thread.interrupt();
		    interupt = true;
		    thread = null;

		} catch (Exception ex) {

		}

		if (getCursor().getType() == Cursor.MOVE_CURSOR) {

		    if (getSelectionCell() == null
			    || getSelectionCellAt(e.getPoint()) == null) {
			if (c instanceof JViewport) {
			    JViewport jv = (JViewport) c;
			    Point p = jv.getViewPosition();
			    int newX = p.x - (e.getX() - m_XDifference);
			    int newY = p.y - (e.getY() - m_YDifference);

			    int maxX = getWidth() - jv.getWidth();
			    int maxY = getHeight() - jv.getHeight();
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

	    }

	    public void mousePressed(MouseEvent e) {

		Object obj = getSelectionCellAt(e.getPoint());

		if (port != null && isPortsVisible()) {
		    start = toScreen(port.getLocation());
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

			if (getSelectionCell() == null) {

			    setCursor(new Cursor(Cursor.MOVE_CURSOR));

			    m_XDifference = e.getX();
			    m_YDifference = e.getY();
			} else if (getSelectionCellAt(e.getPoint()) == null) {
			    setSelectionCell(null);

			    setCursor(new Cursor(Cursor.MOVE_CURSOR));

			    m_XDifference = e.getX();
			    m_YDifference = e.getY();
			}
			thread = null;

			return;
		    }

		}

	    }

	    public void mouseReleased(MouseEvent e) {

		try {
		    thread.interrupt();
		    interupt = true;
		    thread = null;
		} catch (Exception ex) {

		}
		release = true;

		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    }
	};

	addMouseMotionListener(mia);
	addMouseListener(mia);
	addMouseWheelListener(new MouseWheelListener() {
	    public void mouseWheelMoved(MouseWheelEvent e) {

		double zoom = getScale();
		double tmp;

		/*
		 * ici on veut zoomer lorsque l'on bouge la molette vers le haut
		 * et d�zoomer lorsqu'on la bouge vers le bas
		 */
		// double wheelRotation = -((double) e.getWheelRotation()) / 4;

		if (e.getWheelRotation() < 0) {
		    tmp = zoom * (KMADEConstant.WHEEL_ZOOM + 1);
		} else {
		    tmp = zoom / (KMADEConstant.WHEEL_ZOOM + 1);
		}

		if (tmp > 0.001 && tmp < 7) {
		    zoom = tmp;
		}

		// if (zoom > 1) {
		// zoom += wheelRotation;
		// } else if (zoom == 1) {
		// /*
		// * if (wheelRotation == 1) { zoom+=0.5; } else { zoom-=0.33; }
		// */
		// zoom = (wheelRotation == 0.25 ? zoom + wheelRotation : 0.75);
		// } else if (zoom < 1) {
		// if (zoom >= 0.5) {
		// zoom += wheelRotation;
		// } else if (wheelRotation == 0.25) {
		// zoom *= 2;
		// } else {
		// zoom /= 2;
		// }
		//
		// }

		CellView[] views = getGraphLayoutCache().getCellViews();

		if (getSelectionCell() != null) {

		    if (getSelectionCell() instanceof KMADEDefaultGraphCell) {
			for (int i = 0; i < views.length; i++) {
			    if (views[i] instanceof KMADEVertexView) {
				if (((KMADEVertexView) views[i]).getCell() == getSelectionCell()) {
				    Point p = new Point(
					    (int) ((KMADEVertexView) views[i])
						    .getBounds().getCenterX(),
					    (int) ((KMADEVertexView) views[i])
						    .getBounds().getCenterY());
				    setScale(zoom, p);
				    return;
				}
			    }
			}
		    }

		}

		// on zoom en centrant sur la position du curseur
		setScale(zoom, getMousePosition());

	    }
	});
    }

    public String getToolTipText(MouseEvent event) {
	Object cell = getFirstCellForLocation(event.getX(), event.getY());
	Object[] views = this.getGraphLayoutCache().getAllViews();

	KMADEVertexView select = null;
	for (int i = 0; i < views.length; i++) {
	    if (views[i] instanceof KMADEVertexView) {
		KMADEVertexView vertexView = (KMADEVertexView) views[i];
		Object tmp = vertexView.getCell();

		if (tmp == cell) {
		    select = (KMADEVertexView) views[i];
		    break;
		}

	    }
	}

	if (select != null) {

	    if (getScale() < 0.75) {
		if (select.getRectTache() != null
			&& select.getRectTache().contains(event.getPoint())) {
		    return ((KMADEDefaultGraphCell) cell).getTask().getName();
		}
	    }

	    if (select.getRectTache() != null
		    && select.getRectTache().contains(event.getPoint())) {
		if (select.isTacheIsolee()) {
		    return KMADEConstant.NO_ALONE_MESSAGE_PROBLEM;
		}
	    }

	    if (select.getRectNom() != null
		    && select.getRectNom().contains(event.getPoint())) {

		if (!select.isNomCorrect()) {
		    return KMADEConstant.TASK_HAS_NO_NAME_MESSAGE_PROBLEM;
		}

	    }

	    if (select.getRectOpe() != null
		    && select.getRectOpe().contains(event.getPoint())) {
		if (!select.isCoherent()) {
		    if (!select.isFilsCoherent()) {
			return KMADEConstant.NO_ONLY_ONE_SUBTASK_MESSAGE_PROBLEM;
		    } else {
			return KMADEConstant.INVALID_DECOMPOSITION_MESSAGE_PROBLEM;
		    }
		}
	    }

	    /* Pre ok! */
	    if (select.getRectPre() != null
		    && select.getRectPre().contains(event.getPoint())) {

		/*
		 * S'il y a une description textuelle, on l'affiche Sinon, on
		 * affiche la condition telle qu'elle est �crite
		 */
		if (((KMADEDefaultGraphCell) cell).getTask().getPreExpression()
			.getDescription().equals("")) {
		    return (KMADEConstant.VERTEX_PRECONDITION + ": " + ((KMADEDefaultGraphCell) cell)
			    .getTask().getPreExpression().getName());
		}
		return (KMADEConstant.VERTEX_PRECONDITION + ": " + ((KMADEDefaultGraphCell) cell)
			.getTask().getPreExpression().getDescription());
	    }

	    /* Act ok! */
	    if (select.getRectAct() != null
		    && select.getRectAct().contains(event.getPoint())) {
		String S_act = KMADEConstant.VERTEX_ACTOR + ": ";
		int t_list = ((KMADEDefaultGraphCell) cell).getTask()
			.getActeursName().size();

		if (t_list != 0) {
		    S_act = ((KMADEDefaultGraphCell) cell).getTask()
			    .getActeursName().get(0).toString();
		    for (int i = 1; i < t_list; i++) {
			String s_tmp = ((KMADEDefaultGraphCell) cell).getTask()
				.getActeursName().get(i).toString();
			if (i == t_list) {
			    S_act = S_act + s_tmp;
			} else {
			    S_act = S_act + "," + s_tmp;
			}
		    }
		}

		return S_act;
	    }
	    /* Obj */
	    /*
	     * if (select.rectObj.contains(event.getPoint())) { Set set =
	     * InterfaceExpressJava.bdd.keySet(); for (Iterator i =
	     * set.iterator(); i.hasNext();) { Oid oid = (Oid) i.next(); Object
	     * o = InterfaceExpressJava.bdd.prendre(oid); //
	     * GraphicEditorAdaptator
	     * .getMainFrame().getEntityDialog().writeInTextArea
	     * (((Entity)o).toSPF()); return
	     * ("OBJ: "+((Entity)o).toSPF().toString()); } }
	     */

	    /* Rep(iteration) ok ! */
	    if (select.getRectRep() != null
		    && select.getRectRep().contains(event.getPoint())) {
		if (((KMADEDefaultGraphCell) cell).getTask().getIteExpression()
			.getDescription().equals("")) {
		    return (KMADEConstant.VERTEX_ITERATION + ": " + ((KMADEDefaultGraphCell) cell)
			    .getTask().getIteExpression().toSPF().toString());
		}
		return (KMADEConstant.VERTEX_ITERATION + ": " + ((KMADEDefaultGraphCell) cell)
			.getTask().getIteExpression().getDescription());
	    }
	    /* Evt In ok! */
	    if (select.getRectEvenementIn() != null
		    && select.getRectEvenementIn().contains(event.getPoint())) {
		return (KMADEConstant.VERTEX_EVENT_IN_LETTER + ((KMADEDefaultGraphCell) cell)
			.getTask().getDeclencheurName());
	    }

	    /* Evt Out ok! */
	    if (select.getRectEvenementOut() != null
		    && select.getRectEvenementOut().contains(event.getPoint())) {
		String S_evt_out = KMADEConstant.VERTEX_EVENT_OUT_LETTER;
		int t_list = ((KMADEDefaultGraphCell) cell).getTask()
			.getEventsName().size();

		if (t_list != 0) {
		    S_evt_out = ((KMADEDefaultGraphCell) cell).getTask()
			    .getEventsName().get(0).toString();
		    for (int i = 1; i < t_list; i++) {
			String s_tmp = ((KMADEDefaultGraphCell) cell).getTask()
				.getEventsName().get(i).toString();
			if (i == t_list) {
			    S_evt_out = S_evt_out + s_tmp;
			} else {
			    S_evt_out = S_evt_out + "," + s_tmp;
			}
		    }
		}

		return S_evt_out;
	    }

	    /* Post ok! */

	    if (select.getRectPost() != null
		    && select.getRectPost().contains(event.getPoint())) {
		if (((KMADEDefaultGraphCell) cell).getTask()
			.getEffetsDeBordExpression().getDescription()
			.equals("")) {
		    return (KMADEConstant.VERTEX_EFFETSDEBORD + ": " + ((KMADEDefaultGraphCell) cell)
			    .getTask().getEffetsDeBordExpression().toSPF()
			    .toString());
		}
		return (KMADEConstant.VERTEX_EFFETSDEBORD + ": " + ((KMADEDefaultGraphCell) cell)
			.getTask().getEffetsDeBordExpression().getDescription());
	    }

	} else {

	}

	if (cell instanceof KMADEDefaultGraphCell) {
	    // unused KMADEDefaultGraphCell tmp = (KMADEDefaultGraphCell) cell;

	    return ((KMADEDefaultGraphCell) cell).getToolTipString();
	}
	return null;
    }

    class monThread extends Thread {
	private MouseInputAdapter mouseIA;
	private MouseEvent event;

	public monThread(MouseInputAdapter mia, MouseEvent e) {
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

    public boolean isCurseurMove() {
	return curseurMove;
    }
}

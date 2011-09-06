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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ExpressDB;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ListeAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.PileAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressConcreteAttribut;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell.AbstractObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell.AbstractObjectVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ArrayCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ArrayVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ConcreteObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ConcreteObjectVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ListCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ListVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SetCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SetVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SingletonCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SingletonVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.StackCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.StackVertexView;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.ObjectDialogViewAdaptator;

/**
 * Class allowing the user to create concrete objects by a system of drag and drop and to manage the objects in the groups.
 * 
 * @author Joachim TROUVERIE 
 */
public class ConcreteObjectPanel extends JPanel implements MouseMotionListener,
	MouseListener {

    private static final long serialVersionUID = 633648039983036820L;

    private JSplitPane splitPane;

    private JScrollPane west, east;

    private JGraph concreteObjectGraph = new JGraph(new DefaultGraphModel());

    private JGraph concreteObjectCreation = new JGraph(new DefaultGraphModel());

    private Object selected = null;

    /**
     * Constructor for the concrete objects panel
     * 
     * @param data
     *            base to create and place the objects already created
     */
    public ConcreteObjectPanel(ExpressDB bdd) {
	super();

	// Drag and drop
	concreteObjectGraph.setDropEnabled(true);
	concreteObjectCreation.setDragEnabled(true);
	concreteObjectGraph.addMouseListener(this);
	concreteObjectGraph.addMouseMotionListener(this);

	List<ObjetAbstrait> abstracts = new ArrayList<ObjetAbstrait>();
	Set<Oid> set = bdd.keySet();
	Iterator<Oid> i;
	for (i = set.iterator(); i.hasNext();) {
	    Oid oid = (Oid) i.next();
	    if (bdd.prendre(oid) instanceof ObjetAbstrait)
		abstracts.add((ObjetAbstrait) bdd.prendre(oid));
	}

	// Panels creation
	this.setLayout(new BorderLayout());

	// Cell views
	this.concreteObjectGraph.getGraphLayoutCache().setFactory(
		new DefaultCellViewFactory() {

		    private static final long serialVersionUID = -7686258114177267374L;

		    public CellView createView(GraphModel gM, Object cell) {

			if (cell instanceof ConcreteObjectCell) {
			    return new ConcreteObjectVertexView(
				    (ConcreteObjectCell) cell,
				    concreteObjectGraph);
			} else if (cell instanceof GroupDefaultGraphCell) {

			    if (cell instanceof SetCell) {
				return new SetVertexView((SetCell) cell,
					concreteObjectGraph);
			    } else if (cell instanceof ListCell) {
				return new ListVertexView((ListCell) cell,
					concreteObjectGraph);
			    } else if (cell instanceof StackCell) {
				return new StackVertexView((StackCell) cell,
					concreteObjectGraph);
			    } else if (cell instanceof ArrayCell) {
				return new ArrayVertexView((ArrayCell) cell,
					concreteObjectGraph);
			    } else if (cell instanceof SingletonCell) {
				return new SingletonVertexView(
					(SingletonCell) cell,
					concreteObjectGraph);
			    } else
				return super.createView(gM, cell);

			} else if (cell instanceof AbstractObjectCell) {
			    ConcreteObjectCell newCell = createConcreteFromAbstract((AbstractObjectCell) cell);
			    concreteObjectGraph.getGraphLayoutCache().insert(
				    newCell);
			    Object[] aux = { cell };
			    concreteObjectGraph.getGraphLayoutCache().remove(
				    aux);
			    // Select the new cell created
			    concreteObjectGraph.clearSelection();
			    concreteObjectGraph.setSelectionCell(newCell);
			    return new ConcreteObjectVertexView(newCell,
				    concreteObjectGraph);
			} else {
			    return super.createView(gM, cell);
			}
		    }

		    // To create concrete object from abstract object
		    private ConcreteObjectCell createConcreteFromAbstract(
			    AbstractObjectCell cell) {
			ObjetAbstrait ref = ((ObjetAbstrait) ((AbstractObjectCell) cell)
				.getObject());
			Oid oidObjCon = InterfaceExpressJava.createEntity(
				"metaobjet", "ObjetConcret");
			ObjetConcret o = (ObjetConcret) InterfaceExpressJava.bdd
				.prendre(oidObjCon);
			o.setName(ref.getName() + " concret");
			o.setUtiliseParClass(ref);

			ExpressConcreteAttribut.createConcreteAttribut(o, ref);

			ConcreteObjectCell newCell = new ConcreteObjectCell(o,
				((AbstractObjectCell) cell).getX(),
				((AbstractObjectCell) cell).getY());

			Object[] cells = { cell };
			concreteObjectGraph.getGraphLayoutCache().remove(cells);
			concreteObjectCreation.getGraphLayoutCache().remove(
				cells);

			int y = 0;
			CellView[] allViews = concreteObjectCreation
				.getGraphLayoutCache().getAllViews();

			for (int i = allViews.length - 1; i >= 0; i--) {
			    if (allViews[i].getBounds().contains(0, y)) {
				if (!((AbstractObjectCell) allViews[i]
					.getCell()).getObject().equals(
					cell.getObject()))

				    y += allViews[i].getBounds().getHeight() + 10;
			    }
			}
			GraphConstants.setBounds(cell.getAttributes(),
				new Rectangle2D.Double(0, y, cell.getWidth(),
					cell.getHeight()));
			concreteObjectCreation.getGraphLayoutCache().insert(
				cell);

			newCell.edit(concreteObjectGraph);
			ObjectDialogViewAdaptator.checkSaveStatus();
			return newCell;
		    }
		});

	this.concreteObjectCreation.getGraphLayoutCache().setFactory(
		new DefaultCellViewFactory() {

		    private static final long serialVersionUID = 921433043328434236L;

		    public CellView createView(GraphModel gM, Object cell) {
			if (cell instanceof AbstractObjectCell) {
			    return new AbstractObjectVertexView(cell,
				    concreteObjectCreation);
			} else {
			    return super.createView(gM, cell);
			}
		    }
		});

	// Split Pane creation
	west = new JScrollPane(concreteObjectGraph);
	TitledBorder border = BorderFactory
		.createTitledBorder(KMADEConstant.CONCRETE_TASK_OBJECT_TABBEDPANE_TITLE_NAME);
	border.setTitleJustification(TitledBorder.CENTER);
	west.setBorder(border);

	east = new JScrollPane(concreteObjectCreation);
	TitledBorder border2 = BorderFactory
		.createTitledBorder(KMADEConstant.ABSTRACT_OBJECT_TABBEDPANE_TITLE_NAME);
	border2.setTitleJustification(TitledBorder.CENTER);
	east.setBorder(border2);

	splitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(
		JSplitPane.HORIZONTAL_SPLIT, west, east);
	splitPane.setOneTouchExpandable(true);
	splitPane.setContinuousLayout(true);
	splitPane.setResizeWeight(0.8);

	this.add(splitPane, BorderLayout.CENTER);

	// Draw the objects already created
	drawConcreteObjectsCells(abstracts);
    }

    /**
     * Method to draw the objects already created
     * 
     * @param list
     *            of abstract objects already created
     */
    public void drawConcreteObjectsCells(List<ObjetAbstrait> abstracts) {
	if (!abstracts.isEmpty()) {
	    int y = 0;
	    int y2 = 0;
	    int x = 0;
	    GroupDefaultGraphCell group = null;
	    ConcreteObjectCell object = null;
	    for (ObjetAbstrait oA : abstracts) {
		AbstractObjectCell newCell = new AbstractObjectCell(oA, 0, y);
		y += GraphConstants.getBounds(newCell.getAttributes())
			.getHeight() + 10;
		concreteObjectCreation.getGraphLayoutCache().insert(newCell);
		// Concrete Objects
		x = 0;
		y2 = 0;
		if (!oA.getInverseObjConc().isEmpty()) {
		    for (ObjetConcret oC : oA.getInverseObjConc()) {
			if (oC.getAppartientGroupe() == null) {
			    if (oC.getPoint() == null) {
				concreteObjectGraph.getGraphLayoutCache()
					.insert(new ConcreteObjectCell(oC, x,
						y2));
				x += 10;
				y2 += 10;
			    } else
				concreteObjectGraph.getGraphLayoutCache()
					.insert(new ConcreteObjectCell(oC, oC
						.getPoint()));
			}
		    }
		}
		// Groups
		if (!oA.getInverseGroupe().isEmpty()) {
		    for (Groupe g : oA.getInverseGroupe()) {

			x = 0;
			y2 = 0;

			if (g.getPoint() != null) {
			    x = g.getPoint().getX();
			    y2 = g.getPoint().getY();
			}

			if (g.getEnsemble() instanceof PileAg)
			    group = new StackCell(g, x, y2);
			else if (g.getEnsemble() instanceof EnsembleAg)
			    group = new SetCell(g, x, y2);
			else if (g.getEnsemble() instanceof ListeAg)
			    group = new ListCell(g, x, y2);
			else if (g.getEnsemble() instanceof TableauAg)
			    group = new ArrayCell(g, x, y2);
			else
			    group = new SingletonCell(g, x, y2);

			// Concrete objects
			if (!g.getEnsemble().getLstObjConcrets().isEmpty()) {
			    for (ObjetConcret o : g.getEnsemble()
				    .getLstObjConcrets()) {
				object = new ConcreteObjectCell(o, 0, 0);
				group.addCellToGroup(object);
			    }
			}
			concreteObjectGraph.getGraphLayoutCache().insert(group);
			x += GraphConstants.getBounds(group.getAttributes())
				.getWidth() + 10;
		    }
		}
	    }
	}
    }

    /**
     * @return the graph used to create concrete objects from abstract objects
     */
    public JGraph getConcreteObjectGraphCreation() {
	return this.concreteObjectCreation;
    }

    /**
     * @return the graph used to manage concrete objects into groups
     */
    public JGraph getConcreteObjectGraph() {
	return this.concreteObjectGraph;
    }

    /**
     * Method to clear the graphs
     */
    public void clearGraph() {
	CellView[] cells = concreteObjectGraph.getGraphLayoutCache()
		.getAllViews();
	for (CellView view : cells) {
	    Object[] cell = { view.getCell() };
	    concreteObjectGraph.getGraphLayoutCache().remove(cell);
	}

	cells = concreteObjectCreation.getGraphLayoutCache().getAllViews();
	for (CellView view : cells) {
	    Object[] cell = { view.getCell() };
	    concreteObjectCreation.getGraphLayoutCache().remove(cell);
	}
    }

    /**
     * Mouse Listeners
     */
    public void mousePressed(MouseEvent e) {
	selected = concreteObjectGraph.getFirstCellForLocation(e.getX(),
		e.getY());
    }

    public void mouseDragged(MouseEvent e) {
	if (selected != null && selected instanceof GroupDefaultGraphCell)
	    ((GroupDefaultVertexView) ((GroupDefaultGraphCell) selected)
		    .getInvView()).getHandle().mouseDragged(e);
    }

    public void mouseReleased(MouseEvent e) {
	concreteObjectGraph.setCursor(Cursor.getDefaultCursor());
	if (selected instanceof GroupDefaultGraphCell
		&& !GraphConstants.getBounds(
			((GroupDefaultGraphCell) selected).getAttributes())
			.contains(e.getPoint())) {

	    ((GroupDefaultVertexView) ((GroupDefaultGraphCell) selected)
		    .getInvView()).getHandle().mouseReleased(e);
	    selected = null;
	}

	// Drop in a group
	else if (selected instanceof ConcreteObjectCell) {

	    ((ConcreteObjectCell) selected).getObject().getPoint()
		    .setX((int) e.getPoint().getX());
	    ((ConcreteObjectCell) selected).getObject().getPoint()
		    .setY((int) e.getPoint().getY());

	    Object cell = null;
	    Object[] cellViews = concreteObjectGraph.getGraphLayoutCache()
		    .getAllViews();
	    for (Object view : cellViews) {
		if (view instanceof GroupDefaultVertexView) {
		    if (GraphConstants
			    .getBounds(
				    ((GroupDefaultGraphCell) ((GroupDefaultVertexView) view)
					    .getCell()).getAttributes())
			    .contains(e.getPoint())
			    && !((CellView) view).getCell().equals(selected))
			cell = ((GroupDefaultVertexView) view).getCell();
		}
	    }

	    if (cell != null && cell instanceof GroupDefaultGraphCell)
		((GroupDefaultVertexView) ((GroupDefaultGraphCell) cell)
			.getInvView()).getHandle().mouseReleased(e);
	}

	concreteObjectGraph.getGraphLayoutCache().cellViewsChanged(
		concreteObjectGraph.getGraphLayoutCache().getAllViews());
	ObjectDialogViewAdaptator.checkSaveStatus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// GroupDefaultGraphCell case
	concreteObjectCreation.clearSelection();
	if (selected instanceof GroupDefaultGraphCell
		&& SwingUtilities.isLeftMouseButton(e)) {
	    GraphicEditorAdaptator.getMainFrame().getObjectDialogView()
		    .getToolBar().setEditGroupEnabled(true);
	    ((GroupDefaultVertexView) ((GroupDefaultGraphCell) selected)
		    .getInvView()).getHandle().mousePressed(e);

	    CellView[] views = concreteObjectCreation.getGraphLayoutCache()
		    .getAllViews();
	    ObjetAbstrait oA = ((GroupDefaultGraphCell) selected).getObject()
		    .getContientObj();
	    for (CellView v : views) {
		if (((AbstractObjectCell) v.getCell()).getObject().equals(oA)) {
		    concreteObjectCreation.setSelectionCell(v.getCell());
		    return;
		}
	    }

	} else if (selected instanceof ConcreteObjectCell) {
	    ObjectDialogViewAdaptator.setEditGroupEnabled(true);
	    CellView[] views = concreteObjectCreation.getGraphLayoutCache()
		    .getAllViews();
	    ObjetAbstrait oA = ((ConcreteObjectCell) selected).getObject()
		    .getUtiliseParClass();
	    for (CellView v : views) {
		if (((AbstractObjectCell) v.getCell()).getObject().equals(oA)) {
		    concreteObjectCreation.setSelectionCell(v.getCell());
		    return;
		}
	    }
	} else
	    ObjectDialogViewAdaptator.setEditGroupEnabled(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}

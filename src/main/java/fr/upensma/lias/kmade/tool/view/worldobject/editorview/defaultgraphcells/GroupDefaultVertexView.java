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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.VertexView;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ConcreteObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.SingletonCell;

/**
 * Abstract renderer for the group cells.
 * 
 * @author Joachim TROUVERIE
 */
public abstract class GroupDefaultVertexView extends VertexView {

	protected static final long serialVersionUID = -1204954126666700549L;

	public myCellHandle cellHandle;

	protected final JGraph myGraph;

	protected String title;

	protected GroupDefaultGraphCell cell;

	protected JPanel empty, panel;

	protected JList objects;

	protected DefaultListModel listModel;

	protected JScrollPane listPanel;

	public GroupDefaultVertexView(GroupDefaultGraphCell cell, JGraph graph) {
		super(cell);
		this.myGraph = graph;
		this.cell = cell;
		cell.setInvView(this);
		if (((Groupe) cell.getObject()).getEnsemble().getInverseGroupe().getContientObj() == null)
			this.title = " ";

		else
			this.title = ((Groupe) cell.getObject()).getName();

		listModel = new DefaultListModel();
		if (!this.cell.getCellsInGroup().isEmpty()) {
			for (ConcreteObjectCell Ocell : this.cell.getCellsInGroup()) {
				listModel.addElement(Ocell);
			}
		}
		objects = new JList(listModel);
	}

	/**
	 * Call of the renderer
	 */
	public Component getRendererComponent(JGraph graph, boolean selected, boolean focus, boolean preview) {

		if (((Groupe) cell.getObject()).getEnsemble().getInverseGroupe().getContientObj() != null)
			this.title = ((Groupe) cell.getObject()).getName();

		else
			this.title = " ";

		panel = new JPanel();
		panel.setOpaque(false);

		listModel.clear();
		if (!this.cell.getCellsInGroup().isEmpty()) {
			for (ConcreteObjectCell Ocell : this.cell.getCellsInGroup()) {
				listModel.addElement(Ocell);
			}
		}
		ConcreteObjectCell empty = new ConcreteObjectCell(new ObjetConcret(), 0, 0);
		empty.setName(KMADEConstant.EMPTY_CELL_NAME);
		listModel.addElement(empty);

		panel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title));

		objects = new JList(listModel);

		objects.setMinimumSize(new Dimension(80, 40));

		return panel;
	}

	/**
	 * Method to set the size of the renderer
	 */
	public void autoSize() {
		int w = (int) objects.getPreferredSize().getWidth();
		int h = (int) (objects.getPreferredSize().getHeight());
		if (w < 80)
			w = 80;
		if (h < 40)
			h = 40;
		if (listModel.getSize() != 1) {
			listPanel.setPreferredSize(new Dimension(w, h));
			listPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		} else
			listPanel.setPreferredSize(new Dimension(80, 40));
	}

	/**
	 * @return the graph where the cell is located
	 */
	public JGraph getGraph() {
		return myGraph;
	}

	/**
	 * Method to draw an empty cell
	 * 
	 * @return an empty cell
	 */
	public JPanel drawEmpty() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(20, 20));
		panel.setBorder(new Border() {

			private final Insets insets = new Insets(1, 1, 1, 1);
			private final int length = 5;
			private final int space = 3;

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

				g.setColor(Color.RED);
				// --- draw horizontal ---
				for (int i = 0; i < width; i += length) {
					g.drawLine(i, y, i + length, y);
					g.drawLine(i, height - 1, i + length, height - 1);
					i += space;
				}
				// --- draw vertical ---
				for (int i = 0; i < height; i += length) {
					g.drawLine(0, i, 0, i + length);
					g.drawLine(width - 1, i, width - 1, i + length);
					i += space;
				}

			}

			@Override
			public boolean isBorderOpaque() {
				return false;
			}

			@Override
			public Insets getBorderInsets(Component c) {
				return insets;
			}
		});

		return panel;
	}

	/**
	 * Method to call the cell's specific handle
	 * 
	 * @return the cell's specific handle
	 */
	public CellHandle getHandle() {
		return new myCellHandle();
	}

	/**
	 * Class for cell's specific handle
	 * 
	 * @author Joachim TROUVERIE
	 */
	class myCellHandle implements CellHandle {

		protected JGraph Graph = myGraph;

		public void mousePressed(MouseEvent e) {

			int x = (int) (e.getX() - GraphConstants.getBounds(cell.getAttributes()).getX());
			int y = (int) (e.getY() - GraphConstants.getBounds(cell.getAttributes()).getY());

			int index = objects.locationToIndex(new Point(x, y));
			if (index <= listModel.getSize() - 1) {
				GraphConstants.setMoveable(cell.getAttributes(), false);
				objects.setSelectedIndex(index);
				cell.setSelectedIndex(index);
			}

		}

		public void overlay(Graphics g) {
		}

		public void mouseMoved(MouseEvent event) {
		}

		public void mouseDragged(MouseEvent event) {
			if (cell.getSelectedCell() != null
					&& !GraphConstants.getBounds(cell.getAttributes()).contains(event.getPoint())) {

				// Mouse Icon
				Graph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				double width = cell.getSelectedCell().getWidth();
				double height = cell.getSelectedCell().getHeight();
				GraphConstants.setBounds(cell.getSelectedCell().getAttributes(),
						new Rectangle2D.Double(event.getX(), event.getY(), width, height));

				// Select the new cell
				Graph.clearSelection();
				Graph.setSelectionCell(cell.getSelectedCell());
			}
		}

		public void mouseReleased(MouseEvent event) {

			if (GraphConstants.getBounds(cell.getAttributes()).contains(event.getPoint())
					&& Graph.getSelectionCell() instanceof ConcreteObjectCell) {
				if (cell.getObject().getContientObj()
						.equals((((ObjetConcret) ((ObjectDefaultGraphCell) Graph.getSelectionCell()).getObject())
								.getUtiliseParClass()))) {

					if (!(cell instanceof SingletonCell) || cell.getCellsInGroup().isEmpty()) {
						cell.addCellToGroup((ConcreteObjectCell) Graph.getSelectionCell());
						Object[] cells = Graph.getSelectionCells();

						Graph.getGraphLayoutCache().editCell(cell, cell.getAttributes());
						Graph.getGraphLayoutCache().remove(cells);
						Graph.repaint();
					} else
						return;
				} else {
					JOptionPane.showMessageDialog(this.Graph, KMADEConstant.OBJECT_INSERTION_ERROR, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			else if (!GraphConstants.getBounds(cell.getAttributes()).contains(event.getPoint())) {

				if (cell.getSelectedCell() != null) {
					Graph.getGraphLayoutCache().insert(cell.getSelectedCell());

					if (listModel.contains(cell.getSelectedCell())) {
						listModel.removeElement(cell.getSelectedCell());
						((ObjetConcret) cell.getSelectedCell().getObject()).setAppartientGroupe(null);
						cell.getObject().getEnsemble()
								.removeFromConcreteObject((ObjetConcret) cell.getSelectedCell().getObject());

						cell.getCellsInGroup().remove(cell.getSelectedCell());
						Graph.getGraphLayoutCache().editCell(cell, cell.getAttributes());
						cell.setSelectedIndex(-1);
					}
				}
				GraphConstants.setMoveable(cell.getAttributes(), true);
			}
		}

		@Override
		public void paint(Graphics g) {

		}
	};
}

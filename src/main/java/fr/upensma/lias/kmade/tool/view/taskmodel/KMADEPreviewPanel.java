package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import org.jgraph.JGraph;
import org.jgraph.event.GraphLayoutCacheEvent;
import org.jgraph.event.GraphLayoutCacheListener;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.DefaultGraphModel;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEPreviewPanel extends JPanel implements GraphLayoutCacheListener, GraphModelListener, PropertyChangeListener, AdjustmentListener {

    private static final long serialVersionUID = 1162975786596750565L;
    
	protected ComponentListener componentListener;

	protected Point pointRectangle;

	protected double n;

	protected PreviewScrollPane myPreviewScrollPane;

	protected static final Cursor MAIN = new Cursor(0);

	protected static final Cursor CLASSIQUE = new Cursor(12);

	private final KMADEGraph overviewGraph = new KMADEGraph();

	private KMADEGraph currentGraph;

	public KMADEPreviewPanel() {
		super(new BorderLayout());
		componentListener = new ComponentAdapter() {
			public void componentResized(ComponentEvent componentevent) {
				updateScale();
			}
		};

		n = 0.3D;
		setDoubleBuffered(true);

		overviewGraph.setOpaque(false);
		overviewGraph.setScale(n);
		overviewGraph.setEnabled(false);
		overviewGraph.setFocusable(false);
		myPreviewScrollPane = new PreviewScrollPane(overviewGraph);
		overviewGraph.addMouseListener(myPreviewScrollPane);
		overviewGraph.addMouseMotionListener(myPreviewScrollPane);
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		add(myPreviewScrollPane, "Center");
		setFocusable(false);
		addComponentListener(componentListener);
	}

	public KMADEGraph getOverviewGraph() {
		return overviewGraph;
	}

	public void setModelToOverviewGraph(DefaultGraphModel pModel) {
		overviewGraph.setModel(pModel);
	}

	public void setOverviewGraph(KMADEGraph jgraph) {
		this.currentGraph = jgraph;
		if (jgraph != null) {
			jgraph.getModel().addGraphModelListener(this);
			jgraph.getGraphLayoutCache().addGraphLayoutCacheListener(this);
			jgraph.addPropertyChangeListener(this);
			JScrollPane jscrollpane1 = getParentScrollPane(jgraph);
			if (jscrollpane1 != null) {
				jscrollpane1.addComponentListener(componentListener);
				jscrollpane1.getVerticalScrollBar().addAdjustmentListener(this);
				jscrollpane1.getHorizontalScrollBar().addAdjustmentListener(this);
				jscrollpane1.addPropertyChangeListener(this);
			}
			getOverviewGraph().setGraphLayoutCache(jgraph.getGraphLayoutCache());
		}
		updateScale();
	}

	public static JScrollPane getParentScrollPane(Component component) {
		for (; component != null; component = component.getParent())
			if (component instanceof JScrollPane)
				return (JScrollPane) component;
		return null;
	}

	public double getMaximumScale() {
		return n;
	}

	public PreviewScrollPane getScrollPane() {
		return myPreviewScrollPane;
	}

	protected void updateScale() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				KMADEGraph jgraph = currentGraph;
				if (jgraph != null) {
					Dimension dimension = jgraph.getPreferredSize();
					Dimension dimension1 = jgraph.getBounds().getSize();
					dimension.width = Math.max(dimension.width, dimension1.width);
					dimension1.height = Math.max(dimension.height, dimension1.height);
					double d = jgraph.getScale();
					dimension.setSize((double) (dimension.width * 1) / d,(double) (dimension.height * 1) / d);
					Dimension dimension2 = getScrollPane().getViewport().getSize();
					double d1 = dimension2.getWidth() / dimension.getWidth();
					double d2 = dimension2.getHeight() / dimension.getHeight();
					d = Math.min(Math.min(d1, d2), getMaximumScale());
					overviewGraph.setScale(d);
					repaint();
				}
			}

		});
	}

	public void graphLayoutCacheChanged(GraphLayoutCacheEvent graphlayoutcacheevent) {
		updateScale();
	}

	public void graphChanged(GraphModelEvent graphmodelevent) {
		updateScale();
	}

	public void propertyChange(PropertyChangeEvent propertychangeevent) {
		updateScale();
	}

	public void adjustmentValueChanged(AdjustmentEvent adjustmentevent) {
		myPreviewScrollPane.repaint();
	}

	public class PreviewScrollPane extends JScrollPane implements MouseListener,
			MouseMotionListener {
		private static final long serialVersionUID = -6447713169418906265L;

		protected Rectangle _fldif;

		protected Point a;

		public void paint(Graphics g) {
			JGraph jgraph = currentGraph;
			JScrollPane jscrollpane = KMADEPreviewPanel
					.getParentScrollPane(jgraph);
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, getWidth(), getHeight());
			if (jscrollpane != null && jgraph != null) {
				JViewport jviewport = jscrollpane.getViewport();
				Rectangle rectangle = jviewport.getViewRect();
				double d1 = getOverviewGraph().getScale() / jgraph.getScale();
				Dimension dimension = jgraph.getPreferredSize();
				g.setColor(getBackground());
				g.fillRect(0, 0, (int) ((double) dimension.width * d1),
						(int) ((double) dimension.height * d1));
				g.setColor(Color.WHITE);
				_fldif.setFrame((int) (rectangle.getX() * d1), (int) (rectangle
						.getY() * d1), (int) (rectangle.getWidth() * d1),
						(int) (rectangle.getHeight() * d1));
				g.fillRect(_fldif.x, _fldif.y, _fldif.width, _fldif.height);

				super.paint(g);
				g.setColor(Color.RED);
				g.drawRect(_fldif.x, _fldif.y, _fldif.width, _fldif.height);
			}
		}

		public void mouseClicked(MouseEvent mouseevent) {
		}

		public void mousePressed(MouseEvent mouseevent) {
			if (_fldif.contains(mouseevent.getX(), mouseevent.getY()))
				a = mouseevent.getPoint();
		}

		public void mouseReleased(MouseEvent mouseevent) {
			a = null;
		}

		public void mouseEntered(MouseEvent mouseevent) {
		}

		public void mouseExited(MouseEvent mouseevent) {
		}

		public void mouseDragged(MouseEvent mouseevent) {
			if (a != null) {
				JGraph jgraph = currentGraph;
				JScrollPane jscrollpane = KMADEPreviewPanel
						.getParentScrollPane(jgraph);

				if (jscrollpane != null && jgraph != null) {
					JViewport jviewport = jscrollpane.getViewport();
					Rectangle rectangle = jviewport.getViewRect();
					double d1 = overviewGraph.getScale() / jgraph.getScale();
					double d2 = ((double) mouseevent.getX() - a.getX()) / d1;
					double d3 = ((double) mouseevent.getY() - a.getY()) / d1;
					a = mouseevent.getPoint();
					d2 = rectangle.getX()
							+ (d2 <= 0.0D ? 0.0D : rectangle.getWidth()) + d2;
					d3 = rectangle.getY()
							+ (d3 <= 0.0D ? 0.0D : rectangle.getHeight()) + d3;
					java.awt.geom.Point2D.Double double1 = new java.awt.geom.Point2D.Double(
							d2, d3);
					jgraph.scrollPointToVisible(double1);
				}
			}
			this.repaint();
		}

		public void mouseMoved(MouseEvent mouseevent) {
			if (_fldif.contains(mouseevent.getPoint()))
				setCursor(KMADEPreviewPanel.CLASSIQUE);
			else
				setCursor(KMADEPreviewPanel.MAIN);
		}

		public PreviewScrollPane(JGraph jgraph) {
			super(jgraph);
			_fldif = new Rectangle();
			a = null;
			setOpaque(false);
			getViewport().setOpaque(false);
		}
	}
}

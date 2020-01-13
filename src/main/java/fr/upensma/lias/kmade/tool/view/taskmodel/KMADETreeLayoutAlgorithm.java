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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphConstants;

/**
 * @author Mickael BARON
 */
public class KMADETreeLayoutAlgorithm extends KMADEGraphLayoutAlgorithm {

	protected class PolyLine {

		private int dx;

		private PolyLine next;

		public PolyLine(int i) {
			dx = i;
		}
	}

	protected class TreeNode {

		private List<TreeNode> children;

		private int width;

		private int height;

		private int x;

		private int y;

		private int levelheight;

		private PolyLine leftContour;

		private PolyLine rightContour;

		private int depth;

		private CellView view;

		public Iterator getChildren() {
			return children.iterator();
		}

		public int getLeftWidth() {
			int i = 0;
			PolyLine polyline = leftContour;
			int j = 0;
			for (; polyline != null; polyline = polyline.next) {
				j += polyline.dx;
				if (j > 0) {
					i += j;
					j = 0;
				}
			}

			return i;
		}

		public int getRightWidth() {
			int i = 0;
			PolyLine polyline = rightContour;
			int j = 0;
			for (; polyline != null; polyline = polyline.next) {
				j += polyline.dx;
				if (j > 0) {
					i += j;
					j = 0;
				}
			}

			return i;
		}

		public int getHeight() {
			if (children.isEmpty()) {
				return levelheight;
			}
			int i = 0;
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				i = Math.max(i, ((TreeNode) iterator.next()).getHeight());
			}

			return i + levelDistance + levelheight;
		}

		public void setPosition(java.awt.Point point, int i) {
			int j = 0;
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				j = Math.max(j, ((TreeNode) iterator.next()).height);
			}

			if (point == null) {
				java.awt.geom.Rectangle2D rectangle2d = view.getBounds();
				java.awt.Rectangle rectangle1 = new java.awt.Rectangle((int) rectangle2d.getX(),
						(int) rectangle2d.getY(), (int) rectangle2d.getWidth(), (int) rectangle2d.getHeight());
				java.awt.Point point1 = rectangle1.getLocation();
				if (centerRoot) {
					int l = getLeftWidth();
					int j1 = getRightWidth();
					int k1 = getHeight();
					java.awt.Insets insets = graph.getInsets();
					if (orientation == 1) {
						rectangle1.x = l - width / 2;
						rectangle1.y = insets.top;
					} else if (orientation == 3) {
						rectangle1.x = (insets.left + k1) - width;
						rectangle1.y = l - height / 2;
					} else if (orientation == 5) {
						rectangle1.x = l - width / 2;
						rectangle1.y = insets.top + k1;
					} else if (orientation == 7) {
						rectangle1.x = insets.right;
						rectangle1.y = l - width / 2;
					}
					Object obj1 = view.getCell();
					((KMADEDefaultGraphCell) obj1).setExpressPoint(rectangle1.getBounds().x, rectangle1.getBounds().y);
					Map map1 = GraphConstants.createAttributes(obj1, "bounds", rectangle1);
					graph.getGraphLayoutCache().edit(map1, null, null, null);
					if (orientation == 7 || orientation == 3) {
						graph.setPreferredSize(new java.awt.Dimension(k1 + insets.left + insets.right,
								l + j1 + insets.top + insets.bottom));
					} else {
						graph.setPreferredSize(new java.awt.Dimension(l + j1 + insets.left + insets.right,
								k1 + insets.top + insets.bottom));
					}
					point1 = rectangle1.getLocation();
				}
				if (orientation == 7 || orientation == 3) {
					int i1 = point1.x;
					point1.x = point1.y;
					point1.y = i1;
				}
				if (orientation == 1 || orientation == 7) {
					point = new java.awt.Point(point1.x + width / 2, point1.y + height);
				} else if (orientation == 5 || orientation == 3) {
					point = new java.awt.Point(point1.x + width / 2, point1.y);
				}
				for (Iterator iterator1 = children.iterator(); iterator1.hasNext(); ((TreeNode) iterator1.next())
						.setPosition(point, j)) {
				}
				return;
			}
			if (combineLevelNodes) {
				i = levelheight;
			}
			java.awt.Rectangle rectangle = new java.awt.Rectangle(width, height);
			if (orientation == 1 || orientation == 7) {
				rectangle.x = (x + point.x) - width / 2;
				rectangle.y = point.y + levelDistance;
			} else {
				rectangle.x = (x + point.x) - width / 2;
				rectangle.y = point.y - levelDistance - levelheight;
			}
			if (alignment == 0) {
				rectangle.y += (i - height) / 2;
			} else if (alignment == 3) {
				rectangle.y += i - height;
			}
			if (orientation == 7 || orientation == 3) {
				int k = rectangle.x;
				rectangle.x = rectangle.y;
				rectangle.y = k;
				k = rectangle.width;
				rectangle.width = rectangle.height;
				rectangle.height = k;
			}
			Object obj = view.getCell();
			Map map = GraphConstants.createAttributes(obj, "bounds", rectangle);
			graph.getGraphLayoutCache().edit(map, null, null, null);
			((KMADEDefaultGraphCell) obj).setExpressPoint(rectangle.getBounds().x, rectangle.getBounds().y);
			if (orientation == 1 || orientation == 7) {
				y = point.y + levelDistance + i;
			} else {
				y = point.y - levelDistance - i;
			}
			for (Iterator iterator2 = children.iterator(); iterator2.hasNext(); ((TreeNode) iterator2.next())
					.setPosition(new java.awt.Point(x + point.x, y), j)) {
			}
		}

		@SuppressWarnings("unchecked")
		public List getNodesByLevel() {
			Object obj = new ArrayList();
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				Object obj1 = ((TreeNode) iterator.next()).getNodesByLevel();
				if (((List) (obj)).size() < ((List) (obj1)).size()) {
					Object obj2 = obj;
					obj = obj1;
					obj1 = obj2;
				}
				int i = 0;
				while (i < ((List) (obj1)).size()) {
					((List) ((List) (obj)).get(i)).addAll((List) ((List) (obj1)).get(i));
					i++;
				}
			}

			ArrayList<TreeNode> arraylist = new ArrayList<TreeNode>();
			arraylist.add(this);
			((List) (obj)).add(0, arraylist);
			return ((List) (obj));
		}

		public TreeNode(CellView cellview) {
			view = cellview;
			if (orientation == 1 || orientation == 5) {
				width = (int) cellview.getBounds().getWidth();
				height = (int) cellview.getBounds().getHeight();
			} else {
				width = (int) cellview.getBounds().getHeight();
				height = (int) cellview.getBounds().getWidth();
			}
			children = new ArrayList<TreeNode>();
			leftContour = new PolyLine(width / 2);
			rightContour = new PolyLine(width / 2);
			depth = 0;
		}
	}

	protected int alignment;

	protected int orientation;

	protected int levelDistance;

	protected int nodeDistance;

	protected boolean centerRoot;

	protected boolean combineLevelNodes;

	protected JGraph graph;

	protected Map<CellView, TreeNode> cell2node;

	public KMADETreeLayoutAlgorithm() {
		alignment = 1;
		orientation = 1;
		levelDistance = 30;
		nodeDistance = 20;
		centerRoot = false;
		combineLevelNodes = true;
		cell2node = new HashMap<CellView, TreeNode>();
	}

	// i == 0, 1 ou 3
	public void setAlignment(int i) {
		this.alignment = i;
	}

	// i == 1, 3, 5 ou 7
	public void setOrientation(int i) {
		orientation = i;
	}

	public void setLevelDistance(int i) {
		levelDistance = i;
	}

	public void setNodeDistance(int i) {
		nodeDistance = i;
	}

	public void setCenterRoot(boolean flag) {
		centerRoot = flag;
	}

	public void run(JGraph jgraph, Object aobj[], Object aobj1[]) {
		graph = jgraph;
		CellView[] acellview = jgraph.getGraphLayoutCache().getMapping(aobj1);
		List list = Arrays.asList(acellview);

		Iterator iterator = list.iterator();
		do {
			if (!iterator.hasNext()) {
				break;
			}
			if (!(iterator.next() instanceof CellView)) {
				iterator.remove();
			}
		} while (true);
		list = buildTrees(list);
		layoutTrees(list);
		if (combineLevelNodes) {
			setLevelHeights(list);
		}
		setPosition(list);
	}

	protected List buildTrees(List list) {
		ArrayList<TreeNode> arraylist = new ArrayList<TreeNode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist
				.add(buildTree((CellView) iterator.next()))) {
		}
		return arraylist;
	}

	protected TreeNode buildTree(CellView cellview) {
		List list = getMyChildren(cellview);// getChildren(cellview);
		TreeNode treenode = getTreeNode(cellview);
		TreeNode treenode1;
		for (Iterator iterator = list.iterator(); iterator.hasNext(); treenode.children.add(treenode1)) {
			CellView cellview1 = (CellView) iterator.next();
			treenode1 = buildTree(cellview1);
		}

		return treenode;
	}

	protected List getMyChildren(CellView cellview) {
		KMADEDefaultGraphCell cell = (KMADEDefaultGraphCell) cellview.getCell();
		ArrayList<KMADEDefaultGraphCell> myList = ((KMADEDefaultGraphCell) cellview.getCell()).getDirectSubCells();
		ArrayList<CellView> myViewList = new ArrayList<CellView>();
		if (cell.isSonExpanded()) {
			for (KMADEDefaultGraphCell current : myList) {
				myViewList.add(graph.getGraphLayoutCache().getMapping(current, false));
			}
		} else {
			// TODO faire une translation des cellules à la place de ne rien
			// faire
		}
		return myViewList;
	}

	protected TreeNode getTreeNode(CellView cellview) {
		Object obj = cell2node.get(cellview);
		if (obj != null) {
			return (TreeNode) obj;
		} else {
			TreeNode treenode = new TreeNode(cellview);
			cell2node.put(cellview, treenode);
			return treenode;
		}
	}

	protected void layoutTrees(List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext(); layout((TreeNode) iterator.next())) {
		}
	}

	protected void layout(TreeNode treenode) {
		KMADEDefaultGraphCell cell = (KMADEDefaultGraphCell) treenode.view.getCell();

		if (treenode.children.size() != 0 && cell.isSonExpanded()) {
			if (treenode.children.size() == 1) {
				TreeNode treenode1 = (TreeNode) treenode.children.get(0);
				treenode1.depth = treenode.depth + 1;
				layout(treenode1);
				treenode1.leftContour.dx = (treenode1.width - treenode.width) / 2;
				treenode1.rightContour.dx = (treenode1.width - treenode.width) / 2;

				treenode.leftContour.next = treenode1.leftContour;
				treenode.rightContour.next = treenode1.rightContour;
			} else {
				TreeNode treenode2;
				for (Iterator iterator = treenode.children.iterator(); iterator.hasNext(); layout(treenode2)) {
					treenode2 = (TreeNode) iterator.next();
					treenode2.depth = treenode.depth + 1;
				}

				join(treenode);
			}
		}
	}

	protected void join(TreeNode treenode) {
		int i = 0;
		for (int j = 0; j < treenode.children.size(); j++) {
			TreeNode treenode1 = (TreeNode) treenode.children.get(j);
			for (int l = j + 1; l < treenode.children.size(); l++) {
				TreeNode treenode3 = (TreeNode) treenode.children.get(l);

				int j1 = distance(treenode1.rightContour, treenode3.leftContour) / (l - j);
				i = Math.max(i, j1);
			}

		}

		i += nodeDistance;
		int k;
		if (treenode.children.size() % 2 == 0) {
			k = (treenode.children.size() / 2 - 1) * i + i / 2;
		} else {
			k = (treenode.children.size() / 2) * i;
		}
		Iterator iterator = treenode.children.iterator();
		for (int i1 = 0; iterator.hasNext(); i1++) {
			((TreeNode) iterator.next()).x = -k + i1 * i;
		}

		TreeNode treenode2 = getLeftMostX(treenode);
		TreeNode treenode4 = getRightMostX(treenode);
		treenode.leftContour.next = treenode2.leftContour;
		treenode.rightContour.next = treenode4.rightContour;
		for (int k1 = 1; k1 < treenode.children.size(); k1++) {
			TreeNode treenode5 = (TreeNode) treenode.children.get(k1);
			merge(treenode.leftContour.next, treenode5.leftContour, k1 * i + treenode.width);
		}

		for (int l1 = treenode.children.size() - 2; l1 >= 0; l1--) {
			TreeNode treenode6 = (TreeNode) treenode.children.get(l1);
			merge(treenode.rightContour.next, treenode6.rightContour, l1 * i + treenode.width);
		}

		i = ((treenode.children.size() - 1) * i) / 2;
		treenode.leftContour.next.dx += i - treenode.width / 2;
		treenode.rightContour.next.dx += i - treenode.width / 2;
	}

	protected TreeNode getLeftMostX(TreeNode treenode) {
		KMADEDefaultGraphCell cell = (KMADEDefaultGraphCell) treenode.view.getCell();

		int i = 0x7fffffff;
		TreeNode treenode1 = null;
		Iterator iterator = treenode.getChildren();
		do {
			if (!iterator.hasNext()) {
				break;
			}
			TreeNode treenode2 = (TreeNode) iterator.next();
			int j = treenode2.x - treenode2.getLeftWidth();
			if (j < i) {
				treenode1 = treenode2;
				i = j;
			}
		} while (true);
		return treenode1 == null ? (TreeNode) treenode.children.get(0) : treenode1;
	}

	protected TreeNode getRightMostX(TreeNode treenode) {
		int i = 0x80000000;
		TreeNode treenode1 = null;
		Iterator iterator = treenode.getChildren();
		do {
			if (!iterator.hasNext()) {
				break;
			}
			TreeNode treenode2 = (TreeNode) iterator.next();
			int j = treenode2.x + treenode2.getRightWidth();
			if (j > i) {
				treenode1 = treenode2;
				i = j;
			}
		} while (true);
		return treenode1 == null ? (TreeNode) treenode.children.get(0) : treenode1;
	}

	protected void merge(PolyLine polyline, PolyLine polyline1, int i) {
		do {
			if (polyline == null) {
				break;
			}
			if (polyline1.next == null) {
				return;
			}
			if (polyline.next == null) {
				polyline1 = polyline1.next;
				break;
			}
			i += polyline.dx - polyline1.dx;
			polyline = polyline.next;
			polyline1 = polyline1.next;
		} while (true);
		polyline1.dx += -i;
		polyline.next = polyline1;
	}

	protected int distance(PolyLine polyline, PolyLine polyline1) {
		int i = 0;
		int j = 0;
		for (; polyline != null && polyline1 != null; polyline1 = polyline1.next) {
			j += polyline.dx + polyline1.dx;
			if (j > 0) {
				i += j;
				j = 0;
			}
			polyline = polyline.next;
		}

		return i;
	}

	protected void setPosition(List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext(); ((TreeNode) iterator.next()).setPosition(null,
				0)) {
		}
	}

	protected void setLevelHeights(List list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			List list1 = ((TreeNode) iterator.next()).getNodesByLevel();
			int i = 0;
			int j = 0;
			while (j < list1.size()) {
				List list2 = (List) list1.get(j);
				for (int k = 0; k < list2.size(); k++) {
					i = Math.max(i, ((TreeNode) list2.get(k)).height);
				}

				for (int l = 0; l < list2.size(); l++) {
					((TreeNode) list2.get(l)).levelheight = i;
				}

				i = 0;
				j++;
			}
		}

	}

	public boolean isCombineLevelNodes() {
		return combineLevelNodes;
	}

	public void setCombineLevelNodes(boolean flag) {
		combineLevelNodes = flag;
	}

	public int getAlignment() {
		return alignment;
	}

	public boolean isCenterRoot() {
		return centerRoot;
	}

	public int getLevelDistance() {
		return levelDistance;
	}

	public int getNodeDistance() {
		return nodeDistance;
	}

	public int getOrientation() {
		return orientation;
	}
}

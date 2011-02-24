package kmade.kmade.view.print;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.View;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import kmade.kmade.adaptatorUI.PrintAdaptator;
import kmade.kmade.view.toolutilities.KMADEEnhancedSplitPane;
import kmade.kmade.view.toolutilities.SwingWorker;
import kmade.nmda.schema.tache.Tache;

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
public class KMADEPrintingUserCardsPanel extends JPanel {

	private static final long serialVersionUID = 9066622590330238274L;

	private JEditorPane editorPane;
	
	private JTree myTree = new JTree();
	
	private KMADEPrintingDialog refDialog;
 
	public static Tache[] getSelected(DefaultMutableTreeNode root) {
		ArrayList<Tache> list = new ArrayList<Tache>();
		CheckBoxNode temp = (CheckBoxNode) root.getUserObject();
		if (temp.isSelected() && !temp.isRoot()) {
			list.add(temp.getTask());
		}

		for (int i = 0; i < root.getChildCount(); i++) {
			Tache[] toro = KMADEPrintingUserCardsPanel.getSelected((DefaultMutableTreeNode) root.getChildAt(i));
			for (int j = 0; j < toro.length; j++)
				list.add(toro[j]);
		}
		return list.toArray(new Tache[list.size()]);
	}
	
	public KMADEPrintingUserCardsPanel(KMADEPrintingDialog pref) {
		super(new BorderLayout());
		refDialog = pref;
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
        HTMLEditorKit editorKit = new HTMLEditorKit();
        editorKit.install(editorPane);
        editorPane.setEditorKit(editorKit);
        editorPane.setEnabled(true);
        editorPane.setEditable(true);  
              
        myTree.setCellRenderer(new CheckBoxNodeRenderer());
        myTree.setCellEditor(new CheckBoxNodeEditor(myTree));
        myTree.setEditable(true);
        
        myTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)myTree.getLastSelectedPathComponent();
				if (node != null) {
					if (node.getUserObject() instanceof CheckBoxNode) {
						CheckBoxNode myRef = (CheckBoxNode)(node.getUserObject());
						if (myRef.getTask() != null) {
							PrintAdaptator.selectionUserCards(myRef.getTask());
							return;
						}
					}
				}
				PrintAdaptator.noSelectionUserCards();
			}
        });
        
        JSplitPane myCentralSplitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(myTree), new JScrollPane(editorPane));
        myCentralSplitPane.setOneTouchExpandable(true);
        myCentralSplitPane.setResizeWeight(0.2d);
        this.add(BorderLayout.CENTER,myCentralSplitPane);
	}
	
	public void setTasks(ArrayList<Tache> rootTasks) {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(new CheckBoxNode("Taches", false));
		for (Tache current : rootTasks) {
			KMADEPrintingUserCardsPanel.createNodesUserCards(top, current);	
		}
		this.myTree.getSelectionModel().clearSelection();
		this.myTree.setModel(new DefaultTreeModel(top));
	}
	
	public void setText(String m) {
		this.editorPane.setText(m);
	}
	
	
	private static void createNodesUserCards(DefaultMutableTreeNode top, Tache myTache) {
		DefaultMutableTreeNode subRoot = new DefaultMutableTreeNode(new CheckBoxNode(myTache,false));
		top.add(subRoot);

		if (!myTache.isLeaf()) {
			for (Tache current : myTache.getFils()) {
				KMADEPrintingUserCardsPanel.createNodesUserCards(subRoot, current);
			}
		}
	}
	
	public String getValue(DefaultMutableTreeNode node) {
		StringBuffer htmlBuffer = new StringBuffer();
		
		if (node.getUserObject() instanceof Tache) {
			htmlBuffer.append(PrintAdaptator.getSelectionUserCardsContent((Tache)node.getUserObject()));
		}
		
		for (int i = 0 ; i < node.getChildCount() ; i++) {
			DefaultMutableTreeNode temp = (DefaultMutableTreeNode)node.getChildAt(i);
			if (!temp.isLeaf()) {
				htmlBuffer.append(getValue(temp));
			} else {
				htmlBuffer.append(PrintAdaptator.getSelectionUserCardsContent((Tache)temp.getUserObject()));
			}
		}
			
		return htmlBuffer.toString();
	}
	
	public void makeTraitement() {
		JEditorPane myEditorPane = new JEditorPane();
		myEditorPane.setContentType("text/html");

		StringBuffer htmlBuffer = new StringBuffer();
		
		Tache[] tabTask = KMADEPrintingUserCardsPanel.getSelected((DefaultMutableTreeNode)myTree.getModel().getRoot());

		for (int i = 0; i < tabTask.length; i++) {
				htmlBuffer.append(PrintAdaptator.getSelectionUserCardsContent(tabTask[i]));
		}
		
		if (htmlBuffer.length() == 0)
			return;
		
		myEditorPane.setText(htmlBuffer.toString());
		
		DocumentRenderer documentRenderer = new DocumentRenderer(refDialog.getPageFormat(), KMADEPrintingDialog.getPrinterJob());
		documentRenderer.print((HTMLDocument)myEditorPane.getDocument());
	}
	
	static class DocumentRenderer implements Printable {
		protected int currentPage = -1;
		
		protected JEditorPane jeditorPane;

		protected double pageEndY = 0; 
		
		protected double pageStartY = 0;
		
		protected boolean scaleWidthToFit = true; 

		protected PageFormat pFormat;

		protected PrinterJob pJob;
		
		public DocumentRenderer(PageFormat pf, PrinterJob pj) {
			pFormat = new PageFormat();
			pJob = pj;
		}

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
			double scale = 1.0;
			Graphics2D graphics2D;
			View rootView;
			graphics2D = (Graphics2D) graphics;
			jeditorPane.setSize((int) pageFormat.getImageableWidth(), Integer.MAX_VALUE);
			jeditorPane.validate();
			rootView = jeditorPane.getUI().getRootView(jeditorPane);
			if ((scaleWidthToFit) && (jeditorPane.getMinimumSize().getWidth() > pageFormat.getImageableWidth())) {
				scale = pageFormat.getImageableWidth() / jeditorPane.getMinimumSize().getWidth();
				graphics2D.scale(scale, scale);
			}
			graphics2D.setClip((int) (pageFormat.getImageableX() / scale),
					(int) (pageFormat.getImageableY() / scale),
					(int) (pageFormat.getImageableWidth() / scale),
					(int) (pageFormat.getImageableHeight() / scale));

			if (pageIndex > currentPage) {
				currentPage = pageIndex;
				pageStartY += pageEndY;
				pageEndY = graphics2D.getClipBounds().getHeight();
			}
			graphics2D.translate(graphics2D.getClipBounds().getX(), graphics2D.getClipBounds().getY());
			Rectangle allocation = new Rectangle(0, (int) -pageStartY,
					(int) (jeditorPane.getMinimumSize().getWidth()),
					(int) (jeditorPane.getPreferredSize().getHeight()));
			if (printView(graphics2D, allocation, rootView)) {
				return Printable.PAGE_EXISTS;
			} else {
				pageStartY = 0;
				pageEndY = 0;
				currentPage = -1;
				return Printable.NO_SUCH_PAGE;
			}
		}

		public void print(HTMLDocument htmlDocument) {
			setDocument(htmlDocument);
			printDialog();
		}

		protected void printDialog() {
			SwingWorker worker = new SwingWorker() {
				public Object construct() {
					try {
						pJob.print();
					} catch (PrinterException e) {
						System.err.println(e.getMessage());
					}
					return null;
				}
			};    

			pJob.setPrintable(this,pFormat);
	        if (pJob.printDialog()) {
	            worker.start();
	        }		
		}

		protected boolean printView(Graphics2D graphics2D, Shape allocation, View view) {
			boolean pageExists = false;
			Rectangle clipRectangle = graphics2D.getClipBounds();
			Shape childAllocation;
			View childView;

			if (view.getViewCount() > 0) {
				for (int i = 0; i < view.getViewCount(); i++) {
					childAllocation = view.getChildAllocation(i, allocation);
					if (childAllocation != null) {
						childView = view.getView(i);
						if (printView(graphics2D, childAllocation, childView)) {
							pageExists = true;
						}
					}
				}
			} else {
				if (allocation.getBounds().getMaxY() >= clipRectangle.getY()) {
					pageExists = true;
					if ((allocation.getBounds().getHeight() > clipRectangle
							.getHeight())
							&& (allocation.intersects(clipRectangle))) {
						view.paint(graphics2D, allocation);
					} else {
						if (allocation.getBounds().getY() >= clipRectangle
								.getY()) {
							if (allocation.getBounds().getMaxY() <= clipRectangle
									.getMaxY()) {
								view.paint(graphics2D, allocation);
							} else {
								if (allocation.getBounds().getY() < pageEndY) {
									pageEndY = allocation.getBounds().getY();
								}
							}
						}
					}
				}
			}
			return pageExists;
		}

		public void setDocument(HTMLDocument htmlDocument) {
			jeditorPane = new JEditorPane();
			jeditorPane.setContentType("text/html");
			jeditorPane.setDocument(htmlDocument);
		}
	}
	
	static class CheckBoxNodeRenderer implements TreeCellRenderer {
		private JCheckBox leafRenderer = new JCheckBox();

		Color selectionBorderColor, selectionForeground, selectionBackground,
				textForeground, textBackground;

		protected JCheckBox getLeafRenderer() {
			return leafRenderer;
		}

		public CheckBoxNodeRenderer() {
			Font fontValue;
			fontValue = UIManager.getFont("Tree.font");
			if (fontValue != null) {
				leafRenderer.setFont(fontValue);
			}
			Boolean booleanValue = (Boolean) UIManager
					.get("Tree.drawsFocusBorderAroundIcon");
			leafRenderer.setFocusPainted((booleanValue != null)
					&& (booleanValue.booleanValue()));

			selectionBorderColor = UIManager.getColor("Tree.selectionBorderColor");
			selectionForeground = UIManager.getColor("Tree.selectionForeground");
			selectionBackground = UIManager.getColor("Tree.selectionBackground");
			textForeground = UIManager.getColor("Tree.textForeground");
			textBackground = UIManager.getColor("Tree.textBackground");
		}

		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			Component returnValue;

			String stringValue = tree.convertValueToText(value, selected, expanded,
					leaf, row, false);
			leafRenderer.setText(stringValue);
			leafRenderer.setSelected(false);

			leafRenderer.setEnabled(tree.isEnabled());

			if (selected) {
				leafRenderer.setForeground(selectionForeground);
				leafRenderer.setBackground(selectionBackground);
			} else {
				leafRenderer.setForeground(textForeground);
				leafRenderer.setBackground(textBackground);
			}

			if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
				Object userObject = ((DefaultMutableTreeNode) value)
						.getUserObject();
				if (userObject instanceof CheckBoxNode) {
					CheckBoxNode node = (CheckBoxNode) userObject;
					leafRenderer.setText(node.getText());
					leafRenderer.setSelected(node.isSelected());
				}
			}
			returnValue = leafRenderer;
			return returnValue;
		}
	}
	
	static class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

		private static final long serialVersionUID = 6727886881949023248L;

		private CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

		private DefaultMutableTreeNode node = null;
		
		private JTree tree;

		public CheckBoxNodeEditor(JTree tree) {
			this.tree = tree;
		}

		public Object getCellEditorValue() {
			CheckBoxNode userObject = (CheckBoxNode)node.getUserObject();
			JCheckBox checkbox = renderer.getLeafRenderer();
			if (node.isRoot()) {
				selectedAll(node, checkbox.isSelected());
			} else {
				userObject.setSelected(checkbox.isSelected());
				unSelectedRoot(node.getParent());
			}
			return userObject;
		}
		
		public void selectedAll(DefaultMutableTreeNode root, boolean p) {			
			CheckBoxNode temp = (CheckBoxNode) root.getUserObject();
			temp.setSelected(p);

			for (int i = 0; i < root.getChildCount(); i++) {
				this.selectedAll((DefaultMutableTreeNode)root.getChildAt(i),p);
			}
		}
		
		public void unSelectedRoot(TreeNode root) {
			if (root == null)
				return;

			CheckBoxNode temp = (CheckBoxNode)((DefaultMutableTreeNode)root).getUserObject();
			if (temp.isRoot())
				temp.setSelected(false);

			this.unSelectedRoot((DefaultMutableTreeNode)root.getParent());
		}

		public boolean isCellEditable(EventObject event) {
			boolean returnValue = false;
			if (event instanceof MouseEvent) {
				MouseEvent mouseEvent = (MouseEvent) event;
				if (mouseEvent.getClickCount() < 2) {
					return false;
				}
				TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
				if (path != null) {
					Object node = path.getLastPathComponent();
					if ((node != null) && (node instanceof DefaultMutableTreeNode)) {
						DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
						Object userObject = treeNode.getUserObject();
						returnValue = (userObject instanceof CheckBoxNode);
					}
				}
			}
			return returnValue;
		}

		public Component getTreeCellEditorComponent(JTree tree, Object value,
				boolean selected, boolean expanded, boolean leaf, int row) {
			Component editor = renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);
			node = (DefaultMutableTreeNode)value;
			
			// editor always selected / focused
			ItemListener itemListener = new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {
					if (stopCellEditing()) {
						fireEditingStopped();
					}
				}
			};
			if (editor instanceof JCheckBox) {
				((JCheckBox) editor).addItemListener(itemListener);
			}

			return editor;
		}
	}
	
	static class CheckBoxNode {
		String text;
		Tache myRef;
		boolean selected;

		public CheckBoxNode(String text, boolean selected) {
			this.text = text;
			this.selected = selected;
		}
		
		public CheckBoxNode(Tache text, boolean selected) {
			this.myRef = text;
			this.selected = selected;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean newValue) {
			selected = newValue;
		}

		public Tache getTask() {
			return myRef;
		}
		
		public String getText() {
			return myRef == null ? text : myRef.getNumero() + " : " + myRef.getName();
		}
		
		public boolean isRoot() {
			return (myRef == null);
		}

		public void setText(String newValue) {
			text = newValue;
		}

		public String toString() {
			return getClass().getName() + "[" + text + "/" + selected + "]";
		}
	}
}
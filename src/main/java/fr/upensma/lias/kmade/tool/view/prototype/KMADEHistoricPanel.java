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
package fr.upensma.lias.kmade.tool.view.prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;

/**
 * @author Thomas LACHAUME
 */
public class KMADEHistoricPanel extends JPanel implements TreeSelectionListener {

	private static final long serialVersionUID = -5891932166155076865L;

	private DefaultMutableTreeNode last;

	private DefaultMutableTreeNode root;

	private JTree tree;

	private JScrollPane treeView;

	private JScrollPane botPanel;

	private JPanel taskPanel;

	public static ArrayList<String> log = new ArrayList<String>();

	public KMADEHistoricPanel() {

		TitledBorder titlepanel = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1), "Historique ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		this.setBorder(titlepanel);
		this.setLayout(new BorderLayout());
		botPanel = new JScrollPane();

		TitledBorder descriptionTitle = new TitledBorder(null, "Description ", TitledBorder.LEFT, TitledBorder.TOP,
				KMADEConstant.TITLE_PROTO_TASK_FONT);
		botPanel.setBorder(descriptionTitle);
		root = new DefaultMutableTreeNode();
		root.setAllowsChildren(true);
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.setExpandsSelectedPaths(true);
		treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(400, 345));

		taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));

		botPanel.setViewportView(taskPanel);
		botPanel.setPreferredSize(new Dimension(400, 260));
		KMADEEnhancedSplitPane myCenterPanel = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT,
				treeView, botPanel);
		myCenterPanel.setResizeWeight(1);
		myCenterPanel.setDividerLocation(300);
		myCenterPanel.setDividerBorderVisible(true);
		myCenterPanel.setOneTouchExpandable(true);
		this.add(myCenterPanel, BorderLayout.CENTER);
		/*
		 * tree.addMouseListener(new MouseListener() {
		 * 
		 * @Override public void mouseReleased(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { Date date =
		 * Calendar.getInstance().getTime(); log.add(date + " histo click "); } });
		 */
		tree.setCellRenderer(new TreeCellRenderer() {

			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				JLabel myLabel = new JLabel();
				JLabel labelExpanded = new JLabel();
				if (value instanceof DefaultMutableTreeNode) {
					DefaultMutableTreeNode myNode = (DefaultMutableTreeNode) value;
					ImageIcon executantImage = null;
					if (myNode.getUserObject() instanceof HistoricTask) {
						switch (((HistoricTask) myNode.getUserObject()).getTask().getExecutor()) {
						case ABS:
							executantImage = new ImageIcon(
									KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE));
							break;
						case INCONNU:
							executantImage = new ImageIcon(
									KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE));
							break;
						case INT:
							executantImage = new ImageIcon(
									KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));
							break;
						case SYS:
							executantImage = new ImageIcon(
									KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));
							break;
						case USER:
							executantImage = new ImageIcon(
									KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_16_IMAGE));
							break;
						}
						int duree = ((HistoricTask) myNode.getUserObject()).duree;
						myLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
						if (duree != 0) {
							myLabel.setText(myNode.getUserObject().toString() + "(" + duree + ")" + "");
						} else {
							myLabel.setText(myNode.getUserObject().toString() + "       ");
						}
						myLabel.setIcon(executantImage);
					}
				}
				JPanel myPanel = new JPanel(new BorderLayout());
				myPanel.add(BorderLayout.WEST, labelExpanded);
				myPanel.add(BorderLayout.CENTER, myLabel);

				return myPanel;
			}
		});
	}

	public void addNode(Task t) {
		// just for first task
		if (last == null) {
			root.setUserObject(new HistoricTask(t));
			last = root;
			((DefaultTreeModel) tree.getModel()).reload(last);
		} else {
			HistoricTask histoTask = new HistoricTask(t);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(histoTask);

			last.add(node);
			tree.scrollPathToVisible(new TreePath(node.getPath()));
			((DefaultTreeModel) tree.getModel()).reload(last);
			last = node;
			if (t.isLeaf()) {
				if (t.getDuration() != null && !t.getDuration().equals("")) {
					try {
						histoTask.duree = Integer.parseInt(t.getDuration());
					} catch (Exception e) {
						histoTask.duree = 0;
					}
				}
				// calculer la durées
				if (node.getParent() != null) {
					timeRecu((DefaultMutableTreeNode) node.getParent(), histoTask.duree);
				}
			} else {
				histoTask.duree = 0;
			}
		}

	}

	private void timeRecu(DefaultMutableTreeNode node, int time) {
		int res = time;
		HistoricTask t = (HistoricTask) (node.getUserObject());
		int dudu = t.duree;
		res += dudu;
		t.duree = res;
		if (node.getParent() != null) {
			timeRecu((DefaultMutableTreeNode) node.getParent(), time);
		}
	}

	public void remonter() {
		last = (DefaultMutableTreeNode) last.getParent();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node == null) {
			return;
		}
		Task task = ((HistoricTask) node.getUserObject()).getTask();

		displaytask(task);
	}

	private void displaytask(Task task) {
		taskPanel.removeAll();
		JPanel titlePanel = new JPanel();
		TitledBorder titleborder = new TitledBorder(null, task.getName() + " : ", TitledBorder.CENTER, TitledBorder.TOP,
				KMADEConstant.TITLE_PROTO_TASK_FONT);
		titlePanel.setBorder(titleborder);
		// titlePanel.setLayout(new GridLayout(3, 1));
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));

		JTextArea duree;
		if (task.getDuration() != null && !task.getDuration().equals(""))
			duree = new JTextArea(KMADEConstant.PROTOTYPING_TOOL_DURATION + " : " + task.getDuration());
		else
			duree = new JTextArea("");
		duree.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		duree.setEditable(false);
		duree.setLineWrap(true);
		duree.setWrapStyleWord(true);

		TitledBorder titleprecondition = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ", TitledBorder.LEFT, TitledBorder.TOP,
				KMADEConstant.TITLE_PROTO_TASK_FONT);
		JTextArea pre = new JTextArea(task.getPreExpression().getDescription());
		pre.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		pre.setBorder(titleprecondition);
		pre.setEditable(false);
		pre.setLineWrap(true);
		pre.setWrapStyleWord(true);

		TitledBorder titleobservation = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		JTextArea desc = new JTextArea(task.getDescription());
		desc.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		desc.setBorder(titleobservation);
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		TitledBorder titleIter = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP + " : ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		JTextArea iter = new JTextArea(task.getIterExpression().getDescription());
		iter.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		iter.setBorder(titleIter);
		iter.setEditable(false);
		iter.setLineWrap(true);
		iter.setWrapStyleWord(true);
		taskPanel.add(titlePanel);
		titlePanel.add(duree);
		titlePanel.add(desc);
		titlePanel.add(pre);
		titlePanel.add(iter);
		taskPanel.revalidate();
		taskPanel.repaint();

	}

	public void clearHisto() {
		last = null;
		root.removeAllChildren();
		((DefaultTreeModel) tree.getModel()).reload(root);
		taskPanel.removeAll();
	}

	public void deleteCurrentTask() {
		DefaultMutableTreeNode temp = last;
		HistoricTask t = (HistoricTask) last.getUserObject();
		int dudu = t.duree;
		timeRecu((DefaultMutableTreeNode) last.getParent(), -dudu);
		last = (DefaultMutableTreeNode) last.getParent();
		temp.removeFromParent();
		((DefaultTreeModel) tree.getModel()).reload(last);

	}

	public class HistoricTask {
		String name;
		Task t;
		public int duree = 0;

		public HistoricTask(Task t) {
			duree = 0;
			name = t.getName();
			this.t = t;
		}

		@Override
		public String toString() {
			return name;
		}

		public Task getTask() {
			return t;
		}

	}
}

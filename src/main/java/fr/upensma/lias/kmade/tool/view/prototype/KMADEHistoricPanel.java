package fr.upensma.lias.kmade.tool.view.prototype;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

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

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;

public class KMADEHistoricPanel extends JPanel  implements TreeSelectionListener {


	private static final long serialVersionUID = -5891932166155076865L;
	private  DefaultMutableTreeNode last;
	private DefaultMutableTreeNode root;
	private JTree tree ;
	private JScrollPane treeView ;
	private JScrollPane botPanel;
	private JPanel taskPanel;

	public KMADEHistoricPanel() {
		botPanel = new JScrollPane();


		root = new DefaultMutableTreeNode();
		root.setAllowsChildren(true);
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		tree.setExpandsSelectedPaths(true);
		treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(400,345));

		taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel,BoxLayout.PAGE_AXIS));

		botPanel.setViewportView(taskPanel);
		botPanel.setPreferredSize(new Dimension(400,260));
		KMADEEnhancedSplitPane myCenterPanel =  KMADEEnhancedSplitPane
				.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT,
						treeView,botPanel);	
		myCenterPanel.setResizeWeight(1);
		myCenterPanel.setDividerBorderVisible(true);
		myCenterPanel.setOneTouchExpandable(true);
		this.add(myCenterPanel);
		tree.setCellRenderer(new TreeCellRenderer() {

			public Component getTreeCellRendererComponent(JTree tree, Object value,
					boolean selected, boolean expanded, boolean leaf, int row,
					boolean hasFocus) {
				JLabel myLabel = new JLabel();
				JLabel labelExpanded = new JLabel();
				if (value instanceof DefaultMutableTreeNode) {
					DefaultMutableTreeNode myNode = (DefaultMutableTreeNode)value;
					ImageIcon executantImage = null;
					if( myNode.getUserObject() instanceof HistoricTask){
						switch(((HistoricTask) myNode.getUserObject()).getTask().getExecutant()){
						case ABS :
							executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE));
							break;
						case INCONNU:executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE));
						break;
						case INT : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));
						break;
						case SYS : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));
						break;
						case USER : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_16_IMAGE));
						break;
						}
						myLabel.setText(myNode.getUserObject().toString());
						myLabel.setIcon(executantImage);
					}}
				JPanel myPanel = new JPanel(new BorderLayout());
				myPanel.add(BorderLayout.WEST,labelExpanded);
				myPanel.add(BorderLayout.CENTER,myLabel);
				return myPanel;
			}
		});
	}

	public void addNode(Tache t){
		//just for first task
		if(last == null){
			root.setUserObject(new HistoricTask(t));
			last = root;
			((DefaultTreeModel)tree.getModel()).reload(last);
		}else{

			DefaultMutableTreeNode node = new DefaultMutableTreeNode(new HistoricTask(t));
			last.add(node);
			tree.scrollPathToVisible(new TreePath(node.getPath()));
			((DefaultTreeModel)tree.getModel()).reload(last);
			last = node;

		}

	}

	public void remonter(){
		last = (DefaultMutableTreeNode) last.getParent();
	}



	public void valueChanged(TreeSelectionEvent e) {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		if (node == null){
			return;
		}
		Tache task= ((HistoricTask) node.getUserObject()).getTask();
		displaytask(task);
	}

	private void displaytask(Tache task) {
		taskPanel.removeAll();
		JPanel titlePanel = new JPanel();
		TitledBorder titleborder = new TitledBorder(null,task.getName()+" : ",TitledBorder.CENTER, TitledBorder.TOP);
		titlePanel.setBorder(titleborder);
		titlePanel.setLayout(new GridLayout(3, 1));
		TitledBorder titleprecondition = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ",TitledBorder.LEFT, TitledBorder.TOP);
		JTextArea pre = new JTextArea(task.getPreExpression().getDescription());
		pre.setBorder(titleprecondition);
		pre.setEditable(false);
		pre.setLineWrap(true);
		pre.setWrapStyleWord(true);
		TitledBorder titleobservation = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",TitledBorder.LEFT, TitledBorder.TOP);
		JTextArea desc = new JTextArea(task.getObservation());
		desc.setBorder(titleobservation);
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		TitledBorder titleIter = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP+" : ",TitledBorder.LEFT, TitledBorder.TOP);
		JTextArea iter = new JTextArea(task.getIteExpression().getDescription());
		iter.setBorder(titleIter);
		iter.setEditable(false);
		iter.setLineWrap(true);
		iter.setWrapStyleWord(true);
		taskPanel.add(titlePanel);
		titlePanel.add(desc);
		titlePanel.add(pre);
		titlePanel.add(iter);
		taskPanel.revalidate();
		taskPanel.repaint();

	}

	public void clearHisto() {
		last = null;
		root.removeAllChildren();
		((DefaultTreeModel)tree.getModel()).reload(root);
		taskPanel.removeAll();
	}

	public void deleteCurrentTask() {
		DefaultMutableTreeNode temp = last;
		last = (DefaultMutableTreeNode) last.getParent();
		temp.removeFromParent();
		((DefaultTreeModel)tree.getModel()).reload(last);

	}
	public class HistoricTask{
		String name;
		Tache t;

		public HistoricTask(Tache t){
			name =  t.getName();
			this.t = t;
		}
		@Override
		public String toString() {
			return name;
		}

		public Tache getTask(){
			return t;
		}

	}
}


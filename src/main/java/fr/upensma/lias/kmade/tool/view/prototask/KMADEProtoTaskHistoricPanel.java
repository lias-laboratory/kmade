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
package fr.upensma.lias.kmade.tool.view.prototask;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
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

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;

/**
 * @author Thomas LACHAUME
 */
public class KMADEProtoTaskHistoricPanel extends JPanel implements
	TreeSelectionListener {

    private static final long serialVersionUID = -5891932166155076865L;
    private DefaultMutableTreeNode last;
    private ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<DefaultMutableTreeNode>();
    private DefaultMutableTreeNode root = null;
    private JTree tree;
    private JScrollPane treeView;
    private JScrollPane botPanel;
    private JPanel taskPanel;
    private ArrayList<String> actions = new ArrayList<String>();
    private int seq = 1;
    private HashMap<ProtoTaskCondition, StateCondition> lastcondit = new HashMap<ProtoTaskCondition, StateCondition>();

    public static ArrayList<String> log = new ArrayList<String>();

    public KMADEProtoTaskHistoricPanel(int w, int h) {

	TitledBorder titlepanel = new TitledBorder(
		BorderFactory.createLineBorder(Color.BLACK, 1), "Historique ",
		TitledBorder.LEFT, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	this.setBorder(titlepanel);
	this.setLayout(new BorderLayout());
	botPanel = new JScrollPane();

	TitledBorder descriptionTitle = new TitledBorder(null, "Description ",
		TitledBorder.LEFT, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	botPanel.setBorder(descriptionTitle);
	root = new DefaultMutableTreeNode(new HistoricTask(null, seq, null));
	root.setAllowsChildren(true);
	tree = new JTree(root);
	tree.getSelectionModel().setSelectionMode(
		TreeSelectionModel.SINGLE_TREE_SELECTION);
	tree.addTreeSelectionListener(this);
	tree.setExpandsSelectedPaths(true);
	treeView = new JScrollPane(tree);
	treeView.setPreferredSize(new Dimension(w, (int) (h * 0.7)));

	taskPanel = new JPanel();
	taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));

	botPanel.setViewportView(taskPanel);
	botPanel.setPreferredSize(new Dimension(w, (int) (h * 0.3)));
	KMADEEnhancedSplitPane myCenterPanel = KMADEEnhancedSplitPane
		.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT, treeView,
			botPanel);
	myCenterPanel.setResizeWeight(1);
	myCenterPanel.setDividerLocation((int) (h * 0.7));
	myCenterPanel.setDividerBorderVisible(true);
	myCenterPanel.setOneTouchExpandable(true);
	this.add(myCenterPanel, BorderLayout.CENTER);
	tree.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
		Date date = Calendar.getInstance().getTime();
		log.add(date + " histo click ");
	    }
	});
	tree.setCellRenderer(new TreeCellRenderer() {

	    public Component getTreeCellRendererComponent(JTree tree,
		    Object value, boolean selected, boolean expanded,
		    boolean leaf, int row, boolean hasFocus) {

		JLabel container = new JLabel();
		JLabel myLabel = new JLabel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

		JLabel icon1Label = new JLabel();
		JLabel icon2Label = new JLabel();

		if (value instanceof DefaultMutableTreeNode) {
		    DefaultMutableTreeNode myNode = (DefaultMutableTreeNode) value;
		    ImageIcon executantImage = null;
		    if (myNode.getUserObject() instanceof HistoricTask) {
			switch (((HistoricTask) myNode.getUserObject())
				.getTask().getExecutor()) {
			case ABS:
			    executantImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE));
			    break;
			case INCONNU:
			    executantImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE));
			    break;
			case INT:
			    executantImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));
			    break;
			case SYS:
			    executantImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));
			    break;
			case USER:
			    executantImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.USER_TASK_16_IMAGE));
			    break;
			default:
			}
			int duree = ((HistoricTask) myNode.getUserObject()).duree;
			myLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
			if (duree != 0) {
			    myLabel.setText(myNode.getUserObject().toString()
				    + "(" + duree + ")" + "");
			} else {
			    myLabel.setText(myNode.getUserObject().toString()
				    + "       ");
			}
			icon1Label.setIcon(executantImage);
			if (((HistoricTask) myNode.getUserObject())
				.isValidated()) {
			    ImageIcon okImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.ACCEPT_ICON_IMAGE));
			    icon2Label.setIcon(okImage);
			    // ca serait trop simple si ça marchait
			    icon2Label.setToolTipText("Tâche validée");
			} else {
			    ImageIcon inProgressImage = new ImageIcon(
				    KMADETaskModelToolBar.class
					    .getResource(KMADEConstant.PROGRESS_ICON_IMAGE));
			    icon2Label.setIcon(inProgressImage);
			    icon2Label.setToolTipText("Tâche en cours");
			}
		    }
		} else {
		}
		container.add(icon1Label);
		container.add(Box.createRigidArea(new Dimension(5, 0)));
		container.add(icon2Label);
		container.validate();
		container.repaint();

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.X_AXIS));

		myPanel.add(icon2Label);
		myPanel.add(icon1Label);
		myPanel.add(myLabel);

		return myPanel;
	    }
	});
    }

    public void undo(){
    	int number = seq-1;
    	System.out.println("number : "+number);
    	if(nodes.size()>1){
    		//on regarde la derniere action, on supprime le node si la tâche n'était pas terminée
    	  for (DefaultMutableTreeNode treeNode : nodes) {
  	    	HistoricTask hT = (HistoricTask) treeNode.getUserObject();
  	    	if(hT.getStart()==number) {
  	    		nodes.remove(treeNode);
  	    		treeNode.removeFromParent();
  	    		 ((DefaultTreeModel) tree.getModel()).reload(treeNode);
  	    		 break;
  	    	}
  	    	else if(hT.getEnd()==number){
  	    		hT.setEndNumber(-1);
 	    		 ((DefaultTreeModel) tree.getModel()).reload(treeNode);

  	    	}
  		}
    		seq--;
    		}
    	
    }
    
    public void addNode(Task t, ArrayList<ProtoTaskCondition> conditions) {
	if (nodes.isEmpty()) {
	    if (conditions != null) {
		for (ProtoTaskCondition protoTaskCondition : conditions) {
		    lastcondit.put(protoTaskCondition,
			    protoTaskCondition.getCurrentValue());
		}
	    }
	    ((HistoricTask) root.getUserObject()).setTask(t);
	    ((HistoricTask) root.getUserObject()).setName(t.getName());
	    ((HistoricTask) root.getUserObject()).setConditions(lastcondit);
	    HistoricTask histoTask = new HistoricTask(t, seq, lastcondit);
	    root.setUserObject(histoTask);
	    nodes.add(root);
	    ((DefaultTreeModel) tree.getModel()).reload(root);
	} else {
	    boolean changed = false;
	    if (conditions != null && lastcondit != null) {
		for (ProtoTaskCondition protoTaskCondition : conditions) {
		    if (!(lastcondit.get(protoTaskCondition) == protoTaskCondition
			    .getCurrentValue())) {
			changed = true;
			lastcondit.put(protoTaskCondition,
				protoTaskCondition.getCurrentValue());
		    }
		}
	    }

	    HistoricTask histoTask;
	    //si il y a eu un changement dans les conditions
	    if (changed) {
		histoTask = new HistoricTask(t, seq, lastcondit);
	    } else {
		histoTask = new HistoricTask(t, seq, null);
	    }

	    DefaultMutableTreeNode node = new DefaultMutableTreeNode(histoTask);
	    Task mother = t.getMother();
	    if (mother == null) {
		// erreur?
	    }
	    // recherche du node de la mère
	    for (DefaultMutableTreeNode treeNode : nodes) {
	    	HistoricTask hT = (HistoricTask) treeNode.getUserObject();
	    	if(hT.getTask().getOid().equals(mother.getOid())) {
		    treeNode.add(node);
		    ((DefaultTreeModel) tree.getModel()).reload(treeNode);
		}
	    }
	    TreePath path = new TreePath(node.getPath());
	    tree.setSelectionPath(path);
	    nodes.add(node);

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
		    timeRecu((DefaultMutableTreeNode) node.getParent(),
			    histoTask.duree);
		}
	    } else {
		histoTask.duree = 0;
	    }
	}
	seq++;
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

    public void remonter(Task t, ArrayList<ProtoTaskCondition> conditions) {
	// last = (HistoTreeNode) last.getParent();
	boolean changed = false;
	if (conditions != null && lastcondit != null) {
	    for (ProtoTaskCondition protoTaskCondition : conditions) {
		if (!(lastcondit.get(protoTaskCondition) == protoTaskCondition
			.getCurrentValue())) {
		    changed = true;
		    lastcondit.put(protoTaskCondition,
			    protoTaskCondition.getCurrentValue());
		}
	    }
	}

	int i = nodes.size();
	while (i > 0) {
	    int oid = ((HistoricTask) nodes.get(i - 1).getUserObject())
		    .getTask().getOid().getValue();
	    if (oid == t.getOid().getValue()) {
		
		((HistoricTask) nodes.get(i - 1).getUserObject())
			.setEndNumber(seq);
		if (changed) {
		    ((HistoricTask) nodes.get(i - 1).getUserObject())
			    .setEndCondition(conditions);
		}
		seq++;
		i = 0;

		// ((DefaultTreeModel)tree.getModel()).reload(root);
	    }
	    i--;
	}
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
		.getLastSelectedPathComponent();
	if (node == null) {
	    return;
	}
	Task task = ((HistoricTask) node.getUserObject()).getTask();

	displaytask(task);
    }

    private void displaytask(Task task) {
	taskPanel.removeAll();
	JPanel titlePanel = new JPanel();
	TitledBorder titleborder = new TitledBorder(null, task.getName()
		+ " : ", TitledBorder.CENTER, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	titlePanel.setBorder(titleborder);
	// titlePanel.setLayout(new GridLayout(3, 1));
	titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));

	JTextArea duree;
	if (task.getDuration() != null && !task.getDuration().equals(""))
	    duree = new JTextArea(KMADEConstant.PROTOTYPING_TOOL_DURATION
		    + " : " + task.getDuration());
	else
	    duree = new JTextArea("");
	duree.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
	duree.setEditable(false);
	duree.setLineWrap(true);
	duree.setWrapStyleWord(true);

	TitledBorder titleprecondition = new TitledBorder(null,
		KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ",
		TitledBorder.LEFT, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	JTextArea pre = new JTextArea(task.getPreExpression().getDescription());
	pre.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
	pre.setBorder(titleprecondition);
	pre.setEditable(false);
	pre.setLineWrap(true);
	pre.setWrapStyleWord(true);

	TitledBorder titleobservation = new TitledBorder(null,
		KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",
		TitledBorder.LEFT, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	JTextArea desc = new JTextArea(task.getDescription());
	desc.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
	desc.setBorder(titleobservation);
	desc.setEditable(false);
	desc.setLineWrap(true);
	desc.setWrapStyleWord(true);
	TitledBorder titleIter = new TitledBorder(null,
		KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP + " : ",
		TitledBorder.LEFT, TitledBorder.TOP,
		KMADEConstant.TITLE_PROTO_TASK_FONT);
	JTextArea iter = new JTextArea(task.getIterExpression()
		.getDescription());
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

    public String stringXML() {
	String s = "";
	s += recuXML(root, 0);
	return s;

    }

    String recuXML(DefaultMutableTreeNode r, int gap) {
	String s = "\n";
	for (int j = 0; j < gap; j++) {
	    s += "    ";
	}
	s += "<task taskname=\""
		+ r
		+ "\"  idkmad=\"K"
		+ ((HistoricTask) r.getUserObject()).getTask().getOid()
			.getValue() + "\"";
	s += " startnumber=\"" + ((HistoricTask) r.getUserObject()).start
		+ "\" ";
	s += " endnumber=\"" + ((HistoricTask) r.getUserObject()).end + "\" ";
	if (((HistoricTask) r.getUserObject()).getStartConditions() != null) {
	    Set<ProtoTaskCondition> cles = ((HistoricTask) r.getUserObject())
		    .getStartConditions().keySet();
	    java.util.Iterator<ProtoTaskCondition> it = cles.iterator();
	    while (it.hasNext()) {
		ProtoTaskCondition cle = it.next();
		StateCondition valeur = ((HistoricTask) r.getUserObject())
			.getStartConditions().get(cle);
		s += " startconditionid=\"K" + cle.getOid().getValue()
			+ "\" startconditionname=\"" + cle.getDescription()
			+ "\"  startvaleur=\"" + valeur + "\"";
	    }
	}
	if (((HistoricTask) r.getUserObject()).getEndconditions() != null) {
	    Set<ProtoTaskCondition> cles = ((HistoricTask) r.getUserObject())
		    .getEndconditions().keySet();
	    java.util.Iterator<ProtoTaskCondition> it = cles.iterator();
	    while (it.hasNext()) {
		ProtoTaskCondition cle = it.next();
		StateCondition valeur = ((HistoricTask) r.getUserObject())
			.getEndconditions().get(cle);
		s += " endconditionid=\"K" + cle.getOid().getValue()
			+ "\" endconditionname=\"" + cle.getDescription()
			+ "\"  endvaleur=\"" + valeur + "\"";
	    }
	}
	s += ">";
	if (!r.isLeaf()) {
	    for (int i = 0; i < r.getChildCount(); i++) {
		s += recuXML((DefaultMutableTreeNode) r.getChildAt(i), gap + 1);
	    }
	}
	return s;
    }

    public void addAction(Task t) {
	String s = "";
	actions.add(s);
    }

    public void addAction(String s) {

    }

    public void clearHisto() {
	seq = 1;
	last = null;
	nodes.clear();
	root.removeAllChildren();
	// ((DefaultTreeModel)tree.getModel()).reload(root);
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
	int start= -1 ;
	int end = -1;
	HashMap<ProtoTaskCondition, StateCondition> startconditions = null;
	HashMap<ProtoTaskCondition, StateCondition> endconditions = null;

	public int duree = 0;

	public HistoricTask(Task t, int seq,
		HashMap<ProtoTaskCondition, StateCondition> lastcondit) {

	    if (lastcondit != null) {
		this.startconditions = new HashMap<ProtoTaskCondition, StateCondition>();
		Set<ProtoTaskCondition> cles = lastcondit.keySet();
		java.util.Iterator<ProtoTaskCondition> it = cles.iterator();
		while (it.hasNext()) {
		    ProtoTaskCondition cle = it.next();
		    StateCondition valeur = lastcondit.get(cle);
		    this.startconditions.put(cle, valeur);
		}
	    }
	    this.start = seq;
	    duree = 0;
	    if (t != null)
		name = t.getName();
	    else
		name = "";
	    this.t = t;
	}
	public int getStart(){
		return start;
	}

	public int getEnd(){
		return end;
	}
	public void setEndCondition(ArrayList<ProtoTaskCondition> conditions) {
	    this.endconditions = new HashMap<ProtoTaskCondition, StateCondition>();
	    for (ProtoTaskCondition protoTaskCondition : conditions) {
		endconditions.put(protoTaskCondition,
			protoTaskCondition.getCurrentValue());
	    }
	}

	public void setEndCondition(ProtoTaskCondition protoTaskCondition,
		StateCondition currentValue) {
	    endconditions = new HashMap<ProtoTaskCondition, StateCondition>();
	    endconditions.putAll(lastcondit);
	}

	public void setEndNumber(int endNumber) {
	    this.end = endNumber;
	}

	public void setConditions(
		HashMap<ProtoTaskCondition, StateCondition> lastcondit) {
	    startconditions = new HashMap<ProtoTaskCondition, StateCondition>();
	    startconditions.putAll(lastcondit);
	}

	public void setName(String name2) {
	    this.name = name2;
	}

	public void setTask(Task t2) {
	    this.t = t2;
	}

	@Override
	public String toString() {
	    return name;
	}

	public Task getTask() {
	    return t;
	}

	public Boolean isValidated() {
	    return end>0;
	}



	public HashMap<ProtoTaskCondition, StateCondition> getStartConditions() {
	    return startconditions;
	}

	public HashMap<ProtoTaskCondition, StateCondition> getEndconditions() {
	    return endconditions;
	}

    }

}

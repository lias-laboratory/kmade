package fr.upensma.lias.kmade.tool.view.prototask;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ProtoTaskCondition;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.StateCondition;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.ProtoTaskConditionExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressCondition;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.parserkmad.ExpressKMADTXT;
import fr.upensma.lias.kmade.tool.coreadaptator.prototask.Excution;
import fr.upensma.lias.kmade.tool.coreadaptator.prototask.ProtoTaskException;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEFileChooser;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

public class KMADEProtoTaskMainPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2184765933287357646L;
	int gap = 0;
	int maxGap = 7;
	private ArrayList<Task> displayTask = new ArrayList<Task>();
	private LinkedHashMap<String,JInternalFrame> myframes = new LinkedHashMap<String, JInternalFrame>();
	private JDesktopPane desktop;
	private KMADEProtoTaskHistoricPanel historique;
	private KMADEProtoTaskSaveScenarioPanel save = new KMADEProtoTaskSaveScenarioPanel();
	private JPanel conditions = new JPanel();
	private Oid last;
	private String condiID = "condition154212&#";
	private String histoID = "historique3213R5438122312&#";
	private int pttWitdh = 0;
	private int pttHeight = 0;
	final JToolBar taskMenu = new JToolBar();
	JMenuBar menuBar = new JMenuBar();
	//final JMenu menu = new JMenu("Tâches");

	public KMADEProtoTaskMainPanel() {
		super(KMADEConstant.PROTOTYPING_TOOL_TITLE_NAME);
		constructMainPanel();

	}

	public void constructMainPanel() {
		// use deskop pane
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.add(save);
		this.addWindowListener(new WindowAdapter() { public void
			windowClosing(WindowEvent e) { closeProtoTaskMainFrame(); } });

		Dimension screen_dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int witdh =(int) ((screen_dimension.getWidth()));
		int height =(int) ((screen_dimension.getHeight()));
		pttWitdh = witdh -  (witdh/4);
		pttHeight = height - (height/4);
		this.setLocation(
				(int) ((screen_dimension.getWidth() - this.getWidth()) / 8),
				(int) ((screen_dimension.getHeight() - this.getHeight()) / 8));

		historique = new KMADEProtoTaskHistoricPanel((int)(pttWitdh*0.33),(int)(pttHeight*0.7));
		desktop = new JDesktopPane();
		//this.setContentPane(desktop);
		this.add(desktop,BorderLayout.CENTER);
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		this.setPreferredSize(new Dimension(pttWitdh,pttHeight));
		desktop.setPreferredSize(new Dimension(pttWitdh,pttHeight));
		JMenuBar menu = createMenuBar();
		this.setJMenuBar(menu);
		this.add(taskMenu,BorderLayout.PAGE_START);
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent evt) {
	            Component c = (Component)evt.getSource();
				Collection<JInternalFrame> val = myframes.values();
				if(val.size()!=0){
					for (JInternalFrame frame : val) {
						int x = frame.getLocation().x;
						int y = frame.getLocation().y;
						if(x>=c.getWidth()){
							x = c.getWidth()-80;
						}
						if(y+50>=c.getHeight()){
							y=c.getHeight()-150;
						}
						if(y<0){
							y=0;
						}
						frame.setLocation(x, y);
					}
					JInternalFrame histoFrame = myframes.get(histoID);
					histoFrame.setLocation(c.getSize().width-histoFrame.getSize().width-10,0 );

				}

			}
			@Override
			public void componentMoved(ComponentEvent arg0) {

			}
			@Override
			public void componentHidden(ComponentEvent arg0) {

			}
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
		});
		this.pack();
		this.validate();
		this.repaint();
	}

	private void refreshMenu(){
		taskMenu.removeAll();
		Set<Entry<String, JInternalFrame>> set = myframes.entrySet();
		Object[] test = set.toArray();
		for (int j = test.length-1;j>=0;j--){
			final Map.Entry entry =  (Entry) test[j];
			if(entry.getKey().equals(histoID)){
			}else if(entry.getKey().equals(condiID)){
			}else{
				String name = ((JInternalFrame) entry.getValue()).getTitle();
				JButton menuItem = new JButton(name);
				menuItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						((JInternalFrame) entry.getValue()).moveToFront();
						((JInternalFrame) entry.getValue()).getDesktopPane().getDesktopManager().deiconifyFrame(((JInternalFrame) entry.getValue()));

						try {
							((JInternalFrame) entry.getValue()).setSelected(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				taskMenu.add(menuItem);
			}

		}
		//	menu.setText("Tâche(s) : " + (test.length-2) );
		taskMenu.revalidate();
		taskMenu.repaint();
	}

	private JMenuBar createMenuBar() {
		JMenu condi = new JMenu(KMADEConstant.PROTOTASK_CONDITION_MENU);
		JMenuItem showcondi = new JMenuItem(KMADEConstant.PROTOTASK_CONDITION_MENU_ITEM1);
		showcondi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame condiFrame = myframes.get(condiID);	
				condiFrame.moveToFront();
				try {
					condiFrame.setSelected(true);
					condiFrame.getDesktopPane().getDesktopManager().deiconifyFrame(condiFrame);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		condi.add(showcondi);
		JMenu histo = new JMenu(KMADEConstant.PROTOTASK_HISTORIQUE_MENU);
		JMenuItem showHisto = new JMenuItem(KMADEConstant.PROTOTASK_HISTORIQUE_MENU_ITEM1);
		showHisto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String myText = historique.stringXML();

				String myCurrentFile = KMADEFileChooser
						.saveKMADModelSimpleFile();
				try {
					FileWriter fw = new FileWriter(new File(myCurrentFile));
					fw.write(myText);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				desktop.revalidate();
				desktop.repaint();
			}
		});
		JMenuItem histoItem = new JMenuItem(KMADEConstant.PROTOTASK_HISTORIQUE_MENU_ITEM2);
		histoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame histoFrame = myframes.get(histoID);		
				histoFrame.moveToFront();
				try {
					histoFrame.setSelected(true);
					histoFrame.getDesktopPane().getDesktopManager().deiconifyFrame(histoFrame);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		histo.add(histoItem);
		histo.add(showHisto);
		menuBar.add(histo);
		//menuBar.add(menu);
		menuBar.add(condi);
		return menuBar;
	}

	public void constructHistoryPanel() {
		JInternalFrame internalframe2 = new JInternalFrame(KMADEConstant.PROTOTASK_HISTORIQUE_FRAME_TITLE, true,
				false, true, true);
		internalframe2.add(historique);
		desktop.add(internalframe2);
		internalframe2.pack();
		internalframe2.setLocation(this.getSize().width-internalframe2.getSize().width-10,0 );
		internalframe2.setVisible(true);
		desktop.revalidate();
		desktop.repaint();
		//historique3243etc ne peut pas etre un oid
		myframes.put(histoID,internalframe2);

	}

	public void constructConditionPanel(){
		JInternalFrame internalframe = new JInternalFrame(KMADEConstant.PROTOTASK_CONDITION_FRAME_TITLE, true,
				false, true, true);
		conditions.setLayout(new BoxLayout(conditions, BoxLayout.PAGE_AXIS));
		ArrayList<ProtoTaskCondition> c = ExpressCondition.getConditionsIntoTasks();
		for (ProtoTaskCondition protoTaskCondition : c) {
			conditionLine co = new conditionLine(protoTaskCondition);
			conditions.add(co);
		}
		internalframe.add(conditions);
		internalframe.pack();
		internalframe.setLocation(this.getSize().width-internalframe.getSize().width-5,this.getSize().height-internalframe.getSize().height-90);
		if(c.size()!=0){
			internalframe.setVisible(true);
		}
		desktop.add(internalframe);
		desktop.revalidate();
		desktop.repaint();
		myframes.put(condiID,internalframe);
	}

	public void refreshConditionPanel(){
		ArrayList<ProtoTaskCondition> c = ExpressCondition.getConditionsIntoTasks();
		conditions.removeAll();
		if(c.size()!=0){
			for (ProtoTaskCondition protoTaskCondition : c) {
				conditionLine co = new conditionLine(protoTaskCondition);
				conditions.add(co);
			}
		}
	}

	public void openProtoTaskMainFrame() {
		this.setVisible(true);
		GraphicEditorAdaptator.selectNoTask();
		desktop.removeAll();
		Collection<JInternalFrame> val = myframes.values();
		for (JInternalFrame jInternalFrame : val) {
			jInternalFrame.dispose();
		}
		myframes.clear();
		conditions = new JPanel();
		Excution.resetScenario();
		ArrayList<Task> root = ExpressTask.getRootTasks();
		ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
		historique.clearHisto();
		historique.addNode(root.get(0),conditions);
		constructConditionPanel();
		constructHistoryPanel();
		this.starTest();
	}

	public void closeProtoTaskMainFrame() {
		this.setVisible(false);
	}


	private ArrayList<Task> getTaskInState(StateExecution state) {
		ArrayList<Task> root = ExpressTask.getRootTasks();
		ArrayList<Task> res = new ArrayList<Task>();
		for (Task tache : root) {
			res.addAll(getTaskInStateRec(state, new ArrayList<Task>(), tache));
		}
		return res;
	}

	private ArrayList<Task> getTaskInStateRec(StateExecution state,
			ArrayList<Task> prev, Task t) {
		if (t.getStateExecution() == state) {
			prev.add(t);
		}
		for (Task fils : t.getChildren()) {
			prev = getTaskInStateRec(state, prev, fils);
		}
		return prev;
	}

	private void starTest() {
		refreshConditionPanel();
		/*
		// pour pouvoir agrandir les fenetres plus simplement
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}*/
		ArrayList<Task> active = getTaskInState(StateExecution.ACTIVE);
		ArrayList<Task> attentefin = getTaskInState(StateExecution.ATTENTEFIN);
		ArrayList<Task> attentefinKO = getTaskInState(StateExecution.ATTENTEFINKO);

		//on enleve les internalframes qui n'existe plus 
		boolean delete = false;
		Set<Entry<String, JInternalFrame>> set = myframes.entrySet();
		Object[] test = set.toArray();
		for (int j = test.length-1;j>=0;j--){
			delete=true;
			Map.Entry entry =  (Entry) test[j];
			if(entry.getKey().equals(histoID)||entry.getKey().equals(condiID)){
				delete =false;
			}else{
				for (Task t : attentefin) {
					if (entry.getKey().equals(t.getOid().get())){
						delete = false;
					}
				}
				for (Task t1 : active) {
					if (entry.getKey().equals(t1.getOid().get())){
						delete = false;
					}
				}
			}
			if(delete){
				((JInternalFrame) entry.getValue()).dispose();
				myframes.remove(entry.getKey());
			}
		}

		for (Task t : attentefinKO) {
			displayWaitEndKO(t, gap);
			if (gap > maxGap || myframes.size()<=3)
				gap = 0;
		}
		for (Task t : attentefin) {
			displayWaitEnd(t, gap);
			if (gap > maxGap || myframes.size()<=3)
				gap = 0;
		}
		for (Task t : active) {
			displayActive(t, gap);
			if (gap > maxGap || myframes.size()<=3)
				gap = 0;
		}
		Collection c = myframes.values();
		Iterator itr = c.iterator();
		while(itr.hasNext()){
			JInternalFrame inter = (JInternalFrame) itr.next();
			inter.pack();
			inter.revalidate();
			inter.repaint();
			inter.setVisible(true);
		}
		if(last!=null){
			JInternalFrame focus = myframes.get(last.get());
			if(focus!=null){
				focus.moveToFront();
				try {
					focus.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// Display elements
		refreshMenu();
		if(c.size()<=2){
			JInternalFrame internalframe = new JInternalFrame(KMADEConstant.PROTOTASK_END_TITLE, true,
					false, true, true);
			JLabel lab = new JLabel("<HTML><FONT SIZE="+"5"+">"+ KMADEConstant.PROTOTASK_END_LABEL+"   </FONT></HTML>");
			lab.setIcon(new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ICON_LOGO_IMAGE)));
			internalframe.add(lab);
			internalframe.pack();
			internalframe.revalidate();
			internalframe.repaint();
			internalframe.setVisible(true);
			desktop.add(internalframe);
		}
		desktop.revalidate();
		desktop.repaint();
	}

	private JPanel displayTask(final Task t, int offset){
		JInternalFrame internalframe;
		if(myframes.get(t.getOid().get()) != null){
			//"update"
			internalframe = myframes.get(t.getOid().get());
			Dimension size = internalframe.getSize();
			Point loc = internalframe.getLocation();
			myframes.remove(t.getOid().get());
			internalframe.dispose();
			internalframe = new JInternalFrame(t.getName(), true,
					false, true, true);
			internalframe.setSize(size);
			internalframe.setLocation(loc);
			myframes.put(t.getOid().get(),internalframe);
			desktop.add(internalframe);
		}else{
			internalframe = new JInternalFrame(t.getName(), true,
					false, true, true);
			myframes.put(t.getOid().get(),internalframe);
			desktop.add(internalframe);
			internalframe.setLocation(offset*20, offset*20);
			if(myframes.size()>3)
			gap++;

		}
		JPanel frame = new JPanel();
		JScrollPane taskScrollPane = new JScrollPane();
		taskScrollPane.setViewportView(frame);
		internalframe.add(taskScrollPane);
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		JPanel taskPanel = getTaskPanel(t);
		frame.add(taskPanel);
		ArrayList<Task> fils = t.getChildren();
		int number = 0;
		HashMap<String, conditionLine> condition = new HashMap<String, KMADEProtoTaskMainPanel.conditionLine>();
		if(t.getIterExpression().getProtoTaskConditionExpression()!=null && t.getIterExpression().getProtoTaskConditionExpression().getValue().getDescription()!=null && !t.getIterExpression().getProtoTaskConditionExpression().getValue().getDescription().equals("")){
			conditionLine line = new conditionLine(t.getIterExpression().getProtoTaskConditionExpression().getValue());
			condition.put(t.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription(), line);
		}
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		for (final Task tache : fils) {
			if(t.getOrdering()==Decomposition.SEQ){
				number++;
			}
			JButton b =buttonMaker(tache, number);
			// if (precondition)
			if(tache.getPreExpression().getProtoTaskConditionExpression()!=null && tache.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription()!=null && !tache.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription().equals("") ){
				//then display precondition panel
				if(tache.getStateExecution()!=StateExecution.FINISHED && tache.getStateExecution()!=StateExecution.INACTIVE && tache.getStateExecution()!=StateExecution.PASSEE){
					if(!condition.containsKey(tache.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription())){
						{
							conditionLine line = new conditionLine(tache.getPreExpression().getProtoTaskConditionExpression().getValue());
							condition.put(tache.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription(), line);
						}
					}
				}
			}

			buttons.add(b);
		}
		Collection<conditionLine> conditionLine = condition.values();
		JPanel condiPanel = new JPanel();
		condiPanel.setLayout(new BoxLayout(condiPanel,BoxLayout.PAGE_AXIS));

		JLabel condi = new JLabel("<HTML><FONT SIZE="+"5"+">" +KMADEConstant.PROTOTASK_CONDITION_LABEL+"</FONT></HTML>");
		TitledBorder condiTitle = new TitledBorder(null,
				KMADEConstant.PROTOTASK_CONDITION_LABEL,
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		condiPanel.setBorder(condiTitle);
		condiPanel.validate();
		//condiPanel.add(condi);
		frame.add(condiPanel);

		if(conditionLine.size()==0){
			condiPanel.add(new JLabel("<HTML><FONT SIZE="+"3"+">"+"Aucune condition"+"</FONT></HTML>"),BorderLayout.CENTER);
		}else{
			for (conditionLine conditionLine2 : conditionLine) {
				condiPanel.add(conditionLine2);
			}
		}
		JPanel buttonsPanel = new JPanel();
		JPanel panel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		if(buttons.size()!=0){
			TitledBorder buttonsTitle = new TitledBorder(null,
					KMADEConstant.PROTOTASK_TASK_LABEL,
					TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
			buttonsPanel.setBorder(buttonsTitle);
			for (JButton myButton : buttons) {
				panel.add(myButton);
			}
			buttonsPanel.add(panel,BorderLayout.CENTER);
			frame.add(buttonsPanel);
		}
		return frame;

	}

	private void displayWaitEndKO(final Task t, int offset) {
		JPanel frame = displayTask(t,offset);

		/*********************************************************/
		JButton validate = new JButton(KMADEConstant.PROTOTASK_REPEAT_BUTTON);
		validate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {				
					ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
					historique.remonter(t,conditions);
					Excution.repeatTask(t);
					ArrayList<ProtoTaskCondition> conditions2 = ExpressCondition.getConditions();
					historique.addNode(t,conditions2);
					starTest();
				} catch (ProtoTaskException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.add(validate);
		/*******************************************************/


	}

	private void displayWaitEnd(final Task t, int offset) {
		JPanel frame = displayTask(t,offset);

		/*********************************************************/
		JButton validate = new JButton(KMADEConstant.PROTOTASK_VALIDATE_BUTTON);
		validate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
					historique.remonter(t,conditions);
					Excution.endTask(t);
					starTest();
				} catch (ProtoTaskException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.add(validate);
		/*******************************************************/
	}

	private void displayActive(Task t, int offset) {
		JPanel frame = displayTask(t,offset);
	}

	private String recMother(Task t){
		String s1 = t.getName();
		if(t.getMother()!=null){
			return recMother(t.getMother()) +" > " + s1;
		}
		return t.getName();
	}

	private JPanel getTaskPanel(final Task t) {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));
		String s ="";
		if(t.getMother()!=null){
			s = recMother(t.getMother());
		}
		s = "<HTML><FONT SIZE=5><B>"+s+"></B></FONT></HTML>";
		//TODO trouvé un meilleur affichage
		//topPanel.add(new JLabel(s));
		String name = t.getName();
		JLabel nameLabel = new JLabel("<HTML><FONT SIZE=7><B>" + name+"</B></FONT></HTML>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_NAME_TOOLTIP);
		//	nameLabel.setAlignmentX(TOP_ALIGNMENT);
		topPanel.add(nameLabel);

		topPanel.add(Box.createVerticalStrut(2));
		ImageIcon executantImage = null;
		String executantText = "";
		switch(t.getExecutor()){
		case ABS :
			executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_48_IMAGE));
			executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_ABS;
			break;
		case INCONNU:
			//	executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_48_IMAGE));
			//	executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_INCONNU;
			break;
		case INT : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_48_IMAGE));
		executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_INT;
		break;
		case SYS : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_48_IMAGE));
		executantText +=KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_SYS;
		break;
		case USER : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_48_IMAGE));
		executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_USER;
		break;
		}

		JLabel executantLabel = new JLabel();
		executantLabel.setText(executantText);
		executantLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_TOOLTIP);
		executantLabel.setIcon(executantImage);
		topPanel.add(executantLabel);
		TitledBorder ordoPanelTitle = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_DECOMPOSITION_TITLE + " : "+ t.getOrdering().getValue(),TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		//possibleTaskScroll.setBorder(ordoPanelTitle);

		//précondition
		String precondition = t.getPreExpression().getDescription();
		TitledBorder titleprecondition = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ",TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		JTextArea preconditionLabel = new JTextArea(precondition);
		preconditionLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		preconditionLabel.setEditable(false);
		preconditionLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TOOLTIP);
		preconditionLabel.setBackground(Color.white);
		preconditionLabel.setLineWrap(true);
		preconditionLabel.setWrapStyleWord(true);
		JTextArea protoPrecondition = new JTextArea(t.getPreExpression().getProtoTaskConditionExpression().getValue().getDescription());
		JScrollPane preconditionScroll = new JScrollPane();
		preconditionScroll.setViewportView(protoPrecondition);
		preconditionScroll.setBorder(titleprecondition);

		//Iteration
		String iteration =  t.getIterExpression().getProtoTaskConditionExpression().getValue().getDescription();
		JTextArea iterationLabel = new JTextArea(iteration);
		iterationLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		iterationLabel.setEditable(false);
		iterationLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP);
		iterationLabel.setBackground(Color.white);
		iterationLabel.setLineWrap(true);
		iterationLabel.setWrapStyleWord(true);
		JScrollPane iterationScroll = new JScrollPane();
		iterationScroll.setViewportView(iterationLabel);
		TitledBorder iterationTitle = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP+" : ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		iterationScroll.setBorder(iterationTitle);


		//Condition

		//Description 	
		String observation =  t.getDescription();
		final JTextArea descriptionLabel = new JTextArea(observation);
		descriptionLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		descriptionLabel.setEditable(true);
		descriptionLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TOOLTIP);
		descriptionLabel.setBackground(Color.white);
		descriptionLabel.setLineWrap(true);
		descriptionLabel.setWrapStyleWord(true);
		descriptionLabel.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(t,descriptionLabel.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(t,descriptionLabel.getText());				
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(t,descriptionLabel.getText());
			}
		});
		JScrollPane descriptionScroll = new JScrollPane();
		descriptionScroll.setViewportView(descriptionLabel);
		TitledBorder myUserBorder = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		descriptionScroll.setBorder(myUserBorder);
		JPanel task = new JPanel();
		task.setLayout(new BoxLayout(task, BoxLayout.PAGE_AXIS));
		task.add(topPanel);
		task.add(descriptionScroll);
		task.add(preconditionScroll);
		task.add(iterationScroll);
		//task.add(conditionScroll);
		task.setPreferredSize(new Dimension(600,400));
		task.revalidate();
		task.repaint();

		return task;
	}


	private JButton buttonMaker(final Task tache, int number) {

		String fontsize = "5";
		String name ="";
		if(number != 0)
			name = number+". " + tache.getName();
		else
			name = tache.getName();
		JButton b = new JButton(name);
		if (tache.getStateExecution() == StateExecution.ACTIVABLE) {
			String bText ="<HTML><FONT SIZE="+fontsize+"><B>" + name+"<B></FONT></HTML>";
			b.setText(bText);
			b.setEnabled(true);
		} else if(tache.getStateExecution() == StateExecution.FINISHED){
			String bText ="<HTML><FONTSIZE="+fontsize+"><B><S>" + name+"</S></B></FONT></HTML>";
			b.setText(bText);
			b.setEnabled(false);
			ImageIcon executantImage = new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ACCEPT_ICON_IMAGE));
			b.setIcon(executantImage);
		}else{
			String bText ="<HTML><FONT SIZE="+fontsize+"><B>" + name+"</B></FONT></HTML>";
			b.setText(bText);
			b.setEnabled(false);
		}
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Excution.doTask(tache);
					ArrayList<ProtoTaskCondition> conditions = ExpressCondition.getConditions();
					historique.addNode(tache,conditions);
					last = tache.getOid();
					starTest();

				} catch (ProtoTaskException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return b;
	}

	class conditionLine extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		String trueString = "Vrai";
		String falseString = "Faux";
		String indeterminateString = "Indeterminé";
		String condition;
		ProtoTaskCondition exp;


		public conditionLine(ProtoTaskCondition ptc){
			this.exp = ptc;
			this.condition = exp.getDescription();
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setAlignmentY(TOP_ALIGNMENT);
			this.setAlignmentX(LEFT_ALIGNMENT);
			this.setBorder(LineBorder.createBlackLineBorder());
			JLabel condi = new JLabel();
			String conditext = "";

			conditext += exp.getDescription() + " : ";
			condi.setText(conditext);
			condi.setFont(KMADEConstant.TITLE_PROTO_TASK_FONT);
			// Create the radio buttons.
			JRadioButton trueButton = new JRadioButton(trueString);
			// trueButton.setMnemonic(KeyEvent.VK_B);
			trueButton.setActionCommand(trueString);
			trueButton.setSelected(true);

			JRadioButton falseButton = new JRadioButton(falseString);
			// falseButton.setMnemonic(KeyEvent.VK_C);
			falseButton.setActionCommand(falseString);

			JRadioButton indeterminateButton = new JRadioButton(
					indeterminateString);
			// indeterminateButton.setMnemonic(KeyEvent.VK_D);
			indeterminateButton.setActionCommand(indeterminateString);

			// Group the radio buttons.
			ButtonGroup group = new ButtonGroup();
			group.add(trueButton);
			group.add(falseButton);
			group.add(indeterminateButton);
			StateCondition choice = exp.getCurrentValue();
			if (choice == null) {
				indeterminateButton.setSelected(true);
			} else if (choice == StateCondition.TRUE) {
				trueButton.setSelected(true);
			} else if (choice == StateCondition.FALSE) {
				falseButton.setSelected(true);
			} else if (choice == StateCondition.INDETERMINATE) {
				indeterminateButton.setSelected(true);
			}

			this.add(condi);
			this.add(trueButton);
			this.add(falseButton);
			this.add(indeterminateButton);

			// Register a listener for the radio buttons.
			trueButton.addActionListener(this);
			falseButton.addActionListener(this);
			indeterminateButton.addActionListener(this);
		}



		public conditionLine(String conditionDisplay, String condition,
				StateCondition choice, boolean enabled, boolean shift) {
			this.condition = condition;
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setAlignmentY(TOP_ALIGNMENT);
			this.setAlignmentX(LEFT_ALIGNMENT);
			this.setBorder(LineBorder.createBlackLineBorder());
			JLabel condi = new JLabel();
			String conditext = "";
			if (shift)
				conditext += " → ";
			conditext += conditionDisplay + " : ";
			condi.setText(conditext);
			condi.setFont(KMADEConstant.TITLE_PROTO_TASK_FONT);
			// Create the radio buttons.
			JRadioButton trueButton = new JRadioButton(trueString);
			// trueButton.setMnemonic(KeyEvent.VK_B);
			trueButton.setActionCommand(trueString);
			trueButton.setSelected(true);

			JRadioButton falseButton = new JRadioButton(falseString);
			// falseButton.setMnemonic(KeyEvent.VK_C);
			falseButton.setActionCommand(falseString);

			JRadioButton indeterminateButton = new JRadioButton(
					indeterminateString);
			// indeterminateButton.setMnemonic(KeyEvent.VK_D);
			indeterminateButton.setActionCommand(indeterminateString);

			// Group the radio buttons.
			ButtonGroup group = new ButtonGroup();
			group.add(trueButton);
			group.add(falseButton);
			group.add(indeterminateButton);
			if (choice == null) {
				indeterminateButton.setSelected(true);
			} else if (choice == StateCondition.TRUE) {
				trueButton.setSelected(true);
			} else if (choice == StateCondition.FALSE) {
				falseButton.setSelected(true);
			} else if (choice == StateCondition.INDETERMINATE) {
				indeterminateButton.setSelected(true);
			}

			this.add(condi);
			this.add(trueButton);
			this.add(falseButton);
			this.add(indeterminateButton);

			// Register a listener for the radio buttons.
			trueButton.addActionListener(this);
			falseButton.addActionListener(this);
			indeterminateButton.addActionListener(this);
			if (!enabled) {
				// trueButton.setEnabled(false);
				// falseButton.setEnabled(false);
				// indeterminateButton.setEnabled(false);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(trueString)){
				Excution.changeCondition(exp,StateCondition.TRUE);
			}else if(e.getActionCommand().equals(falseString)){
				Excution.changeCondition(exp,StateCondition.FALSE);
			}else {
				Excution.changeCondition(exp,StateCondition.INDETERMINATE);
			}
			starTest();
		}
	}

}

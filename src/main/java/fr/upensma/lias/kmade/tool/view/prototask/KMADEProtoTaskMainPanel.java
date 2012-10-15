package fr.upensma.lias.kmade.tool.view.prototask;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.prototask.Excution;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;

public class KMADEProtoTaskMainPanel extends JFrame {

	private ArrayList<Tache> displayTask = new ArrayList<Tache>();
	private JDesktopPane desktop ;
	public KMADEProtoTaskMainPanel(){
		super(KMADEConstant.PROTOTYPING_TOOL_TITLE_NAME);
		constructMainPanel();


	}



	public void constructMainPanel(){
		//use deslop pane
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closePrototypeDialog();
			}
		});
		Dimension screen_dimension = Toolkit.getDefaultToolkit()
				.getScreenSize();
		//TODO dont understant why it is /4 and /2
		this.setLocation(
				(int) ((screen_dimension.getWidth() - this.getWidth()) / 4),
				(int) ((screen_dimension.getHeight() - this.getHeight()) / 4));
		// JPanel for constraint
		desktop = new JDesktopPane();
		this.setContentPane(desktop);
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		constructTaskFrame();


	}




	public void constructHistoryPanel(){

	}

	public void constructTaskFrame(){
		//recuperer les tâches en active/attente fin , faire des frames avec
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		displayTask.clear();
		desktop.removeAll();
		displayTask.addAll(getTaskInState(StateExecution.ACTIVE));
		displayTask.addAll(getTaskInState(StateExecution.ATTENTEFIN));
		//construire les internal frames
		for (Tache tache : displayTask) {
			// construction des frmes :
			JInternalFrame frame = new JInternalFrame();
			frame.setVisible(true);
			// 1 : la description
			frame.add(constructDescriptionPanel(tache));
			// 4 : construire le condition panel (ne pas oublié les itérations des sous tâches si la première fois !!!

			// 5 : construire nextstep panel, avec la liste des subtask, avec le attente fin/attentefinko
			//
		}
	}





	public JPanel constructDescriptionPanel(final Tache tache){
		JPanel descriptionPanel = new JPanel();
		//head
		JPanel headPanel = new JPanel();
		headPanel.setLayout(new BoxLayout(headPanel,BoxLayout.PAGE_AXIS));
		JLabel nameLabel = new JLabel("<HTML><FONT SIZE=7><B>" + tache.getName()+"</B></FONT></HTML>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_NAME_TOOLTIP);
		headPanel.add(nameLabel);
		headPanel.add(Box.createVerticalStrut(2));
		ImageIcon executantImage = null;
		String executantText = "";
		switch(tache.getExecutant()){
		case ABS :
			//	executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_48_IMAGE));
			//	executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_ABS;
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
		headPanel.add(executantLabel);
		TitledBorder ordoPanelTitle = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_DECOMPOSITION_TITLE + " : "+ tache.getDecomposition().getValue(),TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		//JScrollPane possibleTaskScroll = new JScrollPane() ;
		//possibleTaskScroll.setBorder(ordoPanelTitle);


		//Description 	
		String observation =  tache.getObservation();
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
				ExpressTask.setObservationTask(tache,descriptionLabel.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(tache,descriptionLabel.getText());				
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(tache,descriptionLabel.getText());
			}
		});

		//precondition
		String precondition = tache.getPreExpression().getDisplayableSemiFormalCondition();
		TitledBorder titleprecondition = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ",TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		JTextArea preconditionLabel = new JTextArea(precondition);
		preconditionLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		preconditionLabel.setEditable(false);
		preconditionLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TOOLTIP);
		preconditionLabel.setBackground(Color.white);
		preconditionLabel.setLineWrap(true);
		preconditionLabel.setWrapStyleWord(true);
		JScrollPane preconditionScroll = new JScrollPane();
		preconditionScroll.setViewportView(preconditionLabel);
		preconditionScroll.setBorder(titleprecondition);

		descriptionPanel.add(headPanel);
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(preconditionScroll);
		return descriptionPanel;



	}


	//TODO à faire
	public void constructConditionPanel(Tache tache){
		ArrayList<Tache> fullTask = getTaskInState(StateExecution.ACTIVE);
		for (Tache t : fullTask) {
			if(t.getMotherTask() !=null && t.getMotherTask().equals(tache)){
//				LinkedHashMap<String, ChoiceEnum> map = t.getPreExpression().getParseValue();
			}
		}

		// recuperé les conditions des tâches
		// construire l'ensemble des conditions avec des conditionLine


	}

	public void constructNextStepPanel(Tache tache){
		JPanel nextStepPanel = new JPanel();
		if(tache.getStateExecution() == StateExecution.ATTENTEFIN){
			//affichage de valider et terminer
		}
		if (tache.getStateExecution() == StateExecution.ATTENTEFINKO){
			//affichage itération
		}
		int number = 0 ;
		boolean haveNumber = false;
		if(tache.getDecomposition()==Decomposition.SEQ){
			haveNumber = true;
		}
		for(Tache t : tache.getFils()){
			if(haveNumber){
				number++;
			}
			nextStepButton buttun = new nextStepButton(t, number);
			nextStepPanel.add(buttun);
		}

	}


	/*private void taskToDisplay(Tache tache) {
		StateExecution state = tache.getStateExecution();
		if (state == StateExecution.ATTENTEFIN || state == StateExecution.ACTIVE){
			DisplayTask.add(tache);
		}
		ArrayList<Tache> fils = tache.getFils();
		for (Tache child : fils) {
			taskToDisplay(child);
		}

	}*/

	private ArrayList<Tache> getTaskInState(StateExecution state){
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		ArrayList<Tache> res = new ArrayList<Tache>();
		for (Tache tache : root) {
			res.addAll(getTaskInStateRec(state,res,tache));
		}
		return res;

	}

	private ArrayList<Tache> getTaskInStateRec(StateExecution state, ArrayList<Tache> prev, Tache t){
		if(t.getStateExecution() == state){
			prev.add(t);
			for (Tache fils : t.getFils()) {
				prev = getTaskInStateRec(state, prev, fils);
			}
		}
		return prev;
	}

	public void closePrototypeDialog() {
		this.setVisible(false);
	}

	class nextStepButton extends JButton implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Tache t;

		nextStepButton(Tache t, int number){
			this.t = t;
			String name ;
			if(number !=0){
				name = t.getName();
			}else{
				name = number +" : "+ t.getName();
			}
			switch(t.getStateExecution()){
			case ACTIVABLE : this.setText( "<HTML>"+name+ "<HTML>"); break;
			case INACTIVABLE : this.setText( "<HTML>"+name+ "<HTML>"); this.setEnabled(false) ;
			case PASSEE :this.setText( "<HTML>"+name+ "<HTML>"); this.setEnabled(false) ;break;
			case FINIE :  this.setText( "<HTML><S>"+name+ "</S><HTML>"); this.setEnabled(false) ; break;
			default : this.setText( "<HTML>"+name+ "<HTML>");
			}
			

		}
/*
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Excution.setActive(t);
		}*/

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	class conditionLine extends JPanel implements ActionListener  { 
		private static final long serialVersionUID = 1L;
		String trueString = "Vrai";
		String falseString = "Faux";
		String indeterminateString = "Indeterminé";
		String condition;


		public conditionLine(String conditionDisplay,String condition,ChoiceEnum choice, boolean enabled, boolean shift) {
			this.condition = condition;
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setAlignmentY(TOP_ALIGNMENT);
			this.setAlignmentX(LEFT_ALIGNMENT);
			this.setBorder(LineBorder.createBlackLineBorder());
			JLabel condi = new JLabel();
			String conditext="";
			if(shift)
				conditext+=" → ";
			conditext +=conditionDisplay + " : ";
			condi.setText(conditext);
			condi.setFont(KMADEConstant.TITLE_PROTO_TASK_FONT);
			//Create the radio buttons.
			JRadioButton trueButton = new JRadioButton(trueString);
			// trueButton.setMnemonic(KeyEvent.VK_B);
			trueButton.setActionCommand(trueString);
			trueButton.setSelected(true);

			JRadioButton falseButton = new JRadioButton(falseString);
			//   falseButton.setMnemonic(KeyEvent.VK_C);
			falseButton.setActionCommand(falseString);

			JRadioButton indeterminateButton = new JRadioButton(indeterminateString);
			// indeterminateButton.setMnemonic(KeyEvent.VK_D);
			indeterminateButton.setActionCommand(indeterminateString);


			//Group the radio buttons.
			ButtonGroup group = new ButtonGroup();
			group.add(trueButton);
			group.add(falseButton);
			group.add(indeterminateButton);
			if(choice == null){
				indeterminateButton.setSelected(true);
			}else if(choice == ChoiceEnum.vrai){
				trueButton.setSelected(true);
			}else if(choice == ChoiceEnum.faux){
				falseButton.setSelected(true);
			}else if(choice == ChoiceEnum.indeterminée){
				indeterminateButton.setSelected(true);
			}

			this.add(condi);
			this.add(trueButton);
			this.add(falseButton);
			this.add(indeterminateButton);

			//Register a listener for the radio buttons.
			trueButton.addActionListener(this);
			falseButton.addActionListener(this);
			indeterminateButton.addActionListener(this);
			if(!enabled){
				// 	trueButton.setEnabled(false);
				//	falseButton.setEnabled(false);
				//indeterminateButton.setEnabled(false);
			}
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			//map.clear();

			/*	if(e.getActionCommand().equals(trueString)){
				map.put(condition,ChoiceEnum.vrai);
			}else if(e.getActionCommand().equals(falseString)){
				map.put(condition,ChoiceEnum.faux);
			}else if(e.getActionCommand().equals(indeterminateString)){
				map.put(condition,ChoiceEnum.indeterminée);				
			}
			//todo envoyer la maj du tableau
			displayed.clear();
			PROTOExecution.updateCondition(map);*/
		}
	}



}

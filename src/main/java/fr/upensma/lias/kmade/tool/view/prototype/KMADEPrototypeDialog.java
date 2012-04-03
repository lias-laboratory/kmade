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
package fr.upensma.lias.kmade.tool.view.prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.PROTOExecution;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.PROTOHistoric;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas Lachaume
 *
 */
public class KMADEPrototypeDialog extends JFrame {


	private static final long serialVersionUID = -3520781345362173500L;
	private JPanel task;
	private Box conditionBox;
	private Box possibleTask;
	private JPanel myContentPane; 
	private JPanel buttonPanel;
	private KMADEHistoricPanel historicPanel;
	//private JScrollPane taskScroll ;
	private JScrollPane possibleTaskScroll ;
	private JScrollPane conditionScroll;
	private JPanel rightBotTaskPanel;
	private JButton buttonTermine;
	//	private JPanel conditionPanel;
	private static HashMap<String,ChoiceEnum> map = new HashMap<String, ChoiceEnum>() ;
	private static ArrayList<String> displayed = new ArrayList<String>();

	public KMADEPrototypeDialog() {
		super(KMADEConstant.PROTOTYPING_TOOL_TITLE_NAME);
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
		this.myContentPane = new JPanel();
		this.setContentPane(myContentPane);

		myContentPane.setLayout(new BorderLayout());

		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel myBotPanel = new JPanel();

		KMADEEnhancedSplitPane myCenterPanel =  KMADEEnhancedSplitPane
				.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
						leftPanel,rightPanel);
		myCenterPanel.setResizeWeight(1);
		myCenterPanel.setDividerBorderVisible(true);
		myCenterPanel.setOneTouchExpandable(true);

		leftPanel.setLayout(new BorderLayout());

		//taskScroll = new JScrollPane();
		possibleTaskScroll = new JScrollPane();
		conditionScroll = new JScrollPane();
		this.task = new JPanel();
		//task.setLayout(new GridLayout(4,1));
		task.setLayout(new BoxLayout(task, BoxLayout.PAGE_AXIS));
		//task.setAlignmentX(LEFT_ALIGNMENT);
		//task.setAlignmentY(TOP_ALIGNMENT);


		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setAlignmentY(TOP_ALIGNMENT);
		this.possibleTask = Box.createVerticalBox();
		possibleTask.setAlignmentX(LEFT_ALIGNMENT);
		possibleTask.setAlignmentY(TOP_ALIGNMENT);
		possibleTask.setBackground(Color.WHITE);

		this.conditionBox = Box.createVerticalBox();
		conditionBox.setAlignmentX(LEFT_ALIGNMENT);
		conditionBox.setAlignmentY(TOP_ALIGNMENT);
		conditionBox.setBackground(Color.WHITE);


		JPanel botTaskPanel = new JPanel();
		botTaskPanel.setLayout(new BorderLayout());
		rightBotTaskPanel = new JPanel();
		rightBotTaskPanel.setLayout(new BoxLayout(rightBotTaskPanel, BoxLayout.PAGE_AXIS));
		TitledBorder rightBotTaskPanelTitle = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_CONTROL_TITLE,
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		rightBotTaskPanel.setBorder(rightBotTaskPanelTitle);
		buttonPanel = new JPanel();
		buildButtonPanel();
		historicPanel = new KMADEHistoricPanel();

		this.setSize(new Dimension(1000, 700));
		myCenterPanel.setDividerLocation(580);
		botTaskPanel.setPreferredSize(new Dimension (570,200));
		rightBotTaskPanel.setPreferredSize(new Dimension(250,200));

		myContentPane.add(myCenterPanel,BorderLayout.CENTER);
		myContentPane.add(myBotPanel,BorderLayout.SOUTH);

		leftPanel.add(task,BorderLayout.CENTER);
		leftPanel.add(botTaskPanel, BorderLayout.SOUTH);
		//	taskScroll.setViewportView(task);
		possibleTaskScroll.setViewportView(possibleTask);
		conditionScroll.setViewportView(conditionBox);
		botTaskPanel.add(possibleTaskScroll,BorderLayout.CENTER);
		botTaskPanel.add(rightBotTaskPanel,BorderLayout.EAST);
		TitledBorder leftTitle = new TitledBorder(BorderFactory.createLineBorder(Color.BLACK,2),"Tâche en cours",TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		leftPanel.setBorder(leftTitle);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(historicPanel, BorderLayout.CENTER);
		//	 conditionPanel = new JPanel();
		//	 conditionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//	 conditionPanel.setAlignmentY(TOP_ALIGNMENT);
		//	 conditionPanel.setAlignmentX(LEFT_ALIGNMENT);

		//conditionBox.setBackground(Color.WHITE);
		myBotPanel.add(buttonPanel);
		setEnabledEnd(null,false);
		this.validate();
		this.repaint();
	}


	private void buildButtonPanel() {
		JButton reset = new JButton(KMADEConstant.PROTOTYPING_TOOL_RESET);
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				task.removeAll();
				possibleTask.removeAll();
				conditionBox.removeAll();
				map.clear();
				PROTOExecution.startExecution();
			}
		});
		buttonPanel.add(reset);
	}


	public void openPrototypeDialog(){
		this.setVisible(true);
		displayed.clear();
		GraphicEditorAdaptator.selectNoTask();
		PROTOExecution.startExecution();
	}


	public void closePrototypeDialog() {
		this.setVisible(false);
	}

	public void setCurrentTask(final Tache currentTask, String displayPrecondition) {
		possibleTask.removeAll();
		possibleTask.revalidate();
		possibleTask.repaint();
		conditionBox.removeAll();
		conditionBox.revalidate();
		conditionBox.repaint();
		task.removeAll();

		//TitledBorder titlepanel = new TitledBorder(BorderFactory.createLineBorder(Color.RED,2),KMADEConstant.PROTOTYPING_TOOL_CURRENT_TASK + " : "+currentTask.getName(),TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK);
		//task.setBorder(titlepanel);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));
		String name = currentTask.getName();
		JLabel nameLabel = new JLabel("<HTML><FONT SIZE=7><B>" + name+"</B></FONT></HTML>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_NAME_TOOLTIP);
		//	nameLabel.setAlignmentX(TOP_ALIGNMENT);
		topPanel.add(nameLabel);
		topPanel.add(Box.createVerticalStrut(2));
		ImageIcon executantImage = null;
		String executantText = "";
		switch(currentTask.getExecutant()){
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
		topPanel.add(executantLabel);
		TitledBorder ordoPanelTitle = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_DECOMPOSITION_TITLE + " : "+ currentTask.getDecomposition().getValue(),TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		possibleTaskScroll.setBorder(ordoPanelTitle);

		//précondition
		String precondition = displayPrecondition;
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

		//Iteration
		String iteration =  currentTask.getIteExpression().getDescription();
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
		String observation =  currentTask.getObservation();
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
				ExpressTask.setObservationTask(currentTask,descriptionLabel.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(currentTask,descriptionLabel.getText());				
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				ExpressTask.setObservationTask(currentTask,descriptionLabel.getText());
			}
		});

		JScrollPane descriptionScroll = new JScrollPane();
		descriptionScroll.setViewportView(descriptionLabel);
		TitledBorder myUserBorder = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",
				TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT);
		descriptionScroll.setBorder(myUserBorder);

		task.add(topPanel);
		task.add(descriptionScroll);
		task.add(preconditionScroll);
		task.add(iterationScroll);
		task.add(conditionScroll);
		task.revalidate();
		task.repaint();

	}


	/**
	 * @param t
	 * @param accessible
	 * @param enabled
	 * @param number if 0 no order
	 */
	public void setExecutableTask(final Tache t,boolean strikeOut,boolean enabled, int number,HashMap<String,ChoiceEnum> primary, HashMap<String,ChoiceEnum> secondary,String displayPrecond) {
		if(primary!=null)
			map.putAll(primary);
		if(secondary!=null)
			map.putAll(secondary);
		JPanel executableTask = new JPanel();
		executableTask.setBackground(Color.WHITE);
		executableTask.setLayout(new FlowLayout(FlowLayout.LEFT));
		executableTask.setAlignmentY(TOP_ALIGNMENT);
		executableTask.setAlignmentX(LEFT_ALIGNMENT);
		String buttonName;
		String numberString;
		if(number == 0){
			numberString = "";
		} else {
			numberString = ""+number+". ";
		}

		if(strikeOut)
			buttonName =  "<HTML><S>" + numberString + t.getName()+"</S></HTML>";
		else
			buttonName =  "<HTML>" + numberString + t.getName()+"</HTML>";
		String toolTip = "";
		if(!t.isLeaf()){
			buttonName += " "+ KMADEConstant.PROTOTYPING_TOOL_SUBTASK_DECOMP;
			toolTip += "<HTML>" + KMADEConstant.PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP1+ " " + t.getFils().size() + " " +  KMADEConstant.PROTOTYPING_TOOL_SUBTASK_DECOMP_TOOLTIP2;
		}else{
			toolTip += "<HTML>" +KMADEConstant.PROTOTYPING_TOOL_SUBTASK_TERMINAL_TOOLTIP;
		}
		if(t.getObservation()!=null && !t.getObservation().equals("")){
			toolTip += "<BR> "+  KMADEConstant.PROTOTYPING_TOOL_SUBTASK_DESCRIPTION_TOOLTIP +" : "+t.getObservation();
		}
		toolTip += "</HTML>";
		JButton name = new JButton(buttonName);
		name.setToolTipText(toolTip);
		name.setFont(KMADEConstant.TASK_NAME_FONT);
		ImageIcon executantImage = null;
		switch(t.getExecutant()){
		case ABS : /* executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE))*/; break;
		case INCONNU:/*executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE))*/;break;
		case INT : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));break;
		case SYS : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));break;
		case USER : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_16_IMAGE));break;
		}
		name.setIcon(executantImage);

		executableTask.setMaximumSize(new Dimension(500, name.getPreferredSize().height + 10));
		name.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PROTOHistoric.descendre();
				PROTOExecution.setCurrentTask(t,true,map);
			}
		});
		trueFalseIndeterminateGroupButton buttonChoice;
		if(enabled){
			name.setEnabled(true);
		}else{
			name.setEnabled(false);
		}


		executableTask.add(name);
		//precondition
		if(primary != null){
			String key = (String) primary.keySet().toArray()[0];
			//if(!displayed.contains(key)){
				//String precond = KMADEConstant.PROTOTYPING_TOOL_SUBTASK_PRECONDITION+" : "+ primary.keySet().toArray()[0];
				String precond = key;
				JLabel preLabel = new JLabel(displayPrecond);
				preLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
				preLabel.setBackground(Color.white);
					executableTask.add(preLabel); 
				System.out.println("precondi primary "+ primary.get(key));
				buttonChoice = new trueFalseIndeterminateGroupButton(precond,key,primary.get(key),true,false);
				conditionBox.add(buttonChoice);
				displayed.add(key);
			//}

		}
		if(secondary != null){
			for(int i =0; i< secondary.size();i++){
				String key = (String) secondary.keySet().toArray()[i];
				//if(!displayed.contains(key)){
					//String precond = KMADEConstant.PROTOTYPING_TOOL_SUBTASK_PRECONDITION+" : "+ secondary.keySet().toArray()[i];
					String precond = key;
					JLabel preLabel = new JLabel(precond);
					preLabel.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
					preLabel.setBackground(Color.white);
					//	executableTask.add(preLabel); 
					
					buttonChoice = new trueFalseIndeterminateGroupButton(precond,key,secondary.get(key),true,true);
					conditionBox.add(buttonChoice);
					displayed.add(key);
				//}
			}
		}
		possibleTask.add(executableTask);

		possibleTask.revalidate();
	}


	public void setEnabledEnd(final Tache t,boolean b) {
		rightBotTaskPanel.removeAll();

		JPanel executableTask = new JPanel();
		executableTask.setBackground(Color.white);
		executableTask.setLayout(new FlowLayout(FlowLayout.LEFT));
		executableTask.setAlignmentY(TOP_ALIGNMENT);
		executableTask.setAlignmentX(LEFT_ALIGNMENT);
		JPanel annulerPanel = new JPanel();
		annulerPanel.setBackground(Color.white);
		annulerPanel.setAlignmentY(TOP_ALIGNMENT);
		annulerPanel.setAlignmentX(LEFT_ALIGNMENT);
		annulerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton buttonAnnuler = new JButton( KMADEConstant.PROTOTYPING_TOOL_CANCEL_BUTTON);  
		buttonTermine = new JButton( KMADEConstant.PROTOTYPING_TOOL_END_BUTTON);
		buttonAnnuler.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);
		buttonTermine.setFont(KMADEConstant.TEXT_PROTO_TASK_FONT);

		if(t!=null){
			buttonTermine.setText( KMADEConstant.PROTOTYPING_TOOL_VALIDATE_END_BUTTON);
			buttonAnnuler.setText( KMADEConstant.PROTOTYPING_TOOL_CANCEL_BUTTON); 
			rightBotTaskPanel.setBorder(new TitledBorder(null,
					KMADEConstant.PROTOTYPING_TOOL_CONTROL_TITLE ,
					TitledBorder.LEFT, TitledBorder.TOP, KMADEConstant.TITLE_PROTO_TASK_FONT));
		}

		executableTask.add(buttonTermine);
		annulerPanel.add(buttonAnnuler);
		rightBotTaskPanel.add(executableTask);
		rightBotTaskPanel.add(annulerPanel);
		executableTask.setMaximumSize(new Dimension(500, buttonTermine.getPreferredSize().height + 5));

		if(b){
			buttonTermine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//map.clear();
					displayed.clear();
					PROTOExecution.finishedTask(t,map);
				}
			});
			buttonAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//map.clear();
					displayed.clear();
					PROTOExecution.cancelTask(map);
				}
			});

		}else{
			buttonTermine.setEnabled(false);
			buttonAnnuler.setVisible(false);
		}
		rightBotTaskPanel.revalidate();
		rightBotTaskPanel.repaint();


	}


	public void setIterationEnabled(Tache t,boolean existed, ChoiceEnum enabled) {
		trueFalseIndeterminateGroupButton groupButton;
		if(existed){
			groupButton = new trueFalseIndeterminateGroupButton(t.getIteExpression().getDescription(),t.getIteExpression().getDescription(),enabled,true,false);
			conditionBox.add(groupButton);

			JPanel repeatPanel = new JPanel();
			repeatPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			repeatPanel.setAlignmentY(TOP_ALIGNMENT);
			repeatPanel.setAlignmentX(LEFT_ALIGNMENT);

			JButton repeat = new JButton(KMADEConstant.PROTOTYPING_TOOL_ITERATION_BUTTON);
			repeat.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//map.clear();
					PROTOExecution.repeatCurrentTask(map);
				}
			});
			if(enabled == ChoiceEnum.vrai){
				repeat.setEnabled(true);
				buttonTermine.setEnabled(false);
			}else{
				repeat.setEnabled(false);
				buttonTermine.setEnabled(true);
			}
			repeatPanel.add(repeat);
			rightBotTaskPanel.add(repeatPanel);
			rightBotTaskPanel.revalidate();
			rightBotTaskPanel.repaint();
		}
	}


	public void setHistoricPanel(KMADEHistoricPanel historicPanel) {
		this.historicPanel = historicPanel;
	}


	public KMADEHistoricPanel getHistoricPanel() {
		return historicPanel;
	}




	class trueFalseIndeterminateGroupButton extends JPanel implements ActionListener  { 
		private static final long serialVersionUID = 1L;
		String trueString = "Vrai";
		String falseString = "Faux";
		String indeterminateString = "Indeterminé";
		String condition;


		public trueFalseIndeterminateGroupButton(String conditionDisplay,String condition,ChoiceEnum choice, boolean enabled, boolean shift) {
			this.condition = condition;
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setAlignmentY(TOP_ALIGNMENT);
			this.setAlignmentX(LEFT_ALIGNMENT);
			this.setBorder(LineBorder.createBlackLineBorder());
			JLabel condi = new JLabel();
			String conditext="";
			if(shift)
				conditext+="      ";
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

			if(e.getActionCommand().equals(trueString)){
				System.err.println("condition : "+condition);
				map.put(condition,ChoiceEnum.vrai);
			}else if(e.getActionCommand().equals(falseString)){
				map.put(condition,ChoiceEnum.faux);
			}else if(e.getActionCommand().equals(indeterminateString)){
				map.put(condition,ChoiceEnum.indeterminée);				
			}
			//todo envoyer la maj du tableau
			displayed.clear();
			PROTOExecution.updateCondition(map);
		}
	}



}

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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.PROTOExecution;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.PROTOHistoric;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;


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
	private Box possibleTask;
	private JPanel myContentPane; 
	private JPanel buttonPanel;
	private KMADEHistoricPanel historicPanel;
	private JScrollPane taskScroll ;
	private JScrollPane possibleTaskScroll ;
	private JPanel rightBotTaskPanel;

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

		taskScroll = new JScrollPane();
		possibleTaskScroll = new JScrollPane();

		this.task = new JPanel();
		task.setLayout(new GridLayout(4,1));
		task.setAlignmentX(LEFT_ALIGNMENT);
		task.setAlignmentY(TOP_ALIGNMENT);

		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setAlignmentY(TOP_ALIGNMENT);
		this.possibleTask = Box.createVerticalBox();
		possibleTask.setAlignmentX(LEFT_ALIGNMENT);
		possibleTask.setAlignmentY(TOP_ALIGNMENT);

		JPanel botTaskPanel = new JPanel();
		botTaskPanel.setLayout(new BorderLayout());
		rightBotTaskPanel = new JPanel();
		rightBotTaskPanel.setLayout(new BoxLayout(rightBotTaskPanel, BoxLayout.PAGE_AXIS));
		TitledBorder rightBotTaskPanelTitle = new TitledBorder(null,
				"Pannel de contrôle",
				TitledBorder.LEFT, TitledBorder.TOP);
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
		leftPanel.add(taskScroll,BorderLayout.CENTER);
		leftPanel.add(botTaskPanel, BorderLayout.SOUTH);
		taskScroll.setViewportView(task);
		possibleTaskScroll.setViewportView(possibleTask);
		botTaskPanel.add(possibleTaskScroll,BorderLayout.CENTER);
		botTaskPanel.add(rightBotTaskPanel,BorderLayout.EAST);
		rightPanel.add(historicPanel);

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
				PROTOExecution.startExecution();
			}
		});
		buttonPanel.add(reset);

	}


	public void openPrototypeDialog(){
		this.setVisible(true);
		PROTOExecution.startExecution();
	}


	public void closePrototypeDialog() {
		this.setVisible(false);
	}

	public void setCurrentTask(Tache currentTask) {
		possibleTask.removeAll();
		possibleTask.revalidate();
		possibleTask.repaint();
		possibleTask.add(Box.createVerticalStrut(5));
		task.removeAll();

		TitledBorder titlepanel = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_CURRENT_TASK + " : "+currentTask.getName(),TitledBorder.LEFT, TitledBorder.TOP);
		task.setBorder(titlepanel);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.PAGE_AXIS));
		String name = currentTask.getName();
		JLabel nameLabel = new JLabel("<HTML><FONT SIZE=3>" + KMADEConstant.PROTOTYPING_TOOL_NAME_TITLE +" : "+name+"</size></FONT></HTML>");
		nameLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_NAME_TOOLTIP);
		nameLabel.setAlignmentY(TOP_ALIGNMENT);
		topPanel.add(nameLabel);
		topPanel.add(Box.createVerticalStrut(2));
		ImageIcon executantImage = null;
		String executantText = "";
		switch(currentTask.getExecutant()){
		case ABS :
			executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_48_IMAGE));
			executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_ABS;
			break;
		case INCONNU:executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_48_IMAGE));
		executantText += KMADEConstant.PROTOTYPING_TOOL_EXECUTANT_INCONNU;
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

		TitledBorder ordoPanelTitle = new TitledBorder(null, KMADEConstant.PROTOTYPING_TOOL_DECOMPOSITION_TITLE + " : "+ currentTask.getDecomposition().getValue(),TitledBorder.LEFT, TitledBorder.TOP);
		possibleTaskScroll.setBorder(ordoPanelTitle);

		//précondition
		String precondition = currentTask.getPreExpression().getDescription();
		TitledBorder titleprecondition = new TitledBorder(null,KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE + " : ",TitledBorder.LEFT, TitledBorder.TOP);
		JTextArea preconditionLabel = new JTextArea(precondition);
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
		iterationLabel.setEditable(false);
		iterationLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP);
		iterationLabel.setBackground(Color.white);
		iterationLabel.setLineWrap(true);
		iterationLabel.setWrapStyleWord(true);
		JScrollPane iterationScroll = new JScrollPane();
		iterationScroll.setViewportView(iterationLabel);
		TitledBorder iterationTitle = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_ITERATION_TOOLTIP+" : ",
				TitledBorder.LEFT, TitledBorder.TOP);
		iterationScroll.setBorder(iterationTitle);

		//Description 	
		String observation =  currentTask.getObservation();
		JTextArea descriptionLabel = new JTextArea(observation);
		descriptionLabel.setEditable(false);
		descriptionLabel.setToolTipText(KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TOOLTIP);
		descriptionLabel.setBackground(Color.white);
		descriptionLabel.setLineWrap(true);
		descriptionLabel.setWrapStyleWord(true);
		JScrollPane descriptionScroll = new JScrollPane();
		descriptionScroll.setViewportView(descriptionLabel);
		TitledBorder myUserBorder = new TitledBorder(null,
				KMADEConstant.PROTOTYPING_TOOL_DESCRIPTION_TITLE + " : ",
				TitledBorder.LEFT, TitledBorder.TOP);
		descriptionScroll.setBorder(myUserBorder);

		task.add(topPanel);
		task.add(descriptionScroll);
		task.add(preconditionScroll);
		task.add(iterationScroll);
		task.revalidate();
		task.repaint();

	}


	public void setExecutableTask(final Tache t,boolean enabled, int number) {
		JPanel executableTask = new JPanel();
		executableTask.setLayout(new FlowLayout(FlowLayout.LEFT));
		executableTask.setAlignmentY(TOP_ALIGNMENT);
		executableTask.setAlignmentX(LEFT_ALIGNMENT);
		String buttonName =  number +". "+t.getName();
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
		ImageIcon executantImage = null;
		switch(t.getExecutant()){
		case ABS :  executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.ABSTRACT_TASK_16_IMAGE)); break;
		case INCONNU:executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.UNKNOWN_TASK_16_IMAGE));break;
		case INT : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.INTERACTIF_TASK_16_IMAGE));break;
		case SYS : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.FEEDBACK_TASK_16_IMAGE));break;
		case USER : executantImage =new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.USER_TASK_16_IMAGE));break;
		}
		name.setIcon(executantImage);

		executableTask.setMaximumSize(new Dimension(500, name.getPreferredSize().height + 10));
		name.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PROTOHistoric.descendre();
				PROTOExecution.setCurrentTask(t,true);
			}
		});
		name.setEnabled(enabled);
		executableTask.add(name);
		if(t.getPreExpression().getDescription() != null && !t.getPreExpression().getDescription().equals("")){
			String precond = KMADEConstant.PROTOTYPING_TOOL_SUBTASK_PRECONDITION+" : " +t.getPreExpression().getDescription();
			JLabel preLabel = new JLabel(precond);
			preLabel.setBackground(Color.white);
			executableTask.add(preLabel);
		}
		possibleTask.add(executableTask);
		possibleTask.revalidate();
	}


	public void setEnabledEnd(final Tache t,boolean b) {
		rightBotTaskPanel.removeAll();
		JPanel executableTask = new JPanel();
		executableTask.setLayout(new FlowLayout(FlowLayout.LEFT));
		executableTask.setAlignmentY(TOP_ALIGNMENT);
		executableTask.setAlignmentX(LEFT_ALIGNMENT);
		JPanel annulerPanel = new JPanel();
		annulerPanel.setAlignmentY(TOP_ALIGNMENT);
		annulerPanel.setAlignmentX(LEFT_ALIGNMENT);
		annulerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton buttonAnnuler = new JButton( KMADEConstant.PROTOTYPING_TOOL_CANCEL_BUTTON);  
		JButton buttonTermine = new JButton( KMADEConstant.PROTOTYPING_TOOL_END_BUTTON);
		if(t!=null){
			buttonTermine.setText( KMADEConstant.PROTOTYPING_TOOL_VALIDATE_END_BUTTON +" : "+t.getName());
			buttonAnnuler.setText( KMADEConstant.PROTOTYPING_TOOL_CANCEL_BUTTON+ " : "+ t.getName()); 
			rightBotTaskPanel.setBorder(new TitledBorder(null,
					KMADEConstant.PROTOTYPING_TOOL_CONTROL_TITLE+ " : "+t.getName() ,
					TitledBorder.LEFT, TitledBorder.TOP));
		}

		executableTask.add(buttonTermine);
		annulerPanel.add(buttonAnnuler);

		rightBotTaskPanel.add(executableTask);

		rightBotTaskPanel.add(annulerPanel);
		executableTask.setMaximumSize(new Dimension(500, buttonTermine.getPreferredSize().height + 5));

		if(b){
			buttonTermine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PROTOExecution.finishedTask(t);
				}
			});
			buttonAnnuler.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					PROTOExecution.cancelTask();
				}
			});

		}else{
			buttonTermine.setEnabled(false);
			buttonAnnuler.setVisible(false);
		}
		rightBotTaskPanel.revalidate();
		rightBotTaskPanel.repaint();


	}


	public void setIterationEnabled(boolean enabled) {

		JPanel repeatPanel = new JPanel();
		repeatPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		repeatPanel.setAlignmentY(TOP_ALIGNMENT);
		repeatPanel.setAlignmentX(LEFT_ALIGNMENT);

		JButton repeat = new JButton(KMADEConstant.PROTOTYPING_TOOL_ITERATION_BUTTON);
		repeat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PROTOExecution.repeatCurrentTask();
			}
		});
		repeatPanel.add(repeat);
		rightBotTaskPanel.add(repeatPanel);
		rightBotTaskPanel.revalidate();
		rightBotTaskPanel.repaint();
	}


	public void setHistoricPanel(KMADEHistoricPanel historicPanel) {
		this.historicPanel = historicPanel;
	}


	public KMADEHistoricPanel getHistoricPanel() {
		return historicPanel;
	}








}

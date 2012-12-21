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
import fr.upensma.lias.kmade.tool.coreadaptator.prototask.ProtoTaskException;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

public class KMADEProtoTaskMainPanel extends JFrame {
	int gap = 30;
	private ArrayList<Tache> displayTask = new ArrayList<Tache>();
	private JDesktopPane desktop;

	public KMADEProtoTaskMainPanel() {
		super(KMADEConstant.PROTOTYPING_TOOL_TITLE_NAME);
		constructMainPanel();

	}

	public void constructMainPanel() {
		// use deslop pane
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() { public void
			windowClosing(WindowEvent e) { closeProtoTaskMainFrame(); } });

		Dimension screen_dimension = Toolkit.getDefaultToolkit().getScreenSize();
		// TODO dont understant why it is /4 and /2
		this.setLocation(
				(int) ((screen_dimension.getWidth() - this.getWidth()) / 4),
				(int) ((screen_dimension.getHeight() - this.getHeight()) / 4));
		// JPanel for constraint
		desktop = new JDesktopPane();
		this.setContentPane(desktop);
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		this.setPreferredSize(new Dimension(600, 600));
		desktop.setPreferredSize(new Dimension(600, 600));
		this.pack();
		
		this.validate();
		this.repaint();
	}

	public void constructHistoryPanel() {

	}

	private ArrayList<Tache> getTaskInState(StateExecution state) {
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		ArrayList<Tache> res = new ArrayList<Tache>();
		for (Tache tache : root) {
			res.addAll(getTaskInStateRec(state, new ArrayList<Tache>(), tache));
		}

		return res;

	}

	private ArrayList<Tache> getTaskInStateRec(StateExecution state,
			ArrayList<Tache> prev, Tache t) {
		if (t.getStateExecution() == state) {
			prev.add(t);
		}
		for (Tache fils : t.getFils()) {
			prev = getTaskInStateRec(state, prev, fils);

		}
		return prev;
	}

	private void starTest() {
		// TODO Auto-generated method stub

		ArrayList<Tache> active = getTaskInState(StateExecution.ACTIVE);
		ArrayList<Tache> attentefin = getTaskInState(StateExecution.ATTENTEFIN);
		// active.addAll(getTaskInState(StateExecution.ATTENTEFINKO));
		System.out.println(getTaskInState(StateExecution.ACTIVABLE).size()+ "ACTIVABLE");
		System.out.println(getTaskInState(StateExecution.ACTIVE).size()+ "ACTIVE");
		System.out.println(getTaskInState(StateExecution.ATTENTEFIN).size()
				+ "ATTENTEFIN");
		System.out.println(getTaskInState(StateExecution.ATTENTEFINKO).size()
				+ "ATTENTEFINKO");
		System.out.println(getTaskInState(StateExecution.ATTENTETASK).size()
				+ "ATTENTETASK");

		System.out.println(getTaskInState(StateExecution.FINISHED).size()
				+ "FINISHED");
		System.out.println(getTaskInState(StateExecution.INACTIVABLE).size()
				+ "INACTIVABLE");
		System.out.println(getTaskInState(StateExecution.INACTIVE).size()
				+ "INACTIVE");
		System.out.println(getTaskInState(StateExecution.PASSIVE).size()
				+ "PASSIVE");
		System.out.println(getTaskInState(StateExecution.PASSEE).size()
				+ "PASSEE");
		System.out.println(getTaskInState(StateExecution.WAITEND).size()
				+ "WAITEND");
		for (Tache t : attentefin) {
			displayWaitEnd(t, gap);
			if (gap > 500)
				gap = 0;
			else
				gap += 100;

		}

		for (Tache t : active) {
			displayActive(t, gap);
			if (gap > 500)
				gap = 0;
			else
				gap += 100;

		}
	}

	private void displayWaitEnd(final Tache t, int gap2) {
		JInternalFrame frame = new JInternalFrame(t.getName(), true, false,
				true, true);
		frame.setBounds(gap, gap, 100, 100);
		frame.setVisible(true);
		desktop.add(frame);
		frame.setLayout(new FlowLayout());
		ArrayList<Tache> fils = t.getFils();
		for (final Tache tache : fils) {
			JButton b = new JButton(tache.getName() + "lolilol");
			// b.setName(tache.getName());
			if (tache.getStateExecution() == StateExecution.ACTIVABLE) {
				b.setEnabled(true);
			} else {
				b.setEnabled(false);
			}
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						Excution.doTask(tache);
						starTest();

					} catch (ProtoTaskException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			frame.add(b);
		}
		JButton validate = new JButton("valider");
		validate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Excution.endTask(t);
					starTest();
				} catch (ProtoTaskException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.add(validate);

	}

	private void displayActive(Tache active, int gap) {
		System.out
		.println(active.getName() + "  " + active.getStateExecution());

		JInternalFrame frame = new JInternalFrame(active.getName(), true,
				false, true, true);
		frame.setBounds(gap, gap, 100, 100);
		frame.setVisible(true);
		desktop.add(frame);
		frame.setLayout(new FlowLayout());
		ArrayList<Tache> fils = active.getFils();
		for (final Tache tache : fils) {
			JButton b = new JButton(tache.getName() + "lolilol");
			// b.setName(tache.getName());
			if (tache.getStateExecution() == StateExecution.ACTIVABLE) {
				b.setEnabled(true);
			} else {
				b.setEnabled(false);
			}
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						Excution.doTask(tache);
						starTest();

					} catch (ProtoTaskException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			frame.add(b);
		}
	}

	class conditionLine extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		String trueString = "Vrai";
		String falseString = "Faux";
		String indeterminateString = "Indeterminé";
		String condition;

		public conditionLine(String conditionDisplay, String condition,
				ChoiceEnum choice, boolean enabled, boolean shift) {
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
			} else if (choice == ChoiceEnum.vrai) {
				trueButton.setSelected(true);
			} else if (choice == ChoiceEnum.faux) {
				falseButton.setSelected(true);
			} else if (choice == ChoiceEnum.indeterminée) {
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
			
/*
			
			 if(e.getActionCommand().equals(trueString)){
			//	 Excution.changeCondition();
			 }else
			  if(e.getActionCommand().equals(falseString)){
			  map.put(condition,ChoiceEnum.faux); }else
			  if(e.getActionCommand().equals(indeterminateString)){
			  map.put(condition,ChoiceEnum.indeterminée); } //todo envoyer la
			  maj du tableau displayed.clear();
			  PROTOExecution.updateCondition(map);
			 
		}*/
	}}

	public void openProtoTaskMainFrame() {
		this.setVisible(true);
		GraphicEditorAdaptator.selectNoTask();
		desktop.removeAll();
		Excution.resetScenario();
		
		this.starTest();

	}

	public void closeProtoTaskMainFrame() {
		this.setVisible(false);
	}

}

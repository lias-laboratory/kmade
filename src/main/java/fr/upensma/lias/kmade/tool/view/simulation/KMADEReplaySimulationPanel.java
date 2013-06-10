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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import org.jdesktop.animation.timing.Cycle;
import org.jdesktop.animation.timing.Envelope;
import org.jdesktop.animation.timing.TimingController;
import org.jdesktop.animation.timing.TimingEvent;
import org.jdesktop.animation.timing.TimingListener;
import org.jdesktop.animation.timing.interpolation.ObjectModifier;
import org.jdesktop.animation.timing.interpolation.PropertyRange;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenRecordScenarioSimulation;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenReplayScenarioSimulation;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenSimulation;
import fr.upensma.lias.kmade.tool.view.KMADEToolToolBar;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.SimulationAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReplaySimulationPanel extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = -6856161653751795409L;

    private JList myTaskList;

    private DefaultListModel myReplayModel;

    private JButton openScenario;

    private JButton stopScenario;

    private JButton buttonSave;

    private JToggleButton playScenario;

    private JToggleButton pauseScenario;

    private JButton beforeAction;

    private JButton nextAction;

    private JButton initAction;

    private JCheckBox userValues;

    private DefaultListModel myScenarioReplayModel;

    private JList myScenarioList;

    private TimingController waitController;

    private JSlider mySlider;

    private TitledBorder myTaskListScrollBorder;

    private TitledBorder myScenarioListScrollBorder;

    private TitledBorder myActionsBorder;

    private TitledBorder myReplayOptionsBorder;

    private TitledBorder myTempoBorder;

    private int currentReplaySimulation = 0;

    private TitledBorder myAvailableActionoListScrollBorder;

    private JList availableActionList;

    private DefaultListModel myAvailableActionModel;

    private ClockAction myLabel;

    private static final ImageIcon PLAYER_PLAY_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.PLAYER_PLAY_IMAGE));

    private static final ImageIcon PLAYER_STOP_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.PLAYER_STOP_IMAGE));

    private static final ImageIcon PLAYER_PAUSE_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.PLAYER_PAUSE_IMAGE));

    public static final ImageIcon PLAYER_REW_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.PLAYER_REW_IMAGE));

    public static final ImageIcon PLAYER_FWD_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.PLAYER_FWD_IMAGE));

    public static final ImageIcon SAVEAS_SCENARIO_IMAGE = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.SAVEAS_SCENARIO_IMAGE));

    private static final ImageIcon ONE_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.ONE_STEP_IMAGE));

    private static final ImageIcon TWO_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.TWO_STEP_IMAGE));

    private static final ImageIcon THREE_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.THREE_STEP_IMAGE));

    private static final ImageIcon FOUR_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.FOUR_STEP_IMAGE));

    private static final ImageIcon FIVE_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.FIVE_STEP_IMAGE));

    private static final ImageIcon SIX_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.SIX_STEP_IMAGE));

    private static final ImageIcon SEVEN_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.SEVEN_STEP_IMAGE));

    private static final ImageIcon HEIGH_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.HEIGH_STEP_IMAGE));

    private static final ImageIcon NINE_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.NINE_STEP_IMAGE));

    private static final ImageIcon TEN_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.TEN_STEP_IMAGE));

    private static final ImageIcon ELEVEN_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.ELEVEN_STEP_IMAGE));

    private static final ImageIcon TWELVE_TIME = new ImageIcon(
	    KMADETaskModelToolBar.class
		    .getResource(KMADEConstant.TWELVE_STEP_IMAGE));

    private static final int[] speed = { 3500, 3000, 2500, 2000, 1500, 1000,
	    500 };

    public boolean isFinishedReplayScenario() {
	if (myReplayModel.size() == 0) {
	    return true;
	}

	return currentReplaySimulation == myReplayModel.size();
    }

    public void initCurrentReplaySimulation() {
	this.currentReplaySimulation = 0;
	myTaskList.repaint();
    }

    public void incrementCurrentReplaySimulation() {
	this.currentReplaySimulation++;
	myTaskList.setVisibleRowCount(currentReplaySimulation);
	myTaskList.repaint();
    }

    public void setSelectCurrentTask() {
	myTaskList.setSelectedIndex(currentReplaySimulation);
    }

    public JList getScenarioList() {
	return myTaskList;
    }

    public KMADEReplaySimulationPanel() {
	this.setLayout(new BorderLayout());

	myReplayModel = new DefaultListModel();
	myTaskList = new JList(myReplayModel);
	myTaskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	myTaskList.getSelectionModel().addListSelectionListener(
		new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
			SimulationAdaptator.selectAndGoToTask();
		    }
		});
	myTaskList.setCellRenderer(new ListCellRenderer() {
	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {
		Color myColor = null;
		if (((TokenReplayScenarioSimulation) value).isDoneState()) {
		    myColor = Color.DARK_GRAY;
		} else if (((TokenReplayScenarioSimulation) value)
			.isErrorState()) {
		    myColor = Color.RED;
		}
		if (index == currentReplaySimulation) {
		    myColor = Color.BLUE;
		}

		if (isSelected) {
		    myColor = Color.green;
		}
		JLabel myLabel = new JLabel((index + 1) + " - "
			+ (value == null ? "" : value.toString()));
		myLabel.setOpaque(true);
		myLabel.setBackground(myColor);
		return myLabel;
	    }
	});
	// Replay Scenario
	JScrollPane myTaskListScroll = new JScrollPane(myTaskList);
	this.myTaskListScrollBorder = new TitledBorder(null,
		KMADEConstant.SCENARIO_REPLAY_MESSAGE, TitledBorder.CENTER,
		TitledBorder.TOP);
	myTaskListScroll.setBorder(myTaskListScrollBorder);
	myScenarioReplayModel = new DefaultListModel();
	myScenarioList = new JList(myScenarioReplayModel);
	myScenarioList.setCellRenderer(new ListCellRenderer() {
	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {
		return new JLabel((index + 1) + " - "
			+ (value == null ? "" : value.toString()));
	    }
	});

	// Current Scenario
	JScrollPane myScenarioListScroll = new JScrollPane(myScenarioList);
	this.myScenarioListScrollBorder = new TitledBorder(null,
		KMADEConstant.RECORD_BUILDING_SCENARION_MESSAGE,
		TitledBorder.CENTER, TitledBorder.TOP);
	myScenarioListScroll.setBorder(myScenarioListScrollBorder);

	// Available Actions
	myAvailableActionModel = new DefaultListModel();
	availableActionList = new JList(myAvailableActionModel);
	JScrollPane myAvailableActionListScroll = new JScrollPane(
		availableActionList);
	this.myAvailableActionoListScrollBorder = new TitledBorder(null,
		KMADEConstant.RECORD_AVAILABLE_ACTIONABLE_MESSAGE,
		TitledBorder.CENTER, TitledBorder.TOP);
	myAvailableActionListScroll
		.setBorder(myAvailableActionoListScrollBorder);

	JPanel listPanels = new JPanel();
	listPanels.setLayout(new GridLayout(3, 1));// (listPanels,BoxLayout.Y_AXIS));
	listPanels.add(myTaskListScroll);
	listPanels.add(myAvailableActionListScroll);
	listPanels.add(myScenarioListScroll);

	JPanel panelControl = new JPanel(new BorderLayout(10, 10));
	this.myActionsBorder = new TitledBorder(null,
		KMADEConstant.ACTIONS_SIMULATION_MESSAGE, TitledBorder.CENTER,
		TitledBorder.TOP);
	panelControl.setBorder(this.myActionsBorder);
	panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));

	// La gestion des scénarios (rejeu et sauvegarde).
	JPanel myGestion = new JPanel(new GridLayout(1, 3, 10, 0));
	myGestion
		.add(openScenario = new JButton(KMADEToolToolBar.OPEN_PROJECT));
	openScenario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.loadScenario();
	    }
	});
	myGestion.add(initAction = new JButton(KMADEToolToolBar.CLOSE_PROJECT));
	initAction.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.closeReplayScenario();
	    }
	});
	panelControl.add(myGestion);
	panelControl.add(Box.createRigidArea(new Dimension(0, 5)));
	buttonSave = new JButton(SAVEAS_SCENARIO_IMAGE);
	buttonSave.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.saveScenario();
	    }
	});
	myGestion.add(buttonSave);

	// Les controls du rejeu.
	JPanel myControl = new JPanel(new GridLayout(1, 5, 10, 0));
	myControl.add(stopScenario = new JButton(PLAYER_STOP_IMAGE));
	stopScenario.setEnabled(false);
	stopScenario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.fireStopActionReplaySimulation();
	    }
	});

	myControl.add(playScenario = new JToggleButton(PLAYER_PLAY_IMAGE));
	playScenario.setEnabled(false);
	playScenario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.firePlayActionReplaySimulation();
	    }
	});
	myControl.add(pauseScenario = new JToggleButton(PLAYER_PAUSE_IMAGE));
	pauseScenario.setEnabled(false);
	pauseScenario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SimulationAdaptator.firePauseActionReplaySimulation();
	    }
	});
	myControl.add(beforeAction = new JButton(PLAYER_REW_IMAGE));
	beforeAction.setEnabled(false);
	beforeAction.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    }
	});
	myControl.add(nextAction = new JButton(PLAYER_FWD_IMAGE));
	nextAction.setEnabled(false);
	nextAction.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// Ce bouton devrait être désactivé quand le scénario est
		// terminé, mais nous ne savons jamais ...
		if (currentReplaySimulation < myReplayModel.getSize()) {
		    SimulationAdaptator
			    .fireNextActionReplaySimulation(((TokenReplayScenarioSimulation) myReplayModel
				    .get(currentReplaySimulation)));
		}
	    }
	});
	panelControl.add(myControl);
	panelControl.add(Box.createRigidArea(new Dimension(0, 5)));

	// Les options de rejeu.
	JPanel playOptionPanel = new JPanel(new GridLayout(1, 1));
	this.myReplayOptionsBorder = new TitledBorder(null,
		KMADEConstant.REPLAY_OPTIONS_MESSAGE, TitledBorder.LEFT,
		TitledBorder.TOP);
	playOptionPanel.setBorder(this.myReplayOptionsBorder);
	userValues = new JCheckBox(
		KMADEConstant.REPLAY_KEEP_USER_VALUES_MESSAGE);
	playOptionPanel.add(userValues);
	panelControl.add(playOptionPanel);
	panelControl.add(Box.createRigidArea(new Dimension(0, 5)));

	// Le tempo.
	JPanel panelRegulation = new JPanel(new BorderLayout(5, 0));
	this.myTempoBorder = new TitledBorder(null,
		KMADEConstant.REPLAY_TEMPO_MESSAGE, TitledBorder.LEFT,
		TitledBorder.TOP);
	panelRegulation.setBorder(myTempoBorder);
	myLabel = new ClockAction();

	panelRegulation.add(BorderLayout.CENTER, mySlider = new JSlider());
	panelRegulation.add(BorderLayout.EAST, myLabel);
	panelRegulation.add(BorderLayout.SOUTH,
		Box.createRigidArea(new Dimension(0, 5)));

	mySlider.setPaintLabels(true);
	mySlider.setPaintTicks(true);
	mySlider.setPaintTrack(true);
	mySlider.setSnapToTicks(true);
	mySlider.setMajorTickSpacing(1);
	mySlider.setMinorTickSpacing(1);
	mySlider.setMaximum(6);
	mySlider.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent e) {
		if (waitController != null) {
		    waitController.setCycle(new Cycle(
			    speed[mySlider.getValue()], 30));
		}
	    }
	});

	Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
	labelTable.put(new Integer(0), new JLabel(
		KMADEConstant.REPLAY_SLOW_TEMPO));
	labelTable.put(new Integer(3), new JLabel(
		KMADEConstant.REPLAY_MODERATE_TEMPO));
	labelTable.put(new Integer(6), new JLabel(
		KMADEConstant.REPLAY_SPEED_TEMPO));
	mySlider.setLabelTable(labelTable);
	panelControl.add(panelRegulation);

	this.add(BorderLayout.CENTER, listPanels);
	this.add(BorderLayout.SOUTH, panelControl);
    }

    public void clearScenarioPerformedListModel() {
	myReplayModel.clear();
    }

    public void clearAvailableTasksListModel() {
	myAvailableActionModel.clear();
    }

    public void clearScenarioListModel() {
	myScenarioReplayModel.clear();
    }

    public ArrayList<Task> getAvailableTaches() {
	ArrayList<Task> temp = new ArrayList<Task>();
	for (int i = 0; i < this.myAvailableActionModel.size(); i++) {
	    temp.add(((TokenSimulation) (this.myAvailableActionModel.get(i)))
		    .getTask());
	}
	return temp;
    }

    public ArrayList<TokenRecordScenarioSimulation> getReplayScenarioList() {
	ArrayList<TokenRecordScenarioSimulation> myList = new ArrayList<TokenRecordScenarioSimulation>();
	for (int i = 0; i < this.myScenarioReplayModel.size(); i++) {
	    myList.add((TokenRecordScenarioSimulation) this.myScenarioReplayModel
		    .get(i));
	}
	return myList;
    }

    public void updateListEnabledTask(ArrayList<TokenSimulation> my) {
	this.myAvailableActionModel.clear();
	for (int i = 0; i < my.size(); i++) {
	    this.myAvailableActionModel.addElement(my.get(i));
	}
	if (this.myAvailableActionModel.size() != 0) {
	    this.availableActionList.getSelectionModel().setSelectionInterval(
		    0, 0);
	}
    }

    public void updateReplayListTask(
	    ArrayList<TokenReplayScenarioSimulation> list) {
	this.myReplayModel.clear();
	this.initCurrentReplaySimulation();
	for (TokenReplayScenarioSimulation current : list) {
	    this.myReplayModel.addElement(current);
	}
    }

    public void addActionInReplayScenarioModel(TokenRecordScenarioSimulation p) {
	this.myScenarioReplayModel.addElement(p);
    }

    public boolean isEmptyScenarioModel() {
	return this.myScenarioReplayModel.getSize() == 0;
    }

    public void setToNoPlaySelectedStateButton() {
	this.playScenario.setSelected(false);
    }

    public void setToPlaySelectedStateButton() {
	this.playScenario.setSelected(true);
    }

    public void setToInitState() {
	this.stopScenario.setEnabled(false);
	this.initAction.setEnabled(false);
	this.playScenario.setEnabled(false);
	this.playScenario.setSelected(false);
	this.pauseScenario.setEnabled(false);
	this.pauseScenario.setSelected(false);
	this.nextAction.setEnabled(false);
	this.buttonSave.setEnabled(false);
	this.myLabel.setCurrent(0);
    }

    public void setToStopState() {
	this.stopScenario.setEnabled(true);
	this.playScenario.setEnabled(true);
	this.buttonSave.setEnabled(true);
	this.initAction.setEnabled(true);
	this.playScenario.setSelected(false);
	this.pauseScenario.setEnabled(false);
	this.pauseScenario.setSelected(false);
	this.nextAction.setEnabled(false);
	this.myLabel.setCurrent(0);
    }

    public void setToPlayState() {
	this.stopScenario.setEnabled(true);
	this.initAction.setEnabled(true);
	this.playScenario.setEnabled(true);
	this.playScenario.setSelected(true);
	this.pauseScenario.setEnabled(true);
	this.pauseScenario.setSelected(false);
	this.nextAction.setEnabled(false);
    }

    public void setToPauseState() {
	this.stopScenario.setEnabled(true);
	this.initAction.setEnabled(true);
	this.playScenario.setEnabled(true);
	this.playScenario.setSelected(false);
	this.pauseScenario.setEnabled(true);
	this.pauseScenario.setSelected(true);
	this.nextAction.setEnabled(true);
    }

    public void startWaitController() {
	if (waitController == null || !waitController.isRunning()) {
	    Cycle cycle = new Cycle(speed[mySlider.getValue()], 30);
	    Envelope envelope = new Envelope(TimingController.INFINITE, 0,
		    Envelope.RepeatBehavior.FORWARD, Envelope.EndBehavior.RESET);
	    PropertyRange range = PropertyRange.createPropertyRangeInt(
		    "current", 0, 11);
	    waitController = new TimingController(cycle, envelope,
		    new ObjectModifier(myLabel, range));
	    waitController.addTimingListener(new TimingListener() {
		public void timerStarted(TimingEvent arg0) {
		}

		public void timerStopped(TimingEvent arg0) {
		}

		public void timerRepeated(TimingEvent arg0) {
		    waitController.stop();
		    waitController = null;
		    SimulationAdaptator
			    .playTask(((TokenReplayScenarioSimulation) myReplayModel
				    .get(currentReplaySimulation)));
		}
	    });

	    waitController.start();
	}
    }

    public void resumeAfterExecuteAction() {
	if (waitController == null || !waitController.isRunning()) {
	    startWaitController();
	}
    }

    public void pauseWaitController() {
	if (waitController != null && waitController.isRunning()) {
	    waitController.stop();
	}
    }

    public void stopWaitController() {
	if (waitController != null && waitController.isRunning()) {
	    waitController.stop();
	}
    }

    static public class ClockAction extends JLabel {

	private static final long serialVersionUID = 6252811791042175309L;

	protected int current = 0;

	private ImageIcon[] tabImageIcon;

	public ClockAction() {
	    tabImageIcon = new ImageIcon[12];
	    tabImageIcon[0] = TWELVE_TIME;
	    tabImageIcon[1] = ONE_TIME;
	    tabImageIcon[2] = TWO_TIME;
	    tabImageIcon[3] = THREE_TIME;
	    tabImageIcon[4] = FOUR_TIME;
	    tabImageIcon[5] = FIVE_TIME;
	    tabImageIcon[6] = SIX_TIME;
	    tabImageIcon[7] = SEVEN_TIME;
	    tabImageIcon[8] = HEIGH_TIME;
	    tabImageIcon[9] = NINE_TIME;
	    tabImageIcon[10] = TEN_TIME;
	    tabImageIcon[11] = ELEVEN_TIME;
	    this.setImage();
	}

	public int getCurrent() {
	    return current;
	}

	public void setCurrent(int position) {
	    current = position;
	    this.setImage();
	}

	private void setImage() {
	    this.setIcon(tabImageIcon[current]);
	}
    }

    public void notifLocalisationModification() {
    }
}

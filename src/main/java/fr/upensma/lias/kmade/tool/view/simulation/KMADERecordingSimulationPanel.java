package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenRecordScenarioSimulation;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenSimulation;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.viewadaptator.SimulationAdaptator;


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
public class KMADERecordingSimulationPanel extends JPanel {

    private static final long serialVersionUID = -7751795851680946198L;
    
    private DefaultListModel myModel;
    
    private JList availableTaskList;
    
    private TitledBorder myListActivableBorder;
    
    public static final ImageIcon GO_SIMULATION_SCENARIO_IMAGE = new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.GO_SIMULATION_SCENARIO_IMAGE));
    
    public static final ImageIcon PLAYER_ACTION_IMAGE = new ImageIcon(KMADETaskModelToolBar.class.getResource(KMADEConstant.PLAYER_ACTION_IMAGE));
    
    private TitledBorder myScrollListScenarioBorder;
    
    private TitledBorder myActionsBorder;
    
    private JList scenarioList;
    
    private DefaultListModel myScenarioModel;
    
    private JButton undoButton;
       
    private JButton action;
    
    public DefaultListModel getActionListModel() {
        return myModel;
    }
    
    public JList getActionList() {
        return availableTaskList;
    }
    
    public DefaultListModel getScenarioListModel() {
        return myScenarioModel;
    }
    
    public boolean isEmptyScenarioModel() {
        return this.myScenarioModel.getSize() == 0;
    }
    
    public KMADERecordingSimulationPanel() {
        this.setLayout(new BorderLayout());
        myModel = new DefaultListModel();
        availableTaskList = new JList(myModel);
        JScrollPane myScrollListPane = new JScrollPane(availableTaskList);
        this.myListActivableBorder = new TitledBorder(null, KMADEConstant.RECORD_AVAILABLE_ACTIONABLE_MESSAGE, TitledBorder.CENTER, TitledBorder.TOP);
        myScrollListPane.setBorder(myListActivableBorder);
        availableTaskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableTaskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                SimulationAdaptator.selectAndGoToTask();
            }
        });
        availableTaskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SimulationAdaptator.goToTask((TokenSimulation) availableTaskList.getSelectedValue());
                if (e.getClickCount() == 2) {
                    if (availableTaskList.getSelectedValue() != null) {
                        SimulationAdaptator.launchActionFromRecord((TokenSimulation) availableTaskList.getSelectedValue());
                    }
                }
            }
        });
        
        myScenarioModel = new DefaultListModel();
        scenarioList = new JList(myScenarioModel);
        scenarioList.setCellRenderer(new ListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel((index + 1) + " - "+ (value == null ? "" : value.toString()));
            }           
        });
        JScrollPane myScrollListScenarioPane = new JScrollPane(scenarioList);
        this.myScrollListScenarioBorder = new TitledBorder(null, KMADEConstant.RECORD_BUILDING_SCENARION_MESSAGE, TitledBorder.CENTER, TitledBorder.TOP);
        myScrollListScenarioPane.setBorder(myScrollListScenarioBorder);
        scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JPanel listPanels = new JPanel();
        listPanels.setLayout(new BoxLayout(listPanels,BoxLayout.Y_AXIS));
        listPanels.add(myScrollListPane);
        listPanels.add(myScrollListScenarioPane);
        
        this.add(BorderLayout.CENTER, listPanels);
        
        JPanel panelControl = new JPanel(new GridLayout(2,1,0,5));
        this.myActionsBorder = new TitledBorder(null, KMADEConstant.ACTIONS_SIMULATION_MESSAGE, TitledBorder.CENTER, TitledBorder.TOP);
        panelControl.setBorder(myActionsBorder);
        JButton buttonGo = new JButton(GO_SIMULATION_SCENARIO_IMAGE);
        buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationAdaptator.initRecordLaunchSimulation();
            }
        });

        JButton saveScenario = new JButton(KMADEReplaySimulationPanel.SAVEAS_SCENARIO_IMAGE);
        saveScenario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		SimulationAdaptator.saveScenario();
            }
        });
        
        
        JPanel panelInitSave = new JPanel(new GridLayout(1,3,5,0));
        panelInitSave.add(buttonGo);
        panelInitSave.add(saveScenario);
        panelControl.add(panelInitSave);
        
        JPanel panelUndoRedo = new JPanel(new GridLayout(1,3,5,0));
        undoButton = new JButton(KMADEReplaySimulationPanel.PLAYER_REW_IMAGE);
        undoButton.setEnabled(false);
        undoButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   SimulationAdaptator.undoSimulation();
           }
        });
        
        action = new JButton(PLAYER_ACTION_IMAGE);
        action.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if (availableTaskList.getSelectedValue() != null) {
                   SimulationAdaptator.launchActionFromRecord((TokenSimulation) availableTaskList.getSelectedValue());
               }
           }
        });        
        
        JButton redoButton = new JButton(KMADEReplaySimulationPanel.PLAYER_FWD_IMAGE);
        redoButton.setEnabled(false);
        panelUndoRedo.add(undoButton);
        panelUndoRedo.add(action);
        panelUndoRedo.add(redoButton);
        panelControl.add(panelUndoRedo);

               
        this.add(BorderLayout.SOUTH, panelControl);
    }
    
    public void updateListEnabledTask(ArrayList<TokenSimulation> my) {
        this.getActionListModel().clear();
        for (int i = 0; i < my.size(); i++) {
            this.getActionListModel().addElement(my.get(i));
        }
        if (this.getActionListModel().size() != 0) {
            this.getActionList().getSelectionModel().setSelectionInterval(0,0);
        }
    }
    
    public void addActionInScenarioModel(TokenRecordScenarioSimulation p) {
        this.getScenarioListModel().addElement(p);
    }
    
    public void clearLastActionInScenarioModel() {
    	if (this.getScenarioListModel().size() > 0) {
    		this.getScenarioListModel().remove(this.getScenarioListModel().size() - 1);
    	}
    }
    
    public void clearScenarioListModel() {
        this.getScenarioListModel().clear();
    }
    
    public void clearAvailableTasksListModel() {
        this.myModel.clear();
    }
    
    public void setEnableUndoButton() {
    	this.undoButton.setEnabled(true);
    }
    
    public void setTextUndoButton(String p) {
    	this.undoButton.setText(p);
    }
    
    public void setDisableUndoButton() {
    	this.undoButton.setEnabled(false);
    }
    
    public void setToActionState() {
        action.setEnabled(true);
    }
    
    public void setToNoActionState() {
        action.setEnabled(false);
    }
    
    public ArrayList<TokenRecordScenarioSimulation> getRecordScenarioList() {
        ArrayList<TokenRecordScenarioSimulation> myList = new ArrayList<TokenRecordScenarioSimulation>();
        for (int i = 0 ; i < this.getScenarioListModel().size() ; i++) {
            myList.add((TokenRecordScenarioSimulation)this.getScenarioListModel().get(i));             
        }
        return myList;
    }
}

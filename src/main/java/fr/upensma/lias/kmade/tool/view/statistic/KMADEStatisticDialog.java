package fr.upensma.lias.kmade.tool.view.statistic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.TableSorter;
import fr.upensma.lias.kmade.tool.viewadaptator.StatisticAdaptator;


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
public class KMADEStatisticDialog  extends JDialog {

    private static final long serialVersionUID = -3147707051505669774L;

    private JTable myStatisticTable;
    
    private MyDefaultTableModel myDefaultTableModel;
    
    private TableSorter sorter;
    
    public KMADEStatisticDialog(Frame owner) {
        super(owner, KMADEConstant.STATISTIC_TITLE);
        myDefaultTableModel = new MyDefaultTableModel();
        sorter = new TableSorter(myDefaultTableModel);
        myStatisticTable = new JTable(sorter);
        myStatisticTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myStatisticTable.setSelectionBackground(KMADEConstant.ACTIVE_SELECTION);
        sorter.setTableHeader(myStatisticTable.getTableHeader());
        
        JScrollPane myScrollPane = new JScrollPane(myStatisticTable);
        this.getContentPane().add(BorderLayout.CENTER, myScrollPane);
        
        JPanel myPanelControlButtons = new JPanel();
        JButton verifier = new JButton(KMADEConstant.REFRESH_STATISTIC_MESSAGE);
        verifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StatisticAdaptator.statisticTaskModel();
            }
        });
        JButton fermer = new JButton(KMADEConstant.GO_BACK_MESSAGE);
        fermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StatisticAdaptator.closeStatisticDialog();
            }
        });
        myPanelControlButtons.add(verifier);
        myPanelControlButtons.add(fermer);
        this.getContentPane().add(BorderLayout.SOUTH,myPanelControlButtons);
        this.pack();
        this.setSize(new Dimension(700,300));
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
    }
    
    public void setStatisticInfos(ArrayList<Object[]> pList) {
        this.myDefaultTableModel.setTab(pList);
        myDefaultTableModel.fireTableDataChanged();
    }
    
    static class MyDefaultTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 9072531295424161211L;

        private ArrayList<Object[]> myList = new ArrayList<Object[]>();
              
        public void setTab(ArrayList<Object[]> pTab) {
            this.myList = pTab;
        }
        
        public int getColumnCount() {
            return 2;
        }

        public Class<?> getColumnClass(int columnIndex) {
        	switch(columnIndex) {
        		case 0 : return String.class;
        		case 1 : return Integer.class;
        	}
        	return Object.class;
        }
        
        public String getColumnName(int column) {
            switch (column) {
                case 0 : return KMADEConstant.TYPE_STATISTIC_MESSAGE;
                case 1 : return KMADEConstant.RESULT_STATISTIC_MESSAGE;
            }
            return "";
        }

        public int getRowCount() {
            return (myList == null ? 0 : myList.size());
        }

        public Object getValueAt(int row, int column) {
            return myList.get(row)[column];
        }

        public boolean isCellEditable(int row, int column) {
            return super.isCellEditable(row, column);
        }        
    }
}

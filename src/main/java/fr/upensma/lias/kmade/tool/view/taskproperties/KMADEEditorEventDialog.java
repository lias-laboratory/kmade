package fr.upensma.lias.kmade.tool.view.taskproperties;

//� revoir

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultPropertiesTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesEditorDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.EventAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.PrePostIterExpressionAdaptator;


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
public class KMADEEditorEventDialog extends JPropertiesEditorDialog {
    private static final long serialVersionUID = -6348137983085333000L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private ArrayList<String> listOfEvent = new ArrayList<String>();

    public KMADEEditorEventDialog() {
        super();
        this.setModal(false);
        this.setTitle(KMADEConstant.EVENT_TASK_LINKED_TITLE_NAME);
        JScrollPane myScrollPane = new JScrollPane();
        String[] columnNames = { KMADEConstant.EVENT_NAME_TABLE };
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.EVENT_NEW_ROW_TABLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        myScrollPane.setViewportView(myListTable);
        myScrollPane.setColumnHeaderView(myListTable.getPanelHeader());
        myScrollPane.setOpaque(true);
        myScrollPane.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);

        JPanel panelCenter = new JPanel(new GridLayout(2, 1));
        panelCenter.add(myScrollPane);
        panelCenter.add(EventAdaptator.getEventReadPanel());
        myListTable.getMyTable().getColumnModel().getColumn(0).setCellEditor(new MyCustomListTableCellEditor());
        this.getContentPane().add(BorderLayout.CENTER, panelCenter);
        this.setPreferredSize(new Dimension(400, 300));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
    }

    protected void stopEditorDialog() {
        EventAdaptator.setFiredEventsInSelectedTask();
        EventAdaptator.enabledFrame();
        super.stopEditorDialog();
    }

    class MyCustomListTableCellEditor extends AbstractCellEditor implements
            TableCellEditor {
        private static final long serialVersionUID = -4867708548701681535L;

        protected JComboBox myComboBox;

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            String[] temp = new String[listOfEvent.size()];
            myComboBox = new JComboBox(listOfEvent.toArray(temp));
            myComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MyCustomListTableCellEditor.this.stopCellEditing();
                }
            });
            myComboBox.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                }

                public void focusLost(FocusEvent e) {
                    MyCustomListTableCellEditor.this.stopCellEditing();
                }
            });
            return myComboBox;
        }

        public Object getCellEditorValue() {
            return myComboBox.getSelectedItem();
        }
    }

    class MyTableListModel extends DefaultListTableModel {

        private static final long serialVersionUID = -222223380144769136L;

        public MyTableListModel(String[] columnNames, String nameDefault) {
            super(columnNames, nameDefault);
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            String value = (String) aValue;

            if (rowIndex + 1 == this.getRowCount()) {
                // Ajout d'une nouvelle ligne.
                EventAdaptator.addNewEventInSelectedTask(value);
            } else {
                if (EventAdaptator.setEventInSelectedTask((String) myTableListModel.getValueAt(rowIndex, 0),value)) {
                    Object[] myNew = { value, false };
                    myTableListModel.getListTableData().set(row, myNew);
                    myTableListModel.fireTableDataChanged();
                }
            }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (listOfEvent.size() == 0) {
                return KMADEConstant.EVENT_NONEW_ROW_TABLE;
            } else {
                return super.getValueAt(rowIndex, columnIndex);
            }
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (listOfEvent.size() == 0) {
                return false;
            } else {
                return super.isCellEditable(rowIndex, columnIndex);
            }
        }
    }

    public void addNewEvent(String name) {
        Object[] myNew = { name, false };
        myTableListModel.getListTableData().add(myNew);
        myTableListModel.fireTableDataChanged();
        myTableListModel.fireListRowsInserted();
    }

    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int row) {
        EventAdaptator.refreshReadEventTable();
        listOfEvent = EventAdaptator.getEventsName();

        // Récupère l'ensemble des événements de la tâche.
        ArrayList<?> myEventList = (ArrayList<?>) (refModel.getValue(row));
        myTableListModel.getListTableData().clear();
        for (int i = 0; i < myEventList.size(); i++) {
            Object[] tempo = { myEventList.get(i), false };
            myTableListModel.getListTableData().add(tempo);
        }
        myTableListModel.fireTableDataChanged();
        myListTable.getListSelectionModel().setSelectionInterval(
                myTableListModel.getRowCount() - 1,
                myTableListModel.getRowCount() - 1);

        PrePostIterExpressionAdaptator.disabledFrame();
        super.showPropertiesEditor(refModel, row);
    }

    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem("EventTask", r, myTableListModel.getListTableData(),
                        KMADEEditorEventDialog.this, KMADEConstant.EVENT_REMOVE_INTO_TASK);

                if (state) {
                    myTableListModel.fireTableDataChanged();
                    myListTable.getListSelectionModel().setSelectionInterval(
                            myTableListModel.getRowCount() - 1,
                            myTableListModel.getRowCount() - 1);
                }
            }
        }
    }
    
    public void notifLocalisationModification() {
        super.notifLocalisationModification();
        
        // NMDAEditorEventDialog
        this.setTitle(KMADEConstant.EVENT_TASK_LINKED_TITLE_NAME);
        String[] columnNames = { KMADEConstant.EVENT_NAME_TABLE };
        myTableListModel.setColumnNames(columnNames);
    }
}

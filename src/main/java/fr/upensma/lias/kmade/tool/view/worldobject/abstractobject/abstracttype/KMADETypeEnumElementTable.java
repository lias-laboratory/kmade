package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstracttype;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.EnumElementAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TypePanelAdaptator;


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
public class KMADETypeEnumElementTable extends JScrollPane implements LanguageFactory {
    private static final long serialVersionUID = 6709999477752649169L;

    private JListTable myListTable;
    
    private MyTableListModel myTableListModel;    
     
    public KMADETypeEnumElementTable() {
        String[] columnNames = {KMADEConstant.ELEMENT_NAME_TABLE};    
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.ELEMENT_NEW_OBJECT_TABLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        this.setViewportView(myListTable);
        this.setColumnHeaderView(myListTable.getPanelHeader());
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        myListTable.setCellEditor(0,"String",null);       
    }    

    public void updateDataModel(Object[][] o) {
        for (int i = 0; i < o.length; i++) {
            Object[] temp = new Object[3];
            for (int j = 0; j < o[i].length; j++) {
                temp[j] = (String)o[i][j];
            }
            temp[2] = false;
            myTableListModel.getListTableData().add(temp);
        }        
        myTableListModel.fireTableDataChanged();
    }
    
    public void removeAllEnumElement() {
        myTableListModel.removeAllListTableData();
    }
    
    class MyTableListModel extends DefaultListTableModel {
        private static final long serialVersionUID = 7930031534603520066L;
        
        public MyTableListModel(String[] columnNames, String nameDefault) {
            super(columnNames, nameDefault);
        }    
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            String value = (String)aValue;
            if (value.equals("")) {
                return;
            }         
            if (rowIndex + 1 == this.getRowCount()) {
                // Ajouter une nouvelle ligne.                
                String newEnumElement = EnumElementAdaptator.addElement(TypePanelAdaptator.getEnumerationActive());
                value = EnumElementAdaptator.setElementName(newEnumElement, value);
                Object[] tempo = {value, newEnumElement,false};
                myTableListModel.getListTableData().add(tempo);
                this.fireTableRowsInserted(rowIndex,rowIndex+1);
                myTableListModel.fireListRowsInserted();                    
                myListTable.getListSelectionModel().setSelectionInterval(rowIndex,rowIndex);
            } else {
                Object[] tempValue = (Object[])myTableListModel.getListTableData().get(rowIndex);
                String nameRow = (String)tempValue[0];
                String oidRow = (String)tempValue[1];
                
                if (nameRow.equals(value)) {
                    return;
                } else {   
                    switch (columnIndex) {
                        case 0 : value = EnumElementAdaptator.setElementName(oidRow, value); break;
                    }
                    tempValue[columnIndex] = value;
                }
            }
        }      
    }  
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem("EnumElement",r,myTableListModel.getListTableData(), GraphicEditorAdaptator.getMainFrame(), KMADEConstant.ELEMENT_REMOVE_MESSAGE_TITLE);
                
                if (state) {
                    myTableListModel.fireTableDataChanged();                                     
                    myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount()-1,myTableListModel.getRowCount()-1);
                }
            }
        }
    }

    public void notifLocalisationModification() {
        String[] columnNames = {KMADEConstant.ELEMENT_NAME_TABLE};    
        myTableListModel.setColumnNames(columnNames);     
        myTableListModel.setNameDefault(KMADEConstant.ELEMENT_NEW_OBJECT_TABLE);
    }    
}

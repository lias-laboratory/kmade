package kmade.kmade.UI.worldobject.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.DefaultListTableModel;
import kmade.kmade.UI.toolutilities.JListTable;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorFC.parserExpression.RegularExpression;
import kmade.kmade.adaptatorUI.EventAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.nmda.schema.tache.Evenement;

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
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadWriteEventObjectPanel extends JScrollPane implements LanguageFactory {
    private static final long serialVersionUID = -1076733461644634625L;

    private JListTable myListTable;
    
    private MyTableListModel myTableListModel;    
         
    private TitledBorder myTitledBorder;
    
    public KMADEReadWriteEventObjectPanel() {
        String[] columnNames = {KMADEConstant.EVENT_NAME_TABLE, KMADEConstant.EVENT_DESCRIPTION_TABLE};
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.EVENT_NEW_ROW_TABLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        this.setViewportView(myListTable);
        this.setColumnHeaderView(myListTable.getPanelHeader());
        myListTable.setCellEditor(0,"SpecialString",null);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        myTitledBorder = new TitledBorder(null, KMADEConstant.EVENT_TITLE_NAME, TitledBorder.CENTER, TitledBorder.TOP);
        this.setBorder(myTitledBorder);
    }
    
    public void removeAllEvent(){
        myTableListModel.removeAllListTableData();
    }
    
    public DefaultListTableModel getModel() {
        return myTableListModel;
    }
    
    public void addEvent(String name, String description, String oid) {
        Object[] tempo = {name, description, oid, false};
        myTableListModel.getListTableData().add(tempo);
        myTableListModel.fireTableRowsInserted(myTableListModel.getRowCount() -1,myTableListModel.getRowCount() -1);
        myTableListModel.fireListRowsInserted();                  
        myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() -1,myTableListModel.getRowCount() -1);
    } 
    
    class MyTableListModel extends DefaultListTableModel {
        private static final long serialVersionUID = 7930031534603520066L;
        
        public MyTableListModel(String[] columnNames, String nameDefault) {
            super(columnNames, nameDefault);
        }    
        
		private String nameTest(String value){
			String name =value;
			boolean needVerif = true;
			// un nom null annule l'édition, boucle tant que le nom n'est pas correct  
			while (name != null && needVerif){
				// vérification de l'expression régulière avec affichage d'un popUp
				if( !RegularExpression.isGoodEventObjectName(name)){
					name = (String) JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.BAD_CHARACTER_TEXT,
							KMADEConstant.BAD_CARACTER_TITLE,JOptionPane.YES_NO_OPTION,
							new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
					,null,name
					);
				}else{ // l'expression est ok
					if(Evenement.isUniqueName(name)){
						// si le nom est unique, le nom est correct et possible
						needVerif = false;
					} else {
						name = (String)  JOptionPane.showInputDialog(GraphicEditorAdaptator.getMainFrame(),KMADEConstant.SAME_NAME_TEXT,
								KMADEConstant.SAME_NAME_TITLE,JOptionPane.YES_NO_OPTION,
								new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.ASK_DIALOG_IMAGE))
						,null,Evenement.propositionNom(name)
						);
					}		
				}
			}
			return name;
		}
		
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex + 1 == this.getRowCount()) {                
                String value = (String)aValue;
                if (value.equals("")) {
                    return;
                }
                String name = nameTest(value);
				if(name == null || name.equals("")){
					return;	}
				value = name;
                // Ajouter une nouvelle ligne.                
                String newEventObject = EventAdaptator.addEvent();
                value = EventAdaptator.setEventName(newEventObject, value);
                Object[] tempo = {value, "", newEventObject,false};
                myTableListModel.getListTableData().add(tempo);
                this.fireTableRowsInserted(rowIndex,rowIndex+1);
                myTableListModel.fireListRowsInserted();                    
                myListTable.getListSelectionModel().setSelectionInterval(rowIndex,rowIndex);
            } else {              
                Object[] tempValue = (Object[])myTableListModel.getListTableData().get(rowIndex);
                String oidRow = (String)tempValue[2];
                Object value = aValue;
                
                switch(columnIndex) {
                    case 0 : {
                        String nameRow = (String)tempValue[0];
                        if (nameRow.equals((String)aValue))
                            return;
                        String name = nameTest((String)value);
        				if(name == null || name.equals("")){
        					return;	}
        				value = EventAdaptator.setEventName(oidRow, name);
                        break;
                    }
                    case 1 : {
                        EventAdaptator.setEventDescription(oidRow, (String)value);
                        break;
                    }
                }
                tempValue[columnIndex] = value;
            }                
        }  
        
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            default:
                return String.class;
            }
        }
    }
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem("Event",r,myTableListModel.getListTableData(), GraphicEditorAdaptator.getMainFrame(), KMADEConstant.EVENT_REMOVE_NAME_TITLE);
                
                if (state) {
                    myTableListModel.fireTableDataChanged();                                     
                    myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount()-1,myTableListModel.getRowCount()-1);
                }
            }
        }
    }

    public void notifLocalisationModification() {
        String[] columnNames = {KMADEConstant.EVENT_NAME_TABLE, KMADEConstant.EVENT_DESCRIPTION_TABLE};
        myTableListModel.setColumnNames(columnNames);
        myTableListModel.setNameDefault(KMADEConstant.EVENT_NEW_ROW_TABLE);
        myTitledBorder.setTitle(KMADEConstant.EVENT_TITLE_NAME);
    }
}

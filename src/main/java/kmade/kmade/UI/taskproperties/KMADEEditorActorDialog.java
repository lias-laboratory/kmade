package kmade.kmade.UI.taskproperties;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.DefaultListTableModel;
import kmade.kmade.UI.toolutilities.DefaultPropertiesTableModel;
import kmade.kmade.UI.toolutilities.JListTable;
import kmade.kmade.UI.toolutilities.JPropertiesEditorDialog;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.adaptatorUI.ActorAdaptator;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.UserAdaptator;
import kmade.nmda.schema.tache.Acteur;
import kmade.nmda.schema.tache.Experience;

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
public class KMADEEditorActorDialog extends JPropertiesEditorDialog {
    private static final long serialVersionUID = -6348137983085333000L;
    
    private JListTable myListTable;
    
    private MyTableListModel myTableListModel; 
    
    private String[] listOfUser;
    
    public KMADEEditorActorDialog() {
        super();
        this.setModal(false);
        this.setTitle(KMADEConstant.ACTOR_CREATE_TITLE_NAME);
        JScrollPane myScrollPane = new JScrollPane();
        String[] columnNames = {KMADEConstant.ACTOR_NAME_TABLE,KMADEConstant.ACTOR_EXPERIENCE_TABLE, KMADEConstant.ACTOR_COMPETENCE_TABLE};
        myTableListModel = new MyTableListModel(columnNames, KMADEConstant.ACTOR_NEW_ROW_TABLE);
        myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
        myListTable.addAllKeyListener(new MyKeyListener());
        myScrollPane.setViewportView(myListTable);
        myScrollPane.setColumnHeaderView(myListTable.getPanelHeader());
        myScrollPane.setOpaque(true);
        myScrollPane.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        
        JPanel panelCenter = new JPanel(new GridLayout(2,1));
        panelCenter.add(myScrollPane);
        panelCenter.add(UserAdaptator.getUserPanel());
      //  myListTable.getMyTable().getColumnModel().getColumn(0).setCellEditor(new MyCustomListTableCellEditor());
        //myListTable.setCellEditor(0, "List",Experience.getNameLocaleExperience());
        myListTable.setCellEditor(1, "List",Experience.getNameLocaleExperience());
        myListTable.setCellEditor(2, "String",null);
        this.getContentPane().add(BorderLayout.CENTER, panelCenter);
        this.setPreferredSize(new Dimension(700, 300));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
    }

   public void addNewActor(String name, String experience, String competence, String oidActor) {
        Object[] myNewRow = {name, Experience.getEnumereIntoLocaleExperience(experience), competence, oidActor, false};
        myTableListModel.getListTableData().add(myNewRow);
        myTableListModel.fireTableDataChanged();
        myTableListModel.fireListRowsInserted();  
    }
    
    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int row) {
        UserAdaptator.refreshReadUserTable();
        listOfUser = UserAdaptator.getUsersName();
        ArrayList<?> myEventList = (ArrayList<?>)(refModel.getValue(row)); 
        myTableListModel.getListTableData().clear();
        myListTable.setCellEditor(0, "List",listOfUser);
        for (int i = 0; i < myEventList.size(); i++) {
            Acteur myRef = (Acteur)myEventList.get(i);   
        	Object[] myNew = {myRef.getName(), Experience.getEnumereIntoLocaleExperience(myRef.getExperience().getValue()), myRef.getCompetence(), myRef.getOid().get(), false};
            myTableListModel.getListTableData().add(myNew);
        }            
        myTableListModel.fireTableDataChanged();
        myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() -1,myTableListModel.getRowCount() -1);             
        ActorAdaptator.disabledFrame(); 
        super.showPropertiesEditor(refModel, row);
    }
    
    protected void stopEditorDialog() { 
        ActorAdaptator.enabledFrame();
        super.stopEditorDialog();
    }
    
   class MyCustomListTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        private static final long serialVersionUID = 7010635849574676271L;

        protected JComboBox myComboBox;
          
        public Component getTableCellEditorComponent(JTable table,Object value, boolean isSelected, int row, int column) {
            // enlever les users déjà enregistré
        	myComboBox = new JComboBox(listOfUser);
            myComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
        private static final long serialVersionUID = 8733116037423605634L;

        public MyTableListModel(String[] columnNames, String nameDefault) {
            super(columnNames, nameDefault);
        }    
        
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (listOfUser.length == 0 && columnIndex == 0) {
                return KMADEConstant.ACTOR_NONEW_ROW_TABLE;
            } else {
                return super.getValueAt(rowIndex, columnIndex);
            }
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (listOfUser.length == 0) {
                return false;
            } else {
                return super.isCellEditable(rowIndex, columnIndex);
            }
        }
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        	//les jcombobox peuvent faire qu'il y ai des valeurs null
        	if (aValue == null){
        		return;}
            String value = (String)aValue;
            if (rowIndex + 1 == this.getRowCount()) {
                // Ajout d'une nouvelle ligne.
                String[] myValue = ActorAdaptator.addNewActor(value);
                if (myValue == null)	 {
                    return;
                } else {
                    KMADEEditorActorDialog.this.addNewActor(myValue[0],myValue[1],myValue[2],myValue[3]);
                   
                }
            } else {
                Object[] tempValue = (Object[])myTableListModel.getListTableData().get(rowIndex);
                String oidRow = (String)tempValue[3];
                
                switch(columnIndex) {
                    case 0 : {

                        if (((String)this.getValueAt(rowIndex,columnIndex)).equals(value)) {
                       	return;
                        } else {
                        	String newOid =ActorAdaptator.setOldActorSelectedTask(oidRow,value);
                            // s'il n'y a pas eu de modification de l'oid de l'acteur on ne fait rien
                        	if (newOid==null) {
                            	return;
                            }else{
                            	// si il y a eu une mise à jour de l'acteur : l'oid a changer, 
                            	//il faut donc le mettre à jour !
                            	myTableListModel.setValueAt(newOid, rowIndex, 3);
                            }
                        }
                        break;
                    }
                    case 1 : {
                        ActorAdaptator.setActorExperience(oidRow,Experience.getLocaleExperienceIntoEnumere(value));
                        break;
                    }
                    case 2 : {
                        ActorAdaptator.setActorCompetence(oidRow,value);
                        break;
                    }
                }   
                tempValue[columnIndex] = value;
            }
        }
    }   
    
    class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                int[] r = myListTable.getMyTable().getSelectedRows();
                boolean state = GraphicEditorAdaptator.removeSelectedItem("Actor",r,myTableListModel.getListTableData(),KMADEEditorActorDialog.this, KMADEConstant.ACTOR_REMOVE_NAME_TITLE);
                
                if (state) {
                    for(int i = 0 ; i< r.length ;i++)
                    	myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount()-1,myTableListModel.getRowCount()-1);
                }
            }
        }
    }    
    
    public void notifLocalisationModification() {
        super.notifLocalisationModification();
        
        // NMDAEditorEventDialog
        this.setTitle(KMADEConstant.ACTOR_CREATE_TITLE_NAME);
        String[] columnNames = {KMADEConstant.ACTOR_NAME_TABLE,KMADEConstant.ACTOR_EXPERIENCE_TABLE, KMADEConstant.ACTOR_COMPETENCE_TABLE};
        myTableListModel.setColumnNames(columnNames);
    }
}

package kmade.kmade.UI.worldobject.concreteobject;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.KMADEJTable;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorUI.ConcreteAttributAdaptator;
import kmade.nmda.schema.metaobjet.TypeStructure;

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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadWriteConcreteAttributTable extends JScrollPane implements LanguageFactory {

    private static final long serialVersionUID = 2909674045996891355L;
   
    private final MyConcreteAttributTableModel myModel;

    private final JTable table;
       
    public KMADEReadWriteConcreteAttributTable() {
        myModel = new MyConcreteAttributTableModel();        
        table = new KMADEJTable(myModel);
        this.setViewportView(table);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
    }
    
    public void setConcreteAttributNameBorder(String name) {
        this.setBorder(new TitledBorder(null, KMADEConstant.CONCRETE_ATTRIBUT_TITLE_TABLE + " " + name, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, KMADEConstant.fontPASSIF));
    }
    
    class MyConcreteAttributTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 8958017076947468586L;

        private Object[][] data = new Object[0][];

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return data.length;
        }

        public Object getValueAt(int row, int col) {
            switch(col) {
                case 0 : {
                    return ((String)data[row][0]) + " (" + TypeStructure.getEnumereIntoLocaleTypeStructure(((String)data[row][1])) + ")";
                }
                                
                case 1 : {
                    if (data[row][2] == null) {
                        return KMADEConstant.CONCRETE_ATTRIBUT_NOINIT_TABLE; 
                    } else {
                        return data[row][2];
                    }
                }
                case 2 : {
                    return data[row][3];
                }
            }
            return data[row][col];
        }

        public String getColumnName(int i) {
            switch (i) {
            case 0:
                return KMADEConstant.CONCRETE_ATTRIBUT_NAME_TABLE;
            case 1:
                return KMADEConstant.CONCRETE_ATTRIBUT_VALUE_TABLE;
            default:
                return "";
            }
        }

        public void setData(Object[][] data) {            
            this.data = data;
            this.fireTableDataChanged();
        }
        
        public boolean isCellEditable(int row, int col) {
            switch (col) {
                case 1 : {                   
                    String attributName = (String)data[row][0];
                    ArrayList<String> maList = ConcreteAttributAdaptator.getTypeRefValues(attributName);

                    if (maList.size() != 0) {
                        table.getColumnModel().getColumn(1).setCellEditor(new MyCustomTableCellEditor(maList));
                    } else {
                        if ((data[row][2] == null)) {
                            table.getColumnModel().getColumn(1).setCellEditor(new MyCustomStringTableCellEditor(new JTextField("")));
                        } else
                            table.getColumnModel().getColumn(1).setCellEditor(new MyCustomStringTableCellEditor(new JTextField((String)data[row][2].toString())));
                    }                    
                    return true;
                }
                default : return false;
            }
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (columnIndex == 1) {
                if (aValue instanceof String) {
                    boolean isError = ConcreteAttributAdaptator.setConcreteAttributValue((String)this.getValueAt(rowIndex,2),(String)aValue);
                    if (isError)
                        return;
                }
                data[rowIndex][2] = aValue;
            }
        }
    }
    
    class MyCustomStringTableCellEditor extends DefaultCellEditor {
        private static final long serialVersionUID = -266696908264998928L;

        public MyCustomStringTableCellEditor(JTextField textField) {
            super(textField);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {            
            return this.getComponent();
        }        
    }
    
    class MyCustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        private static final long serialVersionUID = -2831224019550106446L;

        protected JComboBox myComboBox;

        public MyCustomTableCellEditor(ArrayList<String> myValue) {            
            this.myComboBox = new JComboBox(myValue.toArray());
            this.myComboBox.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {             
                }

                public void focusLost(FocusEvent e) {
                    MyCustomTableCellEditor.this.stopCellEditing();
                }                
            });
            this.myComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MyCustomTableCellEditor.this.stopCellEditing();                    
                }                
            });
        }

        public Object getCellEditorValue() {
            return myComboBox.getSelectedItem();
        }

        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            return myComboBox;
        }
    }

    public void removeAllConcreteAttribut() {
        myModel.setData(new Object[0][]);
    }

    public void setData(Object[][] refConcreteAttribut) {
        myModel.setData(refConcreteAttribut);
    }

    public void notifLocalisationModification() {
        // Rien        
    }
}

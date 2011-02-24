package kmade.kmade.UI.toolutilities;

import javax.swing.table.AbstractTableModel;

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
public class DefaultPropertiesTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -8704511474362544003L;

    public static final int COLUMN_NAME_INDEX = 0;
       
    public static final int COLUMN_VALUE_INDEX = 1;
    
    public static final int COLUMN_TYPE_INDEX = 2;
    
    public static final int COLUMN_ISEDITABLE_INDEX = 3;
    
    public static final int COLUMN_COMBOVALUE_INDEX = 4;
    
    public static final int COLUMN_EDITOR_INDEX = 5;
    
    public static final int COLUMN_LEGEND_INDEX = 6;
    
    protected PropertiesObject[] informationTable;

    public DefaultPropertiesTableModel(PropertiesObject[] data) {
        this.informationTable = data;
    }

    public void setInformationTable(PropertiesObject[] data) {
        informationTable = data;
    }

    public int getRowCount() {
        return informationTable.length;
    }

    public int getColumnCount() {
        return 2;
    }
    
    public Object getValue(int row) {
        return informationTable[row].getValue();
    }

    public Object getValueAt(int row, int column) {
        switch(column) {
            case COLUMN_NAME_INDEX : {
                return informationTable[row].getName();
            }
            case COLUMN_VALUE_INDEX : {
                return informationTable[row].getValue();
            }
            case COLUMN_TYPE_INDEX : {
                return informationTable[row].getType();
            }
            case COLUMN_ISEDITABLE_INDEX : {
                return informationTable[row].isEditable();
            }
            case COLUMN_COMBOVALUE_INDEX : {
                return informationTable[row].getComboValue();
            }           
            case COLUMN_EDITOR_INDEX : {
                return informationTable[row].getRefDialog();
            }
            case COLUMN_LEGEND_INDEX : {
                return informationTable[row].getDescription();
            }            
            
            default : return null;
        }
    }

    public boolean isCellEditable(int row, int column) {
        if (column == 0)
            return false;

        return informationTable[row].isEditable();
    }

    public void setInitValueAt(Object aValue, int rowIndex) {
    		this.setValueAt(aValue, rowIndex,1);
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return;
        
        switch(columnIndex) {
            case COLUMN_VALUE_INDEX : {
                informationTable[rowIndex].setValue(aValue);
                break;
            }
            case COLUMN_TYPE_INDEX : {
                informationTable[rowIndex].setType((Integer)aValue);
                break;
            }
            case COLUMN_ISEDITABLE_INDEX : {
                informationTable[rowIndex].setEditable((Boolean)aValue);
                break;
            }
            case COLUMN_COMBOVALUE_INDEX : {
                informationTable[rowIndex].setComboValue((String[])aValue);
                break;
            }
        }     
        this.fireTableRowsUpdated(rowIndex,rowIndex);
        return;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}

/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;

/**
 * @author Mickael BARON
 */
public class DefaultListTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -203939424351372382L;

    private MyTableListModel myTableListModel;

    protected String[] columnNames;

    protected String nameDefault;

    protected ArrayList<Object[]> maList = new ArrayList<Object[]>();

    public void setColumnNames(String[] columnNames) {
	this.columnNames = columnNames;
    }

    public void setNameDefault(String pnameDefault) {
	this.nameDefault = pnameDefault;
    }

    public DefaultListTableModel(String[] columnNames, String nameDefault) {
	this.columnNames = columnNames;
	myTableListModel = new MyTableListModel();
	this.columnNames = columnNames;
	this.nameDefault = nameDefault;
    }

    public int getRowCount() {
	return maList.size() + 1;
    }

    public int getColumnCount() {
	return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
	if (rowIndex == this.getRowCount() - 1) {
	    if (columnIndex == 0) {
		return nameDefault;
	    } else {
		return null;
	    }
	} else {
	    return maList.get(rowIndex)[columnIndex];
	}
    }

    public Class<?> getColumnClass(int columnIndex) {
	return String.class;
    }

    public String getColumnName(int column) {
	return columnNames[column];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
	if (rowIndex + 1 == this.getRowCount()) {
	    if (columnIndex != 0) {
		return false;
	    } else {
		return true;
	    }
	} else {
	    return true;
	}
    }

    class MyTableListModel extends DefaultListModel {

	private static final long serialVersionUID = -7208312048831796834L;

	public Object getElementAt(int index) {
	    return "";
	}

	public int getSize() {
	    return DefaultListTableModel.this.getRowCount();
	}

	public void fireContentsChanged() {
	    this.fireContentsChanged(this, 0, this.getSize() - 1);
	}

	public void fireListRowsInserted() {
	    this.fireContentsChanged(this, 0, this.getSize() - 1);
	}
    }

    /**
     * @return Returns the myTableListModel.
     */
    public MyTableListModel getListModel() {
	return myTableListModel;
    }

    public void fireListRowsInserted() {
	myTableListModel.fireListRowsInserted();
    }

    /**
     * @return Returns the maList.
     */
    public ArrayList<Object[]> getListTableData() {
	return maList;
    }

    public void add(Object[] newObject, int row) {
	maList.add(newObject);
	this.fireTableRowsInserted(row, row + 1);
	myTableListModel.fireListRowsInserted();
    }

    public void setListTableData(ArrayList<Object[]> p) {
	this.maList = p;
    }

    public void removeAllListTableData() {
	this.getListTableData().clear();
	this.fireTableDataChanged();
    }

    public void fireTableDataChanged() {
	super.fireTableDataChanged();
	myTableListModel.fireContentsChanged();
    }
}

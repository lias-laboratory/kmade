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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstracttype;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.Intervalle;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.IntervalAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADETypeIntervalTable extends JScrollPane implements
	LanguageFactory {

    private static final long serialVersionUID = -2260679174770283111L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    public KMADETypeIntervalTable() {
	String[] columnNames = { KMADEConstant.INTERVALLE_NAME_TABLE,
		KMADEConstant.INTERVALLE_DESCRIPTION_TABLE,
		KMADEConstant.INTERVALLE_MIN_VALUE_TABLE,
		KMADEConstant.INTERVALLE_MAX_VALUE_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.INTERVALLE_NEW_OBJECT_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.setCellEditor(0, "String", null);
	myListTable.setCellEditor(1, "String", null);
	myListTable.addAllKeyListener(new MyKeyListener());
	this.setViewportView(myListTable);
	this.setColumnHeaderView(myListTable.getPanelHeader());
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	this.setBorder(new TitledBorder(null,
		KMADEConstant.INTERVALLE_TITLE_TABLE, TitledBorder.CENTER,
		TitledBorder.DEFAULT_POSITION));
    }

    public void removeAllInterval() {
	myTableListModel.removeAllListTableData();
    }

    public void addInterval(String name, String description, Integer min,
	    Integer max, String oid) {
	Object[] tempo = { name, description, min, max, oid, false };
	myTableListModel.getListTableData().add(tempo);
	myTableListModel.fireTableRowsInserted(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
	myTableListModel.fireListRowsInserted();
	myListTable.getListSelectionModel().setSelectionInterval(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
    }

    class MyTableListModel extends DefaultListTableModel {
	private static final long serialVersionUID = 7930031534603520066L;

	public Object getValueAt(int rowIndex, int columnIndex) {
	    if (rowIndex == this.getRowCount() - 1) {
		if (columnIndex == 0) {
		    return KMADEConstant.INTERVALLE_NEW_OBJECT_TABLE;
		} else {
		    return "";
		}
	    } else {
		return maList.get(rowIndex)[columnIndex];
	    }
	}

	public MyTableListModel(String[] columnNames, String nameDefault) {
	    super(columnNames, nameDefault);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if (rowIndex + 1 == this.getRowCount()) {
		String value = (String) aValue;
		if (value.equals("")) {
		    return;
		}
		// Ajouter une nouvelle ligne.
		String newInterval = IntervalAdaptator.addInterval();
		value = IntervalAdaptator.setIntervalName(newInterval, value);
		Object[] tempo = { value, "", Intervalle.MIN_DEFAULT,
			Intervalle.MAX_DEFAULT, newInterval, false };
		myTableListModel.getListTableData().add(tempo);
		this.fireTableRowsInserted(rowIndex, rowIndex + 1);
		myTableListModel.fireListRowsInserted();
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);
	    } else {
		Object value = aValue;
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String nameRow = (String) tempValue[0];
		String oidRow = (String) tempValue[4];

		switch (columnIndex) {
		case 0: {
		    if (((String) value).equals("")
			    || ((String) value).equals(nameRow))
			return;
		    value = IntervalAdaptator.setIntervalName(oidRow,
			    (String) value);
		    break;
		}
		case 1:
		    IntervalAdaptator.setIntervalDescription(oidRow,
			    (String) value);
		    break;
		case 2: {
		    Integer maValue = (Integer) value;
		    Integer maxValue = (Integer) tempValue[3];
		    if (maValue >= maxValue) {
			return;
		    }
		    IntervalAdaptator.setIntervalMin(oidRow, (Integer) value);
		    break;
		}
		case 3: {
		    Integer maValue = (Integer) value;
		    Integer minValue = (Integer) tempValue[2];
		    if (maValue <= minValue) {
			return;
		    }
		    IntervalAdaptator.setIntervalMax(oidRow, (Integer) value);
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
	    case 2:
		return Integer.class;
	    case 3:
		return Integer.class;
	    default:
		return String.class;
	    }
	}
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"Interval", r, myTableListModel.getListTableData(),
			GraphicEditorAdaptator.getMainFrame(),
			KMADEConstant.INTERVALLE_REMOVE_MESSAGE_TITLE);

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
	String[] columnNames = { KMADEConstant.INTERVALLE_NAME_TABLE,
		KMADEConstant.INTERVALLE_DESCRIPTION_TABLE,
		KMADEConstant.INTERVALLE_MIN_VALUE_TABLE,
		KMADEConstant.INTERVALLE_MAX_VALUE_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.INTERVALLE_NEW_OBJECT_TABLE);
    }
}

/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.viewadaptator.ReadAbstractObjectAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadAbstractObjectAttributTable extends JScrollPane {

    private static final long serialVersionUID = -1165946201163488052L;

    private MyTableModel myModel;

    private JTable table;

    public KMADEReadAbstractObjectAttributTable() {
	myModel = new MyTableModel();
	table = new KMADEJTable(myModel);
	this.setViewportView(table);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	table.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    if (!table.getSelectionModel().isSelectionEmpty()) {
			ReadAbstractObjectAdaptator.addNewAttribut(table
				.getSelectionModel().getMinSelectionIndex());
		    }
		}
	    }
	});
    }

    public void setData(Object[][] refData) {
	this.myModel.data = refData;
	this.myModel.fireTableDataChanged();
	table.getSelectionModel().clearSelection();
    }

    public void setAttributNameBorder(String name) {
	this.setBorder(new TitledBorder(null,
		KMADEConstant.ABSTRACT_ATTRIBUT_TITLE_TABLE + " " + name,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
		KMADEConstant.fontPASSIF));
    }

    static class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1983463662827857601L;

	private Object[][] data = new Object[0][];

	public int getColumnCount() {
	    return 4;
	}

	public int getRowCount() {
	    return data.length;
	}

	public String getColumnName(int col) {
	    switch (col) {
	    case 0:
		return KMADEConstant.ABSTRACT_ATTRIBUT_NAME_TABLE;
	    case 1:
		return KMADEConstant.ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE;
	    case 2:
		return KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_TABLE;
	    case 3:
		return KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE;
	    default:
		return "";
	    }
	}

	public Object getValueAt(int row, int col) {
	    return data[row][col];
	}

	public boolean isCellEditable(int row, int col) {
	    return false;
	}
    }
}

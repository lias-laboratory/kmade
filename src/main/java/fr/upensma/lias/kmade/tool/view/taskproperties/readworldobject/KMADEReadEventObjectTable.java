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
package fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.viewadaptator.EventAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadEventObjectTable extends JScrollPane {
	private static final long serialVersionUID = 465521L;

	private MyReadEventTableModel modele;

	private JTable table;

	public KMADEReadEventObjectTable() {
		modele = new MyReadEventTableModel();
		table = new KMADEJTable(modele);
		this.setViewportView(table);
		table.addMouseListener(new mouseHelpListener());
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	}

	/**
	 * Constructeur avec deux param�tres qui permet de ne pas mettre le
	 * mouseListener qui ajout des �v�nerments g�n�r�s
	 * 
	 * @param obj
	 * @param editable
	 */
	public KMADEReadEventObjectTable(KMADEReadEventObjectTable obj, boolean editable) {
		modele = obj.modele;
		table = new KMADEJTable(obj.modele);
		this.setViewportView(table);
		if (editable) {
			table.addMouseListener(new mouseHelpListener());
		}
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	}

	public void setData(Object[][] tab) {
		modele.setData(tab);
		modele.fireTableDataChanged();
	}

	static class MyReadEventTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 6638234406364874255L;

		private Object[][] data = new Object[0][];

		public int getColumnCount() {
			return 2;
		}

		public int getRowCount() {
			return data.length;
		}

		public Object getValueAt(int param, int param1) {
			return data[param][param1];
		}

		public String getColumnName(int i) {
			switch (i) {
			case 0:
				return KMADEConstant.EVENT_NAME_TABLE;
			case 1:
				return KMADEConstant.EVENT_DESCRIPTION_TABLE;
			default:
				return "";
			}
		}

		public void setData(Object[][] data) {
			this.data = data;
		}
	}

	class mouseHelpListener extends MouseAdapter {
		public void mouseClicked(MouseEvent mouseEvent) {
			if (mouseEvent.getClickCount() == 2) {
				String select = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				EventAdaptator.addNewEventInSelectedTask(select);
			}
		}
	}
}

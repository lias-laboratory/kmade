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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.EnumAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TypePanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADETypeEnumTable extends JScrollPane implements LanguageFactory {

	private static final long serialVersionUID = -2997356131277434188L;

	private JListTable myListTable;

	private MyTableListModel myTableListModel;

	private int selectedRowCourant = -1;

	public KMADETypeEnumTable() {
		String[] columnNames = { KMADEConstant.ENUMERATION_NAME_TABLE, KMADEConstant.ENUMERATION_DESCRIPTION_TABLE };
		myTableListModel = new MyTableListModel(columnNames, KMADEConstant.ENUMERATION_NEW_OBJECT_TABLE);
		myListTable = new JListTable(myTableListModel, myTableListModel.getListModel());
		myListTable.setCellEditor(0, "String", null);
		myListTable.setCellEditor(1, "String", null);
		myListTable.addAllKeyListener(new MyKeyListener());
		this.setViewportView(myListTable);
		this.setColumnHeaderView(myListTable.getPanelHeader());
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);

		// Abonnement aux diff???rents ???couteurs.
		myListTable.getListSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (myListTable.getListSelectionModel().isSelectionEmpty()) {
					selectedRowCourant = -1;
					TypePanelAdaptator.setActiveEnumeration("", Oid.OID_NULL);
				} else {
					int selectedRow = myListTable.getListSelectionModel().getMinSelectionIndex();

					if (selectedRow != myListTable.getListSelectionModel().getMaxSelectionIndex()) {
						TypePanelAdaptator.setActiveEnumeration("", Oid.OID_NULL);
						return;
					}

					if (selectedRow != selectedRowCourant) {
						if (selectedRow != myTableListModel.getRowCount() - 1) {
							Object[] tempoValue = (Object[]) myTableListModel.getListTableData().get(selectedRow);
							TypePanelAdaptator.setActiveEnumeration((String) tempoValue[0], (String) tempoValue[2]);
							selectedRowCourant = selectedRow;
						} else {
							selectedRowCourant = -1;
							TypePanelAdaptator.setActiveEnumeration("", Oid.OID_NULL);
						}
					}
				}
			}
		});
	}

	public void setFirstRowIndexTable() {
		myListTable.getListSelectionModel().clearSelection();
		myListTable.getListSelectionModel().setSelectionInterval(0, 0);
	}

	public void removeAllEnum() {
		myTableListModel.removeAllListTableData();
	}

	public void addEnum(String name, String description, String oid) {
		Object[] tempo = { name, description, oid, false };
		myTableListModel.getListTableData().add(tempo);
		myTableListModel.fireTableRowsInserted(myTableListModel.getRowCount() - 1, myTableListModel.getRowCount() - 1);
		myTableListModel.fireListRowsInserted();
		myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() - 1,
				myTableListModel.getRowCount() - 1);
	}

	class MyTableListModel extends DefaultListTableModel {
		private static final long serialVersionUID = 7930031534603520066L;

		public MyTableListModel(String[] columnNames, String nameDefault) {
			super(columnNames, nameDefault);
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			String value = (String) aValue;

			if (rowIndex + 1 == this.getRowCount()) {
				if (value.equals("")) {
					return;
				}
				// Ajouter une nouvelle ligne.
				String newEnumeration = EnumAdaptator.addEnum();
				value = EnumAdaptator.setEnumName(newEnumeration, value);
				Object[] tempo = { value, "", newEnumeration, false };
				myTableListModel.getListTableData().add(tempo);
				this.fireTableRowsInserted(rowIndex, rowIndex + 1);
				myTableListModel.fireListRowsInserted();
				myListTable.getListSelectionModel().setSelectionInterval(rowIndex, rowIndex);
			} else {
				Object[] tempValue = (Object[]) myTableListModel.getListTableData().get(rowIndex);
				String oidRow = (String) tempValue[2];

				switch (columnIndex) {
				case 0: {
					String nameRow = (String) tempValue[0];
					if (nameRow.equals(value) || value.equals(""))
						return;
					value = EnumAdaptator.setEnumName(oidRow, value);
					break;
				}
				case 1: {
					EnumAdaptator.setEnumDescription(oidRow, value);
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
				boolean state = GraphicEditorAdaptator.removeSelectedItem("Enumeration", r,
						myTableListModel.getListTableData(), GraphicEditorAdaptator.getMainFrame(),
						KMADEConstant.ENUMERATION_REMOVE_MESSAGE_TITLE);

				if (state) {
					myTableListModel.fireTableDataChanged();
					myListTable.getListSelectionModel().setSelectionInterval(myTableListModel.getRowCount() - 1,
							myTableListModel.getRowCount() - 1);
				}
			}
		}
	}

	public void notifLocalisationModification() {
		// NMDATypeEnumTable
		String[] columnNames = { KMADEConstant.ENUMERATION_NAME_TABLE, KMADEConstant.ENUMERATION_DESCRIPTION_TABLE };
		myTableListModel.setColumnNames(columnNames);
		myTableListModel.setNameDefault(KMADEConstant.ENUMERATION_NEW_OBJECT_TABLE);
	}
}

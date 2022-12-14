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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.view.toolutilities.JTreeTable.JTreeTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.JTreeTable.TreeTableModel;

/**
 * @author Mickael BARON
 */
public class JListTreeTable extends JPanel {

	private static final long serialVersionUID = -4280074759089012114L;

	private JTreeTable myTable;

	private JList myList;

	private JPanel panelHeader;

	private TableListModelAdapter refTableListModelAdapter;

	public JListTreeTable(TreeTableModel treeTableModel) {
		myTable = new JTreeTable(treeTableModel);
		refTableListModelAdapter = new TableListModelAdapter(myTable.getTableTreeModel());
		myList = new JList(refTableListModelAdapter);
		myList.setCellRenderer(new ListTableRenderer());
		myList.setSelectionModel(myTable.getSelectionModel());

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, myTable);
		this.add(BorderLayout.WEST, myList);

		panelHeader = new JPanel(new BorderLayout());
		JPanel panelVide = new JPanel();
		panelVide.setPreferredSize(new Dimension(10, (int) myTable.getTableHeader().getPreferredSize().getHeight()));
		panelHeader.add(BorderLayout.WEST, panelVide);
		panelHeader.add(BorderLayout.CENTER, myTable.getTableHeader());
	}

	public JPanel getPanelHeader() {
		return panelHeader;
	}

	public void fireListRowsInserted() {
		refTableListModelAdapter.fireListRowsInserted();
	}

	public void addAllKeyListener(KeyListener l) {
		myTable.addKeyListener(l);
		this.myList.addKeyListener(l);
	}

	public JTree getTree() {
		return myTable.getTree();
	}

	public JTreeTable getTreeTable() {
		return this.myTable;
	}

	class TableListModelAdapter extends AbstractListModel {

		private static final long serialVersionUID = -7208312048831796834L;

		private AbstractTableModel modele;

		public TableListModelAdapter(AbstractTableModel ref) {
			this.modele = ref;
			ref.addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					TableListModelAdapter.this.fireContentsChanged();
				}

			});
		}

		public Object getElementAt(int index) {
			return "";
		}

		public int getSize() {
			return modele.getRowCount();
		}

		public void fireContentsChanged() {
			this.fireContentsChanged(this, 0, this.getSize() - 1);
		}

		public void fireListRowsInserted() {
			this.fireContentsChanged(this, 0, this.getSize() - 1);
		}
	}

	class ListTableRenderer implements ListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPanel myComponent = new JPanel();
			int rowHeight = myTable.getRowHeight(index);
			myComponent.setPreferredSize(new Dimension(10, rowHeight));
			myComponent.setOpaque(true);
			myComponent.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

			if (isSelected)
				myComponent.setBackground(Color.gray);
			else
				myComponent.setBackground(new Color(238, 238, 238));
			return myComponent;
		}
	}
}

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel.MyTableListModel;

/**
 * @author Mickael BARON
 */
public class JListTable extends JPanel {

	private static final long serialVersionUID = -8795218850894222284L;

	private JTable myTable;

	private JList myList;

	private DefaultListSelectionModel myListSelectionModel;

	private JPanel panelHeader;

	public JListTable(TableModel myModel, MyTableListModel listModel) {
		myTable = new KMADEJTable(myModel);

		// Cr�ation de la partie JList et de son mod�le.
		myList = new JList(listModel);
		myList.setCellRenderer(new ListTableRenderer());

		// La partie commune entre le JTable et la JList.
		myListSelectionModel = new DefaultListSelectionModel();
		myListSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		myTable.setSelectionModel(myListSelectionModel);
		myList.setSelectionModel(myListSelectionModel);

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, myTable);
		this.add(BorderLayout.WEST, myList);

		panelHeader = new JPanel(new BorderLayout());
		JPanel panelVide = new JPanel();
		panelVide.setPreferredSize(new Dimension(10, (int) myTable.getTableHeader().getPreferredSize().getHeight()));
		panelHeader.add(BorderLayout.WEST, panelVide);
		panelHeader.add(BorderLayout.CENTER, myTable.getTableHeader());
		getMyTable().setRowHeight(KMADEConstant.ROW_HEIGHT);

		// permet de terminer l'edition d'une cellule lorsqu'on perd le focus :
		// utile pour les combobox!
		myTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	}

	public JPanel getPanelHeader() {
		return panelHeader;
	}

	public DefaultListSelectionModel getListSelectionModel() {
		return myListSelectionModel;
	}

	public JTable getMyTable() {
		return myTable;
	}

	public JList getJList() {
		return this.myList;
	}

	public void setCellEditor(int column, String type, String[] value) {
		if (type.equals("String")) {
			myTable.getColumnModel().getColumn(column)
					.setCellEditor(new MyCustomStringTableCellEditor(column, myTable));
			return;
		}

		if (type.equals("List")) {
			myTable.getColumnModel().getColumn(column).setCellEditor(new MyCustomListTableCellEditor(value));
			return;
		}
		if (type.equals("SpecialString")) {
			myTable.getColumnModel().getColumn(column)
					.setCellEditor(new MyCustomSpecialStringTableCellEditor(column, myTable));
		}
	}

	public void addAllKeyListener(KeyListener l) {
		this.getMyTable().addKeyListener(l);
		this.getJList().addKeyListener(l);
	}

	class ListTableRenderer implements ListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPanel myComponent = new JPanel();
			int rowHeight = getMyTable().getRowHeight(index);

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

	class MyCustomStringTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 3769456455863168900L;

		private JTextField myTextField;

		private JTable ref;

		private int newColumn = 0;

		public MyCustomStringTableCellEditor(int newColumn, JTable t) {
			ref = t;
			this.newColumn = newColumn;
			myTextField = new JTextField();
			myTextField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyCustomStringTableCellEditor.this.stopCellEditing();
				}
			});
			this.myTextField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
				}

				public void focusLost(FocusEvent e) {
					stopCellEditing();
				}
			});
		}

		public Object getCellEditorValue() {
			return myTextField.getText();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (row + 1 == ref.getModel().getRowCount()) {
				if (column == newColumn) {
					myTextField.setText("");
				}
			} else {
				myTextField.setText((String) value);
			}
			return myTextField;
		}

		public boolean isCellEditable(EventObject anEvent) {
			if (anEvent instanceof MouseEvent) {
				return ((MouseEvent) anEvent).getClickCount() >= 2;
			}
			return true;
		}
	}

	class MyCustomListTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 3769456455863168900L;

		protected JComboBox myComboBox;

		public MyCustomListTableCellEditor(String[] value) {
			myComboBox = new JComboBox(value);
			myComboBox.addFocusListener(new FocusListener() {

				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub

				}

				public void focusGained(FocusEvent e) {
					myComboBox.hidePopup();
				}
			});
			myComboBox.addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent ek) {
					// TODO Auto-generated method stub

				}

				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				public void keyPressed(KeyEvent ek) {
					if (ek.getKeyCode() == KeyEvent.VK_ENTER) {
						MyCustomListTableCellEditor.this.stopCellEditing();
					} else if (ek.getKeyCode() == KeyEvent.VK_ESCAPE) {
						MyCustomListTableCellEditor.this.cancelCellEditing();
					}
				}
			});
			myComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyCustomListTableCellEditor.this.stopCellEditing();
				}
			});
		}

		public Object getCellEditorValue() {
			return myComboBox.getSelectedItem();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			myComboBox.setSelectedItem(value);
			return myComboBox;
		}
	}

	class MyCustomSpecialStringTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 3769456455863168900L;

		private JTextField myTextField;

		private JTable ref;

		private int newColumn = 0;

		public MyCustomSpecialStringTableCellEditor(int newColumn, JTable t) {
			ref = t;
			this.newColumn = newColumn;
			myTextField = new JTextField();
			myTextField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stopCellEditing();
				}
			});
			this.myTextField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
				}

				public void focusLost(FocusEvent e) {
					// on regarde si le focus est perdu a cause d'une boite de
					// dialogue, si c'est le cas la cellule est toujours en
					// cours d'edition
					// try/catch car il peut y avoir un pointeur null ( �
					// l'initialisation )
					try {
						if (e.getOppositeComponent().getClass().getSimpleName().equals("MultiplexingTextField")) {

						} else {
							stopCellEditing();
						}
					} catch (Exception exc) {
						// ne rien faire
					}
				}
			});
		}

		public Object getCellEditorValue() {
			return myTextField.getText();
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (row + 1 == ref.getModel().getRowCount()) {
				if (column == newColumn) {
					myTextField.setText("");
				}
			} else {
				myTextField.setText((String) value);
			}
			return myTextField;
		}

		public boolean isCellEditable(EventObject anEvent) {
			if (anEvent instanceof MouseEvent) {
				return ((MouseEvent) anEvent).getClickCount() >= 2;
			}
			return true;
		}
	}
}

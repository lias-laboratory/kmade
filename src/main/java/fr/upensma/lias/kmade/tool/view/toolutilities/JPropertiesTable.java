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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class JPropertiesTable extends JScrollPane implements MouseInputListener {

    private static final long serialVersionUID = 7430261370350224060L;

    public static final int SHORT_TEXT = 0;

    public static final int LONG_TEXT = 1;

    public static final int COMBO_TEXT = 2;

    public static final int CHECK_TEXT = 3;

    public static final int COLOR_TEXT = 4;

    public static final int NONE_TEXT = 5;

    public MyCustomTableCellEditor myEditor = new MyCustomTableCellEditor();

    private MyJTable refTable;

    public void addFocusListener(FocusListener ref) {
	refTable.addFocusListener(ref);
    }

    public ListSelectionModel getSelectionModel() {
	return refTable.getSelectionModel();
    }

    public JTable getTable() {
	return refTable;
    }

    class MyJTable extends JTable {

	private static final long serialVersionUID = -6875690328144350698L;

	private boolean editable;

	public MyJTable(TableModel dm, boolean editable) {
	    super(dm);
	    this.editable = editable;
	}

	public boolean isCellEditable(int row, int column) {
	    if (editable) {
		return super.isCellEditable(row, column);
	    } else {
		return false;
	    }
	}
    }

    public JPropertiesTable(DefaultPropertiesTableModel p) {
	this(p, false);
    }

    public JPropertiesTable(DefaultPropertiesTableModel p, boolean editable) {
	refTable = new MyJTable(p, editable);
	refTable.addMouseListener(this);
	refTable.addMouseMotionListener(this);

	refTable.getTableHeader().setDefaultRenderer(
		new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 1348784031812214529L;

		    public Component getTableCellRendererComponent(
			    JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int row, int column) {
			JLabel myLabel = new JLabel();
			myLabel.setOpaque(true);
			myLabel.setPreferredSize(new Dimension(0, 0));
			return myLabel;
		    }
		});

	refTable.getTableHeader().setReorderingAllowed(false);

	refTable.getColumnModel().getColumn(0)
		.setCellRenderer(new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 4038866614648102821L;

		    public Component getTableCellRendererComponent(
			    JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int row, int column) {
			DefaultPropertiesTableModel refModel = (DefaultPropertiesTableModel) table
				.getModel();
			int myValue = (Integer) (refModel.getValueAt(row,
				DefaultPropertiesTableModel.COLUMN_TYPE_INDEX));
			if (myValue == NONE_TEXT) {
			    JLabel myLabel = new JLabel();
			    myLabel.setOpaque(true);
			    if (isSelected) {
				myLabel.setBackground(KMADEConstant.ACTIVE_SELECTION
					.darker());
			    } else {
				myLabel.setBackground(JPropertiesTable.this
					.getBackground().darker());
			    }
			    myLabel.setPreferredSize(new Dimension(0, 20));
			    myLabel.setText((String) (refModel
				    .getValueAt(
					    row,
					    DefaultPropertiesTableModel.COLUMN_NAME_INDEX)));
			    myLabel.setHorizontalAlignment(JLabel.LEFT);
			    return myLabel;
			} else {
			    JLabel myLabel = new JLabel((String) value);
			    myLabel.setOpaque(true);
			    if (isSelected) {
				myLabel.setBackground(KMADEConstant.ACTIVE_SELECTION);
			    } else {
				myLabel.setBackground(KMADEConstant.ACTIVE_PANE);
			    }
			    return myLabel;
			}
		    }
		});

	refTable.getColumnModel().getColumn(1)
		.setCellRenderer(new DefaultTableCellRenderer() {
		    private static final long serialVersionUID = 1348784031812214529L;

		    public Component getTableCellRendererComponent(
			    JTable table, Object value, boolean isSelected,
			    boolean hasFocus, int row, int column) {
			DefaultPropertiesTableModel refModel = (DefaultPropertiesTableModel) table
				.getModel();
			int myValue = (Integer) (refModel.getValueAt(row,
				DefaultPropertiesTableModel.COLUMN_TYPE_INDEX));
			JComponent myComponent = null;
			switch (myValue) {
			case SHORT_TEXT: {
			    myComponent = new JTextField((String) value);
			    break;
			}

			case LONG_TEXT: {
			    myComponent = new JTextField((String) value);
			    break;
			}

			case COMBO_TEXT: {
			    myComponent = new JTextField((String) value);
			    break;
			}

			case COLOR_TEXT: {
			    myComponent = new JLabel("");
			    if (value instanceof Color) {
				Color myColor = (Color) value;
				((JLabel) myComponent).setText("R:"
					+ myColor.getRed() + ", G:"
					+ myColor.getGreen() + ", B:"
					+ myColor.getBlue());
				myComponent.setForeground(Color.WHITE);
				myComponent.setBackground(myColor);
				myComponent.setOpaque(true);
			    }
			    return myComponent;
			}

			case CHECK_TEXT: {
			    String[] checkText = (String[]) (refModel
				    .getValueAt(
					    row,
					    DefaultPropertiesTableModel.COLUMN_COMBOVALUE_INDEX));
			    myComponent = new JCheckBox(checkText[0]);
			    break;
			}

			case NONE_TEXT: {
			    JLabel myLabel = new JLabel();
			    myLabel.setOpaque(true);
			    if (isSelected) {
				myLabel.setBackground(KMADEConstant.ACTIVE_SELECTION
					.darker());
			    } else {
				myLabel.setBackground(JPropertiesTable.this
					.getBackground().darker());
			    }
			    myLabel.setPreferredSize(new Dimension(0, 20));
			    return myLabel;
			}
			default:
			    return null;
			}
			myComponent.setOpaque(true);
			if (isSelected) {
			    myComponent
				    .setBackground(KMADEConstant.ACTIVE_SELECTION);
			} else {
			    myComponent
				    .setBackground(KMADEConstant.ACTIVE_PANE);
			}

			boolean editable = (Boolean) (refModel
				.getValueAt(
					row,
					DefaultPropertiesTableModel.COLUMN_ISEDITABLE_INDEX));
			myComponent.setEnabled(editable);
			myComponent.setBorder(null);
			return myComponent;
		    }
		});

	refTable.getColumnModel().getColumn(1)
		.setCellEditor(new MyCustomTableCellEditor());
	refTable.getColumnModel().getColumn(0).setResizable(true);
	refTable.getColumnModel().getColumn(0).setPreferredWidth(100);
	refTable.setRowSelectionAllowed(true);
	refTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JPanel myPanel = new JPanel(new BorderLayout());
	myPanel.add(refTable);
	myPanel.setBorder(null);
	this.setViewportView(myPanel);
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	refTable.setRowHeight(20);
	refTable.setGridColor(refTable.getParent().getBackground());
    }

    public void addSelectionListener(ListSelectionListener myListener) {
	ListSelectionModel rowSM = refTable.getSelectionModel();
	rowSM.addListSelectionListener(myListener);
    }

    public void clearSelection() {
	ListSelectionModel rowSM = refTable.getSelectionModel();
	rowSM.clearSelection();
    }

    public boolean isEmptySelection() {
	return refTable.getSelectionModel().isSelectionEmpty();
    }

    public void setSelectedElement(int indice) {
	refTable.getSelectionModel().setSelectionInterval(indice, indice);
    }

    public DefaultPropertiesTableModel getPropertiesModel() {
	return (DefaultPropertiesTableModel) refTable.getModel();
    }

    class MyCustomTableCellEditor extends AbstractCellEditor implements
	    TableCellEditor {
	private static final long serialVersionUID = -4362484768177252311L;

	private int state = 0; // 0 : textfield ; 1 : JDialog

	private JTextField myTextfield = new JTextField();

	private JComboBox myComboBox = new JComboBox();

	public boolean isCellEditable(EventObject anEvent) {
	    if (anEvent instanceof MouseEvent) {
		return ((MouseEvent) anEvent).getClickCount() >= 2;
	    }
	    return true;
	}

	public Component getTableCellEditorComponent(JTable table,
		Object value, boolean isSelected, int row, int column) {
	    DefaultPropertiesTableModel refModel = (DefaultPropertiesTableModel) table
		    .getModel();
	    int myValue = (Integer) (refModel.getValueAt(row,
		    DefaultPropertiesTableModel.COLUMN_TYPE_INDEX));
	    switch (myValue) {
	    case SHORT_TEXT: {
		state = 0;
		myTextfield.setText((String) value);
		myTextfield.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			MyCustomTableCellEditor.this.stopCellEditing();
		    }
		});
		return myTextfield;
	    }
	    case LONG_TEXT: {
		state = 1;
		JPropertiesEditorDialog refDialog = (JPropertiesEditorDialog) (refModel
			.getValueAt(row,
				DefaultPropertiesTableModel.COLUMN_EDITOR_INDEX));
		refDialog.showPropertiesEditor(refModel, row);
		return null;
	    }

	    case COLOR_TEXT: {
		state = 1;
		JPropertiesEditorDialog refDialog = (JPropertiesEditorDialog) (refModel
			.getValueAt(row,
				DefaultPropertiesTableModel.COLUMN_EDITOR_INDEX));
		refDialog.showPropertiesEditor(refModel, row);
		return null;
	    }

	    case COMBO_TEXT: {
		state = 2;
		String[] myComboData = (String[]) (refModel.getValueAt(row, 4));
		if (myComboData != null) {
		    myComboBox = new JComboBox(myComboData);
		    myComboBox.setSelectedItem((String) value);
		    myComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    MyCustomTableCellEditor.this.stopCellEditing();
			}
		    });

		    this.myComboBox.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
			    MyCustomTableCellEditor.this.stopCellEditing();
			}
		    });

		    return myComboBox;
		} else {
		    return null;
		}
	    }

	    case CHECK_TEXT: {
		state = 3;
		return new JCheckBox();
	    }

	    default:
		return null;
	    }
	}

	public Object getCellEditorValue() {
	    switch (state) {
	    case 0:
		return myTextfield.getText();
	    case 1:
		return "";
	    case 2:
		return myComboBox.getSelectedItem();
	    default:
		return "";
	    }
	}

    }

    public Cursor resizeCursor = Cursor
	    .getPredefinedCursor(Cursor.E_RESIZE_CURSOR);

    private int mouseXOffset;

    private Cursor otherCursor = resizeCursor;

    private boolean canResize(TableColumn column) {
	return column != null && refTable.getTableHeader().getResizingAllowed()
		&& column.getResizable();
    }

    private TableColumn getResizingColumn(Point p) {
	return getResizingColumn(p, refTable.columnAtPoint(p));
    }

    private TableColumn getResizingColumn(Point p, int column) {
	if (column == -1) {
	    return null;
	}
	int row = refTable.rowAtPoint(p);
	if (row == -1)
	    return null;
	Rectangle r = refTable.getCellRect(row, column, true);
	r.grow(-3, 0);
	if (r.contains(p))
	    return null;

	int midPoint = r.x + r.width / 2;
	int columnIndex;
	if (refTable.getTableHeader().getComponentOrientation().isLeftToRight())
	    columnIndex = (p.x < midPoint) ? column - 1 : column;
	else
	    columnIndex = (p.x < midPoint) ? column : column - 1;

	if (columnIndex == -1)
	    return null;
	return refTable.getTableHeader().getColumnModel()
		.getColumn(columnIndex);
    }

    public void mousePressed(MouseEvent e) {
	refTable.getTableHeader().setDraggedColumn(null);
	refTable.getTableHeader().setResizingColumn(null);
	refTable.getTableHeader().setDraggedDistance(0);

	Point p = e.getPoint();

	int index = refTable.columnAtPoint(p);
	if (index == -1)
	    return;

	TableColumn resizingColumn = getResizingColumn(p, index);
	if (!canResize(resizingColumn))
	    return;

	refTable.getTableHeader().setResizingColumn(resizingColumn);
	if (refTable.getTableHeader().getComponentOrientation().isLeftToRight())
	    mouseXOffset = p.x - resizingColumn.getWidth();
	else
	    mouseXOffset = p.x + resizingColumn.getWidth();
    }

    private void swapCursor() {
	Cursor tmp = refTable.getCursor();
	refTable.setCursor(otherCursor);
	otherCursor = tmp;
    }

    public void mouseMoved(MouseEvent e) {
	if (canResize(getResizingColumn(e.getPoint())) != (refTable.getCursor() == resizeCursor)) {
	    swapCursor();
	}
    }

    public void mouseDragged(MouseEvent e) {
	int mouseX = e.getX();

	TableColumn resizingColumn = refTable.getTableHeader()
		.getResizingColumn();

	boolean headerLeftToRight = refTable.getTableHeader()
		.getComponentOrientation().isLeftToRight();

	if (resizingColumn != null) {
	    int oldWidth = resizingColumn.getWidth();
	    int newWidth;
	    if (headerLeftToRight) {
		newWidth = mouseX - mouseXOffset;
	    } else {
		newWidth = mouseXOffset - mouseX;
	    }
	    resizingColumn.setWidth(newWidth);

	    Container container;
	    if ((refTable.getTableHeader().getParent() == null)
		    || ((container = refTable.getTableHeader().getParent()
			    .getParent()) == null)
		    || !(container instanceof JScrollPane)) {
		return;
	    }

	    if (!container.getComponentOrientation().isLeftToRight()
		    && !headerLeftToRight) {
		if (refTable != null) {
		    JViewport viewport = ((JScrollPane) container)
			    .getViewport();
		    int viewportWidth = viewport.getWidth();
		    int diff = newWidth - oldWidth;
		    int newHeaderWidth = refTable.getWidth() + diff;

		    Dimension tableSize = refTable.getSize();
		    tableSize.width += diff;
		    refTable.setSize(tableSize);

		    if ((newHeaderWidth >= viewportWidth)
			    && (refTable.getAutoResizeMode() == JTable.AUTO_RESIZE_OFF)) {
			Point p = viewport.getViewPosition();
			p.x = Math.max(
				0,
				Math.min(newHeaderWidth - viewportWidth, p.x
					+ diff));
			viewport.setViewPosition(p);
			mouseXOffset += diff;
		    }
		}
	    }
	}
    }

    public void mouseReleased(MouseEvent e) {
	refTable.getTableHeader().setResizingColumn(null);
	refTable.getTableHeader().setDraggedColumn(null);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview;

/**
 * Class allowing the user to create abstract objects with an interface
 * looking just as the one for the task creation.
 * 
 * @author Joachim TROUVERIE
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphModel;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ExpressDB;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell.AbstractObjectCell;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell.AbstractObjectVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.ObjectDialogViewAdaptator;

public class AbstractObjectPanel extends JPanel implements MouseListener {

    private static final long serialVersionUID = 3137105217031351179L;

    private JTable descriptionTable;

    private JPanel east, west;

    private JScrollPane descriptionTablePanel;

    private JSplitPane splitPane;

    private JGraph abstractObjectGraph = new JGraph(new DefaultGraphModel());

    private static JLabel inactive = new JLabel(
	    KMADEConstant.NO_SELECTED_OBJECT, JLabel.CENTER);

    private AbstractObjectCell selected;

    /**
     * Constructor for the abstract object panel
     * 
     * @param data
     *            base to create and place the objects already created
     */
    public AbstractObjectPanel(ExpressDB bdd) {
	super();

	// List of objects already created
	List<ObjetAbstrait> abstracts = new ArrayList<ObjetAbstrait>();
	Set<Oid> set = bdd.keySet();
	Iterator<Oid> i;
	for (i = set.iterator(); i.hasNext();) {
	    Oid oid = (Oid) i.next();
	    if (bdd.prendre(oid) instanceof ObjetAbstrait)
		abstracts.add((ObjetAbstrait) bdd.prendre(oid));
	}
	// Panels creation
	this.setLayout(new BorderLayout());

	east = new JPanel(new BorderLayout());
	west = new JPanel(new BorderLayout());

	// Components
	// Table
	this.descriptionTable = new JTable(new DescriptionTableModel());
	this.descriptionTable.setDefaultRenderer(Object.class,
		new DescriptionTableRenderer());
	this.descriptionTable.setDefaultEditor(String.class,
		(TableCellEditor) new DescriptionTableEditor());

	// Graphs
	this.abstractObjectGraph.getGraphLayoutCache().setFactory(
		new DefaultCellViewFactory() {

		    private static final long serialVersionUID = -1160471165675547112L;

		    public CellView createView(GraphModel gM, Object cell) {
			if (cell instanceof AbstractObjectCell) {
			    return new AbstractObjectVertexView(cell,
				    abstractObjectGraph);
			} else {
			    return super.createView(gM, cell);
			}
		    }
		});

	this.abstractObjectGraph.addMouseListener(this);

	// Table
	descriptionTablePanel = new JScrollPane(descriptionTable);

	// Put description table and buttonpanel inactive
	descriptionTablePanel.setVisible(false);

	// Packing
	west.add(new JScrollPane(abstractObjectGraph), BorderLayout.CENTER);

	east.add(descriptionTablePanel, BorderLayout.NORTH);
	east.add(inactive, BorderLayout.CENTER);
	TitledBorder border = BorderFactory
		.createTitledBorder(KMADEConstant.PROPERTIES_MESSAGE);
	border.setTitleJustification(TitledBorder.CENTER);
	east.setBorder(border);

	splitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(
		JSplitPane.HORIZONTAL_SPLIT, west, east);
	splitPane.setOneTouchExpandable(true);
	splitPane.setContinuousLayout(true);
	splitPane.setResizeWeight(0.8);

	this.add(splitPane, BorderLayout.CENTER);

	// Draw the objects already created
	drawAbstractObjectCells(abstracts);
    }

    /**
     * Method to draw the objects already created
     * 
     * @param list
     *            of abstracts objects
     */
    public void drawAbstractObjectCells(List<ObjetAbstrait> abstracts) {
	// Fill the panels
	// Bounds to place the points
	int abstractX = 0;
	int abstractY = 0;
	AbstractObjectCell cell;
	if (!abstracts.isEmpty()) {
	    for (int i = 0; i < abstracts.size(); i++) {
		if (abstracts.get(i).getPoint() == null) {
		    cell = new AbstractObjectCell(abstracts.get(i), abstractX,
			    abstractY);
		    abstractX += cell.getWidth() + KMADEConstant.TASK_DISTANCE;
		} else
		    cell = new AbstractObjectCell(abstracts.get(i), abstracts
			    .get(i).getPoint());

		this.abstractObjectGraph.getGraphLayoutCache().insert(cell);
	    }
	}
    }

    /**
     * Method to set the selected cell in the graph
     * 
     * @param new selected cell
     */
    public void setSelected(AbstractObjectCell cell) {
	this.selected = cell;
    }

    public void hideTable() {
	inactive.setVisible(true);
	descriptionTablePanel.setVisible(false);
	descriptionTable.setVisible(false);
	east.revalidate();
	east.repaint();
	ObjectDialogViewAdaptator.setAddAttributeEnabled(false);
    }

    public void showTable() {
	if (inactive.isVisible()) {
	    inactive.setVisible(false);
	    descriptionTablePanel.setVisible(true);
	    descriptionTable.setVisible(true);
	    east.revalidate();
	    east.repaint();
	}
    }

    /**
     * @return the selected cell in the graph
     */
    public AbstractObjectCell getSelected() {
	return selected;
    }

    /**
     * @return the graph
     */
    public JGraph getGraph() {
	return this.abstractObjectGraph;
    }

    /**
     * Method to get the table where the details of the selected cell are
     * displayed
     * 
     * @return the table
     */
    public JTable getTable() {
	return this.descriptionTable;
    }

    /**
     * To clear the graph
     */
    public void clearGraph() {
	CellView[] cells = abstractObjectGraph.getGraphLayoutCache()
		.getAllViews();
	for (CellView view : cells) {
	    Object[] cell = { view.getCell() };
	    abstractObjectGraph.getGraphLayoutCache().remove(cell);
	}
    }

    /**
     * Mouse listeners for the graph
     */
    public void mouseClicked(MouseEvent e) {
	if (e.getSource().equals(abstractObjectGraph)) {
	    // Select the new cell
	    selected = (AbstractObjectCell) abstractObjectGraph
		    .getSelectionCell();
	    if (selected != null) {
		((ObjetAbstrait) selected.getObject()).getPoint()
			.setX(e.getX());
		((ObjetAbstrait) selected.getObject()).getPoint()
			.setY(e.getY());
	    }

	    // Deselect the previous selected cell
	    abstractObjectGraph.clearSelection();
	    abstractObjectGraph.setSelectionCell(selected);
	} else
	    selected = null;
	// Clear the table and write the details of the selected cell in the
	// table
	((DescriptionTableModel) descriptionTable.getModel()).clearTable();
	if (selected != null) {
	    ((DescriptionTableModel) descriptionTable.getModel())
		    .writeDetails((ObjetAbstrait) selected.getObject());
	    ObjectDialogViewAdaptator.setAddAttributeEnabled(true);
	    showTable();

	} else {
	    ObjectDialogViewAdaptator.setAddAttributeEnabled(false);
	    hideTable();
	}

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    /**
     * Editor for the JTable describing the abstract object selected
     * 
     * @author Joachim TROUVERIE
     */
    class DescriptionTableEditor extends AbstractCellEditor implements
	    TableCellEditor {

	private static final long serialVersionUID = -7316816734915387182L;

	// For the type value
	private JComboBox box = null;

	private JTextField field = null;

	// "Nombre","Booleen" and "Texte"
	private String[] values = TypeStructure
		.getNameLocaleTypeStructureWithoutInterval();

	/**
	 * @return a combo box for the boolean, and a text filed for numbers and
	 *         text
	 */
	public Component getTableCellEditorComponent(JTable table,
		Object value, boolean isSelected, int row, int column) {

	    field = null;
	    box = null;

	    if (table.isCellEditable(row, column)) {

		if (table.getModel().getValueAt(row, 0)
			.equals(KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_TABLE)) {
		    box = new JComboBox(values);
		    this.box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    DescriptionTableEditor.this.stopCellEditing();
			}
		    });
		    return box;
		}

		else {
		    field = new JTextField();
		    this.field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    DescriptionTableEditor.this.stopCellEditing();
			}
		    });
		    return field;
		}
	    } else
		return null;
	}

	public Object getCellEditorValue() {
	    if (field == null)
		return box.getSelectedItem();
	    else
		return field.getText();
	}
    };

    /**
     * Renderer for the JTable describing the abstract object selected
     * 
     * @author Joachim TROUVERIE
     */
    class DescriptionTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 397734468312942350L;

	@Override
	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus, int row,
		int column) {

	    Component cell = super.getTableCellRendererComponent(table, value,
		    isSelected, hasFocus, row, column);

	    if (table.getValueAt(row, 0).equals(KMADEConstant.GENERALITY)
		    || table.getValueAt(row, 0).equals(
			    KMADEConstant.ATTRIBUTES_NAME)) {
		cell.setBackground(Color.LIGHT_GRAY);
	    }

	    else if (table.getValueAt(row, 0).equals("")
		    && table.getValueAt(row, 1).equals(""))
		cell.setBackground(Color.GRAY);

	    else if (row > 3 && row % 4 != 3 && isSelected) {
		cell.setBackground(KMADEConstant.ACTIVE_SELECTION);
		cell.setForeground(Color.BLACK);
		if (row % 4 == 0)
		    table.addRowSelectionInterval(row, row + 2);
		else if (row % 4 == 1)
		    table.addRowSelectionInterval(row - 1, row + 1);
		else
		    table.addRowSelectionInterval(row - 2, row);
	    }

	    else if (isSelected) {
		cell.setBackground(KMADEConstant.ACTIVE_SELECTION);
		cell.setForeground(Color.BLACK);
	    }

	    else
		cell.setBackground(Color.WHITE);

	    return cell;
	}
    };

    /**
     * Model for the table describing the abstract object selected
     * 
     * @author Joachim TROUVERIE
     */
    class DescriptionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<String[]> data = new ArrayList<String[]>();

	private ObjetAbstrait objet = null;

	private static final int nameRow = 1;

	private static final int descriptionRow = 2;

	public int getRowCount() {
	    return data.size();
	}

	public int getColumnCount() {
	    return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
	    return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    if (data.get(rowIndex)[0].equals(KMADEConstant.GENERALITY)
		    || data.get(rowIndex)[0]
			    .equals(KMADEConstant.ATTRIBUTES_NAME)) {
		return false;
	    } else
		return (columnIndex == 1);
	}

	public String getValueAt(int rowIndex, int columnIndex) {
	    return data.get(rowIndex)[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    this.data.get(rowIndex)[columnIndex] = (String) aValue;
	    if (rowIndex == nameRow)
		AbstractObjectAdaptator.setAbstractObjectName(objet.getOid()
			.get(), (String) aValue);
	    else if (rowIndex == descriptionRow)
		AbstractObjectAdaptator.setAbstractObjectDescription(objet
			.getOid().get(), (String) aValue);
	    else if (rowIndex >= 3) {
		if (rowIndex % 4 == 0)
		    this.objet.getInverseAttributsAbs().get(rowIndex / 4 - 1)
			    .setName((String) aValue);
		else if (rowIndex % 4 == 1) {
		    String type = TypeStructure
			    .getLocaleTypeStructureIntoEnumere((String) aValue);
		    this.objet.getInverseAttributsAbs().get(rowIndex / 4 - 1)
			    .setTypeStructure(TypeStructure.getValue(type));
		}

		else if (rowIndex % 4 == 2)
		    this.objet.getInverseAttributsAbs().get(rowIndex / 4 - 1)
			    .setDescription((String) aValue);
	    }

	    ObjectDialogViewAdaptator.updateTaskPropPanel();
	    this.fireTableDataChanged();

	    abstractObjectGraph.getGraphLayoutCache().editCell(selected,
		    selected.getAttributes());
	}

	public void addNewRow(String s1, String s2) {
	    String[] s = { s1, s2 };
	    this.data.add(s);
	    fireTableDataChanged();
	}

	public void setName(String name) {
	    this.setValueAt(name, nameRow, 1);
	}

	public void setDescription(String description) {
	    this.setValueAt(description, descriptionRow, 1);
	}

	public void addObjectAttribute(String name, String type,
		String description) {
	    this.addNewRow(KMADEConstant.ABSTRACT_ATTRIBUT_NAME_TABLE, name);
	    this.addNewRow(KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_TABLE, type);
	    this.addNewRow(KMADEConstant.ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE,
		    description);
	}

	public void removeObjectAttribute(int[] rows) {
	    for (int i = 0; i < rows.length; i++) {
		data.remove(i);
	    }
	    this.fireTableDataChanged();
	}

	public void clearTable() {
	    data.clear();
	    fireTableDataChanged();
	    this.addNewRow(KMADEConstant.GENERALITY, "");
	    this.addNewRow(KMADEConstant.ABSTRACT_OBJECT_NAME_TABLE, "");
	    this.addNewRow(KMADEConstant.ABSTRACT_OBJECT_OBSERVATION_TABLE, "");
	    this.addNewRow(KMADEConstant.ATTRIBUTES_NAME, "");
	}

	public void writeDetails(ObjetAbstrait objet) {
	    this.objet = objet;
	    this.setName(objet.getName());
	    this.setDescription(objet.getDescription());

	    if (!objet.getInverseAttributsAbs().isEmpty()) {
		for (int i = 0; i < objet.getInverseAttributsAbs().size(); i++) {
		    AttributAbstrait att = objet.getInverseAttributsAbs()
			    .get(i);
		    this.addObjectAttribute(att.getName(), TypeStructure
			    .getEnumereIntoLocaleTypeStructure(att
				    .getTypeStructure().getValue()), att
			    .getDescription());
		    this.addNewRow("", "");
		}
	    }
	}

    };

}

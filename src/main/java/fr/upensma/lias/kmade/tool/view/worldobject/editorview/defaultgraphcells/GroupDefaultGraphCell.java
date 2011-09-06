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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell.ConcreteObjectCell;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * Abstract class for the cell representing all kind of groups.
 * 
 * @author Joachim TROUVERIE
 */
public abstract class GroupDefaultGraphCell extends DefaultGraphCell {

    private static final long serialVersionUID = 1388699310236979117L;

    protected Groupe group;

    protected List<ConcreteObjectCell> childCells = new ArrayList<ConcreteObjectCell>();

    protected int selectedIndex = -1;

    protected GroupEditDialog edit;

    protected GroupEditDialogCreation editCreation;

    protected GroupDefaultVertexView invView;

    /**
     * First constructor of the cell (the point is not created yet)
     * @param group represented by the cell
     * @param point x in the cell
     * @param point y in the cell
     */
    public GroupDefaultGraphCell(Groupe o, int x, int y) {
	super(o.getName());

	this.group = o;

	Oid oidPoint = InterfaceExpressJava.createEntity("tache", "Point");
	Point p = (Point) InterfaceExpressJava.prendre(oidPoint);
	p.setX(x);
	p.setY(y);
	o.setPoint(p);

	Map<?, ?> myHashTable = this.getAttributes();
	GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(x, y, 50,
		50));
	GraphConstants.setEditable(myHashTable, false);
	GraphConstants.setAutoSize(myHashTable, true);
	GraphConstants.setOpaque(myHashTable, false);
	this.setAttributes(new AttributeMap(myHashTable));
    }

    /**
     * Second constructor for the cell (the point is already created)
     * @param group represented by the cell
     * @param point in the graph
     */
    public GroupDefaultGraphCell(Groupe o, Point p) {
	super(o.getName());

	this.group = o;

	Map<?, ?> myHashTable = this.getAttributes();
	GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(p.getX(),
		p.getY(), 100, 100));
	GraphConstants.setEditable(myHashTable, false);
	GraphConstants.setAutoSize(myHashTable, true);
	GraphConstants.setOpaque(myHashTable, false);
	this.setAttributes(new AttributeMap(myHashTable));
    }

    /**
     * @return the group represented by the cell
     */
    public Groupe getObject() {
	return group;
    }

    /**
     * @return the group's name
     */
    public String getName() {
	return group.getName();
    }

    /**
     * Method to add a new concrete object cell in the group
     * and to add the concrete object in the group
     * @param new concrete object cell
     */
    public void addCellToGroup(ConcreteObjectCell cell) {
	this.childCells.add(cell);
	((ObjetConcret) cell.getObject()).setAppartientGroupe(this.group);
    }

    /**
     * @return the concrete object cell contained in the group
     */
    public List<ConcreteObjectCell> getCellsInGroup() {
	return this.childCells;
    }

    /**
     * @return the renderer of this cell
     */
    public GroupDefaultVertexView getInvView() {
	return this.invView;
    }

    /**
     * Method to set a new renderer to the cell
     * @param new renderer
     */
    public void setInvView(GroupDefaultVertexView newView) {
	this.invView = newView;
    }

    /**
     * @return the concrete object cell selected in the group
     */
    public ConcreteObjectCell getSelectedCell() {
	if (selectedIndex == -1)
	    return null;
	else
	    return this.childCells.get(this.selectedIndex);
    }

    /**
     * Method to set the selected cell in the group
     * @param index of the cell
     */
    public void setSelectedIndex(int i) {
	this.selectedIndex = i;
    }

    /**
     * Method to display a window to edit the group when it is created
     */
    public void editCreation() {
	editCreation = new GroupEditDialogCreation(this, GraphicEditorAdaptator
		.getMainFrame().getObjectDialogView());
    }

    /**
     * Method to display a window to edit the group after its creation
     */
    public void edit() {
	edit = new GroupEditDialog(this, GraphicEditorAdaptator.getMainFrame()
		.getObjectDialogView());

    }

    /**
     * Window to edit the group when it is created
     * @author Joachim TROUVERIE
     */
    class GroupEditDialogCreation extends JDialog {

	private static final long serialVersionUID = 5464831586210482158L;

	private Groupe object;

	private JComboBox box;

	private JTextArea description;

	private JTextField name;

	private JButton ok;

	public GroupEditDialogCreation(GroupDefaultGraphCell cell, JDialog owner) {
	    super(owner, KMADEConstant.EDIT_GROUP_ACTION_MESSAGE);

	    // Initialization
	    this.object = cell.getObject();

	    this.box = new JComboBox();

	    final Object[] objets = InterfaceExpressJava.prendreAllOidOfEntity(
		    "metaobjet", "ObjetAbstrait");
	    for (int i = 0; i < objets.length; i++) {
		this.box.addItem((ObjetAbstrait) objets[i]);
	    }
	    this.description = new JTextArea(cell.getObject().getDescription());
	    this.name = new JTextField(cell.getObject().getName());

	    // Form Panel
	    JPanel formPanel = new JPanel(new GridLayout(4, 2));
	    formPanel.add(new JLabel(KMADEConstant.ABSTRACT_GROUP_NAME_TABLE));
	    formPanel.add(name);
	    formPanel.add(new JLabel(
		    KMADEConstant.ABSTRACT_GROUP_DESCRIPTION_TABLE));
	    formPanel.add(description);
	    formPanel.add(new JLabel(KMADEConstant.ABSTRACT_GROUP_TITLE_TABLE));
	    formPanel.add(box);

	    // Button Panel
	    JPanel buttonPanel = new JPanel(new BorderLayout());
	    ok = new JButton("Ok");
	    ok.setBorder(BorderFactory.createEtchedBorder());
	    ok.setOpaque(false);
	    ok.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    object.setName(name.getText());
		    object.setDescription(description.getText());
		    object.setContientObj((ObjetAbstrait) objets[box
			    .getSelectedIndex()]);
		    invView.getGraph()
			    .getGraphLayoutCache()
			    .editCell(GroupDefaultGraphCell.this,
				    GroupDefaultGraphCell.this.getAttributes());
		    GroupEditDialogCreation.this.dispose();
		}
	    });

	    buttonPanel.add(ok, BorderLayout.EAST);

	    // Packing
	    this.setLayout(new BorderLayout());
	    this.add(formPanel, BorderLayout.CENTER);
	    this.add(buttonPanel, BorderLayout.SOUTH);

	    this.pack();
	    this.setVisible(true);
	    KMADEToolUtilities.setCenteredInScreen(this);
	}
    };

    /**
     * Window to edit the group and the objects contained in it after its creation
     * @author Joachim TROUVERIE
     */
    class GroupEditDialog extends JDialog {

	private static final long serialVersionUID = 4897843890244687654L;

	private Groupe object;

	private JTextArea description;

	private JTextField name;

	private JTable editObjects;

	private JButton ok;

	public GroupEditDialog(GroupDefaultGraphCell cell, JDialog owner) {
	    super(owner, KMADEConstant.EDIT_GROUP_ACTION_MESSAGE);

	    // Initialization
	    this.object = cell.getObject();
	    this.description = new JTextArea(cell.getObject().getDescription());
	    this.name = new JTextField(cell.getObject().getName());

	    // Form Panel
	    JPanel formPanel = new JPanel(new GridLayout(4, 2));
	    formPanel.add(new JLabel(KMADEConstant.ABSTRACT_GROUP_NAME_TABLE));
	    formPanel.add(name);
	    formPanel.add(new JLabel(
		    KMADEConstant.ABSTRACT_GROUP_DESCRIPTION_TABLE));
	    formPanel.add(description);

	    this.editObjects = new JTable(new EditGroupTableModel(object));
	    this.editObjects.setDefaultEditor(Object.class,
		    new EditGroupTableEditor());
	    this.editObjects.setDefaultRenderer(Object.class,
		    new EditGroupTableRenderer());

	    // Button Panel
	    JPanel buttonPanel = new JPanel(new BorderLayout());
	    ok = new JButton("Ok");
	    ok.setBorder(BorderFactory.createEtchedBorder());
	    ok.setOpaque(false);
	    ok.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    object.setName(name.getText());
		    object.setDescription(description.getText());
		    for (int i = 0; i < editObjects.getModel().getRowCount(); i++) {
			int cp = -1;
			for (ObjetConcret o : object.getEnsemble()
				.getLstObjConcrets()) {
			    cp = cp+1;
			    for (AttributConcret a : o.getInverseListAttribut()) {
				cp = cp+1;
				if (cp == i)
				    a.setValeur(String.valueOf(editObjects.getModel()
					    .getValueAt(i, 1)));
			    }
			}
		    }
		    invView.getGraph()
			    .getGraphLayoutCache()
			    .editCell(GroupDefaultGraphCell.this,
				    GroupDefaultGraphCell.this.getAttributes());
		    GroupEditDialog.this.dispose();
		}
	    });

	    buttonPanel.add(ok, BorderLayout.EAST);

	    // Packing
	    this.setLayout(new BorderLayout());
	    this.add(formPanel, BorderLayout.NORTH);
	    this.add(new JScrollPane(editObjects), BorderLayout.CENTER);
	    this.add(buttonPanel, BorderLayout.SOUTH);

	    this.pack();
	    this.setVisible(true);
	    KMADEToolUtilities.setCenteredInScreen(this);
	}
    };

    /**
     * Table editor used in the window to edit the objects contained in the group
     * @author Joachim TROUVERIE
     */
    class EditGroupTableEditor extends AbstractCellEditor implements
	    TableCellEditor {

	private static final long serialVersionUID = -6782028045515902195L;

	private JComboBox box = null;

	private JTextField field = null;

	private String[] values = { "true", "false" };

	public Component getTableCellEditorComponent(JTable table,
		Object value, boolean isSelected, int row, int column) {

	    field = null;
	    box = null;

	    if (table.getModel().getValueAt(row, 1) instanceof Boolean) {
		box = new JComboBox(values);
		this.box.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			EditGroupTableEditor.this.stopCellEditing();
		    }
		});
		return box;
	    }

	    else {
		field = new JTextField();
		this.field.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			EditGroupTableEditor.this.stopCellEditing();
		    }
		});
		return field;
	    }
	}

	public Object getCellEditorValue() {
	    if (field == null)
		return Boolean.valueOf((String) box.getSelectedItem())
			.booleanValue();
	    else {
		try {
		    return Double.parseDouble(field.getText());
		} catch (NumberFormatException nfe) {
		    return field.getText();
		}
	    }

	}

    };

    /**
     * Table renderer used in the window to edit the objects contained in the group
     * @author Joachim TROUVERIE
     */
    class EditGroupTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -5875808651735753800L;

	@Override
	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus, int row,
		int column) {

	    Component cell = super.getTableCellRendererComponent(table, value,
		    isSelected, hasFocus, row, column);

	    if (table.getValueAt(row, 0).equals(
		    KMADEConstant.CONCRETE_OBJECT_TITLE_TABLE)) {
		cell.setBackground(Color.LIGHT_GRAY);
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
     * Table model used in the window to edit the objects contained in the group
     * @author Joachim TROUVERIE
     */
    class EditGroupTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1364115072730236546L;

	private List<Object[]> data = new ArrayList<Object[]>();

	@SuppressWarnings("unused")
	private Groupe g;

	public EditGroupTableModel(Groupe g) {
	    this.g = g;
	    for (ObjetConcret o : g.getEnsemble().getLstObjConcrets()) {
		this.addNewRow(KMADEConstant.CONCRETE_OBJECT_TITLE_TABLE,
			o.getName());
		for (AttributConcret a : o.getInverseListAttribut()) {
		    this.addNewRow(a.getDeriveName(), a.getValue().getValeur());
		}
	    }
	}
	
	@Override
	public String getColumnName(int columnIndex) {
	    return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    if (this.getValueAt(rowIndex, 0).equals(
		    KMADEConstant.CONCRETE_OBJECT_TITLE_TABLE)
		    || columnIndex != 1)
		return false;
	    else
		return true;
	}

	@Override
	public int getRowCount() {
	    return data.size();
	}

	@Override
	public int getColumnCount() {
	    return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    return data.get(rowIndex)[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    Class<?> classe = aValue.getClass();
	    if (classe.equals(Double.class)
		    && this.getValueAt(rowIndex, columnIndex).getClass()
			    .equals(Integer.class)) {
		aValue = ((Double) aValue).intValue();
		classe = aValue.getClass();
	    }
	    if (this.getValueAt(rowIndex, columnIndex).getClass()
		    .equals(classe))
		data.get(rowIndex)[columnIndex] = aValue;
	    else
		return;

	    this.fireTableDataChanged();
	}

	public void addNewRow(String name, Object value) {
	    Object[] o = { name, value };
	    data.add(o);
	    this.fireTableDataChanged();
	}

    };
}

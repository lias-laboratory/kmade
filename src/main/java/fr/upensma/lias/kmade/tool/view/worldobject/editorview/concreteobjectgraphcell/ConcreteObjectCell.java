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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.GraphConstants;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.ObjectDefaultGraphCell;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * Class for the cell representing concrete objects.
 * 
 * @author Joachim TROUVERIE
 */
public class ConcreteObjectCell extends ObjectDefaultGraphCell {

	private static final long serialVersionUID = 6774003475034967508L;

	private ObjetConcret item;

	private ConcreteObjectVertexView invView;

	@SuppressWarnings("unused")
	private ConcreteObjectEdit edit = null;

	/**
	 * First constructor for the cell (the point is not created yet)
	 * 
	 * @param concrete object represented by the cell
	 * @param point    x in the graph
	 * @param point    y in the graph
	 */
	public ConcreteObjectCell(ObjetConcret o, int x, int y) {
		super(o, x, y);
		this.item = o;
		Oid oidPoint = InterfaceExpressJava.createEntity(ExpressConstant.CORE_PACKAGE, ExpressConstant.POINT_CLASS);
		Point p = (Point) InterfaceExpressJava.prendre(oidPoint);
		p.setX(x);
		p.setY(y);
		o.setPoint(p);

		Map<?, ?> myHashTable = this.getAttributes();
		GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(x, y, 80, 40));

		this.setAttributes(new AttributeMap(myHashTable));
	}

	/**
	 * Second constructor for the cell (the point is already created)
	 * 
	 * @param concrete object represented by the cell
	 * @param point    in the graph
	 */
	public ConcreteObjectCell(ObjetConcret o, Point p) {
		super(o, p.getX(), p.getY());
		this.item = o;

		Map<?, ?> myHashTable = this.getAttributes();
		GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(p.getX(), p.getY(), 100, 40));

		this.setAttributes(new AttributeMap(myHashTable));
	}

	/**
	 * @return the concrete object description
	 */
	@Override
	public String getDescription() {
		return item.getDescription();
	}

	/**
	 * @return the concrete object represented by the cell
	 */
	public ObjetConcret getObject() {
		return item;
	}

	/**
	 * @return the concrete object's attributes to print it
	 */
	public String[] getObjectAttributes() {
		if (!item.getInverseListAttribut().isEmpty()) {
			String[] s = new String[item.getInverseListAttribut().size()];
			for (int i = 0; i < item.getInverseListAttribut().size(); i++) {
				s[i] = item.getInverseListAttribut().get(i).getName() + " : "
						+ item.getInverseListAttribut().get(i).getValue().getValeur();
			}
			return s;
		} else
			return null;
	}

	/**
	 * Method to set the renderer for the cell
	 * 
	 * @param new renderer for the cell
	 */
	public void setInvView(ConcreteObjectVertexView invView) {
		this.invView = invView;
	}

	/**
	 * @param the graph where the cell is located
	 * @return the cell's renderer
	 */
	public ConcreteObjectVertexView getInvView(JGraph graph) {
		if (this.invView != null)
			return invView;
		else
			return new ConcreteObjectVertexView(this, graph);
	}

	/**
	 * Method to set the concrete object's name
	 * 
	 * @param the new name
	 */
	public void setName(String newName) {
		this.item.setName(newName);
	}

	/**
	 * Method to set the concrete object's description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.item.setDescription(description);
	}

	/**
	 * Method to set a concrete object's attribute value
	 * 
	 * @param attribute 's name
	 * @param value
	 */
	public void setConcreteAttributeValue(String name, String value) {
		if (!item.getInverseListAttribut().isEmpty()) {
			for (AttributConcret a : item.getInverseListAttribut()) {
				if (a.getName().equals(name))
					a.setValeur(value);
			}
		}
	}

	/**
	 * Display a window to edit the concrete object
	 * 
	 * @param graph where the cell is located
	 */
	public void edit(JGraph graph) {
		this.edit = new ConcreteObjectEdit(this, GraphicEditorAdaptator.getMainFrame().getObjectDialogView(), graph);
	}

	/**
	 * Class displaying a window to edit the concrete object represented by the cell
	 * 
	 * @author Joachim TROUVERIE
	 */
	public class ConcreteObjectEdit extends JDialog {

		private static final long serialVersionUID = 5095480082791368262L;

		private JTextField name;

		private JTextArea description;

		private JTable attributes;

		private JButton ok;

		public ConcreteObjectEdit(final ConcreteObjectCell cell, JDialog owner, final JGraph myGraph) {

			super(owner, KMADEConstant.EDIT_CONCRETE_OBJECT_ACTION_MESSAGE);
			this.setLayout(new BorderLayout());

			// Name and description
			JPanel textPanel = new JPanel(new GridLayout(2, 2));
			name = new JTextField(cell.getName());
			description = new JTextArea(cell.getDescription());
			textPanel.add(new JLabel(KMADEConstant.CONCRETE_OBJECT_NAME_TABLE));
			textPanel.add(name);
			textPanel.add(new JLabel(KMADEConstant.CONCRETE_OBJECT_OBSERVATION_TABLE));
			textPanel.add(description);

			// Attributes
			attributes = new JTable(new EditionTableModel(cell));
			attributes.setDefaultEditor(Object.class, (TableCellEditor) new EditionTableEditor());
			JScrollPane table = new JScrollPane(attributes);

			// Button
			JPanel buttonPanel = new JPanel(new BorderLayout());
			ok = new JButton("Ok");
			buttonPanel.add(ok, BorderLayout.EAST);

			ok.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					// Name
					if (name.getText() != null)
						cell.setName(name.getText());
					else {
						JOptionPane.showInputDialog(ConcreteObjectEdit.this, "You need to enter a valid name", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Description
					if (description.getText() != null)
						cell.setDescription(description.getText());
					else
						cell.setDescription("");

					// Attributes
					for (int i = 0; i < attributes.getModel().getRowCount(); i++) {
						String name = ((String) attributes.getModel().getValueAt(i, 0)).split(" : ")[0];
						String value = attributes.getModel().getValueAt(i, 1).toString();
						cell.setConcreteAttributeValue(name, value);
					}
					myGraph.getGraphLayoutCache().insert(ConcreteObjectCell.this);
					ConcreteObjectEdit.this.dispose();
				}
			});

			this.add(textPanel, BorderLayout.NORTH);
			this.add(table, BorderLayout.CENTER);
			this.add(buttonPanel, BorderLayout.SOUTH);

			this.pack();
			this.setVisible(true);
			KMADEToolUtilities.setCenteredInScreen(this);
		}

		/**
		 * Table editor used to edit the concrete object
		 * 
		 * @author Joachim TROUVERIE
		 */
		class EditionTableEditor extends AbstractCellEditor implements TableCellEditor {

			private static final long serialVersionUID = -6782028045515902195L;

			private JComboBox box = null;

			private JTextField field = null;

			private String[] values = { "true", "false" };

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {

				field = null;
				box = null;

				if (table.getModel().getValueAt(row, 1) instanceof Boolean) {
					box = new JComboBox(values);
					this.box.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EditionTableEditor.this.stopCellEditing();
						}
					});
					return box;
				}

				else {
					field = new JTextField();
					this.field.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EditionTableEditor.this.stopCellEditing();
						}
					});
					return field;
				}
			}

			public Object getCellEditorValue() {
				if (field == null)
					return Boolean.valueOf((String) box.getSelectedItem()).booleanValue();
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
		 * Table model used to edit the concrete object
		 * 
		 * @author Joachim TROUVERIE
		 */
		class EditionTableModel extends AbstractTableModel {

			private static final long serialVersionUID = -7353628699628918289L;

			private List<Object[]> data = new ArrayList<Object[]>();

			private String[] columnName = { KMADEConstant.CONCRETE_ATTRIBUT_NAME_TABLE,
					KMADEConstant.CONCRETE_ATTRIBUT_VALUE_TABLE };

			public EditionTableModel(ConcreteObjectCell cell) {
				if (cell.getObjectAttributes() != null) {
					for (String s : cell.getObjectAttributes()) {
						Object value;
						if (s.split(" : ").length == 2) {
							value = s.split(" : ")[1];
							if (value.equals("0"))
								value = 0;
							else if (value.equals("true"))
								value = true;
						}

						else
							value = " ";

						String name = s.split(" : ")[0];
						Object[] tab = { name, value };
						data.add(tab);
					}
				}
			}

			public int getRowCount() {
				return data.size();
			}

			public int getColumnCount() {
				return 2;
			}

			@Override
			public String getColumnName(int columnIndex) {
				return columnName[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnIndex == 1;
			}

			public Object getValueAt(int rowIndex, int columnIndex) {
				return data.get(rowIndex)[columnIndex];
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (columnIndex == 1) {
					Class<?> classe = aValue.getClass();
					if (classe.equals(Double.class)
							&& this.getValueAt(rowIndex, columnIndex).getClass().equals(Integer.class)) {
						aValue = ((Double) aValue).intValue();
						classe = aValue.getClass();
					}
					if (this.getValueAt(rowIndex, columnIndex).getClass().equals(classe))
						data.get(rowIndex)[columnIndex] = aValue;
					else
						return;

					this.fireTableDataChanged();
				}
			}
		};

	};

}

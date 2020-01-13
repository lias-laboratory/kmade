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
package fr.upensma.lias.kmade.tool.view.coherence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.TableSorter;
import fr.upensma.lias.kmade.tool.viewadaptator.CoherenceAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADECoherenceDialog extends JDialog {

	private static final long serialVersionUID = 576880630360938978L;

	private MyDefaultTableModel refModel;

	private TableSorter sorter;

	private static final ImageIcon WARNING_IMAGE = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.WARNING_IMAGE));

	private static final ImageIcon ERROR_IMAGE = new ImageIcon(
			GraphicEditorAdaptator.class.getResource(KMADEConstant.ERROR_IMAGE));

	static class MyWaringErrorImage implements TableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel myLabel;
			if (column == 0) {
				if (value != null && value instanceof Integer) {
					Integer myValue = (Integer) value;
					if (myValue == 1) {
						myLabel = new JLabel(WARNING_IMAGE, JLabel.CENTER);
					} else if (myValue == 2) {
						myLabel = new JLabel(ERROR_IMAGE, JLabel.CENTER);
					} else {
						myLabel = new JLabel(" ");
					}
				} else {
					myLabel = new JLabel(" ");
				}
			} else if (column == 3) {
				String message = "";
				int myValue = (Integer) value;
				switch (myValue) {
				case CoherenceAdaptator.HIERARCHICAL_TYPE: {
					message = KMADEConstant.HIERARCHY_TYPE_MESSAGE;
					break;
				}
				case CoherenceAdaptator.EXPRESSION_TYPE: {
					message = KMADEConstant.EXPRESSION_TYPE_MESSAGE;
					break;
				}
				default:
					message = "";
				}
				myLabel = new JLabel(message, JLabel.LEFT);
			} else if (column == 4) {
				String message = "";
				int myValue = (Integer) value;
				switch (myValue) {
				case CoherenceAdaptator.HIERARCHICAL_TASK_MODEL: {
					message = KMADEConstant.TASKS_SPACE_LOCATION_MESSAGE;
					break;
				}
				case CoherenceAdaptator.PRE_CONDITION_DIALOG: {
					message = KMADEConstant.PRECONDITION_LOCATION_MESSAGE;
					break;
				}
				case CoherenceAdaptator.POST_CONDITION_DIALOG: {
					message = KMADEConstant.EFFETSDEBORD_LOCATION_MESSAGE;
					break;
				}
				case CoherenceAdaptator.ITERATION_DIALOG: {
					message = KMADEConstant.ITERATION_LOCATION_MESSAGE;
					break;
				}
				default:
					message = "";
				}
				myLabel = new JLabel(message, JLabel.LEFT);
			} else {
				myLabel = new JLabel("");
			}

			myLabel.setOpaque(true);
			if (isSelected) {
				myLabel.setBackground(table.getSelectionBackground());
				myLabel.setForeground(table.getSelectionForeground());
			} else {
				myLabel.setBackground(table.getBackground());
				myLabel.setForeground(table.getForeground());
			}
			return myLabel;
		}
	}

	public KMADECoherenceDialog(Frame owner) {
		super(owner, KMADEConstant.COHERENCE_TITLE_MESSAGE);
		this.getContentPane().setLayout(new BorderLayout(10, 10));

		refModel = new MyDefaultTableModel();
		sorter = new TableSorter(refModel);
		JTable myTable = new JTable(sorter);
		myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myTable.setSelectionBackground(KMADEConstant.ACTIVE_SELECTION);
		myTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DefaultListSelectionModel table = (DefaultListSelectionModel) e.getSource();
				int p = table.getMinSelectionIndex();
				if (getTableSorterModel().getRowCount() == 0) {
					return;
				}

				if (p == -1) {
					p = 0;
				}
				CoherenceAdaptator.selectTaskFromError(getTableSorterModel().getValueAt(p, 5));
			}
		});
		sorter.setTableHeader(myTable.getTableHeader());
		MyWaringErrorImage myRenderer = new MyWaringErrorImage();
		myTable.getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(3).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(4).setCellRenderer(myRenderer);
		myTable.getColumnModel().getColumn(0).setMaxWidth(40);
		myTable.getColumnModel().getColumn(1).setMinWidth(100);
		myTable.addMouseListener(new mouseHelpListener());
		JScrollPane myScrollPane = new JScrollPane(myTable);
		myScrollPane.setBorder(BorderFactory.createTitledBorder(KMADEConstant.ERRORS_TITLE_MESSAGE));

		JPanel myPanelControlButtons = new JPanel();
		JButton verifier = new JButton(KMADEConstant.COHERENCE_CHECK_MESSAGE);
		verifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoherenceAdaptator.checkTaskModel();
			}
		});
		JButton fermer = new JButton(KMADEConstant.GO_BACK_MESSAGE);
		fermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoherenceAdaptator.closeCoherenceDialog();
			}
		});
		myPanelControlButtons.add(verifier);
		myPanelControlButtons.add(fermer);

		this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
		this.getContentPane().add(BorderLayout.CENTER, myScrollPane);
		this.getContentPane().add(BorderLayout.SOUTH, myPanelControlButtons);
		this.pack();
		Dimension dim = new Dimension(700, 300);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		Insets insets = tk.getScreenInsets(getGraphicsConfiguration());
		int width = (int) (d.getWidth() - insets.left - insets.right);
		int height = (int) (d.getHeight() - insets.top - insets.bottom);
		Dimension screenDim = new Dimension(width, height);

		if (screenDim.height < dim.height) {
			this.setSize(screenDim);
			this.setLocation(insets.left, insets.top);
		} else {
			this.setSize(new Dimension(700, 300));
			KMADEToolUtilities.setCenteredInScreen(this);
		}
		this.setVisible(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				CoherenceAdaptator.closeCoherenceDialog();
			}
		});
	}

	public void setProblemMessages(ArrayList<Object[]> tab) {
		refModel.setTab(tab);
		refModel.fireTableDataChanged();
	}

	public MyDefaultTableModel getDefaultTableModel() {
		return refModel;
	}

	public TableSorter getTableSorterModel() {
		return sorter;
	}

	static class MyDefaultTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 4827050113761407540L;

		private ArrayList<Object[]> myMessageList = new ArrayList<Object[]>();

		public void setTab(ArrayList<Object[]> pTab) {
			this.myMessageList = pTab;
		}

		public int getRowCount() {
			return myMessageList.size();
		}

		public int getColumnCount() {
			return 5;
		}

		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return " ";
			case 1:
				return KMADEConstant.MESSAGE_ERROR_COLUMN_NAME;
			case 2:
				return KMADEConstant.TASK_NAME_COLUMN_NAME;
			case 3:
				return KMADEConstant.ERROR_TYPE_COLUMN_NAME;
			case 4:
				return KMADEConstant.LOCATION_COLUMN_NAME;
			default:
				return "";
			}
		}

		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return Object.class;
			}
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Object[] temp = myMessageList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return (Integer) temp[0];
			case 1:
				return (String) temp[1];
			case 2:
				return ((Task) temp[2]).getName();
			case 3:
				return (Integer) temp[3];
			case 4:
				return (Integer) temp[4];
			case 5:
				return (Task) temp[2];
			}
			return "";
		}
	}

	class mouseHelpListener extends MouseAdapter {
		public void mouseClicked(MouseEvent mouseEvent) {
			if (mouseEvent.getClickCount() == 2) {
				JTable table = (JTable) mouseEvent.getSource();
				int p = table.getSelectedRow();
				Object[] temp = { getTableSorterModel().getValueAt(p, 0), getTableSorterModel().getValueAt(p, 1),
						getTableSorterModel().getValueAt(p, 5), getTableSorterModel().getValueAt(p, 3),
						getTableSorterModel().getValueAt(p, 4) };
				CoherenceAdaptator.goToSelectMessage(temp);
			}
		}
	}
}

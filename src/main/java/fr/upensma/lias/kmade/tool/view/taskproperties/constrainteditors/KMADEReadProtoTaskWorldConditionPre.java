package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.ConditionAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

public class KMADEReadProtoTaskWorldConditionPre extends JScrollPane implements LanguageFactory  {

	private static final long serialVersionUID = -2510557691422382155L;

	private KMADEJTable table;

	private myModel modele;

	public KMADEReadProtoTaskWorldConditionPre() {
		modele = new myModel();
		table = new KMADEJTable(modele);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.setViewportView(table);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
		// table.setFocusable(true);
		this.setPreferredSize(new Dimension (390,600));
		table.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent ke) {
				// on enleve lacondition de la tâche
				if (ke.getKeyChar() == KeyEvent.VK_DELETE
						|| ke.getKeyChar() == KeyEvent.VK_ENTER) {
					int r[] = ((JTable) ke.getSource()).getSelectedRows();
					for (int i = 0; i < r.length; i++) {
						//3 pour l'oid
						String oidConditions = (String) modele.getValueAt(r[i], 2);
						ConditionAdaptator.setPreCondition(oidConditions);
						//TODO
						GraphicEditorAdaptator.getPanelProp().getEditorPrePostIterDialog().getAllPreconditionPanel().getProtoTaskPreconditionPanel().updateDataModel();

					}

				}

			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyPressed(KeyEvent e) {

			}
		});
		table.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int r = ((JTable) e.getSource()).getSelectedRow();

					String oidConditions = (String) modele.getValueAt(r, 2);
					ConditionAdaptator.setPreCondition(oidConditions);
					GraphicEditorAdaptator.getPanelProp().getEditorPrePostIterDialog().getAllPreconditionPanel().getProtoTaskPreconditionPanel().updateDataModel();
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

		});
	}

	public void setData(Object[][] tab) {
		modele.setData(tab);
		modele.fireTableDataChanged();
	}

	static class myModel extends AbstractTableModel {
		static final long serialVersionUID = 132585986L;

		private Object[][] data = new Object[0][];

		public int getColumnCount() {
			return 2;
		}

		public int getRowCount() {
			return (data == null ? 0 : data.length);
		}

		public Object getValueAt(int param, int param1) {
			return data[param][param1];
		}

		public String getColumnName(int i) {
			switch (i) {
			case 0:
				return KMADEConstant.PROTOTASK_CONDITION_DESCRIPTION;
			case 1:
				return KMADEConstant.PROTOTASK_CONDITION_DEFAULT_VALUE;
			default:
				return "";
			}
		}

		public void setData(Object[][] data) {
			this.data = data;
		}
	}

	class MyTableListModel extends DefaultListTableModel {

		private static final long serialVersionUID = 8773587010073998732L;

		public MyTableListModel(String[] columnNames, String nameDefault) {
			super(columnNames, nameDefault);
		}

		public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
			return false;
		}
	}

	public void notifLocalisationModification() {
	}

	public void clearSelection() {
		table.getSelectionModel().clearSelection();
	}

	public void setOrganizationAddNameBorder(String name) {
		this.setBorder(new TitledBorder(null,
			KMADEConstant.CONDITION_WORLD_TITLE+ " : " + name,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

	}

	public void removeAllCondition() {
		table.removeAll();
		modele.setData(new Object[0][]);
	}

	public void updateDataModel(Object[][] o) {
		this.removeAllCondition();
		// les conditions {oid, description, valeur}
		Object[][] temp = new Object[o.length][3];

		for (int i = 0; i < o.length; i++) {

			// description
			temp[i][0] = (String) o[i][0];
			// valeur
			temp[i][1] = (String) o[i][1];
			// oid
			temp[i][2] = (String) o[i][2];

		}
		modele.setData(temp);
		modele.fireTableDataChanged();

	}
}

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
package fr.upensma.lias.kmade.tool.view.worldobject.user;

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
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;

/**
 * @author Mickael BARON
 */
public class KMADEReadOrganisationNotOfIndividu extends JScrollPane implements LanguageFactory {

	private static final long serialVersionUID = 6371713337519407996L;

	private KMADEJTable table;

	private myModel modele;

	public KMADEReadOrganisationNotOfIndividu() {
		modele = new myModel();
		table = new KMADEJTable(modele);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.setViewportView(table);
		this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
		table.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent ke) {
				// on enleve l'individu des organisations selectionn�es
				if (ke.getKeyChar() == KeyEvent.VK_DELETE || ke.getKeyChar() == KeyEvent.VK_ENTER) {
					int r[] = ((JTable) ke.getSource()).getSelectedRows();
					for (int i = 0; i < r.length; i++) {
						String oidOrga = (String) modele.getValueAt(r[i], 5);
						KMADEMainFrame.getProjectPanel().tableIndividu().addOrganizationToActiveIndividu(oidOrga);
					}
					KMADEMainFrame.getProjectPanel().tableIndividu().refreshActiveIndividu();

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
					String oidOrga = (String) modele.getValueAt(r, 5);
					KMADEMainFrame.getProjectPanel().tableIndividu().addOrganizationToActiveIndividu(oidOrga);
					KMADEMainFrame.getProjectPanel().tableIndividu().refreshActiveIndividu();
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
			return 5;
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
				return KMADEConstant.ORGANIZATION_NAME_TABLE;
			case 1:
				return KMADEConstant.ORGANIZATION_STATUS_TABLE;
			case 2:
				return KMADEConstant.ORGANIZATION_ROLE_TABLE;
			case 3:
				return KMADEConstant.ORGANIZATION_PHOTO_TABLE;
			case 4:
				return KMADEConstant.ORGANIZATION_MEMBERS_TABLE;
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

	public void setOrganizationNameBorder(String name) {
		this.setBorder(new TitledBorder(null,
				KMADEConstant.INDIVIDU_OTHERS + " " + KMADEConstant.INDIVIDU_ORGANIZATION_TABLE + " : " + name,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

	}

	public void removeAllOrga() {
		table.removeAll();
		modele.setData(new Object[0][]);
	}

	public void updateDataModel(Object[][] o) {
		this.removeAllOrga();
		// les organisations {oid, name, statut, role, image path,nom des
		// membres}
		Object[][] temp = new Object[o.length][6];
		for (int i = 0; i < o.length; i++) {

			// nom
			temp[i][0] = (String) o[i][1];
			// statut
			temp[i][1] = (String) o[i][2];
			// role
			temp[i][2] = (String) o[i][3];
			// image path
			temp[i][3] = (String) o[i][4];
			// nom des membres
			temp[i][4] = (String) o[i][5];
			// oid
			temp[i][5] = (String) o[i][0];
		}
		modele.setData(temp);
		modele.fireTableDataChanged();

	}
}

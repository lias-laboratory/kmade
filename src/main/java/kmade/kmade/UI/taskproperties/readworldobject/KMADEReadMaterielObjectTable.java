package kmade.kmade.UI.taskproperties.readworldobject;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.KMADEJTable;
import kmade.kmade.adaptatorUI.ActorSystemAdaptator;
import kmade.nmda.schema.Oid;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadMaterielObjectTable extends JScrollPane {
    private static final long serialVersionUID = 4688187958637137151L;

	private myModel modele;

	private JTable table;
	
    static class MyImageColumn implements TableCellRenderer {

        private ImageIcon refImageIcon;
               
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel myLabel;
            if (value != null && value instanceof Object[]) {
            	Object[] toto = (Object[])value;
            	refImageIcon = (ImageIcon)toto[1];
            	myLabel = new JLabel(refImageIcon,JLabel.CENTER);
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
    
	public KMADEReadMaterielObjectTable() {
        modele = new myModel();
        table = new KMADEJTable(modele);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setViewportView(table);
        table.addMouseListener(new mouseHelpListener());
        table.getColumnModel().getColumn(3).setCellRenderer(new MyImageColumn());
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
                case 0 : return KMADEConstant.MACHINE_NAME_TABLE;
                case 1 : return KMADEConstant.MACHINE_DESCRIPTION_TABLE;
                case 2 : return KMADEConstant.MACHINE_ISCOMPUTER_TABLE;
                case 3 : return KMADEConstant.MACHINE_IMAGE_TABLE;
                case 4 : return KMADEConstant.MACHINE_PARCS_TABLE;
                default : return "";
            }
		}
        
        public void setData(Object[][] data) {
            this.data = data;
        }
	}

	class mouseHelpListener extends MouseAdapter {
		public void mouseClicked(MouseEvent mouseEvent) {
			if (mouseEvent.getClickCount() == 2) {
                String select = (String) table.getModel().getValueAt(table.getSelectedRow(), 5);
                ActorSystemAdaptator.addNewActorSystem(new Oid(select));
			}
		}
	}    
}

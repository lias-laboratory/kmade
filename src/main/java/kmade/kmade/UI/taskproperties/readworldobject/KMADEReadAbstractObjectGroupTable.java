package kmade.kmade.UI.taskproperties.readworldobject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.KMADEJTable;
import kmade.kmade.adaptatorUI.ReadAbstractObjectAdaptator;

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
public class KMADEReadAbstractObjectGroupTable extends JScrollPane {

    private static final long serialVersionUID = 8813178970912444995L;

    private MyReadGroupTableModel myModel;

    private JTable table;

    public KMADEReadAbstractObjectGroupTable() {
        myModel = new MyReadGroupTableModel();
        table = new KMADEJTable(myModel);
        this.setViewportView(table);      
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        table.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   if (!table.getSelectionModel().isSelectionEmpty()) {
                       ReadAbstractObjectAdaptator.addNewGroup(table.getSelectionModel().getMinSelectionIndex());
                   }
               }
           }
        });
    }

    public void setData(Object[][] refData) {
        this.myModel.data = refData;
        this.myModel.fireTableDataChanged();
        table.getSelectionModel().clearSelection();
    }

    public void setGroupNameBorder(String name) {
        this.setBorder(new TitledBorder(null, KMADEConstant.ABSTRACT_GROUP_TITLE_TABLE + " " + name, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, KMADEConstant.fontPASSIF));
    }
    
    static class MyReadGroupTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1079675105218756282L;

        private Object[][] data = new Object[0][];

        public int getColumnCount() {
            return 3;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            switch (col) {
            case 0:
                return KMADEConstant.ABSTRACT_GROUP_NAME_TABLE;
            case 1:
                return KMADEConstant.ABSTRACT_GROUP_DESCRIPTION_TABLE;
            case 2:
                return KMADEConstant.ABSTRACT_GROUP_SET_TYPE_TABLE;
            default:
                return "";
            }
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
}

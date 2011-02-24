package fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEJTable;
import fr.upensma.lias.kmade.tool.viewadaptator.ReadAbstractObjectAdaptator;


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
public class KMADEReadAbstractObjectTable extends JScrollPane {

    private static final long serialVersionUID = -8952846858100692775L;

    private MyReadAbstractObjectTableModel modele;

    private JTable table;

    public KMADEReadAbstractObjectTable() {
        modele = new MyReadAbstractObjectTableModel();
        table = new KMADEJTable(modele);
        this.setViewportView(table);      
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
        
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    ReadAbstractObjectAdaptator.noAbstractSelection();
                } else {
                    ReadAbstractObjectAdaptator.setAbstractObjectSelection(lsm.getMinSelectionIndex());
                }
            }
        });
    }

    public void setAbstractObjectNameBorder(String name) {
        String afficher = "";
        if (name.equals("")) 
            afficher = "";
        else 
            afficher = " : " + name;
        this.setBorder(new TitledBorder(null, KMADEConstant.ABSTRACT_OBJECT_TITLE_TABLE + afficher, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, KMADEConstant.fontACTIF));
    }
    
    public void setData(Object[][] refData) {
        this.modele.data = refData;
        this.modele.fireTableDataChanged();
        table.getSelectionModel().clearSelection();
    }
    
    static class MyReadAbstractObjectTableModel extends AbstractTableModel {
        
        private static final long serialVersionUID = 6307559459569197314L;

        private Object[][] data = new Object[0][];
        
        public int getColumnCount() {
            return 2;
        }       

        public int getRowCount() {
            return data.length;
        }
      
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public String getColumnName(int i) {
            switch(i) {
                case 0 : return KMADEConstant.ABSTRACT_OBJECT_NAME_TABLE;
                case 1 : return KMADEConstant.ABSTRACT_OBJECT_OBSERVATION_TABLE;
                default : return "";
            }
        }
        
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }
}

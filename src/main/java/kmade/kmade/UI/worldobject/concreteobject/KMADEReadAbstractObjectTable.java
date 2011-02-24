package kmade.kmade.UI.worldobject.concreteobject;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.KMADEJTable;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorUI.ConcreteObjectPanelAdaptator;
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
public class KMADEReadAbstractObjectTable extends JScrollPane implements LanguageFactory {
    private static final long serialVersionUID = 465431L;

    private final MyReadAbstractObjectTableModel myModel;

    private final JTable table;

    private int selectedRowCourant = -1;

    public KMADEReadAbstractObjectTable() {
        myModel = new MyReadAbstractObjectTableModel();
        table = new KMADEJTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 90));
        this.setViewportView(table);
        this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    ConcreteObjectPanelAdaptator.setActiveAbstractObject(Oid.OID_NULL);
                    selectedRowCourant = -1;
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    if (selectedRow != selectedRowCourant) {
                        ConcreteObjectPanelAdaptator.setActiveAbstractObject((String)myModel.getValueAt(selectedRow,2));
                        selectedRowCourant = selectedRow;
                    }
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
        this.setBorder(new TitledBorder(null, KMADEConstant.CONCRETE_ABSTRACT_OBJECT_TITLE_TABLE + afficher, TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, KMADEConstant.fontACTIF));
    }
    
    public void removeAllAbstractObject() {
        myModel.setData(new Object[0][]);
    }

    public void setData(Object[][] refAbstractObject) {
        myModel.setData(refAbstractObject);
    }

    class MyReadAbstractObjectTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 7209012552138445616L;

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
            switch (i) {
            case 0:
                return KMADEConstant.ABSTRACT_OBJECT_NAME_TABLE;
            case 1:
                return KMADEConstant.ABSTRACT_OBJECT_OBSERVATION_TABLE;
            default:
                return "";
            }
        }

        public void setData(Object[][] data) {            
            this.data = data;
            this.fireTableDataChanged();
        }
    }

    public void notifLocalisationModification() {
        // Rien.
    }
}

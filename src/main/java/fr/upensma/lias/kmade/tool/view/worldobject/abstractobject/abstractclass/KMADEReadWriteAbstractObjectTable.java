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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstractclass;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.RegularExpression;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteAbstractObjectTable extends JScrollPane implements
	LanguageFactory {

    private static final long serialVersionUID = -7852657328634031613L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private int selectedRowCourant = -1;

    public KMADEReadWriteAbstractObjectTable() {
	String[] columnNames = { KMADEConstant.ABSTRACT_OBJECT_NAME_TABLE,
		KMADEConstant.ABSTRACT_OBJECT_OBSERVATION_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.ABSTRACT_OBJECT_NEW_OBJECT_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.setCellEditor(0, "SpecialString", null);
	myListTable.setCellEditor(1, "String", null);
	myListTable.addAllKeyListener(new MyKeyListener());
	this.setViewportView(myListTable);
	this.setColumnHeaderView(myListTable.getPanelHeader());
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);

	// Abonnement aux diff�rents �couteurs.
	myListTable.getListSelectionModel().addListSelectionListener(
		new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
			if (myListTable.getListSelectionModel()
				.isSelectionEmpty()) {
			    selectedRowCourant = -1;
			    AbstractObjectPanelAdaptator
				    .setActiveAbstractObject("", Oid.OID_NULL);
			} else {
			    int selectedRow = myListTable
				    .getListSelectionModel()
				    .getMinSelectionIndex();

			    if (selectedRow != myListTable
				    .getListSelectionModel()
				    .getMaxSelectionIndex()) {
				AbstractObjectPanelAdaptator
					.setActiveAbstractObject("",
						Oid.OID_NULL);
				return;
			    }

			    if (selectedRow != selectedRowCourant) {
				if (selectedRow != myTableListModel
					.getRowCount() - 1) {
				    AbstractObjectPanelAdaptator.setActiveAbstractObject(
					    ((String) myTableListModel
						    .getValueAt(selectedRow, 0)),
					    ((String) myTableListModel
						    .getValueAt(selectedRow, 2)));
				    selectedRowCourant = selectedRow;
				} else {
				    selectedRowCourant = -1;
				    AbstractObjectPanelAdaptator
					    .setActiveAbstractObject("",
						    Oid.OID_NULL);
				}
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
	this.setBorder(new TitledBorder(null,
		KMADEConstant.ABSTRACT_OBJECT_TITLE_TABLE + afficher,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
		KMADEConstant.fontACTIF));
    }

    public void removeAllAbstractObject() {
	myTableListModel.removeAllListTableData();
    }

    public void addAbstractObject(String name, String description, String oid) {
	Object[] tempo = { name, description, oid, false };
	myTableListModel.getListTableData().add(tempo);
	myTableListModel.fireTableRowsInserted(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
	myTableListModel.fireListRowsInserted();
	myListTable.getListSelectionModel().setSelectionInterval(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
    }

    class MyTableListModel extends DefaultListTableModel {
	private static final long serialVersionUID = 7930031534603520066L;

	public MyTableListModel(String[] columnNames, String nameDefault) {
	    super(columnNames, nameDefault);
	}

	private String nameTest(String value) {
	    String name = value;
	    boolean needVerif = true;
	    // un nom null annule l'�dition, boucle tant que le nom n'est pas
	    // correct
	    while (name != null && needVerif) {
		// v�rification de l'expression r�guli�re avec affichage d'un
		// popUp
		if (!RegularExpression.isGoodAbstractObjectName(name)) {
		    name = (String) JOptionPane
			    .showInputDialog(
				    GraphicEditorAdaptator.getMainFrame(),
				    KMADEConstant.BAD_CHARACTER_TEXT,
				    KMADEConstant.BAD_CARACTER_TITLE,
				    JOptionPane.YES_NO_OPTION,
				    new ImageIcon(
					    GraphicEditorAdaptator.class
						    .getResource(KMADEConstant.ASK_DIALOG_IMAGE)),
				    null, name);
		} else { // l'expression est ok
		    if (ObjetAbstrait.isUniqueName(name)) {
			// si le nom est unique, le nom est correct et possible
			needVerif = false;
		    } else {
			name = (String) JOptionPane
				.showInputDialog(
					GraphicEditorAdaptator.getMainFrame(),
					KMADEConstant.SAME_NAME_TEXT,
					KMADEConstant.SAME_NAME_TITLE,
					JOptionPane.YES_NO_OPTION,
					new ImageIcon(
						GraphicEditorAdaptator.class
							.getResource(KMADEConstant.ASK_DIALOG_IMAGE)),
					null, ObjetAbstrait
						.propositionNom(name));
		    }
		}
	    }
	    return name;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    String value = (String) aValue;

	    if (rowIndex + 1 == this.getRowCount()) {
		if (value.equals("")) {
		    return;
		}
		String name = nameTest(value);
		if (name == null || name.equals("")) {
		    return;
		}
		value = name;
		// Ajouter une nouvelle ligne.
		String newAbstractObject = AbstractObjectAdaptator
			.addAbstractObject();
		value = AbstractObjectAdaptator.setAbstractObjectName(
			newAbstractObject, value);
		Object[] tempo = { value, "", newAbstractObject, false };
		myTableListModel.add(tempo, rowIndex);
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);
	    } else {
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String oidRow = (String) tempValue[2];

		switch (columnIndex) {
		case 0: {
		    String nameRow = (String) tempValue[0];
		    if (nameRow.equals(value) || value.equals(""))
			return;
		    String name = nameTest(value);
		    if (name == null || name.equals("") || nameRow.equals(name)) {
			return;
		    }
		    value = name;
		    value = AbstractObjectAdaptator.setAbstractObjectName(
			    oidRow, value);
		    break;
		}
		case 1: {
		    AbstractObjectAdaptator.setAbstractObjectDescription(
			    oidRow, value);
		}
		}
		tempValue[columnIndex] = value;
	    }
	}
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"AbstractObject", r,
			myTableListModel.getListTableData(),
			GraphicEditorAdaptator.getMainFrame(),
			KMADEConstant.ABSTRACT_OBJECT_REMOVE_MESSAGE_TITLE);

		if (state) {
		    myTableListModel.fireTableDataChanged();
		    myListTable.getListSelectionModel().setSelectionInterval(
			    myTableListModel.getRowCount() - 1,
			    myTableListModel.getRowCount() - 1);
		}
	    }
	}
    }

    public void notifLocalisationModification() {
	String[] columnNames = { KMADEConstant.ABSTRACT_OBJECT_NAME_TABLE,
		KMADEConstant.ABSTRACT_OBJECT_OBSERVATION_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.ABSTRACT_OBJECT_NEW_OBJECT_TABLE);
    }
}

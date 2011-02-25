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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstractclass;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.AgregatStructure;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.RegularExpression;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GroupAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteGroupTable extends JScrollPane implements LanguageFactory {

    private static final long serialVersionUID = 6709999477752649169L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    public KMADEReadWriteGroupTable() {
	String[] columnNames = { KMADEConstant.ABSTRACT_GROUP_NAME_TABLE,
		KMADEConstant.ABSTRACT_GROUP_DESCRIPTION_TABLE,
		KMADEConstant.ABSTRACT_GROUP_SET_TYPE_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.ABSTRACT_GROUP_NEW_OBJECT_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.addAllKeyListener(new MyKeyListener());
	this.setViewportView(myListTable);
	this.setColumnHeaderView(myListTable.getPanelHeader());
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	// specialString pour la verification et le popup
	myListTable.setCellEditor(0, "SpecialString", null);
	myListTable.setCellEditor(1, "String", null);
	String[] enumList = AgregatStructure.getLocaleAgregatStructures();
	myListTable.setCellEditor(2, "List", enumList);

    }

    public void removeAllGroup() {
	myTableListModel.removeAllListTableData();
    }

    public void clearSelection() {
	myListTable.getListSelectionModel().clearSelection();
    }

    public void updateDataModel(Object[][] o) {
	for (int i = 0; i < o.length; i++) {
	    Object[] temp = new Object[o[i].length + 1];
	    for (int j = 0; j < o[i].length; j++) {
		temp[j] = (String) o[i][j];
	    }
	    temp[4] = false;
	    myTableListModel.getListTableData().add(temp);
	}
	myTableListModel.fireTableDataChanged();
    }

    public void setGroupNameBorder(String name) {
	this.setBorder(new TitledBorder(null,
		KMADEConstant.ABSTRACT_GROUP_TITLE_TABLE + " " + name,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
		KMADEConstant.fontPASSIF));
    }

    class MyTableListModel extends DefaultListTableModel {
	private static final long serialVersionUID = 7930031534603520066L;

	public MyTableListModel(String[] columnNames, String nameDefault) {
	    super(columnNames, nameDefault);
	}

	/**
	 * Test si le nom est correct
	 * 
	 * @param value
	 * @return une chaine vide ou null si le nom est incorrect le nom si le
	 *         nom est correct
	 */
	private String nameTest(String value) {
	    String name = value;
	    boolean needVerif = true;
	    // un nom null annule l'�dition, boucle tant que le nom n'est pas
	    // correct
	    while (name != null && needVerif) {
		// v�rification de l'expression r�guli�re avec affichage d'un
		// popUp
		if (!RegularExpression.isGoodGroupName(name)) {
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
		    if (Groupe.isUniqueName(name)) {
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
					null, Groupe.propositionNom(name));
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
		// Ajouter une nouvelle ligne.
		String name = nameTest(value);
		if (name == null || name.equals("")) {
		    return;
		}

		String newAbstractObject = GroupAdaptator
			.addGroup(AbstractObjectPanelAdaptator
				.getActiveAbstractObject());
		value = GroupAdaptator.setGroupName(newAbstractObject, name);
		Object[] tempo = { name, "",
			AgregatStructure.LIST_AGREGAT.getValue(),
			newAbstractObject, false };
		myTableListModel.add(tempo, rowIndex);
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);
		return;

	    } else {
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String oidRow = (String) tempValue[3];

		switch (columnIndex) {
		case 0: {
		    String nameRow = (String) tempValue[0];
		    if (nameRow.equals(value) || value.equals(""))
			return;

		    String name = nameTest(value);
		    if (name == null || name.equals("") || nameRow.equals(name)) {
			return;
		    }

		    value = GroupAdaptator.setGroupName(oidRow, name);
		    myTableListModel.fireTableDataChanged();
		    break;
		}
		case 1: {
		    GroupAdaptator.setGroupDescription(oidRow, value);
		    break;
		}
		case 2: {
		    value = AgregatStructure
			    .getLocaleAgregatStructureIntoEnumere(value);
		    if (GroupAdaptator.setGroupSet(oidRow, value)) {
			break;
		    } else {
			return;
		    }
		}
		}
		tempValue[columnIndex] = value;
	    }
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
	    if ((rowIndex != this.getRowCount() - 1) && columnIndex == 2) {
		return AgregatStructure
			.getEnumereIntoLocaleAgregatStructure((String) maList
				.get(rowIndex)[columnIndex]);
	    } else {
		return super.getValueAt(rowIndex, columnIndex);
	    }
	}
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"Group", r, myTableListModel.getListTableData(),
			GraphicEditorAdaptator.getMainFrame(),
			KMADEConstant.ABSTRACT_GROUP_REMOVE_MESSAGE_TITLE);

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
	String[] columnNames = { KMADEConstant.ABSTRACT_GROUP_NAME_TABLE,
		KMADEConstant.ABSTRACT_GROUP_DESCRIPTION_TABLE,
		KMADEConstant.ABSTRACT_GROUP_SET_TYPE_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.ABSTRACT_GROUP_NEW_OBJECT_TABLE);
	String[] enumList = AgregatStructure.getLocaleAgregatStructures();
	myListTable.setCellEditor(2, "List", enumList);
    }
}

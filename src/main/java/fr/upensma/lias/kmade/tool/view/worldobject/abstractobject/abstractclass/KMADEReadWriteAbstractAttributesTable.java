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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellEditor;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.RegularExpression;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractAttributeAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractTypeObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteAbstractAttributesTable extends JScrollPane implements LanguageFactory {
    private static final long serialVersionUID = 8934853941554435481L;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private String[][] listEnumeration = null;

    private String[] listEnumerationName = null;

    private String[][] listIntervalle = null;

    private String[] listIntervalleName = null;

    private static int typeOfStructure = 0; // 0 : aucune, 1 : enumération, 2 :
					    // intervalle.

    public KMADEReadWriteAbstractAttributesTable() {
	String[] columnNames = { KMADEConstant.ABSTRACT_ATTRIBUT_NAME_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.addAllKeyListener(new MyKeyListener());
	this.setViewportView(myListTable);
	this.setColumnHeaderView(myListTable.getPanelHeader());
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	// SpecialString puisque l'on a un popup et une v�rification de nom
	myListTable.setCellEditor(0, "SpecialString", null);
	myListTable.setCellEditor(1, "String", null);
	// myListTable.setCellEditor(2, "List",
	// TypeStructure.getNameLocaleTypeStructure());
	myListTable.setCellEditor(2, "List",
		TypeStructure.getNameLocaleTypeStructureWithoutInterval());
    }

    public void clearSelection() {
	myListTable.getListSelectionModel().clearSelection();
    }

    public void removeAllAttributes() {
	myTableListModel.removeAllListTableData();
    }

    public void setAttributeNameBorder(String name) {
	this.setBorder(new TitledBorder(null,
		KMADEConstant.ABSTRACT_ATTRIBUT_TITLE_TABLE + " " + name,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
		KMADEConstant.fontPASSIF));
    }

    public void updateDataModel(Object[][] o) {
	this.removeAllAttributes();
	for (int i = 0; i < o.length; i++) {
	    Object[] temp = new Object[6];
	    temp[0] = (String) o[i][0];
	    temp[1] = (String) o[i][1];
	    temp[2] = (String) o[i][2];

	    String[] struct = new String[2];
	    struct[0] = (String) o[i][3];
	    struct[1] = (String) o[i][4];
	    temp[3] = struct;
	    temp[4] = (String) o[i][5];
	    temp[5] = false;
	    myTableListModel.getListTableData().add(temp);
	}
	myTableListModel.fireTableDataChanged();
    }

    public void buildEnumerationList(String[][] listEnum) {
	listEnumeration = listEnum;
	listEnumerationName = getNameOfEnumOrInter(listEnumeration);
    }

    public void buildIntervalList(String[][] listInter) {
	listIntervalle = listInter;
	listIntervalleName = getNameOfEnumOrInter(listIntervalle);
    }

    private static String[] getNameOfEnumOrInter(String[][] value) {
	if (value == null) {
	    return new String[0];
	}
	String[] temp = new String[value.length];
	for (int i = 0; i < value.length; i++) {
	    temp[i] = value[i][0];
	}
	return temp;
    }

    class MyCustomListTableCellEditor extends AbstractCellEditor implements
	    TableCellEditor {
	private static final long serialVersionUID = 9101931789154227157L;

	protected JComboBox myComboBox;

	public MyCustomListTableCellEditor(String[] value) {
	    if (value == null) {
		this.myComboBox = new JComboBox();
	    } else {
		this.myComboBox = new JComboBox(value);
	    }

	    if (typeOfStructure == 2) {
		this.myComboBox
			.addItem(KMADEConstant.ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE);
		this.myComboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JComboBox) {
			    JComboBox myCombo = ((JComboBox) e.getSource());
			    if (myCombo.getSelectedIndex() == (myCombo
				    .getItemCount() - 1)) {
				AbstractTypeObjectPanelAdaptator
					.showIntervalPanel();
			    } else {
				stopCellEditing();
			    }
			}
		    }
		});
	    }

	    if (typeOfStructure == 1) {
		this.myComboBox
			.addItem(KMADEConstant.ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE);
		this.myComboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JComboBox) {
			    JComboBox myCombo = ((JComboBox) e.getSource());
			    if (myCombo.getSelectedIndex() == (myCombo
				    .getItemCount() - 1)) {
				AbstractTypeObjectPanelAdaptator
					.showEnumPanel();
			    } else {
				stopCellEditing();
			    }
			}
		    }
		});
	    }
	    this.myComboBox.addFocusListener(new FocusListener() {
		public void focusGained(FocusEvent e) {
		}

		public void focusLost(FocusEvent e) {
		    stopCellEditing();
		}
	    });
	}

	public Object getCellEditorValue() {
	    if (this.myComboBox.getSelectedIndex() == this.myComboBox
		    .getItemCount() - 1) {
		return -1;
	    } else {
		return new Integer(myComboBox.getSelectedIndex());
	    }
	}

	public Component getTableCellEditorComponent(JTable table,
		Object value, boolean isSelected, int row, int column) {
	    return myComboBox;
	}
    }

    class MyTableListModel extends DefaultListTableModel {
	private static final long serialVersionUID = 7930031534603520066L;

	public MyTableListModel(String[] columnNames, String nameDefault) {
	    super(columnNames, nameDefault);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
	    if (rowIndex == this.getRowCount() - 1) {
		// La ligne pour créer de nouvelles lignes.
		if (columnIndex == 0) {
		    return KMADEConstant.ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE;
		} else {
		    return "";
		}
	    } else {
		if (columnIndex == 2) {
		    return TypeStructure
			    .getEnumereIntoLocaleTypeStructure((String) maList
				    .get(rowIndex)[columnIndex]);
		}

		if (columnIndex == 3) {
		    // Le type : BoolValue, EnumValue, IntervalleValue,
		    // IntValue, StrValue
		    String chaineType = (String) maList.get(rowIndex)[columnIndex - 1];
		    String[] nomType = (String[]) maList.get(rowIndex)[columnIndex];
		    // Afficher ou pas les informations pour choisir
		    // l'intervalle.
		    if (chaineType.equals(TypeStructure.INTERVAL_STRUCT
			    .getValue())) {
			if (!nomType[1].equals(Oid.OID_NULL)) { //
			    return nomType[0];
			} else {
			    return (KMADEConstant.ABSTRACT_ATTRIBUT_INTERVAL_EDIT_TABLE);
			}
		    } else if (chaineType.equals(TypeStructure.ENUM_STRUCT
			    .getValue())) {
			if (!nomType[1].equals(Oid.OID_NULL)) {
			    return nomType[0];
			} else {
			    return (KMADEConstant.ABSTRACT_ATTRIBUT_ENUMERATE_EDIT_TABLE);
			}
		    } else {
			// Tous sauf Enum�ration et Intervalle.
			return "";
		    }
		}
		return maList.get(rowIndex)[columnIndex];
	    }
	}

	private String nameTest(String value) {
	    String name = value;
	    boolean needVerif = true;
	    // un nom null annule l'�dition, boucle tant que le nom n'est pas
	    // correct
	    while (name != null && needVerif) {
		// v�rification de l'expression r�guli�re avec affichage d'un
		// popUp
		if (!RegularExpression.isGoodAbstractAttributName(name)) {
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
		    if (AttributAbstrait.isUniqueName(name)) {
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
					null, AttributAbstrait
						.propositionNom(name));
		    }
		}
	    }
	    return name;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    String value = "";

	    if (rowIndex + 1 == this.getRowCount()) {
		value = (String) aValue;
		if (value.equals("")) {
		    return;
		}
		String name = nameTest(value);
		if (name == null || name.equals("")) {
		    return;
		}
		// Ajouter une nouvelle ligne.
		String newAbstractObject = AbstractAttributeAdaptator
			.addAbstractAttributes(AbstractObjectPanelAdaptator
				.getActiveAbstractObject());
		value = AbstractAttributeAdaptator.setAbstractAttributeName(
			newAbstractObject, name);
		String[] nomType = { "", Oid.OID_NULL };
		Object[] tempo = { name, "",
			TypeStructure.STRING_STRUCT.getValue(), nomType,
			newAbstractObject, false };
		myTableListModel.add(tempo, rowIndex);
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);

	    } else {
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String nameRow = (String) tempValue[0];
		String oidRow = (String) tempValue[4];

		switch (columnIndex) {
		case 0: {
		    if (nameRow.equals((String) aValue)
			    || ((String) aValue).equals("")) {
			return;
		    }
		    String name = nameTest(value);
		    if (name == null || name.equals("") || nameRow.equals(name)) {
			return;
		    }
		    value = AbstractAttributeAdaptator
			    .setAbstractAttributeName(oidRow, name);
		    break;
		}

		case 1: {
		    AbstractAttributeAdaptator.setAbstractAttributDescription(
			    oidRow, (String) aValue);
		    value = (String) aValue;
		    break;
		}

		case 2: {
		    value = TypeStructure
			    .getLocaleTypeStructureIntoEnumere((String) aValue);
		    String currentValue = (String) maList.get(rowIndex)[columnIndex];
		    if (currentValue.equals(value)) {
			return;
		    }

		    AbstractAttributeAdaptator.setAbstractAttributType(oidRow,
			    value);
		    this.fireTableCellUpdated(rowIndex, columnIndex + 1);

		    String[] nomType = { "", Oid.OID_NULL };
		    AbstractAttributeAdaptator.setAbstractAttributNameType(
			    oidRow, Oid.OID_NULL);
		    tempValue[3] = nomType;
		    break;
		}

		case 3: {
		    if (aValue instanceof Integer) {
			if ((Integer) aValue != -1) {
			    String[] nomType;
			    if (typeOfStructure == 1 && listEnumeration != null) {
				nomType = listEnumeration[(Integer) aValue];
				AbstractAttributeAdaptator
					.setAbstractAttributNameType(
						oidRow,
						listEnumeration[(Integer) aValue][1]);
			    } else if (typeOfStructure == 2
				    && listIntervalle != null) {
				nomType = listIntervalle[(Integer) aValue];
				AbstractAttributeAdaptator
					.setAbstractAttributNameType(
						oidRow,
						listIntervalle[(Integer) aValue][1]);
			    } else {
				return;
			    }
			    tempValue[3] = nomType;
			    return;
			} else {
			    return;
			}
		    } else {
			return;
		    }
		}
		}
		tempValue[columnIndex] = value;
	    }
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    if (super.isCellEditable(rowIndex, columnIndex)) {
		if (columnIndex == 3) {
		    // Afficher ou pas les informations pour choisir
		    // l'intervalle.
		    if (((String) maList.get(rowIndex)[columnIndex - 1])
			    .equals(TypeStructure.INTERVAL_STRUCT.getValue())) {
			typeOfStructure = 2;
			myListTable
				.getMyTable()
				.getColumnModel()
				.getColumn(3)
				.setCellEditor(
					new MyCustomListTableCellEditor(
						listIntervalleName));
		    } else if (((String) maList.get(rowIndex)[columnIndex - 1])
			    .equals(TypeStructure.ENUM_STRUCT.getValue())) {
			typeOfStructure = 1;
			myListTable
				.getMyTable()
				.getColumnModel()
				.getColumn(3)
				.setCellEditor(
					new MyCustomListTableCellEditor(
						listEnumerationName));
		    } else {
			typeOfStructure = 0;
			return false;
		    }
		}
		return true;
	    } else {
		return false;
	    }
	}
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"Attribut", r, myTableListModel.getListTableData(),
			GraphicEditorAdaptator.getMainFrame(),
			KMADEConstant.ABSTRACT_ATTRIBUT_REMOVE_MESSAGE_TITLE);

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
	String[] columnNames = { KMADEConstant.ABSTRACT_ATTRIBUT_NAME_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_DESCRIPTION_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_TABLE,
		KMADEConstant.ABSTRACT_ATTRIBUT_TYPE_NAME_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.ABSTRACT_ATTRIBUT_NEW_OBJECT_TABLE);
	myListTable.setCellEditor(2, "List",
		TypeStructure.getNameLocaleTypeStructureWithoutInterval());
	// .getNameLocaleTypeStructure());
    }
}

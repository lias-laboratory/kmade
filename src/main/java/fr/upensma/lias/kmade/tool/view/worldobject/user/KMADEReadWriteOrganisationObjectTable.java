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
package fr.upensma.lias.kmade.tool.view.worldobject.user;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Person;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.parserexpression.RegularExpression;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.OrganisationAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.OrganisationPanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteOrganisationObjectTable extends JScrollPane implements LanguageFactory {

    private static final long serialVersionUID = -6911578310580158697L;

    private int selectedRowCourant = -1;

    private JListTable myListTable;

    private MyTableListModel myTableListModel;

    private TitledBorder myTitledBorder;

    private final ImageIcon ERROR_ORGANIZATION_IMAGEICON;

    private final ImageIcon UNKNOWN_ORGANIZATION_IMAGEICON;

    class MyImageColumn extends AbstractCellEditor implements
	    TableCellRenderer, TableCellEditor {

	private static final long serialVersionUID = -3778437589036879639L;

	private int currentState = 0;

	private ImageIcon currentImageIcon = null;

	private String currentPath = "";

	private Object currentEditor;

	public Component getTableCellRendererComponent(JTable table,
		Object value, boolean isSelected, boolean hasFocus, int row,
		int column) {
	    JLabel myLabel;

	    if (value != null && value instanceof Object[]) {
		Object[] ref = (Object[]) value;
		currentPath = (String) ref[0];
		currentState = (Integer) ref[2];

		if (currentState == 0) {
		    currentImageIcon = null;
		    if (currentPath.equals("")) {
			currentImageIcon = UNKNOWN_ORGANIZATION_IMAGEICON;
		    } else {
			if (ref[1] != null
				&& ((ImageIcon) ref[1]).getIconWidth() != -1) {
			    currentImageIcon = (ImageIcon) ref[1];
			} else {
			    currentImageIcon = ERROR_ORGANIZATION_IMAGEICON;
			}
		    }

		    myLabel = new JLabel(currentImageIcon, JLabel.CENTER);
		} else {
		    myLabel = new JLabel(currentPath, JLabel.CENTER);
		}
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

	public Component getTableCellEditorComponent(JTable table,
		Object value, boolean isSelected, int row, int column) {
	    currentEditor = value;
	    if (value != null && value instanceof Object[]) {
		Object[] myCurrentValue = (Object[]) value;
		JComboBox myCombo = new JComboBox();
		myCombo.putClientProperty("JComboBox.isTableCellEditor",
			Boolean.TRUE);

		if (((String) myCurrentValue[0]).equals("")) {
		    // Pas d'image
		    myCombo.addItem(KMADEConstant.ORGANIZATION_CHOOSE_IMAGE_MESSAGE);
		    myCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    chooseImagePath(e);
			}
		    });
		} else {
		    ImageIcon myIcon = null;
		    if (myCurrentValue[1] != null) {
			myIcon = ((ImageIcon) myCurrentValue[1]);
		    }

		    if (myIcon == ERROR_ORGANIZATION_IMAGEICON) {
			// Image erron�e
			myCombo.addItem(KMADEConstant.ORGANIZATION_DISPLAY_DEFAULT_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_DISPLAY_BAD_PATH_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_CLEAR_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_CHOOSE_IMAGE_MESSAGE);
			myCombo.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
				JComboBox myCombo = (JComboBox) e.getSource();
				if (myCombo.getSelectedIndex() == 0) {
				    displayImage(e);
				} else if (myCombo.getSelectedIndex() == 1) {
				    displayText(e);
				} else if (myCombo.getSelectedIndex() == 2) {
				    clearImagePath(e);
				} else if (myCombo.getSelectedIndex() == 3) {
				    chooseImagePath(e);
				}
			    }
			});
		    } else {
			// Image correcte
			myCombo.addItem(KMADEConstant.ORGANIZATION_DISPLAY_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_DISPLAY_PATH_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_CLEAR_IMAGE_MESSAGE);
			myCombo.addItem(KMADEConstant.ORGANIZATION_CHOOSE_IMAGE_MESSAGE);
			myCombo.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
				JComboBox myCombo = (JComboBox) e.getSource();
				if (myCombo.getSelectedIndex() == 0) {
				    displayImage(e);
				} else if (myCombo.getSelectedIndex() == 1) {
				    displayText(e);
				} else if (myCombo.getSelectedIndex() == 2) {
				    clearImagePath(e);
				} else if (myCombo.getSelectedIndex() == 3) {
				    chooseImagePath(e);
				}
			    }
			});
		    }
		}
		return myCombo;
	    } else {
		MyImageColumn.this.cancelCellEditing();
		return null;
	    }
	}

	private void displayImage(ActionEvent e) {
	    ((Object[]) currentEditor)[2] = 0;

	    MyImageColumn.this.stopCellEditing();
	}

	private void displayText(ActionEvent e) {
	    ((Object[]) currentEditor)[2] = 1;
	    MyImageColumn.this.stopCellEditing();
	}

	private void clearImagePath(ActionEvent e) {
	    ((Object[]) currentEditor)[0] = "";
	    ((Object[]) currentEditor)[1] = UNKNOWN_ORGANIZATION_IMAGEICON;
	    ((Object[]) currentEditor)[2] = 0;
	    MyImageColumn.this.stopCellEditing();
	}

	private void chooseImagePath(ActionEvent e) {
	    JFileChooser fc = new JFileChooser();
	    int returnVal = fc
		    .showOpenDialog(KMADEReadWriteOrganisationObjectTable.this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		((Object[]) currentEditor)[0] = fc.getSelectedFile()
			.getAbsolutePath();
		ImageIcon myCurrent = null;
		Image temp = KMADEToolUtilities.getImageThumbnail(fc
			.getSelectedFile().getAbsolutePath(),
			KMADEConstant.ROW_HEIGHT);

		if (temp == null) {
		    myCurrent = ERROR_ORGANIZATION_IMAGEICON;
		} else {
		    myCurrent = new ImageIcon(temp);
		}

		((Object[]) currentEditor)[1] = myCurrent;
		((Object[]) currentEditor)[2] = 0;
		MyImageColumn.this.stopCellEditing();
	    } else {
		MyImageColumn.this.cancelCellEditing();
	    }
	}

	public Object getCellEditorValue() {
	    return currentEditor;
	}
    }

    public KMADEReadWriteOrganisationObjectTable() {
	String[] columnNames = { KMADEConstant.ORGANIZATION_NAME_TABLE,
		KMADEConstant.ORGANIZATION_STATUS_TABLE,
		KMADEConstant.ORGANIZATION_ROLE_TABLE,
		KMADEConstant.ORGANIZATION_PHOTO_TABLE,
		KMADEConstant.ORGANIZATION_MEMBERS_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.ORGANIZATION_NEW_ROW_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.addAllKeyListener(new MyKeyListener());
	this.setViewportView(myListTable);
	this.setColumnHeaderView(myListTable.getPanelHeader());
	myListTable.setCellEditor(0, "SpecialString", null);
	this.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	myTitledBorder = new TitledBorder(null,
		KMADEConstant.ORGANIZATION_TITLE_NAME, TitledBorder.CENTER,
		TitledBorder.TOP);
	this.setBorder(myTitledBorder);

	MyImageColumn toto = new MyImageColumn();
	myListTable.getMyTable().getColumnModel().getColumn(3)
		.setCellRenderer(toto);
	myListTable.getMyTable().getColumnModel().getColumn(3)
		.setCellEditor(toto);
	myListTable.getMyTable().getColumnModel().getColumn(4)
		.setCellEditor(new TableCellEditor() {

		    public boolean stopCellEditing() {
			// TODO Auto-generated method stub
			return false;
		    }

		    public boolean shouldSelectCell(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		    }

		    public void removeCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub

		    }

		    public boolean isCellEditable(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		    }

		    public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		    }

		    public void cancelCellEditing() {
			// TODO Auto-generated method stub

		    }

		    public void addCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub

		    }

		    public Component getTableCellEditorComponent(JTable table,
			    Object value, boolean isSelected, int row,
			    int column) {
			// TODO Auto-generated method stub
			return null;
		    }
		});

	ERROR_ORGANIZATION_IMAGEICON = new ImageIcon(
		KMADEToolUtilities.getImageThumbnail(
			GraphicEditorAdaptator.class
				.getResource(KMADEConstant.ORGANIZATION_ERROR_IMAGE),
			KMADEConstant.ROW_HEIGHT));
	UNKNOWN_ORGANIZATION_IMAGEICON = new ImageIcon(
		KMADEToolUtilities.getImageThumbnail(
			GraphicEditorAdaptator.class
				.getResource(KMADEConstant.ORGANIZATION_UNKNOWN_IMAGE),
			KMADEConstant.ROW_HEIGHT));

	// Abonnement aux diff�rents �couteurs.
	myListTable.getListSelectionModel().addListSelectionListener(
		new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent e) {
			if (myListTable.getListSelectionModel()
				.isSelectionEmpty()) {
			    selectedRowCourant = -1;
			    OrganisationPanelAdaptator.setActiveOrganisation(
				    "", Oid.OID_NULL);
			} else {
			    int selectedRow = myListTable
				    .getListSelectionModel()
				    .getMinSelectionIndex();
			    if (selectedRow != myListTable
				    .getListSelectionModel()
				    .getMaxSelectionIndex()) {
				OrganisationPanelAdaptator
					.setActiveOrganisation("", Oid.OID_NULL);
				return;
			    }

			    if (selectedRow != selectedRowCourant) {
				if (selectedRow != myTableListModel
					.getRowCount() - 1) {
				    OrganisationPanelAdaptator.setActiveOrganisation(
					    ((String) myTableListModel
						    .getValueAt(selectedRow, 0)),
					    ((String) myTableListModel
						    .getValueAt(selectedRow, 5)));
				    selectedRowCourant = selectedRow;
				} else {
				    selectedRowCourant = -1;
				    OrganisationPanelAdaptator
					    .setActiveOrganisation("",
						    Oid.OID_NULL);
				}
			    }
			}
		    }
		});

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
		if (!RegularExpression.isGoodOrganisationObjectName(name)) {
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
		    if (Organization.isUniqueName(name)) {
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
					null, Organization.propositionNom(name));
		    }
		}
	    }
	    return name;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if (aValue == null)
		return;

	    if (rowIndex + 1 == this.getRowCount()) {
		String value = (String) aValue;
		if (value.equals("")) {
		    return;
		}
		String name = nameTest(value);
		if (name == null || name.equals("")) {
		    return;
		}
		value = name;
		// Ajouter une nouvelle ligne.
		String newOrganisationObject = OrganisationAdaptator
			.addOrganisation();
		value = OrganisationAdaptator.setOrganisationName(
			newOrganisationObject, value);
		Object[] couple = { "", UNKNOWN_ORGANIZATION_IMAGEICON, 0 }; // Chemin,
									     // image
									     // et
									     // etat.
		Organization o = (Organization) InterfaceExpressJava
			.prendre(new Oid(newOrganisationObject));
		ArrayList<Person> ind = o.getMembers();

		// nom ,statut, Role, image , Organisation , oid , Boolean
		Object[] tempo = { value, "", "", couple, ind,
			newOrganisationObject, false };
		myTableListModel.getListTableData().add(tempo);
		this.fireTableRowsInserted(rowIndex, rowIndex + 1);
		myTableListModel.fireListRowsInserted();
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);
	    } else {
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String nameRow = (String) tempValue[0];
		String oidRow = (String) tempValue[5];

		switch (columnIndex) {
		case 0: {
		    String value = (String) aValue;
		    if (value.equals("") || nameRow.equals(value)) {
			return;
		    }
		    String name = nameTest(value);
		    if (name == null || name.equals("")) {
			return;
		    }
		    value = name;
		    aValue = OrganisationAdaptator.setOrganisationName(oidRow,
			    value);
		    break;
		}
		case 1: {
		    OrganisationAdaptator.setOrganisationStatut(oidRow,
			    (String) aValue);
		    break;
		}
		case 2: {
		    OrganisationAdaptator.setOrganisationRole(oidRow,
			    (String) aValue);
		    break;
		}
		case 3: {
		    OrganisationAdaptator.setOrganisationImage(oidRow,
			    (String) ((Object[]) aValue)[0]);
		    tempValue[columnIndex] = aValue;
		    return;
		}
		}
		tempValue[columnIndex] = (String) aValue;
	    }
	}
    }

    public void removeAllOrganization() {
	myTableListModel.removeAllListTableData();
    }

    // (Utilis� lors du chargement d'un fichier KMADe.)
    public void addOrganization(String name, String statut, String role,
	    String image, ArrayList<Person> org, String oid) {
	ImageIcon myCurrent;
	if (image == null || image.equals("")) {
	    myCurrent = UNKNOWN_ORGANIZATION_IMAGEICON;
	} else {
	    Image temp = KMADEToolUtilities.getImageThumbnail(image,
		    KMADEConstant.ROW_HEIGHT);

	    if (temp == null) {
		myCurrent = ERROR_ORGANIZATION_IMAGEICON;
	    } else {
		myCurrent = new ImageIcon(temp);
	    }
	}
	Object[] couple = { image == null ? "" : image, myCurrent, 0 };
	Object[] tempo = { name, statut, role, couple, org, oid, false };
	myTableListModel.getListTableData().add(tempo);
	myTableListModel.fireTableRowsInserted(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
	myTableListModel.fireListRowsInserted();
	myListTable.getListSelectionModel().setSelectionInterval(
		myTableListModel.getRowCount() - 1,
		myTableListModel.getRowCount() - 1);
    }

    public DefaultListTableModel getModel() {
	return myTableListModel;
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"Organisation", r, myTableListModel.getListTableData(),
			GraphicEditorAdaptator.getMainFrame(),
			KMADEConstant.ORGANIZATION_REMOVE_NAME_TITLE);

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
	String[] columnNames = { KMADEConstant.ORGANIZATION_NAME_TABLE,
		KMADEConstant.ORGANIZATION_STATUS_TABLE,
		KMADEConstant.ORGANIZATION_ROLE_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.ORGANIZATION_NEW_ROW_TABLE);
	myTitledBorder.setTitle(KMADEConstant.ORGANIZATION_TITLE_NAME);
    }

    public void setOrganisationNameBorder(String name) {
	this.setBorder(new TitledBorder(null,
		KMADEConstant.ORGANIZATION_TITLE_NAME + " : " + name,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));

    }
}

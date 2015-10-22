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
package fr.upensma.lias.kmade.tool.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.ExpressDB;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultListTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JListTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.ProjectManagerAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEProjectDialog extends JDialog implements LanguageFactory {

    private static final long serialVersionUID = 4967769190573861949L;

    private JButton okButton = new JButton(KMADEConstant.VALID_MESSAGE);

    private JButton cancelButton = new JButton(KMADEConstant.CANCEL_MESSAGE);

    private JTextField entreprise = new JTextField("");

    private JTextField site = new JTextField("");

    private JTextField typeDePoste = new JTextField("");

    private JTextField dateCas = new JTextField("");

    private JTextField otherResource = new JTextField("");

    private JListTable myListTable;

    private JTextArea myJustification;

    private MyTableListModel myTableListModel;

    private TitledBorder myTitledBorder;

    private TitledBorder myTitledBorderInterviewed;

    private TitledBorder myTitledBorderJustification;

    private JLabel myCompanyLabel;

    private JLabel mySiteLabel;

    private JLabel myPostLabel;

    private JLabel myDateStudyLabel;

    private JLabel myOtherResourcesLabel;

    public KMADEProjectDialog(Frame owner) {
	super(owner, KMADEConstant.PROJECT_DIALOG_TITLE_NAME);

	JPanel panelCenter = new JPanel(new BorderLayout(10, 10));
	JPanel panelDescription = new JPanel(new BorderLayout());
	JPanel panelIntDescription = new JPanel(new GridLayout(1, 2, 30, 10));
	panelDescription.add(BorderLayout.CENTER, panelIntDescription);
	panelIntDescription.setBorder(BorderFactory.createEmptyBorder(10, 10,
		10, 10));
	myTitledBorder = BorderFactory
		.createTitledBorder(KMADEConstant.GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE
			+ " :");
	panelDescription.setBorder(myTitledBorder);
	JPanel paneldescgauche = new JPanel(new BorderLayout(10, 10));

	JPanel panellabel = new JPanel(new GridLayout(3, 1, 10, 10));
	myCompanyLabel = new JLabel(KMADEConstant.COMPANY_LABEL_NAME + " :");
	mySiteLabel = new JLabel(KMADEConstant.SITE_LABEL_NAME + " :");
	myPostLabel = new JLabel(KMADEConstant.POSTE_TYPE_LABEL_NAME + " :");
	panellabel.add(myCompanyLabel);
	panellabel.add(mySiteLabel);
	panellabel.add(myPostLabel);
	JPanel paneltext = new JPanel(new GridLayout(3, 1, 10, 10));
	paneltext.add(entreprise);
	paneltext.add(site);
	paneltext.add(typeDePoste);

	paneldescgauche.add(BorderLayout.WEST, panellabel);
	paneldescgauche.add(BorderLayout.CENTER, paneltext);
	panelIntDescription.add(paneldescgauche);

	JPanel paneldescdroite = new JPanel(new BorderLayout(10, 10));
	JPanel paneldlabel = new JPanel(new GridLayout(3, 1, 10, 10));
	myDateStudyLabel = new JLabel(KMADEConstant.DATE_STUDY + " :");
	myOtherResourcesLabel = new JLabel(KMADEConstant.OTHER_RESOURCES + " :");
	paneldlabel.add(myDateStudyLabel);
	paneldlabel.add(myOtherResourcesLabel);
	paneldlabel.add(new JPanel());
	JPanel paneldtext = new JPanel(new GridLayout(3, 1, 10, 10));

	paneldtext.add(dateCas);
	paneldtext.add(otherResource);
	paneldtext.add(new JPanel());

	paneldescdroite.add(BorderLayout.WEST, paneldlabel);
	paneldescdroite.add(BorderLayout.CENTER, paneldtext);
	panelIntDescription.add(paneldescdroite);

	// Cr�ation de la partie du centre.

	// Cr�ation de la JTable avec ses mod�les.
	String[] columnNames = { KMADEConstant.INTERVIEWED_NAME_TABLE,
		KMADEConstant.PLACE_INFORMATION_TABLE,
		KMADEConstant.STATUT_TABLE, KMADEConstant.SENIORITY_TABLE,
		KMADEConstant.DATE_TABLE, KMADEConstant.INTERVIEW_TYPE_TABLE,
		KMADEConstant.SEARCHED_INFORMATIONS_TABLE,
		KMADEConstant.INTERVIEW_NAME_TABLE };
	myTableListModel = new MyTableListModel(columnNames,
		KMADEConstant.INTERVIEW_NEW_OBJECT_TABLE);
	myListTable = new JListTable(myTableListModel,
		myTableListModel.getListModel());
	myListTable.getMyTable().setRowHeight(KMADEConstant.ROW_HEIGHT);
	myListTable.setCellEditor(0, "String", null);
	myListTable.setCellEditor(1, "String", null);
	myListTable.setCellEditor(2, "String", null);
	myListTable.setCellEditor(3, "String", null);
	myListTable.setCellEditor(4, "String", null);
	myListTable.setCellEditor(5, "String", null);
	myListTable.setCellEditor(6, "String", null);
	myListTable.setCellEditor(7, "String", null);
	myListTable.addAllKeyListener(new MyKeyListener());
	JScrollPane panelPersonne = new JScrollPane(myListTable);
	panelPersonne.setViewportView(myListTable);
	panelPersonne.setColumnHeaderView(myListTable.getPanelHeader());
	panelPersonne.setOpaque(true);
	panelPersonne.getViewport().setBackground(KMADEConstant.ACTIVE_PANE);
	myTitledBorderInterviewed = BorderFactory
		.createTitledBorder(KMADEConstant.INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE
			+ " :");
	panelPersonne.setBorder(myTitledBorderInterviewed);

	// Cr�ation de la partie du sud.
	myJustification = new JTextArea();
	JScrollPane panelJustification = new JScrollPane(myJustification);
	panelJustification.setPreferredSize(new Dimension(100, 100));
	myTitledBorderJustification = BorderFactory
		.createTitledBorder(KMADEConstant.JUSTIFICATION_PROJECT_DIALOG_TITLE
			+ " :");
	panelJustification.setBorder(myTitledBorderJustification);

	panelCenter.add(BorderLayout.NORTH, panelDescription);
	panelCenter.add(BorderLayout.CENTER, panelPersonne);
	panelCenter.add(BorderLayout.SOUTH, panelJustification);

	JPanel panelSouth = new JPanel();
	panelSouth.add(okButton);
	panelSouth.add(cancelButton);

	okButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String[] temp = new String[6];
		temp[0] = entreprise.getText();
		temp[1] = site.getText();
		temp[2] = typeDePoste.getText();
		temp[3] = dateCas.getText();
		temp[4] = otherResource.getText();
		temp[5] = myJustification.getText();
		ProjectManagerAdaptator.confirmModification(temp,
			myTableListModel.getListTableData());
	    }
	});

	cancelButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ProjectManagerAdaptator.cancelModification();
	    }
	});

	this.getContentPane().setLayout(new BorderLayout());
	this.getContentPane().add(BorderLayout.CENTER, panelCenter);
	this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
	this.setPreferredSize(new Dimension(900, 600));
	this.pack();

	KMADEToolUtilities.setCenteredInScreen(this);
	this.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		ProjectManagerAdaptator.cancelModification();
	    }
	});
    }

    public void showToModifyProjectData(String[] data,
	    ArrayList<Object[]> interview) {
	// Partie informations g�n�rales.
	entreprise.setText(data[0]);
	site.setText(data[1]);
	typeDePoste.setText(data[2]);
	dateCas.setText(data[3]);
	otherResource.setText(data[4]);
	myJustification.setText(data[5]);

	// Partie interview.
	myTableListModel.removeAllListTableData();
	for (int i = 0; i < interview.size(); i++) {
	    Object[] temp = new Object[interview.get(i).length + 1];
	    for (int j = 0; j < interview.get(i).length; j++) {
		temp[j] = (String) interview.get(i)[j];
	    }
	    temp[8] = false;
	    myTableListModel.getListTableData().add(temp);
	}

	myTableListModel.fireTableDataChanged();
	this.setVisible(true);
    }

    public void showToCreateProjectData() {
	this.setVisible(true);
	String[] temp = { "", "", "", "", "", "" };
	this.showToModifyProjectData(temp, new ArrayList<Object[]>());
    }

    public void closeProjectDialog() {
	/* AG */
	ExpressDB n;

	n = (ExpressDB) InterfaceExpressJava.bdd.clone();
	InterfaceExpressJava.toUndo.push(n);
	/**/
	this.setModal(false);
	this.setVisible(false);
    }

    class MyTableListModel extends DefaultListTableModel {
	private static final long serialVersionUID = 7930031534603520066L;

	public MyTableListModel(String[] columnNames, String nameDefault) {
	    super(columnNames, nameDefault);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    String value = (String) aValue;

	    if (value.equals("")) {
		return;
	    }
	    if (rowIndex + 1 == this.getRowCount()) {
		Object[] tempo = { value, "", "", "", "", "", "", "", false };
		myTableListModel.getListTableData().add(tempo);
		this.fireTableRowsInserted(rowIndex, rowIndex + 1);
		myTableListModel.fireListRowsInserted();
		myListTable.getListSelectionModel().setSelectionInterval(
			rowIndex, rowIndex);
	    } else {
		Object[] tempValue = (Object[]) myTableListModel
			.getListTableData().get(rowIndex);
		String nameRow = (String) tempValue[0];

		if (nameRow.equals(value)) {
		    return;
		} else {
		    tempValue[columnIndex] = value;
		}
	    }
	}
    }

    class MyKeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent ke) {
	    if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
		int[] r = myListTable.getMyTable().getSelectedRows();
		boolean state = GraphicEditorAdaptator.removeSelectedItem(
			"Project", r, myTableListModel.getListTableData(),
			KMADEProjectDialog.this,
			KMADEConstant.REMOVE_INTERVIEW_MESSAGE_TITLE);

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
	this.setTitle(KMADEConstant.PROJECT_DIALOG_TITLE_NAME);
	okButton.setText(KMADEConstant.VALID_MESSAGE);
	cancelButton.setText(KMADEConstant.CANCEL_MESSAGE);
	String[] columnNames = { KMADEConstant.INTERVIEWED_NAME_TABLE,
		KMADEConstant.PLACE_INFORMATION_TABLE,
		KMADEConstant.STATUT_TABLE, KMADEConstant.SENIORITY_TABLE,
		KMADEConstant.DATE_TABLE, KMADEConstant.INTERVIEW_TYPE_TABLE,
		KMADEConstant.SEARCHED_INFORMATIONS_TABLE,
		KMADEConstant.INTERVIEW_NAME_TABLE };
	myTableListModel.setColumnNames(columnNames);
	myTableListModel
		.setNameDefault(KMADEConstant.INTERVIEW_NEW_OBJECT_TABLE);
	myTitledBorder
		.setTitle(KMADEConstant.GENERAL_DESCRIPTION_PROJECT_DIALOG_TITLE
			+ " :");
	myCompanyLabel.setText(KMADEConstant.COMPANY_LABEL_NAME + " :");
	mySiteLabel.setText(KMADEConstant.SITE_LABEL_NAME + " :");
	myPostLabel.setText(KMADEConstant.POSTE_TYPE_LABEL_NAME + " :");
	myDateStudyLabel.setText(KMADEConstant.DATE_STUDY + " :");
	myOtherResourcesLabel.setText(KMADEConstant.OTHER_RESOURCES + " :");
	myTitledBorderInterviewed
		.setTitle(KMADEConstant.INTERVIEWED_DESCRIPTION_PROJECT_DIALOG_TITLE
			+ " :");
	myTitledBorderJustification
		.setTitle(KMADEConstant.JUSTIFICATION_PROJECT_DIALOG_TITLE
			+ " :");
    }
}

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
package fr.upensma.lias.kmade.tool.view.worldobject.usersystem;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressParcMachines;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.ParcMachinesPanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteParcMachinesPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 16531L;

    private final KMADEReadWriteParcMachinesTable parcs = new KMADEReadWriteParcMachinesTable();

    private final KMADEReadMachineInParc machineAdd = new KMADEReadMachineInParc();

    private final KMADEReadMachineNotInParc machineNotAdd = new KMADEReadMachineNotInParc();

    private String oidActiveParc = Oid.OID_NULL;

    private String nameActiveParc = null;

    public KMADEReadWriteParcMachinesPanel() {
	// cr�ation de la table comprenant les 3 Jtable que l'on souhaite
	JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
	panelHaut.add(parcs);
	JPanel panelBas = new JPanel(new GridLayout(1, 2, 2, 2));
	panelBas.add(machineAdd);
	panelBas.add(machineNotAdd);
	this.setLayout(new GridLayout(2, 1, 2, 2));
	this.add(panelHaut);
	this.add(panelBas);
	// il n'y a pas d'individu selectionn�
	this.setActiveParcObject("", Oid.OID_NULL);
	this.setBorder(new TitledBorder(null,
		KMADEConstant.PARCMACHINES_TITLE_NAME, TitledBorder.CENTER,
		TitledBorder.DEFAULT_POSITION));
    }

    /**
     * Met � jour les tables des parcs
     */
    public void refreshActiveParcMachines() {
	setActiveParcObject(nameActiveParc, oidActiveParc);
    }

    /**
     * Affiche les tables des machines du parc selectionn�
     * 
     * @param name
     * @param oid
     */
    public void setActiveParcObject(String name, String oid) {
	machineAdd.clearSelection();
	machineNotAdd.clearSelection();
	oidActiveParc = oid;
	nameActiveParc = name;
	if (oid.equals(Oid.OID_NULL)) {
	    // cas o� il n'y a rien de selectionn�
	    machineAdd.setVisible(false);
	    machineNotAdd.setVisible(false);
	    parcs.setParcMachinesNameBorder("");
	} else {
	    // un individu est selectionn�
	    // affichage du titre avec le nom de l'individu
	    parcs.setParcMachinesNameBorder(name);

	    // La partie indiAdd
	    // Supression de la table indiAdd des informations contenu dedans (
	    // pas dans la bdd Express)
	    machineAdd.removeAllMachine();
	    // Modification du nom de la table
	    machineAdd.setMachineMemberNameBorder(name);
	    // mise � jour des valeurs de la table
	    Object[][] tabindiAdd = ParcMachinesPanelAdaptator
		    .getMachineAddIntoTab(oid);
	    if (tabindiAdd.length != 0) {
		machineAdd.updateDataModel(tabindiAdd);
	    }
	    // affichage de la table
	    machineAdd.setVisible(true);

	    // La partie indiNotAdd
	    // comme pour indiAdd
	    machineNotAdd.removeAllMachine();
	    machineNotAdd.setMachineMemberNameBorder(name);
	    Object[][] tabindiNotAdd = ParcMachinesPanelAdaptator
		    .getMachineNotAddIntoTab(oid);
	    if (tabindiNotAdd.length != 0) {
		machineNotAdd.updateDataModel(tabindiNotAdd);
	    }
	    machineNotAdd.setVisible(true);
	}
    }

    public void addMachineInParcMachines(String oidMachine) {
	try {

	    ExpressParcMachines
		    .addParcMachinesMember(oidActiveParc, oidMachine);
	} catch (Exception e) {

	}
    }

    public void removeMachineOfParc(String oidMachine) {
	try {

	    ExpressParcMachines.removeMachineOfParc(oidActiveParc, oidMachine);
	} catch (Exception e) {

	}
    }

    public KMADEReadMachineInParc getMachineAddTable() {
	return machineAdd;
    }

    public KMADEReadMachineNotInParc getMachineNotAddTable() {
	return machineNotAdd;
    }

    public KMADEReadWriteParcMachinesTable getParcMachinesObjectTable() {
	return parcs;
    }

    public void notifLocalisationModification() {
	parcs.notifLocalisationModification();
	machineAdd.notifLocalisationModification();
	machineNotAdd.notifLocalisationModification();
    }
}

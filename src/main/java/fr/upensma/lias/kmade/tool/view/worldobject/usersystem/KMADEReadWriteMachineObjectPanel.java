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
package fr.upensma.lias.kmade.tool.view.worldobject.usersystem;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressMachine;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.MachinePanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteMachineObjectPanel extends JPanel implements LanguageFactory {

	private static final long serialVersionUID = -2220265715254322307L;

	private final KMADEReadWriteMachineObjectTable machine = new KMADEReadWriteMachineObjectTable();

	private final KMADEReadParcInMachine parcAdd = new KMADEReadParcInMachine();

	private final KMADEReadParcNotInMachine parcNotAdd = new KMADEReadParcNotInMachine();

	private String oidActiveMachine;

	private String nameActiveMachine;

	public KMADEReadWriteMachineObjectPanel() {
		// cr�ation de la table comprenant les 3 Jtable que l'on souhaite
		JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
		panelHaut.add(machine);
		JPanel panelBas = new JPanel(new GridLayout(1, 2, 2, 2));
		panelBas.add(parcAdd);
		panelBas.add(parcNotAdd);
		this.setLayout(new GridLayout(2, 1, 2, 2));
		this.add(panelHaut);
		this.add(panelBas);
		// il n'y a pas d'individu selectionn�
		this.setActiveMachineObject("", Oid.OID_NULL);
		this.setBorder(new TitledBorder(null, KMADEConstant.MACHINE_TITLE_NAME, TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
	}

	/**
	 * Met � jour les tables des machines
	 */
	public void refreshActiveMachine() {
		setActiveMachineObject(nameActiveMachine, oidActiveMachine);
		// force le refresh de la table machine
	}

	/**
	 * Affiche les tables des parcs de la machine selectionnée.
	 * 
	 * @param name
	 * @param oid
	 */
	public void setActiveMachineObject(String name, String oid) {
		parcAdd.clearSelection();
		parcNotAdd.clearSelection();
		oidActiveMachine = oid;
		nameActiveMachine = name;
		if (oid.equals(Oid.OID_NULL)) {
			// cas où il n'y a rien de selectionné
			parcAdd.setVisible(false);
			parcNotAdd.setVisible(false);
			machine.setMachineNameBorder("");
		} else {
			// une machine est selectionnée
			// affichage du titre avec le nom de la machine
			machine.setMachineNameBorder(name);

			// La partie parcAdd
			// Supression de la table orgaAdd des informations contenu dedans (
			// pas dans la bdd Express)
			parcAdd.removeAllParc();
			// Modification du nom de la table
			parcAdd.setParcMachinesNameBorder(name);

			// mise � jour des valeurs de la table
			Object[][] tabOrgaAdd = MachinePanelAdaptator.getParcsIntoTab(oid);
			if (tabOrgaAdd.length != 0) {
				parcAdd.updateDataModel(tabOrgaAdd);
			}
			// affichage de la table
			parcAdd.setVisible(true);

			// La partie parcNotAdd
			// comme pour parcAdd
			parcNotAdd.removeAllParc();
			parcNotAdd.setParcMachinesNameBorder(name);

			Object[][] tabOrgaNotAdd = MachinePanelAdaptator.getOtherParcsIntoTab(oid);
			if (tabOrgaNotAdd.length != 0) {
				parcNotAdd.updateDataModel(tabOrgaNotAdd);
			}
			parcNotAdd.setVisible(true);
		}
	}

	public void addParcToActiveMachine(String oidParc) {
		try {

			ExpressMachine.addMachineInParcMachines(oidActiveMachine, oidParc);
		} catch (Exception e) {

		}
	}

	public void removeParcMachinesToActiveMachine(String oidParcMachines) {
		try {

			ExpressMachine.removeMachineInParcMachines(oidActiveMachine, oidParcMachines);
		} catch (Exception e) {

		}
	}

	public KMADEReadParcInMachine getParcMachinesAddTable() {
		return parcAdd;
	}

	public KMADEReadParcNotInMachine getParcMachinesNotAddTable() {
		return parcNotAdd;
	}

	public KMADEReadWriteMachineObjectTable getMachineObjectTable() {
		return machine;
	}

	public void notifLocalisationModification() {
		machine.notifLocalisationModification();
		parcAdd.notifLocalisationModification();
		parcNotAdd.notifLocalisationModification();
	}
}

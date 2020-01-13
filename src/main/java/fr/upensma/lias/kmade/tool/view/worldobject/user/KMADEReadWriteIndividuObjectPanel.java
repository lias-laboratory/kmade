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
package fr.upensma.lias.kmade.tool.view.worldobject.user;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividu;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.IndividuPanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteIndividuObjectPanel extends JPanel implements LanguageFactory {

	private static final long serialVersionUID = 16531L;

	private final KMADEReadWriteIndividuObjectTable individu = new KMADEReadWriteIndividuObjectTable();

	private final KMADEReadOrganisationOfIndividu orgaAdd = new KMADEReadOrganisationOfIndividu();

	private final KMADEReadOrganisationNotOfIndividu orgaNotAdd = new KMADEReadOrganisationNotOfIndividu();

	private String oidActiveIndividu;

	private String nameActiveIndividu;

	public KMADEReadWriteIndividuObjectPanel() {
		// cr�ation de la table comprenant les 3 Jtable que l'on souhaite
		JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
		panelHaut.add(individu);
		JPanel panelBas = new JPanel(new GridLayout(1, 2, 2, 2));
		panelBas.add(orgaAdd);
		panelBas.add(orgaNotAdd);
		this.setLayout(new GridLayout(2, 1, 2, 2));
		this.add(panelHaut);
		this.add(panelBas);
		// il n'y a pas d'individu selectionn�
		this.setActiveIndividuObject("", Oid.OID_NULL);
		this.setBorder(new TitledBorder(null, KMADEConstant.INDIVIDU_TITLE_NAME, TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
	}

	/**
	 * Met � jour les tables des organisations
	 */
	public void refreshActiveIndividu() {
		setActiveIndividuObject(nameActiveIndividu, oidActiveIndividu);
		// force le refresh de la table individu
	}

	/**
	 * Affiche les tables des organisations de l'individu selectionn�.
	 * 
	 * @param name
	 * @param oid
	 */
	public void setActiveIndividuObject(String name, String oid) {
		orgaAdd.clearSelection();
		orgaNotAdd.clearSelection();
		oidActiveIndividu = oid;
		nameActiveIndividu = name;
		if (oid.equals(Oid.OID_NULL)) {
			// cas o� il n'y a rien de selectionn�
			orgaAdd.setVisible(false);
			orgaNotAdd.setVisible(false);
			individu.setIndividuNameBorder("");
		} else {
			// un individu est selectionn�
			// affichage du titre avec le nom de l'individu
			individu.setIndividuNameBorder(name);

			// La partie orgaAdd
			// Supression de la table orgaAdd des informations contenu dedans (
			// pas dans la bdd Express)
			orgaAdd.removeAllOrga();
			// Modification du nom de la table
			orgaAdd.setOrganizationAddNameBorder(name);

			// mise � jour des valeurs de la table
			Object[][] tabOrgaAdd = IndividuPanelAdaptator.getOrganizationIntoTab(oid);
			if (tabOrgaAdd.length != 0) {
				orgaAdd.updateDataModel(tabOrgaAdd);
			}
			// affichage de la table
			orgaAdd.setVisible(true);
			// La partie orgaNotAdd
			// comme pour orgaAdd
			orgaNotAdd.removeAllOrga();
			orgaNotAdd.setOrganizationNameBorder(name);

			Object[][] tabOrgaNotAdd = IndividuPanelAdaptator.getOtherOrganizationIntoTab(oid);
			if (tabOrgaNotAdd.length != 0) {
				orgaNotAdd.updateDataModel(tabOrgaNotAdd);
			}
			orgaNotAdd.setVisible(true);
		}
	}

	public void addOrganizationToActiveIndividu(String oidOrganization) {
		try {

			ExpressIndividu.addIndividuInOrganisation(oidActiveIndividu, oidOrganization);
		} catch (Exception e) {

		}
	}

	public void removeOrganizationToActiveIndividu(String oidOrganization) {
		try {

			ExpressIndividu.removeIndividuInOrganisation(oidActiveIndividu, oidOrganization);
		} catch (Exception e) {

		}
	}

	public KMADEReadOrganisationOfIndividu getOrganizationAddTable() {
		return orgaAdd;
	}

	public KMADEReadOrganisationNotOfIndividu getOrganizationNotAddTable() {
		return orgaNotAdd;
	}

	public KMADEReadWriteIndividuObjectTable getIndividuObjectTable() {
		return individu;
	}

	public void notifLocalisationModification() {
		individu.notifLocalisationModification();
		orgaAdd.notifLocalisationModification();
		orgaNotAdd.notifLocalisationModification();
	}
}

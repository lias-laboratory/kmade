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
import fr.upensma.lias.kmade.tool.viewadaptator.OrganisationPanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteOrganisationObjectPanel extends JPanel implements LanguageFactory {

	private static final long serialVersionUID = 16531L;

	private final KMADEReadWriteOrganisationObjectTable organisation = new KMADEReadWriteOrganisationObjectTable();

	private final KMADEReadIndividuInOrganisation indiAdd = new KMADEReadIndividuInOrganisation();

	private final KMADEReadIndividuNotInOrganisation indiNotAdd = new KMADEReadIndividuNotInOrganisation();

	private String oidActiveOrganisation = Oid.OID_NULL;

	private String nameActiveOrganisation = null;

	public KMADEReadWriteOrganisationObjectPanel() {
		// cr�ation de la table comprenant les 3 Jtable que l'on souhaite
		JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
		panelHaut.add(organisation);
		JPanel panelBas = new JPanel(new GridLayout(1, 2, 2, 2));
		panelBas.add(indiAdd);
		panelBas.add(indiNotAdd);
		this.setLayout(new GridLayout(2, 1, 2, 2));
		this.add(panelHaut);
		this.add(panelBas);
		// il n'y a pas d'individu selectionn�
		this.setActiveOrganisationObject("", Oid.OID_NULL);
		this.setBorder(new TitledBorder(null, KMADEConstant.ORGANIZATION_TITLE_NAME, TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
	}

	/**
	 * Met � jour les tables des organisations
	 */
	public void refreshActiveOrganisation() {
		setActiveOrganisationObject(nameActiveOrganisation, oidActiveOrganisation);

	}

	/**
	 * Affiche les tables des individus de l'organisation selectionn�e.
	 * 
	 * @param name
	 * @param oid
	 */
	public void setActiveOrganisationObject(String name, String oid) {
		indiAdd.clearSelection();
		indiNotAdd.clearSelection();
		oidActiveOrganisation = oid;
		nameActiveOrganisation = name;
		if (oid.equals(Oid.OID_NULL)) {
			// cas o� il n'y a rien de selectionn�
			indiAdd.setVisible(false);
			indiNotAdd.setVisible(false);
			organisation.setOrganisationNameBorder("");
		} else {
			// un individu est selectionn�
			// affichage du titre avec le nom de l'individu
			organisation.setOrganisationNameBorder(name);

			// La partie indiAdd
			// Supression de la table indiAdd des informations contenu dedans (
			// pas dans la bdd Express)
			indiAdd.removeAllIndividu();
			// Modification du nom de la table
			indiAdd.setIndividuAddNameBorder(name);

			// mise � jour des valeurs de la table
			Object[][] tabindiAdd = OrganisationPanelAdaptator.getIndividuAddIntoTab(oid);
			if (tabindiAdd.length != 0) {
				indiAdd.updateDataModel(tabindiAdd);
			}
			// affichage de la table
			indiAdd.setVisible(true);

			// La partie indiNotAdd
			// comme pour indiAdd
			indiNotAdd.removeAllIndividu();
			indiNotAdd.setIndividuNotInOrganisationNameBorder(name);

			Object[][] tabindiNotAdd = OrganisationPanelAdaptator.getIndividuNotAddIntoTab(oid);
			if (tabindiNotAdd.length != 0) {
				indiNotAdd.updateDataModel(tabindiNotAdd);
			}
			indiNotAdd.setVisible(true);
		}
	}

	public void addIndividuInOrganization(String oidIndividu) {
		try {

			ExpressIndividu.addIndividuInOrganisation(oidIndividu, oidActiveOrganisation);
		} catch (Exception e) {

		}
	}

	public void removeIndividuOfOrganization(String oidIndividu) {
		try {

			ExpressIndividu.removeIndividuInOrganisation(oidIndividu, oidActiveOrganisation);
		} catch (Exception e) {

		}
	}

	public KMADEReadIndividuInOrganisation getIndividuAddTable() {
		return indiAdd;
	}

	public KMADEReadIndividuNotInOrganisation getIndividuNotAddTable() {
		return indiNotAdd;
	}

	public KMADEReadWriteOrganisationObjectTable getOrganizationObjectTable() {
		return organisation;
	}

	public void notifLocalisationModification() {
		organisation.notifLocalisationModification();
		indiAdd.notifLocalisationModification();
		indiNotAdd.notifLocalisationModification();
	}
}

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
package fr.upensma.lias.kmade.tool.coreadaptator;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.tache.Organization;

/**
 * @author Mickael BARON
 */
public class ExpressOrganisationTest {

	// Les organisations
	String oidorga1;
	String onom1 = "Orga1";
	String oimage1 = "Orgimage1";
	String orole1 = "Orgrole1";
	String ostatut1 = "Orgstatut1";

	String oidorga2;
	String onom2 = "Orga2";
	String oimage2 = "Orgimage2";
	String orole2 = "Orgrole2";
	String ostatut2 = "Orgstatut2";

	String oidorga3;
	String onom3 = "Orga3";
	String oimage3 = "Orgimage3";
	String orole3 = "Orgrole3";
	String ostatut3 = "Orgstatut3";

	// Les Individus
	String oidIndividu1;
	String nom1 = "Individu1";
	String image1 = "image1";
	String role1 = "role1";
	String statut1 = "statut1";

	String oidIndividu2;
	String nom2 = "Individu2";
	String image2 = "image2";
	String role2 = "role2";
	String statut2 = "statut2";

	@Before
	public void setUp() throws Exception {
		// suppression des données dans la base
		InterfaceExpressJava.clearCurrentProject();

		// création de deux organisation dans la base de données

		// création de la première organisation et initialisation des paramètres
		oidorga1 = ExpressOrganisation.createOrganisation();
		ExpressOrganisation.setOrganisationImage(oidorga1, oimage1);
		ExpressOrganisation.setOrganisationName(oidorga1, onom1);
		ExpressOrganisation.setOrganisationRole(oidorga1, orole1);
		ExpressOrganisation.setOrganisationStatut(oidorga1, ostatut1);

		// création de la seconde organisation et initialisation des paramètres
		oidorga2 = ExpressOrganisation.createOrganisation();
		ExpressOrganisation.setOrganisationImage(oidorga2, oimage2);
		ExpressOrganisation.setOrganisationName(oidorga2, onom2);
		ExpressOrganisation.setOrganisationRole(oidorga2, orole2);
		ExpressOrganisation.setOrganisationStatut(oidorga2, ostatut2);

		oidorga3 = ExpressOrganisation.createOrganisation();
		ExpressOrganisation.setOrganisationImage(oidorga3, oimage3);
		ExpressOrganisation.setOrganisationName(oidorga3, onom3);
		ExpressOrganisation.setOrganisationRole(oidorga3, orole3);
		ExpressOrganisation.setOrganisationStatut(oidorga3, ostatut3);

		// création de deux individus dans la base de données

		// création du premier Individu et initialisation des paramètres
		oidIndividu1 = ExpressIndividu.createIndividu();
		ExpressIndividu.setIndividuImage(oidIndividu1, image1);
		ExpressIndividu.setIndividuName(oidIndividu1, nom1);
		ExpressIndividu.setIndividuRole(oidIndividu1, role1);
		ExpressIndividu.setIndividuStatut(oidIndividu1, statut1);

		// création du second Individu
		oidIndividu2 = ExpressIndividu.createIndividu();
		ExpressIndividu.setIndividuImage(oidIndividu2, image2);
		ExpressIndividu.setIndividuName(oidIndividu2, nom2);
		ExpressIndividu.setIndividuRole(oidIndividu2, role2);
		ExpressIndividu.setIndividuStatut(oidIndividu2, statut2);

		// Ajout des individus aux Organisations
		ExpressOrganisation.addOrganisationMember(oidorga1, oidIndividu1);
		ExpressOrganisation.addOrganisationMember(oidorga2, oidIndividu2);
		ExpressOrganisation.addOrganisationMember(oidorga2, oidIndividu1);
	}

	@Test
	public void testGetOrganisationsName() {
		String[] s = ExpressOrganisation.getOrganisationsName();
		// Vérification que l'on récupère bien les deux individus.
		assertTrue(s.length == 3);
		// Vérification que le nom est bien enregistré
		for (int i = 0; i < s.length; i++) {
			assertTrue(s[i] == onom1 || s[i] == onom2 || s[i] == onom3);
		}
	}

	@Test
	public void testGetOrganisation() {
		ArrayList<Organization> ai = ExpressOrganisation.getOrganisations();
		assertTrue(ai.size() == 3);
	}

	@Test
	// Ce test permet aussi de vérifier les Set
	public void testGetOrganisationWithName() {
		Organization o1 = ExpressOrganisation.getOrganisationWithName(onom1);
		assertTrue(o1.getImage() == oimage1);
		assertTrue(o1.getName() == onom1);
		assertTrue(o1.getRole() == orole1);
		assertTrue(o1.getStatus() == ostatut1);
		// vérification que o1 contient bien 1 membre et qu'il a pour nom nom1
		assertTrue(o1.getMembers().size() == 1);
		assertTrue(o1.getMembers().get(0).toString() == nom1);

		Organization o2 = ExpressOrganisation.getOrganisationWithName(onom2);
		assertTrue(o2.getImage() == oimage2);
		assertTrue(o2.getName() == onom2);
		assertTrue(o2.getRole() == orole2);
		assertTrue(o2.getStatus() == ostatut2);
		// vérification que o2 contient bien 2 membres et qu'ils ont pour nom
		// nom1 puis nom2
		assertTrue(o2.getMembers().size() == 2);
		assertTrue(o2.getMembers().get(0).toString() == nom2);
		assertTrue(o2.getMembers().get(1).toString() == nom1);

	}

	@Test
	public void getOtherOrganisationIntoTab() {
		Object[][] m = ExpressIndividu.getOtherOrganisationIntoTab(oidIndividu1);
		Object[][] n = ExpressIndividu.getOrganisationIntoTab(oidIndividu1);
		// individu 1 � bien 1/2 orga
		assertTrue(m.length == 1);
		assertTrue(n.length == 2);
	}

	@Test
	public void testDeleteOrgansation() {
		ExpressOrganisation.deleteOrganisation(oidorga1);
		ArrayList<Organization> ai = ExpressOrganisation.getOrganisations();
		assertTrue(ai.size() == 2);

	}

}

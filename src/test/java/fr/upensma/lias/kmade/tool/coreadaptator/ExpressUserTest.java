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
import fr.upensma.lias.kmade.kmad.schema.tache.User;

/**
 * @author Mickael BARON
 */
public class ExpressUserTest {
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
	// suppression des donn??es dans la base
	InterfaceExpressJava.clearCurrentProject();

	// cr??ation de deux organisation dans la base de donn??es

	// cr??ation de la premi??re organisation et initialisation des param??tres
	oidorga1 = ExpressOrganisation.createOrganisation();
	ExpressOrganisation.setOrganisationImage(oidorga1, oimage1);
	ExpressOrganisation.setOrganisationName(oidorga1, onom1);
	ExpressOrganisation.setOrganisationRole(oidorga1, orole1);
	ExpressOrganisation.setOrganisationStatut(oidorga1, ostatut1);

	// cr??ation de la seconde organisation et initialisation des param??tres
	oidorga2 = ExpressOrganisation.createOrganisation();
	ExpressOrganisation.setOrganisationImage(oidorga2, oimage2);
	ExpressOrganisation.setOrganisationName(oidorga2, onom2);
	ExpressOrganisation.setOrganisationRole(oidorga2, orole2);
	ExpressOrganisation.setOrganisationStatut(oidorga2, ostatut2);

	// cr??ation de deux individus dans la base de donn??es

	// cr??ation du premier Individu et initialisation des param??tres
	oidIndividu1 = ExpressIndividu.createIndividu();
	ExpressIndividu.setIndividuImage(oidIndividu1, image1);
	ExpressIndividu.setIndividuName(oidIndividu1, nom1);
	ExpressIndividu.setIndividuRole(oidIndividu1, role1);
	ExpressIndividu.setIndividuStatut(oidIndividu1, statut1);

	// cr??ation du second Individu
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
    public void testGetUsersName() {
	String[] s = ExpressUser.getUsersName();
	// V??rification que l'on r??cup??re bien les quatres users
	assertTrue(s.length == 4);

    }

    @Test
    public void testGetUser() {
	ArrayList<User> ai = ExpressUser.getUsers();
	assertTrue(ai.size() == 4);
    }

    @Test
    public void testGetOrganisationWithName() {
	User o1 = ExpressUser.getUserWithName(onom1);
	assertTrue(o1.getImage() == oimage1);
	assertTrue(o1.getName() == onom1);
	assertTrue(o1.getRole() == orole1);
	assertTrue(o1.getStatus() == ostatut1);
	// v??rification que o1 contient bien 1 membre et qu'il a pour nom nom1

	// on ne test pas le cast puisque l'on sait que l'on doit r??cup??rer une
	// organisation
	assertTrue(((Organization) o1).getMembers().size() == 1);
	assertTrue(((Organization) o1).getMembers().get(0).toString() == nom1);

	User o2 = ExpressUser.getUserWithName(onom2);
	assertTrue(o2.getImage() == oimage2);
	assertTrue(o2.getName() == onom2);
	assertTrue(o2.getRole() == orole2);
	assertTrue(o2.getStatus() == ostatut2);
	// v??rification que o2 contient bien 2 membres et qu'ils ont pour nom
	// nom1 puis nom2

	assertTrue(((Organization) o2).getMembers().size() == 2);
	assertTrue(((Organization) o2).getMembers().get(0).toString() == nom2);
	assertTrue(((Organization) o2).getMembers().get(1).toString() == nom1);

	// r??cup??ration d'un individu
	User i1 = ExpressUser.getUserWithName(nom1);
	assertTrue(i1.getImage() == image1);
	assertTrue(i1.getName() == nom1);
	assertTrue(i1.getRole() == role1);
	assertTrue(i1.getStatus() == statut1);

    }

    @Test
    public void testSetUserStatut() {
	// on change le role d'orga2 en role1 au lieu de role2
	ExpressUser.setUserRole(oidorga2, role1);
	// on r??cup??re l'org2 pour v??rifi?? que le nom est bien chang??
	User o2 = ExpressUser.getUserWithName(onom2);
	assertTrue(o2.getRole() == role1);

	// on change le role d'orga2 en role1 au lieu de role2
	ExpressUser.setUserStatut(oidIndividu1, statut2);
	// on r??cup??re l'org2 pour v??rifi?? que le nom est bien chang??
	User i1 = ExpressUser.getUserWithName(nom1);
	assertTrue(i1.getName() == nom1);
	assertTrue(i1.getStatus() == statut2);

    }
}

package kmade.kmade.adaptatorFC;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import kmade.kmade.coreadaptator.ExpressIndividu;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.tache.Individu;

import org.junit.Before;
import org.junit.Test;

public class ExpressIndividuTest {

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
		InterfaceExpressJava.clearCurrentProject();
		// création de deux individus dans la base de données
		
		// création du premier Individu et initialisation des paramètres
		oidIndividu1 = ExpressIndividu.createIndividu();
		ExpressIndividu.setIndividuImage(oidIndividu1, image1);
		ExpressIndividu.setIndividuName(oidIndividu1, nom1);
		ExpressIndividu.setIndividuRole(oidIndividu1, role1);
		ExpressIndividu.setIndividuStatut(oidIndividu1, statut1);
		
		//création du second Individu
		oidIndividu2 = ExpressIndividu.createIndividu();
		ExpressIndividu.setIndividuImage(oidIndividu2, image2);
		ExpressIndividu.setIndividuName(oidIndividu2, nom2);
		ExpressIndividu.setIndividuRole(oidIndividu2, role2);
		ExpressIndividu.setIndividuStatut(oidIndividu2, statut2);
	
	}



	@Test
	public void testGetIndividusName() {
		String[] s = ExpressIndividu.getIndividusName();
		//Vérification que l'on récupère bien les deux individus.
		assertTrue(s.length==2);
		//Vérification que le nom est bien enregistré
		for(int i = 0; i< s.length; i++){
			assertTrue(s[i] == nom1 || s[i] ==nom2);
		}
	}

	@Test
	public void testGetIndividus() {
		ArrayList<Individu> ai = ExpressIndividu.getIndividus();
		assertTrue(ai.size()==2);
	}

	@Test
	// Ce test permet aussi de vérifier les Set
	public void testGetIndividuWithName() {
		Individu i1  = ExpressIndividu.getIndividuWithName(nom1);
		assertTrue(i1.getImage()==image1);
		assertTrue(i1.getName()==nom1);
		assertTrue(i1.getRole()==role1);
		assertTrue(i1.getStatut()==statut1);
		
		Individu i2  = ExpressIndividu.getIndividuWithName(nom2);
		assertTrue(i2.getImage()==image2);
		assertTrue(i2.getName()==nom2);
		assertTrue(i2.getRole()==role2);
		assertTrue(i2.getStatut()==statut2);
		
	}
	@Test
	public void testDeleteIndividu() {
		ExpressIndividu.deleteIndividu(oidIndividu1);
		ArrayList<Individu> ai = ExpressIndividu.getIndividus();
		assertTrue(ai.size()==1);
	}



}

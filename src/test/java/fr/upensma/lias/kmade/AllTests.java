package fr.upensma.lias.kmade;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.upensma.lias.kmade.kmad.schema.expression.DiffOperatorTest;
import fr.upensma.lias.kmade.kmad.schema.expression.EqualOperatorTest;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValueTest;
import fr.upensma.lias.kmade.kmad.schema.tache.TacheTest;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividuTest;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressOrganisationTest;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressUserTest;



/**
 */
@RunWith(Suite.class)
@SuiteClasses(value = { 
	DiffOperatorTest.class,
	EqualOperatorTest.class,
	NumberValueTest.class,
	ExpressIndividuTest.class,
	ExpressOrganisationTest.class,
	ExpressUserTest.class,
	TacheTest.class
	
})
public class AllTests {

}

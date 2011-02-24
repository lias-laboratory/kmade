package kmade;


import kmade.kmade.adaptatorFC.ExpressIndividuTest;
import kmade.kmade.adaptatorFC.ExpressOrganisationTest;
import kmade.kmade.adaptatorFC.ExpressUserTest;
import kmade.nmda.schema.expression.DiffOperatorTest;
import kmade.nmda.schema.expression.EqualOperatorTest;
import kmade.nmda.schema.metaobjet.NumberValueTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 */
@RunWith(Suite.class)
@SuiteClasses(value = { 
	DiffOperatorTest.class,
	EqualOperatorTest.class,
	NumberValueTest.class,
	ExpressIndividuTest.class,
	ExpressOrganisationTest.class,
	ExpressUserTest.class
	
})
public class AllTests {

}

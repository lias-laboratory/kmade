package kmade.kmade.adaptatorFC.parserExpression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class RegularExpressionTest {

	ArrayList<String> s_true = new ArrayList<String>();
	ArrayList<String> s_false = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception {
		
		s_true .add("a");
		s_true .add("aa");
		s_true .add("a a");
		s_true .add("aa a");
		s_true .add("a  aa");
		s_true .add("a a");
		s_true .add("azaeaz");
		s_true .add("aéezé");
		s_true .add("a é e z é");
		

		s_false.add("a ");
		s_false.add("a a ");
		s_false.add("  a ");
		s_false.add("  a");
		s_false.add(" ");
		s_false.add(" (");
		s_false.add("  a aa   ");
		s_false .add("a é e z é ");
		
	}

	@Test
	public void testIsGoodGroupName() {
		for(int i = 0 ; i< s_true.size();i++){
			assertTrue(RegularExpression.isGoodGroupName(s_true.get(i)));
		}
		for(int i = 0 ; i< s_false.size();i++){
			assertFalse(RegularExpression.isGoodGroupName(s_false.get(i)));
		}
	}

}

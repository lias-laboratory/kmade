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
package fr.upensma.lias.kmade.tool.coreadaptator.parserexpression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mickael BARON
 */
public class RegularExpressionTest {

	List<String> s_true = new ArrayList<String>();
	List<String> s_false = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		s_true.add("a");
		s_true.add("aa");
		s_true.add("a a");
		s_true.add("aa a");
		s_true.add("a  aa");
		s_true.add("a a");
		s_true.add("azaeaz");
		s_true.add("a�ez�");
		s_true.add("a � e z �");

		s_false.add("a ");
		s_false.add("a a ");
		s_false.add("  a ");
		s_false.add("  a");
		s_false.add(" ");
		s_false.add(" (");
		s_false.add("  a aa   ");
		s_false.add("a � e z � ");
	}

	@Test
	public void testIsGoodGroupName() {
		for (int i = 0; i < s_true.size(); i++) {
			assertTrue(RegularExpression.isGoodGroupName(s_true.get(i)));
		}
		for (int i = 0; i < s_false.size(); i++) {
			assertFalse(RegularExpression.isGoodGroupName(s_false.get(i)));
		}
	}
}

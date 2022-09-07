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
package fr.upensma.lias.kmade.kmad.schema.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;

/**
 * @author Mickael BARON
 */
public class EqualOperatorTest {
	private BooleanConstant us1;
	private BooleanConstant us2;
	private EqualOperator op1;
	private NumberConstant arg1op2;
	private NumberConstant arg2op2;
	private EqualOperator op2;
	private NumberConstant arg1op3;
	private NumberConstant arg2op3;
	private EqualOperator op3;
	private NumberConstant arg1op4;
	private StringConstant arg2op4;
	private EqualOperator op4;
	private StringConstant arg1op5;
	private NumberConstant arg2op5;
	private EqualOperator op5;
	private BooleanConstant arg1op6;
	private EqualOperator op6;
	private NumberConstant arg2op6;
	private NumberConstant arg2op7;
	private NodeExpression arg1op7;
	private EqualOperator op7;
	private NumberConstant arg1op8;
	private EqualOperator op8;
	private BooleanConstant arg2op8;
	private NumberConstant arg1op9;
	private BooleanConstant arg2op9;
	private EqualOperator op9;

	@Before
	// Les expressions à tester sont testés avec les types BooleanConstant,
	// NumberConstant et StringConstant
	// La fonction checkNode() vérifie le type des LeftNode et RightNode pour
	// n'autoriser que les deux mêmes type. On ne l'effectue pas pour vérifié
	// certains cast qui ne seront JAMAIS fait.
	public void setUp() throws Exception {
		// Construction de la premiere expression false == true qui est false
		us1 = new BooleanConstant(false);
		us2 = new BooleanConstant(true);
		op1 = new EqualOperator(us1);
		op1.setRightNode(us2);
		// op1.checkNode();
		op1.evaluateNode(null);
		// Construction de l'expression op2 : 1 == 1 qui est true
		arg1op2 = new NumberConstant(new NumberValue("1"));
		arg2op2 = new NumberConstant(new NumberValue("1.00"));
		op2 = new EqualOperator(arg1op2);
		op2.setRightNode(arg2op2);
		// op2.checkNode();
		op2.evaluateNode(null);
		// Construction de l'expression op3 : 1 == 1.0 qui est true
		arg1op3 = new NumberConstant(new NumberValue("1"));
		arg2op3 = new NumberConstant(new NumberValue("1"));
		op3 = new EqualOperator(arg1op3);
		op3.setRightNode(arg2op3);
		// op3.checkNode();
		op3.evaluateNode(null);
		// Construction de l'expression op4 : 1 == "1" qui est true
		arg1op4 = new NumberConstant(new NumberValue("1"));
		arg2op4 = new StringConstant(new String("1"));
		op4 = new EqualOperator(arg1op4);
		op4.setRightNode(arg2op4);
		// op4.checkNode();
		op4.evaluateNode(null);

		// Construction de l'expression op5 : "5.5" == 1 qui est false
		arg1op5 = new StringConstant("5.5");
		arg2op5 = new NumberConstant(new NumberValue("1.0"));
		op5 = new EqualOperator(arg1op5);
		op5.setRightNode(arg2op5);
		// op5.checkNode();
		op5.evaluateNode(null);

		// Construction de l'expression op6 : true == 0 qui est true
		arg1op6 = new BooleanConstant(true);
		arg2op6 = new NumberConstant(new NumberValue("0"));
		op6 = new EqualOperator(arg1op6);
		op6.setRightNode(arg2op6);
		// op6.checkNode();
		op6.evaluateNode(null);
		// Construction de l'expression op7 : true == 1 qui est false
		arg1op7 = new BooleanConstant(true);
		arg2op7 = new NumberConstant(new NumberValue("1"));
		op7 = new EqualOperator(arg1op7);
		op7.setRightNode(arg2op7);
		// op7.checkNode();
		op7.evaluateNode(null);
		// Construction de l'expression op8 : 0 == false qui est false
		arg1op8 = new NumberConstant(new NumberValue("0"));
		arg2op8 = new BooleanConstant(false);
		op8 = new EqualOperator(arg1op8);
		op8.setRightNode(arg2op8);
		// op8.checkNode();
		op8.evaluateNode(null);
		// Construction de l'expression op9 : 1 == false qui est true
		arg1op9 = new NumberConstant(new NumberValue("1"));
		arg2op9 = new BooleanConstant(false);
		op9 = new EqualOperator(arg1op9);
		op9.setRightNode(arg2op9);
		// op9.checkNode();
		op9.evaluateNode(null);
	}

	@Test
	public void testEvaluateNode() throws SemanticException {
		assertFalse((Boolean) op1.getNodeValue());
		assertTrue((Boolean) op2.getNodeValue());
		assertTrue((Boolean) op3.getNodeValue());
		assertTrue((Boolean) op4.getNodeValue());
		assertFalse((Boolean) op5.getNodeValue());
		assertTrue((Boolean) op6.getNodeValue());
		assertFalse((Boolean) op7.getNodeValue());
		assertFalse((Boolean) op8.getNodeValue());
	}
}

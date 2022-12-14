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

import fr.upensma.lias.kmade.kmad.schema.expression.BooleanConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.DiffOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.NumberConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.StringConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;

/**
 * @author Mickael BARON
 */
public class DiffOperatorTest {

	private BooleanConstant us1;
	private BooleanConstant us2;
	private DiffOperator op1;
	private NumberConstant arg1op2;
	private NumberConstant arg2op2;
	private DiffOperator op2;
	private NumberConstant arg1op3;
	private NumberConstant arg2op3;
	private DiffOperator op3;
	private NumberConstant arg1op4;
	private StringConstant arg2op4;
	private DiffOperator op4;
	private StringConstant arg1op5;
	private NumberConstant arg2op5;
	private DiffOperator op5;
	private BooleanConstant arg1op6;
	private DiffOperator op6;
	private NumberConstant arg2op6;
	private NumberConstant arg2op7;
	private NodeExpression arg1op7;
	private DiffOperator op7;
	private NumberConstant arg1op8;
	private DiffOperator op8;
	private BooleanConstant arg2op8;
	private NumberConstant arg1op9;
	private BooleanConstant arg2op9;
	private DiffOperator op9;

	@Before
	// Les expressions ?? tester sont test??s avec les types BooleanConstant,
	// NumberConstant et StringConstant
	// La fonction checkNode() v??rifie le type des LeftNode et RightNode pour
	// n'autoriser que les deux m??mes type. On ne l'effectue pas pour v??rifi??
	// certains cast qui ne seront JAMAIS fait.
	public void setUp() throws Exception {
		// Construction de la premiere expression false != true
		us1 = new BooleanConstant(false);
		us2 = new BooleanConstant(true);
		op1 = new DiffOperator(us1);
		op1.setRightNode(us2);
		// op1.checkNode();
		op1.evaluateNode(null);
		// Construction de l'expression op2 : 1 != 1 qui est false
		arg1op2 = new NumberConstant(new NumberValue("1"));
		arg2op2 = new NumberConstant(new NumberValue("1.00"));
		op2 = new DiffOperator(arg1op2);
		op2.setRightNode(arg2op2);
		// op2.checkNode();
		op2.evaluateNode(null);
		// Construction de l'expression op3 : 1 != 1.0 qui est false
		arg1op3 = new NumberConstant(new NumberValue("1"));
		arg2op3 = new NumberConstant(new NumberValue("1"));
		op3 = new DiffOperator(arg1op3);
		op3.setRightNode(arg2op3);
		// op3.checkNode();
		op3.evaluateNode(null);
		// Construction de l'expression op4 : 1 != "1" qui est false
		arg1op4 = new NumberConstant(new NumberValue("1"));
		arg2op4 = new StringConstant(new String("1"));
		op4 = new DiffOperator(arg1op4);
		op4.setRightNode(arg2op4);
		// op4.checkNode();
		op4.evaluateNode(null);

		// Construction de l'expression op5 : "5.5" != 1 qui est true
		arg1op5 = new StringConstant("5.5");
		arg2op5 = new NumberConstant(new NumberValue("1.0"));
		op5 = new DiffOperator(arg1op5);
		op5.setRightNode(arg2op5);
		// op5.checkNode();
		op5.evaluateNode(null);

		// Construction de l'expression op6 : true != 0 qui est false
		arg1op6 = new BooleanConstant(true);
		arg2op6 = new NumberConstant(new NumberValue("0"));
		op6 = new DiffOperator(arg1op6);
		op6.setRightNode(arg2op6);
		// op6.checkNode();
		op6.evaluateNode(null);
		// Construction de l'expression op7 : true != 1 qui est true
		arg1op7 = new BooleanConstant(true);
		arg2op7 = new NumberConstant(new NumberValue("1"));
		op7 = new DiffOperator(arg1op7);
		op7.setRightNode(arg2op7);
		// op7.checkNode();
		op7.evaluateNode(null);
		// Construction de l'expression op8 : 0 != false qui est true
		arg1op8 = new NumberConstant(new NumberValue("0"));
		arg2op8 = new BooleanConstant(false);
		op8 = new DiffOperator(arg1op8);
		op8.setRightNode(arg2op8);
		// op8.checkNode();
		op8.evaluateNode(null);
		// Construction de l'expression op9 : 1 != false qui est false
		arg1op9 = new NumberConstant(new NumberValue("1"));
		arg2op9 = new BooleanConstant(false);
		op9 = new DiffOperator(arg1op9);
		op9.setRightNode(arg2op9);
		// op9.checkNode();
		op9.evaluateNode(null);

	}

	@Test
	public void testEvaluateNode() throws SemanticException {
		assertTrue((Boolean) op1.getNodeValue());
		assertFalse((Boolean) op2.getNodeValue());
		assertFalse((Boolean) op3.getNodeValue());
		assertFalse((Boolean) op4.getNodeValue());
		assertTrue((Boolean) op5.getNodeValue());
		assertFalse((Boolean) op6.getNodeValue());
		assertTrue((Boolean) op7.getNodeValue());
		assertTrue((Boolean) op8.getNodeValue());
	}
}

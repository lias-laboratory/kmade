/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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

import fr.upensma.lias.kmade.kmad.schema.expression.AddUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.Assignment;
import fr.upensma.lias.kmade.kmad.schema.expression.AssignmentOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.AttributExpressExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.BooleanConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.CreateFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.ExpressException;
import fr.upensma.lias.kmade.kmad.schema.expression.GetFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.GetUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.GroupExpressExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.MinusAssignment;
import fr.upensma.lias.kmade.kmad.schema.expression.MinusComputing;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.NotComma;
import fr.upensma.lias.kmade.kmad.schema.expression.NumberConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.ParenthesisExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.PlusAssignment;
import fr.upensma.lias.kmade.kmad.schema.expression.PlusComputing;
import fr.upensma.lias.kmade.kmad.schema.expression.RemoveUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.ReplaceFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.SetFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.SetUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.StringConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.UserBoolean;
import fr.upensma.lias.kmade.kmad.schema.expression.UserNumber;
import fr.upensma.lias.kmade.kmad.schema.expression.UserString;
import fr.upensma.lias.kmade.kmad.schema.expression.VoidConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.tool.KMADEConstant;

@SuppressWarnings({"all"})
public class EffetsDeBord implements EffetsDeBordConstants {

  final public NodeExpression expression() throws ParseException {
        NodeExpression node;
    if (jj_2_1(2)) {
      node = sequentialExpression();
      jj_consume_token(0);
                                              {if (true) return node;}
    } else if (jj_2_2(2)) {
      jj_consume_token(VOID_TYPE);
      jj_consume_token(0);
                           {if (true) return new VoidConstant();}
    } else if (jj_2_3(2)) {
      jj_consume_token(0);
                {if (true) throw new ParseException(KMADEConstant.NO_EXPRESSION_MESSAGE);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression sequentialExpression() throws ParseException {
        NodeExpression left;
        NodeExpression right;
        NotComma notComma;
    left = primaryExpression();
    label_1:
    while (true) {
      if (jj_2_4(2)) {
        ;
      } else {
        break label_1;
      }
      jj_consume_token(SEQUENCE);
                                                  notComma = new NotComma(left);
      right = primaryExpression();
                if (notComma != null) {
                        notComma.setRightNode(right);
                        left = notComma;
                }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression primaryExpression() throws ParseException {
        NodeExpression node;
    if (jj_2_5(2)) {
      node = remove();
                          {if (true) return node;}
    } else if (jj_2_6(2)) {
      node = set();
                       {if (true) return node;}
    } else if (jj_2_7(2)) {
      node = add();
                       {if (true) return node;}
    } else if (jj_2_8(2)) {
      node = replace();
                           {if (true) return node;}
    } else if (jj_2_9(2)) {
      node = create();
                      {if (true) return node;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression remove() throws ParseException {
        Token myIdentToken;
        GroupExpressExpression myGroupRef;
        NodeExpression node;
    jj_consume_token(REMOVE);
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
    jj_consume_token(PARF);
                try {
                        {if (true) return new RemoveUnaryFunction(new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase()));}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression set() throws ParseException {
        AssignmentOperator nodeAssignment;
        NodeExpression setFunction;
    jj_consume_token(SET);
    jj_consume_token(PARO);
    if (jj_2_10(2)) {
      nodeAssignment = withGroup();
                setFunction = new SetFunction(((AttributExpressExpression)nodeAssignment.getLeftNode()).getGroupExpressExpression(), nodeAssignment);
    } else if (jj_2_11(2)) {
      nodeAssignment = onlyAssignment(null);
                setFunction = new SetUnaryFunction(nodeAssignment); // null Group from Attribut

    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(PARF);
                {if (true) return setFunction;}
    throw new Error("Missing return statement in function");
  }

  final public AssignmentOperator withGroup() throws ParseException {
        Token myIdentToken;
        AssignmentOperator node;
        GroupExpressExpression myExpression;
    myIdentToken = jj_consume_token(IDENT);
                try {
                        myExpression = new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase());
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    jj_consume_token(COMA);
    node = onlyAssignment(myExpression);
                {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression add() throws ParseException {
        Token myIdentToken;
    jj_consume_token(ADD);
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
    jj_consume_token(PARF);
                try {
                        {if (true) return new AddUnaryFunction(new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase()));}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression create() throws ParseException {
        Token myIdentToken;
        Token myNewConcret;
        NodeExpression refNode;
        GroupExpressExpression myExpression;
    jj_consume_token(CREATE);
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
                try {
                        myExpression = new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase());
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    jj_consume_token(COMA);
    refNode = sumAssignment(myExpression);
    jj_consume_token(PARF);
                {if (true) return new CreateFunction(myExpression, refNode);}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression replace() throws ParseException {
        Token myFirstIdentToken;
        Token mySecondIdentToken;
    jj_consume_token(REPLACE);
    jj_consume_token(PARO);
    myFirstIdentToken = jj_consume_token(IDENT);
    jj_consume_token(COMA);
    mySecondIdentToken = jj_consume_token(IDENT);
    jj_consume_token(PARF);
                try {
                        {if (true) return new ReplaceFunction(new GroupExpressExpression(myFirstIdentToken.image.substring(1).toLowerCase()),
                                new GroupExpressExpression(mySecondIdentToken.image.substring(1).toLowerCase()));}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  final public AssignmentOperator onlyAssignment(GroupExpressExpression ref) throws ParseException {
        Token myIdentToken;
        AttributExpressExpression myIdentNode;
        AssignmentOperator refAOp;
        NodeExpression myExpression;
    myIdentToken = jj_consume_token(IDENT);
                if (ref == null) {
                        myIdentNode = new AttributExpressExpression(myIdentToken.image.substring(1).toLowerCase());
                } else {
                        try {
                                myIdentNode = new AttributExpressExpression(myIdentToken.image.substring(1).toLowerCase(),ref);
                        } catch (ExpressException e) {
                                {if (true) throw new Error(e.toString());}
                        }
                }
    if (jj_2_12(2)) {
      jj_consume_token(ASSIGNMENT);
                                 refAOp = new Assignment(myIdentNode);
    } else if (jj_2_13(2)) {
      jj_consume_token(PLUS_ASSIGNMENT);
                                  refAOp = new PlusAssignment(myIdentNode);
    } else if (jj_2_14(2)) {
      jj_consume_token(MINUS_ASSIGNMENT);
                                   refAOp = new MinusAssignment(myIdentNode);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    myExpression = sumAssignment(ref);
                refAOp.setRightNode(myExpression);
                {if (true) return refAOp;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression sumAssignment(GroupExpressExpression ref) throws ParseException {
        Token operator;
        NodeExpression left;
        NodeExpression right;
    left = primaryAssignment(ref);
    label_2:
    while (true) {
      if (jj_2_15(2)) {
        ;
      } else {
        break label_2;
      }
      if (jj_2_16(2)) {
        operator = jj_consume_token(PLUS);
      } else if (jj_2_17(2)) {
        operator = jj_consume_token(MINUS);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = primaryAssignment(ref);
                        if (operator.kind == PLUS) {
                                left = new PlusComputing(left,right);
                        } else {
                                left = new MinusComputing(left,right);
                        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression primaryAssignment(GroupExpressExpression ref) throws ParseException {
        NodeExpression node;
        Token myToken;
    if (jj_2_18(2)) {
      node = getValueDouble(ref);
                                     {if (true) return node;}
    } else if (jj_2_19(2)) {
      myToken = jj_consume_token(STRING);
                {if (true) return new StringConstant(myToken.image.substring(1,myToken.image.length() - 1));}
    } else if (jj_2_20(2)) {
      myToken = jj_consume_token(NUMBER);
                {if (true) return new NumberConstant(new NumberValue(myToken.image));}
    } else if (jj_2_21(2)) {
      myToken = jj_consume_token(BOOLEAN);
                {if (true) return new BooleanConstant(Boolean.parseBoolean(myToken.image));}
    } else if (jj_2_22(2)) {
      jj_consume_token(UNKNOWN_NUMBER);
                             {if (true) return new UserNumber();}
    } else if (jj_2_23(2)) {
      jj_consume_token(UNKNOWN_STRING);
                         {if (true) return new UserString();}
    } else if (jj_2_24(2)) {
      jj_consume_token(UNKNOWN_BOOLEAN);
                          {if (true) return new UserBoolean();}
    } else if (jj_2_25(2)) {
      jj_consume_token(PARO);
      node = sumAssignment(ref);
      jj_consume_token(PARF);
                                                  {if (true) return new ParenthesisExpression(node);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression getValueDouble(GroupExpressExpression ref) throws ParseException {
        Token myGroupOrAttributToken;
        Token myAttrToken = null;
    jj_consume_token(GETVALUE);
    jj_consume_token(PARO);
    myGroupOrAttributToken = jj_consume_token(IDENT);
    if (jj_2_26(2)) {
      jj_consume_token(COMA);
      myAttrToken = jj_consume_token(IDENT);
    } else {
      ;
    }
    jj_consume_token(PARF);
                GroupExpressExpression myGroupRef = null;
                AttributExpressExpression myAttrRef = null;

                try {
                        if (myAttrToken == null) {
                                myGroupRef = ref;
                                myAttrRef = new AttributExpressExpression(myGroupOrAttributToken.image.substring(1).toLowerCase(), myGroupRef);
                                if (myGroupRef == null) {
                                        {if (true) return new GetUnaryFunction(myAttrRef);}
                                } else {
                                        {if (true) return new GetFunction(myGroupRef,myAttrRef);}
                                }
                        } else {
                                myGroupRef = new GroupExpressExpression(myGroupOrAttributToken.image.substring(1).toLowerCase());
                                myAttrRef = new AttributExpressExpression(myAttrToken.image.substring(1).toLowerCase(), myGroupRef);
                                {if (true) return new GetFunction(myGroupRef,myAttrRef);}
                        }
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_20(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_21(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_22(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_23(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_24(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_25(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_26(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_26(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_3_11() {
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3_10() {
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(ASSIGNMENT)) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_scan_token(SET)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_scan_token(MINUS_ASSIGNMENT)) return true;
    return false;
  }

  private boolean jj_3_13() {
    if (jj_scan_token(PLUS_ASSIGNMENT)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(SEQUENCE)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3R_5() {
    if (jj_scan_token(REMOVE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_scan_token(IDENT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_12()) {
    jj_scanpos = xsp;
    if (jj_3_13()) {
    jj_scanpos = xsp;
    if (jj_3_14()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_8() {
    if (jj_3R_8()) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_3R_5()) return true;
    return false;
  }

  private boolean jj_3R_4() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3_9()) return true;
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_8() {
    if (jj_scan_token(REPLACE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3R_3() {
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_26() {
    if (jj_scan_token(COMA)) return true;
    if (jj_scan_token(IDENT)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3R_9() {
    if (jj_scan_token(CREATE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3R_13() {
    if (jj_scan_token(GETVALUE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(VOID_TYPE)) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_17() {
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  private boolean jj_3_25() {
    if (jj_scan_token(PARO)) return true;
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3_22() {
    if (jj_scan_token(UNKNOWN_NUMBER)) return true;
    return false;
  }

  private boolean jj_3_24() {
    if (jj_scan_token(UNKNOWN_BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(ADD)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_23() {
    if (jj_scan_token(UNKNOWN_STRING)) return true;
    return false;
  }

  private boolean jj_3_20() {
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_21() {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_18()) {
    jj_scanpos = xsp;
    if (jj_3_19()) {
    jj_scanpos = xsp;
    if (jj_3_20()) {
    jj_scanpos = xsp;
    if (jj_3_21()) {
    jj_scanpos = xsp;
    if (jj_3_22()) {
    jj_scanpos = xsp;
    if (jj_3_23()) {
    jj_scanpos = xsp;
    if (jj_3_24()) {
    jj_scanpos = xsp;
    if (jj_3_25()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3_18() {
    if (jj_3R_13()) return true;
    return false;
  }

  private boolean jj_3_19() {
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_scan_token(IDENT)) return true;
    if (jj_scan_token(COMA)) return true;
    return false;
  }

  private boolean jj_3_16() {
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  private boolean jj_3_15() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_16()) {
    jj_scanpos = xsp;
    if (jj_3_17()) return true;
    }
    if (jj_3R_12()) return true;
    return false;
  }

  private boolean jj_3R_14() {
    if (jj_3R_12()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public EffetsDeBordTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;

  /** Constructor with InputStream. */
  public EffetsDeBord(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public EffetsDeBord(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new EffetsDeBordTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor. */
  public EffetsDeBord(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new EffetsDeBordTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor with generated Token Manager. */
  public EffetsDeBord(EffetsDeBordTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(EffetsDeBordTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      return token;
    }
    token = oldToken;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    Token errortok = token.next;
    int line = errortok.beginLine, column = errortok.beginColumn;
    String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
    return new ParseException("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}

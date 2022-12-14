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

import fr.upensma.lias.kmade.kmad.schema.expression.AndOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.AttributExpressExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.BooleanConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.DiffOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.EqualOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.EqualityOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.ExpressException;
import fr.upensma.lias.kmade.kmad.schema.expression.GetFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.GetUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.GroupExpressExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.InfEqualOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.InfOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.IsEmptyUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.IsExistAtFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.IsExistFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.LengthFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.LengthUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.NotUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.NumberConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.OrOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.ParenthesisExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.RelationalOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.StringConstant;
import fr.upensma.lias.kmade.kmad.schema.expression.SupEqualOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.SupOperator;
import fr.upensma.lias.kmade.kmad.schema.expression.UserBoolean;
import fr.upensma.lias.kmade.kmad.schema.expression.UserNumber;
import fr.upensma.lias.kmade.kmad.schema.expression.UserString;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.tool.KMADEConstant;
@SuppressWarnings({"all"})

public class Precondition implements PreconditionConstants {

  final public NodeExpression expression() throws ParseException {
        NodeExpression node;
    if (jj_2_1(2)) {
      jj_consume_token(NUMBER);
      jj_consume_token(0);
                           {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
    } else if (jj_2_2(2)) {
      jj_consume_token(STRING);
      jj_consume_token(0);
                       {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
    } else if (jj_2_3(2)) {
      jj_consume_token(NUMBER);
      jj_consume_token(NUMBER);
                          {if (true) throw new ParseException();}
    } else if (jj_2_4(2)) {
      jj_consume_token(UNKNOWN_NUMBER);
      jj_consume_token(0);
                               {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
    } else if (jj_2_5(2)) {
      jj_consume_token(UNKNOWN_STRING);
      jj_consume_token(0);
                               {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
    } else if (jj_2_6(2)) {
      node = conditionalOrExpression();
      jj_consume_token(0);
                if (node instanceof StringConstant || node instanceof NumberConstant
                                || node instanceof UserNumber || node instanceof UserString
                                || node instanceof LengthUnaryFunction || node instanceof LengthFunction) {
                        {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
                }
                if (node instanceof GetFunction) {
                        if (!node.isBoolean()) {
                                {if (true) throw new ParseException(KMADEConstant.NO_FIRST_EXPRESSION_MESSAGE);}
                        }
                }
                {if (true) return node;}
    } else if (jj_2_7(2)) {
      jj_consume_token(0);
            {if (true) throw new ParseException(KMADEConstant.NO_EXPRESSION_MESSAGE);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression conditionalOrExpression() throws ParseException {
        NodeExpression left;
        NodeExpression right;
        OrOperator orOp;
    left = conditionalAndExpression();
    label_1:
    while (true) {
      if (jj_2_8(2)) {
        ;
      } else {
        break label_1;
      }
      jj_consume_token(OR);
                 orOp = new OrOperator(left);
      right = conditionalAndExpression();
        if (orOp != null) {
                orOp.setRightNode(right);
                left = orOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression conditionalAndExpression() throws ParseException {
        NodeExpression left;
        NodeExpression right;
        AndOperator andOp;
    left = equalityExpression();
    label_2:
    while (true) {
      if (jj_2_9(2)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(AND);
                  andOp = new AndOperator(left);
      right = equalityExpression();
        if (andOp != null) {
                andOp.setRightNode(right);
                left = andOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression equalityExpression() throws ParseException {
        NodeExpression left;
        NodeExpression right;
        EqualityOperator compOp;
    left = relationExpression();
    label_3:
    while (true) {
      if (jj_2_10(2)) {
        ;
      } else {
        break label_3;
      }
      if (jj_2_11(2)) {
        jj_consume_token(EQUAL);
                    compOp = new EqualOperator(left);
      } else if (jj_2_12(2)) {
        jj_consume_token(NOT_EQUAL);
                    compOp = new DiffOperator(left);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = relationExpression();
        if (compOp != null) {
                compOp.setRightNode(right);
                left = compOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression relationExpression() throws ParseException {
        NodeExpression left;
        NodeExpression right;
        RelationalOperator relOp;
    left = unaryExpressionNotPlusMinus();
    label_4:
    while (true) {
      if (jj_2_13(2)) {
        ;
      } else {
        break label_4;
      }
      if (jj_2_14(2)) {
        jj_consume_token(INF);
                  relOp = new InfOperator(left);
      } else if (jj_2_15(2)) {
        jj_consume_token(SUP);
              relOp = new SupOperator(left);
      } else if (jj_2_16(2)) {
        jj_consume_token(INF_EQUAL);
                    relOp = new InfEqualOperator(left);
      } else if (jj_2_17(2)) {
        jj_consume_token(SUP_EQUAL);
                    relOp = new SupEqualOperator(left);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = unaryExpressionNotPlusMinus();
        if (relOp != null) {
                relOp.setRightNode(right);
                left = relOp;
        }
    }
        {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression unaryExpressionNotPlusMinus() throws ParseException {
  NodeExpression t;
    if (jj_2_18(2)) {
      jj_consume_token(NOT);
      t = primaryExpression();
                                          {if (true) return new NotUnaryFunction(t);}
    } else if (jj_2_19(2)) {
      t = primaryExpression();
                                  {if (true) return t;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression primaryExpression() throws ParseException {
        Token myToken;
        NodeExpression node;
    if (jj_2_20(2)) {
      node = length();
                      {if (true) return node;}
    } else if (jj_2_21(2)) {
      node = isExist();
                       {if (true) return node;}
    } else if (jj_2_22(2)) {
      node = isEmpty();
                       {if (true) return node;}
    } else if (jj_2_23(2)) {
      node = getValue();
                        {if (true) return node;}
    } else if (jj_2_24(2)) {
      myToken = jj_consume_token(STRING);
                {if (true) return new StringConstant(myToken.image.substring(1,myToken.image.length() - 1));}
    } else if (jj_2_25(2)) {
      myToken = jj_consume_token(NUMBER);
                {if (true) return new NumberConstant(new NumberValue(myToken.image));}
    } else if (jj_2_26(2)) {
      myToken = jj_consume_token(BOOLEAN);
                {if (true) return new BooleanConstant(Boolean.parseBoolean(myToken.image));}
    } else if (jj_2_27(2)) {
      jj_consume_token(UNKNOWN_NUMBER);
                         {if (true) return new UserNumber();}
    } else if (jj_2_28(2)) {
      jj_consume_token(UNKNOWN_STRING);
                         {if (true) return new UserString();}
    } else if (jj_2_29(2)) {
      jj_consume_token(UNKNOWN_BOOLEAN);
                          {if (true) return new UserBoolean();}
    } else if (jj_2_30(2)) {
      jj_consume_token(PARO);
      node = conditionalOrExpression();
      jj_consume_token(PARF);
                                                         {if (true) return new ParenthesisExpression(node);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression getValue() throws ParseException {
        Token myGroupToken;
        Token myAttrToken;
    jj_consume_token(GETVALUE);
    jj_consume_token(PARO);
    myGroupToken = jj_consume_token(IDENT);
    jj_consume_token(COMA);
    myAttrToken = jj_consume_token(IDENT);
    jj_consume_token(PARF);
                try {
                        GroupExpressExpression myGroupRef = new GroupExpressExpression(myGroupToken.image.substring(1).toLowerCase());
                        AttributExpressExpression myAttrRef = new AttributExpressExpression(myAttrToken.image.substring(1).toLowerCase(), myGroupRef);
                        {if (true) return new GetFunction(myGroupRef,myAttrRef);}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression getValueDouble(GroupExpressExpression ref) throws ParseException {
        Token myGroupOrAttributToken;
        Token myAttrToken = null;
    jj_consume_token(GETVALUE);
    jj_consume_token(PARO);
    myGroupOrAttributToken = jj_consume_token(IDENT);
    if (jj_2_31(2)) {
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
                                {if (true) return new GetUnaryFunction(myAttrRef);}
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

  final public NodeExpression isExist() throws ParseException {
        Token myIdentToken;
        Token function;
        GroupExpressExpression myGroupRef;
        NodeExpression node;
    if (jj_2_32(2)) {
      function = jj_consume_token(EXIST);
    } else if (jj_2_33(2)) {
      function = jj_consume_token(EXISTAT);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
                try {
                        myGroupRef = new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase());
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    jj_consume_token(COMA);
    node = conditionalOrExpressionFunction(myGroupRef);
    jj_consume_token(PARF);
                if (function.kind == EXIST) {
                        {if (true) return new IsExistFunction(myGroupRef, node);}
                } else {
                        {if (true) return new IsExistAtFunction(myGroupRef, node);}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression isEmpty() throws ParseException {
        Token myIdentToken;
    jj_consume_token(EMPTY);
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
    jj_consume_token(PARF);
                try {
                        {if (true) return new IsEmptyUnaryFunction(new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase()));}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression length() throws ParseException {
        Token myIdentToken;
        NodeExpression node = null;
        GroupExpressExpression myGroupRef;
    jj_consume_token(NOMBRE);
    jj_consume_token(PARO);
    myIdentToken = jj_consume_token(IDENT);
                try {
                        myGroupRef = new GroupExpressExpression(myIdentToken.image.substring(1).toLowerCase());
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    if (jj_2_34(2)) {
      jj_consume_token(COMA);
      node = conditionalOrExpressionFunction(myGroupRef);
    } else {
      ;
    }
    jj_consume_token(PARF);
                if (node == null) {
                        {if (true) return new LengthUnaryFunction(myGroupRef);}
                } else {
                        {if (true) return new LengthFunction(myGroupRef,node);}
                }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression conditionalOrExpressionFunction(GroupExpressExpression ref) throws ParseException {
        NodeExpression left;
        NodeExpression right;
        OrOperator orOp;
    left = conditionalAndExpressionFunction(ref);
    label_5:
    while (true) {
      if (jj_2_35(2)) {
        ;
      } else {
        break label_5;
      }
      jj_consume_token(OR);
                 orOp = new OrOperator(left);
      right = conditionalAndExpressionFunction(ref);
        if (orOp != null) {
                orOp.setRightNode(right);
                left = orOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression conditionalAndExpressionFunction(GroupExpressExpression ref) throws ParseException {
        NodeExpression left;
        NodeExpression right;
        AndOperator andOp;
    left = equalityExpressionFunction(ref);
    label_6:
    while (true) {
      if (jj_2_36(2)) {
        ;
      } else {
        break label_6;
      }
      jj_consume_token(AND);
                  andOp = new AndOperator(left);
      right = equalityExpressionFunction(ref);
        if (andOp != null) {
                andOp.setRightNode(right);
                left = andOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression equalityExpressionFunction(GroupExpressExpression ref) throws ParseException {
        NodeExpression left;
        NodeExpression right;
        EqualityOperator compOp;
    left = relationExpressionFunction(ref);
    label_7:
    while (true) {
      if (jj_2_37(2)) {
        ;
      } else {
        break label_7;
      }
      if (jj_2_38(2)) {
        jj_consume_token(EQUAL);
                    compOp = new EqualOperator(left);
      } else if (jj_2_39(2)) {
        jj_consume_token(NOT_EQUAL);
                    compOp = new DiffOperator(left);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = relationExpressionFunction(ref);
        if (compOp != null) {
                compOp.setRightNode(right);
                left = compOp;
        }
    }
                {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression relationExpressionFunction(GroupExpressExpression ref) throws ParseException {
        NodeExpression left;
        NodeExpression right;
        RelationalOperator relOp;
    left = unaryExpressionNotPlusMinusFunction(ref);
    label_8:
    while (true) {
      if (jj_2_40(2)) {
        ;
      } else {
        break label_8;
      }
      if (jj_2_41(2)) {
        jj_consume_token(INF);
                  relOp = new InfOperator(left);
      } else if (jj_2_42(2)) {
        jj_consume_token(SUP);
              relOp = new SupOperator(left);
      } else if (jj_2_43(2)) {
        jj_consume_token(INF_EQUAL);
                    relOp = new InfEqualOperator(left);
      } else if (jj_2_44(2)) {
        jj_consume_token(SUP_EQUAL);
                    relOp = new SupEqualOperator(left);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
      right = unaryExpressionNotPlusMinusFunction(ref);
        if (relOp != null) {
                relOp.setRightNode(right);
                left = relOp;
        }
    }
        {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression unaryExpressionNotPlusMinusFunction(GroupExpressExpression ref) throws ParseException {
  NodeExpression t;
    if (jj_2_45(2)) {
      jj_consume_token(NOT);
      t = primaryExpressionFunction(ref);
                                                     {if (true) return new NotUnaryFunction(t);}
    } else if (jj_2_46(2)) {
      t = primaryExpressionFunction(ref);
                                             {if (true) return t;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression primaryExpressionFunction(GroupExpressExpression ref) throws ParseException {
        Token myToken;
        NodeExpression node;
    if (jj_2_47(2)) {
      length();
               {if (true) return null;}
    } else if (jj_2_48(2)) {
      node = isExist();
                       {if (true) return node;}
    } else if (jj_2_49(2)) {
      node = isEmpty();
                       {if (true) return node;}
    } else if (jj_2_50(2)) {
      node = getValueDouble(ref);
                                 {if (true) return node;}
    } else if (jj_2_51(2)) {
      myToken = jj_consume_token(IDENT);
                try {
                        {if (true) return new AttributExpressExpression(myToken.image.substring(1).toLowerCase(), ref);}
                } catch (ExpressException e) {
                        {if (true) throw new Error(e.toString());}
                }
    } else if (jj_2_52(2)) {
      myToken = jj_consume_token(STRING);
                {if (true) return new StringConstant(myToken.image.substring(1,myToken.image.length() - 1));}
    } else if (jj_2_53(2)) {
      myToken = jj_consume_token(NUMBER);
                {if (true) return new NumberConstant(new NumberValue(myToken.image));}
    } else if (jj_2_54(2)) {
      myToken = jj_consume_token(BOOLEAN);
                {if (true) return new BooleanConstant(Boolean.parseBoolean(myToken.image));}
    } else if (jj_2_55(2)) {
      jj_consume_token(UNKNOWN_NUMBER);
                         {if (true) return new UserNumber();}
    } else if (jj_2_56(2)) {
      jj_consume_token(UNKNOWN_STRING);
                         {if (true) return new UserString();}
    } else if (jj_2_57(2)) {
      jj_consume_token(UNKNOWN_BOOLEAN);
                          {if (true) return new UserBoolean();}
    } else if (jj_2_58(2)) {
      jj_consume_token(PARO);
      node = conditionalOrExpressionFunction(ref);
      jj_consume_token(PARF);
                                                                    {if (true) return new ParenthesisExpression(node);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
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

  private boolean jj_2_27(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_27(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_28(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_28(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_29(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_29(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_30(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_30(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_31(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_31(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_32(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_32(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_33(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_33(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_34(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_34(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_35(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_35(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_36(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_36(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_37(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_37(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_38(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_38(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_39(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_39(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_40(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_40(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_41(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_41(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_42(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_42(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_43(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_43(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_44(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_44(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_45(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_45(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_46(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_46(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_47(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_47(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_48(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_48(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_49(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_49(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_50(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_50(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_51(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_51(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_52(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_52(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_53(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_53(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_54(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_54(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_55(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_55(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_56(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_56(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_57(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_57(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_58(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_58(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_3R_19() {
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_10() {
    if (jj_3R_11()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_9()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_34() {
    if (jj_scan_token(COMA)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3R_9() {
    if (jj_3R_10()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_8()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_15() {
    if (jj_scan_token(NOMBRE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3R_17() {
    if (jj_scan_token(EMPTY)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_3R_9()) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(UNKNOWN_STRING)) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(NUMBER)) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_scan_token(UNKNOWN_NUMBER)) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_scan_token(NUMBER)) return true;
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(STRING)) return true;
    if (jj_scan_token(0)) return true;
    return false;
  }

  private boolean jj_3_33() {
    if (jj_scan_token(EXISTAT)) return true;
    return false;
  }

  private boolean jj_3_32() {
    if (jj_scan_token(EXIST)) return true;
    return false;
  }

  private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_32()) {
    jj_scanpos = xsp;
    if (jj_3_33()) return true;
    }
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_58() {
    if (jj_scan_token(PARO)) return true;
    if (jj_3R_19()) return true;
    return false;
  }

  private boolean jj_3_40() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_41()) {
    jj_scanpos = xsp;
    if (jj_3_42()) {
    jj_scanpos = xsp;
    if (jj_3_43()) {
    jj_scanpos = xsp;
    if (jj_3_44()) return true;
    }
    }
    }
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3_31() {
    if (jj_scan_token(COMA)) return true;
    if (jj_scan_token(IDENT)) return true;
    return false;
  }

  private boolean jj_3R_25() {
    if (jj_scan_token(GETVALUE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_57() {
    if (jj_scan_token(UNKNOWN_BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3_56() {
    if (jj_scan_token(UNKNOWN_STRING)) return true;
    return false;
  }

  private boolean jj_3_55() {
    if (jj_scan_token(UNKNOWN_NUMBER)) return true;
    return false;
  }

  private boolean jj_3_53() {
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_54() {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3_52() {
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3_51() {
    if (jj_scan_token(IDENT)) return true;
    return false;
  }

  private boolean jj_3R_18() {
    if (jj_scan_token(GETVALUE)) return true;
    if (jj_scan_token(PARO)) return true;
    return false;
  }

  private boolean jj_3_50() {
    if (jj_3R_25()) return true;
    return false;
  }

  private boolean jj_3_49() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3_48() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_47()) {
    jj_scanpos = xsp;
    if (jj_3_48()) {
    jj_scanpos = xsp;
    if (jj_3_49()) {
    jj_scanpos = xsp;
    if (jj_3_50()) {
    jj_scanpos = xsp;
    if (jj_3_51()) {
    jj_scanpos = xsp;
    if (jj_3_52()) {
    jj_scanpos = xsp;
    if (jj_3_53()) {
    jj_scanpos = xsp;
    if (jj_3_54()) {
    jj_scanpos = xsp;
    if (jj_3_55()) {
    jj_scanpos = xsp;
    if (jj_3_56()) {
    jj_scanpos = xsp;
    if (jj_3_57()) {
    jj_scanpos = xsp;
    if (jj_3_58()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3_47() {
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3_30() {
    if (jj_scan_token(PARO)) return true;
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3_37() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_38()) {
    jj_scanpos = xsp;
    if (jj_3_39()) return true;
    }
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3_46() {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_45()) {
    jj_scanpos = xsp;
    if (jj_3_46()) return true;
    }
    return false;
  }

  private boolean jj_3_45() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3_29() {
    if (jj_scan_token(UNKNOWN_BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3_15()) {
    jj_scanpos = xsp;
    if (jj_3_16()) {
    jj_scanpos = xsp;
    if (jj_3_17()) return true;
    }
    }
    }
    if (jj_3R_13()) return true;
    return false;
  }

  private boolean jj_3_28() {
    if (jj_scan_token(UNKNOWN_STRING)) return true;
    return false;
  }

  private boolean jj_3_27() {
    if (jj_scan_token(UNKNOWN_NUMBER)) return true;
    return false;
  }

  private boolean jj_3_25() {
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_26() {
    if (jj_scan_token(BOOLEAN)) return true;
    return false;
  }

  private boolean jj_3_24() {
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3_23() {
    if (jj_3R_18()) return true;
    return false;
  }

  private boolean jj_3_22() {
    if (jj_3R_17()) return true;
    return false;
  }

  private boolean jj_3_21() {
    if (jj_3R_16()) return true;
    return false;
  }

  private boolean jj_3_36() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3_20() {
    if (jj_3R_15()) return true;
    return false;
  }

  private boolean jj_3R_14() {
    Token xsp;
    xsp = jj_scanpos;
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
    if (jj_3_25()) {
    jj_scanpos = xsp;
    if (jj_3_26()) {
    jj_scanpos = xsp;
    if (jj_3_27()) {
    jj_scanpos = xsp;
    if (jj_3_28()) {
    jj_scanpos = xsp;
    if (jj_3_29()) {
    jj_scanpos = xsp;
    if (jj_3_30()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3_41() {
    if (jj_scan_token(INF)) return true;
    return false;
  }

  private boolean jj_3R_22() {
    if (jj_3R_23()) return true;
    return false;
  }

  private boolean jj_3_44() {
    if (jj_scan_token(SUP_EQUAL)) return true;
    return false;
  }

  private boolean jj_3_43() {
    if (jj_scan_token(INF_EQUAL)) return true;
    return false;
  }

  private boolean jj_3_42() {
    if (jj_scan_token(SUP)) return true;
    return false;
  }

  private boolean jj_3_19() {
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3_18() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_14()) return true;
    return false;
  }

  private boolean jj_3R_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_18()) {
    jj_scanpos = xsp;
    if (jj_3_19()) return true;
    }
    return false;
  }

  private boolean jj_3_35() {
    if (jj_scan_token(OR)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_11()) {
    jj_scanpos = xsp;
    if (jj_3_12()) return true;
    }
    if (jj_3R_12()) return true;
    return false;
  }

  private boolean jj_3_38() {
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3R_21() {
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_scan_token(INF)) return true;
    return false;
  }

  private boolean jj_3_39() {
    if (jj_scan_token(NOT_EQUAL)) return true;
    return false;
  }

  private boolean jj_3_17() {
    if (jj_scan_token(SUP_EQUAL)) return true;
    return false;
  }

  private boolean jj_3R_12() {
    if (jj_3R_13()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_13()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_16() {
    if (jj_scan_token(INF_EQUAL)) return true;
    return false;
  }

  private boolean jj_3_15() {
    if (jj_scan_token(SUP)) return true;
    return false;
  }

  private boolean jj_3_9() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  private boolean jj_3R_20() {
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3_11() {
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_scan_token(OR)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  private boolean jj_3R_11() {
    if (jj_3R_12()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_10()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_12() {
    if (jj_scan_token(NOT_EQUAL)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public PreconditionTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;

  /** Constructor with InputStream. */
  public Precondition(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Precondition(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PreconditionTokenManager(jj_input_stream);
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
  public Precondition(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new PreconditionTokenManager(jj_input_stream);
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
  public Precondition(PreconditionTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(PreconditionTokenManager tm) {
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

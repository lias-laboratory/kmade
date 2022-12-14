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

import fr.upensma.lias.kmade.kmad.schema.expression.NodeExpression;
import fr.upensma.lias.kmade.kmad.schema.expression.NotWhileUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.expression.NumberVariant;
import fr.upensma.lias.kmade.kmad.schema.expression.WhileUnaryFunction;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.tool.KMADEConstant;

@SuppressWarnings({"all"})
public class Iteration implements IterationConstants {

  final public NodeExpression expression() throws ParseException {
        NodeExpression node;
    if (jj_2_1(2)) {
      node = iterationExpression();
      jj_consume_token(0);
                                             {if (true) return node;}
    } else if (jj_2_2(2)) {
      jj_consume_token(0);
            {if (true) throw new ParseException(KMADEConstant.NO_EXPRESSION_MESSAGE);}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression iterationExpression() throws ParseException {
        NodeExpression node;
    if (jj_2_3(2)) {
      node = iterationNatural();
                                    {if (true) return node;}
    } else if (jj_2_4(2)) {
      node = iterationFunction();
                                 {if (true) return node;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression iterationNatural() throws ParseException {
        Token myToken;
    jj_consume_token(CROO);
    myToken = jj_consume_token(NUMBER);
    jj_consume_token(CROF);
                {if (true) return new NumberVariant(new NumberValue(myToken.image));}
    throw new Error("Missing return statement in function");
  }

  final public NodeExpression iterationFunction() throws ParseException {
        Token myString;
    if (jj_2_5(2)) {
      jj_consume_token(WHILE);
      myString = jj_consume_token(STRING_LITERAL);
                String momo = myString.image.substring(1,myString.image.length() - 1);
                java.io.StringReader sr = new java.io.StringReader(momo);
            java.io.Reader r = new java.io.BufferedReader(sr);
        Precondition parser = new Precondition(r);
        NodeExpression toto = parser.expression();
                {if (true) return new WhileUnaryFunction(toto);}
    } else if (jj_2_6(2)) {
      jj_consume_token(NOTWHILE);
      myString = jj_consume_token(STRING_LITERAL);
                String momo = myString.image.substring(1,myString.image.length() - 1);
                java.io.StringReader sr = new java.io.StringReader(momo);
            java.io.Reader r = new java.io.BufferedReader(sr);
        Precondition parser = new Precondition(r);
        NodeExpression toto = parser.expression();
                {if (true) return new NotWhileUnaryFunction(toto);}
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

  private boolean jj_3R_2() {
    if (jj_scan_token(CROO)) return true;
    if (jj_scan_token(NUMBER)) return true;
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_2()) return true;
    return false;
  }

  private boolean jj_3R_1() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) return true;
    }
    return false;
  }

  private boolean jj_3_6() {
    if (jj_scan_token(NOTWHILE)) return true;
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_3R_1()) return true;
    return false;
  }

  private boolean jj_3R_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) return true;
    }
    return false;
  }

  private boolean jj_3_5() {
    if (jj_scan_token(WHILE)) return true;
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(0)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public IterationTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;

  /** Constructor with InputStream. */
  public Iteration(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Iteration(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new IterationTokenManager(jj_input_stream);
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
  public Iteration(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new IterationTokenManager(jj_input_stream);
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
  public Iteration(IterationTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(IterationTokenManager tm) {
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

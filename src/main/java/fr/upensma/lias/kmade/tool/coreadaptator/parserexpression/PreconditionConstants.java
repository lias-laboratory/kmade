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


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface PreconditionConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int EXIST = 4;
  /** RegularExpression Id. */
  int EXISTAT = 5;
  /** RegularExpression Id. */
  int EMPTY = 6;
  /** RegularExpression Id. */
  int NOMBRE = 7;
  /** RegularExpression Id. */
  int GETVALUE = 8;
  /** RegularExpression Id. */
  int UNKNOWN_NUMBER = 9;
  /** RegularExpression Id. */
  int UNKNOWN_STRING = 10;
  /** RegularExpression Id. */
  int UNKNOWN_BOOLEAN = 11;
  /** RegularExpression Id. */
  int PARO = 12;
  /** RegularExpression Id. */
  int PARF = 13;
  /** RegularExpression Id. */
  int COMA = 14;
  /** RegularExpression Id. */
  int AND = 15;
  /** RegularExpression Id. */
  int OR = 16;
  /** RegularExpression Id. */
  int EQUAL = 17;
  /** RegularExpression Id. */
  int NOT_EQUAL = 18;
  /** RegularExpression Id. */
  int INF = 19;
  /** RegularExpression Id. */
  int SUP = 20;
  /** RegularExpression Id. */
  int INF_EQUAL = 21;
  /** RegularExpression Id. */
  int SUP_EQUAL = 22;
  /** RegularExpression Id. */
  int NOT = 23;
  /** RegularExpression Id. */
  int EOL = 24;
  /** RegularExpression Id. */
  int DIGIT = 25;
  /** RegularExpression Id. */
  int LETTER = 26;
  /** RegularExpression Id. */
  int BOOLEAN = 27;
  /** RegularExpression Id. */
  int NUMBER = 28;
  /** RegularExpression Id. */
  int NOSPACE = 29;
  /** RegularExpression Id. */
  int WITHSPACE = 30;
  /** RegularExpression Id. */
  int MIDLESPACE = 31;
  /** RegularExpression Id. */
  int FINALSTR = 32;
  /** RegularExpression Id. */
  int IDENT = 33;
  /** RegularExpression Id. */
  int STRING = 34;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"isExist\"",
    "\"isExistAt\"",
    "\"isEmpty\"",
    "\"card\"",
    "\"getValue\"",
    "\"?NUM\"",
    "\"?STR\"",
    "\"?BOOL\"",
    "\"(\"",
    "\")\"",
    "\",\"",
    "\"AND\"",
    "\"OR\"",
    "\"==\"",
    "\"!=\"",
    "\"<\"",
    "\">\"",
    "\"<=\"",
    "\">=\"",
    "\"not\"",
    "\"\\n\"",
    "<DIGIT>",
    "<LETTER>",
    "<BOOLEAN>",
    "<NUMBER>",
    "<NOSPACE>",
    "<WITHSPACE>",
    "<MIDLESPACE>",
    "<FINALSTR>",
    "<IDENT>",
    "<STRING>",
  };

}
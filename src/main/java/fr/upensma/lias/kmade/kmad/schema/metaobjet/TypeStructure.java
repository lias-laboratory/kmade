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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.tache.Enumerated;

/**
 * @author Vincent LUCQUIAUD
 * @author Mickael BARON
 */
public enum TypeStructure implements Enumerated {
	BOOLEAN_STRUCT("BoolValue"), ENUM_STRUCT("EnumValue"), NUMBER_STRUCT("NumberValue"),
	INTERVAL_STRUCT("IntervalleValue"), STRING_STRUCT("StrValue");

	private final String value;

	private TypeStructure(String s) {
		value = s;
	}

	public static String[] getNameLocaleTypeStructureWithoutInterval() {
		String[] myEnumereIntervalStruct = new String[3];
		myEnumereIntervalStruct[0] = ExpressConstant.BOOLEAN_NAME;
		myEnumereIntervalStruct[1] = ExpressConstant.NUMBER_NAME;
		myEnumereIntervalStruct[2] = ExpressConstant.STRING_NAME;
		return myEnumereIntervalStruct;
	}

	public static String[] getNameLocaleTypeStructure() {
		String[] myEnumereIntervalStruct = new String[5];
		myEnumereIntervalStruct[0] = ExpressConstant.BOOLEAN_NAME;
		myEnumereIntervalStruct[1] = ExpressConstant.NUMBER_NAME;
		myEnumereIntervalStruct[2] = ExpressConstant.STRING_NAME;
		myEnumereIntervalStruct[3] = ExpressConstant.INTERVALLE_NAME;
		myEnumereIntervalStruct[4] = ExpressConstant.ENUMERATION_NAME;
		return myEnumereIntervalStruct;
	}

	// A partir d'une chaine Locale transformation en chaine
	// EnumereIntervalStruct.
	public static String getLocaleTypeStructureIntoEnumere(String myStringDecomp) {

		if (myStringDecomp.equals(ExpressConstant.BOOLEAN_NAME))
			return "BoolValue";
		else if (myStringDecomp.equals(ExpressConstant.ENUMERATION_NAME))
			return "EnumValue";
		else if (myStringDecomp.equals(ExpressConstant.NUMBER_NAME))
			return "NumberValue";
		else if (myStringDecomp.equals(ExpressConstant.INTERVALLE_NAME))
			return "IntervalleValue";
		else if (myStringDecomp.equals(ExpressConstant.STRING_NAME))
			return "StrValue";
		else
			return null;
	}

	// A partir d'une chaine EnumereIntervalStruct retourne la chaine locale.
	public static String getEnumereIntoLocaleTypeStructure(String myStringDecomp) {
		if (myStringDecomp.equals("BoolValue"))
			return ExpressConstant.BOOLEAN_NAME;
		else if (myStringDecomp.equals("EnumValue"))
			return ExpressConstant.ENUMERATION_NAME;
		else if (myStringDecomp.equals("NumberValue"))
			return ExpressConstant.NUMBER_NAME;
		else if (myStringDecomp.equals("IntervalleValue"))
			return ExpressConstant.INTERVALLE_NAME;
		else if (myStringDecomp.equals("StrValue"))
			return ExpressConstant.STRING_NAME;
		else if (myStringDecomp.equals("IntValue"))
			return ExpressConstant.NUMBER_NAME;
		else
			return null;
	}

	public org.w3c.dom.Element toXML(Document doc) {
		Element kmadAbstractAttributTypeStructure = doc.createElement("abstractattribut-typestructure");
		kmadAbstractAttributTypeStructure.setTextContent(this.value);
		return kmadAbstractAttributTypeStructure;
	}

	public org.w3c.dom.Element toXML2(Document doc) {
		return this.toXML(doc);
	}

	public static TypeStructure getValue(String s) {
		for (TypeStructure i : TypeStructure.values()) {
			if (s.equalsIgnoreCase(i.value)) {
				return i;
			}
		}

		// Uniquement pour l'ouverture d'ancien projet,
		if (s.equals("IntValue")) {
			s = "NumberValue";
			for (TypeStructure i : TypeStructure.values()) {
				if (s.equalsIgnoreCase(i.value)) {
					return i;
				}
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public String toSPF() {
		return "." + value + ".";
	}
}

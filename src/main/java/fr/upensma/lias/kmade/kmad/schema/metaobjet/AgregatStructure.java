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

import fr.upensma.lias.kmade.kmad.ExpressConstant;

/**
 * @author Mickael BARON
 */
public enum AgregatStructure {
	SET_AGREGAT("Set"), STACK_AGREGAT("Stack"), SINGLETON_AGREGAT("Singleton"), LIST_AGREGAT("List"),
	ARRAY_AGREGAT("Array");

	private final String value;

	private AgregatStructure(String s) {
		value = s;
	}

	public static AgregatStructure getValue(String s) {
		for (AgregatStructure i : AgregatStructure.values()) {
			if (s.equalsIgnoreCase(i.value)) {
				return i;
			}
		}
		return null;
	}

	public static String[] getLocaleAgregatStructures() {
		String[] myEnumereIntervalStruct = new String[5];
		myEnumereIntervalStruct[0] = ExpressConstant.STACK_NAME;
		myEnumereIntervalStruct[1] = ExpressConstant.LIST_NAME;
		myEnumereIntervalStruct[2] = ExpressConstant.SINGLETON_NAME;
		myEnumereIntervalStruct[3] = ExpressConstant.SET_NAME;
		myEnumereIntervalStruct[4] = ExpressConstant.ARRAY_NAME;
		return myEnumereIntervalStruct;
	}

	// A partir d'une chaine Locale transformation en chaine AgregatStruct.
	public static String getLocaleAgregatStructureIntoEnumere(String myStringDecomp) {
		if (myStringDecomp.equals(ExpressConstant.STACK_NAME))
			return "Stack";
		else if (myStringDecomp.equals(ExpressConstant.LIST_NAME))
			return "List";
		else if (myStringDecomp.equals(ExpressConstant.SINGLETON_NAME))
			return "Singleton";
		else if (myStringDecomp.equals(ExpressConstant.SET_NAME))
			return "Set";
		else if (myStringDecomp.equals(ExpressConstant.ARRAY_NAME)) {
			return ("Array");
		} else
			return "Stack";
	}

	// A partir d'une chaine AgregatStruct retourne la chaine locale.
	public static String getEnumereIntoLocaleAgregatStructure(String myStringDecomp) {
		if (myStringDecomp.equals(STACK_AGREGAT.getValue()))
			return ExpressConstant.STACK_NAME;
		else if (myStringDecomp.equals(LIST_AGREGAT.getValue()))
			return ExpressConstant.LIST_NAME;
		else if (myStringDecomp.equals(SINGLETON_AGREGAT.getValue()))
			return ExpressConstant.SINGLETON_NAME;
		else if (myStringDecomp.equals(SET_AGREGAT.getValue()))
			return ExpressConstant.SET_NAME;
		else if (myStringDecomp.equals(ARRAY_AGREGAT.getValue()))
			return ExpressConstant.ARRAY_NAME;
		else
			return ExpressConstant.STACK_NAME;
	}

	public String getValue() {
		return value;
	}
}

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

import java.util.regex.Pattern;

/**
 * Cette classe contient les fonctions qui permettent de v�rifier si un nom est
 * correct
 * 
 * Il est important de reporter les modifications apport�es � cette classe
 * pouvant impacter la calculette dans les classes de la calculette.
 * 
 * @author Thomas Lachaume
 */
public class RegularExpression {

    public static String avecEspace = "[[a-zA-Z]*[0-9]*[�]*[ ]*[�]*[-]*[_]*[�]*[�]*[@]*[*]*[�]*[�]*[�]*[%]*[~]*[#]*[{]*[}]*[|]*[`]*[�]*]";
    // d�finition des caract�res avec le caract�re espace
    public static String sansEspace = "[[a-zA-Z]*[0-9]*[�]*[�]*[-]*[_]*[�]*[�]*[@]*[*]*[�]*[�]*[�]*[%]*[~]*[#]*[{]*[}]*[|]*[`]*[�]*]";
    // construction d'un expression pouvant contenir un espace au milieu
    public static String avecetsansEspace = sansEspace + "+" + avecEspace + "*"
	    + sansEspace + "+";

    // construction de groupeExpression ( ajout d'un nom avec un seul caract�re)
    public static String groupeExpression = sansEspace + "+" + "|"
	    + avecetsansEspace;

    public static boolean isGoodGroupName(String myGroupName) {
	return Pattern.matches(groupeExpression, myGroupName);
    }

    public static boolean isGoodAbstractAttributName(
	    String myAbstractAttributName) {
	return Pattern.matches(groupeExpression, myAbstractAttributName);
    }

    public static boolean isGoodAbstractObjectName(String myAbstractObjectName) {
	return Pattern.matches(groupeExpression, myAbstractObjectName);
    }

    public static boolean isGoodConcretObjectName(String myConcretObjectName) {
	return Pattern.matches(groupeExpression, myConcretObjectName);
    }

    public static boolean isGoodEventObjectName(String myEventObjectName) {
	return Pattern.matches(groupeExpression, myEventObjectName);
    }

    public static boolean isGoodLabelName(String myLabelName) {
	return Pattern.matches(groupeExpression, myLabelName);
    }

    public static boolean isGoodIndividuObjectName(String myIndividuObjectName) {
	return Pattern.matches(groupeExpression, myIndividuObjectName);
    }

    public static boolean isGoodOrganisationObjectName(
	    String myOrganisationObjectName) {
	return Pattern.matches(groupeExpression, myOrganisationObjectName);
    }

    public static boolean isGoodMachineObjectName(String myMachineObjectName) {
	return Pattern.matches(groupeExpression, myMachineObjectName);
    }

    public static boolean isGoodParcMachinesObjectName(
	    String myParcMachinesObjectName) {
	return Pattern.matches(groupeExpression, myParcMachinesObjectName);
    }
}

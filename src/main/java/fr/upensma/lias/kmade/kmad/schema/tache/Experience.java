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
package fr.upensma.lias.kmade.kmad.schema.tache;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;

/**
 * Type enumerer representant l'experience d'un utilisateur. Trois niveaux sont
 * predefinis, EXPERT("HIGH"), MOYEN("MIDDLE"), NOVICE("LOW", et un niveau
 * INCONNU("UNK") est prevu pour l'absence de definition L'ordre croissant des
 * valeurs est INCONNU-EXPERT-MOYEN-NOVICE
 * 
 * @author Delphine AUTARD
 * @author Mickaël BARON
 */
public enum Experience implements Enumerated {
	INCONNU("UNK"), EXPERT("HIGH"), MOYEN("MIDDLE"), NOVICE("LOW");

	private final String value;

	/**
	 * Constructeur de la classe Aucune verification n'est faite a la creation !
	 * 
	 * @param s chaine de caractere supposee representer l'enumere
	 */
	private Experience(String s) {
		value = s;
	}

	/**
	 * @param s String chaine de caractere supposee representer un element de
	 *          l'enumere
	 * @return la valeur de l'enumere si la conversion est ok, null sinon
	 */
	public static Experience getValue(String s) {
		for (Experience i : Experience.values()) {
			if (s.equalsIgnoreCase(i.value)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * @return tableau de chaines contenant les valeurs localisees
	 */
	public static String[] getNameLocaleExperience() {
		String[] myExperienceArray = new String[4];
		myExperienceArray[0] = ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
		myExperienceArray[1] = ExpressConstant.HIGH_EXPERIENCE_NAME;
		myExperienceArray[2] = ExpressConstant.AVERAGE_EXPERIENCE_NAME;
		myExperienceArray[3] = ExpressConstant.LOW_EXPERIENCE_NAME;
		return myExperienceArray;
	}

	public static String getEnumereIntoLocaleExperience(String exp) {
		if (exp.equals("UNK"))
			return ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
		else if (exp.equals("HIGH"))
			return ExpressConstant.HIGH_EXPERIENCE_NAME;
		else if (exp.equals("MIDDLE"))
			return ExpressConstant.AVERAGE_EXPERIENCE_NAME;
		else if (exp.equals("LOW"))
			return ExpressConstant.LOW_EXPERIENCE_NAME;
		else
			return ExpressConstant.UNKNOWN_EXPERIENCE_NAME;
	}

	public static String getLocaleExperienceIntoEnumerate(String experience) {
		if (experience.equals(ExpressConstant.UNKNOWN_EXPERIENCE_NAME)) {
			return "UNK";
		} else if (experience.equals(ExpressConstant.HIGH_EXPERIENCE_NAME)) {
			return "HIGH";
		} else if (experience.equals(ExpressConstant.AVERAGE_EXPERIENCE_NAME)) {
			return "MIDDLE";
		} else if (experience.equals(ExpressConstant.LOW_EXPERIENCE_NAME)) {
			return "LOW";
		}
		return "UNK";
	}

	public org.w3c.dom.Element toXML2(Document doc) {
		Element kmadActorExperience = doc.createElement("actor-experience");
		kmadActorExperience.setTextContent(this.value);
		return kmadActorExperience;
	}

	public static Experience getXMLExperienceValue(org.w3c.dom.Element p) {
		NodeList nodeList = p.getElementsByTagName("actor-experience");
		if (nodeList != null && nodeList.item(0) != null && nodeList.item(0).getParentNode() != p) {
			nodeList = null;
		}
		if (nodeList.item(0) == null) {
			return INCONNU;
		}
		String value = (String) nodeList.item(0).getTextContent();

		for (Experience i : Experience.values()) {
			if (value.equalsIgnoreCase(i.value)) {
				return i;
			}
		}
		return INCONNU;
	}

	public String toSPF() {
		return "." + value + ".";
	}

	public String getValue() {
		return value;
	}

}

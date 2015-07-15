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
 * @author Delphine AUTARD
 * @author Mickael BARON
 */
public enum Executor implements Enumerated {
    INCONNU("UNK", ExpressConstant.UNKNOWN_TASK_16_IMAGE), USER("USER",
	    ExpressConstant.USER_TASK_16_IMAGE), SYS("SYS",
	    ExpressConstant.FEEDBACK_TASK_16_IMAGE), INT("INT",
	    ExpressConstant.INTERACTIF_TASK_16_IMAGE), ABS("ABS",
	    ExpressConstant.ABSTRACT_TASK_16_IMAGE);

    private final String value;

    private final String image;

    private Executor(String s, String pimage) {
	value = s;
	image = pimage;
    }

    public String getImage() {
	return image;
    }

    public String getValue() {
	return value;
    }

    public String toString() {
	return Executor.getEnumereIntoLocaleExecutor(value);
    }

    public static Executor getXMLExecutorValue(org.w3c.dom.Element p) {

	NodeList nodeList = p.getElementsByTagName("task-executant");
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}
	String value = (String) nodeList.item(0).getTextContent();

	for (Executor i : Executor.values()) {
	    if (value.equalsIgnoreCase(i.value)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public static Executor getXMLExecutorValue2(org.w3c.dom.Element p) {

	NodeList nodeList = p.getElementsByTagName("task-executant");
	if (nodeList != null && nodeList.item(0) != null
		&& nodeList.item(0).getParentNode() != p) {
	    return INCONNU;
	}
	if (nodeList.item(0) == null) {
	    return INCONNU;
	}
	String value = (String) nodeList.item(0).getTextContent();

	for (Executor i : Executor.values()) {
	    if (value.equalsIgnoreCase(i.value)) {
		return i;
	    }
	}
	return INCONNU;
    }

    public static Executor getValue(String s) {
	for (Executor i : Executor.values()) {
	    if (s.equalsIgnoreCase(i.value)) {
		return i;
	    }
	}
	return null;
    }

    public org.w3c.dom.Element toXML2(Document doc) {
	Element kmadTaskExecutant = doc.createElement("task-executant");
	kmadTaskExecutant.setTextContent(this.value);
	return kmadTaskExecutant;
    }

    public String toSPF() {
	return "." + value + ".";
    }

    // Retourne un tableau de String.
    public static String[] getNameLocaleExecutor() {
	String[] myExecutorArray = new String[5];
	myExecutorArray[0] = ExpressConstant.UNKNOWN_EXECUTANT_NAME;
	myExecutorArray[1] = ExpressConstant.USER_EXECUTANT_NAME;
	myExecutorArray[2] = ExpressConstant.SYSTEM_EXECUTANT_NAME;
	myExecutorArray[3] = ExpressConstant.INTERACTION_EXECUTANT_NAME;
	myExecutorArray[4] = ExpressConstant.ABSTRACT_EXECUTANT_NAME;
	return myExecutorArray;
    }

    public static String[] getImageLocaleExecutor() {
	String[] myExecutorArray = new String[5];
	myExecutorArray[0] = Executor.INCONNU.getImage();
	myExecutorArray[1] = Executor.USER.getImage();
	myExecutorArray[2] = Executor.SYS.getImage();
	myExecutorArray[3] = Executor.INT.getImage();
	myExecutorArray[4] = Executor.ABS.getImage();
	return myExecutorArray;
    }

    public static Executor getLocaleExecutorIntoExecutor(String myStringExecutor) {
	if (myStringExecutor.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME))
	    return Executor.INCONNU;
	else if (myStringExecutor.equals(ExpressConstant.USER_EXECUTANT_NAME))
	    return Executor.USER;
	else if (myStringExecutor.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
	    return Executor.SYS;
	else if (myStringExecutor
		.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
	    return Executor.INT;
	else if (myStringExecutor
		.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
	    return Executor.ABS;
	else
	    return Executor.INCONNU;
    }

    public static String getLocaleExecutorIntoEnumere(String myStringExecutor) {
	if (myStringExecutor.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME))
	    return "UNK";
	else if (myStringExecutor.equals(ExpressConstant.USER_EXECUTANT_NAME))
	    return "USER";
	else if (myStringExecutor.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
	    return "SYS";
	else if (myStringExecutor
		.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
	    return "INT";
	else if (myStringExecutor
		.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
	    return "ABS";
	else
	    return "UNK";
    }

    public static int getLocaleExecutorAt(String myStringExecutor) {
	if (myStringExecutor.equals(ExpressConstant.UNKNOWN_EXECUTANT_NAME))
	    return 0;
	else if (myStringExecutor.equals(ExpressConstant.USER_EXECUTANT_NAME))
	    return 1;
	else if (myStringExecutor.equals(ExpressConstant.SYSTEM_EXECUTANT_NAME))
	    return 2;
	else if (myStringExecutor
		.equals(ExpressConstant.INTERACTION_EXECUTANT_NAME))
	    return 3;
	else if (myStringExecutor
		.equals(ExpressConstant.ABSTRACT_EXECUTANT_NAME))
	    return 4;
	else
	    return 0;
    }

    public static String getEnumereIntoLocaleExecutor(String myStringExecutor) {
	if (myStringExecutor.equals("UNK"))
	    return ExpressConstant.UNKNOWN_EXECUTANT_NAME;
	else if (myStringExecutor.equals("USER"))
	    return ExpressConstant.USER_EXECUTANT_NAME;
	else if (myStringExecutor.equals("SYS"))
	    return ExpressConstant.SYSTEM_EXECUTANT_NAME;
	else if (myStringExecutor.equals("INT"))
	    return ExpressConstant.INTERACTION_EXECUTANT_NAME;
	else if (myStringExecutor.equals("ABS"))
	    return ExpressConstant.ABSTRACT_EXECUTANT_NAME;
	else
	    return null;
    }

    public static int getEnumereIntoLocaleAt(String myStringExecutant) {
	if (myStringExecutant.equals("UNK"))
	    return 0;
	else if (myStringExecutant.equals("USER"))
	    return 1;
	else if (myStringExecutant.equals("SYS"))
	    return 2;
	else if (myStringExecutant.equals("INT"))
	    return 3;
	else if (myStringExecutant.equals("ABS"))
	    return 4;
	else
	    return 0;
    }

}

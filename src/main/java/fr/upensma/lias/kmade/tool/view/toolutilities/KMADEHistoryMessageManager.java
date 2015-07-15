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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.util.ArrayList;

/**
 * @author Mickael BARON
 */
public class KMADEHistoryMessageManager {

    private static ArrayList<MessageIO> myMessagePanels = new ArrayList<MessageIO>();
    
    private static ArrayList<MessageIO> myErrorPanels = new ArrayList<MessageIO>();

    KMADEHistoryMessageManager() {

    }

    /**
     * @param aPanel
     */
    public static void setOutputMessage(MessageIO aPanel) {
	if (myMessagePanels.contains(aPanel)) {
	    myMessagePanels.remove(aPanel);
	}
	myMessagePanels.add(0, aPanel);
    }

    public static void setErrputMessage(MessageIO aPanel) {
	if (myErrorPanels.contains(aPanel)) {
	    myErrorPanels.remove(aPanel);
	}
	myMessagePanels.add(0, aPanel);
    }

    public static void remove(MessageIO aPanel) {
	if (myMessagePanels.contains(aPanel)) {
	    myMessagePanels.remove(aPanel);
	}
    }

    public static void printlnMessage(String message) {
	if (!myMessagePanels.isEmpty()) {
	    myMessagePanels.get(0).printlnMessage(message);
	} else {
	    System.out.println(message);
	}
    }

    public static void printMessage(String message) {
	if (!myMessagePanels.isEmpty()) {
	    myMessagePanels.get(0).printlnMessage(message);
	} else {
	    System.out.print(message);
	}
    }

    public static void printlnError(String message) {
	if (!myErrorPanels.isEmpty()) {
	    myErrorPanels.get(0).printlnError(message);
	} else {
	    System.err.println(message);
	}
    }

    public static void printlnError(Throwable message) {
	if (!myErrorPanels.isEmpty()) {
	    myErrorPanels.get(0).printlnError(message);
	} else {
	    System.err.println(message);
	}
    }
}

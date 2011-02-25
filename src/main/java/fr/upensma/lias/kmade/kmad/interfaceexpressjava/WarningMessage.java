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
package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class WarningMessage implements AbstractMessage {

    private static ArrayList<Integer> listInteger = new ArrayList<Integer>();

    private static ArrayList<String> listName = new ArrayList<String>();

    private static ArrayList<Oid> listOid = new ArrayList<Oid>();

    private static ArrayList<String> listMessages = new ArrayList<String>();

    public static final String[] messWarning = {
	    ExpressConstant.REMOVE_ABSTRACT_OBJECT, // 0
	    ExpressConstant.REMOVE_ABSTRACT_ATTRIBUT, // 1
	    ExpressConstant.REMOVE_GROUP, // 2
	    ExpressConstant.REMOVE_USER, // 3
	    ExpressConstant.REMOVE_EVENT, // 4
	    ExpressConstant.REMOVE_CONCRETE_OBJECT, // 5
	    ExpressConstant.REMOVE_CONCRETE_ATTRIBUT, // 6
	    ExpressConstant.NO_USED_GROUP_ATTRIBUT, // 7
	    ExpressConstant.NO_USED_ABSTRACT_OBJECT, // 8
	    ExpressConstant.REMOVE_ACTOR, // 9
	    ExpressConstant.REMOVE_ENUMERATION, // 10
	    ExpressConstant.REMOVE_ELEMENT, // 11
	    ExpressConstant.REMOVE_INTERVALLE, // 12
	    ExpressConstant.REMOVE_LABEL, // 13
	    ExpressConstant.CHANGE_AGREGAT, // 14
	    ExpressConstant.REMOVE_ACTOR_SYSTEM, // 15
	    ExpressConstant.REMOVE_MATERIEL // 16
    };

    public void addMessage(Oid oid, int idWarning) {
	this.addMessage(oid, idWarning, "");
    }

    public void addMessage(Oid oid, int idWarning, String other) {
	Entity e = (Entity) InterfaceExpressJava.prendre(oid);
	WarningMessage.listOid.add(oid);
	WarningMessage.listName.add(e.getName());
	WarningMessage.listInteger.add(idWarning);
	WarningMessage.listMessages.add(other);
    }

    public String[] getMessages() {
	String[] mess = new String[WarningMessage.listOid.size()];
	for (int i = 0; i < WarningMessage.listOid.size(); i++) {
	    mess[i] = messWarning[WarningMessage.listInteger.get(i)] + " \""
		    + WarningMessage.listName.get(i) + "\" "
		    + WarningMessage.listMessages.get(i);
	}
	return mess;
    }

    public boolean isMessages() {
	return (WarningMessage.listOid.size() == 0);
    }

    public void displayMessages() {
	String[] str = getMessages();
	for (int i = 0; i < str.length; i++) {
	    System.out.println("ATTENTION " + str[i]);
	}
	listOid.clear();
	listInteger.clear();
	listMessages.clear();
	listName.clear();
    }

    public String[] takeMessages() {
	String[] str = getMessages();
	listOid.clear();
	listInteger.clear();
	listMessages.clear();
	listName.clear();
	return str;
    }
}

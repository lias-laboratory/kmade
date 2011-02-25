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

import java.util.TreeMap;

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public class ClipBoardDB extends TreeMap<Oid, Object> {  

    private static final long serialVersionUID = -4065187555842667783L;

    private static Oid oid = new Oid(1);

    public Oid put(Object value) {
	Oid leoid = new Oid(oid);
	put(leoid, value);

	oid.plus();
	return leoid;
    }

    public void mettre(Oid key, Object value) {
	put(new Oid(key), value);
    }

    public Object prendre(Oid key) {
	return get(key);
    }

    /**
     * Supprime l'intégralité de la BDD.
     */
    public void clear() {
	// L'oid principale est mise à 1.
	ClipBoardDB.oid = new Oid(1);
	// Utilisation de la méthode de la super-classe pour supprimer
	// l'intégralité.
	super.clear();
    }

    public void supprimer(Oid key) {
	this.remove(key);
    }

    public Oid getLastOid() {
	return oid;
    }
}

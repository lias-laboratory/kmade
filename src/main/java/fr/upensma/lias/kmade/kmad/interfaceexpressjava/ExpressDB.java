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
package fr.upensma.lias.kmade.kmad.interfaceexpressjava;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;

/**
 * @author Mickael BARON
 */
public class ExpressDB extends TreeMap<Oid, Object> implements Serializable {

    private static final long serialVersionUID = 1254529829201249621L;

    private static Oid oid = new Oid(1);

    private static boolean isSet;

    public ExpressDB() {
	super();
	// Bdd est propre.
	ExpressDB.isSet = false;
    }

    public Oid put(Object value) {
	Oid leoid = new Oid(oid);
	put(leoid, value);
	oid.plus();

	ExpressDB.isSet = true;

	return leoid;
    }

    public void mettre(Oid key, Object value) {
	put(new Oid(key), value);

	ExpressDB.isSet = true;
    }

    public int getMax() {
	Set<Oid> set = this.keySet();
	Iterator<Oid> i;
	int max = 0;
	for (i = set.iterator(); i.hasNext();) {
	    Oid oid = (Oid) i.next();
	    if (oid.getValue() > max)
		max = oid.getValue();
	}

	return max;
    }

    public Object prendre(Oid key) {
	return get(key);
    }

    /**
     * Supprime l'intégralité de la BDD.
     */
    public void clear() {
	// L'oid principale est mise à 1.
	ExpressDB.oid = new Oid(1);
	super.clear();

	ExpressDB.isSet = false;
    }

    public void supprimer(Oid key) {
	this.remove(key);

	ExpressDB.isSet = true;
    }

    public Oid getLastOid() {
	return oid;
    }

    public void loadSPFFinished() {
	oid = new Oid(((Oid) super.lastKey()).get()).plus();
    }

    /**
     * Permet de savoir si la base a �t� modifi� ou pas.
     * 
     * @return Returns the isSet.
     */
    public static boolean isSet() {
	return isSet;
    }

    public static void setSetOn() {
	isSet = true;
    }

    public static void setSetOff() {
	isSet = false;
    }

    public void out() {
	Set<Oid> set = this.keySet();
	Iterator<Oid> i;
	for (i = set.iterator(); i.hasNext();) {
	    Oid oid = (Oid) i.next();
	    Object o = this.prendre(oid);
	    KMADEHistoryMessageManager.printlnMessage(((Entity) o).toSPF());
	}
    }

}

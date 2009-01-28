package kmade.nmda.interfaceexpressjava;

import java.util.TreeMap;

import kmade.nmda.schema.Oid;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
        // Utilisation de la méthode de la super-classe pour supprimer l'intégralité.
        super.clear();
    }

    public void supprimer(Oid key) {
        this.remove(key);
    }

    public Oid getLastOid() {
        return oid;
    }
}


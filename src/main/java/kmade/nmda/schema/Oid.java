package kmade.nmda.schema;

import java.io.Serializable;

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
 * @authors Vincent Lucquiaud and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class Oid implements Comparable, Serializable {
    private static final long serialVersionUID = -5084335304591633333L;

    public static final String OID_NULL = "null";

    private int numoid = 1;

    public Oid() { }

    public Oid(int i) {
        numoid = i;
    }

    public Oid(String st) {
        if (st.charAt(0) != '#') {
            System.err.println("Erreur dans chaine pour oid st=" + st);
        }
        Integer in = Integer.valueOf(Integer.parseInt(st.substring(1)));
        numoid = in.intValue();
    }

    public Oid(Oid oid) {
        this(oid.get());
    }

    public String get() {
        return ("#" + String.valueOf(numoid));
    }

    public String toString() {
        return String.valueOf(numoid);
    }

    public Oid plus() {
        numoid++;
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Oid))
            return false;
        return (this.numoid == ((Oid) obj).numoid);
    }

    public int compareTo(Object o) {
        Oid n = (Oid) o;
        if (this.numoid < n.numoid)
            return -1;
        if (this.numoid > n.numoid)
            return 1;
        return 0;
    }

    public int getValue() {
        return this.numoid;
    }
}

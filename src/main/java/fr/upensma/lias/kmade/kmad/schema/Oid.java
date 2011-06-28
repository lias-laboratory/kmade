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
package fr.upensma.lias.kmade.kmad.schema;

import java.io.Serializable;

/**
 * @author Vincent LUCQUIAUD et Mickael BARON
 */
public class Oid implements Comparable<Object>, Serializable {
    private static final long serialVersionUID = -5084335304591633333L;

    public static final String OID_NULL = "null";

    private int numoid = 1;

    public Oid() {
    }

    public Oid(int i) {
	numoid = i;
    }

    public Oid(String st) {
    	//Previous and actual version of the dtd
	if (st.charAt(0) != '#' || st.charAt(0) != 'K') {
	    System.err.println("Erreur dans chaine pour oid st=" + st);
	}
	Integer in = Integer.valueOf(Integer.parseInt(st.substring(1)));
	numoid = in.intValue();
    }

    public Oid(Oid oid) {
	this(oid.get());
    }

    public String get() {
	return ("K" + String.valueOf(numoid));
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

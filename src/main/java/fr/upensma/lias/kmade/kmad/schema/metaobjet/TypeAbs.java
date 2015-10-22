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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @author Mickael BARON
 */
public abstract class TypeAbs implements Entity {

    private static final long serialVersionUID = -1691961187731708342L;

    public Oid oid = null;

    protected String name = null;

    protected String description = null;

    public ArrayList<AttributAbstrait> inverseAttributAbs = new ArrayList<AttributAbstrait>();

    public abstract ArrayList<String> getValues();

    public void addInverseAttributAbs(AttributAbstrait attributabs) {
	inverseAttributAbs.add(attributabs);
    }

    public void removeInverseAttributAbs(AttributAbstrait attributabs) {
	inverseAttributAbs.remove(attributabs);
    }

    public ArrayList<AttributAbstrait> getInverseAttributAbs() {
	return this.inverseAttributAbs;
    }

    public void setOid(Oid oid) {
	this.oid = oid;
    }

    public Oid getOid() {
	return this.oid;
    }

    public String getName() {
	return name;
    }

    public void setDescription(String n) {
	description = n;
    }

    public String getDescription() {
	return description;
    }
}

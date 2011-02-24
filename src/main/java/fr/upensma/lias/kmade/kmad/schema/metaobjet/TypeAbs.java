package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;


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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
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

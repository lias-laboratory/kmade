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
package fr.upensma.lias.kmade.kmad.schema.tache;

import fr.upensma.lias.kmade.kmad.schema.expression.VoidConstant;

/**
 * @author Mickael BARON
 */
public class EffetsDeBordExpression extends Expression {

    private static final long serialVersionUID = 1083101442297410483L;

    public EffetsDeBordExpression() {
	refNode = new VoidConstant();
	chaine = refNode.getName();
    }

    public EffetsDeBordExpression(String pre) {
	this.chaine = pre;
	if (this.chaine.equals("")) {
	    refNode = new VoidConstant();
	    chaine = refNode.getName();
	}
    }
}

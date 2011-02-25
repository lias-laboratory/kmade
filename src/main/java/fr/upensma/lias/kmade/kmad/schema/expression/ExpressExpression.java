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
package fr.upensma.lias.kmade.kmad.schema.expression;

/**
 * @author Mickael BARON
 */
public abstract class ExpressExpression extends UnaryExpression {

    private static final long serialVersionUID = 7446474724360755466L;

    protected String identExpress;

    public ExpressExpression(String pie) {
	super(new Object());
	this.identExpress = pie;
	this.name = "$" + identExpress;
    }

    public void checkNode() throws SemanticException {
	// Ne fait rien ...
    }

    public String toString() {
	return super.toString();
    }
}

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
package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressGroup;

/**
 * @author Mickael BARON
 */
public class GroupExpressExpression extends ExpressExpression {

	private static final long serialVersionUID = -3796792735826302638L;

	private Groupe myGroup;

	public GroupExpressExpression(String ie) throws ExpressException {
		super(ie);
		myGroup = ExpressGroup.getRefAbstractGroupFromName(ie);
		if (myGroup == null) {
			throw new ExpressException(ExpressConstant.GROUP_MESSAGE + " \"" + this.identExpress + "\" "
					+ ExpressConstant.NOT_FOUNDED_ERROR + ".");
		}
	}

	public Groupe getGroup() {
		return myGroup;
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {
	}
}

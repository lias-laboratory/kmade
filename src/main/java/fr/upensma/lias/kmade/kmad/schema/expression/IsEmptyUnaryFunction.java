package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

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
public class IsEmptyUnaryFunction extends UnaryFunction {

    private static final long serialVersionUID = 2040793276663813914L;

    public IsEmptyUnaryFunction(GroupExpressExpression pee) {
		super(false,pee);
		this.name = ExpressConstant.IS_EMPTY_UNARY_FUNCTION_EXPRESSION;
	}
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        this.setNodeValue(((GroupExpressExpression)myAloneExpression).getGroup().getEnsemble().isEmpty());        
    }
}

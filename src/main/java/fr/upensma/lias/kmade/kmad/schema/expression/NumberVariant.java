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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class NumberVariant extends VariableExpression {

    private static final long serialVersionUID = 1207077613054213868L;

    public NumberVariant() {
	this(new NumberValue("1"));
    }

    public NumberVariant(int value) {
	super(new NumberValue(value));
	this.setStateToValue();
	this.name = ExpressConstant.INTEGER_VARIANT_EXPRESSION;
    }

    public NumberVariant(NumberValue value) {
	super(value);
	this.setStateToValue();
	this.name = ExpressConstant.INTEGER_VARIANT_EXPRESSION;
    }

    public void checkNode() throws SemanticException {
    }

    public void evaluateNode(ObjetConcret ref) throws SemanticException {
    }
}

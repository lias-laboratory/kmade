package kmade.nmda.schema.expression;

import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
public class IntegerVariant extends VariableExpression {

    private static final long serialVersionUID = 1207077613054213868L;

    public IntegerVariant() {
        this(new Integer(1));
    }
    
    public IntegerVariant(int value) {
        super(new Integer(value <= 0 ? 1 : value));
        this.setStateToValue();
        this.name = ExpressConstant.INTEGER_VARIANT_EXPRESSION;
    }
    
    public void checkNode() throws SemanticException { }

    public void evaluateNode(ObjetConcret ref) throws SemanticException { }
}

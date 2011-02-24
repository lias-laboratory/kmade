package kmade.nmda.schema.expression;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class WhileUnaryFunction extends UnaryFunction {

    private static final long serialVersionUID = -2557504737851443643L;

    public WhileUnaryFunction(NodeExpression pee) {
        super(false,pee);
        this.name = "while";
    }
    
    public void checkNode() throws SemanticException { 
        myAloneExpression.checkNode();
    }
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        this.setNodeValue(this.myAloneExpression.getNodeValue());
    }
}

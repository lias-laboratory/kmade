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
public class NotUnaryFunction extends UnaryFunction {

    private static final long serialVersionUID = 6844275161051663640L;

    public NotUnaryFunction(NodeExpression pnode) {
		super(false,pnode);
        this.name = ExpressConstant.NOT_UNARY_EXPRESSION;
	}
    
    public void checkNode() throws SemanticException {
        super.checkNode();
        
        if (this.getAloneExpression().isBoolean()) {                       
            this.setStateToUnknown();
            return;
        }
        // Y a une erreur de type.
        this.setStateToError();
        throw new SemanticException(ExpressConstant.TYPE_NEED_BOOLEAN);
    } 
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        if (this.isValueState()) {
            this.setNodeValue(new Boolean(!(Boolean)this.getAloneExpression().getNodeValue()));
        }
    }
}

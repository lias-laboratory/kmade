package fr.upensma.lias.kmade.kmad.schema.expression;

import java.util.ArrayList;

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
public class LengthFunction extends BinaryFunction {

    private static final long serialVersionUID = -891728359658195164L;

    public LengthFunction(GroupExpressExpression left, NodeExpression right) {
        super(new Integer(0), left, right);
        this.name = ExpressConstant.LENGTH_FUNCTION_EXPRESSION;
    }
    
    public void checkNode() throws SemanticException {
        super.checkNode();
        
        if (this.rightNode.isBoolean()) {
            this.setStateToUnknown();
            return;
        }
        this.setStateToError();
        throw new SemanticException(ExpressConstant.BOOLEAN_IS_NEEDED_ERROR + " : " + this.getName());
    }
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        ArrayList<ObjetConcret> list = ((GroupExpressExpression)this.getLeftNode()).getGroup().getEnsemble().getLstObjConcrets();
        
        int compteur = 0;
        for (ObjetConcret current : list) {
            super.evaluateNode(current);
            
            if (this.isValueState()) {
                if (this.rightNode.isBoolean()) {                
                    if ((Boolean)this.rightNode.getNodeValue()) {
                        compteur++;
                    }
                }
            } else {
    				throw new SemanticErrorException();
            }
        }
        if (this.isValueState()) {
            this.setNodeValue(compteur);
        }
    }
}

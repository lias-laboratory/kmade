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
public class InfOperator extends RelationalOperator {

    private static final long serialVersionUID = -1311669179618639600L;

    public InfOperator(NodeExpression left) {
        super(left);
        this.name = ExpressConstant.INF_OPERATOR_EXPRESSION;
    }    

    public void evaluateNode(ObjetConcret ref) throws SemanticException { 
		super.evaluateNode(ref);
        
		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}
		
		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}
		
        if (getLeftNode().isInteger() && getRightNode().isString()) {
            this.setNodeValue(new Boolean(((Integer)getLeftNode().getNodeValue()).intValue() < (new Integer((String)getRightNode().getNodeValue())).intValue()));
            return;
        }
        
        if (getLeftNode().isString() && getRightNode().isInteger()) {
            this.setNodeValue(new Boolean(((new Integer((String)getLeftNode().getNodeValue())).intValue()) < (((Integer)getRightNode().getNodeValue()).intValue())));
            return;
        }
        
        // �galement � prendre en compte : Comparaison entre deux string � valeurs enti�res ou double
        if (getLeftNode().isString() && getRightNode().isString()) {
            this.setNodeValue(new Boolean(((new Integer((String)getLeftNode().getNodeValue())).intValue()) < ((new Integer((String)getRightNode().getNodeValue())).intValue())));
            return;
        }                    
        
        if (getLeftNode().isInteger() && getRightNode().isInteger()) {
            this.setNodeValue(new Boolean(((Integer)getLeftNode().getNodeValue()).intValue() < ((Integer)getRightNode().getNodeValue()).intValue()));
            return;
        }
    }
    
    public String toString() {
        return super.toString();
    }
}

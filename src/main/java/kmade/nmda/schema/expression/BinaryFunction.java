package kmade.nmda.schema.expression;

import java.util.ArrayList;

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
public class BinaryFunction extends BinaryExpression {

    private static final long serialVersionUID = -3402788153213192714L;

    public BinaryFunction(Object value, GroupExpressExpression left, NodeExpression right) {
        super(value,left, right);
    }    
    
    public ArrayList<Object> getLinearExpression() {
        ArrayList<Object> myLinearList = new ArrayList<Object>();
        myLinearList.add(this.getName());
        myLinearList.add("(");
        for (Object current:leftNode.getLinearExpression()) {
            myLinearList.add(current);
        }
        myLinearList.add(",");
        for (Object current:rightNode.getLinearExpression()) {
            myLinearList.add(current);
        }
        myLinearList.add(")");
        return myLinearList;
    }
    
	public void evaluateNode(ObjetConcret ref) throws SemanticException {
		this.rightNode.evaluateNode(ref);
        // Plus simple qu'opérateur car seul le membre de droite est consid�r�.
		this.setObjectValueState(this.getRightNode().getObjectValueState());
	}	
	   
	public void checkNode() throws SemanticException {
		this.rightNode.checkNode();
	}
}

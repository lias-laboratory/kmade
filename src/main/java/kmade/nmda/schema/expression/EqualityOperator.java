package kmade.nmda.schema.expression;

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
public class EqualityOperator extends ComparisonOperator {

    private static final long serialVersionUID = 3907268708910997841L;

    public EqualityOperator(NodeExpression left) {
		super(false, left);
	}    
    
	public void checkNode() throws SemanticException {
		super.checkNode();

        if (this.getLeftNode().isInteger() && this.getRightNode().isString()) {
            try {   
                new Integer((String)this.getRightNode().getNodeValue()).intValue();
                this.setNodeType(true);
                return;
            } catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }
        
        if (this.getLeftNode().isString() && this.getRightNode().isInteger()) {
            try {   
                new Integer((String)this.getLeftNode().getNodeValue()).intValue();
                this.setNodeType(true);
                return;
            }
            catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }
        
        if (this.getLeftNode().isString() && this.getRightNode().isString()) {
            this.setNodeType(true);
            return;
        }
        
        if (this.getLeftNode().isInteger() && this.getRightNode().isInteger()) {
            this.setNodeType(true);
            return;
        }

        if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
            this.setNodeType(true);
            return;
        }
               
        if (this.getLeftNode().isBoolean() && this.getRightNode().isInteger()) {
            this.setNodeType(true);
            return;
        }

        if (this.getLeftNode().isInteger() && this.getRightNode().isBoolean()) {
            this.setNodeType(true);
            return;            
        }

		this.setStateToError();
		throw new SemanticException();
	}
}

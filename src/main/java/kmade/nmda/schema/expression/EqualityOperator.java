package kmade.nmda.schema.expression;

import kmade.nmda.ExpressConstant;

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
public class EqualityOperator extends ComparisonOperator {

	private static final long serialVersionUID = 3907268708910997841L;

	public EqualityOperator(NodeExpression left) {
		super(false, left);
	}    

	public void checkNode() throws SemanticException {
		super.checkNode();

		/* cast d'un String en nombre autorisé en enlevant le commentaire
        if (this.getLeftNode().isNumber() && this.getRightNode().isString()) {

            try {   
                new NumberValue((String)this.getRightNode().getNodeValue());
                this.setNodeType(true);
                return;
            } catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }
		 */
		/* cast d'un String en nombre autorisé en enlevant le commentaire
        if (this.getLeftNode().isString() && this.getRightNode().isNumber()) {
            try {   
                new NumberValue((String)this.getLeftNode().getNodeValue());
                this.setNodeType(true);
                return;
            }
            catch (NumberFormatException e) {
                this.setStateToError();
                throw new SemanticException();
            }
        }
		 */
		if (this.getLeftNode().isString() && this.getRightNode().isString()) {
			this.setNodeType(true);
			return;
		}

		if (this.getLeftNode().isNumber() && this.getRightNode().isNumber()) {
			this.setNodeType(true);
			return;
		}

		if (this.getLeftNode().isBoolean() && this.getRightNode().isBoolean()) {
			this.setNodeType(true);
			return;
		}
		/*  cast d'un Nombre en Boolean autorisé en enlevant le commentaire    
        if (this.getLeftNode().isBoolean() && this.getRightNode().isNumber()) {
            this.setNodeType(true);
            return;
        }
		 */
		/* cast d'un Nombre en Boolean autorisé en enlevant le commentaire 
        if (this.getLeftNode().isNumber() && this.getRightNode().isBoolean()) {
            this.setNodeType(true);
            return;            
        }
		 */
		this.setStateToError();
		throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
	}
}

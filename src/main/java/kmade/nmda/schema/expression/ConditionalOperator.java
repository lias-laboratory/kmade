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
public class ConditionalOperator extends Operator {

    private static final long serialVersionUID = 2290563616278127681L;

    public ConditionalOperator(NodeExpression left) {
        super(false,left);
    }

    public void checkNode() throws SemanticException {
        super.checkNode();
        
        if (getLeftNode().isBoolean() && getRightNode().isBoolean()) {
                this.setNodeValue(false);
                this.setStateToUnknown();
                return;
        }
    
        /* On n'autorise pas le cast d'un String en booléen
        if (getLeftNode().isBoolean() && getRightNode().isString()) {
            try 
            {
                new Boolean((String)getRightNode().getNodeValue()).booleanValue();
                this.setNodeValue(false);
                this.setStateToUnknown();
                return;
            }
            catch (NumberFormatException e) {
            		this.setStateToError();
                throw new SemanticException();
            }
        }
        */
        /* On n'autorise pas le cast d'un String en booléen           
        if (getLeftNode().isString() && getRightNode().isBoolean()) {
            try 
            {
                new Boolean((String)getLeftNode().getNodeValue()).booleanValue();
                this.setNodeValue(true);
                this.setStateToUnknown();
                return;
            }
            catch (NumberFormatException e) {
            		this.setStateToError();
            		throw new SemanticException();
            }
        }           
        */
        /*  On n'autorise pas le cast d'un String en booléen
        if (getLeftNode().isString() && getRightNode().isString()) {
            try 
            {
                new Boolean((String)getLeftNode().getNodeValue()).booleanValue();
                new Boolean((String)getRightNode().getNodeValue()).booleanValue();
                this.setNodeValue(true);
                this.setStateToUnknown();
                return;
            }
            catch (NumberFormatException e) {
            		this.setStateToError();
                throw new SemanticException();
            }
        }
        */

        // Y a une erreur de type : pas deux booléens
        this.setStateToError();
		throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
    }
}

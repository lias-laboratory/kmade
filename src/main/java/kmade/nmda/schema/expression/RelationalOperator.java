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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class RelationalOperator extends ComparisonOperator {

    private static final long serialVersionUID = -8878786394525528235L;

    public RelationalOperator(NodeExpression left) {
        super(false,left);
    }
    
    public void checkNode() throws SemanticException {  
    	super.checkNode();
    	
        if (getLeftNode().isInteger() && getRightNode().isString()) {
            try {
                	new Integer((String)getRightNode().getNodeValue()).intValue();
                	this.setNodeValue(true);
                	this.setStateToUnknown();
                	return;
            } catch (NumberFormatException e) {
            		this.setStateToError();
            		throw new SemanticException(ExpressConstant.INTEGER_STRING_COMPARISON_OPERATOR_ERROR + " : " + this.name);
            }
        }
        
        if (getLeftNode().isString() && getRightNode().isInteger()) {
            try {
                	new Integer((String)getLeftNode().getNodeValue()).intValue();
                	this.setNodeValue(true);
                	this.setStateToUnknown();
                	return;
            } catch (NumberFormatException e) {
            		this.setStateToError();
            		throw new SemanticException(ExpressConstant.INTEGER_STRING_COMPARISON_OPERATOR_ERROR + " : " + this.name);
            }
        }
        
        if (getLeftNode().isString() && getRightNode().isString()) {
            try {   
                	new Integer((String)getLeftNode().getNodeValue()).intValue();
                	new Integer((String)getRightNode().getNodeValue()).intValue();              
                	this.setNodeValue(true);
                	this.setStateToUnknown();
                	return;
            } catch (NumberFormatException e) {
            		this.setStateToError();
            		throw new SemanticException(ExpressConstant.INTEGER_STRINGS_COMPARISON_OPERATOR_ERROR + " : " + this.name);
            }
        }
        
        if (getLeftNode().isInteger() && getRightNode().isInteger()) {
            this.setNodeValue(true);
            this.setStateToUnknown();
            return;
        }
        
        this.setStateToError();
        throw new SemanticException(ExpressConstant.COMPARISON_OPERATOR_ERROR + " : " + this.name);
    }
}

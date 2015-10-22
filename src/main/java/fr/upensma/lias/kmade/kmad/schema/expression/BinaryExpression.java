/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade.kmad.schema.expression;

/**
 * @author Mickael BARON
 */
public abstract class BinaryExpression extends NodeExpression {

    private static final long serialVersionUID = 2346679206867201911L;

    protected NodeExpression leftNode;

    protected NodeExpression rightNode;

    public BinaryExpression(Object value, NodeExpression left) {
	super(value);
	this.setStateToUnknown();
	this.leftNode = left;
	this.rightNode = null;
    }

    public BinaryExpression(Object value, NodeExpression left,
	    NodeExpression right) {
	super(value);
	this.setStateToUnknown();
	this.leftNode = left;
	this.rightNode = right;
    }

    public NodeExpression getLeftNode() {
	return leftNode;
    }

    public NodeExpression getRightNode() {
	return rightNode;
    }

    public void setLeftNode(NodeExpression leftNode) {
	this.leftNode = leftNode;
    }

    public void setRightNode(NodeExpression rightNode) {
	this.rightNode = rightNode;
    }

    public String toString() {
	if (leftNode != null && rightNode != null) {
	    return super.toString() + "\n" + leftNode.toString() + "\n"
		    + rightNode.toString();
	} else {
	    return super.toString();
	}
    }
}

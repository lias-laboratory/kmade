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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
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
    
    public BinaryExpression(Object value, NodeExpression left, NodeExpression right) {
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
            return super.toString() + "\n" + leftNode.toString() + "\n" + rightNode.toString();
        } else {
            return super.toString();
        }
    }
}

package kmade.nmda.schema.expression;

import kmade.nmda.schema.metaobjet.NumberValue;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class SupOperator extends RelationalOperator {

	private static final long serialVersionUID = 735201628011662933L;

	public SupOperator(NodeExpression left) {
		super(left);
		this.name = ">";
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {  
		super.evaluateNode(ref);

		if (this.isErrorState()) {
			throw new SemanticErrorException();
		}

		if (this.isUnknownState()) {
			throw new SemanticUnknownException();
		}

		if (getLeftNode().isNumber() && getRightNode().isString()) {
			this.setNodeValue(new Boolean(((NumberValue)getLeftNode().getNodeValue()).supOperator( (new NumberValue((String)getRightNode().getNodeValue())))));
			return;
		}

		if (getLeftNode().isString() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((new NumberValue((String)getLeftNode().getNodeValue())).supOperator(((NumberValue)getRightNode().getNodeValue())))));
			return;
		}

		// uniquement ordre lexicographique
		if (getLeftNode().isString() && getRightNode().isString()) {
			this.setNodeValue(new Boolean( ((String)getLeftNode().getNodeValue()).toLowerCase().compareTo(((String)getRightNode().getNodeValue()).toLowerCase()) > 0  ) );
			return;
		}                    

		if (getLeftNode().isNumber() && getRightNode().isNumber()) {
			this.setNodeValue(new Boolean(((NumberValue)getLeftNode().getNodeValue()).supOperator((NumberValue)getRightNode().getNodeValue())));
			return;
		}
	}    


	public String toString() {
		return super.toString();
	}
}

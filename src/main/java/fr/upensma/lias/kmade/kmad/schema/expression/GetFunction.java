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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;

/**
 * @author Mickael BARON
 */
public class GetFunction extends BinaryFunction implements ConcreteObjectType {

	private static final long serialVersionUID = 6431796702725796954L;

	private ObjetConcret refUserObjetConcret;

	/**
	 * @param value
	 * @param left
	 * @param right
	 */
	public GetFunction(GroupExpressExpression left, AttributExpressExpression right) {
		super(false, left, right);
		this.setNodeType(right.getNodeType());
		this.name = ExpressConstant.GET_FUNCTION_EXPRESSION;
	}

	public boolean isGroupSetType() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble() instanceof EnsembleAg);
	}

	public boolean isGroupArrayType() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble() instanceof TableauAg);
	}

	public ArrayList<ObjetConcret> getConcreteObjects() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble().getLstObjConcrets());
	}

	public ObjetConcret getConcreteObject() {
		return ((GroupExpressExpression) this.leftNode).getGroup().getEnsemble().get();
	}

	public void setUserConcreteObject(ObjetConcret p) {
		this.refUserObjetConcret = p;
	}

	public ObjetConcret getUserConcreteObject() {
		return this.refUserObjetConcret;
	}

	public boolean isEmptyUserConcreteObject() {
		return this.refUserObjetConcret == null;
	}

	public ArrayList<Object> getLinearExpression() {
		ArrayList<Object> myLinearList = new ArrayList<Object>();
		myLinearList.add(this);
		myLinearList.add("(");
		for (Object current : leftNode.getLinearExpression()) {
			myLinearList.add(current);
		}
		myLinearList.add(",");
		for (Object current : rightNode.getLinearExpression()) {
			myLinearList.add(current);
		}
		myLinearList.add(")");
		return myLinearList;
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {
		if (!this.isGroupSetType() && !this.isGroupArrayType()) {
			refUserObjetConcret = this.getConcreteObject();
		}
		this.rightNode.evaluateNode(refUserObjetConcret);
		this.setObjectValueState(this.rightNode.getObjectValueState());
		if (this.isValueState()) {
			this.setNodeValue(this.rightNode.getNodeValue());
		}
	}
}

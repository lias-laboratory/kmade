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
package fr.upensma.lias.kmade.kmad.schema.tache;

import fr.upensma.lias.kmade.kmad.schema.expression.NumberVariant;
import fr.upensma.lias.kmade.kmad.schema.expression.VariableExpression;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.NumberValue;

/**
 * @author Mickael BARON
 */
public class IterExpression extends Expression {

	private static final long serialVersionUID = -834815954065663264L;

	private int currentValue = 3;

	private boolean moreOneIteration = false;

	public IterExpression() {
		refNode = new NumberVariant();
		this.value = refNode.getName();
	}

	public IterExpression(String value) {
		this.value = value;
	}

	public boolean isMoreOneIteration() {
		return moreOneIteration;
	}

	public void decreaseCounter() {
		moreOneIteration = true;
		if (currentValue > 0) {
			currentValue--;
		} else {
			currentValue = 0;
		}
	}

	public int getIterationVariant() {
		return currentValue;
	}

	public void setInitIterationVariant() {
		if (this.refNode instanceof NumberVariant) {
			// cast en number, on prend que la partie entière en cas de nombre à
			// virgule
			this.setIterationVariant(((Number) ((NumberValue) this.refNode.getNodeValue()).getValeur()).intValue());
		} else {
			this.setIterationVariant(1);
		}
	}

	public void setIterationVariant(int i) {
		this.moreOneIteration = false;
		this.currentValue = i;
	}

	public boolean isLoopProgress() {
		if (refNode instanceof NumberVariant) {
			return ((NumberValue) (refNode.getNodeValue())).supOperator(new NumberValue(0));
		} else {
			return (Boolean) refNode.getNodeValue();
		}
	}

	public boolean isNumberVarient() {
		if (refNode instanceof NumberVariant) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFinished() {
		if (this.isNumberVarient()) {
			return (this.getIterationVariant() <= 0);
		} else {
			return !((Boolean) refNode.getNodeValue());
		}
	}

	public boolean isVariableExpressionNode() {
		return (refNode instanceof VariableExpression);
	}
}

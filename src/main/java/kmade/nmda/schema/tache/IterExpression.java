package kmade.nmda.schema.tache;

import kmade.nmda.schema.expression.IntegerVariant;
import kmade.nmda.schema.expression.VariableExpression;

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
public class IterExpression extends Expression {

    private static final long serialVersionUID = -834815954065663264L;
    
    private int currentValue = 3;
    
    private boolean moreOneIteration = false;
    
    public IterExpression() {
        refNode = new IntegerVariant();
        this.chaine = refNode.getName();
    }
    
    public IterExpression(String value) {
        this.chaine = value;
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
    	if (this.refNode instanceof IntegerVariant) {
    		this.setIterationVariant((Integer)this.refNode.getNodeValue());
    	} else {
    		this.setIterationVariant(1);
    	}
    }
    
    public void setIterationVariant(int i) {
    	this.moreOneIteration = false;
        this.currentValue = i;
    }

    public boolean isLoopProgress() {
    	if (refNode instanceof IntegerVariant) {
			return (Integer) (refNode.getNodeValue()) > 0;
		} else {
			return (Boolean) refNode.getNodeValue();
		}
    }
    
    public boolean isVariableExpressionNode() {
        return (refNode instanceof VariableExpression);
    }
}

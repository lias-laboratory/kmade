/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import java.util.HashMap;

/**
 * @author Thomas Lachaume
 */
public class ExpressionTree {

    public String value;
    
    public ExpressionTree left;
    
    public ExpressionTree right;

    public ExpressionTree(String value, ExpressionTree left,
	    ExpressionTree right) {
	this.value = value;
	this.left = left;
	this.right = right;
    }

    public ChoiceEnum evaluate(HashMap<String, ChoiceEnum> map) {

	ChoiceEnum res = eval_rec(map);
	System.err.println("mon res c  : " + res);
	return res;
    }

    @Override
    public String toString() {
	String res = value;
	if (left != null) {
	    res += left.value;
	}
	if (right != null) {
	    res += right.value;
	}
	res += "\n";
	if (left != null) {
	    res += left.toString();
	}
	if (right != null) {
	    res += right.toString();
	}

	return res;
    }

    private ChoiceEnum eval_rec(HashMap<String, ChoiceEnum> map) {
	System.err.println("in 2 :" + this);
	if (value == null) {
	    return null;
	} else {
	    if (value.equals("!")) {
		ChoiceEnum l = left.eval_rec(map);
		switch (l) {
		case faux:
		    return ChoiceEnum.vrai;
		case indeterminée:
		    return ChoiceEnum.indeterminée;
		case vrai:
		    return ChoiceEnum.faux;
		}
	    } else if (value.equals("*")) {
		ChoiceEnum l = left.eval_rec(map);
		ChoiceEnum r = right.eval_rec(map);
		if (l == ChoiceEnum.vrai) {
		    return r;
		} else if (l == ChoiceEnum.faux) {
		    return l;
		} else if (l == ChoiceEnum.indeterminée) {
		    if (r == ChoiceEnum.faux) {
			return r;
		    } else {
			return l;
		    }
		}
	    } else if (value.equals("+")) {
		ChoiceEnum l = left.eval_rec(map);
		ChoiceEnum r = right.eval_rec(map);
		if (l == ChoiceEnum.vrai || r == ChoiceEnum.vrai) {
		    return ChoiceEnum.vrai;
		} else if (l == ChoiceEnum.indeterminée
			|| r == ChoiceEnum.indeterminée) {
		    return ChoiceEnum.indeterminée;
		} else {
		    return ChoiceEnum.faux;
		}
	    } else if (value != null) {
		if (map.get(value) != null) {
		    System.out
			    .println("map valueeeeeeeeeeeee" + map.get(value));
		    return map.get(value);
		}
		System.out.println("pas de map value!!");
	    } else {
		System.err.println("hahaah");
	    }

	}
	return null;

    }

    public String getDescription() {
	String res = "";
	if (value == null) {

	} else if (value.equals("+")) {
	    res += "(" + left.getDescription() + " OU "
		    + right.getDescription() + ")";
	} else if (value.equals("*")) {
	    res += "(" + left.getDescription() + " ET "
		    + right.getDescription() + ")";
	} else if (value.equals("!")) {
	    res += "(NON " + left.getDescription() + ")";
	} else {
	    res += value;
	}
	return res;
    }
}

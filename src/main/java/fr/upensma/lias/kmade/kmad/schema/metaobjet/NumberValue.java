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
package fr.upensma.lias.kmade.kmad.schema.metaobjet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upensma.lias.kmade.kmad.schema.Oid;

/**
 * @authors Vincent LUCQUIAUD and Mickael BARON
 **/
public class NumberValue extends ValeurType {

    private static final long serialVersionUID = -403645145776016389L;

    private Integer valeurInt;

    private Double valeurDouble;

    private Boolean isInt = true;

    public NumberValue() {
	this.setValeur("0");
    }

    public NumberValue(String data) {
	try {
	    this.setValeur(data);
	} catch (NumberFormatException e) {
	    throw e;
	}
    }

    public NumberValue(Integer i) {
	this.valeurInt = i;
	this.isInt = true;
    }

    public NumberValue(Integer i, Oid oid) {
	this.valeurInt = i;
	this.isInt = true;
	this.oid = oid;
    }

    public NumberValue(Double d) {
	this.valeurDouble = d;
	this.isInt = false;
    }

    public NumberValue(Double d, Oid oid) {
	this.valeurDouble = d;
	this.isInt = false;
	this.oid = oid;
    }

    public Object getValeur() {
	if (this.isInt == true) {
	    if (this.valeurInt == null) {
		return null;
	    } else {
		return (Integer) this.valeurInt;
	    }
	} else {
	    if (this.valeurDouble == null) {
		return null;
	    }
	    return (Double) this.valeurDouble;
	}
    }

    public TypeStructure getType() {
	return TypeStructure.NUMBER_STRUCT;
    }

    public String toSPF() {
	if (this.isInt == true) {
	    if (this.valeurInt == null) {
		return (this.oid.get() + "=NumberValue($);");
	    } else {
		return (this.oid.get() + "=NumberValue(" + this.valeurInt + ");");
	    }
	} else {
	    if (this.valeurDouble == null) {
		return (this.oid.get() + "=NumberValue($);");
	    } else {
		return (this.oid.get() + "=NumberValue(" + this.valeurDouble + ");");
	    }
	}
    }

    public String toString() {
	if (this.isInt == true) {
	    if (this.valeurInt == null) {
		return "";
	    } else {
		return this.valeurInt.toString();
	    }
	} else {
	    if (this.valeurDouble == null) {
		return "";
	    } else {
		return this.valeurDouble.toString();
	    }
	}
    }

    public void setValeur(String s) {
	if (s == null) {
	    this.isInt = true;
	    this.valeurInt = null;
	    this.valeurDouble = null;
	} else {
	    // on ne veut pas pouvoir construire de nombre commen�ant par .
	    if (s.startsWith(".")) {
		throw new NumberFormatException();
	    }
	    Integer i = null;
	    try {
		i = new Integer(s);
		valeurInt = i;
		isInt = true;
	    } catch (NumberFormatException e) {
		try {
		    Double d = new Double(s);
		    valeurDouble = d;
		    isInt = false;
		} catch (NumberFormatException e2) {
		    throw e2;
		}
	    }

	}
    }

    public org.w3c.dom.Element toXML(Document doc) {
	Element racine = super.toXML(doc);
	racine.setAttribute("classkmad", "metaobjet.NumberValue");

	return racine;
    }

    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
	this.oid = new Oid(p.getAttribute("idkmad"));

	NodeList nodeList = p.getElementsByTagName("typevalue-value");
	if(nodeList != null && nodeList.item(0)!=null && nodeList.item(0).getParentNode()!=p){
		nodeList = null;}
	this.setValeur(nodeList.item(0).getTextContent());
    }

    public Object clone() {
	ValeurType clone = new StrValue();
	clone.oid = this.oid;
	clone.setValeur(this.toString());
	return clone;
    }

    /**
     * Retourne si les valeurs des deux nombres sont les mêmes ( équivalant à
     * un ==)
     * 
     * @param n
     */
    public boolean equalOperator(NumberValue n) {
	if (this.isInt && n.isInt) {
	    return ((Integer) this.getValeur()).intValue() == ((Integer) n
		    .getValeur()).intValue();
	} else if (this.isInt && !n.isInt) {
	    return (new Double(this.toString()).doubleValue() == ((Double) n
		    .getValeur()).doubleValue());
	} else if (!this.isInt && n.isInt) {
	    return (((Double) this.getValeur()).doubleValue() == (new Double(n
		    .getValeur().toString()).doubleValue()));
	} else {
	    return ((Double) this.getValeur()).doubleValue() == ((Double) n
		    .getValeur()).doubleValue();
	}

    }

    /**
     * Retourne si les valeurs des deux nombres sont différentes ( != )
     * 
     * @param n
     * @return
     */
    public boolean diffOperator(NumberValue n) {
	return !equalOperator(n);
    }

    public boolean infOperator(NumberValue n) {
	if (this.isInt && n.isInt) {
	    return ((Integer) this.getValeur()).intValue() < ((Integer) n
		    .getValeur()).intValue();
	} else if (this.isInt && !n.isInt) {
	    return (new Double(this.toString()).doubleValue() < ((Double) n
		    .getValeur()).doubleValue());
	} else if (!this.isInt && n.isInt) {
	    return (((Double) this.getValeur()).doubleValue() < (new Double(n
		    .getValeur().toString()).doubleValue()));
	} else {
	    return ((Double) this.getValeur()).doubleValue() < ((Double) n
		    .getValeur()).doubleValue();
	}
    }

    public boolean supEqualOperator(NumberValue n) {
	return !infOperator(n);
    }

    public boolean supOperator(NumberValue n) {
	if (this.isInt && n.isInt) {
	    return ((Integer) this.getValeur()).intValue() > ((Integer) n
		    .getValeur()).intValue();
	} else if (this.isInt && !n.isInt) {
	    return (new Double(this.toString()).doubleValue() > ((Double) n
		    .getValeur()).doubleValue());
	} else if (!this.isInt && n.isInt) {
	    return (((Double) this.getValeur()).doubleValue() > (new Double(n
		    .getValeur().toString()).doubleValue()));
	} else {
	    return ((Double) this.getValeur()).doubleValue() > ((Double) n
		    .getValeur()).doubleValue();
	}
    }

    public boolean infEqualOperator(NumberValue n) {
	return !supOperator(n);
    }

    public NumberValue minusComputing(NumberValue n) {
	if (this.isInt && n.isInt) {
	    return new NumberValue(((Integer) this.getValeur()).intValue()
		    - ((Integer) n.getValeur()).intValue());
	} else if (this.isInt && !n.isInt) {
	    return new NumberValue(new Double(this.toString()).doubleValue()
		    - ((Double) n.getValeur()).doubleValue());
	} else if (!this.isInt && n.isInt) {
	    return new NumberValue(((Double) this.getValeur()).doubleValue()
		    - (new Double(n.getValeur().toString()).doubleValue()));
	} else {
	    return new NumberValue(((Double) this.getValeur()).doubleValue()
		    - ((Double) n.getValeur()).doubleValue());
	}
    }

    public NumberValue plusComputing(NumberValue n) {
	if (this.isInt && n.isInt) {
	    return new NumberValue(((Integer) this.getValeur()).intValue()
		    + ((Integer) n.getValeur()).intValue());
	} else if (this.isInt && !n.isInt) {
	    return new NumberValue(new Double(this.toString()).doubleValue()
		    + ((Double) n.getValeur()).doubleValue());
	} else if (!this.isInt && n.isInt) {
	    return new NumberValue(((Double) this.getValeur()).doubleValue()
		    + (new Double(n.getValeur().toString()).doubleValue()));
	} else {
	    return new NumberValue(((Double) this.getValeur()).doubleValue()
		    + ((Double) n.getValeur()).doubleValue());
	}
    }

    @Override
    public Element toXML2(Document doc) throws Exception {
	// TODO Auto-generated method stub
	Element racine = super.toXML2(doc);
	racine.setAttribute("classkmad", "metaobjet.NumberValue");

	return racine;
    }

    @Override
    public void createObjectFromXMLElement2(Element p) throws Exception {
	// TODO Auto-generated method stub
	createObjectFromXMLElement(p);
    }
}

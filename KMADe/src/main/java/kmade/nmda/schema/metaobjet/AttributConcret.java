package kmade.nmda.schema.metaobjet;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

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
public class AttributConcret implements Entity, Cloneable {

    private static final long serialVersionUID = 2989569559332885070L;

    public Oid oid = null;

    private ObjetConcret objConcDe = null;

    private AttributAbstrait attributAbsDe = null;

    private ValeurType valeur = null;

    private String name;

    public AttributConcret() {
        this.name = "";
    }

    public AttributConcret(ObjetConcret i, AttributAbstrait attr, ValeurType valeur) {
        this.valeur = valeur;
        this.setObjConcDe(i);
        this.setAttributDe(attr);
        this.name = attr.getName();
    }

    public AttributConcret(ObjetConcret i, AttributAbstrait attr, ValeurType valeur, Oid oid) {
        this.valeur = valeur;
        this.setObjConcDe(i);
        this.setAttributDe(attr);
        this.oid = oid;
        this.name = attr.getName();
    }

    public void setObjConcDe(ObjetConcret objConcDe) {
        this.objConcDe = objConcDe;
        objConcDe.addInverseListAttribut(this);
    }

    public void setInitValeur() {
        TypeStructure typeStructure = this.attributAbsDe.getTypeStructure();
        
        if (valeur != null) {
            valeur.delete();
        }
        
        if (typeStructure.equals(TypeStructure.STRING_STRUCT)) {
            Oid oid = InterfaceExpressJava.createEntity("metaobjet", "StrValue");
            StrValue val = (StrValue) InterfaceExpressJava.prendre(oid);
            val.setValeur("");
            this.valeur = val;

        } else if (typeStructure.equals(TypeStructure.INTEGER_STRUCT)) {
            Oid oid = InterfaceExpressJava.createEntity("metaobjet", "IntValue");
            IntValue val = (IntValue) InterfaceExpressJava.prendre(oid);
            val.setValeur("0");
            this.valeur = val;
        } else if (typeStructure.equals(TypeStructure.BOOLEAN_STRUCT)) {
            Oid oid = InterfaceExpressJava.createEntity("metaobjet", "BoolValue");
            BoolValue val = (BoolValue) InterfaceExpressJava.prendre(oid);
            val.setValeur("true");
            this.valeur = val;
        }
    }
    
    public boolean setValeur(String v) {
        TypeStructure typeStructure = this.attributAbsDe.getTypeStructure();
       
        if (typeStructure.equals(TypeStructure.STRING_STRUCT)) {
        	if (this.valeur == null) {
        		Oid oid = InterfaceExpressJava.createEntity("metaobjet", "StrValue");
        		StrValue val = (StrValue) InterfaceExpressJava.prendre(oid);
        		val.setValeur(v);
        		this.valeur = val;
        	} else {
        		((StrValue)valeur).setValeur(v);
        	}
        		
        } else if (typeStructure.equals(TypeStructure.INTEGER_STRUCT)) {
            try {
                new Integer(v);
            } catch (NumberFormatException ee) {
                return true;
            }
            if (this.valeur == null) {
            	Oid oid = InterfaceExpressJava.createEntity("metaobjet", "IntValue");
            	IntValue val = (IntValue)InterfaceExpressJava.prendre(oid);
            	val.setValeur(v);
            	this.valeur = val;
            } else {
            	((IntValue)valeur).setValeur(v);
            }
        } else if (typeStructure.equals(TypeStructure.ENUM_STRUCT)) {
       		Enumeration enu = (Enumeration) this.attributAbsDe.getTypeRef();
			Element el = enu.getElement(v);
       		if (this.valeur == null) {
       			Oid oid = InterfaceExpressJava.createEntity("metaobjet", "EnumValue");
       			EnumValue val = (EnumValue) InterfaceExpressJava.prendre(oid);
       			val.setEnumeration(enu);
       			val.setElement(el);
       			this.valeur = val;
       		} else {
       			((EnumValue)valeur).setEnumeration(enu);
       			((EnumValue)valeur).setElement(el);
       		}
        } else if (typeStructure.equals(TypeStructure.INTERVAL_STRUCT)) {
            Intervalle inter = (Intervalle) this.attributAbsDe.getTypeRef();
            if (this.valeur == null) {
            	Oid oid = InterfaceExpressJava.createEntity("metaobjet", "IntervalleValue");
            	IntervalleValue val = (IntervalleValue) InterfaceExpressJava.prendre(oid);
            	val.setIntervalle(inter);
            	val.setValeur(v);
            	this.valeur = val;
            } else {
            	((IntervalleValue)valeur).setIntervalle(inter);
            	((IntervalleValue)valeur).setValeur(v);
            }
        } else if (typeStructure.equals(TypeStructure.BOOLEAN_STRUCT)) {
            if (this.valeur == null) {
            	Oid oid = InterfaceExpressJava.createEntity("metaobjet", "BoolValue");
            	BoolValue val = (BoolValue) InterfaceExpressJava.prendre(oid);
            	val.setValeur(v);
            	this.valeur = val;
            } else {
            	((BoolValue)valeur).setValeur(v);
            }
        }
        return false;
    }

    public void delete() {
        objConcDe.removeInverseListAttribut(this);
        attributAbsDe.removeUtiliseParAttr(this);
        if (valeur != null) {
            valeur.delete();
        }
        InterfaceExpressJava.remove(this.oid);
    }

    public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 6);
    }

    public void setAttributDe(AttributAbstrait a) {
        this.attributAbsDe = a;
        a.setUtiliseParAttr(this);
    }

    public AttributAbstrait getAttributAbsDe() {
        return this.attributAbsDe;
    }

    public String getDeriveName() {
        return attributAbsDe.getName();
    }

    public org.w3c.dom.Element toXML(Document doc) {
        org.w3c.dom.Element racine = doc.createElement("concreteattribut");
        racine.setAttribute("classkmad", "metaobjet.AttributConcret");
        racine.setAttribute("idkmad", oid.get());
        
        org.w3c.dom.Element element = doc.createElement("id-concreteattribut-concreteobject");
        element.setTextContent(this.objConcDe.getOid().get());
        racine.appendChild(element);

        element = doc.createElement("id-concreteattribut-abstractattribut");
        element.setTextContent(this.attributAbsDe.getOid().get());
        racine.appendChild(element);

        element = doc.createElement("id-concreteattribut-value");
        element.setTextContent(this.valeur.getOid().get());
        racine.appendChild(element);        
        
        return racine;
    }    
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        NodeList userValue = p.getElementsByTagName("id-concreteattribut-concreteobject");
        if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
            return true;
        }
        
        userValue = p.getElementsByTagName("id-concreteattribut-abstractattribut");
        if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
            return true;
        }
        
        userValue = p.getElementsByTagName("id-concreteattribut-value");
        if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
            return true;
        }
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));   
        
        NodeList nodeList = p.getElementsByTagName("id-concreteattribut-concreteobject");
        this.setObjConcDe((ObjetConcret)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())));
        
        nodeList = p.getElementsByTagName("id-concreteattribut-abstractattribut");
        this.setAttributDe((AttributAbstrait)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())));
        this.name = this.attributAbsDe.getName();
        
        nodeList = p.getElementsByTagName("id-concreteattribut-value");
        if (nodeList.item(0) != null) {
            this.valeur = (ValeurType)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent()));
        }
    }
    
    public String toSPF() {
        String SPF = oid.get() + "=" + "AttributConcret" + "(";
        if (objConcDe != null)
            SPF = SPF + objConcDe.getOid().get();
        else
            SPF = SPF + "$";
        SPF = SPF + ",";
        if (attributAbsDe != null)
            SPF = SPF + attributAbsDe.oid.get();
        else
            SPF = SPF + "$";
        SPF = SPF + ",";
        if (valeur != null)
            SPF = SPF + valeur.oid.get();
        else
            SPF = SPF + "$";
        SPF = SPF + ");";
        return SPF;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }

    public Oid getOid() {
        return this.oid;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public ObjetConcret getObjetConcDe() {
        return this.objConcDe;
    }

    public boolean isAttributeNull() {
        return (this.valeur == null);
    }
    
    public ValeurType getValue() {
    	return this.valeur;
    }
    
//    public Object getValue() {
//    	// Contient le type de la valeur
//    	TypeStructure typeStructure = this.attributAbsDe.getTypeStructure();
//       
//        if (typeStructure.equals(TypeStructure.ENUM_STRUCT)) {
//            Enumeration enu = (Enumeration) this.attributAbsDe.getTypeRef();
//            Element el = enu.getElement(this.valeur.toString());
//            return new EnumValue(enu, el);            
//        } else if (typeStructure.equals(TypeStructure.INTERVAL_STRUCT)) {
//            Intervalle inter = (Intervalle) this.attributAbsDe.getTypeRef();
//            return new IntervalleValue(inter, this.valeur.toString());
//        } else if (typeStructure.equals(TypeStructure.BOOLEAN_STRUCT)){
//            return new BoolValue(this.valeur.toString());
//        } else if (typeStructure.equals(TypeStructure.STRING_STRUCT)) {
//            return new StrValue(this.valeur.toString());
//        } else if (typeStructure.equals(TypeStructure.INTEGER_STRUCT)){
//            return new IntValue(this.valeur.toString());
//        } else {
//            return null;
//        }
//    }

    public String toString() {
        return this.name;
    }
    
    public Object clone() {
    	AttributConcret clone = new AttributConcret();
    	clone.oid = this.oid;
    	clone.attributAbsDe = this.attributAbsDe;
    	clone.valeur = (ValeurType)(this.valeur).clone();
    	clone.name = this.name;
    	return clone;
    }
}
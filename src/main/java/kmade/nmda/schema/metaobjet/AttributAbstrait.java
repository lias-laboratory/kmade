package kmade.nmda.schema.metaobjet;

import java.util.ArrayList;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
public class AttributAbstrait implements Entity {

    private static final long serialVersionUID = 1128722637230479607L;

    public Oid oid;
    
    private String name;

	private String description = null;

    private TypeStructure typeStruct = TypeStructure.STRING_STRUCT;
    
	private TypeAbs typeref = null;

    private ObjetAbstrait utiliseParClass = null;
    
	private ArrayList<AttributConcret> utilisePar = null;
   
	public AttributAbstrait() {
		this.name = "";
		this.description = "";
        this.typeStruct = TypeStructure.STRING_STRUCT;
		this.utilisePar = new ArrayList<AttributConcret>();
	}

	public AttributAbstrait(String name, String description, String type, ObjetAbstrait classe, TypeAbs typeref, Oid oid) {
		this.name = name;
		this.description = description;
        this.typeStruct = TypeStructure.getValue(type);
		this.setUtiliseeparClass(classe);
		if (typeref != null) {
			this.setTypeRef(typeref);
		}
		this.utilisePar = new ArrayList<AttributConcret>();
		this.oid = oid;
	}

	public AttributAbstrait(String name, String description, String type, ObjetAbstrait classe, Oid oid) {
		this.name = name;
		this.description = description;
        this.typeStruct = TypeStructure.getValue(type);
		this.setUtiliseeparClass(classe);
		this.utilisePar = new ArrayList<AttributConcret>();
		this.oid = oid;
	}

	public AttributAbstrait(String name, ObjetAbstrait classe, TypeAbs type, String type2) {
		this.name = name;
		this.description = "";
		this.setUtiliseeparClass(classe);
		this.utilisePar = new ArrayList<AttributConcret>();
        this.typeStruct = TypeStructure.getValue(type2);
		if (type != null) {
			this.typeref = type;
		}
	}

	public void delete() {
		this.utiliseParClass.removeInverseAttributsAbs(this);
		int taille = this.utilisePar.size();
		for (int i = 0; i < taille; i++) {
			AttributConcret a = this.utilisePar.get(0);
			a.delete();
			InterfaceExpressJava.remove(a.getOid());
		}
		InterfaceExpressJava.remove(oid);
	}

	public void affDelete() {
		InterfaceExpressJava.getGestionWarning().addMessage(oid, 1, ExpressConstant.REMOVE_OF_THE_ABSTRACT_OBJECT_MESSAGE + " \"" + utiliseParClass.getName() + "\"");
		for (int i = 0; i < this.utilisePar.size(); i++) {
			AttributConcret a = this.utilisePar.get(i);
            System.out.println(a);
			a.affDelete();
		}
	}

	public void setUtiliseeparClass(ObjetAbstrait madclasse) {
		this.utiliseParClass = madclasse;
		madclasse.addInverseAttributsAbs(this);
	}

	public ObjetAbstrait getUtiliseParClasse() {
		return this.utiliseParClass;
	}

	public void setUtiliseParAttr(AttributConcret a) {
		this.utilisePar.add(a);
	}

    public void removeUtiliseParAttr(AttributConcret a) {
        this.utilisePar.remove(a);
    }
    
    public void removeAllConcreteAttribut() {
    	this.utilisePar = new ArrayList<AttributConcret>();
    }
    
	public TypeAbs getTypeRef() {
		return this.typeref;
	}

	public ArrayList getUtiliseParAttr() {
		return this.utilisePar;
	}

	public void setTypeRef(TypeAbs ptyperef) {
        this.delTypeRef();
        if (ptyperef != null) {
            this.typeref = ptyperef;
            ptyperef.addInverseAttributAbs(this);
        }
	}

	public void delTypeRef() {
		if (this.typeref != null) {
			this.typeref.removeInverseAttributAbs(this);
			this.typeref = null;
		}
	}
    
    public Element toXML(Document doc) {
        Element racine = doc.createElement("abstractattribut");
        racine.setAttribute("classkmad", "metaobjet.AttributAbstrait");
        racine.setAttribute("idkmad", oid.get());
        
        Element element = doc.createElement("abstractattribut-name");
        element.setTextContent(this.getName());
        racine.appendChild(element);
        
        if (this.getDescription().equals("")) {
            element = doc.createElement("abstractattribut-description");
            element.setTextContent(this.getDescription());
            racine.appendChild(element);
        }
        
        racine.appendChild(typeStruct.toXML(doc));
        
        element = doc.createElement("id-abstractattribut-abstractobject");
        element.setTextContent(this.utiliseParClass.getOid().get());
        racine.appendChild(element);

        if (this.typeref != null) {
            element = doc.createElement("id-abstractattribut-type");
            element.setTextContent(this.typeref.getOid().get());
            racine.appendChild(element);
        }
        return racine;
    }
    
    public boolean oidIsAnyMissing(Element p) {
        NodeList userValue = p.getElementsByTagName("id-abstractattribut-abstractobject");
        if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
            return true;
        }
        userValue = p.getElementsByTagName("id-abstractattribut-type");
        if (userValue.item(0) != null) {
            if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(0).getTextContent())) == null) {
                return true;
            }
        }
        return false;
    }
    
    public void createObjectFromXMLElement(Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList nodeList = p.getElementsByTagName("abstractattribut-name");
        this.name = nodeList.item(0).getTextContent();
        
        nodeList = p.getElementsByTagName("abstractattribut-description");
        if (nodeList.item(0) != null) {
            this.description = nodeList.item(0).getTextContent();
        }
        
        nodeList = p.getElementsByTagName("abstractattribut-typestructure");
        this.typeStruct = TypeStructure.getValue(nodeList.item(0).getTextContent());
        
        nodeList = p.getElementsByTagName("id-abstractattribut-abstractobject");
        this.setUtiliseeparClass((ObjetAbstrait)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())));

        nodeList = p.getElementsByTagName("id-abstractattribut-type");
        if (nodeList.item(0) != null) {
            TypeAbs ref = (TypeAbs)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent()));
            if (ref != null) {
                this.setTypeRef(ref);
            }
        }
    }
    
	public String toSPF() {
		String SPF = oid.get() + "=" + "AttributAbstrait" + "(" + "'" + name
				+ "'" + "," + "'" + description + "'" + "," + typeStruct.toSPF()
				+ ",";
		if (utiliseParClass != null)
			SPF = SPF + utiliseParClass.getOid().get();
		else
			SPF = SPF + "$";
		SPF = SPF + ",";
		if (typeref != null)
			SPF = SPF + typeref.oid.get();
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

	public String getName() {
		return name;
	}

	public void setName(String n) {
		boolean ok = false;
		int cpt = 0;
		n = n.replace(" ", "_");
		while (!ok) {
			if (cpt != 0) {
				if (cpt == 1) {
					n = n + "_" + String.valueOf(cpt);
				} else {
					n = n.substring(0, n.length() - 1) + String.valueOf(cpt);
				}
			}
			ok = isUniqueName(n, oid.get());
			cpt++;
		}
		name = n;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String d) {
		description = d;
	}

    public TypeStructure getTypeStructure() {
        return this.typeStruct;
    }
    
    public void setTypeStructure(TypeStructure p) {
        this.typeStruct = p;  
        
        for (AttributConcret current : utilisePar) {
            current.setInitValeur();
        }        
    }

	public static boolean isUniqueName(String s, String oid) {
		AttributAbstrait attr = (AttributAbstrait) InterfaceExpressJava.prendre(new Oid(oid));
		ObjetAbstrait objAss = attr.utiliseParClass;
		ArrayList<AttributAbstrait> attrsAbs = objAss.getInverseAttributsAbs();
		for (int i = 0; i < attrsAbs.size(); i++) {
			AttributAbstrait obj = attrsAbs.get(i);
			if (s.equalsIgnoreCase(obj.name) && (!obj.oid.get().equals(oid))) {
				return false;
			}
		}
		return true;
	}

	public boolean noSpace() {
		return (name.indexOf(" ") == -1);
	}
}

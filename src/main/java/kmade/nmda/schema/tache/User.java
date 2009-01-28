package kmade.nmda.schema.tache;

import java.util.ArrayList;

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
 * @authors Delphine Autard and Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class User implements Entity {

    private static final long serialVersionUID = 4380917017782960768L;

    public Oid oid = null;

	private String name = "";

	private String statut = "";

	private String role = "";

    private String imagePath = "";
    
	private ArrayList<Acteur> inverseActeur = new ArrayList<Acteur>();
        
	public User() {
        this("","","","",null);
	}

	public User(String name, Oid oid) {
        this(name,"","","",oid);
	}

	public User(String name, String st, String r, Oid oid) {
        this(name,st,r,"",oid);
	}
        
    public User(String name, String st, String r, String pi, Oid oid) {
        this.name = name;
        this.statut = st;
        this.role = r;
        this.imagePath = pi;
        this.oid = oid;
    }

	public void addInverseActeur(Acteur a) {
		inverseActeur.add(a);
	}

	public void removeInverseActeur(Acteur a) {
		inverseActeur.remove(a);
	}

	public void delete() {
		for (int i = 0; i < inverseActeur.size(); i++) {
			Acteur a = inverseActeur.get(i);
            a.delete();
		}
		InterfaceExpressJava.remove(oid);
	}

	public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 3);
		for (int i = 0; i < inverseActeur.size(); i++) {
			Acteur a = inverseActeur.get(i);
			a.affDelete();
		}		
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
			ok = isUniqueName(n);
			cpt++;
		}
		name = n;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String n) {
		statut = n;
	}

    public void setImage(String n) {
        this.imagePath = n;
    }
    
    public String getImage() {
        return this.imagePath;
    }
    
	public String getRole() {
		return role;
	}

	public void setRole(String n) {
		role = n;
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public String toString() {
		return name;
	}

    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("user");
        racine.setAttribute("classkmad", "tache.User");
        racine.setAttribute("idkmad", oid.get());
        
        Element kmadUserName = doc.createElement("user-name");
        kmadUserName.setTextContent(this.getName());
        racine.appendChild(kmadUserName);
        
        if (!this.getStatut().equals("")) {
            Element kmadUserStatut = doc.createElement("user-statut");
            kmadUserStatut.setTextContent(this.getStatut());
            racine.appendChild(kmadUserStatut);
        }
        
        if (!this.getRole().equals("")) {
            Element kmadUserRole = doc.createElement("user-role");
            kmadUserRole.setTextContent(this.getRole());
            racine.appendChild(kmadUserRole);
        }

        if (!this.getImage().equals("")) {
            Element kmadUserImagePath = doc.createElement("user-imagepath");
            kmadUserImagePath.setTextContent(this.getImage());
            racine.appendChild(kmadUserImagePath);
        }

        return racine;
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList kmadUserName = p.getElementsByTagName("user-name");
        if (kmadUserName.item(0) != null)
            this.name = kmadUserName.item(0).getTextContent();
        
        NodeList kmadUserStatut = p.getElementsByTagName("user-statut");
        if (kmadUserStatut.item(0) != null)
            this.statut = kmadUserStatut.item(0).getTextContent();
        
        NodeList kmadUserRole = p.getElementsByTagName("user-role");
        if (kmadUserRole.item(0) != null)
            this.role = kmadUserRole.item(0).getTextContent();
        
        NodeList kmadUserImagePath = p.getElementsByTagName("user-imagepath");
        if (kmadUserImagePath.item(0) != null)
            this.imagePath = kmadUserImagePath.item(0).getTextContent();
    }
    
	public String toSPF() {
		return oid.get() + "=User('" + name + "','" + statut + "','" + role	+ "','" + this.imagePath + "');";
	}

	public static boolean isUniqueName(String s) {
		Object[] objAbs = InterfaceExpressJava.prendreAllOidOfEntity("tache", "User");
		for (int i = 0; i < objAbs.length; i++) {
			User obj = (User) objAbs[i];
			if (s.equalsIgnoreCase(obj.getName())) {
				return false;
			}
		}
		return true;
	}

	public Oid getOid() {
		return oid;
	}
}

package kmade.nmda.schema.tache;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Entity;
import kmade.nmda.schema.KMADXMLParserException;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/

public class ActeurSysteme implements Entity {

    private static final long serialVersionUID = -5271006845716055412L;

    public Oid oid = null;
    // Materiel est appel� parfois UserSystem
	private Materiel userRef;

	private Experience experience = Experience.INCONNU;

	private String competence = "";
  
    private Tache inverseTache;
    	     
	public ActeurSysteme() {
		userRef = null;
		experience = Experience.INCONNU;
		competence = "";
	}

	public ActeurSysteme(String exp, String comp, Materiel u, Oid o) {
		userRef = u;
		experience = Experience.getValue(exp);
		competence = comp;
		this.oid = o;
	}

	public void delete() {
		userRef.removeInverseActeurSysteme(this);
		inverseTache.removeActeurSysteme(this);
		InterfaceExpressJava.remove(oid);
	}

	public String getName() {
		return userRef.getName();
	}

	public void affDelete() {
        InterfaceExpressJava.getGestionWarning().addMessage(oid, 15, ExpressConstant.REMOVE_OF_THE_TASK_MESSAGE + " \"" + inverseTache.getName() + "\"");
	}

	public void setOid(Oid oid) {
		this.oid = oid;
	}

	public void setInverseTache(Tache a) {
		this.inverseTache = a;
	}
	
	public String toString() {
		return userRef.toString();
	}
    
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("actorSystem");
        racine.setAttribute("classkmad", "tache.ActeurSysteme");
        racine.setAttribute("idkmad", oid.get());
        
        racine.appendChild(experience.toXML(doc));
        
        if (!this.competence.equals("")) {
            Element kmadActorCompetence = doc.createElement("actorSystem-competence");
            kmadActorCompetence.setTextContent(this.competence);
            racine.appendChild(kmadActorCompetence);
        }
        
        Element idUser = doc.createElement("id-userSystem");
        idUser.setTextContent(this.userRef.getOid().get());
        racine.appendChild(idUser);
        return racine;        
    }
    
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) throws Exception, KMADXMLParserException {
        NodeList nodeList = p.getElementsByTagName("id-userSystem");
        if (InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent())) == null) {
            return true;
        }        
        return false;
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));
        
        this.experience = Experience.getXMLExperienceValue(p);
        
        NodeList nodeList = p.getElementsByTagName("actorSystem-competence");
        if (nodeList.item(0) != null) {
            this.competence = nodeList.item(0).getTextContent();
        }
                        
        nodeList = p.getElementsByTagName("id-userSystem");
        this.userRef = (Materiel)InterfaceExpressJava.bdd.prendre(new Oid(nodeList.item(0).getTextContent()));
    }
    
	public String toSPF() {
		String SPF = oid.get() + "=" + "Acteur System" + "(" + experience.toSPF()
				+ "," + "'" + competence + "'" + ",";
		if (userRef != null)
			SPF = SPF + userRef.getOid().get();
		else
			SPF = SPF + "$";
		SPF = SPF + ");";
		return SPF;
	}

	public void setExperience(String s) {
		experience = Experience.getValue(s);
	}

	public Experience getExperience() {
		return experience;
	}

	public void setCompetence(String s) {
		competence = s;
	}

	public String getCompetence() {
		return competence;
	}

	public void setMaterielRef(Materiel u) {
		userRef = u;
	}

	public Materiel getMaterielRef() {
		return userRef;
	}

	public Oid getOid() {
		return oid;
	}
    
    public Tache getInverseTache() {
        return inverseTache; 
    }

	public Materiel getUserSystemRef() {
		return userRef;
	}
}
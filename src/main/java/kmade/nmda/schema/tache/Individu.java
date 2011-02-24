package kmade.nmda.schema.tache;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Individu extends User{

	private static final long serialVersionUID = 144972651724436715L;
	
	private ArrayList<Organisation> memberOf = new ArrayList<Organisation>();
	
	public Individu() {
        super();
	}

	public Individu(String name, Oid oid) {
        super(name,"","","",oid);
	}

	public Individu(String name, String st, String r, Oid oid) {
        super(name,st,r,"",oid);
	}
        
    public Individu(String name, String st, String r, String pi, Oid oid) {
       super(name,st,r,pi,oid);
    }

    public void delete(){
		//supprimer l'individu des organisations
		for(int i =0;i<memberOf.size();i++){
			memberOf.get(i).removeToOrganization(this);
		}
		super.delete();
    }
    
    /**
     * Enregistre que l'individu fait partie de l'organisation org
     * L'organisation org ne sait pas que l'individu en fait partie
     * @param org
     */
    public void addToOrganization(Organisation org){
    	if(!memberOf.contains(org)){
    		memberOf.add(org);
    	}
    }
    
    public ArrayList<Organisation> getMemberOf(){
    	return memberOf;
    }
    
    public void removeToOrganization(Organisation org){
    	memberOf.remove(org);
    }
	
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("Individu");
        racine.setAttribute("classkmad", "tache.Individu");
        racine.setAttribute("idkmad", oid.get());
        
        Element kmadIndividuName = doc.createElement("individu-name");
        kmadIndividuName.setTextContent(this.getName());
        racine.appendChild(kmadIndividuName);
        
        if (!this.getStatut().equals("")) {
            Element kmadIndividuStatut = doc.createElement("individu-statut");
            kmadIndividuStatut.setTextContent(this.getStatut());
            racine.appendChild(kmadIndividuStatut);
        }
        
        if (!this.getRole().equals("")) {
            Element kmadIndividuRole = doc.createElement("individu-role");
            kmadIndividuRole.setTextContent(this.getRole());
            racine.appendChild(kmadIndividuRole);
        }

        if (!this.getImage().equals("")) {
            Element kmadIndividuImagePath = doc.createElement("individu-imagepath");
            kmadIndividuImagePath.setTextContent(this.getImage());
            racine.appendChild(kmadIndividuImagePath);
        }
        if(this.memberOf.size()!=0){
        	for(int i =0; i<memberOf.size();i++){
                Element idOrganisation = doc.createElement("id-organisation");
                idOrganisation.setTextContent(memberOf.get(i).getOid().get());
                racine.appendChild(idOrganisation);
        	}
        }
        return racine;
    }
	
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {
        NodeList userValue = p.getElementsByTagName("id-organisation");
       	for(int i =0;i<userValue.getLength();i++){
        		if (InterfaceExpressJava.bdd.prendre(new Oid(userValue.item(i).getTextContent()))== null){
        			return true;
        		}
        	}

        return false; 	
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList kmadIndividuName = p.getElementsByTagName("individu-name");
        if (kmadIndividuName.item(0) != null)
            super.setName(kmadIndividuName.item(0).getTextContent());
        
        NodeList kmadIndividuStatut = p.getElementsByTagName("individu-statut");
        if (kmadIndividuStatut.item(0) != null)
            super.setStatut (kmadIndividuStatut.item(0).getTextContent());
        
        NodeList kmadIndividuRole = p.getElementsByTagName("individu-role");
        if (kmadIndividuRole.item(0) != null)
            super.setRole(kmadIndividuRole.item(0).getTextContent());
        
        NodeList kmadIndividuImagePath = p.getElementsByTagName("individu-imagepath");
        if (kmadIndividuImagePath.item(0) != null)
            super.setImage( kmadIndividuImagePath.item(0).getTextContent());
        
        NodeList kmadIndividuOrganisation= p.getElementsByTagName("id-organisation");
        if (kmadIndividuOrganisation.item(0) != null){
        	for(int i =0;i<kmadIndividuOrganisation.getLength();i++){
        		this.addToOrganization((Organisation)InterfaceExpressJava.bdd.prendre(new Oid(kmadIndividuOrganisation.item(i).getTextContent())));
        	}
        }
    }
    
	public String toSPF() {
		String s = new String("(");
		for(int i = 0;i<memberOf.size();i++){
			s = s + "#"+ memberOf.get(i).getOid().toString();
			if(i!= memberOf.size()-1)
				s += ",";
		}
		s += ")";
		return oid.get() + "=Individu('" + super.getName() + "','" + super.getStatut() + "','" + super.getRole() +  "','" + super.getImage()+ "','" + s + "');";
	}

	public Object[] toArray(){
		String s = "";
		for(int i=0;i<memberOf.size();i++){
			s += memberOf.get(i).getName();
			if(i!=memberOf.size()-1){
				s+= ", ";
			}
		}

		Object[] res = {super.oid.get(), super.getName(),super.getStatut(),super.getRole(),super.getImage(),s};
		return res;
	}
	
	public static int toArrayLenght(){
		return 5;
	}
	
    
}

package kmade.nmda.schema.tache;

import java.util.ArrayList;

import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Organisation extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4187152840289735872L;
	
	private ArrayList<Individu> inverseMember = new ArrayList<Individu>();
	
	
	public Organisation() {
        super();
	}

	public Organisation(String name, Oid oid) {
        super(name,"","","",oid);
	}

	public Organisation(String name, String st, String r, Oid oid) {
        super(name,st,r,"",oid);
	}
        
    public Organisation(String name, String st, String r, String pi, Oid oid) {
       super(name,st,r,pi,oid);
    }
    
    public void delete(){
		//supprimer l'individu des organisations
		for(int i =0;i<inverseMember.size();i++){
			inverseMember.get(i).removeToOrganization(this);
		}
		super.delete();
    }
   
    /**
     * Enregistre l'individu ind au sein de l'organisation
     * L'individu ne sait pas qu'il appartient Ã  l'organisation
     * @param org
     */
    public void addMember(Individu ind){
    	if(!inverseMember.contains(ind)){
    		inverseMember.add(ind);
    	}
    }
    
    public void removeToOrganization(Individu ind){
    	inverseMember.remove(ind);
    }
	
    public ArrayList<Individu> getMember(){
    	return inverseMember;
    }
	
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("Organisation");
        racine.setAttribute("classkmad", "tache.Organisation");
        racine.setAttribute("idkmad", oid.get());
        
        Element kmadOrganisationName = doc.createElement("organisation-name");
        kmadOrganisationName.setTextContent(this.getName());
        racine.appendChild(kmadOrganisationName);
        
        if (!this.getStatut().equals("")) {
            Element kmadOrganisationStatut = doc.createElement("organisation-statut");
            kmadOrganisationStatut.setTextContent(this.getStatut());
            racine.appendChild(kmadOrganisationStatut);
        }
        
        if (!this.getRole().equals("")) {
            Element kmadOrganisationRole = doc.createElement("organisation-role");
            kmadOrganisationRole.setTextContent(this.getRole());
            racine.appendChild(kmadOrganisationRole);
        }

        if (!this.getImage().equals("")) {
            Element kmadOrganisationImagePath = doc.createElement("organisation-imagepath");
            kmadOrganisationImagePath.setTextContent(this.getImage());
            racine.appendChild(kmadOrganisationImagePath);
        }
        
        /* seul les individus stocks les organisations dans le fichier XML
        if(this.inverseMember.size()!=0){
        	for(int i =0; i<inverseMember.size();i++){
                Element idOrganisation = doc.createElement("id-Organisation");
                idOrganisation.setTextContent(inverseMember.get(i).getOid().get());
                racine.appendChild(idOrganisation);
        	}
        }*/
        return racine;
    }
	
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {

        return false; 	
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList kmadOrganisationName = p.getElementsByTagName("organisation-name");
        if (kmadOrganisationName.item(0) != null)
            super.setName(kmadOrganisationName.item(0).getTextContent());
        
        NodeList kmadOrganisationStatut = p.getElementsByTagName("organisation-statut");
        if (kmadOrganisationStatut.item(0) != null)
            super.setStatut (kmadOrganisationStatut.item(0).getTextContent());
        
        NodeList kmadOrganisationRole = p.getElementsByTagName("organisation-role");
        if (kmadOrganisationRole.item(0) != null)
            super.setRole(kmadOrganisationRole.item(0).getTextContent());
        
        NodeList kmadOrganisationImagePath = p.getElementsByTagName("organisation-imagepath");
        if (kmadOrganisationImagePath.item(0) != null)
            super.setImage( kmadOrganisationImagePath.item(0).getTextContent());
    }
    
	public String toSPF() {
		
		String s = new String("");
		/* affichage des oid des membres dÃ©sactivÃ©s
		s += "','(";
		for(int i = 0;i<inverseMember.size();i++){
			s = s + inverseMember.get(i).getOid().toString();
			if(i!= inverseMember.size()-1)
				s += ",";
		}
		s += ")";
		*/
		return oid.get() + "=Organisation('" + super.getName() + "','" + super.getStatut() + "','" + super.getRole() +  "','" + super.getImage() + s + "');";
	}
	
	/**
	 * le noms des membres est une chaîne de caractère de type "Nom1, Nom2, ...."
	 * @return
	 * 	{oid, name, statut, role, image path , Nomsdesmembres}
	 */
	public Object[] toArray(){
		String s = "";
		for(int i=0;i<inverseMember.size();i++){
			s += inverseMember.get(i).getName();
			if(i!=inverseMember.size()-1){
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

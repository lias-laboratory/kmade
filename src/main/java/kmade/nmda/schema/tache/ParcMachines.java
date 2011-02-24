package kmade.nmda.schema.tache;

import java.util.ArrayList;

import kmade.nmda.schema.Oid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParcMachines extends Materiel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4187152840289735872L;
	
	private ArrayList<Machine> inverseMember = new ArrayList<Machine>();
	
	
	public ParcMachines() {
        super();
	}

	public ParcMachines(String name, Oid oid) {
        super(name,oid);
	}

	public ParcMachines(String name, String description, Oid oid) {
        super(name,description,oid);
	}
        
	public void delete(){
		//supprimer le parc des machines
		for(int i =0;i<inverseMember.size();i++){
			inverseMember.get(i).removeToParc(this);
		}
		super.delete();
		}
	
    /**
     * Enregistre la machine machine au sein du parc
     * La machine ne sait pas qu'elle appartient au parc
     * @param machine
     */
    public void addMember(Machine machine){
    	if(!inverseMember.contains(machine)){
    		inverseMember.add(machine);
    	}
    }
    
    public void removeToParc(Machine machine){
    	inverseMember.remove(machine);
    }
	
    public ArrayList<Machine> getMember(){
    	return inverseMember;
    }
	
    public org.w3c.dom.Element toXML(Document doc) {
        Element racine = doc.createElement("ParcMachines");
        racine.setAttribute("classkmad", "tache.ParcMachines");
        racine.setAttribute("idkmad", oid.get());
        
        Element kmadParcName = doc.createElement("parcMachines-name");
        kmadParcName.setTextContent(this.getName());
        racine.appendChild(kmadParcName);
        
        if (!this.getDescription().equals("")) {
            Element kmadParcDescription = doc.createElement("parcMachines-description");
            kmadParcDescription.setTextContent(this.getDescription());
            racine.appendChild(kmadParcDescription);
        }
        
        if (!super.getImage().equals("")) {
            Element kmadParcImagePath = doc.createElement("parcMachines-imagepath");
            kmadParcImagePath.setTextContent(super.getImage());
            racine.appendChild(kmadParcImagePath);
        }
        // seul les machines stocks les parcs dans le XML
        
        return racine;
    }
	
    public boolean oidIsAnyMissing(org.w3c.dom.Element p) {

        return false; 	
    }
    
    public void createObjectFromXMLElement(org.w3c.dom.Element p) {
        this.oid = new Oid(p.getAttribute("idkmad"));        
        
        NodeList kmadParcName = p.getElementsByTagName("parcMachines-name");
        if (kmadParcName.item(0) != null)
            super.setName(kmadParcName.item(0).getTextContent());
        
        NodeList kmadParcDescription = p.getElementsByTagName("parcMachines-description");
        if (kmadParcDescription.item(0) != null)
            super.setDescription(kmadParcDescription.item(0).getTextContent());
        
        NodeList kmadParcImagePath = p.getElementsByTagName("parcMachines-imagepath");
        if (kmadParcImagePath.item(0) != null)
            super.setImage(kmadParcImagePath.item(0).getTextContent());
    }
    
	public String toSPF() {
		return oid.get() + "=ParcMachines('" + super.getName() + "','" + super.getDescription() + "','" + super.getImage() + "');";
	}
	
	/**
	 * le noms des machines est une chaîne de caractère de type "Nom1, Nom2, ...."
	 * @return
	 * 	{oid, name, description, image,machines du parc}
	 */
	public Object[] toArray(){
		String s = "";
		for(int i=0;i<inverseMember.size();i++){
			s += inverseMember.get(i).getName();
			if(i!=inverseMember.size()-1){
				s+= ", ";
			}
		}

		Object[] res = {super.oid.get(), super.getName(),super.getDescription(),super.getImage(),s};
		return res;
	}
	
	public static int toArrayLenght(){
		return 5;
	}
}
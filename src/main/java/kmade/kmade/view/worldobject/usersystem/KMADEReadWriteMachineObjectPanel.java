package kmade.kmade.view.worldobject.usersystem;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorFC.ExpressMachine;
import kmade.kmade.adaptatorUI.MachinePanelAdaptator;
import kmade.kmade.view.toolutilities.LanguageFactory;
import kmade.nmda.schema.Oid;

public class KMADEReadWriteMachineObjectPanel extends JPanel implements LanguageFactory {


	private static final long serialVersionUID = -2220265715254322307L;

	private final KMADEReadWriteMachineObjectTable machine = new KMADEReadWriteMachineObjectTable();
    
    private final KMADEReadParcInMachine parcAdd = new KMADEReadParcInMachine();

    private final KMADEReadParcNotInMachine parcNotAdd = new KMADEReadParcNotInMachine();

	private String oidActiveMachine;

	private String nameActiveMachine;
     
    public KMADEReadWriteMachineObjectPanel() {
    	//cr�ation de la table comprenant les 3 Jtable que l'on souhaite
        JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
        panelHaut.add(machine); 
        JPanel panelBas = new JPanel(new GridLayout(1,2,2,2));
        panelBas.add(parcAdd);
        panelBas.add(parcNotAdd);
        this.setLayout(new GridLayout(2,1,2,2));
        this.add(panelHaut);
        this.add(panelBas);
        // il n'y a pas d'individu selectionn�
        this.setActiveMachineObject("", Oid.OID_NULL);
        this.setBorder(new TitledBorder(null, KMADEConstant.MACHINE_TITLE_NAME ,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }
    
    
    
    /**
     * Met � jour les tables des machines
     */
    public void refreshActiveMachine(){
    	setActiveMachineObject(nameActiveMachine,oidActiveMachine);
    	//force le refresh de la table machine	
    }
    
    
    /**
     * Affiche les tables des parcs de la machine selectionn�e.
     * @param name
     * @param oid
     */
    public void setActiveMachineObject(String name, String oid) {
        parcAdd.clearSelection();
        parcNotAdd.clearSelection();
        oidActiveMachine = oid;
        nameActiveMachine = name;
        if (oid.equals(Oid.OID_NULL)) {
        	// cas o� il n'y a rien de selectionn�
            parcAdd.setVisible(false);
            parcNotAdd.setVisible(false);
            machine.setMachineNameBorder("");
        } else {
        	// une machine est selectionn�z
        	// affichage du titre avec le nom de la machine
            machine.setMachineNameBorder(name);
        	
            // La partie parcAdd
            // Supression de la table orgaAdd des informations contenu dedans ( pas dans la bdd Express)
            parcAdd.removeAllParc();
            // Modification du nom de la table
            parcAdd.setParcMachinesNameBorder(name);

            
            // mise � jour des valeurs de la table
            Object[][] tabOrgaAdd = MachinePanelAdaptator.getParcsIntoTab(oid);
            if (tabOrgaAdd.length != 0){
            	parcAdd.updateDataModel(tabOrgaAdd);
            }        	
            // affichage de la table
            parcAdd.setVisible(true);
            
            // La partie parcNotAdd
            // comme pour parcAdd
            parcNotAdd.removeAllParc();
            parcNotAdd.setParcMachinesNameBorder(name);


            Object[][] tabOrgaNotAdd = MachinePanelAdaptator.getOtherParcsIntoTab(oid);
            if (tabOrgaNotAdd.length != 0) {
            	parcNotAdd.updateDataModel(tabOrgaNotAdd);
            }
            parcNotAdd.setVisible(true);
        }
    }
    
    public void addParcToActiveMachine(String oidParc){
    	try{
    		
    		ExpressMachine.addMachineInParcMachines(oidActiveMachine, oidParc);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public void removeParcMachinesToActiveMachine(String oidParcMachines){
    	try{
    		
    		ExpressMachine.removeMachineInParcMachines(oidActiveMachine, oidParcMachines);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public KMADEReadParcInMachine getParcMachinesAddTable() {
        return parcAdd;
    }

    public KMADEReadParcNotInMachine getParcMachinesNotAddTable() {
        return parcNotAdd;
    }

    public KMADEReadWriteMachineObjectTable getMachineObjectTable() {
        return machine;
    }

    public void notifLocalisationModification() {
        machine.notifLocalisationModification();
        parcAdd.notifLocalisationModification();
        parcNotAdd.notifLocalisationModification();
    }


}

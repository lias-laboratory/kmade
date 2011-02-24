package kmade.kmade.UI.worldobject.usersystem;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorFC.ExpressParcMachines;
import kmade.kmade.adaptatorUI.ParcMachinesPanelAdaptator;
import kmade.nmda.schema.Oid;

public class KMADEReadWriteParcMachinesPanel extends JPanel implements LanguageFactory {
	private static final long serialVersionUID = 16531L;

	private final KMADEReadWriteParcMachinesTable parcs = new KMADEReadWriteParcMachinesTable();

	private final KMADEReadMachineInParc machineAdd = new KMADEReadMachineInParc();

	private final KMADEReadMachineNotInParc machineNotAdd = new KMADEReadMachineNotInParc();

	private String oidActiveParc = Oid.OID_NULL;

	private String nameActiveParc =null;

	public KMADEReadWriteParcMachinesPanel() {
		//création de la table comprenant les 3 Jtable que l'on souhaite
		JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
		panelHaut.add(parcs); 
		JPanel panelBas = new JPanel(new GridLayout(1,2,2,2));
		panelBas.add(machineAdd);
		panelBas.add(machineNotAdd);
		this.setLayout(new GridLayout(2,1,2,2));
		this.add(panelHaut);
		this.add(panelBas);
		// il n'y a pas d'individu selectionné
		this.setActiveParcObject("", Oid.OID_NULL);
		this.setBorder(new TitledBorder(null, KMADEConstant.PARCMACHINES_TITLE_NAME ,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
	}



	/**
	 * Met à jour les tables des parcs
	 */
	public void refreshActiveParcMachines(){
		setActiveParcObject(nameActiveParc,oidActiveParc);
	}


	/**
	 * Affiche les tables des machines du parc selectionné
	 * @param name
	 * @param oid
	 */
	public void setActiveParcObject(String name, String oid) {
		machineAdd.clearSelection();
		machineNotAdd.clearSelection();
		oidActiveParc = oid;
		nameActiveParc = name;
		if (oid.equals(Oid.OID_NULL)) {
			// cas où il n'y a rien de selectionné
			machineAdd.setVisible(false);
			machineNotAdd.setVisible(false);
			parcs.setParcMachinesNameBorder("");
		} else {
			// un individu est selectionné
			// affichage du titre avec le nom de l'individu
			parcs.setParcMachinesNameBorder(name);

			// La partie indiAdd  
			// Supression de la table indiAdd des informations contenu dedans ( pas dans la bdd Express)
			machineAdd.removeAllMachine();
			// Modification du nom de la table
			machineAdd.setMachineMemberNameBorder(name);
			// mise à jour des valeurs de la table
			Object[][] tabindiAdd = ParcMachinesPanelAdaptator.getMachineAddIntoTab(oid);
			if (tabindiAdd.length != 0){
				machineAdd.updateDataModel(tabindiAdd);
			}        	
			// affichage de la table
			machineAdd.setVisible(true);

			// La partie indiNotAdd
			// comme pour indiAdd
			machineNotAdd.removeAllMachine();
			machineNotAdd.setMachineMemberNameBorder(name);
			Object[][] tabindiNotAdd = ParcMachinesPanelAdaptator.getMachineNotAddIntoTab(oid);
			if (tabindiNotAdd.length != 0) {
				machineNotAdd.updateDataModel(tabindiNotAdd);
			}
			machineNotAdd.setVisible(true);
		}
	}

	public void addMachineInParcMachines(String oidMachine){
		try{

			ExpressParcMachines.addParcMachinesMember(oidActiveParc, oidMachine);
		}
		catch(Exception e){

		}
	}

	public void removeMachineOfParc(String oidMachine){
		try{

			ExpressParcMachines.removeMachineOfParc(oidActiveParc,oidMachine);
		}
		catch(Exception e){

		}
	}

	public  KMADEReadMachineInParc getMachineAddTable() {
		return machineAdd;
	}

	public  KMADEReadMachineNotInParc getMachineNotAddTable() {
		return machineNotAdd;
	}

	public  KMADEReadWriteParcMachinesTable getParcMachinesObjectTable() {
		return parcs;
	}

	public void notifLocalisationModification() {
		parcs.notifLocalisationModification();
		machineAdd.notifLocalisationModification();
		machineNotAdd.notifLocalisationModification();
	}






}

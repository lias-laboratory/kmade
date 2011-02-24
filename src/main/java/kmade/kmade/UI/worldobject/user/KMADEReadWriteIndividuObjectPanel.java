package kmade.kmade.UI.worldobject.user;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorFC.ExpressIndividu;
import kmade.kmade.adaptatorUI.IndividuPanelAdaptator;
import kmade.nmda.schema.Oid;

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
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadWriteIndividuObjectPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 16531L;

    private final KMADEReadWriteIndividuObjectTable individu = new KMADEReadWriteIndividuObjectTable();
    
    private final KMADEReadOrganisationOfIndividu orgaAdd = new KMADEReadOrganisationOfIndividu();

    private final KMADEReadOrganisationNotOfIndividu orgaNotAdd = new KMADEReadOrganisationNotOfIndividu();

	private String oidActiveIndividu;

	private String nameActiveIndividu;
     
    public KMADEReadWriteIndividuObjectPanel() {
    	//création de la table comprenant les 3 Jtable que l'on souhaite
        JPanel panelHaut = new JPanel(new GridLayout(1, 1, 2, 2));
        panelHaut.add(individu); 
        JPanel panelBas = new JPanel(new GridLayout(1,2,2,2));
        panelBas.add(orgaAdd);
        panelBas.add(orgaNotAdd);
        this.setLayout(new GridLayout(2,1,2,2));
        this.add(panelHaut);
        this.add(panelBas);
        // il n'y a pas d'individu selectionné
        this.setActiveIndividuObject("", Oid.OID_NULL);
    	this.setBorder(new TitledBorder(null, KMADEConstant.INDIVIDU_TITLE_NAME ,
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    }
    
    
    
    /**
     * Met à jour les tables des organisations
     */
    public void refreshActiveIndividu(){
    	setActiveIndividuObject(nameActiveIndividu,oidActiveIndividu);
    	//force le refresh de la table individu    	
    }
    
    
    /**
     * Affiche les tables des organisations de l'individu selectionné.
     * @param name
     * @param oid
     */
    public void setActiveIndividuObject(String name, String oid) {
        orgaAdd.clearSelection();
        orgaNotAdd.clearSelection();
        oidActiveIndividu = oid;
        nameActiveIndividu = name;
        if (oid.equals(Oid.OID_NULL)) {
        	// cas où il n'y a rien de selectionné
            orgaAdd.setVisible(false);
            orgaNotAdd.setVisible(false);
            individu.setIndividuNameBorder("");
        } else {
        	// un individu est selectionné
        	// affichage du titre avec le nom de l'individu
            individu.setIndividuNameBorder(name);
        	
            // La partie orgaAdd  
            // Supression de la table orgaAdd des informations contenu dedans ( pas dans la bdd Express)
            orgaAdd.removeAllOrga();
            // Modification du nom de la table
            orgaAdd.setOrganizationAddNameBorder(name);
            
            
            // mise à jour des valeurs de la table
            Object[][] tabOrgaAdd = IndividuPanelAdaptator.getOrganizationIntoTab(oid);
            if (tabOrgaAdd.length != 0){
            	orgaAdd.updateDataModel(tabOrgaAdd);
            }        	
            // affichage de la table
            orgaAdd.setVisible(true);
            // La partie orgaNotAdd
            // comme pour orgaAdd
            orgaNotAdd.removeAllOrga();
            orgaNotAdd.setOrganizationNameBorder(name);


            Object[][] tabOrgaNotAdd = IndividuPanelAdaptator.getOtherOrganizationIntoTab(oid);
            if (tabOrgaNotAdd.length != 0) {
            	orgaNotAdd.updateDataModel(tabOrgaNotAdd);
            }
            orgaNotAdd.setVisible(true);
        }
    }
    
    public void addOrganizationToActiveIndividu(String oidOrganization){
    	try{
    		
    		ExpressIndividu.addIndividuInOrganisation(oidActiveIndividu, oidOrganization);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public void removeOrganizationToActiveIndividu(String oidOrganization){
    	try{
    		
    		ExpressIndividu.removeIndividuInOrganisation(oidActiveIndividu, oidOrganization);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public KMADEReadOrganisationOfIndividu getOrganizationAddTable() {
        return orgaAdd;
    }

    public KMADEReadOrganisationNotOfIndividu getOrganizationNotAddTable() {
        return orgaNotAdd;
    }

    public KMADEReadWriteIndividuObjectTable getIndividuObjectTable() {
        return individu;
    }

    public void notifLocalisationModification() {
        individu.notifLocalisationModification();
        orgaAdd.notifLocalisationModification();
        orgaNotAdd.notifLocalisationModification();
    }


}

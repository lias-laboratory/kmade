
package fr.upensma.lias.kmade.tool.viewadaptator;

import fr.upensma.lias.kmade.tool.coreadaptator.ExpressIndividu;
import fr.upensma.lias.kmade.tool.view.worldobject.user.KMADEReadOrganisationOfIndividu;

public class ReadOrganizationOfIndividuAdaptator {
	
	  private static final KMADEReadOrganisationOfIndividu myReadOrganisationOfIndividu = new KMADEReadOrganisationOfIndividu();
      
	    public static void showReadOrganisationOfIndividu() {
	    	ReadOrganizationOfIndividuAdaptator.updateReadOrganizationOfIndividu();
			if (!myReadOrganisationOfIndividu.isVisible()) {
	    			myReadOrganisationOfIndividu.setVisible(true);
	    		}
	    }
	    
	    public static void closeReadOrganizationOfIndividu() {
	        myReadOrganisationOfIndividu.setVisible(false);
	    }
	    
	    public static void updateReadOrganizationOfIndividu() {
	        myReadOrganisationOfIndividu.updateDataModel(ExpressIndividu.getOrganisationIntoTab(""));            	
	    }

}

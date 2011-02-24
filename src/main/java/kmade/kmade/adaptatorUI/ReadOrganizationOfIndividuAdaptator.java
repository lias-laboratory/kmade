
package kmade.kmade.adaptatorUI;

import kmade.kmade.UI.worldobject.user.KMADEReadOrganisationOfIndividu;
import kmade.kmade.adaptatorFC.ExpressIndividu;

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

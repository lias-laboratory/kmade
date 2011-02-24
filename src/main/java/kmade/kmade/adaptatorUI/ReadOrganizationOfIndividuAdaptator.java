
package kmade.kmade.adaptatorUI;

import kmade.kmade.adaptatorFC.ExpressIndividu;
import kmade.kmade.view.worldobject.user.KMADEReadOrganisationOfIndividu;

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

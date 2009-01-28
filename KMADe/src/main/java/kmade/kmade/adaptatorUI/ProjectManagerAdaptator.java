package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.adaptatorFC.ExpressProjectManager;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public final class ProjectManagerAdaptator {   
    private static String oidProject;
    
    private static boolean newState; 
    
    public static void setOidProject(String oid) {
        ProjectManagerAdaptator.oidProject = oid;
    }
    
    public static void createNewProject() { 
        GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
        ProjectManagerAdaptator.oidProject = ExpressProjectManager.createProjectManager();
        GraphicEditorAdaptator.getMainFrame().getProjectDialog().showToCreateProjectData();
        newState = true;
    }
    
    public static void setProject() {
        // R�cup�re le projet.
        ProjectManagerAdaptator.oidProject = ExpressProjectManager.getProjectManager();
        String[] generalInfo = ExpressProjectManager.getAllGeneralInformation(new Oid(oidProject));
        ArrayList<Object[]> interview = ExpressProjectManager.getAllInterviewIndex(new Oid(oidProject));
        GraphicEditorAdaptator.getMainFrame().getProjectDialog().showToModifyProjectData(generalInfo, interview);
        GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
        TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
        newState = false;
    }

    public static void confirmModification(String[] generalInformation, ArrayList<Object[]> interviewIndex) {
        if (newState) {        
        } else {
            ExpressProjectManager.removeAllGeneralInformationAndInterviewIndexEntity(new Oid(oidProject));
        }
        
        ExpressProjectManager.createGeneralInformation(new Oid(oidProject),
                generalInformation[0], generalInformation[1], generalInformation[2],
                generalInformation[3], generalInformation[4], generalInformation[5]);
        
        for (int i = 0; i < interviewIndex.size(); i++) {
            ExpressProjectManager.createInterviewIndex(new Oid(oidProject),
                    (String)interviewIndex.get(i)[0],(String)interviewIndex.get(i)[1],(String)interviewIndex.get(i)[2],
                    (String)interviewIndex.get(i)[3],(String)interviewIndex.get(i)[4],(String)interviewIndex.get(i)[5],
                    (String)interviewIndex.get(i)[6],(String)interviewIndex.get(i)[7]);
        }

        if (newState)
            KMADeAdaptator.noToExistProject();
        GraphicEditorAdaptator.enabledMainFrameAfterEdition();
        TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
        GraphicEditorAdaptator.getMainFrame().getProjectDialog().closeProjectDialog();
    }

    /**
     * 
     */
    public static void cancelModification() {
        if (newState) {
            InterfaceExpressJava.clearCurrentProject();
        } else {
            // Ne rien faire normalement.
        }
        GraphicEditorAdaptator.enabledMainFrameAfterEdition();
        TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
        GraphicEditorAdaptator.getMainFrame().getProjectDialog().closeProjectDialog();
    }
}

package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Acteur;
import fr.upensma.lias.kmade.kmad.schema.tache.User;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressActeur;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressUser;


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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class ActorAdaptator {
    public static int origine = 0;
    
    public static void disabledFrame() {
        GraphicEditorAdaptator.disabledMainFrameBeforeEdition();
        TaskPropertiesEnhancedEditorAdaptator.disabledMainFrameBeforeEdition();
    }
    
    public static void editedFromEnhancedFrame() {
        origine = 1;
    }
    
    public static void enabledFrame() {
        GraphicEditorAdaptator.enabledMainFrameAfterEdition();
        TaskPropertiesEnhancedEditorAdaptator.enabledMainFrameAfterEdition();
        if (origine == 0) {
            GraphicEditorAdaptator.requestFocus();
        } else {
            TaskPropertiesEnhancedEditorAdaptator.requestFocus();
        }
        origine = 0;
    }
    

    public static String setOldActorSelectedTask(String oidActor, String newUser ) {
    	
        Acteur m = (Acteur)InterfaceExpressJava.prendre(new Oid(oidActor));
        User myUser = ExpressUser.getUserWithName(newUser);
        if (!isUserInActors(myUser.getOid())) {
        	m.delete();	
            Oid oidNewActor = ExpressActeur.createActor(myUser.getOid());
            if (ExpressTask.addActor(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
            	return oidNewActor.get();
            }
        }
        return null;
    }
    
    public static void removeActeur(String oidAct) {
        ExpressTask.removeActor(oidAct);
    }
    
    public static void affRemoveActeur(String oidAct) {
        ExpressTask.affRemoveActeur(oidAct);
    }
    
    private static boolean isUserInActors(Oid oidUser) {
        ArrayList<Acteur> ma_liste = ExpressActeur.extractActorFromTask(GraphicEditorAdaptator.getSelectedExpressTask());
        for (int i = 0; i < ma_liste.size(); i++) {
            Oid oidActeurUser = ma_liste.get(i).getUserRef().getOid();
            if (oidActeurUser.equals(oidUser)) {
                return true;
            }
        }
        return false;
    }
    
    public static String[] addNewActor(String user) {
        User myUser = ExpressUser.getUserWithName(user);
        if (!isUserInActors(myUser.getOid())) {
            Oid oidNewActor = ExpressActeur.createActor(myUser.getOid());
            if (ExpressTask.addActor(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
                return ExpressActeur.getActorFromOid(oidNewActor);
            }
        }
        return null;
    }
    
    public static void addNewActor(Oid user) {
        if (!ActorAdaptator.isUserInActors(user)) {
            // L'utilisateur n'est pas utilisé dans cette tâche
            Oid oidNewActor = ExpressActeur.createActor(user);
            if (ExpressTask.addActor(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
                String[] temp = ExpressActeur.getActorFromOid(oidNewActor);
                GraphicEditorAdaptator.getPanelProp().addNewActor(temp[0], temp[1] , temp[2], temp[3]);
            } else {
                
            }
        } else {
            
        }
    }
    
    public static void setActorExperience(String oid, String name) {
        ExpressActeur.setActorExperience(oid, name);
    }

    public static void setActorCompetence(String oid, String name) {
        ExpressActeur.setActorCompetence(oid, name);
    }
}

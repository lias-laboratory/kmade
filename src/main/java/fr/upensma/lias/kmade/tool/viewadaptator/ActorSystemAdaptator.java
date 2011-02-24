package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.ActeurSysteme;
import fr.upensma.lias.kmade.kmad.schema.tache.Materiel;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressActeurSysteme;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressMateriel;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;


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
public class ActorSystemAdaptator {
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
    
    public static String setOldActorSystemSelectedTask(String oidActor, String newUser) {
        ActeurSysteme m = (ActeurSysteme)InterfaceExpressJava.prendre(new Oid(oidActor));
        Materiel myUser = ExpressMateriel.getMaterielWithName(newUser);
        if (!isMaterielInActorSystem(myUser.getOid())) {
            m.delete();
            Oid oidNewActor = ExpressActeurSysteme.createActorSystem(myUser.getOid());
            if (ExpressTask.addActorSystem(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
                return oidNewActor.get();
            }
        }
        return null;
    }
    
    public static void removeActeurSystem(String oidAct) {
        ExpressTask.removeActorSystem(oidAct);
    }
    
    public static void affRemoveActeurSystem(String oidAct) {
        ExpressTask.affRemoveActeurSystem(oidAct);
    }
    
    private static boolean isMaterielInActorSystem(Oid oidMateriel) {
        ArrayList<ActeurSysteme> ma_liste = ExpressActeurSysteme.extractActorSystemFromTask(GraphicEditorAdaptator.getSelectedExpressTask());
        for (int i = 0; i < ma_liste.size(); i++) {
            Oid oidActeurUser = ma_liste.get(i).getMaterielRef().getOid();
            if (oidActeurUser.equals(oidMateriel)) {
                return true;
            }
        }
        return false;
    }
    
    public static String[] addNewActorSystem(String materiel) {
        Materiel myMateriel = ExpressMateriel.getMaterielWithName(materiel);
        if (!isMaterielInActorSystem(myMateriel.getOid())) {
            Oid oidNewActor = ExpressActeurSysteme.createActorSystem(myMateriel.getOid());
            if (ExpressTask.addActorSystem(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
                return ExpressActeurSysteme.getActorSystemFromOid(oidNewActor);
            }
        }
        return null;
    }
    
    public static void addNewActorSystem(Oid user) {
        if (!ActorSystemAdaptator.isMaterielInActorSystem(user)) {
            // L'utilisateur n'est pas utilisé dans cette tâche
            Oid oidNewActor = ExpressActeurSysteme.createActorSystem(user);
            if (ExpressTask.addActorSystem(GraphicEditorAdaptator.getSelectedExpressTask(),oidNewActor.get())) {
                String[] temp = ExpressActeurSysteme.getActorSystemFromOid(oidNewActor);
                GraphicEditorAdaptator.getPanelProp().addNewActorSystem(temp[0], temp[1] , temp[2], temp[3]);
            } else {
                
            }
        } else {
            
        }
    }
    
    public static void setActorSystemExperience(String oid, String xp) {
        ExpressActeurSysteme.setActorSystemExperience(oid, xp);
    }

    public static void setActorSystemCompetence(String oid, String name) {
    	ExpressActeurSysteme.setActorSystemCompetence(oid, name);
    }
}

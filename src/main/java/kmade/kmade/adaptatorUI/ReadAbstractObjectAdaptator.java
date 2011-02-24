package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.UI.taskproperties.KMADEEditorPrePostIterDialog;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadAbstractObjectPanel;
import kmade.kmade.adaptatorFC.ExpressAbstractAttribut;
import kmade.kmade.adaptatorFC.ExpressAbstractObject;
import kmade.kmade.adaptatorFC.ExpressGroup;
import kmade.nmda.schema.metaobjet.AgregatStructure;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
import kmade.nmda.schema.metaobjet.TypeStructure;

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
public final class ReadAbstractObjectAdaptator {     
    private static ArrayList<ObjetAbstrait> refAbstractObject;
    
    private static ArrayList<Groupe> refGroupes;
    
    private static ArrayList<AttributAbstrait> refAttributs;
    
    private static ObjetAbstrait currentAbstractObject;    
    
    public static KMADEReadAbstractObjectPanel getReadAbstractObjectPanel() {
        return KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel();
    } 
  
    public static void addNewGroup(int selected) {
        // V�rifie s'il existe bien un index parmis la liste
        if (selected < refGroupes.size() && selected >= 0) {
            Groupe myGroupe = refGroupes.get(selected);
            PrePostIterExpressionAdaptator.setNewToken("$" + myGroupe.getName());
        }
    }

    public static void addNewAttribut(int selected) {
        if (selected < refAttributs.size() && selected >= 0) {
            AttributAbstrait myaa = refAttributs.get(selected);
            PrePostIterExpressionAdaptator.setNewToken("$" + myaa.getName());
        }
    }   
    
    public static void initReadAbstractObjectTable() {
        // On prend tous les objets abstrait et on les place dans la table des objets abstraits.
        ReadAbstractObjectAdaptator.refAbstractObject = ExpressAbstractObject.getAbstractObjects();
        Object[][] tabObj = new Object[refAbstractObject.size()][2];
        for (int i = 0; i < refAbstractObject.size(); i++) {
            tabObj[i][0] = refAbstractObject.get(i).getName();
            tabObj[i][1] = refAbstractObject.get(i).getDescription();
        }
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable().setData(tabObj);
        ReadAbstractObjectAdaptator.noAbstractSelection();
    }  
  
    public static void noAbstractSelection() {
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().hideGroupAndAttributPanel();
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable().setAbstractObjectNameBorder("");
        currentAbstractObject = null;
    }

    public static void setAbstractObjectSelection(int minSelectionIndex) {
        ObjetAbstrait refAO = refAbstractObject.get(minSelectionIndex);
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractObjectTable().setAbstractObjectNameBorder(refAO.getName());
        currentAbstractObject = refAO;
        
        // Mise � jour du groupe et de l'attribut.
        refGroupes = ExpressGroup.getGroups(currentAbstractObject);
        String[][] tabGroup = new String[refGroupes.size()][3];
        for (int i = 0; i < refGroupes.size(); i++) {
            tabGroup[i][0] = refGroupes.get(i).getName();
            tabGroup[i][1] = refGroupes.get(i).getDescription();
            tabGroup[i][2] = AgregatStructure.getEnumereIntoLocaleAgregatStructure(refGroupes.get(i).getEnsemble().getAgregatStructure().getValue());
        }
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getGroupTable().setData(tabGroup);
        
        refAttributs = ExpressAbstractAttribut.getAbstractAttributs(currentAbstractObject);
        Object[][] tabAttribut = new Object[refAttributs.size()][4];
        for (int i = 0; i < refAttributs.size(); i++) {
            tabAttribut[i][0] = refAttributs.get(i).getName();
            tabAttribut[i][1] = refAttributs.get(i).getDescription();
            tabAttribut[i][2] = TypeStructure.getEnumereIntoLocaleTypeStructure(refAttributs.get(i).getTypeStructure().getValue());
            tabAttribut[i][3] = refAttributs.get(i).getTypeRef();
        }       
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractAttributTable().setData(tabAttribut);
        
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().showGroupAndAttributPanel();
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getGroupTable().setGroupNameBorder(refAO.getName());
        KMADEEditorPrePostIterDialog.getReadAbstractObjectPanel().getAbstractAttributTable().setAttributNameBorder(refAO.getName());
    }
}

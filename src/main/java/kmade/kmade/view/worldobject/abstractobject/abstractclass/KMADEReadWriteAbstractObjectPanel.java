package kmade.kmade.view.worldobject.abstractobject.abstractclass;

import java.awt.GridLayout;

import javax.swing.JPanel;

import kmade.kmade.adaptatorUI.AbstractObjectPanelAdaptator;
import kmade.kmade.coreadaptator.ExpressEnum;
import kmade.kmade.coreadaptator.ExpressInterval;
import kmade.kmade.view.toolutilities.LanguageFactory;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class KMADEReadWriteAbstractObjectPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 16531L;

    private final KMADEReadWriteGroupTable groupe = new KMADEReadWriteGroupTable();
    
    private final KMADEReadWriteAbstractObjectTable objAbstrait = new KMADEReadWriteAbstractObjectTable();

    private final KMADEReadWriteAbstractAttributesTable attrAbstrait = new KMADEReadWriteAbstractAttributesTable();
     
    public KMADEReadWriteAbstractObjectPanel() {
        JPanel panelHaut = new JPanel(new GridLayout(1, 2, 2, 2));
        panelHaut.add(objAbstrait);      
        panelHaut.add(groupe);  
        this.setLayout(new GridLayout(2,1,2,2));
        this.add(panelHaut);
        this.add(attrAbstrait);        
        this.setActiveAbstractObject("", Oid.OID_NULL);
    }
       
    public void setActiveAbstractObject(String name, String oid) {
        groupe.clearSelection();
        attrAbstrait.clearSelection();
        if (oid.equals(Oid.OID_NULL)) {
            groupe.setVisible(false);
            attrAbstrait.setVisible(false);
            objAbstrait.setAbstractObjectNameBorder("");
        } else {
            objAbstrait.setAbstractObjectNameBorder(name);
        	// La partie "Groupe"         
            groupe.removeAllGroup();
            groupe.setGroupNameBorder(name);
            groupe.setVisible(true);
                        
            Object[][] tab = AbstractObjectPanelAdaptator.getGroupesIntoTab(oid);
            if (tab.length != 0)
            	groupe.updateDataModel(tab);
            
            // La partie "Attribut"
            attrAbstrait.removeAllAttributes();
            attrAbstrait.setAttributeNameBorder(name);
            attrAbstrait.setVisible(true);

            Object[][] tabAttr = AbstractObjectPanelAdaptator.getAttributesIntoTab(oid);
            if (tabAttr.length != 0) {
                attrAbstrait.updateDataModel(tabAttr);
                attrAbstrait.buildEnumerationList(ExpressEnum.getArrayEnum());
                attrAbstrait.buildIntervalList(ExpressInterval.getArrayIntervals());
            }
        }
    }
    
    public KMADEReadWriteAbstractObjectTable getAbstractObjectTable() {
        return objAbstrait;
    }

    public KMADEReadWriteAbstractAttributesTable getAbstractObjectAttributTable() {
        return attrAbstrait;
    }

    public KMADEReadWriteGroupTable getGroupTable() {
        return groupe;
    }

    public void notifLocalisationModification() {
        groupe.notifLocalisationModification();
        objAbstrait.notifLocalisationModification();
        attrAbstrait.notifLocalisationModification();
    }
}

package kmade.kmade.adaptatorUI;

import java.awt.Color;
import java.util.ArrayList;

import kmade.kmade.adaptatorFC.ExpressLabel;
import kmade.kmade.view.KMADEMainFrame;
import kmade.nmda.schema.tache.Label;

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
public final class LabelAdaptator {
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
    
    public static void affRemoveLabel(String oid) {
        ExpressLabel.affRemoveLabel(oid);
    }
    
    public static void removeLabel(String oidAct) {
        ExpressLabel.removeLabel(oidAct);
    }
    
    public static void updateLabelView() {
        ArrayList<Label> myList = ExpressLabel.getLabels();
        for (Label current : myList) {
            KMADEMainFrame.getProjectPanel().getLabelPanel().addLabel(current.getName(), current.getDescription(), current.getColor(), current.isColorVisible(), current.isVisible(), current.getOid().get());
        }
    }
    
    public static String[] getLabelsNameArray() {
        return ExpressLabel.getLabelsNameArray();
    }
    
    public static ArrayList<String> getLabelsNameList() {
        return ExpressLabel.getLabelsNameList();
    }
    
	public static String addLabel() {
		return ExpressLabel.createLabel();
	}
	
	public static void setLabelDescription(String oid, String description) {
		ExpressLabel.setLabelDescription(oid, description);
	}
	
	public static void setLabelColor(String oid, Color color) {
		ExpressLabel.setLabelColor(oid, color);
	}	
	
	public static void setLabelVisible(String oid, boolean p) {
		ExpressLabel.setLabelVisible(oid,p);
	}   
    
    public static void removeAllLabels() {
        KMADEMainFrame.getProjectPanel().getLabelPanel().removeAllLabels();
    }
    
    /**
     * @param newLabelObject
     * @param value
     * @return
     */
    public static String setLabelName(String newLabelObject, String value) {
        return (ExpressLabel.setLabelName(newLabelObject, value));
    }

    /**
     * @param oidRow
     * @param boolean1
     */
    public static void setLabelColorVisible(String oidRow, boolean boolean1) {
        ExpressLabel.setLabelColorVisible(oidRow,boolean1);
    }
}

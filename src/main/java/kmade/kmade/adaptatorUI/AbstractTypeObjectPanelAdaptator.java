package kmade.kmade.adaptatorUI;

import kmade.kmade.view.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;

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
public final class AbstractTypeObjectPanelAdaptator {
    
    private static int stateEnumereOrInterval = 0; // 0 : aucun; 1 : enuemere; 2 : intervalle.
    
    public static void showEnumPanel() {
        KMADEReadWriteAbstractTypeObjectPanel.showEnumOrIntervalPanel();
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().showEnumOrIntervalPanel(true);
        stateEnumereOrInterval = 1;
    }
    
    public static void showIntervalPanel() {
        KMADEReadWriteAbstractTypeObjectPanel.showEnumOrIntervalPanel();
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().showEnumOrIntervalPanel(false);
        stateEnumereOrInterval = 2;
    }
    
    public static void setActiveEnumereOrInterval(int p) {
        if (p == 0) {
            if (stateEnumereOrInterval == 1) {
                AbstractAttributeAdaptator.updateEnumList();
            } else if (stateEnumereOrInterval == 2) {
                AbstractAttributeAdaptator.updateIntervalList();
            }
            KMADEReadWriteAbstractTypeObjectPanel.showAbstractObjectPanel();
        }
        AbstractTypeObjectPanelAdaptator.stateEnumereOrInterval = p;
    }
    
    public static int getActiveEnumereOrInterval() {
        return AbstractTypeObjectPanelAdaptator.stateEnumereOrInterval;
    }
}


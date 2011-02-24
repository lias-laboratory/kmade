package kmade.kmade.adaptatorUI;

import java.util.ArrayList;

import kmade.kmade.UI.worldobject.abstractobject.KMADEReadWriteAbstractTypeObjectPanel;
import kmade.kmade.adaptatorFC.ExpressInterval;
import kmade.nmda.schema.metaobjet.Intervalle;

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
public final class IntervalAdaptator {
	public static void removeAllInterval() {
        KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getIntervalPanel().removeAllInterval();
	}

	public static void removeInterval(String oid) {
		ExpressInterval.removeInterval(oid);
		ReadConcreteObjectAdaptator.updateReadConcreteObject();
	}

	public static void affRemoveIntervalle(String oid) {
		ExpressInterval.affRemoveInterval(oid);
	}

    public static String addInterval() {
    		String interval = ExpressInterval.createInterval();
    		ReadConcreteObjectAdaptator.updateReadConcreteObject();
    		return interval;
    }

	public static String setIntervalName(String oid, String name) {
		String n = ExpressInterval.setIntervalName(oid, name);
		AbstractAttributeAdaptator.updateIntervalList();
		return n;
	}

	public static void setIntervalDescription(String oid, String description) {
		ExpressInterval.setIntervalDescription(oid, description);
	}

	public static void setIntervalMin(String oid, Integer min) {
		ExpressInterval.setIntervalMin(oid, min);
	}

	public static void setIntervalMax(String oid, Integer max) {
		ExpressInterval.setIntervalMax(oid, max);
	}
    
    public static void updateIntervalView() {
        ArrayList<Intervalle> myList = ExpressInterval.getIntervals();
        for (int i = 0; i < myList.size(); i++) {
            KMADEReadWriteAbstractTypeObjectPanel.getEnumIntervalEditor().getIntervalPanel().addInterval(myList.get(i).getName(),myList.get(i).getDescription(),myList.get(i).getMin(), myList.get(i).getMax(), myList.get(i).getOid().get());            
        }        
    }
}

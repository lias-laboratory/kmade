package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.Iterator;
import java.util.Set;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;


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
public class EntityListAdaptator {
    private static SwingWorker worker;
    
    public static void openEntityListDialog() {    
        EntityListAdaptator.startEntityList();
        GraphicEditorAdaptator.getMainFrame().getEntityDialog().setVisible(true);
    } 
    
    public static void stopEntityList() {
        if (worker != null) {
            worker.interrupt();
        }
    }
    
    public static void startEntityList() {
        if (worker != null) {
            worker.interrupt();
            worker = null;
        }       
        worker = new SwingWorker() {
            public Object construct() {
                displayEntityList();
                return null;
            }
        };
        worker.start();
    }
    
    private static synchronized void displayEntityList() {
        Set<Oid> set = InterfaceExpressJava.bdd.keySet();
        GraphicEditorAdaptator.getMainFrame().getEntityDialog().clearTextArea();
        
        for (Iterator<Oid> i = set.iterator(); i.hasNext();) {
            Oid oid = i.next();
            Object o = InterfaceExpressJava.bdd.prendre(oid);
            GraphicEditorAdaptator.getMainFrame().getEntityDialog().writeInTextArea(((Entity)o).toSPF());
        }
    }
    
    public static void closeEntityListDialog() {
        GraphicEditorAdaptator.getMainFrame().getEntityDialog().setVisible(false);
    }
}

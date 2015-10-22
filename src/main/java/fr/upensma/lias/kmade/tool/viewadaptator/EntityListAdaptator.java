/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.util.Iterator;
import java.util.Set;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Entity;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;

/**
 * @author Mickael BARON
 */
public class EntityListAdaptator {

    private static SwingWorker worker;

    public static void openEntityListDialog() {
	EntityListAdaptator.startEntityList();
	GraphicEditorAdaptator.getMainFrame().getEntityDialog()
		.setVisible(true);
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
	    GraphicEditorAdaptator.getMainFrame().getEntityDialog()
		    .writeInTextArea(((Entity) o).toSPF());
	}
    }

    public static void closeEntityListDialog() {
	GraphicEditorAdaptator.getMainFrame().getEntityDialog()
		.setVisible(false);
    }
}

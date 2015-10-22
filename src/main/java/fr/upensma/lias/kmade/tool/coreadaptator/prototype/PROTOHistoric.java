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
package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Thomas Lachaume
 */
public class PROTOHistoric {

    public static void addTask(Task t) {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
		.getHistoricPanel().addNode(t);
    }

    public static void addPrecondition(Task t, boolean respected) {

    }

    public static void addFinished(Task t) {
    }

    public static void descendre() {

    }

    public static void remonter() {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
		.getHistoricPanel().remonter();
    }

    public static void clearHisto() {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
		.getHistoricPanel().clearHisto();

    }

    public static void deleteCurrentTask() {
	GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
		.getHistoricPanel().deleteCurrentTask();
    }

}

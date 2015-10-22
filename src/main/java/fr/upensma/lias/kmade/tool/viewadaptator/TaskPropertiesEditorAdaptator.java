/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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

import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;

/**
 * @author Mickael BARON
 */
public final class TaskPropertiesEditorAdaptator {

    public static void setNameInTaskProperties(String value) {
	TaskPropertiesAdaptator.updateNameRealTime(value);
    }

    public static void setExecutantInTaskProperties(String value) {
	Executor myExec = Executor.getLocaleExecutorIntoExecutor(value);
	KMADEVertexView.editor.setEnableFaculEditor(!myExec
		.equals(Executor.SYS));
	TaskPropertiesAdaptator.updateExecutantTypeRealTime(myExec);
    }

    public static void setOptionalInTaskProperties(boolean b) {
	TaskPropertiesAdaptator.updateOptionalCharacRealTime(b);
    }

    public static void setInterruptibleInTaskProperties(boolean b) {
	TaskPropertiesAdaptator.updateInterruptibleCharacRealTime(b);
    }

    public static void setDecompositionInTaskProperties(String string) {
	TaskPropertiesAdaptator.updateDecompositionRealTime(string);
    }
}

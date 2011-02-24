package fr.upensma.lias.kmade.tool.viewadaptator;

import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;

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
public final class TaskPropertiesEditorAdaptator {
    
    public static void setNameInTaskProperties(String value) {
        TaskPropertiesAdaptator.updateNameRealTime(value);
    }
    
    public static void setExecutantInTaskProperties(String value) {
    	Executant myExec = Executant.getLocaleExecutantIntoExecutant(value);
   		KMADEVertexView.editor.setEnableFaculEditor(!myExec.equals(Executant.SYS));
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

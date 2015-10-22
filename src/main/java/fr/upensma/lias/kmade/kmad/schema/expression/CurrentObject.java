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
package fr.upensma.lias.kmade.kmad.schema.expression;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public class CurrentObject {

    private Groupe currentCheckGroup;

    private ObjetConcret currentEvaluateConcreteObject;

    public void setCurrentCheckGroup(Groupe currentCheckGroup) {
	this.currentCheckGroup = currentCheckGroup;
    }

    public Groupe getCurrentCheckGroup() {
	return this.currentCheckGroup;
    }

    public boolean isExistCurrentCheckGroup() {
	return (this.currentCheckGroup != null);
    }

    public void setCurrentEvaluateConcreteObject(
	    ObjetConcret currentEvaluateConcreteObject) {
	this.currentEvaluateConcreteObject = currentEvaluateConcreteObject;
	currentCheckGroup = currentEvaluateConcreteObject.getAppartientGroupe();
    }

    public ObjetConcret getCurrentEvaluateConcreteObject() {
	return currentEvaluateConcreteObject;
    }

    public void clearCurrentEvaluateConcreteObject() {
	currentEvaluateConcreteObject = null;
	currentCheckGroup = null;
    }

    public boolean isExistCurrentEvaluateConcreteObject() {
	return (this.currentEvaluateConcreteObject != null);
    }

    public String toString() {
	return "G" + " : " + this.currentCheckGroup.getName() + " , "
		+ ExpressConstant.CONCRETE_OBJECT_MESSAGE + " : "
		+ this.currentEvaluateConcreteObject.getName();
    }
}

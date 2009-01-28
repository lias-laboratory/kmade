package kmade.nmda.schema.expression;

import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
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
    
    public void setCurrentEvaluateConcreteObject(ObjetConcret currentEvaluateConcreteObject) {
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
        return "G" + " : " + this.currentCheckGroup.getName() + " , " + ExpressConstant.CONCRETE_OBJECT_MESSAGE + " : " + this.currentEvaluateConcreteObject.getName();
    }
}

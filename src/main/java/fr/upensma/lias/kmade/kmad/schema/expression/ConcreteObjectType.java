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

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;

/**
 * @author Mickael BARON
 */
public interface ConcreteObjectType {

    /**
     * Vérifie si le groupe a un type set
     * 
     * @return
     */
    boolean isGroupSetType();

    boolean isGroupArrayType();
   
    /**
     * Retourne null si il n'y en a pas ou s'il s'agit d'un ensemble avec plus
     * d'un objet concret
     * 
     * @return
     */
    ObjetConcret getConcreteObject();

    /**
     * Récupère la liste complète des objets concrets.
     * 
     * @return
     */
    ArrayList<ObjetConcret> getConcreteObjects();

    void setUserConcreteObject(ObjetConcret p);

    ObjetConcret getUserConcreteObject();

    boolean isEmptyUserConcreteObject();
}
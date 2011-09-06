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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultGraphCell;

/**
 * Class for the cell representing the set groups.
 * 
 * @author Joachim TROUVERIE
 */
public class SetCell extends GroupDefaultGraphCell{

    private static final long serialVersionUID = 2028720001387402470L;
    
    @SuppressWarnings("unused")
    private EnsembleAg set;

    /**
     * First constructor for the cell (the point is not created yet)
     * @param set represented by the cell
     * @param point x in the graph
     * @param point y in the graph
     */
    public SetCell(Groupe object, int x, int y) {
	super(object, x, y);	
	this.set = (EnsembleAg) object.getEnsemble();
    }
    
    /**
     * Second constructor for the cell (the point is already yet)
     * @param set represented by the cell
     * @param point in the graph
     */
    public SetCell(Groupe object, Point p) {
	super(object,p);	
	this.set = (EnsembleAg) object.getEnsemble();
    }


}

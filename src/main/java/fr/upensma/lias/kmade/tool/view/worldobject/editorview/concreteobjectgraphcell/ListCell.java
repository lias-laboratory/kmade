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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.concreteobjectgraphcell;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ListeAg;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultGraphCell;

/**
 * Class for the cell representing the list group.
 * 
 * @author Joachim TROUVERIE
 */
public class ListCell extends GroupDefaultGraphCell {

	private static final long serialVersionUID = -5284774279616678598L;

	@SuppressWarnings("unused")
	private ListeAg list;

	/**
	 * First constructor for the cell (the point is not created yet)
	 * 
	 * @param list  represented by the cell
	 * @param point x in the graph
	 * @param point y in the graph
	 */
	public ListCell(Groupe object, int x, int y) {
		super(object, x, y);
		this.list = (ListeAg) object.getEnsemble();
	}

	/**
	 * Second constructor for the cell (the point is already yet)
	 * 
	 * @param list  represented by the cell
	 * @param point in the graph
	 */
	public ListCell(Groupe object, Point p) {
		super(object, p);
		this.list = (ListeAg) object.getEnsemble();
	}

	/**
	 * Method to set the selected cell in the group
	 * 
	 * @param index of the cell
	 */
	@Override
	public void setSelectedIndex(int i) {
		if (i != -1) {
			this.selectedIndex = 0;
		} else
			this.selectedIndex = i;
	}
}

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
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.PileAg;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultGraphCell;

/**
 * Class for the cell representing stack groups.
 * 
 * @author Joachim TROUVERIE
 */
public class StackCell extends GroupDefaultGraphCell {

	private static final long serialVersionUID = -5888066168242352877L;

	@SuppressWarnings("unused")
	private PileAg stack;

	/**
	 * First constructor for the cell (the point is not created yet)
	 * 
	 * @param stack represented by the cell
	 * @param point x in the graph
	 * @param point y in the graph
	 */
	public StackCell(Groupe o, int x, int y) {
		super(o, x, y);
		this.stack = (PileAg) o.getEnsemble();
	}

	/**
	 * Second constructor for the cell (the point is already yet)
	 * 
	 * @param stack represented by the cell
	 * @param point in the graph
	 */
	public StackCell(Groupe g, Point point) {
		super(g, point);
		this.stack = (PileAg) g.getEnsemble();
	}

	/**
	 * Method to add a cell in the stack in the first position
	 */
	@Override
	public void addCellToGroup(ConcreteObjectCell cell) {
		this.childCells.add(0, cell);
		((ObjetConcret) cell.getObject()).setAppartientGroupe(this.group);
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
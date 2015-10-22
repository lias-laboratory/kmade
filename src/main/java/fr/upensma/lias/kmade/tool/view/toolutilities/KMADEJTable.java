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
package fr.upensma.lias.kmade.tool.view.toolutilities;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class KMADEJTable extends JTable {

    private static final long serialVersionUID = 5310655349676770773L;

    public KMADEJTable() {
	this(null);
    }

    public KMADEJTable(TableModel dm) {
	super(dm);
	this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
	this.setSelectionBackground(KMADEConstant.ACTIVE_SELECTION);

	this.setSelectionForeground(KMADEConstant.ACTIVE_SELECTION_FONT_COLOR);
	this.setRowHeight(KMADEConstant.ROW_HEIGHT);
	this.setRowSelectionAllowed(true);
    }
}

package kmade.kmade.view.toolutilities;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import kmade.kmade.KMADEConstant;

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

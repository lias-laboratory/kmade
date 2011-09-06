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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.jgraph.JGraph;

import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultVertexView;

/**
 * Renderer for the stack cells.
 * 
 * @author Joachim TROUVERIE
 */
public class StackVertexView extends GroupDefaultVertexView {

    private static final long serialVersionUID = 750292847166129896L;

    public StackVertexView(StackCell cell, JGraph graph) {
	super(cell, graph);
    }

    /**
     * Call of the renderer
     */
    public Component getRendererComponent(JGraph graph, boolean selected,
	    boolean focus, boolean preview) {

	panel = (JPanel) super.getRendererComponent(graph, selected, focus,
		preview);
	panel.setLayout(new BorderLayout());
	
	listModel.clear();
	ConcreteObjectCell emptyCell = new ConcreteObjectCell(new ObjetConcret(),
		0, 0);
	emptyCell.setName(KMADEConstant.EMPTY_CELL_NAME);
	listModel.addElement(emptyCell);
	if (!this.cell.getCellsInGroup().isEmpty()) {
	    for (ConcreteObjectCell Ocell : this.cell.getCellsInGroup()) {
		listModel.addElement(Ocell);
	    }
	}
	

	objects = new JList(listModel);

	// The first cell is represented as it is possible to select it
	objects.setCellRenderer(new ListCellRenderer() {
	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {

		if (!((ConcreteObjectCell) value).getName().equals(
			KMADEConstant.EMPTY_CELL_NAME)) {
		    JLabel label = new JLabel(((ConcreteObjectCell) value).getName());
		    label.setOpaque(true);
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    if (index == 1)
			label.setBackground(KMADEConstant.ACTIVE_OBJECT);
		    
		    else
			label.setBackground(KMADEConstant.INACTIVE_OBJECT);
		    
		    return label;
		} else
		    return drawEmpty();
		
		
	    }
	});

	JPanel empty = new JPanel();
	empty.setBorder(BorderFactory
		.createMatteBorder(0, 1, 0, 1, Color.BLACK));

	listPanel = new JScrollPane(objects);
	listPanel.invalidate();
	listPanel.validate();
	listPanel.setOpaque(false);
	listPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,
		Color.BLACK));

	empty.setOpaque(false);

	panel.add(empty, BorderLayout.NORTH);
	panel.add(listPanel, BorderLayout.CENTER);


	this.autoSize();

	return panel;
    }
}
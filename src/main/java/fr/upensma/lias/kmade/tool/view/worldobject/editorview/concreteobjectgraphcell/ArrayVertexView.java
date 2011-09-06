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

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.GroupDefaultVertexView;


/**
 * Renderer for the array cells.
 * 
 * @author Joachim TROUVERIE
 */
public class ArrayVertexView extends GroupDefaultVertexView {

    private static final long serialVersionUID = -8213898490016362073L;

    public ArrayVertexView(ArrayCell cell, JGraph graph) {
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

	listPanel = new JScrollPane(objects);
	listPanel.setOpaque(false);

	panel.add(listPanel, BorderLayout.CENTER);

	// To have numbers alongside the cells
	objects.setCellRenderer(new ListCellRenderer() {

	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {

		JPanel panel = new JPanel(new BorderLayout());

		if (!((ConcreteObjectCell) value).getName().equals(
			KMADEConstant.EMPTY_CELL_NAME)) {

		    String s = String.valueOf(index);
		    JLabel numero = new JLabel(s, JLabel.CENTER);
		    numero.setBorder(BorderFactory
			    .createLineBorder(Color.BLACK));
		    numero.setOpaque(false);

		    JLabel label = new JLabel(((ConcreteObjectCell) value).getName(),JLabel.CENTER);
		    label.setOpaque(true);
		    label.setBackground(KMADEConstant.ACTIVE_OBJECT);
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    
		    panel.add(numero, BorderLayout.WEST);
		    panel.add(label, BorderLayout.CENTER);

		} else
		    panel = drawEmpty();

		return panel;
	    }
	});

	this.autoSize();
	
	return panel;
    }
}

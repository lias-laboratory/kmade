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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

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
 * Renderer for the list cells.
 * 
 * @author Joachim TROUVERIE
 */
public class ListVertexView extends GroupDefaultVertexView {

	private static final long serialVersionUID = -1524303378034073570L;

	public ListVertexView(ListCell cell, JGraph graph) {
		super(cell, graph);
	}

	/**
	 * Call of the renderer
	 */
	public Component getRendererComponent(JGraph graph, boolean selected, boolean focus, boolean preview) {

		panel = (JPanel) super.getRendererComponent(graph, selected, focus, preview);

		panel.setLayout(new BorderLayout());

		// Cells are represented horizontally
		objects.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		objects.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		objects.setVisibleRowCount(1);

		objects.setCellRenderer(new ListCellRenderer() {

			// The first cell is represented as it is possible to select it
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				if (!((ConcreteObjectCell) value).getName().equals(KMADEConstant.EMPTY_CELL_NAME)) {
					JLabel label = new JLabel(((ConcreteObjectCell) value).getName());
					label.setOpaque(true);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					if (index == 0)
						label.setBackground(KMADEConstant.ACTIVE_OBJECT);

					else
						label.setBackground(KMADEConstant.INACTIVE_OBJECT);

					return label;
				} else
					return drawEmpty();

			}
		});

		listPanel = new JScrollPane(objects);
		listPanel.invalidate();
		listPanel.validate();
		listPanel.setOpaque(false);

		listPanel.setPreferredSize(new Dimension((int) objects.getPreferredSize().getWidth() + 10,
				(int) (objects.getPreferredSize().getHeight() + 5)));

		panel.add(listPanel, BorderLayout.CENTER);
		panel.setOpaque(false);

		this.autoSize();

		return panel;
	}

}

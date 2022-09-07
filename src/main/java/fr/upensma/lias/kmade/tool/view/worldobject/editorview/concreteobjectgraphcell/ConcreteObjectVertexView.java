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
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jgraph.JGraph;
import org.jgraph.graph.VertexView;

import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * Class for the renderer of concrete object cell.
 * 
 * @author Joachim TROUVERIE
 */
public class ConcreteObjectVertexView extends VertexView {

	private static final long serialVersionUID = -2853170773819790684L;

	private static Color borderColor = Color.BLACK;

	@SuppressWarnings("unused")
	private JGraph myGraph;

	private String title;

	private String[] attributes;

	private ConcreteObjectCell object;

	public ConcreteObjectVertexView(ConcreteObjectCell cell, JGraph graph) {
		super(cell);
		this.object = cell;
		this.myGraph = graph;
		this.title = object.getName();
		this.attributes = object.getObjectAttributes();
	}

	/**
	 * Call of the renderer
	 */
	public Component getRendererComponent(JGraph graph, boolean selected, boolean focus, boolean preview) {

		Color background;

		JPanel panel = new JPanel();

		if (selected) {
			background = KMADEConstant.ACTIVE_OBJECT;
		} else
			background = KMADEConstant.INACTIVE_OBJECT;

		if (object.getName().endsWith("concret"))
			this.title = "<html><font color=\"red\">" + object.getName() + "</font></html>";
		else
			this.title = object.getName();

		this.attributes = object.getObjectAttributes();

		panel.setLayout(new BorderLayout());
		JLabel titlePanel = new JLabel(this.title, JLabel.CENTER);
		titlePanel.setBorder(BorderFactory.createLineBorder(borderColor));
		titlePanel.setBackground(background);
		titlePanel.setOpaque(true);
		JLabel attributesPanel = new JLabel();
		String a = new String("<html>");
		if (attributes != null) {
			for (int i = 0; i < this.attributes.length; i++) {
				a += " - " + attributes[i] + "<br/>";
			}
		}
		a += "</html>";
		attributesPanel.setText(a);
		attributesPanel.setBorder(BorderFactory.createLineBorder(borderColor));
		attributesPanel.setOpaque(true);
		attributesPanel.setBackground(background);
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(attributesPanel, BorderLayout.CENTER);

		return panel;
	}

}

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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell;

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
 * Renderer for the abstract object cells.
 * 
 * @author Joachim TROUVERIE
 */
public class AbstractObjectVertexView extends VertexView {

    private static final long serialVersionUID = -2853170773819790684L;

    private Color borderColor = Color.BLACK;

    @SuppressWarnings("unused")
    private JGraph myGraph;

    private String title;

    private String[] attributes;

    private AbstractObjectCell object;

    private JPanel panel = new JPanel();

    public AbstractObjectVertexView(Object cell, JGraph graph) {
	super(cell);
	this.object = (AbstractObjectCell) cell;
	this.myGraph = graph;
	this.title = ((AbstractObjectCell) cell).getName();
	this.attributes = ((AbstractObjectCell) cell).getObjectAttributes();
    }

    /**
     * Call of the renderer
     */
    public Component getRendererComponent(JGraph graph, boolean selected,
	    boolean focus, boolean preview) {

	Color background;

	if (selected)
	    background = KMADEConstant.ACTIVE_OBJECT;
	else
	    background = KMADEConstant.INACTIVE_OBJECT;

	if (((AbstractObjectCell) cell).getName().equals(
		KMADEConstant.NEW_OBJECT_NAME))
	    this.title = "<html><font color=\"red\">"
		    + ((AbstractObjectCell) cell).getName() + "</font></html>";
	else
	    this.title = ((AbstractObjectCell) cell).getName();

	this.attributes = ((AbstractObjectCell) cell).getObjectAttributes();

	panel = new JPanel();
	panel.setLayout(new BorderLayout());
	// Title
	JLabel titlePanel = new JLabel(this.title, JLabel.CENTER);
	titlePanel.setBorder(BorderFactory.createLineBorder(borderColor));
	titlePanel.setOpaque(true);
	titlePanel.setBackground(background);
	// Attributes
	JPanel attributesPanel = new JPanel(new BorderLayout());
	JLabel attributesLabel = new JLabel();
	String a = new String("<html>");
	if (attributes != null) {
	    for (int i = 0; i < this.attributes.length; i++) {
		a += " - " + attributes[i] + "<br/>";
	    }
	}
	a += "</html>";
	attributesLabel.setText(a);
	attributesLabel.setOpaque(true);
	attributesLabel.setBackground(background);
	attributesPanel.add(attributesLabel, BorderLayout.CENTER);
	attributesPanel.setBackground(background);
	attributesPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,
		borderColor));
	panel.add(titlePanel, BorderLayout.NORTH);
	panel.add(attributesPanel, BorderLayout.CENTER);

	return panel;
    }

    public Object getObject() {
	return object;
    }

}

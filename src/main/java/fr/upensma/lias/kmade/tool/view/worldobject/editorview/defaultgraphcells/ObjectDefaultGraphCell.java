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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells;

import java.awt.geom.Rectangle2D;
import java.util.Map;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import fr.upensma.lias.kmade.kmad.schema.Entity;

/**
 * Abstract class to defines the concrete and abstract objects.
 * 
 * @author Joachim TROUVERIE
 */
public abstract class ObjectDefaultGraphCell extends DefaultGraphCell {

    private static final long serialVersionUID = 1388699310236979117L;

    protected Entity object;

    /**
     * Constructor for the cell
     * 
     * @param object
     *            represented by the cell
     * @param point
     *            x in the graph
     * @param point
     *            y in the graph
     */
    public ObjectDefaultGraphCell(Entity o, int x, int y) {
	super(o.getName());

	this.object = o;

	Map<?, ?> myHashTable = this.getAttributes();

	GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(x, y, 80,
		40));
	GraphConstants.setEditable(myHashTable, false);
	GraphConstants.setAutoSize(myHashTable, false);
	GraphConstants.setOpaque(myHashTable, false);

	this.setAttributes(new AttributeMap(myHashTable));
    }

    /**
     * @return the cell's width
     */
    public double getWidth() {
	Map<?, ?> myHashTable = this.getAttributes();
	return GraphConstants.getBounds(myHashTable).getWidth();
    }

    /**
     * @return the cell height
     */
    public double getHeight() {
	Map<?, ?> myHashTable = this.getAttributes();
	return GraphConstants.getBounds(myHashTable).getHeight();
    }

    /**
     * @return the object represented by the cell
     */
    public Object getObject() {
	return object;
    }

    /**
     * @return the object's name
     */
    public String getName() {
	return object.getName();
    }

    public abstract String getDescription();

    /**
     * @return the point x in the graph where the cell is located
     */
    public int getX() {
	Map<?, ?> myHashTable = this.getAttributes();
	return (int) GraphConstants.getBounds(myHashTable).getX();
    }

    /**
     * @return the point y in the graph where the cell is located
     */
    public int getY() {
	Map<?, ?> myHashTable = this.getAttributes();
	return (int) GraphConstants.getBounds(myHashTable).getY();
    }

}

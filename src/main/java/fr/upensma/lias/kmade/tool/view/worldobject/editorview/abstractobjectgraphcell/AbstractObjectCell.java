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
package fr.upensma.lias.kmade.tool.view.worldobject.editorview.abstractobjectgraphcell;

import java.awt.geom.Rectangle2D;
import java.util.Map;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.GraphConstants;

import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TypeStructure;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.tool.view.worldobject.editorview.defaultgraphcells.ObjectDefaultGraphCell;

/**
 * Class for the cell representing the abstract objects.
 * 
 * @author Joachim TROUVERIE
 */
public class AbstractObjectCell extends ObjectDefaultGraphCell {

    private static final long serialVersionUID = -3322065665432316166L;

    private ObjetAbstrait item;

    /**
     * First constructor for the cell (the point is not created yet)
     * @param abstract object represented by the cell
     * @param point x in the graph
     * @param point y in the graph
     */
    public AbstractObjectCell(ObjetAbstrait o, int x, int y) {
	super(o, x, y);
	this.item = (ObjetAbstrait) o;
	Oid oidPoint = InterfaceExpressJava.createEntity("tache", "Point");
	Point p = (Point) InterfaceExpressJava.prendre(oidPoint);
	p.setX(x);
	p.setY(y);
	o.setPoint(p);

	Map<?, ?> myHashTable = this.getAttributes();
	GraphConstants.setBounds(myHashTable, new Rectangle2D.Double(x, y, 80,
		40));

	this.setAttributes(new AttributeMap(myHashTable));

    }

    /**
     * Second constructor for the cell (the point is already created)
     * @param object represented by the cell
     * @param point in the graph
     */
    public AbstractObjectCell(ObjetAbstrait o, Point p) {
	super(o, p.getX(), p.getY());
	this.item = (ObjetAbstrait) o;
    }

    /**
     * @return the object's name
     */
    public String getName() {
	return item.getName();
    }

    /**
     * @return a String representing the object's attributes 
     */
    public String[] getObjectAttributes() {
	if (!item.getInverseAttributsAbs().isEmpty()) {
	    String[] attributes = new String[item.getInverseAttributsAbs()
		    .size()];
	    for (int i = 0; i < attributes.length; i++) {
		attributes[i] = this.item.getInverseAttributsAbs().get(i)
			.getName()
			+ " : "
			+ TypeStructure
				.getEnumereIntoLocaleTypeStructure(this.item
					.getInverseAttributsAbs().get(i)
					.getTypeStructure().getValue());
	    }

	    return attributes;
	} else
	    return null;
    }
    /**
     * @return the object's description
     */
    @Override
    public String getDescription() {
	return item.getDescription();
    }

}

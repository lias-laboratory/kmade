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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.VertexView;

/**
 * @author Mickael BARON
 */
public abstract class KMADEGraphLayoutAlgorithm {

    protected static Set<String> LAYOUT_ATTRIBUTES;

    private boolean isAllowedToRun;

    private int progress;

    private int maximumProgress;

    static {
	LAYOUT_ATTRIBUTES = new HashSet<String>();
	LAYOUT_ATTRIBUTES.add("bounds");
	LAYOUT_ATTRIBUTES.add("points");
	LAYOUT_ATTRIBUTES.add("labelposition");
	LAYOUT_ATTRIBUTES.add("routing");
    }

    public KMADEGraphLayoutAlgorithm() {
	isAllowedToRun = true;
	progress = 0;
	maximumProgress = 0;
    }

    public abstract void run(JGraph jgraph, Object aobj[], Object aobj1[]);

    public boolean isAllowedToRun() {
	return isAllowedToRun;
    }

    public void setAllowedToRun(boolean flag) {
	isAllowedToRun = flag;
    }

    public int getMaximumProgress() {
	return maximumProgress;
    }

    public void setMaximumProgress(int i) {
	maximumProgress = i;
    }

    public int getProgress() {
	return progress;
    }

    public void setProgress(int i) {
	progress = i;
    }

    public static void applyLayout(JGraph jgraph,
	    KMADEGraphLayoutAlgorithm jgraphlayoutalgorithm, Object aobj[]) {
	applyLayout(jgraph, jgraphlayoutalgorithm, aobj, null);
    }

    public static void applyLayout(JGraph jgraph,
	    KMADEGraphLayoutAlgorithm jgraphlayoutalgorithm, Object aobj[],
	    Object aobj1[]) {
	JGraph jgraph1 = new JGraph(jgraph.getModel());
	jgraph1.setBounds(jgraph.getBounds());
	GraphLayoutCache graphlayoutcache = jgraph1.getGraphLayoutCache();
	graphlayoutcache.setLocalAttributes(LAYOUT_ATTRIBUTES);
	jgraphlayoutalgorithm.run(jgraph1, aobj, aobj1);
	if (jgraphlayoutalgorithm.isAllowedToRun()) {
	    Hashtable<Object, AttributeMap> hashtable = new Hashtable<Object, AttributeMap>();
	    CellView acellview[] = graphlayoutcache
		    .getAllDescendants(graphlayoutcache.getRoots());
	    for (int i = 0; i < acellview.length; i++) {
		AttributeMap attributemap = acellview[i].getAttributes();
		java.awt.geom.Rectangle2D rectangle2d = GraphConstants
			.getBounds(attributemap);
		if ((acellview[i] instanceof VertexView) && rectangle2d == null) {
		    GraphConstants.setBounds(attributemap,
			    acellview[i].getBounds());
		}
		if (!attributemap.isEmpty()) {
		    hashtable.put(acellview[i].getCell(), attributemap);
		}
	    }

	    if (!hashtable.isEmpty()) {
		jgraph.getGraphLayoutCache().edit(hashtable, null, null, null);
	    }
	}
    }
}

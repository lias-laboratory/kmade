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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.Executor;
import fr.upensma.lias.kmade.kmad.schema.tache.Point;
import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;

/**
 * @author Mickael BARON
 */
public class KMADEDefaultGraphCell extends DefaultGraphCell {

	private static final long serialVersionUID = 1L;

	private Task myTask;

	private String oid;

	private final KMADEDefaultPort motherPort;

	private final KMADEDefaultPort sonPort;

	private boolean isExpanded = true;

	private boolean isSonExpanded = true;

	private boolean isSimulationExpanded = true;

	private boolean isSonSimulationExpanded = true;

	private boolean flagSearch = false;

	private Object tempValue = null;

	public Object getStoreObject() {
		return tempValue;
	}

	public void setStoreObject(Object tempValue) {
		this.tempValue = tempValue;
	}

	/**
	 * V�rifie si le port de la m�re est connect� ou pas.
	 * 
	 * @return
	 */
	public boolean isMotherAndSonPortsNoEdged() {
		// Une autre manière était de vérifier si la tâche possède des enfants
		// ou pas.
		return (motherPort.getEdges().isEmpty() && sonPort.getEdges().isEmpty());
	}

	public boolean isMotherPortNoEdged() {
		return (motherPort.getEdges().isEmpty());
	}

	public boolean isRootTask() {
		return this.myTask.isRoot();
	}

	public KMADEDefaultGraphCell(Task currentTask) {
		this.myTask = currentTask;
		this.myTask.setRefJTask(this);
		this.oid = this.myTask.getOid().get();

		this.motherPort = new KMADEDefaultPort("mother");
		this.sonPort = new KMADEDefaultPort("son");
		this.add(motherPort);
		this.add(sonPort);
		// KMADEHistoryMessageManager.printMessage("methode
		// gettask"+getTask().getLabelName());

		Map<Object, Object> map = new Hashtable<Object, Object>();

		Point p = this.myTask.getPoint();

		if (p == null) {
			myTask.setPoint(new Point(0, 0, new Oid(InterfaceExpressJava.bdd.getMax() + 1)));
			// We need to put the root task out of line
			if (myTask.getNumber().contains(ExpressConstant.ROOT_TASK_NAME)) {
				myTask.getPoint().setX(20);
				myTask.getPoint().setY(20);
			}
		}

		p = this.myTask.getPoint();

		GraphConstants.setBounds(map, new Rectangle2D.Double(p.getX(), p.getY(), 0, 0));
		GraphConstants.setResize(map, true);
		GraphConstants.setAutoSize(map, true);
		GraphConstants.setOpaque(map, false);
		this.getAttributes().applyMap(map);
	}

	public KMADEDefaultPort getMotherPort() {
		return this.motherPort;
	}

	public KMADEDefaultPort getSonPort() {
		return this.sonPort;
	}

	public String getName() {
		return myTask.getName();
	}

	public void setName(String str) {
		myTask.setName(str);
	}

	public String toString() {
		return this.getName();
	}

	/**
	 * @return Returns the decomposition.
	 */
	public String getDecomposition() {
		return Decomposition.getEnumereIntoLocaleDecomposition(this.myTask.getOrdering().getValue());
	}

	/**
	 * @return Returns the facultatif.
	 */
	public boolean isFacultatif() {
		return myTask.isOptional();
	}

	/**
	 * @return Returns the interruptible.
	 */
	public boolean isInterruptible() {
		return myTask.isInterruptible();
	}

	/**
	 * @return Returns the numero.
	 */
	public String getNumero() {
		return myTask.getNumber();
	}

	/**
	 * @return Returns the oid.
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @return Returns the executant.
	 */
	public Executor getExecutant() {
		return myTask.getExecutor();
	}

	/**
	 * @return Returns the myTask.
	 */
	public Task getTask() {
		return myTask;
	}

	public void setTask(Task p) {
		myTask = p;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public boolean isSonExpanded() {
		return isSonExpanded;
	}

	public void setSonExpanded(boolean isExpanded) {
		isSonExpanded = isExpanded;
		this.getMotherPort().setExpanded(isExpanded);
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
		GraphConstants.setMoveable(this.getAttributes(), this.isExpanded);
		if (this.isSonExpanded()) {
			this.getMotherPort().setExpanded(isExpanded);
		}
	}

	public boolean isSimulationExpanded() {
		return this.isSimulationExpanded;
	}

	public boolean isSonSimulationExpanded() {
		return this.isSonSimulationExpanded;
	}

	public void setSonSimulationExpanded(boolean pIsSimulationExpanded) {
		isSonSimulationExpanded = pIsSimulationExpanded;
		this.getMotherPort().setSimulationExpanded(pIsSimulationExpanded);
	}

	public void setSimulationExpanded(boolean pIsSimulationExpanded) {
		this.isSimulationExpanded = pIsSimulationExpanded;
		if (this.isSonSimulationExpanded()) {
			this.getMotherPort().setSimulationExpanded(pIsSimulationExpanded);
		}
	}

	@SuppressWarnings("unchecked")
	public void setDeltaPoint(int dx, int dy) {
		Map<Object, Object> myAttributes = this.getAttributes();
		Rectangle2D myPosition = GraphConstants.getBounds(myAttributes);
		double nx = (myPosition.getX() - (double) dx < 0) ? 0 : myPosition.getX() - (double) dx;
		double ny = (myPosition.getY() - (double) dy < 0) ? 0 : myPosition.getY() - (double) dy;

		GraphConstants.setBounds(myAttributes, new Rectangle2D.Double(nx, ny, 0, 0));
		ExpressTask.setTaskPoint((int) nx, (int) ny, this.myTask);
	}

	@SuppressWarnings("unchecked")
	public void setPoint(double x, double y) {
		Map<Object, Object> myAttributes = this.getAttributes();
		GraphConstants.setBounds(myAttributes, new Rectangle2D.Double(x, y, 0, 0));
		ExpressTask.setTaskPoint((int) x, (int) y, this.myTask);
	}

	public void setExpressPoint(double x, double y) {
		ExpressTask.setTaskPoint((int) x, (int) y, this.myTask);
	}

	public Point getPoint() {
		return this.myTask.getPoint();
	}

	public ArrayList<KMADEDefaultGraphCell> getDirectSubCells() {
		ArrayList<Task> mySubTasks = this.myTask.getChildren();
		ArrayList<KMADEDefaultGraphCell> myCell = new ArrayList<KMADEDefaultGraphCell>();

		for (int i = 0; i < mySubTasks.size(); i++) {
			myCell.add((KMADEDefaultGraphCell) mySubTasks.get(i).getRefJTask());
		}
		return myCell;
	}

	public ArrayList<KMADEDefaultGraphCell> getDescendantSubCells(boolean onlyLeaf) {
		ArrayList<Task> mySubTasks = this.myTask.getChildren();
		ArrayList<KMADEDefaultGraphCell> mySubTasksTotal = new ArrayList<KMADEDefaultGraphCell>();
		for (int i = 0; i < mySubTasks.size(); i++) {
			ArrayList<KMADEDefaultGraphCell> current = ((KMADEDefaultGraphCell) mySubTasks.get(i).getRefJTask())
					.getDescendantSubCells(onlyLeaf);
			for (int j = 0; j < current.size(); j++) {
				mySubTasksTotal.add(current.get(j));
			}
		}

		if (mySubTasks.size() == 0) {
			ArrayList<KMADEDefaultGraphCell> onlyOne = new ArrayList<KMADEDefaultGraphCell>();
			onlyOne.add(this);
			return onlyOne;
		}

		if (!onlyLeaf) {
			mySubTasksTotal.add(this);
		}

		return mySubTasksTotal;
	}

	public boolean isFlagSearch() {
		return flagSearch;
	}

	public void setFlagSearch(boolean flagSearch) {
		this.flagSearch = flagSearch;
	}

	public String getToolTipString() {
		return null;
	}
}

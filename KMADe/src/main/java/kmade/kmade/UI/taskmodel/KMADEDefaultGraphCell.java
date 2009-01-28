package kmade.kmade.UI.taskmodel;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import kmade.kmade.adaptatorFC.ExpressTask;
import kmade.nmda.schema.tache.Decomposition;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Point;
import kmade.nmda.schema.tache.Tache;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

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
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class KMADEDefaultGraphCell extends DefaultGraphCell {
	private static final long serialVersionUID = 1L;

    private Tache myTask;
    
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
     * @return
     */
    public boolean isMotherAndSonPortsNoEdged() {
        // Une autre mani�re �tait de v�rifier si la t�che poss�de des enfants ou pas.
        return (motherPort.getEdges().isEmpty() && sonPort.getEdges().isEmpty());
    }
    
    public boolean isMotherPortNoEdged() {
        return (motherPort.getEdges().isEmpty());
    }
    
    public boolean isRootTask() {
        return this.myTask.isRoot();
    }
    
    public KMADEDefaultGraphCell(Tache currentTask) {
        this.myTask = currentTask;
        this.myTask.setJTask(this);
        this.oid = this.myTask.getOid().get();               
        
        this.motherPort = new KMADEDefaultPort("mother");
        this.sonPort = new KMADEDefaultPort("son");        
        this.add(motherPort);
        this.add(sonPort);

        Map map = new Hashtable();
        Point p = this.myTask.getPoint();
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

	public String toString() {
		return this.getName();
	}

	/**
	 * @return Returns the decomposition.
	 */
	public String getDecomposition() {
		return Decomposition.getEnumereIntoLocaleDecomposition(this.myTask.getDecomposition().getValue());
	}

	/**
	 * @return Returns the facultatif.
	 */
	public boolean isFacultatif() {
		return myTask.isFacultatif();
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
		return myTask.getNumero();
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
    public Executant getExecutant() {
        return myTask.getExecutant();
    }

    /**
     * @return Returns the myTask.
     */
    public Tache getTask() {
        return myTask;
    }
    
    public void setTask(Tache p) {
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
        
    public void setDeltaPoint(int dx, int dy) {
        Map myAttributes = this.getAttributes();
        Rectangle2D myPosition = GraphConstants.getBounds(myAttributes);
        double nx = (myPosition.getX() - (double)dx < 0) ? 0 : myPosition.getX() - (double)dx;
        double ny = (myPosition.getY() - (double)dy < 0) ? 0 : myPosition.getY() - (double)dy;

        GraphConstants.setBounds(myAttributes, new Rectangle2D.Double(nx, ny, 0, 0));
        ExpressTask.setTaskPoint((int)nx, (int)ny, this.myTask);
    }
    
    public void setPoint(double x, double y) {
        Map myAttributes = this.getAttributes();
        GraphConstants.setBounds(myAttributes, new Rectangle2D.Double(x, y, 0, 0));
        ExpressTask.setTaskPoint((int)x, (int)y, this.myTask);
    }
      
    public void setExpressPoint(double x, double y) {
        ExpressTask.setTaskPoint((int)x, (int)y, this.myTask);
    }
    
    public Point getPoint() {
        return this.myTask.getPoint();
    }  
    
    public ArrayList<KMADEDefaultGraphCell> getDirectSubCells() {
        ArrayList<Tache> mySubTasks = this.myTask.getFils();
        ArrayList<KMADEDefaultGraphCell> myCell = new ArrayList<KMADEDefaultGraphCell>();
        
        for (int i = 0; i < mySubTasks.size(); i++) {
            myCell.add((KMADEDefaultGraphCell)mySubTasks.get(i).getJTask());
        }
        return myCell;
    }

    public ArrayList<KMADEDefaultGraphCell> getDescendantSubCells(boolean onlyLeaf) {
        ArrayList<Tache> mySubTasks = this.myTask.getFils();
        ArrayList<KMADEDefaultGraphCell> mySubTasksTotal = new ArrayList<KMADEDefaultGraphCell>();
        for (int i = 0; i < mySubTasks.size(); i++) {
            ArrayList<KMADEDefaultGraphCell> current = ((KMADEDefaultGraphCell)mySubTasks.get(i).getJTask()).getDescendantSubCells(onlyLeaf);
            for (int j = 0; j <current.size(); j++) {
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
}

package kmade.nmda.schema.tache;

import java.io.Serializable;
import java.util.ArrayList;

import kmade.nmda.schema.expression.NodeExpression;
import kmade.nmda.schema.expression.UserExpression;

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
public abstract class Expression implements Serializable {
    protected transient NodeExpression refNode = null;

    protected ArrayList<String> myObjectValues = new ArrayList<String>();
    
    protected String chaine = "";
	
    protected boolean locked = false;
    
    protected String description = "";
    
    public void setDescription(String p) {
    		this.description = p;
    }
    
    public String getDescription() {
    		return description;
    }
    
    public void setObjectValues(ArrayList<String> p) {
    		this.myObjectValues = p;
    }
    
    // Todo : les valeurs utilisateurs concernant les groupes.
    public boolean isAnyUserExpressionInExpression() {
    		ArrayList<Object> myList = this.getNodeExpression().getLinearExpression();
    		for (Object current : myList) {
    			if (current instanceof UserExpression) {
    				return true;
    			}
    		}
    		return false;
    }
    
    public ArrayList<String> getObjectValues() {
    		return this.myObjectValues;
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public void setLocked(boolean p) {
        this.locked = p;
    }
    
    public String toSPF() {
        return chaine;
    }

    public String getName() {
        return chaine;
    }

    public void setName(String pname) {
        chaine = pname;
    }
    
    /**
     * @return Returns the refNode.
     */
    public NodeExpression getNodeExpression() {
        return refNode;
    }

    /**
     * @param refNode The refNode to set.
     */
    public void setNodeExpression(NodeExpression refNode) {
        this.refNode = refNode;
        myObjectValues = new ArrayList<String>();
        locked = false;
    }
    
    public boolean isErrorInExpression() {
        return (refNode == null);
    }
}

package kmade.nmda.schema.expression;

import java.util.ArrayList;

import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.metaobjet.EnsembleAg;
import kmade.nmda.schema.metaobjet.ObjetConcret;

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
public class IsExistAtFunction extends BinaryFunction implements ConcreteObjectType {

    private static final long serialVersionUID = -4093221522071521465L;

    private ObjetConcret refUserObjetConcret;
	
	public IsExistAtFunction(GroupExpressExpression left, NodeExpression right) {
        super(false, left, right);
        this.name = ExpressConstant.IS_EXIST_AT_FUNCTION_EXPRESSION;
    }
    
    // Vérifie si le groupe a un type set
    public boolean isGroupSetType() {
        return (((GroupExpressExpression)this.leftNode).getGroup().getEnsemble() instanceof EnsembleAg);
    }
    
    // Récupère la liste complète des objets concrets
    public ArrayList<ObjetConcret> getConcreteObjects() {
        return (((GroupExpressExpression)this.leftNode).getGroup().getEnsemble().getLstObjConcrets());
    }
    
    // Retourne null si il n'y en a pas ou s'il s'agit d'un ensemble avec plus d'un objet concret
    public ObjetConcret getConcreteObject() {
    	return ((GroupExpressExpression)this.leftNode).getGroup().getEnsemble().get();
    }
    
    public void setUserConcreteObject(ObjetConcret p) {
    	this.refUserObjetConcret = p;
    }
    
    public ObjetConcret getUserConcreteObject() {
    	return this.refUserObjetConcret;
    }
    
    public boolean isEmptyUserConcreteObject() {
        return this.refUserObjetConcret == null; 
    }
    
    public ArrayList<Object> getLinearExpression() {
        ArrayList<Object> myLinearList = new ArrayList<Object>();
        myLinearList.add(this);
        myLinearList.add("(");
        for (Object current:leftNode.getLinearExpression()) {
            myLinearList.add(current);
        }
        myLinearList.add(",");
        for (Object current:rightNode.getLinearExpression()) {
            myLinearList.add(current);
        }
        myLinearList.add(")");
        return myLinearList;
    }
       
    public void checkNode() throws SemanticException {
        super.checkNode();
        
        if (this.rightNode.isBoolean()) {
            this.setStateToUnknown();
            return;
        }
        this.setStateToError();
        throw new SemanticException(ExpressConstant.BOOLEAN_IS_NEEDED_ERROR + " : " + this.getName());
    }
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        if (!this.isGroupSetType()) {
            refUserObjetConcret = this.getConcreteObject();
        }
        super.evaluateNode(refUserObjetConcret);
        if (this.isValueState()) {
            this.setNodeValue(this.rightNode.getNodeValue());
        }
    }
}


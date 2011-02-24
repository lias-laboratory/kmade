package kmade.nmda.schema.expression;

import java.util.ArrayList;

import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.metaobjet.EnsembleAg;
import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.metaobjet.TableauAg;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class RemoveUnaryFunction extends UnaryFunction implements ConcreteObjectType {

    private static final long serialVersionUID = 9202590519366647616L;

    private ObjetConcret refUserObjetConcret;
    
    public RemoveUnaryFunction(GroupExpressExpression pnode) {
        super(false,pnode);
        this.name = "remove";
    }
    
    // V�rifie si le groupe a un type set
    public boolean isGroupSetType() {
        return (((GroupExpressExpression)this.myAloneExpression).getGroup().getEnsemble() instanceof EnsembleAg);
    }
    
    public boolean isGroupArrayType() {
        return (((GroupExpressExpression)this.myAloneExpression).getGroup().getEnsemble() instanceof TableauAg);
    }
    
    // R�cup�re la liste compl�te des objets concrets
    public ArrayList<ObjetConcret> getConcreteObjects() {
        return (((GroupExpressExpression)this.myAloneExpression).getGroup().getEnsemble().getLstObjConcrets());
    }
    
    // Retourne null si il n'y en a pas ou s'il s'agit d'un ensemble avec plus d'un objet concret
    public ObjetConcret getConcreteObject() {
        return ((GroupExpressExpression)this.myAloneExpression).getGroup().getEnsemble().get();
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
        ArrayList<Object> myList = new ArrayList<Object>();
        myList.add(this);
        myList.add("(");
        for (Object current:myAloneExpression.getLinearExpression()) {
            myList.add(current);
        }        
        myList.add(")");
        return myList;
    } 
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {       
    	if (!this.isGroupSetType()&& !this.isGroupArrayType()) {
            refUserObjetConcret = this.getConcreteObject();
        }
        this.setStateToValue();
        
        refUserObjetConcret.getAppartientGroupe().removeFromGroup(refUserObjetConcret);
        refUserObjetConcret.getUtiliseParClass().removeInverseObjConc(refUserObjetConcret);
        
        if (this.isValueState()) {
            InterfaceExpressJava.appendHistoryMessage(this.name);
            InterfaceExpressJava.getCurrentObject().setCurrentEvaluateConcreteObject(refUserObjetConcret);
        } 
    }
}

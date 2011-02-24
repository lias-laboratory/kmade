package kmade.nmda.schema.expression;

import java.util.ArrayList;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.AttributConcret;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetAbstrait;
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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
public class AddUnaryFunction extends UnaryFunction {

    private static final long serialVersionUID = 7194395202228059922L;

    public AddUnaryFunction(GroupExpressExpression pnode) {
        super(false,pnode);
        this.name = ExpressConstant.ADD_UNARY_FUNCTION_EXPRESSION;
    }    
    
    public void checkNode() throws SemanticException {
        ObjetAbstrait groupeStored = InterfaceExpressJava.getCurrentObject().getCurrentCheckGroup().getContientObj();
        ObjetAbstrait groupeAttributed = ((GroupExpressExpression)myAloneExpression).getGroup().getContientObj();

        if (!groupeStored.getName().equals(groupeAttributed.getName())) {
            throw new SemanticException(ExpressConstant.NO_SAME_ABSTRACT_OBJECT + " : \"" + groupeStored.getName() + "\" et " + "\"" + groupeAttributed.getName() + "\""); 
        }
    } 
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
    	
        ObjetConcret myRef = ref;
        if (InterfaceExpressJava.getCurrentObject().isExistCurrentEvaluateConcreteObject()) {
            myRef = InterfaceExpressJava.getCurrentObject().getCurrentEvaluateConcreteObject();
        } else {
            myRef = null;
        }

        if (myRef == null) {
            this.setStateToError();
            throw new SemanticErrorException();
        }
           
        ObjetAbstrait groupeStored = InterfaceExpressJava.getCurrentObject().getCurrentCheckGroup().getContientObj();
        ObjetAbstrait groupeAttributed = ((GroupExpressExpression)myAloneExpression).getGroup().getContientObj();
        
        if (groupeStored.getName().equals(groupeAttributed.getName())) {
            // Création de l'objet concret
            Oid oidObjConc = InterfaceExpressJava.createEntity("metaobjet", "ObjetConcret");
            // Récupère l'objet Abstrait
            ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava.prendre(groupeStored.getOid());
            // Récupère l'instance de l'objet concret
            ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava.prendre(oidObjConc);
            concreteObject.setName(InterfaceExpressJava.getCurrentObject().getCurrentEvaluateConcreteObject().getName());
            // Récupère le groupe
            Groupe g = (Groupe)InterfaceExpressJava.prendre(((GroupExpressExpression)myAloneExpression).getGroup().getOid());
            // Le même objet abstrait
            concreteObject.setUtiliseParClass(abstractObject);
            // Le groupe spécifié par cette fonction 
            concreteObject.setAppartientGroupe(g);
            // La partie liée aux objets concrets
            ArrayList<AttributAbstrait> listattributabs = abstractObject.getInverseAttributsAbs();
            boolean etat = false;
            for (int i = 0; i < listattributabs.size(); i++) {
                Oid oidAttribut = InterfaceExpressJava.createEntity("metaobjet", "AttributConcret");
                AttributConcret concreteAttribut = (AttributConcret) InterfaceExpressJava.prendre(oidAttribut);
                AttributAbstrait abstractAttribut = listattributabs.get(i);
                concreteAttribut.setObjConcDe(concreteObject);
                concreteAttribut.setAttributDe(abstractAttribut);
                concreteAttribut.setName(abstractAttribut.getName());
                etat = etat | concreteAttribut.setValeur(InterfaceExpressJava.getCurrentObject().getCurrentEvaluateConcreteObject().getInverseListAttribut().get(i).getValue().toString());
            }            
            
            if (etat) {
                this.setStateToError();
                throw new SemanticErrorException();
            } else {
            	myRef = concreteObject;
                this.setStateToValue();                
            }
        } else {
            this.setStateToError();
            throw new SemanticErrorException();
        }        
        
        if (this.isValueState()) {
            InterfaceExpressJava.appendHistoryMessage(this.name);
            InterfaceExpressJava.getCurrentObject().setCurrentEvaluateConcreteObject(myRef);            
        } 
    }
}

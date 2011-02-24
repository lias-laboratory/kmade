package kmade.nmda.schema.expression;

import java.util.ArrayList;

import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.Oid;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.AttributConcret;
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
public class CreateFunction extends BinaryFunction {

    private static final long serialVersionUID = 6628594676613676862L;

    public CreateFunction(GroupExpressExpression left, NodeExpression right) {
        super(false, left, right);
        this.name = ExpressConstant.CREATE_FUNCTION_EXPRESSION;
    }
        
    public void evaluateNode(ObjetConcret ref) throws SemanticException {
        super.evaluateNode(ref);
        
        ObjetConcret myRef = null;
        Oid oidObjConc = InterfaceExpressJava.createEntity("metaobjet", "ObjetConcret");
        ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava.prendre(oidObjConc);
        concreteObject.setName(this.rightNode.getNodeValue().toString());
        // Le même objet abstrait
        concreteObject.setUtiliseParClass(((GroupExpressExpression)leftNode).getGroup().getContientObj());
        // Le groupe spécifié par cette fonction 
        concreteObject.setAppartientGroupe(((GroupExpressExpression)leftNode).getGroup());

        ArrayList<AttributAbstrait> listattributabs = concreteObject.getUtiliseParClass().getInverseAttributsAbs();
        for (int i = 0; i < listattributabs.size(); i++) {
            Oid oidAttribut = InterfaceExpressJava.createEntity("metaobjet", "AttributConcret");
            AttributConcret concreteAttribut = (AttributConcret) InterfaceExpressJava.prendre(oidAttribut);
            AttributAbstrait abstractAttribut = listattributabs.get(i);
            concreteAttribut.setObjConcDe(concreteObject);
            concreteAttribut.setAttributDe(abstractAttribut);
            concreteAttribut.setName(abstractAttribut.getName());
            concreteAttribut.setInitValeur();
        }            
        myRef = concreteObject;
        this.setStateToValue();
               
        if (this.isValueState()) {        		
            InterfaceExpressJava.appendHistoryMessage(this.name);
            InterfaceExpressJava.getCurrentObject().setCurrentEvaluateConcreteObject(myRef);            
        } 
    }
}

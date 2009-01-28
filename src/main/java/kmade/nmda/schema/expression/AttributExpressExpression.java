package kmade.nmda.schema.expression;

import kmade.kmade.adaptatorFC.ExpressAbstractAttribut;
import kmade.nmda.ExpressConstant;
import kmade.nmda.interfaceexpressjava.InterfaceExpressJava;
import kmade.nmda.schema.metaobjet.AttributAbstrait;
import kmade.nmda.schema.metaobjet.Groupe;
import kmade.nmda.schema.metaobjet.ObjetConcret;
import kmade.nmda.schema.metaobjet.TypeStructure;
import kmade.nmda.schema.metaobjet.ValeurType;

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
public class AttributExpressExpression extends ExpressExpression {

    private static final long serialVersionUID = -4901763108260815664L;

    // Un attribut doit connaitre son groupe.
	private GroupExpressExpression refGroup;
	
    private AttributAbstrait refAttrAbstrait;
    
    private final boolean groupKnown;
    
    public AttributExpressExpression(String ie, GroupExpressExpression ge) throws ExpressException {
        super(ie);
        this.refGroup = ge;     
        this.setGroup(ge,ie);
        this.groupKnown = true;
    }
    
    public AttributExpressExpression(String ie) {
        super(ie);
        this.refGroup = null;        
        groupKnown = false;
    }

    private void setGroup(GroupExpressExpression refGroup, String ie) throws ExpressException {
        refAttrAbstrait = ExpressAbstractAttribut.getRefAbstractAttributFromName(refGroup.getGroup(), ie);
        if (refAttrAbstrait == null) {
            throw new ExpressException(ExpressConstant.ATTRIBUT_ABSTRAIT + " \"" + this.identExpress + "\" " + ExpressConstant.FROM_GROUP_MESSAGE + " \"" + refGroup.identExpress + "\" " + ExpressConstant.NOT_FOUNDED_ERROR + ".");
        }
        
        // Maintenant il faut déterminer le type de l'attribut.
        TypeStructure typeStruct = refAttrAbstrait.getTypeStructure();        
        if (typeStruct.equals(TypeStructure.ENUM_STRUCT)) {            
            // Un énuméré n'est pas une chaine de caractères?
            this.setNodeType("");
            return;
        } else if (typeStruct.equals(TypeStructure.INTERVAL_STRUCT)) {
            // Un intervalle n'est pas un entier?
            this.setNodeType(0);
            return;
        } else if (typeStruct.equals(TypeStructure.BOOLEAN_STRUCT)){
            this.setNodeType(false);
            return;
        } else if (typeStruct.equals(TypeStructure.STRING_STRUCT)){
            this.setNodeType("");
            return;
        } else if (typeStruct.equals(TypeStructure.INTEGER_STRUCT)){
            this.setNodeType(0);
            return;
        }  
        this.setNodeType(new Object());
        throw new ExpressException(ExpressConstant.ATTRIBUT_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE);        
    }

    
    public void setGroup(GroupExpressExpression refGroup) throws ExpressException {
        this.refGroup = refGroup;
        this.setGroup(refGroup,identExpress);
    }
    
    public GroupExpressExpression getGroupExpressExpression() {
        return this.refGroup;
    }
    
    public AttributAbstrait getAbstractAttribut() {
        return refAttrAbstrait;
    }
    
    public void checkNode() throws SemanticException {
        // Vérifie s'il existe dans InterfaceExpressJava un objet concret.
        if (!this.groupKnown) {
            if (InterfaceExpressJava.getCurrentObject().isExistCurrentCheckGroup()) {
                Groupe myGroupe = InterfaceExpressJava.getCurrentObject().getCurrentCheckGroup();
                try {
                    GroupExpressExpression myExpression = new GroupExpressExpression(myGroupe.getName().toLowerCase());
                    this.setGroup(myExpression);
                } catch(ExpressException e) {
                    throw new SemanticException(e.getMessage());
                }
            } else {
                throw new SemanticException(ExpressConstant.GROUP_NOT_FOUND_EXPRESSION_EXCEPTION_MESSAGE);
            }
        }
    }
    
    public void evaluateNode(ObjetConcret ref) throws SemanticException {        
        if (ref == null) {
            this.setStateToUnknown();
            throw new SemanticUnknownException();
        } else {
            ValeurType myValue = (ValeurType)(ref.getAttribut(refAttrAbstrait).getValue());
            if (myValue == null) {
                this.setStateToError();
                throw new SemanticErrorException();
            } else {
                this.setNodeValue(myValue.getValeur());
            }
        }
    }
}

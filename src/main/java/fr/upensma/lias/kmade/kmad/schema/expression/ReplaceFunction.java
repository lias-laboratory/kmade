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
package fr.upensma.lias.kmade.kmad.schema.expression;

import java.util.ArrayList;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.AttributConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.EnsembleAg;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.Groupe;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetAbstrait;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.TableauAg;

/**
 * @author Mickael BARON
 */
public class ReplaceFunction extends BinaryFunction implements ConcreteObjectType {

	private static final long serialVersionUID = 2721823127863159308L;

	private ObjetConcret refUserObjetConcret;

	public ReplaceFunction(GroupExpressExpression left, GroupExpressExpression right) {
		super(false, left, right);
		this.name = "replace";
	}

	// Check if the group is a set.
	public boolean isGroupSetType() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble() instanceof EnsembleAg);
	}

	public boolean isGroupArrayType() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble() instanceof TableauAg);
	}

	// R�cup�re la liste compl�te des objets concrets
	public ArrayList<ObjetConcret> getConcreteObjects() {
		return (((GroupExpressExpression) this.leftNode).getGroup().getEnsemble().getLstObjConcrets());
	}

	// Retourne null si il n'y en a pas ou s'il s'agit d'un ensemble avec plus
	// d'un objet concret
	public ObjetConcret getConcreteObject() {
		return ((GroupExpressExpression) this.leftNode).getGroup().getEnsemble().get();
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
		for (Object current : leftNode.getLinearExpression()) {
			myLinearList.add(current);
		}
		myLinearList.add(",");
		for (Object current : rightNode.getLinearExpression()) {
			myLinearList.add(current);
		}
		myLinearList.add(")");
		return myLinearList;
	}

	public void evaluateNode(ObjetConcret ref) throws SemanticException {
		if (!this.isGroupSetType() && !this.isGroupArrayType()) {
			refUserObjetConcret = this.getConcreteObject();
		}

		ObjetAbstrait groupeStored = refUserObjetConcret.getUtiliseParClass();
		ObjetAbstrait groupeAttributed = ((GroupExpressExpression) rightNode).getGroup().getContientObj();

		if (groupeStored.getName().equals(groupeAttributed.getName())) {
			// Cr�ation de l'objet concret
			Oid oidObjConc = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE,
					ExpressConstant.CONCRETE_OBJECT_CLASS);
			// R�cup�re l'objet Abstrait
			ObjetAbstrait abstractObject = (ObjetAbstrait) InterfaceExpressJava.prendre(groupeStored.getOid());
			// R�cup�re l'instance de l'objet concret
			ObjetConcret concreteObject = (ObjetConcret) InterfaceExpressJava.prendre(oidObjConc);
			concreteObject.setName(refUserObjetConcret.getName());
			// R�cup�re le groupe
			Groupe g = (Groupe) InterfaceExpressJava.prendre(((GroupExpressExpression) rightNode).getGroup().getOid());
			// Le m�me objet abstrait
			concreteObject.setUtiliseParClass(groupeStored);
			// Le groupe sp�cifi� par cette fonction
			concreteObject.setAppartientGroupe(g);
			// La partie li�e aux objets concrets
			ArrayList<AttributAbstrait> listattributabs = abstractObject.getInverseAttributsAbs();
			boolean etat = false;
			for (int i = 0; i < listattributabs.size(); i++) {
				Oid oidAttribut = InterfaceExpressJava.createEntity(ExpressConstant.METAOBJECT_PACKAGE,
						ExpressConstant.CONCRETE_ATTRIBUTE_CLASS);
				AttributConcret concreteAttribut = (AttributConcret) InterfaceExpressJava.prendre(oidAttribut);
				AttributAbstrait abstractAttribut = listattributabs.get(i);
				concreteAttribut.setObjConcDe(concreteObject);
				concreteAttribut.setAttributDe(abstractAttribut);
				concreteAttribut.setName(abstractAttribut.getName());
				etat = etat | concreteAttribut
						.setValeur(refUserObjetConcret.getInverseListAttribut().get(i).getValue().toString());
			}

			// Suppression du premier objet concret.
			refUserObjetConcret.delete();

			if (etat) {
				this.setStateToError();
				throw new SemanticErrorException();
			} else {
				this.setStateToValue();
				refUserObjetConcret = concreteObject;
			}
		} else {
			this.setStateToError();
			throw new SemanticErrorException();
		}

		if (this.isValueState()) {
			InterfaceExpressJava.appendHistoryMessage(this.name);
			InterfaceExpressJava.getCurrentObject().setCurrentEvaluateConcreteObject(refUserObjetConcret);
		}
	}
}

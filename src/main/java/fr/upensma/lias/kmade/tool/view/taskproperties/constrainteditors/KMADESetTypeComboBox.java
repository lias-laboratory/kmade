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
package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.tool.KMADEConstant;

/**
 * @author Mickael BARON
 */
public class KMADESetTypeComboBox extends KMADEGroupTypeComboBox {
	private static final long serialVersionUID = -5106570507739645552L;

	private ArrayList<ObjetConcret> refObjetConcret;

	private ConcreteObjectType ref;

	public KMADESetTypeComboBox(ConcreteObjectType ref, ArrayList<ObjetConcret> concreteObjects) {
		refObjetConcret = concreteObjects;
		this.ref = ref;
		Object[] refTab = new Object[refObjetConcret.size() + 1];
		refTab[0] = KMADEConstant.NO_CONCRETE_OBJECT_GROUPE_NAME;
		for (int i = 0; i < concreteObjects.size(); i++) {
			refTab[i + 1] = concreteObjects.get(i);
		}
		this.setModel(new DefaultComboBoxModel(refTab));

		if (ref.getUserConcreteObject() != null) {
			this.setSelectedItem(ref.getUserConcreteObject());
		} else {
			this.setSelectedIndex(0);
			ref.setUserConcreteObject(null);
		}

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (KMADESetTypeComboBox.this.getSelectedIndex() == 0) {
					KMADESetTypeComboBox.this.ref.setUserConcreteObject(null);
				} else {
					KMADESetTypeComboBox.this.ref
							.setUserConcreteObject((ObjetConcret) KMADESetTypeComboBox.this.getSelectedItem());
				}
			}
		});
	}

	public ConcreteObjectType getConcreteObjectType() {
		return ref;
	}

	public boolean isConcreteObjectEmpty() {
		return ref.isEmptyUserConcreteObject();
	}
}

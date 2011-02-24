package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;
import fr.upensma.lias.kmade.kmad.schema.metaobjet.ObjetConcret;
import fr.upensma.lias.kmade.tool.KMADEConstant;

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
public class KMADEArrayTypeComboBox extends KMADEGroupTypeComboBox {
		private static final long serialVersionUID = -5106570507739645552L;

		private ArrayList<ObjetConcret> refObjetConcret;
		
		private ConcreteObjectType ref;
		
		public KMADEArrayTypeComboBox(ConcreteObjectType ref, ArrayList<ObjetConcret> concreteObjects) {
			refObjetConcret = concreteObjects;
			this.ref = ref;
			Object[] refTab = new Object[refObjetConcret.size() + 1];
			refTab[0] = KMADEConstant.NO_CONCRETE_OBJECT_GROUPE_NAME;
			for (int i = 0; i < concreteObjects.size(); i++) {
				// on met la liste de 1 à n (numérotation de l'affichage)
				refTab[i + 1] = i+1;
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
					if (KMADEArrayTypeComboBox.this.getSelectedIndex() == 0) {
						KMADEArrayTypeComboBox.this.ref.setUserConcreteObject(null);
					} else {
						// l'objet numéro i est à la ième position - 1
						ObjetConcret tmp = refObjetConcret.get(-1 + (Integer) KMADEArrayTypeComboBox.this.getSelectedItem());
						KMADEArrayTypeComboBox.this.ref.setUserConcreteObject(tmp);
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


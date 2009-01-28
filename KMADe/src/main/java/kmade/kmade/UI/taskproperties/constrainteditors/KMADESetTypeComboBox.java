package kmade.kmade.UI.taskproperties.constrainteditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import kmade.kmade.KMADEConstant;
import kmade.nmda.schema.expression.ConcreteObjectType;
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
public class KMADESetTypeComboBox extends JComboBox {
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
					KMADESetTypeComboBox.this.ref.setUserConcreteObject((ObjetConcret)KMADESetTypeComboBox.this.getSelectedItem());
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

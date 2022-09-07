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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstractclass;

import java.awt.GridLayout;

import javax.swing.JPanel;

import fr.upensma.lias.kmade.kmad.schema.Oid;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressInterval;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractObjectPanelAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteAbstractObjectPanel extends JPanel implements LanguageFactory {
	private static final long serialVersionUID = 16531L;

	private final KMADEReadWriteGroupTable groupe = new KMADEReadWriteGroupTable();

	private final KMADEReadWriteAbstractObjectTable objAbstrait = new KMADEReadWriteAbstractObjectTable();

	private final KMADEReadWriteAbstractAttributesTable attrAbstrait = new KMADEReadWriteAbstractAttributesTable();

	public KMADEReadWriteAbstractObjectPanel() {
		JPanel panelHaut = new JPanel(new GridLayout(1, 2, 2, 2));
		panelHaut.add(objAbstrait);
		panelHaut.add(groupe);
		this.setLayout(new GridLayout(2, 1, 2, 2));
		this.add(panelHaut);
		this.add(attrAbstrait);
		this.setActiveAbstractObject("", Oid.OID_NULL);
	}

	public void setActiveAbstractObject(String name, String oid) {
		groupe.clearSelection();
		attrAbstrait.clearSelection();
		if (oid.equals(Oid.OID_NULL)) {
			groupe.setVisible(false);
			attrAbstrait.setVisible(false);
			objAbstrait.setAbstractObjectNameBorder("");
		} else {
			objAbstrait.setAbstractObjectNameBorder(name);
			// La partie "Groupe"
			groupe.removeAllGroup();
			groupe.setGroupNameBorder(name);
			groupe.setVisible(true);

			Object[][] tab = AbstractObjectPanelAdaptator.getGroupesIntoTab(oid);
			if (tab.length != 0)
				groupe.updateDataModel(tab);

			// La partie "Attribut"
			attrAbstrait.removeAllAttributes();
			attrAbstrait.setAttributeNameBorder(name);
			attrAbstrait.setVisible(true);

			Object[][] tabAttr = AbstractObjectPanelAdaptator.getAttributesIntoTab(oid);
			if (tabAttr.length != 0) {
				attrAbstrait.updateDataModel(tabAttr);
				attrAbstrait.buildEnumerationList(ExpressEnum.getArrayEnum());
				attrAbstrait.buildIntervalList(ExpressInterval.getArrayIntervals());
			}
		}
	}

	public KMADEReadWriteAbstractObjectTable getAbstractObjectTable() {
		return objAbstrait;
	}

	public KMADEReadWriteAbstractAttributesTable getAbstractObjectAttributTable() {
		return attrAbstrait;
	}

	public KMADEReadWriteGroupTable getGroupTable() {
		return groupe;
	}

	public void notifLocalisationModification() {
		groupe.notifLocalisationModification();
		objAbstrait.notifLocalisationModification();
		attrAbstrait.notifLocalisationModification();
	}
}

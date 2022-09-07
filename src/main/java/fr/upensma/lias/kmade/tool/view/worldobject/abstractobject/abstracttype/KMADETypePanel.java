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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstracttype;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;

/**
 * @author Mickael BARON
 */
public class KMADETypePanel extends JPanel implements LanguageFactory {

	private static final long serialVersionUID = 98756L;

	private final JPanel panel;

	private final KMADETypeEnumTable enumeration = new KMADETypeEnumTable();

	private final KMADETypeEnumElementTable element = new KMADETypeEnumElementTable();

	private final KMADETypeIntervalTable intervalle = new KMADETypeIntervalTable();

	public KMADETypePanel() {
		// Le JPanel pour la partie Enumeration.
		panel = new JPanel(new GridLayout(1, 0));
		panel.add(enumeration);
		panel.add(element);

		this.setLayout(new GridLayout(0, 1));
		this.add(intervalle);
		this.add(panel);
	}

	public KMADETypeIntervalTable getIntervalPanel() {
		return this.intervalle;
	}

	public KMADETypeEnumElementTable getEnumElementPanel() {
		return this.element;
	}

	public KMADETypeEnumTable getEnumPanel() {
		return this.enumeration;
	}

	public void setBorderTypePanel(String afficher) {
		panel.setBorder(new TitledBorder(null, KMADEConstant.ENUMERATION_TITLE_TABLE + afficher, TitledBorder.CENTER,
				TitledBorder.DEFAULT_POSITION));
	}

	public void showEnumOrIntervalPanel(boolean b) {
		intervalle.setVisible(!b);
		panel.setVisible(b);
		enumeration.setVisible(b);
		element.setVisible(false);

		if (!b) {
			panel.setBorder(new TitledBorder(null, KMADEConstant.ENUMERATION_TITLE_TABLE, TitledBorder.CENTER,
					TitledBorder.DEFAULT_POSITION));
		} else {
			enumeration.setFirstRowIndexTable();
		}
	}

	public void notifLocalisationModification() {
		enumeration.notifLocalisationModification();
		element.notifLocalisationModification();
		intervalle.notifLocalisationModification();
	}
}
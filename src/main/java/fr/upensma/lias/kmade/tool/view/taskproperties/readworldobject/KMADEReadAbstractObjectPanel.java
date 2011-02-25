/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;

/**
 * @author Mickael BARON
 */
public class KMADEReadAbstractObjectPanel extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = -8765013733902053802L;

    private final KMADEReadAbstractObjectTable objAbstrait = new KMADEReadAbstractObjectTable();

    private final KMADEReadAbstractObjectGroupTable groupe = new KMADEReadAbstractObjectGroupTable();

    private final KMADEReadAbstractObjectAttributTable attrAbstrait = new KMADEReadAbstractObjectAttributTable();

    private TitledBorder myTitledBorder;

    public KMADEReadAbstractObjectPanel() {
	myTitledBorder = new TitledBorder(null,
		KMADEConstant.ABSTRACT_OBJECT_LIST_MESSAGE_TITLE,
		TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
		KMADEConstant.fontACTIF);
	this.setBorder(myTitledBorder);
	JPanel panelHaut = new JPanel(new GridLayout(1, 2, 2, 2));
	panelHaut.add(objAbstrait);
	panelHaut.add(groupe);
	this.setLayout(new GridLayout(2, 1, 2, 2));
	this.add(panelHaut);
	this.add(attrAbstrait);
    }

    public void hideGroupAndAttributPanel() {
	groupe.setVisible(false);
	attrAbstrait.setVisible(false);
    }

    public void showGroupAndAttributPanel() {
	groupe.setVisible(true);
	attrAbstrait.setVisible(true);
    }

    public KMADEReadAbstractObjectTable getAbstractObjectTable() {
	return this.objAbstrait;
    }

    public KMADEReadAbstractObjectAttributTable getAbstractAttributTable() {
	return this.attrAbstrait;
    }

    public KMADEReadAbstractObjectGroupTable getGroupTable() {
	return this.groupe;
    }

    public void notifLocalisationModification() {
	myTitledBorder
		.setTitle(KMADEConstant.ABSTRACT_OBJECT_LIST_MESSAGE_TITLE);
    }
}

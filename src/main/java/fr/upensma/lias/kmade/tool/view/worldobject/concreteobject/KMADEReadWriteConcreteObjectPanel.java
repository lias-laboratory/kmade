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
package fr.upensma.lias.kmade.tool.view.worldobject.concreteobject;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;

/**
 * @author Mickael BARON
 */
public class KMADEReadWriteConcreteObjectPanel extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = 565653L;

    private KMADEReadAbstractObjectTable refAbstractObjectTable = new KMADEReadAbstractObjectTable();

    private KMADEReadWriteConcreteObjectTreeTable refConcreteObjectTreeTable = new KMADEReadWriteConcreteObjectTreeTable();

    private KMADEReadWriteConcreteAttributTable refConcreteAttributTable = new KMADEReadWriteConcreteAttributTable();

    public KMADEReadWriteConcreteObjectPanel() {
	this.setBorder(new TitledBorder(null,
		KMADEConstant.CONCRETE_OBJECT_TITLE_NAME, TitledBorder.CENTER,
		TitledBorder.TOP));
	JPanel panelHaut = new JPanel(new GridLayout(1, 2, 2, 2));
	panelHaut.add(refAbstractObjectTable);
	panelHaut.add(refConcreteObjectTreeTable);
	this.setLayout(new GridLayout(2, 1, 2, 2));
	this.add(panelHaut);
	this.add(refConcreteAttributTable);
    }

    public void removeAllConcreteObject() {
	refAbstractObjectTable.removeAllAbstractObject();
	refConcreteAttributTable.removeAllConcreteAttribut();
    }

    public KMADEReadAbstractObjectTable getTableObjAbs() {
	return this.refAbstractObjectTable;
    }

    public KMADEReadWriteConcreteObjectTreeTable getTreeTableObjConc() {
	return this.refConcreteObjectTreeTable;
    }

    public KMADEReadWriteConcreteAttributTable getTableAttr() {
	return this.refConcreteAttributTable;
    }

    public void hideConcreteObjectsAttributes() {
	refConcreteObjectTreeTable.setVisible(false);
	refConcreteAttributTable.setVisible(false);
    }

    public void showConcreteObjects() {
	refConcreteObjectTreeTable.setVisible(true);
    }

    public void showConcreteAttributes() {
	refConcreteAttributTable.setVisible(true);
    }

    public void hideConcreteAttributes() {
	refConcreteAttributTable.setVisible(false);
    }

    public void notifLocalisationModification() {
	refAbstractObjectTable.notifLocalisationModification();
	refConcreteObjectTreeTable.notifLocalisationModification();
	refConcreteAttributTable.notifLocalisationModification();
    }
}

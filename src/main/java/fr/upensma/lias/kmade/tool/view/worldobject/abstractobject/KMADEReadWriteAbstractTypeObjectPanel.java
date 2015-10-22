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
package fr.upensma.lias.kmade.tool.view.worldobject.abstractobject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstractclass.KMADEReadWriteAbstractObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.abstractobject.abstracttype.KMADETypePanel;
import fr.upensma.lias.kmade.tool.viewadaptator.AbstractTypeObjectPanelAdaptator;

/**
 * @author Mickael BARON
 */
public final class KMADEReadWriteAbstractTypeObjectPanel extends JPanel
	implements LanguageFactory {
    private static final long serialVersionUID = 5647468L;

    private static final KMADETypePanel enumIntervalEditor = new KMADETypePanel();

    private static final KMADEReadWriteAbstractObjectPanel abstractObjectEditor = new KMADEReadWriteAbstractObjectPanel();

    private static JPanel cards;

    private TitledBorder myTitledBorder;

    private JButton bVers;

    public static KMADETypePanel getEnumIntervalEditor() {
	return enumIntervalEditor;
    }

    public static JPanel getCardLayout() {
	return cards;
    }

    public static KMADEReadWriteAbstractObjectPanel getAbstractObjectEditor() {
	return abstractObjectEditor;
    }

    public KMADEReadWriteAbstractTypeObjectPanel() {
	myTitledBorder = new TitledBorder(null,
		KMADEConstant.ABSTRACT_OBJECT_TITLE_NAME, TitledBorder.CENTER,
		TitledBorder.TOP);
	this.setBorder(myTitledBorder);
	this.setLayout(new BorderLayout());

	JPanel p = new JPanel();
	bVers = new JButton(KMADEConstant.BACK_TO_EDITOR);
	p.add(bVers);

	cards = new JPanel(new CardLayout());

	JPanel pane = new JPanel(new BorderLayout());
	pane.add(abstractObjectEditor, BorderLayout.CENTER);

	JPanel type = new JPanel(new BorderLayout());
	type.add(enumIntervalEditor, BorderLayout.CENTER);
	type.add(p, BorderLayout.NORTH);

	cards.add(pane, "ABSTRACTOBJECT");
	cards.add(type, "TYPE");
	this.add(cards, BorderLayout.CENTER);

	bVers.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		AbstractTypeObjectPanelAdaptator.setActiveEnumereOrInterval(0);
	    }
	});
    }

    public static void showAbstractObjectPanel() {
	CardLayout ca = (CardLayout) cards.getLayout();
	ca.show(cards, "ABSTRACTOBJECT");
    }

    public static void showEnumOrIntervalPanel() {
	CardLayout ca = (CardLayout) cards.getLayout();
	ca.show(cards, "TYPE");
    }

    public void notifLocalisationModification() {
	myTitledBorder.setTitle(KMADEConstant.ABSTRACT_OBJECT_TITLE_NAME);
	bVers.setText(KMADEConstant.BACK_TO_EDITOR);
	enumIntervalEditor.notifLocalisationModification();
	abstractObjectEditor.notifLocalisationModification();
    }
}

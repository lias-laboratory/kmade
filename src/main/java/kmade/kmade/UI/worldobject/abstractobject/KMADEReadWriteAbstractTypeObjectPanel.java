package kmade.kmade.UI.worldobject.abstractobject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.UI.worldobject.abstractobject.abstractclass.KMADEReadWriteAbstractObjectPanel;
import kmade.kmade.UI.worldobject.abstractobject.abstracttype.KMADETypePanel;
import kmade.kmade.adaptatorUI.AbstractTypeObjectPanelAdaptator;

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
public final class KMADEReadWriteAbstractTypeObjectPanel extends JPanel implements LanguageFactory {
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
        myTitledBorder = new TitledBorder(null, KMADEConstant.ABSTRACT_OBJECT_TITLE_NAME, TitledBorder.CENTER, TitledBorder.TOP); 
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
        CardLayout ca = (CardLayout)cards.getLayout();
        ca.show(cards, "ABSTRACTOBJECT");        
    }
    
    public static void showEnumOrIntervalPanel() {
        CardLayout ca = (CardLayout)cards.getLayout();
        ca.show(cards, "TYPE");        
    }

    public void notifLocalisationModification() {
        myTitledBorder.setTitle(KMADEConstant.ABSTRACT_OBJECT_TITLE_NAME);
        bVers.setText(KMADEConstant.BACK_TO_EDITOR);
        enumIntervalEditor.notifLocalisationModification();
        abstractObjectEditor.notifLocalisationModification();
    }
}

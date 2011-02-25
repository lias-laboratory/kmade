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
package fr.upensma.lias.kmade.tool.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.upensma.lias.kmade.KMADEToolConstant;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHeapView;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;

/**
 * @author Mickael BARON
 */
public class KMADEStatusBar extends JPanel implements LanguageFactory {

    private static final long serialVersionUID = 1840698728748992207L;

    protected JLabel leftSideStatus;

    public KMADEStatusBar() {
	super();
	this.setLayout(new BorderLayout());
	this.leftSideStatus = new JLabel(KMADEConstant.VERSION_MESSAGE + " "
		+ KMADEToolConstant.VERSION_VALUE);
	this.leftSideStatus.setBorder(BorderFactory.createLoweredBevelBorder());
	this.add(leftSideStatus, BorderLayout.CENTER);
	JComponent heapView = new KMADEHeapView();
	heapView.setBorder(BorderFactory.createLoweredBevelBorder());
	this.add(heapView, BorderLayout.EAST);
    }

    public void notifLocalisationModification() {
	this.leftSideStatus.setText(KMADEConstant.VERSION_MESSAGE + " "
		+ KMADEToolConstant.VERSION_VALUE);
    }
}

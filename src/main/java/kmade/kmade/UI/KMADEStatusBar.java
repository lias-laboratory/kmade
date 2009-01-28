package kmade.kmade.UI;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kmade.KMADEToolConstant;
import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.KMADEHeapView;
import kmade.kmade.UI.toolutilities.LanguageFactory;

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
public class KMADEStatusBar extends JPanel implements LanguageFactory {
    private static final long serialVersionUID = 1840698728748992207L;

    protected JLabel leftSideStatus;
        
    public KMADEStatusBar() {
        super();
        this.setLayout(new BorderLayout());
        this.leftSideStatus = new JLabel(KMADEConstant.VERSION_MESSAGE + " " + KMADEToolConstant.VERSION_VALUE);
        this.leftSideStatus.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(leftSideStatus, BorderLayout.CENTER);
        JComponent heapView = new KMADEHeapView();
        heapView.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(heapView, BorderLayout.EAST);
    }
   
    public void notifLocalisationModification() {
        this.leftSideStatus.setText(KMADEConstant.VERSION_MESSAGE + " " + KMADEToolConstant.VERSION_VALUE);
    }
}

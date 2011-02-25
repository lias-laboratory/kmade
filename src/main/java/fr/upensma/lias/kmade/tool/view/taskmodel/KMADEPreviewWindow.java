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
package fr.upensma.lias.kmade.tool.view.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;


import org.jgraph.JGraph;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;

/**
 * @author Mickael BARON
 */
public class KMADEPreviewWindow extends JDialog {
	private static final long serialVersionUID = 1162975786596750565L;

	private final KMADEPreviewPanel myPreviewPanel = new KMADEPreviewPanel();
    
	public KMADEPreviewWindow(Frame owner) {
        super(owner, KMADEConstant.PREVIEW_WINDOW_TITLE_NAME, false);
		this.getContentPane().setForeground(Color.lightGray);
		this.add(BorderLayout.CENTER, myPreviewPanel);
		this.pack();
		this.setSize(new Dimension(150,150));
		this.setResizable(true);
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
		this.repaint();
	}
	
	public void setOverviewGraph(KMADEGraph graphModel) {
		myPreviewPanel.setOverviewGraph(graphModel);
	}
	
	public JGraph getOverviewGraph() {
		return myPreviewPanel.getOverviewGraph();
	}
	
	public void updatePreviewPanel() {
		myPreviewPanel.repaint();
	}

    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.PREVIEW_WINDOW_TITLE_NAME);
    }
}

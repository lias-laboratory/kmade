package kmade.kmade.UI.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.InDevelopmentGlassPanel;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;

import org.jgraph.JGraph;

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
 * @author Mickaël BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 **/
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

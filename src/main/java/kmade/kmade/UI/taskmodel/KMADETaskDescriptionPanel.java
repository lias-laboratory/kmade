package kmade.kmade.UI.taskmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.KMADETaskPropertiesPanel;
import kmade.kmade.UI.toolutilities.KMADEEnhancedSplitPane;

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
public class KMADETaskDescriptionPanel extends JPanel {
    private static final long serialVersionUID = 4329498250171579508L;

	private static final KMADETaskModelPanel jgraphTaskPanel = new KMADETaskModelPanel();

	private static final KMADETaskPropertiesPanel panelProprieteTache = new KMADETaskPropertiesPanel();
    
	private KMADERule columnView;

	private KMADERule rowView;

	private JScrollPane scrollerPanelGraphTache;
	
    private TitledBorder myTitledBorder;    
    
    
    private JSplitPane jSplitPaneV;
    
	public KMADETaskDescriptionPanel() {
		columnView = new KMADERule(KMADERule.HORIZONTAL, true);
		columnView.setPreferredWidth(30);

		rowView = new KMADERule(KMADERule.VERTICAL, true);
		rowView.setPreferredWidth(30);

		JPanel buttonCorner = new JPanel();
        buttonCorner.setBackground(new Color(165, 163, 151));
		JToggleButton isMetric = new JToggleButton("cm", true);
		isMetric.setFont(new Font("SansSerif", Font.PLAIN, 11));
		isMetric.setMargin(new Insets(2, 2, 2, 2));
		isMetric.addItemListener(new UnitsListener());
		buttonCorner.add(isMetric); 

		KMADECorner lowerLeft = new KMADECorner();
		KMADECorner upperRight = new KMADECorner();

		this.setLayout(new BorderLayout());
		
		

		// Ajout des composants avec JSplitPane
		
		scrollerPanelGraphTache = new JScrollPane(jgraphTaskPanel);
		
		scrollerPanelGraphTache.setColumnHeaderView(columnView);
		scrollerPanelGraphTache.setRowHeaderView(rowView);

		scrollerPanelGraphTache.setCorner(JScrollPane.UPPER_LEFT_CORNER, buttonCorner);
		scrollerPanelGraphTache.setCorner(JScrollPane.LOWER_LEFT_CORNER, lowerLeft);
		scrollerPanelGraphTache.setCorner(JScrollPane.UPPER_RIGHT_CORNER, upperRight);
		
		JPanel my_panel_central = new JPanel(new BorderLayout());
        my_panel_central.setMinimumSize(new Dimension(600,600));
        myTitledBorder = new TitledBorder(null, KMADEConstant.TASK_MODEL_TITLE_NAME, TitledBorder.CENTER, TitledBorder.TOP);
		my_panel_central.add(BorderLayout.CENTER, scrollerPanelGraphTache);
        
		// Initialisation des JSplitPane
		jSplitPaneV = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT, my_panel_central, panelProprieteTache);		
        jSplitPaneV.setOneTouchExpandable(true);
		jSplitPaneV.setContinuousLayout(true);
		jSplitPaneV.setResizeWeight(0.3);
			
        
		this.setLayout(new BorderLayout());
		this.add(jSplitPaneV, BorderLayout.CENTER);
	}
	
	public KMADETaskModelPanel getTaskModelPanel() {
		return jgraphTaskPanel;
	}
	
	public KMADETaskPropertiesPanel getProprieteTache() {
		return panelProprieteTache;
	}
    
	public JScrollPane getScrollerPanelGraphTache()
	{
		return scrollerPanelGraphTache;
	}
	
	public void showOrHideRule() {
		boolean state = scrollerPanelGraphTache.getColumnHeader().isVisible();		
		scrollerPanelGraphTache.getColumnHeader().setVisible(!state);
		scrollerPanelGraphTache.getRowHeader().setVisible(!state);
	}    

    public void hideRule() {
        scrollerPanelGraphTache.getColumnHeader().setVisible(false);
        scrollerPanelGraphTache.getRowHeader().setVisible(false);        
    }
    
	public boolean isRuleVisible() {
		return scrollerPanelGraphTache.getColumnHeader().isVisible();
	}

	class UnitsListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				// turn it to metric
				rowView.setIsMetric(true);
				columnView.setIsMetric(true);
			} else {
				// turn it to inches
				rowView.setIsMetric(false);
				columnView.setIsMetric(false);
			}
		}
	}
	
    public void notifLocalisationModification() {
        // NMDATaskDescriptionPanel
        myTitledBorder.setTitle(KMADEConstant.TASK_MODEL_TITLE_NAME);
        
        jgraphTaskPanel.notifLocalisationModification(); // Aucune modification
        panelProprieteTache.notifLocalisationModification();
    }

	public JSplitPane getJSplitPaneV() {
		return jSplitPaneV;
	}

	public void setJSplitPaneV(JSplitPane splitPaneV) {
		jSplitPaneV = splitPaneV;
	}
}

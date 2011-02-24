package kmade.kmade.UI.toolutilities;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

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
public final class KMADEEnhancedSplitPane extends JSplitPane {

    private static final long serialVersionUID = -2273721332646585L;

    public static final String PROPERTYNAME_DIVIDER_BORDER_VISIBLE = "dividerBorderVisible";
    
    private boolean dividerBorderVisible;

   
    public KMADEEnhancedSplitPane() {
        this(JSplitPane.HORIZONTAL_SPLIT, false,
                new JButton(UIManager.getString("SplitPane.leftButtonText")),
                new JButton(UIManager.getString("SplitPane.rightButtonText")));
    }

    public KMADEEnhancedSplitPane(int newOrientation) {
        this(newOrientation, false);
    }

    public KMADEEnhancedSplitPane(int newOrientation, boolean newContinuousLayout) {
        this(newOrientation, newContinuousLayout, null, null);
    }

    public KMADEEnhancedSplitPane(int orientation,
                         Component leftComponent,
                         Component rightComponent) {
        this(orientation, false, leftComponent, rightComponent);
    }    
    
    public KMADEEnhancedSplitPane(int orientation,
                      boolean continuousLayout,
                      Component leftComponent,
                      Component rightComponent){
        super(orientation, continuousLayout, leftComponent, rightComponent);
        dividerBorderVisible = false;
    }
    
    public static KMADEEnhancedSplitPane createStrippedSplitPane(
             int orientation,
             Component leftComponent,
             Component rightComponent) {
        KMADEEnhancedSplitPane split = new KMADEEnhancedSplitPane(orientation, leftComponent, rightComponent);
        split.setBorder(BorderFactory.createEmptyBorder());
       
        split.setOneTouchExpandable(false);
        split.setDividerLocation(1d);
        return split;
    }
    
    public boolean isDividerBorderVisible() {
        return dividerBorderVisible;
    }
    
    public void setDividerBorderVisible(boolean newVisibility) {
        boolean oldVisibility = isDividerBorderVisible();
        if (oldVisibility == newVisibility)
            return;
        dividerBorderVisible = newVisibility;
        firePropertyChange(PROPERTYNAME_DIVIDER_BORDER_VISIBLE, 
                           oldVisibility, 
                           newVisibility);
    }
    
    public void updateUI() {
        super.updateUI();
        if (!isDividerBorderVisible())
            setEmptyDividerBorder();
    }
    
    private void setEmptyDividerBorder() {
        SplitPaneUI splitPaneUI = getUI();
        if (splitPaneUI instanceof BasicSplitPaneUI) {
            BasicSplitPaneUI basicUI = (BasicSplitPaneUI) splitPaneUI;
            basicUI.getDivider().setBorder(BorderFactory.createEmptyBorder());
        }
    }    
}
package kmade.kmade.UI.taskproperties.readworldobject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.toolutilities.InDevelopmentGlassPanel;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;

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
public class KMADEReadConcreteObjectDialog extends JDialog {
    private static final long serialVersionUID = -4495133696562074076L;

    private final KMADEReadConcreteObjectTable objAbstrait = new KMADEReadConcreteObjectTable();

    private JButton myOkButton;
    
    public KMADEReadConcreteObjectDialog() {
   		this.getContentPane().add(BorderLayout.CENTER, objAbstrait);
   		myOkButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
   		JPanel panelSouth = new JPanel();
   		panelSouth.add(myOkButton);
        this.setAlwaysOnTop(true);

   		myOkButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          	KMADEReadConcreteObjectDialog.this.setVisible(false);
        }
        });
        this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        
        InDevelopmentGlassPanel.plugDialogUnderDevelopmentGlassPanel(this);
        this.setVisible(false);        
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
    }
    
    public KMADEReadConcreteObjectTable getAbstractObjectTable() {
        return objAbstrait;
    }
}

package kmade.kmade.UI.toolutilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import kmade.kmade.KMADEConstant;

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
public class JPropertiesEditorDialog extends JDialog implements LanguageFactory {
    private static final long serialVersionUID = -8318323148935479783L;

    protected JPropertiesTable refPropertiesTable;

    protected DefaultPropertiesTableModel model;

    protected int row;

    protected JButton myOkButton;
    
    protected JPanel panelSouth;
    
    public JPropertiesEditorDialog() {
        this.setModal(true);
        myOkButton = new JButton(KMADEConstant.GO_BACK_MESSAGE);
        panelSouth = new JPanel();
        panelSouth.add(myOkButton);

        myOkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPropertiesEditorDialog.this.stopEditorDialog();
            }
        });

		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);	
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JPropertiesEditorDialog.this.stopEditorDialog();
            }
        });

        this.getContentPane().add(BorderLayout.SOUTH, panelSouth);
    }
    
    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int prow) {
        model = refModel;
        row = prow;
        this.setVisible(true);
    }

    protected void stopEditorDialog() { 
        JPropertiesEditorDialog.this.setVisible(false);
    }

    protected void cancelEditorDialog() { }
    
    public void notifLocalisationModification() {
        this.myOkButton.setText(KMADEConstant.GO_BACK_MESSAGE);
    }
}

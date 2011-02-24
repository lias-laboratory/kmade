package fr.upensma.lias.kmade.tool.view.taskproperties;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultPropertiesTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesEditorDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.PrePostIterExpressionAdaptator;


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
public class KMADEEditorTextDialog extends JPropertiesEditorDialog {
    private static final long serialVersionUID = -6348137983085333000L;
  
    public JEditorPane myTextArea;

    public KMADEEditorTextDialog() {
        super();
        this.setModal(false);
        myTextArea = new JEditorPane();
        this.setModal(true);
        this.getContentPane().add(BorderLayout.CENTER, new JScrollPane(myTextArea));

        this.setPreferredSize(new Dimension(400, 300));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
    }
    
    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int row) {
        myTextArea.setText((String)refModel.getValueAt(row,1));
        // Modification du titre.
        this.setTitle(KMADEConstant.EDITOR_TEXT_TITLE_NAME + " : " + refModel.getValueAt(row,0));
        PrePostIterExpressionAdaptator.disabledFrame();  
        super.showPropertiesEditor(refModel, row);
    }   

    protected void stopEditorDialog() {   
        PrePostIterExpressionAdaptator.enabledFrame();	
        model.setValueAt(myTextArea.getText(),row,1);
        super.stopEditorDialog();
    }
}

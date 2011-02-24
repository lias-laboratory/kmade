package fr.upensma.lias.kmade.tool.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;


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
public class KMADEStartDialog extends JDialog implements LanguageFactory {

    private static final long serialVersionUID = 7999269478676543553L;

    private JEditorPane editor_pane;
    
    protected JButton ok_button;
    
    /**
    * Constructeur de la classe InfoDebugAbout.
    * @param param_instance_dialog Instance du contr�leur de dialogue de la partie Builder.
    **/ 
    public KMADEStartDialog() {
        super(GraphicEditorAdaptator.getMainFrame(),true);
        Container content_pane = getContentPane();
        content_pane.setLayout(new BorderLayout());

        editor_pane = new JEditorPane();
        editor_pane.setEditable(false);
        editor_pane.setContentType("text/html");
        JScrollPane area_scroll_pane = new JScrollPane(editor_pane);
        area_scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        area_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        area_scroll_pane.setPreferredSize(new Dimension(400, 200));
                
        JPanel panel_button = new JPanel();
        panel_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ok_button = new JButton(KMADEConstant.GO_BACK_MESSAGE);
        ok_button.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                KMADEStartDialog.this.setVisible(false);
            }
        });
        panel_button.add(ok_button);

        content_pane.add(area_scroll_pane,BorderLayout.CENTER);     
        content_pane.add(panel_button,BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(700,500));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setModal(true);
        this.setVisible(false);
    }

    /**
    * M�thode qui affiche cette bo�te de dialogue et modifie son contenu.
    * @param title Titre de la bo�te de dialogue.
    * @param content Adresse de la page web contenant les informations. C'est une adresse qui pointe sur un fichier.
    **/ 
    public void showInfoAbout(String title, String content) {
        this.setTitle(title);
        try {
            editor_pane.setPage(this.getClass().getResource(content));
            this.pack();
            KMADEToolUtilities.setCenteredInScreen(this);
            this.setVisible(true);
        } catch (IOException e) { 
                        
        }
    }

    public void notifLocalisationModification() {
        ok_button.setText(KMADEConstant.VALID_MESSAGE);
    }
}

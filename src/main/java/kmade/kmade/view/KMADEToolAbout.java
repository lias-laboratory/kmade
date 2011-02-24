package kmade.kmade.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import kmade.KMADEToolConstant;
import kmade.kmade.KMADEConstant;
import kmade.kmade.view.toolutilities.KMADEToolUtilities;
import kmade.kmade.view.toolutilities.LanguageFactory;

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
public class KMADEToolAbout extends JDialog implements LanguageFactory {
	private static final long serialVersionUID = -108735426633480605L;

	// Instance de la classe ImageIcon qui stocke une image.
	private KMADEToolAnimationPanel panel_dialog;

    private JTextArea participants;
    
    private JTextArea librairies;
    
    private JTabbedPane myTabbedPane;
    
	public KMADEToolAbout(Frame owner) {
		super(owner,KMADEConstant.ABOUT_TITLE_NAME + " " + KMADEToolConstant.VERSION_VALUE, true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		Container content_pane = this.getContentPane();
		panel_dialog = new KMADEToolAnimationPanel();
		JPanel panel_global = new JPanel(new BorderLayout(10, 10));
		panel_global.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panelAnimation = new JPanel();
		panelAnimation.setBorder(BorderFactory.createRaisedBevelBorder());
		panelAnimation.setBackground(Color.WHITE);
		panelAnimation.add(BorderLayout.CENTER, panel_dialog);
		
		panel_global.add(BorderLayout.CENTER, panelAnimation);

		JPanel mySouthPanel = new JPanel();
		mySouthPanel.setLayout(new BorderLayout(10,10));
		
		JPanel panel_info = new JPanel();
		panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));

		panel_info.setBorder(BorderFactory.createRaisedBevelBorder());
		panel_info.add(KMADEToolUtilities.getLabelCenter(KMADEToolConstant.TOOL_NAME + " " + KMADEConstant.VERSION_MESSAGE + " " + KMADEToolConstant.VERSION_VALUE));
		panel_info.add(KMADEToolUtilities.getLabelCenter("2005 - 2010"));
		panel_info.add(KMADEToolUtilities.getLabelCenter("INRIA Rocquencourt - Projet MErLIn - LISI ENSMA"));
		panel_info.add(KMADEToolUtilities.getLabelCenter("France"));
		mySouthPanel.add(panel_info,BorderLayout.CENTER);		
		
		participants = new JTextArea();
		participants.setEditable(false);

		librairies = new JTextArea();
		librairies.setEditable(false);
        
        myTabbedPane = new JTabbedPane();
		myTabbedPane.addTab(KMADEConstant.KMADE_TEAM_MESSAGE,participants);
		myTabbedPane.addTab(KMADEConstant.JAVA_LIBRARIES_MESSAGE,librairies);
        this.notifLocalisationModification();
		mySouthPanel.add(myTabbedPane,BorderLayout.SOUTH);
		
		panel_global.add(BorderLayout.SOUTH, mySouthPanel);
		content_pane.add(panel_global);
		pack();
		KMADEToolUtilities.setCenteredInScreen(this);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeAboutDialog();
			}
		});
	}

	public void closeAboutDialog() {
		panel_dialog.stopAnimator();
		this.setVisible(false);
	}
	
	public void showAbout() {
		panel_dialog.go();
		this.setVisible(true);
	}

    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.ABOUT_TITLE_NAME + " " + KMADEToolConstant.VERSION_VALUE);
        participants.setText("");
        participants.append(KMADEConstant.PROJECT_LEADER_MESSAGE + "\n");
        participants.append(" - Dominique Scapin (dominique.scapin@inria.fr) \n\n");
        participants.append(KMADEConstant.DEVELOPMENT_QUERING_TOOL_MESSAGE + "\n");
        participants.append(" - Mickael Baron (mickael.baron@ensma.fr ; baron.mickael@gmail.com)\n\n");
        participants.append(KMADEConstant.KMAD_SPECIFY_MESSAGE + "\n");
        participants.append(" - Vincent Lucquiaud \n\n");
        participants.append(KMADEConstant.KMAD_ADAPTATOR_MESSAGE + "\n");
        participants.append(" - Delphine Autard");
        librairies.setText("");
        librairies.append(KMADEConstant.JAVA_GRAPHIC_LIBRARIES_MESSAGE + "\n");
        librairies.append(" - JGraph (http://www.jgraph.com)\n");
        librairies.append(" - JGoodies (http://www.jgoodies.com)\n");
        librairies.append(" - L2FProd (http://www.l2fprod.com)\n");
        librairies.append(" - QuickTime for Java (http://developer.apple.com/quicktime/qtjava)\n");
        librairies.append(" - Timingframework (http://timingframework.dev.java.net)\n");
        librairies.append(" - Velocity (http://jakarta.apache.org/velocity)\n");
        librairies.append(" - Swingstates (http://swingstates.sourceforge.net/)\n");
        myTabbedPane.setTitleAt(0,KMADEConstant.KMADE_TEAM_MESSAGE);
        myTabbedPane.setTitleAt(1,KMADEConstant.JAVA_LIBRARIES_MESSAGE);
    }
}
package kmade.kmade.UI.toolutilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;

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
public class KMADEHistoryMessagePanel extends JPanel {

    private static final long serialVersionUID = -6863003670072426978L;

    private static final ImageIcon CLEAR_HISTORY = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.CLEAR_HISTORY_IMAGE));
    
    private static final ImageIcon SAVE_HISTORY = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.SAVE_HISTORY_IMAGE));    
    
    private JTextArea refTextArea;
    
    private JTextAreaOutputStream outStream;
    
    private JButton myClearButton;
    
    private JButton mySaveButton;
    
    public KMADEHistoryMessagePanel(String borderMessage) {
    	this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        this.setLayout(new BorderLayout(5,5));
        refTextArea = new JTextArea();
        JScrollPane myScrollPane = new JScrollPane(refTextArea);
        if (!borderMessage.equals("")) {
        	this.setBorder(BorderFactory.createTitledBorder(borderMessage));
        }
        outStream = new JTextAreaOutputStream(refTextArea);
        JPanel panelTool = new JPanel();
        panelTool.setLayout(new BoxLayout(panelTool, BoxLayout.Y_AXIS));
        myClearButton = new JButton(CLEAR_HISTORY);
        mySaveButton = new JButton(SAVE_HISTORY);
        mySaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	KMADEHistoryMessagePanel.this.saveHistoryMessage();
            }
        });
        mySaveButton.setToolTipText(KMADEConstant.SAVE_HISTORIC_ACTION_MESSAGE);
        panelTool.add(myClearButton);
        panelTool.add(Box.createRigidArea(new Dimension(5, 5)));
        panelTool.add(mySaveButton);
        this.add(BorderLayout.WEST,panelTool);
        this.add(BorderLayout.CENTER,myScrollPane);
        
        myClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refTextArea.setText("");
            }
        });
        myClearButton.setToolTipText(KMADEConstant.CLEAR_HISTORIC_ACTION_MESSAGE);
    }
    
    public void setBorderName(String message) {
        this.setBorder(BorderFactory.createTitledBorder(message));
    }
    
    public void setOutputMessage() {
        System.setOut(new PrintStream(outStream));   
    }
    
    public void setErrputMessage() {
    	System.setErr(new PrintStream(outStream));
    }
    
    private void saveHistoryMessage() {
		File nomFichier = KMADEFileChooser.saveTxtFile(GraphicEditorAdaptator.getMainFrame().getFindReplaceDialog());
		if (nomFichier == null) {
			return;
		}
		
		if (nomFichier != null) {
            try {
            	FileWriter fw = new FileWriter(nomFichier);
            	fw.write(refTextArea.getText());
    			fw.close();
    			System.out.println(KMADEConstant.WRITE_TXT_FILE_OK + nomFichier.getName());
            } catch (IOException e) {
            	System.out.println(KMADEConstant.WRITE_TXT_FILE_ERROR);
            }
		}
    }
}

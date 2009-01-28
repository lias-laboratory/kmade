package kmade.kmade.UI.print;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.KMADEToolToolBar;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.UI.toolutilities.LanguageFactory;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.PrintAdaptator;

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
public class KMADEPrintingDialog extends JDialog implements LanguageFactory {
	private static final long serialVersionUID = 4335454175244701286L;
   
    private static final ImageIcon LANDSCAPE_IMAGE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.LANDSCAPE_IMAGE));
    
    private static final ImageIcon PORTRAIT_IMAGE = new ImageIcon(GraphicEditorAdaptator.class.getResource(KMADEConstant.PORTRAIT_IMAGE));
    
	private String[] myTable = {"10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%", "110%", "120%", "130%","Adapter au modèle", "Personnaliser..."};
    
    private AbstractAction printAction;
    
    private AbstractAction pageLayoutAction;
    
    private AbstractAction pdfAction;
    
    private AbstractAction epsAction;
    
    private AbstractAction portraitAction;
    
    private AbstractAction landscapeAction;
    
    private AbstractAction zoomInAction;
    
    private AbstractAction zoomOutAction;
    
    private AbstractAction zoomDefaultAction;
    
    private JLabel myLabel;

    private JComboBox myComboBox;
    
    private JTabbedPane category;
    
    private KMADEPrintingTaskModelPanel refTaskModel;    
    
    private KMADEPrintingUserCardsPanel refUserCards;
    
	private PageFormat pf;
    
	private static PrinterJob printJob = PrinterJob.getPrinterJob();
	
    public KMADEPrintingTaskModelPanel getTaskModel() {
    	return refTaskModel;
    }
    
    public KMADEPrintingUserCardsPanel getUserCards() {
    	return refUserCards;
    }
	
    public PageFormat getPageFormat() {
    	return pf;
    }
    
    public static PrinterJob getPrinterJob() {
    	return printJob;
    }
    
	public void setPageLayout() {
		pf = printJob.pageDialog(pf);
	}
	
	public void setToPortrait() {
		pf.setOrientation(PageFormat.PORTRAIT);
	}
	
	public void setToLandscape() {
		pf.setOrientation(PageFormat.LANDSCAPE);
	}
    
	public KMADEPrintingDialog(Frame owner) {
		super(owner,KMADEConstant.PRINT_PREVIEW_WINDOW_TITLE_NAME,true);
		
		pf = printJob.defaultPage();
		
		JToolBar myToolBar = new JToolBar();
		myToolBar.setFloatable(false);
	
		// Impression sur imprimante physique
        printAction = new AbstractAction("", KMADEToolToolBar.PRINT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				PrintAdaptator.printAction();
			}
		};
        printAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_ACTION_MESSAGE);
		myToolBar.add(printAction);
		
		// Mise en page
		pageLayoutAction = new AbstractAction("", KMADEToolToolBar.PRINT_LAYOUT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				PrintAdaptator.pageLayoutAction();
			}
		};
        pageLayoutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.LAYOUT_PRINT_ACTION_MESSAGE);
		myToolBar.add(pageLayoutAction);
		myToolBar.addSeparator();
		
		// Impression en PDF (interne)
        pdfAction = new AbstractAction("", KMADEToolToolBar.PRINT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {				
			}
		};
        pdfAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PDF_EXPORT_ACTION_MESSAGE);
        pdfAction.setEnabled(false);
		myToolBar.add(pdfAction);

		// Impression en EPS (interne)
		epsAction = new AbstractAction("", KMADEToolToolBar.PRINT) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
			}
		};
        epsAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.EPS_EXPORT_ACTION_MESSAGE);
        epsAction.setEnabled(false);
		myToolBar.add(epsAction);
		myToolBar.addSeparator();

		// Changement en portrait
		portraitAction = new AbstractAction("", PORTRAIT_IMAGE) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				PrintAdaptator.setToPortrait();
			}
		};
        portraitAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PORTRAIT_ACTION_MESSAGE);
		myToolBar.add(portraitAction);

		// Changement en paysage
		landscapeAction = new AbstractAction("", LANDSCAPE_IMAGE) {
			private static final long serialVersionUID = 838662422873173261L;
			public void actionPerformed(ActionEvent e) {
				PrintAdaptator.setToLandscape();
			}
		};
        landscapeAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.LANDSCAPE_ACTION_MESSAGE);
		myToolBar.add(landscapeAction);
		myToolBar.addSeparator();

		// Zoom de l'aperçu.
		myLabel = new JLabel(KMADEConstant.SCALE_ACTION_MESSAGE);
		myToolBar.add(myLabel);
		myComboBox = new JComboBox();
		myComboBox.setToolTipText(KMADEConstant.PREVIEW_ZOOM_ACTION_MESSAGE);
		for (int i = 0; i < myTable.length; i++) {
			myComboBox.addItem(myTable[i]);
		}
		myComboBox.setSelectedIndex(9);
		
		myToolBar.add(myComboBox);		
		myComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refTaskModel.setScaleView((myComboBox.getSelectedIndex() + 1) / 10.0);
			}
		});
        myToolBar.addSeparator();
	
		JPanel myPanelToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		myPanelToolBar.add(myToolBar);
		this.getContentPane().add(BorderLayout.NORTH, myPanelToolBar);
 			
		category = new JTabbedPane();
		refUserCards = new KMADEPrintingUserCardsPanel(this);
		refTaskModel = new KMADEPrintingTaskModelPanel(this);
		category.add(KMADEConstant.TREE_TASK_PRINT_TITLE_MESSAGE, refTaskModel);
		category.add(KMADEConstant.USER_CARDS_PRINT_TITLE_MESSAGE, refUserCards);
		category.add(KMADEConstant.OBJECTS_PRINT_TITLE_MESSAGE, new JPanel());
		category.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				PrintAdaptator.setSheet(category.getSelectedIndex());
			}			
		});
		
		this.getContentPane().add(BorderLayout.CENTER, category);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                GraphicEditorAdaptator.closePrintDialog();
            }
        });
        
        this.setPreferredSize(new Dimension(800,700));
		this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
		this.setVisible(false);
	}       

	public void showTaskModel() {
		category.setSelectedIndex(0);
	}
	
    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.PRINT_PREVIEW_WINDOW_TITLE_NAME);
        printAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PRINT_ACTION_MESSAGE);
        pageLayoutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.LAYOUT_PRINT_ACTION_MESSAGE);
        pdfAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PDF_EXPORT_ACTION_MESSAGE);
        epsAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.EPS_EXPORT_ACTION_MESSAGE);
        portraitAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.PORTRAIT_ACTION_MESSAGE);
        landscapeAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.LANDSCAPE_ACTION_MESSAGE);
        zoomInAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_IN_ACTION_MESSAGE);
        zoomOutAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_OUT_ACTION_MESSAGE);
        zoomDefaultAction.putValue(AbstractAction.SHORT_DESCRIPTION, KMADEConstant.ZOOM_DEFAULT_ACTION_MESSAGE);
        myLabel.setText(KMADEConstant.SCALE_ACTION_MESSAGE);
    }    
}

package kmade.kmade.view.toolutilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
public class InDevelopmentGlassPanel extends JComponent {
    private static final long serialVersionUID = -44200753178867628L;

    private String myString;
          
    private RenderingHints hints = null;    
        
    public InDevelopmentGlassPanel(String p, Color color) {
        this.myString = p;
        this.hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        this.setOpaque(false);        
    }
    
    public InDevelopmentGlassPanel(Color color) {
    	this("",color);
    }
    
    public static void plugWindowInDisabledGlassPanel(JFrame myFrame,String myString) {
        //((InDevelopmentGlassPanel)myFrame.getGlassPane()).setText(myString);
        myFrame.getGlassPane().setVisible(true);
        myFrame.setEnabled(false);
    }
    
    public static void plugWindowInDisabled(JFrame myFrame) {
        myFrame.setEnabled(false);
    }

    public static void unPlugWindowInDisabled(JFrame myFrame) {
        myFrame.setEnabled(true);
    }

    public static void plugDialogInDisabled(JDialog myDialog) {
        myDialog.setEnabled(false);
    }
    
    public static void unPlugDialogInDisabled(JDialog myFrame) {
        myFrame.setEnabled(true);
    }
    
    public static void plugWindowInDisabledGlassPanel(JFrame myFrame) {
        myFrame.getGlassPane().setVisible(true);
        myFrame.setEnabled(false);
    }
        
    public static void unPlugWindowInDisabledGlassPanel(JFrame myFrame) {
        myFrame.getGlassPane().setVisible(false);
        //((InDevelopmentGlassPanel)myFrame.getGlassPane()).setText("");
        myFrame.setEnabled(true);
    }

    public static void plugDialogInDisabledGlassPanel(JDialog myDialog) {
        myDialog.getGlassPane().setVisible(true);
        myDialog.setEnabled(false);
    }
    
    public static void unPlugDialogInDisabledGlassPanel(JDialog myFrame) {
        myFrame.getGlassPane().setVisible(false);
        myFrame.setEnabled(true);
    }
   
    public static void plugDialogUnderDevelopmentGlassPanel(JDialog myDialog) {
        InDevelopmentGlassPanel myGlassPanel = new InDevelopmentGlassPanel("Under Development ...", Color.GRAY);
        myDialog.setGlassPane(myGlassPanel);
        myGlassPanel.setOpaque(false);
        myGlassPanel.setVisible(true);
    }
    
    public void setText(String pString) {
        this.myString = pString;
    }
    
    protected void paintComponent(Graphics g) { 
        Graphics2D g2d = (Graphics2D)g;
               
        g2d.setRenderingHints(hints);
        g2d.setColor(new Color(128,128,128,(int) (100 * 0.70f)));        
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (!myString.equals("")) {
	        int longueur = this.getBounds().width;
	        FontMetrics fm = this.getFontMetrics(g2d.getFont());
	        int heighText = fm.getHeight();
	        int widthText =  SwingUtilities.computeStringWidth(fm, myString) + 30;  
	        double scaleAlpha = (double)longueur / (double)widthText;
	        if (scaleAlpha == 0.0) {
	            scaleAlpha = 1.0;
	        }
	
	        g2d.scale(scaleAlpha,scaleAlpha);
	        g2d.setColor(Color.GREEN);        
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.drawString(myString,15, (int)(((this.getBounds().height / 2) + ((heighText / 2) * scaleAlpha)) / (scaleAlpha)));
        }
    }
}

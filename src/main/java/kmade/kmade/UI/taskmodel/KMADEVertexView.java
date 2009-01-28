package kmade.kmade.UI.taskmodel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.tache.Executant;

import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

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
public class KMADEVertexView extends VertexView {
	private static final long serialVersionUID = 3183893455933034534L;

    public static final ImageIcon ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.ABSTRACT_TASK_IMAGE));
    
    public static final ImageIcon FEEDBACK_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.FEEDBACK_TASK_IMAGE));
    
    public static final ImageIcon USER_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.USER_TASK_IMAGE));
    
    public static final ImageIcon INTERACTIF_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.INTERACTIF_TASK_IMAGE));
    
    public static final ImageIcon UNKNOWN_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.UNKNOWN_TASK_IMAGE));

    public static final ImageIcon OPTIONAL_ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.OPTIONAL_ABSTRACT_TASK_IMAGE));
    
    public static final ImageIcon OPTIONAL_USER_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.OPTIONAL_USER_TASK_IMAGE));
    
    public static final ImageIcon OPTIONAL_INTERACTIF_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.OPTIONAL_INTERACTIF_TASK_IMAGE));
    
    public static final ImageIcon OPTIONAL_UNKNOWN_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.OPTIONAL_UNKNOWN_TASK_IMAGE));

    public static final ImageIcon INTERRUPTIBLE_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.INTERRUPTIBLE_TASK_IMAGE));
    
    public static final ImageIcon EXPAND_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.ZOOM_OUT_IMAGE));
    
    public static final ImageIcon COLLAPSE_TASK_IMAGE_ICON = new ImageIcon(KMADEVertexView.class.getResource(KMADEConstant.ZOOM_IN_IMAGE));
    
    protected static final int IMAGE_32_WIDTH = 32;

    protected static final int IMAGE_32_HEIGH = 32;

    protected static final int IMAGE_16_WIDTH = 16;

    protected static final int IMAGE_16_HEIGH = 16;

    protected static final int H_GAP = 2;

    protected static final int V_GAP = 2;

    protected static final int BEGIN_V_GAP = 10;

    protected static final int H_FONT_GAP = 10;
    
    protected static final int NUM_LIMIT = 15;
    
    protected static final int NAME_LIMIT = 45;
    
	public static final KMADEGraphCellEditor editor = new KMADEGraphCellEditor();
	   
    protected JGraph myGraph;
    
	public KMADEVertexView(Object cell, JGraph myGraph) {
		super(cell);
        this.myGraph = myGraph;
	}
	
	public void setCell(Object cell) {
		this.cell = cell;
	}

	public GraphCellEditor getEditor() {
		return editor;
	}
    
    public CellViewRenderer getRenderer() {   
        return new MyVertexRenderer();
    }
    
    public Rectangle2D getBounds() {
        Rectangle2D temp = super.getBounds();
        KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell)this.getCell();
               
        JPanel myPanel = new JPanel();              

        FontMetrics fname = myPanel.getFontMetrics(KMADEConstant.TASK_NAME_FONT);        
        String name = myGraphCell.getName();
        if (name.length() > NAME_LIMIT) {
            name = name.substring(0,NAME_LIMIT) + "..."; 
        }
        
        FontMetrics fnum = myPanel.getFontMetrics(KMADEConstant.TASK_NUM_FONT);        
        String num = "N:" + myGraphCell.getNumero();
        if (num.length() > NUM_LIMIT) {
            num = num.substring(0, NUM_LIMIT) + "...";
        }
        String iteration = getIterationName(myGraphCell);
        int maxNumOrIter = Math.max(fnum.stringWidth(num), fnum.stringWidth(iteration));
        
        int width = 0;
        if (fname.stringWidth(name) < (16 + 32 + 4 + 4 * Math.max(num.length(),iteration.length()) + maxNumOrIter + 10)) {
                width = 16 + 32 + 4 + 4 * Math.max(num.length(),iteration.length()) + maxNumOrIter + 10;
        } else {
                width = fname.stringWidth(name) + 10;
        }
        int hauteur = 10 + 32 + fname.getHeight() + 10 + 10 + 15;
        return new Rectangle2D.Double(temp.getX(),temp.getY(), width,hauteur);
    }
    
    protected String getIterationName(KMADEDefaultGraphCell myGraphCell) {
        String iteration = "I:";
        if (myGraphCell.getTask().getIteExpression().isErrorInExpression()) {
            iteration += "Erreur";
        } else {
            if (myGraphCell.getTask().getIteExpression().isVariableExpressionNode()) {
                iteration += myGraphCell.getTask().getIteExpression().getNodeExpression().getNodeValue();
            } else {
                iteration += "prédicat";
            }
        }  
        return iteration;
    }
    
    public class MyVertexRenderer extends VertexRenderer {
 
        private static final long serialVersionUID = 5060836696940302890L;
        
        protected void paintTask(Graphics gr) {
            KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell)KMADEVertexView.this.getCell();
            Graphics2D f = (Graphics2D) gr;
            
            paintLabelCaract(f, myGraphCell);
            
            if (myGraphCell.isFlagSearch()) {
                double x =1, y = 1, w = this.getBounds().getWidth() - 3, h = this.getBounds().getHeight() - 3;
                Ellipse2D ellips = new Ellipse2D.Double(x, y, w, h);
                f.setColor(Color.WHITE);
                GradientPaint gp = new GradientPaint(50, 50, Color.WHITE, 80, 80,new Color(192,255,192),true);
                f.setPaint(gp);
                f.fill(ellips);
                f.setColor(Color.RED);
                f.draw(ellips);
                f.clip(ellips);
            }           
            
            f.setColor(Color.BLACK);
            int width = (int) this.getBounds().getWidth() / 2;
            
            // L'image
            Executant e = myGraphCell.getExecutant();    
            ImageIcon myImage = KMADEVertexView.UNKNOWN_TASK_IMAGE_ICON;
            if (e == Executant.ABS) {
                if (myGraphCell.isFacultatif()) 
                    myImage = KMADEVertexView.OPTIONAL_ABSTRACT_TASK_IMAGE_ICON;                    
                else
                    myImage = KMADEVertexView.ABSTRACT_TASK_IMAGE_ICON;
            } else if (e == Executant.INT) {
                if (myGraphCell.isFacultatif())
                    myImage = KMADEVertexView.OPTIONAL_INTERACTIF_TASK_IMAGE_ICON;
                else 
                    myImage = KMADEVertexView.INTERACTIF_TASK_IMAGE_ICON;
            } else if (e == Executant.SYS) {
                    myImage = KMADEVertexView.FEEDBACK_TASK_IMAGE_ICON;
            } else if (e == Executant.USER) {
                if (myGraphCell.isFacultatif())
                    myImage = KMADEVertexView.OPTIONAL_USER_TASK_IMAGE_ICON;
                else
                    myImage = KMADEVertexView.USER_TASK_IMAGE_ICON;
            } else if (e == Executant.INCONNU) {
                if (myGraphCell.isFacultatif())
                    myImage = KMADEVertexView.OPTIONAL_UNKNOWN_TASK_IMAGE_ICON;
                else
                    myImage = KMADEVertexView.UNKNOWN_TASK_IMAGE_ICON;
            }           
                       
            if (!preview) {
                myImage.paintIcon(this,gr,width - IMAGE_32_WIDTH / 2, BEGIN_V_GAP);
            } 

            int currentHeighImage = IMAGE_32_HEIGH + BEGIN_V_GAP;

            // Le numéro
            String numero = "N:" + myGraphCell.getNumero();
            if (numero.length() > NUM_LIMIT) {
                numero = numero.substring(0, NUM_LIMIT) + "...";
            }
            f.setFont(KMADEConstant.TASK_NUM_FONT);
            FontMetrics fm = f.getFontMetrics();
            Rectangle2D rnum = fm.getStringBounds(numero, f);

            // L'itération
            String iteration = getIterationName(myGraphCell);
                      
            Rectangle2D riter = fm.getStringBounds(iteration, f);

            this.paintStateCaract(f, width + IMAGE_32_WIDTH / 2 + H_GAP, numero, iteration, rnum.getHeight(), riter.getHeight());
            
            // Le nom
            String name = myGraphCell.getName();
            if (name.length() > NAME_LIMIT) {
                name = name.substring(0, NAME_LIMIT) + "...";
            }
            f.setFont(KMADEConstant.TASK_NAME_FONT);
            f.setColor(KMADEConstant.TASK_NAME_COLOR); 
            fm = f.getFontMetrics();
            Rectangle2D rn = fm.getStringBounds(name, f);
            f.drawString(name, (int) (width - (rn.getWidth() / 2)), currentHeighImage + (int) (rn.getHeight()));
            currentHeighImage += (int) (rn.getHeight()) + H_FONT_GAP;
                       
            // L'opérateur
            String operateur = "";
            if (myGraphCell.isMotherAndSonPortsNoEdged()) {
                operateur = ExpressConstant.OPERATOR_NAME;
            } else {
                operateur = myGraphCell.getDecomposition();
            }
            
            f.setFont(KMADEConstant.TASK_OPERATOR_FONT);
            f.setColor(KMADEConstant.TASK_OPERATOR_COLOR); 
            fm = f.getFontMetrics();
            Rectangle2D ro = fm.getStringBounds(operateur, f);
            f.drawString(operateur, (int) (width - (ro.getWidth() / 2)), currentHeighImage + (int) (ro.getHeight()));

            // Le rectange autour de l'opérateur
            f.drawRect((int) (width - (ro.getWidth() / 2)) - H_GAP, currentHeighImage - H_GAP, (int) ro.getWidth() + 2 * H_GAP, (int) ro.getHeight() + 3 * V_GAP);
            
            // La tache est sélectionnée
            if (myGraph != GraphicEditorAdaptator.getMainFrame().getPreviewWindow().getOverviewGraph()) {
                if (selected) {
                    f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
                    f.setStroke(new BasicStroke(2.0f));
                    f.drawRect(width - (IMAGE_32_WIDTH / 2) - 1, BEGIN_V_GAP - 1, IMAGE_32_WIDTH + 1, IMAGE_32_HEIGH + 1);           
                }
            }
            
            if (myGraphCell.isInterruptible()) {
                f.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                if (!preview) {
                    INTERRUPTIBLE_TASK_IMAGE_ICON.paintIcon(this,gr,width / 2, 0);
                }
            }
        }

        protected void paintLabelCaract(Graphics2D f, KMADEDefaultGraphCell myCell) {
            if (KMADEMainFrame.getProjectPanel().getTaskDescriptionPanel().getTaskModelToolBar().isLabelColorSelected()) {
                if (myCell.getTask().getLabel() != null) {
                    if (myCell.getTask().getLabel().isColorVisible()) {
                        Color taskColor = myCell.getTask().getLabel().getColor();
                        Color old = f.getColor();
                        Color colorWithTransparency = new Color(taskColor.getRed(), taskColor.getGreen(),taskColor.getBlue(),(int)(100 * 0.70f));
                        f.setColor(colorWithTransparency);
                        f.fillRect(0,0,this.getBounds().width,this.getBounds().height);
                        f.setColor(old);
                    }
                }                
            }
        }
        
        protected void paintStateCaract(Graphics2D f, int widthString, String numString, String iterString, double num, double iter) {
            int V_NUM_ITE_GAP = (int)(IMAGE_32_HEIGH - num - iter) / 3;
            f.drawString(numString, widthString, BEGIN_V_GAP + V_NUM_ITE_GAP + (int)num);
            f.drawString(iterString, widthString, BEGIN_V_GAP + V_NUM_ITE_GAP + (int)num + V_NUM_ITE_GAP + (int)iter);            

        }
        
        public void paint(Graphics gr) {
            KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell)KMADEVertexView.this.getCell();
            if (!myGraphCell.isExpanded()) {
                return;
            }
            
            if (KMADEMainFrame.getProjectPanel().getTaskDescriptionPanel().getTaskModelToolBar().isLabelSelected()) {
                if (myGraphCell.getTask().getLabel() != null) {
                    if (!myGraphCell.getTask().getLabel().isVisible()) {
                        return;
                    }
                }
            }
            
            // Agrandir ou réduire     
            if (!myGraphCell.isMotherPortNoEdged() && (myGraph == GraphicEditorAdaptator.getTaskModelPanel().getJGraph())) {
                ImageIcon myImageIcon = null;
                if (myGraphCell.isSonExpanded()) {
                    myImageIcon = KMADEVertexView.EXPAND_TASK_IMAGE_ICON;                
                } else {
                    myImageIcon = KMADEVertexView.COLLAPSE_TASK_IMAGE_ICON;
                }
                if (!preview) {
                    myImageIcon.paintIcon(this,gr,((int) this.getBounds().getWidth() / 2) - IMAGE_32_WIDTH / 2 - IMAGE_16_WIDTH - V_GAP, BEGIN_V_GAP + IMAGE_16_HEIGH / 2);
                }
            }
            
            this.paintTask(gr);
            Graphics2D f = (Graphics2D) gr;
            if (myGraph != GraphicEditorAdaptator.getMainFrame().getPreviewWindow().getOverviewGraph()) {
                if (selected) {
                    f.setStroke(GraphConstants.SELECTION_STROKE);
                    f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
                    Dimension d = getSize();
                    f.drawRect(0, 0, d.width - 1, d.height - 1);
                }
            }
        }
    }
    
    public CellHandle getHandle(GraphContext context) {
        return new MyCellHandle();
    }
    
    class MyCellHandle implements CellHandle {        
        public void paint(Graphics g) { }

        public void overlay(Graphics g) { }

        public void mouseMoved(MouseEvent event) { }

        public void mousePressed(MouseEvent event) {   
            int mx = (int)(event.getX() / myGraph.getScale());
            int my = (int)(event.getY() / myGraph.getScale());
            Rectangle2D rec = getBounds();
            int tolerance = 2;
            int rx = (int)(rec.getX()) + ((int)rec.getWidth() / 2) - IMAGE_32_WIDTH / 2 - IMAGE_16_WIDTH - V_GAP - tolerance;
            int ry = (int)rec.getY() + BEGIN_V_GAP + IMAGE_16_HEIGH / 2 - tolerance;
            int rw = rx + 16 + 2 * tolerance;
            int rh = ry + 16 + 2 * tolerance;
            if (mx >= rx && mx <= rw && my >= ry && my <= rh) {
                KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell)getCell();
                if (!myGraphCell.isMotherPortNoEdged()) {
                    myGraphCell.setSonExpanded(!myGraphCell.isSonExpanded());
                    myGraph.repaint();
                }
            }
        }

        public void mouseDragged(MouseEvent event) {
            
        }

        public void mouseReleased(MouseEvent event) {
            
        }        
    }
}

package kmade.kmade.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import kmade.kmade.KMADEConstant;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.view.taskmodel.KMADEDefaultGraphCell;
import kmade.kmade.view.taskmodel.KMADEEdgeView;
import kmade.kmade.view.taskmodel.KMADEPortView;
import kmade.kmade.view.taskmodel.KMADEVertexView;
import kmade.kmade.view.toolutilities.InDevelopmentGlassPanel;
import kmade.kmade.view.toolutilities.KMADEToolUtilities;
import kmade.kmade.view.toolutilities.LanguageFactory;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphModel;

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
public class KMADEClipBoardDialog extends JDialog implements LanguageFactory {
    
    private static final long serialVersionUID = -4814746185677765580L;
    
    private ComponentListener componentListener;
    
    private JGraph myGraph = new JGraph();
    
    
    
    
    private JScrollPane myScrollPane;
    
    private DefaultGraphModel refModel;
        
    public KMADEClipBoardDialog(JFrame owner) {
        super(owner,KMADEConstant.CLIPBOARD_TITLE_NAME);
        componentListener = new ComponentAdapter() {
            public void componentResized(ComponentEvent componentevent) {
            	
               updateScale();               
            }
        };
        refModel = new DefaultGraphModel();
        myGraph.setModel(refModel);
        myGraph.setOpaque(true);
        myGraph.setScale(1);
        myGraph.setEnabled(false);
        myGraph.setFocusable(false);
        myGraph.setGridEnabled(false); 
        
        myGraph.getGraphLayoutCache().setFactory(new DefaultCellViewFactory() {

			private static final long serialVersionUID = -733364299084085305L;

			public CellView createView(GraphModel arg0, Object arg1) {                
                if (arg1 instanceof KMADEDefaultGraphCell) {
                    return new KMADEVertexView(arg1,myGraph);
                } else if (arg1 instanceof DefaultPort) {
                    return new KMADEPortView(arg1);
                } else if (arg1 instanceof DefaultEdge)
                    return new KMADEEdgeView(arg1,myGraph);
                {
                    return super.createView(arg0, arg1);
                }
            }
        });

        myScrollPane = new JScrollPane(myGraph);
        this.getContentPane().add(BorderLayout.CENTER, myScrollPane);
        this.addComponentListener(componentListener);
        this.setPreferredSize(new Dimension(200,200));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
        this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
        this.setGlassPane(new InDevelopmentGlassPanel("", Color.RED));
        this.setVisible(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().setSelectedOverviewClipboardToggleButton(false);
            }            
        });
    }

    public void emptyRootFromModel() {
        Object[] toto = DefaultGraphModel.getAll(refModel);
        for (int i = 0; i < toto.length; i++) {
            refModel.remove(toto);
        }
    }
    
    protected void updateScale() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JGraph jgraph = myGraph;
                if (jgraph != null) {
                    Dimension dimension = KMADEClipBoardDialog.this.getPreferredSize();
                    Dimension dimension1 = KMADEClipBoardDialog.this.getBounds().getSize();
                    dimension.width = Math.max(dimension.width,
                            dimension1.width);
                    dimension1.height = Math.max(dimension.height,
                            dimension1.height);
                    double d = 1;
                    dimension.setSize((double) (dimension.width * 1) / d,
                            (double) (dimension.height * 1) / d);
                    Dimension dimension2 = myScrollPane.getViewport()
                            .getSize();
                    double d1 = dimension2.getWidth() / dimension.getWidth();
                    double d2 = dimension2.getHeight() / dimension.getHeight();
                    d = Math.min(Math.min(d1, d2), 0.3d);
                    myGraph.setScale(d);
                    repaint();
                }
            }

        });
    }
    
    public JGraph getMyGraph() {
        return myGraph;
    }
    
    /**
     * Recherche une t�che dans l'arbre de t�che portant l'oid OID.
     * 
     * @param oid
     * @return
     */
    public KMADEDefaultGraphCell getTask(String oid) {
        Object[] allCell = DefaultGraphModel.getAll(myGraph.getModel());

        for (int i = 0; i <= allCell.length; i++) {
            if (allCell[i] instanceof KMADEDefaultGraphCell) {
                KMADEDefaultGraphCell myCurrentCell = (KMADEDefaultGraphCell) allCell[i];
                if (oid.equalsIgnoreCase(myCurrentCell.getOid()))
                    return myCurrentCell;
            } else {
                // Ce n'est pas une cellule.
            }
        }
        return null;
    }
    
    public void removeAllEntities() {
        Object[] toto = DefaultGraphModel.getAll(myGraph.getModel());
        for (int i = 0; i < toto.length; i++) {
            myGraph.getModel().remove(toto);
        }
    }  
    
    public void notifLocalisationModification() {
        this.setTitle(KMADEConstant.CLIPBOARD_TITLE_NAME);
    }
}

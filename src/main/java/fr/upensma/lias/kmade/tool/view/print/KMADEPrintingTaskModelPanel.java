/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
 * 
 * KMADe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * KMADe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
 **********************************************************************************/
package fr.upensma.lias.kmade.tool.view.print;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.VertexView;

import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelToolBar;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEPrintingTaskModelPanel extends JPanel {

    private static final long serialVersionUID = 2792808372683769051L;

    private JGraphExtendedView myGraph = new JGraphExtendedView();

    private Rectangle jgraphSize;

    private CardLayout refCardLayout;

    private JPanel myPanel;

    private boolean inPreviewMode = false;

    private JPanel panelPreview;

    private BufferedImage buffer_image;

    private AbstractAction zoomInAction;

    private AbstractAction zoomOutAction;

    private AbstractAction zoomDefaultAction;

    private boolean printMode = false;

    private static final Color colorPage = Color.CYAN;

    private static final Color colorLandmark = Color.ORANGE;

    private static final String namePage = "p";

    private Image up = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.UP_ARROW_IMAGE)).getImage();

    private Image down = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.DOWN_ARROW_IMAGE)).getImage();

    private Image left = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.LEFT_ARROW_IMAGE)).getImage();

    private Image right = new ImageIcon(
	    GraphicEditorAdaptator.class
		    .getResource(KMADEConstant.RIGHT_ARROW_IMAGE)).getImage();

    private double scaleGraph = 1;

    private double scaleView = 1;

    private KMADEPrintingDialog refDialog;

    public KMADEPrintingTaskModelPanel(KMADEPrintingDialog pdialog) {
	refDialog = pdialog;
	myGraph.setOpaque(true);
	myGraph.setScale(1);
	this.scaleGraph = 1.0;
	myGraph.setEnabled(false);
	myGraph.setFocusable(false);
	myGraph.setGridEnabled(false);

	JToolBar myToolBar = new JToolBar();
	myToolBar.setFloatable(false);

	// Extrait la taille réelle du graphe.
	jgraphSize = KMADEPrintingTaskModelPanel.getRealJGraphSize(myGraph);

	// Zoom In
	zoomInAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOMIN_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		KMADEPrintingTaskModelPanel.this
			.setTaskModelZoom(scaleGraph * 1.5d);
	    }
	};
	zoomInAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_IN_ACTION_MESSAGE);
	myToolBar.add(zoomInAction);

	// Zoom Out
	zoomOutAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOMOUT_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		KMADEPrintingTaskModelPanel.this
			.setTaskModelZoom(scaleGraph / 1.5d);
	    }
	};
	zoomOutAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_OUT_ACTION_MESSAGE);
	myToolBar.add(zoomOutAction);

	// Zoom Default
	zoomDefaultAction = new AbstractAction("",
		KMADETaskModelToolBar.ZOOM_IMAGE_ICON) {
	    private static final long serialVersionUID = 5128574049819721220L;

	    public void actionPerformed(ActionEvent e) {
		KMADEPrintingTaskModelPanel.this.setTaskModelZoom(1d);
	    }
	};
	zoomDefaultAction.putValue(AbstractAction.SHORT_DESCRIPTION,
		KMADEConstant.ZOOM_DEFAULT_ACTION_MESSAGE);
	myToolBar.add(zoomDefaultAction);

	JPanel myPanelToolBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
	myPanelToolBar.add(myToolBar);

	refCardLayout = new CardLayout();
	myPanel = new JPanel() {
	    private static final long serialVersionUID = 706337229826676770L;

	    public Dimension getPreferredSize() {
		if (refCardLayout == null)
		    return super.getPreferredSize();

		if (inPreviewMode) {
		    return panelPreview.getPreferredSize();
		} else {
		    return myGraph.getPreferredSize();
		}
	    }
	};
	myPanel.setLayout(refCardLayout);

	panelPreview = new JPanel() {
	    private static final long serialVersionUID = 1146891657978396941L;

	    public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(myGraph.getBackground());
		if (inPreviewMode) {
		    if (buffer_image != null) {
			((Graphics2D) g).drawImage(buffer_image, 0, 0, null);
		    }
		}
	    }

	    public Dimension getPreferredSize() {
		if (buffer_image == null) {
		    return new Dimension(100, 100);
		} else {
		    return new Dimension(buffer_image.getTileWidth(),
			    buffer_image.getTileHeight());
		}
	    }

	};

	this.setLayout(new BorderLayout());
	this.add(BorderLayout.NORTH, myPanelToolBar);
	this.add(BorderLayout.CENTER, new JScrollPane(myGraph));
    }

    public void setModelToPrint(DefaultGraphModel myModel, GraphLayoutCache lc) {
	myGraph.setModel(myModel);
	myGraph.setGraphLayoutCache(lc);
	// Extrait la taille réelle du graphe.
	jgraphSize = KMADEPrintingTaskModelPanel.getRealJGraphSize(myGraph);
    }

    private static Rectangle getRealJGraphSize(JGraph myGraph) {
	CellView[] allCellView = myGraph.getGraphLayoutCache().getCellViews();
	double maxWidth = 0;
	double maxHeigh = 0;
	for (int i = 0; i < allCellView.length; i++) {
	    if (allCellView[i] instanceof VertexView) {
		VertexView currentVV = (VertexView) allCellView[i];
		Rectangle2D myRect = currentVV.getBounds();
		double cxw = myRect.getMaxX();
		double cyh = myRect.getMaxY();

		maxWidth = Math.max(cxw, maxWidth);
		maxHeigh = Math.max(cyh, maxHeigh);

	    }
	}
	return new Rectangle(0, 0, (int) maxWidth, (int) maxHeigh);
    }

    private static Rectangle getRealJGraphScaleSize(Rectangle current,
	    JGraph myGraph, double scale) {
	// Faut maintenant penser au Scale du JGraph.
	double maxWidth = current.getWidth() * scale;// myGraph.getScale();
	double maxHeigh = current.getHeight() * scale;// myGraph.getScale();

	return new Rectangle(0, 0, (int) maxWidth, (int) maxHeigh);
    }

    public void setScaleView(double p) {
	scaleView = p;
	myGraph.setScale(this.scaleGraph * this.scaleView);
	myGraph.repaint();
	myPanel.revalidate();
    }

    private void setTaskModelZoom(double p) {
	inPreviewMode = false;
	refCardLayout.show(myPanel, "TaskModel");
	this.scaleGraph = p;
	myGraph.setScale(p * this.scaleView);
	myGraph.repaint();
	myPanel.revalidate();
    }

    public void refreshGraph() {
	myGraph.repaint();
    }

    class JGraphExtendedView extends JGraph {
	private static final long serialVersionUID = 1290553252166040948L;

	public JGraphExtendedView() {
	    super();
	}

	public void paint(Graphics g) {
	    super.paint(g);
	    // Les dimensions pour la page d'impression.
	    double wp = refDialog.getPageFormat().getImageableWidth()
		    * scaleView;
	    double hp = refDialog.getPageFormat().getImageableHeight()
		    * scaleView;

	    // Les dimensions du modèle de tâches.
	    Rectangle myGraphBounds = KMADEPrintingTaskModelPanel
		    .getRealJGraphScaleSize(jgraphSize, myGraph, scaleGraph);
	    double w = myGraphBounds.width * scaleView;
	    double h = myGraphBounds.height * scaleView;

	    int currentx = 0;
	    int currenty = 0;

	    int textHGap = (int) (40 * scaleView);
	    int textVGap = (int) (10 * scaleView);

	    int rightHGap = (int) (55 * scaleView);
	    int rightVGap = (int) (7 * scaleView);

	    int bottomHGap = (int) (4 * scaleView);
	    int bottomVGap = (int) (5 * scaleView);

	    int topHGap = (int) (4 * scaleView);
	    int topVGap = (int) (20 * scaleView);

	    int leftHGap = (int) (32 * scaleView);
	    int leftVGap = (int) (7 * scaleView);

	    int widthIcon = (int) (32 * scaleView);
	    int heightIcon = (int) (32 * scaleView);

	    int nw = (int) (Math.ceil(w / wp));
	    int nh = (int) (Math.ceil(h / hp));
	    if (nw == 0)
		nw = 1;
	    if (nh == 0)
		nh = 1;

	    int currentPage = 0;
	    Font myFont = new Font("Serif", Font.PLAIN, (int) (25 * scaleView));
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setFont(myFont);

	    FontRenderContext frc = g2d.getFontRenderContext();
	    for (int i = 0; i < nh; i++) {
		for (int j = 0; j < nw; j++) {
		    AlphaComposite composite = AlphaComposite.getInstance(
			    AlphaComposite.SRC_OVER, 0.5f);
		    g2d.setComposite(composite);

		    if (!printMode) {
			g2d.setColor(KMADEPrintingTaskModelPanel.colorPage);
			g2d.drawRect(currentx, currenty, (int) wp, (int) hp);
			g2d.setColor(KMADEPrintingTaskModelPanel.colorLandmark);
		    }

		    currentPage = nw * i + j + 1;
		    String text = Integer.toString(currentPage);
		    Rectangle2D bounds = g2d.getFont().getStringBounds(text,
			    frc);
		    int width = (int) bounds.getWidth();
		    int heigh = (int) bounds.getHeight();

		    g2d.drawString(namePage + text,
			    (int) (currentx + wp - (width + textHGap)),
			    (int) (currenty + hp - textVGap));

		    // Affiche la page à droite.
		    if (j + 1 < nw) {
			text = Integer.toString(currentPage + 1);
			bounds = g2d.getFont().getStringBounds(text, frc);
			width = (int) bounds.getWidth();
			g2d.drawString(namePage + text, (int) (currentx + wp)
				- (width + rightHGap),
				(int) (currenty + (hp / 2)) + (heigh / 2)
					- rightVGap);
			g2d.drawImage(right, (int) (currentx + wp) - widthIcon,
				(int) (currenty + (hp / 2)) - (heightIcon / 2),
				widthIcon, heightIcon, null);
		    }

		    // Affiche la page du bas.
		    if (i + 1 < nh) {
			text = Integer.toString(currentPage + nw);
			bounds = g2d.getFont().getStringBounds(text, frc);
			width = (int) bounds.getWidth();
			g2d.drawString(namePage + text, (int) (currentx
				+ (wp / 2) - (width + bottomHGap)),
				(int) (currenty + hp) - (heigh + bottomVGap));
			g2d.drawImage(down, (int) (currentx + wp / 2)
				- (widthIcon / 2), (int) (currenty + hp)
				- (heightIcon), widthIcon, heightIcon, null);
		    }

		    // Affiche la page du haut.
		    if (i != 0) {
			text = Integer.toString(currentPage - nw);
			bounds = g2d.getFont().getStringBounds(text, frc);
			width = (int) bounds.getWidth();
			g2d.drawString(namePage + text, (int) (currentx
				+ (wp / 2) - (width + topHGap)),
				(int) (currenty) + (heigh + topVGap));
			g2d.drawImage(up, (int) (currentx + wp / 2)
				- (widthIcon / 2), (int) (currenty), widthIcon,
				heightIcon, null);
		    }

		    // Affiche la page de gauche.
		    if (j != 0) {
			text = Integer.toString(currentPage - 1);
			bounds = g2d.getFont().getStringBounds(text, frc);
			width = (int) bounds.getWidth();
			g2d.drawString(namePage + text, (int) currentx
				+ leftHGap, (int) (currenty + (hp / 2))
				+ (heigh / 2) - leftVGap);
			g2d.drawImage(left, (int) currentx,
				(int) (currenty + (hp / 2)) - (heightIcon / 2),
				widthIcon, heightIcon, null);
		    }

		    currentx += wp;

		    // Si (j + 1 == nw) => plus de page à droite
		    // Si (i + 1 == nh) => plus de page en bas
		    // Page en haut ? => i != 0
		    // Page à gauche ? => j != 0
		    // Current Page = nw*i + j + 1
		    // Nombre Page = nw * nh
		    // Page à droite : Current Page + 1 (si (j + 1 != nw))
		    // Page à gauche : Current Page - 1 (si (j + 1 != 1))
		    // Page en haut :
		    // Page en bas :

		}
		currentx = 0;
		currenty += hp;
	    }

	    // Ecrire en bas de page un descriptif du numéro de page.
	}

	public Dimension getPreferredSize() {
	    double wp = refDialog.getPageFormat().getImageableWidth()
		    * scaleView;
	    double hp = refDialog.getPageFormat().getImageableHeight()
		    * scaleView;

	    Rectangle myGraphBounds = KMADEPrintingTaskModelPanel
		    .getRealJGraphScaleSize(jgraphSize, myGraph, scaleGraph);
	    double w = myGraphBounds.width * scaleView;
	    double h = myGraphBounds.height * scaleView;

	    int tnw = (int) (Math.ceil(w / wp));
	    int tnh = (int) (Math.ceil(h / hp));
	    if (tnw == 0)
		tnw = 1;
	    if (tnh == 0)
		tnh = 1;

	    return new Dimension((int) (tnw * wp + 10), (int) (tnh * hp) + 10);
	}
    }

    public void makeTraitement() {
	inPreviewMode = false;
	refCardLayout.show(myPanel, "TaskModel");

	Book myBook = new Book();

	// Les dimensions pour la page d'impression.
	double wp = refDialog.getPageFormat().getImageableWidth();
	double hp = refDialog.getPageFormat().getImageableHeight();

	// Les dimensions du modèle de tâches.
	Rectangle myGraphBounds = KMADEPrintingTaskModelPanel
		.getRealJGraphScaleSize(jgraphSize, myGraph, myGraph.getScale());
	double w = myGraphBounds.width;
	double h = myGraphBounds.height;

	int currentx = 0;
	int currenty = 0;

	int nw = (int) (Math.ceil(w / wp));
	int nh = (int) (Math.ceil(h / hp));
	if (nw == 0)
	    nw = 1;
	if (nh == 0)
	    nh = 1;

	for (int i = 0; i < nh; i++) {
	    for (int j = 0; j < nw; j++) {
		BookComponentPrintable toto = new BookComponentPrintable(
			myGraph, currentx, currenty);
		myBook.append(toto, refDialog.getPageFormat());
		currentx += wp;
	    }
	    currentx = 0;
	    currenty += hp;
	}

	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		try {
		    KMADEPrintingDialog.getPrinterJob().print();
		} catch (PrinterException e) {
		    KMADEHistoryMessageManager.printlnError(e.getMessage());
		}
		return null;
	    }
	};

	KMADEPrintingDialog.getPrinterJob().setPageable(myBook);
	if (KMADEPrintingDialog.getPrinterJob().printDialog()) {
	    worker.start();
	}
    }

    class BookComponentPrintable implements Printable {
	private Component mComponent;
	private int x;
	private int y;

	public BookComponentPrintable(Component c, int currentx, int currenty) {
	    mComponent = c;
	    x = currentx;
	    y = currenty;
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
		throws PrinterException {
	    myGraph.setDoubleBuffered(false);
	    int width = (int) (pageFormat.getImageableX()
		    + pageFormat.getWidth() + 5);
	    int heigh = (int) (pageFormat.getImageableY()
		    + pageFormat.getHeight() + 5);
	    BufferedImage buffer_image = new BufferedImage(width, heigh,
		    BufferedImage.TYPE_INT_RGB);
	    Graphics2D im = buffer_image.createGraphics();

	    AffineTransform at = AffineTransform.getTranslateInstance(
		    pageFormat.getImageableX() - x - 2,
		    pageFormat.getImageableY() - y - 2);
	    im.transform(at);

	    printMode = true;
	    mComponent.paint(im);
	    printMode = false;

	    graphics.drawImage(buffer_image, 0, 0, Color.WHITE, null);
	    myGraph.setDoubleBuffered(true);
	    return BookComponentPrintable.PAGE_EXISTS;
	}
    }
}

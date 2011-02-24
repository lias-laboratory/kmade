package fr.upensma.lias.kmade.tool.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

import fr.lri.swingstates.applets.MenuItem;
import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

public class Menu {

	protected static Color BG_COLOR;

	protected static Color HILITE_COLOR;

	public static final Color BORDER_COLOR = new Color(10, 195, 140);

	public static final Font FONT = new Font("verdana", Font.BOLD, 15);
	
	// Permet de d�finir la taille du point centrale qui est une ellipse ->
	// an ellipse, specified by its bounding box (upper left corner, width and height)(x,y,w,h)
	// valeur pour w et h
	public static final int taillePointCentre = 10;
	//valeur pour x et y
	public static final int centerPointCentre = -5;
	
	protected CShape parent;

	protected CExtensionalTag tagWhole, tagLabels;

	protected CStateMachine interaction;

	protected JStateMachine smText;

	protected Canvas canvas;

	protected String lastItemVisited;

	protected boolean isVisible = false;

	protected Point2D pointDisplay;

	// cette variable permet de conna�tre la taille des �lements en dehors du cadre de l'espace de t�che
	// ils sont calcul� automatiquement
	protected Point2D gap = new Point2D.Double(0, 0);

	protected Object[] selection;

	protected boolean onJGraph = false;

	// l'attribut a �t� supprim�, il fallait soit le mettre en static, soit utilis� les fonctions d�j� en place
	// l'attribut mainframe a donc �t� remplac� par GraphicEditorAdaptator.getMainFrame(); ou KMADEMainFrame 
	//protected KMADEMainFrame mainFrame = GraphicEditorAdaptator.getMainFrame();

	protected boolean menuDisplay = false;

	/** g�re la surbrillance des items des menus
	 * 
	 * @return la CStateMachine g�rant la surbrillance
	 */
	public static CStateMachine getHiliteMachine() {
		return new CStateMachine() {
			/* on entre sur un item, on change la couleur */
			public State out = new State() {
				Transition hiliteItem = new EnterOnTag(MenuItem.class, ">> in") {
					public void action() {
						getShape().setFillPaint(HILITE_COLOR);
					}
				};
			};

			/* on sort d'un item, on le remet � sa couleur initial */
			public State in = new State() {
				Transition unhiliteItem = new LeaveOnTag(MenuItem.class,
						">> out") {
					public void action() {
						getShape().setFillPaint(BG_COLOR);
					}
				};
			};
		};
	};

	public Menu(Canvas canvas, Color c1, Color c2) {
		this.canvas = canvas;
		BG_COLOR = c1;
		HILITE_COLOR = c2;
		tagWhole = new CExtensionalTag(canvas) {
		};
		tagLabels = new CExtensionalTag(canvas) {
		};
	}

	void showMenu(Point2D pt) {
		parent.translateTo(pt.getX(), pt.getY()).setDrawable(true);
		tagWhole.setDrawable(true).setPickable(true);
		tagWhole.aboveAll();
		tagLabels.aboveAll();
		tagLabels.setPickable(false);

	}

	public void hideMenu() {
		isVisible = false;
		tagWhole.setDrawable(false).setPickable(false);
	}

	public boolean isVisible() {
		return isVisible;
	}
}

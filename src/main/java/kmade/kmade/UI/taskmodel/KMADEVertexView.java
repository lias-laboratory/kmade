package kmade.kmade.UI.taskmodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.KMADEMainFrame;
import kmade.kmade.UI.taskmodel.KMADETaskModelPanel.MyBasicGraphUI;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.tache.Executant;
import kmade.nmda.schema.tache.Tache;

import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

/**
 * K-MADe : Kernel of Model for Activity Description environment Copyright (C)
 * 2006 INRIA - MErLIn Project
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * 
 * 
 * @author MickaÃ«l BARON (baron@ensma.fr ou baron.mickael@gmail.com)
 */
public class KMADEVertexView extends VertexView {

	private static final long serialVersionUID = 3183893455933034534L;

	public static final ImageIcon ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.ABSTRACT_TASK_IMAGE));

	public static final ImageIcon FEEDBACK_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.FEEDBACK_TASK_IMAGE));

	public static final ImageIcon USER_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class.getResource(KMADEConstant.USER_TASK_IMAGE));

	public static final ImageIcon INTERACTIF_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.INTERACTIF_TASK_IMAGE));

	public static final ImageIcon UNKNOWN_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class.getResource(KMADEConstant.UNKNOWN_TASK_IMAGE));

	public static final ImageIcon OPTIONAL_ABSTRACT_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.OPTIONAL_ABSTRACT_TASK_IMAGE));

	public static final ImageIcon OPTIONAL_USER_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.OPTIONAL_USER_TASK_IMAGE));

	public static final ImageIcon OPTIONAL_INTERACTIF_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.OPTIONAL_INTERACTIF_TASK_IMAGE));

	public static final ImageIcon OPTIONAL_UNKNOWN_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.OPTIONAL_UNKNOWN_TASK_IMAGE));

	public static final ImageIcon INTERRUPTIBLE_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.INTERRUPTIBLE_TASK_IMAGE));

	public static final ImageIcon EXPAND_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class.getResource(KMADEConstant.ZOOM_OUT_IMAGE));

	public static final ImageIcon COLLAPSE_TASK_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class.getResource(KMADEConstant.ZOOM_IN_IMAGE));

	/** * Icon des évènements entrants/sortants** */
	public static final ImageIcon EVENEMENT_IN_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class.getResource(KMADEConstant.EVENEMENT_IN_IMAGE));

	public static final ImageIcon EVENEMENT_OUT_IMAGE_ICON = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.EVENEMENT_OUT_IMAGE));

	public static final ImageIcon EVENEMENT_IN_IMAGE_ICON_DISABLE = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.EVENEMENT_IN_IMAGE_DISABLE));

	public static final ImageIcon EVENEMENT_OUT_IMAGE_ICON_DISABLE = new ImageIcon(
			KMADEVertexView.class
					.getResource(KMADEConstant.EVENEMENT_OUT_IMAGE_DISABLE));

	protected static final int IMAGE_32_WIDTH = 32;

	protected static final int IMAGE_32_HEIGH = 32;

	protected static final int IMAGE_16_WIDTH = 16;

	protected static final int IMAGE_16_HEIGH = 16;

	protected static final int H_GAP = 2;

	protected static final int V_GAP = 2;

	protected static final int BEGIN_V_GAP = 10;

	protected static final int H_FONT_GAP = 10;

	protected static final int NUM_LIMIT = 30; // a été augmenté

	protected static final int NAME_LIMIT = 45;

	public static final KMADEGraphCellEditor editor = new KMADEGraphCellEditor();

	protected JGraph myGraph;

	// partie rajoutée///////////////////////////////////////////////
	private Object select;

	protected Rectangle2D depart;

	private JGraph graph;

	private CellView[] tabCellules;

	private double ordonnee;

	private double ancienY = Double.POSITIVE_INFINITY;
	
	// mis à jour mais pas utilisé
	@SuppressWarnings("unused")
	private double distanceX;

	private double distanceY;

	private Point2D pointDepart;

	private boolean haut;

	private boolean coherent;

	private boolean nomCorrect;

	private boolean tacheCorrecte;

	private boolean tacheIsolee;

	private boolean filsCoherent;

	
	@SuppressWarnings("unused")
	//dimension du rectangle mis à jour mais pas utilisé
	private Rectangle2D rectNum;

	private Rectangle2D rectNom;

	private Rectangle2D rectOpe;

	private Rectangle2D rectPre;

	private Rectangle2D rectPost;

	private Rectangle2D rectRep;

	private Rectangle2D rectAct;

	private Rectangle2D rectObj;

	private Rectangle2D rectTache;

	private Rectangle2D rectEvenementIn;

	private Rectangle2D rectEvenementOut;

	private Rectangle2D rectOpt;

	private Rectangle2D rectInter;

	// //////////////////////////////////////////////////////////////

	public KMADEVertexView(Object cell, JGraph myGraph) {
		super(cell);
		this.myGraph = myGraph;
		graph = myGraph;

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
		double zoom = graph.getScale();
		KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) this
				.getCell();

		JPanel myPanel = new JPanel();

		FontMetrics fname = myPanel
				.getFontMetrics(KMADEConstant.TASK_NAME_FONT);
		String name = myGraphCell.getName();
		if (name.length() > NAME_LIMIT) {
			name = name.substring(0, NAME_LIMIT) + "...";
		}

		FontMetrics fnum = myPanel.getFontMetrics(KMADEConstant.TASK_NUM_FONT);
		String num = "N:" + myGraphCell.getNumero();
		if (num.length() > NUM_LIMIT) {
			num = num.substring(0, NUM_LIMIT) + "...";
		}
		int width = 0;
		if (fname.stringWidth(name) < 120) {
			width = 120;
		} else {
			width = fname.stringWidth(name) + 10;
		}
		int hauteur = 10 + fnum.getHeight() + 5 + 32 + fname.getHeight() + 10
				+ 10 + 15 + 5;

		rectTache = new Rectangle((int) (zoom * temp.getX()),
				(int) (zoom * temp.getY()), (int) (zoom * width),
				(int) (zoom * hauteur));

		return new Rectangle2D.Double(temp.getX(), temp.getY(), width, hauteur);

	}
	
	/**
	 * Retourne la longueur d'une tâche ayant pour nom name
	 * @param name
	 * @return la longueur
	 */
	public static int widthView(String name){
		JPanel myPanel = new JPanel();
		FontMetrics fname = myPanel.getFontMetrics(KMADEConstant.TASK_NAME_FONT);

		int width = 0;
		if (fname.stringWidth(name) < 120) {
			width = 120;
		} else {
			width = fname.stringWidth(name) + 10;
		}
		return width;
		
	}

	/**
	 * Retourne la hauteur d'une tâche
	 * @return la hauteur
	 */
	public static int heightView(){
		JPanel myPanel = new JPanel();
		FontMetrics fname = myPanel.getFontMetrics(KMADEConstant.TASK_NAME_FONT);
		FontMetrics fnum = myPanel.getFontMetrics(KMADEConstant.TASK_NUM_FONT);
		return 10 + fnum.getHeight() + 5 + 32 + fname.getHeight() + 10
		+ 10 + 15 + 5;
	}
	public class MyVertexRenderer extends VertexRenderer {

		private static final long serialVersionUID = 5060836696940302890L;

		public ImageIcon myImage;

		protected void paintTask(Graphics gr) {
			KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexView.this
					.getCell();
			Graphics2D f = (Graphics2D) gr;

			paintLabelCaract(f, myGraphCell);

			// on détecte les incohérences pour la décomposition et le nom
			detecteIncoherence();

			if (myGraphCell.isFlagSearch()) {
				double x = 1, y = 1, w = this.getBounds().getWidth() - 3, h = this
						.getBounds().getHeight() - 3;
				Ellipse2D ellips = new Ellipse2D.Double(x, y, w, h);
				f.setColor(Color.WHITE);
				GradientPaint gp = new GradientPaint(50, 50, Color.WHITE, 80,
						80, new Color(192, 255, 192), true);
				f.setPaint(gp);
				f.fill(ellips);

				f.setColor(Color.RED);

				f.draw(ellips);
				f.clip(ellips);
			}

			f.setColor(Color.BLACK);

			/*******************************************************************
			 * Les inscriptions centrées
			 ******************************************************************/
			int width = (int) this.getBounds().getWidth() / 2;

			double zoom = graph.getScale();

			// Le numÃ©ro
			String numero = KMADEConstant.VERTEX_NUM+ myGraphCell.getNumero();
			/** * augmentation de NUM_LIMIT ** */
			if (numero.length() > NUM_LIMIT) {
				numero = numero.substring(0, NUM_LIMIT) + "...";
			}
			f.setFont(KMADEConstant.TASK_NUM_FONT);
			FontMetrics fm = f.getFontMetrics();
			Rectangle2D rnum = fm.getStringBounds(numero, f);
			f.drawString(numero, (int) ((int) width - (rnum.getWidth() / 2)),
					(int) rnum.getHeight() + BEGIN_V_GAP);

			rectNum = new Rectangle((int) (zoom * (this.getBounds().getX()
					+ width - (rnum.getWidth() / 2))), (int) (zoom * (this
					.getBounds().getY() + rnum.getHeight())),
					(int) (zoom * rnum.getWidth()), (int) (zoom * rnum
							.getHeight()));
			// L'image
			Executant e = myGraphCell.getExecutant();
			myImage = KMADEVertexView.UNKNOWN_TASK_IMAGE_ICON;
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
				myImage.paintIcon(this, gr, width - IMAGE_32_WIDTH / 2,
						BEGIN_V_GAP + (int) rnum.getHeight() + V_GAP);
			}
			int currentHeighImage = BEGIN_V_GAP + (int) rnum.getHeight()
					+ V_GAP + IMAGE_32_HEIGH + V_GAP;

			// Le nom
			String name = myGraphCell.getName();
			if (name.length() > NAME_LIMIT) {
				name = name.substring(0, NAME_LIMIT) + "...";
			}
			f.setFont(KMADEConstant.TASK_NAME_FONT);
			f.setColor(KMADEConstant.TASK_NAME_COLOR);

			if (!nomCorrect) {
				if (KMADEConstant.TASK_NAME_COLOR.equals(Color.RED)) {
					f.setColor(Color.BLACK);
				} else {
					f.setColor(Color.RED);
				}
			}

			fm = f.getFontMetrics();
			Rectangle2D rn = fm.getStringBounds(name, f);
			f.drawString(name, (int) (width - (rn.getWidth() / 2)),
					currentHeighImage + (int) (rn.getHeight()));
			rectNom = new Rectangle((int) (zoom * (this.getBounds().getX()
					+ width - (rn.getWidth() / 2))), (int) (zoom * (this
					.getBounds().getY() + currentHeighImage)), (int) (zoom * rn
					.getWidth()), (int) (zoom * rn.getHeight()));
			currentHeighImage += (int) (rn.getHeight()) + H_FONT_GAP;
			f.setColor(KMADEConstant.TASK_NAME_COLOR);
			f.setBackground(Color.magenta);
			// L'opÃ©rateur
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

			// Le rectange autour de l'opÃ©rateur
			f.drawRect((int) (width - (ro.getWidth() / 2)) - H_GAP,
					currentHeighImage - H_GAP, (int) ro.getWidth() + 2 * H_GAP,
					(int) ro.getHeight() + 3 * V_GAP);

			rectOpe = new Rectangle(
					(int) (zoom * (this.getBounds().getX() + width
							- (ro.getWidth() / 2) - H_GAP)),
					(int) (zoom * (this.getBounds().getY() + currentHeighImage - H_GAP)),
					(int) (zoom * (ro.getWidth() + 2 * H_GAP)),
					(int) (zoom * (ro.getHeight() + 3 * V_GAP)));

			if (!coherent) {
				f.setColor(Color.RED);
				f.fillRect((int) (width - (ro.getWidth() / 2)) - H_GAP,
						currentHeighImage - H_GAP, (int) ro.getWidth() + 2
								* H_GAP, (int) ro.getHeight() + 3 * V_GAP);
				f.setColor(KMADEConstant.TASK_OPERATOR_COLOR);
			}

			f.drawString(operateur, (int) (width - (ro.getWidth() / 2)),
					currentHeighImage + (int) (ro.getHeight()));

			int widthg = H_GAP;

			if (graph != null && graph.getScale() >= 1) {
				/***************************************************************
				 * Les inscriptions de gauche
				 **************************************************************/
				// L'icône évènement
				ImageIcon myImage_evt_in;
				if (myGraphCell.getTask().getDeclencheur() != null) {
					myImage_evt_in = KMADEVertexView.EVENEMENT_IN_IMAGE_ICON;
				} else {
					myImage_evt_in = KMADEVertexView.EVENEMENT_IN_IMAGE_ICON_DISABLE;
					// myImage_evt_in= KMADEVertexView.EVENEMENT_IN_IMAGE_ICON;
				}
				myImage_evt_in.paintIcon(this, gr, widthg, (int) rnum
						.getHeight());
				// la tooltips
				rectEvenementIn = new Rectangle((int) (zoom * (this.getBounds()
						.getX() + widthg)), (int) (zoom * (this.getBounds()
						.getY() + myImage_evt_in.getIconHeight())),
						(int) (zoom * myImage_evt_in.getIconWidth()),
						(int) (zoom * myImage_evt_in.getIconHeight()));

				// L'icône PRE
				String s_pre = KMADEConstant.VERTEX_PRECONDITION;

				if (myGraphCell.getTask().getPreExpression().getName().equals("true")) {
					f.setColor(Color.LIGHT_GRAY);
				} else {
					f.setColor(Color.BLACK);
				}
				
				f.setFont(KMADEConstant.TASK_NUM_FONT);
				FontMetrics fm_pre = f.getFontMetrics();
				Rectangle2D rpre = fm_pre.getStringBounds(s_pre, f);
				f.drawString(s_pre, widthg, (int) rpre.getHeight()
						+ BEGIN_V_GAP + myImage_evt_in.getIconHeight());

				rectPre = new Rectangle(
						(int) ((this.getBounds().getX() + widthg) * zoom),
						(int) ((this.getBounds().getY() + rpre.getHeight() + myImage_evt_in
								.getIconHeight()) * zoom), (int) (zoom * rpre
								.getWidth()), (int) (zoom * rpre.getHeight()));

				f.setColor(Color.BLACK);

				// L'icône OPT
				String s_opt = KMADEConstant.VERTEX_OPT;
				Rectangle2D ropt = fm_pre.getStringBounds(s_opt, f);

				if (myGraphCell.getTask().isFacultatif()) {
					f.setColor(Color.BLACK);
				} else {
					f.setColor(Color.LIGHT_GRAY);
				}
				f.drawString(s_opt, widthg, (int) ropt.getHeight()
						+ BEGIN_V_GAP + myImage_evt_in.getIconHeight()
						+ (int) rpre.getHeight());

				rectOpt = new Rectangle(
						(int) (zoom * (this.getBounds().getX() + widthg)),
						(int) ((this.getBounds().getY() + ropt.getHeight()
								+ myImage_evt_in.getIconHeight() + rpre
								.getHeight()) * zoom), (int) (zoom * ropt
								.getWidth()), (int) (zoom * ropt.getHeight()));

				f.setColor(Color.BLACK);

				// INTER
				String s_int = KMADEConstant.VERTEX_INTERRUPTIBLE;
				Rectangle2D rint = fm_pre.getStringBounds(s_int, f);

				if (myGraphCell.getTask().isInterruptible()) {
					f.setColor(Color.BLACK);
				} else {
					f.setColor(Color.LIGHT_GRAY);
				}

				f.drawString(s_int, widthg, (int) ropt.getHeight()
						+ BEGIN_V_GAP + myImage_evt_in.getIconHeight()
						+ (int) rpre.getHeight() + (int) rint.getHeight());
				rectInter = new Rectangle((int) (zoom * (this.getBounds()
						.getX() + widthg)), (int) (zoom * (this.getBounds()
						.getY()
						+ ropt.getHeight()
						+ myImage_evt_in.getIconHeight()
						+ rpre.getHeight() + rint.getHeight())),
						(int) (zoom * rint.getWidth()), (int) (zoom * rint
								.getHeight()));

				f.setColor(Color.BLACK);

				// ACT
				if(myGraphCell.getTask().canHaveActor()){
				String s_act = KMADEConstant.VERTEX_ACTOR;
				Rectangle2D ract = fm_pre.getStringBounds(s_act, f);

				if (myGraphCell.getTask().getActeurs() != null
						&& myGraphCell.getTask().getActeurs().size() != 0) {
					f.setColor(Color.BLACK);
				} else {
					f.setColor(Color.LIGHT_GRAY);
				}

				f.drawString(s_act, widthg, (int) ropt.getHeight()
						+ BEGIN_V_GAP + myImage_evt_in.getIconHeight()
						+ (int) rpre.getHeight() + (int) rint.getHeight()
						+ (int) ract.getHeight() + IMAGE_32_HEIGH
						+ (int) ract.getHeight());
				f.setColor(Color.BLACK);
				// la tooltip
				rectAct = new Rectangle(
						(int) (zoom * (this.getBounds().getX() + widthg)),
						(int) (zoom * (this.getBounds().getMinY()
								+ (int) rpre.getHeight()
								+ myImage_evt_in.getIconHeight()
								+ (int) ropt.getHeight()
								+ (int) rint.getHeight() + (int) rn.getHeight()
								+ (int) ro.getHeight() + (int) ract.getHeight()
								+ (int) ract.getHeight() + 5)),
						(int) (zoom * ract.getWidth()), (int) (zoom * ract
								.getHeight()));
				}
				int widthd = (int) this.getBounds().getWidth() - H_GAP;
				/***************************************************************
				 * Les inscriptions de droite
				 **************************************************************/
				// L'icône REP
				String s_rep = KMADEConstant.VERTEX_ITERATION;
				
				Rectangle2D rrep = fm_pre.getStringBounds(s_rep, f);

				if (!myGraphCell.getTask().getIteExpression().isMoreOneIteration())
					f.setColor(Color.LIGHT_GRAY);
				
				f.drawString(s_rep, widthd - (int) rrep.getWidth(), (int) rpre
						.getHeight()
						+ BEGIN_V_GAP + myImage_evt_in.getIconHeight() - V_GAP);
				// la tooltip
				rectRep = new Rectangle((int) (zoom * (this.getBounds().getX()
						+ widthd - (int) rrep.getWidth())), (int) (zoom * (this
						.getBounds().getY()
						+ (int) rpre.getHeight() + myImage_evt_in
						.getIconHeight())), (int) (zoom * rrep.getWidth()),
						(int) (zoom * rrep.getHeight()));
				

				f.setColor(Color.BLACK);

				// L'icône POST
				String s_post = KMADEConstant.VERTEX_EFFETSDEBORD;

				if (myGraphCell.getTask().getEffetsDeBordExpression().getName().equals("void")) {
					f.setColor(Color.LIGHT_GRAY);
				}
				
				Rectangle2D rpost = fm_pre.getStringBounds(s_post, f);

				f.drawString(s_post, widthd - (int) rpost.getWidth(),
						(int) rrep.getHeight() + BEGIN_V_GAP
								+ myImage_evt_in.getIconHeight()
								+ (int) rpost.getHeight() - V_GAP);

				// la tooltip
				rectPost = new Rectangle((int) (zoom * (this.getBounds().getX()
						+ widthd - (int) rpost.getWidth())),
						(int) (zoom * (this.getBounds().getY()
								+ myImage_evt_in.getIconHeight()
								+ (int) rrep.getHeight() + (int) rpost
								.getHeight())),
						(int) (zoom * rpost.getWidth()), (int) (zoom * rpost
								.getHeight()));

				// évènement
				ImageIcon myImage_evt_out;
				if (myGraphCell.getTask().getEvents() != null
						&& myGraphCell.getTask().getEvents().size() != 0) {
					myImage_evt_out = KMADEVertexView.EVENEMENT_OUT_IMAGE_ICON;
				} else {
					myImage_evt_out = KMADEVertexView.EVENEMENT_OUT_IMAGE_ICON_DISABLE;
					
				}
				myImage_evt_out.paintIcon(this, gr, widthd
						- myImage_evt_out.getIconWidth(), (int) rpre
						.getHeight()
						+ BEGIN_V_GAP
						+ myImage_evt_in.getIconHeight()
						+ (int) rpost.getHeight());
				// la tooltip
				rectEvenementOut = new Rectangle((int) (zoom * (this
						.getBounds().getX()
						+ widthd - myImage_evt_out.getIconWidth())),
						(int) (zoom * (this.getBounds().getY()
								+ myImage_evt_in.getIconHeight()
								+ (int) rrep.getHeight()
								+ (int) rpost.getHeight() + myImage_evt_out
								.getIconHeight())),
						(int) (zoom * myImage_evt_out.getIconWidth()),
						(int) (zoom * myImage_evt_out.getIconHeight()));

				// TODO objet non géré par les tâches
				// OBJ
				String s_obj = KMADEConstant.VERTEX_OBJET;
				Rectangle2D robj = fm_pre.getStringBounds(s_obj, f);
				f.setColor(Color.LIGHT_GRAY);
				f.drawString(s_obj, widthd - (int) robj.getWidth(), (int) ropt
						.getHeight()
						+ BEGIN_V_GAP
						+ myImage_evt_in.getIconHeight()
						+ (int) rpre.getHeight()
						+ (int) rint.getHeight()
						+ (int) robj.getHeight()
						+ IMAGE_32_HEIGH
						+ (int) robj.getHeight());
				f.setColor(Color.BLACK);
				// la tooltip
				rectObj = new Rectangle((int) (zoom * (this.getBounds().getX()
						+ widthd - (int) robj.getWidth())), (int) (zoom * (this
						.getBounds().getMinY()
						+ (int) rpre.getHeight()
						+ myImage_evt_in.getIconHeight()
						+ (int) ropt.getHeight() + (int) rint.getHeight()))
						+ (int) (zoom * (rn.getHeight() + (int) ro.getHeight()
								+ (int) robj.getHeight()
								+ (int) robj.getHeight() + 5)),
						(int) (zoom * robj.getWidth()), (int) (zoom * robj
								.getHeight()));

				
			}

			
			// La tache est selectionnee
			Dimension d = getSize();
			if (myGraph != GraphicEditorAdaptator.getMainFrame()
					.getPreviewWindow().getOverviewGraph()) {
				if (selected) {
					if (!tacheIsolee) {
						f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
					} else {
						f.setColor(Color.RED);
					}
					
					f.setStroke(GraphConstants.SELECTION_STROKE);
					f.drawRect(0, 0, d.width - 1, d.height - 1);
					

				}
			}

			if (tacheIsolee) {
				f.setColor(Color.RED);

				f.drawRect(0, 0, d.width - 1, d.height - 1);
				f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
			}
		}

		protected void paintLabelCaract(Graphics2D f,
				KMADEDefaultGraphCell myCell) {
			if (GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar().isLabelColorSelected()) {
				if (myCell.getTask().getLabel() != null) {
					if (myCell.getTask().getLabel().isColorVisible()) {
						Color taskColor = myCell.getTask().getLabel()
								.getColor();
						Color old = f.getColor();
						Color colorWithTransparency = new Color(taskColor
								.getRed(), taskColor.getGreen(), taskColor
								.getBlue(), (int) (100 * 0.70f));
						f.setColor(colorWithTransparency);
						f.fillRect(0, 0, this.getBounds().width, this
								.getBounds().height);
						f.setColor(old);
					}
				}
			}
		}

		protected void paintNumCaract(Graphics2D f, int widthString,
				String numString, double num) {

			f.drawString(numString, widthString, 0);
		}

		public void paint(Graphics gr) {

			KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexView.this
					.getCell();
			if (!myGraphCell.isExpanded()) {
				return;
			}

			if (GraphicEditorAdaptator.getMainFrame().getApplicationToolBar().getEditorsToolBar().isLabelSelected()) {
				if (myGraphCell.getTask().getLabel() != null) {
					if (!myGraphCell.getTask().getLabel().isVisible()) {
						return;
					}
				}
			}

			Graphics2D f = (Graphics2D) gr;

			// Agrandir ou rÃ©duire
			if (!myGraphCell.isMotherPortNoEdged()
					&& (myGraph == GraphicEditorAdaptator.getTaskModelPanel()
							.getJGraph())) {
				ImageIcon myImageIcon = null;
				if (myGraphCell.isSonExpanded()) {
					myImageIcon = KMADEVertexView.EXPAND_TASK_IMAGE_ICON;
				} else {
					myImageIcon = KMADEVertexView.COLLAPSE_TASK_IMAGE_ICON;
				}
				if (!preview) {
					/** icone repositionnée * */
					myImageIcon.paintIcon(this, gr, (int) this.getBounds()
							.getWidth()
							- H_GAP - myImageIcon.getIconWidth(), V_GAP);
				}
			}

			this.paintTask(gr);
			Dimension d = getSize();
			if (myGraph != GraphicEditorAdaptator.getMainFrame()
					.getPreviewWindow().getOverviewGraph()) {
				if (selected) {
					f.setStroke(GraphConstants.SELECTION_STROKE);
					
					if (!tacheIsolee) {
						f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
					} else {
						f.setColor(Color.RED);
					}

					f.drawRect(0, 0, d.width - 1, d.height - 1);
				}

			}

		}

		public void detecteIncoherence() {
			KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexView.this
					.getCell();

			String decomposition = myGraphCell.getDecomposition();

			String nom = myGraphCell.getTask().getName();

			filsCoherent = true;

			if (myGraphCell.getTask().getMotherTask() != null
					|| (myGraphCell.getTask().getFils() != null && myGraphCell
							.getTask().getFils().size() != 0)) {
				tacheIsolee = false;
			} else {
				tacheIsolee = true;
			}

			if (ExpressConstant.NEW_NAME_TASK.equals(nom)) {
				nomCorrect = false;
			} else {
				nomCorrect = true;
			}

			ArrayList<Tache> fils = myGraphCell.getTask().getFils();

			if (fils == null || fils.size() == 0) {

				if (!ExpressConstant.LEAF_LONG_NAME.equals(decomposition)) {
					coherent = false;
				} else {
					coherent = true;
				}

			

			} else {

				if (fils.size() == 1) {
					// une tâche ne peut pas avoir une seule sous tâche
					coherent = false;
					filsCoherent = false;
				} else if (ExpressConstant.LEAF_LONG_NAME.equals(decomposition)
						|| ExpressConstant.OPERATOR_NAME.equals(decomposition)) {
					coherent = false;
				} else {
					coherent = true;
				}
				tacheCorrecte = true;
			}

		}

	}

	public CellHandle getHandle(GraphContext context) {
		// //////rajouté/////////////
		graph = context.getGraph();

		ordonnee = this.getBounds().getY();
		tabCellules = graph.getGraphLayoutCache().getAllViews();
		// /////////////////////////////////////

		return new MyCellHandle(this);
	}

	class MyCellHandle implements CellHandle {

		private KMADEVertexView cell;

		private CellView plusPres = null;

		public MyCellHandle(KMADEVertexView vue) {
			super();
			cell = vue;

		}

		public void paint(Graphics g) {
		}

		public void overlay(Graphics g) {

		}

		public void mouseMoved(MouseEvent event) {
		}

		public void mousePressed(MouseEvent event) {

			select = graph.getSelectionCellAt(event.getPoint());

			depart = graph.getCellBounds(select);

			pointDepart = event.getPoint();

			if (depart != null) {

				distanceX = Math.abs(event.getX() - graph.getScale()
						* depart.getX());

				distanceY = Math.abs(event.getY() - graph.getScale()
						* depart.getY());

			}

			int mx = (int) (event.getX() / myGraph.getScale());
			int my = (int) (event.getY() / myGraph.getScale());
			Rectangle2D rec = getBounds();
			int tolerance = 2;

			
			

			int rx = (int) (rec.getX() + rec.getWidth() - H_GAP
					- KMADEVertexView.EXPAND_TASK_IMAGE_ICON.getIconWidth() - tolerance);
			
			int ry = (int) (rec.getY() + V_GAP + tolerance);
		
			int rw = rx + 16 + 2 * tolerance;
			int rh = ry + 16 + 2 * tolerance;

			if (mx >= rx && mx <= rw && my >= ry && my <= rh) {
				KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) getCell();
				if (!myGraphCell.isMotherPortNoEdged()) {
					myGraphCell.setSonExpanded(!myGraphCell.isSonExpanded());
					myGraph.repaint();
				}
			}
		}

		public void mouseDragged(MouseEvent event) {
			// ///////////rajouté////////////////////
		
			CellView cellRef = null;
			double zoom = graph.getScale();

			ordonnee = event.getPoint().getY();

			// on détecte le mouvement
			if ((zoom * ordonnee) > (zoom * pointDepart.getY())) {

				haut = false;

			} else if ((zoom * ordonnee) < (zoom * pointDepart.getY())) {

				haut = true;

			}

			pointDepart = event.getPoint();

			ordonnee -= distanceY;
			
			// on trouve la cellule la plus près
			cellRef = trouvePlusPres();
			
			if (ancienY != Double.POSITIVE_INFINITY) {

				graph.getGraphics().clearRect(0, (int) (ancienY),
						(int) (graph.getWidth()), (int) (1));

			}

			if (cellRef != null) {

				if (haut) {
					// on dessine le trait sur le haut de la cellule de référence
					((Graphics2D) graph.getGraphics()).drawLine(0,
							(int) (zoom * (cellRef.getBounds().getY())),
							(int) (graph.getWidth()), (int) (zoom * (cellRef
									.getBounds().getY())));
					ancienY = (int) (zoom * (cellRef.getBounds().getY()));
				} else {

//					 on dessine le trait sur le bas de la cellule de référence
					((Graphics2D) graph.getGraphics())
							.drawLine(
									0,
									(int) ((zoom * (cellRef.getBounds().getY() + cellRef
											.getBounds().getHeight()))),
									(int) (graph.getWidth()),
									(int) (zoom * ((cellRef.getBounds().getY() + cellRef
											.getBounds().getHeight()))));
					ancienY = (int) (zoom * (cellRef.getBounds().getY() + cellRef
							.getBounds().getHeight()));
				}
			}

			
			// /////////////////////////////
		}

		public void mouseReleased(MouseEvent event) {
			// ///////rajouté//////////////////
			double zoom = graph.getScale();
			
			// on efface l'éventuel trait
			if (ancienY != Double.POSITIVE_INFINITY) {
				graph.getGraphics().clearRect(0, (int) (zoom * ancienY),
						(int) (graph.getWidth()), 0);
				graph.repaint();
			}
			ancienY = Double.POSITIVE_INFINITY;

			
			
			if (plusPres != null) {

				// on aimante
				Rectangle2D newBounds = new Rectangle((int) cell.getBounds()
						.getX(), (int) plusPres.getBounds().getY(), (int) cell
						.getBounds().getWidth(), (int) cell.getBounds()
						.getHeight());
				cell.setBounds(newBounds);
			}

			boolean intersect = false;
			// on regarde si l'intersection est trop importante
			for (int i = 0; i < tabCellules.length; i++) {

				Object tmp = tabCellules[i].getCell();

				if (tmp != select && !graph.getModel().isEdge(tmp)
						&& !graph.getModel().isPort(tmp)) {
					CellView cellRef = tabCellules[i];
					
				intersect = intersect || cellRef.getBounds().contains(
						cell.getBounds().getCenterX(),
						cell.getBounds().getCenterY());

				if (intersect) {
					// on remet aux coordonnées de départ
					cell.setBounds(depart);
					break;
				}
				}
			}
			plusPres = null;

			// /////////////////////////////////
		}

		public void mouseClicked(MouseEvent event) {

			// on regarde si on a double cliqué dans un des rectangles définis et on fait l'action en conséquence
			
			
			if (rectOpt != null && rectOpt.contains(event.getPoint())) {
				((KMADEDefaultGraphCell) cell.getCell()).getTask()
						.setFacultatif(
								!((KMADEDefaultGraphCell) cell.getCell())
										.getTask().getFacultatif());

			} else if (rectInter != null
					&& rectInter.contains(event.getPoint())) {
				((KMADEDefaultGraphCell) cell.getCell()).getTask()
						.setInterruptible(
								!((KMADEDefaultGraphCell) cell.getCell())
										.getTask().getInterruptible());

			} else if (rectNom != null && rectNom.contains(event.getPoint())) {
				
				((MyBasicGraphUI) graph.getUI()).startEditing(cell.getCell(),
						event);
			} else if (rectAct != null && rectAct.contains(event.getPoint())) {
				// on rajoute un test pour savoir si la tache peut avoir des acteurs ou non
				KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexView.this
				.getCell();
				if(myGraphCell.getTask().canHaveActor()){
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache()
						.setActorList();
				}

			} else if (rectPre != null && rectPre.contains(event.getPoint())) {
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache()
						.setPrecondition();
			} else if (rectPost != null && rectPost.contains(event.getPoint())) {
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache()
						.setEffetsDeBord();
			} else if (rectRep != null && rectRep.contains(event.getPoint())) {
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache()
						.setIteration();
			} else if (rectEvenementOut != null
					&& rectEvenementOut.contains(event.getPoint())) {
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache()
						.setFiredEvents();
			} else if (rectEvenementIn != null
					&& rectEvenementIn.contains(event.getPoint())) {
				KMADEMainFrame.getProjectPanel()
						.getTaskDescriptionPanel().getProprieteTache().setEventDecl();
			}
			graph.repaint();
		}

		/**
		 * trouve la cellule la plus proche
		 * 
		 * @return la cellule la plus proche
		 */
		public CellView trouvePlusPres() {
			plusPres = null;
			CellView cellRef = null;
			// unused double zoom = graph.getScale();
			
			double dist = Double.MAX_VALUE;
			for (int i = 0; i < tabCellules.length; i++) {

				double y = tabCellules[i].getBounds().getY() * graph.getScale();

				double tmp;
				Object enCours = tabCellules[i].getCell();

				if (!graph.getModel().isEdge(enCours)
						&& !(graph.getModel().isPort(enCours))) {
					if (select != enCours) {
						

						tmp = Math.abs(y - ordonnee);
						if (tmp < dist) {

							dist = tmp;
							cellRef = tabCellules[i];
						}
					}
				}
			}

			double aimant = KMADEConstant.DISTANCE_AIMANT;

			

			if ((Math.abs(dist)) < (aimant)) {
				plusPres = cellRef;

			} else {
				plusPres = null;
			}

			return plusPres;

		}

	}

	public Rectangle2D getRectPre() {
		return rectPre;
	}

	public Rectangle2D getRectPost() {
		return rectPost;
	}

	public Rectangle2D getRectRep() {
		return rectRep;
	}

	public Rectangle2D getRectAct() {
		return rectAct;
	}

	public Rectangle2D getRectObj() {
		return rectObj;
	}

	public Rectangle2D getRectEvenementIn() {
		return rectEvenementIn;
	}

	public Rectangle2D getRectEvenementOut() {
		return rectEvenementOut;
	}

	public Rectangle2D getRectOpt() {
		return rectOpt;
	}

	public Rectangle2D getRectInter() {
		return rectInter;
	}

	public boolean isCoherent() {
		return coherent;
	}

	public boolean isNomCorrect() {
		return nomCorrect;
	}

	public boolean isTacheCorrecte() {
		return tacheCorrecte;
	}

	public boolean isTacheIsolee() {
		return tacheIsolee;
	}

	public Rectangle2D getRectNom() {
		return rectNom;
	}

	public Rectangle2D getRectOpe() {
		return rectOpe;
	}

	public boolean isFilsCoherent() {
		return filsCoherent;
	}

	public static int getIMAGE_32_WIDTH() {
		return IMAGE_32_WIDTH;
	}

	public static int getBEGIN_V_GAP() {
		return BEGIN_V_GAP;
	}

	public static int getIMAGE_32_HEIGH() {
		return IMAGE_32_HEIGH;
	}

	public Rectangle2D getRectTache() {
		return rectTache;
	}
}

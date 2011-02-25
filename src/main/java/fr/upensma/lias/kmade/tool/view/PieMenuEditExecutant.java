/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.tool.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JViewport;


import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexView;

import fr.lri.swingstates.applets.MenuItem;
import fr.lri.swingstates.canvas.CEllipse;
import fr.lri.swingstates.canvas.CImage;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultEdge;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraph;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class PieMenuEditExecutant extends Menu {
    private final Canvas canvas;

    private CText tooltipText;

    private boolean tooltip = false;

    private Point2D pointToolTip;

    private String[] items = { KMADEConstant.UNKNOWN_TASK_MESSAGE,
	    KMADEConstant.FEEDBACK_TASK_MESSAGE,
	    KMADEConstant.USER_TASK_MESSAGE,
	    KMADEConstant.INTERACTION_TASK_MESSAGE,
	    KMADEConstant.ABSTRACT_TASK_MESSAGE };

    private int rayonExt = 100;

    private double angleStep;

    public PieMenuEditExecutant(Canvas c) {
	super(c, new Color(182, 206, 241), new Color(255, 255, 0));
	this.canvas = c;
	menuLayout(items);
	hideMenu();

	/* machine qui g�re les �v�nements relatifs aux JComponent */
	smText = new JStateMachine() {

	    public State initialState = new State() {
		/*
		 * on "press" le bouton de gauche sur l'image pour afficher le
		 * menu
		 */
		Transition pressOnJGraph = new PressOnComponent(BUTTON1,
			">> initialState") {
		    public boolean guard() {
			if (onJGraph) {
			    selection = KMADEMainFrame.getProjectPanel()
				    .getTaskModelPanel().getJGraph()
				    .getSelectionCells();
			    if (selection != null) {
				if (selection.length == 1) {
				    /*
				     * on v�rifie que l'on est sur la tache
				     * s�lectionn�e
				     */
				    CellView[] allViews = KMADEMainFrame
					    .getProjectPanel()
					    .getTaskModelPanel().getJGraph()
					    .getGraphLayoutCache()
					    .getAllViews();

				    for (int i = 0; i < allViews.length; i++) {
					if (!selection[0].getClass().equals(
						KMADEDefaultEdge.class)
						&& allViews[i].getCell()
							.equals(selection[0])) {
					    Double scale = KMADEMainFrame
						    .getProjectPanel()
						    .getTaskModelPanel()
						    .getJGraph().getScale();

					    /*
					     * on v�rifie que l'on est sur
					     * l'image de l'executant
					     */
					    Point2D pointRectExecutant = new Point2D.Double(
						    scale
							    * ((VertexView) allViews[i])
								    .getBounds()
								    .getX()
							    + scale
							    * (((VertexView) allViews[i])
								    .getBounds()
								    .getWidth() / 2)
							    - scale
							    * (((KMADEVertexView) allViews[i])
								    .getIMAGE_32_WIDTH() / 2),
						    scale
							    * ((VertexView) allViews[i])
								    .getBounds()
								    .getY()
							    + scale
							    * ((KMADEVertexView) allViews[i])
								    .getBEGIN_V_GAP()
							    + 12 * scale);

					    Point2D currentPosition = getPoint();

					    if (currentPosition.getX() >= pointRectExecutant
						    .getX()
						    && currentPosition.getX() <= (pointRectExecutant
							    .getX() + scale
							    * ((KMADEVertexView) allViews[i])
								    .getIMAGE_32_WIDTH())
						    && currentPosition.getY() >= pointRectExecutant
							    .getY()
						    && currentPosition.getY() <= (pointRectExecutant
							    .getY() + scale
							    * ((KMADEVertexView) allViews[i])
								    .getIMAGE_32_HEIGH())) {
						return true;
					    }
					}
				    }
				}
			    }

			    /*
			     * si il n'y a pas de tache s�lectionn�e mais que
			     * l'on est sur l'image de l'executant d'une tache
			     * alor on la s�lectionne
			     */
			    CellView[] allViews = KMADEMainFrame
				    .getProjectPanel().getTaskModelPanel()
				    .getJGraph().getGraphLayoutCache()
				    .getAllViews();

			    for (int i = 0; i < allViews.length; i++) {
				if (allViews[i] instanceof KMADEVertexView) {
				    Double scale = KMADEMainFrame
					    .getProjectPanel()
					    .getTaskModelPanel().getJGraph()
					    .getScale();

				    Point2D pointRectExecutant = new Point2D.Double(
					    scale
						    * ((VertexView) allViews[i])
							    .getBounds().getX()
						    + scale
						    * (((VertexView) allViews[i])
							    .getBounds()
							    .getWidth() / 2)
						    - scale
						    * (((KMADEVertexView) allViews[i])
							    .getIMAGE_32_WIDTH() / 2),
					    scale
						    * ((VertexView) allViews[i])
							    .getBounds().getY()
						    + scale
						    * ((KMADEVertexView) allViews[i])
							    .getBEGIN_V_GAP()
						    + 12 * scale);

				    Point2D currentPosition = getPoint();

				    if (currentPosition.getX() >= pointRectExecutant
					    .getX()
					    && currentPosition.getX() <= (pointRectExecutant
						    .getX() + scale
						    * ((KMADEVertexView) allViews[i])
							    .getIMAGE_32_WIDTH())
					    && currentPosition.getY() >= pointRectExecutant
						    .getY()
					    && currentPosition.getY() <= (pointRectExecutant
						    .getY() + scale
						    * ((KMADEVertexView) allViews[i])
							    .getIMAGE_32_HEIGH())) {
					KMADEGraph graph = KMADEMainFrame
						.getProjectPanel()
						.getTaskModelPanel()
						.getJGraph();
					graph.setSelectionCell(allViews[i]
						.getCell());
					return true;
				    }
				}
			    }
			}
			return false;
		    }

		    public void action() {
			menuDisplay = true;
			/* on active le GlassPane pour l'affichage du menu */
			GraphicEditorAdaptator.getMainFrame().getGlassPane()
				.setVisible(true);
			/* on relaie le clic gauche pour que la CStateMachine */
			fireEvent(new MouseEvent(getComponent(),
				MouseEvent.MOUSE_PRESSED, 1,
				MouseEvent.BUTTON1_MASK, (int) getPoint()
					.getX(), (int) getPoint().getY(), 1,
				false));
		    }
		};

		/*
		 * on entre sur le panel du JGraph, on passe dans le mode o� le
		 * menu peut-�tre activ�
		 */
		Transition enterJGraph = new EnterOnComponent(">> initialState") {
		    public boolean guard() {
			return ((getComponent() == KMADEMainFrame
				.getProjectPanel().getTaskModelPanel()
				.getJGraph()));
		    }

		    public void action() {
			onJGraph = true;
		    }
		};

		/*
		 * on sort du panel du JGraph, on passe dans le mode o� le menu
		 * ne peut pas �tre activ�
		 */
		Transition leaveJGraph = new EnterOnComponent(">> initialState") {
		    public boolean guard() {
			return ((getComponent() != KMADEMainFrame
				.getProjectPanel().getTaskModelPanel()
				.getJGraph()));
		    }

		    public void action() {
			onJGraph = false;
		    }
		};
	    };
	};

	/* on attache la machine � la fen�tre */
	smText.attachTo(GraphicEditorAdaptator.getMainFrame());

	/*
	 * machine qui g�re les �v�nements relatifs aux CShape lorsque le
	 * GlassPane est actif
	 */
	interaction = new CStateMachine() {
	    KMADEDefaultGraphCell cell;

	    /* �tat initial : le menu est inactif */
	    public State menuOff = new State() {
		/*
		 * avec un "press" du bouton gauche on va dans l'�tat d'attente
		 * d'un second clic pour activer le menu seulement sur un
		 * double-clic
		 */
		Transition pressToDisplay = new Press(BUTTON1, ">> waitClik") {
		    public boolean guard() {
			return menuDisplay;
		    }

		    public void action() {
			// timer pour le double clic
			armTimer(1000, false);
		    }
		};

	    };

	    public State waitClik = new State() {

		// un second clic gauche affiche le menu
		Transition raz = new Click(BUTTON1, ">> menuOn") {
		    public void action() {
			disarmTimer();
			Point decalageGrab = ((JViewport) KMADEMainFrame
				.getProjectPanel().getTaskModelPanel()
				.getJGraph().getParent().getParent())
				.getViewPosition();
			pointDisplay = getPoint();
			pointDisplay.setLocation(pointDisplay.getX(),
				pointDisplay.getY());
			showMenu(pointDisplay);
			menuDisplay = false;
			// timer d'affichage
			armTimer(10, false);
		    }
		};

		// s'il n'y a pas de second clic, on va dans l'etat du menu
		// inactif
		Transition endDelay = new TimeOut(">> menuOff") {
		    public void action() {
			hideMenu();
			GraphicEditorAdaptator.getMainFrame().getGlassPane()
				.setVisible(false);

		    }
		};

		public void leave() {
		    // menu is interactive but not visible
		    tagWhole.setPickable(true);
		    tagLabels.setPickable(false);
		    // timer d'affichage
		    armTimer(10, false);

		}

	    };

	    /* �tat o� le menu est actif */
	    public State menuOn = new State() {
		/* on choisit un item en cliquant dessus avec le bouton gauche */
		Transition select = new Release(BUTTON1, ">> menuOff") {
		    public void action() {
			disarmTimer();
			if (lastItemVisited != null) {

			    /* on execute l'action associ�e � l'item s�lection� */
			    if (lastItemVisited
				    .equals(KMADEConstant.UNKNOWN_TASK_MESSAGE)) {
				ExpressTask.setExecutingUserTask(
					cell.getTask(), Executant.INCONNU);

			    } else if (lastItemVisited
				    .equals(KMADEConstant.USER_TASK_MESSAGE)) {
				ExpressTask.setExecutingUserTask(
					cell.getTask(), Executant.USER);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.ABSTRACT_TASK_MESSAGE)) {
				ExpressTask.setExecutingUserTask(
					cell.getTask(), Executant.ABS);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.INTERACTION_TASK_MESSAGE)) {
				ExpressTask.setExecutingUserTask(
					cell.getTask(), Executant.INT);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.FEEDBACK_TASK_MESSAGE)) {
				ExpressTask.setExecutingUserTask(
					cell.getTask(), Executant.SYS);
			    }

			    KMADEMainFrame.getProjectPanel()
				    .getTaskModelPanel().getJGraph().repaint();
			    lastItemVisited = null;
			}
		    }
		};

		/* avec un clic droit, on d�selectionne le dernier item parcouru */
		Transition quit = new Release(BUTTON3, ">> menuOff") {
		    public void action() {
			lastItemVisited = null;
		    }
		};

		/* on change d'item */
		Transition changeItem = new EnterOnTag(MenuItem.class) {
		    public void action() {
			canvas.removeShape(tooltipText);
			pointToolTip = getPoint();
			lastItemVisited = ((MenuItem) getTag()).getName();
			selection = KMADEMainFrame.getProjectPanel()
				.getTaskModelPanel().getJGraph()
				.getSelectionCells();
			if (selection != null) {
			    if (selection.length == 1) {
				cell = (KMADEDefaultGraphCell) selection[0];
			    }
			}
			// timer pour l'affichage
			armTimer(10, false);
		    }
		};

		/* on est situ� au centre du menu */
		Transition cancel = new EnterOnShape() {
		    public void action() {
			canvas.removeShape(tooltipText);
			lastItemVisited = null;
		    }
		};

		/*
		 * on affiche le menu ou un tooltip selon le mode dans lequel on
		 * est
		 */
		Transition showMenu = new TimeOut() {
		    public void action() {
			if (tooltip) {
			    if (lastItemVisited != null) {
				/*
				 * calcul de la position pour l'afficher �
				 * l'ext�rieur du menu
				 */
				Point2D pointDisplayToolTip = new Point2D.Double(
					pointToolTip.getX() + 10,
					pointToolTip.getY() - 10);

				for (int i = 0; i < items.length; i++) {
				    if (items[i].equals(lastItemVisited)) {
					if (Math.cos((i * angleStep)
						+ angleStep / 2) < 0
						&& Math.sin((i * angleStep)
							+ angleStep / 2) < 0.5
						&& Math.sin((i * angleStep)
							+ angleStep / 2) > -0.5) {
					    pointDisplayToolTip = new Point2D.Double(
						    pointDisplay.getX()
							    + Math.cos((i * angleStep)
								    + angleStep
								    / 2)
							    * rayonExt
							    * 1.2
							    - lastItemVisited.length()
							    * 10,
						    pointDisplay.getY()
							    + Math.sin((i * angleStep)
								    + angleStep
								    / 2)
							    * rayonExt * 1.5);
					} else {
					    pointDisplayToolTip = new Point2D.Double(
						    pointDisplay.getX()
							    + Math.cos((i * angleStep)
								    + angleStep
								    / 2)
							    * rayonExt * 1.2,
						    pointDisplay.getY()
							    + Math.sin((i * angleStep)
								    + angleStep
								    / 2)
							    * rayonExt * 1.5);
					}
				    }
				}
				tooltipText = new CText(pointDisplayToolTip,
					lastItemVisited, FONT);
				canvas.addShape(tooltipText);
			    }
			} else {
			    /* on affiche le menu */
			    disarmTimer();
			    tooltip = true;
			    tagWhole.setDrawable(true);
			    tagLabels.setTransparencyFill(1f);
			}

		    }
		};

		/* on quitte un item */
		Transition quitMenu = new LeaveOnTag(MenuItem.class) {
		    public void action() {
			lastItemVisited = null;
		    }

		};

		/* on sort de l'�tat o� le menu est actif */
		public void leave() {
		    hideMenu();
		    canvas.removeShape(tooltipText);
		    GraphicEditorAdaptator.getMainFrame().getGlassPane()
			    .setVisible(false);
		    tooltip = false;
		    menuDisplay = false;
		}
	    };
	};
	/* on attache la CStateMachine � la fen�tre principale */
	interaction.attachTo(canvas);
	/* la CStateMachine �coute les �v�nements provenant de la JStateMachine */
	smText.addStateMachineListener(interaction);
	/* on attache la machine g�rant la surbrillance au canvas */
	Menu.getHiliteMachine().attachTo(canvas);

    }

    @Override
    void showMenu(Point2D pt) {
	isVisible = true;
	parent.translateTo(pt.getX(), pt.getY()).setDrawable(true);
	tagWhole.setPickable(true);
	tagLabels.setPickable(false);
	tagWhole.aboveAll();
	tagLabels.aboveAll();
    }

    /* d�fini les param�tres graphiques du menu */
    void menuLayout(String[] items) {
	parent = canvas.newEllipse(Menu.centerPointCentre,
		Menu.centerPointCentre, Menu.taillePointCentre,
		Menu.taillePointCentre);

	int rayonInt = (int) (rayonExt * 0.6);

	CShape clipCircle = (new CEllipse(-rayonExt, -rayonExt, 2 * rayonExt,
		2 * rayonExt)).getSubtraction(new CEllipse(-rayonInt,
		-rayonInt, 2 * rayonInt, 2 * rayonInt));
	clipCircle.setFilled(false).setOutlined(false);
	clipCircle.addTag(tagWhole).setParent(parent);

	CPolyLine bgItem;
	CImage labelItem;

	angleStep = 2 * Math.PI / items.length;
	for (int i = 0; i < items.length; i++) {
	    /* background de l'item */
	    bgItem = canvas.newPolyLine(0, 0).lineTo(rayonExt, 0)
		    .arcTo(0, -angleStep, rayonExt, rayonExt).close();
	    bgItem.addTag(tagWhole).addTag(new MenuItem(items[i]))
		    .setReferencePoint(0, 0).rotateBy(i * angleStep)
		    .setTransparencyFill(0.8f).setFillPaint(BG_COLOR)
		    .setOutlinePaint(BORDER_COLOR).setClip(clipCircle);

	    if (items[i].equals(KMADEConstant.USER_TASK_MESSAGE)) {
		/* image de l'item */
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuEditExecutant.class
					.getResource(KMADEConstant.USER_TASK_IMAGE))
			.setTransparencyOutline(0)
			.setPickable(false)
			.addTag(tagWhole)
			.addTag(tagLabels)
			.setReferencePoint(0.5, 0.5)
			.translateTo(
				Math.cos((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8),
				Math.sin((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8));

		parent.addChild(labelItem).addChild(bgItem);
	    }
	    if (items[i].equals(KMADEConstant.UNKNOWN_TASK_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuEditExecutant.class
					.getResource(KMADEConstant.UNKNOWN_TASK_IMAGE))
			.setTransparencyOutline(0)
			.setPickable(false)
			.addTag(tagWhole)
			.addTag(tagLabels)
			.setReferencePoint(0.5, 0.5)
			.translateTo(
				Math.cos((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8),
				Math.sin((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8));

		parent.addChild(labelItem).addChild(bgItem);
	    }
	    if (items[i].equals(KMADEConstant.FEEDBACK_TASK_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuEditExecutant.class
					.getResource(KMADEConstant.FEEDBACK_TASK_IMAGE))
			.setTransparencyOutline(0)
			.setPickable(false)
			.addTag(tagWhole)
			.addTag(tagLabels)
			.setReferencePoint(0.5, 0.5)
			.translateTo(
				Math.cos((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8),
				Math.sin((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8));

		parent.addChild(labelItem).addChild(bgItem);
	    }
	    if (items[i].equals(KMADEConstant.INTERACTION_TASK_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuEditExecutant.class
					.getResource(KMADEConstant.INTERACTIF_TASK_IMAGE))
			.setTransparencyOutline(0)
			.setPickable(false)
			.addTag(tagWhole)
			.addTag(tagLabels)
			.setReferencePoint(0.5, 0.5)
			.translateTo(
				Math.cos((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8),
				Math.sin((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8));

		parent.addChild(labelItem).addChild(bgItem);
	    }
	    if (items[i].equals(KMADEConstant.ABSTRACT_TASK_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuEditExecutant.class
					.getResource(KMADEConstant.ABSTRACT_TASK_IMAGE))
			.setTransparencyOutline(0)
			.setPickable(false)
			.addTag(tagWhole)
			.addTag(tagLabels)
			.setReferencePoint(0.5, 0.5)
			.translateTo(
				Math.cos((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8),
				Math.sin((i * angleStep) + angleStep / 2)
					* (rayonExt * 0.8));

		parent.addChild(labelItem).addChild(bgItem);
	    }
	}

	parent.addTag(tagWhole);
	tagLabels.aboveAll();
	parent.aboveAll();
    }
}

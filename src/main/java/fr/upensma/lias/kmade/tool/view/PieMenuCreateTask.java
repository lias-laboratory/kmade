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
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JTabbedPane;

import org.jgraph.graph.CellView;

import fr.lri.swingstates.applets.MenuItem;
import fr.lri.swingstates.canvas.CEllipse;
import fr.lri.swingstates.canvas.CImage;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.ConcreteObjectPanelAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.KMADeAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesAdaptator;

/**
 * @author Mickael BARON
 */
public class PieMenuCreateTask extends Menu {
    private final Canvas canvas;

    private CText textToolTip;

    private Point2D pointToolTip;

    private boolean tooltip = false;

    private CImage coller;

    private String[] items = { KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE,
	    KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE,
	    KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE,
	    KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE,
	    KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE,
	    KMADEConstant.PASTE_ACTION_MESSAGE };

    private int rayonExt = 100;

    private double angleStep;

    public PieMenuCreateTask(Canvas c) {
	super(c, new Color(182, 206, 241), new Color(255, 255, 0));
	this.canvas = c;
	menuLayout(items);
	hideMenu();

	/* machine qui g�re les �v�nements relatifs aux JComponent */
	smText = new JStateMachine() {

	    public State initialState = new State() {
		/*
		 * on entre sur le panel du JGraph, on passe dans le mode o� le
		 * menu peut-�tre activ�
		 */
		Transition enterJGraph = new EnterOnComponent(">> initialState") {
		    public boolean guard() {
			return ((getComponent() == KMADEMainFrame
				.getProjectPanel().getTaskModelPanel()
				.getJGraph()) && KMADEMainFrame
				.getProjectPanel().isTaskEditorVisible());
		    }

		    public void action() {
			onJGraph = true;
		    }
		};

		/* on "press" le bouton de droite pour l'affichage du menu */
		Transition pressOnJGraph = new PressOnComponent(BUTTON3,
			">> initialState") {
		    public boolean guard() {
			selection = KMADEMainFrame.getProjectPanel()
				.getTaskModelPanel().getJGraph()
				.getSelectionCells();

			/* on v�rifie que l'on n'est pas au-dessus d'une t�che */
			CellView[] allViews = KMADEMainFrame.getProjectPanel()
				.getTaskModelPanel().getJGraph()
				.getGraphLayoutCache().getAllViews();
			boolean onTask = false;
			for (int i = 0; i < allViews.length; i++) {
			    if (allViews[i] instanceof KMADEVertexView) {
				Rectangle2D rectTask = ((KMADEVertexView) allViews[i])
					.getBounds();
				Double scale = KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel().getJGraph()
					.getScale();

				rectTask.setRect(rectTask.getX() * scale,
					rectTask.getY() * scale,
					rectTask.getWidth() * scale,
					rectTask.getHeight() * scale);
				if (onTask = rectTask.contains(getPoint())) {
				    break;
				}
			    }
			}
			return (getComponent() == KMADEMainFrame
				.getProjectPanel().getTaskModelPanel()
				.getJGraph())
				&& selection != null
				&& selection.length == 0
				&& !onTask;
		    }

		    public void action() {
			/* on active le GlassPane pour l'affichage du menu */
			GraphicEditorAdaptator.getMainFrame().getGlassPane()
				.setVisible(true);
			menuDisplay = true;
			/* on relaie le clic droit pour que la CStateMachine */
			fireEvent(new MouseEvent(getComponent(),
				MouseEvent.MOUSE_PRESSED, 1,
				MouseEvent.BUTTON3_MASK, (int) getPoint()
					.getX(), (int) getPoint().getY(), 1,
				false));
		    }
		};

		/*
		 * on fait un clic gauche sur les onglets et on affiche le panel
		 * correspondant
		 */
		Transition pressOnTabpane = new PressOnComponent(BUTTON1,
			">> initialState") {
		    public boolean guard() {
			return getComponent().getParent().getParent()
				.getClass().equals(JTabbedPane.class)
				&& getPoint().getX() < 20
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel().getMenuTask()
					.isVisible()
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel()
					.getMenuEditExecutant().isVisible()
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel()
					.getMenuEditDecomposition().isVisible()
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel().getMenuEdition()
					.isVisible();
		    }

		    public void action() {
			KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				.setSelectedIndex(0);

			int firstSeparation = (int) KMADEMainFrame
				.getProjectPanel().getTabpaneProjet()
				.getBoundsAt(1).getY();
			int secondSeparation = (int) KMADEMainFrame
				.getProjectPanel().getTabpaneProjet()
				.getBoundsAt(2).getY();
			int thirdSeparation = (int) KMADEMainFrame
				.getProjectPanel().getTabpaneProjet()
				.getBoundsAt(3).getY();
			int fourthSeparation = (int) KMADEMainFrame
				.getProjectPanel().getTabpaneProjet()
				.getBoundsAt(4).getY();
			int fifthSeparation = (int) KMADEMainFrame
				.getProjectPanel().getTabpaneProjet()
				.getBoundsAt(5).getY();
			int end = fifthSeparation
				+ (int) KMADEMainFrame.getProjectPanel()
					.getTabpaneProjet().getBoundsAt(5)
					.getHeight();
			int pointY = (int) getPoint().getY();

			if (pointY > firstSeparation
				&& pointY < secondSeparation + 1) {
			    KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				    .setSelectedIndex(1);
			}
			if (pointY > secondSeparation
				&& pointY < thirdSeparation + 1) {
			    KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				    .setSelectedIndex(2);
			}
			if (pointY > thirdSeparation
				&& pointY < fourthSeparation + 1) {
			    KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				    .setSelectedIndex(3);
			}
			if (pointY > fourthSeparation
				&& pointY < fifthSeparation + 1) {
			    KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				    .setSelectedIndex(4);
			}
			if (pointY > fifthSeparation && pointY < end) {
			    KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				    .setSelectedIndex(5);
			}

			if (KMADEMainFrame.getProjectPanel().getTabpaneProjet()
				.getSelectedIndex() == 0) {
			    if (GraphicEditorAdaptator.getSelectedGraphicTask() == null) {
			    } else {
				TaskPropertiesAdaptator
					.updateTaskPropertiesPanel();
			    }
			} else if (KMADEMainFrame.getProjectPanel()
				.getTabpaneProjet().getSelectedIndex() == 2) {
			    ConcreteObjectPanelAdaptator
				    .updateConcreteObjectView();
			}
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

	    /* �tat initial : le menu est inactif */
	    public State menuOff = new State() {

		/*
		 * un "press" du bouton droit affiche le menu, ici ce "press"
		 * est g�n�r� par la JStateMachine
		 */
		Transition pressToDisplay = new Press(BUTTON3, ">> menuOn") {
		    public boolean guard() {
			selection = KMADEMainFrame.getProjectPanel()
				.getTaskModelPanel().getJGraph()
				.getSelectionCells();
			return onJGraph && selection != null
				&& selection.length == 0 && menuDisplay;
		    }

		    public void action() {

			// on r�cup�re l'information concernant la position de
			// la souris � l'�cran pour calculer le d�calage via
			// l'information r�� via get point
			PointerInfo pointer = MouseInfo.getPointerInfo();
			Point locationOnScreen = pointer.getLocation();
			Point locationWindows = GraphicEditorAdaptator
				.getMainFrame().getLocationOnScreen();
			Point location = new Point(
				(int) (locationOnScreen.getX() - locationWindows
					.getX()), (int) (locationOnScreen
					.getY() - locationWindows.getY()));
			pointDisplay = getPoint();
			gap.setLocation(location.x - getPoint().getX(),
				location.y - getPoint().getY());
			// on oublie pas de prendre en compte la taille du point
			pointDisplay.setLocation(
				pointDisplay.getX() + gap.getX()
					- (taillePointCentre / 2),
				pointDisplay.getY() + gap.getY()
					- (taillePointCentre / 2));
			showMenu(pointDisplay);
			menuDisplay = false;

		    }
		};

		public void leave() {
		    // menu is interactive but not visible
		    tagWhole.setPickable(true);
		    tagLabels.setPickable(false);
		    // timer d'affichage
		    armTimer(10, false);

		    Tache[] newTasks = ExpressTask.getAllTaskFromExpress();
		    /*
		     * ExpressTask .pasteElementsFromClipBoard((Point)
		     * pointDisplay);
		     */
		    Object tagWholeIterator = null;
		    Object precedent = null;

		    /*
		     * on affiche l'icone de l'action "coller" seulement si il y
		     * a une t�che � coller
		     */
		    for (tagWhole.reset(); tagWhole.hasNext();) {
			if (tagWholeIterator != null) {
			    precedent = tagWholeIterator;
			}
			tagWholeIterator = tagWhole.next();
			if (tagWholeIterator.getClass().equals(CImage.class)) {
			    CImage textTag = (CImage) tagWholeIterator;

			    if (textTag.equals(coller)) {
				CPolyLine formTag = (CPolyLine) precedent;
				if (newTasks.length == 0) {
				    textTag.setPickable(false);
				    textTag.setTransparencyFill(0.4f);

				    formTag.setPickable(false);
				    formTag.setTransparencyFill(0.4f);

				} else {
				    formTag.setTransparencyFill(0.8f);
				    textTag.setFilled(true);

				}
			    }
			}

		    }

		}
	    };

	    /* �tat o� le menu est actif */
	    public State menuOn = new State() {

		/* on choisit un item en cliquant dessus avec le bouton gauche */
		Transition select = new Release(BUTTON1, ">> menuOff") {
		    public void action() {
			disarmTimer();
			if (lastItemVisited != null) {
			    Point p = (Point) pointDisplay;
			    p.x -= gap.getX();
			    p.y -= gap.getY();

			    /* on execute l'action associ�e � l'item s�lection� */
			    if (lastItemVisited
				    .equals(KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE)) {
				GraphicEditorAdaptator.addNewUnknownTask(p);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE)) {
				GraphicEditorAdaptator.addNewUserTask(p);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE)) {
				GraphicEditorAdaptator.addNewAbstractTask(p);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE)) {
				GraphicEditorAdaptator.addNewInteractionTask(p);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE)) {
				GraphicEditorAdaptator.addNewFeedbackTask(p);
			    } else if (lastItemVisited
				    .equals(KMADEConstant.PASTE_ACTION_MESSAGE)) {
				KMADeAdaptator.pasteAction();
			    }
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
			lastItemVisited = ((MenuItem) getTag()).getName();
			canvas.removeShape(textToolTip);
			pointToolTip = getPoint();

			if (lastItemVisited
				.equals(KMADEConstant.PASTE_ACTION_MESSAGE)) {
			    Tache[] newTasks = ExpressTask
				    .getAllTaskFromExpress();
			    // .pasteElementsFromClipBoard((Point)
			    // pointDisplay);
			    if (newTasks.length == 0) {
				((MenuItem) getTag()).setPickable(false);
			    }
			}
			// timer pour l'affichage de la cr�ation du menu
			armTimer(10, false);
		    }
		};

		/* on est situ� au centre du menu */
		Transition cancel = new EnterOnShape() {
		    public void action() {
			lastItemVisited = null;
			canvas.removeShape(textToolTip);
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
				Point2D p = new Point2D.Double(
					pointToolTip.getX() + 10,
					pointToolTip.getY() - 10);

				for (int i = 0; i < items.length; i++) {

				    if (items[i].equals(lastItemVisited)) {
					if (Math.cos((i * angleStep)
						+ angleStep / 2) < 0
						&& Math.sin((i * angleStep)
							+ angleStep / 2) < 0.6
						&& Math.sin((i * angleStep)
							+ angleStep / 2) > -0.6) {
					    p = new Point2D.Double(
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
					    p = new Point2D.Double(
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
				textToolTip = new CText(p, lastItemVisited,
					FONT);
				canvas.addShape(textToolTip);
			    }
			} else {
			    /* on affiche le menu */
			    tooltip = true;
			    tagWhole.setDrawable(true);
			    disarmTimer();
			    Tache[] newTasks = ExpressTask
				    .getAllTaskFromExpress();
			    // .pasteElementsFromClipBoard((Point)
			    // pointDisplay);
			    for (tagLabels.reset(); tagLabels.hasNext();) {
				CImage tagImage = (CImage) tagLabels.next();

				if (tagImage.equals(coller)) {
				    if (newTasks.length == 0) {
					tagImage.setPickable(false);
					tagImage.setTransparencyFill(0.4f);

				    } else {
					tagImage.setTransparencyFill(1f);
				    }
				} else {
				    tagImage.setTransparencyFill(1f);
				}
			    }
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
		    canvas.removeShape(textToolTip);
		    GraphicEditorAdaptator.getMainFrame().getGlassPane()
			    .setVisible(false);
		    tooltip = false;
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
	// an ellipse, specified by its bounding box(upper left corner, width
	// and height)
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

	    if (items[i].equals(KMADEConstant.NEW_USER_TASK_ACTION_MESSAGE)) {
		/* image de l'item */
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuCreateTask.class
					.getResource(KMADEConstant.USER_TASK_48_IMAGE))
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
	    if (items[i].equals(KMADEConstant.NEW_UNKNOWN_TASK_ACTION_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuCreateTask.class
					.getResource(KMADEConstant.UNKNOWN_TASK_48_IMAGE))
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
	    if (items[i].equals(KMADEConstant.NEW_FEEDBACK_TASK_ACTION_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuCreateTask.class
					.getResource(KMADEConstant.FEEDBACK_TASK_48_IMAGE))
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
	    if (items[i]
		    .equals(KMADEConstant.NEW_INTERACTION_TASK_ACTION_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuCreateTask.class
					.getResource(KMADEConstant.INTERACTIF_TASK_48_IMAGE))
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
	    if (items[i].equals(KMADEConstant.NEW_ABSTRACT_TASK_ACTION_MESSAGE)) {
		labelItem = (CImage) canvas
			.newImage(
				0,
				0,
				PieMenuCreateTask.class
					.getResource(KMADEConstant.ABSTRACT_TASK_48_IMAGE))
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
	    if (items[i].equals(KMADEConstant.PASTE_ACTION_MESSAGE)) {
		coller = (CImage) canvas.newImage(0, 0, PieMenuCreateTask.class
			.getResource(KMADEConstant.PASTE_IMAGE_32));
		labelItem = (CImage) coller
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

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

import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexView;

import fr.lri.swingstates.applets.MenuItem;
import fr.lri.swingstates.canvas.CEllipse;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultEdge;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraph;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.KMADeAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.TaskPropertiesEnhancedEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class PieMenuEdition extends Menu {
    private final Canvas canvas;

    private boolean selectionMultiple = false;

    public PieMenuEdition(Canvas c) {
	super(c, new Color(182, 206, 241), new Color(255, 255, 0));
	this.canvas = c;
	String[] items = {
		KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU,
		KMADEConstant.COPY_ACTION_MESSAGE,
		KMADEConstant.PASTE_ACTION_MESSAGE,
		KMADEConstant.CUT_ACTION_MESSAGE,
		KMADEConstant.DELETE_CELL_ACTION_MESSAGE_PIE_MENU };
	menuLayout(items);
	hideMenu();

	/* machine qui g�re les �v�nements relatifs aux JComponent */
	smText = new JStateMachine() {

	    public State initialState = new State() {
		/*
		 * on "press" le bouton de droite sur la tache pour afficher le
		 * menu
		 */
		Transition pressOnJGraph = new PressOnComponent(BUTTON3,
			">> initialState") {
		    public boolean guard() {
			if (onJGraph) {
			    selection = KMADEMainFrame.getProjectPanel()
				    .getTaskModelPanel().getJGraph()
				    .getSelectionCells();
			    if (selection != null) {
				if (selection.length == 1) {
				    selectionMultiple = false;

				    GraphicEditorAdaptator.getMainFrame();
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
					     * on v�rifie que l'on est sur la
					     * t�che
					     */
					    Point2D pointMinRectTask = new Point2D.Double(
						    scale
							    * ((VertexView) allViews[i])
								    .getBounds()
								    .getX(),
						    scale
							    * ((VertexView) allViews[i])
								    .getBounds()
								    .getY());
					    Point2D pointMaxRectTask = new Point2D.Double(
						    pointMinRectTask.getX()
							    + ((KMADEVertexView) allViews[i])
								    .getBounds()
								    .getWidth()
							    * scale,
						    pointMinRectTask.getY()
							    + ((KMADEVertexView) allViews[i])
								    .getBounds()
								    .getHeight()
							    * scale);

					    Point2D currentPosition = getPoint();

					    if (currentPosition.getX() > pointMinRectTask
						    .getX()
						    && currentPosition.getX() < pointMaxRectTask
							    .getX()
						    && currentPosition.getY() > pointMinRectTask
							    .getY()
						    && currentPosition.getY() < pointMaxRectTask
							    .getY()) {
						return true;
					    }

					}

				    }
				} else if (selection.length > 1) {
				    selectionMultiple = true;
				    return true;
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
			/* on relaie le clic droit pour que la CStateMachine */
			fireEvent(new MouseEvent(getComponent(),
				MouseEvent.MOUSE_PRESSED, 1,
				MouseEvent.BUTTON3_MASK, (int) getPoint()
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
	    /* �tat initial : le menu est inactif */
	    public State menuOff = new State() {
		/*
		 * un "press" du bouton droit affiche le menu, ici ce "press"
		 * est g�n�r� par la JStateMachine
		 */
		Transition pressToDisplay = new Press(BUTTON3, ">> menuOn") {
		    public boolean guard() {
			return menuDisplay
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel()
					.getMenuEditExecutant().isVisible()
				&& !KMADEMainFrame.getProjectPanel()
					.getTaskModelPanel()
					.getMenuEditDecomposition().isVisible();
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

		    Tache[] newTasks = ExpressTask.getAllTaskFromExpress();
		    // .pasteElementsFromClipBoard((Point) pointDisplay);
		    Object tagWholeIterator = null;
		    Object precedentTag = null;

		    /*
		     * on affiche l'item d'"�dition compl�te" seulement si on
		     * n'a pas de s�lection multiple et l'item "coller"
		     * seulement si on a des t�ches copi�es ou coup�es
		     */
		    for (tagWhole.reset(); tagWhole.hasNext();) {
			if (tagWholeIterator != null) {
			    precedentTag = tagWholeIterator;
			}
			tagWholeIterator = tagWhole.next();

			if (tagWholeIterator.getClass().equals(CText.class)) {
			    CText textTag = (CText) tagWholeIterator;
			    if (textTag
				    .getText()
				    .equals(KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU)) {
				CPolyLine formTag = (CPolyLine) precedentTag;
				if (selectionMultiple) {
				    textTag.setPickable(false);
				    textTag.setTransparencyFill(0.4f);
				    formTag.setPickable(false);
				    formTag.setTransparencyFill(0.4f);
				} else {
				    formTag.setTransparencyFill(0.8f);
				    textTag.setFilled(true);
				}
			    }

			    if (textTag.getText().equals(
				    KMADEConstant.PASTE_ACTION_MESSAGE)) {
				CPolyLine formTag = (CPolyLine) precedentTag;
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

			    /*
			     * on res�lectionne la tache s�lectionn�e avant que
			     * le clic gauche ne l'enl�ve
			     */
			    if (selection != null) {
				GraphicEditorAdaptator.getMainFrame();
				CellView[] allViews = KMADEMainFrame
					.getProjectPanel().getTaskModelPanel()
					.getJGraph().getGraphLayoutCache()
					.getAllViews();

				for (int i = 0; i < selection.length; i++) {

				    for (int j = 0; j < allViews.length; j++) {
					if (!selection[0].getClass().equals(
						KMADEDefaultEdge.class)
						&& allViews[j].getCell()
							.equals(selection[i])) {
					    KMADEGraph g = KMADEMainFrame
						    .getProjectPanel()
						    .getTaskModelPanel()
						    .getJGraph();
					    g.addSelectionCell(allViews[j]
						    .getCell());

					}
				    }
				}

			    }

			    /* on execute l'action associ�e � l'item s�lection� */
			    if (lastItemVisited
				    .equals(KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU)) {
				TaskPropertiesEnhancedEditorAdaptator
					.showNMDAEnhancedTaskEditor();
			    } else if (lastItemVisited
				    .equals(KMADEConstant.COPY_ACTION_MESSAGE)) {
				KMADeAdaptator.copyAction();
			    } else if (lastItemVisited
				    .equals(KMADEConstant.PASTE_ACTION_MESSAGE)) {
				KMADeAdaptator.pasteAction();
			    } else if (lastItemVisited
				    .equals(KMADEConstant.CUT_ACTION_MESSAGE)) {
				KMADeAdaptator.cutAction();
			    } else if (lastItemVisited
				    .equals(KMADEConstant.DELETE_CELL_ACTION_MESSAGE_PIE_MENU)) {
				KMADeAdaptator.deleteAction();
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
			if (selectionMultiple) {
			    if (((MenuItem) getTag())
				    .getName()
				    .equals(KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU)) {
				((MenuItem) getTag()).setPickable(false);
				lastItemVisited = null;
			    } else {
				lastItemVisited = ((MenuItem) getTag())
					.getName();
			    }
			} else {
			    lastItemVisited = ((MenuItem) getTag()).getName();
			}

			selection = KMADEMainFrame.getProjectPanel()
				.getTaskModelPanel().getJGraph()
				.getSelectionCells();

		    }
		};

		/* on est situ� au centre du menu */
		Transition cancel = new EnterOnShape() {
		    public void action() {
			lastItemVisited = null;
		    }
		};

		/*
		 * on affiche le menu
		 */
		Transition showMenu = new TimeOut() {
		    public void action() {
			tagWhole.setDrawable(true);

			for (tagLabels.reset(); tagLabels.hasNext();) {
			    CText textTag = (CText) tagLabels.next();

			    if (textTag
				    .getText()
				    .equals(KMADEConstant.COMPLETE_EDIT_TASK_ACTION_MESSAGE_PIE_MENU)) {
				if (selectionMultiple) {
				    textTag.setPickable(false);
				    textTag.setTransparencyFill(0.4f);

				} else {
				    textTag.setTransparencyFill(1f);
				}
			    } else {
				textTag.setTransparencyFill(1f);
			    }

			    if (textTag.getText().equals(
				    KMADEConstant.PASTE_ACTION_MESSAGE)) {
				Tache[] newTasks = ExpressTask
					.getAllTaskFromExpress();
				// .pasteElementsFromClipBoard((Point)
				// pointDisplay);
				if (newTasks.length == 0) {
				    textTag.setPickable(false);
				    textTag.setTransparencyFill(0.4f);

				} else {
				    textTag.setTransparencyFill(1f);
				}
			    } else {
				textTag.setTransparencyFill(1f);
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
		    GraphicEditorAdaptator.getMainFrame().getGlassPane()
			    .setVisible(false);
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

	int rayonExt = 100;
	int rayonInt = (int) (rayonExt * 0.6);

	CShape clipCircle = (new CEllipse(-rayonExt, -rayonExt, 2 * rayonExt,
		2 * rayonExt)).getSubtraction(new CEllipse(-rayonInt,
		-rayonInt, 2 * rayonInt, 2 * rayonInt));
	clipCircle.setFilled(false).setOutlined(false);
	clipCircle.addTag(tagWhole).setParent(parent);

	CPolyLine bgItem;
	CText labelItem;

	double angleStep = 2 * Math.PI / items.length;
	for (int i = 0; i < items.length; i++) {
	    /* background de l'item */
	    bgItem = canvas.newPolyLine(0, 0).lineTo(rayonExt, 0)
		    .arcTo(0, -angleStep, rayonExt, rayonExt).close();
	    bgItem.addTag(tagWhole).addTag(new MenuItem(items[i]))
		    .setReferencePoint(0, 0).rotateBy(i * angleStep)
		    .setTransparencyFill(0.8f).setFillPaint(BG_COLOR)
		    .setOutlinePaint(BORDER_COLOR).setClip(clipCircle);

	    /* le texte de l'item */
	    labelItem = (CText) canvas
		    .newText(0, 0, items[i], Menu.FONT)
		    .setFillPaint(Color.BLUE)
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

	parent.addTag(tagWhole);
	tagLabels.aboveAll();
	parent.aboveAll();
    }
}

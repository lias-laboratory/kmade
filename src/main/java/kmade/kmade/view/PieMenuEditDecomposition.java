package kmade.kmade.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JViewport;

import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.coreadaptator.ExpressTask;
import kmade.kmade.view.taskmodel.KMADEDefaultEdge;
import kmade.kmade.view.taskmodel.KMADEDefaultGraphCell;
import kmade.kmade.view.taskmodel.KMADEGraph;
import kmade.kmade.view.taskmodel.KMADEVertexView;
import kmade.nmda.ExpressConstant;
import kmade.nmda.schema.tache.Decomposition;

import org.jgraph.graph.CellView;

import fr.lri.swingstates.applets.MenuItem;
import fr.lri.swingstates.canvas.CEllipse;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;

/** Pie Menu pour la modification de la d�composition */
public class PieMenuEditDecomposition extends Menu {
	private final Canvas canvas;

	public PieMenuEditDecomposition(Canvas c) {
		super(c, new Color(182, 206, 241), new Color(255, 255, 0));
		this.canvas = c;
		String[] items = { ExpressConstant.SEQUENTIEL_LONG_NAME,
				ExpressConstant.NO_ORDER_LONG_NAME,
				ExpressConstant.PARALLELE_LONG_NAME,
				ExpressConstant.ALTERNATIF_LONG_NAME,
				ExpressConstant.LEAF_LONG_NAME,
				ExpressConstant.UNKNOWN_LONG_NAME };
		menuLayout(items);
		hideMenu();

		/* machine qui g�re les �v�nements relatifs aux JComponent */
		smText = new JStateMachine() {

			public State initialState = new State() {
				/*
				 * on "press" le bouton de gauche sur la decomposition de la
				 * tache pour afficher le menu
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
											/*
											 * on v�rifie que l'on est sur la
											 * d�composition de la t�che
											 */
											Rectangle2D recDecomposition = ((KMADEVertexView) allViews[i])
													.getRectOpe();
											if (recDecomposition
													.contains(getPoint())) {
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
							CellView[] allViews = KMADEMainFrame.getProjectPanel()
									.getTaskModelPanel().getJGraph()
									.getGraphLayoutCache().getAllViews();

							for (int i = 0; i < allViews.length; i++) {
								if (allViews[i] instanceof KMADEVertexView) {
									Rectangle2D recDecomposition = ((KMADEVertexView) allViews[i])
											.getRectOpe();
									if (recDecomposition != null
											&& recDecomposition
													.contains(getPoint())) {
										KMADEGraph g = KMADEMainFrame 
												.getProjectPanel()
												.getTaskModelPanel()
												.getJGraph();
										g.setSelectionCell(allViews[i]
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
						 GraphicEditorAdaptator.getMainFrame().getGlassPane().setVisible(true);
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
						return ((getComponent() == KMADEMainFrame.getProjectPanel()
								.getTaskModelPanel().getJGraph()));
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
						return ((getComponent() != KMADEMainFrame.getProjectPanel()
								.getTaskModelPanel().getJGraph()));
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

				// un second clic gauche
				
				Transition raz = new Click(BUTTON1, ">> menuOn") {
					
					public void action() {
						disarmTimer();
						Point gapGrab = ((JViewport) KMADEMainFrame
								.getProjectPanel().getTaskModelPanel()
								.getJGraph().getParent().getParent())
								.getViewPosition();
						pointDisplay = getPoint();
						pointDisplay
								.setLocation(
										pointDisplay.getX()/* +gap.getX()-gapGrab.getX() */,
										pointDisplay.getY()/* +gap.getY()-gapGrab.getY() */);
						showMenu(pointDisplay);
						menuDisplay = false;
						//timer d'affichage
						armTimer(10, false);
					}
				};

				// s'il n'y a pas de second clic, on va dans l'etat du menu
				// inactif
				Transition endDelay = new TimeOut(">> menuOff") {
					public void action() {
						hideMenu();
						GraphicEditorAdaptator.getMainFrame().getGlassPane().setVisible(false);
					}
				};

				public void leave() {
					// menu is interactive but not visible
					tagWhole.setPickable(true);
					tagLabels.setPickable(false);
					//timer d'affichage
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
							if (lastItemVisited.equals(ExpressConstant.SEQUENTIEL_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.SEQ);
							} else if (lastItemVisited.equals(ExpressConstant.NO_ORDER_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.ET);
							} else if (lastItemVisited.equals(ExpressConstant.PARALLELE_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.PAR);
							} else if (lastItemVisited.equals(ExpressConstant.ALTERNATIF_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.ALT);
							} else if (lastItemVisited.equals(ExpressConstant.LEAF_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.ELE);
							} else if (lastItemVisited.equals(ExpressConstant.UNKNOWN_LONG_NAME)) {
								ExpressTask.setOperatorTask(cell.getTask(),Decomposition.INCONNU);
							}
							KMADEMainFrame.getProjectPanel().getTaskModelPanel()
									.getJGraph().repaint();
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
						selection = KMADEMainFrame.getProjectPanel()
								.getTaskModelPanel().getJGraph()
								.getSelectionCells();
						if (selection != null) {
							if (selection.length == 1) {
								cell = (KMADEDefaultGraphCell) selection[0];
							}
						}
						lastItemVisited = ((MenuItem) getTag()).getName();
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
						tagLabels.setTransparencyFill(1f);
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
					GraphicEditorAdaptator.getMainFrame().getGlassPane().setVisible(false);
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
		parent = canvas.newEllipse(Menu.centerPointCentre,Menu.centerPointCentre, Menu.taillePointCentre, Menu.taillePointCentre);

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
			bgItem = canvas.newPolyLine(0, 0).lineTo(rayonExt, 0).arcTo(0,
					-angleStep, rayonExt, rayonExt).close();
			bgItem.addTag(tagWhole).addTag(new MenuItem(items[i]))
					.setReferencePoint(0, 0).rotateBy(i * angleStep)
					.setTransparencyFill(0.8f).setFillPaint(BG_COLOR)
					.setOutlinePaint(BORDER_COLOR).setClip(clipCircle);

			/* le texte du tooltip */
			labelItem = (CText) canvas.newText(0, 0, items[i], Menu.FONT)
					.setFillPaint(Color.BLUE).setPickable(false).addTag(
							tagWhole).addTag(tagLabels).setReferencePoint(0.5,
							0.5).translateTo(
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

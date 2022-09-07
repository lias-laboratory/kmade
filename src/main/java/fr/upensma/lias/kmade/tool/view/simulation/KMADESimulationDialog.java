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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultGraphSelectionModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.GraphSelectionModel;
import org.jgraph.plaf.basic.BasicGraphUI;

import fr.upensma.lias.kmade.kmad.schema.tache.Task;
import fr.upensma.lias.kmade.kmad.schema.tache.CurrentEvents.CurrentEvent;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.simulation.TokenSimulation;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultEdge;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraph;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEPortView;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEPreviewWindow;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesTable;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessagePanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.view.toolutilities.VerticalTextIcon;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.SimulationAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADESimulationDialog extends JFrame {

	private static final long serialVersionUID = -2108309586455132522L;

	private final CardLayout cardProperties = new CardLayout();

	private JTabbedPane tabpaneProjet;

	private fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraph mySimulationGraph;

	private KMADEHistoryMessagePanel myHMP;

	private JPropertiesTable protertiesTable;

	private JPanel switchEmptyProp;

	private JLabel labelText;

	private TitledBorder myObjetConcretBorder;

	private TitledBorder myEventBorder;

	private TitledBorder myConstraintsBorder;

	private TitledBorder myPropertiesBorder;

	private TitledBorder myUserBorder;

	private KMADEPreviewWindow PREVIEW_WINDOW = new KMADEPreviewWindow(this);

	private KMADESimulationToolBar simulationToolBar = new KMADESimulationToolBar();

	private JComboBox myExecutantCombo;

	private JPanel myPanelUser;

	private final CardLayout cardConstraints = new CardLayout();

	private ConstraintExecuteTaskTableModel refModel;

	private JPanel panelConstraints;

	private KMADERecordingSimulationPanel myRecordingPanel;

	private KMADEReplaySimulationPanel myReplayPanel;

	public final ImageIcon ERROR_USER_IMAGEICON;

	public final ImageIcon UNKNOWN_USER_IMAGEICON;

	private JList myEventList;

	private JLabel myLabelConcreteObject;

	private JPanel myEventPanel;

	private final CardLayout cardEvents = new CardLayout();

	private final CardLayout cardCurrentObject = new CardLayout();

	private JPanel panelConcreteObject;

	public KMADESimulationToolBar getSimulationToolBar() {
		return simulationToolBar;
	}

	public KMADERecordingSimulationPanel getRecordingPanel() {
		return myRecordingPanel;
	}

	public KMADEReplaySimulationPanel getReplayPanel() {
		return myReplayPanel;
	}

	public void updateSimulationGraph() {
		this.mySimulationGraph.repaint();
	}

	public void setValue(String[] executant, String[] precondition, String[] evenement) {
		this.refModel.setValue(executant, precondition, evenement);
	}

	public KMADESimulationDialog() {
		super(KMADEConstant.SIMULATION_TITLE_MESSAGE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mySimulationGraph = new KMADEGraph();
		mySimulationGraph.setSelectionModel(new NMDASimulationGraphSelectionModel(mySimulationGraph));
		mySimulationGraph.setEditable(false);
		mySimulationGraph.setMoveable(false);
		mySimulationGraph.setUI(new MySimulationBasicGraphUI());
		mySimulationGraph.getGraphLayoutCache().setFactory(new DefaultCellViewFactory() {

			private static final long serialVersionUID = -2109112499639730709L;

			public CellView createView(GraphModel arg0, Object arg1) {
				if (arg1 instanceof KMADEDefaultGraphCell) {
					return new KMADEVertexSimulationView(arg1, mySimulationGraph);
				} else if (arg1 instanceof DefaultPort) {
					return new KMADEPortView(arg1);
				} else if (arg1 instanceof DefaultEdge)
					return new KMADEEdgeSimulationView(arg1, mySimulationGraph);
				{
					return super.createView(arg0, arg1);
				}
			}
		});

		JScrollPane myScrollGraphPane = new JScrollPane(mySimulationGraph);

		myHMP = new KMADEHistoryMessagePanel(KMADEConstant.MESSAGES_MESSAGE);

		myRecordingPanel = new KMADERecordingSimulationPanel();
		myReplayPanel = new KMADEReplaySimulationPanel();
		tabpaneProjet = new JTabbedPane(JTabbedPane.RIGHT) {

			private static final long serialVersionUID = -5848735738637864437L;

			public void setSelectedIndex(int index) {
				if (tabpaneProjet.getSelectedIndex() != -1) {
					boolean state = SimulationAdaptator.recordOrReplayMode(tabpaneProjet.getSelectedIndex());
					if (state) {
						super.setSelectedIndex(index);
					}
				} else {
					super.setSelectedIndex(index);
				}
			}

		};
		tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.RECORD_TITLE_MESSAGE, false, myRecordingPanel),
				myRecordingPanel);
		tabpaneProjet.addTab(null, new VerticalTextIcon(KMADEConstant.REPLAY_TITLE_MESSAGE, false, myReplayPanel),
				myReplayPanel);

		JSplitPane myCentralSplitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				myScrollGraphPane, tabpaneProjet);
		myCentralSplitPane.setOneTouchExpandable(true);
		myCentralSplitPane.setDividerLocation(750);
		myCentralSplitPane.setResizeWeight(0.9d);

		// Le JPanel pour les contraintes de déclenchement d'une tâche.
		panelConstraints = new JPanel(cardConstraints);
		refModel = new ConstraintExecuteTaskTableModel();
		JScrollPane myScrollPaneConstraints = new JScrollPane(new JTable(refModel));
		JPanel panelRecordEmpty = new JPanel(new BorderLayout());
		panelRecordEmpty.add(new JLabel(KMADEConstant.NO_SELECTED_ACTION_FOR_CONSTRAINTS_MESSAGE, JLabel.CENTER));
		JPanel panelReplayEmpty = new JPanel(new BorderLayout());
		panelReplayEmpty
				.add(new JLabel(KMADEConstant.NO_SELECTED_SCENARIO_ACTION_FOR_CONSTRAINTS_MESSAGE, JLabel.CENTER));
		panelConstraints.add("RecordEmptyConstraints", panelRecordEmpty);
		panelConstraints.add("ReplayEmptyConstraints", panelReplayEmpty);
		panelConstraints.add("Table", myScrollPaneConstraints);
		this.myConstraintsBorder = new TitledBorder(null, KMADEConstant.CONSTRAINTS_TITLE_MESSAGE, TitledBorder.CENTER,
				TitledBorder.TOP);
		panelConstraints.setPreferredSize(new Dimension(100, 80));
		panelConstraints.setBorder(this.myConstraintsBorder);

		JSplitPane myConstraintsHistory = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelConstraints, myHMP);
		myConstraintsHistory.setDividerLocation(300);
		myConstraintsHistory.setResizeWeight(0.5);

		JSplitPane myMainSplitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT,
				myCentralSplitPane, myConstraintsHistory);
		myMainSplitPane.setOneTouchExpandable(true);

		myMainSplitPane.setDividerLocation(425);
		myMainSplitPane.setResizeWeight(1);

		// Le JPanel du Haut

		// Le JPanel pour gérer les propriétés d'une tâche.
		protertiesTable = new JPropertiesTable(
				KMADEMainFrame.getProjectPanel().getTaskDescriptionPanel().getProprieteTache().getPropertiesModel());
		JPanel cache = new JPanel(new GridLayout(3, 1));
		labelText = new JLabel(KMADEConstant.NO_SELECTED_TASK_MESSAGE);
		labelText.setHorizontalAlignment(JLabel.CENTER);
		cache.add(new JPanel());
		cache.add(labelText);
		cache.add(new JPanel());

		switchEmptyProp = new JPanel();
		switchEmptyProp.setLayout(cardProperties);
		switchEmptyProp.add(protertiesTable, "Prop");
		switchEmptyProp.add(cache, "Empty");

		JPanel panelProperties = new JPanel(new BorderLayout());
		this.myPropertiesBorder = new TitledBorder(null, KMADEConstant.PROPERTIES_MESSAGE, TitledBorder.CENTER,
				TitledBorder.TOP);
		panelProperties.setBorder(this.myPropertiesBorder);
		panelProperties.add(switchEmptyProp);

		// Le JPanel pour l'objet concret courant et les événements engendrés
		panelConcreteObject = new JPanel();
		panelConcreteObject.setLayout(this.cardCurrentObject);
		this.myObjetConcretBorder = new TitledBorder(null, KMADEConstant.EFFETSDEBORD_CONCRETE_OBJECT_TITLE_MESSAGE,
				TitledBorder.CENTER, TitledBorder.TOP);
		panelConcreteObject.setBorder(this.myObjetConcretBorder);
		panelConcreteObject.setPreferredSize(new Dimension(150, 90));

		JPanel panelCurrentObjectEmpty = new JPanel(new BorderLayout());
		panelCurrentObjectEmpty.add(new JLabel(KMADEConstant.NO_CONCRETE_OBJECT, JLabel.CENTER));

		myLabelConcreteObject = new JLabel("", JLabel.CENTER);
		panelConcreteObject.add("EMPTY", panelCurrentObjectEmpty);
		panelConcreteObject.add("CONCRETEOBJECT", myLabelConcreteObject);

		myEventPanel = new JPanel();
		myEventPanel.setLayout(cardEvents);
		this.myEventBorder = new TitledBorder(null, KMADEConstant.EVENT_TITLE_MESSAGE, TitledBorder.CENTER,
				TitledBorder.TOP);
		myEventPanel.setBorder(myEventBorder);
		myEventPanel.setPreferredSize(new Dimension(100, 90));

		JPanel panelEventEmpty = new JPanel(new BorderLayout());
		panelEventEmpty.add(new JLabel(KMADEConstant.NO_FIRING_EVENT_MESSAGE, JLabel.CENTER));

		this.myEventList = new JList(new DefaultListModel());
		this.myEventList.setCellRenderer(new ListCellRenderer() {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				CurrentEvent toto = (CurrentEvent) value;
				return new JLabel("E : " + toto.getFirableEvent() + " , T : " + toto.getFiringTask().getName());
			}
		});
		JScrollPane myEventScrollPane = new JScrollPane(this.myEventList);

		myEventPanel.add("EMPTY", panelEventEmpty);
		myEventPanel.add("EVENT", myEventScrollPane);

		JSplitPane executantEventConcreteObject = KMADEEnhancedSplitPane
				.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelProperties, panelConcreteObject);
		executantEventConcreteObject.setDividerLocation(400);// 289);
		executantEventConcreteObject.setResizeWeight(0.8);

		JSplitPane executantEventConcreteObjectContraints = KMADEEnhancedSplitPane
				.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT, executantEventConcreteObject, myEventPanel);
		executantEventConcreteObjectContraints.setDividerLocation(625);
		executantEventConcreteObjectContraints.setResizeWeight(0.6);

		// Le JPanel pour l'exécutant.
		myPanelUser = new JPanel(new CardLayout());
		JPanel panelCombo = new JPanel(new BorderLayout());
		myExecutantCombo = new JComboBox();
		myExecutantCombo.setRenderer(new MyComboBoxRenderer());
		myExecutantCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				SimulationAdaptator.selectAndGoToTask();
				// if (myRecordingPanel.getActionList().getSelectedIndex() !=
				// -1) {
				// SimulationAdaptator.selectAndGoToTask((TokenSimulation)myRecordingPanel.getActionList().getSelectedValue());
				// }
			}
		});
		panelCombo.add(myExecutantCombo);

		JPanel panelEmpty = new JPanel(new BorderLayout());
		panelEmpty.add(new JLabel(KMADEConstant.NO_USER_MESSAGE, JLabel.CENTER));

		myPanelUser.add("COMBO", panelCombo);
		myPanelUser.add("EMPTY", panelEmpty);

		myPanelUser.setPreferredSize(new Dimension(150, 100));
		this.myUserBorder = new TitledBorder(null, KMADEConstant.EXECUTING_USER_TITLE_MESSAGE, TitledBorder.CENTER,
				TitledBorder.TOP);
		myPanelUser.setBorder(myUserBorder);

		JSplitPane executantEventConcreteObjectContraintsProperties = KMADEEnhancedSplitPane.createStrippedSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, executantEventConcreteObjectContraints, myPanelUser);
		executantEventConcreteObjectContraintsProperties.setDividerLocation(800);
		executantEventConcreteObjectContraintsProperties.setResizeWeight(1);
		// Le JSplitPane du haut et bas.
		JSplitPane upDownSplitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(JSplitPane.VERTICAL_SPLIT,
				executantEventConcreteObjectContraintsProperties, myMainSplitPane);
		upDownSplitPane.setOneTouchExpandable(true);
		upDownSplitPane.setDividerLocation(0.2);
		upDownSplitPane.setResizeWeight(0.12d);
		upDownSplitPane.setDividerLocation(100);

		this.getContentPane().add(BorderLayout.NORTH, simulationToolBar);
		this.getContentPane().add(upDownSplitPane);
		this.pack();
		this.setSize(new Dimension(1000, 700));
		this.setVisible(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SimulationAdaptator.closeSimulationDialog();
			}
		});
		this.setGlassPane(new InDevelopmentGlassPanel("", Color.GRAY));
		KMADEToolUtilities.setCenteredInScreen(this);
		cardProperties.show(switchEmptyProp, "Empty");

		PREVIEW_WINDOW.setOverviewGraph(mySimulationGraph);
		PREVIEW_WINDOW.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				simulationToolBar.setSelectedOverviewWindowToggleButton(false);
			}
		});

		ERROR_USER_IMAGEICON = new ImageIcon(KMADEToolUtilities.getImageThumbnail(
				GraphicEditorAdaptator.class.getResource(KMADEConstant.USER_ERROR_IMAGE), KMADEConstant.ROW_HEIGHT));
		UNKNOWN_USER_IMAGEICON = new ImageIcon(KMADEToolUtilities.getImageThumbnail(
				GraphicEditorAdaptator.class.getResource(KMADEConstant.USER_UNKNOWN_IMAGE), KMADEConstant.ROW_HEIGHT));
		Dimension dim = new Dimension(1800, 1000);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		Insets insets = tk.getScreenInsets(getGraphicsConfiguration());
		int width = (int) (d.getWidth() - insets.left - insets.right);
		int height = (int) (d.getHeight() - insets.top - insets.bottom);
		Dimension screenDim = new Dimension(width, height);

		if (screenDim.height < dim.height) {
			this.setSize(screenDim);
			this.setLocation(insets.left, insets.top);
		} else {
			this.setPreferredSize(dim);
			KMADEToolUtilities.setCenteredInScreen(this);
		}
	}

	public Task getSelectedTask() {
		Object temp = this.mySimulationGraph.getSelectionModel().getSelectionCell();
		if (temp instanceof KMADEDefaultGraphCell) {
			return ((KMADEDefaultGraphCell) temp).getTask();
		} else {
			return null;
		}
	}

	public void setModelToSimulation(DefaultGraphModel myModel) {
		mySimulationGraph.setModel(myModel);
		mySimulationGraph.setScale(0.85d);
		tabpaneProjet.setSelectedIndex(0);
	}

	public void closeSimulationDialog() {
		this.setVisible(false);
	}

	public void enableSimulationDialog() {
		myHMP.setOutputMessage();
	}

	public JGraph getGraphSimulation() {
		return mySimulationGraph;
	}

	public JViewport getJGraphScrollPaneParent() {
		return (JViewport) (mySimulationGraph.getParent());
	}

	/**
	 * Effectue un Zoom plus sur le graph.
	 * 
	 * @param p
	 */
	public void zoomPlus(double p) {
		mySimulationGraph.setScale(mySimulationGraph.getScale() * p);
	}

	/**
	 * Effectue un Zoom moins sur le graph.
	 * 
	 * @param p
	 */
	public void zoomMinus(double p) {
		mySimulationGraph.setScale(mySimulationGraph.getScale() / p);
	}

	/**
	 * Effectue un Zoom d�fault sur le graph.
	 * 
	 * @param p
	 */
	public void zoomDefault(double p) {
		mySimulationGraph.setScale(p);
	}

	public void showCurrentObjectPanel() {
		this.cardCurrentObject.show(panelConcreteObject, "CONCRETEOBJECT");
	}

	public void setCurrentObjectPanel(String value) {
		myLabelConcreteObject.setText(value);
		this.showCurrentObjectPanel();
	}

	public void hideCurrentObjectPanel() {
		this.cardCurrentObject.show(panelConcreteObject, "EMPTY");
	}

	public void showEventsPanel() {
		this.cardEvents.show(myEventPanel, "EVENT");
	}

	public void hideEventsPanel() {
		this.cardEvents.show(myEventPanel, "EMPTY");
		myEventList.setModel(new DefaultComboBoxModel());
	}

	public void setEventListModel(CurrentEvent[] ce) {
		if (ce.length == 0) {
			this.hideEventsPanel();
		} else {
			myEventList.setModel(new DefaultComboBoxModel(ce));
			this.showEventsPanel();
		}
	}

	public void showConstraintsExecute() {
		cardConstraints.show(panelConstraints, "Table");
	}

	public void hideRecordConstraintsExecute() {
		cardConstraints.show(panelConstraints, "RecordEmptyConstraints");
	}

	public void hideReplayConstraintsExecute() {
		cardConstraints.show(panelConstraints, "ReplayEmptyConstraints");
	}

	public void hideTaskProperties() {
		cardProperties.show(switchEmptyProp, "Empty");
	}

	public void showTaskProperties() {
		cardProperties.show(switchEmptyProp, "Prop");
	}

	public int getSelectionUserListCombo() {
		return myExecutantCombo.getSelectedIndex();
	}

	public void setUserListCombo(Object[] p) {
		CardLayout ca = (CardLayout) myPanelUser.getLayout();
		if (p.length == 0) {
			ca.show(myPanelUser, "EMPTY");
		} else {
			// Mettre à jour le JComboBox ...
			ca.show(myPanelUser, "COMBO");
			myExecutantCombo.setModel(new DefaultComboBoxModel(p));
		}
	}

	// Nécessaire pour interagir dessus quand une tâche est indéplaçable.
	static class MySimulationBasicGraphUI extends BasicGraphUI {

		private static final long serialVersionUID = -8614792800620215133L;

		public CellHandle createHandle(GraphContext context) {
			if (context != null && !context.isEmpty() && graph.isEnabled()) {
				try {
					return new MyRootHandle(context);
				} catch (NullPointerException e) {
					// ignore for now...
				}
			}
			return null;
		}

		protected MouseListener createMouseListener() {
			return new MySimulationMouseHandler();
		}

		class MySimulationMouseHandler extends MouseHandler {

			private static final long serialVersionUID = -4476255069335986374L;

			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (cell != null) {
					// Faire réagir ici?
				}
			}
		}

		class MyRootHandle extends RootHandle {

			private static final long serialVersionUID = -5477365196640045745L;

			public MyRootHandle(GraphContext ctx) {
				super(ctx);
			}

			public void mouseDragged(MouseEvent event) {

			}

			public void mousePressed(MouseEvent event) {
				if (!event.isConsumed()) {
					if (handles != null) { // Find Handle
						for (int i = handles.length - 1; i >= 0; i--) {
							if (handles[i] != null) {
								handles[i].mousePressed(event);
								if (event.isConsumed()) {
									activeHandle = handles[i];
									return;
								}
							}
						}
					}
					if (views != null) { // Start Move if over cell
						Point2D screenPoint = event.getPoint();
						Point2D pt = graph.fromScreen((Point2D) screenPoint.clone());
						CellView view = findViewForPoint(pt);
						if (view != null) {
							if (snapSelectedView) {
								Rectangle2D bounds = view.getBounds();
								start = graph.toScreen(new Point2D.Double(bounds.getX(), bounds.getY()));
								snapStart = graph.snap((Point2D) start.clone());
								_mouseToViewDelta_x = screenPoint.getX() - start.getX();
								_mouseToViewDelta_y = screenPoint.getY() - start.getY();
							} else { // this is the original RootHandle's mode.
								snapStart = graph.snap((Point2D) screenPoint.clone());
								_mouseToViewDelta_x = snapStart.getX() - screenPoint.getX();
								_mouseToViewDelta_y = snapStart.getY() - screenPoint.getY();
								start = (Point2D) snapStart.clone();
							}
							last = (Point2D) start.clone();
							snapLast = (Point2D) snapStart.clone();
							isContextVisible = contextViews != null && contextViews.length < MAXCELLS
									&& (!event.isControlDown() || !graph.isCloneable());
							event.consume();
						}
					}
					// Analyze for common parent
					if (graph.isMoveIntoGroups() || graph.isMoveOutOfGroups()) {
						Object[] cells = context.getCells();
						Object ignoreGroup = graph.getModel().getParent(cells[0]);
						for (int i = 1; i < cells.length; i++) {
							Object tmp = graph.getModel().getParent(cells[i]);
							if (ignoreGroup != tmp) {
								ignoreGroup = null;
								break;
							}
						}
						if (ignoreGroup != null)
							ignoreTargetGroup = graph.getGraphLayoutCache().getMapping(ignoreGroup, false);
					}
				}
			}
		}
	}

	class NMDASimulationGraphSelectionModel extends DefaultGraphSelectionModel implements GraphSelectionListener {
		private static final long serialVersionUID = 2208622699738909622L;

		public NMDASimulationGraphSelectionModel(JGraph arg0) {
			super(arg0);
			this.setSelectionMode(GraphSelectionModel.SINGLE_GRAPH_SELECTION);
			this.addGraphSelectionListener(this);
		}

		public void valueChanged(GraphSelectionEvent arg0) {
			boolean enabled = !graph.isSelectionEmpty();
			if (!enabled) {
				SimulationAdaptator.selectNoTask();
			} else {
				if (this.getSelectionCount() == 0) {
					SimulationAdaptator.selectNoTask();
				} else if (this.getSelectionCount() == 1) {
					Object toto = NMDASimulationGraphSelectionModel.this.getSelectionCell();
					if (toto instanceof KMADEDefaultEdge) {
						if (!((KMADEDefaultEdge) toto).isSimulationExpanded()) {
							NMDASimulationGraphSelectionModel.this.clearSelection();
							return;
						}
					} else if (toto instanceof KMADEDefaultGraphCell) {
						if (!((KMADEDefaultGraphCell) toto).isSimulationExpanded()) {
							NMDASimulationGraphSelectionModel.this.clearSelection();
							return;
						}
					}

					int firstIndex = 0;
					boolean find = false;
					for (int i = 0; i < myRecordingPanel.getActionListModel().getSize(); i++) {
						if (!find && ((TokenSimulation) myRecordingPanel.getActionListModel().getElementAt(i)).getTask()
								.getRefJTask() == toto) {
							firstIndex = i;
							find = true;
						}
					}

					if (find) {
						SimulationAdaptator.selectOneTask(toto);
						if (!myRecordingPanel.getActionList().isSelectionEmpty()) {
							if (((TokenSimulation) myRecordingPanel.getActionList().getSelectedValue()).getTask()
									.getRefJTask() == toto) {
								return;
							} else {
								myRecordingPanel.getActionList().getSelectionModel().setSelectionInterval(firstIndex,
										firstIndex);
								return;
							}
						} else {
							myRecordingPanel.getActionList().getSelectionModel().setSelectionInterval(firstIndex,
									firstIndex);
							return;
						}
					}

					myRecordingPanel.getActionList().clearSelection();
					SimulationAdaptator.selectOneTask(toto);
				}
			}
		}
	}

	static class MyComboBoxRenderer extends JLabel implements ListCellRenderer {

		private static final long serialVersionUID = 2114910853277283019L;

		public MyComboBoxRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value != null && value instanceof Object[]) {
				Object[] data = (Object[]) value;

				if (isSelected) {
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(list.getBackground());
					setForeground(list.getForeground());
				}

				this.setIcon((ImageIcon) data[2]);
				this.setText((String) data[0]);
			}
			return this;
		}
	}

	public KMADEPreviewWindow getPreviewWindow() {
		return PREVIEW_WINDOW;
	}

	static class ConstraintExecuteTaskTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 3329710596885090744L;

		private String[] executant = { "", "" };

		private String[] precondition = { "", "" };

		private String[] evenement = { "", "" };

		public void setValue(String[] executant, String[] precondition, String[] evenement) {
			this.evenement = evenement;
			this.executant = executant;
			this.precondition = precondition;
			this.fireTableDataChanged();
		}

		public int getRowCount() {
			return 3;
		}

		public int getColumnCount() {
			return 3;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				switch (rowIndex) {
				case 0:
					return KMADEConstant.UTILISATEUR_LABEL_NAME;
				case 1:
					return KMADEConstant.PRECONDITION_LABEL_VALUE;
				case 2:
					return KMADEConstant.DECLENCHEMENT_LABEL_NAME;
				}
			} else {
				switch (rowIndex) {
				case 0:
					return executant[columnIndex - 1];
				case 1:
					return precondition[columnIndex - 1];
				case 2:
					return evenement[columnIndex - 1];
				}
			}
			return null;
		}

		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return KMADEConstant.CONSTRAINTS_NAME_COLUMN_NAME;
			case 1:
				return KMADEConstant.CONSTRAINTS_VALEUR_COLUMN_NAME;
			case 2:
				return KMADEConstant.CONSTRAINTS_STATE_COLUMN_NAME;
			}
			return super.getColumnName(column);
		}
	}
}

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
package fr.upensma.lias.kmade.tool.viewadaptator;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;

import fr.upensma.lias.kmade.kmad.ExpressConstant;
import fr.upensma.lias.kmade.kmad.interfaceexpressjava.InterfaceExpressJava;
import fr.upensma.lias.kmade.kmad.schema.tache.Executant;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.KMADEMainFrame;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultEdge;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultPort;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEGraphLayoutAlgorithm;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETaskModelPanel;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADETreeLayoutAlgorithm;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.view.taskproperties.KMADETaskPropertiesPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.CustomOptionDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.InDevelopmentGlassPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.view.toolutilities.SwingWorker;
import fr.upensma.lias.kmade.tool.view.worldobject.concreteobject.KMADEReadWriteConcreteObjectPanel;
import fr.upensma.lias.kmade.tool.view.worldobject.event.KMADEReadWriteEventObjectPanel;

/**
 * @author Mickael BARON
 */
public final class GraphicEditorAdaptator {

    private static boolean done = false;

    private static boolean canceled = false;

    private static boolean begining = false;

    private static Tache[] tacheToBeCreated;

    private static KMADEDefaultGraphCell myDefaultGraphCellRef = null;

    private static int currentNewTask = 0;

    private static final KMADEMainFrame MAIN_FRAME = new KMADEMainFrame();

    private static final KMADETaskPropertiesPanel TASK_PROPERTIES_PANEL = KMADEMainFrame
	    .getProjectPanel().getPanelProprieteTache();

    private static final KMADETaskModelPanel TASK_MODEL_PANEL = KMADEMainFrame
	    .getProjectPanel().getTaskModelPanel();

    private static final KMADEReadWriteEventObjectPanel EVENT_OBJECT_PANEL = KMADEMainFrame
	    .getProjectPanel().tableEvent();

    private static final KMADEReadWriteConcreteObjectPanel CONCRETE_OBJECT_PANEL = KMADEMainFrame
	    .getProjectPanel().getPanelCreationObjConc();

    public static KMADEMainFrame getMainFrame() {
	return MAIN_FRAME;
    }

    public static KMADETaskPropertiesPanel getPanelProp() {
	return TASK_PROPERTIES_PANEL;
    }

    public static KMADETaskModelPanel getTaskModelPanel() {
	return TASK_MODEL_PANEL;
    }

    public static KMADEReadWriteEventObjectPanel getEventObjectPanel() {
	return EVENT_OBJECT_PANEL;
    }

    public static KMADEReadWriteConcreteObjectPanel getPanelCreationObjConc() {
	return CONCRETE_OBJECT_PANEL;
    }

    // // Mise à jour des tâches vues avec les tâches modèles.
    // private static void updateViewAndModel() {
    // Object[] cellView =
    // GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getGraphLayoutCache().getCells(false,
    // true, false, false);
    // ArrayList<Tache> cellModel = ExpressTask.getTasksFromExpress();
    // if (cellView == null) {
    // return;
    // }
    //
    // if (cellView.length != cellModel.size()) {
    // KMADEHistoryMessageManager.printMessage("Non correspondance entre le modèle Express et les vues JGraph : un conseil recharger le fichier");
    // return;
    // } else {
    // for (int i = 0; i < cellView.length; i++) {
    // boolean stop = false;
    // for (int j = 0; j < cellModel.size() && !stop; j++) {
    // if (((KMADEDefaultGraphCell)
    // cellView[i]).getOid().equals(cellModel.get(j).getOid().get())) {
    // // La vue connait sa tache.
    // ((KMADEDefaultGraphCell) cellView[i]).setTask(cellModel.get(j));
    // // La tache connait sa vue.
    // cellModel.get(j).setJTask((KMADEDefaultGraphCell) cellView[i]);
    // cellModel.remove(j);
    // stop = true;
    // }
    // }
    // }
    // }
    // }

    public static boolean addNewTask(Point p) {
	switch (currentNewTask) {
	case 0: {
	    break;
	}
	case 1: {
	    GraphicEditorAdaptator.addNewUnknownTask(p);
	    break;
	}
	case 2: {
	    GraphicEditorAdaptator.addNewAbstractTask(p);
	    break;
	}
	case 3: {
	    GraphicEditorAdaptator.addNewUserTask(p);
	    break;
	}
	case 4: {
	    GraphicEditorAdaptator.addNewFeedbackTask(p);
	    break;
	}
	case 5: {
	    GraphicEditorAdaptator.addNewInteractionTask(p);
	    break;
	}
	}

	// Mise � jour de la pr�sentation concernant la barre d'outils.
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.getEditorsToolBar().enabledButton();
	if (currentNewTask != 0) {
	    currentNewTask = 0;
	    return true;
	} else {
	    currentNewTask = 0;
	    return false;
	}
    }

    public static void clearSelection() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().clearSelection();
    }

    public static void setSelectionTask(Tache myTask) {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().setSelectionCell(
		myTask.getJTask());
    }

    public static void enabledMainFrameAfterSimulation() {
	GraphicEditorAdaptator.getMainFrame().activeMessageStream();
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.unPlugWindowInDisabledGlassPanel(MAIN_FRAME);
	MAIN_FRAME.requestFocus();
    }

    public static void disabledMainFrameBeforeSimulation() {
	TaskPropertiesEnhancedEditorAdaptator.closeNMDAEnhancedTaskEditor();
	SearchAdaptator.closeFindReplaceDialog();
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.plugWindowInDisabledGlassPanel(MAIN_FRAME,
		KMADEConstant.SIMULATION_TITLE_MESSAGE + "...");
    }

    public static void enabledMainFrameAfterLoadAndSaveProcess() {
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getFindReplaceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.unPlugWindowInDisabledGlassPanel(MAIN_FRAME);
    }

    public static void disabledMainFrameBeforeLoadAndSaveProcess() {
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getFindReplaceDialog());
	InDevelopmentGlassPanel.plugWindowInDisabledGlassPanel(MAIN_FRAME);
    }

    public static void enabledMainFrameAfterEdition() {
	GraphicEditorAdaptator.getMainFrame().activeMessageStream();
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getFindReplaceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabled(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.unPlugWindowInDisabled(MAIN_FRAME);
    }

    public static void disabledMainFrameBeforeEdition() {
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.plugDialogInDisabled(MAIN_FRAME
		.getFindReplaceDialog());
	InDevelopmentGlassPanel.plugWindowInDisabled(MAIN_FRAME);
    }

    /**
     * Cette m�thode permet l'impression du mod�le de t�ches.
     */
    public static void printCurrentTaskModel() {
	// R�cup�re le mod�le actuel du JGraph.
	DefaultGraphModel myGraphModel = TASK_MODEL_PANEL.getGraphModel();
	SearchAdaptator.closeFindReplaceDialog();
	TaskPropertiesEnhancedEditorAdaptator.closeNMDAEnhancedTaskEditor();
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.plugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.plugWindowInDisabledGlassPanel(MAIN_FRAME,
		KMADEConstant.PRINT_ACTION_MESSAGE + "...");
	PrintAdaptator.openPrintDialog(myGraphModel,
		GraphicEditorAdaptator.getCurrentGraphLayoutCache());
    }

    public static void closePrintDialog() {
	GraphicEditorAdaptator.getMainFrame().getPrintingFrame()
		.setVisible(false);
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getClipBoardDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getPreviewWindow());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getCoherenceDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getStatisticDialog());
	InDevelopmentGlassPanel.unPlugDialogInDisabledGlassPanel(MAIN_FRAME
		.getEntityDialog());
	InDevelopmentGlassPanel.unPlugWindowInDisabledGlassPanel(MAIN_FRAME);
    }

    public static void requestFocus() {
	MAIN_FRAME.requestFocus();
    }

    public static void initMainFrame() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar().enabledButton();
	currentNewTask = 0;
	KMADEMainFrame.getProjectPanel().initMainFrame();
    }

    public static void addNewAbstractTask() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.enabledAbstractButton();
	if (currentNewTask == 2) {
	    currentNewTask = 0;
	} else {
	    currentNewTask = 2;
	}
    }

    public static void addNewUserTask() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.enabledUserButton();
	if (currentNewTask == 3) {
	    currentNewTask = 0;
	} else {
	    currentNewTask = 3;
	}
    }

    public static void addNewFeedbackTask() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.enabledSystemButton();
	if (currentNewTask == 4) {
	    currentNewTask = 0;
	} else {
	    currentNewTask = 4;
	}
    }

    public static void addNewInteractionTask() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.enabledInteractionButton();
	if (currentNewTask == 5) {
	    currentNewTask = 0;
	} else {
	    currentNewTask = 5;
	}
    }

    public static void addNewUnknownTask() {
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.enabledUnknownButton();
	if (currentNewTask == 1) {
	    currentNewTask = 0;
	} else {
	    currentNewTask = 1;
	}
    }

    private static Point centeredTask(Point p) {
//	FontMetrics fname = TASK_MODEL_PANEL
//		.getFontMetrics(KMADEConstant.TASK_NAME_FONT);
	/*
	 * ancienne version du code -> int width =
	 * SwingUtilities.computeStringWidth(fname,
	 * ExpressConstant.NEW_NAME_TASK) + 10;
	 */
	int width = KMADEVertexView.widthView(ExpressConstant.NEW_NAME_TASK);
	int nx = p.x - (width / 2);
	if (nx < 0) {
	    nx = 0;
	}
	int height = KMADEVertexView.heightView();
	int ny = p.y - (height / 2);
	if (ny < 0) {
	    ny = 0;
	}
	return new Point(nx, ny);
    }

    public static void addNewUnknownTask(Point p) {
	int mx = (int) (p.getX() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	int my = (int) (p.getY() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	GraphicEditorAdaptator.addNewTask(Executant.INCONNU,
		centeredTask(new Point(mx, my)));
    }

    public static void addNewAbstractTask(Point p) {
	int mx = (int) (p.getX() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	int my = (int) (p.getY() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	GraphicEditorAdaptator.addNewTask(Executant.ABS,
		centeredTask(new Point(mx, my)));
    }

    public static void addNewUserTask(Point p) {
	int mx = (int) (p.getX() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	int my = (int) (p.getY() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	GraphicEditorAdaptator.addNewTask(Executant.USER,
		centeredTask(new Point(mx, my)));
    }

    public static void addNewFeedbackTask(Point p) {
	int mx = (int) (p.getX() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	int my = (int) (p.getY() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	GraphicEditorAdaptator.addNewTask(Executant.SYS,
		centeredTask(new Point(mx, my)));
    }

    public static void addNewInteractionTask(Point p) {
	int mx = (int) (p.getX() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	int my = (int) (p.getY() / GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getScale());
	GraphicEditorAdaptator.addNewTask(Executant.INT,
		centeredTask(new Point(mx, my)));
    }

    /**
     * Cette m�thode est appel�e � la suite de l'action Ajouter T�che.
     */
    public static void addNewTask(Executant e, Point p) {
	// A d�terminer ici les coordonn�es de cr�ation.
	Tache myTask = ExpressTask.addNewTask(p.x, p.y, e);
	GraphicEditorAdaptator.addNewTask(myTask);
    }

    /**
     * Cette m�thode est utilis�e � la fois � la cr�ation d'une
     * t�che par l'utilisateur et � la cr�ation d'une t�che par
     * l'application (chargement).
     * 
     * @param pTask
     */

    public static void addNewTask(Tache pTask) {
	// Creation de la tache de type graphique.
	KMADEDefaultGraphCell tempTask = new KMADEDefaultGraphCell(pTask);

	// Positionner la tache dans le graphique.
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().clearSelection();
	GraphicEditorAdaptator.getCurrentGraphLayoutCache().insert(tempTask);
    }

    /* Ajout */
    public static void addCopyTask(Tache pTask) {
	// Creation de la tache de type graphique.
	KMADEDefaultGraphCell tempTask = new KMADEDefaultGraphCell(pTask);

	// Positionner la tache dans le graphique.
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().clearSelection();
	GraphicEditorAdaptator.getCurrentGraphLayoutCache().insert(tempTask);
    }

    /****/
    /**
     * Cette m�thode permet de cr�er une t�che dans le presse-papier.
     * 
     * @param pTask
     */
    public static void addNewTaskIntoClipBoard(Tache pTask) {
	// Cr�ation de la t�che de type graphique.
	KMADEDefaultGraphCell tempTask = new KMADEDefaultGraphCell(pTask);

	// KMADEHistoryMessageManager.printMessage("Nom "+tempTask.getName()+" "+tempTask.getExecutant()+" "+tempTask.getDecomposition());
	// Positionner la t�che dans le graphique.
	MAIN_FRAME.getClipBoardDialog().getMyGraph().getGraphLayoutCache()
		.insert(tempTask);
    }

    public static void clearClipBoard() {
	MAIN_FRAME.getClipBoardDialog().removeAllEntities();
	InterfaceExpressJava.clearClipBoard();
    }

    /**
     * Cette m�thode permet la connection de toutes les t�ches dans le
     * presse-papier.
     * 
     * @param taches
     */
    public static void connectAllTasksIntoClipBoard(Tache[] taches) {
	// Creation des liens.
	for (int i = 0; i < taches.length; i++) {
	    // OID de la tache (
	    String oid = ((Tache) taches[i]).getOid().get();
	    // Reference de la tache mere.
	    Tache mere = ((Tache) taches[i]).getMotherTask();
	    if (mere != null)
		GraphicEditorAdaptator.addNewEdgeIntoClipBoard(mere.getOid()
			.get(), oid);
	}
    }

    /**
     * Cette methode permet d'effectuer un lien graphique entre deux taches
     * dans le presse-papier.
     * 
     * @param oidMere
     * @param oidFils
     */
    public static void addNewEdgeIntoClipBoard(String oidMere, String oidFils) {
	KMADEDefaultGraphCell mere = MAIN_FRAME.getClipBoardDialog().getTask(
		oidMere);
	KMADEDefaultGraphCell fils = MAIN_FRAME.getClipBoardDialog().getTask(
		oidFils);

	// Creation de la liaison dans la partie graphique.
	KMADEDefaultEdge edge = new KMADEDefaultEdge();
	MAIN_FRAME.getClipBoardDialog().getMyGraph().getGraphLayoutCache()
		.insertEdge(edge, mere.getMotherPort(), fils.getSonPort());
    }

    /**
     * Cette m�thode permet la connection de toutes les t�ches.
     * 
     * @param taches
     */
    public static void connectAllTasks(Tache[] taches) {
	// Cr�ation des liens.
	for (int i = 0; i < taches.length; i++) {
	    // OID de la t�che (
	    String oid = ((Tache) taches[i]).getOid().get();
	    // R�f�rence de la t�che m�re.
	    Tache mere = ((Tache) taches[i]).getMotherTask();
	    if (mere != null)
		GraphicEditorAdaptator.addNewEdge(mere.getOid().get(), oid);
	}
    }

    /**
     * Cette m�thode permet d'effectuer un lien graphique entre deux t�ches.
     * 
     * @param oidMere
     * @param oidFils
     */
    private static void addNewEdge(String oidMere, String oidFils) {
	KMADEDefaultGraphCell mere = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getTask(oidMere);
	KMADEDefaultGraphCell fils = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getTask(oidFils);

	// Cr�ation de la liaison dans la partie graphique.
	KMADEDefaultEdge edge = new KMADEDefaultEdge();
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getGraphLayoutCache()
		.insertEdge(edge, mere.getMotherPort(), fils.getSonPort());
    }

    /**
     * Cette m�thode est appel�e lors de la demande de cr�ation d'un lien.
     * 
     * @param source
     *            : le port source
     * @param target
     *            : le port destination
     */
    public static boolean addNewEdge(KMADEDefaultPort source,
	    KMADEDefaultPort target) {
	boolean successed = false;
	if (source != null && target != null) {
	    if ((source.getParent() instanceof KMADEDefaultGraphCell)
		    && (target.getParent() instanceof KMADEDefaultGraphCell)) {
		// Règle 1 : ne pas connecter le port "mother" avec le port
		// "son" de la même tâche.
		if (source.getParent() == target.getParent()) {
		    // Règle 2 : ne pas connecter un port "mother" avec un
		    // autre
		    // port "mother"
		} else if (source.isMotherPort() && target.isMotherPort()) {
		    // Règle 3 : ne pas connecter un port "son" avec un autre
		    // port "son"
		} else if (source.isSonPort() && target.isSonPort()) {
		    // Règle 4 : pas de connexion d'un port "mother" avec un
		    // port "son" déjà connecté
		} else if (source.isMotherPort()
			&& (target.getEdges().size() != 0)) {
		    // Règle 5 : pas de connexion d'un port "son" déjà
		    // connecté
		    // à un port "mother"
		} else if (source.isSonPort()
			&& (source.getEdges().size() != 0)) {
		} else {
		    if (source.isSonPort() && target.isMotherPort()) {
			KMADEDefaultPort tempNewPort = source;
			source = target;
			target = tempNewPort;
		    }
		    KMADEDefaultGraphCell sourceCell = (KMADEDefaultGraphCell) source
			    .getParent();
		    KMADEDefaultGraphCell targetCell = (KMADEDefaultGraphCell) target
			    .getParent();
		    // Cr�ation de la liaison dans Express.
		    ExpressTask.addNewEdge(sourceCell.getTask(),
			    targetCell.getTask());
		    // Cr�ation de la liaison dans la partie graphique.
		    KMADEDefaultEdge edge = new KMADEDefaultEdge();
		    GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
			    .getGraphLayoutCache()
			    .insertEdge(edge, source, target);

		    successed = true;
		}
	    }
	} else {
	}

	return successed;
    }

    /**
     * Cette m�thode permet de supprimer une t�che et ses noeuds associ�s.
     * 
     * @param currentCell
     */
    private static void removeTask(KMADEDefaultGraphCell currentCell) {
	// On cache le JPanel li� � l'�dition des attributs de t�ches.
	TaskPropertiesAdaptator.hideTaskProperties();
	// Suppression dans Express avec tous ces liens.
	ExpressTask.removeTask(((KMADEDefaultGraphCell) currentCell).getTask());

	// Partie graphique (Ces deux ancres avec tous les liens)
	// La tâche avec les deux ancres.
	Object[] cells = GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getDescendants(new Object[] { currentCell });
	for (int i = 0; i < cells.length; i++) {
	    if (cells[i] instanceof KMADEDefaultPort) {
		// Supprime tous les liens associ�s.
		Set<?> removeEdges = ((KMADEDefaultPort) cells[i]).getEdges();
		Iterator<?> iterator = removeEdges.iterator();
		while (iterator.hasNext()) {
		    Object[] tempo = new Object[] { iterator.next() };
		    GraphicEditorAdaptator.getCurrentGraphLayoutCache().remove(
			    tempo);
		    GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
			    .getModel().remove(tempo);
		}
	    }
	}
	// La t�che en elle-m�me.
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getModel()
		.remove(cells);
    }

    /**
     * Cette m�thode permet de supprimer un lien.
     * 
     * @param currentEdge
     */
    private static void removeEdge(KMADEDefaultEdge currentEdge) {
	KMADEDefaultGraphCell motherCell = ((KMADEDefaultEdge) currentEdge)
		.getMotherCell();

	// Suppression dans Express du lien.
	ExpressTask.removeEdge(motherCell.getTask(),
		((KMADEDefaultEdge) currentEdge).getSonCell().getTask());

	// Suppression dans la partie graphique.
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getGraphLayoutCache().remove(new Object[] { currentEdge });
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getModel()
		.remove(new Object[] { currentEdge });
    }

    /**
     * Cette m�thode permet la suppression de t�ches et de liens.
     */
    public static void removeTaskAndEdge() {
	Object[] cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getSelectionCells();
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().clearSelection();

	if (cellSelected.length == 0) {
	    // Un noeud ou une t�che.
	} else if (cellSelected.length == 1) {
	    // C'est une tâche.
	    if (cellSelected[0] instanceof KMADEDefaultGraphCell) {
		GraphicEditorAdaptator
			.removeTask((KMADEDefaultGraphCell) cellSelected[0]);
	    } else {
		// C'est lien. Plus compliqu� dans le sens o� il faut aller
		// chercher la t�che m�re
		// et la t�che fille avant de faire le traitement.
		GraphicEditorAdaptator
			.removeEdge((KMADEDefaultEdge) cellSelected[0]);
	    }
	} else if (cellSelected.length > 1) {
	    // L'optimisation propos�e ici vient du fait que dans les
	    // �l�ments
	    // s�lectionn�e
	    // il peut y avoir des liens qui ont pu �tre supprim�s avant.
	    // Donc
	    // pour un lien
	    // on v�rifie seulement si sa m�re existe toujours.
	    for (int i = 0; i < cellSelected.length; i++) {
		if (cellSelected[i] instanceof KMADEDefaultGraphCell) {
		    GraphicEditorAdaptator
			    .removeTask((KMADEDefaultGraphCell) cellSelected[i]);
		} else {
		    if (((KMADEDefaultEdge) cellSelected[i]).getMotherCell() != null) {
			GraphicEditorAdaptator
				.removeEdge((KMADEDefaultEdge) cellSelected[i]);
		    }
		}
	    }
	}
    }

    /**
     * Cette m�thode permet de mettre � jour les points des t�ches dans
     * l'express
     */
    public static void fireMoveTasks() {
	if (GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getSelectionCount() == 1) {
	    // One cell selection (cell = vertex or edge)
	    Object cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		    .getJGraph().getSelectionCell();
	    if (cellSelected instanceof KMADEDefaultGraphCell) {

		// Get cell point
		Point2D monPoint = GraphicEditorAdaptator.getPointFromCell(cellSelected);
		if (monPoint != null) {
		    KMADEDefaultGraphCell myCell = (KMADEDefaultGraphCell) cellSelected;
		    // Old position values
		    int x = myCell.getPoint().x;
		    int y = myCell.getPoint().y;
		    ExpressTask.setTaskPoint((int) monPoint.getX(),(int) monPoint.getY(), myCell.getTask());
		    if (!myCell.isSonExpanded() || MAIN_FRAME.getApplicationToolBar().getEditorsToolBar().isMagnet()) {
			int dx = x - (int) monPoint.getX();
			int dy = y - (int) monPoint.getY();

			ArrayList<KMADEDefaultGraphCell> toto = myCell.getDescendantSubCells(false);
			for (int i = 0; i < toto.size(); i++) {
			    if (toto.get(i) != myCell) {
				toto.get(i).setDeltaPoint(dx, dy);
			    }
			}
		    }
		}
		ArrayList<KMADEDefaultGraphCell> tt = ((KMADEDefaultGraphCell) cellSelected)
			.getDescendantSubCells(false);
		GraphicEditorAdaptator.updateGraphModel(tt);
	    }
	} else if (GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getSelectionCount() > 1) {
	    // Several cell selections (cell = vertex or edge)
	    Object[] cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		    .getJGraph().getSelectionCells();

	    for (int i = 0; i < cellSelected.length; i++) {
		if (cellSelected[i] instanceof KMADEDefaultGraphCell) {
		    Point2D monPoint = GraphicEditorAdaptator
			    .getPointFromCell(cellSelected[i]);
		    if (monPoint != null) {
			ExpressTask.setTaskPoint((int) monPoint.getX(),
				(int) monPoint.getY(),
				((KMADEDefaultGraphCell) cellSelected[i])
					.getTask());
		    }
		}
	    }
	    GraphicEditorAdaptator.updateGraphModel(cellSelected);
	}
    }

    public static void setFewAttributesSelectedTask() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().startEditingAtCell(
		myDefaultGraphCellRef);
    }

    public static JPopupMenu showPopupMenu(Point pt) {
	Object[] cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getSelectionCells();

	if (cellSelected == null) {
	    return null;
	}

	// Aucune tâche de sélectionnée.
	if (cellSelected.length == 0) {
	    return GraphicEditorAdaptator.getMainFrame()
		    .getApplicationToolBar().createPopupMenuForAdd(pt);
	}

	// Une tâche de sélectionnée.
	if (cellSelected.length == 1) {
	    if (cellSelected[0] instanceof KMADEDefaultEdge) {
		return GraphicEditorAdaptator.getMainFrame()
			.getApplicationToolBar().createPopupMenuForOneEdge(pt);
	    } else {
		return GraphicEditorAdaptator.getMainFrame()
			.getApplicationToolBar().createPopupMenuForOneTask(pt);
	    }
	}

	if (cellSelected.length > 1) {
	    boolean existedCell = false;
	    for (int i = 0; i < cellSelected.length && !existedCell; i++) {
		if (cellSelected[i] instanceof KMADEDefaultGraphCell) {
		    existedCell = true;
		}
	    }
	    if (existedCell) {
		return GraphicEditorAdaptator.getMainFrame()
			.getApplicationToolBar()
			.createPopupMenuForSeveralTasks(pt);
	    } else {
		return GraphicEditorAdaptator.getMainFrame()
			.getApplicationToolBar()
			.createPopupMenuForSeveralEdges(pt);
	    }
	}
	return null;
    }

    /**
     * Retourne les nouvelles coordonn�es de la cellule.
     * 
     * @param pCell
     * @return
     */
    private static Point2D getPointFromCell(Object pCell) {
	if (pCell instanceof KMADEDefaultGraphCell) {
	    Rectangle2D monPoint = GraphConstants
		    .getBounds(((KMADEDefaultGraphCell) pCell).getAttributes());
	    Point2D.Double temp = new Point2D.Double(monPoint.getX(),
		    monPoint.getY());
	    return temp;
	} else {
	    return null;
	}
    }

    public static void refreshJGraphView() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.refreshJGraphView();
    }

    public static void refreshViews() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.refreshViews();
    }

    public static void updateGraphModel(Object myObject) {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.updateGraphModel(myObject);
    }

    public static void updateSelectedTaskGraphModel() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL
		.updateGraphModel(myDefaultGraphCellRef);
    }

    public static void updateGraphModel(Object[] myObject) {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.updateGraphModel(myObject);
    }

    public static GraphLayoutCache getCurrentGraphLayoutCache() {
	return GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getGraphLayoutCache();
    }

    /**
     * Cette m�thode permet la s�lection d'une t�che
     * 
     * @param pDefaultGraphCell
     *            : t�che s�lectionn�e
     */
    public static void selectOneTask(Object defaultGraphCell) {
	if (defaultGraphCell instanceof KMADEDefaultGraphCell) {
	    // Vérifie si on peut le sélectionner car peut-pêtre invisible.
	    myDefaultGraphCellRef = (KMADEDefaultGraphCell) defaultGraphCell;
	    TaskPropertiesAdaptator.showTaskProperties();
	    TaskPropertiesAdaptator.setSelectedTaskAttributes();
	    // If ComplexTaskEditor is visible ...
	    setSelectedTaskInEnhancedEditor();
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .selectedElementViewState();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .selectedElementViewState();
	} else {
	    // C'est un lien.
	    myDefaultGraphCellRef = null;
	    TaskPropertiesAdaptator.hideTaskProperties();
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .selectedEdgeViewState();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .noSelectedElementViewState();
	}
    }

    public static void setSelectedTaskInEnhancedEditor() {
	if (myDefaultGraphCellRef != null) {
	    if (TaskPropertiesEnhancedEditorAdaptator.isVisible()) {
		TaskPropertiesEnhancedEditorAdaptator
			.setSelectedTaskAttributes(getSelectedGraphicTask()
				.getTask());
	    }
	}
    }

    /**
     * Appel�e quand plusieurs t�ches sont s�lectionn�e.
     */
    public static void selectSeveralTask() {
	myDefaultGraphCellRef = null;
	TaskPropertiesAdaptator.hideTaskProperties();
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.selectedElementsViewState();
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.selectedElementsViewState();
    }

    /**
     * Cette m�thode permet de cacher les Panels quand aucune t�che n'est
     * s�lectionn�e.
     */
    public static void selectNoTask() {
	myDefaultGraphCellRef = null;
	// D�sactivation des actions associ�es � la non s�lection d'une
	// t�che.
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.noSelectedElementViewState();
	MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		.noSelectedElementViewState();
	TaskPropertiesAdaptator.hideTaskProperties();
    }

    /**
     * Appel�e pour effectuer un zoom plus.
     */
    public static void setPlusZoom() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.zoomPlus(1.1d);
    }

    /**
     * Appel�e pour effectuer un zoom moins.
     */
    public static void setMinusZoom() {
	if (GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getScale() <= 0.5) {
	    GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().setGridVisible(
		    false);
	    GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().setGridEnabled(
		    false);
	    GraphicEditorAdaptator.showOrHideGrid(true);
	}
	GraphicEditorAdaptator.TASK_MODEL_PANEL.zoomMinus(1.1d);
    }

    /**
     * Appelée pour effectuer un zoom par d�faut.
     */
    public static void setDefaultZoom() {
	GraphicEditorAdaptator.TASK_MODEL_PANEL.zoomDefault(1d);
    }

    /**
     * Cette méthode s'occupe de la mise à jour de la partie graphique après
     * chargement d'un fichier SPF.
     */
    public static void makeAllGraphElements() {
	tacheToBeCreated = ExpressTask.getAllTaskFromExpress();
	SwingWorker worker = new SwingWorker() {
	    public Object construct() {
		GraphicEditorAdaptator.getTaskModelPanel().emptyRootFromModel();

		KMADEDefaultGraphCell[] toto = new KMADEDefaultGraphCell[tacheToBeCreated.length];
		for (int i = 0; i < tacheToBeCreated.length
			&& !GraphicEditorAdaptator.canceled; i++) {
		    // Cr�ation de la t�che de type graphique.
		    KMADEDefaultGraphCell tempTask = new KMADEDefaultGraphCell(
			    tacheToBeCreated[i]);
		    if (tacheToBeCreated[i].hasNoPoint()
			    && tacheToBeCreated[i].getNumero().contains(
				    ExpressConstant.ROOT_TASK_NAME)) {
			GraphicEditorAdaptator.setSelectedTask(tempTask);
		    }

		    toto[i] = tempTask;
		}

		if (GraphicEditorAdaptator.canceled) {
		    return null;
		}

		// Plus rapide que de créer séparement la tâche.
		KMADEHistoryMessageManager.printlnMessage("\n"
			+ KMADEConstant.CREATE_GRAPHICAL_TASKS_MESSAGE);
		GraphicEditorAdaptator.getCurrentGraphLayoutCache()
			.insert(toto);

		// Création des liens.
		for (int i = 0; i < tacheToBeCreated.length
			&& !GraphicEditorAdaptator.canceled; i++) {
		    // OID de la t�che (
		    String oid = (tacheToBeCreated[i]).getOid().get();
		    // Réfèrence de la tâche mère.
		    Tache mere = (tacheToBeCreated[i]).getMotherTask();
		    if (mere != null)
			GraphicEditorAdaptator.addNewEdge(mere.getOid().get(),
				oid);
		}

		if (GraphicEditorAdaptator.getSelectedGraphicTask() != null) {
		    GraphicEditorAdaptator.setJustifyTaskModel();
		}

		if (GraphicEditorAdaptator.canceled) {
		    return null;
		} else {
		    KMADEHistoryMessageManager.printlnMessage(KMADEConstant.CREATE_GRAPHICAL_EDGES_MESSAGE);
		    // Effacer les anciennes valeurs dans les Tables (Graphique)
		    GraphicEditorAdaptator.done = true;
		}

		return null;
	    }
	};
	worker.start();
    }

    public static KMADEDefaultGraphCell getSelectedGraphicTask() {
	return GraphicEditorAdaptator.myDefaultGraphCellRef;
    }

    public static void setSelectedTask(KMADEDefaultGraphCell ref) {
	myDefaultGraphCellRef = ref;
    }

    public static String getOidSelectedTask() {
	return GraphicEditorAdaptator.myDefaultGraphCellRef.getOid();
    }

    public static Tache getSelectedExpressTask() {
	return GraphicEditorAdaptator.myDefaultGraphCellRef.getTask();
    }

    /**
     * Modifier la valeur de la taille de la grille.
     */
    public static void setGridSize() {
	double graphGridSize = TASK_MODEL_PANEL.getJGraph().getGridSize();
	Object myObject = JOptionPane.showInputDialog(
		GraphicEditorAdaptator.getMainFrame(),
		KMADEConstant.GRID_SIZE_ACTION_MESSAGE,
		KMADEConstant.INPUT_GRID_SIZE_MESSAGE,
		JOptionPane.QUESTION_MESSAGE,
		new ImageIcon(GraphicEditorAdaptator.class
			.getResource(KMADEConstant.ASK_DIALOG_IMAGE)), null,
		(int) TASK_MODEL_PANEL.getJGraph().getGridSize());
	if (myObject == null)
	    return;
	try {
	    graphGridSize = Double.parseDouble((String) myObject);
	    if (graphGridSize > 0 && graphGridSize <= 40) {
		TASK_MODEL_PANEL.getJGraph().setGridSize(graphGridSize);
		if (!(GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
			.getScale() <= 0.5)) {
		    TASK_MODEL_PANEL.getJGraph().setGridEnabled(true);
		    TASK_MODEL_PANEL.getJGraph().setGridVisible(true);
		    GraphicEditorAdaptator.showOrHideGrid(false);
		}
	    } else {
		KMADEHistoryMessageManager.printlnError(KMADEConstant.INPUT_GRID_SIZE_ERROR_MESSAGE);
	    }
	} catch (Exception ee) {
	    KMADEHistoryMessageManager.printlnError(KMADEConstant.INPUT_GRID_SIZE_ERROR_MESSAGE);
	}
    }

    public static void showOrHideGrid() {
	if (!(GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getScale() <= 0.5)) {
	    boolean state = TASK_MODEL_PANEL.getJGraph().isGridEnabled();
	    TASK_MODEL_PANEL.getJGraph().setGridEnabled(!state);
	    TASK_MODEL_PANEL.getJGraph().setGridVisible(!state);
	    GraphicEditorAdaptator.showOrHideGrid(state);
	}
    }

    public static void hideGrid() {
	TASK_MODEL_PANEL.getJGraph().setGridEnabled(false);
	TASK_MODEL_PANEL.getJGraph().setGridVisible(false);
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.setDisabledGrid();
    }

    public static void hideRule() {
	KMADEMainFrame.getProjectPanel().getTaskDescriptionPanel().hideRule();
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.setDisabledRule();
    }

    /**
     * Affiche ou cache la grille.
     */
    public static void showOrHideGrid(boolean state) {
	if (state) {
	    // Pas Activée
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .setDisabledGrid();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .setDisabledGrid();
	} else {
	    // Activée
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .setEnabledGrid();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .setEnabledGrid();
	}
    }

    /**
     * Affiche ou cache la règle.
     */
    public static void showOrHideRule() {
	boolean state = KMADEMainFrame.getProjectPanel()
		.getTaskDescriptionPanel().isRuleVisible();
	KMADEMainFrame.getProjectPanel().getTaskDescriptionPanel()
		.showOrHideRule();

	if (state) {
	    // Pas Activée
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .setDisabledRule();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .setDisabledRule();
	} else {
	    // Activée
	    GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		    .setEnabledRule();
	    MAIN_FRAME.getApplicationToolBar().getEditorsToolBar()
		    .setEnabledRule();
	}
    }

    public static ArrayList<Tache> getSelectedTasks() {
	Object[] cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getSelectionCells();
	ArrayList<Tache> taskList = new ArrayList<Tache>();

	for (int i = 0; i < cellSelected.length; i++) {
	    if (cellSelected[i] instanceof KMADEDefaultGraphCell) {
		taskList.add(((KMADEDefaultGraphCell) cellSelected[i])
			.getTask());
	    }
	}
	return taskList;
    }

    /**
     * Cette méthode effectue une copie sur la partie Express (direction le
     * presse-papier).
     */
    public static void copyAction() {
	Object[] cellSelected = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getSelectionCells();

	// KMADEHistoryMessageManager.printMessage("Ajout d'une tache dans le clipBord  "+
	// cellSelected.length);
	// Etape 1 : dissocier les taches et les liens au sens graphique.
	ArrayList<String[]> edgeOIDList = new ArrayList<String[]>();
	ArrayList<Tache> taskList = new ArrayList<Tache>();

	for (int i = 0; i < cellSelected.length; i++) {

	    if (cellSelected[i] instanceof KMADEDefaultEdge) {
		String[] couple = new String[2];
		couple[0] = ((KMADEDefaultEdge) cellSelected[i])
			.getMotherCell().getOid();
		couple[1] = ((KMADEDefaultEdge) cellSelected[i]).getSonCell()
			.getOid();
		edgeOIDList.add(couple);
	    } else if (cellSelected[i] instanceof KMADEDefaultGraphCell) {
		taskList.add(((KMADEDefaultGraphCell) cellSelected[i])
			.getTask());
	    }
	}

	MAIN_FRAME.getClipBoardDialog().removeAllEntities();

	Tache[] newTasks = ExpressTask.copyElementsIntoClipBoard(taskList,
		edgeOIDList);
	for (int i = 0; i < newTasks.length; i++) {
	    GraphicEditorAdaptator.addNewTaskIntoClipBoard(newTasks[i]);
	}
	GraphicEditorAdaptator.connectAllTasksIntoClipBoard(newTasks);
	GraphicEditorAdaptator.getMainFrame().getApplicationToolBar()
		.enablePasteActionViewState();
    }

    /**
     * Cette methode effectue un collage sur la partie graphique et express.
     */
    public static void pasteAction(Point pt) {
	Tache[] newTasks = ExpressTask.pasteElementsFromClipBoard(pt);
	for (int i = 0; i < newTasks.length; i++) {
	    GraphicEditorAdaptator.addNewTask(newTasks[i]);
	}
	GraphicEditorAdaptator.connectAllTasks(newTasks); // connexion des tache
	// entre elles
    }

    /**
     * @return Returns the done.
     */
    public static boolean isDone() {
	return done;
    }

    /**
     * @return Returns the inProgress.
     */
    public static boolean isBegining() {
	return begining;
    }

    public static void setBegining(boolean p) {
	begining = p;
    }

    /**
     * @param done
     *            The done to set.
     */
    public static void setDone(boolean done) {
	GraphicEditorAdaptator.done = done;
    }

    /**
     * @param canceled
     *            The canceled to set.
     */
    public static void setCanceled(boolean canceled) {
	GraphicEditorAdaptator.canceled = canceled;
    }

    /**
     * @return Returns the canceled.
     */
    public static boolean isCanceled() {
	return canceled;
    }

    public static void cleanAllInterface() {
	MAIN_FRAME.showEmptyPanel();
	TaskPropertiesEnhancedEditorAdaptator.closeNMDAEnhancedTaskEditor();
	GraphicEditorAdaptator.selectNoTask();
	StatisticAdaptator.closeStatisticDialog();
	CoherenceAdaptator.closeCoherenceDialog();
	SearchAdaptator.closeFindReplaceDialog();
	EntityListAdaptator.closeEntityListDialog();
    }

    public static boolean removeSelectedItem(String type, int[] r,
	    ArrayList<Object[]> list, Window myOwner, String title) {
	// Le -2 et le -1 correspondent respectivement � l'OID et �
	// l'information si l'�l�ment peut �tre supprim�
	if (r.length == 0)
	    return false;

	boolean isRemoved = false;
	boolean allAnswer = false;
	for (int i = 0; i < r.length; i++) {
	    if (r[i] != list.size()) {
		Object[] tempoValue = list.get(r[i]);
		String maValue = (String) tempoValue[tempoValue.length - 2];
		if (type.equals("AbstractObject")) {
		    AbstractObjectAdaptator.affRemoveObjAbs(maValue);
		} else if (type.equals("Enumeration")) {
		    EnumAdaptator.affRemoveEnumeration(maValue);
		} else if (type.equals("Group")) {
		    GroupAdaptator.affRemoveGroupe(maValue);
		} else if (type.equals("Attribut")) {
		    AbstractAttributeAdaptator.affRemoveAttrAbs(maValue);
		} else if (type.equals("EnumElement")) {
		    EnumElementAdaptator.affRemoveElement(maValue);
		} else if (type.equals("Interval")) {
		    IntervalAdaptator.affRemoveIntervalle(maValue);
		} else if (type.equals("Event")) {
		    EventAdaptator.affRemoveEvent(maValue);
		} else if (type.equals("User")) {
		    UserAdaptator.affRemoveUser(maValue);
		} else if (type.equals("Individu")) {
		    IndividuAdaptator.affRemoveIndividu(maValue);
		} else if (type.equals("Organisation")) {
		    OrganisationAdaptator.affRemoveOrganisation(maValue);
		} else if (type.equals("Machine")) {
		    MachineAdaptator.affRemoveMachine(maValue);
		} else if (type.equals("ParcMachines")) {
		    ParcMachinesAdaptator.affRemoveParcMachines(maValue);
		} else if (type.equals("Actor")) {
		    ActorAdaptator.affRemoveActeur(maValue);
		} else if (type.equals("EventTask")) {
		    EventAdaptator.affRemoveEventInSelectedTask(maValue);
		} else if (type.equals("Project")) {
		    // Pas d'incidence sur le reste
		} else if (type.equals("ConcreteObject")) {
		    ConcreteObjectAdaptator.affRemoveObjConc(maValue);
		} else if (type.equals("Label")) {
		    LabelAdaptator.affRemoveLabel(maValue);
		} else if (type.equals("ActorSystem")) {
		    ActorSystemAdaptator.affRemoveActeurSystem(maValue);
		}

		String[] lstWarning = InterfaceExpressJava.getGestionWarning()
			.takeMessages();
		String s = "";
		for (int j = 0; j < lstWarning.length; j++) {
		    if (j == 0)
			s = s + lstWarning[j];
		    else
			s = s + "\n" + lstWarning[j];
		}

		if (!allAnswer) {
		    int reponse = CustomOptionDialog
			    .showAllConfirmDeleteDialog(myOwner, title, s);

		    switch (reponse) {
		    case CustomOptionDialog.ALL_OPTION: {
			allAnswer = true;
			tempoValue[tempoValue.length - 1] = true;
			isRemoved = true;
			break;
		    }
		    case CustomOptionDialog.CANCEL_OPTION: {
			return isRemoved;
		    }
		    case CustomOptionDialog.NO_OPTION: {
			break;
		    }
		    case CustomOptionDialog.YES_OPTION: {
			tempoValue[tempoValue.length - 1] = true;
			isRemoved = true;
			break;
		    }
		    }
		} else {
		    tempoValue[tempoValue.length - 1] = true;
		    isRemoved = true;
		}

		if (((Boolean) tempoValue[tempoValue.length - 1])) {

		    if (type.equals("AbstractObject")) {
			AbstractObjectAdaptator.removeAbstractObject(maValue);
		    } else if (type.equals("Enumeration")) {
			EnumAdaptator.removeEnum(maValue);
		    } else if (type.equals("Group")) {
			GroupAdaptator.removeGroup(maValue);
		    } else if (type.equals("Attribut")) {
			AbstractAttributeAdaptator
				.removeAbstractAttribute(maValue);
		    } else if (type.equals("EnumElement")) {
			EnumElementAdaptator.removeElement(maValue);
		    } else if (type.equals("Intervalle")) {
			IntervalAdaptator.removeInterval(maValue);
		    } else if (type.equals("Event")) {
			EventAdaptator.removeEvent(maValue);
		    } else if (type.equals("User")) {
			UserAdaptator.removeUser(maValue);
		    } else if (type.equals("Individu")) {
			IndividuAdaptator.removeIndividu(maValue);
		    } else if (type.equals("Organisation")) {
			OrganisationAdaptator.removeOrganisation(maValue);
		    } else if (type.equals("Machine")) {
			MachineAdaptator.removeMachine(maValue);
		    } else if (type.equals("ParcMachines")) {
			ParcMachinesAdaptator.removeParcMachines(maValue);
		    } else if (type.equals("Actor")) {
			ActorAdaptator.removeActeur(maValue);
		    } else if (type.equals("EventTask")) {
			EventAdaptator
				.removeEventIntoSelectedTask((String) tempoValue[0]);
		    } else if (type.equals("Project")) {
			// Ne rien faire
		    } else if (type.equals("ConcreteObject")) {
			ConcreteObjectAdaptator.removeObjConc(maValue);
		    } else if (type.equals("Label")) {
			LabelAdaptator.removeLabel(maValue);
		    } else if (type.equals("ActorSystem")) {
			ActorSystemAdaptator.removeActeurSystem(maValue);
		    }
		}
	    }
	}

	if (isRemoved) {
	    int count = 0;
	    while (count != list.size()) {
		Object[] tempoValue = list.get(count);
		if (((Boolean) tempoValue[tempoValue.length - 1])) {
		    list.remove(count);
		} else {
		    count++;
		}
	    }
	    return true;
	} else {
	    return false;
	}
    }

    public static void applySameExecutant() {
	Tache currentTask = myDefaultGraphCellRef.getTask();
	GraphicEditorAdaptator.applySameExecutant(currentTask);
	GraphicEditorAdaptator.refreshJGraphView();
    }

    private static void applySameExecutant(Tache myTask) {
	for (Tache current : myTask.getFils()) {
	    GraphicEditorAdaptator.applySameExecutant(current);
	    current.setExecutant(myTask.getExecutant());
	}
    }

    public static void setGlobalTaskModel() {
	Dimension dimension = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getPreferredSize();
	Dimension dimension1 = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraph().getBounds().getSize();
	dimension.width = Math.max(dimension.width, dimension1.width);
	dimension1.height = Math.max(dimension.height, dimension1.height);
	double d = GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph()
		.getScale();
	dimension.setSize((double) (dimension.width * 1) / d,
		(double) (dimension.height * 1) / d);
	Dimension dimension2 = GraphicEditorAdaptator.TASK_MODEL_PANEL
		.getJGraphScrollPaneParent().getSize();
	double d1 = dimension2.getWidth() / dimension.getWidth();
	double d2 = dimension2.getHeight() / dimension.getHeight();
	d = Math.min(Math.min(d1, d2), 1d);
	GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().setScale(d);
    }

    public static void setJustifyTaskModel() {
	if (myDefaultGraphCellRef == null) {
	    return;
	}

	KMADETreeLayoutAlgorithm layout = new KMADETreeLayoutAlgorithm();// CircleGraphLayout()
	layout.setCenterRoot(myDefaultGraphCellRef.isRootTask());
	layout.setCombineLevelNodes(true);
	layout.setAlignment(1);
	layout.setOrientation(1);
	layout.setNodeDistance(KMADEConstant.TASK_DISTANCE);
	layout.setLevelDistance(KMADEConstant.LEVEL_DISTANCE);
	KMADEGraphLayoutAlgorithm.applyLayout(
		GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph(), layout,
		GraphicEditorAdaptator.TASK_MODEL_PANEL.getJGraph().getRoots(),
		new Object[] { myDefaultGraphCellRef });
    }

    public static void showOverviewWindow() {
	MAIN_FRAME.getPreviewWindow().setVisible(
		!MAIN_FRAME.getPreviewWindow().isVisible());
    }

    public static void notifLocalisationModification() {
	MAIN_FRAME.notifLocalisationModification();
    }
}

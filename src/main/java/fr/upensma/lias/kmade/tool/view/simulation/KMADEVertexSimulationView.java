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
package fr.upensma.lias.kmade.tool.view.simulation;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import org.jgraph.JGraph;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphContext;

import fr.upensma.lias.kmade.kmad.schema.expression.SemanticErrorException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticException;
import fr.upensma.lias.kmade.kmad.schema.expression.SemanticUnknownException;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEDefaultGraphCell;
import fr.upensma.lias.kmade.tool.view.taskmodel.KMADEVertexView;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEVertexSimulationView extends KMADEVertexView {
    private static final float TRANSPARENCY_COEF = 0.70f;

    private static final long serialVersionUID = -6504019067175762415L;

    private static Color PASSIVE_COLOR = new Color(50, 50, 255,
	    (int) (100 * TRANSPARENCY_COEF));

    private static Color ACTIVE_COLOR = new Color(210, 210, 255,
	    (int) (100 * TRANSPARENCY_COEF));

    private static Color FINISHED_COLOR = new Color(153, 153, 153,
	    (int) (100 * TRANSPARENCY_COEF));

    private static Color PASSED_COLOR = new Color(51, 204, 0,
	    (int) (100 * TRANSPARENCY_COEF));

    private static Color SUSPENDED_COLOR = new Color(255, 0, 0,
	    (int) (100 * TRANSPARENCY_COEF));

    private static Color NO_RESUMED_COLOR = new Color(255, 255, 0,
	    (int) (100 * TRANSPARENCY_COEF));

    public KMADEVertexSimulationView(Object cell, JGraph myGraph) {
	super(cell, myGraph);
    }

    public String getStateMessage() {
	KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexSimulationView.this
		.getCell();
	String message = "";
	if (myGraphCell.getTask().getStateSimulation().isActive()) {
	    if (myGraphCell.getTask().getIterExpression().isMoreOneIteration()) {
		message = KMADEConstant.ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE;
	    } else {
		message = KMADEConstant.ACTIVE_STATE_TASK_SIMULATION_MESSAGE;
	    }
	} else if (myGraphCell.getTask().getStateSimulation().isFinished()) {
	    message = KMADEConstant.FINISHED_STATE_TASK_SIMULATION_MESSAGE;
	} else if (myGraphCell.getTask().getStateSimulation().isPassive()) {
	    if (myGraphCell.getTask().getIterExpression().isMoreOneIteration()) {
		message = KMADEConstant.ACTIVE_ITERATION_STATE_TASK_SIMULATION_MESSAGE;
		;
	    } else {
		message = KMADEConstant.PASSIVE_STATE_TASK_SIMULATION_MESSAGE;
	    }
	} else if (myGraphCell.getTask().getStateSimulation().isNotAccessible()) {
	    message = KMADEConstant.NO_ACCESSIBLE_STATE_TASK_SIMULATION_MESSAGE;
	} else if (myGraphCell.getTask().getStateSimulation().isPassed()) {
	    message = KMADEConstant.PASSED_STATE_TASK_SIMULATION_MESSAGE;
	} else if (myGraphCell.getTask().getStateSimulation().isSuspended()) {
	    message = KMADEConstant.SUSPENDED_STATE_TASK_SIMULATION_MESSAGE;
	} else if (myGraphCell.getTask().getStateSimulation().isNoResumed()) {
	    message = KMADEConstant.NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE;
	}

	if (myGraphCell.getTask().getStateSimulation().isSuspendedSubTask()) {
	    message = KMADEConstant.SUSPENDED_STATE_TASK_SIMULATION_MESSAGE;
	}

	return message;
    }

    public Color getStateColor() {
	KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexSimulationView.this
		.getCell();
	Color stateTaskColor = null;
	if (myGraphCell.getTask().getStateSimulation().isActive()) {
	    stateTaskColor = KMADEVertexSimulationView.ACTIVE_COLOR;
	} else if (myGraphCell.getTask().getStateSimulation().isFinished()) {
	    stateTaskColor = KMADEVertexSimulationView.FINISHED_COLOR;
	} else if (myGraphCell.getTask().getStateSimulation().isPassive()) {
	    if (myGraphCell.getTask().getIterExpression().isMoreOneIteration()) {
		stateTaskColor = KMADEVertexSimulationView.ACTIVE_COLOR;
	    } else {
		stateTaskColor = KMADEVertexSimulationView.PASSIVE_COLOR;
	    }
	} else if (myGraphCell.getTask().getStateSimulation().isNotAccessible()) {
	} else if (myGraphCell.getTask().getStateSimulation().isPassed()) {
	    stateTaskColor = KMADEVertexSimulationView.PASSED_COLOR;
	} else if (myGraphCell.getTask().getStateSimulation().isSuspended()) {
	    stateTaskColor = KMADEVertexSimulationView.SUSPENDED_COLOR;
	} else if (myGraphCell.getTask().getStateSimulation().isNoResumed()) {
	    stateTaskColor = KMADEVertexSimulationView.NO_RESUMED_COLOR;
	}

	if (myGraphCell.getTask().getStateSimulation().isSuspendedSubTask()) {
	    stateTaskColor = KMADEVertexSimulationView.SUSPENDED_COLOR;
	}

	return stateTaskColor;
    }

    public Rectangle2D getBounds() {
	Rectangle2D temp = super.getBounds();

	JPanel myPanel = new JPanel();

	FontMetrics fstate = myPanel
		.getFontMetrics(KMADEConstant.TASK_NUM_FONT);
	String state = KMADEConstant.VERTEX_STATE_LETTER
		+ KMADEConstant.NO_RESUMED_STATE_TASK_SIMULATION_MESSAGE;

	int newWidth = 16 + 32 + 4 + 4 * state.length()
		+ fstate.stringWidth(state) + 10;

	if (temp.getWidth() < newWidth) {
	    return new Rectangle2D.Double(temp.getX(), temp.getY(), newWidth,
		    temp.getHeight());
	} else {
	    return new Rectangle2D.Double(temp.getX(), temp.getY(),
		    temp.getWidth(), temp.getHeight());
	}
    }

    public CellViewRenderer getRenderer() {
	return new MyVertexSimulationRenderer();
    }

    public CellHandle getHandle(GraphContext context) {
	return new MyCellHandle2();
    }

    class MyCellHandle2 implements CellHandle {
	public void paint(Graphics g) {
	}

	public void overlay(Graphics g) {
	}

	public void mouseMoved(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	    int mx = (int) (event.getX() / myGraph.getScale());
	    int my = (int) (event.getY() / myGraph.getScale());
	    Rectangle2D rec = getBounds();
	    int tolerance = 2;
	    int rx = (int) (rec.getX()) + ((int) rec.getWidth() / 2)
		    - IMAGE_32_WIDTH / 2 - IMAGE_16_WIDTH - V_GAP - tolerance;
	    int ry = (int) rec.getY() + BEGIN_V_GAP + IMAGE_16_HEIGH / 2
		    - tolerance;
	    int rw = rx + 16 + 2 * tolerance;
	    int rh = ry + 16 + 2 * tolerance;
	    if (mx >= rx && mx <= rw && my >= ry && my <= rh) {
		KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) getCell();
		if (!myGraphCell.isMotherPortNoEdged()) {
		    myGraphCell.setSonSimulationExpanded(!myGraphCell
			    .isSonSimulationExpanded());
		    myGraph.repaint();
		}
	    }
	}

	public void mouseDragged(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}
    }

    protected String getIterationName(KMADEDefaultGraphCell myGraphCell) {
	String iteration = KMADEConstant.VERTEX_ITER_LETTER;
	if (myGraphCell.getTask().getIterExpression().isErrorInExpression()) {
	    iteration += KMADEConstant.ERROR_ITERATION_VIEW_MESSAGE;
	} else {
	    if (myGraphCell.getTask().getIterExpression()
		    .isVariableExpressionNode()) {
		iteration += String.valueOf(myGraphCell.getTask()
			.getIterExpression().getIterationVariant())
			+ "/"
			+ myGraphCell.getTask().getIterExpression()
				.getNodeExpression().getNodeValue();
	    } else {
		if (myGraphCell.getTask().getStateSimulation().isActive()
			|| myGraphCell.getTask().getStateSimulation()
				.isPassive()) {
		    try {
			myGraphCell.getTask().getIterExpression()
				.getNodeExpression().evaluateNode(null);
			iteration += myGraphCell.getTask().getIterExpression()
				.getNodeExpression().getNodeValue();
		    } catch (SemanticErrorException e) {
			iteration += KMADEConstant.ERROR_ITERATION_VIEW_MESSAGE;
		    } catch (SemanticUnknownException e) {
			iteration += KMADEConstant.USER_VALUE_ITERATION_VIEW_MESSAGE;
		    } catch (SemanticException e) {
		    }
		} else {
		    iteration += KMADEConstant.PREDICAT_ITERATION_VIEW_MESSAGE;
		}
	    }
	}
	return iteration;
    }

    class MyVertexSimulationRenderer extends MyVertexRenderer {

	private static final long serialVersionUID = 5060836696940302890L;

	protected void paintLabelCaract(Graphics2D f,
		KMADEDefaultGraphCell myCell) {
	    if (GraphicEditorAdaptator.getMainFrame().getSimulationDialog()
		    .getSimulationToolBar().isLabelColorSelected()) {
		if (myCell.getTask().getLabel() != null) {
		    if (myCell.getTask().getLabel().isColorVisible()) {
			Color taskColor = myCell.getTask().getLabel()
				.getColor();
			Color old = f.getColor();
			Color colorWithTransparency = new Color(
				taskColor.getRed(), taskColor.getGreen(),
				taskColor.getBlue(), (int) (100 * 0.70f));
			f.setColor(colorWithTransparency);
			f.fillRect(0, 0, this.getBounds().width,
				this.getBounds().height);
			f.setColor(old);
		    }
		}
	    }
	}

	/**
	 * permet l'affichage du texte et de la couleur concernant l'état de la
	 * tâche ainsi que les informations d'it�ration
	 * 
	 * @param gr
	 *            le graphix
	 * @param widthString
	 *            - n'est plus utilisé
	 * @param numString
	 *            - n'est plus utilisé
	 * @param iterString
	 *            - la chaine pour itération
	 * @param num
	 *            - la position horizontale du texte pour l'état
	 * @param iter
	 *            - la position de la fin de la chaine pour l'it�ration
	 */
	protected void paintStateCaract(Graphics2D gr, int widthString,
		String numString, String iterString, double num, double iter) {
	    gr.setFont(KMADEConstant.TASK_NUM_FONT);
	    FontMetrics fm = gr.getFontMetrics();
	    // Rectangle2D rState = fm.getStringBounds("S:", gr);
	    int height = fm.getAscent();
	    int iterWidth = fm.stringWidth(iterString);

	    // int V_NUM_ITE_GAP = (int)(IMAGE_32_HEIGH - num - iter -
	    // rState.getHeight()) / 4;
	    // gr.drawString(numString, widthString, BEGIN_V_GAP + V_NUM_ITE_GAP
	    // + (int)num);
	    // gr.drawString(iterString, widthString, BEGIN_V_GAP +
	    // V_NUM_ITE_GAP + (int)num + V_NUM_ITE_GAP + (int)iter);

	    Color stateTaskColor = KMADEVertexSimulationView.this
		    .getStateColor();
	    String message = KMADEVertexSimulationView.this.getStateMessage();
	    gr.setColor(Color.BLACK);

	    // gr.drawString("S:" + message, widthString, BEGIN_V_GAP +
	    // V_NUM_ITE_GAP + (int)num + V_NUM_ITE_GAP + (int)iter +
	    // V_NUM_ITE_GAP + (int)rState.getHeight());
	    // gr.drawString("I:" + iterString, widthString, BEGIN_V_GAP +
	    // V_NUM_ITE_GAP + (int)num + V_NUM_ITE_GAP + (int)iter);
	    double marge = 2;
	    gr.drawString(KMADEConstant.VERTEX_STATE_LETTER + message,
		    (int) (num + marge), height);
	    gr.drawString(iterString, (int) (iter - iterWidth - marge), height);

	    // Dessin pour visualiser l'état de la tâche.
	    if (stateTaskColor != null) {
		gr.setColor(stateTaskColor);
		gr.fillRect(((int) this.getBounds().getWidth() / 2) - 40, 1,
			80, (int) this.getBounds().getHeight() - 1);
	    }
	}

	public void paint(Graphics gr) {
	    KMADEDefaultGraphCell myGraphCell = (KMADEDefaultGraphCell) KMADEVertexSimulationView.this
		    .getCell();
	    if (!myGraphCell.isSimulationExpanded()) {
		return;
	    }

	    if (GraphicEditorAdaptator.getMainFrame().getSimulationDialog()
		    .getSimulationToolBar().isLabelColorSelected()) {
		if (myGraphCell.getTask().getLabel() != null) {
		    if (!myGraphCell.getTask().getLabel().isVisible()) {
			return;
		    }
		}
	    }
	    Rectangle2D rect = this.getBounds();
	    double widht = rect.getWidth();
	    this.paintTask(gr);
	    String iter = getIterationName(myGraphCell);
	    Graphics2D f = (Graphics2D) gr;
	    // permet l'ajout de la couleur et du texte du � l'�tat de la tache,
	    // f le graph, 0 position selon X, "" n'est plus utile,"",-5 et -20
	    // permettent de determiner la position verticale de l'affichage du
	    // texte
	    paintStateCaract(f, 0, "", iter, 0, widht);
	    if (selected) {
		f.setStroke(GraphConstants.SELECTION_STROKE);
		f.setColor(KMADEConstant.TASK_SELECTION_COLOR);
		f.drawRect(((int) this.getBounds().getWidth() / 2) - 40, 1,
			80 - 1, (int) this.getBounds().getHeight() - 1);
	    }

	    if (!myGraphCell.isMotherPortNoEdged()
		    && (myGraph == GraphicEditorAdaptator.getMainFrame()
			    .getSimulationDialog().getGraphSimulation())) {
		ImageIcon myImageIcon = null;
		if (myGraphCell.isSonSimulationExpanded()) {
		    myImageIcon = KMADEVertexView.EXPAND_TASK_IMAGE_ICON;
		} else {
		    myImageIcon = KMADEVertexView.COLLAPSE_TASK_IMAGE_ICON;
		}
		if (!preview) {
		    myImageIcon.paintIcon(this, gr, ((int) this.getBounds()
			    .getWidth() / 2)
			    - IMAGE_32_WIDTH
			    / 2
			    - IMAGE_16_WIDTH - V_GAP, BEGIN_V_GAP
			    + IMAGE_16_HEIGH / 2);
		}
	    }
	}
    }
}

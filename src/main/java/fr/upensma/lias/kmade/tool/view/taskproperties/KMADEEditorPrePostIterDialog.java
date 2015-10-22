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
package fr.upensma.lias.kmade.tool.view.taskproperties;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.text.JTextComponent;

import fr.upensma.lias.kmade.kmad.schema.tache.SideEffectExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.IterExpression;
import fr.upensma.lias.kmade.kmad.schema.tache.PreExpression;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEBaseIterationPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEBasePreconditionPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEEffetsDeBordPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEIterationPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors.KMADEPreconditionPanel;
import fr.upensma.lias.kmade.tool.view.taskproperties.readworldobject.KMADEReadAbstractObjectPanel;
import fr.upensma.lias.kmade.tool.view.toolutilities.DefaultPropertiesTableModel;
import fr.upensma.lias.kmade.tool.view.toolutilities.JPropertiesEditorDialog;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEToolUtilities;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.PrePostIterExpressionAdaptator;

/**
 * @author Mickael BARON
 */
public class KMADEEditorPrePostIterDialog extends JPropertiesEditorDialog {   

    private static final long serialVersionUID = 529537907422650640L;

    private static final KMADEPreconditionPanel refPreconditionPanel = new KMADEPreconditionPanel();

    private static final KMADEEffetsDeBordPanel refEffetsDeBordPanel = new KMADEEffetsDeBordPanel();

    private static final KMADEIterationPanel refIterconditionPanel = new KMADEIterationPanel();

    private static final KMADEReadAbstractObjectPanel refReadObject = new KMADEReadAbstractObjectPanel();

    private JPanel cardPanel;

    public KMADEEditorPrePostIterDialog() {
	super();
	this.setModal(false);
	cardPanel = new JPanel(new CardLayout());
	cardPanel.add(refPreconditionPanel, "PRECONDITION");
	cardPanel.add(refEffetsDeBordPanel, "EFFETSDEBORD");
	cardPanel.add(refIterconditionPanel, "ITERATION");
	JSplitPane mySplitPane = KMADEEnhancedSplitPane
		.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
			cardPanel, refReadObject);
	cardPanel.setMinimumSize(new Dimension(250, 400));
	mySplitPane.setOneTouchExpandable(true);
	mySplitPane.setDividerLocation(800);
	mySplitPane.setResizeWeight(0.55);
	ScrollPane scrollPane = new ScrollPane();
	Dimension dim = new Dimension(1300, 900);

	scrollPane.setPreferredSize(dim);
	scrollPane.add(mySplitPane);
	this.setLayout(new BorderLayout());
	this.getContentPane().add(BorderLayout.CENTER, scrollPane);
	this.pack();
	if(Toolkit.getDefaultToolkit().getScreenSize().height< dim.height){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
	}else{
		this.setPreferredSize(dim);
		KMADEToolUtilities.setCenteredInScreen(this);
		}
	this.setResizable(true);
    }

    public void showPropertiesEditor(DefaultPropertiesTableModel refModel,
	    int row) {
	PrePostIterExpressionAdaptator.initExpression();
	CardLayout cl = (CardLayout) (cardPanel.getLayout());
	if (refModel.getValue(row) instanceof PreExpression) {
	    this.setTitle(KMADEConstant.EDITOR_PRE_TITLE_NAME
		    + " : "
		    + GraphicEditorAdaptator.getSelectedGraphicTask().getTask()
			    .getName());
	    refPreconditionPanel.setOutputMessage();
	    refPreconditionPanel.getTextArea().setText(((PreExpression) refModel
		    .getValue(row)).getFormalText());
	    refPreconditionPanel.setDescriptionArea(((PreExpression) refModel
		    .getValue(row)).getDescription());
	    // Sélection de l'onglet pour la précondition
	    cl.show(cardPanel, "PRECONDITION");
	    PrePostIterExpressionAdaptator.setToPreCondition();
	    refPreconditionPanel.getProtoTaskPreconditionPanel().updateDataModel();
	} else if (refModel.getValue(row) instanceof SideEffectExpression) {
	    this.setTitle(KMADEConstant.EDITOR_POST_TITLE_NAME
		    + " : "
		    + GraphicEditorAdaptator.getSelectedGraphicTask().getTask()
			    .getName());
	    refEffetsDeBordPanel.setOutputMessage();
	    refEffetsDeBordPanel.textArea
		    .setText(((SideEffectExpression) refModel.getValue(row))
			    .getFormalText());
	    refEffetsDeBordPanel
		    .setDescriptionArea(((SideEffectExpression) refModel
			    .getValue(row)).getDescription());
	    // Sélection de l'onglet pour la effetsdebord
	    cl.show(cardPanel, "EFFETSDEBORD");
	    PrePostIterExpressionAdaptator.setToEffetsDeBord();
	} else if (refModel.getValue(row) instanceof IterExpression) {
	    this.setTitle(KMADEConstant.EDITOR_ITER_TITLE_NAME
		    + " : "
		    + GraphicEditorAdaptator.getSelectedGraphicTask().getTask()
			    .getName());
	    refIterconditionPanel.setOutputMessage();
	    refIterconditionPanel.getBaseIterationPanel().textArea.setText(((IterExpression) refModel
		    .getValue(row)).getFormalText());
	    refIterconditionPanel.setDescriptionArea(((IterExpression) refModel
		    .getValue(row)).getDescription());
	    // Selection de l'onglet pour l'iteration
	    cl.show(cardPanel, "ITERATION");
	    PrePostIterExpressionAdaptator.setToIterationCondition();
	    refIterconditionPanel.getProtoTaskIterationPanel().updateDataModel();

	}
	PrePostIterExpressionAdaptator.disabledFrame();
	// Va falloir identifier s'il s'agit d'une précondition, d'une itération
	// ou d'une effetsdebord
	super.showPropertiesEditor(refModel, row);
    }

    protected void stopEditorDialog() {
	PrePostIterExpressionAdaptator.finishExpressionEdition();
    }

    public static KMADEBasePreconditionPanel getPreconditionPanel() {
	return refPreconditionPanel.getBasePreconditionPanel();
    }
    
    public static KMADEPreconditionPanel getAllPreconditionPanel() {
    	return refPreconditionPanel;
        }
    public static KMADEIterationPanel getAllIterationPanel() {
    	return refIterconditionPanel;
        }
    
    public static KMADEEffetsDeBordPanel getPostonditionPanel() {
	return refEffetsDeBordPanel;
    }

    public static KMADEBaseIterationPanel getIterationPanel() {
	return refIterconditionPanel.getBaseIterationPanel();
    }

    public static KMADEReadAbstractObjectPanel getReadAbstractObjectPanel() {
	return refReadObject;
    }

    public void notifLocalisationModification() {
	super.notifLocalisationModification();

	refReadObject.notifLocalisationModification();
    }
}

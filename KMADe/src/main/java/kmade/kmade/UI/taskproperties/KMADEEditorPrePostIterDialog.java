package kmade.kmade.UI.taskproperties;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import kmade.kmade.KMADEConstant;
import kmade.kmade.UI.taskproperties.constrainteditors.KMADEIterationPanel;
import kmade.kmade.UI.taskproperties.constrainteditors.KMADEPostconditionPanel;
import kmade.kmade.UI.taskproperties.constrainteditors.KMADEPreconditionPanel;
import kmade.kmade.UI.taskproperties.readworldobject.KMADEReadAbstractObjectPanel;
import kmade.kmade.UI.toolutilities.DefaultPropertiesTableModel;
import kmade.kmade.UI.toolutilities.JPropertiesEditorDialog;
import kmade.kmade.UI.toolutilities.KMADEEnhancedSplitPane;
import kmade.kmade.UI.toolutilities.KMADEToolUtilities;
import kmade.kmade.adaptatorUI.GraphicEditorAdaptator;
import kmade.kmade.adaptatorUI.PrePostIterExpressionAdaptator;
import kmade.nmda.schema.tache.IterExpression;
import kmade.nmda.schema.tache.PostExpression;
import kmade.nmda.schema.tache.PreExpression;

/**
 * K-MADe : Kernel of Model for Activity Description environment
 * Copyright (C) 2006  INRIA - MErLIn Project
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Mickaël BARON (mickael.baron@inria.fr ou baron.mickael@gmail.com)
 **/
public class KMADEEditorPrePostIterDialog extends JPropertiesEditorDialog {   
    private static final long serialVersionUID = 529537907422650640L;

    private static final KMADEPreconditionPanel refPreconditionPanel = new KMADEPreconditionPanel();

    private static final KMADEPostconditionPanel refPostconditionPanel = new KMADEPostconditionPanel();

    private static final KMADEIterationPanel refIterconditionPanel = new KMADEIterationPanel();
    
    private static final KMADEReadAbstractObjectPanel refReadObject = new KMADEReadAbstractObjectPanel();
        
    private JPanel cardPanel;
    
    public KMADEEditorPrePostIterDialog() {
        super();
        this.setModal(false);
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(refPreconditionPanel,"PRECONDITION");
        cardPanel.add(refPostconditionPanel,"POSTCONDITION");
        cardPanel.add(refIterconditionPanel, "ITERATION");
                     
        JSplitPane mySplitPane = KMADEEnhancedSplitPane.createStrippedSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                cardPanel, 
                refReadObject);
        cardPanel.setMinimumSize(new Dimension(250,400));
        mySplitPane.setOneTouchExpandable(true);
        mySplitPane.setDividerLocation(560);

        this.getContentPane().add(BorderLayout.CENTER, mySplitPane);
        this.setPreferredSize(new Dimension(1010, 700));
        this.pack();
        KMADEToolUtilities.setCenteredInScreen(this);
    }
    
    public void showPropertiesEditor(DefaultPropertiesTableModel refModel, int row) {
        PrePostIterExpressionAdaptator.initExpression();
        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        if (refModel.getValue(row) instanceof PreExpression) {
            this.setTitle(KMADEConstant.EDITOR_PRE_TITLE_NAME + " : " + GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getName());
            refPreconditionPanel.setOutputMessage();
            refPreconditionPanel.textArea.setText(((PreExpression)refModel.getValue(row)).getName());
            refPreconditionPanel.setDescriptionArea(((PreExpression)refModel.getValue(row)).getDescription());
            // Sélection de l'onglet pour la précondition
            cl.show(cardPanel,"PRECONDITION");
            PrePostIterExpressionAdaptator.setToPreCondition();
        } else if (refModel.getValue(row) instanceof PostExpression) {
        		this.setTitle(KMADEConstant.EDITOR_POST_TITLE_NAME + " : " + GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getName());
            refPostconditionPanel.setOutputMessage();
            refPostconditionPanel.textArea.setText(((PostExpression)refModel.getValue(row)).getName());
            refPostconditionPanel.setDescriptionArea(((PostExpression)refModel.getValue(row)).getDescription());
            // Sélection de l'onglet pour la postcondition
            cl.show(cardPanel,"POSTCONDITION");
            PrePostIterExpressionAdaptator.setToPostCondition();
        } else if (refModel.getValue(row) instanceof IterExpression) {
            this.setTitle(KMADEConstant.EDITOR_ITER_TITLE_NAME + " : " + GraphicEditorAdaptator.getSelectedGraphicTask().getTask().getName());
            refIterconditionPanel.setOutputMessage();
            refIterconditionPanel.textArea.setText(((IterExpression)refModel.getValue(row)).getName());
            refIterconditionPanel.setDescriptionArea(((IterExpression)refModel.getValue(row)).getDescription());
            // Sélection de l'onglet pour l'itération
            cl.show(cardPanel,"ITERATION");
            PrePostIterExpressionAdaptator.setToIterationCondition();
        } 
        PrePostIterExpressionAdaptator.disabledFrame();    
        // Va falloir identifier s'il s'agit d'une précondition, d'une itération ou d'une postcondition
        super.showPropertiesEditor(refModel, row);
    }   

    protected void stopEditorDialog() { 
        PrePostIterExpressionAdaptator.finishExpressionEdition();
    }    

    public static KMADEPreconditionPanel getPreconditionPanel() {
        return refPreconditionPanel;
    }
    
    public static KMADEPostconditionPanel getPostonditionPanel() {
        return refPostconditionPanel;
    }
    
    public static KMADEIterationPanel getIterationPanel() {
        return refIterconditionPanel;
    }
    
    public static KMADEReadAbstractObjectPanel getReadAbstractObjectPanel() {
        return refReadObject;
    }
    
    public void notifLocalisationModification() {
        super.notifLocalisationModification();
        
        refReadObject.notifLocalisationModification();
    }
}

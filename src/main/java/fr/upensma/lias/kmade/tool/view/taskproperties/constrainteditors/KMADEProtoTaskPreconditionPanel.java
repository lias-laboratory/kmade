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
package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.ConditionAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

/**
 * @author Thomas LACHAUME
 */
public class KMADEProtoTaskPreconditionPanel extends JPanel implements
	LanguageFactory {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5857696613018213441L;

    private final KMADEReadProtoTaskTaskPreCondition taskCondition = new KMADEReadProtoTaskTaskPreCondition();

    private final KMADEReadProtoTaskWorldConditionPre worldCondition = new KMADEReadProtoTaskWorldConditionPre();

    public KMADEProtoTaskPreconditionPanel() {

	JSplitPane mySplitPane = KMADEEnhancedSplitPane
		.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
			taskCondition, worldCondition);
	this.add(mySplitPane, BorderLayout.CENTER);
	//this.setPreferredSize(new Dimension(800, 800));

    }

    public void updateDataModel() {
	taskCondition.updateDataModel(GraphicEditorAdaptator
		.getSelectedGraphicTask().getTask().getPreExpression()
		.getProtoTaskConditionExpression().getValue()
		.getProtoTaskConditionIntoTab());
	worldCondition.updateDataModel(ConditionAdaptator
		.getProtoTaskConditionsIntoTab());
    }

    @Override
    public void notifLocalisationModification() {
    }
}

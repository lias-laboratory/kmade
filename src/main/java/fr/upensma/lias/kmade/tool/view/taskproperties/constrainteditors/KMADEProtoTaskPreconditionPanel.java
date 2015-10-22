package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEEnhancedSplitPane;
import fr.upensma.lias.kmade.tool.view.toolutilities.LanguageFactory;
import fr.upensma.lias.kmade.tool.viewadaptator.ConditionAdaptator;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;

public class KMADEProtoTaskPreconditionPanel extends JPanel implements LanguageFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857696613018213441L;

	private final KMADEReadProtoTaskTaskPreCondition taskCondition = new KMADEReadProtoTaskTaskPreCondition();

	private final KMADEReadProtoTaskWorldConditionPre worldCondition = new KMADEReadProtoTaskWorldConditionPre();

	public KMADEProtoTaskPreconditionPanel(){

		JSplitPane mySplitPane = KMADEEnhancedSplitPane
				.createStrippedSplitPane(JSplitPane.HORIZONTAL_SPLIT,
						taskCondition, worldCondition);
		this.add(mySplitPane, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(800,800));

	}

	public void updateDataModel(){
		taskCondition.updateDataModel( GraphicEditorAdaptator.getSelectedGraphicTask().getTask()
				.getPreExpression().getProtoTaskConditionExpression().getValue().getProtoTaskConditionIntoTab());
		worldCondition.updateDataModel(ConditionAdaptator.getProtoTaskConditionsIntoTab());	
	}

	@Override
	public void notifLocalisationModification() {
		// TODO Auto-generated method stub

	}

}

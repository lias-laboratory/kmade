package fr.upensma.lias.kmade.tool.view.taskproperties.constrainteditors;

import javax.swing.JComboBox;

import fr.upensma.lias.kmade.kmad.schema.expression.ConcreteObjectType;


public abstract class KMADEGroupTypeComboBox  extends JComboBox {

	private static final long serialVersionUID = 7684513862213729151L;


	
	public KMADEGroupTypeComboBox() {
	}



	public abstract ConcreteObjectType getConcreteObjectType();
	
	public abstract boolean isConcreteObjectEmpty();
	


}

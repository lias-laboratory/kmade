package kmade.kmade.view.taskproperties.constrainteditors;

import javax.swing.JComboBox;

import kmade.nmda.schema.expression.ConcreteObjectType;

public abstract class KMADEGroupTypeComboBox  extends JComboBox {

	private static final long serialVersionUID = 7684513862213729151L;


	
	public KMADEGroupTypeComboBox() {
	}



	public abstract ConcreteObjectType getConcreteObjectType();
	
	public abstract boolean isConcreteObjectEmpty();
	


}

package fr.upensma.lias.kmade.tool.view.toolutilities;

import javax.swing.JTextArea;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class JTextAreaMessageIO extends JTextArea implements MessageIO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6043303314605144118L;

	@Override
	public void printlnMessage(String message) {
		message +="\n";
		this.append(message);		
	}
	
	@Override
	public void printMessage(String message) {
		this.append(message);		
	}

	@Override
	public void printlnError(String message) {
		message +="\n";
		this.append(message);	
	}

	@Override
	public void setOutputMessage() {
		KMADEHistoryMessageManager.setOutputMessage(this);
		
	}

	@Override
	public void setErrputMessage() {
		KMADEHistoryMessageManager.setErrputMessage(this);
		
	}

	@Override
	public void printlnError(Throwable e) {
		this.append(e.getMessage());
		
	}

}

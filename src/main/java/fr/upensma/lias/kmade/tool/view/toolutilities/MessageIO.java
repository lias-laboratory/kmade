package fr.upensma.lias.kmade.tool.view.toolutilities;

public interface MessageIO {

	void printlnMessage(String message);

	void printlnError(String message);
	
	void setOutputMessage();

	void setErrputMessage();

	void printlnError(Throwable e);
	
	void printMessage(String message);

}

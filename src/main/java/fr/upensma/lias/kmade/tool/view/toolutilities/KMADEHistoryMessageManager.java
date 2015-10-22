package fr.upensma.lias.kmade.tool.view.toolutilities;

import java.util.ArrayList;

public class KMADEHistoryMessageManager {
	
		private static ArrayList<MessageIO> myMessagePanels = new ArrayList<MessageIO>();
		private static ArrayList<MessageIO> myErrorPanels = new ArrayList<MessageIO>();

	  KMADEHistoryMessageManager(){
		  
	  }
	
	  
	  /**
	 * @param aPanel
	 */
	public static void setOutputMessage(MessageIO aPanel){
		  if(myMessagePanels.contains(aPanel)){
			  myMessagePanels.remove(aPanel);
		  }
		  myMessagePanels.add(0,aPanel);
	  }
	
	public static void setErrputMessage(MessageIO aPanel){
		  if(myErrorPanels.contains(aPanel)){
			  myErrorPanels.remove(aPanel);
		  }
		  myMessagePanels.add(0,aPanel);
	  }
	
	public static void remove(MessageIO aPanel){
		if(myMessagePanels.contains(aPanel)){
			myMessagePanels.remove(aPanel);
		}
	}
	
	public static void printlnMessage(String message){
		if(!myMessagePanels.isEmpty()){
			myMessagePanels.get(0).printlnMessage(message);
		}else{
			System.out.println(message);
		}
	}
	
	public static void printMessage(String message){
		if(!myMessagePanels.isEmpty()){
			myMessagePanels.get(0).printlnMessage(message);
		}else{
			System.out.print(message);
		}
	}
	
	public static void printlnError(String message){
		if(!myErrorPanels.isEmpty()){
			myErrorPanels.get(0).printlnError(message);
		}else{
			System.err.println(message);
		}
	}
	
	public static void printlnError(Throwable message){
		if(!myErrorPanels.isEmpty()){
			myErrorPanels.get(0).printlnError(message);
		}else{
			System.err.println(message);
		}
	}
	
	
}

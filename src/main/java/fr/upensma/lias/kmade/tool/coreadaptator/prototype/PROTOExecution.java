package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.sun.crypto.provider.DESCipher;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.KMADEConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.view.toolutilities.KMADEHistoryMessageManager;
import fr.upensma.lias.kmade.tool.viewadaptator.GraphicEditorAdaptator;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class PROTOExecution {

	private static Tache currentTask;
	private static HashMap <String,ChoiceEnum> map = new HashMap<String, ChoiceEnum>();
	private static ArrayList<String> expressions = new ArrayList<String>();

	public static void startExecution() {
		PROTOHistoric.clearHisto();
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		if (root.size() == 1) {
			// normal execution with one mother task
			setAllChildStateExecution(root.get(0), StateExecution.INACTIVE);
			getAllExpressions(root.get(0));
			setCurrentTask(root.get(0),true,null);
		} else {
			// two or more mother tasks...
		}
	}



	//modification of task state
	public static void setCurrentTask(Tache t, boolean historic,HashMap<String,ChoiceEnum> map) {
		int reponse = 42;
		/*if(t.getPreExpression()!= null && t.getPreExpression().getDescription()!=null && !t.getPreExpression().getDescription().equals("")){
			reponse = JOptionPane.showConfirmDialog(null, KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TEXT_PANE +" : "+ t.getPreExpression().getDescription(),KMADEConstant.PROTOTYPING_TOOL_PRECONDITION_TITLE_PANE, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);

		}*/
		remanance();

		switch(reponse){
		case JOptionPane.YES_OPTION :
			PROTOHistoric.addPrecondition(t,true);
		case 42 : 	
			currentTask = t;
			if(historic){
				PROTOHistoric.addTask(t);
			}
			t.setStateExecution(StateExecution.ACTIVE);
			execution();
			break;
		case JOptionPane.NO_OPTION :
			PROTOHistoric.addPrecondition(t,false);
			break;
		default:

		}

	}




	private static void execution() {
		
		String displayPrecondition= "";
		if(currentTask.getMotherTask()!=null)
		displayPrecondition = displayCondition(currentTask.getPreExpression().getDescription());

		// update displayed task
		// update information
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setCurrentTask(currentTask, displayPrecondition);
		GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, false);

		switch (currentTask.getStateExecution()) {
		case INACTIVE:
			//never happen

		case PASSIVE:
			//never happen
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_PASSIVE_INACTIVE_ERROR);
			return;
		case ACTIVE:
			// depend of ordonnancement
			switch (currentTask.getOrdonnancement()) {
			case ELE:
				// Elementary task have to change in WAIT_END state
				elementarycase(map);

				break;
			case SEQ:
				sequentialCase();
				break;
			case ALT:
				alternatifCase();
				break;
			case ET:
				noOrderCase(map);
				break;
			case PAR:
				// TODO now it's no order but need to change it
				noOrderCase(map);
				break;
			}
			break;
		case WAITEND:
			switch (currentTask.getOrdonnancement()) {
			case SEQ:
				sequentialCase();
				break;
			case ALT:
				alternatifCase();
				break;
			case ET:
				noOrderCase(map);
			default:
			}
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setEnabledEnd(currentTask, true);
			iteration(map);
			// TODO une tâche en attente de fin peut être répété si il y a lieu


			break;
		case FINISHED:
			// if the task is in finish state we need to go up in the tree
			if(currentTask.getOrdonnancement() != Decomposition.ELE){
				PROTOHistoric.addFinished(currentTask);
			}
			if (currentTask.getMotherTask() != null) {
				currentTask = currentTask.getMotherTask();
				execution();
			} else {
				// TODO display simulation end
			}
			break;
		}

	}






	private static void iteration(HashMap<String, ChoiceEnum> map) {
		if((currentTask.getIteExpression().getDescription()!= null) && !currentTask.getIteExpression().getDescription().equals("") 
				&& (map!=null && map.get(currentTask.getIteExpression().getDescription())==ChoiceEnum.vrai)){	
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask,true,ChoiceEnum.vrai);
		}else{
			if((currentTask.getIteExpression().getDescription()== null) || currentTask.getIteExpression().getDescription().equals("") ){
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask,false,ChoiceEnum.indeterminée);
			}else{
				ChoiceEnum choiceE;
				if(map==null || map.get(currentTask.getIteExpression().getDescription())==null){
					choiceE = ChoiceEnum.indeterminée;
				}else{
					choiceE = map.get(currentTask.getIteExpression().getDescription());
				}
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setIterationEnabled(currentTask,true,choiceE);
			}}




	}

	public static void setAllChildStateExecution(Tache t, StateExecution s) {
		if (t.getFils() != null) {
			ArrayList<Tache> child = t.getFils();
			for (Tache tache : child) {
				tache.setStateExecution(s);
				setAllChildStateExecution(tache, s);
			}
		}
	}

	public static void finishedTask(Tache t,HashMap<String,ChoiceEnum> map) {
		if (currentTask.equals(t)) {
			PROTOHistoric.remonter();
			currentTask.setStateExecution(StateExecution.FINISHED);
			GraphicEditorAdaptator.getMainFrame().getPrototypeDialog()
			.setEnabledEnd(currentTask, false);
			execution();
		} else {
			KMADEHistoryMessageManager.printlnError(KMADEConstant.PROTOTYPING_TOOL_END_ERROR + " : "
					+ t.getName());
		}
	}



	private static void elementarycase(HashMap<String, ChoiceEnum> map) {
		currentTask.setStateExecution(StateExecution.WAITEND);
		execution();

	}


	private static void alternatifCase() {

		// si le choix a été fait la tâche ne peut que se terminer
		boolean choice = false;
		for (Tache t : currentTask.getFils()) {
			if (t.getStateExecution() == StateExecution.FINISHED) {
				choice = true;
			}
		}
		for (Tache t : currentTask.getFils()) {
			String displayCondition = displayCondition(t.getPreExpression().getDescription());
			if (t.getStateExecution() == StateExecution.FINISHED) {
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,true,false,0,null, null,displayCondition);
			} else {
				HashMap<String, ChoiceEnum> secondary = new HashMap<String, ChoiceEnum>();
				HashMap<String, ChoiceEnum> primary =  new HashMap<String, ChoiceEnum>();
				List<String> list = preonditionEvaluation(t.getPreExpression().getDescription());
				if (list!=null){

					primary.put(list.get(0),map.get(list.get(0)));
					System.out.println("map get"+map.get(list.get(0)));
					for(int i=1; i< list.size();i++){
						secondary.put(list.get(i),map.get(list.get(i)));
					}
					if(map.get(list.get(0))==ChoiceEnum.vrai)
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,(true&&!choice),0,primary,secondary,displayCondition);
					else
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,false,0,primary,secondary,displayCondition);
				}else{	
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,(true&&!choice),0,null,null,displayCondition);
				}
			}
		}
		if (choice) {
			if (currentTask.getStateExecution() != StateExecution.WAITEND) {
				currentTask.setStateExecution(StateExecution.WAITEND);
				execution();
			}
		}
	}

	private static void sequentialCase() {
		boolean futurActiveFound = false;
		Tache lastfinished = null;
		for(Tache t : currentTask.getFils()){
			if(t.getStateExecution() == StateExecution.FINISHED){
				lastfinished = t;
			}
		}
		int number = 1;
		for (Tache t : currentTask.getFils()) {
			String displayCondition = displayCondition(t.getPreExpression().getDescription());
			if (t.getStateExecution() == StateExecution.FINISHED) {
				// il faut afficher cette sous tâche comme terminée
				// faire affichage sous tâche non exécutable
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,true,false, number++,null,null,displayCondition);
			} else {

				// si la tâche n'est pas terminée il faut regarder si la
				// dernière active lançeable a été trouvée
				if (futurActiveFound || currentTask.getFils().indexOf(lastfinished)>currentTask.getFils().indexOf(t)) {
					// afficher la tâche comme non éxécutable
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,false,number++,null,null,displayCondition);

				} else {
					// la tâche est éxécutable il faut l'affiché pour pouvoir la
					// lancer
					HashMap<String, ChoiceEnum> secondary = new HashMap<String, ChoiceEnum>();
					HashMap<String, ChoiceEnum> primary =  new HashMap<String, ChoiceEnum>();
					List<String> list = preonditionEvaluation(t.getPreExpression().getDescription());
					if (list!=null){
						primary.put(list.get(0),map.get(list.get(0)));
						System.out.println("map get"+map.get(list.get(0)));
						for(int i=1; i< list.size();i++){
							secondary.put(list.get(i),map.get(list.get(i)));
						}
						if(map.get(list.get(0))==ChoiceEnum.vrai){
							GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,true,number++,primary,secondary,displayCondition);
						}else{
							GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,false,number++,primary,secondary,displayCondition);
						}
					}else{
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,true, number++,null,null,displayCondition);
					}

					// faire appel fonction avec un parametre qui prend
					// l'optionnalité
					if (!t.getFacultatif()) {
						// on ne met que futurActiveFound que si la tâche est
						// non optionnel
						futurActiveFound = true;
					}
				}
			}

		}
		if (!futurActiveFound
				&& currentTask.getStateExecution() != StateExecution.WAITEND) {
			// la tâche peut se terminer s'il n'y a pas de futur active trouvé
			// affiché terminaison possible
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution();
		}
	}

	private static void noOrderCase(HashMap<String,ChoiceEnum> map) {
		int nbFinished = 0;
		for (Tache t : currentTask.getFils()) {
			String displayCondition = displayCondition(t.getPreExpression().getDescription());
			if (t.getStateExecution() == StateExecution.FINISHED) {
				nbFinished++;
				GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,true,false, 0,null,null,displayCondition);
			} else {
				// la tâche est éxécutable il faut l'affiché pour pouvoir la
				// lancer
				HashMap<String, ChoiceEnum> secondary = new HashMap<String, ChoiceEnum>();
				HashMap<String, ChoiceEnum> primary =  new HashMap<String, ChoiceEnum>();
				List<String> list = preonditionEvaluation(t.getPreExpression().getDescription());
				if (list!=null){
					primary.put(list.get(0),map.get(list.get(0)));
					System.out.println("map get"+map.get(list.get(0)));
					for(int i=1; i< list.size();i++){
						secondary.put(list.get(i),map.get(list.get(i)));
					}
					if(map.get(list.get(0))==ChoiceEnum.vrai){
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,true,0,primary,secondary,displayCondition);
					}else{
						GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,false,0,primary,secondary,displayCondition);
					}
				}else{
					GraphicEditorAdaptator.getMainFrame().getPrototypeDialog().setExecutableTask(t,false,true, 0,null,null,displayCondition);
				}


				if (t.getFacultatif()) {
					nbFinished++;
				}
			}
		}
		if (nbFinished == currentTask.getFils().size()
				&& currentTask.getStateExecution() != StateExecution.WAITEND) {
			currentTask.setStateExecution(StateExecution.WAITEND);
			execution();
		}
	}

	public static void repeatCurrentTask(HashMap<String,ChoiceEnum> map) {
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		PROTOHistoric.remonter();
		setCurrentTask(currentTask,true, map);
	}

	public static void cancelTask(HashMap<String,ChoiceEnum> map) {
		PROTOHistoric.deleteCurrentTask();
		currentTask.setStateExecution(StateExecution.INACTIVE);
		setAllChildStateExecution(currentTask, StateExecution.INACTIVE);
		setCurrentTask(currentTask.getMotherTask(),false,map);
	}

	public static void updateCondition(HashMap<String,ChoiceEnum> map2 ){
		map.putAll(map2);
		execution();
	}


	private static void getAllExpressions(Tache tache) {
		String delim = "\\^";
		String[] tokens = tache.getPreExpression().getDescription().split(delim);
		expressions.clear();
		map.clear();
		for (String exp : tokens) {
			expressions.add(exp);
			map.put(exp,ChoiceEnum.indeterminée);
		}

		System.out.println("map size: " + map.size());



	}


	private static boolean isReman(String expression){
		String delim = "[\\^]";
		String[] tokens = expression.split(delim);
		String formal = tokens[0];

		String delimFormal = "[=]";
		String[] formalDecomp = formal.split(delimFormal);
		boolean rema = true;
		StringTokenizer myST = new StringTokenizer(formalDecomp[0],"[//?]",true);
		if(myST.countTokens()>1){
			rema = false;
		}

		return rema;
	}

	private static String displayCondition(String description) {
		String res= "";
		if(description!=null && !description.equals("")){

			String delim = "[\\^]";
			String[] tokens = description.split(delim);
			String formal = tokens[0];
			ArrayList<String> exp = new ArrayList<String>();
			String delimFormal = "[=]";
			String[] formalDecomp = formal.split(delimFormal);
			int number = 0;
			boolean rema = true;
			StringTokenizer myST = new StringTokenizer(formalDecomp[0],"[//?]",true);
			if(myST.countTokens()>1){
				while (myST.hasMoreTokens()) {
					rema=false;
					String element = myST.nextToken();
					if(!element.equals("?"))
						number =  Integer.parseInt(element);
				}
			}
			else
				number = Integer.parseInt(formalDecomp[0]);

			res+= expressions.get(number)+ "  ";
			if(formalDecomp.length >1){
				// logical expression parsing and evaluation

				ExpressionTree expression = parseLogical(formalDecomp[1],rema);
				res += expression.getDescription();
			}
		}


		return res;
	}



	private static List<String> parsePreCondition(String expression){
		String delim = "[\\^]";
		String[] tokens = expression.split(delim);
		String formal = tokens[0];
		ArrayList<String> exp = new ArrayList<String>();

		String delimFormal = "[=]";
		String[] formalDecomp = formal.split(delimFormal);
		int number = 0;
		boolean rema = true;
		StringTokenizer myST = new StringTokenizer(formalDecomp[0],"[//?]",true);
		if(myST.countTokens()>1){
			while (myST.hasMoreTokens()) {
				rema=false;
				String element = myST.nextToken();
				if(!element.equals("?"))
					number =  Integer.parseInt(element);
			}
		}
		else
			number = Integer.parseInt(formalDecomp[0]);

		exp.add(expressions.get(number));
		if(formalDecomp.length >1){
			// logical expression parsing and evaluation

			ChoiceEnum rep = evaluateLogical(parseLogical(formalDecomp[1],rema));
			exp.addAll(extractExpression(formalDecomp[1]));
			map.put(expressions.get(number),rep);
		}

		//l'expression logigue est evaluer il faut transmettre la valeur et les differentes partie de l'expression à afficher
		for (int i = 0; i<exp.size();i++) {
			System.out.println("exp "+i+" :"+exp.get(i));
		}
		return exp;
	}




	private static ChoiceEnum evaluateLogical(ExpressionTree expressionTree) {
		return expressionTree.evaluate(map);
	}



	private static List<String> extractExpression(String logical) {
		String delim =  "[+\\-*!()]";
		StringTokenizer myST = new StringTokenizer(logical,delim,false);
		ArrayList<String> value = new ArrayList<String>();
		while (myST.hasMoreTokens()) {
			String element = myST.nextToken();
			value.add(expressions.get(Integer.parseInt(element)));
		}
		return value;

	}



	/**
	 * parse and evalue the logical expression
	 * @param logical
	 * @param rema 
	 * @param exp 
	 * @return
	 */
	private static ExpressionTree parseLogical(String logical, boolean rema){
		String delim =  "[+\\-*!()]";
		StringTokenizer myST = new StringTokenizer(logical,delim,true);
		ArrayList<String> operator = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();
		ArrayList<String> res = new ArrayList<String>();
		ExpressionTree myExpression = null;
		boolean first = false;
		while (myST.hasMoreTokens()) {
			String element = myST.nextToken();
			if(element.equals("(")){
				operator.add(element);
			}else if (element.equals(")")){
				while(!operator.get(operator.size()-1).equals("(")){
					System.err.println("my exp : "+myExpression);
					if( operator.get(operator.size()-1).equals("!") ){
						if(value.size()>0){
							myExpression = new ExpressionTree("!",new ExpressionTree(value.get(value.size()-1), null, null),null);
							operator.remove(operator.size()-1);
							value.remove(value.size()-1);
						}
						else{
							myExpression = new ExpressionTree("!",myExpression,null);
							operator.remove(operator.size()-1);
						}
					}else if( operator.get(operator.size()-1).equals("+") ){
						if(myExpression==null){
							myExpression = new ExpressionTree(expressions.get(Integer.parseInt(element)),null,null);
						}
						myExpression = new ExpressionTree("+",myExpression,new ExpressionTree(value.get(value.size()-1),null,null));
						operator.remove(operator.size()-1);
						value.remove(value.size()-1);

					}else if( operator.get(operator.size()-1).equals("*") ){
						if(myExpression==null){
							myExpression = new ExpressionTree(value.get(value.size()-1),null,null);
							value.remove(value.size()-1);
						}
						myExpression = new ExpressionTree("*",myExpression,new ExpressionTree(value.get(value.size()-1),null,null));
						operator.remove(operator.size()-1);
						value.remove(value.size()-1);
					}
				}
				operator.remove(operator.size()-1);
				//depiler jusqu'à (
			}else if (element.equals("!")){
				operator.add(element);
			}else if (element.equals("+")){
				operator.add(element);
			}else if (element.equals("*")){
				operator.add(element);
			}else {
				if(first){
					myExpression = new ExpressionTree(expressions.get(Integer.parseInt(element)),null,null);
					first = false;
				}else{ 
					value.add(expressions.get(Integer.parseInt(element)));
					res.add(expressions.get(Integer.parseInt(element)));
				}
			}

		}


		return myExpression;


	}


	private static List<String> preonditionEvaluation(String expression){

		// si ya pas de precondition il faut afficher la tâche selon son ordonnancement
		if ((expression == null) || expression.equals("") )
			return null;
		else { 
			// sinon il faut parser l'expression et l'évaluer avec la map
			// il faut donner l'ensemble des expressions à afficher avec leurs valeurs 
			List<String> str = parsePreCondition(expression);
			return str;
		}
	}

	private static void remanance() {
		if(currentTask!=null && currentTask.getFacultatif() !=null){
			for (Tache t : currentTask.getFils()) {
				String precondition = t.getPreExpression().getDescription();
				if(!isReman(precondition)){
					List<String> l = parsePreCondition(precondition);
					for(int i = 0;i<l.size();i++){
						map.put(l.get(i),ChoiceEnum.indeterminée);
					}
				}
			}		
		}
	}





}

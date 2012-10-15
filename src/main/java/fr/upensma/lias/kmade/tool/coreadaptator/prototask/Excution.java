package fr.upensma.lias.kmade.tool.coreadaptator.prototask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import fr.upensma.lias.kmade.kmad.schema.tache.Decomposition;
import fr.upensma.lias.kmade.kmad.schema.tache.StateExecution;
import fr.upensma.lias.kmade.kmad.schema.tache.Tache;
import fr.upensma.lias.kmade.tool.coreadaptator.ExpressTask;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ExpressionTree;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.PROTOHistoric;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ReturnParsePreCondition;


/**
 * KMADe project
 * LISI-ENSMA and University of Poitiers
 * Teleport 2 - 1 avenue Clement Ader
 * BP 40109 - 86961 Futuroscope Chasseneuil Cedex
 *
 * @author Thomas LACHAUME
 *
 */
public class Excution {
/*
	private static HashMap <String,ChoiceEnum> map = new HashMap<String, ChoiceEnum>();
	private static ArrayList<String> expressions = new ArrayList<String>();

	public static void startExecution() {
		PROTOHistoric.clearHisto();
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		for (Tache tache : root) {
			setAllChildStateExecution(tache, StateExecution.INACTIVE);
			setTaskToActive(tache,true,null);
		}
	}



	//modification of task state
	public static void setTaskToActive(Tache t, boolean historic,HashMap<String,ChoiceEnum> map) {
		if(historic){
			PROTOHistoric.addTask(t);
		}
		t.setStateExecution(StateExecution.ACTIVE);
	}

	// une tâche qui change d'état doit mettre à jour ses tâches filles
	public static void updateMySubTask(Tache t){
		ArrayList<Tache> fils = t.getFils();
		Tache last;
		switch (t.getDecomposition()) {
		case ELE: 
			setInWaitingState(t);
			break;
		case SEQ:
			boolean firstInactive = true;
			boolean OptionalFound = false;
			for (Tache tache : fils) {
				StateExecution state = tache.getStateExecution();
				if(state == StateExecution.FINIE || state == StateExecution.PASSEE){

				}else if(state == StateExecution.INACTIVE){
					if(firstInactive || OptionalFound){
						if(t.getPreconditionValue() == ChoiceEnum.vrai){
							t.setStateExecution(StateExecution.ACTIVABLE);
						}else{
							t.setStateExecution(StateExecution.INACTIVABLE);
						}
						firstInactive = false;
						OptionalFound = t.getFacultatif();
					}
				}	

			}
			//on regarde si on se met en attente de fin
			boolean waitEnd = true;
			last = fils.get(fils.size());
			StateExecution statefin = last.getStateExecution();
			switch (statefin) {
			case INACTIVE:waitEnd =false;break;
			case ACTIVABLE:
			case INACTIVABLE:
				if(last.getFacultatif())
					waitEnd = true;
				else
					waitEnd = false;
				break;
			case FINIE: waitEnd = true;	break;
			default:
				break;
			}
			if(waitEnd){
				setInWaitingState(t);
			}

			break;
		case ALT:
			boolean choicemade = false;
			for (Tache tache : fils) {
				if(tache.getStateExecution() == StateExecution.FINIE){
					choicemade = true;
				}
			}
			if(!choicemade){
				for (Tache tache : fils) {
					StateExecution state = tache.getStateExecution();
					if(state == StateExecution.INACTIVE){
						if(t.getPreconditionValue() == ChoiceEnum.vrai){
							t.setStateExecution(StateExecution.ACTIVABLE);
						}else{
							t.setStateExecution(StateExecution.INACTIVABLE);
						}
					}
				}
			}else{
				setInWaitingState(t);
			}
			break;
		case ET:
			for (Tache tache : fils) {
				if(tache.getStateExecution()==StateExecution.INACTIVE){
					if(t.getPreconditionValue() == ChoiceEnum.vrai){
						t.setStateExecution(StateExecution.ACTIVABLE);
					}else{
						t.setStateExecution(StateExecution.INACTIVABLE);
					}
				}
			}
			waitEnd = true;
			for (Tache tache : fils) {
				if((tache.getStateExecution() == StateExecution.ACTIVABLE|| tache.getStateExecution()== StateExecution.INACTIVABLE) && (!tache.getFacultatif()))
					waitEnd = false;
			}
			if(waitEnd){
				setInWaitingState(t);
			}
			break;
		case PAR:
			for (Tache tache : fils) {
				if(tache.getStateExecution()==StateExecution.INACTIVE){
					if(t.getPreconditionValue() == ChoiceEnum.vrai){
						t.setStateExecution(StateExecution.ACTIVABLE);
					}else{
						t.setStateExecution(StateExecution.INACTIVABLE);
					}
				}
			}
			waitEnd = true;
			for (Tache tache : fils) {
				if(!(tache.getStateExecution() == StateExecution.FINIE) && !tache.getFacultatif()){
					waitEnd = false;
				}
			}
			break;
		case INCONNU :
		default:System.err.println("erreur decomposition");
		break;
		}


	}

	//gestion des itérations
	private static void setInWaitingState(Tache t) {
		t.setStateExecution(StateExecution.ATTENTEFIN);
		if(t.getIteExpression().getDescription()!=null || t.getIteExpression().getDescription().equals("") ){
			if(t.getIterationValue() != ChoiceEnum.vrai){
				t.setStateExecution(StateExecution.ATTENTEFINKO);
			}
		} 

	}



	//attente
	public static void updateMother(Tache mother){
		Decomposition decomp = mother.getDecomposition();
		ArrayList<Tache> fils = mother.getFils();
		if(decomp == Decomposition.ALT){
			mother.setStateExecution(StateExecution.ATTENTETASK);
			boolean choicemade = false;
			for (Tache tache : fils) {
				if(tache.getStateExecution() == StateExecution.FINIE){
					choicemade = true;
				}
			}
			if(choicemade){
				for (Tache tache : fils) {
					if(tache.getStateExecution() != StateExecution.FINIE){
						tache.setStateExecution(StateExecution.PASSEE);
					}
				}

			}
		}else if( decomp == Decomposition.ET ){

			mother.setStateExecution(StateExecution.ATTENTETASK);

			for (Tache tache : fils) {
				StateExecution state = tache.getStateExecution();
				if( state == StateExecution.ACTIVABLE || state == StateExecution.INACTIVABLE){
					tache.setStateExecution(StateExecution.INACTIVE);
				}
			}
		}else if(decomp == Decomposition.PAR){
			boolean active = false;
			for (Tache tache : fils) {
				if(tache.getStateExecution()==StateExecution.ACTIVABLE || tache.getStateExecution() == StateExecution.INACTIVABLE)
					active = true;
			}
			if(active)
				mother.setStateExecution(StateExecution.ACTIVE);
			else
				mother.setStateExecution(StateExecution.ATTENTETASK);

		}else if (decomp == Decomposition.SEQ ){
			mother.setStateExecution(StateExecution.ATTENTETASK);
			for (Tache tache : fils) {
				StateExecution state = tache.getStateExecution();
				if( state == StateExecution.ACTIVABLE || state == StateExecution.INACTIVABLE){
					if(tache.getFacultatif() &&  tache.getOldSisterTask() != null && (tache.getOldSisterTask().getStateExecution()==StateExecution.ACTIVE ||tache.getOldSisterTask().getStateExecution()==StateExecution.ATTENTEFIN || tache.getOldSisterTask().getStateExecution()==StateExecution.ATTENTETASK || tache.getOldSisterTask().getStateExecution()==StateExecution.FINIE) ){
						tache.setStateExecution(StateExecution.PASSEE);
					}else{
						tache.setStateExecution(StateExecution.INACTIVE);
					}
				}
			}
		}else{

			System.err.println("erreur decompo");
		}
	}


	public static void setActive(Tache t){
		t.setStateExecution(StateExecution.ACTIVE);
		Tache mother = t.getMotherTask();
		if (mother != null){
			updateMother(mother);
		}
	}

	public static void startTask(Tache t){

		Tache mother = t.getMotherTask();
		//	if(t.getStateExecution() == StateExecution.ACTIVABLE){
		t.setStateExecution(StateExecution.ACTIVE);
		if(t.getMotherTask() != null){
			updateMother(t.getMotherTask());
		}
		updateMySubTask(t);


		/*}else{
			//TODO 
			System.err.println("error ");
		}*/

	
	/*
	}


	public static void valideTask(Tache t){
		if(t.getStateExecution() == StateExecution.ATTENTEFIN){
			t.setStateExecution(StateExecution.FINIE);
			if(t.getMotherTask()!=null){
				startTask(t.getMotherTask());
			}
		}else{
			System.err.println("error no wait end");
		}
	}

	public static boolean preconditionSensitive(StateExecution s){
		return true;
	}

	public static boolean buttunViewed(){
		return true;
	}

	public static boolean panelViewed(){
		return true;
	}






	//TODO test
	private static List<Tache> getAllActive() {
		ArrayList<Tache> active = new ArrayList<Tache>();
		ArrayList<Tache> root = ExpressTask.getRootTasks();
		for (Tache tache : root) {
			getActive(tache, active);
		}
		return active;
	}
	//TODO test
	private static void getActive(Tache t, ArrayList<Tache> active){
		if( t.getStateExecution() == StateExecution.ACTIVE){
			active.add(t);
		}
		ArrayList<Tache> fils = t.getFils();
		for (Tache tache : fils) {
			getActive(tache, active);
		}
	}




	public static void updateCondition(HashMap<String,ChoiceEnum> map2 ){
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
	}


	private static boolean isReman(String expression){
		System.err.println("expresssion :" + expression);
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
			StringTokenizer myST = new StringTokenizer(formalDecomp[0],"[//?!]",true);
			if(myST.countTokens()>1){
				while (myST.hasMoreTokens()) {
					rema=false;
					String element = myST.nextToken();
					if(!element.equals("?") && !element.equals("!"))
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




	private static ReturnParsePreCondition parsePreCondition(String expression){
		System.out.println(map.get("zero"));
		String delim = "[\\^]";
		String[] tokens = expression.split(delim);
		String formal = tokens[0];
		ArrayList<String> exp = new ArrayList<String>();

		String delimFormal = "[=]";
		String[] formalDecomp = formal.split(delimFormal);
		int number = 0;
		boolean rema = true;
		boolean negat = false;
		StringTokenizer myST = new StringTokenizer(formalDecomp[0],"[//?!]",true);
		if(myST.countTokens()>1){
			while (myST.hasMoreTokens()) {
				rema=false;
				String element = myST.nextToken();
				if(element.equals("?"))
					rema=false;
				else if(element.equals("!"))
					negat = true;
				else
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
		ReturnParsePreCondition res = new ReturnParsePreCondition();
		res.list = exp;
		res.negat = negat;
		System.out.println(map.get("zero"));

		return res;
	}




	private static ChoiceEnum evaluateLogical(ExpressionTree expressionTree) {
		ChoiceEnum res = expressionTree.evaluate(map);
		return res;

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
	/*
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
							myExpression = new ExpressionTree(value.get(value.size()-1),null,null);
							value.remove(value.size()-1);
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
	*/

/*
	private static ReturnParsePreCondition preonditionEvaluation(String expression){

		// si ya pas de precondition il faut afficher la tâche selon son ordonnancement
		if ((expression == null) || expression.equals("") )
			return null;
		else { 
			// sinon il faut parser l'expression et l'évaluer avec la map
			// il faut donner l'ensemble des expressions à afficher avec leurs valeurs 
			ReturnParsePreCondition res = parsePreCondition(expression);
			//List<String> str = res.list;
			return res;
		}
	}
*/
	/*	private static void remanance() {
		if(currentTask!=null){ // && currentTask.getFacultatif() !=null){
			for (Tache t : currentTask.getFils()) {
				String precondition = t.getPreExpression().getDescription();
				if(!isReman(precondition)){
					List<String> l = parsePreCondition(precondition).list;
					for(int i = 0;i<l.size();i++){
						System.err.println("kmc"+l.get(i));
						map.put(l.get(i),ChoiceEnum.indeterminée);
					}
				}
			}		
		}
	}*/

	/*
	public static void setAllChildStateExecution(Tache t, StateExecution s) {
		if (t.getFils() != null) {
			ArrayList<Tache> child = t.getFils();
			for (Tache tache : child) {
				tache.setStateExecution(s);
				setAllChildStateExecution(tache, s);
			}
		}
	}


*/

}

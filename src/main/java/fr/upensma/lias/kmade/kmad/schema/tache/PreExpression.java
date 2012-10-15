/*********************************************************************************
 * This file is part of KMADe Project.
 * Copyright (C) 2006  INRIA - MErLIn Project and LISI - ENSMA
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
package fr.upensma.lias.kmade.kmad.schema.tache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import fr.upensma.lias.kmade.kmad.schema.expression.BooleanConstant;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ChoiceEnum;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ExpressionTree;
import fr.upensma.lias.kmade.tool.coreadaptator.prototype.ReturnParsePreCondition;

/**
 * @author Mickael BARON
 */
public class PreExpression extends Expression {

	private static final long serialVersionUID = 966193885483435378L;

	public PreExpression() {
		refNode = new BooleanConstant();
		this.chaine = refNode.getName();
	}

	public PreExpression(String pre) {
		this.chaine = pre;
	}
	
	//PROTOTASK 
	
	//TODO need rework
	private static ArrayList<String> expressions = new ArrayList<String>();
	public  String getDisplayableSemiFormalCondition() {
		String res= "";
		//get semiformal condition
		String description = super.getDescription(); 
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
	//TODO need REWORK
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


/*	private static ReturnParsePreCondition preonditionEvaluation(String expression){

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

	public void getSFStringDecomposition() {
		// TODO Auto-generated method stub
		
	}

	public ChoiceEnum getPreconditionValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPreconditionValue(ChoiceEnum preconditionValue) {
		// TODO Auto-generated method stub
		
	}

	
	public List<String> getParseString() {
		return myObjectValues;
		// TODO Auto-generated method stub
		
	}
	
/*	public List<> getParseValue(){
		// TODO Auto-generated method stub

		return null;
		
	}
	*/
	

}

package fr.upensma.lias.kmade.tool.coreadaptator.prototype;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpressionTree {

	public String value;
	public ExpressionTree left;
	public ExpressionTree right;


	public ExpressionTree(String value ,ExpressionTree left,ExpressionTree right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}


	public ChoiceEnum evaluate(HashMap<String, ChoiceEnum> map) {

		ChoiceEnum res = eval_rec(map);
		return res;
	}

	@Override
	public String toString() {
		String res =  value   ;
		if(left!=null){
			res += left.value;
		}
		if(right!=null){
			res += right.value;
		}
		res +="\n";
		if(left!=null){
			res += left.toString();
		}
		if(right!=null){
			res += right.toString();
		}

		return res;
	}


	private ChoiceEnum eval_rec(HashMap<String,ChoiceEnum> map){
		System.err.println("in 2 :" +this);
		if(value == null){
			return null;
		}else {
			if(value.equals("!")){
				ChoiceEnum l = left.eval_rec(map);
				switch(l){
				case faux : return ChoiceEnum.vrai;
				case indeterminée : return ChoiceEnum.indeterminée ;
				case vrai : return ChoiceEnum.faux;
				}
			} else if (value.equals("*")){
				ChoiceEnum l = left.eval_rec(map);
				ChoiceEnum r = right.eval_rec(map);
				if(l == ChoiceEnum.vrai){
					return r; 
				}else if (l == ChoiceEnum.faux){
					return l;
				} else if (l == ChoiceEnum.indeterminée){
					if(r == ChoiceEnum.faux){
						return r;
					}else{
						return l;
					}			
				}
			} else if (value.equals("+")){
				ChoiceEnum l = left.eval_rec(map);
				ChoiceEnum r = right.eval_rec(map);
				if(l == ChoiceEnum.vrai || r == ChoiceEnum.vrai){
					return ChoiceEnum.vrai;
				}else if(l == ChoiceEnum.indeterminée || r == ChoiceEnum.indeterminée){
					return ChoiceEnum.indeterminée;
				}else{
					return ChoiceEnum.faux;
				}
			}else if (value != null){
				if( map.get(value) != null ){
					System.out.println("map valueeeeeeeeeeeee"+map.get(value));
					return map.get(value);
				}
				System.out.println("pas de map value!!");
			}else {
				System.err.println("hahaah");
			}

		}
		return null;

	}


	public String getDescription() {
		String res ="";
		if(value == null){
			
		}else if(value.equals("+")){
			res += "(" + left.getDescription() + " OU " + right.getDescription() +")";
		}else if(value.equals("*")){
			res += "(" + left.getDescription() + " ET " + right.getDescription() +")";
		}else if(value.equals("!")){
			res += "(NON " + left.getDescription()+")";
		}else{
			res+=value;
		}
		return res;
	}

}

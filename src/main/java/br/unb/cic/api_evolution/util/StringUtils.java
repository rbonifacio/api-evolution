package br.unb.cic.api_evolution.util;

public class StringUtils {
	
	public static String generateFact(String fact, String...atoms) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(fact);
		builder.append("("); 
		
		for(int i =  0; i < atoms.length - 1; i++) {
			builder.append(convert(atoms[i]) + ", ");
		}
		
		//add the last atom. 
		if(atoms.length > 0) {
			builder.append(convert(atoms[atoms.length-1]));
		}
		builder.append(").");
		return builder.toString();
	}
	
	public static String generateList(String...elements) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("["); 
		
		for(int i =  0; i < elements.length - 1; i++) {
			builder.append(convert(elements[i]) + ", ");
		}
		
		//add the last element. 
		if(elements.length > 0) {
			builder.append(convert(elements[elements.length-1]));
		}
		builder.append("]");
		return builder.toString();
	}
	
	public static String convert(String atom) {
		if(isNumber(atom)) {  
			return atom;   
		}else if(atom.startsWith("[") && atom.endsWith("]")) {
			return atom;
		}
		else {
			return ("\"" + atom + "\"");
		}
	}
	
	private static boolean isNumber(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false; 
			}
		}
		return true; 
	}

}

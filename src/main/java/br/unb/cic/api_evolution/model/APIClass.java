package br.unb.cic.api_evolution.model;

import java.util.ArrayList;
import java.util.List;

public class APIClass {

	private String className; 
	private List<APIMethod> methods; 
	
	public APIClass() {
		methods = new ArrayList<>();
	}
	
	public APIClass(String name) {
		this();
		this.className = name; 
	}

	public void addMethod(APIMethod method) {
		if(methods == null) {
			methods = new ArrayList<>();
		}
		methods.add(method);
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<APIMethod> getMethods() {
		return methods;
	}
	
}

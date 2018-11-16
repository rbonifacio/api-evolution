package br.unb.cic.api_evolution.model;

import java.util.ArrayList;
import java.util.List;

public class APIClass {

	private String className; 
	private String superClass;
	private List<String> interfaces; 
	
	private List<APIMethod> methods; 
	
	public APIClass() {
		methods = new ArrayList<>();
		interfaces = new ArrayList<>();
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
	
	public void addInterface(String declaredInterface) {
		if(interfaces == null) {
			interfaces = new ArrayList<>();
		}
		interfaces.add(declaredInterface);
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

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}
	
	
	
}

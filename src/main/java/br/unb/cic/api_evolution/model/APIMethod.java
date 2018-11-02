package br.unb.cic.api_evolution.model;

import java.util.List;

public class APIMethod {

	private boolean abstractMethod;
	private String returnType;
	private String name; 
	private List<String> args;
	private List<String> exceptions;
	private String signature;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAbstractMethod() {
		return abstractMethod;
	}

	public void setAbstractMethod(boolean abstractMethod) {
		this.abstractMethod = abstractMethod;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public List<String> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

}

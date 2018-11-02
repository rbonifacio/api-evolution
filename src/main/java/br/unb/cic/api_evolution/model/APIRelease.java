package br.unb.cic.api_evolution.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a release of an API. 
 * 
 * @author rbonifacio
 */
public class APIRelease {

	private String name;
	private String version; 
	private String targetJavaVersion;
	private String location;
	private List<APIClass> classes; 
	
	public APIRelease(String name, String version, String targetJavaVersion, String location) {
		this.name = name;
		this.version = version;
		this.targetJavaVersion = targetJavaVersion;
		this.location = location;
		classes = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getTargetJavaVersion() {
		return targetJavaVersion;
	}
	
	public void setTargetJavaVersion(String targetJavaVersion) {
		this.targetJavaVersion = targetJavaVersion;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public void addClass(APIClass c) {
		classes.add(c);
	}
	
	public List<APIClass> getClasses() {
		return classes;
	}

	public void setClasses(List<APIClass> classes) {
		this.classes = classes;
	} 
	
	
	
	
	
}

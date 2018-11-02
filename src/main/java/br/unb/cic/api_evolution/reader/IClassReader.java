package br.unb.cic.api_evolution.reader;

import java.util.List;

import br.unb.cic.api_evolution.model.APIClass;

public interface IClassReader {
	
	public List<APIClass> read(String jarFile, String javaVersion);
	
	public default String jreClasses(String version) {
		switch(version) {
		 case "jdk11": return "rt-11.jar"; 
		 case "jdk12": return "rt-12.jar"; 
		}
		return "rt-11.jar"; 
	}


}

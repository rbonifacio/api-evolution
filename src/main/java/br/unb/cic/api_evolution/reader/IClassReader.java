package br.unb.cic.api_evolution.reader;

import java.io.File;
import java.util.List;

import br.unb.cic.api_evolution.model.APIClass;

public interface IClassReader {
	
	public List<APIClass> read(String jarFile, String javaVersion);
	
	public default String jreClasses(String version) {
		File file = new File("./jdk/" + version); 
		
		if(file.exists()) {
			return file.getAbsolutePath();
		}
		return "./jdk/jdk1.5/";
	}


}

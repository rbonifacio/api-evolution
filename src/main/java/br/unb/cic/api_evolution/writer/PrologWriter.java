package br.unb.cic.api_evolution.writer;

import java.io.PrintWriter;
import java.util.List;

import br.unb.cic.api_evolution.model.APIClass;
import br.unb.cic.api_evolution.model.APIMethod;
import br.unb.cic.api_evolution.model.APIRelease;
import br.unb.cic.api_evolution.util.StringUtils;

public class PrologWriter implements Writer {
	
	private PrintWriter pw;
	


	@Override
	public void export(Integer id, APIRelease release) {
		try {
			exportRelease(id, release);
			exportClasses(id, release.getClasses());
			exportMethods(id, release.getClasses());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exportRelease(Integer id, APIRelease release) {
		pw.println(StringUtils.generateFact("release", id.toString(),release.getName(), release.getVersion(), release.getTargetJavaVersion()));
	}
	
	private void exportClasses(Integer idRelease, List<APIClass> classes) {
		for(Integer i = 0; i < classes.size(); i++) {
			APIClass c = classes.get(i);
			pw.println(StringUtils.generateFact("class",idRelease.toString(), i.toString(),  c.getClassName()));
		}
	}
	
	private void exportMethods(Integer idRelease, List<APIClass> classes) {
		for(Integer i = 0; i < classes.size(); i++) {
			APIClass c = classes.get(i);
			
			for(APIMethod m : c.getMethods()) {
				pw.println(StringUtils.generateFact("method",idRelease.toString(), i.toString(), 
						m.getReturnType(),
						m.getName(), 
						StringUtils.generateList(m.getArgs().toArray(new String[m.getArgs().size()])),
						StringUtils.generateList(m.getExceptions().toArray(new String[m.getExceptions().size()]))));
			}
		}
	}

	@Override
	public void setPrintWriter(PrintWriter pw) {
		this.pw = pw;
	}

}

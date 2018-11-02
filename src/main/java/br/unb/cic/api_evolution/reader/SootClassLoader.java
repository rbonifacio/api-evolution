package br.unb.cic.api_evolution.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.unb.cic.api_evolution.model.APIClass;
import br.unb.cic.api_evolution.model.APIMethod;
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Type;

@Deprecated
public class SootClassLoader  implements IClassReader {

	private List<APIClass> classes = new ArrayList<>(); 
	
	public SootClassLoader() {
		if(PackManager.v().getPack("wjtp").get("wjtp.myTransform") != null) {
			return;
		}
		PackManager.v().getPack("wjtp").add(
			      new Transform("wjtp.myTransform", new SceneTransformer() {
			    	  
			       protected void internalTransform(String phaseName, Map options) {
			    	   for(SootClass c : Scene.v().getClasses()) {
			    		   if(c.isPublic() && c.isApplicationClass()) {
			    			   classes.add(createClassFromSoot(c));
			        	  }	
			          }	
			        }
			      }));
	}
	
	@Override
	public List<APIClass> read(String jarFile, String javaVersion) {
		classes = new ArrayList<>();
		soot.Main.main(new String[] {"-w", "-allow-phantom-refs", "-process-dir", jarFile} );
		return classes;  
	}

	private APIClass createClassFromSoot(SootClass sc) {
		APIClass c = new APIClass();
		c.setClassName(sc.getName());
		for(SootMethod sm : sc.getMethods()) {
			if(sm.isPublic()) {
				c.addMethod(createMethodFromSootMethod(sm)); 
			}
		}
		return c;
	}
	
	@Deprecated
	private APIMethod createMethodFromSootMethod(SootMethod sm) {
		APIMethod m = new APIMethod();
		m.setReturnType(sm.getReturnType().toString());
		m.setName(sm.getName());
		m.setSignature(sm.getSignature());
		
		List<String> args = new ArrayList<>(); 
		List<String> exceptions = new ArrayList<>();
		
		for(Type t: sm.getParameterTypes()) {
			args.add(toString());
		}
		
		for(SootClass e: sm.getExceptions()) {
			exceptions.add(e.getName());
		}
		m.setArgs(args);
		m.setExceptions(exceptions);
		return m; 
	}
	
}


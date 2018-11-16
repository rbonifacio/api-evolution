package br.unb.cic.api_evolution.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;

import br.unb.cic.api_evolution.model.APIClass;
import br.unb.cic.api_evolution.model.APIMethod;

public class DefaultClassReader implements IClassReader {
	
	
	public List<APIClass> read(String jarFile, String javaVersion)  { 
		List<APIClass> classes = new ArrayList<APIClass>();
		int errors = 0; 
		int css = 0;  
		int ncss = 0; 
		try {
			AnalysisScope scope = AnalysisScope.createJavaAnalysisScope();
			//scope.addToScope(ClassLoaderReference.Primordial, new JarFile("jSDG-stubs-jre1.5.jar")); 
		
			File folder = new File(jreClasses(javaVersion));
			File[] listOfFiles = folder.listFiles();
			
			for(File f: listOfFiles) {
				if(f.getName().endsWith("jar")) {
					System.out.println("Adding JAR file:" + f.getAbsolutePath());
					scope.addToScope(ClassLoaderReference.Primordial, new JarFile(f.getAbsolutePath())); 
				}
			}
			
			//scope.addToScope(ClassLoaderReference.Primordial, new JarFile(jreClasses(javaVersion))); 
			
			scope.addToScope(ClassLoaderReference.Application,new JarFile(jarFile));
			
			IClassHierarchy h = ClassHierarchyFactory.makeWithPhantom(scope);
			
			System.out.println(h.getNumberOfClasses());
			
			for(IClass c: h) {
				try {
					if(filter(c)) {
						APIClass ac = new APIClass();
						ac.setClassName(c.getName().toString().replaceAll("/", ".").substring(1));
						ac.setSuperClass(c.getSuperclass().getName().toString().replaceAll("/", ".").substring(1));
					
						for(IClass anInterface : c.getAllImplementedInterfaces()) {
							ac.addInterface(anInterface.getName().toString().replace("/", ".").substring(1));
						}
						
						for(IMethod m : c.getAllMethods()) {
							APIMethod am = new APIMethod();
							am.setAbstractMethod(m.isAbstract());
							am.setName(m.getName().toString());
							am.setSignature(m.getSignature());
							
							List<String> args = new ArrayList<>();
							List<String> exceptions = new ArrayList<>();
							List<String> annotations = new ArrayList<>();
							
							//args
							for(int i = 0; i < m.getNumberOfParameters(); i++) {
								args.add(m.getParameterType(i).toString().replace("/", ".").substring(1));
							}
							
							//exceptions 
							for(TypeReference t: m.getDeclaredExceptions()) {
								exceptions.add(t.getName().toString().replace("/", ".").substring(1));
							}
							
							am.setArgs(args);
							am.setExceptions(exceptions);
							am.setReturnType(m.getReturnType().toString().replace("/", ".").substring(1));	
							ac.addMethod(am);
						}
						css++;
						classes.add(ac);
					}
					else {
						ncss++;
					}
				}
				catch(com.ibm.wala.classLoader.NoSuperclassFoundException e) {
					if(c.getName().toString().startsWith("Lorg/bouncycastle/jcajce/provider/keystore/bc")) {
						System.out.println(c.getName());	
					}
					errors++;
				}
			}	
			System.out.println("Erros: " + errors);
			System.out.println("Classes: " + css);
			System.out.println("Classes: " + ncss);
			
			System.out.println("Classes added " + classes.size());
			return classes;
		}
		catch(Throwable t) {
			t.printStackTrace();
			return null;
		}
	}

	
	private boolean filter(IClass clasz) {
		return clasz.getClassLoader().getReference().equals(ClassLoaderReference.Application) && clasz.isPublic();
	}
}

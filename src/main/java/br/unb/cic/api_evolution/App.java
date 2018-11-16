package br.unb.cic.api_evolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import apidiff.APIDiff;
import apidiff.Change;
import apidiff.Result;
import apidiff.enums.Classifier;
import br.unb.cic.api_evolution.model.APIRelease;
import br.unb.cic.api_evolution.reader.DefaultClassReader;
import br.unb.cic.api_evolution.reader.IClassReader;
import br.unb.cic.api_evolution.writer.Factory;
import br.unb.cic.api_evolution.writer.Writer;

/**
 * Main method of the API-Evolution application. 
 */
public class App 
{
    public static void main( String[] args )
     {
    	if(args.length == 0) {
    		System.out.println("this program expects two arguments: ");
    		System.out.println("(1) the path for a CSV file detailing the API releases that must be considered in the analysis.");
    		System.out.println("(2) the output format: eithar pl or csv (pl is the default)");
    		
    		System.exit(1);
    	}
    	try {
    		String format = "pl"; 
    		if(args.length == 2) {
    			format = args[1]; 
    		}
    		PrintWriter pw = new PrintWriter(new FileWriter("releases." + format));
    	    
    		List<APIRelease> releases = releases(args[0]);
    		int i = 1; 
    		for(APIRelease r : releases) {
	    		IClassReader cr = new DefaultClassReader();	
	    		
	    		r.setClasses(cr.read(r.getLocation(), r.getTargetJavaVersion()));	
	    		
	    		Writer export = Factory.create(format);
	    		
	    		export.setPrintWriter(pw);
	    		export.export(i++, r);
    		}
    		pw.close();
    		//navigateDiffChange();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    

    /*
     * Experimental. Tryng to use API-DIFF. Unfortunately, it did not 
     * work as expected. For this reason, we are marking this method 
     * as deprecated. 
     */
    @Deprecated
    private static void navigateDiffChange() {
    	try{ 
    		APIDiff diff = new APIDiff("BC", "https://github.com/bcgit/bc-java.git");
    	
	    	diff.setPath("/Users/rbonifacio/tmp/github");
	    	Result result = diff.detectChangeAllHistory(Classifier.API);
	    	for(Change ct : result.getChangeMethod()){
	    	    System.out.println("\n" + ct.getCategory().getDisplayName() + " - " + ct.getDescription());
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private static List<APIRelease> releases(String file) {
    	final List<APIRelease> releases = new ArrayList<>(); 
    	try(BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    	    while ((line = br.readLine()) != null) {
    	    	StringTokenizer tokenizer = new StringTokenizer(line, ",");
                releases.add(new APIRelease(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken()));    	
    	    }
            return releases;
    	}
    	catch(IOException e) {
    		throw new RuntimeException("Problem reading the input file"); 
    	}
    }
    
    
}

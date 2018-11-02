package br.unb.cic.api_evolution.writer;

public class Factory {
	
	public static Writer create(String format) {
		if("csv".equals(format)) {
			return new CSVWriter();
		}
		return new PrologWriter();
	}

}

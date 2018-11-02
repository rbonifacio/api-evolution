package br.unb.cic.api_evolution.writer;

import java.io.PrintWriter;

import br.unb.cic.api_evolution.model.APIRelease;

public interface Writer {
	
	public void export(Integer id, APIRelease release);
	public void setPrintWriter(PrintWriter pw);

}

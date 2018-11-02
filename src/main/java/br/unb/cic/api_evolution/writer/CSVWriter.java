package br.unb.cic.api_evolution.writer;

import java.io.PrintWriter;

import br.unb.cic.api_evolution.model.APIRelease;

public class CSVWriter implements Writer {
	
	private PrintWriter pw;
	
	@Override
	public void export(Integer id, APIRelease release) {
		exportRelease(release);
	}
	
	private void exportRelease(APIRelease release) {
		release.getClasses().stream().forEach(c -> {
			c.getMethods().stream().forEach(m -> {
				pw.println(release.getName() + ", " + release.getVersion() + ", " + c.getClassName() + ", " + m.getSignature());
			});
		});
	}

	@Override
	public void setPrintWriter(PrintWriter pw) {
		this.pw = pw; 
	}

}

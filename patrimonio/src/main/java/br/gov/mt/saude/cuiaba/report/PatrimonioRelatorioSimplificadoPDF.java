package br.gov.mt.saude.cuiaba.report;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.jasperreports.Report;
import br.gov.mt.saude.cuiaba.report.model.PatrimonioRelatorio;

public class PatrimonioRelatorioSimplificadoPDF implements Report{

	private final List<PatrimonioRelatorio> data;
	private Map<String, Object> parameters;
	
	public PatrimonioRelatorioSimplificadoPDF(List<PatrimonioRelatorio> data) {
		this.data = data;
		this.parameters = new HashMap<String, Object>();
	}
	
	@Override
	public String getTemplate() {	
		return "patrimonio_simplificado.jasper";
	}

	@Override
	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public Collection getData() {
		return data;
	}

	@Override
	public String getFileName() {
		return "report" + System.currentTimeMillis();
	}

	@Override
	public Report addParameter(String parameter, Object value) {
		this.parameters.put(parameter, value);
		return this;
	}

	@Override
	public boolean isCacheable() {
		return true;
	}

}

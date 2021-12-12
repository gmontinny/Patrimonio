package br.gov.mt.saude.cuiaba.report.factory;

import java.util.ArrayList;
import java.util.List;

import br.gov.mt.saude.cuiaba.report.model.BaixaPatrimonio;

public class BaixaPatrimonioFactory {
	public static List<BaixaPatrimonio> loadRelatorio() {
		List<BaixaPatrimonio> baixaPatrimonios = new ArrayList<>();
		
		BaixaPatrimonio baixaPatrimonio = new BaixaPatrimonio("123564/2018", "31321AS2D13A543", "SECRETARIA MUNICIPAL DE SAÚDE", "COORDENADORIA DE VIGILÂNCIA SANITÁRIA",
				"INFORMATICA", "MATERIAL PERMANENTE", "INFORMATICA", "MONITOR", "HP", "SL2000", "SECRETARIA MUNICIPAL DE SAÚDE");
		
		baixaPatrimonios.add(baixaPatrimonio);
	    
		return baixaPatrimonios;
	}
}

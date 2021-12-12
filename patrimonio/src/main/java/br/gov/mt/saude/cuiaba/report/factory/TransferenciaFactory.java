package br.gov.mt.saude.cuiaba.report.factory;

import java.util.ArrayList;
import java.util.List;

import br.gov.mt.saude.cuiaba.report.model.TransferenciaPatrimonio;

public class TransferenciaFactory {
	public static List<TransferenciaPatrimonio> loadRelatorio() {
		List<TransferenciaPatrimonio> transferenciaPatrimonios = new ArrayList<>();
		
		TransferenciaPatrimonio transferenciaPatrimonio = new TransferenciaPatrimonio("000000123/2018", "A3216546S", "SECRETÁRIA MUNICIPA DE SAÚDE", 
				"COORDENADORIA DE VIGILÂNCIA SANITÁRIA", "COORDENADORIA DE VIGILÂNCIA ZOONOSE", "FISCALIZAÇÃO", "ADMINISTRATIVO", "MATERIAL PERMANENTE", 
				"INFORMATICA", "COMPUTADOR", "HP", "542", "SECRETARIA MUNICIPAL DE SAÚDE", "GIOVANNY");
		
		transferenciaPatrimonios.add(transferenciaPatrimonio);
		
		return transferenciaPatrimonios;
	}
}

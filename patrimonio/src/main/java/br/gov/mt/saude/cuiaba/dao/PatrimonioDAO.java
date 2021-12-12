package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Patrimonio;

public interface PatrimonioDAO extends GenericDAO<Patrimonio, Integer>{
	public List<Patrimonio> pesquisaPatrimonioGeral(Integer idsecretaria, Integer idsetor,Integer idlocal, Integer idgrupo, Integer idsubgrupo, Integer idmaterial, 
			Integer idmarca, Integer idmodelo, Integer fornecedor, Integer situacao, Integer baixa, String numeroPatrimonio, String serial);
	
	public Patrimonio pesquisaPorNumeroPatrimonio(String numeroPatrimonio);
	public boolean validaNumeroPatrimonio(String numeroPatrimonio);
	public boolean validaNumeroSerial(String numeroSerial);
}

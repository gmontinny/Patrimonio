package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Arquivo;

public interface ArquivoDAO extends GenericDAO<Arquivo, Integer>{
	public List<Arquivo> pesquisaArquivoPorNumero(int numero);
}

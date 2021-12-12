package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Fornecedor;

public interface FornecedorDAO extends GenericDAO<Fornecedor, Integer>{
	public List<Fornecedor> pesquisaFornecedorGeral();
	public boolean validaCNPJFonecedor(String cnpj);
}

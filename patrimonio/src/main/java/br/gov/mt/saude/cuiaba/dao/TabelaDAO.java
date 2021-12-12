package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Tabela;

public interface TabelaDAO extends GenericDAO<Tabela, Integer>{
	public List<Tabela> pesquisaTabelaGeral();
	public Tabela pesquisaTabelaPorNome(String nome);
	public boolean validaNomeTabela(String nome);
}

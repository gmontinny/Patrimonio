package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Setor;

public interface SetorDAO extends GenericDAO<Setor, Integer>{
	public List<Setor> pesquisaGeralSetor();
	public List<Setor> pesquisaSetorPorSecretaria(int idsecretaria);
	public Setor pesquisaSetorPorCodigoESecretaria(int codigo, int idsecretaria);
	public boolean validaNomeSetor(String nome);
}

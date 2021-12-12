package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Secretaria;

public interface SecretariaDAO extends GenericDAO<Secretaria, Integer>{
	public List<Secretaria> pesquisaSecretariaGeral();
	public boolean validaNomeSecretaria(String nome);
}

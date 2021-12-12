package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Local;

public interface LocalDAO extends GenericDAO<Local, Integer>{
	public List<Local> pesquisaGeralLocal();
	public List<Local> pesquisaLocalPorSetor(int idsetor);
	public Local pesquisaLocalPorCodigoESetor(int codigo, int idsetor);
	public boolean validaNomeLocal(int codigo, String nome);	
}

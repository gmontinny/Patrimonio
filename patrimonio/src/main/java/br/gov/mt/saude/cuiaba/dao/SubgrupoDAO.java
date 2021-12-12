package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Subgrupo;

public interface SubgrupoDAO extends GenericDAO<Subgrupo, Integer>{
	public List<Subgrupo> pesquisaGeralSubGrupo();
	public List<Subgrupo> pesquisaSubgrupoPorGrupo(int idgrupo);
	public Subgrupo pesquisaSubGrupoPorCodigoEGrupo(int codigo, int idgrupo);
	public boolean validaNomeSubGrupo(String nome);
}

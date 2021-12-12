package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Grupo;

public interface GrupoDAO extends GenericDAO<Grupo, Integer>{
	public List<Grupo> pesquisaGrupoGeral();
	public boolean validaDescricaoGrupo(String descricao);
}

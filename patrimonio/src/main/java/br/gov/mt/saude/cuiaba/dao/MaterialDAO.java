package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Material;

public interface MaterialDAO extends GenericDAO<Material, Integer>{
	public List<Material> pesquisaGeralMaterial();
	public List<Material> pesquisaMaterialPorSubGrupo(int idsubgrupo);
	public Material pesquisaMaterialPorCodigoESubgrupo(int codigo, int idsubgrupo);
	public boolean validaDescricaoMaterial(int idsubgrupo , String descricao);
}

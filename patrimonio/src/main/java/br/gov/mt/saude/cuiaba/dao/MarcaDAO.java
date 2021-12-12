package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Marca;

public interface MarcaDAO extends GenericDAO<Marca, Integer>{
	public List<Marca> pesquisaMarcaGeral();
	public boolean validaDescricaoMarca(String descricao);
}

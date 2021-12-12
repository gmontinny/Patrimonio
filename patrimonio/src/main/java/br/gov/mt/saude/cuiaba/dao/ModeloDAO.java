package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Modelo;

public interface ModeloDAO extends GenericDAO<Modelo, Integer>{
	public List<Modelo> pesquisaGeralModelo();
	public List<Modelo> pesquisaModeloPorMarca(int idmarca);
	public boolean validaNomeModelo(String nome);
	public Modelo pesquisaModeloPorCodigoEMarca(int codigo, int idmarca);
}

package br.gov.mt.saude.cuiaba.dao;

import br.gov.mt.saude.cuiaba.model.Geraarquivo;

public interface GeraArquivoDAO extends GenericDAO<Geraarquivo, Integer>{
	public Geraarquivo pesquisaUltimoGeraArquivo(int idusuario);
}

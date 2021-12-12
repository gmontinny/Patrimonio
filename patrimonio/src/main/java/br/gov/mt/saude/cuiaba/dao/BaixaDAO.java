package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Baixa;
import br.gov.mt.saude.cuiaba.model.Local;

public interface BaixaDAO extends GenericDAO<Baixa, Integer>{
	public boolean validaBaixaPorPatrimonio(String numero);
	public List<Object[]> pesquisaBaixaGeral();
	public List<Baixa> pesquisaBaixaPorPatrimonioESerial(String patrimonio, String serial);
	public List<Object[]> pesquisaBaixaPorLocal(Local local);
	public List<Baixa> pesquisaBaixaMobile();
}

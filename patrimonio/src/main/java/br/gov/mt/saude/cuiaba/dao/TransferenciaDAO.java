package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Transferencia;

public interface TransferenciaDAO  extends GenericDAO<Transferencia, Integer>{
	public List<Object[]> pesquisaTransferenciaGeral();
	public Transferencia pesquisaTransferencia(String numeroPatrimonio);
	public List<Object[]> pesquisaTransferenciaRealizada(int codigo);
	public List<Object[]> pesquisaTransferenciaLocal(Local local);
	public List<Transferencia> pesquisaTransferenciaMobile();
}

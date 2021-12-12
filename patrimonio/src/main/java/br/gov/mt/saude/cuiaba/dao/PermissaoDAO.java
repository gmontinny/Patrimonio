package br.gov.mt.saude.cuiaba.dao;

import java.util.List;

import br.gov.mt.saude.cuiaba.model.Permissao;
import br.gov.mt.saude.cuiaba.model.Tabela;
import br.gov.mt.saude.cuiaba.model.Usuario;

public interface PermissaoDAO extends GenericDAO<Permissao, Integer>{
	public boolean validaSelect(Usuario usuario, Tabela tabela);
	public boolean validaInsert(Usuario usuario, Tabela tabela);
	public boolean validaUpdate(Usuario usuario, Tabela tabela);
	public boolean validaDelete(Usuario usuario, Tabela tabela);
	public List<Permissao> pesquisaPermissaoOrdenadaPorNome(Usuario usuario);
	public List<Permissao> pesquisaPermissaoGeral();
	public List<Object[]>  pesquisaPermissaoPorUsuario(int codigo, int idusuario);
	public int removePermissao(int codigo);
}

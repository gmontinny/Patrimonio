package br.gov.mt.saude.cuiaba.dao;


import java.util.List;

import br.gov.mt.saude.cuiaba.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{
	public List<Usuario> pesquisaUsuarioGeral();
	public Usuario pesquisaUsuarioPorEmail(String email);
	public Usuario pesquisaUsuarioPorCPF(String cpf);
	public Usuario pesquisaUsuarioPorCPFESenha(String cpf, String senha);
	public boolean validaUsuarioLogin(String cpf, String senha);
	public boolean validaUsuarioEmail(String email);
}

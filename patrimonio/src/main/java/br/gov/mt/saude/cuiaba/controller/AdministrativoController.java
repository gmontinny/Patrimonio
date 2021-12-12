package br.gov.mt.saude.cuiaba.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.dao.impl.PermissaoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Usuario;


@Controller
public class AdministrativoController {
	private final Result result;
	private final UsuarioSessao sessao;
	private final PermissaoDAOImpl permissaodao;
	private final UsuarioDAOImpl usuariodao;
	
	
	@Inject
	public AdministrativoController(Result result, UsuarioSessao sessao, PermissaoDAOImpl permissaodao, UsuarioDAOImpl usuariodao) {		
		this.result = result;		
		this.sessao = sessao;
		this.permissaodao = permissaodao;
		this.usuariodao = usuariodao;
	}
	
	public AdministrativoController() {
		this(null,null,null,null);
	}
	
	@Path("/administrativo/index")
	public void index(){
		if (!sessao.isLogged()) {
			result.redirectTo(IndexController.class).index();
		}else{
			result.include("usuario",sessao.getUsuario());
			result.include("menuList",permissaodao.pesquisaPermissaoOrdenadaPorNome(sessao.getUsuario()));
			result.include("ativar","principal");
		}
	}
	
	@Path("/administrativo/meucadastro")
	public void meucadastro(){
		if (!sessao.isLogged()) {
			result.redirectTo(IndexController.class).index();
		}else{
			Usuario usuario = usuariodao.pesquisaUsuarioPorCPF(sessao.getUsuario().getCpfusuario());
			result.include(sessao.getUsuario());
			result.include("menuList",permissaodao.pesquisaPermissaoOrdenadaPorNome(sessao.getUsuario()));
			result.include("usuario",usuario);
			result.include("ativar","meucadastro");
		}
	}	
	
	@Path("/administrativo/error")
	public void error(){
		
	}

}

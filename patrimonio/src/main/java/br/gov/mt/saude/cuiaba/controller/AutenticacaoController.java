package br.gov.mt.saude.cuiaba.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Usuario;
import br.gov.mt.saude.cuiaba.util.Encryption;

@Controller
public class AutenticacaoController {
	private final Result result;
	private final Validator validator;
	private final UsuarioSessao sessao;
	private final UsuarioDAOImpl usuariodao;
	
	@Inject
	public AutenticacaoController(Result result, Validator validator, UsuarioSessao sessao, UsuarioDAOImpl usuariodao) {	
		this.result = result;
		this.validator = validator;
		this.sessao = sessao;
		this.usuariodao = usuariodao;
	}
	
	public AutenticacaoController() {
		this(null,null,null,null);
	}
	
	@Path("/autenticacao/index")
	public void index(){
		
	}

	@Path("/autenticacao/logout")
	public void logout() {
		sessao.logout();
		result.redirectTo(IndexController.class).index();
	}

	@Post("/autenticacao/valida")
	public void valida(String cpf, String senha) {
		
		validator.ensure(usuariodao.validaUsuarioLogin(cpf, new Encryption().encrypt(senha)),
				new SimpleMessage("usuarioNaoExistente", "Usuario não autorizado !"));

		validator.onErrorForwardTo(this).validacao();
		
		Usuario usuario = usuariodao.pesquisaUsuarioPorCPF(cpf);
		
		sessao.setUsuario(usuario);
		
		result.nothing();
	}
	
	@Path("/autenticacao/validacao")
	public void validacao(){
			
	}
	
}

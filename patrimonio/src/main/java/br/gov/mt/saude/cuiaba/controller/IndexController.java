package br.gov.mt.saude.cuiaba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Usuario;
import br.gov.mt.saude.cuiaba.util.Email;
import br.gov.mt.saude.cuiaba.util.Encryption;
import br.gov.mt.saude.cuiaba.util.GeraSenha;

@Controller
public class IndexController {
	private final Result result;
	private final UsuarioDAOImpl usuariodao;

	@Inject
	public IndexController(Result result, UsuarioDAOImpl usuariodao) {		
		this.result = result;
		this.usuariodao = usuariodao;

	}
	
	public IndexController() {
		this(null,null);
	}
	
	@Path("/index/validaEmail")
	public void validaEmail(String email) {
		boolean resultado = usuariodao.validaUsuarioEmail(email);
		Map<String, Object> dados = new HashMap<>();
		dados.put("resultado",resultado);
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/index/resetaSenha")
	public void resetaSenha(String email) {
		String senha = new GeraSenha().generateSessionKey(8);		
		Usuario usuario = usuariodao.pesquisaUsuarioPorEmail(email);
		usuario.setSenusuario(new Encryption().encrypt(senha));
		usuariodao.update(usuario);
		Email emailUsuario = new Email(email, "NOVA SENHA :"+ senha);
		String resultado = emailUsuario.sendMail();
		result.use(Results.http()).body(resultado);
	}
	
	
	@Path("/")
	public void index() {
		
	}
}

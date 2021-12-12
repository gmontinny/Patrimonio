package br.gov.mt.saude.cuiaba.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.PermissaoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Usuario;
import br.gov.mt.saude.cuiaba.util.Encryption;

@Controller
public class UsuarioController {
	private final Result result;
	private final UsuarioDAOImpl usuariodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final PermissaoDAOImpl permissaodao;
	
	@Inject
	public UsuarioController(Result result, UsuarioDAOImpl usuariodao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, PermissaoDAOImpl permissaodao) {
		this.result = result;
		this.usuariodao = usuariodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.permissaodao = permissaodao;
	}
	
	public UsuarioController() {
		this(null,null,null,null,null,null);
	}
	
	@Path("/usuario/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "usuario").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/usuario/salvar")
	public void salvar(@Valid Usuario usuario) {
		if(usuario.getIdusuario() == null) {
			validacao.valida(1,"usuario").onErrorForwardTo(AdministrativoController.class).error();
			usuario.setDatausuario(new Date());
			usuario.setSenusuario(new Encryption().encrypt(usuario.getSenusuario()));
			usuariodao.save(usuario);
		}else {
			validacao.valida(2,"usuario").onErrorForwardTo(AdministrativoController.class).error();
			Usuario userold = new Usuario();
			userold = usuariodao.getById(Usuario.class, usuario.getIdusuario());
			
			if (!userold.getSenusuario().equals(usuario.getSenusuario())){
				usuario.setSenusuario(new Encryption().encrypt(usuario.getSenusuario()));
			}
			
			usuario.setDatausuario(new Date());
			usuariodao.update(usuario);
		}
		
		result.nothing();
	}
	
	@Post("/usuario/meucadastro")
	public void meucadastro(int codigo, String imagem, String novaSenha) {
		Usuario usuario = usuariodao.getById(Usuario.class, codigo);
		if(novaSenha != null) {
			usuario.setSenusuario(new Encryption().encrypt(novaSenha));
		}
		usuario.setImagemusuario(imagem);
		usuariodao.update(usuario);
		result.nothing();
	}
	
	@Path("/usuario/grid")
	public void grid(){
		List<Map<String,Object>> dados = new ArrayList<>();
		List<Usuario> usuarios = usuariodao.pesquisaUsuarioGeral();
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		
		usuarios
		.stream()
		.filter(u -> u != null)
		.forEach(u -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idusuario", u.getIdusuario());
			resultado.put("nomusuario", u.getNomusuario());
			resultado.put("senusuario", u.getSenusuario());
			resultado.put("cpfusuario", u.getCpfusuario());
			resultado.put("emailusuario", u.getEmailusuario());
			resultado.put("imagemusuario", u.getImagemusuario());
			resultado.put("statususuario", u.getStatususuario());
			resultado.put("tipousuario", u.getTipousuario());
			resultado.put("datausuario", dt.format(u.getDatausuario()));
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/usuario/editar")
	public void editar(int codigo){
		result.use(Results.json()).withoutRoot().from(usuariodao.getById(Usuario.class, codigo)).serialize();
	}
	
	@Path("/usuario/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "usuario").onErrorForwardTo(AdministrativoController.class).error();
		Usuario usuario = usuariodao.getById(Usuario.class, codigo);
		usuariodao.delete(usuario);
		result.nothing();
	}
	
	@Get("/usuario/mobile/validaUsuario/{cpf}/{senha}")
	public void validaUsuario(String cpf, String senha) {		
		Usuario usuario = usuariodao.pesquisaUsuarioPorCPFESenha(cpf, new Encryption().encrypt(senha));		
		result.use(Results.json()).withoutRoot().from(usuario).serialize();	
	}
	
}

package br.gov.mt.saude.cuiaba.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.SecretariaDAOImpl;
import br.gov.mt.saude.cuiaba.model.Secretaria;

@Controller
public class SecretariaController {
	private final Result result;
	private final SecretariaDAOImpl secretariadao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public SecretariaController(Result result, SecretariaDAOImpl secretariadao, UsuarioSessao sessao,
			Validacao validacao, Validator validation) {
		this.result = result;
		this.secretariadao = secretariadao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}

	
	public SecretariaController() {
		this(null,null,null,null,null);
	}
	
	@Path("/secretaria/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "secretaria").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/secretaria/salvar")
	public void salvar(@Valid Secretaria secretaria){
		if(secretaria.getIdsecretaria() == null) {
			validation.addIf(secretariadao.validaNomeSecretaria(secretaria.getNomsecretaria()), new SimpleMessage("secretaria", "Secretaria já cadastrada !"));
			validacao.valida(1,"secretaria").onErrorForwardTo(AdministrativoController.class).error();
			secretariadao.save(secretaria);
		}else {
			validacao.valida(2,"secretaria").onErrorForwardTo(AdministrativoController.class).error();
			secretariadao.update(secretaria);
		}
		
		result.nothing();
	}
	
	@Path("/secretaria/grid")
	public void grid(){
		result.use(Results.json()).withoutRoot().from(secretariadao.pesquisaSecretariaGeral()).serialize();
	}
	
	@Path("/secretaria/editar")
	public void editar(int codigo){
		result.use(Results.json()).withoutRoot().from(secretariadao.getById(Secretaria.class, codigo)).serialize();
	}
	
	@Path("/secretaria/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "secretaria").onErrorForwardTo(AdministrativoController.class).error();
		Secretaria secretaria = secretariadao.getById(Secretaria.class, codigo);
		secretariadao.delete(secretaria);
		result.nothing();
	}
	
	@Get("/secretaria/mobile/importarSecretaria")
	public void importarSecretaria() {
		result.use(Results.json()).withoutRoot().from(secretariadao.pesquisaSecretariaGeral()).serialize();
	}
	
	
}

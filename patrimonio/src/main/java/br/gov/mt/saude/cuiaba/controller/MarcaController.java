package br.gov.mt.saude.cuiaba.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.caelum.vraptor.Consumes;
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
import br.gov.mt.saude.cuiaba.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.MarcaMobile;
import br.gov.mt.saude.cuiaba.model.Marca;

@Controller
public class MarcaController {
	private final Result result;
	private final MarcaDAOImpl marcadao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public MarcaController(Result result, MarcaDAOImpl marcadao, UsuarioSessao sessao,
			Validacao validacao, Validator validation) {
		this.result = result;
		this.marcadao = marcadao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}

	
	public MarcaController() {
		this(null,null,null,null,null);
	}
	
	@Path("/marca/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "marca").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/marca/salvar")
	public void salvar(@Valid Marca marca){
		if(marca.getIdmarca() == null) {
			validation.addIf(marcadao.validaDescricaoMarca(marca.getDescmarca()), new SimpleMessage("marca", "Marca já cadastrada !"));
			validacao.valida(1,"marca").onErrorForwardTo(AdministrativoController.class).error();
			marcadao.save(marca);
		}else {
			validacao.valida(2,"marca").onErrorForwardTo(AdministrativoController.class).error();
			marcadao.update(marca);
		}
		
		result.nothing();
	}
	
	@Path("/marca/grid")
	public void grid(){
		result.use(Results.json()).withoutRoot().from(marcadao.pesquisaMarcaGeral()).serialize();
	}
	
	@Path("/marca/editar")
	public void editar(int codigo){
		result.use(Results.json()).withoutRoot().from(marcadao.getById(Marca.class, codigo)).serialize();
	}
	
	@Path("/marca/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "marca").onErrorForwardTo(AdministrativoController.class).error();
		Marca marca = marcadao.getById(Marca.class, codigo);
		marcadao.delete(marca);
		result.nothing();
	}
	
	@Get("/marca/mobile/importarMarca")
	public void importarMarca() {
		result.use(Results.json()).withoutRoot().from(marcadao.pesquisaMarcaGeral()).serialize();
	}
	
	@Consumes("application/json")
	@Post("/marca/receberDadosTablet")
	public void receberDadosTablet(List<MarcaMobile> marcas) {
		for (MarcaMobile marcaMobile : marcas) {
			Marca marca = marcadao.getById(Marca.class, (int)marcaMobile.getIdmarca());
			if(marca != null) {
				marca.setDescmarca(marcaMobile.getDescmarca());
				marcadao.update(marca);
			}else {
				Marca m = new Marca();
				m.setDescmarca(marcaMobile.getDescmarca());
				marcadao.save(m);
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
	}

}

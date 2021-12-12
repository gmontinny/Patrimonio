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
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.GrupoMobile;
import br.gov.mt.saude.cuiaba.model.Grupo;

@Controller
public class GrupoController {
	private final Result result;
	private final GrupoDAOImpl grupodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public GrupoController(Result result, GrupoDAOImpl grupodao, UsuarioSessao sessao,
			Validacao validacao, Validator validation) {
		this.result = result;
		this.grupodao = grupodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}

	
	public GrupoController() {
		this(null,null,null,null,null);
	}
	
	@Path("/grupo/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "grupo").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/grupo/salvar")
	public void salvar(@Valid Grupo grupo){
		if(grupo.getIdgrupo() == null) {
			validation.addIf(grupodao.validaDescricaoGrupo(grupo.getDescgrupo()), new SimpleMessage("grupo", "grupo já cadastrada !"));
			validacao.valida(1,"grupo").onErrorForwardTo(AdministrativoController.class).error();
			grupodao.save(grupo);
		}else {
			validacao.valida(2,"grupo").onErrorForwardTo(AdministrativoController.class).error();
			grupodao.update(grupo);
		}
		
		result.nothing();
	}
	
	@Path("/grupo/grid")
	public void grid(){
		result.use(Results.json()).withoutRoot().from(grupodao.pesquisaGrupoGeral()).serialize();
	}
	
	@Path("/grupo/editar")
	public void editar(int codigo){
		result.use(Results.json()).withoutRoot().from(grupodao.getById(Grupo.class, codigo)).serialize();
	}
	
	@Path("/grupo/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "grupo").onErrorForwardTo(AdministrativoController.class).error();
		Grupo grupo = grupodao.getById(Grupo.class, codigo);
		grupodao.delete(grupo);
		result.nothing();
	}
	
	@Get("/grupo/mobile/importarGrupo")
	public void importarGrupo() {
		result.use(Results.json()).withoutRoot().from(grupodao.pesquisaGrupoGeral()).serialize();
	}

	@Consumes("application/json")
	@Post("/grupo/receberDadosTablet")
	public void receberDadosTablet(List<GrupoMobile> grupos) {
		for (GrupoMobile grupoMobile : grupos) {
			Grupo grupo = grupodao.getById(Grupo.class,(int) grupoMobile.getIdgrupo());	
			if(grupo != null) {
				grupo.setDescgrupo(grupoMobile.getDescgrupo());
				grupodao.update(grupo);
			}else {
				Grupo g = new Grupo();
				g.setDescgrupo(grupoMobile.getDescgrupo());
				grupodao.save(g);
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
		
	}
	
}

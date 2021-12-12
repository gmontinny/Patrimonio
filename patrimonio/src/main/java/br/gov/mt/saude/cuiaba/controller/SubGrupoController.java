package br.gov.mt.saude.cuiaba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.gov.mt.saude.cuiaba.dao.impl.GrupoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.SubGrupoMobile;
import br.gov.mt.saude.cuiaba.model.Grupo;
import br.gov.mt.saude.cuiaba.model.Subgrupo;

@Controller
public class SubGrupoController {
	private final Result result;
	private final SubGrupoDAOImpl subgrupodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final GrupoDAOImpl grupodao;
	
	@Inject
	public SubGrupoController(Result result, SubGrupoDAOImpl subgrupodao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, GrupoDAOImpl grupodao) {	
		this.result = result;
		this.subgrupodao = subgrupodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.grupodao = grupodao;
	}
	
	public SubGrupoController() {
		this(null,null,null,null,null,null);
	}
	
	
	@Path("/subgrupo/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "subgrupo").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/subgrupo/salvar")
	public void salvar(@Valid Subgrupo subgrupo) {
		if(subgrupo.getIdsubgrupo() == null) {
			validation.addIf(subgrupodao.validaNomeSubGrupo(subgrupo.getDescsubgrupo()), new SimpleMessage("subgrupo", "SubGrupo já cadastrada !"));
			validacao.valida(1,"subgrupo").onErrorForwardTo(AdministrativoController.class).error();
			subgrupodao.save(subgrupo);			
		}else {
			validacao.valida(2,"subgrupo").onErrorForwardTo(AdministrativoController.class).error();
			subgrupodao.update(subgrupo);
		}
		
		result.nothing();
	}
	
	@Path("/subgrupo/grid")
	public void grid(){
		List<Subgrupo> subgrupos = subgrupodao.pesquisaGeralSubGrupo();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		subgrupos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsubgrupo", s.getIdsubgrupo());
			resultado.put("descsubgrupo", s.getDescsubgrupo());
			resultado.put("idgrupo", s.getGrupo().getIdgrupo());
			resultado.put("descgrupo", s.getGrupo().getDescgrupo());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/subgrupo/pesquisaSubGrupoPorGrupo")
	public void pesquisaSubGrupoPorGrupo(int idgrupo){
		List<Subgrupo> subgrupos = subgrupodao.pesquisaSubgrupoPorGrupo(idgrupo);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		subgrupos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsubgrupo", s.getIdsubgrupo());
			resultado.put("descsubgrupo", s.getDescsubgrupo());
			resultado.put("idgrupo", s.getGrupo().getIdgrupo());
			resultado.put("descgrupo", s.getGrupo().getDescgrupo());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/subgrupo/pesquisaSubgrupoPorCodigoEGrupo")
	public void pesquisaSubgrupoPorCodigoEGrupo(int codigo, int idgrupo){
		Subgrupo subgrupo = subgrupodao.pesquisaSubGrupoPorCodigoEGrupo(codigo, idgrupo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idsubgrupo", subgrupo.getIdsubgrupo());
		resultado.put("descsubgrupo", subgrupo.getDescsubgrupo());
		resultado.put("idgrupo", subgrupo.getGrupo().getIdgrupo());
		resultado.put("descgrupo", subgrupo.getGrupo().getDescgrupo());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/subgrupo/editar")
	public void editar(int codigo){
		Subgrupo subgrupo = subgrupodao.getById(Subgrupo.class, codigo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idsubgrupo", subgrupo.getIdsubgrupo());
		resultado.put("descsubgrupo", subgrupo.getDescsubgrupo());
		resultado.put("idgrupo", subgrupo.getGrupo().getIdgrupo());
		resultado.put("descgrupo", subgrupo.getGrupo().getDescgrupo());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/subgrupo/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "subgrupo").onErrorForwardTo(AdministrativoController.class).error();
		Subgrupo subgrupo = subgrupodao.getById(Subgrupo.class, codigo);
		subgrupodao.delete(subgrupo);
		result.nothing();
	}
	
	@Get("/subgrupo/mobile/importarSubGrupo")
	public void importarSubGrupo() {
		List<Subgrupo> subgrupos = subgrupodao.pesquisaGeralSubGrupo();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		subgrupos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsubgrupo", s.getIdsubgrupo());
			resultado.put("descsubgrupo", s.getDescsubgrupo());
			resultado.put("idgrupo", s.getGrupo().getIdgrupo());
			resultado.put("descgrupo", s.getGrupo().getDescgrupo());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();		
	}
	
	@Consumes("application/json")
	@Post("/subgrupo/receberDadosTablet")
	public void receberDadosTablet(List<SubGrupoMobile> sugrupos) {
		for (SubGrupoMobile subGrupoMobile : sugrupos) {			
			Subgrupo subgrupo = subgrupodao.getById(Subgrupo.class, (int)subGrupoMobile.getIdsubgrupo());
			if(subgrupo != null) {
				Grupo grupo = grupodao.getById(Grupo.class, subGrupoMobile.getIdgrupo());
				subgrupo.setGrupo(grupo);
				subgrupo.setDescsubgrupo(subGrupoMobile.getDescsubgrupo());
				subgrupodao.update(subgrupo);
			}else {
				Grupo grupo = grupodao.getById(Grupo.class, subGrupoMobile.getIdgrupo());
				Subgrupo s = new Subgrupo();
				s.setGrupo(grupo);
				s.setDescsubgrupo(subGrupoMobile.getDescsubgrupo());
				subgrupodao.save(s);
			}
			result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
			
		}
	}
	

}

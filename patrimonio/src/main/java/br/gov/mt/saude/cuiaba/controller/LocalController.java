package br.gov.mt.saude.cuiaba.controller;

import java.util.ArrayList;
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
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.model.Local;

@Controller
public class LocalController {
	private final Result result;
	private final LocalDAOImpl localdao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public LocalController(Result result, LocalDAOImpl localdao, UsuarioSessao sessao, Validacao validacao,
			Validator validation) {
		this.result = result;
		this.localdao = localdao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}
	
	public LocalController() {
		this(null,null,null,null,null);
	}
	
	@Path("/local/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "local").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/local/salvar")
	public void salvar(@Valid Local local) {
		if(local.getIdlocal() == null) {
			validation.addIf(localdao.validaNomeLocal(local.getSetor().getIdsetor(), local.getDesclocal()), new SimpleMessage("local", "Local já cadastrada !"));
			validacao.valida(1,"local").onErrorForwardTo(AdministrativoController.class).error();
			localdao.save(local);
		}else {
			validacao.valida(2,"local").onErrorForwardTo(AdministrativoController.class).error();
			localdao.update(local);
		}
		
		result.nothing();
	}
	
	@Path("/local/grid")
	public void grid(){
		List<Local> locals = localdao.pesquisaGeralLocal();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		locals
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idlocal", l.getIdlocal());
			resultado.put("desclocal", l.getDesclocal());
			resultado.put("idsetor", l.getSetor().getIdsetor());
			resultado.put("nomsetor", l.getSetor().getNomsetor());
			resultado.put("idsecretaria", l.getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", l.getSetor().getSecretaria().getNomsecretaria());			
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/local/pesquisaLocalPorSetor")
	public void pesquisaLocalPorSetor(int idsetor) {
		List<Local> locals = localdao.pesquisaLocalPorSetor(idsetor);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		locals
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idlocal", l.getIdlocal());
			resultado.put("desclocal", l.getDesclocal());
			resultado.put("idsetor", l.getSetor().getIdsetor());
			resultado.put("nomsetor", l.getSetor().getNomsetor());
			resultado.put("idsecretaria", l.getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", l.getSetor().getSecretaria().getNomsecretaria());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/local/pesquisaLocalPorCodigoESetor")
	public void pesquisaLocalPorCodigoESetor(int codigo , int idsetor) {
		Local local = localdao.pesquisaLocalPorCodigoESetor(codigo, idsetor);

		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idlocal", local.getIdlocal());
		resultado.put("desclocal", local.getDesclocal());
		resultado.put("idsetor", local.getSetor().getIdsetor());
		resultado.put("nomsetor", local.getSetor().getNomsetor());
		resultado.put("idsecretaria", local.getSetor().getSecretaria().getIdsecretaria());
		resultado.put("nomsecretaria", local.getSetor().getSecretaria().getNomsecretaria());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/local/editar")
	public void editar(int codigo){
		Local local = localdao.getById(Local.class, codigo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idlocal", local.getIdlocal());
		resultado.put("desclocal", local.getDesclocal());
		resultado.put("idsetor", local.getSetor().getIdsetor());
		resultado.put("nomsetor", local.getSetor().getNomsetor());
		resultado.put("idsecretaria", local.getSetor().getSecretaria().getIdsecretaria());
		resultado.put("nomsecretaria", local.getSetor().getSecretaria().getNomsecretaria());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/local/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "local").onErrorForwardTo(AdministrativoController.class).error();
		Local local = localdao.getById(Local.class, codigo);
		localdao.delete(local);
		result.nothing();
	}
	
	@Get("/local/mobile/importarLocal")
	public void importarLocal() {
		List<Local> locals = localdao.pesquisaGeralLocal();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		locals
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idlocal", l.getIdlocal());
			resultado.put("desclocal", l.getDesclocal());
			resultado.put("idsetor", l.getSetor().getIdsetor());
			resultado.put("nomsetor", l.getSetor().getNomsetor());
			resultado.put("idsecretaria", l.getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", l.getSetor().getSecretaria().getNomsecretaria());			
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();		
	}
	
}

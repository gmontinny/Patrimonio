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
import br.gov.mt.saude.cuiaba.dao.impl.SetorDAOImpl;
import br.gov.mt.saude.cuiaba.model.Setor;

@Controller
public class SetorController {
	private final Result result;
	private final SetorDAOImpl setordao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	
	@Inject
	public SetorController(Result result, SetorDAOImpl setordao, UsuarioSessao sessao, Validacao validacao,
			Validator validation) {	
		this.result = result;
		this.setordao = setordao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}
	
	public SetorController() {
		this(null,null,null,null,null);
	}
	
	
	@Path("/setor/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "setor").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/setor/salvar")
	public void salvar(@Valid Setor setor) {
		if(setor.getIdsetor() == null) {
			validation.addIf(setordao.validaNomeSetor(setor.getNomsetor()), new SimpleMessage("setor", "Setor já cadastrada !"));
			validacao.valida(1,"setor").onErrorForwardTo(AdministrativoController.class).error();
			setordao.save(setor);			
		}else {
			validacao.valida(2,"setor").onErrorForwardTo(AdministrativoController.class).error();
			setordao.update(setor);
		}
		
		result.nothing();
	}
	
	@Path("/setor/grid")
	public void grid(){
		List<Setor> setors = setordao.pesquisaGeralSetor();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		setors
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsetor", s.getIdsetor());
			resultado.put("nomsetor", s.getNomsetor());
			resultado.put("idsecretaria", s.getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", s.getSecretaria().getNomsecretaria());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/setor/pesquisaSetorPorSecretaria")
	public void pesquisaSetorPorSecretaria(int idsecretaria){
		List<Setor> setors = setordao.pesquisaSetorPorSecretaria(idsecretaria);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		setors
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsetor", s.getIdsetor());
			resultado.put("nomsetor", s.getNomsetor());
			resultado.put("idsecretaria", s.getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", s.getSecretaria().getNomsecretaria());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/setor/pesquisaSetorPorCodigoESecretaria")
	public void pesquisaSetorPorSecretaria(int codigo, int idsecretaria){
		Setor setor = setordao.pesquisaSetorPorCodigoESecretaria(codigo, idsecretaria);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idsetor", setor.getIdsetor());
		resultado.put("nomsetor", setor.getNomsetor());
		resultado.put("idsecretaria", setor.getSecretaria().getIdsecretaria());
		resultado.put("nomsecretaria", setor.getSecretaria().getNomsecretaria());
		
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/setor/editar")
	public void editar(int codigo){
		Setor setor = setordao.getById(Setor.class, codigo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idsetor", setor.getIdsetor());
		resultado.put("nomsetor", setor.getNomsetor());
		resultado.put("idsecretaria", setor.getSecretaria().getIdsecretaria());
		resultado.put("nomsecretaria", setor.getSecretaria().getNomsecretaria());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/setor/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "setor").onErrorForwardTo(AdministrativoController.class).error();
		Setor setor = setordao.getById(Setor.class, codigo);
		setordao.delete(setor);
		result.nothing();
	}
	
	
	@Get("/setor/mobile/importarSetor")
	public void importarSetor() {
		List<Setor> setors = setordao.pesquisaGeralSetor();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		setors
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idsetor", s.getIdsetor());
			resultado.put("nomsetor", s.getNomsetor());
			resultado.put("idsecretaria", s.getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", s.getSecretaria().getNomsecretaria());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();		
	}
	

}

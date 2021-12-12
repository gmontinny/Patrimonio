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
import br.gov.mt.saude.cuiaba.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.SubGrupoDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.MaterialMobile;
import br.gov.mt.saude.cuiaba.model.Material;
import br.gov.mt.saude.cuiaba.model.Subgrupo;

@Controller
public class MaterialController {
	private final Result result;
	private final MaterialDAOImpl materialdao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final SubGrupoDAOImpl subgrupodao;
	
	@Inject
	public MaterialController(Result result, MaterialDAOImpl materialdao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, SubGrupoDAOImpl subgrupodao) {
		this.result = result;
		this.materialdao = materialdao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.subgrupodao = subgrupodao;
	}
	
	public MaterialController() {
		this(null,null,null,null,null,null);
	}
	
	@Path("/material/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "material").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/material/salvar")
	public void salvar(@Valid Material material) {
		if(material.getIdmaterial() == null) {
			validation.addIf(materialdao.validaDescricaoMaterial(material.getSubgrupo().getIdsubgrupo(), material.getDescmaterial()), new SimpleMessage("material", "Material já cadastrada !"));
			validacao.valida(1,"material").onErrorForwardTo(AdministrativoController.class).error();
			materialdao.save(material);
		}else {
			validacao.valida(2,"material").onErrorForwardTo(AdministrativoController.class).error();
			materialdao.update(material);
		}
		
		result.nothing();
	}
	
	@Path("/material/grid")
	public void grid(){
		List<Material> materials = materialdao.pesquisaGeralMaterial();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		materials
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmaterial", l.getIdmaterial());
			resultado.put("descmaterial", l.getDescmaterial());
			resultado.put("idsubgrupo", l.getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", l.getSubgrupo().getDescsubgrupo());
			resultado.put("idgrupo", l.getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", l.getSubgrupo().getGrupo().getDescgrupo());			
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/material/pesquisaMaterialPorSubGrupo")
	public void pesquisaMaterialPorSubGrupo(int idsubgrupo) {
		List<Material> materials = materialdao.pesquisaMaterialPorSubGrupo(idsubgrupo);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		materials
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmaterial", l.getIdmaterial());
			resultado.put("descmaterial", l.getDescmaterial());
			resultado.put("idsubgrupo", l.getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", l.getSubgrupo().getDescsubgrupo());
			resultado.put("idgrupo", l.getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", l.getSubgrupo().getGrupo().getDescgrupo());	
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/material/pesquisaMaterialPorCodigoESubgrupo")
	public void pesquisaMaterialPorCodigoESubgrupo(int codigo, int idsubgrupo){
		Material material = materialdao.pesquisaMaterialPorCodigoESubgrupo(codigo, idsubgrupo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idmaterial", material.getIdmaterial());
		resultado.put("descmaterial", material.getDescmaterial());
		resultado.put("idsubgrupo", material.getSubgrupo().getIdsubgrupo());
		resultado.put("descsubgrupo", material.getSubgrupo().getDescsubgrupo());
		resultado.put("idgrupo", material.getSubgrupo().getGrupo().getIdgrupo());
		resultado.put("descgrupo", material.getSubgrupo().getGrupo().getDescgrupo());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/material/editar")
	public void editar(int codigo){
		Material material = materialdao.getById(Material.class, codigo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idmaterial", material.getIdmaterial());
		resultado.put("descmaterial", material.getDescmaterial());
		resultado.put("idsubgrupo", material.getSubgrupo().getIdsubgrupo());
		resultado.put("descsubgrupo", material.getSubgrupo().getDescsubgrupo());
		resultado.put("idgrupo", material.getSubgrupo().getGrupo().getIdgrupo());
		resultado.put("descgrupo", material.getSubgrupo().getGrupo().getDescgrupo());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/material/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "material").onErrorForwardTo(AdministrativoController.class).error();
		Material material = materialdao.getById(Material.class, codigo);
		materialdao.delete(material);
		result.nothing();
	}
	
	@Get("/material/mobile/importarMaterial")
	public void importarMaterial() {
		List<Material> materials = materialdao.pesquisaGeralMaterial();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		materials
		.stream()
		.filter(l -> l != null)
		.forEach(l -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmaterial", l.getIdmaterial());
			resultado.put("descmaterial", l.getDescmaterial());
			resultado.put("idsubgrupo", l.getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", l.getSubgrupo().getDescsubgrupo());
			resultado.put("idgrupo", l.getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", l.getSubgrupo().getGrupo().getDescgrupo());			
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();		
	}
	
	@Consumes("application/json")
	@Post("/material/receberDadosTablet")
	public void receberDadosTablet(List<MaterialMobile> materias) {
		for (MaterialMobile materialMobile : materias) {
			Material material = materialdao.getById(Material.class, (int)materialMobile.getIdmaterial());
			if(material != null) {
				Subgrupo subgrupo = subgrupodao.getById(Subgrupo.class, materialMobile.getIdsubgrupo());
				material.setSubgrupo(subgrupo);
				material.setDescmaterial(materialMobile.getDescmaterial());
				materialdao.update(material);
			}else {
				Subgrupo subgrupo = subgrupodao.getById(Subgrupo.class, materialMobile.getIdsubgrupo());
				Material m = new Material();
				m.setDescmaterial(materialMobile.getDescmaterial());
				m.setSubgrupo(subgrupo);
				materialdao.save(m);
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
	}
	
}

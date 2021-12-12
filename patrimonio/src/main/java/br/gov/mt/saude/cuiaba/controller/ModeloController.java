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
import br.gov.mt.saude.cuiaba.dao.MarcaDAO;
import br.gov.mt.saude.cuiaba.dao.impl.MarcaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.ModeloMobile;
import br.gov.mt.saude.cuiaba.model.Marca;
import br.gov.mt.saude.cuiaba.model.Modelo;

@Controller
public class ModeloController {
	private final Result result;
	private final ModeloDAOImpl modelodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final MarcaDAOImpl marcadao;
	
	@Inject
	public ModeloController(Result result, ModeloDAOImpl modelodao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, MarcaDAOImpl marcadao) {	
		this.result = result;
		this.modelodao = modelodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.marcadao = marcadao;
	}
	
	public ModeloController() {
		this(null,null,null,null,null,null);
	}
	
	
	@Path("/modelo/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "modelo").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/modelo/salvar")
	public void salvar(@Valid Modelo modelo) {
		if(modelo.getIdmodelo() == null) {
			validation.addIf(modelodao.validaNomeModelo(modelo.getDescmodelo()), new SimpleMessage("modelo", "Modelo já cadastrada !"));
			validacao.valida(1,"modelo").onErrorForwardTo(AdministrativoController.class).error();
			modelodao.save(modelo);			
		}else {
			validacao.valida(2,"modelo").onErrorForwardTo(AdministrativoController.class).error();
			modelodao.update(modelo);
		}
		
		result.nothing();
	}
	
	@Path("/modelo/grid")
	public void grid(){
		List<Modelo> modelos = modelodao.pesquisaGeralModelo();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		modelos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmodelo", s.getIdmodelo());
			resultado.put("descmodelo", s.getDescmodelo());
			resultado.put("idmarca", s.getMarca().getIdmarca());
			resultado.put("descmarca", s.getMarca().getDescmarca());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/modelo/pesquisaModeloPorMarca")
	public void pesquisaModeloPormarca(int idmarca){
		List<Modelo> modelos = modelodao.pesquisaModeloPorMarca(idmarca);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		modelos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmodelo", s.getIdmodelo());
			resultado.put("descmodelo", s.getDescmodelo());
			resultado.put("idmarca", s.getMarca().getIdmarca());
			resultado.put("descmarca", s.getMarca().getDescmarca());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Path("/modelo/pesquisaModeloPorCodigoEMarca")
	public void pesquisaModeloPorCodigoEMarca(int codigo, int idmarca){
		Modelo modelo = modelodao.pesquisaModeloPorCodigoEMarca(codigo, idmarca);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idmodelo", modelo.getIdmodelo());
		resultado.put("descmodelo", modelo.getDescmodelo());
		resultado.put("idmarca", modelo.getMarca().getIdmarca());
		resultado.put("descmarca", modelo.getMarca().getDescmarca());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}	
	
	@Path("/modelo/editar")
	public void editar(int codigo){
		Modelo modelo = modelodao.getById(Modelo.class, codigo);
		
		Map<String, Object> resultado = new HashMap<>();
		resultado.put("idmodelo", modelo.getIdmodelo());
		resultado.put("descmodelo", modelo.getDescmodelo());
		resultado.put("idmarca", modelo.getMarca().getIdmarca());
		resultado.put("descmarca", modelo.getMarca().getDescmarca());
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
		
	}
	
	@Path("/modelo/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "modelo").onErrorForwardTo(AdministrativoController.class).error();
		Modelo modelo = modelodao.getById(Modelo.class, codigo);
		modelodao.delete(modelo);
		result.nothing();
	}
	
	@Get("/modelo/mobile/importarModelo")
	public void importarModelo() {
		List<Modelo> modelos = modelodao.pesquisaGeralModelo();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		modelos
		.stream()
		.filter(s -> s != null)
		.forEach(s -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idmodelo", s.getIdmodelo());
			resultado.put("descmodelo", s.getDescmodelo());
			resultado.put("idmarca", s.getMarca().getIdmarca());
			resultado.put("descmarca", s.getMarca().getDescmarca());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Consumes("application/json")
	@Post("/modelo/receberDadosTablet")
	public void receberDadosTablet(List<ModeloMobile> modelos) {
		for (ModeloMobile modeloMobile : modelos) {
			Modelo modelo = modelodao.getById(Modelo.class,(int) modeloMobile.getIdmodelo());
			if(modelo != null) {
				Marca marca = marcadao.getById(Marca.class, modeloMobile.getIdmarca());
				modelo.setMarca(marca);
				modelo.setDescmodelo(modeloMobile.getDescmodelo());
				modelodao.update(modelo);
			}else {
				Marca marca = marcadao.getById(Marca.class, modeloMobile.getIdmarca());
				Modelo m = new Modelo();
				m.setMarca(marca);
				m.setDescmodelo(modeloMobile.getDescmodelo());
				modelodao.save(m);
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
		
	}
	

}

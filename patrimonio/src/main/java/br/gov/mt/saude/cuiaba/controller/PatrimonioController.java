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
import br.gov.mt.saude.cuiaba.dao.impl.FornecedorDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.MaterialDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.ModeloDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.PatrimonioMobile;
import br.gov.mt.saude.cuiaba.model.Fornecedor;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Material;
import br.gov.mt.saude.cuiaba.model.Modelo;
import br.gov.mt.saude.cuiaba.model.Patrimonio;

@Controller
public class PatrimonioController {
	private final Result result;
	private final PatrimonioDAOImpl patrimoniodao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final FornecedorDAOImpl fornecedordao;
	private final LocalDAOImpl localdao;
	private final MaterialDAOImpl materialdao;
	private final ModeloDAOImpl modelodao;
	
	@Inject
	public PatrimonioController(Result result, PatrimonioDAOImpl patrimoniodao, UsuarioSessao sessao,
			Validacao validacao, Validator validation, FornecedorDAOImpl fornecedordao,LocalDAOImpl localdao, MaterialDAOImpl materialdao, ModeloDAOImpl modelodao) {
		this.result = result;
		this.patrimoniodao = patrimoniodao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.fornecedordao = fornecedordao;
		this.localdao = localdao;
		this.materialdao = materialdao;
		this.modelodao = modelodao;
	}
	
	public PatrimonioController() {
		this(null,null,null,null,null,null,null,null,null);
	}
	
	@Path("/patrimonio/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "patrimonio").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/patrimonio/salvar")
	public void salvar(@Valid Patrimonio patrimonio) {
		if(patrimonio.getIdpatrimonio() == null) {
			validation.addIf(!patrimonio.getIdpatrimonio().isEmpty() && patrimoniodao.validaNumeroPatrimonio(patrimonio.getIdpatrimonio()), new SimpleMessage("patrimonio", "PATRIMÔNIO Já Cadastrado !"));
			validation.addIf(!patrimonio.getSerialpatrimonio().isEmpty() && patrimoniodao.validaNumeroSerial(patrimonio.getSerialpatrimonio()), new SimpleMessage("patrimonio", "SERIAL Já Cadastrado !"));
			validacao.valida(1,"patrimonio").onErrorForwardTo(AdministrativoController.class).error();
			patrimoniodao.save(patrimonio);
		}else {
			validacao.valida(2,"patrimonio").onErrorForwardTo(AdministrativoController.class).error();
			patrimoniodao.update(patrimonio);
		}
		
		result.nothing();
	}
	
	@Path("/patrimonio/pesquisa")
	public void pesquisa(Integer idsecretaria, Integer idsetor, Integer idlocal,
			Integer idgrupo, Integer idsubgrupo, Integer idmaterial, Integer idmarca, Integer idmodelo,
			Integer fornecedor, Integer situacao, Integer baixa, String numeroPatrimonio, String serial) {
		
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(idsecretaria, idsetor, idlocal, idgrupo, idsubgrupo, idmaterial, idmarca, idmodelo, fornecedor, situacao, baixa, numeroPatrimonio, serial);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		patrimonios
		.stream()
		.filter(p -> p != null)
		.forEach(p -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idpatrimonio", p.getIdpatrimonio());
			resultado.put("idmaterial", p.getMaterial().getIdmaterial());
			resultado.put("descmaterial", p.getMaterial().getDescmaterial());
			resultado.put("idgrupo", p.getMaterial().getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", p.getMaterial().getSubgrupo().getGrupo().getDescgrupo());
			resultado.put("idsubgrupo", p.getMaterial().getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", p.getMaterial().getSubgrupo().getDescsubgrupo());
			resultado.put("idlocal", p.getLocal().getIdlocal());
			resultado.put("desclocal", p.getLocal().getDesclocal());
			resultado.put("idsecretaria", p.getLocal().getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", p.getLocal().getSetor().getSecretaria().getNomsecretaria());
			resultado.put("idsetor", p.getLocal().getSetor().getIdsetor());
			resultado.put("nomsetor", p.getLocal().getSetor().getNomsetor());
			resultado.put("idfornecedor", p.getFornecedor().getIdfornecedor());
			resultado.put("razaosocialfornecedor", p.getFornecedor().getRazaosocial());
			resultado.put("nomefantasiafornecedor", p.getFornecedor().getNomefantasia());
			resultado.put("idmodelo", p.getModelo().getIdmodelo());
			resultado.put("descmodelo", p.getModelo().getDescmodelo());
			resultado.put("idmarca", p.getModelo().getMarca().getIdmarca());
			resultado.put("descmarca", p.getModelo().getMarca().getDescmarca());
			resultado.put("situacaopatrimonio", p.getSituacaopatrimonio());
			resultado.put("situacaopatrimonio", p.getSituacaopatrimonio());
			resultado.put("serialpatrimonio", p.getSerialpatrimonio());
			resultado.put("statusbaixa", p.getStatusbaixa());
			resultado.put("obspatrimonio", p.getObspatrimonio());
			resultado.put("numeroarquivo", p.getNumeroarquivo());
			resultado.put("imgpatrimonio", p.getImgpatrimonio());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/patrimonio/editar")
	public void editar(String numeroPatrimonio) {
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(null, null, null, null, null, null, null, null, null, null, null, numeroPatrimonio, null);
		List<Map<String, Object>> dados = new ArrayList<>();
		
		patrimonios
		.stream()
		.filter(p -> p != null)
		.forEach(p -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idpatrimonio", p.getIdpatrimonio());
			resultado.put("idmaterial", p.getMaterial().getIdmaterial());
			resultado.put("descmaterial", p.getMaterial().getDescmaterial());
			resultado.put("idgrupo", p.getMaterial().getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", p.getMaterial().getSubgrupo().getGrupo().getDescgrupo());
			resultado.put("idsubgrupo", p.getMaterial().getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", p.getMaterial().getSubgrupo().getDescsubgrupo());
			resultado.put("idlocal", p.getLocal().getIdlocal());
			resultado.put("desclocal", p.getLocal().getDesclocal());
			resultado.put("idsecretaria", p.getLocal().getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", p.getLocal().getSetor().getSecretaria().getNomsecretaria());
			resultado.put("idsetor", p.getLocal().getSetor().getIdsetor());
			resultado.put("nomsetor", p.getLocal().getSetor().getNomsetor());
			resultado.put("idfornecedor", p.getFornecedor().getIdfornecedor());
			resultado.put("razaosocialfornecedor", p.getFornecedor().getRazaosocial());
			resultado.put("nomefantasiafornecedor", p.getFornecedor().getNomefantasia());
			resultado.put("idmodelo", p.getModelo().getIdmodelo());
			resultado.put("descmodelo", p.getModelo().getDescmodelo());
			resultado.put("idmarca", p.getModelo().getMarca().getIdmarca());
			resultado.put("descmarca", p.getModelo().getMarca().getDescmarca());
			resultado.put("situacaopatrimonio", p.getSituacaopatrimonio());			
			resultado.put("serialpatrimonio", p.getSerialpatrimonio());
			resultado.put("statusbaixa", p.getStatusbaixa());
			resultado.put("obspatrimonio", p.getObspatrimonio());
			resultado.put("numeroarquivo", p.getNumeroarquivo());
			resultado.put("imgpatrimonio", p.getImgpatrimonio());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
		
	}
	
	@Get("/patrimonio/mobile/importarPatrimonio")
	public void importarPatrimonio() {
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(null, null, null, null, null, null, null, null, null, null, null, null, null);
		List<Map<String, Object>> dados = new ArrayList<>();
		patrimonios
		.stream()
		.filter(p -> p != null)
		.forEach(p -> {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idpatrimonio", p.getIdpatrimonio());
			resultado.put("idmaterial", p.getMaterial().getIdmaterial());
			resultado.put("descmaterial", p.getMaterial().getDescmaterial());
			resultado.put("idgrupo", p.getMaterial().getSubgrupo().getGrupo().getIdgrupo());
			resultado.put("descgrupo", p.getMaterial().getSubgrupo().getGrupo().getDescgrupo());
			resultado.put("idsubgrupo", p.getMaterial().getSubgrupo().getIdsubgrupo());
			resultado.put("descsubgrupo", p.getMaterial().getSubgrupo().getDescsubgrupo());
			resultado.put("idlocal", p.getLocal().getIdlocal());
			resultado.put("desclocal", p.getLocal().getDesclocal());
			resultado.put("idsecretaria", p.getLocal().getSetor().getSecretaria().getIdsecretaria());
			resultado.put("nomsecretaria", p.getLocal().getSetor().getSecretaria().getNomsecretaria());
			resultado.put("idsetor", p.getLocal().getSetor().getIdsetor());
			resultado.put("nomsetor", p.getLocal().getSetor().getNomsetor());
			resultado.put("idfornecedor", p.getFornecedor().getIdfornecedor());
			resultado.put("razaosocialfornecedor", p.getFornecedor().getRazaosocial());
			resultado.put("nomefantasiafornecedor", p.getFornecedor().getNomefantasia());
			resultado.put("idmodelo", p.getModelo().getIdmodelo());
			resultado.put("descmodelo", p.getModelo().getDescmodelo());
			resultado.put("idmarca", p.getModelo().getMarca().getIdmarca());
			resultado.put("descmarca", p.getModelo().getMarca().getDescmarca());
			resultado.put("situacaopatrimonio", p.getSituacaopatrimonio());			
			resultado.put("serialpatrimonio", p.getSerialpatrimonio());
			resultado.put("statusbaixa", p.getStatusbaixa());
			resultado.put("obspatrimonio", p.getObspatrimonio());
			resultado.put("numeroarquivo", p.getNumeroarquivo());
			resultado.put("imgpatrimonio", p.getImgpatrimonio());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/patrimonio/deletar")
	public void deletar(String numeroPatrimonio) {
		validacao.valida(3,"patrimonio").onErrorForwardTo(AdministrativoController.class).error();
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(null, null, null, null, null, null, null, null, null, null, null, numeroPatrimonio, null);
		patrimoniodao.delete(patrimonios.get(0));
		result.nothing();
	}
	
	@Consumes("application/json")
	@Post("/patrimonio/receberDadosTablet")
	public void receberDadosTablet(List<PatrimonioMobile> patrimonios) {
		for (PatrimonioMobile patrimonioMobile : patrimonios) {
			Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(patrimonioMobile.getIdpatrimonio());
			if(patrimonio != null) {
				Fornecedor fornecedor = fornecedordao.getById(Fornecedor.class, patrimonioMobile.getIdfornecedor());
				Local local = localdao.getById(Local.class, patrimonioMobile.getIdlocal());
				Material material = materialdao.getById(Material.class, patrimonioMobile.getIdmaterial());
				Modelo modelo = modelodao.getById(Modelo.class, patrimonioMobile.getIdmodelo());
				
				patrimonio.setFornecedor(fornecedor);
				patrimonio.setLocal(local);
				patrimonio.setMaterial(material);
				patrimonio.setModelo(modelo);
				patrimonio.setObspatrimonio(patrimonioMobile.getObspatrimonio());
				patrimonio.setSerialpatrimonio(patrimonioMobile.getSerialpatrimonio());
				patrimonio.setSituacaopatrimonio(patrimonioMobile.getSituacaopatrimonio());
				patrimoniodao.update(patrimonio);
			}else {
				
				Fornecedor fornecedor = fornecedordao.getById(Fornecedor.class, patrimonioMobile.getIdfornecedor());
				Local local = localdao.getById(Local.class, patrimonioMobile.getIdlocal());
				Material material = materialdao.getById(Material.class, patrimonioMobile.getIdmaterial());
				Modelo modelo = modelodao.getById(Modelo.class, patrimonioMobile.getIdmodelo());
				
				Patrimonio p = new Patrimonio();
				p.setIdpatrimonio(patrimonioMobile.getIdpatrimonio());
				p.setFornecedor(fornecedor);
				p.setLocal(local);
				p.setMaterial(material);
				p.setModelo(modelo);
				p.setNumeroarquivo(0);
				p.setObspatrimonio(patrimonioMobile.getObspatrimonio());
				p.setSerialpatrimonio(patrimonioMobile.getSerialpatrimonio());
				p.setSituacaopatrimonio(patrimonioMobile.getSituacaopatrimonio());
				patrimoniodao.save(p);				
				
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
	} 
	
}

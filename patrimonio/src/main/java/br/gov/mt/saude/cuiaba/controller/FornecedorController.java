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
import br.gov.mt.saude.cuiaba.dao.impl.FornecedorDAOImpl;
import br.gov.mt.saude.cuiaba.model.Fornecedor;

@Controller
public class FornecedorController {
	private final Result result;
	private final FornecedorDAOImpl fornecedordao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public FornecedorController(Result result, FornecedorDAOImpl fornecedordao, UsuarioSessao sessao,
			Validacao validacao, Validator validation) {
		this.result = result;
		this.fornecedordao = fornecedordao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}
	
	public FornecedorController() {
		this(null,null,null,null,null);
	}
	
	@Path("/fornecedor/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "fornecedor").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/fornecedor/salvar")
	public void salvar(@Valid Fornecedor fornecedor) {
		if(fornecedor.getIdfornecedor() == null) {
			validation.addIf(fornecedordao.validaCNPJFonecedor(fornecedor.getCnpjfornecedor()), new SimpleMessage("fornecedor", "Fornecedor Já Cadastrado !"));
			validacao.valida(1,"fornecedor").onErrorForwardTo(AdministrativoController.class).error();
			fornecedordao.save(fornecedor);
		}else {
			validacao.valida(2,"fornecedor").onErrorForwardTo(AdministrativoController.class).error();
			fornecedordao.update(fornecedor);
		}
		
		result.nothing();
	}
	
	@Path("/fornecedor/grid")
	public void grid() {
		List<Fornecedor> fornecedors = fornecedordao.pesquisaFornecedorGeral();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		fornecedors
		.stream()
		.filter(f->f!=null)
		.forEach(f->{
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idfornecedor", f.getIdfornecedor());
			resultado.put("razaosocial", f.getRazaosocial());
			resultado.put("nomefantasia", f.getNomefantasia());
			resultado.put("cnpjfornecedor", f.getCnpjfornecedor());
			resultado.put("enderecofornecedor", f.getEnderecofornecedor());
			resultado.put("bairrofornecedor", f.getBairrofornecedor());
			resultado.put("emailfornecedor", f.getEmailfornecedor());
			resultado.put("telefonefornecedor", f.getTelefonefornecedor());
			resultado.put("celularfornecedor", f.getCelularfornecedor() == null ? "" : f.getCelularfornecedor());
			resultado.put("contatofornecedor", f.getContatofornecedor());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/fornecedor/editar")
	public void editar(int codigo) {
		result.use(Results.json()).withoutRoot().from(fornecedordao.getById(Fornecedor.class, codigo)).serialize();
	}
	
	@Path("/fornecedor/deletar")
	public void deletar(int codigo) {
		validacao.valida(3, "grupo").onErrorForwardTo(AdministrativoController.class).error();
		Fornecedor fornecedor = fornecedordao.getById(Fornecedor.class, codigo);
		fornecedordao.delete(fornecedor);
		result.nothing();
	}
	
	@Get("/fornecedore/mobile/importarFornecedor")
	public void importarFornecedor() {
		List<Fornecedor> fornecedors = fornecedordao.pesquisaFornecedorGeral();
		List<Map<String, Object>> dados = new ArrayList<>();
		
		fornecedors
		.stream()
		.filter(f->f!=null)
		.forEach(f->{
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("idfornecedor", f.getIdfornecedor());
			resultado.put("razaosocial", f.getRazaosocial());
			resultado.put("nomefantasia", f.getNomefantasia());
			resultado.put("cnpjfornecedor", f.getCnpjfornecedor());
			resultado.put("enderecofornecedor", f.getEnderecofornecedor());
			resultado.put("bairrofornecedor", f.getBairrofornecedor());
			resultado.put("emailfornecedor", f.getEmailfornecedor());
			resultado.put("telefonefornecedor", f.getTelefonefornecedor());
			resultado.put("celularfornecedor", f.getCelularfornecedor() == null ? "" : f.getCelularfornecedor());
			resultado.put("contatofornecedor", f.getContatofornecedor());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();		
	}
	
}

package br.gov.mt.saude.cuiaba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.PermissaoDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TabelaDAOImpl;
import br.gov.mt.saude.cuiaba.model.Tabela;

@Controller  
public class TabelaController {
	private final Result result;
	private final TabelaDAOImpl tabeladao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	private final PermissaoDAOImpl permissaodao;
	
	
	@Inject
	public TabelaController(Result result, TabelaDAOImpl tabeladao, UsuarioSessao sessao, Validacao validacao,
			Validator validation, PermissaoDAOImpl permissaodao) {
		super();
		this.result = result;
		this.tabeladao = tabeladao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
		this.permissaodao = permissaodao;
	}



	public TabelaController() {
		this(null,null,null,null,null,null);
	}
	
	
	@Path("/tabela/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "tabela").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/tabela/salvar")
	public void salvar(@Valid Tabela tabela){
		
		if (tabela.getIdtabela() == null){
			validation.addIf(tabeladao.validaNomeTabela(tabela.getNomtabela()), new SimpleMessage("tabela", "Tabela já cadastrada !"));
			validacao.valida(1,"tabela").onErrorForwardTo(AdministrativoController.class).error();
			tabeladao.save(tabela);
		}else{
			validacao.valida(2,"tabela").onErrorForwardTo(AdministrativoController.class).error();
			tabeladao.update(tabela);			
		}
		
		result.nothing();
	}
	
	@Path("/tabela/grid")
	public void grid(){
		List<Map<String,Object>> dados = new ArrayList<>();
		List<Tabela> tabelas = tabeladao.pesquisaTabelaGeral();
		tabelas
		.stream()
		.filter(t -> t != null)
		.forEach(t-> {
			Map<String,Object> resultado = new HashMap<>();
			resultado.put("idtabela", t.getIdtabela());
			resultado.put("nometabela", t.getNomtabela());
			resultado.put("descricaotabela", t.getDesctabela());
			resultado.put("ordemtabela", t.getOrdtabela());
			resultado.put("tipotabela", t.getTipotabela());
			resultado.put("icontabela", t.getIcontabela());
			resultado.put("urltabela", t.getUrltabela());
			dados.add(resultado);
		});
		
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/tabela/editar")
	public void editar(int codigo){
		result.use(Results.json()).withoutRoot().from(tabeladao.getById(Tabela.class, codigo)).serialize();
	}
	
	@Path("/tabela/deletar")
	public void deletar(int codigo){
		validacao.valida(3, "tabela").onErrorForwardTo(AdministrativoController.class).error();
		Tabela tabela = tabeladao.getById(Tabela.class, codigo);
		tabeladao.delete(tabela);
		result.nothing();
	}
	
	
}

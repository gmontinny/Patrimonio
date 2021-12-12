package br.gov.mt.saude.cuiaba.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.mt.saude.cuiaba.component.UsuarioSessao;
import br.gov.mt.saude.cuiaba.component.Validacao;
import br.gov.mt.saude.cuiaba.dao.impl.GeraArquivoDAOImpl;
import br.gov.mt.saude.cuiaba.model.Geraarquivo;

@Controller
public class GeraArquivoController {
	private final Result result;
	private final GeraArquivoDAOImpl geradao;
	private final UsuarioSessao sessao;
	private final Validacao validacao;
	private final Validator validation;
	
	@Inject
	public GeraArquivoController(Result result, GeraArquivoDAOImpl geradao, UsuarioSessao sessao, Validacao validacao,
			Validator validation) {
		this.result = result;
		this.geradao = geradao;
		this.sessao = sessao;
		this.validacao = validacao;
		this.validation = validation;
	}
	
	public GeraArquivoController() {
		this(null,null,null,null,null);
	}
	
	
	@Post("/geraArquivo/salvar")
	public void salvar(@Valid Geraarquivo geraarquivo) {
		if(geraarquivo.getIdgera() == null) {
			geraarquivo.setDatagera(new Date());
			geraarquivo.setHoragera(new java.sql.Time(new Date().getTime()));
			geraarquivo.setIdusuario(sessao.getUsuario().getIdusuario());
			geradao.save(geraarquivo);
		}else {
			geraarquivo.setDatagera(new Date());
			geraarquivo.setHoragera(new java.sql.Time(new Date().getTime()));
			geraarquivo.setIdusuario(sessao.getUsuario().getIdusuario());
			geraarquivo.setStatusgera(1);
			geradao.update(geraarquivo);
		}
		
		result.nothing();
	}
	
	@Path("/geraArquivo/validaGeraArquivo")
	public void validaGeraArquivo() {
		Geraarquivo geraarquivo = geradao.pesquisaUltimoGeraArquivo(sessao.getUsuario().getIdusuario());
		boolean resultado = geraarquivo == null ? true : false;
		Map<String, Object> dados = new HashMap<>();
		dados.put("resultado", resultado);
		result.use(Results.json()).withoutRoot().from(dados).serialize();
	}
	
	@Path("/geraArquivo/listaNumero")
	public void listaNumero() {
		result.use(Results.json()).withoutRoot().from(geradao.pesquisaUltimoGeraArquivo(sessao.getUsuario().getIdusuario())).serialize();
	}
	
}

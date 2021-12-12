package br.gov.mt.saude.cuiaba.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Time;

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
import br.gov.mt.saude.cuiaba.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.BaixaMobile;
import br.gov.mt.saude.cuiaba.model.Baixa;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.model.Usuario;

@Controller
public class BaixaController {
	private final Result result;
	private final BaixaDAOImpl baixadao;
	private final Validator validator;
	private final Validacao validacao;
	private final UsuarioSessao sessao;
	private final PatrimonioDAOImpl patrimoniodao;
	private final UsuarioDAOImpl usuariodao;

	@Inject
	public BaixaController(Result result, BaixaDAOImpl baixadao, Validator validator, Validacao validacao,
			UsuarioSessao sessao, PatrimonioDAOImpl patrimoniodao, UsuarioDAOImpl usuariodao) {
		this.result = result;
		this.baixadao = baixadao;
		this.validator = validator;
		this.validacao = validacao;
		this.sessao = sessao;
		this.patrimoniodao = patrimoniodao;
		this.usuariodao = usuariodao;
	}

	public BaixaController() {
		this(null,null,null,null,null,null,null);
	}
	
	@Path("/baixa/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "baixa").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/baixa/salvar")
	public void salvar(@Valid Baixa baixa) {
		if(baixa.getIdbaixa() == null) {
			validator.addIf(baixadao.validaBaixaPorPatrimonio(baixa.getIdpatrimonio()), new SimpleMessage("baixa","Patrimonio já Baixado !"));
			validacao.valida(1,"baixa").onErrorForwardTo(AdministrativoController.class).error();
			Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(baixa.getIdpatrimonio());
			patrimonio.setIdpatrimonio(baixa.getIdpatrimonio());
			patrimonio.setStatusbaixa(1);
			patrimoniodao.update(patrimonio);
			
			baixa.setDatabaixa(new Date());
			baixa.setHorabaixa(new java.sql.Time(new Date().getTime()));
			baixa.setUsuario(sessao.getUsuario());
			baixadao.save(baixa);
		}
		
		result.nothing();
	}
	
	
	@Path("/baixa/grid")
	public void grid() {
		List<Object[]> objects = baixadao.pesquisaBaixaGeral();
		List<Map<String, Object>> resultado = new ArrayList<>();
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			dados.put("idpatrimonio", o[0]);
			dados.put("serialpatrimonio", o[1] == null ? "" : o[1]);
			dados.put("idlocal", o[2]);
			dados.put("desclocal", o[3]);
			dados.put("idsetor", o[4]);
			dados.put("nomsetor", o[5]);
			dados.put("idsecretaria", o[6]);
			dados.put("nomsecretaria", o[7]);
			dados.put("idbaixa", o[8]);
			
			resultado.add(dados);
		});
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();		
	}
	
	@Path("/baixa/pesquisa")
	public void pesquisa(Local local) {
		List<Object[]> objects = baixadao.pesquisaBaixaPorLocal(local);
		List<Map<String, Object>> resultado = new ArrayList<>();
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			dados.put("idpatrimonio", o[0]);
			dados.put("serialpatrimonio", o[1] == null ? "" : o[1]);
			dados.put("idlocal", o[2]);
			dados.put("desclocal", o[3]);
			dados.put("idsetor", o[4]);
			dados.put("nomsetor", o[5]);
			dados.put("idsecretaria", o[6]);
			dados.put("nomsecretaria", o[7]);
			dados.put("idbaixa", o[8]);
			
			resultado.add(dados);
		});
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
	}
	
	@Path("/baixa/deletar")
	public void deletar(int codigo) {
		validacao.valida(3,"baixa").onErrorForwardTo(AdministrativoController.class).error();
		Baixa baixa = baixadao.getById(Baixa.class, codigo);
		baixadao.delete(baixa);
		result.nothing();
	}
	
	@Get("/baixa/mobile/importarBaixa")
	public void importarBaixa() {
		List<Baixa> objects = baixadao.pesquisaBaixaMobile();
		List<Map<String, Object>> resultado = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hf = new SimpleDateFormat("HH:mm");
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			dados.put("idbaixa", o.getIdbaixa());
			dados.put("idpatrimonio", o.getIdpatrimonio());
			dados.put("databaixa", df.format(o.getDatabaixa()));
			dados.put("horabaixa", hf.format(o.getHorabaixa()));
			dados.put("idusario", o.getUsuario().getIdusuario());
			
			resultado.add(dados);
		});
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();		
	}
	
	@Consumes("application/json")
	@Post("/baixa/receberDadosTablet")
	public void receberDadosTablet(List<BaixaMobile> baixas) {
		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		
		for (BaixaMobile baixaMobile : baixas) {
			Baixa baixa = baixadao.getById(Baixa.class,(int) baixaMobile.getIdbaixa());
			
			if(baixa != null) {
				
				try {
					
					Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(baixaMobile.getIdpatrimonio());
					patrimonio.setStatusbaixa(1);
					patrimoniodao.update(patrimonio);
					
					Usuario usuario = usuariodao.getById(Usuario.class, baixaMobile.getIdusario());
					Date data = formatterData.parse(baixaMobile.getDatabaixa());					
					baixa.setDatabaixa(data);
					baixa.setHorabaixa(new Time(formatterHora.parse(baixaMobile.getHorabaixa()).getTime()));
					baixa.setIdpatrimonio(baixaMobile.getIdpatrimonio());
					baixa.setUsuario(usuario);
					baixadao.update(baixa);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else {
				try {
					
					Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(baixaMobile.getIdpatrimonio());
					patrimonio.setStatusbaixa(1);
					patrimoniodao.update(patrimonio);
					
					Usuario usuario = usuariodao.getById(Usuario.class, baixaMobile.getIdusario());
					Date data = formatterData.parse(baixaMobile.getDatabaixa());
					Baixa b = new Baixa();
					b.setDatabaixa(data);
					b.setHorabaixa(new Time(formatterHora.parse(baixaMobile.getHorabaixa()).getTime()));
					b.setIdpatrimonio(baixaMobile.getIdpatrimonio());
					b.setUsuario(usuario);
					baixadao.save(b);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}	
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
	}
	
}

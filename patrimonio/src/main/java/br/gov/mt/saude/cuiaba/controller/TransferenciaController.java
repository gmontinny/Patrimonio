package br.gov.mt.saude.cuiaba.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.gov.mt.saude.cuiaba.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.mobile.model.TransferenciaMobile;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.model.Transferencia;
import br.gov.mt.saude.cuiaba.model.Usuario;

@Controller
public class TransferenciaController {
	private final Result result;
	private final TransferenciaDAOImpl transferenciadao;
	private final Validator validator;
	private final Validacao validacao;
	private final UsuarioSessao sessao;
	private final PatrimonioDAOImpl patrimoniodao;
	private final BaixaDAOImpl baixadao;
	private final LocalDAOImpl localdao;
	private final UsuarioDAOImpl usuariodao;
	
	@Inject
	public TransferenciaController(Result result, TransferenciaDAOImpl transferenciadao, Validator validator,
			Validacao validacao, UsuarioSessao sessao, PatrimonioDAOImpl patrimoniodao, BaixaDAOImpl baixadao,
			LocalDAOImpl localdao, UsuarioDAOImpl usuariodao) {	
		this.result = result;
		this.transferenciadao = transferenciadao;
		this.validator = validator;
		this.validacao = validacao;
		this.sessao = sessao;
		this.patrimoniodao = patrimoniodao;
		this.baixadao = baixadao;
		this.localdao = localdao;
		this.usuariodao = usuariodao;
	}
	
	public TransferenciaController() {
		this(null,null,null,null,null,null,null,null,null);
	}
	
	@Path("/transferencia/cadastro")
	public void cadastro(){		
		if (!sessao.isLogged()) {
			result.include("ativar","login");
		}else{
			validacao.valida(0, "transferencia").onErrorForwardTo(AdministrativoController.class).error();			
		}
	}
	
	@Post("/transferencia/salvar")
	public void salvar(@Valid Transferencia transferencia) {
		if(transferencia.getIdtransferencia() == null) {
			validator.addIf(transferencia.getIdlocalorigem() == transferencia.getIdlocaldestino(), new SimpleMessage("transferencia","SETOR ORIGEM igual SETOR DESTINO !"));
			validator.addIf(!baixadao.pesquisaBaixaPorPatrimonioESerial(transferencia.getIdpatrimonio(), null).isEmpty(), new SimpleMessage("transferencia", "PATRIMÔNIO com BAIXA Lançada !"));
			validacao.valida(1,"transferencia").onErrorForwardTo(AdministrativoController.class).error();
			
			Local local = localdao.getById(Local.class, transferencia.getIdlocaldestino());
			Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(transferencia.getIdpatrimonio());
			patrimonio.setLocal(local);
			patrimoniodao.update(patrimonio);
			
			transferencia.setDatatransferencia(new Date());
			transferencia.setHoratransferencia(new java.sql.Time(new Date().getTime()));
			transferencia.setUsuario(sessao.getUsuario());
			transferenciadao.save(transferencia);
		}
		result.nothing();
	}
	
	@Path("/transferencia/grid")
	public void grid() {
		List<Object[]> objects = transferenciadao.pesquisaTransferenciaGeral();
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
			dados.put("idtransferencia", o[8]);
			resultado.add(dados);
		});
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();
	}
	
	@Path("/transferencia/deletar")
	public void deletar(int codigo) {
		validacao.valida(3,"transferencia").onErrorForwardTo(AdministrativoController.class).error();
		Transferencia transferencia = transferenciadao.getById(Transferencia.class, codigo);
		transferenciadao.delete(transferencia);
		result.nothing();
	}
	
	@Get("/transferencia/mobile/importarTransferencia")
	public void importarTransferencia() {
		List<Transferencia> objects = transferenciadao.pesquisaTransferenciaMobile();
		List<Map<String, Object>> resultado = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hf = new SimpleDateFormat("HH:mm");
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			dados.put("idtransferencia", o.getIdtransferencia());
			dados.put("idlocalorigem", o.getIdlocalorigem());
			dados.put("idlocaldestino", o.getIdlocaldestino());
			dados.put("idpatrimonio", o.getIdpatrimonio());
			dados.put("datatransferencia", df.format(o.getDatatransferencia()));
			dados.put("horatransferencia", hf.format(o.getHoratransferencia()));
			dados.put("idusuario", o.getUsuario().getIdusuario());
			resultado.add(dados);
		});
		
		result.use(Results.json()).withoutRoot().from(resultado).serialize();		
	}
	
	@Consumes("application/json")
	@Post("/transferencia/receberDadosTablet")
	public void receberDadosTablet(List<TransferenciaMobile> transferencias) {
		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatterHora = new SimpleDateFormat("HH:mm");
		
		for (TransferenciaMobile transferenciaMobile : transferencias) {
			Transferencia transferencia = transferenciadao.getById(Transferencia.class,(int) transferenciaMobile.getIdtransferencia());
			if(transferencia != null) {
			
				try {
					
					Local local = localdao.getById(Local.class, transferenciaMobile.getIdsetordestino());
					
					Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(transferenciaMobile.getIdpatrimonio());
					patrimonio.setLocal(local);
					patrimoniodao.update(patrimonio);
					
					Usuario usuario = usuariodao.getById(Usuario.class,transferenciaMobile.getIdusuario());
					Date data = formatterData.parse(transferenciaMobile.getDatatransferencia());
					transferencia.setDatatransferencia(data);
					transferencia.setHoratransferencia(new Time(formatterHora.parse(transferenciaMobile.getHoratransferencia()).getTime()));
					transferencia.setIdpatrimonio(transferenciaMobile.getIdpatrimonio());
					transferencia.setIdlocaldestino(transferenciaMobile.getIdsetordestino());
					transferencia.setIdlocalorigem(transferenciaMobile.getIdsetororigem());
					transferencia.setUsuario(usuario);
					transferenciadao.update(transferencia);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}else {
				try {
					
					Local local = localdao.getById(Local.class, transferenciaMobile.getIdsetordestino());
					
					Patrimonio patrimonio = patrimoniodao.pesquisaPorNumeroPatrimonio(transferenciaMobile.getIdpatrimonio());
					patrimonio.setLocal(local);
					patrimoniodao.update(patrimonio);
					
					Usuario usuario = usuariodao.getById(Usuario.class,transferenciaMobile.getIdusuario());
					Date data = formatterData.parse(transferenciaMobile.getDatatransferencia());
					Transferencia t = new Transferencia();
					t.setDatatransferencia(data);
					t.setHoratransferencia(new Time(formatterHora.parse(transferenciaMobile.getHoratransferencia()).getTime()));
					t.setIdpatrimonio(transferenciaMobile.getIdpatrimonio());
					t.setIdlocaldestino(transferenciaMobile.getIdsetordestino());
					t.setIdlocalorigem(transferenciaMobile.getIdsetororigem());
					t.setUsuario(usuario);
					transferenciadao.save(t);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
		}
		
		result.use(Results.http()).setStatusCode(HttpServletResponse.SC_ACCEPTED);
	}
	
}

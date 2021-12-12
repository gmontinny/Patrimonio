package br.gov.mt.saude.cuiaba.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.download.ReportDownload;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;
import br.com.caelum.vraptor.observer.download.Download;
import br.gov.mt.saude.cuiaba.dao.impl.BaixaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.UsuarioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Usuario;
import br.gov.mt.saude.cuiaba.report.BaixaPatrimonioAnaliticoPDF;
import br.gov.mt.saude.cuiaba.report.TransferenciaPatrimonioAnaliticoPDF;
import br.gov.mt.saude.cuiaba.report.model.BaixaPatrimonio;
import br.gov.mt.saude.cuiaba.report.model.TransferenciaPatrimonio;

@Controller
public class RelatorioTransferenciaAnaliticoController {
	private final ServletContext servletContext;
	private final PatrimonioDAOImpl patrimoniodao;
	private final TransferenciaDAOImpl transferenciadao;
	private final LocalDAOImpl localdao;
	private final HttpServletRequest request;
	private final UsuarioDAOImpl usuariodao;
	
	@Inject
	public RelatorioTransferenciaAnaliticoController(ServletContext servletContext, PatrimonioDAOImpl patrimoniodao, TransferenciaDAOImpl transferenciadao , LocalDAOImpl localdao, HttpServletRequest reques,
			UsuarioDAOImpl usuariodao) {
		this.servletContext = servletContext;
		this.patrimoniodao = patrimoniodao;
		this.transferenciadao = transferenciadao;
		this.localdao = localdao;
		request = reques;
		this.usuariodao = usuariodao;
	}
	
	public RelatorioTransferenciaAnaliticoController() {
		this(null,null,null,null,null,null);
	}
	
	@Path("/transferenciaanalitico/pdf")
	public Download pdfReport(int codigo) {
		Report report = null;
		report = generateTransferenciaAnalitico(codigo);
		return new ReportDownload(report, ExportFormats.pdf());
	}

	
	private Report generateTransferenciaAnalitico(int codigo) {				
		List<Object[]> objects = transferenciadao.pesquisaTransferenciaRealizada(codigo);
		List<TransferenciaPatrimonio> transferenciaPatrimonios = new ArrayList<>();
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Local localOrigem  = localdao.getById(Local.class, (int) o[15]);
			Local localDestino = localdao.getById(Local.class, (int) o[16]);
			Usuario usuario = usuariodao.getById(Usuario.class, (int) o[17]);
			TransferenciaPatrimonio transferenciaPatrimonio = new 
					TransferenciaPatrimonio(o[0].toString(),o[1] == null ? "" : o[1].toString(), o[7].toString(),
							localOrigem.getSetor().getNomsetor(), localDestino.getSetor().getNomsetor(), localOrigem.getDesclocal(), localDestino.getDesclocal(), 
							o[9].toString(), o[10].toString(), o[11].toString(), o[12].toString(), o[13].toString(), o[14].toString(), usuario.getNomusuario());
			
			transferenciaPatrimonios.add(transferenciaPatrimonio);
		});
		
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
		
				
		Locale localeBR = new Locale("pt", "BR");
		
		String carimbo = request.getScheme() + "://" + request.getServerName() +":" + request.getServerPort() + request.getContextPath() + "/assets/images/carimbo.png";
		
		ImageIcon gto = new ImageIcon(logo);
		
		
		return new TransferenciaPatrimonioAnaliticoPDF(transferenciaPatrimonios)
				.addParameter("logo",gto.getImage())
				.addParameter("imgBaixa", carimbo)
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}

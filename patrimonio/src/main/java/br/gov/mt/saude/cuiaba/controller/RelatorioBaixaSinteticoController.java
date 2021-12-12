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
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.report.BaixaPatrimonioSinteticoPDF;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioSinteticoPDF;
import br.gov.mt.saude.cuiaba.report.model.BaixaPatrimonio;
import br.gov.mt.saude.cuiaba.report.model.PatrimonioRelatorio;

@Controller
public class RelatorioBaixaSinteticoController {
	private final ServletContext servletContext;
	private final PatrimonioDAOImpl patrimoniodao;
	private final BaixaDAOImpl baixadao;
	private final LocalDAOImpl localdao;
	private final HttpServletRequest request;
	
	@Inject
	public RelatorioBaixaSinteticoController(ServletContext servletContext, PatrimonioDAOImpl patrimoniodao,
			BaixaDAOImpl baixadao, LocalDAOImpl localdao, HttpServletRequest request) {
		this.servletContext = servletContext;
		this.patrimoniodao = patrimoniodao;
		this.baixadao = baixadao;
		this.localdao = localdao;
		this.request = request;
	}

	public RelatorioBaixaSinteticoController() {
		this(null,null,null,null,null);
	}
	
	@Path("/baixasintetico/pdf")
	public Download pdfReport(int codigo) {
		Report report = null;
		report = generateBaixaSintetico(codigo);
		
		return new ReportDownload(report, ExportFormats.pdf());
	}

	
	private Report generateBaixaSintetico(int codigo) {
		Local local = localdao.getById(Local.class, codigo);		
		List<Object[]> objects = baixadao.pesquisaBaixaPorLocal(local);
		List<BaixaPatrimonio> baixaPatrimonios = new ArrayList<>();
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			BaixaPatrimonio baixaPatrimonio = new BaixaPatrimonio(o[0].toString(), o[1].toString(), 
					o[7].toString(), o[5].toString(), o[3].toString(), o[9].toString(), o[10].toString(), o[11].toString(), o[12].toString(), o[13].toString(), o[14].toString());
			baixaPatrimonios.add(baixaPatrimonio);
		});
		
		
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
				
		Locale localeBR = new Locale("pt", "BR");
		ImageIcon gto = new ImageIcon(logo);
		
		return new BaixaPatrimonioSinteticoPDF(baixaPatrimonios)
				.addParameter("logo",gto.getImage())
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}

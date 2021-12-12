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
import br.gov.mt.saude.cuiaba.dao.impl.LocalDAOImpl;
import br.gov.mt.saude.cuiaba.dao.impl.TransferenciaDAOImpl;
import br.gov.mt.saude.cuiaba.model.Local;
import br.gov.mt.saude.cuiaba.report.TransferenciaPatrimonioSinteticoPDF;
import br.gov.mt.saude.cuiaba.report.model.TransferenciaPatrimonio;

@Controller
public class RelatorioTransferenciaSinteticoController {
	private final ServletContext servletContext;
	private final TransferenciaDAOImpl transferenciadao;
	private final LocalDAOImpl localdao;
	private final HttpServletRequest request;
	
	
	@Inject
	public RelatorioTransferenciaSinteticoController(ServletContext servletContext, 
			TransferenciaDAOImpl transferenciadao, LocalDAOImpl localdao, HttpServletRequest request) {
		this.servletContext = servletContext;		
		this.transferenciadao = transferenciadao;
		this.localdao = localdao;
		this.request = request;
	}

	public RelatorioTransferenciaSinteticoController() {
		this(null,null,null,null);
	}
	
	@Path("/transferenciasintetico/pdf")
	public Download pdfReport(int codigo) {
		Report report = null;
		report = generateTransferenciaSintetico(codigo);
		
		return new ReportDownload(report, ExportFormats.pdf());
	}

	
	private Report generateTransferenciaSintetico(int codigo) {
		Local local = localdao.getById(Local.class, codigo);
		List<Object[]> objects =  transferenciadao.pesquisaTransferenciaLocal(local);
		List<TransferenciaPatrimonio> transferenciaPatrimonios = new ArrayList<>();
		
		objects
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Local localOrigem  = localdao.getById(Local.class, (int) o[15]);
			Local localDestino = localdao.getById(Local.class, (int) o[16]);
			
			TransferenciaPatrimonio transferenciaPatrimonio = new 
					TransferenciaPatrimonio(o[0].toString(), o[1] == null ? "" : o[1].toString(), o[7].toString(),
							localOrigem.getSetor().getNomsetor(), localDestino.getSetor().getNomsetor(), localOrigem.getDesclocal(), localDestino.getDesclocal(), 
							o[9].toString(), o[10].toString(), o[11].toString(), o[12].toString(), o[13].toString(), o[14].toString(), null);
			
			transferenciaPatrimonios.add(transferenciaPatrimonio);
		});
		
		
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
				
		Locale localeBR = new Locale("pt", "BR");
		ImageIcon gto = new ImageIcon(logo);
		
		return new TransferenciaPatrimonioSinteticoPDF(transferenciaPatrimonios)
				.addParameter("logo",gto.getImage())
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}

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

import br.gov.mt.saude.cuiaba.dao.impl.PatrimonioDAOImpl;
import br.gov.mt.saude.cuiaba.model.Patrimonio;
import br.gov.mt.saude.cuiaba.report.PatrimonioRelatorioSinteticoPDF;
import br.gov.mt.saude.cuiaba.report.model.PatrimonioRelatorio;

@Controller
public class RelatorioPatrimonioSinteticoController {
	private final ServletContext servletContext;
	private final PatrimonioDAOImpl patrimoniodao;
	private final HttpServletRequest request;
	private String situacaoresultado = "";
	
	@Inject
	public RelatorioPatrimonioSinteticoController(ServletContext servletContext, PatrimonioDAOImpl patrimoniodao, HttpServletRequest reques) {
		this.servletContext = servletContext;
		this.patrimoniodao = patrimoniodao;
		request = reques;
	}
	
	public RelatorioPatrimonioSinteticoController() {
		this(null,null,null);
	}
	
	@Path("/relatoriosintetico/pdf")
	public Download pdfReport(String numeropatrimonio, String numeroserial, int situacao, int secretaria, int setor, int local,
			int grupo, int subgrupo, int material, int marca, int modelo, int fornecedor) {
		Report report = null;
		report = generateRelatorioSintetico(numeropatrimonio,numeroserial,situacao,secretaria,setor,local,
				grupo,subgrupo,material,marca,modelo,fornecedor);
		
		return new ReportDownload(report, ExportFormats.pdf());
	}

	
	private Report generateRelatorioSintetico(String numeropatrimonio, String numeroserial, int situacao, int secretaria, int setor, int local,
			int grupo, int subgrupo, int material, int marca, int modelo, int fornecedor) {
		List<PatrimonioRelatorio> patrimonioRelatorios = new ArrayList<>();
		
		List<Patrimonio> patrimonios = patrimoniodao.pesquisaPatrimonioGeral(secretaria,setor, local, grupo, subgrupo, material, marca, modelo, fornecedor, situacao, null, numeropatrimonio, numeroserial);
		
		patrimonios
		.stream()
		.filter(patrimonio -> patrimonio != null)
		.forEach(patrimonio ->{

			switch (patrimonio.getSituacaopatrimonio()) {
			case 1:
				situacaoresultado = "NOVO";
				break;
			case 2:
				situacaoresultado = "USADO";
				break;
			case 3:
				situacaoresultado = "QUEBRADO";
				break;
			case 4:
				situacaoresultado = "DOADO";
				break;
			default:
				break;
			}
			
			PatrimonioRelatorio patrimonioRelatorio = new PatrimonioRelatorio(patrimonio.getIdpatrimonio(), patrimonio.getSerialpatrimonio(),
					situacaoresultado, patrimonio.getLocal().getSetor().getSecretaria().getNomsecretaria(), patrimonio.getLocal().getSetor().getNomsetor(), 
					patrimonio.getLocal().getDesclocal(), patrimonio.getMaterial().getSubgrupo().getGrupo().getDescgrupo(), patrimonio.getMaterial().getSubgrupo().getDescsubgrupo(),
					patrimonio.getMaterial().getDescmaterial(), patrimonio.getModelo().getMarca().getDescmarca(), patrimonio.getModelo().getDescmodelo(), 
					patrimonio.getFornecedor().getRazaosocial(), null, null, 0);
			patrimonioRelatorios.add(patrimonioRelatorio);
			
		});
		
		
		String logo = servletContext.getRealPath("/assets/images/relatorio_logo.jpg");
				
		Locale localeBR = new Locale("pt", "BR");
		ImageIcon gto = new ImageIcon(logo);
		
		return new PatrimonioRelatorioSinteticoPDF(patrimonioRelatorios)
				.addParameter("logo",gto.getImage())
				.addParameter("JRParameter.REPORT_LOCALE", localeBR);
	}
	
}
